package segn.code.ebank.service;

import java.util.List;

import segn.code.ebank.dtos.AccountHistoryDTO;
import segn.code.ebank.dtos.AccountOperationDTO;
import segn.code.ebank.dtos.BankAccountDTO;
import segn.code.ebank.dtos.CurrentBankAccountDTO;
import segn.code.ebank.dtos.CustomerDTO;
import segn.code.ebank.dtos.SavingBankAccountDTO;
import segn.code.ebank.entities.BankAccount;
import segn.code.ebank.entities.CurrentAccount;
import segn.code.ebank.entities.Customer;
import segn.code.ebank.entities.SavingAccount;
import segn.code.ebank.exception.BalanceNotSufficientException;
import segn.code.ebank.exception.BankAccountNotFoundException;
import segn.code.ebank.exception.CustomerNotFoundException;

public interface BankAccountService {


SavingBankAccountDTO saveSavingBankAccountDTO(double initialBalance,long Overdraft ,Long customerId) throws CustomerNotFoundException;

CurrentBankAccountDTO saveCurrentBankAccountDTO(double initialBalance,long interestRate ,Long customerId) throws CustomerNotFoundException;

List<CustomerDTO> listCustomerDTO();



BankAccountDTO  getBankAccountDTO(String accountId) throws BankAccountNotFoundException;


void debit(String accountId,double amount,String description) throws BalanceNotSufficientException, BankAccountNotFoundException;


void credit(String accountId ,double amount,String description)throws BankAccountNotFoundException;




void transfert (String accountIdSource,String accountIdDestination,double amount) throws BalanceNotSufficientException, BankAccountNotFoundException;

List<BankAccountDTO> listAccount() ;

CustomerDTO getCustomerDTO(Long customerId) throws CustomerNotFoundException;

Customer saveCustomer(Customer customer);

CustomerDTO saveCustomerDTO(CustomerDTO customerDTO);

CustomerDTO updateCustomerDTO(CustomerDTO customerDTO);

void deleteCustomer(Long CustomerId);

List<AccountOperationDTO> accountHistory(String accountId);

AccountHistoryDTO accountHistoryOperation(String accountId, int page, int size) throws BankAccountNotFoundException;

List<CustomerDTO> searchCustomers(String Keyword);


}
