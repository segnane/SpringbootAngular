package segn.code.ebank.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import segn.code.ebank.dtos.AccountOperationDTO;
import segn.code.ebank.dtos.CurrentBankAccountDTO;
import segn.code.ebank.dtos.CustomerDTO;
import segn.code.ebank.dtos.SavingBankAccountDTO;
import segn.code.ebank.entities.AccountOperation;
import segn.code.ebank.entities.CurrentAccount;
import segn.code.ebank.entities.Customer;
import segn.code.ebank.entities.SavingAccount;
// framework pour faire le mapping comme Mapstruct
@Service
public class BankAccountMapperImpl {
	
public CustomerDTO fromCostumer(Customer customer) 
{
CustomerDTO customerDTO = new CustomerDTO();
BeanUtils.copyProperties(customer, customerDTO);
/*
 * customerDTO.setId(customer.getId()); customerDTO.setName(customer.getName());
 * customerDTO.setEmail(customer.getEmail());
 */	
	return customerDTO; 
}

public Customer fromCustomerDTO(CustomerDTO customerDTO) 
{
	Customer customer =new Customer();
	BeanUtils.copyProperties(customerDTO, customer);
	/*
	 * customer.setId(customerDTO.getId()); customer.setName(customerDTO.getName());
	 * customer.setEmail(customerDTO.getEmail());
	 */
	return customer;
}

public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount)
{   SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO()  ;
BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
savingBankAccountDTO.setCustomerDTO(fromCostumer(savingAccount.getCustomer()));
savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
	return savingBankAccountDTO;
	}


public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO)
{ SavingAccount savingAccount = new SavingAccount()  ;
BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));

return savingAccount;

	}

public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount)
{ CurrentBankAccountDTO currentBankAccountDTO =new CurrentBankAccountDTO();
BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
currentBankAccountDTO.setCustomerDTO(fromCostumer(currentAccount.getCustomer()));
currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
	return currentBankAccountDTO;
	}


public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO)
{ CurrentAccount currentAccount =new CurrentAccount();
BeanUtils.copyProperties(currentBankAccountDTO, currentAccount);
currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
	return currentAccount;
	}


public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation)
{
	AccountOperationDTO accountOperationDTO=new AccountOperationDTO();
BeanUtils.copyProperties(accountOperation, accountOperationDTO);
return accountOperationDTO;
}
}
