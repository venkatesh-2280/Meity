package cdfi.fintantra.meity.Pawhs.model.lotmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewEstimateProcurementSingleDao {

    @SerializedName("context")
    @Expose
    private SingleContextDao singleContextDao;

    public SingleContextDao getSingleContextDao() {
        return singleContextDao;
    }

    public void setSingleContextDao(SingleContextDao singleContextDao) {
        this.singleContextDao = singleContextDao;
    }
}
