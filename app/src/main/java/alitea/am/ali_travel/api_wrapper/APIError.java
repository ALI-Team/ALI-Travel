package alitea.am.ali_travel.api_wrapper;

/**
 * Created by axel on 04/03/17.
 */

public class APIError {
    private String errorCode;
    private String errorText;

    public APIError(String errorCode, String errorText) {
        this.errorCode = errorCode;
        this.errorText = errorText;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorText() {
        return errorText;
    }
}
