package cdfi.fintantra.meity.Pawhs.bulk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cdfi.fintantra.meity.Pawhs.Estqtydao;


public class BulkEstProdDao{

    @SerializedName("in_Estm_rowid")
    @Expose
    private int in_Estm_rowid;

    @SerializedName("in_LotNo")
    @Expose
    private String in_LotNo;

    @SerializedName("in_Farmer_Code")
    @Expose
    private String in_Farmer_Code;

    @SerializedName("in_Farmer_Name")
    @Expose
    private String in_Farmer_Name;

    @SerializedName("in_Member_Type")
    @Expose
    private String in_Member_Type;

    @SerializedName("in_Item_Code")
    @Expose
    private String in_Item_Code;

    @SerializedName("in_Item_Name")
    @Expose
    private String in_Item_Name;

    @SerializedName("in_Estimated_Qty")
    @Expose
    private double in_Estimated_Qty;

    @SerializedName("in_Estimated_Price")
    @Expose
    private double in_Estimated_Price;

    @SerializedName("in_Estimated_Value")
    @Expose
    private double in_Estimated_Value;

    @SerializedName("in_Estimated_PickDate")
    @Expose
    private String in_Estimated_PickDate;

    @SerializedName("in_Remarks")
    @Expose
    private String in_Remarks;

    @SerializedName("in_rowtimestamp")
    @Expose
    private String in_rowtimestamp;

    @SerializedName("in_mode_flag")
    @Expose
    private String in_mode_flag;

    public String getIn_LRP_Mobile_no() {
        return In_LRP_Mobile_no;
    }

    public void setIn_LRP_Mobile_no(String in_LRP_Mobile_no) {
        In_LRP_Mobile_no = in_LRP_Mobile_no;
    }

    @SerializedName("In_LRP_Mobile_no")
    @Expose
    private String In_LRP_Mobile_no;

    public String getIn_Estimated_attach() {
        return in_Estimated_attach;
    }

    public void setIn_Estimated_attach(String in_Estimated_attach) {
        this.in_Estimated_attach = in_Estimated_attach;
    }

    public String getIn_variety_status() {
        return in_variety_status;
    }

    public void setIn_variety_status(String in_variety_status) {
        this.in_variety_status = in_variety_status;
    }

    public String getIn_Estimated_Status() {
        return in_Estimated_Status;
    }

    public void setIn_Estimated_Status(String in_Estimated_Status) {
        this.in_Estimated_Status = in_Estimated_Status;
    }

    @SerializedName("in_variety_status")
    @Expose
    private String in_variety_status;

    @SerializedName("in_Estimated_Status")
    @Expose
    private String in_Estimated_Status;


    @SerializedName("in_Estimated_attach")
    @Expose
    private String in_Estimated_attach;

    public String getIn_LRP_Name() {
        return in_LRP_Name;
    }

    public void setIn_LRP_Name(String in_LRP_Name) {
        this.in_LRP_Name = in_LRP_Name;
    }

    @SerializedName("in_LRP_Name")
    @Expose
    private String in_LRP_Name;

    public List<Estqtydao> getPostQtyDetailDaoList() {
        return postQtyDetailDaoList;
    }

    public void setPostQtyDetailDaoList(List<Estqtydao> postQtyDetailDaoList) {
        this.postQtyDetailDaoList = postQtyDetailDaoList;
    }

    @SerializedName("QtyDetail")
    @Expose
    private List<Estqtydao> postQtyDetailDaoList;

