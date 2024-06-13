package cdfi.fintantra.meity.Pawhs.bulk;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BulkOtherDetailDao implements Parcelable {

    @SerializedName("in_otherdtl_row_id")
    @Expose
    private int in_otherdtl_row_id;

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

    public BulkOtherDetailDao(int in_otherdtl_row_id, int in_packaging_cost, int in_transporting_cost, int in_unpacking_cost, int in_local_packaging_cost, int in_local_transporting_cost, int in_temp_cost, int in_temp_cost1, int in_temp_cost2, String in_mode_flag) {
        this.in_otherdtl_row_id = in_otherdtl_row_id;
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

    public BulkOtherDetailDao() {

    }

    protected BulkOtherDetailDao(Parcel in) {
        in_otherdtl_row_id = in.readInt();
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

    public static final Creator<BulkOtherDetailDao> CREATOR = new Creator<BulkOtherDetailDao>() {
        @Override
        public BulkOtherDetailDao createFromParcel(Parcel in) {
            return new BulkOtherDetailDao(in);
        }

        @Override
        public BulkOtherDetailDao[] newArray(int size) {
            return new BulkOtherDetailDao[size];
        }
    };

    public int getIn_otherdtl_row_id() {
        return in_otherdtl_row_id;
    }

    public void setIn_otherdtl_row_id(int in_otherdtl_row_id) {
        this.in_otherdtl_row_id = in_otherdtl_row_id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(in_otherdtl_row_id);
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
