package cdfi.fintantra.meity.Pawhs.bulk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BulkActualProcurementDao {

    @SerializedName("in_Actual_rowid")
    @Expose
    private int in_Actual_rowid;

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

    @SerializedName("in_Actual_Qty")
    @Expose
    private int in_Actual_Qty;

    @SerializedName("in_Actual_Price")
    @Expose
    private int in_Actual_Price;

    @SerializedName("in_Actual_Value")
    @Expose
    private int in_Actual_Value;

    @SerializedName("in_advance_amt")
    @Expose
    private int in_advance_amt;

    @SerializedName("in_no_of_bags")
    @Expose
    private int in_no_of_bags;

    @SerializedName("in_Status")
    @Expose
    private String in_Status;

    @SerializedName("in_mode_flag")
    @Expose
    private String in_mode_flag;

    @SerializedName("in_actual_remarks")
    @Expose
    private String in_actual_remarks;

    @SerializedName("in_approved_remarks")
    @Expose
    private String in_approved_remarks;

    @SerializedName("in_pickup_remarks")
    @Expose
    private String in_pickup_remarks;

    @SerializedName("QtyDetail")
    @Expose
    private List<BulkQtyDetailDao> bulkQtyDetailDaoList;

    @SerializedName("OtherDetail")
    @Expose
    private List<BulkOtherDetailDao> bulkOtherDetailDaoList;

    @SerializedName("SlnoDetail")
    @Expose
    private List<BulkSInoDetailsDao> bulkSInoDetailsDaoList;

    public int getIn_Actual_rowid() {
        return in_Actual_rowid;
    }

    public void setIn_Actual_rowid(int in_Actual_rowid) {
        this.in_Actual_rowid = in_Actual_rowid;
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

    public int getIn_Actual_Qty() {
        return in_Actual_Qty;
    }

    public void setIn_Actual_Qty(int in_Actual_Qty) {
        this.in_Actual_Qty = in_Actual_Qty;
    }

    public int getIn_Actual_Price() {
        return in_Actual_Price;
    }

    public void setIn_Actual_Price(int in_Actual_Price) {
        this.in_Actual_Price = in_Actual_Price;
    }

    public int getIn_Actual_Value() {
        return in_Actual_Value;
    }

    public void setIn_Actual_Value(int in_Actual_Value) {
        this.in_Actual_Value = in_Actual_Value;
    }

    public int getIn_no_of_bags() {
        return in_no_of_bags;
    }

    public void setIn_no_of_bags(int in_no_of_bags) {
        this.in_no_of_bags = in_no_of_bags;
    }

    public String getIn_Status() {
        return in_Status;
    }

    public void setIn_Status(String in_Status) {
        this.in_Status = in_Status;
    }

    public String getIn_mode_flag() {
        return in_mode_flag;
    }

    public void setIn_mode_flag(String in_mode_flag) {
        this.in_mode_flag = in_mode_flag;
    }

    public String getIn_actual_remarks() {
        return in_actual_remarks;
    }

    public void setIn_actual_remarks(String in_actual_remarks) {
        this.in_actual_remarks = in_actual_remarks;
    }

    public String getIn_approved_remarks() {
        return in_approved_remarks;
    }

    public void setIn_approved_remarks(String in_approved_remarks) {
        this.in_approved_remarks = in_approved_remarks;
    }

    public String getIn_pickup_remarks() {
        return in_pickup_remarks;
    }

    public void setIn_pickup_remarks(String in_pickup_remarks) {
        this.in_pickup_remarks = in_pickup_remarks;
    }

    public List<BulkQtyDetailDao> getBulkQtyDetailDaoList() {
        return bulkQtyDetailDaoList;
    }

    public void setBulkQtyDetailDaoList(List<BulkQtyDetailDao> bulkQtyDetailDaoList) {
        this.bulkQtyDetailDaoList = bulkQtyDetailDaoList;
    }

    public List<BulkOtherDetailDao> getBulkOtherDetailDaoList() {
        return bulkOtherDetailDaoList;
    }

    public void setBulkOtherDetailDaoList(List<BulkOtherDetailDao> bulkOtherDetailDaoList) {
        this.bulkOtherDetailDaoList = bulkOtherDetailDaoList;
    }

    public List<BulkSInoDetailsDao> getBulkSInoDetailsDaoList() {
        return bulkSInoDetailsDaoList;
    }

    public void setBulkSInoDetailsDaoList(List<BulkSInoDetailsDao> bulkSInoDetailsDaoList) {
        this.bulkSInoDetailsDaoList = bulkSInoDetailsDaoList;
    }

    public int getIn_advance_amt() {
        return in_advance_amt;
    }

    public void setIn_advance_amt(int in_advance_amt) {
        this.in_advance_amt = in_advance_amt;
    }
}
