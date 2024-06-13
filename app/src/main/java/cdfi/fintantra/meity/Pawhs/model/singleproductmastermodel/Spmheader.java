package cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Spmheader {

    @SerializedName("ioU_product_rowid")
    @Expose
    private int ioU_product_rowid;

    @SerializedName("ioU_agg_code")
    @Expose
    private String ioU_agg_code;

    @SerializedName("ioU_pawhs_code")
    @Expose
    private String ioU_pawhs_code;

    @SerializedName("in_product_code")
    @Expose
    private String in_product_code;

    @SerializedName("in_product_name")
    @Expose
    private String in_product_name;

    @SerializedName("in_product_category")
    @Expose
    private String in_product_category;

    @SerializedName("in_product_subcategory")
    @Expose
    private String in_product_subcategory;

    @SerializedName("in_hsn_code")
    @Expose
    private String in_hsn_code;

    @SerializedName("in_hsn_description")
    @Expose
    private String in_hsn_description;

    @SerializedName("in_status_code")
    @Expose
    private String in_status_code;

    @SerializedName("in_status_desc")
    @Expose
    private String in_status_desc;

    @SerializedName("in_mode_flag")
    @Expose
    private String in_mode_flag;

    @SerializedName("in_row_timestamp")
    @Expose
    private String in_row_timestamp;

    public int getIoU_product_rowid() {
        return ioU_product_rowid;
    }

    public void setIoU_product_rowid(int ioU_product_rowid) {
        this.ioU_product_rowid = ioU_product_rowid;
    }

    public String getIoU_agg_code() {
        return ioU_agg_code;
    }

    public void setIoU_agg_code(String ioU_agg_code) {
        this.ioU_agg_code = ioU_agg_code;
    }

    public String getIoU_pawhs_code() {
        return ioU_pawhs_code;
    }

    public void setIoU_pawhs_code(String ioU_pawhs_code) {
        this.ioU_pawhs_code = ioU_pawhs_code;
    }

    public String getIn_product_code() {
        return in_product_code;
    }

    public void setIn_product_code(String in_product_code) {
        this.in_product_code = in_product_code;
    }

    public String getIn_product_name() {
        return in_product_name;
    }

    public void setIn_product_name(String in_product_name) {
        this.in_product_name = in_product_name;
    }

    public String getIn_product_category() {
        return in_product_category;
    }

    public void setIn_product_category(String in_product_category) {
        this.in_product_category = in_product_category;
    }

    public String getIn_product_subcategory() {
        return in_product_subcategory;
    }

    public void setIn_product_subcategory(String in_product_subcategory) {
        this.in_product_subcategory = in_product_subcategory;
    }

    public String getIn_hsn_code() {
        return in_hsn_code;
    }

    public void setIn_hsn_code(String in_hsn_code) {
        this.in_hsn_code = in_hsn_code;
    }

    public String getIn_hsn_description() {
        return in_hsn_description;
    }

    public void setIn_hsn_description(String in_hsn_description) {
        this.in_hsn_description = in_hsn_description;
    }

    public String getIn_status_code() {
        return in_status_code;
    }

    public void setIn_status_code(String in_status_code) {
        this.in_status_code = in_status_code;
    }

    public String getIn_status_desc() {
        return in_status_desc;
    }

    public void setIn_status_desc(String in_status_desc) {
        this.in_status_desc = in_status_desc;
    }

    public String getIn_mode_flag() {
        return in_mode_flag;
    }

    public void setIn_mode_flag(String in_mode_flag) {
        this.in_mode_flag = in_mode_flag;
    }

    public String getIn_row_timestamp() {
        return in_row_timestamp;
    }

    public void setIn_row_timestamp(String in_row_timestamp) {
        this.in_row_timestamp = in_row_timestamp;
    }
}
