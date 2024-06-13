package cdfi.fintantra.meity.Pawhs.formermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ir.mirrajabi.searchdialog.core.Searchable;


public class FormerDao implements Searchable {

    int id;

    @SerializedName("farmer_rowid")
    @Expose
    private String farmer_rowid;

    @SerializedName("farmer_code")
    @Expose
    private String farmer_code;

    @SerializedName("farmer")
    @Expose
    private String farmer;

    @SerializedName("fhw_name")
    @Expose
    private String fhw_name;

    @SerializedName("farmer_name")
    @Expose
    private String farmer_name;

    @SerializedName("farmer_dist")
    @Expose
    private String farmer_dist;

    @SerializedName("farmer_dist_desc")
    @Expose
    private String farmer_dist_desc;

    @SerializedName("farmer_taluk")
    @Expose
    private String farmer_taluk;

    @SerializedName("farmer_taluk_desc")
    @Expose
    private String farmer_taluk_desc;

    @SerializedName("farmer_panchayat")
    @Expose
    private String farmer_panchayat;

    @SerializedName("farmer_panchayat_desc")
    @Expose
    private String farmer_panchayat_desc;

    @SerializedName("farmer_village")
    @Expose
    private String farmer_village;

    @SerializedName("farmer_village_desc")
    @Expose
    private String farmer_village_desc;

    public FormerDao() {
    }

    public FormerDao(int id, String farmer_rowid, String farmer_code, String farmer, String fhw_name, String farmer_name, String farmer_dist, String farmer_dist_desc, String farmer_taluk, String farmer_taluk_desc, String farmer_panchayat, String farmer_panchayat_desc, String farmer_village, String farmer_village_desc) {
        this.id = id;
        this.farmer_rowid = farmer_rowid;
        this.farmer_code = farmer_code;
        this.farmer = farmer;
        this.fhw_name = fhw_name;
        this.farmer_name = farmer_name;
        this.farmer_dist = farmer_dist;
        this.farmer_dist_desc = farmer_dist_desc;
        this.farmer_taluk = farmer_taluk;
        this.farmer_taluk_desc = farmer_taluk_desc;
        this.farmer_panchayat = farmer_panchayat;
        this.farmer_panchayat_desc = farmer_panchayat_desc;
        this.farmer_village = farmer_village;
        this.farmer_village_desc = farmer_village_desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFarmer_rowid() {
        return farmer_rowid;
    }

    public void setFarmer_rowid(String farmer_rowid) {
        this.farmer_rowid = farmer_rowid;
    }

    public String getFarmer_code() {
        return farmer_code;
    }

    public void setFarmer_code(String farmer_code) {
        this.farmer_code = farmer_code;
    }

    public String getFarmer() {
        return farmer;
    }

    public void setFarmer(String farmer) {
        this.farmer = farmer;
    }

    public String getFhw_name() {
        return fhw_name;
    }

    public void setFhw_name(String fhw_name) {
        this.fhw_name = fhw_name;
    }

    public String getFarmer_name() {
        return farmer_name;
    }

    public void setFarmer_name(String farmer_name) {
        this.farmer_name = farmer_name;
    }

    public String getFarmer_dist() {
        return farmer_dist;
    }

    public void setFarmer_dist(String farmer_dist) {
        this.farmer_dist = farmer_dist;
    }

    public String getFarmer_dist_desc() {
        return farmer_dist_desc;
    }

    public void setFarmer_dist_desc(String farmer_dist_desc) {
        this.farmer_dist_desc = farmer_dist_desc;
    }

    public String getFarmer_taluk() {
        return farmer_taluk;
    }

    public void setFarmer_taluk(String farmer_taluk) {
        this.farmer_taluk = farmer_taluk;
    }

    public String getFarmer_taluk_desc() {
        return farmer_taluk_desc;
    }

    public void setFarmer_taluk_desc(String farmer_taluk_desc) {
        this.farmer_taluk_desc = farmer_taluk_desc;
    }

    public String getFarmer_panchayat() {
        return farmer_panchayat;
    }

    public void setFarmer_panchayat(String farmer_panchayat) {
        this.farmer_panchayat = farmer_panchayat;
    }

    public String getFarmer_panchayat_desc() {
        return farmer_panchayat_desc;
    }

    public void setFarmer_panchayat_desc(String farmer_panchayat_desc) {
        this.farmer_panchayat_desc = farmer_panchayat_desc;
    }

    public String getFarmer_village() {
        return farmer_village;
    }

    public void setFarmer_village(String farmer_village) {
        this.farmer_village = farmer_village;
    }

    public String getFarmer_village_desc() {
        return farmer_village_desc;
    }

    public void setFarmer_village_desc(String farmer_village_desc) {
        this.farmer_village_desc = farmer_village_desc;
    }



    @Override
    public String getTitle() {
        return farmer_code;
    }
}
