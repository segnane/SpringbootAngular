package segn.code.ebank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import segn.code.ebank.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	//List<Customer> findByNameContains(String keyword ); premiere methode de recherche des customers 
	@Query("select cus from Customer cus where cus.name like :kw")
	List<Customer> searchCustomer(@Param("kw") String keyword);

}
