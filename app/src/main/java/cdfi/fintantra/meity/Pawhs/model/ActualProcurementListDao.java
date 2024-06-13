package cdfi.fintantra.meity.Pawhs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActualProcurementListDao {

    @SerializedName("context")
    @Expose
    private ActProContextDao actProContextDao;

    public ActProContextDao getActProContextDao() {
        return actProContextDao;
    }

    public void setActProContextDao(ActProContextDao actProContextDao) {
        this.actProContextDao = actProContextDao;
    }
}