    public BulkEstProdDao(int in_Estm_rowid, String in_LotNo, String in_Farmer_Code, String in_Farmer_Name,
                          String in_Member_Type, String in_Item_Code, String in_Item_Name, double in_Estimated_Qty,
                          double in_Estimated_Price, double in_Estimated_Value, String in_Estimated_PickDate,
                          String in_Remarks, String in_rowtimestamp, String in_mode_flag, String in_Estimated_attach, String in_variety_status,
                          String in_Estimated_Status, String in_LRP_Name, List<Estqtydao> postQtyDetailDaoList, String In_LRP_Mobile_no) {
        this.in_Estm_rowid = in_Estm_rowid;
        this.in_LotNo = in_LotNo;
        this.in_Farmer_Code = in_Farmer_Code;
        this.in_Farmer_Name = in_Farmer_Name;
        this.in_Member_Type = in_Member_Type;
        this.in_Item_Code = in_Item_Code;
        this.in_Item_Name = in_Item_Name;
        this.in_Estimated_Qty = in_Estimated_Qty;
        this.in_Estimated_Price = in_Estimated_Price;
        this.in_Estimated_Value = in_Estimated_Value;
        this.in_Estimated_PickDate = in_Estimated_PickDate;
        this.in_Remarks = in_Remarks;
        this.in_rowtimestamp = in_rowtimestamp;
        this.in_mode_flag = in_mode_flag;
        this.in_Estimated_attach=in_Estimated_attach;
        this.in_variety_status=in_variety_status;
        this.in_Estimated_Status=in_Estimated_Status;
        this.in_LRP_Name=in_LRP_Name;
        this.postQtyDetailDaoList=postQtyDetailDaoList;
        this.In_LRP_Mobile_no=In_LRP_Mobile_no;
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

    public String getIn_Farmer_Code() {
        return in_Farmer_Code;
    }

    public void setIn_Farmer_Code(String in_Farmer_Code) {
        this.in_Farmer_Code = in_Farmer_Code;
    }

    public String getIn_Farmer_Name() {
        return in_Farmer_Name;
    }

    public void setIn_Farmer_Name(String in_Farmer_Name) {
        this.in_Farmer_Name = in_Farmer_Name;
    }

    public String getIn_Member_Type() {
        return in_Member_Type;
    }

    public void setIn_Member_Type(String in_Member_Type) {
        this.in_Member_Type = in_Member_Type;
    }

    public String getIn_Item_Code() {
        return in_Item_Code;
    }

    public void setIn_Item_Code(String in_Item_Code) {
        this.in_Item_Code = in_Item_Code;
    }

    public String getIn_Item_Name() {
        return in_Item_Name;
    }

    public void setIn_Item_Name(String in_Item_Name) {
        this.in_Item_Name = in_Item_Name;
    }

    public double getIn_Estimated_Qty() {
        return in_Estimated_Qty;
    }

    public void setIn_Estimated_Qty(int in_Estimated_Qty) {
        this.in_Estimated_Qty = in_Estimated_Qty;
    }

    public double getIn_Estimated_Price() {
        return in_Estimated_Price;
    }

    public void setIn_Estimated_Price(double in_Estimated_Price) {
        this.in_Estimated_Price = in_Estimated_Price;
    }

    public double getIn_Estimated_Value() {
        return in_Estimated_Value;
    }

    public void setIn_Estimated_Value(double in_Estimated_Value) {
        this.in_Estimated_Value = in_Estimated_Value;
    }

    public String getIn_Estimated_PickDate() {
        return in_Estimated_PickDate;
    }

    public void setIn_Estimated_PickDate(String in_Estimated_PickDate) {
        this.in_Estimated_PickDate = in_Estimated_PickDate;
    }

    public String getIn_Remarks() {
        return in_Remarks;
    }

    public void setIn_Remarks(String in_Remarks) {
        this.in_Remarks = in_Remarks;
    }

    public String getIn_rowtimestamp() {
        return in_rowtimestamp;
    }

    public void setIn_rowtimestamp(String in_rowtimestamp) {
        this.in_rowtimestamp = in_rowtimestamp;
    }

    public String getIn_mode_flag() {
        return in_mode_flag;
    }

    public void setIn_mode_flag(String in_mode_flag) {
        this.in_mode_flag = in_mode_flag;
    }
}
