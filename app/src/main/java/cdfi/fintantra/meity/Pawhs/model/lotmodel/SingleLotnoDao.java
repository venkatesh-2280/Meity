package cdfi.fintantra.meity.Pawhs.model.lotmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleLotnoDao {

    @SerializedName("in_Estm_rowid")
    @Expose
    private int in_Estm_rowid;

    @SerializedName("in_LotNo")
    @Expose
    private String in_LotNo;

    @SerializedName("in_Agg_code")
    @Expose
    private String in_Agg_code;

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

    public String getOut_Status() {
        return out_Status;
    }

    public void setOut_Status(String out_Status) {
        this.out_Status = out_Status;
    }

    @SerializedName("out_Status")
    @Expose
    private String out_Status;

    public SingleLotnoDao(int in_Estm_rowid, String in_LotNo, String in_Agg_code, String in_Farmer_Code, String in_Farmer_Name, String in_Member_Type, String in_Item_Code, String in_Item_Name, double in_Estimated_Qty, double in_Estimated_Price, double in_Estimated_Value, String in_Estimated_PickDate, String in_Remarks, String out_Status) {
        this.in_Estm_rowid = in_Estm_rowid;
        this.in_LotNo = in_LotNo;
        this.in_Agg_code = in_Agg_code;
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
        this.out_Status = out_Status;
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

    public String getIn_Agg_code() {
        return in_Agg_code;
    }

    public void setIn_Agg_code(String in_Agg_code) {
        this.in_Agg_code = in_Agg_code;
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
}
