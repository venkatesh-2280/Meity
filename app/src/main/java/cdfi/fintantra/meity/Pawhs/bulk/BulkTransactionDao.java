package cdfi.fintantra.meity.Pawhs.bulk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BulkTransactionDao {

    @SerializedName("document")
    @Expose
    private BulkDocumentDao bulkDocumentDao;

    private String[] generateLotNO;

    public BulkDocumentDao getBulkDocumentDao() {
        return bulkDocumentDao;
    }

    public void setBulkDocumentDao(BulkDocumentDao bulkDocumentDao) {
        this.bulkDocumentDao = bulkDocumentDao;
    }

    public String[] getGenerateLotNO() {
        return generateLotNO;
    }

    public void setGenerateLotNO(String[] generateLotNO) {
        this.generateLotNO = generateLotNO;
    }
}
