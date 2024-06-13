package cdfi.fintantra.meity.Pawhs.model.lotmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActualListDao implements Parcelable {

    private int id;

    @SerializedName("out_act_rowid")
    @Expose
    private int out_act_rowid;

    @SerializedName("out_lotno")
    @Expose
    private String out_lotno;

    @SerializedName("out_agg_code")
    @Expose
    private String out_agg_code;

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

    @SerializedName("out_actual_qty")
    @Expose
    private double out_actual_qty;

    @SerializedName("out_actual_price")
    @Expose
    private double out_actual_price;

    @SerializedName("out_actual_value")
    @Expose
    private double out_actual_value;

    @SerializedName("out_actual_date")
    @Expose
    private String out_actual_date;

    @SerializedName("out_advance_amt")
    @Expose
    private double out_advance_amt;

    @SerializedName("out_approve_date")
    @Expose
    private String out_approve_date;

    @SerializedName("out_pickup_date")
    @Expose
    private String out_pickup_date;

    @SerializedName("out_wr_date")
    @Expose
    private String out_wr_date;

    @SerializedName("out_no_of_bags")
    @Expose
    private String out_no_of_bags;

    @SerializedName("out_status")
    @Expose
    private String out_status;

    @SerializedName("out_actual_remarks")
    @Expose
    private String out_actual_remarks;

    @SerializedName("out_approved_remarks")
    @Expose
    private String out_approved_remarks;

    @SerializedName("out_pickup_remarks")
    @Expose
    private String out_pickup_remarks;

    @SerializedName("out_wr_remarks")
    @Expose
    private String out_wr_remarks;

    @SerializedName("in_actual_attach")
    @Expose
    private String in_actual_attach;


    @SerializedName("in_vehicle_no")
    @Expose
    private String in_vehicle_no;

    @SerializedName("In_Payment_type")
    @Expose
    private String In_Payment_type;

    @SerializedName("In_Bank_acc_no")
    @Expose
    private String In_Bank_acc_no;

    public String getIn_Payment_type() {
        return In_Payment_type;
    }

    public void setIn_Payment_type(String in_Payment_type) {
        In_Payment_type = in_Payment_type;
    }

    public String getIn_Bank_acc_no() {
        return In_Bank_acc_no;
    }

    public void setIn_Bank_acc_no(String in_Bank_acc_no) {
        In_Bank_acc_no = in_Bank_acc_no;
    }

    public String getIn_cheque_no() {
        return In_cheque_no;
    }

    public void setIn_cheque_no(String in_cheque_no) {
        In_cheque_no = in_cheque_no;
    }

    @SerializedName("In_cheque_no")
    @Expose
    private String In_cheque_no;

    public String getIn_LRP_Name() {
        return in_LRP_Name;
    }

    public void setIn_LRP_Name(String in_LRP_Name) {
        this.in_LRP_Name = in_LRP_Name;
    }

    @SerializedName("in_LRP_Name")
    @Expose
    private String in_LRP_Name;

    public String getIn_LRP_Mobile_no() {
        return In_LRP_Mobile_no;
    }

    public void setIn_LRP_Mobile_no(String in_LRP_Mobile_no) {
        In_LRP_Mobile_no = in_LRP_Mobile_no;
    }

    @SerializedName("In_LRP_Mobile_no")
    @Expose
    private String In_LRP_Mobile_no;

    public String getIn_vehicle_type() {
        return in_vehicle_type;
    }

    public void setIn_vehicle_type(String in_vehicle_type) {
        this.in_vehicle_type = in_vehicle_type;
    }

    public String getIn_destination() {
        return in_destination;
    }

    public void setIn_destination(String in_destination) {
        this.in_destination = in_destination;
    }

    @SerializedName("in_vehicle_type")
    @Expose
    private String in_vehicle_type;


    @SerializedName("in_destination")
    @Expose
    private String in_destination;


    public String getIn_actual_attach() {
        return in_actual_attach;
    }

    public void setIn_actual_attach(String in_actual_attach) {
        this.in_actual_attach = in_actual_attach;
    }

    public String getIn_vehicle_no() {
        return in_vehicle_no;
    }

    public void setIn_vehicle_no(String in_vehicle_no) {
        this.in_vehicle_no = in_vehicle_no;
    }

    public String getIn_qcperson_name() {
        return in_qcperson_name;
    }

    public void setIn_qcperson_name(String in_qcperson_name) {
        this.in_qcperson_name = in_qcperson_name;
    }

    @SerializedName("in_qcperson_name")
    @Expose
    private String in_qcperson_name;

    public ActualListDao(int id, int out_act_rowid, String out_lotno, String out_agg_code, String out_farmer_code, String out_farmer_name, String out_member_type, String out_item_code, String out_item_name, double out_actual_qty, double out_actual_price, double out_actual_value, String out_actual_date, double out_advance_amt, String out_approve_date, String out_pickup_date, String out_wr_date, String out_no_of_bags, String out_status, String out_actual_remarks, String out_approved_remarks, String out_pickup_remarks, String out_wr_remarks, String in_actual_attach, String in_qcperson_name, String in_vehicle_no, String in_vehicle_type, String in_destination, String in_LRP_Name, String In_LRP_Mobile_no, String In_Payment_type, String In_Bank_acc_no, String In_cheque_no) {
        this.id = id;
        this.out_act_rowid = out_act_rowid;
        this.out_lotno = out_lotno;
        this.out_agg_code = out_agg_code;
        this.out_farmer_code = out_farmer_code;
        this.out_farmer_name = out_farmer_name;
        this.out_member_type = out_member_type;
        this.out_item_code = out_item_code;
        this.out_item_name = out_item_name;
        this.out_actual_qty = out_actual_qty;
        this.out_actual_price = out_actual_price;
        this.out_actual_value = out_actual_value;
        this.out_actual_date = out_actual_date;
        this.out_advance_amt = out_advance_amt;
        this.out_approve_date = out_approve_date;
        this.out_pickup_date = out_pickup_date;
        this.out_wr_date = out_wr_date;
        this.out_no_of_bags = out_no_of_bags;
        this.out_status = out_status;
        this.out_actual_remarks = out_actual_remarks;
        this.out_approved_remarks = out_approved_remarks;
        this.out_pickup_remarks = out_pickup_remarks;
        this.out_wr_remarks = out_wr_remarks;
        this.in_actual_attach = in_actual_attach;
        this.in_qcperson_name = in_qcperson_name;
        this.in_vehicle_no = in_vehicle_no;
        this.in_vehicle_type = in_vehicle_type;
        this.in_destination = in_destination;
        this.in_LRP_Name = in_LRP_Name;
        this.In_LRP_Mobile_no = In_LRP_Mobile_no;
        this.In_Payment_type =In_Payment_type;
        this.In_Bank_acc_no = In_Bank_acc_no;
        this.In_cheque_no =In_cheque_no;
    }

    public ActualListDao() {
    }

    protected ActualListDao(Parcel in) {
        out_act_rowid = in.readInt();
        out_lotno = in.readString();
        out_agg_code = in.readString();
        out_farmer_code = in.readString();
        out_farmer_name = in.readString();
        out_member_type = in.readString();
        out_item_code = in.readString();
        out_item_name = in.readString();
        out_actual_qty = in.readDouble();
        out_actual_price = in.readDouble();
        out_actual_value = in.readDouble();
        out_actual_date = in.readString();
        out_advance_amt = in.readDouble();
        out_approve_date = in.readString();
        out_pickup_date = in.readString();
        out_wr_date = in.readString();
        out_no_of_bags = in.readString();
        out_status = in.readString();
        out_actual_remarks = in.readString();
        out_approved_remarks = in.readString();
        out_pickup_remarks = in.readString();
        out_wr_remarks = in.readString();
        in_actual_attach = in.readString();
        in_qcperson_name = in.readString();
        in_vehicle_no = in.readString();
        in_vehicle_type = in.readString();
        in_destination = in.readString();
        in_LRP_Name = in.readString();
        In_LRP_Mobile_no = in.readString();
        In_Payment_type = in.readString();
        In_Bank_acc_no = in.readString();
        In_cheque_no = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static final Creator<ActualListDao> CREATOR = new Creator<ActualListDao>() {
        @Override
        public ActualListDao createFromParcel(Parcel in) {
            return new ActualListDao(in);
        }

        @Override
        public ActualListDao[] newArray(int size) {
            return new ActualListDao[size];
        }
    };

    public int getOut_act_rowid() {
        return out_act_rowid;
    }

    public void setOut_act_rowid(int out_act_rowid) {
        this.out_act_rowid = out_act_rowid;
    }

    public String getOut_lotno() {
        return out_lotno;
    }

    public void setOut_lotno(String out_lotno) {
        this.out_lotno = out_lotno;
    }

    public String getOut_agg_code() {
        return out_agg_code;
    }

    public void setOut_agg_code(String out_agg_code) {
        this.out_agg_code = out_agg_code;
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

    public double getOut_actual_qty() {
        return out_actual_qty;
    }

    public void setOut_actual_qty(double out_actual_qty) {
        this.out_actual_qty = out_actual_qty;
    }

    public double getOut_actual_price() {
        return out_actual_price;
    }

    public void setOut_actual_price(double out_actual_price) {
        this.out_actual_price = out_actual_price;
    }

    public double getOut_actual_value() {
        return out_actual_value;
    }

    public void setOut_actual_value(double out_actual_value) {
        this.out_actual_value = out_actual_value;
    }

    public String getOut_actual_date() {
        return out_actual_date;
    }

    public void setOut_actual_date(String out_actual_date) {
        this.out_actual_date = out_actual_date;
    }

    public double getOut_advance_amt() {
        return out_advance_amt;
    }

    public void setOut_advance_amt(double out_advance_amt) {
        this.out_advance_amt = out_advance_amt;
    }

    public String getOut_approve_date() {
        return out_approve_date;
    }

    public void setOut_approve_date(String out_approve_date) {
        this.out_approve_date = out_approve_date;
    }

    public String getOut_pickup_date() {
        return out_pickup_date;
    }

    public void setOut_pickup_date(String out_pickup_date) {
        this.out_pickup_date = out_pickup_date;
    }

    public String getOut_wr_date() {
        return out_wr_date;
    }

    public void setOut_wr_date(String out_wr_date) {
        this.out_wr_date = out_wr_date;
    }

    public String getOut_no_of_bags() {
        return out_no_of_bags;
    }

    public void setOut_no_of_bags(String out_no_of_bags) {
        this.out_no_of_bags = out_no_of_bags;
    }

    public String getOut_status() {
        return out_status;
    }

    public void setOut_status(String out_status) {
        this.out_status = out_status;
    }

    public String getOut_actual_remarks() {
        return out_actual_remarks;
    }

    public void setOut_actual_remarks(String out_actual_remarks) {
        this.out_actual_remarks = out_actual_remarks;
    }

    public String getOut_approved_remarks() {
        return out_approved_remarks;
    }

    public void setOut_approved_remarks(String out_approved_remarks) {
        this.out_approved_remarks = out_approved_remarks;
    }

    public String getOut_pickup_remarks() {
        return out_pickup_remarks;
    }

    public void setOut_pickup_remarks(String out_pickup_remarks) {
        this.out_pickup_remarks = out_pickup_remarks;
    }

    public String getOut_wr_remarks() {
        return out_wr_remarks;
    }

    public void setOut_wr_remarks(String out_wr_remarks) {
        this.out_wr_remarks = out_wr_remarks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(out_act_rowid);
        parcel.writeString(out_lotno);
        parcel.writeString(out_agg_code);
        parcel.writeString(out_farmer_code);
        parcel.writeString(out_farmer_name);
        parcel.writeString(out_member_type);
        parcel.writeString(out_item_code);
        parcel.writeString(out_item_name);
        parcel.writeDouble(out_actual_qty);
        parcel.writeDouble(out_actual_price);
        parcel.writeDouble(out_actual_value);
        parcel.writeString(out_actual_date);
        parcel.writeDouble(out_advance_amt);
        parcel.writeString(out_approve_date);
        parcel.writeString(out_pickup_date);
        parcel.writeString(out_wr_date);
        parcel.writeString(out_no_of_bags);
        parcel.writeString(out_status);
        parcel.writeString(out_actual_remarks);
        parcel.writeString(out_approved_remarks);
        parcel.writeString(out_pickup_remarks);
        parcel.writeString(out_wr_remarks);
        parcel.writeString(in_actual_attach);
        parcel.writeString(in_qcperson_name);
        parcel.writeString(in_vehicle_no);
        parcel.writeString(in_vehicle_type);
        parcel.writeString(in_destination);
        parcel.writeString(in_LRP_Name);
        parcel.writeString(In_LRP_Mobile_no);
        parcel.writeString(In_Payment_type);
        parcel.writeString(In_Bank_acc_no);
        parcel.writeString(In_cheque_no);
    }
}
