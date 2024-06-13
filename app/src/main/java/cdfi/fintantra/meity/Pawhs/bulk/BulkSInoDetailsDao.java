package cdfi.fintantra.meity.Pawhs.bulk;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BulkSInoDetailsDao implements Parcelable {

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

    public BulkSInoDetailsDao(int in_slno_row_id, String in_slno, String in_temp1, String in_temp2, String in_mode_flag) {
        this.in_slno_row_id = in_slno_row_id;
        this.in_slno = in_slno;
        this.in_temp1 = in_temp1;
        this.in_temp2 = in_temp2;
        this.in_mode_flag = in_mode_flag;
    }

    protected BulkSInoDetailsDao(Parcel in) {
        in_slno_row_id = in.readInt();
        in_slno = in.readString();
        in_temp1 = in.readString();
        in_temp2 = in.readString();
        in_mode_flag = in.readString();
    }

    public static final Creator<BulkSInoDetailsDao> CREATOR = new Creator<BulkSInoDetailsDao>() {
        @Override
        public BulkSInoDetailsDao createFromParcel(Parcel in) {
            return new BulkSInoDetailsDao(in);
        }

        @Override
        public BulkSInoDetailsDao[] newArray(int size) {
            return new BulkSInoDetailsDao[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(in_slno_row_id);
        parcel.writeString(in_slno);
        parcel.writeString(in_temp1);
        parcel.writeString(in_temp2);
        parcel.writeString(in_mode_flag);
    }
}
