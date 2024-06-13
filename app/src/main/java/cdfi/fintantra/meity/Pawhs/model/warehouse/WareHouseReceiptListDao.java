package cdfi.fintantra.meity.Pawhs.model.warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WareHouseReceiptListDao {

    @SerializedName("context")
    @Expose
    private WareReceiptContextDao wareReceiptContextDao;

    public WareReceiptContextDao getWareReceiptContextDao() {
        return wareReceiptContextDao;
    }

    public void setWareReceiptContextDao(WareReceiptContextDao wareReceiptContextDao) {
        this.wareReceiptContextDao = wareReceiptContextDao;
    }
}
