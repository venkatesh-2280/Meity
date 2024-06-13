package cdfi.fintantra.meity.Pawhs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActualProcurementDocumentDao {

    @SerializedName("context")
    @Expose
    private ActualProcurementContextDao actualProcurementContextDao;

    public ActualProcurementContextDao getActualProcurementContextDao() {
        return actualProcurementContextDao;
    }

    public void setActualProcurementContextDao(ActualProcurementContextDao actualProcurementContextDao) {
        this.actualProcurementContextDao = actualProcurementContextDao;
    }
}
