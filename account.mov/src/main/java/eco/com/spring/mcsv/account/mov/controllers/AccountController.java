package eco.com.spring.mcsv.account.mov.controllers;

import eco.com.spring.mcsv.account.mov.dtos.AccountDto;
import eco.com.spring.mcsv.account.mov.errors.AccountError;
import eco.com.spring.mcsv.account.mov.models.Account;
import eco.com.spring.mcsv.account.mov.responses.BaseResponse;
import eco.com.spring.mcsv.account.mov.services.AccountService;
import eco.com.spring.mcsv.account.mov.services.impl.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @Autowired
    private AccountError accountError;

    @GetMapping
    public ResponseEntity<?> listAccount() {
        return ResponseEntity.ok(accountService.listAccounts());
    }

    @GetMapping({"/{numAccount}"})
    public ResponseEntity<?> numberAccount(@PathVariable("numAccount") String numAccount) {
        return ResponseEntity.ok(accountService.numberAccount(numAccount));
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountDto command, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(accountError.validationErrors(result));
        }

        try {
            Long clientId= kafkaConsumerService.getDataByNameClient(command.getNameClient());
            if(null != clientId)
            {
                Optional<Account> temp = accountService.numberAccount(command.getNumberAccount());

                if(temp.isPresent()){
                    return new ResponseEntity<>(new BaseResponse("El numero de cuenta ya existe"), HttpStatus.NOT_FOUND);
                }

                if(command.getInitialBalance() <= 0.0f)
                {
                    return new ResponseEntity<>(new BaseResponse("El saldo inicial debe ser positivo"), HttpStatus.NOT_FOUND);
                }

                Account account = Account.builder()
                    .numberAccount(command.getNumberAccount())
                    .accountType(command.getAccountType())
                    .initialBalance(command.getInitialBalance())
                    .estate(command.getEstate())
                    .client(command.getNameClient())
                    .build();

                accountService.insertAccount(account);
            }
            else{
                return new ResponseEntity<>(new BaseResponse("El cliente no existe"), HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(new BaseResponse("Datos creados exitosamente"), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return accountError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return accountError.handleInternalError(e);
        }
    }

    @PutMapping({"/{accountId}"})
    public ResponseEntity<?> updateAccount(@PathVariable("accountId") Long accountId, @Valid @RequestBody AccountDto command, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(accountError.validationErrors(result));
        }

        try {
            Optional<Account> account = accountService.updateAccount(accountId, command);
            if (account.isPresent())
                return new ResponseEntity<>(new BaseResponse("Se actualizo exitosamente"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new BaseResponse("La cuenta no existe"), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return accountError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return accountError.handleInternalError(e);
        }
    }

    @DeleteMapping({"/{accountId}"})
    public ResponseEntity<?> deleteAccount(@PathVariable("accountId") Long accountId) {
        try {
            if (accountService.getAccountById(accountId)){
                accountService.deleteAccount(accountId);
                return new ResponseEntity<>(new BaseResponse("Se ha eliminado exitosamente"), HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(new  BaseResponse("El cliente no existe"), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return accountError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return accountError.handleInternalError(e);
        }
    }
}
