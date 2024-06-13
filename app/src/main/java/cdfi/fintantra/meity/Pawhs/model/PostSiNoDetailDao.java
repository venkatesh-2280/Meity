package cdfi.fintantra.meity.Pawhs.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostSiNoDetailDao implements Parcelable {

    private int id;

    @SerializedName("in_slno_row_id")
    @Expose
    private int In_slno_row_id;

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

    private String statusValue;
    private String lotno;

    public PostSiNoDetailDao(int in_slno_row_id, String in_slno, String in_temp1, String in_temp2, String in_mode_flag) {
        this.In_slno_row_id = in_slno_row_id;
        this.in_slno = in_slno;
        this.in_temp1 = in_temp1;
        this.in_temp2 = in_temp2;
        this.in_mode_flag = in_mode_flag;
    }
    public PostSiNoDetailDao(int in_slno_row_id, String in_slno, String in_temp1, String in_temp2, String in_mode_flag, String statusValue, String lotno) {
        this.In_slno_row_id = in_slno_row_id;
        this.in_slno = in_slno;
        this.in_temp1 = in_temp1;
        this.in_temp2 = in_temp2;
        this.in_mode_flag = in_mode_flag;
        this.statusValue = statusValue;
        this.lotno=lotno;
    }

    public PostSiNoDetailDao(int id, int in_slno_row_id, String in_slno, String in_temp1, String in_temp2, String in_mode_flag, String statusValue, String lotno) {
        this.id = id;
        In_slno_row_id = in_slno_row_id;
        this.in_slno = in_slno;
        this.in_temp1 = in_temp1;
        this.in_temp2 = in_temp2;
        this.in_mode_flag = in_mode_flag;
        this.statusValue = statusValue;
        this.lotno=lotno;
    }

    public PostSiNoDetailDao() {
    }

    protected PostSiNoDetailDao(Parcel in) {
        In_slno_row_id = in.readInt();
        in_slno = in.readString();
        in_temp1 = in.readString();
        in_temp2 = in.readString();
        in_mode_flag = in.readString();
    }

    public static final Creator<PostSiNoDetailDao> CREATOR = new Creator<PostSiNoDetailDao>() {
        @Override
        public PostSiNoDetailDao createFromParcel(Parcel in) {
            return new PostSiNoDetailDao(in);
        }

        @Override
        public PostSiNoDetailDao[] newArray(int size) {
            return new PostSiNoDetailDao[size];
        }
    };

    public int getIn_slno_row_id() {
        return In_slno_row_id;
    }

    public void setIn_slno_row_id(int in_slno_row_id) {
        this.In_slno_row_id = in_slno_row_id;
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
        parcel.writeInt(In_slno_row_id);
        parcel.writeString(in_slno);
        parcel.writeString(in_temp1);
        parcel.writeString(in_temp2);
        parcel.writeString(in_mode_flag);
    }
}
