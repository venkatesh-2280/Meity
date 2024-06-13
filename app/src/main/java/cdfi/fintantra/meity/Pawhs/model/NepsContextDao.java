package cdfi.fintantra.meity.Pawhs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cdfi.fintantra.meity.Pawhs.Estqtydao;


public class NepsContextDao {

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
    private NepsHeaderDao nepsHeaderDao;

    @SerializedName("header")
    @Expose
    private NepsHeaderDao nepsheaderDao;

    public List<Estqtydao> getPostQtyDetailDaoList() {
        return postQtyDetailDaoList;
    }

    public void setPostQtyDetailDaoList(List<Estqtydao> postQtyDetailDaoList) {
        this.postQtyDetailDaoList = postQtyDetailDaoList;
    }

    @SerializedName("QtyDetail")
    @Expose
    private List<Estqtydao> postQtyDetailDaoList;

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

    public NepsHeaderDao getNepsHeaderDao() {
        return nepsHeaderDao;
    }

    public void setNepsHeaderDao(NepsHeaderDao nepsHeaderDao) {
        this.nepsHeaderDao = nepsHeaderDao;
    }

    public NepsHeaderDao getNepsheaderDao() {
        return nepsheaderDao;
    }

    public void setNepsheaderDao(NepsHeaderDao nepsheaderDao) {
        this.nepsheaderDao = nepsheaderDao;
    }
}
