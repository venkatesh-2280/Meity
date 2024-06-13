package cdfi.fintantra.meity.Pawhs.bulk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BulkNewWareHouseReceiptDao {

    @SerializedName("in_whs_rowid")
    @Expose
    private int in_whs_rowid;

    @SerializedName("in_whs_code")
    @Expose
    private String in_whs_code;

    @SerializedName("in_LotNo")
    @Expose
    private String in_LotNo;

    @SerializedName("in_Accepted_Qty")
    @Expose
    private int in_Accepted_Qty;

    @SerializedName("in_Accepted_date")
    @Expose
    private String in_Accepted_date;

    @SerializedName("in_status")
    @Expose
    private String in_status;

    @SerializedName("in_Remarks")
    @Expose
    private String in_Remarks;

    @SerializedName("in_rowtimestamp")
    @Expose
    private String in_rowtimestamp;

    @SerializedName("in_mode_flag")
    @Expose
    private String in_mode_flag;

    public int getIn_whs_rowid() {
        return in_whs_rowid;
    }

    public void setIn_whs_rowid(int in_whs_rowid) {
        this.in_whs_rowid = in_whs_rowid;
    }

    public String getIn_whs_code() {
        return in_whs_code;
    }

    public void setIn_whs_code(String in_whs_code) {
        this.in_whs_code = in_whs_code;
    }

    public String getIn_LotNo() {
        return in_LotNo;
    }

    public void setIn_LotNo(String in_LotNo) {
        this.in_LotNo = in_LotNo;
    }

    public int getIn_Accepted_Qty() {
        return in_Accepted_Qty;
    }

    public void setIn_Accepted_Qty(int in_Accepted_Qty) {
        this.in_Accepted_Qty = in_Accepted_Qty;
    }

    public String getIn_Accepted_date() {
        return in_Accepted_date;
    }

    public void setIn_Accepted_date(String in_Accepted_date) {
        this.in_Accepted_date = in_Accepted_date;
    }

    public String getIn_status() {
        return in_status;
    }

    public void setIn_status(String in_status) {
        this.in_status = in_status;
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
