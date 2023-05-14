package segn.code.ebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import segn.code.ebank.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
