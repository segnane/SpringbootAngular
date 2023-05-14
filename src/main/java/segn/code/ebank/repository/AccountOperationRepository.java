package segn.code.ebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import segn.code.ebank.entities.AccountOperation;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {

}
