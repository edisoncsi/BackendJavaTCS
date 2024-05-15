package eco.com.spring.mcsv.account.mov.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class DepositWithdrawals {

    private String client;
    private String date;
}
