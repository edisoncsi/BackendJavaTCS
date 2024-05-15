package eco.com.spring.mcsv.account.client.responses;

import lombok.Getter;
import lombok.Setter;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

@Setter
@Getter
public class BaseResponse {
    private String message;

    public BaseResponse(String message) {
        this.message = message;
    }

}
