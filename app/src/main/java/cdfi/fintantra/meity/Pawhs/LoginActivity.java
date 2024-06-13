package cdfi.fintantra.meity.Pawhs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cdfi.fintantra.meity.DBHelper;
import cdfi.fintantra.meity.ExceptionHandler;
import cdfi.fintantra.meity.HomePageActivity;
import cdfi.fintantra.meity.Pojokyc;
import cdfi.fintantra.meity.R;
import cdfi.fintantra.meity.VolleySingleton;
import cdfi.fintantra.meity.Pawhs.api.ApiService;
import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import cdfi.fintantra.meity.Pawhs.utils.Utility;

public class LoginActivity extends AppCompatActivity {
    private Context mContext;
    private Button buttonLogin;
    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText ipaddress;
    private String username,userpass;
    private ApiService apiService;
    private PAWHSApplication pawhsApplication;
    SQLiteDataBaseHandler db;
    RelativeLayout progressLayout;
    private boolean isNetwork;
    JSONObject userd;
    JSONArray castmaster;
    ProgressDialog pdialog;
    SharedPreferences sharedpreferences;
    TextView copyright;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_login);
        mContext=this;
        //save();
        //save2();

        initView();

    }

    private void initView() {
        apiService = ApiUtils.getLoginAPIService();
        pawhsApplication=PAWHSApplication.getGetInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Produce Aggregation");
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        buttonLogin=(Button)findViewById(R.id.login_buton);
        editTextUserName=(EditText)findViewById(R.id.edt_username);
        editTextPassword=(EditText)findViewById(R.id.edt_userpass);
        ipaddress=(EditText)findViewById(R.id.ipaddress);
        progressLayout = (RelativeLayout) findViewById(R.id.progressLayout);
        copyright = findViewById(R.id.copyright);
        db = new SQLiteDataBaseHandler(LoginActivity.this);
        save();
        //save2();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        copyright.setText("Copyright Fintantra "+String.valueOf(year));
        ipaddress.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    ApiUtils.TEST_URL_API="http://"+ipaddress.getText().toString()+"/api/";
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username=editTextUserName.getText().toString();
                userpass=editTextPassword.getText().toString();

                if(username.isEmpty()||userpass.isEmpty()){
                    // Toast.makeText(mContext,"Enter all fields",Toast.LENGTH_SHORT).show();
                    showErrorDialog("Enter all the fields");
                }else {
                    ;

                    jsonCallForLogin();



                }





            }
        });


    }

    private void jsonCallForLogin() {
        isNetwork = Utility.checkConnectivity(getApplicationContext());
        if(isNetwork){

//            progressLayout.setVisibility(View.VISIBLE);
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

//            apiService.getLogin("abc","chennai","MMFISM","en_US",username,userpass)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Subscriber<LoginDao>() {
//                        @Override
//                        public void onCompleted() {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//                            progressLayout.setVisibility(View.GONE);
//                            showErrorDialog("Invalid UserName and Password");
//
//                        }
//
//                        @Override
//                        public void onNext(LoginDao loginDao) {
//                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//                            progressLayout.setVisibility(View.GONE);
//                            // Toast.makeText(mContext,loginDao.getContextDao().getOrgnId(),Toast.LENGTH_SHORT).show();
//                            List<String> in_FPO_ORGN = new ArrayList<String>();
//                            List<String> in_orgn_code = new ArrayList<String>();
//                            if (loginDao.getContextDao()!=null){
//                                editTextPassword.setText("");
//                                editTextUserName.setText("");
//                                pawhsApplication.storePreference(mContext,ApiUtils.IN_ROLE_CODE,loginDao.getContextDao().getHeaderDao().getIn_role_code());
//                                pawhsApplication.storePreference(mContext,ApiUtils.IN_USER_CODE,loginDao.getContextDao().getHeaderDao().getIn_user_code());
//                                pawhsApplication.storePreference(mContext,ApiUtils.IN_USER_NAME,loginDao.getContextDao().getHeaderDao().getIn_user_name());
//                                pawhsApplication.storePreference(mContext,ApiUtils.ORGN_CODE,loginDao.getContextDao().getHeaderDao().getIn_orgn_code());
//                                pawhsApplication.storePreference(mContext,ApiUtils.ORGN_ID,loginDao.getContextDao().getOrgnId());
//                                pawhsApplication.storePreference(mContext,ApiUtils.LOCN_ID,loginDao.getContextDao().getLocnId());
//                                pawhsApplication.storePreference(mContext,ApiUtils.USER_ID,loginDao.getContextDao().getUserId());
//                                pawhsApplication.storePreference(mContext,ApiUtils.LOCALE_ID,loginDao.getContextDao().getLocaleId());
//                                startActivity(new Intent(mContext,HomePageActivity.class));
//                            }
//
//
//                        }
//                    });

            final String url = ApiUtils.TEST_URL_API+"New_PAWHS_Login/PAWHSLogin?org=flexi6&locn="+ApiUtils.instance+"&user=admin&instance="+ApiUtils.instance+"&lang=en_us&In_user_code="+editTextUserName.getText().toString()+"&In_user_pwd="+editTextPassword.getText().toString()+"&version_number="+ Pojokyc.ver;

// prepare the Request

            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(JSONObject response) {
                            // display response
                            Log.e("Response", response.toString());

                            JSONObject sys  = null;
                            try {
                                sys = response.getJSONObject("context");

                                if(sys.getString("ioU_user_code").equalsIgnoreCase(""))
                                {
                                    showErrorDialog(sys.getString("ioU_Reponse"));
                                }
                                else
                                {
                                    editTextPassword.setText("");
                                    editTextUserName.setText("");
                                    pawhsApplication.storePreference(mContext,ApiUtils.IN_ROLE_CODE,sys.getString("ioU_role_code"));
                                    pawhsApplication.storePreference(mContext,ApiUtils.IN_USER_CODE,sys.getString("ioU_user_code"));
                                    pawhsApplication.storePreference(mContext,ApiUtils.IN_USER_NAME,sys.getString("ioU_user_name"));
                                    pawhsApplication.storePreference(mContext,ApiUtils.ORGN_ID,sys.getString("orgnId"));
                                    pawhsApplication.storePreference(mContext,ApiUtils.LOCN_ID,sys.getString("locnId"));
                                    pawhsApplication.storePreference(mContext,ApiUtils.USER_ID,sys.getString("userId"));
                                    pawhsApplication.storePreference(mContext,ApiUtils.LOCALE_ID,sys.getString("localeId"));
                                    editTextUserName.setFocusable(true);
                                    SQLiteDatabase dbs = db.getWritableDatabase();
                                    dbs.execSQL("delete from byr");
                                    JSONObject jsonParam = null;
                                    try {

                                        jsonParam = new JSONObject();

                                        jsonParam.put("orgnId", sys.getString("orgnId"));
                                        jsonParam.put("locnId", sys.getString("locnId"));
                                        jsonParam.put("userId", sys.getString("userId"));
                                        jsonParam.put("localeId", sys.getString("localeId"));
                                        jsonParam.put("instance", sys.getString("locnId"));
                                        jsonParam.put("FilterBy_ToValue", "");
                                        jsonParam.put("FilterBy_FromValue", "");
                                        jsonParam.put("FilterBy_Code", "");
                                        jsonParam.put("FilterBy_Option", "ALL");

                                        Log.e("OUTPUTK",""+jsonParam.toString());
                                    }
                                    catch(Exception e)
                                    {

                                    }
                                    JsonObjectRequest stringRequest2 = new JsonObjectRequest(Request.Method.POST,ApiUtils.TEST_URL_API+"New_Pawhs_BuyerMaster/BuyerMaster_List",jsonParam,
                                            new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {

                                                    try {


                                                        JSONObject sysb = response.getJSONObject("context");
                                                        Log.e("KKK", "" + response.toString());
                                                        JSONArray castb = sysb.getJSONArray("list");
                                                        for (int i = 0; i < castb.length(); i++) {
                                                            JSONObject actor = castb.getJSONObject(i);
                                                            String name = actor.getString("out_buyer_name");
                                                            String code = actor.getString("out_buyer_code");
                                                            String org = actor.getString("out_agg_code");

                                                            db.insertbyr(name,code,org);

                                                        }
                                                    }
                                                    catch (Exception e)
                                                    {


                                                    }


                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Log.e("CCCC", "" + error);

                                                    // Toast.makeText(getApplicationContext(),"Unable to Insert",Toast.LENGTH_SHORT).show();

                                                    //on error storing the name to sqlite with status unsynced
                                                    // Toast.makeText(Demo.this, "Farmer "+n+" SuccessFull Added to Sync List" , Toast.LENGTH_SHORT).show();

                                                }
                                            }) {
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String, String> params = new HashMap<>();

                                            return params;
                                        }
                                    };
                                    stringRequest2.setRetryPolicy(new DefaultRetryPolicy(
                                            80000,
                                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                                    VolleySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest2);
//                                    final Dialog dialog2 = new Dialog(mContext);
//                                    dialog2.setContentView(R.layout.orglist);
//                                    //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
//                                    dialog2.setTitle("Title...");
//                                    int width = WindowManager.LayoutParams.MATCH_PARENT;
//                                    int height = WindowManager.LayoutParams.MATCH_PARENT;
//
//                                    dialog2.getWindow().setLayout(width, height);
//
//                                    final Spinner spinner = (Spinner) dialog2.findViewById(R.id.spinner);
//                                    final Spinner spinner2 = (Spinner) dialog2.findViewById(R.id.spinner2);
//                                    final Spinner spinner3 = (Spinner) dialog2.findViewById(R.id.spinner3);
//                                    final Spinner spinner4 = (Spinner) dialog2.findViewById(R.id.spinner4);
//                                    final Button button = (Button) dialog2.findViewById(R.id.button);
//
//                                    List<String> org1 = new ArrayList<String>();
//                                    org1.add("Tap to Select FPO");
//                                    final List<String> org2 = new ArrayList<String>();
//                                    org2.add("0");
//                                    final List<String> org3 = new ArrayList<String>();
//                                    org3.add("0");
//
//                                    JSONArray cast = sys.getJSONArray("list");
//                                    for (int i=0; i<cast.length(); i++) {
//                                        JSONObject actor = cast.getJSONObject(i);
//                                        String name = actor.getString("in_FPO_ORGN");
//                                        String code = actor.getString("in_orgn_code");
//                                        String fcode = actor.getString("in_FPO_Code");
//                                        org1.add(name);
//                                        org2.add(code);
//                                        org3.add(fcode);
//                                    }
//
//
//                                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(LoginActivity.this, R.layout.spinner_item, org1);
//
//                                    // Drop down layout style - list view with radio button
//                                    dataAdapter.setDropDownViewResource(R.layout.spinner_item);
//
//                                    // attaching data adapter to spinner
//                                    spinner.setAdapter(dataAdapter);
//                                    spinner.setOnItemSelectedListener(
//                                            new AdapterView.OnItemSelectedListener() {
//                                                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//
//                                                    if(pos >0) {
//
//                                                        Log.e("SPINNERVALUE", "" + org2.get(pos).toString());
//                                                        //   System.out.println(item.toString());     //prints the text in spinner item.
//                                                        pawhsApplication.storePreference(mContext, ApiUtils.ORGN_CODE, org2.get(pos));
//                                                        editTextPassword.setText("");
//                                                        editTextUserName.setText("");
//                                                        pdialog = new ProgressDialog(LoginActivity.this);
//                                                        pdialog.setCanceledOnTouchOutside(false);
//                                                        pdialog.setTitle("Loading.....");
//                                                        pdialog.show();
//                                                        final SQLiteDataBaseHandler db = new SQLiteDataBaseHandler(LoginActivity.this);
//                                                        final SQLiteDatabase dbs = db.getWritableDatabase();
//                                                        dbs.execSQL("delete from fpoad");
//
//
//                                                        final List<String> lvil = new ArrayList<String>();
//                                                        final List<String> lvilc = new ArrayList<String>();
//                                                        final List<String> lvil2 = new ArrayList<String>();
//                                                        lvil.add("Tap to Select Village");
//                                                        lvilc.add("0");
//                                                        final List<String> lgr = new ArrayList<String>();
//                                                        final List<String> lgrc = new ArrayList<String>();
//                                                        final List<String> lgr2 = new ArrayList<String>();
//                                                        lgrc.add("0");
//                                                        lgr.add("Tap to Select Gramapanchayat");
//                                                        final List<String> lta = new ArrayList<String>();
//                                                        final List<String> ltac = new ArrayList<String>();
//                                                        lta.add("Tap to Select Taluk");
//                                                        ltac.add("0");
//
//                                                        SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                                                        editor.putString("Fcode", org3.get(pos));
//
//                                                        editor.commit();
//                                                        final String url = ApiUtils.TEST_URL_API+"New_PAWHS_Login/PAWHSGramFetch?org="+org3.get(pos)+"&instance="+ApiUtils.instance;
//
//                                                        Log.e("URL",""+url);
//
//// prepare the Request
//
//                                                        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                                                                new Response.Listener<JSONObject>()
//                                                                {
//                                                                    @Override
//                                                                    public void onResponse(JSONObject response) {
//                                                                        // display response
//                                                                        Log.e("Response", response.toString());
//
//
//                                                                        try {
//                                                                            JSONObject obj = response.getJSONObject("gramFetchContext");
//                                                                            JSONArray cast = obj.getJSONArray("list");
//                                                                            for (int i = 0; i < cast.length(); i++) {
//
//
//                                                                                JSONObject actor = cast.getJSONObject(i);
//
//
//                                                                                String n1 = actor.getString("country_mst");
//                                                                                String n2 = actor.getString("country_desc");
//                                                                                String n3 = actor.getString("state_mst");
//                                                                                String n4 = actor.getString("state_desc");
//                                                                                String n5 = actor.getString("dist_mst");
//                                                                                String n6 = actor.getString("dist_desc");
//                                                                                String n7 = actor.getString("taluk_mst");
//                                                                                String n8 = actor.getString("taluk_desc");
//                                                                                String n9 = actor.getString("panchayat_mst");
//                                                                                String n10 = actor.getString("panchayat_desc");
//                                                                                String n11 = actor.getString("village_mst");
//                                                                                String n12 = actor.getString("village_desc");
//                                                                                String n13 = actor.getString("pincode");
////                                                                            if(lta.contains(n8))
////                                                                            {
////
////                                                                            }
////                                                                            else
////                                                                            {
////                                                                                lta.add(n8);
////                                                                                ltac.add(n7);
////                                                                            }
//                                                                                if(lgr.contains(n10))
//                                                                                {
//
//                                                                                }
//                                                                                else
//                                                                                {
//                                                                                    lgr.add(n10);
//                                                                                    lgrc.add(n9);
//                                                                                }
//                                                                                lgr2.add(n9);
//                                                                                lvil2.add(n11);
//
//
//                                                                                Log.e("Table2", "" + n3);
//
//                                                                                db.insertaddress(n1, n2, n3, n4, n5, n6, n7, n8, n9,n10,n11,n12,n13);
//                                                                                spinner.setVisibility(View.GONE);
//                                                                                button.setVisibility(View.VISIBLE);
//                                                                                // spinner2.setVisibility(View.VISIBLE);
//                                                                                spinner3.setVisibility(View.VISIBLE);
//                                                                                spinner4.setVisibility(View.VISIBLE);
//
//                                                                            }
//                                                                            pdialog.dismiss();
//                                                                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(LoginActivity.this, R.layout.spinner_item2, lta);
//
//                                                                            // Drop down layout style - list view with radio button
//                                                                            dataAdapter.setDropDownViewResource(R.layout.spinner_item2);
//
//                                                                            // attaching data adapter to spinner
//                                                                            spinner2.setAdapter(dataAdapter);
//
//                                                                            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(LoginActivity.this, R.layout.spinner_item2, lgr);
//
//                                                                            // Drop down layout style - list view with radio button
//                                                                            dataAdapter2.setDropDownViewResource(R.layout.spinner_item2);
//
//                                                                            // attaching data adapter to spinner
//                                                                            spinner3.setAdapter(dataAdapter2);
//
//                                                                            ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(LoginActivity.this, R.layout.spinner_item2, lvil);
//
//                                                                            // Drop down layout style - list view with radio button
//                                                                            dataAdapter3.setDropDownViewResource(R.layout.spinner_item2);
//
//                                                                            // attaching data adapter to spinner
//                                                                            spinner4.setAdapter(dataAdapter3);
//
//                                                                            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//                                                                            {
//                                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                                                                                {
//
//                                                                                    if(position>0)
//                                                                                    {
//                                                                                        SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                                                                                        editor.putString("tcode","QCD_UNT_"+(String) parent.getItemAtPosition(position));
//                                                                                        editor.putString("tname", (String) parent.getItemAtPosition(position));
//
//                                                                                        editor.commit();
//                                                                                        Cursor cursor = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_depend_desc"
//                                                                                                }, "out_depend_code" + "=? COLLATE NOCASE",
//                                                                                                new String[]{"QCD_UNT_"+(String) parent.getItemAtPosition(position)}, null, null, null, null);
//                                                                                        //  Toast.makeText(LoginActivity.this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
//                                                                                        if (cursor.getCount() != 0) {
//                                                                                            lgr.clear();
//                                                                                            lgrc.clear();
//                                                                                            lgr.add("Tap To Select Gramapanchayat");
//                                                                                            lgrc.add("0");
//                                                                                            if (cursor.moveToFirst()) {
//                                                                                                do {
//                                                                                                    if(lgr2.contains(cursor.getString(0)))
//                                                                                                    {
//
//                                                                                                        Cursor cursor2 = dbs.query("masterl", new String[]{"out_depend_desc"
//                                                                                                                }, "out_master_code" + "=? COLLATE NOCASE",
//                                                                                                                new String[]{"QCD_UNT_"+(String) parent.getItemAtPosition(position)}, null, null, null, null);
//                                                                                                        if (cursor2.moveToFirst()) {
//
//                                                                                                            editor.putString("dis",cursor2.getString(0));
//
//                                                                                                            editor.commit();
//                                                                                                        }
//
//                                                                                                        lgr.add(cursor.getString(1));
//                                                                                                        lgrc.add(cursor.getString(0));
//                                                                                                    }
//
//
//
//                                                                                                } while (cursor.moveToNext());
//
//
//                                                                                                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(LoginActivity.this, R.layout.spinner_item2, lgr);
//
//                                                                                                // Drop down layout style - list view with radio button
//                                                                                                dataAdapter2.setDropDownViewResource(R.layout.spinner_item2);
//
//                                                                                                // attaching data adapter to spinner
//                                                                                                spinner3.setAdapter(dataAdapter2);
//                                                                                            }
//
//
//                                                                                        }
//                                                                                    }
//
//                                                                                } // to close the onItemSelected
//                                                                                public void onNothingSelected(AdapterView<?> parent)
//                                                                                {
//
//                                                                                }
//                                                                            });
//
//                                                                            spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//                                                                            {
//                                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                                                                                {
//
//                                                                                    if(position>0)
//                                                                                    {
//                                                                                        SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                                                                                        editor.putString("gcode","QCD_UNG_"+(String) parent.getItemAtPosition(position));
//                                                                                        editor.putString("gname", (String) parent.getItemAtPosition(position));
//
//                                                                                        Log.e("CHK",""+"QCD_UNG_"+(String) parent.getItemAtPosition(position));
//                                                                                        Log.e("CHKN",""+(String) parent.getItemAtPosition(position));
//
//                                                                                        editor.commit();
//                                                                                        Cursor cursor = dbs.query("masterl", new String[]{"out_master_code","out_master_description"
//                                                                                                }, "out_depend_code" + "=? COLLATE NOCASE",
//                                                                                                new String[]{"QCD_UNG_"+(String) parent.getItemAtPosition(position)}, null, null, null, null);
//
//                                                                                        Cursor cursor2 = dbs.query("masterl", new String[]{"out_depend_code","out_depend_desc"
//                                                                                                }, "out_master_code" + "=? COLLATE NOCASE",
//                                                                                                new String[]{"QCD_UNG_"+(String) parent.getItemAtPosition(position)}, null, null, null, null);
//                                                                                        if(cursor2.moveToNext())
//                                                                                        {
//
//
//                                                                                            editor.putString("tcode",cursor2.getString(0));
//                                                                                            editor.putString("tname", cursor2.getString(1));
//
//                                                                                            editor.commit();
//                                                                                            Cursor cursor3 = dbs.query("masterl", new String[]{"out_depend_desc"
//                                                                                                    }, "out_master_code" + "=? COLLATE NOCASE",
//                                                                                                    new String[]{cursor2.getString(0)}, null, null, null, null);
//                                                                                            if (cursor3.moveToFirst()) {
//
//                                                                                                editor.putString("dis",cursor3.getString(0));
//
//                                                                                                editor.commit();
//                                                                                            }
//                                                                                        }
//
//                                                                                        //  Toast.makeText(LoginActivity.this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
//                                                                                        if (cursor.getCount() != 0) {
//                                                                                            lvil.clear();
//                                                                                            lvilc.clear();
//                                                                                            lvil.add("Tap To Select Village");
//                                                                                            lvilc.add("0");
//                                                                                            if (cursor.moveToFirst()) {
//                                                                                                do {
//                                                                                                    if(lvil2.contains(cursor.getString(0)))
//                                                                                                    {
//
//                                                                                                        lvil.add(cursor.getString(1));
//                                                                                                        lvilc.add(cursor.getString(0));
//                                                                                                    }
//
//
//
//                                                                                                } while (cursor.moveToNext());
//
//
//                                                                                                ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(LoginActivity.this, R.layout.spinner_item2, lvil);
//
//                                                                                                // Drop down layout style - list view with radio button
//                                                                                                dataAdapter3.setDropDownViewResource(R.layout.spinner_item2);
//
//                                                                                                // attaching data adapter to spinner
//                                                                                                spinner4.setAdapter(dataAdapter3);
//                                                                                            }
//
//
//                                                                                        }
//                                                                                    }
//
//                                                                                } // to close the onItemSelected
//                                                                                public void onNothingSelected(AdapterView<?> parent)
//                                                                                {
//
//                                                                                }
//                                                                            });
//
//
//                                                                            spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//                                                                            {
//                                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                                                                                {
//
//                                                                                    if(position>0)
//                                                                                    {
//
//
//                                                                                        SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                                                                                        editor.putString("vcode","QCD_UNV_"+(String) parent.getItemAtPosition(position));
//                                                                                        editor.putString("vname", (String) parent.getItemAtPosition(position));
//
//                                                                                        editor.commit();
//
//
//
//
//
//
//                                                                                    }
//
//                                                                                } // to close the onItemSelected
//                                                                                public void onNothingSelected(AdapterView<?> parent)
//                                                                                {
//
//                                                                                }
//                                                                            });
//
//                                                                        } catch (JSONException e) {
//                                                                            // Toast.makeText(LoginActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
//                                                                            e.printStackTrace();
//                                                                        }
//                                                                    }
//                                                                },
//                                                                new Response.ErrorListener()
//                                                                {
//                                                                    @Override
//                                                                    public void onErrorResponse(VolleyError error) {
//                                                                        Toast.makeText(LoginActivity.this, "Error:"+error.getMessage(), Toast.LENGTH_LONG).show();
//
//                                                                        Log.d("Error.Response", String.valueOf(error));
//                                                                    }
//                                                                }
//                                                        );
//
//// add it to the RequestQueue
//                                                        getRequest.setRetryPolicy(new DefaultRetryPolicy(
//                                                                90000,
//                                                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                                                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                                                        VolleySingleton.getInstance(LoginActivity.this).addToRequestQueue(getRequest);
////                                                    startActivity(new Intent(mContext, HomePageActivity.class));
////                                                    finish();
//                                                    }
//
//                                                }
//                                                public void onNothingSelected(AdapterView<?> parent) {
//                                                }
//                                            });
//
//
////                                ImageView dialogButton = (ImageView) dialog2.findViewById(R.id.cls);
//                                    button.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//
//                                            //  callQualityParameterJsonDetails();
//
//                                            if(spinner3.getSelectedItem().toString().equalsIgnoreCase("Tap to select gramapanchayat"))
//                                            {
//                                                showErrorDialog("Please Select Gramapanchayat");
//                                            }
//                                            else  if(spinner4.getSelectedItem().toString().equalsIgnoreCase("Tap to select village"))
//                                            {
//                                                showErrorDialog("Please Select Village");
//                                            }
//                                            else
//                                            {
//                                                startActivity(new Intent(mContext, HomePageActivity.class));
//                                                finish();
//                                            }
//                                        }
//                                    });
//
//                                    dialog2.show();
                                }



                            } catch (JSONException e) {
                                Toast.makeText(LoginActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, "Error:"+error.getMessage(), Toast.LENGTH_LONG).show();

                            Log.d("Error.Response", String.valueOf(error));
                        }
                    }
            );

