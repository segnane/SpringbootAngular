package segn.code.ebank.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
//import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import segn.code.ebank.dtos.AccountHistoryDTO;
import segn.code.ebank.dtos.AccountOperationDTO;
import segn.code.ebank.dtos.BankAccountDTO;
import segn.code.ebank.dtos.CurrentBankAccountDTO;
import segn.code.ebank.dtos.CustomerDTO;
import segn.code.ebank.dtos.SavingBankAccountDTO;
import segn.code.ebank.entities.AccountOperation;
import segn.code.ebank.entities.BankAccount;
import segn.code.ebank.entities.CurrentAccount;
import segn.code.ebank.entities.Customer;
import segn.code.ebank.entities.SavingAccount;
import segn.code.ebank.enums.OperationType;
import segn.code.ebank.exception.BalanceNotSufficientException;
import segn.code.ebank.exception.BankAccountNotFoundException;
import segn.code.ebank.exception.CustomerNotFoundException;
import segn.code.ebank.mappers.BankAccountMapperImpl;
import segn.code.ebank.repository.AccountOperationRepository;
import segn.code.ebank.repository.BankAccountRepository;
import segn.code.ebank.repository.CustomerRepository;
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {
	private CustomerRepository  customerRepository;
	private BankAccountRepository bankAccountRepository;
	private AccountOperationRepository accountOperationRepositorty;
	private BankAccountMapperImpl bankAccountMapperImpl;
	
	
	
	
	//Logger log =org.slf4j.LoggerFactory.getLogger(this.getClass().getName());// pour la journalisation 
	


	
	  @Override public CustomerDTO saveCustomerDTO(CustomerDTO customerDTO) { //
	  //  
		  log.info("saving customer");
		  Customer customer =bankAccountMapperImpl.fromCustomerDTO(customerDTO);
	  
	  Customer savingCustomer=customerRepository.save(customer);
	  
	  return bankAccountMapperImpl.fromCostumer(savingCustomer); }
	 

	  
	  
	  
	  
	  
	public Customer saveCustomer(Customer customer)
	{ log.info("saving new customer");
	Customer saveCustomer=customerRepository.save(customer);
		return saveCustomer;
	}
	@Override
	public SavingBankAccountDTO saveSavingBankAccountDTO(double initialBalance, long Overdraft, Long customerId)
			throws CustomerNotFoundException {
Customer customer =customerRepository.findById(customerId).orElse(null);
if(customer==null) 
	throw new CustomerNotFoundException("customer not found");
SavingAccount savingAccount =new SavingAccount();
savingAccount.setId(UUID.randomUUID().toString());
savingAccount.setCreatedAT(new Date());
savingAccount.setBalance(initialBalance);
savingAccount.setCustomer(customer);
savingAccount.setInterestRate(2345);
SavingAccount saveSAccount = bankAccountRepository.save(savingAccount);
		// TODO Auto-generated method stub
		return bankAccountMapperImpl.fromSavingBankAccount(saveSAccount);
	}



	
	
	

	@Override
	public CurrentBankAccountDTO saveCurrentBankAccountDTO(double initialBalance, long interestRate, Long customerId)
			throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		Customer customer =customerRepository.findById(customerId).orElse(null);
		if(customer==null) 
			throw new CustomerNotFoundException("customer not found");
		CurrentAccount currentAccount =new CurrentAccount();
		currentAccount.setId(UUID.randomUUID().toString());
		currentAccount.setCreatedAT(new Date());
		currentAccount.setBalance(initialBalance);
		currentAccount.setCustomer(customer);
		currentAccount.setOverDraft(1.5);
		CurrentAccount saveCAccount=bankAccountRepository.save(currentAccount);

		return bankAccountMapperImpl.fromCurrentBankAccount(saveCAccount);
	}
	
	
	
	
	
	

	@Override
	public List<CustomerDTO> listCustomerDTO() {
		// TODO Auto-generated method stub
	List<Customer> customers = customerRepository.findAll(); //programmation imperatif plus classique mais plus long
	List<CustomerDTO> customerDTOs= customers.stream().map(customer -> bankAccountMapperImpl.fromCostumer(customer)).collect(Collectors.toList());
	
	
	 
	/*List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>()
	 * for(Customer customer:customers) { CustomerDTO customerDTO
	 * =bankAccountMapperImpl.fromCostumer(customer); customerDTOs.add(customerDTO);
	 * }
	 */
	
		return customerDTOs;
	} 

	
	
	
	
	
	
	
	
	
	
	
	@Override
	public BankAccountDTO getBankAccountDTO(String accountId) throws BankAccountNotFoundException {
		// TODO Auto-generated method stub
		BankAccount bankAccount =bankAccountRepository.findById(accountId)
				.orElseThrow(()-> new BankAccountNotFoundException("bank account not found"));
		
		
		if(bankAccount instanceof SavingAccount) 
		{
			SavingAccount savingAccount =(SavingAccount) bankAccount;
		return bankAccountMapperImpl.fromSavingBankAccount(savingAccount);}
		else
		{
			
			CurrentAccount currentAccount =(CurrentAccount) bankAccount;
			return bankAccountMapperImpl.fromCurrentBankAccount(currentAccount);
		
		}
		
	}

	
	
	
	
	
	@Override
	public void debit(String accountId, double amount, String description) throws BalanceNotSufficientException , BankAccountNotFoundException {
		// TODO Auto-generated method stub
		BankAccount bankAccount =bankAccountRepository.findById(accountId)
				.orElseThrow(()-> new BankAccountNotFoundException("bank account not found"));
		 if(bankAccount.getBalance()<amount)
			 throw new BalanceNotSufficientException("Balance not sufficient");
		 AccountOperation accountOperation =new AccountOperation();
		 accountOperation.setType(OperationType.DEBIT);
		 accountOperation.setAmount(amount);
		 accountOperation.setDescription(description);
		 accountOperation.setBankAccount(bankAccount);
		 accountOperation.setOperationDate(new Date());
		 accountOperationRepositorty.save(accountOperation);
		bankAccount.setBalance(bankAccount.getBalance()-amount);
		bankAccountRepository.save(bankAccount);
	}

	
	
	@Override
	public void credit(String accountId, double amount, String description) throws  BankAccountNotFoundException {
		// TODO Auto-generated method stub
		BankAccount bankAccount =bankAccountRepository.findById(accountId)
				.orElseThrow(()-> new BankAccountNotFoundException("bank account not found"));
		 AccountOperation accountOperation =new AccountOperation();
		 accountOperation.setType(OperationType.CREDIT);
		 accountOperation.setAmount(amount);
		 accountOperation.setDescription(description);
		 accountOperation.setBankAccount(bankAccount);
		 accountOperation.setOperationDate(new Date());
		 accountOperationRepositorty.save(accountOperation);
		bankAccount.setBalance(bankAccount.getBalance()+amount);
		bankAccountRepository.save(bankAccount);
	}

	
	
	
	@Override
	public void transfert(String accountIdSource, String accountIdDestination, double amount) throws BalanceNotSufficientException, BankAccountNotFoundException {
		// TODO Auto-generated method stub
		debit(accountIdSource,amount,"transfert to "+accountIdDestination);
		credit(accountIdDestination,amount,"transfert from "+accountIdSource);
	}



	
	
	@Override
