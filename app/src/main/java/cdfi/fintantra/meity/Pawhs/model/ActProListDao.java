package cdfi.fintantra.meity.Pawhs.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActProListDao implements Parcelable {

    private int id;

    @SerializedName("out_act_rowid")
    @Expose
    private int out_act_rowid;

    @SerializedName("out_agg_code")
    @Expose
    private String out_agg_code;

    @SerializedName("out_lotno")
    @Expose
    private String out_lotno;

    @SerializedName("out_farmer_code")
    @Expose
    private String out_farmer_code;

    @SerializedName("out_farmer_name")
    @Expose
    private String out_farmer_name;

    @SerializedName("out_member_type")
    @Expose
    private String out_member_type;

    @SerializedName("out_item_code")
    @Expose
    private String out_item_code;

    @SerializedName("out_item_name")
    @Expose
    private String out_item_name;

    @SerializedName("out_status")
    @Expose
    private String out_status;

    @SerializedName("out_row_timestamp")
    @Expose
    private String out_row_timestamp;

    public ActProListDao(int id, int out_act_rowid, String out_agg_code, String out_lotno, String out_farmer_code, String out_farmer_name, String out_member_type, String out_item_code, String out_item_name, String out_status, String out_row_timestamp) {
        this.id = id;
        this.out_act_rowid = out_act_rowid;
        this.out_agg_code = out_agg_code;
        this.out_lotno = out_lotno;
        this.out_farmer_code = out_farmer_code;
        this.out_farmer_name = out_farmer_name;
        this.out_member_type = out_member_type;
        this.out_item_code = out_item_code;
        this.out_item_name = out_item_name;
        this.out_status = out_status;
        this.out_row_timestamp = out_row_timestamp;
    }

    public ActProListDao() {
    }

    protected ActProListDao(Parcel in) {
        id = in.readInt();
        out_act_rowid = in.readInt();
        out_agg_code = in.readString();
        out_lotno = in.readString();
        out_farmer_code = in.readString();
        out_farmer_name = in.readString();
        out_member_type = in.readString();
        out_item_code = in.readString();
        out_item_name = in.readString();
        out_status = in.readString();
        out_row_timestamp = in.readString();
    }

    public static final Creator<ActProListDao> CREATOR = new Creator<ActProListDao>() {
        @Override
        public ActProListDao createFromParcel(Parcel in) {
            return new ActProListDao(in);
        }

        @Override
        public ActProListDao[] newArray(int size) {
            return new ActProListDao[size];
        }
    };

    public int getOut_act_rowid() {
        return out_act_rowid;
    }

    public void setOut_act_rowid(int out_act_rowid) {
        this.out_act_rowid = out_act_rowid;
    }

    public String getOut_agg_code() {
        return out_agg_code;
    }

    public void setOut_agg_code(String out_agg_code) {
        this.out_agg_code = out_agg_code;
    }

    public String getOut_lotno() {
        return out_lotno;
    }

    public void setOut_lotno(String out_lotno) {
        this.out_lotno = out_lotno;
    }

    public String getOut_farmer_code() {
        return out_farmer_code;
    }

    public void setOut_farmer_code(String out_farmer_code) {
        this.out_farmer_code = out_farmer_code;
    }

    public String getOut_farmer_name() {
        return out_farmer_name;
    }

    public void setOut_farmer_name(String out_farmer_name) {
        this.out_farmer_name = out_farmer_name;
    }

    public String getOut_member_type() {
        return out_member_type;
    }

    public void setOut_member_type(String out_member_type) {
        this.out_member_type = out_member_type;
    }

    public String getOut_item_code() {
        return out_item_code;
    }

    public void setOut_item_code(String out_item_code) {
        this.out_item_code = out_item_code;
    }

    public String getOut_item_name() {
        return out_item_name;
    }

    public void setOut_item_name(String out_item_name) {
        this.out_item_name = out_item_name;
    }

    public String getOut_status() {
        return out_status;
    }

    public void setOut_status(String out_status) {
        this.out_status = out_status;
    }

    public String getOut_row_timestamp() {
        return out_row_timestamp;
    }

    public void setOut_row_timestamp(String out_row_timestamp) {
        this.out_row_timestamp = out_row_timestamp;
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

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(out_act_rowid);
        parcel.writeString(out_agg_code);
        parcel.writeString(out_lotno);
        parcel.writeString(out_farmer_code);
        parcel.writeString(out_farmer_name);
        parcel.writeString(out_member_type);
        parcel.writeString(out_item_code);
        parcel.writeString(out_item_name);
        parcel.writeString(out_status);
        parcel.writeString(out_row_timestamp);
    }
}
