package eco.com.spring.mcsv.account.mov.responses;

/**
 * @author edisoncsi on 06/05/24
 * @project McsvBackendJava
 */

public class BaseResponse {
    private String message;

    public BaseResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
