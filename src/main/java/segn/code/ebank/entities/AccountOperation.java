package segn.code.ebank.entities;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import segn.code.ebank.enums.OperationType;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AccountOperation {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;
private Date operationDate;
private double amount;
@Enumerated(EnumType.STRING)
private OperationType type;
@ManyToOne
private BankAccount bankAccount;
private String description;


public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
	
	
}
public Date getOperationDate() {
	return operationDate;
}
public void setOperationDate(Date operationDate) {
	this.operationDate = operationDate;
}



public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
	
	
	
}
public OperationType getType() {
	return type;
}



public void setType(OperationType type) {
	this.type = type;
}



public BankAccount getBankAccount() {
	return bankAccount;
}


public void setBankAccount(BankAccount bankAccount) {
	this.bankAccount = bankAccount;
	
}


}
