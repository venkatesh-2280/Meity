package cdfi.fintantra.meity.Pawhs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NepsDocumentDao {

    @SerializedName("context")
    @Expose
    private NepsContextDao nepsContextDao;

    public NepsContextDao getNepsContextDao() {
        return nepsContextDao;
    }

    public void setNepsContextDao(NepsContextDao nepsContextDao) {
        this.nepsContextDao = nepsContextDao;
    }
}
