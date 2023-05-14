package segn.code.ebank.entities;

import java.util.Date;
import java.util.List;



import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import segn.code.ebank.enums.AccountsStatus;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",length=4,discriminatorType =DiscriminatorType.STRING )
//l anotation au dessus permet de gere la creation des 
//classes et de leur heritatoin pour single tableau tout les classe qui vont 
//heriter de BankAccount sont regroupe  dans la table bankAccount 
@Data @NoArgsConstructor @AllArgsConstructor
public class BankAccount {
@Id
	private String id;
	private double balance;
	private Date createdAT;
	@Enumerated(EnumType.STRING)
	private AccountsStatus status;
	@ManyToOne
	private Customer customer;
	@OneToMany(mappedBy="bankAccount")
	private List<AccountOperation> accountOperations;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getBalance() {
		return balance;
	}
	public void setStatus(AccountsStatus status) {
		this.status = status;
	}
	public AccountsStatus getStatus() {
		return status;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCreatedAT(Date createdAT) {
		this.createdAT = createdAT;
	}
	public Date getCreatedAT() {
		return createdAT;
	}
}
