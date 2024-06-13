package cdfi.fintantra.meity.Pawhs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PmListDao {

    int id;

    @SerializedName("out_product_rowid")
    @Expose
    private String out_product_rowid;

    @SerializedName("out_pawhs_code")
    @Expose
    private String out_pawhs_code;

    @SerializedName("out_agg_code")
    @Expose
    private String out_agg_code;

    @SerializedName("out_product_code")
    @Expose
    private String out_product_code;

    @SerializedName("out_product_name")
    @Expose
    private String out_product_name;

    @SerializedName("out_product_category")
    @Expose
    private String out_product_category;

    @SerializedName("out_product_subcategory")
    @Expose
    private String out_product_subcategory;

    @SerializedName("out_hsn_code")
    @Expose
    private String out_hsn_code;

    @SerializedName("out_hsn_desctiption")
    @Expose
    private String out_hsn_desctiption;

    @SerializedName("out_status_code")
    @Expose
    private String out_status_code;

    @SerializedName("out_status_desc")
    @Expose
    private String out_status_desc;

    @SerializedName("out_row_timestamp")
    @Expose
    private String out_row_timestamp;



    @SerializedName("out_uomtype_code")
    @Expose
    private String out_uomtype_code;

    public String getOut_value_withtax() {
        return Out_value_withtax;
    }

    public void setOut_value_withtax(String out_value_withtax) {
        Out_value_withtax = out_value_withtax;
    }

    @SerializedName("Out_value_withtax")
    @Expose
    private String Out_value_withtax;

    public String getOut_crop_variety() {
        return out_crop_variety;
    }

    public void setOut_crop_variety(String out_crop_variety) {
        this.out_crop_variety = out_crop_variety;
    }

    @SerializedName("out_crop_variety")
    @Expose
    private String out_crop_variety;
    public PmListDao(int id, String out_product_rowid, String out_pawhs_code, String out_agg_code, String out_product_code, String out_product_name, String out_product_category, String out_product_subcategory, String out_hsn_code, String out_hsn_desctiption, String out_status_code, String out_status_desc, String out_row_timestamp, String out_uomtype_code, String out_crop_variety,String Out_value_withtax) {
        this.id = id;
        this.out_product_rowid = out_product_rowid;
        this.out_pawhs_code = out_pawhs_code;
        this.out_agg_code = out_agg_code;
        this.out_product_code = out_product_code;
        this.out_product_name = out_product_name;
        this.out_product_category = out_product_category;
        this.out_product_subcategory = out_product_subcategory;
        this.out_hsn_code = out_hsn_code;
        this.out_hsn_desctiption = out_hsn_desctiption;
        this.out_status_code = out_status_code;
        this.out_status_desc = out_status_desc;
        this.out_row_timestamp = out_row_timestamp;
        this.out_uomtype_code = out_uomtype_code;
        this.out_crop_variety  =out_crop_variety;
        this.Out_value_withtax  =Out_value_withtax;
    }

    public PmListDao() {

    }
    public String getOut_uomtype_code() {
        return out_uomtype_code;
    }

    public void setOut_uomtype_code(String out_uomtype_code) {
        this.out_uomtype_code = out_uomtype_code;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOut_product_rowid() {
        return out_product_rowid;
    }

    public void setOut_product_rowid(String out_product_rowid) {
        this.out_product_rowid = out_product_rowid;
    }

    public String getOut_pawhs_code() {
        return out_pawhs_code;
    }

    public void setOut_pawhs_code(String out_pawhs_code) {
        this.out_pawhs_code = out_pawhs_code;
    }

    public String getOut_agg_code() {
        return out_agg_code;
    }

    public void setOut_agg_code(String out_agg_code) {
        this.out_agg_code = out_agg_code;
    }

    public String getOut_product_code() {
        return out_product_code;
    }

    public void setOut_product_code(String out_product_code) {
        this.out_product_code = out_product_code;
    }

    public String getOut_product_name() {
        return out_product_name;
    }

    public void setOut_product_name(String out_product_name) {
        this.out_product_name = out_product_name;
    }

    public String getOut_product_category() {
        return out_product_category;
    }

    public void setOut_product_category(String out_product_category) {
        this.out_product_category = out_product_category;
    }

    public String getOut_product_subcategory() {
        return out_product_subcategory;
    }

    public void setOut_product_subcategory(String out_product_subcategory) {
        this.out_product_subcategory = out_product_subcategory;
    }

    public String getOut_hsn_code() {
        return out_hsn_code;
    }

    public void setOut_hsn_code(String out_hsn_code) {
        this.out_hsn_code = out_hsn_code;
    }

    public String getOut_hsn_desctiption() {
        return out_hsn_desctiption;
    }

    public void setOut_hsn_desctiption(String out_hsn_desctiption) {
        this.out_hsn_desctiption = out_hsn_desctiption;
    }

    public String getOut_status_code() {
        return out_status_code;
    }

    public void setOut_status_code(String out_status_code) {
        this.out_status_code = out_status_code;
    }

    public String getOut_status_desc() {
        return out_status_desc;
    }

    public void setOut_status_desc(String out_status_desc) {
        this.out_status_desc = out_status_desc;
    }

    public String getOut_row_timestamp() {
        return out_row_timestamp;
    }

    public void setOut_row_timestamp(String out_row_timestamp) {
        this.out_row_timestamp = out_row_timestamp;
    }
}
