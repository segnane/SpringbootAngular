 package segn.code.ebank;


import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import segn.code.ebank.dtos.BankAccountDTO;
import segn.code.ebank.dtos.CurrentBankAccountDTO;
import segn.code.ebank.dtos.CustomerDTO;
import segn.code.ebank.dtos.SavingBankAccountDTO;
import segn.code.ebank.entities.AccountOperation;
import segn.code.ebank.entities.BankAccount;
import segn.code.ebank.entities.CurrentAccount;
import segn.code.ebank.entities.Customer;
import segn.code.ebank.entities.SavingAccount;
import segn.code.ebank.enums.AccountsStatus;
import segn.code.ebank.enums.OperationType;
import segn.code.ebank.exception.BalanceNotSufficientException;
import segn.code.ebank.exception.BankAccountNotFoundException;
import segn.code.ebank.exception.CustomerNotFoundException;
import segn.code.ebank.repository.AccountOperationRepository;
import segn.code.ebank.repository.BankAccountRepository;
import segn.code.ebank.repository.CustomerRepository;
import segn.code.ebank.service.BankAccountService;


@SpringBootApplication
public class EbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankApplication.class, args);
	}
 /**
 * @param customerRepository
 * @param bankAccountRepository 
 * @param accountOperationRepository  
 * @return
 */
@Bean
CommandLineRunner commandLineRunner(BankAccountService bankAccountService)
{
	return args ->{
		
		
		
		
		  Stream.of("segnane","diop","ndiaye").forEach(name->{ CustomerDTO
		  customerDTO=new CustomerDTO(); 
		  customerDTO.setName(name);
		  customerDTO.setEmail(name+"senegal@gmail.com");
		  bankAccountService.saveCustomerDTO(customerDTO);
		  
		  });
		 
		 
  
		
		
		
		
		
		
		
		
/*CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {
	return args ->{
		Stream.of("segnane","diop","ndiaye").forEach(name->{
			Customer customer=new Customer();
			customer.setName(name);
			customer.setEmail(name+"senegal@gmail.com");
			bankAccountService.saveCustomer(customer);
			  
		});*/
		bankAccountService.listCustomerDTO().forEach(customer->{
			try {
				bankAccountService.saveCurrentBankAccountDTO(Math.random()*100, 4000, customer.getId());
				bankAccountService.saveSavingBankAccountDTO(Math.random()*100, 4000, customer.getId());
				
			} catch (CustomerNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		});
		List<BankAccountDTO> bankAccounts=bankAccountService.listAccount();
		for(BankAccountDTO bankAccount:bankAccounts) {
			for(int i =0;i<10;i++)
		{ String accountId; 
		if(bankAccount instanceof SavingBankAccountDTO) 
		{
			accountId=((SavingBankAccountDTO) bankAccount).getId();
			}
		
		else { 
			accountId =((CurrentBankAccountDTO) bankAccount).getId();
			}
				
				bankAccountService.credit(accountId, 10000, "credit");
				bankAccountService.debit(accountId, 1000,"debit");
			}
		}
	};
	 
}
 /*CommandLineRunner start(CustomerRepository customerRepository,
		 BankAccountRepository bankAccountRepository,AccountOperationRepository accountOperationRepository )
 { 
	return args ->{
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
 }*/
 }
