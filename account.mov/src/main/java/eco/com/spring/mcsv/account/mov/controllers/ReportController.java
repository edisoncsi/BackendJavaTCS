package eco.com.spring.mcsv.account.mov.controllers;

import eco.com.spring.mcsv.account.mov.dtos.DepositWithdrawals;
import eco.com.spring.mcsv.account.mov.dtos.ReporteDto;
import eco.com.spring.mcsv.account.mov.services.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private MovementService movementService;

    @PostMapping
    public ResponseEntity<?> listReportMovements(@Valid @RequestBody DepositWithdrawals findData, BindingResult result) {

        if (result.hasErrors()) {
            return (ResponseEntity<?>) validationErrors(result);
        }

        List<Object[]> listReport = movementService.listMovements(findData);
        if (listReport.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else{
            List<ReporteDto> newReport = listReport.stream()
                    .map(ss -> new ReporteDto(
                            String.valueOf(ss[0]),
                            String.valueOf(ss[1]),
                            String.valueOf(ss[2]),
                            String.valueOf(ss[3]),
                            String.valueOf(ss[4]),
                            String.valueOf(ss[5]),
                            String.valueOf(ss[6]),
                            String.valueOf(ss[7])
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(newReport);
        }
    }

    private Map<String, String> validationErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return errors;
    }
}
