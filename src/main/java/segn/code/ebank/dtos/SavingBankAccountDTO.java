package segn.code.ebank.dtos;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import segn.code.ebank.enums.AccountsStatus;


@Data  @AllArgsConstructor @NoArgsConstructor
public class SavingBankAccountDTO extends BankAccountDTO {

	 
	private String id;
	private double balance;
	private Date createdAT;
	private AccountsStatus status;
	private CustomerDTO customerDTO;
	private double InteresRate;
	
}
