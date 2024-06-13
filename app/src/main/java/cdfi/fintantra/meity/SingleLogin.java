package cdfi.fintantra.meity;

import static android.os.Build.VERSION.SDK_INT;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.RadioAccessSpecifier;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cdfi.fintantra.meity.Pawhs.LoginActivity;
import cdfi.fintantra.meity.Pawhs.PAWHSApplication;
import cdfi.fintantra.meity.Pawhs.SQLiteDataBaseHandler;
import cdfi.fintantra.meity.Pawhs.SaleEntry;
import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import cdfi.fintantra.meity.Pawhs.utils.MyConstants;

public class SingleLogin extends AppCompatActivity implements TextWatcher {
    int PERMISSION_ALL = 1;
    String usercode;
    int pflag = 0;
    AutoCompleteTextView spinnernew;
    SQLiteDataBaseHandler pawhsdbhelper;
    String[] PERMISSIONS = {

            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_PHONE_NUMBERS
    };
    Button login,continues;
    RequestQueue queue;
    EditText username,password;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    ImageView splash,ministry1,ministry;
    TextView copyright,mpin,copyright1;
    DBHelper dbHelper;
    SQLiteDatabase dbs,dbs_pa;
    RelativeLayout spinners;
    EditText mpin1,mpin2,mpin3,mpin4;
    String result,rmpin1,rmpin2,rmpin3,rmpin4,respin;
    RelativeLayout r2;
    LinearLayout mpinbox;
    PAWHSApplication pawhsApplication;
    String orgnlvl_code,orgnlvl_name,fpocode,fponame,orgncode,orgnname,fpoorgn;
    RadioButton radioPass,radioMpin,radioPassMpinButton;
    RadioGroup radioPassMpinGroup;
    String passOrMpin = "";
    String user = "";
    String mpinflag = "";
    LinearLayout agree;
    TextView tagree;
    CheckBox cagree;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_login);
        getSupportActionBar().hide();


        File file = new File("/data/data/cdfi.fintantra.meity/databases/MyDBNameup.db");
        if (file.exists()) {
            dbHelper = new DBHelper(this);
            dbs = dbHelper.getWritableDatabase();

            // Toast.makeText(MainActivity.this, "Available", Toast.LENGTH_SHORT).show();
        }
        else {
            //  Toast.makeText(MainActivity.this, "Not Available", Toast.LENGTH_SHORT).show();
            dbHelper = new DBHelper(this);
            dbs = dbHelper.getWritableDatabase();


            try {
                // hAdbhelper.copyDataBase();

                dbHelper.copyDataBase();
                                            /*SharedPreferences.Editor editor = prefs.edit();
                                            editor.putBoolean("firstTimebo", true);
                                            editor.commit();*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        dbs = dbHelper.getWritableDatabase();
        pawhsdbhelper = new SQLiteDataBaseHandler(this);

        dbs_pa = pawhsdbhelper.getWritableDatabase();
        splash = (ImageView)findViewById(R.id.splash);
        copyright = findViewById(R.id.copyright);
        copyright1 = findViewById(R.id.copyright1);
        mpin1 = findViewById(R.id.mpin1);
        mpin2 = findViewById(R.id.mpin2);
        mpin3 = findViewById(R.id.mpin3);
        mpin4 = findViewById(R.id.mpin4);
        ministry1 = findViewById(R.id.ministry1);
        ministry = findViewById(R.id.ministry);
        spinners = findViewById(R.id.spinners);
        spinnernew = findViewById(R.id.spinnernew);
        continues = findViewById(R.id.continues);
        agree = findViewById(R.id.agree);
        tagree = findViewById(R.id.tagree);
        cagree = findViewById(R.id.cagree);
        tagree.setClickable(true);
        //tagree.setMovementMethod(LinkMovementMethod.getInstance());
        //String text = "<a href='https://uatuptn.kanchiffi.com/Terms-Conditions.html'> Terms and Conditions </a>";
        //tagree.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT));
        cagree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                   if(isChecked)
                                                   {
                                                       login.setVisibility(View.VISIBLE);
                                                   }
                                                   else
                                                   {
                                                       login.setVisibility(View.GONE);
                                                   }

                                               }
                                           }
        );
        tagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlString = "https://uatuptn.kanchiffi.com/Terms-Conditions.html";
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(urlString));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    // Chrome browser presumably not installed so allow user to choose instead
                    intent.setPackage(null);
                    startActivity(intent);
                }
            }
        });
        r2 = findViewById(R.id.r2);
        radioPass = findViewById(R.id.radioPass);
        radioMpin = findViewById(R.id.radioMpin);
        radioPassMpinGroup = findViewById(R.id.radioPassMpinGroup);
        pawhsApplication=PAWHSApplication.getGetInstance();
        mpinbox = findViewById(R.id.mpinbox);
        mpin = findViewById(R.id.mpin);
        login = (Button)findViewById(R.id.login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        copyright.setText("Copyright Fintantra "+String.valueOf(year));
        copyright1.setText("Copyright Fintantra "+String.valueOf(year));

        mpin1.addTextChangedListener(this);
        mpin2.addTextChangedListener(this);
        mpin3.addTextChangedListener(this);
        mpin4.addTextChangedListener(this);

        splash.setBackgroundResource(R.drawable.newkanchilogos);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        queue = Volley.newRequestQueue(this);


        fcheck();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            // Do something for lollipop and above versions
//            try {
//                if (!Environment.isExternalStorageManager()) {
//                    requestPermission();
//                }
//            } catch (Exception e) {
//
//            }
//        } else {
//            // do something for phones running an SDK before lollipop
//        }
        radioPassMpinGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioPassMpinButton = (RadioButton) findViewById(selectedId);
                passOrMpin = radioPassMpinButton.getText().toString();
                Log.e("toggles1", passOrMpin);
                if(passOrMpin.equalsIgnoreCase("MPIN")){
                    r2.setVisibility(View.GONE);
                    mpin.setVisibility(View.VISIBLE);
                    mpinbox.setVisibility(View.VISIBLE);
                    agree.setVisibility(View.GONE);
                    login.setVisibility(View.VISIBLE);
                    username.setEnabled(false);
                    loadmpin();
                }else if(passOrMpin.equalsIgnoreCase("USER ID")){
                    r2.setVisibility(View.VISIBLE);
                    mpin.setVisibility(View.GONE);
                    mpinbox.setVisibility(View.GONE);
                    agree.setVisibility(View.VISIBLE);
                    login.setVisibility(View.GONE);
                    username.setText("");
                    username.setEnabled(true);
                }
            }
        });

        Cursor cr1 = dbs.rawQuery("select * from mpin", null);
        if(cr1.getCount()>0){
            radioMpin.setEnabled(true);
            radioPass.setChecked(false);
            radioMpin.setChecked(true);
            r2.setVisibility(View.GONE);
            mpin.setVisibility(View.VISIBLE);
            mpinbox.setVisibility(View.VISIBLE);
            agree.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);
        }else{
            radioMpin.setEnabled(false);
            radioPass.setChecked(true);
            radioMpin.setChecked(false);
            r2.setVisibility(View.VISIBLE);
            mpin.setVisibility(View.GONE);
            mpinbox.setVisibility(View.GONE);
            agree.setVisibility(View.VISIBLE);
            login.setVisibility(View.GONE);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioMpin.isChecked()) {
                    login.setVisibility(View.VISIBLE);

                    mpinlogin();
                }
                else {
                    if(isNetworkAvailable()){
                        jsoncallforlogin();
                    }else{
                        showErrorDialog("No Internet Connection Available !");
                    }
                }
            }
        });

    }


    private void jsoncallforlogin(){

        if(username.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(SingleLogin.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
        }
        else if(password.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(SingleLogin.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            dbs.execSQL("DELETE FROM icdlist");
            final String url = Pojokyc.url+"/api/Mobile_FDR_Login/commonmobLogin?org=flexi6&locn=up&user=admin&lang=en_US&In_user_code="+username.getText().toString()+"&In_user_pwd="+password.getText().toString()+"&version_number="+Pojokyc.ver;
            Log.e("URL",""+url);
            HttpsTrustManager.allowAllSSL();
           // Toast.makeText(getApplicationContext(), ""+url, Toast.LENGTH_SHORT).show();
            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(JSONObject response) {
                            // display response
                            Log.e("Response", response.toString());

                            JSONObject sys  = new JSONObject();
                            try {
                                radioMpin.setEnabled(false);
                                sys = response.getJSONObject("context");
                                JSONObject obj = sys.getJSONObject("list");
                                final String uc = obj.getString("in_user_code1");
                                usercode = uc;
                                Pojokyc.uc = uc;
                                agree.setVisibility(View.GONE);
                                final String un = obj.getString("in_user_name");
                                final String rc = obj.getString("in_ic_role_code");
                                final String rn = obj.getString("in_role_name");
                                final String orgcoden = obj.getString("in_orgn_code");
                                final String oc = obj.getString("in_ic_orgn_code");
                                final String lo = obj.getString("in_location");
                                final String orgnlevel = obj.getString("in_orgn_level");
                                String tempoc1 = obj.getString("in_ic_orgn_code");
                                String[] oc1 = {};
                               try{
                                   if(tempoc1.equalsIgnoreCase("")){
                                       oc1 = new String[2];
                                       oc1[0] = obj.getString("in_orgn_code");
                                       oc1[1] = "";
                                   }else{
                                       oc1 = tempoc1.split("/");
                                   }
                                   Log.e("oc1val",""+oc1[0]);
                               }catch (Exception er){
                                   Log.e("error",Log.getStackTraceString(er));
                               }
                                String rs = obj.getString("in_Reponse");
                                final String cf = obj.getString("config");
                                if(sharedpreferences.getString("oc1","").equalsIgnoreCase(oc1[0])||sharedpreferences.getString("oc1","").isEmpty())
                               // if(sharedpreferences.getString("uc","").equalsIgnoreCase(usercode)||sharedpreferences.getString("uc","").isEmpty())
                                {

                                }
                                else
                                {
                                    deletetableandsharedpreference();
                                }
                                final JSONArray jsonArray = response.getJSONArray("icd");
                                for(int i = 0; i<jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    orgnlvl_code = object.getString("in_orgnlvl_code");
                                    orgnlvl_name = object.getString("in_orgnlvl_name");
                                    dbHelper.inserticdlist(orgnlvl_code, orgnlvl_name);
                                }
                                List<String> org1 = new ArrayList<String>();
                                org1.add("Tap to Select FPO");
                                final List<String> org2 = new ArrayList<String>();
                                org2.add("0");
                                final List<String> org3 = new ArrayList<String>();
                                org3.add("0");
                                final JSONArray paarray = response.getJSONArray("pawhs");
                                for(int i = 0; i<paarray.length(); i++){
                                    JSONObject paobject = paarray.getJSONObject(i);
                                    fpocode = paobject.getString("in_FPO_Code");
                                    fponame = paobject.getString("in_FPO_Name");
                                    orgncode = paobject.getString("in_orgn_code");
                                    orgnname = paobject.getString("in_orgn_name");
                                    fpoorgn = paobject.getString("in_FPO_ORGN");
                                    String name = paobject.getString("in_FPO_ORGN");
                                    String code = paobject.getString("in_orgn_code");
                                    String fcode = paobject.getString("in_FPO_Code");
                                    org1.add(name);
                                    org2.add(code);
                                    org3.add(fcode);
                                }
                                final List<String> org1fpo = new ArrayList<String>();
                                final List<String> org1fpocode = new ArrayList<>();
                                JSONArray fpoarray = response.getJSONArray("fpo");
                                for(int i = 0; i<fpoarray.length(); i++){
                                    JSONObject paobject2 = fpoarray.getJSONObject(i);

                                    String name = paobject2.getString("fpo_role_name");

                                    org1fpo.add(name);
                                    org1fpocode.add(paobject2.getString("fpo_orgn_code"));
                                }


                                final  SharedPreferences.Editor editor = sharedpreferences.edit();

                                editor.putString(uc+"/un", un);
                                editor.putString(uc+"/rc", rc);
                                editor.putString(uc+"/oc", oc);
                                editor.putString(uc+"/rn", rn);
                                editor.putString(uc+"/uc", uc);
                                editor.putString(uc+"/lo", lo);
                                editor.putString(uc+"/orgn",oc);
                                editor.putString(uc+"/oc1", oc1[0]);
                                editor.putString(uc+"/login", "1");
                                editor.putString(uc+"/off","1");
                                editor.putString(uc+"/cf",cf);
                                editor.putString(uc+"/username",username.getText().toString());
                                editor.putString(uc+"/fpocode",fpocode);
                                editor.putString(uc+"/fponame",fponame);
                                editor.putString(uc+"/orgncode",orgncode);
                                editor.putString(uc+"/orgnname",orgnname);
                                editor.putString(uc+"/fpoorgn",fpoorgn);
                                editor.putString(uc+"/orgnlvlcode",oc);


                                editor.commit();
                                pawhsApplication.storePreference(getApplicationContext(),uc+"/"+ApiUtils.IN_ROLE_CODE,rc);
                                pawhsApplication.storePreference(getApplicationContext(),uc+"/"+ApiUtils.IN_USER_CODE,uc);
                                pawhsApplication.storePreference(getApplicationContext(),uc+"/"+ApiUtils.IN_USER_NAME,un);
                                pawhsApplication.storePreference(getApplicationContext(),uc+"/"+ApiUtils.ORGN_ID,sys.getString("orgnId"));
                                pawhsApplication.storePreference(getApplicationContext(),uc+"/"+ApiUtils.LOCN_ID,sys.getString("locnId"));
                                pawhsApplication.storePreference(getApplicationContext(),uc+"/"+ApiUtils.USER_ID,sys.getString("userId"));
                                pawhsApplication.storePreference(getApplicationContext(),uc+"/"+ApiUtils.LOCALE_ID,sys.getString("localeId"));
                                if(un.equalsIgnoreCase(""))
                                {
                                    showErrorDialog(rs);
                                }

                                else
                                {
                                    login.setVisibility(View.GONE);
                                    ministry.setVisibility(View.GONE);
                                    copyright.setVisibility(View.GONE);
                                    continues.setVisibility(View.VISIBLE);
                                   // ministry1.setVisibility(View.VISIBLE);
                                    spinners.setVisibility(View.VISIBLE);
                                    copyright1.setVisibility(View.VISIBLE);

                                    final Dialog dialog2 = new Dialog(SingleLogin.this);
                                    dialog2.setContentView(R.layout.orglist);
                                    //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                                    dialog2.setTitle("Title...");
                                    int width = WindowManager.LayoutParams.MATCH_PARENT;
                                    int height = WindowManager.LayoutParams.MATCH_PARENT;

                                    dialog2.getWindow().setLayout(width, height);


                                    final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(SingleLogin.this, R.layout.fpospinnertext, org1fpo);

                                    // Drop down layout style - list view with radio button
                                    //dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                                    spinnernew.setAdapter(dataAdapter);
                                    if(dataAdapter.getCount() == 1){
                                        spinnernew.setText(org1fpo.get(0));
                                    }
                                  //  spinnernew.setText(org1fpo.get(0));
                                    spinnernew.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            spinnernew.setAdapter(dataAdapter);
                                            spinnernew.setThreshold(0);
                                            spinnernew.showDropDown();
                                        }
                                    });
                                    // attaching data adapter to spinner
                                    spinnernew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            Log.e("test","1");
                                            if(orgnlevel.equalsIgnoreCase("ORG_FO")){
                                              //  int a = org1fpo.indexOf(spinnernew.getText().toString().trim());
                                                String selectedcode = "";

                                                    selectedcode = org1fpocode.get(i);

                                                Log.e("selectedcode",i + " " +selectedcode);
                                                for(int b = 0; b<jsonArray.length(); b++){
                                                  try{
                                                      JSONObject object = jsonArray.getJSONObject(b);
                                                      String orgnlvl_codediv = object.getString("in_orgnlvl_code");
                                                      String oglvl[] = orgnlvl_codediv.split("/");
                                                      if(oglvl[0].equalsIgnoreCase(selectedcode)){
                                                          if(pflag ==0) {
                                                              orgnlvl_code = object.getString("in_orgnlvl_code");
                                                              orgnlvl_name = object.getString("in_orgnlvl_name");
                                                         pflag = 1;
                                                          }
                                                      }
                                                  }catch (Exception er){
                                                      Log.e("error",Log.getStackTraceString(er));
                                                  }
                                                }
                                                for(int k = 0 ; k < paarray.length();k++){
                                                    JSONObject paobject = null;
                                                    try {
                                                        paobject = paarray.getJSONObject(k);
                                                        String fpocodediv = paobject.getString("in_FPO_Code");
                                                        if(fpocodediv.equalsIgnoreCase(selectedcode)){
                                                            fpocode = paobject.getString("in_FPO_Code");
                                                            fponame = paobject.getString("in_FPO_Name");
                                                            orgncode = paobject.getString("in_orgn_code");
                                                            orgnname = paobject.getString("in_orgn_name");
                                                            fpoorgn = paobject.getString("in_FPO_ORGN");
                                                       /* String name = paobject.getString("in_FPO_ORGN");
                                                        String code = paobject.getString("in_orgn_code");
                                                        String fcode = paobject.getString("in_FPO_Code");
                                                        org1.add(name);
                                                        org2.add(code);
                                                        org3.add(fcode);*/
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                                final  SharedPreferences.Editor editor = sharedpreferences.edit();

                                                Log.e("orgcodenew",orgnlvl_code);
                                                String newocarr[] = orgnlvl_code.split("/");
                                                editor.putString(uc+"/un", un);
                                                editor.putString(uc+"/rc", rc);
                                                editor.putString(uc+"/oc", orgnlvl_code);
                                                editor.putString(uc+"/rn", rn);
                                                editor.putString(uc+"/uc", uc);
                                                editor.putString(uc+"/lo", lo);
                                                editor.putString(uc+"/orgn",orgnlvl_code);
                                                editor.putString(uc+"/oc1", newocarr[0]);
                                                editor.putString(uc+"/login", "1");
                                                editor.putString(uc+"/off","1");
                                                editor.putString(uc+"/cf",cf);
                                                editor.putString(uc+"/username",username.getText().toString());
                                                editor.putString(uc+"/fpocode",fpocode);
                                                editor.putString(uc+"/fponame",fponame);
                                                editor.putString(uc+"/orgncode",orgncode);
                                                editor.putString(uc+"/orgnname",orgnname);
                                                editor.putString(uc+"/fpoorgn",fpoorgn);
                                                editor.putString(uc+"/orgnlvlcode",orgnlvl_code);
                                                editor.putString("Fcode", fpocode);

                                                editor.commit();
                                                pawhsApplication.storePreference(getApplicationContext(),uc+"/"+ApiUtils.IN_ROLE_CODE,rc);
                                                pawhsApplication.storePreference(getApplicationContext(),uc+"/"+ApiUtils.IN_USER_CODE,uc);
                                                pawhsApplication.storePreference(getApplicationContext(),uc+"/"+ApiUtils.IN_USER_NAME,un);
/*                                                pawhsApplication.storePreference(getApplicationContext(),uc+"/"+ApiUtils.ORGN_ID,sys.getString("orgnId"));
                                                pawhsApplication.storePreference(getApplicationContext(),uc+"/"+ApiUtils.LOCN_ID,sys.getString("locnId"));
                                                pawhsApplication.storePreference(getApplicationContext(),uc+"/"+ApiUtils.USER_ID,sys.getString("userId"));
                                                pawhsApplication.storePreference(getApplicationContext(),uc+"/"+ApiUtils.LOCALE_ID,sys.getString("localeId"));*/
                                                pawhsApplication.storePreference(getApplicationContext(), uc+"/"+ApiUtils.ORGN_CODE, orgncode);
                                            }
                                        }
                                    });
                                    try{



                                        editor.putString("Fcode", org3.get(1));

                                        editor.commit();
                                        pawhsApplication.storePreference(getApplicationContext(), uc+"/"+ApiUtils.ORGN_CODE, org2.get(1));
                                    }catch (Exception e){

                                    }

                                    Toast.makeText(SingleLogin.this, "Welcome "+rn , Toast.LENGTH_SHORT).show();

