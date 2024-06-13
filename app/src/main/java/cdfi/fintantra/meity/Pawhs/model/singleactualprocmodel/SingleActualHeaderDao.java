package cdfi.fintantra.meity.Pawhs.model.singleactualprocmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleActualHeaderDao {

    private int id;

    @SerializedName("ioU_act_rowid")
    @Expose
    private int ioU_act_rowid;

    @SerializedName("ioU_agg_code")
    @Expose
    private String ioU_agg_code;

    @SerializedName("ioU_lotno")
    @Expose
    private String ioU_lotno;

    @SerializedName("in_farmer_code")
    @Expose
    private String in_farmer_code;

    @SerializedName("in_farmer_name")
    @Expose
    private String in_farmer_name;

    @SerializedName("in_member_type")
    @Expose
    private String in_member_type;

    @SerializedName("in_item_code")
    @Expose
    private String in_item_code;

    @SerializedName("in_item_name")
    @Expose
    private String in_item_name;

    @SerializedName("in_actual_qty")
    @Expose
    private double in_actual_qty;

    @SerializedName("in_actual_price")
    @Expose
    private double in_actual_price;

    @SerializedName("in_actual_value")
    @Expose
    private double in_actual_value;

    @SerializedName("in_advance_amt")
    @Expose
    private double in_advance_amt;

    @SerializedName("in_no_of_bags")
    @Expose
    private int in_no_of_bags;

    @SerializedName("in_status")
    @Expose
    private String in_status;

    @SerializedName("in_actual_remarks")
    @Expose
    private String in_actual_remarks;

    @SerializedName("in_approved_remarks")
    @Expose
    private String in_approved_remarks;

    @SerializedName("in_pickup_remarks")
    @Expose
    private String in_pickup_remarks;

    @SerializedName("in_wr_remarks")
    @Expose
    private String in_wr_remarks;

    @SerializedName("in_mode_flag")
    @Expose
    private String in_mode_flag;

    @SerializedName("in_row_timestamp")
    @Expose
    private String in_row_timestamp;

    @SerializedName("in_actual_attach")
    @Expose
    private String in_actual_attach;


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

    @SerializedName("in_vehicle_no")
    @Expose
    private String in_vehicle_no;

    @SerializedName("in_qcperson_name")
    @Expose
    private String in_qcperson_name;

    @SerializedName("in_vehicle_type")
    @Expose
    private String in_vehicle_type;

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

    @SerializedName("in_destination")
    @Expose
    private String in_destination;

    public SingleActualHeaderDao() {
    }

    public SingleActualHeaderDao(int id, int ioU_act_rowid, String ioU_agg_code, String ioU_lotno, String in_farmer_code, String in_farmer_name, String in_member_type, String in_item_code, String in_item_name, double in_actual_qty, double in_actual_price, double in_actual_value, double in_advance_amt, int in_no_of_bags, String in_status, String in_actual_remarks, String in_approved_remarks, String in_pickup_remarks, String in_wr_remarks, String in_mode_flag, String in_row_timestamp, String in_actual_attach, String in_qcperson_name, String in_vehicle_no, String in_vehicle_type, String in_destination, String in_LRP_Name, String In_LRP_Mobile_no, String In_Payment_type, String In_Bank_acc_no, String In_cheque_no) {
        this.id = id;
        this.ioU_act_rowid = ioU_act_rowid;
        this.ioU_agg_code = ioU_agg_code;
        this.ioU_lotno = ioU_lotno;
        this.in_farmer_code = in_farmer_code;
        this.in_farmer_name = in_farmer_name;
        this.in_member_type = in_member_type;
        this.in_item_code = in_item_code;
        this.in_item_name = in_item_name;
        this.in_actual_qty = in_actual_qty;
        this.in_actual_price = in_actual_price;
        this.in_actual_value = in_actual_value;
        this.in_advance_amt = in_advance_amt;
        this.in_no_of_bags = in_no_of_bags;
        this.in_status = in_status;
        this.in_actual_remarks = in_actual_remarks;
        this.in_approved_remarks = in_approved_remarks;
        this.in_pickup_remarks = in_pickup_remarks;
        this.in_wr_remarks = in_wr_remarks;
        this.in_mode_flag = in_mode_flag;
        this.in_row_timestamp = in_row_timestamp;
        this.in_actual_attach = in_actual_attach;
        this.in_qcperson_name = in_qcperson_name;
        this.in_vehicle_no=in_vehicle_no;
        this.in_vehicle_type=in_vehicle_type;
        this.in_destination = in_destination;
        this.in_LRP_Name=in_LRP_Name;
        this.In_LRP_Mobile_no = In_LRP_Mobile_no;
        this.In_Payment_type =In_Payment_type;
        this.In_Bank_acc_no =In_Bank_acc_no;
        this.In_cheque_no =In_cheque_no;
    }

    public int getIoU_act_rowid() {
        return ioU_act_rowid;
    }

    public void setIoU_act_rowid(int ioU_act_rowid) {
        this.ioU_act_rowid = ioU_act_rowid;
    }

    public String getIoU_agg_code() {
        return ioU_agg_code;
    }

    public void setIoU_agg_code(String ioU_agg_code) {
        this.ioU_agg_code = ioU_agg_code;
    }

    public String getIoU_lotno() {
        return ioU_lotno;
    }

    public void setIoU_lotno(String ioU_lotno) {
        this.ioU_lotno = ioU_lotno;
    }

    public String getIn_farmer_code() {
        return in_farmer_code;
    }

    public void setIn_farmer_code(String in_farmer_code) {
        this.in_farmer_code = in_farmer_code;
    }

    public String getIn_farmer_name() {
        return in_farmer_name;
    }

    public void setIn_farmer_name(String in_farmer_name) {
        this.in_farmer_name = in_farmer_name;
    }

    public String getIn_member_type() {
        return in_member_type;
    }

    public void setIn_member_type(String in_member_type) {
        this.in_member_type = in_member_type;
    }

    public String getIn_item_code() {
        return in_item_code;
    }

    public void setIn_item_code(String in_item_code) {
        this.in_item_code = in_item_code;
    }

    public String getIn_item_name() {
        return in_item_name;
    }

    public void setIn_item_name(String in_item_name) {
        this.in_item_name = in_item_name;
    }

    public double getIn_actual_qty() {
        return in_actual_qty;
    }

    public void setIn_actual_qty(double in_actual_qty) {
        this.in_actual_qty = in_actual_qty;
    }

    public double getIn_actual_price() {
        return in_actual_price;
    }

    public void setIn_actual_price(double in_actual_price) {
        this.in_actual_price = in_actual_price;
    }

    public double getIn_actual_value() {
        return in_actual_value;
    }

    public void setIn_actual_value(double in_actual_value) {
        this.in_actual_value = in_actual_value;
    }

    public double getIn_advance_amt() {
        return in_advance_amt;
    }

    public void setIn_advance_amt(double in_advance_amt) {
        this.in_advance_amt = in_advance_amt;
    }

    public int getIn_no_of_bags() {
        return in_no_of_bags;
    }

    public void setIn_no_of_bags(int in_no_of_bags) {
        this.in_no_of_bags = in_no_of_bags;
    }

    public String getIn_status() {
        return in_status;
    }

    public void setIn_status(String in_status) {
        this.in_status = in_status;
    }

    public String getIn_actual_remarks() {
        return in_actual_remarks;
    }

    public void setIn_actual_remarks(String in_actual_remarks) {
        this.in_actual_remarks = in_actual_remarks;
    }

    public String getIn_approved_remarks() {
        return in_approved_remarks;
    }

    public void setIn_approved_remarks(String in_approved_remarks) {
        this.in_approved_remarks = in_approved_remarks;
    }

    public String getIn_pickup_remarks() {
        return in_pickup_remarks;
    }

    public void setIn_pickup_remarks(String in_pickup_remarks) {
        this.in_pickup_remarks = in_pickup_remarks;
    }

    public String getIn_wr_remarks() {
        return in_wr_remarks;
    }

    public void setIn_wr_remarks(String in_wr_remarks) {
        this.in_wr_remarks = in_wr_remarks;
    }

    public String getIn_mode_flag() {
        return in_mode_flag;
    }

    public void setIn_mode_flag(String in_mode_flag) {
        this.in_mode_flag = in_mode_flag;
    }

    public String getIn_row_timestamp() {
        return in_row_timestamp;
    }

    public void setIn_row_timestamp(String in_row_timestamp) {
        this.in_row_timestamp = in_row_timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
