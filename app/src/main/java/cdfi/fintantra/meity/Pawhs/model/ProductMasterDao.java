package cdfi.fintantra.meity.Pawhs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductMasterDao {

    @SerializedName("context")
    @Expose
    private PmContextDao pmContextDao;

    public PmContextDao getPmContextDao() {
        return pmContextDao;
    }

    public void setPmContextDao(PmContextDao pmContextDao) {
        this.pmContextDao = pmContextDao;
    }
}
