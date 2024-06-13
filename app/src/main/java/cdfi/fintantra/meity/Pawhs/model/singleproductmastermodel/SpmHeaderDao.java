package cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpmHeaderDao {
    @SerializedName("IOU_product_rowid")
    @Expose
    private int IOU_product_rowid;

    @SerializedName("IOU_agg_code")
    @Expose
    private String IOU_agg_code;

    @SerializedName("IOU_product_code")
    @Expose
    private String IOU_product_code;

    public int getIOU_product_rowid() {
        return IOU_product_rowid;
    }

    public void setIOU_product_rowid(int IOU_product_rowid) {
        this.IOU_product_rowid = IOU_product_rowid;
    }

    public String getIOU_agg_code() {
        return IOU_agg_code;
    }

    public void setIOU_agg_code(String IOU_agg_code) {
        this.IOU_agg_code = IOU_agg_code;
    }

    public String getIOU_product_code() {
        return IOU_product_code;
    }

    public void setIOU_product_code(String IOU_product_code) {
        this.IOU_product_code = IOU_product_code;
    }
}
