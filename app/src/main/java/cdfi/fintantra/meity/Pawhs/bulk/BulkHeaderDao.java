package cdfi.fintantra.meity.Pawhs.bulk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BulkHeaderDao {

    @SerializedName("aggrcode")
    @Expose
    private String aggrcode;

    @SerializedName("username")
    @Expose
    private String username;

    public String getAggrcode() {
        return aggrcode;
    }

    public void setAggrcode(String aggrcode) {
        this.aggrcode = aggrcode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
