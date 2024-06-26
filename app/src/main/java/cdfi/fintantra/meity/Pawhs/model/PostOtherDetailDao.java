package cdfi.fintantra.meity.Pawhs.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostOtherDetailDao implements Parcelable {

    private int id;

    @SerializedName("In_otherdtl_row_id")
    @Expose
    private int In_otherdtl_row_id;

    @SerializedName("in_packaging_cost")
    @Expose
    private int in_packaging_cost;

    @SerializedName("in_transporting_cost")
    @Expose
    private int in_transporting_cost;

    @SerializedName("in_unpacking_cost")
    @Expose
    private int in_unpacking_cost;

    @SerializedName("in_local_packaging_cost")
    @Expose
    private int in_local_packaging_cost;

    @SerializedName("in_local_transporting_cost")
    @Expose
    private int in_local_transporting_cost;

    @SerializedName("in_temp_cost")
    @Expose
    private int in_temp_cost;

    @SerializedName("in_temp_cost1")
    @Expose
    private int in_temp_cost1;

    @SerializedName("in_temp_cost2")
    @Expose
    private int in_temp_cost2;

    @SerializedName("in_mode_flag")
    @Expose
    private String in_mode_flag;

    private String statusValue;
    private String lotno;

    public PostOtherDetailDao(int in_otherdtl_row_id, int in_packaging_cost, int in_transporting_cost, int in_unpacking_cost, int in_local_packaging_cost, int in_local_transporting_cost, int in_temp_cost, int in_temp_cost1, int in_temp_cost2, String in_mode_flag) {
        In_otherdtl_row_id = in_otherdtl_row_id;
        this.in_packaging_cost = in_packaging_cost;
        this.in_transporting_cost = in_transporting_cost;
        this.in_unpacking_cost = in_unpacking_cost;
        this.in_local_packaging_cost = in_local_packaging_cost;
        this.in_local_transporting_cost = in_local_transporting_cost;
        this.in_temp_cost = in_temp_cost;
        this.in_temp_cost1 = in_temp_cost1;
        this.in_temp_cost2 = in_temp_cost2;
        this.in_mode_flag = in_mode_flag;
    }

    public PostOtherDetailDao(int id, int in_otherdtl_row_id, int in_packaging_cost, int in_transporting_cost, int in_unpacking_cost, int in_local_packaging_cost, int in_local_transporting_cost, int in_temp_cost, int in_temp_cost1, int in_temp_cost2, String in_mode_flag, String statusValue, String lotno) {
        this.id = id;
        this.In_otherdtl_row_id = in_otherdtl_row_id;
        this.in_packaging_cost = in_packaging_cost;
        this.in_transporting_cost = in_transporting_cost;
        this.in_unpacking_cost = in_unpacking_cost;
        this.in_local_packaging_cost = in_local_packaging_cost;
        this.in_local_transporting_cost = in_local_transporting_cost;
        this.in_temp_cost = in_temp_cost;
        this.in_temp_cost1 = in_temp_cost1;
        this.in_temp_cost2 = in_temp_cost2;
        this.in_mode_flag = in_mode_flag;
        this.statusValue = statusValue;
        this.lotno=lotno;
    }

    public PostOtherDetailDao() {
    }

    protected PostOtherDetailDao(Parcel in) {
        In_otherdtl_row_id = in.readInt();
        in_packaging_cost = in.readInt();
        in_transporting_cost = in.readInt();
        in_unpacking_cost = in.readInt();
        in_local_packaging_cost = in.readInt();
        in_local_transporting_cost = in.readInt();
        in_temp_cost = in.readInt();
        in_temp_cost1 = in.readInt();
        in_temp_cost2 = in.readInt();
        in_mode_flag = in.readString();
    }

    public static final Creator<PostOtherDetailDao> CREATOR = new Creator<PostOtherDetailDao>() {
        @Override
        public PostOtherDetailDao createFromParcel(Parcel in) {
            return new PostOtherDetailDao(in);
        }

        @Override
        public PostOtherDetailDao[] newArray(int size) {
            return new PostOtherDetailDao[size];
        }
    };

    public int getIn_otherdtl_row_id() {
        return In_otherdtl_row_id;
    }

    public void setIn_otherdtl_row_id(int in_otherdtl_row_id) {
        In_otherdtl_row_id = in_otherdtl_row_id;
    }

    public int getIn_packaging_cost() {
        return in_packaging_cost;
    }

    public void setIn_packaging_cost(int in_packaging_cost) {
        this.in_packaging_cost = in_packaging_cost;
    }

    public int getIn_transporting_cost() {
        return in_transporting_cost;
    }

    public void setIn_transporting_cost(int in_transporting_cost) {
        this.in_transporting_cost = in_transporting_cost;
    }

    public int getIn_unpacking_cost() {
        return in_unpacking_cost;
    }

    public void setIn_unpacking_cost(int in_unpacking_cost) {
        this.in_unpacking_cost = in_unpacking_cost;
    }

    public int getIn_local_packaging_cost() {
        return in_local_packaging_cost;
    }

    public void setIn_local_packaging_cost(int in_local_packaging_cost) {
        this.in_local_packaging_cost = in_local_packaging_cost;
    }

    public int getIn_local_transporting_cost() {
        return in_local_transporting_cost;
    }

    public void setIn_local_transporting_cost(int in_local_transporting_cost) {
        this.in_local_transporting_cost = in_local_transporting_cost;
    }

    public int getIn_temp_cost() {
        return in_temp_cost;
    }

    public void setIn_temp_cost(int in_temp_cost) {
        this.in_temp_cost = in_temp_cost;
    }

    public int getIn_temp_cost1() {
        return in_temp_cost1;
    }

    public void setIn_temp_cost1(int in_temp_cost1) {
        this.in_temp_cost1 = in_temp_cost1;
    }

    public int getIn_temp_cost2() {
        return in_temp_cost2;
    }

    public void setIn_temp_cost2(int in_temp_cost2) {
        this.in_temp_cost2 = in_temp_cost2;
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
        parcel.writeInt(In_otherdtl_row_id);
        parcel.writeInt(in_packaging_cost);
        parcel.writeInt(in_transporting_cost);
        parcel.writeInt(in_unpacking_cost);
        parcel.writeInt(in_local_packaging_cost);
        parcel.writeInt(in_local_transporting_cost);
        parcel.writeInt(in_temp_cost);
        parcel.writeInt(in_temp_cost1);
        parcel.writeInt(in_temp_cost2);
        parcel.writeString(in_mode_flag);
    }
}
