package segn.code.ebank.dtos;

import java.util.Date;




import lombok.Data;

import segn.code.ebank.enums.OperationType;

@Data 
public class AccountOperationDTO {
	
private long id;
private Date operationDate;
private double amount;

private OperationType type;

private String description;


}