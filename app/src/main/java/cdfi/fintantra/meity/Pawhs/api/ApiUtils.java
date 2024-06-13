package cdfi.fintantra.meity.Pawhs.api;


public class ApiUtils {
    public static final String TAG = "Hotel App";
    public static String LOGIN_URL_API="http://169.38.77.190:949/Deployable_image_final/api/KANCHIICD_MOBILEAPP/";
  //  public static String TEST_URL_API="http://169.38.77.190:101/api/";
      public static String TEST_URL_API="https://apiuptn.kanchiffi.com/api/";//Live
     // public static String TEST_URL_API="http://15.206.37.1:102/api/";
 // public static String TEST_URL_API="http://169.38.77.190:102/api/";
   // public static String TEST_URL_API="http://15.206.37.1:101/api/";
    //public static String TEST_URL_API="http://15.206.37.1:1011/api/";
   // public static String TEST_URL_API="http://13.127.171.32:1022/api/";//demo

  // public static String TEST_URL_API="https://apiuatuptn.kanchiffi.com/api/";//demonew
   // public static String TEST_URL_API="https://apiuatuptn.kanchiffi.com/api/";//demo

  //public static String TEST_URL_API
    //    //sharedPreference;
    public static String IN_ROLE_CODE="IN_ROLE_CODE";
    public static String IN_USER_NAME="IN_USER_NAME";
    public static String IN_USER_CODE="IN_USER_CODE";
    public static String IN_FPO_CODE="IN_FPO_CODE";
    public static String IN_FPO_NAME="IN_FPO_NAME";
    public static String IN_ORGN_CODE="IN_ORGN_CODE";
   public static String IN_ORGN_NAME="IN_ORGN_NAME";
   public static String IN_FPO_ORGN="IN_FPO_ORGN";
    public static String instance = "up";
    public static String ORGN_ID="ORGN_ID";
    public static String ORGN_CODE="ORGN_CODE";
    public static String LOCN_ID="LOCN_ID";
    public static String USER_ID="USER_ID";
    public static String LOCALE_ID="LOCALE_ID";
    public static String CHECK_STATUS="check_status";
    //bundle
    public static String CATEGORY_NAME="CATEGORY_NAME";
    public static String CATEGORY_JAP_NAME="CATEGORY_JAP_NAME";
    public static String CATEGORY_ID="CATEGORY_ID";
    public static String CUSTOMER_NAME="CUSTOMER_NAME";
    public static String TABLE_NO="TABLE_NO";
    public static String FIRST_ORDER_DETAILS="FIRST_ORDER_DETAILS";
    public static final int SL_NO_VALUE=1;
    public static final int QUALITY_PARA_VALUE=2;
    public static final int OTHER_COST_VALUE=3;
    public static final int EST_EDT_VALUE=4;
    public static final int EST_ACT_VALUE=5;
    public static ApiService getLoginAPIService() {
        return RetrofitClient.getClient(LOGIN_URL_API).create(ApiService.class);
    }
    public static ApiService getAPIService() {
        return RetrofitClient1.getClient(TEST_URL_API).create(ApiService.class);
    }
}
