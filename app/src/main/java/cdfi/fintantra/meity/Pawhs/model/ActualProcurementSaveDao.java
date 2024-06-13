package cdfi.fintantra.meity.Pawhs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActualProcurementSaveDao {

    @SerializedName("document")
    @Expose
    private ActualProcurementDocumentDao actualProcurementDocumentDao;

    public ActualProcurementContextDao getActualProcurementContextDao() {
        return actualProcurementContextDao;
    }

    public void setActualProcurementContextDao(ActualProcurementContextDao actualProcurementContextDao) {
        this.actualProcurementContextDao = actualProcurementContextDao;
    }

    @SerializedName("context")
    @Expose
    private ActualProcurementContextDao actualProcurementContextDao;

    public ActualProcurementDocumentDao getActualProcurementDocumentDao() {
        return actualProcurementDocumentDao;
    }

    public void setActualProcurementDocumentDao(ActualProcurementDocumentDao actualProcurementDocumentDao) {
        this.actualProcurementDocumentDao = actualProcurementDocumentDao;
    }
}
