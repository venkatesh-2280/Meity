package cdfi.fintantra.meity.Pawhs.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostQtyDetailDao implements Parcelable {

    private int id;

    @SerializedName("In_qty_dtl_rowid")
    @Expose
    private int In_qty_dtl_rowid;

    @SerializedName("in_qty_code")
    @Expose
    private String in_qty_code;

    @SerializedName("in_qty_name")
    @Expose
    private String in_qty_name;

    @SerializedName("in_actual_value")
    @Expose
    private double in_actual_value;

    @SerializedName("in_wr_qty_value")
    @Expose
    private double in_wr_qty_value;

    @SerializedName("in_mode_flag")
    @Expose
    private String in_mode_flag;

    private String thresHoldValue;
    private String uom;
    private String statusValue;
    private String lotno;



    public PostQtyDetailDao(int in_qty_dtl_rowid, String in_qty_code, String in_qty_name, double in_actual_value, double in_wr_qty_value, String in_mode_flag) {
        In_qty_dtl_rowid = in_qty_dtl_rowid;
        this.in_qty_code = in_qty_code;
        this.in_qty_name = in_qty_name;
        this.in_actual_value = in_actual_value;
        this.in_wr_qty_value = in_wr_qty_value;
        this.in_mode_flag = in_mode_flag;
    }
    public PostQtyDetailDao(int in_qty_dtl_rowid, String in_qty_code, String in_qty_name, int in_actual_value, int in_wr_qty_value, String in_mode_flag, String statusValue, String lotno) {
        In_qty_dtl_rowid = in_qty_dtl_rowid;
        this.in_qty_code = in_qty_code;
        this.in_qty_name = in_qty_name;
        this.in_actual_value = in_actual_value;
        this.in_wr_qty_value = in_wr_qty_value;
        this.in_mode_flag = in_mode_flag;
        this.statusValue = statusValue;
        this.lotno=lotno;
    }

    public PostQtyDetailDao(int in_qty_row_id, String in_qty_code, double in_actual_value, int in_wr_qty_value, String in_mode_flag, String thresHoldValue, String uom, String qtyName) {
        this.In_qty_dtl_rowid = in_qty_row_id;
        this.in_qty_code = in_qty_code;
        this.in_actual_value = in_actual_value;
        this.in_wr_qty_value = in_wr_qty_value;
        this.in_mode_flag = in_mode_flag;
        this.thresHoldValue = thresHoldValue;
        this.uom = uom;
        this.in_qty_name = qtyName;
    }
    public PostQtyDetailDao(int in_qty_row_id, String in_qty_code, int in_actual_value, int in_wr_qty_value, String in_mode_flag, String thresHoldValue, String uom, String qtyName, String statusValue, String lotno) {
        this.In_qty_dtl_rowid = in_qty_row_id;
        this.in_qty_code = in_qty_code;
        this.in_actual_value = in_actual_value;
        this.in_wr_qty_value = in_wr_qty_value;
        this.in_mode_flag = in_mode_flag;
        this.thresHoldValue = thresHoldValue;
        this.uom = uom;
        this.in_qty_name = qtyName;
        this.statusValue = statusValue;
        this.lotno=lotno;
    }

    public PostQtyDetailDao(int id, int in_qty_dtl_rowid, String in_qty_code, String in_qty_name, double in_actual_value, double in_wr_qty_value, String in_mode_flag, String thresHoldValue, String uom, String statusValue, String lotno) {
        this.id=id;
        In_qty_dtl_rowid = in_qty_dtl_rowid;
        this.in_qty_code = in_qty_code;
        this.in_qty_name = in_qty_name;
        this.in_actual_value = in_actual_value;
        this.in_wr_qty_value = in_wr_qty_value;
        this.in_mode_flag = in_mode_flag;
        this.thresHoldValue = thresHoldValue;
        this.uom = uom;
        this.statusValue = statusValue;
        this.lotno=lotno;
    }

    public PostQtyDetailDao() {
    }

    protected PostQtyDetailDao(Parcel in) {
        In_qty_dtl_rowid = in.readInt();
        in_qty_code = in.readString();
        in_qty_name = in.readString();
        in_actual_value = in.readDouble();
        in_wr_qty_value = in.readDouble();
        in_mode_flag = in.readString();
        thresHoldValue = in.readString();
        statusValue = in.readString();
        uom = in.readString();
    }

    public static final Creator<PostQtyDetailDao> CREATOR = new Creator<PostQtyDetailDao>() {
        @Override
        public PostQtyDetailDao createFromParcel(Parcel in) {
            return new PostQtyDetailDao(in);
        }

        @Override
        public PostQtyDetailDao[] newArray(int size) {
            return new PostQtyDetailDao[size];
        }
    };




    public int getIn_qty_dtl_rowid() {
        return In_qty_dtl_rowid;
    }

    public void setIn_qty_dtl_rowid(int in_qty_dtl_rowid) {
        In_qty_dtl_rowid = in_qty_dtl_rowid;
    }

    public String getIn_qty_code() {
        return in_qty_code;
    }

    public void setIn_qty_code(String in_qty_code) {
        this.in_qty_code = in_qty_code;
    }

    public String getIn_qty_name() {
        return in_qty_name;
    }

    public void setIn_qty_name(String in_qty_name) {
        this.in_qty_name = in_qty_name;
    }

    public double getIn_actual_value() {
        return in_actual_value;
    }

    public void setIn_actual_value(int in_actual_value) {
        this.in_actual_value = in_actual_value;
    }

    public double getIn_wr_qty_value() {
        return in_wr_qty_value;
    }

    public void setIn_wr_qty_value(int in_wr_qty_value) {
        this.in_wr_qty_value = in_wr_qty_value;
    }

    public String getIn_mode_flag() {
        return in_mode_flag;
    }

    public void setIn_mode_flag(String in_mode_flag) {
        this.in_mode_flag = in_mode_flag;
    }

    public String getThresHoldValue() {
        return thresHoldValue;
    }

    public void setThresHoldValue(String thresHoldValue) {
        this.thresHoldValue = thresHoldValue;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getLotno() {
        return lotno;
    }

    public void setLotno(String lotno) {
        this.lotno = lotno;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(In_qty_dtl_rowid);
        parcel.writeString(in_qty_code);
        parcel.writeString(in_qty_name);
        parcel.writeDouble(in_actual_value);
        parcel.writeDouble(in_wr_qty_value);
        parcel.writeString(in_mode_flag);
        parcel.writeString(thresHoldValue);
        parcel.writeString(uom);
        parcel.writeString(statusValue);
    }
}
