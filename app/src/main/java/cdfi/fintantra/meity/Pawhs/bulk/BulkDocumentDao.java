package cdfi.fintantra.meity.Pawhs.bulk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BulkDocumentDao {

    @SerializedName("context")
    @Expose
    private BulkContextDao bulkContextDao;

    public BulkContextDao getBulkContextDao() {
        return bulkContextDao;
    }

    public void setBulkContextDao(BulkContextDao bulkContextDao) {
        this.bulkContextDao = bulkContextDao;
    }
}