public List<BankAccountDTO> listAccount()
	{
	// TODO Auto-generated method stub
	List<BankAccount> bankAccounts= bankAccountRepository.findAll();
	 List<BankAccountDTO> listBankAccountDTO = bankAccounts.stream().map(bankAccount->
	{
		if(bankAccount instanceof SavingAccount)
	{ SavingAccount savingAccount=(SavingAccount) bankAccount;
	  
		return bankAccountMapperImpl.fromSavingBankAccount(savingAccount);
	}
		else
		{
		   CurrentAccount currentAccount =(CurrentAccount) bankAccount;
		   return bankAccountMapperImpl.fromCurrentBankAccount(currentAccount);
		}
	
		
	}).collect(Collectors.toList());
	 return listBankAccountDTO;
}


	
	       
	
	; @Override
public CustomerDTO getCustomerDTO(Long customerId) throws CustomerNotFoundException {
	// TODO Auto-generated method stub
	Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("customer not found "));
	return bankAccountMapperImpl.fromCostumer(customer) ;
}

	
	@Override 
	public CustomerDTO updateCustomerDTO(CustomerDTO customerDTO) { //
		  //  
			  log.info("saving customer");
			  Customer customer =bankAccountMapperImpl.fromCustomerDTO(customerDTO);
		  
		  Customer savingCustomer=customerRepository.save(customer);
		  
		  return bankAccountMapperImpl.fromCostumer(savingCustomer);
	}

@Override	 
public void deleteCustomer(Long CustomerId ) 
{ 
	 customerRepository.deleteById(CustomerId);
}




@Override

public List<AccountOperationDTO> accountHistory(String accountId)
{
 	List<AccountOperation> accountOperations=accountOperationRepositorty.findByBankAccountId(accountId);
return 	accountOperations.stream().map(operation->bankAccountMapperImpl.fromAccountOperation(operation)).collect(Collectors.toList());

}






  @Override public AccountHistoryDTO accountHistoryOperation(String accountId,
  int page, int size) throws BankAccountNotFoundException { BankAccount
  bankAccount = bankAccountRepository.findById(accountId).orElse(null);
  if(bankAccount==null) throw new
  BankAccountNotFoundException("account ,ot found");
  
  Page<AccountOperation> accountOperations =
  accountOperationRepositorty.findByBankAccountIdOrderByOperationDateDesc(accountId,
  PageRequest.of(page,size)); AccountHistoryDTO accountHistoryDTO = new
  AccountHistoryDTO(); List<AccountOperationDTO> accountOperationDTOS=
  accountOperations.getContent().stream().map(opera->bankAccountMapperImpl.
  fromAccountOperation(opera)).collect(Collectors.toList());
  accountHistoryDTO.setAccountOperationDTOs(accountOperationDTOS);
  accountHistoryDTO.setAccountId(bankAccount.getId());
  accountHistoryDTO.setBalance(bankAccount.getBalance());
  accountHistoryDTO.setPageSize(size);
  accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
  accountHistoryDTO.setCurrentPage(page); return accountHistoryDTO; }







@Override
public List<CustomerDTO> searchCustomers(String Keyword) {
	// TODO Auto-generated method stub
	List<Customer> customers = customerRepository.searchCustomer(Keyword); //programmation imperatif plus classique mais plus long
	List<CustomerDTO> customerDTOs= customers.stream().map(customer -> bankAccountMapperImpl.fromCostumer(customer)).collect(Collectors.toList());
	return customerDTOs;
}
 
}
