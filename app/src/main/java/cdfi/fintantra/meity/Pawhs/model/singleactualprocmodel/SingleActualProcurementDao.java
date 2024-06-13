package cdfi.fintantra.meity.Pawhs.model.singleactualprocmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleActualProcurementDao {

    @SerializedName("context")
    @Expose
    private SingleActProcContextDao singleActProcContextDao;

    public SingleActProcContextDao getSingleActProcContextDao() {
        return singleActProcContextDao;
    }

    public void setSingleActProcContextDao(SingleActProcContextDao singleActProcContextDao) {
        this.singleActProcContextDao = singleActProcContextDao;
    }
}
