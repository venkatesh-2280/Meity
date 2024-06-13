package cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleProductMasterDao {

    @SerializedName("context")
    @Expose
    private SpmContextDao spmContextDao;

    public SpmContextDao getSpmContextDao() {
        return spmContextDao;
    }

    public void setSpmContextDao(SpmContextDao spmContextDao) {
        this.spmContextDao = spmContextDao;
    }
}
