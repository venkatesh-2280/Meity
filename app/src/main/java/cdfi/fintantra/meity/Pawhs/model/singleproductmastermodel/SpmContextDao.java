package cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpmContextDao {
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
    private SpmHeaderDao spmHeaderDao;

    @SerializedName("header")
    @Expose
    private Spmheader spmheader;

    @SerializedName("detail")
    @Expose
    private List<SpmDetailDao> spmDetailDaoList;

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

    public SpmHeaderDao getSpmHeaderDao() {
        return spmHeaderDao;
    }

    public void setSpmHeaderDao(SpmHeaderDao spmHeaderDao) {
        this.spmHeaderDao = spmHeaderDao;
    }

    public Spmheader getSpmheader() {
        return spmheader;
    }

    public void setSpmheader(Spmheader spmheader) {
        this.spmheader = spmheader;
    }

    public List<SpmDetailDao> getSpmDetailDaoList() {
        return spmDetailDaoList;
    }

    public void setSpmDetailDaoList(List<SpmDetailDao> spmDetailDaoList) {
        this.spmDetailDaoList = spmDetailDaoList;
    }
}
