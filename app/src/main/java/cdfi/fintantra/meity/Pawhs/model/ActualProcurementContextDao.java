package cdfi.fintantra.meity.Pawhs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActualProcurementContextDao {

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
    private ActualProcurementHeaderDao actualProcurementHeaderDao;

    @SerializedName("QtyDetail")
    @Expose
    private List<PostQtyDetailDao> postQtyDetailDaoList;

    @SerializedName("OtherDetail")
    @Expose
    private List<PostOtherDetailDao> postOtherDetailDaoList;

    @SerializedName("SlnoDetail")
    @Expose
    private List<PostSiNoDetailDao> postSiNoDetailDaoList;

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

    public ActualProcurementHeaderDao getActualProcurementHeaderDao() {
        return actualProcurementHeaderDao;
    }

    public void setActualProcurementHeaderDao(ActualProcurementHeaderDao actualProcurementHeaderDao) {
        this.actualProcurementHeaderDao = actualProcurementHeaderDao;
    }

    public List<PostQtyDetailDao> getPostQtyDetailDaoList()      {
        return postQtyDetailDaoList;
    }

    public void setPostQtyDetailDaoList(List<PostQtyDetailDao> postQtyDetailDaoList) {
        this.postQtyDetailDaoList = postQtyDetailDaoList;
    }

    public List<PostOtherDetailDao> getPostOtherDetailDaoList() {
        return postOtherDetailDaoList;
    }

    public void setPostOtherDetailDaoList(List<PostOtherDetailDao> postOtherDetailDaoList) {
        this.postOtherDetailDaoList = postOtherDetailDaoList;
    }

    public List<PostSiNoDetailDao> getPostSiNoDetailDaoList() {
        return postSiNoDetailDaoList;
    }

    public void setPostSiNoDetailDaoList(List<PostSiNoDetailDao> postSiNoDetailDaoList) {
        this.postSiNoDetailDaoList = postSiNoDetailDaoList;
    }
}
