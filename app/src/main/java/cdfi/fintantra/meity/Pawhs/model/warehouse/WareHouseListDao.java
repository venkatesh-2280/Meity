package cdfi.fintantra.meity.Pawhs.model.warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WareHouseListDao {

    private int id;

    @SerializedName("out_whs_rowid")
    @Expose
    private int out_whs_rowid;

    @SerializedName("out_LotNo")
    @Expose
    private String out_LotNo;

    @SerializedName("out_whs_code")
    @Expose
    private String out_whs_code;

    @SerializedName("out_farmer_code")
    @Expose
    private String out_farmer_code;

    @SerializedName("out_farmer_name")
    @Expose
    private String out_farmer_name;

    @SerializedName("out_member_type")
    @Expose
    private String out_member_type;

    @SerializedName("out_item_code")
    @Expose
    private String out_item_code;

    @SerializedName("out_item_name")
    @Expose
    private String out_item_name;

    @SerializedName("out_accepted_qty")
    @Expose
    private String out_accepted_qty;

    @SerializedName("out_payment_advcno")
    @Expose
    private String out_payment_advcno;

    @SerializedName("out_Accepted_date")
    @Expose
    private String out_Accepted_date;

    @SerializedName("out_status")
    @Expose
    private String out_status;

    @SerializedName("out_Remarks")
    @Expose
    private String out_Remarks;

    @SerializedName("out_row_timestamp")
    @Expose
    private String out_row_timestamp;

    public WareHouseListDao(int id, int out_whs_rowid, String out_LotNo, String out_whs_code, String out_farmer_code, String out_farmer_name, String out_member_type, String out_item_code, String out_item_name, String out_accepted_qty, String out_payment_advcno, String out_Accepted_date, String out_status, String out_Remarks, String out_row_timestamp) {
        this.id = id;
        this.out_whs_rowid = out_whs_rowid;
        this.out_LotNo = out_LotNo;
        this.out_whs_code = out_whs_code;
        this.out_farmer_code = out_farmer_code;
        this.out_farmer_name = out_farmer_name;
        this.out_member_type = out_member_type;
        this.out_item_code = out_item_code;
        this.out_item_name = out_item_name;
        this.out_accepted_qty = out_accepted_qty;
        this.out_payment_advcno = out_payment_advcno;
        this.out_Accepted_date = out_Accepted_date;
        this.out_status = out_status;
        this.out_Remarks = out_Remarks;
        this.out_row_timestamp = out_row_timestamp;
    }

    public WareHouseListDao() {
    }

    public int getOut_whs_rowid() {
        return out_whs_rowid;
    }

    public void setOut_whs_rowid(int out_whs_rowid) {
        this.out_whs_rowid = out_whs_rowid;
    }

    public String getOut_LotNo() {
        return out_LotNo;
    }

    public void setOut_LotNo(String out_LotNo) {
        this.out_LotNo = out_LotNo;
    }

    public String getOut_whs_code() {
        return out_whs_code;
    }

    public void setOut_whs_code(String out_whs_code) {
        this.out_whs_code = out_whs_code;
    }

    public String getOut_farmer_code() {
        return out_farmer_code;
    }

    public void setOut_farmer_code(String out_farmer_code) {
        this.out_farmer_code = out_farmer_code;
    }

    public String getOut_farmer_name() {
        return out_farmer_name;
    }

    public void setOut_farmer_name(String out_farmer_name) {
        this.out_farmer_name = out_farmer_name;
    }

    public String getOut_member_type() {
        return out_member_type;
    }

    public void setOut_member_type(String out_member_type) {
        this.out_member_type = out_member_type;
    }

    public String getOut_item_code() {
        return out_item_code;
    }

    public void setOut_item_code(String out_item_code) {
        this.out_item_code = out_item_code;
    }

    public String getOut_item_name() {
        return out_item_name;
    }

    public void setOut_item_name(String out_item_name) {
        this.out_item_name = out_item_name;
    }

    public String getOut_accepted_qty() {
        return out_accepted_qty;
    }

    public void setOut_accepted_qty(String out_accepted_qty) {
        this.out_accepted_qty = out_accepted_qty;
    }

    public String getOut_payment_advcno() {
        return out_payment_advcno;
    }

    public void setOut_payment_advcno(String out_payment_advcno) {
        this.out_payment_advcno = out_payment_advcno;
    }

    public String getOut_Accepted_date() {
        return out_Accepted_date;
    }

    public void setOut_Accepted_date(String out_Accepted_date) {
        this.out_Accepted_date = out_Accepted_date;
    }

    public String getOut_status() {
        return out_status;
    }

    public void setOut_status(String out_status) {
        this.out_status = out_status;
    }

    public String getOut_Remarks() {
        return out_Remarks;
    }

    public void setOut_Remarks(String out_Remarks) {
        this.out_Remarks = out_Remarks;
    }

    public String getOut_row_timestamp() {
        return out_row_timestamp;
    }

    public void setOut_row_timestamp(String out_row_timestamp) {
        this.out_row_timestamp = out_row_timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
