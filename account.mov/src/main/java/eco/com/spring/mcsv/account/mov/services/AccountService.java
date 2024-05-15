package eco.com.spring.mcsv.account.mov.services;

import eco.com.spring.mcsv.account.mov.dtos.AccountDto;
import eco.com.spring.mcsv.account.mov.models.Account;

import java.util.List;
import java.util.Optional;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

public interface AccountService {
    List<Account> listAccounts();

    Optional<Account> getAccount(Long id);

    Optional<Account> numberAccount(String numAccount);

    Optional<Account> updateAccount(Long id, AccountDto cuenta);

    void insertAccount(Account account);

    boolean getAccountById(Long id);

    void deleteAccount(Long id);
}