//                                    spinnernew.setOnItemSelectedListener(
//                                            new AdapterView.OnItemSelectedListener() {
//                                                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//
//                                                    if(pos>0)
//                                                    {
//
//
//                                                        editor.putString("Fcode", org3.get(pos));
//
//                                                        editor.commit();
//                                                        pawhsApplication.storePreference(getApplicationContext(), uc+"/"+ApiUtils.ORGN_CODE, org2.get(pos));
//                                                        Toast.makeText(SingleLogin.this, "Welcome "+rn , Toast.LENGTH_SHORT).show();
//
//
//
//
//                                                        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
//
//                                                    }
//
//                                                }
//                                                public void onNothingSelected(AdapterView<?> parent) {
//                                                }
//                                            });

                                    continues.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(spinnernew.getText().toString().equalsIgnoreCase(""))
                                            {
                                                Toast.makeText(getApplicationContext(), "Please Select FPO", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                sprefresh();
                                                if (sharedpreferences.getString("mpinflag", "0").equalsIgnoreCase("0") || sharedpreferences.getString("mpinflag","").equalsIgnoreCase("")) {
                                                    Intent i = new Intent(getApplicationContext(), Mpin.class);
                                                    startActivity(i);
                                                } else {

                                                    Intent i = new Intent(getApplicationContext(), Landpage.class);
                                                    startActivity(i);
                                                }
                                            }
                                        }
                                    });
                                    //  dialog2.show();

                                }


                            } catch (JSONException e) {
                                /* FirebaseApp.initializeApp(SingleLogin.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis()/1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                Log.e("Valuecheck",""+mRef.getRef());
                                mRef.child("name").setValue("LOGIN");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("FDR");
                                Toast.makeText(SingleLogin.this, "Invalid Login", Toast.LENGTH_SHORT).show();*/
                                e.printStackTrace();
                                Log.e("error1234",e.toString());
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SingleLogin.this, "Error:"+error, Toast.LENGTH_LONG).show();

                            Log.d("Error.Response", String.valueOf(error));
                        }
                    }
            );

