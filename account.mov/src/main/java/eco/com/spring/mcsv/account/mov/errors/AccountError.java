package eco.com.spring.mcsv.account.mov.errors;

import eco.com.spring.mcsv.account.mov.responses.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@Component
public class AccountError {

    private final Logger logger = Logger.getLogger(AccountError.class.getName());

    public ResponseEntity<?> handleBadRequest(Exception e) {
        logger.log(Level.WARNING,  String.format("Bad request: %s " , e.getMessage()));
        return ResponseEntity.badRequest().body(new eco.com.spring.mcsv.account.mov.responses.BaseResponse(e.getMessage()));
    }

    public ResponseEntity<?> handleInternalError(Exception e) {
        logger.log(Level.SEVERE, String.format("Internal server error:  %s",  e.getMessage()), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse("Internal server error"));
    }

    public Map<String, String> validationErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return errors;
    }
}
