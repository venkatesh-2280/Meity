package cdfi.fintantra.meity.Pawhs;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Estqtydao implements Parcelable {

    @SerializedName("in_qty_row_id")
    @Expose
    private int in_qty_row_id;

    @SerializedName("in_qty_code")
    @Expose
    private String in_qty_code;

    @SerializedName("in_actual_value")
    @Expose
    private double in_actual_value;

    @SerializedName("in_wr_qty_value")
    @Expose
    private double in_wr_qty_value;

    @SerializedName("in_estimate_qty_value")
    @Expose
    private String in_estimate_qty_value;

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

    public String getIn_estimate_qty_value() {
        return in_estimate_qty_value;
    }

    public void setIn_estimate_qty_value(String in_estimate_qty_value) {
        this.in_estimate_qty_value = in_estimate_qty_value;
    }

    public String getIn_mode_flag() {
        return in_mode_flag;
    }

    public void setIn_mode_flag(String in_mode_flag) {
        this.in_mode_flag = in_mode_flag;
    }

    @SerializedName("in_mode_flag")
    @Expose
    private String in_mode_flag;



      public Estqtydao(int in_qty_row_id, String in_qty_code, double in_actual_value, double in_wr_qty_value, String in_estimate_qty_value, String in_mode_flag)
      {
          this.in_qty_row_id=in_qty_row_id;
          this.in_qty_code = in_qty_code;
          this.in_actual_value = in_actual_value;
          this.in_wr_qty_value=in_wr_qty_value;
          this.in_estimate_qty_value=in_estimate_qty_value;
          this.in_mode_flag = in_mode_flag;
      }

    protected Estqtydao(Parcel in) {
        in_qty_row_id=in.readInt();
        in_qty_code = in.readString();
        in_actual_value = in.readDouble();
        in_wr_qty_value=in.readDouble();
        in_estimate_qty_value=in.readString();
        in_mode_flag=in.readString();
    }

    public static final Creator<Estqtydao> CREATOR = new Creator<Estqtydao>() {
        @Override
        public Estqtydao createFromParcel(Parcel in) {
            return new Estqtydao(in);
        }

        @Override
        public Estqtydao[] newArray(int size) {
            return new Estqtydao[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(in_qty_row_id);
        dest.writeString(in_qty_code);
        dest.writeDouble(in_actual_value);
        dest.writeDouble(in_wr_qty_value);
        dest.writeString(in_estimate_qty_value);
        dest.writeString(in_mode_flag);
    }
}
