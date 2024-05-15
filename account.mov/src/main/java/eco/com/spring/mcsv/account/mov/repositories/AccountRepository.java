package eco.com.spring.mcsv.account.mov.repositories;

import eco.com.spring.mcsv.account.mov.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@Repository
public interface AccountRepository extends CrudRepository<Account,Long> {
    Optional<Account> findByNumberAccount(String numAccount);
}
