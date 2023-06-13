package segn.code.ebank.dtos;

import lombok.Data;

@Data
public class TransfertRequestDTO {
	private String accountSource;
	private String accountDestination;
	private double amount;
	private String description;
	
	
}
