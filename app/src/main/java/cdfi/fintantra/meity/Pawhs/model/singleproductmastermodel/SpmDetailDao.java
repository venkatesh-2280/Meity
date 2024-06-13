package cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpmDetailDao {

    private int id;

    @SerializedName("in_product_dtl_rowid")
    @Expose
    private int in_product_dtl_rowid;

    @SerializedName("in_pawhs_code")
    @Expose
    private String in_pawhs_code;

    @SerializedName("in_row_slno")
    @Expose
    private int in_row_slno;

    @SerializedName("in_maize_code")
    @Expose
    private String in_maize_code;

    @SerializedName("in_maize_name")
    @Expose
    private String in_maize_name;

    @SerializedName("in_range")
    @Expose
    private String in_range;

    @SerializedName("in_maize_interval")
    @Expose
    private String in_maize_interval;

    @SerializedName("in_maize_unit")
    @Expose
    private String in_maize_unit;

   /* @SerializedName("in_min_value")
    @Expose
    private int in_min_value;

    @SerializedName("in_max_value")
    @Expose
    private int in_max_value;

    @SerializedName("in_temp_min_value")
    @Expose
    private int in_temp_min_value;

    @SerializedName("in_temp_max_value")
    @Expose
    private int in_temp_max_value;*/

    @SerializedName("in_status_code")
    @Expose
    private String in_status_code;

    @SerializedName("in_mode_flag")
    @Expose
    private String in_mode_flag;

    public SpmDetailDao(int id, int in_product_dtl_rowid, String in_pawhs_code, int in_row_slno, String in_maize_code, String in_maize_name, String in_range, String in_maize_interval, String in_maize_unit, String in_status_code, String in_mode_flag) {
        this.id = id;
        this.in_product_dtl_rowid = in_product_dtl_rowid;
        this.in_pawhs_code = in_pawhs_code;
        this.in_row_slno = in_row_slno;
        this.in_maize_code = in_maize_code;
        this.in_maize_name = in_maize_name;
        this.in_range = in_range;
        this.in_maize_interval = in_maize_interval;
        this.in_maize_unit = in_maize_unit;
        this.in_status_code = in_status_code;
        this.in_mode_flag = in_mode_flag;
    }

    public SpmDetailDao() {
    }

    public int getIn_product_dtl_rowid() {
        return in_product_dtl_rowid;
    }

    public void setIn_product_dtl_rowid(int in_product_dtl_rowid) {
        this.in_product_dtl_rowid = in_product_dtl_rowid;
    }

    public String getIn_pawhs_code() {
        return in_pawhs_code;
    }

    public void setIn_pawhs_code(String in_pawhs_code) {
        this.in_pawhs_code = in_pawhs_code;
    }

    public int getIn_row_slno() {
        return in_row_slno;
    }

    public void setIn_row_slno(int in_row_slno) {
        this.in_row_slno = in_row_slno;
    }

    public String getIn_maize_code() {
        return in_maize_code;
    }

    public void setIn_maize_code(String in_maize_code) {
        this.in_maize_code = in_maize_code;
    }

    public String getIn_maize_name() {
        return in_maize_name;
    }

    public void setIn_maize_name(String in_maize_name) {
        this.in_maize_name = in_maize_name;
    }

    public String getIn_range() {
        return in_range;
    }

    public void setIn_range(String in_range) {
        this.in_range = in_range;
    }

    public String getIn_maize_interval() {
        return in_maize_interval;
    }

    public void setIn_maize_interval(String in_maize_interval) {
        this.in_maize_interval = in_maize_interval;
    }

    public String getIn_maize_unit() {
        return in_maize_unit;
    }

    public void setIn_maize_unit(String in_maize_unit) {
        this.in_maize_unit = in_maize_unit;
    }

  /*  public int getIn_min_value() {
        return in_min_value;
    }

    public void setIn_min_value(int in_min_value) {
        this.in_min_value = in_min_value;
    }

    public int getIn_max_value() {
        return in_max_value;
    }

    public void setIn_max_value(int in_max_value) {
        this.in_max_value = in_max_value;
    }

    public int getIn_temp_min_value() {
        return in_temp_min_value;
    }

    public void setIn_temp_min_value(int in_temp_min_value) {
        this.in_temp_min_value = in_temp_min_value;
    }

    public int getIn_temp_max_value() {
        return in_temp_max_value;
    }

    public void setIn_temp_max_value(int in_temp_max_value) {
        this.in_temp_max_value = in_temp_max_value;
    }*/

    public String getIn_status_code() {
        return in_status_code;
    }

    public void setIn_status_code(String in_status_code) {
        this.in_status_code = in_status_code;
    }

    public String getIn_mode_flag() {
        return in_mode_flag;
    }

    public void setIn_mode_flag(String in_mode_flag) {
        this.in_mode_flag = in_mode_flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
