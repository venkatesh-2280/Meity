package cdfi.fintantra.meity.Pawhs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActProContextDao {

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

    @SerializedName("FilterBy_Option")
    @Expose
    private String FilterBy_Option;

    @SerializedName("FilterBy_Code")
    @Expose
    private String FilterBy_Code;

    @SerializedName("FilterBy_FromValue")
    @Expose
    private String FilterBy_FromValue;

    @SerializedName("FilterBy_ToValue")
    @Expose
    private String FilterBy_ToValue;

    @SerializedName("list")
    @Expose
    private List<ActProListDao> actProListDaoList;

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

    public String getFilterBy_Option() {
        return FilterBy_Option;
    }

    public void setFilterBy_Option(String filterBy_Option) {
        FilterBy_Option = filterBy_Option;
    }

    public String getFilterBy_Code() {
        return FilterBy_Code;
    }

    public void setFilterBy_Code(String filterBy_Code) {
        FilterBy_Code = filterBy_Code;
    }

    public String getFilterBy_FromValue() {
        return FilterBy_FromValue;
    }

    public void setFilterBy_FromValue(String filterBy_FromValue) {
        FilterBy_FromValue = filterBy_FromValue;
    }

    public String getFilterBy_ToValue() {
        return FilterBy_ToValue;
    }

    public void setFilterBy_ToValue(String filterBy_ToValue) {
        FilterBy_ToValue = filterBy_ToValue;
    }

    public List<ActProListDao> getActProListDaoList() {
        return actProListDaoList;
    }

    public void setActProListDaoList(List<ActProListDao> actProListDaoList) {
        this.actProListDaoList = actProListDaoList;
    }
}
