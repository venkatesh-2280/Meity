package cdfi.fintantra.meity.Pawhs.model.lotmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EstimateListDao {

    int id;

    @SerializedName("out_Estm_rowid")
    @Expose
    private int out_Estm_rowid;

    @SerializedName("out_LotNo")
    @Expose
    private String out_LotNo;

    @SerializedName("out_agg_code")
    @Expose
    private String out_agg_code;

    @SerializedName("out_Farmer_Code")
    @Expose
    private String out_Farmer_Code;

    @SerializedName("out_Farmer_Name")
    @Expose
    private String out_Farmer_Name;

    @SerializedName("out_Member_Type")
    @Expose
    private String out_Member_Type;

    @SerializedName("out_Item_Code")
    @Expose
    private String out_Item_Code;

    @SerializedName("out_Item_Name")
    @Expose
    private String out_Item_Name;

    @SerializedName("out_Estimated_Qty")
    @Expose
    private String out_Estimated_Qty;

    @SerializedName("out_Estimated_Price")
    @Expose
    private String out_Estimated_Price;

    @SerializedName("out_Estimated_Value")
    @Expose
    private String out_Estimated_Value;



    @SerializedName("out_Remarks")
    @Expose
    private String out_Remarks;

    @SerializedName("out_Status")
    @Expose
    private String out_Status;

    @SerializedName("out_Estimated_PickupDate")
    @Expose
    private String out_Estimated_PickupDate;

    private String alreadyTaken;

    public int getOut_Estm_rowid() {
        return out_Estm_rowid;
    }

    public void setOut_Estm_rowid(int out_Estm_rowid) {
        this.out_Estm_rowid = out_Estm_rowid;
    }

    public String getOut_LotNo() {
        return out_LotNo;
    }

    public void setOut_LotNo(String out_LotNo) {
        this.out_LotNo = out_LotNo;
    }

    public String getOut_agg_code() {
        return out_agg_code;
    }

    public void setOut_agg_code(String out_agg_code) {
        this.out_agg_code = out_agg_code;
    }

    public String getOut_Farmer_Code() {
        return out_Farmer_Code;
    }

    public void setOut_Farmer_Code(String out_Farmer_Code) {
        this.out_Farmer_Code = out_Farmer_Code;
    }

    public String getOut_Farmer_Name() {
        return out_Farmer_Name;
    }

    public void setOut_Farmer_Name(String out_Farmer_Name) {
        this.out_Farmer_Name = out_Farmer_Name;
    }

    public String getOut_Member_Type() {
        return out_Member_Type;
    }

    public void setOut_Member_Type(String out_Member_Type) {
        this.out_Member_Type = out_Member_Type;
    }

    public String getOut_Item_Code() {
        return out_Item_Code;
    }

    public void setOut_Item_Code(String out_Item_Code) {
        this.out_Item_Code = out_Item_Code;
    }

    public String getOut_Item_Name() {
        return out_Item_Name;
    }

    public void setOut_Item_Name(String out_Item_Name) {
        this.out_Item_Name = out_Item_Name;
    }

    public String getOut_Estimated_Qty() {
        return out_Estimated_Qty;
    }

    public void setOut_Estimated_Qty(String out_Estimated_Qty) {
        this.out_Estimated_Qty = out_Estimated_Qty;
    }

    public String getOut_Estimated_Price() {
        return out_Estimated_Price;
    }

    public void setOut_Estimated_Price(String out_Estimated_Price) {
        this.out_Estimated_Price = out_Estimated_Price;
    }

    public String getOut_Estimated_Value() {
        return out_Estimated_Value;
    }

    public void setOut_Estimated_Value(String out_Estimated_Value) {
        this.out_Estimated_Value = out_Estimated_Value;
    }

    public String getOut_Remarks() {
        return out_Remarks;
    }

    public void setOut_Remarks(String out_Remarks) {
        this.out_Remarks = out_Remarks;
    }

    public String getOut_Status() {
        return out_Status;
    }

    public void setOut_Status(String out_Status) {
        this.out_Status = out_Status;
    }

    public EstimateListDao(int id, int out_Estm_rowid, String out_LotNo, String out_agg_code, String out_Farmer_Code, String out_Farmer_Name, String out_Member_Type, String out_Item_Code, String out_Item_Name, String out_Estimated_Qty, String out_Estimated_Price, String out_Estimated_Value, String out_Remarks, String out_Status, String out_Estimated_PickupDate, String alreadyTaken) {
        this.id = id;
        this.out_Estm_rowid = out_Estm_rowid;
        this.out_LotNo = out_LotNo;
        this.out_agg_code = out_agg_code;
        this.out_Farmer_Code = out_Farmer_Code;
        this.out_Farmer_Name = out_Farmer_Name;
        this.out_Member_Type = out_Member_Type;
        this.out_Item_Code = out_Item_Code;
        this.out_Item_Name = out_Item_Name;
        this.out_Estimated_Qty = out_Estimated_Qty;
        this.out_Estimated_Price = out_Estimated_Price;
        this.out_Estimated_Value = out_Estimated_Value;
        this.out_Remarks = out_Remarks;
        this.out_Status = out_Status;
        this.out_Estimated_PickupDate=out_Estimated_PickupDate;
        this.alreadyTaken=alreadyTaken;
    }

    public EstimateListDao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOut_Estimated_PickupDate() {
        return out_Estimated_PickupDate;
    }

    public void setOut_Estimated_PickupDate(String out_Estimated_PickupDate) {
        this.out_Estimated_PickupDate = out_Estimated_PickupDate;
    }

    public String getAlreadyTaken() {
        return alreadyTaken;
    }

    public void setAlreadyTaken(String alreadyTaken) {
        this.alreadyTaken = alreadyTaken;
    }
}
