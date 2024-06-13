package cdfi.fintantra.meity.Pawhs.model.lotmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleContextDao {

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

    @SerializedName("in_Estm_rowid")
    @Expose
    private int in_Estm_rowid;

    @SerializedName("in_LotNo")
    @Expose
    private String in_LotNo;

    @SerializedName("header")
    @Expose
    private SingleLotnoDao singleLotnoDao;

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

    public int getIn_Estm_rowid() {
        return in_Estm_rowid;
    }

    public void setIn_Estm_rowid(int in_Estm_rowid) {
        this.in_Estm_rowid = in_Estm_rowid;
    }

    public String getIn_LotNo() {
        return in_LotNo;
    }

    public void setIn_LotNo(String in_LotNo) {
        this.in_LotNo = in_LotNo;
    }

    public SingleLotnoDao getSingleLotnoDao() {
        return singleLotnoDao;
    }

    public void setSingleLotnoDao(SingleLotnoDao singleLotnoDao) {
        this.singleLotnoDao = singleLotnoDao;
    }
}
