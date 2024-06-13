package cdfi.fintantra.meity.Pawhs.model.singleactualprocmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cdfi.fintantra.meity.Pawhs.model.OtherDetailDao;
import cdfi.fintantra.meity.Pawhs.model.QtyDetailDao;
import cdfi.fintantra.meity.Pawhs.model.SiNoDetailDao;


public class SingleActProcContextDao {

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

    @SerializedName("ioU_act_rowid")
    @Expose
    private int IOU_act_rowid;

    @SerializedName("ioU_agg_code")
    @Expose
    private String IOU_agg_code;

    @SerializedName("ioU_lotno")
    @Expose
    private String IOU_lotno;

    @SerializedName("header")
    @Expose
    private SingleActualHeaderDao singleActualHeaderDao;

    @SerializedName("qtyDetail")
    @Expose
    private List<QtyDetailDao> qtyDetailDaoList;

    @SerializedName("otherDetail")
    @Expose
    private List<OtherDetailDao> otherDetailDaoList;

    @SerializedName("slnoDetail")
    @Expose
    private List<SiNoDetailDao> siNoDetailDaoList;

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



    public SingleActualHeaderDao getSingleActualHeaderDao() {
        return singleActualHeaderDao;
    }

    public void setSingleActualHeaderDao(SingleActualHeaderDao singleActualHeaderDao) {
        this.singleActualHeaderDao = singleActualHeaderDao;
    }

    public int getIOU_act_rowid() {
        return IOU_act_rowid;
    }

    public void setIOU_act_rowid(int IOU_act_rowid) {
        this.IOU_act_rowid = IOU_act_rowid;
    }

    public String getIOU_agg_code() {
        return IOU_agg_code;
    }

    public void setIOU_agg_code(String IOU_agg_code) {
        this.IOU_agg_code = IOU_agg_code;
    }

    public String getIOU_lotno() {
        return IOU_lotno;
    }

    public void setIOU_lotno(String IOU_lotno) {
        this.IOU_lotno = IOU_lotno;
    }


    public List<QtyDetailDao> getQtyDetailDaoList() {
        return qtyDetailDaoList;
    }

    public void setQtyDetailDaoList(List<QtyDetailDao> qtyDetailDaoList) {
        this.qtyDetailDaoList = qtyDetailDaoList;
    }

    public List<OtherDetailDao> getOtherDetailDaoList() {
        return otherDetailDaoList;
    }

    public void setOtherDetailDaoList(List<OtherDetailDao> otherDetailDaoList) {
        this.otherDetailDaoList = otherDetailDaoList;
    }

    public List<SiNoDetailDao> getSiNoDetailDaoList() {
        return siNoDetailDaoList;
    }

    public void setSiNoDetailDaoList(List<SiNoDetailDao> siNoDetailDaoList) {
        this.siNoDetailDaoList = siNoDetailDaoList;
    }
}
