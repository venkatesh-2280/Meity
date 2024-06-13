package cdfi.fintantra.meity.Pawhs.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QtyDetailDao implements Parcelable {

    private int id;

    @SerializedName("in_qty_row_id")
    @Expose
    private int in_qty_row_id;

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

    @SerializedName("in_agg_code")
    @Expose
    private String in_agg_code;

    @SerializedName("in_lotno")
    @Expose
    private String in_lotno;

    @SerializedName("in_item_code")
    @Expose
    private String in_item_code;

    private String thresHoldValue;
    private String uom;
    private String statusValue;

    public QtyDetailDao(int in_qty_row_id, String in_qty_code, String in_qty_name, int in_actual_value, int in_wr_qty_value, String in_mode_flag) {
        this.in_qty_row_id = in_qty_row_id;
        this.in_qty_code = in_qty_code;
        this.in_qty_name = in_qty_name;
        this.in_actual_value = in_actual_value;
        this.in_wr_qty_value = in_wr_qty_value;
        this.in_mode_flag = in_mode_flag;
    }

    public QtyDetailDao(int in_qty_row_id, String in_qty_code, int in_actual_value, int in_wr_qty_value, String in_mode_flag, String thresHoldValue, String uom, String qtyName) {
        this.in_qty_row_id = in_qty_row_id;
        this.in_qty_code = in_qty_code;
        this.in_actual_value = in_actual_value;
        this.in_wr_qty_value = in_wr_qty_value;
        this.in_mode_flag = in_mode_flag;
        this.thresHoldValue = thresHoldValue;
        this.uom = uom;
        this.in_qty_name = qtyName;
    }

    public QtyDetailDao(int id, int in_qty_row_id, String in_qty_code, String in_qty_name, double in_actual_value, double in_wr_qty_value, String in_mode_flag, String in_agg_code, String in_lotno, String in_item_code, String statusValue) {
        this.id=id;
        this.in_qty_row_id = in_qty_row_id;
        this.in_qty_code = in_qty_code;
        this.in_qty_name = in_qty_name;
        this.in_actual_value = in_actual_value;
        this.in_wr_qty_value = in_wr_qty_value;
        this.in_mode_flag = in_mode_flag;
        this.in_agg_code = in_agg_code;
        this.in_lotno = in_lotno;
        this.in_item_code = in_item_code;
        this.statusValue = statusValue;
    }

    public QtyDetailDao() {
    }

    protected QtyDetailDao(Parcel in) {
        in_qty_row_id = in.readInt();
        in_qty_code = in.readString();
        in_qty_name = in.readString();
        in_actual_value = in.readDouble();
        in_wr_qty_value = in.readDouble();
        in_mode_flag = in.readString();
        this.in_agg_code = in.readString();
        this.in_lotno = in.readString();
        this.in_item_code = in.readString();
        thresHoldValue = in.readString();
        uom = in.readString();
        statusValue=in.readString();
    }

    public static final Creator<QtyDetailDao> CREATOR = new Creator<QtyDetailDao>() {
        @Override
        public QtyDetailDao createFromParcel(Parcel in) {
            return new QtyDetailDao(in);
        }

        @Override
        public QtyDetailDao[] newArray(int size) {
            return new QtyDetailDao[size];
        }
    };

    public int getIn_qty_row_id() {
        return in_qty_row_id;
    }

    public void setIn_qty_row_id(int in_qty_row_id) {
        this.in_qty_row_id = in_qty_row_id;
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

    public void setIn_actual_value(double in_actual_value) {
        this.in_actual_value = in_actual_value;
    }

    public double getIn_wr_qty_value() {
        return in_wr_qty_value;
    }

    public void setIn_wr_qty_value(double in_wr_qty_value) {
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

    public String getIn_agg_code() {
        return in_agg_code;
    }

    public void setIn_agg_code(String in_agg_code) {
        this.in_agg_code = in_agg_code;
    }

    public String getIn_lotno() {
        return in_lotno;
    }

    public void setIn_lotno(String in_lotno) {
        this.in_lotno = in_lotno;
    }

    public String getIn_item_code() {
        return in_item_code;
    }

    public void setIn_item_code(String in_item_code) {
        this.in_item_code = in_item_code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(in_qty_row_id);
        parcel.writeString(in_qty_code);
        parcel.writeString(in_qty_name);
        parcel.writeDouble(in_actual_value);
        parcel.writeDouble(in_wr_qty_value);
        parcel.writeString(in_mode_flag);
        parcel.writeString(in_agg_code);
        parcel.writeString(in_lotno);
        parcel.writeString(in_item_code);
        parcel.writeString(thresHoldValue);
        parcel.writeString(uom);
        parcel.writeString(statusValue);
    }
}
