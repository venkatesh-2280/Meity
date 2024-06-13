package cdfi.fintantra.meity.Pawhs.bulk;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BulkQtyDetailDao implements Parcelable {

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
    private int in_actual_value;

    @SerializedName("in_wr_qty_value")
    @Expose
    private int in_wr_qty_value;

    @SerializedName("in_mode_flag")
    @Expose
    private String in_mode_flag;

    private String thresHoldValue;
    private String uom;
   // private String qtyName;


    public BulkQtyDetailDao(int in_qty_row_id, String in_qty_code, int in_actual_value, int in_wr_qty_value, String in_mode_flag) {
        this.in_qty_row_id = in_qty_row_id;
        this.in_qty_code = in_qty_code;
        this.in_actual_value = in_actual_value;
        this.in_wr_qty_value = in_wr_qty_value;
        this.in_mode_flag = in_mode_flag;
    }

    public BulkQtyDetailDao(int in_qty_row_id, String in_qty_code, String in_qty_name, int in_actual_value, int in_wr_qty_value, String in_mode_flag) {
        this.in_qty_row_id = in_qty_row_id;
        this.in_qty_code = in_qty_code;
        this.in_qty_name = in_qty_name;
        this.in_actual_value = in_actual_value;
        this.in_wr_qty_value = in_wr_qty_value;
        this.in_mode_flag = in_mode_flag;
    }

    public BulkQtyDetailDao(int in_qty_row_id, String in_qty_code, int in_actual_value, int in_wr_qty_value, String in_mode_flag, String thresHoldValue, String uom, String qtyName) {
        this.in_qty_row_id = in_qty_row_id;
        this.in_qty_code = in_qty_code;
        this.in_actual_value = in_actual_value;
        this.in_wr_qty_value = in_wr_qty_value;
        this.in_mode_flag = in_mode_flag;
        this.thresHoldValue = thresHoldValue;
        this.uom = uom;
        this.in_qty_name = qtyName;
    }

    protected BulkQtyDetailDao(Parcel in) {
        in_qty_row_id = in.readInt();
        in_qty_code = in.readString();
        in_qty_name = in.readString();
        in_actual_value = in.readInt();
        in_wr_qty_value = in.readInt();
        in_mode_flag = in.readString();
        thresHoldValue = in.readString();
        uom = in.readString();
       // qtyName = in.readString();
    }

    public static final Creator<BulkQtyDetailDao> CREATOR = new Creator<BulkQtyDetailDao>() {
        @Override
        public BulkQtyDetailDao createFromParcel(Parcel in) {
            return new BulkQtyDetailDao(in);
        }

        @Override
        public BulkQtyDetailDao[] newArray(int size) {
            return new BulkQtyDetailDao[size];
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

    public int getIn_actual_value() {
        return in_actual_value;
    }

    public void setIn_actual_value(int in_actual_value) {
        this.in_actual_value = in_actual_value;
    }

    public int getIn_wr_qty_value() {
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

   /* public String getQtyName() {
        return qtyName;
    }

    public void setQtyName(String qtyName) {
        this.qtyName = qtyName;
    }*/

    public String getIn_qty_name() {
        return in_qty_name;
    }

    public void setIn_qty_name(String in_qty_name) {
        this.in_qty_name = in_qty_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(in_qty_row_id);
        parcel.writeString(in_qty_code);
        parcel.writeString(in_qty_name);
        parcel.writeInt(in_actual_value);
        parcel.writeInt(in_wr_qty_value);
        parcel.writeString(in_mode_flag);
        parcel.writeString(thresHoldValue);
        parcel.writeString(uom);
      //  parcel.writeString(qtyName);
    }
}