// add it to the RequestQueue
            getRequest.setRetryPolicy(new DefaultRetryPolicy(
                    900000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(SingleLogin.this).addToRequestQueue(getRequest);
        }
    }

    private void mpinlogin(){
        rmpin1 = mpin1.getText().toString();
        rmpin2 = mpin2.getText().toString();
        rmpin3 = mpin3.getText().toString();
        rmpin4 = mpin4.getText().toString();
        respin = rmpin1+rmpin2+rmpin3+rmpin4;

        if(rmpin1.isEmpty() || rmpin2.isEmpty() || rmpin3.isEmpty() || rmpin4.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter all the fields",Toast.LENGTH_SHORT).show();
        }else{
            Cursor cr3 = dbs.rawQuery("select * from mpin", null);
            if(cr3.getCount()>0){
                if(cr3.moveToFirst()){
                    do{
                        result = cr3.getString(cr3.getColumnIndexOrThrow("usermpin"));
                    }while(cr3.moveToNext());
                }
            }
            if(!respin.equals(result)){
                Toast.makeText(getApplicationContext(),"PIN Not Matched",Toast.LENGTH_SHORT).show();
            }else{
                usercode = username.getText().toString();
                sprefresh();
                Intent intent = new Intent(getApplicationContext(),Landpage.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity(); // or finish();
    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    private void loadmpin(){
        Cursor cr2 = dbs.rawQuery("select * from mpin", null);
        if(cr2.getCount()>0){
            if(cr2.moveToFirst()){
                do{
                    user = cr2.getString(cr2.getColumnIndexOrThrow("usercode"));
                    username.setText(user);
                }while(cr2.moveToNext());
            }
        }
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
    public void fcheck()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        if (!sharedpreferences.getBoolean("onlyonce", false)) {
            // <---- run your one time code here

            Log.e("LAUNCHFIST","YES");
            // mark once runned.
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean("onlyonce", true);

            editor.putString("LAST_LAUNCH_DATEICD", currentDate);
            editor.putString("LAST_LAUNCH_DATE", currentDate);
            editor.putString("dateforsyncicd", ".");
            editor.putString("dateforsync", ".");
            editor.putString("dateforsyncpa", ".");
            editor.putString("dateforsyncpd", ".");
            editor.putString("mpinflag", "0");
            editor.commit();
            editor.commit();
        }
        else
        {
            Log.e("LAUNCHFIST","NO");
        }


    }
    public void sprefresh()
    {
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("un", sharedpreferences.getString(usercode+"/un",""));
        editor.putString("rc", sharedpreferences.getString(usercode+"/rc",""));
        editor.putString("oc", sharedpreferences.getString(usercode+"/oc",""));
        editor.putString("rn", sharedpreferences.getString(usercode+"/rn",""));
        editor.putString("uc", sharedpreferences.getString(usercode+"/uc",""));
        editor.putString("lo", sharedpreferences.getString(usercode+"/lo",""));
        editor.putString("orgn",sharedpreferences.getString(usercode+"/oc",""));
        editor.putString("oc1", sharedpreferences.getString(usercode+"/oc1",""));
        editor.putString("login", "1");
        editor.putString("off","1");
        if(sharedpreferences.getString(usercode+"/lo","").equalsIgnoreCase("QCD_UNS_UP"))
        {
            Pojokyc.paloc = "UP";
        }
        else
        {
            Pojokyc.paloc = "Tamil Nadu";
        }
        editor.putString("cf",sharedpreferences.getString(usercode+"/cf",""));
        editor.putString("username",username.getText().toString());
        editor.putString("fpocode",sharedpreferences.getString(usercode+"/fpocode",""));
        editor.putString("fponame",sharedpreferences.getString(usercode+"/fponame",""));
        editor.putString("orgncode",sharedpreferences.getString(usercode+"/orgncode",""));
        editor.putString("orgnname",sharedpreferences.getString(usercode+"/orgnname",""));
        editor.putString("fpoorgn",sharedpreferences.getString(usercode+"/fpoorgn",""));
        editor.putString("orgnlvlcode",sharedpreferences.getString(usercode+"/orgnlvlcode",""));


        editor.commit();
        pawhsApplication.storePreference(getApplicationContext(),ApiUtils.IN_ROLE_CODE,sharedpreferences.getString(usercode+"/rc",""));
        pawhsApplication.storePreference(getApplicationContext(),ApiUtils.IN_USER_CODE,sharedpreferences.getString(usercode+"/uc",""));
        pawhsApplication.storePreference(getApplicationContext(),ApiUtils.IN_USER_NAME,sharedpreferences.getString(usercode+"/un",""));
        pawhsApplication.storePreference(getApplicationContext(),ApiUtils.ORGN_ID,pawhsApplication.getPreferenceFromString(SingleLogin.this, usercode+"/"+ApiUtils.ORGN_ID));
        pawhsApplication.storePreference(getApplicationContext(),ApiUtils.LOCN_ID,pawhsApplication.getPreferenceFromString(SingleLogin.this, usercode+"/"+ApiUtils.LOCN_ID));
        pawhsApplication.storePreference(getApplicationContext(),ApiUtils.USER_ID,pawhsApplication.getPreferenceFromString(SingleLogin.this, usercode+"/"+ApiUtils.USER_ID));
        pawhsApplication.storePreference(getApplicationContext(),ApiUtils.LOCALE_ID,pawhsApplication.getPreferenceFromString(SingleLogin.this, usercode+"/"+ApiUtils.LOCALE_ID));
        pawhsApplication.storePreference(getApplicationContext(), ApiUtils.ORGN_CODE,pawhsApplication.getPreferenceFromString(SingleLogin.this, usercode+"/"+ApiUtils.ORGN_CODE));

    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
    /*    mpin1.requestFocus();
        if(!mpin1.getText().toString().equalsIgnoreCase("")){
            mpin2.requestFocus();
        }
        if(!mpin2.getText().toString().equalsIgnoreCase("")){
            mpin3.requestFocus();
        }
        if(!mpin3.getText().toString().equalsIgnoreCase("")){
            mpin4.requestFocus();
        }*/
        if(editable == mpin1.getEditableText()){
            if(editable.length() == 1){
                mpin2.requestFocus();
            }
        }else if(editable == mpin2.getEditableText()){
            if(editable.length() == 1){
                mpin3.requestFocus();
            }else if(editable.length() == 0){
                mpin1.requestFocus();
            }
        }else if(editable == mpin3.getEditableText()){
            if(editable.length() == 1){
                mpin4.requestFocus();
            }else if(editable.length() == 0){
                mpin2.requestFocus();
            }
        }else if(editable == mpin4.getEditableText()){
            if(editable.length() == 0){
                mpin3.requestFocus();
            }
        }
    }
    public void deletetableandsharedpreference()
    {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();
        dbs.execSQL("Delete from product");
        dbs.execSQL("Delete from productlist");
        dbs.execSQL("Delete from inwardlist");
        dbs.execSQL("Delete from productlist2");
        dbs.execSQL("Delete from invoice");
        dbs.execSQL("Delete from inward");
        dbs.execSQL("Delete from paylist");
        dbs.execSQL("Delete from paylist2");
        dbs.execSQL("Delete from invoicelist");
        dbs.execSQL("Delete from supplierlist");
        dbs.execSQL("Delete from customerlist");
        dbs.execSQL("Delete from customerlistnm");
        dbs.execSQL("Delete from astock");
        dbs.execSQL("Delete from astock2");
        dbs.execSQL("Delete from tab");
        dbs.execSQL("Delete from state");
        dbs.execSQL("Delete from tablist");
        dbs.execSQL("Delete from farmerh");
        dbs.execSQL("Delete from kyc");
        dbs.execSQL("Delete from bank");
        dbs.execSQL("Delete from personal");
        dbs.execSQL("Delete from family");
        dbs.execSQL("Delete from training");
        dbs.execSQL("Delete from loan");
        dbs.execSQL("Delete from input");
        dbs.execSQL("Delete from loanr");
        dbs.execSQL("Delete from loanq");
        dbs.execSQL("Delete from shareh");
        dbs.execSQL("Delete from business");
        dbs.execSQL("Delete from land");
        dbs.execSQL("Delete from live");
        dbs.execSQL("Delete from production");
        dbs.execSQL("Delete from asset");
        dbs.execSQL("Delete from stock");
        dbs.execSQL("Delete from insurance");
        dbs.execSQL("Delete from sale");
       // dbs.execSQL("Delete from bankm");
       // dbs.execSQL("Delete from masterl");
        dbs.execSQL("Delete from masterla");
        dbs.execSQL("Delete from address");
        dbs.execSQL("Delete from farlist");
        dbs.execSQL("Delete from crop");
        dbs.execSQL("Delete from sowing");
        dbs.execSQL("Delete from ads");
        dbs.execSQL("Delete from orgn");
        dbs.execSQL("Delete from icdlist");
        dbs.execSQL("Delete from sno");
        dbs.execSQL("Delete from lrplist");
        dbs.execSQL("Delete from inw");
        dbs.execSQL("Delete from itransfer");
        dbs.execSQL("Delete from iconfirm");
        dbs.execSQL("Delete from icdoc");
        dbs.execSQL("Delete from icdip");
        dbs.execSQL("Delete from oci");
        dbs.execSQL("Delete from mpin");
        dbs.execSQL("Delete from attachment");
        dbs.execSQL("Delete from template");
        dbs.execSQL("Delete from templatefarmer");
        dbs.execSQL("Delete from gpsactivity");


        //for pawhs db -start
        dbs_pa.execSQL("DELETE FROM ActualProList ");
        dbs_pa.execSQL("DELETE FROM ActualProcSave ");
        dbs_pa.execSQL("DELETE FROM AppOtherDetail ");
        dbs_pa.execSQL("DELETE FROM AppQtyDetail ");
        dbs_pa.execSQL("DELETE FROM AppSiNoDetail ");
        dbs_pa.execSQL("DELETE FROM AppSingleHeader ");
        dbs_pa.execSQL("DELETE FROM EstimateList ");
        dbs_pa.execSQL("DELETE FROM FARMER ");
        dbs_pa.execSQL("DELETE FROM OtherDetailList ");
        dbs_pa.execSQL("DELETE FROM ProductMasterAllProduct ");
        dbs_pa.execSQL("DELETE FROM QtyDetailList ");
        dbs_pa.execSQL("DELETE FROM SiNoDetailList ");
        dbs_pa.execSQL("DELETE FROM SpmDetailList ");
        dbs_pa.execSQL("DELETE FROM SubmitRecEstPurchase ");
        dbs_pa.execSQL("DELETE FROM WROtherDetail ");
        dbs_pa.execSQL("DELETE FROM WRQtyDetail ");
        dbs_pa.execSQL("DELETE FROM WRSingleHeader ");
        dbs_pa.execSQL("DELETE FROM WRSiNoDetail ");
        dbs_pa.execSQL("DELETE FROM WrList ");
        dbs_pa.execSQL("DELETE FROM WrReceiptList ");
        dbs_pa.execSQL("DELETE FROM bank ");
        dbs_pa.execSQL("DELETE FROM bankm ");
        dbs_pa.execSQL("DELETE FROM byr ");
        dbs_pa.execSQL("DELETE FROM dstn ");
        dbs_pa.execSQL("DELETE FROM farmerh ");
        dbs_pa.execSQL("DELETE FROM fpoad ");
        dbs_pa.execSQL("DELETE FROM masterl ");
        dbs_pa.execSQL("DELETE FROM purchaseentry ");
        dbs_pa.execSQL("DELETE FROM qpar ");
        dbs_pa.execSQL("DELETE FROM qparest ");
        dbs_pa.execSQL("DELETE FROM qparpe ");
        dbs_pa.execSQL("DELETE FROM saleentry ");
        dbs_pa.execSQL("DELETE FROM sno ");
        dbs_pa.execSQL("DELETE FROM snope ");
        dbs_pa.execSQL("DELETE FROM vstatus ");
        dbs_pa.execSQL("DELETE FROM vtype ");
        dbs_pa.execSQL("DELETE FROM sqlite_sequence ");
        //for pawhs db -end
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        editor.putString("LAST_LAUNCH_DATEICD", currentDate);
        editor.putString("LAST_LAUNCH_DATE", currentDate);
        editor.putString("dateforsyncicd", ".");
        editor.putString("dateforsync", ".");
        editor.putString("dateforsyncpa", ".");
        editor.putString("dateforsyncpd", ".");
        editor.putString("mpinflag", "0");
        editor.putBoolean("onlyonce", true);
        editor.commit();
    }
    private void requestPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R)
        {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                startActivityForResult(intent, 2296);
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 2296);
            }
        }
        else
        {
            //below android 11
            //  ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
