package segn.code.ebank.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Setter
public class Customer {
	
	@Id @GeneratedValue(strategy =GenerationType.IDENTITY)
private long id;
private String name;
private String email;
@OneToMany(mappedBy="customer")
private List<BankAccount> bankAccounts;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
}