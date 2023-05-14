package segn.code.ebank;


import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import segn.code.ebank.entities.AccountOperation;
import segn.code.ebank.entities.CurrentAccount;
import segn.code.ebank.entities.Customer;
import segn.code.ebank.entities.SavingAccount;
import segn.code.ebank.enums.AccountsStatus;
import segn.code.ebank.enums.OperationType;
import segn.code.ebank.repository.AccountOperationRepository;
import segn.code.ebank.repository.BankAccountRepository;
import segn.code.ebank.repository.CustomerRepository;

@SpringBootApplication
public class EbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankApplication.class, args);
	}
 @Bean
 CommandLineRunner start(CustomerRepository customerRepository,
		 BankAccountRepository bankAccountRepository,AccountOperationRepository accountOperationRepository )
 { return args ->{
	 Stream.of("pablo","tafa","picasso").forEach(name-> {
		 Customer customer =new Customer();
		 customer.setName(name);
		 customer.setEmail(name+"@gmail.com");
		 customerRepository.save(customer);
	 });
	 customerRepository.findAll().forEach(cust-> {
		 CurrentAccount currentAccount = new CurrentAccount();
		 currentAccount.setId(UUID.randomUUID().toString());
		 currentAccount.setBalance(Math.random()*4000);
		 currentAccount.setCreatedAT(new Date());
		 currentAccount.setStatus(AccountsStatus.CREATED);
		 currentAccount.setCustomer(cust);
		 currentAccount.setOverDraft(2000);
		 bankAccountRepository.save(currentAccount);
		 
		 
		 SavingAccount savingAccount = new SavingAccount();
		 savingAccount.setId(UUID.randomUUID().toString());
		 savingAccount.setBalance(Math.random()*4000);
		 savingAccount.setCreatedAT(new Date());
		 savingAccount.setStatus(AccountsStatus.CREATED);
		 savingAccount.setCustomer(cust);
		 savingAccount.setInterestRate(5.5);
		 bankAccountRepository.save(savingAccount);
		 
		 
	 });
	 
	 bankAccountRepository.findAll().forEach(acc->{
		 for (int i=0;i<10;i++)
		 {
			 AccountOperation accountOperation=new AccountOperation();
			 accountOperation.setOperationDate(new Date());
			 accountOperation.setAmount(Math.random()*500);
			 accountOperation.setType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
			 accountOperation.setBankAccount(acc);
			 accountOperationRepository.save(accountOperation);
		 }
	 });
 };
 }
}
