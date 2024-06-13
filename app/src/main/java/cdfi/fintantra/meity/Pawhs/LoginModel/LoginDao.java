package cdfi.fintantra.meity.Pawhs.LoginModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDao {

    @SerializedName("context")
    @Expose
    private ContextDao contextDao;

    @SerializedName("ApplicationException")
    @Expose
    private AppExceptionDao ApplicationException;

    public ContextDao getContextDao() {
        return contextDao;
    }

    public void setContextDao(ContextDao contextDao) {
        this.contextDao = contextDao;
    }

    public AppExceptionDao getApplicationException() {
        return ApplicationException;
    }

    public void setApplicationException(AppExceptionDao applicationException) {
        ApplicationException = applicationException;
    }
}
