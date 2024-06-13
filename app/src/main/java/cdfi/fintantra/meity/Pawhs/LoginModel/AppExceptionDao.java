package cdfi.fintantra.meity.Pawhs.LoginModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppExceptionDao {

    @SerializedName("errorNumber")
    @Expose
    private String errorNumber;

    @SerializedName("errorDescription")
    @Expose
    private String errorDescription;

    public String getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(String errorNumber) {
        this.errorNumber = errorNumber;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
