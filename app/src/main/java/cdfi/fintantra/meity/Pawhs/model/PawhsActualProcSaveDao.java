package cdfi.fintantra.meity.Pawhs.model;

public class PawhsActualProcSaveDao {

    private int id;
    private int act_row_id;
    private String aggCode;
    private String lotNo;
    private String farmerCode;
    private String farmerName;
    private String memberType;
    private String itemCode;
    private String itemName;
    private String actualqty;
    private String actualPrice;
    private String actualValue;
    private String actualDate;
    private String advanceAmount;
    private String approveDate;
    private String rejectDate;
    private String pickupDate;
    private String wrDate;
    private String noofBags;
    private String status;
    private String actualRemarks;
    private String approveRemarks;
    private String rejectRemarks;
    private String pickUpRemarks;
    private String wrRemarks;
    private String statusValue;
    private String modeFlag;
    private String isCloud;
    private String acceptQty;
    private String in_actual_attach;
    private String in_vehicle_no;
    private String In_cheque_no;
    private String In_Bank_acc_no;

    public String getIn_cheque_no() {
        return In_cheque_no;
    }

    public void setIn_cheque_no(String in_cheque_no) {
        In_cheque_no = in_cheque_no;
    }

    public String getIn_Bank_acc_no() {
        return In_Bank_acc_no;
    }

    public void setIn_Bank_acc_no(String in_Bank_acc_no) {
        In_Bank_acc_no = in_Bank_acc_no;
    }

    public String getIn_Payment_type() {
        return In_Payment_type;
    }

    public void setIn_Payment_type(String in_Payment_type) {
        In_Payment_type = in_Payment_type;
    }

    private String In_Payment_type;


    public String getIn_LRP_Name() {
        return in_LRP_Name;
    }

    public void setIn_LRP_Name(String in_LRP_Name) {
        this.in_LRP_Name = in_LRP_Name;
    }

    private String in_LRP_Name;

    public String getIn_LRP_Mobile_no() {
        return In_LRP_Mobile_no;
    }

    public void setIn_LRP_Mobile_no(String in_LRP_Mobile_no) {
        In_LRP_Mobile_no = in_LRP_Mobile_no;
    }

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

    private String in_vehicle_type;
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

    private String in_qcperson_name;

    public PawhsActualProcSaveDao(int id, int act_row_id, String aggCode, String lotNo, String farmerCode, String farmerName, String memberType, String itemCode, String itemName, String actualqty, String actualPrice, String actualValue, String actualDate, String advanceAmount, String approveDate, String rejectDate, String pickupDate, String wrDate, String noofBags, String status, String actualRemarks, String approveRemarks, String rejectRemarks, String pickUpRemarks, String wrRemarks, String statusValue, String modeFlag, String isCloud, String acceptQty, String in_actual_attach, String in_qcperson_name, String in_vehicle_no, String in_vehicle_type, String in_destination, String in_LRP_Name, String In_LRP_Mobile_no, String In_Payment_type, String In_Bank_acc_no, String In_cheque_no) {
        this.id = id;
        this.act_row_id = act_row_id;
        this.aggCode=aggCode;
        this.lotNo = lotNo;
        this.farmerCode = farmerCode;
        this.farmerName = farmerName;
        this.memberType = memberType;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.actualqty = actualqty;
        this.actualPrice = actualPrice;
        this.actualValue = actualValue;
        this.actualDate = actualDate;
        this.advanceAmount = advanceAmount;
        this.approveDate = approveDate;
        this.rejectDate = rejectDate;
        this.pickupDate = pickupDate;
        this.wrDate = wrDate;
        this.noofBags = noofBags;
        this.status = status;
        this.actualRemarks = actualRemarks;
        this.approveRemarks = approveRemarks;
        this.rejectRemarks = rejectRemarks;
        this.pickUpRemarks = pickUpRemarks;
        this.wrRemarks = wrRemarks;
        this.statusValue = statusValue;
        this.modeFlag=modeFlag;
        this.isCloud=isCloud;
        this.acceptQty=acceptQty;
        this.in_actual_attach=in_actual_attach;
        this.in_qcperson_name=in_qcperson_name;
        this.in_vehicle_no=in_vehicle_no;
        this.in_vehicle_type = in_vehicle_type;
        this.in_destination = in_destination;
        this.in_LRP_Name = in_LRP_Name;
        this.In_LRP_Mobile_no = In_LRP_Mobile_no;
        this.In_Payment_type = In_Payment_type;
        this.In_Bank_acc_no = In_Bank_acc_no;
        this.In_cheque_no=In_cheque_no;
    }

    public PawhsActualProcSaveDao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAct_row_id() {
        return act_row_id;
    }

    public void setAct_row_id(int act_row_id) {
        this.act_row_id = act_row_id;
    }

    public String getAggCode() {
        return aggCode;
    }

    public void setAggCode(String aggCode) {
        this.aggCode = aggCode;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getFarmerCode() {
        return farmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        this.farmerCode = farmerCode;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getActualqty() {
        return actualqty;
    }

    public void setActualqty(String actualqty) {
        this.actualqty = actualqty;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getActualDate() {
        return actualDate;
    }

    public void setActualDate(String actualDate) {
        this.actualDate = actualDate;
    }

    public String getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(String advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public String getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(String rejectDate) {
        this.rejectDate = rejectDate;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getWrDate() {
        return wrDate;
    }

    public void setWrDate(String wrDate) {
        this.wrDate = wrDate;
    }

    public String getNoofBags() {
        return noofBags;
    }

    public void setNoofBags(String noofBags) {
        this.noofBags = noofBags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActualRemarks() {
        return actualRemarks;
    }

    public void setActualRemarks(String actualRemarks) {
        this.actualRemarks = actualRemarks;
    }

    public String getApproveRemarks() {
        return approveRemarks;
    }

    public void setApproveRemarks(String approveRemarks) {
        this.approveRemarks = approveRemarks;
    }

    public String getRejectRemarks() {
        return rejectRemarks;
    }

    public void setRejectRemarks(String rejectRemarks) {
        this.rejectRemarks = rejectRemarks;
    }

    public String getPickUpRemarks() {
        return pickUpRemarks;
    }

    public void setPickUpRemarks(String pickUpRemarks) {
        this.pickUpRemarks = pickUpRemarks;
    }

    public String getWrRemarks() {
        return wrRemarks;
    }

    public void setWrRemarks(String wrRemarks) {
        this.wrRemarks = wrRemarks;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getModeFlag() {
        return modeFlag;
    }

    public void setModeFlag(String modeFlag) {
        this.modeFlag = modeFlag;
    }

    public String getIsCloud() {
        return isCloud;
    }

    public void setIsCloud(String isCloud) {
        this.isCloud = isCloud;
    }

    public String getAcceptQty() {
        return acceptQty;
    }

    public void setAcceptQty(String acceptQty) {
        this.acceptQty = acceptQty;
    }
}
