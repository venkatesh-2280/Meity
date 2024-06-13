package cdfi.fintantra.meity.Pawhs.LoginModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeaderDao {
    @SerializedName("In_user_code")
    @Expose
    private String In_user_code;

    @SerializedName("In_user_name")
    @Expose
    private String In_user_name;

    @SerializedName("In_role_code")
    @Expose
    private String In_role_code;

    @SerializedName("In_role_name")
    @Expose
    private String In_role_name;

    @SerializedName("In_orgn_code")
    @Expose
    private String In_orgn_code;

    @SerializedName("In_location")
    @Expose
    private String In_location;

    @SerializedName("In_Reponse")
    @Expose
    private String In_Reponse;

    public String getIn_user_code() {
        return In_user_code;
    }

    public void setIn_user_code(String in_user_code) {
        In_user_code = in_user_code;
    }

    public String getIn_user_name() {
        return In_user_name;
    }

    public void setIn_user_name(String in_user_name) {
        In_user_name = in_user_name;
    }

    public String getIn_role_code() {
        return In_role_code;
    }

    public void setIn_role_code(String in_role_code) {
        In_role_code = in_role_code;
    }

    public String getIn_role_name() {
        return In_role_name;
    }

    public void setIn_role_name(String in_role_name) {
        In_role_name = in_role_name;
    }

    public String getIn_orgn_code() {
        return In_orgn_code;
    }

    public void setIn_orgn_code(String in_orgn_code) {
        In_orgn_code = in_orgn_code;
    }

    public String getIn_location() {
        return In_location;
    }

    public void setIn_location(String in_location) {
        In_location = in_location;
    }

    public String getIn_Reponse() {
        return In_Reponse;
    }

    public void setIn_Reponse(String in_Reponse) {
        In_Reponse = in_Reponse;
    }
}
