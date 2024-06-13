package cdfi.fintantra.meity.Pawhs.model.lotmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EstActAppContextDao {

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

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("estimate_List")
    @Expose
    private List<EstimateListDao> estimateListDaoList;

    @SerializedName("actual_List")
    @Expose
    private List<ActualListDao> actualListDaoList;

    @SerializedName("approved_List")
    @Expose
    private List<ActualListDao> approvedListDaoList;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<EstimateListDao> getEstimateListDaoList() {
        return estimateListDaoList;
    }

    public void setEstimateListDaoList(List<EstimateListDao> estimateListDaoList) {
        this.estimateListDaoList = estimateListDaoList;
    }

    public List<ActualListDao> getActualListDaoList() {
        return actualListDaoList;
    }

    public void setActualListDaoList(List<ActualListDao> actualListDaoList) {
        this.actualListDaoList = actualListDaoList;
    }

    public List<ActualListDao> getApprovedListDaoList() {
        return approvedListDaoList;
    }

    public void setApprovedListDaoList(List<ActualListDao> approvedListDaoList) {
        this.approvedListDaoList = approvedListDaoList;
    }
}
