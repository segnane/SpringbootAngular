package segn.code.ebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import segn.code.ebank.entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, String>{

}
