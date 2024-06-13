package cdfi.fintantra.meity.Pawhs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import cdfi.fintantra.meity.Pawhs.LoginModel.AppExceptionDao;


public class NewEstimateProcSaveDao {

    @SerializedName("document")
    @Expose
    private NepsDocumentDao nepsDocumentDao;

    @SerializedName("context")
    @Expose
    private NepsContextDao nepsContextDao;

    @SerializedName("applicationException")
    @Expose
    private AppExceptionDao appExceptionDao;

    public NepsDocumentDao getNepsDocumentDao() {
        return nepsDocumentDao;
    }

    public void setNepsDocumentDao(NepsDocumentDao nepsDocumentDao) {
        this.nepsDocumentDao = nepsDocumentDao;
    }

    public NepsContextDao getNepsContextDao() {
        return nepsContextDao;
    }

    public void setNepsContextDao(NepsContextDao nepsContextDao) {
        this.nepsContextDao = nepsContextDao;
    }

    public AppExceptionDao getAppExceptionDao() {
        return appExceptionDao;
    }

    public void setAppExceptionDao(AppExceptionDao appExceptionDao) {
        this.appExceptionDao = appExceptionDao;
    }
}
