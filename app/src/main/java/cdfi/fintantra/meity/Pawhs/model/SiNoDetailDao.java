package cdfi.fintantra.meity.Pawhs.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SiNoDetailDao implements Parcelable {

    private int id;

    @SerializedName("in_slno_row_id")
    @Expose
    private int in_slno_row_id;

    @SerializedName("in_slno")
    @Expose
    private String in_slno;

    @SerializedName("in_temp1")
    @Expose
    private String in_temp1;

    @SerializedName("in_temp2")
    @Expose
    private String in_temp2;

    @SerializedName("in_mode_flag")
    @Expose
    private String in_mode_flag;

    @SerializedName("in_agg_code")
    @Expose
    private String in_agg_code;

    @SerializedName("in_lotno")
    @Expose
    private String in_lotno;

    private String statusValue;

    public SiNoDetailDao(int in_slno_row_id, String in_slno, String in_temp1, String in_temp2, String in_mode_flag) {
        this.in_slno_row_id = in_slno_row_id;
        this.in_slno = in_slno;
        this.in_temp1 = in_temp1;
        this.in_temp2 = in_temp2;
        this.in_mode_flag = in_mode_flag;
    }

    public SiNoDetailDao(int id, int in_slno_row_id, String in_slno, String in_temp1, String in_temp2, String in_mode_flag, String in_agg_code, String in_lotno, String statusValue) {
        this.id=id;
        this.in_slno_row_id = in_slno_row_id;
        this.in_slno = in_slno;
        this.in_temp1 = in_temp1;
        this.in_temp2 = in_temp2;
        this.in_mode_flag = in_mode_flag;
        this.in_agg_code = in_agg_code;
        this.in_lotno = in_lotno;
        this.statusValue=statusValue;
    }

    public SiNoDetailDao() {
    }

    protected SiNoDetailDao(Parcel in) {
        in_slno_row_id = in.readInt();
        in_slno = in.readString();
        in_temp1 = in.readString();
        in_temp2 = in.readString();
        in_mode_flag = in.readString();
        in_agg_code = in.readString();
        in_lotno = in.readString();
        statusValue=in.readString();
    }

    public static final Creator<SiNoDetailDao> CREATOR = new Creator<SiNoDetailDao>() {
        @Override
        public SiNoDetailDao createFromParcel(Parcel in) {
            return new SiNoDetailDao(in);
        }

        @Override
        public SiNoDetailDao[] newArray(int size) {
            return new SiNoDetailDao[size];
        }
    };

    public int getIn_slno_row_id() {
        return in_slno_row_id;
    }

    public void setIn_slno_row_id(int in_slno_row_id) {
        this.in_slno_row_id = in_slno_row_id;
    }

    public String getIn_slno() {
        return in_slno;
    }

    public void setIn_slno(String in_slno) {
        this.in_slno = in_slno;
    }

    public String getIn_temp1() {
        return in_temp1;
    }

    public void setIn_temp1(String in_temp1) {
        this.in_temp1 = in_temp1;
    }

    public String getIn_temp2() {
        return in_temp2;
    }

    public void setIn_temp2(String in_temp2) {
        this.in_temp2 = in_temp2;
    }

    public String getIn_mode_flag() {
        return in_mode_flag;
    }

    public void setIn_mode_flag(String in_mode_flag) {
        this.in_mode_flag = in_mode_flag;
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
        parcel.writeInt(in_slno_row_id);
        parcel.writeString(in_slno);
        parcel.writeString(in_temp1);
        parcel.writeString(in_temp2);
        parcel.writeString(in_mode_flag);
        parcel.writeString(in_agg_code);
        parcel.writeString(in_lotno);
        parcel.writeString(statusValue);
    }
}
