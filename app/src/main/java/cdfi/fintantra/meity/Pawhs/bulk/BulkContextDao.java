package cdfi.fintantra.meity.Pawhs.bulk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BulkContextDao {

    @SerializedName("orgnId")
    @Expose
    private String orgnId;

    @SerializedName("locnId")
    @Expose
    private String locnId;

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("localeId")
    @Expose
    private String localeId;

    @SerializedName("Header")
    @Expose
    private BulkHeaderDao bulkHeaderDao;

    @SerializedName("Esitmated_Prod")
    @Expose
    private List<BulkEstProdDao> bulkEstProdDaoList;

    @SerializedName("Actual_Procurment")
    @Expose
    private List<BulkActualProcurementDao> bulkActualProcurementDaoList;

    @SerializedName("NewWareHouseReceipt")
    @Expose
    private List<BulkNewWareHouseReceiptDao> bulkNewWareHouseReceiptDaoList;

    public String getOrgnId() {
        return orgnId;
    }

    public void setOrgnId(String orgnId) {
        this.orgnId = orgnId;
    }

    public String getLocnId() {
        return locnId;
    }

    public void setLocnId(String locnId) {
        this.locnId = locnId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocaleId() {
        return localeId;
    }

    public void setLocaleId(String localeId) {
        this.localeId = localeId;
    }

    public BulkHeaderDao getBulkHeaderDao() {
        return bulkHeaderDao;
    }

    public void setBulkHeaderDao(BulkHeaderDao bulkHeaderDao) {
        this.bulkHeaderDao = bulkHeaderDao;
    }

    public List<BulkEstProdDao> getBulkEstProdDaoList() {
        return bulkEstProdDaoList;
    }

    public void setBulkEstProdDaoList(List<BulkEstProdDao> bulkEstProdDaoList) {
        this.bulkEstProdDaoList = bulkEstProdDaoList;
    }

    public List<BulkActualProcurementDao> getBulkActualProcurementDaoList() {
        return bulkActualProcurementDaoList;
    }

    public void setBulkActualProcurementDaoList(List<BulkActualProcurementDao> bulkActualProcurementDaoList) {
        this.bulkActualProcurementDaoList = bulkActualProcurementDaoList;
    }

    public List<BulkNewWareHouseReceiptDao> getBulkNewWareHouseReceiptDaoList() {
        return bulkNewWareHouseReceiptDaoList;
    }

    public void setBulkNewWareHouseReceiptDaoList(List<BulkNewWareHouseReceiptDao> bulkNewWareHouseReceiptDaoList) {
        this.bulkNewWareHouseReceiptDaoList = bulkNewWareHouseReceiptDaoList;
    }
}
