package eco.com.spring.mcsv.account.mov.services.impl;

import eco.com.spring.mcsv.account.mov.dtos.AccountDto;
import eco.com.spring.mcsv.account.mov.models.Account;
import eco.com.spring.mcsv.account.mov.repositories.AccountRepository;
import eco.com.spring.mcsv.account.mov.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public List<Account> listAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<Account> numberAccount(String numAccount) {
        return accountRepository.findByNumberAccount(numAccount);
    }

    @Override
    public void insertAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Optional<Account> updateAccount(Long id, AccountDto cuenta) {
        Optional<Account> cuentaFromDb = accountRepository.findByNumberAccount(id.toString());

        if(cuentaFromDb.isPresent()){
            Account _account = cuentaFromDb.get();
            _account.setNumberAccount(cuenta.getNumberAccount());
            _account.setAccountType(cuenta.getAccountType());
            _account.setInitialBalance(cuenta.getInitialBalance());
            _account.setClient(cuenta.getNameClient());
            _account.setEstate(cuenta.getEstate());
            accountRepository.save(_account);
        }
        return cuentaFromDb;
    }

    @Override
    public boolean getAccountById(Long id) {
        return accountRepository.findById(id).isPresent();
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
