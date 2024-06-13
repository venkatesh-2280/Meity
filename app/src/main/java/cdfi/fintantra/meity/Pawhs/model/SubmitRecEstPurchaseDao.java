package cdfi.fintantra.meity.Pawhs.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SubmitRecEstPurchaseDao implements Parcelable {

    int id;
    private String farmerCode;
    private String farmerName;
    private String farmerMember;
    private String itemCode;
    private String itemName;
    private String qty;
    private String price;
    private String value;
    private String pickupdate;
    private String remarks;
    private String lotNo;

    public String getLotmn() {
        return lotmn;
    }

    public void setLotmn(String lotmn) {
        this.lotmn = lotmn;
    }

    private String lotmn;
    private String estiRowId;
    private String rowTimeStamp;
    private String mode_Flag;
    private String isCloud;

    public String getIn_Estimated_attach() {
        return in_Estimated_attach;
    }

    public void setIn_Estimated_attach(String in_Estimated_attach) {
        this.in_Estimated_attach = in_Estimated_attach;
    }

    private String in_Estimated_attach;
    private String vs;

    public String getVs() {
        return vs;
    }

    public void setVs(String vs) {
        this.vs = vs;
    }

    public String getQp() {
        return qp;
    }

    public void setQp(String qp) {
        this.qp = qp;
    }

    public String getRv() {
        return rv;
    }

    public void setRv(String rv) {
        this.rv = rv;
    }

    public String getLrp() {
        return lrp;
    }

    public void setLrp(String lrp) {
        this.lrp = lrp;
    }

    private String qp;
    private String rv;
    private String lrp;

    public SubmitRecEstPurchaseDao(int id, String farmerCode, String farmerName, String farmerMember, String itemCode, String itemName, String qty, String price, String value, String pickupdate, String remarks, String lotNo, String estiRowId, String rowTimeStamp, String mode_Flag, String isCloud, String in_Estimated_attach, String vs, String qp, String rv, String lrp, String lrpmn) {
        this.id = id;
        this.farmerCode = farmerCode;
        this.farmerName = farmerName;
        this.farmerMember = farmerMember;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.qty = qty;
        this.price = price;
        this.value = value;
        this.pickupdate = pickupdate;
        this.remarks = remarks;
        this.lotNo = lotNo;
        this.estiRowId = estiRowId;
        this.rowTimeStamp = rowTimeStamp;
        this.mode_Flag = mode_Flag;
        this.isCloud=isCloud;
        this.in_Estimated_attach = in_Estimated_attach;
        this.vs = vs;
        this.qp = qp;
        this.rv = rv;
        this.lrp = lrp;
        this.lotmn = lrpmn;
    }

    public SubmitRecEstPurchaseDao() {

    }

    protected SubmitRecEstPurchaseDao(Parcel in) {
        id = in.readInt();
        farmerCode = in.readString();
        farmerName = in.readString();
        farmerMember = in.readString();
        itemCode = in.readString();
        itemName = in.readString();
        qty = in.readString();
        price = in.readString();
        value = in.readString();
        pickupdate = in.readString();
        remarks = in.readString();
        lotNo = in.readString();
        estiRowId = in.readString();
        rowTimeStamp = in.readString();
        mode_Flag = in.readString();
        isCloud = in.readString();
        in_Estimated_attach = in.readString();
        vs = in.readString();
        qp = in.readString();
        rv = in.readString();
        lrp = in.readString();
        lotmn=in.readString();
    }

    public static final Creator<SubmitRecEstPurchaseDao> CREATOR = new Creator<SubmitRecEstPurchaseDao>() {
        @Override
        public SubmitRecEstPurchaseDao createFromParcel(Parcel in) {
            return new SubmitRecEstPurchaseDao(in);
        }

        @Override
        public SubmitRecEstPurchaseDao[] newArray(int size) {
            return new SubmitRecEstPurchaseDao[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFarmerMember() {
        return farmerMember;
    }

    public void setFarmerMember(String farmerMember) {
        this.farmerMember = farmerMember;
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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPickupdate() {
        return pickupdate;
    }

    public void setPickupdate(String pickupdate) {
        this.pickupdate = pickupdate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getEstiRowId() {
        return estiRowId;
    }

    public void setEstiRowId(String estiRowId) {
        this.estiRowId = estiRowId;
    }

    public String getRowTimeStamp() {
        return rowTimeStamp;
    }

    public void setRowTimeStamp(String rowTimeStamp) {
        this.rowTimeStamp = rowTimeStamp;
    }

    public String getMode_Flag() {
        return mode_Flag;
    }

    public void setMode_Flag(String mode_Flag) {
        this.mode_Flag = mode_Flag;
    }

    public String getIsCloud() {
        return isCloud;
    }

    public void setIsCloud(String isCloud) {
        this.isCloud = isCloud;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(farmerCode);
        parcel.writeString(farmerName);
        parcel.writeString(farmerMember);
        parcel.writeString(itemCode);
        parcel.writeString(itemName);
        parcel.writeString(qty);
        parcel.writeString(price);
        parcel.writeString(value);
        parcel.writeString(pickupdate);
        parcel.writeString(remarks);
        parcel.writeString(lotNo);
        parcel.writeString(estiRowId);
        parcel.writeString(rowTimeStamp);
        parcel.writeString(mode_Flag);
        parcel.writeString(isCloud);
        parcel.writeString(in_Estimated_attach);
        parcel.writeString(vs);
        parcel.writeString(qp);
        parcel.writeString(rv);
        parcel.writeString(lrp);
        parcel.writeString(lotmn);
    }
}
