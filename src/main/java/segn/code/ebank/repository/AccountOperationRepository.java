package segn.code.ebank.repository;

//import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import segn.code.ebank.entities.AccountOperation;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
	
List<AccountOperation> findByBankAccountId(String accountID);
Page<AccountOperation> findByBankAccountIdOrderByOperationDateDesc(String accountId,Pageable pageable);
}