// add it to the RequestQueue
            getRequest.setRetryPolicy(new DefaultRetryPolicy(
                    90000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(LoginActivity.this).addToRequestQueue(getRequest);





        }else {
            showConnectionDialog();
        }

    }

    private void showConnectionDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Please Check Your Network Connection")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .show();
    }

    private void showErrorDialog(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Info!")
                .setMessage(s)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void save() {



        SQLiteDatabase dbs = db.getWritableDatabase();
        dbs.execSQL("delete from byr");
        JSONObject jsonParam = null;
        try {

            jsonParam = new JSONObject();

            jsonParam.put("orgnId",  pawhsApplication.getPreferenceFromString(this, ApiUtils.ORGN_CODE));
            jsonParam.put("locnId",  pawhsApplication.getPreferenceFromString(this, ApiUtils.LOCN_ID));
            jsonParam.put("userId", pawhsApplication.getPreferenceFromString(this, ApiUtils.USER_ID));
            jsonParam.put("localeId",  pawhsApplication.getPreferenceFromString(this, ApiUtils.LOCALE_ID));
            jsonParam.put("instance", Pojokyc.instance);
            jsonParam.put("FilterBy_ToValue", "");
            jsonParam.put("FilterBy_FromValue", "");
            jsonParam.put("FilterBy_Code", "");
            jsonParam.put("FilterBy_Option", "ALL");

            Log.e("OUTPUTK",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUTK",""+e.getMessage());
        }
        JsonObjectRequest stringRequest2 = new JsonObjectRequest(Request.Method.POST,ApiUtils.TEST_URL_API+"New_Pawhs_BuyerMaster/BuyerMaster_List",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {


                            JSONObject sysb = response.getJSONObject("context");
                            Log.e("KKK", "" + response.toString());
                            JSONArray castb = sysb.getJSONArray("list");
                            for (int i = 0; i < castb.length(); i++) {
                                JSONObject actor = castb.getJSONObject(i);
                                String name = actor.getString("out_buyer_name");
                                String code = actor.getString("out_buyer_code");
                                String org = actor.getString("out_agg_code");

                                db.insertbyr(name,code,org);

                            }
                        }
                        catch (Exception e)
                        {


                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);

                        // Toast.makeText(getApplicationContext(),"Unable to Insert",Toast.LENGTH_SHORT).show();

                        //on error storing the name to sqlite with status unsynced
                        // Toast.makeText(Demo.this, "Farmer "+n+" SuccessFull Added to Sync List" , Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        stringRequest2.setRetryPolicy(new DefaultRetryPolicy(
                80000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest2);

        pdialog = new ProgressDialog(LoginActivity.this);
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Loading.....");
        pdialog.show();



        dbs.execSQL("delete from vtype");
        dbs.execSQL("delete from vstatus");
        dbs.execSQL("delete from dstn");
        dbs.execSQL("delete from qparest");
        dbs.execSQL("delete from bankm");
        dbs.execSQL("delete from masterl");
        dbs.execSQL("delete from fpoad");

        try {


            DecimalFormat amountFormate = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", "Flexi");
            userd.put("locnId", sharedpreferences.getString("dateforsyncpa",""));
            userd.put("userId", "admin");
            userd.put("localeId", "en_US");
            userd.put("screen_Id", "FARMERANDPAWHS");
            userd.put("instance", ApiUtils.instance);

            Log.e("OUTPUT", "" + userd.toString());

        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

        //169.38.77.190:101/api/Mobile_FDR/Farmermaster
        //15.206.21.248:27/Farmermaster
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, ApiUtils.TEST_URL_API+"Mobile_FDR/Farmermaster", userd,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC2", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                             castmaster = obj.getJSONArray("detail");


                            JSONArray cast3 = obj.getJSONArray("varityDt");
                            for (int i = 0; i < cast3.length(); i++) {
                                Log.e("TableVS", "" + "Hi");

                                JSONObject actor = cast3.getJSONObject(i);


                                String n1 = actor.getString("out_master_rowid");
                                String n2 = actor.getString("out_master_code");
                                String n3 = actor.getString("out_master_desc");



                                Log.e("TableVS", "" + n3);

                                db.insertvstatus(n1, n2, n3);


                            }

                            JSONArray cast2 = obj.getJSONArray("qualityDt");
                            for (int i = 0; i < cast2.length(); i++) {


                                JSONObject actor = cast2.getJSONObject(i);


                                String n1 = actor.getString("out_qlt_rowid");
                                String n2 = actor.getString("out_qlt_code");
                                String n3 = actor.getString("out_qlt_name");
                                String n4 = actor.getString("out_QualityRange");


                                Log.e("TableQ", "" + n3);

                                db.insertqparest(n1, n2, n3, n4);


                            }



                            JSONArray cast4 = obj.getJSONArray("vehicleDt");
                            for (int i = 0; i < cast4.length(); i++) {


                                JSONObject actor = cast4.getJSONObject(i);


                                String n1 = actor.getString("out_master_rowid");
                                String n2 = actor.getString("out_master_code");
                                String n3 = actor.getString("out_master_desc");



                                Log.e("TableVT", "" + n3);

                                db.insertvtype(n1, n2, n3);


                            }

                            JSONArray cast5 = obj.getJSONArray("destinationDt");
                            for (int i = 0; i < cast5.length(); i++) {


                                JSONObject actor = cast5.getJSONObject(i);


                                String n1 = actor.getString("out_master_rowid");
                                String n2 = actor.getString("out_master_code");
                                String n3 = actor.getString("out_master_desc");



                                Log.e("TableDS", "" + n3);

                                db.insertdstn(n1, n2, n3);


                            }
                            new SomeTask().execute();
                          //  pdialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);

                        //on error storing the name to sqlite with status unsynced
                        // Toast.makeText(Demo.this, "Farmer "+n+" SuccessFull Added to Sync List" , Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                1500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    class SomeTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // super.onPreExecute();

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            final SQLiteDatabase dbs = db.getWritableDatabase();
            try {

                for (int i = 0; i < castmaster.length(); i++) {


                    JSONObject actor = castmaster.getJSONObject(i);


                    String n1 = actor.getString("out_master_rowid");
                    String n2 = actor.getString("out_parent_code");
                    String n3 = actor.getString("out_master_code");
                    String n4 = actor.getString("out_master_description");
                    String n5 = actor.getString("out_depend_code");
                    String n6 = actor.getString("out_depend_desc");
                    String n7 = actor.getString("out_locallang_flag");
                    String n8 = actor.getString("out_status_code");
                    String n9 = actor.getString("out_status_desc");

                    //   Log.e("Table2", "" + n3);

                    if(sharedpreferences.getString("dateforsyncpa","").equalsIgnoreCase(".")) {
                        db.insertmasterl(n1, n2, n3, n4, n5, n6, n7, n8, n9);
                    }
                    else
                    {
                        Cursor cr1 = dbs.rawQuery("select * from masterl where out_master_code='"+n3+"'",null);

                        try {
                            if (cr1.getCount() > 0) {
                                dbs.execSQL("Delete from masterl where out_master_code = '" + n3 + "'");
                                db.insertmasterl(n1, n2, n3, n4, n5, n6, n7, n8, n9);
                            } else {
                                db.insertmasterl(n1, n2, n3, n4, n5, n6, n7, n8, n9);
                            }
                        } finally {
                            cr1.close();
                        }
                    }


                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
            //callLotnoListDetails();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            pdialog.dismiss();
            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString("dateforsyncpa", getDate1());

            editor.commit();
            final Dialog dialog2 = new Dialog(mContext);
            dialog2.setContentView(R.layout.orglist);
            //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
            dialog2.setTitle("Title...");
            int width = WindowManager.LayoutParams.MATCH_PARENT;
            int height = WindowManager.LayoutParams.MATCH_PARENT;

            dialog2.getWindow().setLayout(width, height);

            final Spinner spinner = (Spinner) dialog2.findViewById(R.id.spinner);
            final Spinner spinner2 = (Spinner) dialog2.findViewById(R.id.spinner2);
            final Spinner spinner3 = (Spinner) dialog2.findViewById(R.id.spinner3);
            final Spinner spinner4 = (Spinner) dialog2.findViewById(R.id.spinner4);
            final ProgressBar progressBar = (ProgressBar) dialog2.findViewById(R.id.dialogProgressBar);
            final TextView dtitle = (TextView) dialog2.findViewById(R.id.dtitle);
            final Button button = (Button) dialog2.findViewById(R.id.button);

            progressBar.setVisibility(View.VISIBLE);
            // attaching data adapter to spinner


            //   System.out.println(item.toString());     //prints the text in spinner item.

            pdialog = new ProgressDialog(LoginActivity.this);
            pdialog.setCanceledOnTouchOutside(false);
            pdialog.setTitle("Loading.....");
            pdialog.show();
            final SQLiteDataBaseHandler db = new SQLiteDataBaseHandler(LoginActivity.this);
            final SQLiteDatabase dbs = db.getWritableDatabase();
            dbs.execSQL("delete from fpoad");
            dtitle.setText("");


            final List<String> lvil = new ArrayList<String>();
            final List<String> lvilc = new ArrayList<String>();
            final List<String> lvil2 = new ArrayList<String>();
            lvil.add("Tap to Select Village");
            lvilc.add("0");
            final List<String> lgr = new ArrayList<String>();
            final List<String> lgrc = new ArrayList<String>();
            final List<String> lgr2 = new ArrayList<String>();
            lgrc.add("0");
            lgr.add("Tap to Select Gramapanchayat");
            final List<String> lta = new ArrayList<String>();
            final List<String> ltac = new ArrayList<String>();
            lta.add("Tap to Select Taluk");
            ltac.add("0");


            final String url = ApiUtils.TEST_URL_API + "New_PAWHS_Login/PAWHSGramFetch?org=" + sharedpreferences.getString("Fcode","") + "&Mdatetime=.&instance=" + ApiUtils.instance;

            Log.e("URL", "" + url);

// prepare the Request

            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // display response
                            Log.e("Response", response.toString());


                            try {
                                JSONObject obj = response.getJSONObject("gramFetchContext");
                                JSONArray cast = obj.getJSONArray("list");
                                for (int i = 0; i < cast.length(); i++) {


                                    JSONObject actor = cast.getJSONObject(i);


                                    String n1 = actor.getString("country_mst");
                                    String n2 = actor.getString("country_desc");
                                    String n3 = actor.getString("state_mst");
                                    String n4 = actor.getString("state_desc");
                                    String n5 = actor.getString("dist_mst");
                                    String n6 = actor.getString("dist_desc");
                                    String n7 = actor.getString("taluk_mst");
                                    String n8 = actor.getString("taluk_desc");
                                    String n9 = actor.getString("panchayat_mst");
                                    String n10 = actor.getString("panchayat_desc");
                                    String n11 = actor.getString("village_mst");
                                    String n12 = actor.getString("village_desc");
                                    String n13 = actor.getString("pincode");
//                                                                            if(lta.contains(n8))
//                                                                            {
//
//                                                                            }
//                                                                            else
//                                                                            {
//                                                                                lta.add(n8);
//                                                                                ltac.add(n7);
//                                                                            }
                                    if (lgr.contains(n10)) {

                                    } else {
                                        lgr.add(n10);
                                        lgrc.add(n9);
                                    }
                                    lgr2.add(n9);
                                    lvil2.add(n11);


                                    Log.e("Table2", "" + n3);
                                    progressBar.setVisibility(View.GONE);
                                    db.insertaddress(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13);
                                    spinner.setVisibility(View.GONE);
                                    button.setVisibility(View.VISIBLE);
                                    // spinner2.setVisibility(View.VISIBLE);
                                    spinner3.setVisibility(View.VISIBLE);
                                    spinner4.setVisibility(View.VISIBLE);

                                }
                                pdialog.dismiss();
                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(LoginActivity.this, R.layout.spinner_item2, lta);

                                // Drop down layout style - list view with radio button
                                dataAdapter.setDropDownViewResource(R.layout.spinner_item2);

                                // attaching data adapter to spinner
                                spinner2.setAdapter(dataAdapter);

                                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(LoginActivity.this, R.layout.spinner_item2, lgr);

                                // Drop down layout style - list view with radio button
                                dataAdapter2.setDropDownViewResource(R.layout.spinner_item2);

                                // attaching data adapter to spinner
                                spinner3.setAdapter(dataAdapter2);

                                ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(LoginActivity.this, R.layout.spinner_item2, lvil);

                                // Drop down layout style - list view with radio button
                                dataAdapter3.setDropDownViewResource(R.layout.spinner_item2);

                                // attaching data adapter to spinner
                                spinner4.setAdapter(dataAdapter3);

                                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                        if (position > 0) {
                                            SharedPreferences.Editor editor = sharedpreferences.edit();

                                            editor.putString("tcode", "QCD_UNT_" + (String) parent.getItemAtPosition(position));
                                            editor.putString("tname", (String) parent.getItemAtPosition(position));

                                            editor.commit();
                                            Cursor cursor = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_depend_desc"
                                                    }, "out_depend_code" + "=? COLLATE NOCASE",
                                                    new String[]{"QCD_UNT_" + (String) parent.getItemAtPosition(position)}, null, null, null, null);
                                            //  Toast.makeText(LoginActivity.this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
                                            if (cursor.getCount() != 0) {
                                                lgr.clear();
                                                lgrc.clear();
                                                lgr.add("Tap To Select Gramapanchayat");
                                                lgrc.add("0");
                                                if (cursor.moveToFirst()) {
                                                    do {
                                                        if (lgr2.contains(cursor.getString(0))) {

                                                            Cursor cursor2 = dbs.query("masterl", new String[]{"out_depend_desc"
                                                                    }, "out_master_code" + "=? COLLATE NOCASE",
                                                                    new String[]{"QCD_UNT_" + (String) parent.getItemAtPosition(position)}, null, null, null, null);
                                                            if (cursor2.moveToFirst()) {

                                                                editor.putString("dis", cursor2.getString(0));

                                                                editor.commit();
                                                            }

                                                            lgr.add(cursor.getString(1));
                                                            lgrc.add(cursor.getString(0));
                                                        }


                                                    } while (cursor.moveToNext());


                                                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(LoginActivity.this, R.layout.spinner_item2, lgr);

                                                    // Drop down layout style - list view with radio button
                                                    dataAdapter2.setDropDownViewResource(R.layout.spinner_item2);

                                                    // attaching data adapter to spinner
                                                    spinner3.setAdapter(dataAdapter2);
                                                }


                                            }
                                        }

                                    } // to close the onItemSelected

                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });

                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                        if (position > 0) {
                                            SharedPreferences.Editor editor = sharedpreferences.edit();

                                            editor.putString("gcode", lgrc.get(position));
                                            editor.putString("gname", (String) parent.getItemAtPosition(position));

                                            Log.e("CHK", "" + lgrc.get(position));
                                            Log.e("CHKN", "" + (String) parent.getItemAtPosition(position));

                                            editor.commit();
                                            Cursor cursor = dbs.query("masterl", new String[]{"out_master_code", "out_master_description"
                                                    }, "out_depend_code" + "=? COLLATE NOCASE",
                                                    new String[]{lgrc.get(position)}, null, null, null, null);

                                            Cursor cursor2 = dbs.query("masterl", new String[]{"out_depend_code", "out_depend_desc"
                                                    }, "out_master_code" + "=? COLLATE NOCASE",
                                                    new String[]{"QCD_UNG_" + (String) parent.getItemAtPosition(position)}, null, null, null, null);
                                            if (cursor2.moveToNext()) {


                                                editor.putString("tcode", cursor2.getString(0));
                                                editor.putString("tname", cursor2.getString(1));

                                                editor.commit();
                                                Cursor cursor3 = dbs.query("masterl", new String[]{"out_depend_desc"
                                                        }, "out_master_code" + "=? COLLATE NOCASE",
                                                        new String[]{cursor2.getString(0)}, null, null, null, null);
                                                if (cursor3.moveToFirst()) {

                                                    editor.putString("dis", cursor3.getString(0));

                                                    editor.commit();
                                                }
                                            }

                                            //  Toast.makeText(LoginActivity.this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
                                            if (cursor.getCount() != 0) {
                                                lvil.clear();
                                                lvilc.clear();
                                                lvil.add("Tap To Select Village");
                                                lvilc.add("0");
                                                if (cursor.moveToFirst()) {
                                                    do {
                                                        if (lvil2.contains(cursor.getString(0))) {

                                                            lvil.add(cursor.getString(1));
                                                            lvilc.add(cursor.getString(0));
                                                        }


                                                    } while (cursor.moveToNext());


                                                    ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(LoginActivity.this, R.layout.spinner_item2, lvil);

                                                    // Drop down layout style - list view with radio button
                                                    dataAdapter3.setDropDownViewResource(R.layout.spinner_item2);

                                                    // attaching data adapter to spinner
                                                    spinner4.setAdapter(dataAdapter3);
                                                }


                                            }
                                        }

                                    } // to close the onItemSelected

                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });


                                spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                        if (position > 0) {


                                            SharedPreferences.Editor editor = sharedpreferences.edit();

                                            editor.putString("vcode", lvilc.get(position));
                                            editor.putString("vname", (String) parent.getItemAtPosition(position));

                                            editor.commit();


                                        }

                                    } // to close the onItemSelected

                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });

                            } catch (JSONException e) {
                                // Toast.makeText(LoginActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, "Error:" + error.getMessage(), Toast.LENGTH_LONG).show();

                            Log.d("Error.Response", String.valueOf(error));
                        }
                    }
            );

// add it to the RequestQueue
            getRequest.setRetryPolicy(new DefaultRetryPolicy(
                    90000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(LoginActivity.this).addToRequestQueue(getRequest);
//                                                    startActivity(new Intent(mContext, HomePageActivity.class));
//                                                    finish();


//                                ImageView dialogButton = (ImageView) dialog2.findViewById(R.id.cls);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //  callQualityParameterJsonDetails();

                    if (spinner3.getSelectedItem().toString().equalsIgnoreCase("Tap to select gramapanchayat")) {
                        showErrorDialog("Please Select Gramapanchayat");
                    } else if (spinner4.getSelectedItem().toString().equalsIgnoreCase("Tap to select village")) {
                        showErrorDialog("Please Select Village");
                    } else {
                        startActivity(new Intent(mContext, HomePageActivity.class));
                        finish();
                    }
                }
            });

            dialog2.show();

            // callLotnoListDetailsOfApproveWare("WareHouse");

        }
    }
    public static String getDate1() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}