package cdfi.fintantra.meity.Pawhs.formermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormerListDao {

    @SerializedName("context")
    @Expose
    private FormerContextDao formerContextDao;

    public FormerContextDao getFormerContextDao() {
        return formerContextDao;
    }

    public void setFormerContextDao(FormerContextDao formerContextDao) {
        this.formerContextDao = formerContextDao;
    }
}
