package segn.code.ebank.dtos;

import java.util.Date;

import lombok.Data;
@Data
public class DebitDTO {
private String accountId;
private double amount;
private String description;
private  Date date;
}
