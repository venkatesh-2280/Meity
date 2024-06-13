package cdfi.fintantra.meity.Pawhs.model.lotmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EstimateActualApprovedListDao {

    @SerializedName("context")
    @Expose
    private EstActAppContextDao estActAppContextDao;

    public EstActAppContextDao getEstActAppContextDao() {
        return estActAppContextDao;
    }

    public void setEstActAppContextDao(EstActAppContextDao estActAppContextDao) {
        this.estActAppContextDao = estActAppContextDao;
    }
}
