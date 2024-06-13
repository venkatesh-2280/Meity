package cdfi.fintantra.meity.Pawhs.model.lotmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewEstimateProcurementListDao {

    @SerializedName("context")
    @Expose
    private LotNoContextDao lotNoContextDao;

    public LotNoContextDao getLotNoContextDao() {
        return lotNoContextDao;
    }

    public void setLotNoContextDao(LotNoContextDao lotNoContextDao) {
        this.lotNoContextDao = lotNoContextDao;
    }
}
