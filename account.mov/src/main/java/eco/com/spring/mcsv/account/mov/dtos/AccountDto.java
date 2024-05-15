package eco.com.spring.mcsv.account.mov.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class AccountDto {

    @NotNull
    @JsonProperty("number_account")
    private  String numberAccount;

    @NotNull
    @JsonProperty("account_type")
    private String AccountType;

    @NotNull
    @JsonProperty("initial_balance")
    private Float InitialBalance;

    @NotNull
    private String estate;

    @NotNull
    @JsonProperty("name_client")
    private String nameClient;
}
