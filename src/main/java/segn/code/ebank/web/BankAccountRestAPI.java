 package segn.code.ebank.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import segn.code.ebank.dtos.AccountHistoryDTO;
import segn.code.ebank.dtos.AccountOperationDTO;
import segn.code.ebank.dtos.BankAccountDTO;
import segn.code.ebank.dtos.CreditDTO;
import segn.code.ebank.dtos.DebitDTO;
import segn.code.ebank.dtos.TransfertRequestDTO;
import segn.code.ebank.entities.AccountOperation;
import segn.code.ebank.exception.BalanceNotSufficientException;
import segn.code.ebank.exception.BankAccountNotFoundException;
import segn.code.ebank.service.BankAccountService;
@RestController
@CrossOrigin("*")
public class BankAccountRestAPI {
	private BankAccountService bankAccountService;
	public BankAccountRestAPI(BankAccountService bankAccountService)
	{
		
		 this.bankAccountService=bankAccountService;
	}
@GetMapping("/accounts/{accountId}")
public BankAccountDTO getBankAccountDTO(@PathVariable String accountId) throws BankAccountNotFoundException 
{
	
	return bankAccountService.getBankAccountDTO(accountId);
}

@GetMapping("/accounts") 
public List<BankAccountDTO> listOfAccount() 
{
	return bankAccountService.listAccount();
	}

	
	  @GetMapping("/account/{accountId}/pageOperations") public AccountHistoryDTO
	  getHistorique(@PathVariable String accountId
	  ,@RequestParam(name="page",defaultValue = "0")int page,
	  
	  @RequestParam(name = "size", defaultValue = "5")int size) throws
	  BankAccountNotFoundException { return
	  bankAccountService.accountHistoryOperation(accountId,page,size); }
	 


@GetMapping("/account/{accountId}/operation")
public List<AccountOperationDTO> getAccountHistorique(@PathVariable String accountId) 
{
	return bankAccountService.accountHistory(accountId);
}
@PostMapping("/accounts/debit")
public DebitDTO debit(@RequestBody DebitDTO debitDTO ) throws BalanceNotSufficientException, BankAccountNotFoundException {
	this.bankAccountService.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
	return debitDTO;
}
@PostMapping("/accounts/credit")
public CreditDTO credit(@RequestBody  CreditDTO creditDTO ) throws BalanceNotSufficientException, BankAccountNotFoundException {
	this.bankAccountService.debit(creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription());
	return creditDTO;
}
@PostMapping("/accounts/transfert")
public void transfert(@RequestBody TransfertRequestDTO transfertRequestDTO ) throws BalanceNotSufficientException, BankAccountNotFoundException
{
	this.bankAccountService.transfert
	(transfertRequestDTO.getAccountSource()
			,transfertRequestDTO.getAccountDestination(), transfertRequestDTO.getAmount());
}
}
