package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cdfi.fintantra.meity.Pawhs.LoginActivity;
import cdfi.fintantra.meity.Pawhs.PAWHSApplication;
import cdfi.fintantra.meity.Pawhs.SQLiteDataBaseHandler;
import cdfi.fintantra.meity.Pawhs.SaleEntry;
import cdfi.fintantra.meity.Pawhs.api.ApiService;
import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import cdfi.fintantra.meity.Pawhs.formermodel.FormerDao;
import cdfi.fintantra.meity.Pawhs.model.PmContextDao;
import cdfi.fintantra.meity.Pawhs.model.PmListDao;
import cdfi.fintantra.meity.Pawhs.model.ProductMasterDao;
import cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel.SingleProductMasterDao;
import cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel.SpmContextDao;
import cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel.SpmDetailDao;
import cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel.SpmHeaderDao;
import cdfi.fintantra.meity.Pawhs.recactpurchase.RecActualPurchaseActivity;
import cdfi.fintantra.meity.Pawhs.utils.MyConstants;
import cdfi.fintantra.meity.Pawhs.utils.Utility;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Landpage extends AppCompatActivity {

    ImageView pawhs, icd, fdr, pro;
    private static final int REQ_CODE_VERSION_UPDATE = 530;
    JSONObject userd;
    int memcount =0,mmid=0,memcount2=0;
    String payinvoiceno = "";
    ArrayList<String> memid = new ArrayList<>();
    String inn,rqty;
    String gpsrid="";
    String fmid,adsfid;
    int ctest = 0;
    int gpscount=0;
    int gpscount2= 0;
    String icid,icgrn,pcode2,icqty,icrmk;
    String lrp,itid,itorg,tstock,pcode;
    TextView txtissue;
    String tspr,orgnnew,orgnnew2,orgnew3;
    JSONObject jsonParam;
    int ct4,ct5,ct6;
    String iid;
    String uflag;
    String billno,billno2;
    String paybalamt,refno,paidamt,paymode,payindate,cinv;
    String at,atno,aqc,aqcd,aqsc,aqsc2,aqpc,aqpd,aqu,aqi,aqd,aqpco,aqi2,aty,aqid,inwid;
    String payid,paydate,payamt;
    String sname,slcn,sst,encodedimage="",ids,farco,ids2,tax,oth,trs,net,pr,pd,pt,pa,pq,pn,pc,psc,pco,pu,amt,id,bp,balamt;
    String sname2,slcn2,sst2,encodedimage2="",ids22,ids222,tax2,oth2,trs2,net2,pr2,pd2,pt2,pa2,pq2,pn2,pc2,psc2,pco2,pu2,amt2,id2;
    int ct=0,ct2=0,ctt=0,ct22=0,c3=0,ct3=0;
    int finalcount ,fcnt=0;
    SQLiteDataBaseHandler pawhsdbhelper;
    String afcode;
    String gf;
    String farid;
    String n2n;
    String n1, n10, n11, n12, n13,n2,n3,n4,n5;
    String encodedImage = "0",encodedImage2 = "0",encodedImage3="0";
    int offc = 0;;
    int offc2 = 0;
    JSONArray castemaster,castfar,castbank;
    EditText username,password;
    ArrayList<String> tfield = new ArrayList<>();
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    String bst = "0";
    private String orgnCode, locnId, userId, localeId, orgnId;
    ByteArrayOutputStream byteArrayOutputStream,byteArrayOutputStream2,byteArrayOutputStream3;
    DBHelper mydb;
    ToggleButton tb;
    RelativeLayout regfar,modfar,pentry,sentry,pdc,stkadj,stkinw,bin,binp,issue;
    ProgressDialog pdialog;
    ImageView nd,cls;
    RelativeLayout r1,r2;
    SQLiteDatabase dbs,dbs_pa;
    Dialog dialog;
    private RelativeLayout progressLayout;
    String cqno;
    String myr,myr2,mcode,phn;
    String lc2;
    String lc;
    String inid;
    ApiService mAPIService;
    private boolean isNetwork;
    private PAWHSApplication pawhsApplication;
    private String orglvlcode = "";
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {

            Manifest.permission.BLUETOOTH_CONNECT,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_landpage);
        getSupportActionBar().hide();


        isNetwork = Utility.checkConnectivity(getApplicationContext());
        mAPIService = ApiUtils.getAPIService();
        pawhsApplication = PAWHSApplication.getGetInstance();
        pawhsdbhelper = new SQLiteDataBaseHandler(this);
        dbs_pa = pawhsdbhelper.getWritableDatabase();
        progressLayout = (RelativeLayout) findViewById(R.id.progressLayout);
        orgnCode = pawhsApplication.getPreferenceFromString(getApplicationContext(), ApiUtils.ORGN_CODE);
        locnId = pawhsApplication.getPreferenceFromString(getApplicationContext(), ApiUtils.LOCN_ID);
        userId = pawhsApplication.getPreferenceFromString(getApplicationContext(), ApiUtils.USER_ID);
        localeId = pawhsApplication.getPreferenceFromString(getApplicationContext(), ApiUtils.LOCALE_ID);
        orgnId = pawhsApplication.getPreferenceFromString(getApplicationContext(), ApiUtils.ORGN_ID);
       // startService(new Intent( this, NewService.class ) );
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mydb = new DBHelper(this);
        dbs = mydb.getWritableDatabase();
        pdialog = new ProgressDialog(this);
        pawhs = (ImageView) findViewById(R.id.pawhs);
        icd = (ImageView) findViewById(R.id.icd);
        fdr = (ImageView) findViewById(R.id.fdr);
        pro = (ImageView) findViewById(R.id.pro);
        nd = findViewById(R.id.nd);
        cls = findViewById(R.id.cls);
        regfar = findViewById(R.id.regfar);
        modfar = findViewById(R.id.modfar);
        pentry = findViewById(R.id.pentry);
        sentry = findViewById(R.id.sentry);
        pdc = findViewById(R.id.pdc);
        stkinw = findViewById(R.id.stkinw);
        stkadj = findViewById(R.id.stkadj);
        bin = findViewById(R.id.bin);
        binp = findViewById(R.id.binp);
        issue = findViewById(R.id.issue);
        txtissue = findViewById(R.id.txtissue);
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        String usercode = sharedpreferences.getString("uc","");
        orglvlcode = sharedpreferences.getString(usercode+"/"+"orgnlvlcode","");


        if(sharedpreferences.getString("rc","").equalsIgnoreCase("ROLE_PRIMARY_IC"))
        {
            txtissue.setText("IC-Stock Transfer");
            stkinw.setEnabled(true);
        }
        else
        {
            txtissue.setText("IC-Stock Confirm");
            stkinw.setEnabled(false);
            regfar.setEnabled(false);
            modfar.setEnabled(false);
            pentry.setEnabled(false);
            sentry.setEnabled(false);
            pdc.setEnabled(false);
            stkadj.setEnabled(false);
        }

        stkinw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(orglvlcode.equalsIgnoreCase("")){

              }else{
                  final SQLiteDatabase dbs = mydb.getWritableDatabase();
                  dbs.execSQL("delete from productlist");
                  dbs.execSQL("delete from icdip");
                  dbs.execSQL("delete from icdoc");
                  Intent i = new Intent(getApplicationContext(),Stockinwardnew.class);
                  startActivity(i);
                  overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
              }
            }
        });
        stkadj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orglvlcode.equalsIgnoreCase("")){

                }else{
                    Intent i = new Intent(getApplicationContext(),StockAdjustment2.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                }
            }
        });
        bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orglvlcode.equalsIgnoreCase("")){

                }else {
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("bi", "0");
                    editor.putString("rfs", "1");

                    editor.commit();
                    final SQLiteDatabase dbs = mydb.getWritableDatabase();
                    dbs.execSQL("delete from productlist2");
                    Intent i = new Intent(getApplicationContext(),Bookinvoicenew.class);
                    startActivity(i);

                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                }
            }
        });
        binp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orglvlcode.equalsIgnoreCase("")){

                }else {
                    String selectQuerypay = "SELECT  * FROM paylist where uflag = 0";

                    Cursor cpay = dbs.rawQuery(selectQuerypay, null);
                    if(cpay.getCount()>0)
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Please Do the Transaction ICD To Continue")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //set what would happen when positive button is clicked
                                                //finish();

                                            }
                                        }
//set negative button

                                )
                                .setCancelable(false)
                                .show();
                    }
                    else {
                        Intent i = new Intent(getApplicationContext(), Paymentlist.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    }
                }
            }
        });
        issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orglvlcode.equalsIgnoreCase("")){

                }else{
                    if(sharedpreferences.getString("rc","").equalsIgnoreCase("ROLE_PRIMARY_IC"))
                    {

                        Intent i = new Intent(getApplicationContext(),Issuetransfer.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

                    }
                    else
                    {

                        Intent i = new Intent(getApplicationContext(),Issueconfirm.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

                    }
                }
            }
        });

        pdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ProductionTab.class);
                startActivity(i);
            }
        });

        pentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try{
                   Pojokyc.purno="";
                /*RecEstPurchaseFragment recEstPurchaseFragment=new RecEstPurchaseFragment();
                Utility.loadFragment(getFragmentManager(), R.id.content_frame, recEstPurchaseFragment);*/
                   String usercode = sharedpreferences.getString("uc","");
                   String padtl = pawhsApplication.getPreferenceFromString(Landpage.this, usercode+"/"+"ORGN_CODE");
                   if(!padtl.equalsIgnoreCase("")){
                       Intent intent = new Intent(getApplicationContext(), RecActualPurchaseActivity.class);
                       intent.putExtra("Frm", "Actual");
                       intent.putExtra("PEID", "");
                       startActivity(intent);
                   }
               }catch (Exception er){
                   Log.e("error",Log.getStackTraceString(er));
               }
            }
        });

        sentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             try{
                 Pojokyc.saleno = "";
                /*RecEstPurchaseFragment recEstPurchaseFragment=new RecEstPurchaseFragment();
                Utility.loadFragment(getFragmentManager(), R.id.content_frame, recEstPurchaseFragment);*/
                 String usercode = sharedpreferences.getString("uc","");
                 String padtl = pawhsApplication.getPreferenceFromString(Landpage.this, usercode+"/"+"ORGN_CODE");
                 if(!padtl.equalsIgnoreCase("")) {
                     Intent intent = new Intent(getApplicationContext(), SaleEntry.class);
                     intent.putExtra("SEID", "");
                     startActivity(intent);
                 }
             }catch (Exception er){
                 Log.e("error",Log.getStackTraceString(er));
             }
                }
           // }
        });



        regfar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity3.class);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("frm", "1");
                editor.putString("fcode", "");
                editor.putString("fid", "");

                editor.commit();
                startActivity(i);
            }
        });
        modfar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkAvailable()) {
                    final ProgressDialog pd = new ProgressDialog(Landpage.this);
                    pd.setMessage("Loading");
                    pd.show();
                    Intent i = new Intent(getApplicationContext(), MainActivity4.class);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("frm", "2");
                    editor.putString("fid2", "");
                    editor.putString("farmer1", "");
                    editor.putString("fcode2", "");
                    editor.commit();
                    startActivity(i);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            pd.dismiss();

                        }

                    }, 2000);
                }
            }
        });

        nd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fcnt = 0;
                finalcount = 0;
                dialog = new Dialog(Landpage.this);
                dialog.setContentView(R.layout.singlenavmenu);
                dialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
                dialog.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                r1 = dialog.findViewById(R.id.r1);
                r2 = dialog.findViewById(R.id.r2);
                RelativeLayout rm = dialog.findViewById(R.id.rm);
                RelativeLayout memmap = dialog.findViewById(R.id.memmap);
                RelativeLayout trsfdr = dialog.findViewById(R.id.trsfdr);
                RelativeLayout trsicd = dialog.findViewById(R.id.trsicd);
                RelativeLayout trspa = dialog.findViewById(R.id.trspa);
                RelativeLayout trsmm = dialog.findViewById(R.id.trsmm);
                RelativeLayout trsgps = dialog.findViewById(R.id.trsgps);
                RelativeLayout rlog = dialog.findViewById(R.id.rlog);
                RelativeLayout r3 = dialog.findViewById(R.id.r3);
                TextView tuser = dialog.findViewById(R.id.tuser);
                TextView fname = dialog.findViewById(R.id.fname);
                RelativeLayout paymentpending = dialog.findViewById(R.id.paymentpending);
                RelativeLayout aepslist = dialog.findViewById(R.id.aepslist);
                cls = dialog.findViewById(R.id.cls);
                Cursor cr1 = dbs.rawQuery("select * from mpin", null);
                if(cr1.getCount()>0){
                    r1.setVisibility(View.GONE);
                }else{
                    r2.setVisibility(View.GONE);
                }

                tuser.setText("Welcome"+" "+sharedpreferences.getString("username","")+"!");
                fname.setText(sharedpreferences.getString("fponame",""));

                memmap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Pojokyc.mapfcode="";
                        Intent i = new Intent(getApplicationContext(),MemberMapping.class);
                        startActivity(i);

                    }
                });

                paymentpending.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isNetworkAvailable()) {
                            Intent i = new Intent(getApplicationContext(), Paymentpending.class);
                            startActivity(i);
                        } else{
                        showErrorDialog("No Internet Connections Available !");
                    }
                    }
                });

                aepslist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isNetworkAvailable()) {
                            Intent i = new Intent(getApplicationContext(), aepslist.class);
                            startActivity(i);
                        } else{
                            showErrorDialog("No Internet Connections Available !");
                        }
                    }
                });

                r3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(),GPSActivity.class);
                        startActivity(i);
                    }
                });

                rlog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final String url = Pojokyc.url+"/api/Mobile_FDR_Login/commonmobLogin?org=flexi6&locn=up&user=logout&lang=en_US&In_user_code="+sharedpreferences.getString("uc","")+"&In_user_pwd=&version_number="+Pojokyc.ver;
                        Log.e("URL",""+url);
                        HttpsTrustManager.allowAllSSL();
                        // Toast.makeText(getApplicationContext(), ""+url, Toast.LENGTH_SHORT).show();
                        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                                new com.android.volley.Response.Listener<JSONObject>()
                                {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // display response
                                        Log.e("Response", response.toString());
                                        Intent i = new Intent(getApplicationContext(), SingleLogin.class);
                                        startActivity(i);
                                        finish();
                                    }


                                },
                                new Response.ErrorListener()
                                {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(Landpage.this, "Error:"+error, Toast.LENGTH_LONG).show();

                                        Log.d("Error.Response", String.valueOf(error));
                                    }
                                }
                        );

// add it to the RequestQueue
                        getRequest.setRetryPolicy(new DefaultRetryPolicy(
                                900000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        VolleySingleton.getInstance(Landpage.this).addToRequestQueue(getRequest);

                    }
                });
                dialog.show();
                cls.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    dialog.dismiss();
                    }
                });
                r1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),Mpin.class);
                        startActivity(intent);
                    }
                });
                r2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),ChangeMpin.class);
                        startActivity(intent);
                    }
                });

                trsmm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isNetworkAvailable())
                        {
                            Cursor cursor = dbs.rawQuery("select * from membermapping where flag = '0'",null);
                            if(cursor.getCount()>0)
                            {
                                memcount = cursor.getCount();
                                if(cursor.moveToFirst())
                                {
                                    membermap(cursor.getString(cursor.getColumnIndexOrThrow("farmercode")));
                                    //memid.add(cursor.getString(cursor.getColumnIndexOrThrow("farmercode")));
                                }

                            }
                            else
                            {
                                alertdialog("No Data Available");
                            }
                        }
                        else
                        {
                            alertdialog("No Network Available");
                        }

                    }
                });


                trsgps.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isNetworkAvailable())
                        {
                            Cursor cursor = dbs.rawQuery("select * from gpsactivity where flag = '0'",null);
                            if(cursor.getCount()>0)
                            {
                                gpscount = cursor.getCount();
                                if(cursor.moveToFirst())
                                {
                                    String[] latlng = cursor.getString(2).split("/");
                                    gpsrid = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                                    savegps(cursor.getString(1),latlng[0],latlng[1],cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
                                }

                            }
                            else
                            {
                                alertdialog("No Data Available");
                            }
                           // savegps();
                        }
                        else {
                            alertdialog("No Network Available");
                        }
                    }
                });


                trspa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isNetworkAvailable()){
                            String selectQuery = "SELECT  * FROM saleentry where v17 = 0";


                            Cursor cursor = dbs_pa.rawQuery(selectQuery, null);

                            if(cursor.getCount()>0)
                            {progressLayout.setVisibility(View.VISIBLE);
//                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                offlinesavesaleentry();
                            }
                            else
                            {
                                String selectQuery2 = "SELECT  * FROM purchaseentry where v22 = 0";



                                Cursor cursor2 = dbs_pa.rawQuery(selectQuery2, null);

                                if(cursor2.getCount()>0)
                                {
                                    progressLayout.setVisibility(View.VISIBLE);
//                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                    offlinesavepurchaseentry();
                                }
                                else
                                {
                                    showErrorDialog("No Transaction Available");
                                }



                            }
                        }else{
                            showErrorDialog("No Internet Connections Available !");
                        }
                    }
                });

                trsfdr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isNetworkAvailable()) {

                            final SQLiteDatabase dbs = mydb.getWritableDatabase();
                            String selectQuery5 = "SELECT  * FROM farmerh where flag = 0";
                            Cursor cc = dbs.rawQuery(selectQuery5, null);

                            if (cc.getCount() == 0) {
                                final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("No Data Available")
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        // finish();
                                                        //rlsno=1;
                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                            } else {
                                offlinesave();
                                offc = cc.getCount();
                                Log.e("NULL", "" + cc.getCount());
                            }
                        } else {
                            final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("No Internet Available")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                    // finish();
                                                    //rlsno=1;
                                                }
                                            }
//set negative button

                                    )
                                    .show();
                        }
                    }
                });
                trsicd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                        dbs = mydb.getWritableDatabase();

                        String selectQuery5h = "SELECT  * FROM farmerh where flag = 0";
                        Cursor cc = dbs.rawQuery(selectQuery5h, null);

                        if(isNetworkAvailable()) {

                            if (cc.getCount() == 0) {
                                savetrs();
                            } else {
                                offlinesave();
                            }
                        }
                        else
                        {
                            final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("No Internet Connections Available")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked

                                                }
                                            }
//set negative button

                                    )
                                    .show();
                        }

                    }
                });
                rm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isNetworkAvailable()){
                            savemasterandfarmerlist();
                            save3();
                            save4();

                            save6();
                            save7();
                            callProductMasterAllProductJsonDetails();
                            serialnumberfetch_api();
                            dbs_pa.execSQL("delete from purchaseentry");
                            dbs_pa.execSQL("delete from saleentry");
                            dbs_pa.execSQL("delete from sno");
                            dbs.execSQL("delete from product");
                            dbs.execSQL("delete from supplierlist");
                            dbs.execSQL("delete from template");
                            dbs.execSQL("delete from lrplist");
                            dbs.execSQL("delete from inw");
                            dbs.execSQL("delete from oci");
                            final SQLiteDataBaseHandler dbpa = new SQLiteDataBaseHandler(Landpage.this);
                            SQLiteDatabase dbspa = dbpa.getWritableDatabase();
                            dbspa.execSQL("delete from byr");

                            JSONObject jsonParam2 = null;
                            try {

                                jsonParam2 = new JSONObject();

                                jsonParam2.put("orgnId",  sharedpreferences.getString("oc1",""));
                                jsonParam2.put("locnId",  "chennai");
                                jsonParam2.put("userId", "admin");
                                jsonParam2.put("localeId",  ".");
                                jsonParam2.put("instance", Pojokyc.instance);


                                Log.e("OUTPUTK",""+jsonParam2.toString());
                            }
                            catch(Exception e)
                            {
                                Log.e("OUTPUTK",""+e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest2temp = new JsonObjectRequest(Request.Method.POST,ApiUtils.TEST_URL_API+"FDR_Constent/FarmerConstenttemplatefetch",jsonParam2,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {

                                            try {


                                                JSONObject sysb = response.getJSONObject("context");
                                                Log.e("KKK", "" + response.toString());
                                                JSONArray castb = sysb.getJSONArray("tempalteContextDtl");
                                                for (int i = 0; i < castb.length(); i++) {
                                                    JSONObject actor = castb.getJSONObject(i);
                                                    String n1 = actor.getString("template_id");
                                                    String n2 = actor.getString("template_consent");
                                                    String n3 = actor.getString("effective_From");
                                                    String n4 = actor.getString("effective_to");
                                                    String n5 = actor.getString("lang_code");

                                                    mydb.inserttemplate(n1,n2,n3,n4,n5);

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
                            stringRequest2temp.setRetryPolicy(new DefaultRetryPolicy(
                                    80000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(Landpage.this).addToRequestQueue(stringRequest2temp);


                            JSONObject jsonParam = null;
                            try {

                                jsonParam = new JSONObject();

                                jsonParam.put("orgnId",  pawhsApplication.getPreferenceFromString(getApplicationContext(), ApiUtils.ORGN_CODE));
                                jsonParam.put("locnId",  pawhsApplication.getPreferenceFromString(getApplicationContext(), ApiUtils.LOCN_ID));
                                jsonParam.put("userId", pawhsApplication.getPreferenceFromString(getApplicationContext(), ApiUtils.USER_ID));
                                jsonParam.put("localeId",  pawhsApplication.getPreferenceFromString(getApplicationContext(), ApiUtils.LOCALE_ID));
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
                            HttpsTrustManager.allowAllSSL();
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

                                                    if(actor.getString("out_buyer_type").equalsIgnoreCase("ONE TIME BUYER")){

                                                    }
                                                    else{
                                                        dbpa.insertbyr(name,code,org);
                                                    }



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
                            VolleySingleton.getInstance(Landpage.this).addToRequestQueue(stringRequest2);

                            if(sharedpreferences.getString("rc","").equalsIgnoreCase("ROLE_SECONDARY_IC"))
                            {
                                final String url = Pojokyc.icdurl+"api/ICDMOBInward/IssueList?org="+sharedpreferences.getString("orgnlvlcode","")+"&locn="+Pojokyc.instance+"&user=admin&lang=en_US";
                                Log.e("URL",""+url);

// prepare the Request
                                HttpsTrustManager.allowAllSSL();
                                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                                        new Response.Listener<JSONObject>()
                                        {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                // display response
                                                try {

                                                    JSONArray cast = response.getJSONArray("issueDetails");
                                                    for (int i=0; i<cast.length(); i++) {



                                                        JSONObject actor = cast.getJSONObject(i);

                                                        String n1 = actor.getString("inward_no");
                                                        String n2 = actor.getString("product_code");
                                                        String n3 = actor.getString("product_name");
                                                        String n4 = actor.getString("issued_qty");


                                                        mydb.insertinw(n1,n2,n3,n4,"0");


                                                    }



                                                } catch (JSONException e) {

                                                }
                                                Log.d("Response", response.toString());
                                            }
                                        },
                                        new Response.ErrorListener()
                                        {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Log.d("Error.Response", String.valueOf(error));
                                            }
                                        }
                                );

// add it to the RequestQueue
                                getRequest.setRetryPolicy(new DefaultRetryPolicy(
                                        50000,
                                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                                VolleySingleton.getInstance(Landpage.this).addToRequestQueue(getRequest);
                            }
                            RequestQueue queue = Volley.newRequestQueue(Landpage.this);
                            final String url = Pojokyc.icdurl+"api/ICDMOBProduct/ICD_MOBILE_Product?org="+sharedpreferences.getString("orgnlvlcode","")+"&locn="+Pojokyc.instance+"&user=admin&lang=en_US";
                            Log.e("URL",""+url);

// prepare the Request
                            HttpsTrustManager.allowAllSSL();
                           JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                                    new Response.Listener<JSONObject>()
                                    {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            // display response
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("Detail");
                                                for (int i=0; i<cast.length(); i++) {



                                                    JSONObject actor = cast.getJSONObject(i);

                                                    String n1 = actor.getString("In_product_code");
                                                    String n2 = actor.getString("In_product_name");
                                                    String n3 = actor.getString("In_productcategory_code");
                                                    String n4 = actor.getString("In_productcategory_desc");
                                                    String n5 = actor.getString("In_productsubcategory_code");
                                                    String n6 = actor.getString("In_productsubcategory_desc");
                                                    String n7 = actor.getString("In_uomtype_code");
                                                    String n8 = actor.getString("In_uomtype_desc");
                                                    String n9 = actor.getString("In_hsn_code");
                                                    String n10 = actor.getString("In_hsn_desc");
                                                    String n11 = actor.getString("In_base_price");
                                                    String n12 = actor.getString("In_current_qty");
                                                    Log.e("CHK",""+actor.getString("In_cgst"));
                                                    String n13 = actor.getString("In_cgst");

                                                    String n14 = actor.getString("In_sgst");
                                                    String n15 = actor.getString("In_igst");
                                                    String n16 = actor.getString("In_orgn_code");
                                                    String n17 = actor.getString("In_ic_code");

                                                    String n18 = actor.getString("In_value_addproduct_verified");



                                                    mydb.insertContact(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17,n18);


                                                }



                                            } catch (JSONException e) {

                                            }
                                            Log.d("Response", response.toString());
                                        }
                                    },
                                    new Response.ErrorListener()
                                    {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Error.Response", String.valueOf(error));
                                        }
                                    }
                            );

// add it to the RequestQueue
                            getRequest.setRetryPolicy(new DefaultRetryPolicy(
                                    50000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(Landpage.this).addToRequestQueue(getRequest);







                            final String url2 = Pojokyc.icdurl+"api/ICDMOBList/ICD_Supplier_list?org="+sharedpreferences.getString("orgnlvlcode","")+"&locn="+Pojokyc.instance+"&user=admin&lang=en_US&filterby_option=all&filterby_code=.&filterby_fromvalue=.&filterby_tovalue=.";

// prepare the Request
                            Log.e("URL",""+url2);

                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest getRequest2 = new JsonObjectRequest(Request.Method.GET, url2, null,
                                    new Response.Listener<JSONObject>()
                                    {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            // display response
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("List");
                                                for (int i=0; i<cast.length(); i++) {



                                                    JSONObject actor = cast.getJSONObject(i);
                                                    String n1 = actor.getString("In_supplier_code");
                                                    String n2 = actor.getString("In_supplier_name");
                                                    String n3 = actor.getString("In_pan_no");
                                                    String n4 = actor.getString("In_supplier_state_code");
                                                    String n5 = actor.getString("In_supplier_state_desc");
                                                    String n6 = actor.getString("In_supplier_location");

                                                    String n7 = actor.getString("In_ic_code");



                                                    mydb.insertsupplier(n1,n2,n3,n4,n5,n6,n7);


                                                }



                                            } catch (JSONException e) {

                                            }
                                            Log.d("Response", response.toString());
                                        }
                                    },
                                    new Response.ErrorListener()
                                    {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Error.Response", String.valueOf(error));
                                        }
                                    }
                            );

// add it to the RequestQueue
                            getRequest2.setRetryPolicy(new DefaultRetryPolicy(
                                    50000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(Landpage.this).addToRequestQueue(getRequest2);


                            final String url2l = Pojokyc.icdurl+"api/ICDMOBStockadj/StockadjLRP?org=flexi&locn="+Pojokyc.instance+"&user=admin&lang=en_US&In_orgn_code="+sharedpreferences.getString("orgnlvlcode","");

// prepare the Request
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest getRequest2l = new JsonObjectRequest(Request.Method.GET, url2l, null,
                                    new Response.Listener<JSONObject>()
                                    {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            // display response
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("LRPList");
                                                for (int i=0; i<cast.length(); i++) {



                                                    JSONObject actor = cast.getJSONObject(i);
                                                    String n1 = actor.getString("In_lrp_name");



                                                    mydb.insertlrplist(n1,"");


                                                }



                                            } catch (JSONException e) {

                                            }
                                            Log.d("Response", response.toString());
                                        }
                                    },
                                    new Response.ErrorListener()
                                    {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Error.Response", String.valueOf(error));
                                        }
                                    }
                            );

// add it to the RequestQueue
                            getRequest2.setRetryPolicy(new DefaultRetryPolicy(
                                    50000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            //      VolleySingleton.getInstance(Landpage.this).addToRequestQueue(getRequest2l);






                            final String url4 = Pojokyc.icdurl+"api/ICDMOBList/allmasterlist?org=QCD_UN_STATE&locn="+Pojokyc.instance+"&user=admin&lang=en_US&parent_code=QCD_UN_STATE";

// prepare the Request
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest getRequest4 = new JsonObjectRequest(Request.Method.GET, url4, null,
                                    new Response.Listener<JSONObject>()
                                    {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            // display response
                                            Log.e("Response", response.toString());
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("detail");
                                                for (int i=0; i<cast.length(); i++) {



                                                    JSONObject actor = cast.getJSONObject(i);
                                                    String n1 = actor.getString("out_master_description");
                                                    String n2 = actor.getString("out_master_code");



                                                    mydb.istate(n1,n2);


                                                }



                                            } catch (JSONException e) {

                                            }

                                        }
                                    },
                                    new Response.ErrorListener()
                                    {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("Response", error.toString());
                                            Log.d("Error.Response", String.valueOf(error));
                                        }
                                    }
                            );

// add it to the RequestQueue
                            getRequest4.setRetryPolicy(new DefaultRetryPolicy(
                                    90000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(Landpage.this).addToRequestQueue(getRequest4);


                            final String url4s = Pojokyc.icdurl+"api/ICDMOBList/allmasterlist?org=QCD_UN_STATE&locn="+Pojokyc.instance+"&user=admin&lang=en_US&parent_code=QCD_ICD_OTHERCOST";

// prepare the Request
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest getRequest4s = new JsonObjectRequest(Request.Method.GET, url4s, null,
                                    new Response.Listener<JSONObject>()
                                    {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            // display response
                                            Log.e("Responseoci", response.toString());
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("detail");
                                                for (int i=0; i<cast.length(); i++) {



                                                    JSONObject actor = cast.getJSONObject(i);
                                                    String n1 = actor.getString("out_parent_code");
                                                    String n2 = actor.getString("out_master_code");
                                                    String n3 = actor.getString("out_master_description");



                                                    mydb.insertoci(n1,n2,n3,"0");
                                                    Log.e("TABLEOCE","YES");


                                                }



                                            } catch (JSONException e) {

                                            }

                                        }
                                    },
                                    new Response.ErrorListener()
                                    {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("Response", error.toString());
                                            Log.d("Error.Response", String.valueOf(error));
                                        }
                                    }
                            );

// add it to the RequestQueue
                            getRequest4s.setRetryPolicy(new DefaultRetryPolicy(
                                    90000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(Landpage.this).addToRequestQueue(getRequest4s);
                            String oc1[] = sharedpreferences.getString("oc","").split("/");
                            final String url5 = Pojokyc.icdurl+"api/ICDMOBList/geticdtransaction?org="+oc1[0]+"&locn="+Pojokyc.instance+"&user=dsfdsf&lang=en_US";
                            Log.e("URL",""+url5);

// prepare the Request
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest getRequest5 = new JsonObjectRequest(Request.Method.GET, url5, null,
                                    new Response.Listener<JSONObject>()
                                    {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            // display response
                                            Log.e("Response", response.toString());
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONObject obj2 = obj.getJSONObject("Header");

                                                //String inrid = obj2.getString("IOU_adjustment_rowid");

                                                SharedPreferences.Editor editor = sharedpreferences.edit();

                                                editor.putString("inwardno", obj2.getString("In_inward_no"));
                                                editor.putString("invoiceno", obj2.getString("In_invoice_no"));
                                                editor.putString("sano", obj2.getString("In_adjustment_no"));


                                                editor.commit();

                                            } catch (JSONException e) {

                                            }

                                        }
                                    },
                                    new Response.ErrorListener()
                                    {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("Response", error.toString());
                                            Log.d("Error.Response", String.valueOf(error));
                                        }
                                    }
                            );

// add it to the RequestQueue
                            getRequest5.setRetryPolicy(new DefaultRetryPolicy(
                                    90000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(Landpage.this).addToRequestQueue(getRequest5);
                        }
                        else{
                            showErrorDialog("No Internet Connection Available!");
                        }
                    }
                });
                // set the custom dialog components - text, image and button
            }
        });

//        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//
//// Define a listener that responds to location updates
//        LocationListener locationListener = new LocationListener() {
//            public void onLocationChanged(Location location) {
//                // Called when a new location is found by the network location provider.
//                // makeUseOfNewLocation(location);
//                Log.e("LOCAL GPS",""+location.getLatitude()+"/"+location.getLongitude());
//            }
//
//            public void onStatusChanged(String provider, int status, Bundle extras) {}
//
//            public void onProviderEnabled(String provider) {}
//
//            public void onProviderDisabled(String provider) {}
//        };
//
//// Register the listener with the Location Manager to receive location updates
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        pawhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        icd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(getApplicationContext(), cdfi.fintantra.meity.Loginicd.class);
                Intent i = new Intent(getApplicationContext(), Landpage.class);
                startActivity(i);
            }
        });


        fdr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(getApplicationContext(), MainActivity.class);
                Intent i = new Intent(getApplicationContext(), Landpage.class);
                startActivity(i);
            }
        });
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(getApplicationContext(), MainActivityp.class);
                Intent i = new Intent(getApplicationContext(), ProductionTab.class);
                startActivity(i);
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
        String currentDateandTime = sdf.format(new Date());

        Date date = null;
        try {
            date = sdf.parse(currentDateandTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Log.e("Time here1 ",""+calendar.getTime());
        calendar.add(Calendar.HOUR, 1);

        Log.e("Time here2 ",""+calendar.getTime());
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("offtime",calendar.getTime().toString());

        editor.commit();
       // save();
    //checkForAppUpdate();

}


    @Override
    protected void onResume() {
        super.onResume();
       // checkNewAppVersionState();
    }

//    @Override
//    public void onActivityResult(int requestCode, final int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//
//        switch (requestCode) {
//
//            case REQ_CODE_VERSION_UPDATE:
//                if (resultCode != RESULT_OK) { //RESULT_OK / RESULT_CANCELED / RESULT_IN_APP_UPDATE_FAILED
//                   // Log.d("Update flow failed! Result code: ",+ resultCode);
//                    // If the update is cancelled or fails,
//                    // you can request to start the update again.
//                    unregisterInstallStateUpdListener();
//                }
//
//                break;
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        unregisterInstallStateUpdListener();
//        super.onDestroy();
//    }
//
//
//    private void checkForAppUpdate() {
//        // Creates instance of the manager.
//        appUpdateManager = AppUpdateManagerFactory.create(this);
//
//        // Returns an intent object that you use to check for an update.
//        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
//
//        // Create a listener to track request state updates.
//        installStateUpdatedListener = new InstallStateUpdatedListener() {
//            @Override
//            public void onStateUpdate(InstallState installState) {
//                // Show module progress, log state, or install the update.
//                if (installState.installStatus() == InstallStatus.DOWNLOADED)
//                    // After the update is downloaded, show a notification
//                    // and request user confirmation to restart the app.
//                    popupSnackbarForCompleteUpdateAndUnregister();
//            }
//        };
//
//        // Checks that the platform will allow the specified type of update.
//        appUpdateInfoTask.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
//            @Override
//            public void onSuccess(AppUpdateInfo appUpdateInfo) {
//                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
//                    // Request the update.
//                    if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
//
//                        // Before starting an update, register a listener for updates.
//                        appUpdateManager.registerListener(installStateUpdatedListener);
//                        // Start an update.
//                        Landpage.this.startAppUpdateFlexible(appUpdateInfo);
//                    } else if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
//                        // Start an update.
//                        Landpage.this.startAppUpdateImmediate(appUpdateInfo);
//                    }
//                }
//            }
//        });
//    }
//
//    private void startAppUpdateImmediate(AppUpdateInfo appUpdateInfo) {
//        try {
//            appUpdateManager.startUpdateFlowForResult(
//                    appUpdateInfo,
//                    AppUpdateType.IMMEDIATE,
//                    // The current activity making the update request.
//                    this,
//                    // Include a request code to later monitor this update request.
//                    Landpage.REQ_CODE_VERSION_UPDATE);
//        } catch (IntentSender.SendIntentException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void startAppUpdateFlexible(AppUpdateInfo appUpdateInfo) {
//        try {
//            appUpdateManager.startUpdateFlowForResult(
//                    appUpdateInfo,
//                    AppUpdateType.FLEXIBLE,
//                    // The current activity making the update request.
//                    this,
//                    // Include a request code to later monitor this update request.
//                    Landpage.REQ_CODE_VERSION_UPDATE);
//        } catch (IntentSender.SendIntentException e) {
//            e.printStackTrace();
//            unregisterInstallStateUpdListener();
//        }
//    }
//
//    /**
//     * Displays the snackbar notification and call to action.
//     * Needed only for Flexible app update
//     */
//    private void popupSnackbarForCompleteUpdateAndUnregister() {
//        Snackbar snackbar =
//                Snackbar.make(findViewById(android.R.id.content), getString(R.string.update_downloaded), Snackbar.LENGTH_INDEFINITE);
//        snackbar.setAction(R.string.restart, new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                appUpdateManager.completeUpdate();
//            }
//        });
//        snackbar.setActionTextColor(getResources().getColor(R.color.black));
//        snackbar.show();
//
//        unregisterInstallStateUpdListener();
//    }
//
//    /**
//     * Checks that the update is not stalled during 'onResume()'.
//     * However, you should execute this check at all app entry points.
//     */
//    private void checkNewAppVersionState() {
//        appUpdateManager
//                .getAppUpdateInfo()
//                .addOnSuccessListener(
//                        new OnSuccessListener<AppUpdateInfo>() {
//                            @Override
//                            public void onSuccess(AppUpdateInfo appUpdateInfo) {
//                                //FLEXIBLE:
//                                // If the update is downloaded but not installed,
//                                // notify the user to complete the update.
//                                if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
//                                    Landpage.this.popupSnackbarForCompleteUpdateAndUnregister();
//                                }
//
//                                //IMMEDIATE:
//                                if (appUpdateInfo.updateAvailability()
//                                        == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
//                                    // If an in-app update is already running, resume the update.
//                                    Landpage.this.startAppUpdateImmediate(appUpdateInfo);
//                                }
//                            }
//                        });
//
//    }
//
//    /**
//     * Needed only for FLEXIBLE update
//     */
//    private void unregisterInstallStateUpdListener() {
//        if (appUpdateManager != null && installStateUpdatedListener != null)
//            appUpdateManager.unregisterListener(installStateUpdatedListener);
//    }



    public void savemasterandfarmerlist()
    {

        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Downloading...");
      //  pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pdialog.setIndeterminate(true);
        pdialog.setMax(100);
        pdialog.setMessage("");
        pdialog.setMessage("Fetching...");
        //Set the current progress to zero
       // pdialog.setProgress(0);
        pdialog.show();



        SQLiteDatabase dbs = mydb.getWritableDatabase();
        final SQLiteDataBaseHandler dbpa = new SQLiteDataBaseHandler(Landpage.this);
        SQLiteDatabase dbspa = dbpa.getWritableDatabase();
        // dbs.execSQL("delete from masterl");
         dbs.execSQL("delete from mercid");
        dbs.execSQL("delete from aepsmercid");
        try {

            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", sharedpreferences.getString("oc1",""));
            userd.put("locnId", sharedpreferences.getString("dateforsync",""));
            userd.put("userId", "admin");
            userd.put("localeId", "en_US");
            userd.put("screen_Id", "FARMERANDPAWHS");
            userd.put("instance", Pojokyc.instance);

            Log.e("OUTPUT", "" + userd.toString());

        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/Mobile_FDR/Farmermaster", userd,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC2", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            castemaster = obj.getJSONArray("detail");
                            JSONArray cast3 = obj.getJSONArray("varityDt");
                            for (int i = 0; i < cast3.length(); i++) {
                                Log.e("TableVS", "" + "Hi");

                                JSONObject actor = cast3.getJSONObject(i);


                                String n1 = actor.getString("out_master_rowid");
                                String n2 = actor.getString("out_master_code");
                                String n3 = actor.getString("out_master_desc");



                                Log.e("TableVS", "" + n3);

                                dbpa.insertvstatus(n1, n2, n3);


                            }

                            JSONArray cast2 = obj.getJSONArray("qualityDt");
                            for (int i = 0; i < cast2.length(); i++) {


                                JSONObject actor = cast2.getJSONObject(i);


                                String n1 = actor.getString("out_qlt_rowid");
                                String n2 = actor.getString("out_qlt_code");
                                String n3 = actor.getString("out_qlt_name");
                                String n4 = actor.getString("out_QualityRange");


                                Log.e("TableQ", "" + n3);

                                dbpa.insertqparest(n1, n2, n3, n4);


                            }



                            JSONArray cast4 = obj.getJSONArray("vehicleDt");
                            for (int i = 0; i < cast4.length(); i++) {


                                JSONObject actor = cast4.getJSONObject(i);


                                String n1 = actor.getString("out_master_rowid");
                                String n2 = actor.getString("out_master_code");
                                String n3 = actor.getString("out_master_desc");



                                Log.e("TableVT", "" + n3);

                                dbpa.insertvtype(n1, n2, n3);


                            }

                            JSONArray cast5 = obj.getJSONArray("destinationDt");
                            for (int i = 0; i < cast5.length(); i++) {


                                JSONObject actor = cast5.getJSONObject(i);


                                String n1 = actor.getString("out_master_rowid");
                                String n2 = actor.getString("out_master_code");
                                String n3 = actor.getString("out_master_desc");



                                Log.e("TableDS", "" + n3);

                                dbpa.insertdstn(n1, n2, n3);


                            }


                            JSONArray cast6 = obj.getJSONArray("paytmMasterDt");
                            for (int i = 0; i < cast6.length(); i++) {


                                JSONObject actor = cast6.getJSONObject(i);


                                String n1 = actor.getString("out_paytm_masid");
                                String n2 = actor.getString("out_paytm_fpocode");
                                String n3 = actor.getString("out_paytm_type");
                                String n4 = actor.getString("out_paytm_merchantId");
                                String n5 = actor.getString("out_paytm_tid");



                                Log.e("TableDS", "" + n3);

                                mydb.insertMercID(n1, n2, n3,n4,n5);


                            }

                            JSONArray cast7 = obj.getJSONArray("aepsMasterDt");
                            for (int i = 0; i < cast7.length(); i++) {


                                JSONObject actor = cast7.getJSONObject(i);


                                String n1 = actor.getString("out_AEPS_masid");
                                String n2 = actor.getString("out_AEPS_fpocode");
                                String n3 = actor.getString("out_AEPS_type");
                                String n4 = actor.getString("out_AEPS_merchantId");
                                String n5 = actor.getString("out_AEPS_tid");


                                Log.e("TableDS", "" + n3);

                                mydb.insertAepsMercID(n1, n2, n3, n4, n5);
                            }
                          save2();

                            // pdialog.dismiss();


                        } catch (JSONException e) {

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
    public void save2() {
        SQLiteDatabase dbs = mydb.getWritableDatabase();
        // dbs.execSQL("delete from bankm");


        try {


            DecimalFormat amountFormate = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", "Flexi");
            userd.put("locnId", "Chennai");
            userd.put("userId", "admin");
            userd.put("localeId", "en_US");
            userd.put("instance", Pojokyc.instance);
            userd.put("FilterBy_Option", "ALL");
            userd.put("FilterBy_Code", sharedpreferences.getString("dateforsync",""));
            userd.put("FilterBy_FromValue", ".");
            userd.put("FilterBy_ToValue", ".");

            Log.e("OUTPUTbank", "" + userd.toString());

        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

        //http://169.38.77.190:101/api/Mobile_FDR/Farmerbank
        //15.206.21.248:27/Farmerbank
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/Mobile_FDR/Farmerbank", userd,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response.length());
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            castbank = obj.getJSONArray("bankDtl");
                            //  new SomeTask().execute();

                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    //TODO your background code

                                    SQLiteDatabase dbs = mydb.getWritableDatabase();
                                    // dbs.execSQL("delete from farlist");
                                    try {


                                        DecimalFormat amountFormate = new DecimalFormat("############.##");
                                        amountFormate.setMinimumFractionDigits(2);
                                        amountFormate.setMaximumFractionDigits(2);

                                        Date cc = Calendar.getInstance().getTime();
                                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                                        //userd = new JSONObject();

                                        userd = new JSONObject();
                                        userd.put("orgnId", sharedpreferences.getString("oc1",""));
                                        userd.put("locnId", "Chennai");
                                        userd.put("userId", "admin");
                                        userd.put("localeId", "en_US");
                                        userd.put("instance", Pojokyc.instance);
                                        userd.put("FilterBy_Option", "ALL");
                                        userd.put("FilterBy_Code", sharedpreferences.getString("dateforsync",""));
                                        userd.put("FilterBy_FromValue", ".");
                                        userd.put("FilterBy_ToValue", ".");

                                        Log.e("OUTPUT", "" + userd.toString());

                                    } catch (Exception e) {
                                        Log.e("OUTPUT", "" + e.getMessage());
                                    }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();


                                    HttpsTrustManager.allowAllSSL();
                                    JsonObjectRequest stringRequest2 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/Mobile_FDR_FList/FarmerList", userd,
                                            new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    Log.e("CCCCFARMER", "" + response);
                                                    try {
                                                        JSONObject obj = response.getJSONObject("context");
                                                        castfar = obj.getJSONArray("list");
                                                        new SomeTask().execute();

                                                        //  pdialog.dismiss();




                                                    } catch (JSONException e) {

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
                                    stringRequest2.setRetryPolicy(new DefaultRetryPolicy(
                                            4500000,
                                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                                    VolleySingleton.getInstance(Landpage.this).addToRequestQueue(stringRequest2);
                                }
                            });
                        } catch (JSONException e) {

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
    class SomeTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            // super.onPreExecute();

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            final SQLiteDatabase dbs = mydb.getWritableDatabase();
            try {
                for (int i = 0; i < castbank.length(); i++) {


                    JSONObject actor = castbank.getJSONObject(i);
                    String n1 = actor.getString("bank_rowid");
                    String n2 = actor.getString("bank_code");
                    String n3 = actor.getString("bank_name");
                    String n4 = actor.getString("branch_name");
                    String n5 = actor.getString("ifsc_code");
                    String n6 = actor.getString("status_desc");
                    Log.e("Table", "" + n1);
                    String query = "Select * from bankm where bank_rowid = '" + actor.getString("bank_rowid") + "'";
                    Cursor cursor = dbs.rawQuery(query, null);
                    final int finalI = i;
                    if(sharedpreferences.getString("dateforsync","").equalsIgnoreCase(".")) {
                        try{
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                   // pdialog.setMessage("Fetching Bank Data..."+ finalI +"/"+castbank.length());

                                    // Stuff that updates the UI

                                }
                            });
                            //  mydb.insertbankm(n1, n2, n3, n4, n5, n6);
                            if (cursor.getCount() > 0) {

                            } else {
                                mydb.insertbankm(n1, n2, n3, n4, n5, n6);
                            }
                        }finally {
                            cursor.close();
                        }
                    }
                    else {

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                pdialog.setMessage("Fetching Bank Data..."+ finalI +"/"+castbank.length());

                                // Stuff that updates the UI

                            }
                        });
                        try {

                            if (cursor.getCount() > 0) {
                                dbs.execSQL("Delete from bankm where bank_rowid = '" + actor.getString("bank_rowid") + "'");
                                mydb.insertbankm(n1, n2, n3, n4, n5, n6);
                            } else {
                                mydb.insertbankm(n1, n2, n3, n4, n5, n6);
                            }
                        } finally {
                            cursor.close();
                        }
                    }





                }

                for (int j = 0; j < castfar.length(); j++) {
                    JSONObject actor = castfar.getJSONObject(j);


                    String n1 = actor.getString("farmer_rowid");
                    String n2 = actor.getString("farmer_code");
                    String n3 = actor.getString("farmer_name");
                    String n4 = actor.getString("farmer_village_desc");
                    String n5 = actor.getString("farmer_panchayat_desc");
                    String n6 = actor.getString("farmer_taluk_desc");
                    String n7 = actor.getString("farmer_dist_desc");
                    String n8 = actor.getString("father_name");
                    String n9 = actor.getString("farmer_surname");
                    String n10 = "NA";
                    String n11 = actor.getString("verified_flag");
                    String n12 = actor.getString("farmer_dob");
                    String n13 = actor.getString("farmer_state_desc");
                    String n14 = actor.getString("farmer_village");
                    String n15 = actor.getString("farmer_state");
                    String n16 = actor.getString("contact_No");
                    String n17 = actor.getString("ic_code");
                    String n18 = actor.getString("farmer_dist");
                    String n19 = actor.getString("farmer_taluk");
                    String n20 = actor.getString("farmer_panchayat");
                    String n21 = actor.getString("farmer");
                    String n22 = actor.getString("fpoorgn_code");
                    String n23 = actor.getString("fpomember_code");



                    //String[] dob2 = n12.split("/");
                    //Log.e("DATE",""+dob2[2]+"/"+dob2[1]+"/"+dob2[1]);
                    String query = "Select * from farlist where fc = '"+n2+"'";
                    String query2 = "Select * from customerlist where farmer_code = '"+n2+"'";
                    String query3 = "Select * from FARMER where FarmerCode = '"+n2+"'";
                    Cursor cursor = dbs.rawQuery(query , null);
                    Cursor cursor2 = dbs.rawQuery(query2 , null);

                    final SQLiteDataBaseHandler dbpa = new SQLiteDataBaseHandler(Landpage.this);
                    SQLiteDatabase dbspa = dbpa.getWritableDatabase();
                    Cursor cursor3 = dbspa.rawQuery(query3 , null);

                    final int finalJ = j;
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            pdialog.setMessage("Fetching Farmer Data..."+ finalJ +"/"+castfar.length());

                            // Stuff that updates the UI

                        }
                    });
                    if(sharedpreferences.getString("dateforsync","").equalsIgnoreCase(".")) {

                        mydb.inserfarlist(n1, n2, n3, n8, n4, n5, n6, n7, n9, n16, n11, n12, "1",n22,n23,n15);
                        mydb.insertcustomer(n2,n3,n4,n13,n13,n14,n15,"M",n16,n17,n9,n8);


                            FormerDao formerDao = new FormerDao(1, n1,
                                    n2, n21, n8,
                                    n3, n18, n7,
                                    n19, n6, n20,
                                    n5, n14, n4);
                            dbpa.addAllFarmerDetails(formerDao);


                    }
                    else {
                        try {
                            if (cursor.getCount() > 0) {
                                dbs.execSQL("Delete from farlist where fc = '" + n2 + "'");
                                mydb.inserfarlist(n1, n2, n3, n8, n4, n5, n6, n7, n9, n16, n11, n12, "1",n22,n23,n15);
                                mydb.membermapping("", "", "", "0");
                            } else {
                                mydb.inserfarlist(n1, n2, n3, n8, n4, n5, n6, n7, n9, n16, n11, n12, "1",n22,n23,n15);
                                mydb.membermapping("", "", "", "0");
                            }
                        } finally {
                            cursor.close();

                        }
                        try {
                            if (cursor2.getCount() > 0) {
                                dbs.execSQL("Delete from customerlist where farmer_code = '" + n2 + "'");
                                mydb.insertcustomer(n2,n3,n4,n13,n13,n14,n15,"M",n16,n17,n9,n8);
                            } else {
                                mydb.insertcustomer(n2,n3,n4,n13,n13,n14,n15,"M",n16,n17,n9,n8);
                            }
                        } finally {
                            cursor2.close();

                        }
                        try
                        {
                        if (cursor3.getCount() > 0) {
                            dbspa.execSQL("Delete from FARMER where FarmerCode = '" + n2 + "'");
                            FormerDao formerDao = new FormerDao(1, n1,
                                    n2, n21, n8,
                                    n3, n18, n7,
                                    n19, n6, n20,
                                    n5, n14, n4);
                            dbpa.addAllFarmerDetails(formerDao);
                        } else {
                            FormerDao formerDao = new FormerDao(1, n1,
                                    n2, n21, n8,
                                    n3, n18, n7,
                                    n19, n6, n20,
                                    n5, n14, n4);
                            dbpa.addAllFarmerDetails(formerDao);
                        }
                    } finally {
                        cursor3.close();

                    }
                    }



                }

                for (int i = 0; i < castemaster.length(); i++) {


                    JSONObject actor = castemaster.getJSONObject(i);


                    String n1 = actor.getString("out_master_rowid");
                    String n2 = actor.getString("out_parent_code");
                    String n3 = actor.getString("out_master_code");
                    String n4 = actor.getString("out_master_description");
                    String n5 = actor.getString("out_depend_code");
                    String n6 = actor.getString("out_depend_desc");
                    String n7 = actor.getString("out_locallang_flag");
                    String n8 = actor.getString("out_status_code");
                    String n9 = actor.getString("out_status_desc");
                    final int finalI = i;

                    Log.e("Table2", "" + n3);
                    Cursor cr1 = dbs.rawQuery("select * from masterl where out_master_rowid='"+n1+"' and out_locallang_flag = '"+n7+"'",null);
                    if(sharedpreferences.getString("dateforsync","").equalsIgnoreCase(".")) {
                        try{
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                 //   pdialog.setMessage("Fetching Master QCD's..."+ finalI +"/"+castemaster.length());

                                    // Stuff that updates the UI

                                }
                            });
                            //mydb.insertmasterl(n1, n2, n3, n4, n5, n6, n7, n8, n9);
                            if (cr1.getCount() > 0) {


                            }
                            else
                            {
                                mydb.insertmasterl(n1, n2, n3, n4, n5, n6, n7, n8, n9);
                            }

                        }finally {
                            cr1.close();
                        }
                    }
                    else
                    {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                pdialog.setMessage("Fetching Master QCD's..."+ finalI +"/"+castemaster.length());

                                // Stuff that updates the UI

                            }
                        });

                        try {
                            if (cr1.getCount() > 0) {
                                dbs.execSQL("Delete from masterl where out_master_rowid='"+n1+"' and out_locallang_flag = '"+n7+"'");
                                mydb.insertmasterl(n1, n2, n3, n4, n5, n6, n7, n8, n9);
                            } else {
                                mydb.insertmasterl(n1, n2, n3, n4, n5, n6, n7, n8, n9);
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

            editor.putString("dateforsync", getDate1());

            editor.commit();

            final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                    .setTitle("Success!")
//set message
                    .setMessage("Master Sync Completed!")
//set positive button
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //set what would happen when positive button is clicked

                                }
                            }
//set negative button

                    )
                    .show();

            // callLotnoListDetailsOfApproveWare("WareHouse");

        }
    }
    public void save3() {
        SQLiteDatabase dbs = mydb.getWritableDatabase();
        dbs.execSQL("delete from tab");

        try {


            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", "Flexi");
            userd.put("locnId", "Chennai");
            userd.put("userId", "admin");
            userd.put("localeId", "en_US");
            userd.put("instance", Pojokyc.instance);
            userd.put("FilterBy_Option", "ALL");
            userd.put("FilterBy_Code", ".");
            userd.put("FilterBy_FromValue", ".");
            userd.put("FilterBy_ToValue", ".");

            Log.e("OUTPUT", "" + userd.toString());

        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();


        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/Mobile_FDR/FarmerProfileDetail", userd,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {

                            Log.e("Res", "" + response);
                            JSONObject obj = response.getJSONObject("context");
                            JSONArray cast = obj.getJSONArray("farmerProfileDetails");

                            //


                            JSONObject actor = cast.getJSONObject(0);
                            String n1 = actor.getString("dynamic_list");
                            Log.e("Res", "" + cast.toString());
                            JSONArray jsonArray = new JSONArray(n1);
                            String[] strArr = new String[jsonArray.length()];

                            for (int j = 0; j < jsonArray.length(); j++) {
                                strArr[j] = jsonArray.getString(j);

                                JSONObject jsonObj = new JSONObject(strArr[j]);

                                Log.e("Res2", "" + jsonObj.getString("tab_name"));
                                mydb.inserttab(jsonObj.getString("tab_name"));

                            }
                            //  pdialog.dismiss();


                        } catch (JSONException e) {

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

    public void save4() {

        SQLiteDatabase dbs = mydb.getWritableDatabase();
        dbs.execSQL("delete from tablist");
        try {


            DecimalFormat amountFormate = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", "Flexi");
            userd.put("locnId", "Chennai");
            userd.put("userId", "admin");
            userd.put("localeId", "en_US");
            userd.put("instance", Pojokyc.instance);
            userd.put("FilterBy_Option", "ALL");
            userd.put("FilterBy_Code", ".");
            userd.put("FilterBy_FromValue", ".");
            userd.put("FilterBy_ToValue", ".");

            Log.e("OUTPUT", "" + userd.toString());

        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();


        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/Mobile_FDR/FarmerProfileDetail", userd,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONArray cast = obj.getJSONArray("farmerProfileDetails");
                            for (int i = 1; i < cast.length() + 1; i++) {


                                JSONObject actor = cast.getJSONObject(i);

                                //Log.e("GETTA",""+actor.getString("tab_name"));

                                String n1 = actor.getString("dynamic_list");
                                // Log.e("Res",""+n1.toString());
                                JSONArray jsonArray = new JSONArray(n1);
                                String[] strArr = new String[jsonArray.length()];
                                // Toast.makeText(FarmerReg.this, ""+jsonArray.length(), Toast.LENGTH_SHORT).show();

                                for (int j = 0; j < jsonArray.length(); j++) {
                                    strArr[j] = jsonArray.getString(j);
                                    tfield.clear();
                                    JSONObject jsonObj = new JSONObject(strArr[j]);

                                    //Log.e("GET2",""+jsonObj.getString("columnname"));

                                    JSONArray jsonArray2 = new JSONArray(jsonObj.getString("columnname"));
                                    //  Toast.makeText(FarmerReg.this, ""+jsonArray2.length(), Toast.LENGTH_SHORT).show();
                                    String[] strArr2 = new String[jsonArray2.length()];
                                    for (int k = 0; k < jsonArray2.length(); k++) {
                                        strArr2[k] = jsonArray2.getString(k);

                                        JSONObject jsonObj2 = new JSONObject(strArr2[k]);
                                        // Log.e("GETT",""+jsonObj2.getString("cname"));
                                        //tfield.add(jsonObj2.getString("cname"));

                                        JSONArray jsonArray3 = new JSONArray(jsonObj.getString("columnvalue"));
                                        String[] strArr3 = new String[jsonArray3.length()];
                                        for (int a = 0; a < jsonArray3.length(); a++) {
                                            strArr3[a] = jsonArray3.getString(a);
                                            JSONObject jsonObj3 = new JSONObject(strArr3[a]);
                                            String s = strArr2[k];
                                            // Log.e("GET4",""+s);

                                            //  Log.e("GET1",""+jsonObj2.getString("cname"));
                                            try {
                                                String as = jsonObj2.getString("cname") + "DIV" + "[" + jsonObj3.getString(jsonObj2.getString("cname")) + "]:";

                                                tfield.add(as);
                                                //Log.e("GETT",""+jsonObj2.getString("cname")+"//"+jsonObj3.getString(jsonObj2.getString("cname")));
                                                if (jsonObj3.getString(jsonObj2.getString("cname")).equalsIgnoreCase("null")) {
                                                    //  Log.e("NULl","++"+jsonObj3.getString(jsonObj2.getString("cname")));

                                                } else {


                                                    // [typeDIV[ID,Address,DOB,Date of joining]:subtypeDIV[Aadhar,Pan Card,Voterid,t1,t2]:document noDIV[0]:valid till dateDIV[0]]
                                                }


                                            } catch (Exception e) {
                                                //Log.e("GETT",""+jsonObj2.getString("cname")+"// 0");

                                            }

                                            // tfield.add(jsonObj2.getString("cname"));
                                        }


                                    }
                                    //Log.e("GET4",""+ Arrays.toString(new ArrayList[]{tfield}));

                                }

                                Log.e("NULl", "++" + actor.getString("tab_name") + "//" + tfield.toString());
                                mydb.inserttablist(actor.getString("tab_name"), tfield.toString());

                            }


                        } catch (JSONException e) {

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
    public void save6() {

        SQLiteDatabase dbs = mydb.getWritableDatabase();
        //  dbs.execSQL("delete from masterla");
        try {


            DecimalFormat amountFormate = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", "Flexi");
            userd.put("locnId", "Chennai");
            userd.put("userId", "admin");
            userd.put("localeId", "hi_IN");
            userd.put("instance", Pojokyc.instance);
            userd.put("module", "FDR");

            Log.e("OUTPUT", "" + userd.toString());

        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

        //169.38.77.190:101/api/Mobile_FDR/Farmermaster
        //15.206.21.248:27/Farmermaster
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/PAWHS_NEW_Localization/Apilocalization", userd,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC2", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONArray cast = obj.getJSONArray("detail");
                            for (int i = 0; i < cast.length(); i++) {


                                JSONObject actor = cast.getJSONObject(i);


                                String n1 = actor.getString("out_master_rowid");
                                String n2 = actor.getString("out_parent_code");
                                String n3 = actor.getString("out_master_code");
                                String n4 = actor.getString("out_master_description");
                                String n5 = actor.getString("out_depend_code");
                                String n6 = actor.getString("out_depend_desc");
                                String n7 = actor.getString("out_locallang_flag");
                                String n8 = actor.getString("out_status_code");
                                String n9 = actor.getString("out_status_desc");

                                Log.e("Table2", "" + n1);

                                mydb.insertmasterla(n1, n2, n3, n4, n5, n6, n7, n8, n9);


                            }


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
    public void save7()
    {
        SQLiteDatabase dbs = mydb.getWritableDatabase();
        dbs.execSQL("delete from orgn");
        final String url = Pojokyc.url+"/api/FDRFpoReg/allfpo_reg?org=Flexi&locn="+Pojokyc.instance+"&user=Admin&lang=en_US&filterby_option=ALL&filterby_code=.&filterby_fromvalue=.&filterby_tovalue=.";

// prepare the Request
        Log.e("URLOR",""+url.toString());
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        try {
                            Log.e("REPORG",""+response.toString());
                            JSONObject obj = response.getJSONObject("context");
                            JSONArray cast = obj.getJSONArray("list");
                            for (int i = 0; i < cast.length(); i++) {


                                JSONObject actor = cast.getJSONObject(i);

                                String n1 = actor.getString("out_orgn_rowid");
                                String n2 = actor.getString("out_orgn_code");
                                String n3 = actor.getString("out_orgn_name");
                                String n4 = "";
                                String n5 = "";



                                mydb.insertorgn(n1, n2, n3, n4, n5);

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );

// add it to the RequestQueue
        getRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Landpage.this).addToRequestQueue(getRequest);
    }
    public static String getDate1() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    private void callProductMasterAllProductJsonDetails() {
        final SQLiteDataBaseHandler dbpa = new SQLiteDataBaseHandler(Landpage.this);
        SQLiteDatabase dbspa = dbpa.getWritableDatabase();

        dbpa.deleteAllProductMasterAllProduct(getApplicationContext());
        dbpa.deleteEstimateList(getApplicationContext());
        dbpa.deleteSpmDetailsList(getApplicationContext());

        PmContextDao pmContextDao = new PmContextDao();
        pmContextDao.setOrgnId(orgnCode);
        // pmContextDao.setLocnId(locnId);
        pmContextDao.setLocnId(ApiUtils.instance);
        pmContextDao.setUserId(userId);
        pmContextDao.setLocaleId(localeId);
        pmContextDao.setFilterBy_Option("ALL");
        pmContextDao.setFilterBy_Code(".");
        pmContextDao.setFilterBy_FromValue(".");
        pmContextDao.setFilterBy_ToValue(".");

        mAPIService.getProductMasterAllProductDetails(pmContextDao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProductMasterDao>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ProductMasterDao productMasterDao) {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        List<PmListDao> pmListDaoList = productMasterDao.getPmContextDao().getPmListDaoList();

                        for (int i = 0; i < pmListDaoList.size(); i++) {

                            PmListDao pmListDao = new PmListDao(1, pmListDaoList.get(i).getOut_product_rowid(),
                                    pmListDaoList.get(i).getOut_pawhs_code(), pmListDaoList.get(i).getOut_agg_code(), pmListDaoList.get(i).getOut_product_code(),
                                    pmListDaoList.get(i).getOut_product_name(), pmListDaoList.get(i).getOut_product_category(), pmListDaoList.get(i).getOut_product_subcategory(),
                                    pmListDaoList.get(i).getOut_hsn_code(), pmListDaoList.get(i).getOut_hsn_desctiption(), pmListDaoList.get(i).getOut_status_code(),
                                    pmListDaoList.get(i).getOut_status_desc(), pmListDaoList.get(i).getOut_row_timestamp(), pmListDaoList.get(i).getOut_uomtype_code(), pmListDaoList.get(i).getOut_crop_variety(),pmListDaoList.get(i).getOut_value_withtax());
                            dbpa.addAllProductMasterAllProduct(pmListDao);

                        }
                        List<PmListDao> pmListDaosList = dbpa.getOfflineMasterAllProductDetails();

                        for (int i = 0; i < pmListDaosList.size(); i++) {
                            String productRowId = pmListDaosList.get(i).getOut_product_rowid();
                            String productCode = pmListDaosList.get(i).getOut_product_code();

                            callQualityParameterJsonDetails(productRowId, productCode);

                        }
                        //  callLotnoListDetails();

                        //showCompleteDialog();

                        Log.i(MyConstants.TAG, String.valueOf(pmListDaoList.size()));


                    }
                });
    }
    private void callQualityParameterJsonDetails(String productRowId, String productCode) {
        final SQLiteDataBaseHandler dbpa = new SQLiteDataBaseHandler(Landpage.this);
        SQLiteDatabase dbspa = dbpa.getWritableDatabase();
       /* progressLayout.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);*/

        SpmContextDao spmContextDao = new SpmContextDao();
        spmContextDao.setOrgnId(orgnCode);
        // spmContextDao.setLocnId(locnId);
        spmContextDao.setLocnId(ApiUtils.instance);
        spmContextDao.setUserId(userId);
        spmContextDao.setLocaleId(localeId);
        SpmHeaderDao spmHeaderDao = new SpmHeaderDao();
        spmHeaderDao.setIOU_product_rowid(Integer.parseInt(productRowId));
        spmHeaderDao.setIOU_agg_code(orgnCode);
        spmHeaderDao.setIOU_product_code(productCode);
        spmContextDao.setSpmHeaderDao(spmHeaderDao);

        mAPIService.getQualityParameterDetails(spmContextDao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SingleProductMasterDao>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SingleProductMasterDao singleProductMasterDao) {
                       /* getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressLayout.setVisibility(View.GONE);*/

                        List<SpmDetailDao> spmDetailDaoList = singleProductMasterDao.getSpmContextDao().getSpmDetailDaoList();

                        for (int i = 0; i < spmDetailDaoList.size(); i++) {

                            SpmDetailDao spmDetailDao = new SpmDetailDao(1, spmDetailDaoList.get(i).getIn_product_dtl_rowid(),
                                    spmDetailDaoList.get(i).getIn_pawhs_code(), spmDetailDaoList.get(i).getIn_row_slno(), spmDetailDaoList.get(i).getIn_maize_code(),
                                    spmDetailDaoList.get(i).getIn_maize_name(), spmDetailDaoList.get(i).getIn_range(), spmDetailDaoList.get(i).getIn_maize_interval(),
                                    spmDetailDaoList.get(i).getIn_maize_unit(), spmDetailDaoList.get(i).getIn_status_code(), spmDetailDaoList.get(i).getIn_mode_flag());
                            dbpa.addAllSpmDetailsList(spmDetailDao);

                        }
                    }
                });

    }
    public void offlinesave()

        {
            final SQLiteDatabase dbs = mydb.getWritableDatabase();
            String selectQuery5 = "SELECT  * FROM farmerh where flag = 0";
            final Cursor cc = dbs.rawQuery(selectQuery5, null);
            Log.e("NULL2", "" + cc.getCount());

            try {
                pdialog.setCanceledOnTouchOutside(false);
                pdialog.setTitle("Uploading Please Wait.......");
                pdialog.show();

                if (cc.moveToFirst()) {
                    farid = cc.getString(0);
                    Log.e("NULL", "" + cc.getString(0));

                    if (cc.getString(5).equalsIgnoreCase("male")) {
                        gf = "QCD_GENDER_MALE";
                    }
                    if (cc.getString(5).equalsIgnoreCase("female")) {
                        gf = "QCD_GENDER_FEMALE";
                    }
                    if (cc.getString(5).equalsIgnoreCase("transgender")) {
                        gf = "QCD_GENDER_TRANSGENDER";
                    }


                    jsonParam = new JSONObject();


                    JSONObject user2 = new JSONObject();
                    if(cc.getString(24).equalsIgnoreCase("0"))
                    {
                        encodedImage = "0";
                    }
                    else
                    {
                        try {

                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(cc.getString(24)));

                            byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                            //Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());


                            encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    user2.put("in_farmer_rowid", 0);
                    user2.put("in_farmer_code", "");
                    user2.put("in_version_no", 1);
                    user2.put("in_photo_farmer", encodedImage);

                    fmid = cc.getString(0);

                    n1 = cc.getString(1);
                    n2 = cc.getString(2);
                    n2n = cc.getString(3);
                    n3 = cc.getString(4);
                    n4 = cc.getString(7);
                    n5 = cc.getString(6);
                    n10 = cc.getString(10);
                    n11 = cc.getString(11);
                    n12 = cc.getString(12);
                    n13 = cc.getString(13);

                    adsfid = cc.getString(22);

                    user2.put("in_farmer_name", cc.getString(1));

                    if(cc.getString(2).equalsIgnoreCase(""))
                    {
                        user2.put("in_sur_name", cc.getString(1));
                    }
                    else
                    {
                        user2.put("in_sur_name", cc.getString(2));
                    }

                    user2.put("in_fhw_name", cc.getString(3));
                    String[] dd = cc.getString(6).split("/");

                    user2.put("in_farmer_dob", dd[2]+"/"+dd[1]+"/"+dd[0]);
                    user2.put("in_farmer_addr1", cc.getString(9));
                    user2.put("in_farmer_addr2", cc.getString(23));
                    user2.put("in_farmer_country", "QCD_UN_IND");
                    user2.put("in_farmer_state", "QCD_UNS_UP");

                    user2.put("in_farmer_dist", cc.getString(21));
                    user2.put("in_farmer_taluk", cc.getString(20));
                    user2.put("in_farmer_panchayat", cc.getString(19));
                    user2.put("in_farmer_village", cc.getString(18));
                    user2.put("in_farmer_pincode", cc.getString(8));
                    user2.put("in_marital_status", "");
                    user2.put("in_gender_flag", gf);
                    user2.put("in_reg_mobile_no", cc.getString(4));
                    user2.put("in_status_code", "A");
                    user2.put("in_mode_flag", "I");
                    user2.put("in_row_timestamp", "");
                    user2.put("in_fpo_orgncode", sharedpreferences.getString("oc1", ""));
                    user2.put("orgnId", "flexi");
                    user2.put("locnId", "chennai");
                    user2.put("userId", sharedpreferences.getString("un", "") + "-" + sharedpreferences.getString("phn", ""));
                    user2.put("localeId", "en_US");
                    user2.put("instance", Pojokyc.instance);
                    user2.put("in_dup_flag","N");


                    jsonParam.put("MFarmerHeaderDetails", user2);


                    String fn = cc.getString(22);
                    Log.e("NULL", "" + cc.getCount() + fn);

                    JSONArray jsonArray2 = new JSONArray();
                    Cursor cursorb = dbs.query("bank", new String[]{"ano", "typec", "bc", "branch", "ifsc", "dc", "dd"
                            }, "fid" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTb", "" + cursorb.getCount());
                    if (cursorb.moveToFirst()) {

                        do {

                            JSONObject userb = new JSONObject();


                            userb.put("in_farmerbank_rowid", 0);
                            userb.put("in_bank_acc_no", cursorb.getString(0));
                            userb.put("in_bank_acc_type", cursorb.getString(1));

                            userb.put("in_bank_code", cursorb.getString(2));
                            userb.put("in_branch_code", cursorb.getString(3));
                            userb.put("in_ifsc_code", cursorb.getString(4));
                            userb.put("in_dflt_dr_acc", cursorb.getString(6));
                            userb.put("in_dflt_cr_acc", cursorb.getString(5));
                            userb.put("in_status_code", "A");
                            userb.put("in_mode_flag", "I");


                            jsonArray2.put(userb);

                        } while (cursorb.moveToNext());

                        user2.put("MfarmerBankDetails", jsonArray2);

                    }


                    JSONArray jsonArray = new JSONArray();
                    Cursor cursork = dbs.query("kyc", new String[]{"id", "type", "subtype", "dno", "vtd", "tms", "stmc", "rid","poto"
                            }, "fid" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTK", "" + cursork.getCount());
                    if (cursork.moveToFirst()) {

                        do {

                            JSONObject userk = new JSONObject();


                            userk.put("in_farmerkyc_rowid", 0);
                            userk.put("in_proof_type", cursork.getString(5));
                            userk.put("in_proof_doc", cursork.getString(6));

                            userk.put("in_proof_doc_no", cursork.getString(3));
                            userk.put("in_proof_doc_adharno", cursork.getString(3));
                            userk.put("in_proof_valid_till", cursork.getString(4));
                            userk.put("in_status_code", "A");
                            userk.put("in_mode_flag", "I");
                            if(cursork.getString(8).equalsIgnoreCase("0"))
                            {
                                encodedImage2 = "0";
                            }
                            else
                            {
                                try {

                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(cursork.getString(8)));

                                    byteArrayOutputStream2 = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream2);
                                    //Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());


                                    encodedImage2 = Base64.encodeToString(byteArrayOutputStream2.toByteArray(), Base64.DEFAULT);


                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            userk.put("in_photo_kyc",encodedImage2);


                            jsonArray.put(userk);

                        } while (cursork.moveToNext());

                        user2.put("MfarmerKycDetails", jsonArray);

                    }
                    JSONArray jsonArray3 = new JSONArray();
                    Cursor cursora = dbs.query("address", new String[]{"at", "pi", "addr", "vic", "gpc", "tac", "dic", "slno"
                            }, "fc" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTa", "" + cursora.getCount());
                    if (cursora.moveToFirst()) {

                        do {

                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_ADDR");
                                ob1.put("in_entity_code", "EC_ADDR_ADDR1");
                                ob1.put("in_row_slno", cursora.getString(7));
                                ob1.put("in_entity_value", cursora.getString(2));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_ADDR");
                                ob2.put("in_entity_code", "EC_ADDR_ADDR2");
                                ob2.put("in_row_slno", cursora.getString(7));
                                ob2.put("in_entity_value", "");
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_ADDR");
                                ob3.put("in_entity_code", "EC_ADDR_DIST");
                                ob3.put("in_row_slno", cursora.getString(7));
                                ob3.put("in_entity_value", cursora.getString(6));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_ADDR");
                                ob4.put("in_entity_code", "EC_ADDR_GRAMPAN");
                                ob4.put("in_row_slno", cursora.getString(7));
                                ob4.put("in_entity_value", cursora.getString(4));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob5 = new JSONObject();
                            try {
                                ob5.put("in_farmerdetail_rowid", 0);
                                ob5.put("in_entitygrp_code", "EC_ADDR");
                                ob5.put("in_entity_code", "EC_ADDR_PINCODE");
                                ob5.put("in_row_slno", cursora.getString(7));
                                ob5.put("in_entity_value", cursora.getString(1));
                                ob5.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob6 = new JSONObject();
                            try {
                                ob6.put("in_farmerdetail_rowid", 0);
                                ob6.put("in_entitygrp_code", "EC_ADDR");
                                ob6.put("in_entity_code", "EC_ADDR_STATE");
                                ob6.put("in_row_slno", cursora.getString(7));
                                ob6.put("in_entity_value", "QCD_UNS_UP");
                                ob6.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob7 = new JSONObject();
                            try {
                                ob7.put("in_farmerdetail_rowid", 0);
                                ob7.put("in_entitygrp_code", "EC_ADDR");
                                ob7.put("in_entity_code", "EC_ADDR_TALUK");
                                ob7.put("in_row_slno", cursora.getString(7));
                                ob7.put("in_entity_value", cursora.getString(5));
                                ob7.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob8 = new JSONObject();
                            try {
                                ob8.put("in_farmerdetail_rowid", 0);
                                ob8.put("in_entitygrp_code", "EC_ADDR");
                                ob8.put("in_entity_code", "EC_ADDR_TYPE");
                                ob8.put("in_row_slno", cursora.getString(7));
                                ob8.put("in_entity_value", cursora.getString(0));
                                ob8.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob9 = new JSONObject();
                            try {
                                ob9.put("in_farmerdetail_rowid", 0);
                                ob9.put("in_entitygrp_code", "EC_ADDR");
                                ob9.put("in_entity_code", "EC_ADDR_UN");
                                ob9.put("in_row_slno", cursora.getString(7));
                                ob9.put("in_entity_value", "QCD_UN_IND");
                                ob9.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob10 = new JSONObject();
                            try {
                                ob10.put("in_farmerdetail_rowid", 0);
                                ob10.put("in_entitygrp_code", "EC_ADDR");
                                ob10.put("in_entity_code", "EC_ADDR_VILLAGE");
                                ob10.put("in_row_slno", cursora.getString(7));
                                ob10.put("in_entity_value", cursora.getString(3));
                                ob10.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            JSONArray jsonArraya2 = new JSONArray();

                            jsonArraya2.put(ob1);
                            jsonArraya2.put(ob2);
                            jsonArraya2.put(ob3);
                            jsonArraya2.put(ob4);
                            jsonArraya2.put(ob5);
                            jsonArraya2.put(ob6);
                            jsonArraya2.put(ob7);
                            jsonArraya2.put(ob8);
                            jsonArraya2.put(ob9);
                            jsonArraya2.put(ob10);


                            jsonArray3.put(jsonArraya2);

                        } while (cursora.moveToNext());

                        user2.put("MfarmerAddressDetails", jsonArray3);




                    }

                    JSONArray jsonArray4 = new JSONArray();
                    Cursor cursorc = dbs.query("crop", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8","v9","v10","v11","v12"
                            }, "v11" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTc", "" + cursorc.getCount());
                    if (cursorc.moveToFirst()) {

                        do {
                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_CROP");
                                ob1.put("in_entity_code", "EC_CROP_Year");
                                ob1.put("in_row_slno", String.valueOf(cursorc.getString(8)));
                                ob1.put("in_entity_value", "QCD_CROP_"+cursorc.getString(0));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_CROP");
                                ob2.put("in_entity_code", "EC_CROP_Season");
                                ob2.put("in_row_slno", String.valueOf(cursorc.getString(8)));
                                ob2.put("in_entity_value", "QCD_CROP_"+cursorc.getString(1));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_CROP");
                                ob3.put("in_entity_code", "EC_CROP_CropType");
                                ob3.put("in_row_slno", String.valueOf(cursorc.getString(8)));
                                ob3.put("in_entity_value", "QCD_CROPTYPE_MaizeKharif");
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_CROP");
                                ob4.put("in_entity_code", "EC_CROP_Vareity");
                                ob4.put("in_row_slno", String.valueOf(cursorc.getString(8)));
                                ob4.put("in_entity_value", cursorc.getString(3));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob5 = new JSONObject();
                            try {
                                ob5.put("in_farmerdetail_rowid", 0);
                                ob5.put("in_entitygrp_code", "EC_CROP");
                                ob5.put("in_entity_code", "EC_CROP_Acres");
                                ob5.put("in_row_slno", String.valueOf(cursorc.getString(8)));
                                ob5.put("in_entity_value", cursorc.getString(4));
                                ob5.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob6 = new JSONObject();
                            try {
                                ob6.put("in_farmerdetail_rowid", 0);
                                ob6.put("in_entitygrp_code", "EC_CROP");
                                ob6.put("in_entity_code", "EC_CROP_Total");
                                ob6.put("in_row_slno", String.valueOf(cursorc.getString(8)));
                                ob6.put("in_entity_value", "0");
                                ob6.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }


                            JSONArray jsonArrayc = new JSONArray();

                            jsonArrayc.put(ob1);
                            jsonArrayc.put(ob2);
                            jsonArrayc.put(ob3);
                            jsonArrayc.put(ob4);
                            jsonArrayc.put(ob5);
                            jsonArrayc.put(ob6);
                            jsonArray4.put(jsonArrayc);
                        } while (cursorc.moveToNext());

                        user2.put("Mfarmercrop", jsonArray4);
                    }
                    JSONArray jsonArray5 = new JSONArray();
                    Cursor cursors = dbs.query("sowing", new String[]{"id","v1","v2","v3","v4","v5","v6","v7","v8","v9","v10","v11","v12","v13","v14","v15","v16","v17","n1","n2","n3","n4","n5"
                            }, "v16" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTc", "" + cursorc.getCount());
                    if (cursors.moveToFirst()) {

                        do {

                            String vname = null;

                            Cursor cursor = dbs.query("masterl", new String[]{"out_depend_code"
                                    }, "out_master_code" + " LIKE ?",
                                    new String[]{"%"+cursors.getString(13)+"%"}, null, null, null, null);
                            if(cursor.getCount()>0)
                            {
                                if(cursor.moveToFirst())
                                {
                                    do {
                                        vname = cursor.getString(0);
                                    }while(cursor.moveToNext());
                                }
                            }

//                        String scd[] = cursors.getString(0).split("/");
//                        Log.e("NULL",""+scd[0]);
//                        String scd1[] = scd[0].split(":");
//                        Log.e("NULL",""+scd[1]);
//                        String scd2[] = scd[1].split(":");
//                        Log.e("NULL",""+scd[3]);
//                        String scd3[] = scd[3].split(":");
                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob1.put("in_entity_code", "EC_CROP_SOWING_QTY");
                                ob1.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob1.put("in_entity_value", cursors.getString(5));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob2.put("in_entity_code", "EC_CROP_SOWING_Vareity");
                                ob2.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob2.put("in_entity_value", vname);
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob3.put("in_entity_code", "EC_CROP_SOWING_Croptype");
                                ob3.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob3.put("in_entity_value", cursors.getString(3));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob4.put("in_entity_code", "EC_CROP_SOWING_Season");
                                ob4.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob4.put("in_entity_value", cursors.getString(2));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob5 = new JSONObject();
                            try {
                                ob5.put("in_farmerdetail_rowid", 0);
                                ob5.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob5.put("in_entity_code", "EC_CROP_SOWING_Year");
                                ob5.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob5.put("in_entity_value", cursors.getString(1));
                                ob5.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob6 = new JSONObject();
                            try {
                                ob6.put("in_farmerdetail_rowid", 0);
                                ob6.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob6.put("in_entity_code", "EC_CROP_SOWING_AREA");
                                ob6.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob6.put("in_entity_value", cursors.getString(6));
                                ob6.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob7 = new JSONObject();
                            try {
                                ob7.put("in_farmerdetail_rowid", 0);
                                ob7.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob7.put("in_entity_code", "EC_CROP_SOWING_EXPECYIELD");
                                ob7.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob7.put("in_entity_value", cursors.getString(7));
                                ob7.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob8 = new JSONObject();
                            try {
                                ob8.put("in_farmerdetail_rowid", 0);
                                ob8.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob8.put("in_entity_code", "EC_CROP_SOWING_SURPLUS");
                                ob8.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob8.put("in_entity_value", cursors.getString(8));
                                ob8.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob9 = new JSONObject();
                            try {
                                ob9.put("in_farmerdetail_rowid", 0);
                                ob9.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob9.put("in_entity_code", "EC_CROP_SOWING_EXPECPRICE");
                                ob9.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob9.put("in_entity_value", cursors.getString(8));
                                ob9.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob10 = new JSONObject();
                            try {
                                ob10.put("in_farmerdetail_rowid", 0);
                                ob10.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob10.put("in_entity_code", "EC_CROP_SOWING_SOWDATE");
                                ob10.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob10.put("in_entity_value", cursors.getString(19));
                                ob10.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob11 = new JSONObject();
                            try {
                                ob11.put("in_farmerdetail_rowid", 0);
                                ob11.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob11.put("in_entity_code", "EC_CROP_SOWING_TRANSDATE");
                                ob11.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob11.put("in_entity_value", cursors.getString(11));
                                ob11.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob12 = new JSONObject();
                            try {
                                ob12.put("in_farmerdetail_rowid", 0);
                                ob12.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob12.put("in_entity_code", "EC_CROP_SOWING_DWEEDDATE");
                                ob12.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob12.put("in_entity_value", cursors.getString(12));
                                ob12.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob13 = new JSONObject();
                            try {
                                ob13.put("in_farmerdetail_rowid", 0);
                                ob13.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob13.put("in_entity_code", "EC_CROP_SOWING_HARDATE");
                                ob13.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob13.put("in_entity_value", cursors.getString(13));
                                ob13.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob14 = new JSONObject();
                            try {
                                ob14.put("in_farmerdetail_rowid", 0);
                                ob14.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob14.put("in_entity_code", "EC_CROP_SOWING_CROPCLASS");
                                ob14.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob14.put("in_entity_value", cursors.getString(11));
                                ob14.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            JSONObject ob15 = new JSONObject();
                            try {
                                ob15.put("in_farmerdetail_rowid", 0);
                                ob15.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob15.put("in_entity_code", "EC_CROP_SOWING_Month");
                                ob15.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob15.put("in_entity_value", "");
                                ob15.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            JSONObject ob16 = new JSONObject();
                            try {
                                ob16.put("in_farmerdetail_rowid", 0);
                                ob16.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob16.put("in_entity_code", "EC_CROP_SOWING_SEED_NAME");
                                ob16.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob16.put("in_entity_value", cursors.getString(13));
                                ob16.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            JSONObject ob17 = new JSONObject();
                            try {
                                ob17.put("in_farmerdetail_rowid", 0);
                                ob17.put("in_entitygrp_code", "EC_CROP_SOWING");
                                ob17.put("in_entity_code", "EC_CROP_SOWING_EXPECYIELDTOBESOLD");
                                ob17.put("in_row_slno", String.valueOf(cursors.getString(14)));
                                ob17.put("in_entity_value", cursors.getString(9));
                                ob17.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONArray jsonArrays = new JSONArray();

                            jsonArrays.put(ob1);
                            jsonArrays.put(ob2);
                            jsonArrays.put(ob3);
                            jsonArrays.put(ob4);
                            jsonArrays.put(ob5);
                            jsonArrays.put(ob6);
                            jsonArrays.put(ob7);
                            jsonArrays.put(ob8);
                            jsonArrays.put(ob9);
                            jsonArrays.put(ob10);
                            jsonArrays.put(ob11);
                            jsonArrays.put(ob12);
                            jsonArrays.put(ob13);
                            jsonArrays.put(ob14);
                            jsonArrays.put(ob15);
                            jsonArrays.put(ob16);
                            jsonArrays.put(ob17);
                            jsonArray5.put(jsonArrays);
                        } while (cursors.moveToNext());

                        user2.put("Mfarmercropsowing", jsonArray5);
                    }
                    JSONArray jsonArray6 = new JSONArray();
                    Cursor cursorf = dbs.query("family", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8","v9","v10","v11"
                            }, "v10" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTf", "" + cursorc.getCount());
                    if (cursorf.moveToFirst()) {

                        do {

                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_FAMILYDET");
                                ob1.put("in_entity_code", "EC_FD_TYPE");
                                ob1.put("in_row_slno", String.valueOf(cursorf.getString(7)));
                                ob1.put("in_entity_value", cursorf.getString(0));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_FAMILYDET");
                                ob2.put("in_entity_code", "EC_FD_MEMNAME");
                                ob2.put("in_row_slno", String.valueOf(cursorf.getString(7)));
                                ob2.put("in_entity_value", cursorf.getString(1));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_FAMILYDET");
                                ob3.put("in_entity_code", "EC_FD_MEMDOB");
                                ob3.put("in_row_slno", String.valueOf(cursorf.getString(7)));
                                ob3.put("in_entity_value", cursorf.getString(2));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_FAMILYDET");
                                ob4.put("in_entity_code", "EC_FD_GENDER");
                                ob4.put("in_row_slno", String.valueOf(cursorf.getString(7)));
                                ob4.put("in_entity_value", cursorf.getString(6));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob5 = new JSONObject();
                            try {
                                ob5.put("in_farmerdetail_rowid", 0);
                                ob5.put("in_entitygrp_code", "EC_FAMILYDET");
                                ob5.put("in_entity_code", "EC_FD_RELATION");
                                ob5.put("in_row_slno", String.valueOf(cursorf.getString(7)));
                                ob5.put("in_entity_value", cursorf.getString(3));
                                ob5.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob6 = new JSONObject();
                            try {
                                ob6.put("in_farmerdetail_rowid", 0);
                                ob6.put("in_entitygrp_code", "EC_FAMILYDET");
                                ob6.put("in_entity_code", "EC_FD_QUAL");
                                ob6.put("in_row_slno", String.valueOf(cursorf.getString(7)));
                                ob6.put("in_entity_value", cursorf.getString(4));
                                ob6.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob7 = new JSONObject();
                            try {
                                ob7.put("in_farmerdetail_rowid", 0);
                                ob7.put("in_entitygrp_code", "EC_FAMILYDET");
                                ob7.put("in_entity_code", "EC_FD_OCCU");
                                ob7.put("in_row_slno", String.valueOf(cursorf.getString(7)));
                                ob7.put("in_entity_value", cursorf.getString(5));
                                ob7.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            JSONArray jsonArrayf = new JSONArray();

                            jsonArrayf.put(ob1);
                            jsonArrayf.put(ob2);
                            jsonArrayf.put(ob3);
                            jsonArrayf.put(ob4);
                            jsonArrayf.put(ob5);
                            jsonArrayf.put(ob6);
                            jsonArrayf.put(ob7);

                            jsonArray6.put(jsonArrayf);
                        } while (cursorf.moveToNext());

                        user2.put("Mfarmerfamily", jsonArray6);
                    }

                    JSONArray jsonArray7 = new JSONArray();
                    Cursor cursorp = dbs.query("personal", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8","v9","v10","v11","v12"
                            }, "v11" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTp", "" + cursorp.getCount());
                    if (cursorp.moveToFirst()) {

                        do {

                            JSONObject ob1 = new JSONObject();
                            try {

                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_PER_FARMER");
                                ob1.put("in_entity_code", "EC_PDF_CASTE");
                                ob1.put("in_row_slno", cursorp.getString(8));
                                ob1.put("in_entity_value", cursorp.getString(6));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_PER_FARMER");
                                ob2.put("in_entity_code", "EC_PDF_MAIL");
                                ob2.put("in_row_slno", cursorp.getString(8));
                                ob2.put("in_entity_value", cursorp.getString(3));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_PER_FARMER");
                                ob3.put("in_entity_code", "EC_PDF_MINORITY");
                                ob3.put("in_row_slno", cursorp.getString(8));
                                ob3.put("in_entity_value", ""+cursorp.getString(7));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_PER_FARMER");
                                ob4.put("in_entity_code", "EC_PDF_MS");
                                ob4.put("in_row_slno", cursorp.getString(8));
                                ob4.put("in_entity_value", cursorp.getString(0));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob5 = new JSONObject();
                            try {
                                ob5.put("in_farmerdetail_rowid", 0);
                                ob5.put("in_entitygrp_code", "EC_PER_FARMER");
                                ob5.put("in_entity_code", "EC_PDF_MOBILE");
                                ob5.put("in_row_slno", cursorp.getString(8));
                                ob5.put("in_entity_value", cursorp.getString(2));
                                ob5.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob6 = new JSONObject();
                            try {
                                ob6.put("in_farmerdetail_rowid", 0);
                                ob6.put("in_entitygrp_code", "EC_PER_FARMER");
                                ob6.put("in_entity_code", "EC_PDF_PHONE");
                                ob6.put("in_row_slno", cursorp.getString(8));
                                ob6.put("in_entity_value", cursorp.getString(1));
                                ob6.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob7 = new JSONObject();
                            try {
                                ob7.put("in_farmerdetail_rowid", 0);
                                ob7.put("in_entitygrp_code", "EC_PER_FARMER");
                                ob7.put("in_entity_code", "EC_PDF_QUAL");
                                ob7.put("in_row_slno", cursorp.getString(8));
                                ob7.put("in_entity_value", cursorp.getString(4));
                                ob7.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob8 = new JSONObject();
                            try {
                                ob8.put("in_farmerdetail_rowid", 0);
                                ob8.put("in_entitygrp_code", "EC_PER_FARMER");
                                ob8.put("in_entity_code", "EC_PDF_RLGN");
                                ob8.put("in_row_slno", cursorp.getString(8));
                                ob8.put("in_entity_value", cursorp.getString(5));
                                ob8.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            JSONArray jsonArrayp = new JSONArray();

                            jsonArrayp.put(ob1);
                            jsonArrayp.put(ob2);
                            jsonArrayp.put(ob3);
                            jsonArrayp.put(ob4);
                            jsonArrayp.put(ob5);
                            jsonArrayp.put(ob6);
                            jsonArrayp.put(ob7);
                            jsonArrayp.put(ob8);
                            jsonArray7.put(jsonArrayp);
                        } while (cursorp.moveToNext());

                        user2.put("Mfarmerpersonal", jsonArray7);
                    }
                    JSONArray jsonArray9 = new JSONArray();
                    Cursor cursorad = dbs.query("asset", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8","v9","v10"
                            }, "v9" + "=?",
                            new String[]{fn}, null, null, null, null);

                    // Log.e("COUNTp", "" + cursorp.getCount());
                    if (cursorad.moveToFirst()) {

                        do {

                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_ASSET");
                                ob1.put("in_entity_code", "EC_AD_TYPE");
                                ob1.put("in_row_slno", cursorad.getString(6));
                                ob1.put("in_entity_value", cursorad.getString(0));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_ASSET");
                                ob2.put("in_entity_code", "EC_AD_SUBTYPE");
                                ob2.put("in_row_slno", cursorad.getString(6));
                                ob2.put("in_entity_value", cursorad.getString(1));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_ASSET");
                                ob3.put("in_entity_code", "EC_AD_COUNT");
                                ob3.put("in_row_slno", cursorad.getString(6));
                                ob3.put("in_entity_value", cursorad.getString(2));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_ASSET");
                                ob4.put("in_entity_code", "EC_AD_PURYEAR");
                                ob4.put("in_row_slno", cursorad.getString(6));
                                ob4.put("in_entity_value", cursorad.getString(3));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob5 = new JSONObject();
                            try {
                                ob5.put("in_farmerdetail_rowid", 0);
                                ob5.put("in_entitygrp_code", "EC_ASSET");
                                ob5.put("in_entity_code", "EC_AD_SERIALNO");
                                ob5.put("in_row_slno", cursorad.getString(6));
                                ob5.put("in_entity_value", cursorad.getString(4));
                                ob5.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob6 = new JSONObject();
                            try {
                                ob6.put("in_farmerdetail_rowid", 0);
                                ob6.put("in_entitygrp_code", "EC_ASSET");
                                ob6.put("in_entity_code", "EC_AD_PURPOSE");
                                ob6.put("in_row_slno", cursorad.getString(6));
                                ob6.put("in_entity_value", cursorad.getString(5));
                                ob6.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            JSONArray jsonArrayad = new JSONArray();

                            jsonArrayad.put(ob1);
                            jsonArrayad.put(ob2);
                            jsonArrayad.put(ob3);
                            jsonArrayad.put(ob4);
                            jsonArrayad.put(ob5);
                            jsonArrayad.put(ob6);
                            jsonArray9.put(jsonArrayad);
                        } while (cursorad.moveToNext());

                        user2.put("MfarmerAssets", jsonArray9);
                    }

                    JSONArray jsonArray8 = new JSONArray();
                    Cursor cursorli = dbs.query("live", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8","v9"
                            }, "v8" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTp", "" + cursorp.getCount());
                    if (cursorli.moveToFirst()) {

                        do {

                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_LIVESTOCKDET");
                                ob1.put("in_entity_code", "EC_LSD_TYPE");
                                ob1.put("in_row_slno", cursorli.getString(5));
                                ob1.put("in_entity_value", cursorli.getString(0));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_LIVESTOCKDET");
                                ob2.put("in_entity_code", "EC_LSD_SUBTYPE");
                                ob2.put("in_row_slno", cursorli.getString(5));
                                ob2.put("in_entity_value", cursorli.getString(1));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_LIVESTOCKDET");
                                ob3.put("in_entity_code", "EC_LSD_OWNSHIP");
                                ob3.put("in_row_slno", cursorli.getString(5));
                                ob3.put("in_entity_value", cursorli.getString(2));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_LIVESTOCKDET");
                                ob4.put("in_entity_code", "EC_LSD_COUNT");
                                ob4.put("in_row_slno", cursorli.getString(5));
                                ob4.put("in_entity_value", cursorli.getString(3));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob5 = new JSONObject();
                            try {
                                ob5.put("in_farmerdetail_rowid", 0);
                                ob5.put("in_entitygrp_code", "EC_LIVESTOCKDET");
                                ob5.put("in_entity_code", "EC_LSD_PUR");
                                ob5.put("in_row_slno", cursorli.getString(5));
                                ob5.put("in_entity_value", cursorli.getString(4));
                                ob5.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }


                            JSONArray jsonArrayli = new JSONArray();

                            jsonArrayli.put(ob1);
                            jsonArrayli.put(ob2);
                            jsonArrayli.put(ob3);
                            jsonArrayli.put(ob4);
                            jsonArrayli.put(ob5);
                            jsonArray8.put(jsonArrayli);
                        } while (cursorli.moveToNext());

                        user2.put("Mfarmerlivestock", jsonArray8);
                    }
                    JSONArray jsonArray10 = new JSONArray();
                    Cursor cursorlo = dbs.query("loan", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8","v9","v10","v11","v12","v13","v14","v15"
                            }, "v14" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTp", "" + cursorp.getCount());
                    if (cursorlo.moveToFirst()) {

                        do {

                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_LOANDET");
                                ob1.put("in_entity_code", "EC_LD_LOANTYPE");
                                ob1.put("in_row_slno", cursorlo.getString(11));
                                ob1.put("in_entity_value", cursorlo.getString(0));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_LOANDET");
                                ob2.put("in_entity_code", "EC_LD_INSTYPE");
                                ob2.put("in_row_slno", cursorlo.getString(11));
                                ob2.put("in_entity_value", cursorlo.getString(1));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_LOANDET");
                                ob3.put("in_entity_code", "EC_LD_INSNAME");
                                ob3.put("in_row_slno", cursorlo.getString(11));
                                ob3.put("in_entity_value", cursorlo.getString(2));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_LOANDET");
                                ob4.put("in_entity_code", "EC_LD_INSBRANCH");
                                ob4.put("in_row_slno", cursorlo.getString(11));
                                ob4.put("in_entity_value", cursorlo.getString(3));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob5 = new JSONObject();
                            try {
                                ob5.put("in_farmerdetail_rowid", 0);
                                ob5.put("in_entitygrp_code", "EC_LOANDET");
                                ob5.put("in_entity_code", "EC_LD_AMOUNT");
                                ob5.put("in_row_slno", cursorlo.getString(11));
                                ob5.put("in_entity_value", cursorlo.getString(4));
                                ob5.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob6 = new JSONObject();
                            try {
                                ob6.put("in_farmerdetail_rowid", 0);
                                ob6.put("in_entitygrp_code", "EC_LOANDET");
                                ob6.put("in_entity_code", "EC_LD_TENOR");
                                ob6.put("in_row_slno", cursorlo.getString(11));
                                ob6.put("in_entity_value", cursorlo.getString(8));
                                ob6.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob7 = new JSONObject();
                            try {
                                ob7.put("in_farmerdetail_rowid", 0);
                                ob7.put("in_entitygrp_code", "EC_LOANDET");
                                ob7.put("in_entity_code", "EC_LD_INTEREST");
                                ob7.put("in_row_slno", cursorlo.getString(11));
                                ob7.put("in_entity_value", cursorlo.getString(9));
                                ob7.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob8 = new JSONObject();
                            try {
                                ob8.put("in_farmerdetail_rowid", 0);
                                ob8.put("in_entitygrp_code", "EC_LOANDET");
                                ob8.put("in_entity_code", "EC_LD_EMI");
                                ob8.put("in_row_slno", cursorlo.getString(11));
                                ob8.put("in_entity_value", cursorlo.getString(10));
                                ob8.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob9 = new JSONObject();
                            try {
                                ob9.put("in_farmerdetail_rowid", 0);
                                ob9.put("in_entitygrp_code", "EC_LOANDET");
                                ob9.put("in_entity_code", "EC_LD_STARTDATE");
                                ob9.put("in_row_slno", cursorlo.getString(11));
                                ob9.put("in_entity_value", cursorlo.getString(5));
                                ob9.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob10 = new JSONObject();
                            try {
                                ob10.put("in_farmerdetail_rowid", 0);
                                ob10.put("in_entitygrp_code", "EC_LOANDET");
                                ob10.put("in_entity_code", "EC_LD_ENDDATE");
                                ob10.put("in_row_slno", cursorlo.getString(11));
                                ob10.put("in_entity_value", cursorlo.getString(6));
                                ob10.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob11 = new JSONObject();
                            try {
                                ob11.put("in_farmerdetail_rowid", 0);
                                ob11.put("in_entitygrp_code", "EC_LOANDET");
                                ob11.put("in_entity_code", "EC_LD_ACCNO");
                                ob11.put("in_row_slno", cursorlo.getString(11));
                                ob11.put("in_entity_value", cursorlo.getString(7));
                                ob11.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            JSONArray jsonArraylo = new JSONArray();

                            jsonArraylo.put(ob1);
                            jsonArraylo.put(ob2);
                            jsonArraylo.put(ob3);
                            jsonArraylo.put(ob4);
                            jsonArraylo.put(ob5);
                            jsonArraylo.put(ob6);
                            jsonArraylo.put(ob7);
                            jsonArraylo.put(ob8);
                            jsonArraylo.put(ob9);
                            jsonArraylo.put(ob10);
                            jsonArraylo.put(ob11);
                            jsonArray10.put(jsonArraylo);
                        } while (cursorlo.moveToNext());

                        user2.put("Mfarmerloans", jsonArray10);
                    }

                    JSONArray jsonArray11 = new JSONArray();
                    Cursor cursorla = dbs.query("land", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8","v9","v10","v11","v12","v13","v14"
                            }, "v10" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTp", "" + cursorp.getCount());
                    if (cursorla.moveToFirst()) {

                        do {


                            try {

                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(cursorla.getString(13)));

                                byteArrayOutputStream3 = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream3);
                                //Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());


                                encodedImage3 = Base64.encodeToString(byteArrayOutputStream3.toByteArray(), Base64.DEFAULT);


                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_OWNEDLAND");
                                ob1.put("in_entity_code", "EC_OWNED_Ownership");
                                ob1.put("in_row_slno", cursorla.getString(7));
                                ob1.put("in_entity_value", cursorla.getString(1));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_OWNEDLAND");
                                ob2.put("in_entity_code", "EC_OWNED_LandType");
                                ob2.put("in_row_slno", cursorla.getString(7));
                                ob2.put("in_entity_value", cursorla.getString(0));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_OWNEDLAND");
                                ob3.put("in_entity_code", "EC_OWNED_Noof_Acres");
                                ob3.put("in_row_slno", cursorla.getString(7));
                                ob3.put("in_entity_value", cursorla.getString(2));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_OWNEDLAND");
                                ob4.put("in_entity_code", "EC_OWNED_SoilType");
                                ob4.put("in_row_slno", cursorla.getString(7));
                                ob4.put("in_entity_value", cursorla.getString(3));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob5 = new JSONObject();
                            try {
                                ob5.put("in_farmerdetail_rowid", 0);
                                ob5.put("in_entitygrp_code", "EC_OWNEDLAND");
                                ob5.put("in_entity_code", "EC_OWNED_Irrigation");
                                ob5.put("in_row_slno", cursorla.getString(7));
                                ob5.put("in_entity_value", cursorla.getString(4));
                                ob5.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob6 = new JSONObject();
                            try {
                                ob6.put("in_farmerdetail_rowid", 0);
                                ob6.put("in_entitygrp_code", "EC_OWNEDLAND");
                                ob6.put("in_entity_code", "EC_OWNED_Latitude");
                                ob6.put("in_row_slno", cursorla.getString(7));
                                ob6.put("in_entity_value", cursorla.getString(5));
                                ob6.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob7 = new JSONObject();
                            try {
                                ob7.put("in_farmerdetail_rowid", 0);
                                ob7.put("in_entitygrp_code", "EC_OWNEDLAND");
                                ob7.put("in_entity_code", "EC_OWNED_Longitude");
                                ob7.put("in_row_slno", cursorla.getString(7));
                                ob7.put("in_entity_value", cursorla.getString(6));
                                ob7.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            JSONObject ob8 = new JSONObject();
                            try {
                                ob8.put("in_farmerdetail_rowid", 0);
                                ob8.put("in_entitygrp_code", "EC_OWNEDLAND");
                                ob8.put("in_entity_code", "EC_OWNED_Village");
                                ob8.put("in_row_slno", cursorla.getString(7));
                                ob8.put("in_entity_value", cursorla.getString(11));
                                ob8.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }  JSONObject ob9 = new JSONObject();
                            try {
                                ob9.put("in_farmerdetail_rowid", 0);
                                ob9.put("in_entitygrp_code", "EC_OWNEDLAND");
                                ob9.put("in_entity_code", "EC_OWNED_polyhouse");
                                ob9.put("in_row_slno", cursorla.getString(7));
                                ob9.put("in_entity_value", cursorla.getString(12));
                                ob9.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }  JSONObject ob10 = new JSONObject();
                            try {
                                ob10.put("in_farmerdetail_rowid", 0);
                                ob10.put("in_entitygrp_code", "EC_OWNEDLAND");
                                ob10.put("in_entity_code", "EC_OWNED_picture");
                                ob10.put("in_row_slno", cursorla.getString(7));
                                ob10.put("in_owned_picture", encodedImage3);
                                ob10.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            JSONArray jsonArrayla = new JSONArray();

                            jsonArrayla.put(ob1);
                            jsonArrayla.put(ob2);
                            jsonArrayla.put(ob3);
                            jsonArrayla.put(ob4);
                            jsonArrayla.put(ob5);
                            jsonArrayla.put(ob6);
                            jsonArrayla.put(ob7);
                            jsonArrayla.put(ob8);
                            jsonArrayla.put(ob9);
                            // JSONArray jsonArrayla2 = new JSONArray();
                            jsonArrayla.put(ob10);
                            jsonArray11.put(jsonArrayla);
                        } while (cursorla.moveToNext());

                        user2.put("Mfarmerland", jsonArray11);
                    }


                    JSONArray jsonArray12 = new JSONArray();
                    Cursor cursorlor = dbs.query("loanr", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8"
                            }, "v7" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTp", "" + cursorp.getCount());
                    if (cursorlor.moveToFirst()) {

                        do {
                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_LOAN_REPAY");
                                ob1.put("in_entity_code", "EC_LOAN_ACCNO");
                                ob1.put("in_row_slno", cursorlor.getString(4));
                                ob1.put("in_entity_value", cursorlor.getString(0));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_LOAN_REPAY");
                                ob2.put("in_entity_code", "EC_LOAN_REPAYMODE");
                                ob2.put("in_row_slno", cursorlor.getString(4));
                                ob2.put("in_entity_value", cursorlor.getString(1));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_LOAN_REPAY");
                                ob3.put("in_entity_code", "EC_LOAN_REPAYDATE");
                                ob3.put("in_row_slno", cursorlor.getString(4));
                                ob3.put("in_entity_value", cursorlor.getString(2));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_LOAN_REPAY");
                                ob4.put("in_entity_code", "EC_LOAN_REPAYAMT");
                                ob4.put("in_row_slno", cursorlor.getString(4));
                                ob4.put("in_entity_value", cursorlor.getString(3));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }


                            JSONArray jsonArraylor = new JSONArray();

                            jsonArraylor.put(ob1);
                            jsonArraylor.put(ob2);
                            jsonArraylor.put(ob3);
                            jsonArraylor.put(ob4);

                            jsonArray12.put(jsonArraylor);
                        } while (cursorlor.moveToNext());

                        user2.put("MfarmerloansrePay", jsonArray12);
                    }

                    JSONArray jsonArray13 = new JSONArray();
                    Cursor cursorin = dbs.query("insurance", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8","v9","v10","v11"
                            }, "v13" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTp", "" + cursorp.getCount());
                    if (cursorin.moveToFirst()) {

                        do {
                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_INSURANCE");
                                ob1.put("in_entity_code", "EC_INSURER_NAME");
                                ob1.put("in_row_slno", cursorin.getString(10));
                                ob1.put("in_entity_value", cursorin.getString(1));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_INSURANCE");
                                ob2.put("in_entity_code", "EC_AGENCY_NAME");
                                ob2.put("in_row_slno", cursorin.getString(10));
                                ob2.put("in_entity_value", cursorin.getString(2));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_INSURANCE");
                                ob3.put("in_entity_code", "EC_INSURANCE_TYPE");
                                ob3.put("in_row_slno", cursorin.getString(10));
                                ob3.put("in_entity_value", cursorin.getString(0));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_INSURANCE");
                                ob4.put("in_entity_code", "EC_INSURED_ON");
                                ob4.put("in_row_slno", cursorin.getString(10));
                                ob4.put("in_entity_value", cursorin.getString(4));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob5 = new JSONObject();
                            try {
                                ob5.put("in_farmerdetail_rowid", 0);
                                ob5.put("in_entitygrp_code", "EC_INSURANCE");
                                ob5.put("in_entity_code", "EC_POLICY_NO");
                                ob5.put("in_row_slno", cursorin.getString(10));
                                ob5.put("in_entity_value", cursorin.getString(3));
                                ob5.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob6 = new JSONObject();
                            try {
                                ob6.put("in_farmerdetail_rowid", 0);
                                ob6.put("in_entitygrp_code", "EC_INSURANCE");
                                ob6.put("in_entity_code", "EC_MATURITY_DATE");
                                ob6.put("in_row_slno", cursorin.getString(10));
                                ob6.put("in_entity_value", cursorin.getString(6));
                                ob6.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob7 = new JSONObject();
                            try {
                                ob7.put("in_farmerdetail_rowid", 0);
                                ob7.put("in_entitygrp_code", "EC_INSURANCE");
                                ob7.put("in_entity_code", "EC_PREMIUM");
                                ob7.put("in_row_slno", cursorin.getString(10));
                                ob7.put("in_entity_value", cursorin.getString(7));
                                ob7.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob8 = new JSONObject();
                            try {
                                ob8.put("in_farmerdetail_rowid", 0);
                                ob8.put("in_entitygrp_code", "EC_INSURANCE");
                                ob8.put("in_entity_code", "EC_PAYMENT_MODE");
                                ob8.put("in_row_slno", cursorin.getString(10));
                                ob8.put("in_entity_value", cursorin.getString(8));
                                ob8.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob9 = new JSONObject();
                            try {
                                ob9.put("in_farmerdetail_rowid", 0);
                                ob9.put("in_entitygrp_code", "EC_INSURANCE");
                                ob9.put("in_entity_code", "EC_NOMINEE");
                                ob9.put("in_row_slno", cursorin.getString(10));
                                ob9.put("in_entity_value", cursorin.getString(9));
                                ob9.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob10 = new JSONObject();
                            try {
                                ob10.put("in_farmerdetail_rowid", 0);
                                ob10.put("in_entitygrp_code", "EC_INSURANCE");
                                ob10.put("in_entity_code", "EC_POLICY_DATE");
                                ob10.put("in_row_slno", cursorin.getString(10));
                                ob10.put("in_entity_value", cursorin.getString(5));
                                ob10.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            JSONArray jsonArrayin = new JSONArray();

                            jsonArrayin.put(ob1);
                            jsonArrayin.put(ob2);
                            jsonArrayin.put(ob3);
                            jsonArrayin.put(ob4);
                            jsonArrayin.put(ob5);
                            jsonArrayin.put(ob6);
                            jsonArrayin.put(ob7);
                            jsonArrayin.put(ob8);
                            jsonArrayin.put(ob9);
                            jsonArrayin.put(ob10);

                            jsonArray13.put(jsonArrayin);
                        } while (cursorin.moveToNext());

                        user2.put("Mfarmerinsurance", jsonArray13);
                    }

                    JSONArray jsonArray14 = new JSONArray();
                    Cursor cursorsh = dbs.query("shareh", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8"
                            }, "v7" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTp", "" + cursorp.getCount());
                    if (cursorsh.moveToFirst()) {

                        do {
                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_SHARE");
                                ob1.put("in_entity_code", "EC_FPOName");
                                ob1.put("in_row_slno", cursorsh.getString(4));
                                ob1.put("in_entity_value", cursorsh.getString(0));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_SHARE");
                                ob2.put("in_entity_code", "EC_FIGName");
                                ob2.put("in_row_slno", cursorsh.getString(4));
                                ob2.put("in_entity_value", cursorsh.getString(1));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_SHARE");
                                ob3.put("in_entity_code", "EC_Shares");
                                ob3.put("in_row_slno", cursorsh.getString(4));
                                ob3.put("in_entity_value", cursorsh.getString(2));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_SHARE");
                                ob4.put("in_entity_code", "EC_ShareAmount");
                                ob4.put("in_row_slno", cursorsh.getString(4));
                                ob4.put("in_entity_value", cursorsh.getString(3));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }


                            JSONArray jsonArraysh = new JSONArray();

                            jsonArraysh.put(ob1);
                            jsonArraysh.put(ob2);
                            jsonArraysh.put(ob3);
                            jsonArraysh.put(ob4);


                            jsonArray14.put(jsonArraysh);
                        } while (cursorsh.moveToNext());

                        user2.put("Mfarmershareholding", jsonArray14);
                    }

                    JSONArray jsonArray15 = new JSONArray();
                    Cursor cursorbu = dbs.query("business", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8"
                            }, "v7" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTp", "" + cursorp.getCount());
                    if (cursorbu.moveToFirst()) {

                        do {
                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_BUSINESS");
                                ob1.put("in_entity_code", "EC_BusinessSegment");
                                ob1.put("in_row_slno", cursorbu.getString(4));
                                ob1.put("in_entity_value", cursorbu.getString(0));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_BUSINESS");
                                ob2.put("in_entity_code", "EC_Description");
                                ob2.put("in_row_slno", cursorbu.getString(4));
                                ob2.put("in_entity_value", cursorbu.getString(1));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_BUSINESS");
                                ob3.put("in_entity_code", "EC_Period");
                                ob3.put("in_row_slno", cursorbu.getString(4));
                                ob3.put("in_entity_value", cursorbu.getString(2));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_BUSINESS");
                                ob4.put("in_entity_code", "EC_BusinessAmount");
                                ob4.put("in_row_slno", cursorbu.getString(4));
                                ob4.put("in_entity_value", cursorbu.getString(3));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }


                            JSONArray jsonArraybu = new JSONArray();

                            jsonArraybu.put(ob1);
                            jsonArraybu.put(ob2);
                            jsonArraybu.put(ob3);
                            jsonArraybu.put(ob4);


                            jsonArray15.put(jsonArraybu);
                        } while (cursorsh.moveToNext());

                        user2.put("Mfarmerbusiness", jsonArray15);
                    }

                    JSONArray jsonArray16 = new JSONArray();
                    Cursor cursortn = dbs.query("training", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8"
                            }, "v10" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTt", "" + cursortn.getCount());
                    if (cursortn.moveToFirst()) {

                        do {
                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_TRAINING");
                                ob1.put("in_entity_code", "EC_TRAIN_YEAR");
                                ob1.put("in_row_slno", cursortn.getString(7));
                                ob1.put("in_entity_value", cursortn.getString(0));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_TRAINING");
                                ob2.put("in_entity_code", "EC_TRAIN_Season");
                                ob2.put("in_row_slno", cursortn.getString(7));
                                ob2.put("in_entity_value", cursortn.getString(1));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_TRAINING");
                                ob3.put("in_entity_code", "EC_TRAIN_Agenda");
                                ob3.put("in_row_slno", cursortn.getString(7));
                                ob3.put("in_entity_value", cursortn.getString(2));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_TRAINING");
                                ob4.put("in_entity_code", "EC_TRAIN_Duration");
                                ob4.put("in_row_slno", cursortn.getString(7));
                                ob4.put("in_entity_value", cursortn.getString(3));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob5 = new JSONObject();
                            try {
                                ob5.put("in_farmerdetail_rowid", 0);
                                ob5.put("in_entitygrp_code", "EC_TRAINING");
                                ob5.put("in_entity_code", "EC_TRAIN_Place");
                                ob5.put("in_row_slno", cursortn.getString(7));
                                ob5.put("in_entity_value", cursortn.getString(4));
                                ob5.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob6 = new JSONObject();
                            try {
                                ob6.put("in_farmerdetail_rowid", 0);
                                ob6.put("in_entitygrp_code", "EC_TRAINING");
                                ob6.put("in_entity_code", "EC_TRAIN_Date");
                                ob6.put("in_row_slno", cursortn.getString(7));
                                ob6.put("in_entity_value", cursortn.getString(5));
                                ob6.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob7 = new JSONObject();
                            try {
                                ob7.put("in_farmerdetail_rowid", 0);
                                ob7.put("in_entitygrp_code", "EC_TRAINING");
                                ob7.put("in_entity_code", "EC_TRAIN_ExperDet");
                                ob7.put("in_row_slno", cursortn.getString(7));
                                ob7.put("in_entity_value", cursortn.getString(6));
                                ob7.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }


                            JSONArray jsonArraytn = new JSONArray();

                            jsonArraytn.put(ob1);
                            jsonArraytn.put(ob2);
                            jsonArraytn.put(ob3);
                            jsonArraytn.put(ob4);
                            jsonArraytn.put(ob5);
                            jsonArraytn.put(ob6);
                            jsonArraytn.put(ob7);


                            jsonArray16.put(jsonArraytn);
                        } while (cursortn.moveToNext());

                        user2.put("Mfarmertraning", jsonArray16);
                    }

                    JSONArray jsonArray17 = new JSONArray();
                    Cursor cursorsk = dbs.query("stock", new String[]{"v1","v2","v3","v4","v5","v6","v7"
                            }, "v9" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTt", "" + cursortn.getCount());
                    if (cursorsk.moveToFirst()) {

                        do {

                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_STOCK");
                                ob1.put("in_entity_code", "EC_Stock_year");
                                ob1.put("in_row_slno", cursorsk.getString(6));
                                ob1.put("in_entity_value", cursorsk.getString(0));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_STOCK");
                                ob2.put("in_entity_code", "EC_Stock_Season");
                                ob2.put("in_row_slno", cursorsk.getString(6));
                                ob2.put("in_entity_value", cursorsk.getString(1));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_STOCK");
                                ob3.put("in_entity_code", "EC_Stock_Type");
                                ob3.put("in_row_slno", cursorsk.getString(6));
                                ob3.put("in_entity_value", cursorsk.getString(2));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_STOCK");
                                ob4.put("in_entity_code", "EC_Stock_Variety");
                                ob4.put("in_row_slno", cursorsk.getString(6));
                                ob4.put("in_entity_value", cursorsk.getString(3));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob5 = new JSONObject();
                            try {
                                ob5.put("in_farmerdetail_rowid", 0);
                                ob5.put("in_entitygrp_code", "EC_STOCK");
                                ob5.put("in_entity_code", "EC_Stock_Unsold");
                                ob5.put("in_row_slno", cursorsk.getString(6));
                                ob5.put("in_entity_value", cursorsk.getString(5));
                                ob5.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }


                            JSONArray jsonArraysk = new JSONArray();

                            jsonArraysk.put(ob1);
                            jsonArraysk.put(ob2);
                            jsonArraysk.put(ob3);
                            jsonArraysk.put(ob4);
                            jsonArraysk.put(ob5);

                            jsonArray17.put(jsonArraysk);
                        } while (cursorsk.moveToNext());

                        user2.put("Mfarmerstock", jsonArray17);
                    }


                    JSONArray jsonArray18 = new JSONArray();
                    Cursor cursorsal = dbs.query("sale", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8","v9","v10","v11"
                            }, "v13" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTt", "" + cursorsal.getCount());
                    if (cursorsal.moveToFirst()) {

                        do {
                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_SALE");
                                ob1.put("in_entity_code", "EC_SALE_Year");
                                ob1.put("in_row_slno", cursorsal.getString(10));
                                ob1.put("in_entity_value", cursorsal.getString(0));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_SALE");
                                ob2.put("in_entity_code", "EC_SALE_Season");
                                ob2.put("in_row_slno", cursorsal.getString(10));
                                ob2.put("in_entity_value", cursorsal.getString(1));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_SALE");
                                ob3.put("in_entity_code", "EC_SALE_Croptype");
                                ob3.put("in_row_slno", cursorsal.getString(10));
                                ob3.put("in_entity_value", cursorsal.getString(2));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_SALE");
                                ob4.put("in_entity_code", "EC_SALE_Vareity");
                                ob4.put("in_row_slno", cursorsal.getString(10));
                                ob4.put("in_entity_value", cursorsal.getString(3));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob5 = new JSONObject();
                            try {
                                ob5.put("in_farmerdetail_rowid", 0);
                                ob5.put("in_entitygrp_code", "EC_SALE");
                                ob5.put("in_entity_code", "EC_SALE_Qty");
                                ob5.put("in_row_slno", cursorsal.getString(10));
                                ob5.put("in_entity_value", cursorsal.getString(4));
                                ob5.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob6 = new JSONObject();
                            try {
                                ob6.put("in_farmerdetail_rowid", 0);
                                ob6.put("in_entitygrp_code", "EC_SALE");
                                ob6.put("in_entity_code", "EC_SALE_Price");
                                ob6.put("in_row_slno", cursorsal.getString(10));
                                ob6.put("in_entity_value", cursorsal.getString(5));
                                ob6.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob7 = new JSONObject();
                            try {
                                ob7.put("in_farmerdetail_rowid", 0);
                                ob7.put("in_entitygrp_code", "EC_SALE");
                                ob7.put("in_entity_code", "EC_SALE_Value");
                                ob7.put("in_row_slno", cursorsal.getString(10));
                                ob7.put("in_entity_value", cursorsal.getString(6));
                                ob7.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob8 = new JSONObject();
                            try {
                                ob8.put("in_farmerdetail_rowid", 0);
                                ob8.put("in_entitygrp_code", "EC_SALE");
                                ob8.put("in_entity_code", "EC_SALE_Buyer");
                                ob8.put("in_row_slno", cursorsal.getString(10));
                                ob8.put("in_entity_value", cursorsal.getString(7));
                                ob8.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob9 = new JSONObject();
                            try {
                                ob9.put("in_farmerdetail_rowid", 0);
                                ob9.put("in_entitygrp_code", "EC_SALE");
                                ob9.put("in_entity_code", "EC_SALE_Terms");
                                ob9.put("in_row_slno", cursorsal.getString(10));
                                ob9.put("in_entity_value", cursorsal.getString(8));
                                ob9.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob10 = new JSONObject();
                            try {
                                ob10.put("in_farmerdetail_rowid", 0);
                                ob10.put("in_entitygrp_code", "EC_SALE");
                                ob10.put("in_entity_code", "EC_SALE_Remark");
                                ob10.put("in_row_slno", cursorsal.getString(10));
                                ob10.put("in_entity_value", cursorsal.getString(9));
                                ob10.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            JSONArray jsonArraysal = new JSONArray();

                            jsonArraysal.put(ob1);
                            jsonArraysal.put(ob2);
                            jsonArraysal.put(ob3);
                            jsonArraysal.put(ob4);
                            jsonArraysal.put(ob5);
                            jsonArraysal.put(ob6);
                            jsonArraysal.put(ob7);
                            jsonArraysal.put(ob8);
                            jsonArraysal.put(ob9);
                            jsonArraysal.put(ob10);


                            jsonArray18.put(jsonArraysal);
                        } while (cursorsal.moveToNext());

                        user2.put("Mfarmersale", jsonArray18);
                    }

                    JSONArray jsonArray19 = new JSONArray();
                    Cursor cursorpdu = dbs.query("production", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8","v9"
                            }, "v8" + "=?",
                            new String[]{fn}, null, null, null, null);

                    Log.e("COUNTt", "" + cursorpdu.getCount());
                    if (cursorpdu.moveToFirst()) {

                        do {

                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_PRODUCTION");
                                ob1.put("in_entity_code", "EC_PROD_Year");
                                ob1.put("in_row_slno", cursorpdu.getString(5));
                                ob1.put("in_entity_value", cursorpdu.getString(0));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_PRODUCTION");
                                ob2.put("in_entity_code", "EC_PROD_Season");
                                ob2.put("in_row_slno", cursorpdu.getString(5));
                                ob2.put("in_entity_value", cursorpdu.getString(1));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_PRODUCTION");
                                ob3.put("in_entity_code", "EC_PROD_Croptype");
                                ob3.put("in_row_slno", cursorpdu.getString(5));
                                ob3.put("in_entity_value", cursorpdu.getString(2));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_PRODUCTION");
                                ob4.put("in_entity_code", "EC_PROD_Variety");
                                ob4.put("in_row_slno", cursorpdu.getString(5));
                                ob4.put("in_entity_value", cursorpdu.getString(3));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob5 = new JSONObject();
                            try {
                                ob5.put("in_farmerdetail_rowid", 0);
                                ob5.put("in_entitygrp_code", "EC_PRODUCTION");
                                ob5.put("in_entity_code", "EC_PROD_ActualProduction");
                                ob5.put("in_row_slno", cursorpdu.getString(5));
                                ob5.put("in_entity_value", cursorpdu.getString(4));
                                ob5.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }


                            JSONArray jsonArraypdu = new JSONArray();

                            jsonArraypdu.put(ob1);
                            jsonArraypdu.put(ob2);
                            jsonArraypdu.put(ob3);
                            jsonArraypdu.put(ob4);
                            jsonArraypdu.put(ob5);

                            jsonArray19.put(jsonArraypdu);
                        } while (cursorpdu.moveToNext());

                        user2.put("Mfarmerproduction", jsonArray19);
                    }

                    JSONArray jsonArray20 = new JSONArray();
                    Cursor cursorip = dbs.query("input", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8","v9","v10","v11","v12","v13"
                            }, "v15" + "=?",
                            new String[]{fn}, null, null, null, null);

                    // Log.e("COUNTt", "" + cursorpdu.getCount());
                    if (cursorip.moveToFirst()) {

                        do {
                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_INPUTS");
                                ob1.put("in_entity_code", "EC_INP_Year");
                                ob1.put("in_row_slno", cursorip.getString(12));
                                ob1.put("in_entity_value", cursorip.getString(0));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_INPUTS");
                                ob2.put("in_entity_code", "EC_INP_Season");
                                ob2.put("in_row_slno", cursorip.getString(12));
                                ob2.put("in_entity_value", cursorip.getString(1));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_INPUTS");
                                ob3.put("in_entity_code", "EC_INP_CropType");
                                ob3.put("in_row_slno", cursorip.getString(12));
                                ob3.put("in_entity_value", cursorip.getString(2));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_INPUTS");
                                ob4.put("in_entity_code", "EC_INP_Variety");
                                ob4.put("in_row_slno", cursorip.getString(12));
                                ob4.put("in_entity_value", cursorip.getString(3));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob5 = new JSONObject();
                            try {
                                ob5.put("in_farmerdetail_rowid", 0);
                                ob5.put("in_entitygrp_code", "EC_INPUTS");
                                ob5.put("in_entity_code", "EC_INP_ICName");
                                ob5.put("in_row_slno", cursorip.getString(12));
                                ob5.put("in_entity_value", cursorip.getString(4));
                                ob5.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob6 = new JSONObject();
                            try {
                                ob6.put("in_farmerdetail_rowid", 0);
                                ob6.put("in_entitygrp_code", "EC_INPUTS");
                                ob6.put("in_entity_code", "EC_INP_Type");
                                ob6.put("in_row_slno", cursorip.getString(12));
                                ob6.put("in_entity_value", cursorip.getString(5));
                                ob6.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob7 = new JSONObject();
                            try {
                                ob7.put("in_farmerdetail_rowid", 0);
                                ob7.put("in_entitygrp_code", "EC_INPUTS");
                                ob7.put("in_entity_code", "EC_INPUTS");
                                ob7.put("in_row_slno", cursorip.getString(12));
                                ob7.put("in_entity_value", cursorip.getString(6));
                                ob7.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob8 = new JSONObject();
                            try {
                                ob8.put("in_farmerdetail_rowid", 0);
                                ob8.put("in_entitygrp_code", "EC_INPUTS");
                                ob8.put("in_entity_code", "EC_INP_UsedDate");
                                ob8.put("in_row_slno", cursorip.getString(12));
                                ob8.put("in_entity_value", cursorip.getString(7));
                                ob8.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob9 = new JSONObject();
                            try {
                                ob9.put("in_farmerdetail_rowid", 0);
                                ob9.put("in_entitygrp_code", "EC_INPUTS");
                                ob9.put("in_entity_code", "EC_INP_Qty");
                                ob9.put("in_row_slno", cursorip.getString(12));
                                ob9.put("in_entity_value", cursorip.getString(8));
                                ob9.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob10 = new JSONObject();
                            try {
                                ob10.put("in_farmerdetail_rowid", 0);
                                ob10.put("in_entitygrp_code", "EC_INPUTS");
                                ob10.put("in_entity_code", "EC_INP_Rate");
                                ob10.put("in_row_slno", cursorip.getString(12));
                                ob10.put("in_entity_value", cursorip.getString(9));
                                ob10.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob11 = new JSONObject();
                            try {
                                ob11.put("in_farmerdetail_rowid", 0);
                                ob11.put("in_entitygrp_code", "EC_INPUTS");
                                ob11.put("in_entity_code", "EC_INP_Amount");
                                ob11.put("in_row_slno", cursorip.getString(12));
                                ob11.put("in_entity_value", cursorip.getString(10));
                                ob11.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            JSONObject ob12 = new JSONObject();
                            try {
                                ob12.put("in_farmerdetail_rowid", 0);
                                ob12.put("in_entitygrp_code", "EC_INPUTS");
                                ob12.put("in_entity_code", "EC_INP_Remarks");
                                ob12.put("in_row_slno", cursorip.getString(12));
                                ob12.put("in_entity_value", cursorip.getString(11));
                                ob12.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            JSONArray jsonArrayip = new JSONArray();

                            jsonArrayip.put(ob1);
                            jsonArrayip.put(ob2);
                            jsonArrayip.put(ob3);
                            jsonArrayip.put(ob4);
                            jsonArrayip.put(ob5);
                            jsonArrayip.put(ob6);
                            jsonArrayip.put(ob7);
                            jsonArrayip.put(ob8);
                            jsonArrayip.put(ob9);
                            jsonArrayip.put(ob10);
                            jsonArrayip.put(ob11);
                            jsonArrayip.put(ob12);


                            jsonArray20.put(jsonArrayip);
                        } while (cursorip.moveToNext());

                        user2.put("MfarmerInputs", jsonArray20);
                    }
                    JSONArray jsonArray21 = new JSONArray();
                    Cursor cursorlq = dbs.query("loanq", new String[]{"v1","v2","v3","v4","v5","v6","v7","v8"
                            }, "v7" + "=?",
                            new String[]{fn}, null, null, null, null);

                    // Log.e("COUNTt", "" + cursorpdu.getCount());
                    if (cursorlq.moveToFirst()) {

                        do {
                            JSONObject ob1 = new JSONObject();
                            try {
                                ob1.put("in_farmerdetail_rowid", 0);
                                ob1.put("in_entitygrp_code", "EC_LOAN_REQUIRMENT");
                                ob1.put("in_entity_code", "EC_LOAN_REQ_TYPE");
                                ob1.put("in_row_slno", cursorlq.getString(4));
                                ob1.put("in_entity_value", cursorlq.getString(0));
                                ob1.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob2 = new JSONObject();
                            try {
                                ob2.put("in_farmerdetail_rowid", 0);
                                ob2.put("in_entitygrp_code", "EC_LOAN_REQUIRMENT");
                                ob2.put("in_entity_code", "EC_LOAN_REQ_AMOUNT");
                                ob2.put("in_row_slno", cursorlq.getString(4));
                                ob2.put("in_entity_value", cursorlq.getString(1));
                                ob2.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob3 = new JSONObject();
                            try {
                                ob3.put("in_farmerdetail_rowid", 0);
                                ob3.put("in_entitygrp_code", "EC_LOAN_REQUIRMENT");
                                ob3.put("in_entity_code", "EC_LOAN_REQ_DESCRIPITION");
                                ob3.put("in_row_slno", cursorlq.getString(4));
                                ob3.put("in_entity_value", cursorlq.getString(2));
                                ob3.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JSONObject ob4 = new JSONObject();
                            try {
                                ob4.put("in_farmerdetail_rowid", 0);
                                ob4.put("in_entitygrp_code", "EC_LOAN_REQUIRMENT");
                                ob4.put("in_entity_code", "EC_LOAN_REQ_REQUIRED");
                                ob4.put("in_row_slno", cursorlq.getString(4));
                                ob4.put("in_entity_value", cursorlq.getString(3));
                                ob4.put("in_mode_flag", "I");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }


                            JSONArray jsonArraylq = new JSONArray();

                            jsonArraylq.put(ob1);
                            jsonArraylq.put(ob2);
                            jsonArraylq.put(ob3);
                            jsonArraylq.put(ob4);


                            jsonArray21.put(jsonArraylq);
                        } while (cursorlq.moveToNext());

                        user2.put("Mfarmerloanreq", jsonArray21);
                    }
                    Log.e("OUTPUT", "" + fn + jsonParam.toString());
                }

            } catch (Exception e) {
                Log.e("OUTPUT", "" + e.getMessage());

            }
            HttpsTrustManager.allowAllSSL();
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/Mobile_FDR_Offlinesave/MFarmerProfileSave", jsonParam,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.e("CCCC", "" + response);

                            try {


                                if (response.getString("in_farmer_rowid").equalsIgnoreCase("0")) {

//                                pdialog.dismiss();
//                                final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
////set icon
//                                        .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                        .setTitle("Error!")
////set message
//                                        .setMessage("Transactions Unable To Complete Because!"+response.getString("error_msg")+"//farmer Name:"+cc.getString(1))
////set positive button
//                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                                        //set what would happen when positive button is clicked
//                                                        // finish();
//                                                        // ms();
//                                                        try {
//                                                            offc2++;
//                                                            final SQLiteDatabase dbs = mydb.getWritableDatabase();
//
//
//                                                               dbs.execSQL("DELETE FROM farmerh WHERE id = " + fmid);
//                                                           // mydb.inserfarlist(response.getString("in_farmer_rowid").toString(), response.getString("in_farmer_code").toString(), n1, "0", n10, n11, n12, n13);
//
//                                                            String selectQuery5 = "SELECT  * FROM farmerh where flag = 0";
//                                                            Cursor cc = dbs.rawQuery(selectQuery5, null);
//
//                                                            if (cc.getCount() != 0) {
//
//                                                                offlinesave();
//
//
//                                                            }
//                                                        }
//                                                        catch(Exception e)
//                                                        {
//
//                                                        }
//
//                                                    }
//                                                }
////set negative button
//
//                                        )
//                                        .show();


                                    try {
                                        offc2++;
                                        final SQLiteDatabase dbs = mydb.getWritableDatabase();
                                        // Toast.makeText(Landpage.this, ""+offc+"/"+offc2, Toast.LENGTH_SHORT).show();

                                        //dbs.execSQL("UPDATE farlist SET v5 = 0 WHERE id = " + fmid);
                                        dbs.execSQL("UPDATE farmerh SET flag = 1 WHERE id = " + fmid);
                                        // dbs.execSQL("DELETE FROM farmerh WHERE id = " + fmid);
                                        mydb.inserfarlist("0", "DUPLICATE ENTRY", n1, n2n, n10, n11, n12, n13,n2,n3,n4,n5,"0","0","0","0");

                                        String selectQuery5 = "SELECT  * FROM farmerh where flag = 0";
                                        Cursor cc = dbs.rawQuery(selectQuery5, null);

                                        if (cc.getCount() != 0) {

                                            offlinesave();


                                        }
                                        if (offc == offc2)
                                        {
                                            pdialog.dismiss();
                                            String selectQuery6 = "SELECT  * FROM farlist where v5 = 0";
                                            Cursor cc6 = dbs.rawQuery(selectQuery6, null);
                                            final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                                    .setTitle("Success!")
//set message
                                                    .setMessage("Transaction Completed!DUPLICATE FARMER: "+cc6.getCount())
//set positive button
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
//                                                                                        String selectQuery5 = "SELECT  * FROM farmerh where v5 = 0";
//                                                                                        Cursor cc = dbs.rawQuery(selectQuery5, null);
//                                                                                        Log.e("CNT","OFF"+cc.getCount());
                                                                    SharedPreferences.Editor editor = sharedpreferences.edit();
//
                                                                    editor.putString("fcode", "");
                                                                    editor.putString("fid", "");
                                                                    offc = 0;
                                                                    offc2 = 0;
                                                                    editor.commit();
                                                                }
                                                            }
//set negative button

                                                    )
                                                    .show();
                                        }
                                    }
                                    catch(Exception e)
                                    {

                                    }


                                } else {


                                    afcode = response.getString("in_farmer_code");
                                    Cursor cursoraddr = dbs.query("ads", new String[]{"id"
                                            }, "v93" + "=?",
                                            new String[]{adsfid}, null, null, null, null);
//                                    if(cursoraddr.moveToFirst()) {
//                                        saveads(cursoraddr.getString(0));
//                                    }
                                    offc2++;
                                    final SQLiteDatabase dbs = mydb.getWritableDatabase();
                                    dbs.execSQL("UPDATE farmerh SET flag = 1 WHERE id = " + farid);
                                    mydb.inserfarlist(response.getString("in_farmer_rowid").toString(), response.getString("in_farmer_code").toString(), n1, n2n, n10, n11, n12, n13,n2,n3,n4,n5,"1","0","0","0");

                                    String selectQuery5 = "SELECT  * FROM farmerh where flag = 0";
                                    Cursor cc = dbs.rawQuery(selectQuery5, null);

                                    if (cc.getCount() != 0) {

                                        offlinesave();


                                    }


//                        try {
//                            JSONObject obj = response.getJSONObject("context");
//                            JSONObject obj2 = obj.getJSONObject("Header");
//
//                            String inrid = obj2.getString("IOU_invoice_rowid");
//                            Log.e("CCCC","Hi"+inrid);
//


                                    if (offc == offc2) {
                                        pdialog.dismiss();

                                        String selectQuery6 = "SELECT  * FROM farlist where v5 = 0";
                                        Cursor cc6 = dbs.rawQuery(selectQuery6, null);
                                        final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                                .setTitle("Success!")
//set message
                                                .setMessage("Transaction Completed!DUPLICATE FARMER:"+cc6.getCount())
//set positive button
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                            String selectQuery5 = "SELECT  * FROM farmerh where v5 = 0";
//                                                            Cursor cc = dbs.rawQuery(selectQuery5, null);
//                                                            Log.e("CNT","OFF"+cc.getCount());
                                                                SharedPreferences.Editor editor = sharedpreferences.edit();
//
                                                                editor.putString("fcode", "");
                                                                editor.putString("fid", "");
                                                                offc = 0;
                                                                offc2 = 0;
                                                                editor.commit();
                                                            }
                                                        }
//set negative button

                                                )
                                                .show();
                                    }

//                        catch (Exception e)
//                        {
//
//                        }


                                    // Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_SHORT).show();
                                    //finish();


                                }
                            } catch (Exception e) {

                            }
                        }


                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("CCCC", "" + error);

                            // Toast.makeText(getApplicationContext(),"Unable to Insert",Toast.LENGTH_SHORT).show();
                            pdialog.dismiss();
                            final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Transaction Unable To Connect!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked

                                                }
                                            }
//set negative button

                                    )
                                    .show();
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
                    80000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }

    public void offlinesaveicd() {
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();
        
        String selectQuery5 = "SELECT  * FROM farmerh where flag = 0";
        final Cursor cc = dbs.rawQuery(selectQuery5, null);
        Log.e("NULL2", "" + cc.getCount());

        try {


            if (cc.moveToFirst()) {
                farid = cc.getString(0);
                Log.e("NULLK", "" + cc.getString(24));

                if (cc.getString(5).equalsIgnoreCase("male")) {
                    gf = "QCD_GENDER_MALE";
                }
                if (cc.getString(5).equalsIgnoreCase("female")) {
                    gf = "QCD_GENDER_FEMALE";
                }
                if (cc.getString(5).equalsIgnoreCase("transgender")) {
                    gf = "QCD_GENDER_TRANSGENDER";
                }


                jsonParam = new JSONObject();
                JSONObject userd = new JSONObject();
                jsonParam.put("document", userd);
                JSONObject user = new JSONObject();
                user.put("orgnId", "flexi");
                user.put("locnId", "chennai");
                user.put("userId", "fdrmob");
                user.put("localeId", "en_US");
                user.put("instance", Pojokyc.instance);
                userd.put("context", user);

                JSONObject user2 = new JSONObject();
                if (cc.getString(24).equalsIgnoreCase("0")) {
                    encodedImage = "0";
                } else {
                    try {

                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(cc.getString(24)));

                        byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        //Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());


                        encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                user2.put("in_farmer_rowid", 0);
                user2.put("in_farmer_code", "");
                user2.put("in_version_no", 1);
                user2.put("in_photo_farmer", encodedImage);

                fmid = cc.getString(0);

                n1 = cc.getString(1);

                n10 = cc.getString(10);
                n11 = cc.getString(11);
                n12 = cc.getString(12);
                n13 = cc.getString(13);

                adsfid = cc.getString(22);

                user2.put("in_farmer_name", cc.getString(1));
                user2.put("in_sur_name", cc.getString(2));
                user2.put("in_fhw_name", cc.getString(3));

                String[] dob = cc.getString(6).split("/");
                user2.put("in_farmer_dob",dob[2]+"/"+dob[1]+"/"+dob[0]);
                user2.put("in_farmer_addr1", cc.getString(9));
                user2.put("in_farmer_addr2", cc.getString(23));
                user2.put("in_farmer_country", "QCD_UN_IND");
                user2.put("in_farmer_state", "QCD_UNS_OR");

                user2.put("in_farmer_dist", cc.getString(21));
                user2.put("in_farmer_taluk", cc.getString(20));
                user2.put("in_farmer_panchayat", cc.getString(19));
                user2.put("in_farmer_village", cc.getString(18));
                user2.put("in_farmer_pincode", cc.getString(8));
                user2.put("in_marital_status", "");
                user2.put("in_gender_flag", gf);
                user2.put("in_reg_mobile_no", cc.getString(4));
                user2.put("in_status_code", "A");
                user2.put("in_mode_flag", "I");
                user2.put("in_row_timestamp", "DUMFRMMOB/"+adsfid);

                user2.put("in_fpo_orgncode", sharedpreferences.getString("oc1", ""));
                user2.put("in_created_by", sharedpreferences.getString("un", "").toUpperCase() + "" + sharedpreferences.getString("phn", ""));
                user2.put("in_modified_by", sharedpreferences.getString("un", "").toUpperCase() + "" + sharedpreferences.getString("phn", ""));
                user.put("Header", user2);

                Log.e("OUTPUT", "" + jsonParam.toString());
            }
        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());

        }
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "api/Mobile_FDR_FHeader/NewMobileFarmerHeader", jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        Log.e("CCCC", "" + response);

                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("header");

                            if (obj2.getString("in_farmer_rowid").equalsIgnoreCase("0")) {
                                pdialog.dismiss();


                                final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(Landpage.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Transactions Unable To Complete Because!" + obj2.getString("error_msg") + "//farmer Name:" + cc.getString(1))
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        // finish();
                                                        // ms();
                                                        try {



                                                            dbs.execSQL("DELETE FROM farmerh WHERE id = " + fmid);
                                                            // mydb.inserfarlist(response.getString("in_farmer_rowid").toString(), response.getString("in_farmer_code").toString(), n1, "0", n10, n11, n12, n13);

                                                            String selectQuery5 = "SELECT  * FROM farmerh where flag = 0";
                                                            Cursor cc = dbs.rawQuery(selectQuery5, null);

                                                            if (cc.getCount() != 0) {

                                                                offlinesave();


                                                            }
                                                        } catch (Exception e) {

                                                        }

                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                            } else {





                                dbs.execSQL("UPDATE farmerh SET flag = 1 WHERE id = " + fmid);

                                String selectQuery5h = "SELECT  * FROM farmerh where flag = 0";
                                Cursor cc = dbs.rawQuery(selectQuery5h, null);

                                if (cc.getCount() != 0) {

                                    offlinesave();


                                }
                                else
                                {

                                    String selectQuery = "SELECT  * FROM inwardlist where flag = 0";
                                    String selectQuery2 = "SELECT  * FROM invoicelist where flag = 0";
                                    String selectQuery3 = "SELECT  * FROM astock2";
                                    String selectQuery6 = "SELECT  * FROM itransfer";
                                    String selectQuery4 = "SELECT  * FROM invoice where flag = 0";
                                    String selectQuery5 = "SELECT  * FROM paylist where uflag = 0";
                                    String selectQuery7 = "SELECT  * FROM iconfirm WHERE v5 = 0";
                                    Cursor cursor6 = dbs.rawQuery(selectQuery7, null);

                                    Cursor cursor2 = dbs.rawQuery(selectQuery, null);
                                    Cursor cursor = dbs.rawQuery(selectQuery2, null);
                                    Cursor cursor3 = dbs.rawQuery(selectQuery3, null);
                                    Cursor cursorp = dbs.rawQuery(selectQuery4, null);
                                    Cursor cursorp2 = dbs.rawQuery(selectQuery5, null);
                                    Cursor cursor5 = dbs.rawQuery(selectQuery6, null);
                                    ct = cursor2.getCount();
                                    ctt = cursor.getCount();
                                    ct3 = cursor3.getCount();
                                    ct4 = cursorp2.getCount();
                                    ct5 = cursor5.getCount();
                                    ct6 = cursor6.getCount();
                                    finalcount = ct + ctt + ct3 +ct4+ct5+ct6;

                                    if(finalcount == 0)
                                    {
                                        pdialog.dismiss();
                                        final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                                .setIcon(android.R.drawable.ic_menu_save)
//set title
                                                .setTitle("Success!")
//set message
                                                .setMessage("Transaction Completed")
//set positive button
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                //set what would happen when positive button is clicked

                                                            }
                                                        }
//set negative button

                                                )
                                                .show();
                                    }
                                    else
                                    {
                                        savetrs();
                                    }

                                }


//                        try {
//                            JSONObject obj = response.getJSONObject("context");
//                            JSONObject obj2 = obj.getJSONObject("Header");
//
//                            String inrid = obj2.getString("IOU_invoice_rowid");
//                            Log.e("CCCC","Hi"+inrid);
//




//                        catch (Exception e)
//                        {
//
//                        }


                                // Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_SHORT).show();
                                //finish();


                            }
                        } catch (Exception e) {

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
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                80000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    public void savetrs()
    {
        String selectQuery = "SELECT  * FROM inwardlist where flag = 0";
        String selectQuery2 = "SELECT  * FROM invoicelist where flag = 0";
        String selectQuery3 = "SELECT  * FROM astock2";
        String selectQuery6 = "SELECT  * FROM itransfer";
        String selectQuery4 = "SELECT  * FROM invoice where flag = 0";

        String selectQuery7 = "SELECT  * FROM iconfirm  WHERE v5 = 0";
        Cursor cursor6 = dbs.rawQuery(selectQuery7, null);

        Cursor cursor2 = dbs.rawQuery(selectQuery, null);
        Cursor cursor = dbs.rawQuery(selectQuery2, null);
        Cursor cursor3 = dbs.rawQuery(selectQuery3, null);
        Cursor cursorp = dbs.rawQuery(selectQuery4, null);

        Cursor cursor5 = dbs.rawQuery(selectQuery6, null);
        ct = cursor2.getCount();
        ctt = cursor.getCount();
        ct3 = cursor3.getCount();
       // ct4 = cursorp2.getCount();
        ct5 = cursor5.getCount();
        ct6 = cursor6.getCount();
        finalcount = ct + ctt + ct3+ct5+ct6;

        if(isNetworkAvailable())
        {
            if (finalcount != 0) {
                pdialog.setCanceledOnTouchOutside(false);
                pdialog.setTitle("Uploading Loading.....");
                pdialog.setMessage("");
                pdialog.show();
                if (ct == 0) {

                } else {


                    if (cursor2.moveToFirst()) {

                        id = cursor2.getString(0);
                        inwid = cursor2.getString(1);
                        sname = cursor2.getString(3);
                        slcn = cursor2.getString(4);
                        sst = cursor2.getString(4);
                        net = cursor2.getString(8);
                        tax = cursor2.getString(5);
                        orgnnew = cursor2.getString(11);
                        if(cursor2.getString(6).equalsIgnoreCase(""))
                        {
                            trs = "0";
                        }
                        else
                        {
                            trs = cursor2.getString(6);
                        }
                        if(cursor2.getString(7).equalsIgnoreCase(""))
                        {
                            oth = "0";
                        }
                        else
                        {
                            oth = cursor2.getString(7);
                        }
                        ids = cursor2.getString(9);
                        ids2 = ids.substring(1, ids.length() - 1);
                        lc = cursor2.getString(14);
                        billno = cursor2.getString(16);

                        //Toast.makeText(Landpage.this, ""+ids, Toast.LENGTH_SHORT).show();
                        if(cursor2.getString(13).equalsIgnoreCase("0"))
                        {
                            saveinward();
                        }
                        else {
                            final Uri imageUri = Uri.parse(cursor2.getString(13));
                            final InputStream imageStream;
                            try {
                                imageStream = getContentResolver().openInputStream(imageUri);
                                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                                encodedimage = encodeImage(selectedImage);
                                saveinward();


                                ct2++;
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }


                    }


                }
                if (cursor.getCount() == 0) {

                } else {

                    if (cursor.moveToFirst()) {

                        id2 = cursor.getString(0);
                        payinvoiceno = cursor.getString(1);
                        sname2 = cursor.getString(3);
                        slcn2 = cursor.getString(4);
                        sst2 = cursor.getString(4);
                        net2 = cursor.getString(8);
                        tax2 = cursor.getString(5);
                        orgnnew2 = cursor.getString(11);
                        if(cursor.getString(6).equalsIgnoreCase(""))
                        {
                            trs2 = "0";
                        }
                        else
                        {
                            trs2 = cursor.getString(6);
                        }
                        if(cursor.getString(7).equalsIgnoreCase(""))
                        {
                            oth2 = "0";
                        }
                        else
                        {
                            oth2 = cursor.getString(7);
                        }
                        ids22 = cursor.getString(9);
                        lc2 = cursor.getString(13);
                        mcode = cursor.getString(17);
                        phn = cursor.getString(16);
                        ids222 = ids22.substring(1, ids22.length() - 1);
                        // Toast.makeText(Landpage.this, ""+ids22, Toast.LENGTH_SHORT).show();
                        Cursor cursori = dbs.query("invoice", new String[]{"balance",
                                }, "invoiceno" + "=?",
                                new String[]{cursor.getString(1)}, null, null, null, null);
                        if (cursori.moveToFirst()) {
                            do {
                                // idn = cursor2.getString(2);
                                //Toast.makeText(Landpage.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

                                balamt = cursori.getString(0);


                            }
                            while (cursori.moveToNext());


                        }

                        Cursor cursorfc =  dbs.query("customerlist", new String[]{"farmer_code"
                                }, "phone" + "=?" + " and " + "farmer_name" + "=? COLLATE NOCASE",
                                new String[]{phn,sname2}, null, null, null, null);
                        if(cursorfc.getCount() == 0)
                        {
                            farco = "0";
                        }

                        if (cursorfc.moveToFirst()) {
                            do {

                                farco = cursorfc.getString(0);
                            }
                            while (cursorfc.moveToNext());


                        }
                        //Toast.makeText(Landpage.this, ""+ids, Toast.LENGTH_SHORT).show();

                        saveinvoice();




                    }
                    if (cursor3.getCount() == 0) {

                    }
                }
                if (cursor3.getCount() == 0) {

                } else {


                    if (cursor3.moveToFirst()) {


                        aqid = cursor3.getString(0);

                        aqi = cursor3.getString(1);
                        Log.e("HARI", "" + aqi);
                        aqi2 = aqi.substring(1, aqi.length() - 1);
                        aqd = cursor3.getString(6);
                        orgnew3 = cursor3.getString(7);
                        savetransfer();

                    }

                }

                if (cursor5.getCount() == 0) {

                } else {


                    if (cursor5.moveToFirst()) {


                        itid = cursor5.getString(0);
                        pcode = cursor5.getString(2);
                        itorg = cursor5.getString(1);
                        tstock = cursor5.getString(3);



                        saveistrs();

                    }

                }

                if (cursor6.getCount() == 0) {

                } else {


                    if (cursor6.moveToFirst()) {


                        icid = cursor6.getString(0);
                        pcode2 = cursor6.getString(2);
                        icgrn = cursor6.getString(1);
                        icqty = cursor6.getString(3);
                        icrmk = cursor6.getString(4);



                        saveiscnf();

                    }

                }

                // Toast.makeText(Landpage.this, ""+cursor2.getCount(), Toast.LENGTH_SHORT).show();\

            }
            else
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("No Data Available to Upload")
//set positive button
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //set what would happen when positive button is clicked

                                    }
                                }
//set negative button

                        )
                        .show();
            }
        }
        else
        {
            final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                    .setTitle("Error!")
//set message
                    .setMessage("No Internet Connections Available")
//set positive button
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //set what would happen when positive button is clicked

                                }
                            }
//set negative button

                    )
                    .show();
        }
    }
    public void saveinward()
    {



        try {
            if(billno.toString().equalsIgnoreCase("null"))
            {
                billno2="0";
            }
            else
            {
                billno2 = billno;
            }

            DecimalFormat amountFormate  = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(cc);
            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", orgnnew);
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc",""));
            user.put("localeId", "en_US");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("IOU_inward_rowid","0");
            user2.put("In_orgn_code",orgnnew);
            user2.put("In_ic_code",orgnnew);
            user2.put("In_inward_code",billno2);

            user2.put("In_grn_no","");
            user2.put("In_grn_date",formattedDate);
            user2.put("In_supplier_name",sname);
            user2.put("In_supplier_location",sst);
            user2.put("In_from_state",lc);
            user2.put("In_Remarks","Inward From Mobile");
            user2.put("In_status_code","A");
            user2.put("In_process_status","QCD_INWARD_MOBILE ");
            user2.put("In_row_timestamp",formattedDate);
            user2.put("In_mode_flag","I");
            user2.put("In_bill_image",encodedimage);
            user2.put("In_transport_amount","0");
            if(trs.equalsIgnoreCase(""))
            {
                user2.put("In_roundoff","0");
            }
            else
            {
                user2.put("In_roundoff",trs);
            }
            user2.put("In_others","0");
            user2.put("In_loading_unloading_cost","0");
            user2.put("In_local_transport_cost","0");
            user2.put("In_local_loading_unloading_cost","0");


            user.put("Header",user2);

            String[] id = ids2.split(",");
            JSONArray notebookUsers2 = new JSONArray();
            for(int i = 0 ; i< id.length;i++)
            {
                final SQLiteDatabase dbs = mydb.getWritableDatabase();
                String selectQuery = "SELECT  * FROM productlist where id ="+id[i];
                Cursor cursor2 = dbs.rawQuery(selectQuery, null);
                if (cursor2.moveToFirst()) {
                    do {
                        pn = cursor2.getString(1);
                        pr = cursor2.getString(3);
                        pq = cursor2.getString(2);
                        pd = cursor2.getString(5);
                        amt = cursor2.getString(6);
                        myr = cursor2.getString(4);
                        tspr = cursor2.getString(9);

                        Cursor cursor3 = dbs.query("product", new String[]{"In_product_code",
                                        "In_productcategory_code","In_productsubcategory_code","In_uomtype_code","In_cgst","In_sgst","In_current_qty","id","In_igst"}, "In_product_name" + "=?",
                                new String[]{pn}, null, null, null, null);

                        if (cursor3.moveToFirst()) {
                            do {
                                DecimalFormat format = new DecimalFormat("##########.##");
                                pco = cursor3.getString(0);
                                pc = cursor3.getString(1);
                                psc = cursor3.getString(2);
                                pu = cursor3.getString(3);
                                Log.e("CLL",""+lc+"//"+sharedpreferences.getString("lo",""));

                                if(lc.equalsIgnoreCase(sharedpreferences.getString("lo",""))) {
                                    double c = Float.parseFloat(cursor3.getString(4));
                                    double s = Float.parseFloat(cursor3.getString(5));
                                    double rl = Double.parseDouble(amt);

                                    double cg = (c / 100) * rl;
                                    double sg = (s / 100) * rl;
                                    double f = cg + sg;
                                    pt = format.format(f).toString();
                                    double x = Double.parseDouble(pt);
                                    double y = Double.parseDouble(amt);

                                    double z = x + y;
                                    pa = format.format(z).toString();
                                }
                                else {
                                    double c = Float.parseFloat(cursor3.getString(8));

                                    double rl = Double.parseDouble(amt);

                                    double cg = (c / 100) * rl;

                                    double f = cg ;
                                    pt = format.format(f).toString();
                                    double x = Double.parseDouble(pt);
                                    double y = Double.parseDouble(amt);

                                    double z = x + y;
                                    pa = format.format(z).toString();

                                }


                            }

                            while (cursor3.moveToNext());
                        }
                    }

                    while (cursor2.moveToNext());
                }

                JSONObject user4 = new JSONObject();
                user4.put("In_inwarddtl_rowid",0);
                user4.put("In_inward_code",billno2);
                user4.put("In_grn_no","0");
                user4.put("In_product_catg_code",pc);
                user4.put("In_product_subcatg_code",psc);
                user4.put("In_product_code",pco);
                user4.put("In_uomtype_code",pu);
                user4.put("In_batch_no","");
                user4.put("In_received_qty",pq);
                user4.put("In_product_amount",pr);
                user4.put("In_tax_amount",""+pt);
                user4.put("In_transport_amount",0);
                if(pd.equalsIgnoreCase(""))
                {
                    user4.put("In_discount", "0");
                }
                else {
                    user4.put("In_discount", pd);
                }
                user4.put("In_net_amount",""+pa);
                user4.put("In_status_code","A");
                user4.put("In_mode_flag","I");
                Cursor cursor = dbs.query("sno", new String[]{"v1","v2","v3"
                        }, "v3" + "=? COLLATE NOCASE",
                        new String[]{tspr}, null, null, null, null);
                Log.e("COUNT", "" + cursor.getCount());




                JSONArray notebookUserssno = new JSONArray();
                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        JSONObject usersno = new JSONObject();
                        usersno.put("In_inwardslno_rowid","0");
                        usersno.put("In_inwarddtl_rowid",0);
                        usersno.put("In_slno",cursor.getString(0));
                        usersno.put("In_no_of_bags","");

                        usersno.put("In_grn_no","");
                        usersno.put("In_product_catg_code","");
                        usersno.put("In_product_subcatg_code","");
                        usersno.put("In_product_code","");
                        usersno.put("In_status_code","");
                        usersno.put("In_mode_flag","I");
                        notebookUserssno.put(usersno);

                        ///String[] pn = cursor.getString(1).split("-");
                        //


                    } while (cursor.moveToNext());

                }

                user4.put("Slnoinfo",notebookUserssno);


                Cursor cursoroc = dbs.query("icdoc", new String[]{"v1","v2","v3","v4","v5","v6"
                        }, "v1" + "=? COLLATE NOCASE",
                        new String[]{tspr}, null, null, null, null);
                Log.e("COUNT", "" + cursoroc.getCount());




                JSONArray otheroc = new JSONArray();
                // looping through all rows and adding to list
                if (cursoroc.moveToFirst()) {
                    do {
                        JSONObject usersno = new JSONObject();
                        usersno.put("In_inwardOtherCost_rowid","0");
                        usersno.put("In_inwarddtl_rowid",0);
                        usersno.put("In_OtherCostCode",cursoroc.getString(2));
                        usersno.put("In_OtherCostType",cursoroc.getString(4));

                        usersno.put("In_OtherCostOn",cursoroc.getString(3));
                        usersno.put("In_OtherCostAmount",cursoroc.getString(5));
                        usersno.put("In_grn_no","");
                        usersno.put("In_product_catg_code","");
                        usersno.put("In_product_subcatg_code","");
                        usersno.put("In_product_code","");
                        usersno.put("In_status_code","");
                        usersno.put("In_mode_flag","I");
                        otheroc.put(usersno);

                        ///String[] pn = cursor.getString(1).split("-");
                        //


                    } while (cursoroc.moveToNext());

                }
                user4.put("OtherCostInfo",otheroc);


                Cursor cursorip = dbs.query("icdip", new String[]{"v1","v2","v3","v4","v5","v6","v7"
                        }, "v1" + "=? COLLATE NOCASE",
                        new String[]{tspr}, null, null, null, null);
                Log.e("COUNT", "" + cursorip.getCount());




                JSONArray otherip = new JSONArray();
                // looping through all rows and adding to list
                if (cursorip.moveToFirst()) {
                    do {
                        JSONObject usersno = new JSONObject();
                        usersno.put("In_inwardOtherinput_rowid","0");
                        usersno.put("In_inwarddtl_rowid",0);
                        usersno.put("In_NoOfLoosePack",Integer.parseInt(cursorip.getString(1)));
                        usersno.put("In_CartonVolume",Integer.parseInt(cursorip.getString(2)));
                        usersno.put("In_Type",cursorip.getString(3));
                        String[] d2 = cursorip.getString(4).split("/");
                        String[] d3 = cursorip.getString(5).split("/");
                        usersno.put("In_ManufactureDate",d2[2]+"-"+d2[1]+"-"+d2[0]);
                        usersno.put("In_ExpDate",d3[2]+"-"+d3[1]+"-"+d3[0]);
                        usersno.put("In_Mrp",Double.parseDouble(cursorip.getString(6)));
                        usersno.put("In_mode_flag","I");
                        otherip.put(usersno);

                        ///String[] pn = cursor.getString(1).split("-");
                        //


                    } while (cursorip.moveToNext());

                }
                user4.put("OtherInputInfo",otherip);

                notebookUsers2.put(user4);
            }



            user.put("Detail",notebookUsers2);

            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUTIMWARD",""+Log.getStackTraceString(e));
        }




        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.icdurl+"api/ICDMOBInward/newSaveStockInward",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);

                        final SQLiteDatabase dbs = mydb.getWritableDatabase();
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("Header");

                            String inrid = obj2.getString("IOU_inward_rowid");
                            String inrno = obj2.getString("In_grn_no");

                            Log.e("CCCC", "Hi" + inrid);

                            if (inrid.equalsIgnoreCase("0")) {
                                pdialog.dismiss();
                                // dbs.execSQL("DELETE FROM inwardlist WHERE id = " + id);
                                // Toast.makeText(Stockinwardnew.this, "Unable To Insert", Toast.LENGTH_SHORT).show();
                                final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Error Inserting Inward !")
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked

                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                                FirebaseApp.initializeApp(Landpage.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis()/1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                Log.e("Valuecheck",""+mRef.getRef());
                                mRef.child("name").setValue("SAVEINWARD");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("OFFLINE");
                            } else {
                                fcnt++;

                                if(fcnt == finalcount)
                                {
                                    //  dbs.execSQL("UPDATE productlist SET flag = 1 WHERE flag = 0");
                                    pdialog.dismiss();
                                    final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                                            .setTitle("Success!")
//set message
                                            .setMessage("Transactions Completed!")
//set positive button
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            //set what would happen when positive button is clicked
                                                            // dbs.execSQL("UPDATE invoicelist SET inweb = " + inrno + " WHERE id = " + id2);

                                                            // finish();
                                                            ms();
                                                        }
                                                    }
//set negative button

                                            )
                                            .show();
                                }
                                // Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                                dbs.execSQL("UPDATE inwardlist SET flag = 1 WHERE id = " + id);
                                Log.e("CCCC", "Inward");
                                //finish();

                                // einwno.setText(""+inrno);

                                // mydb.upinward(Integer.valueOf(id),inrno);
                                String selectQueryn = "SELECT  * FROM inwardlist where flag = 0";
                                Cursor cursor2 = dbs.rawQuery(selectQueryn, null);
                                if (cursor2.moveToFirst()) {

                                    id = cursor2.getString(0);
                                    inwid = cursor2.getString(1);
                                    sname = cursor2.getString(3);
                                    slcn = cursor2.getString(4);
                                    sst = cursor2.getString(4);
                                    net = cursor2.getString(8);
                                    tax = cursor2.getString(5);
                                    orgnnew = cursor2.getString(11);
                                    if(cursor2.getString(6).equalsIgnoreCase(""))
                                    {
                                        trs = "0";
                                    }
                                    else
                                    {
                                        trs = cursor2.getString(6);
                                    }
                                    if(cursor2.getString(7).equalsIgnoreCase(""))
                                    {
                                        oth = "0";
                                    }
                                    else
                                    {
                                        oth = cursor2.getString(7);
                                    }

                                    ids = cursor2.getString(9);
                                    ids2 = ids.substring(1, ids.length() - 1);
                                    lc = cursor2.getString(14);
                                    billno = cursor2.getString(16);

                                    //Toast.makeText(Landpage.this, ""+ids, Toast.LENGTH_SHORT).show();
                                    if(cursor2.getString(13).equalsIgnoreCase("0"))
                                    {
                                        saveinward();
                                    }
                                    else {
                                        final Uri imageUri = Uri.parse(cursor2.getString(13));
                                        final InputStream imageStream;
                                        try {
                                            imageStream = getContentResolver().openInputStream(imageUri);
                                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                                            encodedimage = encodeImage(selectedImage);
                                            saveinward();


                                            ct2++;
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }







                                }
//                                final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
////set icon
//                                        .setIcon(android.R.drawable.ic_menu_save)
////set title
//                                        .setTitle("Success!")
////set message
//                                        .setMessage("Inward Inserted !" +
//                                  cast              "Inward No :"+inrno)
////set positive button
//                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                                        //set what would happen when positive button is clicked
//                                                        // dbs.execSQL("UPDATE invoicelist SET inweb = " + inrno + " WHERE id = " + id2);
//
//                                                        finish();
//                                                    }
//                                                }
////set negative button
//
//                                        )
//                                        .show();

//                                String[] id = ids2.split(",");
//                                JSONArray notebookUsers2 = new JSONArray();
//                                for(int i = 0 ; i< id.length;i++)
//                                {
//
//                                    String selectQuery = "SELECT  * FROM productlist where id ="+id[i];
//                                    Cursor cursor2 = dbs.rawQuery(selectQuery, null);
//                                    if (cursor2.moveToFirst()) {
//                                        do {
//                                            pn = cursor2.getString(1);
//                                            pq = cursor2.getString(2);
//
//
//                                            Cursor cursor3 = dbs.query("product", new String[]{"In_product_code",
//                                                            "In_productcategory_code","In_productsubcategory_code","In_uomtype_code","In_cgst","In_sgst","In_current_qty","id"}, "In_product_name" + "=?",
//                                                    new String[]{pn}, null, null, null, null);
//
//                                            if (cursor3.moveToFirst()) {
//                                                do {
//
//                                                    String ii = cursor3.getString(7);
//                                                    double yy = Double.parseDouble(pq);
//                                                    double xx = Double.parseDouble(cursor3.getString(6));
//                                                    double zz = xx+yy;
//
//                                                    // newcq = String.valueOf(z);
//                                                  //  dbs.execSQL("UPDATE product SET In_current_qty = " +zz+ " WHERE id = "+ii);
//                                                }
//
//                                                while (cursor3.moveToNext());
//                                            }
//                                        }
//
//                                        while (cursor2.moveToNext());
//                                    }
//
//
//                                }



                                // Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_SHORT).show();
                                //finish();

                            }

                        } catch (Exception e) {

                        }
                    }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);
                        final SQLiteDatabase dbs = mydb.getWritableDatabase();
                        //  dbs.execSQL("DELETE FROM inwardlist WHERE id = " + id);
                        //  dbs.execSQL("UPDATE inwardlist SET flag = 1 WHERE id = "+id);
                        // Toast.makeText(getApplicationContext(),"Unable to Insert",Toast.LENGTH_SHORT).show();
                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Error Inserting Inward!")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //set what would happen when positive button is clicked

                                            }
                                        }
//set negative button

                                )
                                .show();
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
    public void saveinvoice()
    {


        try {
            DecimalFormat amountFormate  = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(cc);
            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", orgnnew2);
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc",""));
            user.put("localeId", "en_US");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("IOU_invoice_rowid","0");
            user2.put("In_orgn_code",orgnnew2);
            user2.put("In_invoice_no","0");
            user2.put("In_ic_code",orgnnew2);
            user2.put("In_invoice_date",formattedDate);
            user2.put("In_customer_type",mcode);

            user2.put("In_customer_name",sname2);
            user2.put("In_customer_address",slcn2);
            user2.put("In_farmer_code",farco);
            user2.put("In_provider_location",sharedpreferences.getString("lo",""));
            user2.put("In_reciver_location",lc2);

            user2.put("In_totalinvoice_amount",net2);
            user2.put("In_balance_amount",balamt);
            user2.put("In_status_code","A");
            user2.put("In_process_status","A");
            user2.put("In_row_timestamp",formattedDate);
            user2.put("In_mode_flag","I");
            user2.put("In_transport_amount",trs2);
            user2.put("In_others",oth2);
            user2.put("In_phone_no",phn);


            user.put("Header",user2);

            String[] id = ids222.split(",");

            JSONArray notebookUsers2 = new JSONArray();
            for(int i = 0 ; i< id.length;i++)
            {
                final SQLiteDatabase dbs = mydb.getWritableDatabase();
                String selectQuery = "SELECT  * FROM productlist2 where id ="+id[i];
                Cursor cursor2 = dbs.rawQuery(selectQuery, null);
                // Toast.makeText(this, ""+id[0]+"//"+cursor2.getCount(), Toast.LENGTH_SHORT).show();
                if (cursor2.moveToFirst()) {
                    do {

                        pn2 = cursor2.getString(1);
                        pr2 = cursor2.getString(3);
                        pq2 = cursor2.getString(2);
                        pd2 = cursor2.getString(5);
                        amt2 = cursor2.getString(6);
                        myr2 = cursor2.getString(4);
                        tspr = cursor2.getString(9);

                        Cursor cursor3 = dbs.query("product", new String[]{"In_product_code",
                                        "In_productcategory_code","In_productsubcategory_code","In_hsn_code","In_cgst","In_sgst","In_base_price","In_current_qty","id","In_igst"}, "In_product_name" + "=?",
                                new String[]{pn2}, null, null, null, null);

                        if (cursor3.moveToFirst()) {
                            do {
                                DecimalFormat format = new DecimalFormat("##########.##");
                                pco2 = cursor3.getString(0);
                                pc2 = cursor3.getString(1);
                                //Toast.makeText(this, ""+pco2, Toast.LENGTH_SHORT).show();
                                psc2 = cursor3.getString(2);
                                pu2 = cursor3.getString(3);
                                //newcq = cursor3.getString(7);
                                double bas = Double.parseDouble(cursor3.getString(6));
                                bp = format.format(bas);
                                Log.e("CLL",""+lc2+"//"+sharedpreferences.getString("lo",""));
                                if(lc2.equalsIgnoreCase(sharedpreferences.getString("lo",""))) {
                                    double c = Float.parseFloat(cursor3.getString(4));
                                    double s = Float.parseFloat(cursor3.getString(5));
                                    double rl = Double.parseDouble(amt2);

                                    double cg = (c/100)*rl;
                                    double sg = (s/100)*rl;
                                    double f = cg+sg;
                                    pt2 = format.format(f).toString();
                                    double x = Double.parseDouble(pt2);
                                    double y = Double.parseDouble(amt2);

                                    double z = x + y;
                                    pa2 = format.format(z).toString();
                                }
                                else
                                {
                                    double c = Float.parseFloat(cursor3.getString(9));
                                    double rl = Double.parseDouble(amt2);

                                    double cg = (c/100)*rl;
                                    double f = cg;
                                    pt2 = format.format(f).toString();
                                    double x = Double.parseDouble(pt2);
                                    double y = Double.parseDouble(amt2);

                                    double z = x + y;
                                    pa2 = format.format(z).toString();
                                }
//                               String ii = cursor3.getString(8);
//                                double yy = Double.parseDouble(pq2);
//                                double xx = Double.parseDouble(newcq);
//                                double zz = xx-yy;
//
//                                dbs.execSQL("UPDATE product SET In_current_qty = "  +zz+ " WHERE id = "+ii);
                            }

                            while (cursor3.moveToNext());
                        }
                    }

                    while (cursor2.moveToNext());
                }

                JSONObject user4 = new JSONObject();
                user4.put("In_invoicedtl_rowid",0);
                user4.put("In_product_catg_code",pc2);
                user4.put("In_product_subcatg_code",psc2);
                user4.put("In_product_code",pco2);
                user4.put("In_hsn_code",pu2);
                user4.put("In_qty",pq2);
                user4.put("In_base_price",pr2);
                user4.put("In_product_amount",myr2);
                user4.put("In_discount_amount",pd2);
                user4.put("In_tax_amount",""+pt2);
                user4.put("In_net_amount",""+pa2);
                user4.put("In_status_code","A");
                user4.put("In_mode_flag","I");
                Cursor cursor = dbs.query("sno", new String[]{"v1","v2","v3"
                        }, "v3" + "=? COLLATE NOCASE",
                        new String[]{tspr}, null, null, null, null);
                Log.e("COUNT", "" + cursor.getCount());




                JSONArray notebookUserssno = new JSONArray();
                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        JSONObject usersno = new JSONObject();
                        usersno.put("In_invoiceslno_rowid",0);
                        usersno.put("In_invoicedtl_rowid",0);
                        usersno.put("In_slno",cursor.getString(0));
                        usersno.put("In_no_of_bags","");
                        usersno.put("In_invoice_no","");
                        usersno.put("In_product_code","");
                        usersno.put("In_mode_flag","I");
                        notebookUserssno.put(usersno);

                        ///String[] pn = cursor.getString(1).split("-");
                        //


                    } while (cursor.moveToNext());

                }

                user4.put("Slnoinfo",notebookUserssno);
                notebookUsers2.put(user4);

            }



            user.put("InvoiceDetail",notebookUsers2);

            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
            Log.e("OUTPUT",""+jsonParam.toString());
        }




        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.icdurl+"api/ICDMOBInvoice/newsaveInvoice",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        ct22++;

                        final SQLiteDatabase dbs = mydb.getWritableDatabase();
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("Header");

                            String inrid = obj2.getString("IOU_invoice_rowid");
                            final String inrno = obj2.getString("In_invoice_no");
                            Log.e("CCCC","Hi"+inrid);

                            if(inrid.equalsIgnoreCase("0"))
                            {
                                // dbs.execSQL("DELETE FROM invoicelist WHERE id = " + id2);
                                pdialog.dismiss();
                                //Toast.makeText(Landpage.this, "Unable To Insert", Toast.LENGTH_SHORT).show();
                                final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Error Inserting Invoice")
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        // finish();
                                                    }
                                                }
//set negative button

                                        )
                                        .show();

                            }
                            else {
                                ctest++;
                                if(ctt == ctest)
                                {
                                    // psave();
                                }

                                String selectQuery5 = "SELECT  * FROM paylist where uflag = 0 and invoiceno = '"+payinvoiceno+"'";
                                Cursor cursorp2 = dbs.rawQuery(selectQuery5, null);
                                if(cursorp2.getCount()!=0) {

                                    if (cursorp2.moveToFirst()) {
                                        inid = cursorp2.getString(0);;
                                        String cinv2 = cursorp2.getString(1);
                                       // if (cursorp.moveToFirst()) {

                                            Cursor cursorfc22 = dbs.query("invoicelist", new String[]{"date", "sid", "invoiceamount", "inweb"
                                                    }, "invoiceno" + "=?",
                                                    new String[]{cinv2}, null, null, null, null);

                                            if (cursorfc22.moveToFirst()) {
                                                do {

                                                    payid = cursorfc22.getString(1);
                                                    paydate = cursorfc22.getString(0);
                                                    payamt = cursorfc22.getString(2);
                                                    cinv = cursorfc22.getString(3);
                                                }

                                                while (cursorfc22.moveToNext());


                                            }


                                            Cursor cursorfc2 = dbs.query("paylist", new String[]{"amountpaid", "refno", "date", "paymode", "bal", "id", "uflag","ckno"
                                                    }, "id" + "=?",
                                                    new String[]{inid}, null, null, null, null);
                                            // Toast.makeText(Landpage.this, ""+inid, Toast.LENGTH_SHORT).show();
                                            if (cursorfc2.getCount() != 0) {

                                                if (cursorfc2.moveToFirst()) {

                                                    //Toast.makeText(Landpage.this, "Hi" + cursorfc2.getString(6), Toast.LENGTH_SHORT).show();


                                                    uflag = cursorfc2.getString(6);


                                                    iid = cursorfc2.getString(5);
                                                    refno = cursorfc2.getString(1);
                                                    paidamt = cursorfc2.getString(0);
                                                    paymode = cursorfc2.getString(3);
                                                    payindate = cursorfc2.getString(2);
                                                    paybalamt = cursorfc2.getString(4);
                                                    cqno = cursorfc2.getString(7);


                                                    if (uflag.equalsIgnoreCase("0")) {

                                                        savep();
                                                    }
                                                }
                                            }


                                       // }
                                    }

                                }

                                dbs.execSQL("UPDATE invoicelist SET flag = 1 WHERE id = " + id2);
                                dbs.execSQL("UPDATE invoicelist SET sid = " + inrid + " WHERE id = " + id2);
                                mydb.upinvoice(Integer.valueOf(id2),inrno);
                                String selectQuery2n = "SELECT  * FROM invoicelist where flag = 0";
                                Cursor cursor = dbs.rawQuery(selectQuery2n, null);
                                if (cursor.moveToFirst()) {

                                    id2 = cursor.getString(0);
                                    payinvoiceno = cursor.getString(1);
                                    sname2 = cursor.getString(3);
                                    slcn2 = cursor.getString(4);
                                    sst2 = cursor.getString(4);
                                    net2 = cursor.getString(8);
                                    tax2 = cursor.getString(5);
                                    if(cursor.getString(6).equalsIgnoreCase(""))
                                    {
                                        trs2 = "0";
                                    }
                                    else
                                    {
                                        trs2 = cursor.getString(6);
                                    }
                                    if(cursor.getString(7).equalsIgnoreCase(""))
                                    {
                                        oth2 = "0";
                                    }
                                    else
                                    {
                                        oth2 = cursor.getString(7);
                                    }
                                    ids22 = cursor.getString(9);
                                    lc2 = cursor.getString(12);
                                    mcode = cursor.getString(17);
                                    phn = cursor.getString(16);
                                    ids222 = ids22.substring(1, ids22.length() - 1);
                                    // Toast.makeText(Landpage.this, ""+ids22, Toast.LENGTH_SHORT).show();
                                    Cursor cursori = dbs.query("invoice", new String[]{"balance",
                                            }, "invoiceno" + "=?",
                                            new String[]{cursor.getString(1)}, null, null, null, null);
                                    if (cursori.moveToFirst()) {
                                        do {
                                            // idn = cursor2.getString(2);
                                            //Toast.makeText(Landpage.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

                                            balamt = cursori.getString(0);


                                        }
                                        while (cursori.moveToNext());


                                    }

                                    Cursor cursorfc = dbs.query("customerlist", new String[]{"farmer_code"
                                            }, "farmer_name" + "=?",
                                            new String[]{sname2}, null, null, null, null);

                                    if (cursorfc.moveToFirst()) {
                                        do {

                                            farco = cursorfc.getString(0);
                                        }
                                        while (cursorfc.moveToNext());


                                    }
                                    //Toast.makeText(Landpage.this, ""+ids, Toast.LENGTH_SHORT).show();

                                    saveinvoice();




                                }
//                                String[] id = ids222.split(",");
//
//                                JSONArray notebookUsers2 = new JSONArray();
//                                for(int i = 0 ; i< id.length;i++) {
//
//                                   // String selectQuery = "SELECT  * FROM productlist2 where id =" + id[i];
//                                    Cursor cursor2 = dbs.rawQuery(selectQuery, null);
//                                    // Toast.makeText(this, ""+id[0]+"//"+cursor2.getCount(), Toast.LENGTH_SHORT).show();
//                                    if (cursor2.moveToFirst()) {
//                                        do {
//
//                                            pn2 = cursor2.getString(1);
//                                            pq2 = cursor2.getString(2);
//
//
//                                            Cursor cursor3 = dbs.query("product", new String[]{"In_product_code",
//                                                            "In_productcategory_code", "In_productsubcategory_code", "In_hsn_code", "In_cgst", "In_sgst", "In_base_price", "In_current_qty", "id"}, "In_product_name" + "=?",
//                                                    new String[]{pn2}, null, null, null, null);
//
//                                            if (cursor3.moveToFirst()) {
//                                                do {
//                                                    String ii = cursor3.getString(8);
//                                                    newcq = cursor3.getString(7);
//                                                    double yy = Double.parseDouble(pq2);
//                                                    double xx = Double.parseDouble(newcq);
//                                                    double zz = xx - yy;
//
//                                                    dbs.execSQL("UPDATE product SET In_current_qty = " + zz + " WHERE id = " + ii);
//                                                }
//
//                                                while (cursor3.moveToNext());
//                                            }
//                                        }
//
//                                        while (cursor2.moveToNext());
//                                    }
//                                }

                                fcnt++;

                                if(fcnt == finalcount)
                                {
                                    // dbs.execSQL("UPDATE productlist2 SET flag = 1 WHERE flag = 0");
                                    pdialog.dismiss();

                                    final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                            .setTitle("Success!")
//set message
                                            .setMessage("Transaction Completed!")
//set positive button
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            //set what would happen when positive button is clicked
                                                            // dbs.execSQL("UPDATE invoicelist SET inweb = " + inrno + " WHERE id = " + id2);

                                                            // finish();
                                                            ms();
                                                        }
                                                    }
//set negative button

                                            )
                                            .show();
                                }
                                // pdialog.dismiss();

                                // Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();

                                // einwno.setText(""+inrno);
                                Log.e("CCCC", "Invoice");

                                // finish();
                            }
                        }
                        catch (Exception e)
                        {

                        }



                        // Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_SHORT).show();
                        //finish();


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);
                        final SQLiteDatabase dbs = mydb.getWritableDatabase();
                        //dbs.execSQL("DELETE FROM invoicelist WHERE id = " + id2);
                        //Toast.makeText(getApplicationContext(),"Unable to Insert",Toast.LENGTH_SHORT).show();
                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Error Inserting Invoice")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //set what would happen when positive button is clicked
                                                // finish();
                                            }
                                        }
//set negative button

                                )
                                .show();
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
                80000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    public void savep()
    {


        try {
            DecimalFormat amountFormate  = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(cc);
            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", sharedpreferences.getString("oc",""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc",""));
            user.put("localeId", "en_US");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            String[] d1 = payindate.toString().split("/");


            user2.put("IOU_invoice_rowid",payid);
            user2.put("IOU_invoice_no",cinv);
            user2.put("In_invoice_date",paydate);
            user2.put("In_invoice_amount",payamt);
            user2.put("In_balance_amount",paybalamt);
            user2.put("In_payment_mode",paymode);
            user2.put("In_ref_no",refno);
            user2.put("In_payment_date",d1[2]+"/"+d1[1]+"/"+d1[0]);
            user2.put("In_payment_amount",paidamt);
            user2.put("In_payment_response","Success");
            user2.put("In_status_code","A");
            user2.put("In_row_timestamp",formattedDate);
            user2.put("In_mode_flag","I");
            user2.put("In_check_no",cqno);

            user.put("Header",user2);

            JSONArray notebookUsers2 = new JSONArray();

            JSONObject user4 = new JSONObject();
            user4.put("In_paymentcollection_rowid","0");
            user4.put("In_payment_type","");
            user4.put("In_payment_no",cinv);
            user4.put("In_balance_amount",paybalamt);
            user4.put("In_payment_amount",paidamt);
            user4.put("In_payment_mode",paymode);
            user4.put("In_ref_no",refno);
            user4.put("In_payment_date",d1[2]+"/"+d1[1]+"/"+d1[0]);
            user4.put("In_process_status","A");
            user4.put("In_paid_amount",""+paidamt);
            user4.put("In_mode_flag",""+"I");


            notebookUsers2.put(user4);




            user.put("Detail",notebookUsers2);

            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
            Log.e("OUTPUT",""+jsonParam.toString());
        }




        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.icdurl+"api/ICDMOBInvoice/mobnewsavePaymentCollection",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCCPAY", "" + response);
                        fcnt++;
                        final SQLiteDatabase dbs = mydb.getWritableDatabase();
                        dbs.execSQL("UPDATE paylist SET uflag = 1 WHERE id = " + iid);




                        String selectQuery4 = "SELECT  * FROM invoice where flag = 0";
                        String selectQuery5 = "SELECT  * FROM paylist where uflag = 0";
                        Cursor cursorp2 = dbs.rawQuery(selectQuery5, null);
                        Cursor cursorp = dbs.rawQuery(selectQuery4, null);
                        // Toast.makeText(Landpage.this, ""+cursor2.getCount(), Toast.LENGTH_SHORT).show();
//                        if(cursorp2.getCount()!=0) {
//
//                            if (cursorp2.moveToFirst()) {
//                                String cinv2 = cursorp2.getString(1);
//                                inid = cursorp2.getString(0);
//                                if (cursorp.moveToFirst()) {
//
//
//                                    // iid = cursorp.getString(0);
//                                    // String cinv2 = cursorp.getString(1);
//
//
//                                    Cursor cursorfc22 = dbs.query("invoicelist", new String[]{"date", "sid", "invoiceamount", "inweb"
//                                            }, "invoiceno" + "=?",
//                                            new String[]{cinv2}, null, null, null, null);
//
//                                    if (cursorfc22.moveToFirst()) {
//                                        do {
//
//                                            payid = cursorfc22.getString(1);
//                                            paydate = cursorfc22.getString(0);
//                                            payamt = cursorfc22.getString(2);
//                                            cinv = cursorfc22.getString(3);
//                                        }
//
//                                        while (cursorfc22.moveToNext());
//
//
//                                    }
//
//
//                                    Cursor cursorfc2 = dbs.query("paylist", new String[]{"amountpaid", "refno", "date", "paymode", "bal", "id", "uflag","ckno"
//                                            }, "id" + "=?",
//                                            new String[]{inid}, null, null, null, null);
//
//                                    if (cursorfc2.getCount() != 0) {
//
//                                        if (cursorfc2.moveToFirst()) {
//
//
//                                            uflag = cursorfc2.getString(6);
//
//                                            iid = cursorfc2.getString(5);
//                                            refno = cursorfc2.getString(1);
//                                            paidamt = cursorfc2.getString(0);
//                                            paymode = cursorfc2.getString(3);
//                                            payindate = cursorfc2.getString(2);
//                                            paybalamt = cursorfc2.getString(4);
//                                            cqno = cursorfc2.getString(7);
//
//
//                                            if (uflag.equalsIgnoreCase("0")) {
//
//                                                savep();
//                                            }
//                                        }
//                                    }
//
//
//                                }
//                            }
//                        }

//                        try {
//                            JSONObject obj = response.getJSONObject("context");
//                            JSONObject obj2 = obj.getJSONObject("Header");
//
//                            String inrid = obj2.getString("IOU_invoice_rowid");
//                            Log.e("CCCC","Hi"+inrid);
//


                        if(fcnt == finalcount) {
                            pdialog.dismiss();

                            final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Trasactions Completed!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                    // finish();
                                                    ms();
                                                }
                                            }
//set negative button

                                    )
                                    .show();
                        }

//                        catch (Exception e)
//                        {
//
//                        }


                        // Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_SHORT).show();
                        //finish();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);

                        // Toast.makeText(getApplicationContext(),"Unable to Insert",Toast.LENGTH_SHORT).show();
                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Payment Error!")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //set what would happen when positive button is clicked

                                            }
                                        }
//set negative button

                                )
                                .show();
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
                80000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    public void savetransfer()
    {


        try {
            DecimalFormat amountFormate  = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(cc);
            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", orgnew3);
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc",""));
            user.put("localeId", "en_US");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("IOU_adjustment_rowid","0");
            user2.put("In_orgn_code",orgnew3);
            user2.put("In_ic_code",orgnew3);
            user2.put("In_ic_desc","");
            user2.put("In_adjustment_no","");
            user2.put("In_adjustment_date",aqd);
            user2.put("In_status_code","A");
            user2.put("In_process_status","A");
            user2.put("In_row_timestamp",formattedDate);
            user2.put("In_mode_flag","I");


            user.put("Header",user2);

            String[] id = aqi2.split(",");

            JSONArray notebookUsers2 = new JSONArray();
            for(int i = 0 ; i< id.length;i++)
            {
                final SQLiteDatabase dbs = mydb.getWritableDatabase();

                Cursor cursor4 = dbs.query("astock", new String[]{"item","adjusttype",
                                "inwardno","stock","aqty","rqty","lrp" }, "id" + "=?",
                        new String[]{id[i]}, null, null, null, null);

                if (cursor4.moveToFirst()) {
                    do {
                        at = cursor4.getString(0);
                        atno = cursor4.getString(4);
                        if(cursor4.getString(1).replaceAll(" ","").equalsIgnoreCase("transferfromlrp"))
                        {
                            aty = "QCD_ICD_TRANFRMLRP";
                        }
                        else if(cursor4.getString(1).replaceAll(" ","").equalsIgnoreCase("transfertolrp"))
                        {
                            aty = "QCD_ICD_TRANTOLRP";
                        }
                        else
                        {
                            aty = "QCD_ICD_"+cursor4.getString(1);
                        }

                        if(cursor4.getString(6).equalsIgnoreCase(""))
                        {
                            lrp = "";
                        }
                        else
                        {

                            lrp = cursor4.getString(6);
                        }
                        inn = cursor4.getString(2);
                        rqty = cursor4.getString(5);

                        Cursor cursor3 = dbs.query("product", new String[]{"In_product_code","In_product_name",
                                        "In_productcategory_code","In_productcategory_desc","In_productsubcategory_code","In_productsubcategory_desc","In_uomtype_desc"  }, "In_product_name" + "=?",
                                new String[]{at}, null, null, null, null);

                        if (cursor3.moveToFirst()) {
                            do {

                                aqpco = cursor3.getString(0);
                                aqc = cursor3.getString(2);
                                aqcd = cursor3.getString(3);
                                aqsc = cursor3.getString(4);
                                aqsc2 = cursor3.getString(5);
                                aqu = cursor3.getString(6);

                                //Toast.makeText(this, ""+pco2, Toast.LENGTH_SHORT).show();

                            }

                            while (cursor3.moveToNext());
                        }

                    }

                    while (cursor4.moveToNext());
                }





                JSONObject user4 = new JSONObject();
                user4.put("In_adjustmentdtl_rowid","0");
                user4.put("In_adjustment_no","");
                user4.put("In_receipt_ref_doc_no",inn);
                user4.put("In_ref_doc_date","");
                user4.put("In_adjustment_type",aty);
                user4.put("In_product_catg_code",aqc);
                user4.put("In_product_catg_desc",aqcd);
                user4.put("In_product_subcatg_code",aqsc);
                user4.put("In_product_subcatg_desc",aqsc2);
                user4.put("In_product_code",""+aqpco);
                user4.put("In_product_desc",""+at);
                user4.put("In_adjustment_qty",atno);
                user4.put("In_uom_type",aqu);
                user4.put("In_remarks","Adjustment From Mobile");
                user4.put("In_status_code","A");
                user4.put("In_status_desc","Active");
                user4.put("In_mode_flag","I");
                user4.put("In_out_qty",rqty);
                user4.put("In_lrp_name",lrp);
                notebookUsers2.put(user4);
                // dbs.execSQL("UPDATE astock SET flag = 1 WHERE id ="+id[i]);
            }



            user.put("Detail",notebookUsers2);

            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
            Log.e("OUTPUT",""+jsonParam.toString());
        }




        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.icdurl+"api/ICDMOBStockadj/newSaveStockAdjustment",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        c3++;
                        final SQLiteDatabase dbs = mydb.getWritableDatabase();
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("Header");

                            String inrid = obj2.getString("IOU_adjustment_rowid");
                            final String inrno = obj2.getString("In_adjustment_no");
                            Log.e("CCCC", "Hi" + inrid);

                            if (inrid.equalsIgnoreCase("0")) {
                                // dbs.execSQL("Delete FROM  astock2 WHERE id = " + aqid);
                                pdialog.dismiss();

                                // Toast.makeText(StockAdjustment.this, "Unable To Insert", Toast.LENGTH_SHORT).show();
                                final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Unable To Insert!")
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
//                                                        finish();
//                                                        startActivity(getIntent());
                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                            } else {


//                                String selectQuery3 = "SELECT  * FROM astock2";
//                                Cursor cursor33 = dbs.rawQuery(selectQuery3, null);
//                                ct3 = cursor33.getCount();
//                                if (cursor33.moveToFirst()) {
//                                    do {
//
//                                        aqid = cursor33.getString(0);
//
//                                        aqi = cursor33.getString(1);
//                                        aqi2 = aqi.substring(1, aqi.length() - 1);
//                                        aqd = cursor33.getString(6);
//                                        String[] id = aqi2.split(",");
//
//
//                                        for(int i = 0 ; i< id.length;i++) {
//
//
//                                            Cursor cursor4 = dbs.query("astock", new String[]{"item", "adjusttype",
//                                                            "inwardno", "stock", "aqty", "rqty",}, "id" + "=?",
//                                                    new String[]{id[i]}, null, null, null, null);
//
//                                            if (cursor4.moveToFirst()) {
//                                                do {
//                                                    at = cursor4.getString(0);
//                                                    atno = cursor4.getString(4);
//                                                    aty = "QCD_ICD_" + cursor4.getString(1);
//                                                    String rqty = cursor4.getString(5);
//
//
//
//                                                        // newcq = String.valueOf(z);
//
//
//
//                                                    Cursor cursor3 = dbs.query("product", new String[]{"In_product_code", "In_product_name",
//                                                                    "In_productcategory_code", "In_productcategory_desc", "In_productsubcategory_code", "In_productsubcategory_desc", "In_uomtype_desc","id"}, "In_product_name" + "=?",
//                                                            new String[]{at}, null, null, null, null);
//
//                                                    if (cursor3.moveToFirst()) {
//                                                        do {
//
//                                                            aqpco = cursor3.getString(0);
//                                                            aqc = cursor3.getString(2);
//                                                            aqcd = cursor3.getString(3);
//                                                            aqsc = cursor3.getString(4);
//                                                            aqsc2 = cursor3.getString(5);
//                                                            aqu = cursor3.getString(6);
//                                                            String ii = cursor3.getString(7);
//
//                                                            //Toast.makeText(this, ""+pco2, Toast.LENGTH_SHORT).show();
//                                                            dbs.execSQL("UPDATE product SET In_current_qty = " +rqty+ " WHERE id = "+ii);
//                                                        }
//
//                                                        while (cursor3.moveToNext());
//                                                    }
//
//                                                }
//
//                                                while (cursor4.moveToNext());
//                                            }
//                                        }
//                                        } while (cursor33.moveToNext());
//                                }
                                //dbs.execSQL("UPDATE astock SET flag = 1 WHERE flag = 0");
                                // Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                                dbs.execSQL("Delete FROM  astock2 WHERE id = " + aqid);
                                Log.e("CCCC", "SA");
                                String selectQuery3 = "SELECT  * FROM astock2";
                                Cursor cursor3 = dbs.rawQuery(selectQuery3, null);
                                if (cursor3.moveToFirst()) {


                                    aqid = cursor3.getString(0);

                                    aqi = cursor3.getString(1);
                                    Log.e("HARI", "" + aqi);
                                    aqi2 = aqi.substring(1, aqi.length() - 1);
                                    aqd = cursor3.getString(6);
                                    savetransfer();

                                }
                                // eadj.setText(""+inrno);
                                // finish();
                                fcnt++;

                                if(fcnt == finalcount)
                                {
                                    pdialog.dismiss();

                                    final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                            .setTitle("Success!")
//set message
                                            .setMessage("Transactions Completed!")
//set positive button
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            //set what would happen when positive button is clicked
                                                            // dbs.execSQL("UPDATE invoicelist SET inweb = " + inrno + " WHERE id = " + id2);
                                                            //  db.upinvoice(Integer.valueOf(id2),inrno);
                                                            // finish();
                                                            ms();
                                                        }
                                                    }
//set negative button

                                            )
                                            .show();
                                }


                            }
                        }catch (Exception e)
                        {

                        }
                        // Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_SHORT).show();
                        //finish();


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);
                        final SQLiteDatabase dbs = mydb.getWritableDatabase();
                        // dbs.execSQL("Delete FROM  astock2 WHERE id = " + aqid);
                        //Toast.makeText(getApplicationContext(),"Unable to Insert",Toast.LENGTH_SHORT).show();
                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Unable To Insert!")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //set what would happen when positive button is clicked
//                                                finish();
//                                                startActivity(getIntent());
                                            }
                                        }
//set negative button

                                )
                                .show();
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
                80000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    public void saveistrs()
    {


        try {



            DecimalFormat amountFormate  = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(cc);
            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", sharedpreferences.getString("oc",""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc",""));
            user.put("localeId", "en_US");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("IOU_inward_rowid","0");
            user2.put("In_orgn_code",sharedpreferences.getString("oc",""));
            user2.put("In_ic_code",itorg);
            user2.put("In_inward_code","");

            user2.put("In_grn_no","");
            user2.put("In_grn_date",formattedDate);
            user2.put("In_supplier_name","");
            user2.put("In_supplier_location","");
            user2.put("In_from_state","");
            user2.put("In_Remarks","Inward From Mobile");
            user2.put("In_status_code","A");
            user2.put("In_process_status","QCD_INWARD_ISSUETRANSFER");
            user2.put("In_row_timestamp",formattedDate);
            user2.put("In_mode_flag","I");
            user2.put("In_bill_image","");
            user2.put("In_transport_amount","0");
            user2.put("In_others","0");

            user2.put("In_loading_unloading_cost","0");
            user2.put("In_local_transport_cost","0");
            user2.put("In_local_loading_unloading_cost","0");

            JSONArray notebookUsers2 = new JSONArray();
            user.put("Header",user2);
            JSONObject user4 = new JSONObject();
            user4.put("In_inwarddtl_rowid",0);
            user4.put("In_inward_code","");
            user4.put("In_grn_no","0");
            user4.put("In_product_catg_code","");
            user4.put("In_product_subcatg_code","");
            user4.put("In_product_code",pcode);
            user4.put("In_uomtype_code","");
            user4.put("In_batch_no","");
            user4.put("In_received_qty",tstock);
            user4.put("In_product_amount","0");
            user4.put("In_tax_amount","0");
            user4.put("In_transport_amount",0);
            user4.put("In_discount","0");
            user4.put("In_net_amount","0");
            user4.put("In_status_code","A");
            user4.put("In_mode_flag","I");
            JSONArray notebookUserssno = new JSONArray();
            JSONObject usersno = new JSONObject();
            usersno.put("In_inwardslno_rowid","0");
            usersno.put("In_inwarddtl_rowid",0);
            usersno.put("In_slno","");
            usersno.put("In_no_of_bags","");
            usersno.put("In_grn_no","");
            usersno.put("In_product_catg_code","");
            usersno.put("In_product_subcatg_code","");
            usersno.put("In_product_code","");
            usersno.put("In_status_code","");
            usersno.put("In_mode_flag","I");
            notebookUserssno.put(usersno);

            user4.put("Slnoinfo",notebookUserssno);
            notebookUsers2.put(user4);




            user.put("Detail",notebookUsers2);


            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
        }


        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();

        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.icdurl+"api/ICDMOBInward/newSaveStockInward",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("Header");

                            String inrid = obj2.getString("IOU_inward_rowid");
                            String inrno = obj2.getString("In_grn_no");

                            Log.e("CCCC", "Hi" + inrid);

                            if (inrid.equalsIgnoreCase("0")) {
                                pdialog.dismiss();
                                final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Error Inserting Inward !")
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked

                                                    }
                                                }
//set negative button

                                        )
                                        .show();

                            } else {
                                pdialog.dismiss();

                                fcnt++;

                                final SQLiteDatabase dbs = mydb.getWritableDatabase();
                                dbs.execSQL("delete from itransfer WHERE id = "+itid);
                                String selectQuery6 = "SELECT  * FROM itransfer";

                                Cursor cursor5 = dbs.rawQuery(selectQuery6, null);
                                if (cursor5.getCount() == 0) {

                                } else {


                                    if (cursor5.moveToFirst()) {


                                        itid = cursor5.getString(0);
                                        pcode = cursor5.getString(2);
                                        itorg = cursor5.getString(1);
                                        tstock = cursor5.getString(3);



                                        saveistrs();

                                    }

                                }



                                if(fcnt == finalcount)
                                {
                                    pdialog.dismiss();

                                    final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                            .setTitle("Success!")
//set message
                                            .setMessage("Transactions Completed!")
//set positive button
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            //set what would happen when positive button is clicked
                                                            // dbs.execSQL("UPDATE invoicelist SET inweb = " + inrno + " WHERE id = " + id2);
                                                            //  db.upinvoice(Integer.valueOf(id2),inrno);
                                                            // finish();
                                                            ms();
                                                        }
                                                    }
//set negative button

                                            )
                                            .show();
                                }
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

                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Error Inserting Inward!")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //set what would happen when positive button is clicked

                                            }
                                        }
//set negative button

                                )
                                .show();
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

    public void saveiscnf()
    {


        try {



            DecimalFormat amountFormate  = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(cc);
            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", sharedpreferences.getString("oc",""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc",""));
            user.put("localeId", "en_US");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("IOU_inward_rowid","0");
            user2.put("In_orgn_code",sharedpreferences.getString("oc",""));
            user2.put("In_ic_code",sharedpreferences.getString("oc",""));
            user2.put("In_inward_code","");

            user2.put("In_grn_no",icgrn);
            user2.put("In_grn_date",formattedDate);
            user2.put("In_supplier_name","");
            user2.put("In_supplier_location","");
            user2.put("In_from_state","");
            user2.put("In_Remarks",icrmk);
            user2.put("In_status_code","A");
            user2.put("In_process_status","QCD_INWARD_CONFIRMISSUE");
            user2.put("In_row_timestamp",formattedDate);
            user2.put("In_mode_flag","U");
            user2.put("In_bill_image","");
            user2.put("In_transport_amount","0");
            user2.put("In_others","0");

            user2.put("In_loading_unloading_cost","0");
            user2.put("In_local_transport_cost","0");
            user2.put("In_local_loading_unloading_cost","0");

            JSONArray notebookUsers2 = new JSONArray();
            user.put("Header",user2);
            JSONObject user4 = new JSONObject();
            user4.put("In_inwarddtl_rowid",0);
            user4.put("In_inward_code","");
            user4.put("In_grn_no","0");
            user4.put("In_product_catg_code","");
            user4.put("In_product_subcatg_code","");
            user4.put("In_product_code",pcode2);
            user4.put("In_uomtype_code","");
            user4.put("In_batch_no","");
            user4.put("In_received_qty",icqty);
            user4.put("In_product_amount","0");
            user4.put("In_tax_amount","0");
            user4.put("In_transport_amount",0);
            user4.put("In_discount","0");
            user4.put("In_net_amount","0");
            user4.put("In_status_code","A");
            user4.put("In_mode_flag","U");
            JSONArray notebookUserssno = new JSONArray();
            JSONObject usersno = new JSONObject();
            usersno.put("In_inwardslno_rowid","0");
            usersno.put("In_inwarddtl_rowid",0);
            usersno.put("In_slno","");
            usersno.put("In_no_of_bags","");
            usersno.put("In_grn_no","");
            usersno.put("In_product_catg_code","");
            usersno.put("In_product_subcatg_code","");
            usersno.put("In_product_code","");
            usersno.put("In_status_code","");
            usersno.put("In_mode_flag","U");
            notebookUserssno.put(usersno);

            user4.put("Slnoinfo",notebookUserssno);
            notebookUsers2.put(user4);




            user.put("Detail",notebookUsers2);


            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
        }


        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();

        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.icdurl+"api/ICDMOBInward/newSaveStockInward",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("Header");

                            String inrid = obj2.getString("IOU_inward_rowid");
                            String inrno = obj2.getString("In_grn_no");

                            Log.e("CCCC", "Hi" + inrid);

                            if (inrid.equalsIgnoreCase("0")) {
                                pdialog.dismiss();
                                final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Error Inserting Inward !")
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked

                                                    }
                                                }
//set negative button

                                        )
                                        .show();

                            } else {
                                pdialog.dismiss();
                                final SQLiteDatabase dbs = mydb.getWritableDatabase();
                                // dbs.execSQL("delete from iconfirm WHERE id = "+icid);

                                dbs.execSQL("UPDATE iconfirm SET v5 = 1 WHERE id = "+icid);
                                String selectQuery7 = "SELECT  * FROM iconfirm WHERE v5 = 0";
                                Cursor cursor6 = dbs.rawQuery(selectQuery7, null);

                                fcnt++;
                                if (cursor6.getCount() == 0) {

                                } else {


                                    if (cursor6.moveToFirst()) {


                                        icid = cursor6.getString(0);
                                        pcode2 = cursor6.getString(2);
                                        icgrn = cursor6.getString(1);
                                        icqty = cursor6.getString(3);
                                        icrmk = cursor6.getString(4);



                                        saveiscnf();

                                    }

                                }


                                if(fcnt == finalcount)
                                {
                                    pdialog.dismiss();

                                    final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                            .setTitle("Success!")
//set message
                                            .setMessage("Transactions Completed!")
//set positive button
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            //set what would happen when positive button is clicked
                                                            // dbs.execSQL("UPDATE invoicelist SET inweb = " + inrno + " WHERE id = " + id2);
                                                            //  db.upinvoice(Integer.valueOf(id2),inrno);
                                                            // finish();
                                                            ms();
                                                        }
                                                    }
//set negative button

                                            )
                                            .show();
                                }
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

                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Error Inserting Inward!")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //set what would happen when positive button is clicked

                                            }
                                        }
//set negative button

                                )
                                .show();
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
    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }
    public void ms()
    {
        // dialog.dismiss();
//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Master Syncing.....");
//        pdialog.show();
        //rmast.setEnabled(false);
        SQLiteDatabase dbs = mydb.getWritableDatabase();
        dbs.execSQL("delete from product");

        // dbs.execSQL("delete from customerlist");

        //dbs.execSQL("delete from masterl");

        // pb.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(Landpage.this);
        final String url = Pojokyc.icdurl+"api/ICDMOBProduct/ICD_MOBILE_Product?org="+sharedpreferences.getString("oc","")+"&locn="+Pojokyc.instance+"&user=admin&lang=en_US";

// prepare the Request
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONArray cast = obj.getJSONArray("Detail");
                            for (int i=0; i<cast.length(); i++) {



                                JSONObject actor = cast.getJSONObject(i);

                                String n1 = actor.getString("In_product_code");
                                String n2 = actor.getString("In_product_name");
                                String n3 = actor.getString("In_productcategory_code");
                                String n4 = actor.getString("In_productcategory_desc");
                                String n5 = actor.getString("In_productsubcategory_code");
                                String n6 = actor.getString("In_productsubcategory_desc");
                                String n7 = actor.getString("In_uomtype_code");
                                String n8 = actor.getString("In_uomtype_desc");
                                String n9 = actor.getString("In_hsn_code");
                                String n10 = actor.getString("In_hsn_desc");
                                String n11 = actor.getString("In_base_price");
                                String n12 = actor.getString("In_current_qty");
                                Log.e("CHK",""+actor.getString("In_cgst"));
                                String n13 = actor.getString("In_cgst");

                                String n14 = actor.getString("In_sgst");
                                String n15 = actor.getString("In_igst");
                                String n16 = actor.getString("In_orgn_code");
                                String n17 = actor.getString("In_ic_code");

                                String n18 = actor.getString("In_value_addproduct_verified");



                                mydb.insertContact(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17,n18);


                            }
                          //  pdialog.dismiss();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );

// add it to the RequestQueue
        getRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Landpage.this).addToRequestQueue(getRequest);






        final String url4 = Pojokyc.icdurl+"api/ICDMOBList/allmasterlist?org=QCD_UN_STATE&locn="+Pojokyc.instance+"&user=admin&lang=en_US&parent_code=QCD_UN_STATE";

// prepare the Request
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest getRequest4 = new JsonObjectRequest(Request.Method.GET, url4, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.e("Response", response.toString());
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONArray cast = obj.getJSONArray("detail");
                            for (int i=0; i<cast.length(); i++) {



                                JSONObject actor = cast.getJSONObject(i);
                                String n1 = actor.getString("out_master_description");
                                String n2 = actor.getString("out_master_code");



                                mydb.istate(n1,n2);


                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response", error.toString());
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );

// add it to the RequestQueue
        getRequest4.setRetryPolicy(new DefaultRetryPolicy(
                90000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Landpage.this).addToRequestQueue(getRequest4);
        String oc1[] = sharedpreferences.getString("oc","").split("/");
        final String url5 = Pojokyc.icdurl+"api/ICDMOBList/geticdtransaction?org="+oc1[0]+"&locn=sdfsdf&user=dsfdsf&lang=en_US";

// prepare the Request
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest getRequest5 = new JsonObjectRequest(Request.Method.GET, url5, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.e("Response", response.toString());
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("Header");

                            //String inrid = obj2.getString("IOU_adjustment_rowid");

                            SharedPreferences.Editor editor = sharedpreferences.edit();

                            editor.putString("inwardno", obj2.getString("In_inward_no"));
                            editor.putString("invoiceno", obj2.getString("In_invoice_no"));
                            editor.putString("sano", obj2.getString("In_adjustment_no"));


                            editor.commit();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response", error.toString());
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );

// add it to the RequestQueue
        getRequest5.setRetryPolicy(new DefaultRetryPolicy(
                90000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Landpage.this).addToRequestQueue(getRequest5);
    }
    private void showErrorDialog(String s) {
        new androidx.appcompat.app.AlertDialog.Builder(this)
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
    public  void offlinesavesaleentry()
    {
        final SQLiteDatabase dbs = pawhsdbhelper.getWritableDatabase();
        String selectQuery = "SELECT  * FROM saleentry where v17 = 0";

        final Cursor cursor = dbs.rawQuery(selectQuery, null);
        Log.e("COUNT",""+cursor.getCount());



        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            try {
                jsonParam = new JSONObject();
                JSONObject userd = new JSONObject();
                jsonParam.put("document",userd);
                JSONObject user = new JSONObject();
                user.put("orgnId", orgnCode);
                user.put("locnId", locnId);
                user.put("userId", userId);
                user.put("localeId", localeId);
                userd.put("context",user);
                JSONObject user2 = new JSONObject();
                user2.put("in_Sale_rowid",0);
                user2.put("in_SaleNo","");
                //String[] by = spinnerb.getSelectedItem().toString().split("---");
                user2.put("In_Buyer_code",cursor.getString(1));
                user2.put("In_Buyer_name",cursor.getString(2));

                user2.put("in_Item_Code",cursor.getString(4));
                user2.put("in_Item_Name",cursor.getString(3));

                user2.put("in_Sale_Qty",Double.parseDouble(String.valueOf(cursor.getString(7))));
                if(cursor.getString(14).equalsIgnoreCase("Yes")) {
                    user2.put("in_Sale_Price", 0);
                    user2.put("in_Sale_Value", 0);
                }
                else
                {
                    user2.put("in_Sale_Price", Double.parseDouble(cursor.getString(13)));
                    user2.put("in_Sale_Value", (Double.parseDouble(cursor.getString(7))*Double.parseDouble(cursor.getString(13))));
                }
                user2.put("in_advance_amt",0);
                Cursor cursor2t = dbs.query("sno", new String[]{"id","v1","v2","v3"
                        }, "v3" + "=? COLLATE NOCASE",
                        new String[]{cursor.getString(16)}, null, null, null, null);
                if(cursor.getString(21).equalsIgnoreCase(""))
                {
                    user2.put("in_no_of_bags",0);
                }
                else
                {
                    user2.put("in_no_of_bags",Integer.parseInt(cursor.getString(21)));
                }

                user2.put("in_Status","Active");
                user2.put("in_mode_flag",cursor.getString(20));
                user2.put("in_sale_remarks",cursor.getString(10));

                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(cursor.getString(15)));

                    byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    //Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());


                    encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                    Log.e("ECS",""+encodedImage);


                } catch (Exception e) {
                    Log.e("OUTPUT ERROR",""+Log.getStackTraceString(e));
                    e.printStackTrace();
                }
                user2.put("in_sale_attach",encodedImage);

                if(cursor.getString(11).equalsIgnoreCase(""))
                {
                    user2.put("in_vehicle_type","NA");
                }
                else
                {
                    user2.put("in_vehicle_type",cursor.getString(11));
                }

                if(cursor.getString(12).equalsIgnoreCase(""))
                {
                    user2.put("in_vehicle_no","NA");
                }
                else
                {
                    user2.put("in_vehicle_no",cursor.getString(12));
                }

                if(cursor.getString(8).equalsIgnoreCase(""))
                {
                    user2.put("in_qcperson_name","NA");
                }
                else
                {
                    user2.put("in_qcperson_name",cursor.getString(8));
                }

//                if(cursor.getString(13).equalsIgnoreCase(""))
//                {
                    user2.put("in_LRP_Name","NA");
//                }
//                else
//                {
//                    user2.put("in_LRP_Name",cursor.getString(13));
//                }
//
//                if(cursor.getString(14).equalsIgnoreCase(""))
//                {
                    user2.put("In_LRP_Mobile_no","NA");
//                }
//                else
//                {
//                    user2.put("In_LRP_Mobile_no",cursor.getString(14));
//                }

                user2.put("In_Payment_type","");
                user2.put("In_Bank_acc_no","");
                user2.put("In_cheque_no","");
                user2.put("In_whs_code","");
                user2.put("In_whs_name","");
                user2.put("In_batch_type",cursor.getString(14));
                user2.put("In_buyer_mobileno",cursor.getString(14));
                String[] dt = cursor.getString(9).split("-");
                user2.put("In_Saledate",dt[2]+"-"+dt[1]+"-"+dt[0]);

                user.put("Header",user2);



                JSONArray snodetails = new JSONArray();

                Cursor cursor2 = dbs.query("sno", new String[]{"id","v1","v2","v3"
                        }, "v3" + "=? COLLATE NOCASE",
                        new String[]{cursor.getString(16)}, null, null, null, null);
                if (cursor2.moveToFirst()) {
                    do {
                        JSONObject snolist = new JSONObject();

                        snolist.put("in_slno_row_id",0);
                        snolist.put("in_slno",cursor2.getString(1));
                        snolist.put("in_temp1","");
                        snolist.put("in_temp2","");
                        snolist.put("in_mode_flag","I");
                        JSONArray qpar = new JSONArray();
                        String selectQuery3 = "SELECT  * FROM qpar WHERE v1 ="+cursor2.getString(0);
                        Cursor cursorq = dbs.rawQuery(selectQuery3, null);
                        if (cursorq.moveToFirst()) {
                            do {
                                JSONObject qparlist = new JSONObject();

                                qparlist.put("In_qly_dtl_rowid",0);
                                qparlist.put("In_slno",cursor2.getString(1));
                                String[] q = cursorq.getString(2).split("-");
                                qparlist.put("In_qly_code",q[0]);
                                if(cursorq.getString(3).equalsIgnoreCase("Yes"))
                                {
                                    qparlist.put("In_actual_value",1);
                                }
                                else if(cursorq.getString(3).equalsIgnoreCase("No"))
                                {
                                    qparlist.put("In_actual_value",2);
                                }
                                else
                                {
                                    qparlist.put("In_actual_value",Double.parseDouble(cursorq.getString(3)));
                                }

                                qparlist.put("In_wr_qly_value",0);
                                qparlist.put("in_estimate_qly_value",0);
                                qparlist.put("in_mode_flag","I");


                                qpar.put(qparlist);

                            }

                            while (cursorq.moveToNext());
                        }
                        snolist.put("QlyDetail",qpar);
                        snodetails.put(snolist);
                    }

                    while (cursor2.moveToNext());
                }
                user.put("SlnoDetail",snodetails);








                Log.e("OUTPUT",""+jsonParam.toString());
            }
            catch(Exception e)
            {
                Log.e("OUTPUT ERROR",""+Log.getStackTraceString(e));
                Log.e("OUTPUT",""+e.getMessage());
            }
            HttpsTrustManager.allowAllSSL();
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,ApiUtils.TEST_URL_API+"New_PAWHS_SaleEntry/New_Pawhs_SaleEntry_Save",jsonParam,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("CCCC", "" + response);

                            try {
                                JSONObject obj = response.getJSONObject("context");
                                JSONObject obj2 = obj.getJSONObject("header");

                                if(obj2.getString("in_SaleNo").equalsIgnoreCase("null"))
                                {
                                    showErrorDialog("Unable To Save");
                                }
                                else
                                {
                                    dbs.execSQL("UPDATE saleentry SET v17 = 1 WHERE id = "+cursor.getString(0));
                                    //dbs.execSQL("delete from saleentry where id = "+cursor.getString(0));
                                    String selectQuery = "SELECT  * FROM saleentry where v17 = 0";

                                    pawhsdbhelper.updatesaleentry(Integer.valueOf(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(15),cursor.getString(16),"1",obj2.getString("in_SaleNo"),obj2.getString("in_Sale_rowid"),"U",cursor.getString(21));

                                    Cursor cursor = dbs.rawQuery(selectQuery, null);

                                    if(cursor.getCount()>0)
                                    {
                                        offlinesavesaleentry();
                                    }
                                    else
                                    {
                                        progressLayout.setVisibility(View.GONE);

                                        String selectQuery2 = "SELECT  * FROM purchaseentry where v22 = 0";



                                        Cursor cursor2 = dbs.rawQuery(selectQuery2, null);

                                        if(cursor2.getCount()>0)
                                        {
                                            progressLayout.setVisibility(View.VISIBLE);
//                                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                                                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                            offlinesavepurchaseentry();
                                        }
                                        else
                                        {
                                            showErrorDialog("Transaction Completed");
                                        }

                                    }
                                    dbs.execSQL("Update sno set v5 = '1' where v5 = '0' ");
                                    // showErrorDialog("Saved Successfully !!! Sale No is"+obj2.getString("in_SaleNo"));

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



    }

    public  void offlinesavepurchaseentry()
    {

        final SQLiteDatabase dbs = pawhsdbhelper.getWritableDatabase();
        String selectQuery = "SELECT  * FROM purchaseentry where v22 = 0";

        final Cursor cursor = dbs.rawQuery(selectQuery, null);
        Log.e("COUNT",""+cursor.getCount());



        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            try {
                jsonParam = new JSONObject();
                JSONObject userd = new JSONObject();
                jsonParam.put("document",userd);
                JSONObject user = new JSONObject();
                user.put("orgnId", orgnCode);
                user.put("locnId", locnId);
                user.put("userId", userId);
                user.put("localeId", localeId);
                userd.put("context",user);
                JSONObject user2 = new JSONObject();

                user2.put("in_Actual_rowid",Integer.parseInt(cursor.getString(24)));
                user2.put("in_LotNo",cursor.getString(23));
                //String[] by = spinnerb.getSelectedItem().toString().split("---");
                user2.put("in_Farmer_Code",cursor.getString(1));
                user2.put("in_Farmer_Name",cursor.getString(2));

                user2.put("in_Member_Type",cursor.getString(3));
                user2.put("in_Item_Code",cursor.getString(4));

                user2.put("in_Item_Name",cursor.getString(5));
                user2.put("in_Actual_Qty",Double.parseDouble(cursor.getString(6)));
                user2.put("in_Actual_Price",Double.parseDouble(cursor.getString(7)));
                user2.put("in_Actual_Value",Double.parseDouble(cursor.getString(8)));
                user2.put("in_advance_amt",0);
              //  user2.put("in_no_of_bags",Integer.parseInt(cursor.getString(9)));
                if(cursor.getString(9).equalsIgnoreCase(""))
                {
                    user2.put("in_no_of_bags",0);
                }
                else
                {
                    user2.put("in_no_of_bags",Integer.parseInt(cursor.getString(9)));
                }
                user2.put("in_Status","Actual");
                user2.put("in_mode_flag",cursor.getString(25));
                user2.put("in_actual_remarks",cursor.getString(10));
                user2.put("in_approved_remarks","");
                user2.put("in_pickup_remarks","");
                try {


                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(cursor.getString(11)));

                    byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    //Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());


                    encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                    Log.e("ECS",""+encodedImage);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                user2.put("in_actual_attach",encodedImage);
                if(cursor.getString(12).equalsIgnoreCase(""))
                {
                    user2.put("in_vehicle_type","NA");
                }
                else
                {
                    user2.put("in_vehicle_type",cursor.getString(12));
                }

                if(cursor.getString(13).equalsIgnoreCase(""))
                {
                    user2.put("in_vehicle_no","NA");
                }
                else
                {
                    user2.put("in_vehicle_no",cursor.getString(13));
                }

                if(cursor.getString(14).equalsIgnoreCase(""))
                {
                    user2.put("in_destination","NA");
                }
                else
                {
                    user2.put("in_destination",cursor.getString(14));
                }

                if(cursor.getString(15).equalsIgnoreCase(""))
                {
                    user2.put("in_qcperson_name","NA");
                }
                else
                {
                    user2.put("in_qcperson_name",cursor.getString(15));
                }


                if(cursor.getString(16).equalsIgnoreCase(""))
                {
                    user2.put("in_LRP_Name","NA");
                }
                else
                {
                    user2.put("in_LRP_Name",cursor.getString(16));
                }
                if(cursor.getString(17).equalsIgnoreCase(""))
                {
                    user2.put("In_LRP_Mobile_no","NA");
                }
                else
                {
                    user2.put("In_LRP_Mobile_no",cursor.getString(17));
                }

                user2.put("In_Payment_type","");
                user2.put("In_Bank_acc_no","");
                user2.put("In_cheque_no","");
                user2.put("In_Buyer_code",cursor.getString(19));
                user2.put("In_season",cursor.getString(20));
                String[] dt = cursor.getString(18).split("-");
                user2.put("In_Acc_Date",dt[2]+"-"+dt[1]+"-"+dt[0]);


                user.put("Header",user2);



                JSONArray snodetails = new JSONArray();

                Cursor cursor2 = dbs.query("snope", new String[]{"id","v1","v2","v3"
                        }, "v3" + "=? COLLATE NOCASE",
                        new String[]{cursor.getString(21)}, null, null, null, null);
                if (cursor2.moveToFirst()) {
                    do {
                        JSONObject snolist = new JSONObject();

                        snolist.put("in_slno_row_id",0);
                        snolist.put("in_slno",cursor2.getString(1));
                        snolist.put("in_temp1","");
                        snolist.put("in_temp2","");
                        snolist.put("in_mode_flag","I");

                        //snolist.put("QlyDetail",snolist);
                        snodetails.put(snolist);
                    }

                    while (cursor2.moveToNext());
                }
                user.put("SlnoDetail",snodetails);

                JSONArray qpar = new JSONArray();
                Cursor cursorq = dbs.query("qparpe", new String[]{"id","v1","v2","v3"
                        }, "v1" + "=? COLLATE NOCASE",
                        new String[]{cursor.getString(21)}, null, null, null, null);
                if (cursorq.moveToFirst()) {
                    do {
                        JSONObject qparlist = new JSONObject();

                        qparlist.put("in_qty_dtl_rowid",0);

                        String[] q = cursorq.getString(2).split("-");
                        qparlist.put("in_qty_code",q[0]);
                        if(cursorq.getString(3).equalsIgnoreCase("Yes"))
                        {
                            qparlist.put("in_actual_value",1);
                        }
                        else if(cursorq.getString(3).equalsIgnoreCase("No"))
                        {
                            qparlist.put("in_actual_value",2);
                        }
                        else
                        {
                            qparlist.put("in_actual_value",Double.parseDouble(cursorq.getString(3)));
                        }

                        qparlist.put("in_wr_qty_value",0);

                        qparlist.put("in_mode_flag","I");


                        qpar.put(qparlist);

                    }

                    while (cursorq.moveToNext());
                }
                user.put("QtyDetail",qpar);

                JSONArray od = new JSONArray();
                user.put("OtherDetail",od);



                Log.e("OUTPUT",""+jsonParam.toString());
            }
            catch(Exception e)
            {
                Log.e("OUTPUT ERROR",""+Log.getStackTraceString(e));
                Log.e("OUTPUT",""+e.getMessage());
            }

            //  http://169.38.77.190:101/api/NewPawhsActulProcurment/new_pawhs_ActualProc_save

            Log.e("URL",""+ApiUtils.TEST_URL_API+"NewPawhsActulProcurment/new_pawhs_ActualProc_save");
            HttpsTrustManager.allowAllSSL();
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,ApiUtils.TEST_URL_API+"NewPawhsActulProcurment/new_pawhs_ActualProc_save",jsonParam,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("CCCC", "" + response);

                            try {
                                JSONObject obj = response.getJSONObject("context");
                                JSONObject obj2 = obj.getJSONObject("header");


                                if(obj2.getString("in_LotNo").equalsIgnoreCase("null"))
                                {
                                    showErrorDialog("Unable To Save");
                                }
                                else
                                {

                                    //   dbs.execSQL("DELETE FROM purchaseentry WHERE id = "+cursor.getString(0));

                                    pawhsdbhelper.updatepurchaseentry(Integer.valueOf(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(15),cursor.getString(16),cursor.getString(17),cursor.getString(18),cursor.getString(19),cursor.getString(20),cursor.getString(21),"1",obj2.getString("in_LotNo"),obj2.getString("in_Actual_rowid"),"U");
                                    dbs.execSQL("UPDATE purchaseentry SET v22 = 1 WHERE id = "+cursor.getString(0));


                                    String selectQuery = "SELECT  * FROM purchaseentry where v22 = 0";

                                    final Cursor cursor = dbs.rawQuery(selectQuery, null);
                                    Log.e("COUNT",""+cursor.getCount());



                                    // looping through all rows and adding to list

                                    if(cursor.getCount()>0)
                                    {
                                        offlinesavepurchaseentry();
                                    }
                                    else
                                    {
                                        progressLayout.setVisibility(View.GONE);
                                        showErrorDialog("Transaction Completed");
                                    }
                                }
                                dbs.execSQL("Update snowithqty set v7 = '1' where v7 = '0' ");
                            }
                            catch (Exception e)
                            {

                            }
                            progressLayout.setVisibility(View.GONE);

                        }



                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

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



    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void savegps(String v1,String v2,String v3,String v4,String v5,String v6,String v7,String v8)
    {
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Saving ...");
        pdialog.show();
        final JSONObject header = new JSONObject(),context = new JSONObject(),
                document = new JSONObject(),gpsobj = new JSONObject();
        try{
            header.put("in_usergps_id",0);
            header.put("in_qrvalue",v1);
            header.put("in_latitude",v2);
            header.put("in_longitude",v3);
            header.put("in_date",v4);
            header.put("in_input_start_time",v5);
            header.put("in_input_close_time",v6);
            header.put("in_pa_start_time",v7);
            header.put("in_pa_close_time",v8);
            header.put("in_usercode",sharedpreferences.getString("uc",""));
            header.put("in_mode_flag","I" );
            context.put("orgnId","flexi");
            context.put("locnId", "chennai");
            context.put("userId", "admin");
            context.put("localeId", "en_US");
            context.put("instance", "up");
            context.put("header",header);

            document.put("context",context);

            gpsobj.put("document",document);
        }catch (Exception er){
            Log.e("error",er.toString());
        }
        Log.e("Jsonbody",gpsobj.toString());
        String gpssaveurl = Pojokyc.url + "/api/FDR_Constent/gpssave";
        Log.e("url",gpssaveurl);
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest gps_save_request = new JsonObjectRequest(Request.Method.POST, gpssaveurl, gpsobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pdialog.dismiss();
                Log.e("response",response.toString());
                JSONObject context1  = new JSONObject(),header1 = new JSONObject();
                String errorNo = "";
                int gpsid = 0;
                try{
                    context1 = response.getJSONObject("context");
                    header1 = context1.getJSONObject("header") ;
                    errorNo = header1.getString("errorNo");
                    gpsid = header1.getInt("in_usergps_id");
                }catch (Exception er){
                    Log.e("error",er.toString());
                }
                if(errorNo != null && errorNo.equalsIgnoreCase("Success") && gpsid != 0){
                   // alertdialog("GPS Saved Successfully");
                    gpscount2++;
                    dbs.execSQL("update gpsactivity set flag = '1' where id = '"+gpsrid+"'");
                    Cursor cursor = dbs.rawQuery("select * from gpsactivity where flag = '0'",null);
                    if(cursor.getCount()>0)
                    {
                        //gpscount = cursor.getCount();
                        if(cursor.moveToFirst())
                        {
                            gpsrid = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                            String[] latlng = cursor.getString(2).split("/");
                            savegps(cursor.getString(1),latlng[0],latlng[1],cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
                        }

                    }
                    if(gpscount == gpscount2)
                    {
                        pdialog.dismiss();
                        alertdialog("Transaction Success!!");
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pdialog.dismiss();
                Log.e("response",error.toString());
                alertdialog(error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        gps_save_request.setRetryPolicy(new DefaultRetryPolicy(
                80000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Landpage.this).addToRequestQueue(gps_save_request);
    }
    public void alertdialog(String message){
        final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                .setIcon(android.R.drawable.ic_menu_save)
//set title
                .setTitle("Success!")
//set message
                .setMessage(message+" !")
//set positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what would happen when positive button is clicked
                                // finish();
                               // finish();
                            }
                        }
//set negative button

                )
                .setCancelable(false)
                .show();
    }
    private void membermap(final String fcode){
        jsonParam = new JSONObject();
        JSONObject userd = new JSONObject();
        try {
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", sharedpreferences.getString("oc1",""));
            user.put("locnId", "up");
            user.put("userId", "en_US");
            user.put("localeId", "admin");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();
            user2.put("In_fpoorgn_code",sharedpreferences.getString("oc1",""));
            user.put("Header",user2);
            JSONArray user4 = new JSONArray();
            JSONObject user3 = new JSONObject();
            user3.put("In_fpomember_rowid", 0);
            user3.put("In_fpomember_code", "0");
            user3.put("In_farmer_code", fcode);
            user3.put("In_status_code", "A");
            user3.put("In_row_timestamp", "");
            user3.put("In_mode_flag", "I");
            user4.put(user3);
            user.put("Map",user4);
            Log.e("farmercode", fcode);
            Log.e("OUTPUT", jsonParam.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpsTrustManager.allowAllSSL();
        final String url = Pojokyc.icdurl+"api/FISFarmermapping/newFPOFarmerMap";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("responses", "" + response);
                        try {
                            memcount2++;
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("header");
                            JSONObject obj3 = response.getJSONObject("applicationException");
                            if(memcount == memcount2) {
                                final AlertDialog alertDialog = new AlertDialog.Builder(Landpage.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("INFO!")
//set message
                                        .setMessage("" + obj3.getString("errorDescription"))
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        //finish();
                                                        dialogInterface.dismiss();

                                                    }
                                                }
//set negative button

                                        )
                                        .setCancelable(false)
                                        .show();
                            }
                            if(obj3.getString("errorNumber").equalsIgnoreCase("Success"))
                            {
                                dbs.execSQL("update membermapping set flag = '1' where farmercode='"+fcode+"'");
                            }
                            Cursor cursor = dbs.rawQuery("select * from membermapping where flag = '0'",null);
                            if(cursor.getCount()>0)
                            {
                               // memcount = cursor.getCount();
                                if(cursor.moveToFirst())
                                {
                                    membermap(cursor.getString(cursor.getColumnIndexOrThrow("farmercode")));
                                    //memid.add(cursor.getString(cursor.getColumnIndexOrThrow("farmercode")));
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("error123", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                2500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
    public void serialnumberfetch_api(){
        final SQLiteDataBaseHandler dbpa = new SQLiteDataBaseHandler(Landpage.this);
        SQLiteDatabase dbspa = dbpa.getWritableDatabase();
        String sl_fetch_url = ApiUtils.TEST_URL_API  + "New_PAWHS_SaleEntry/Pawhs_sale_qty";
        JSONObject serialobj = new JSONObject();
        try{
            serialobj.put("instance","up");
            serialobj.put("fpocode",sharedpreferences.getString("fpocode",""));
            serialobj.put("aggcode",sharedpreferences.getString("orgncode",""));
            Log.e("seriallistjson",serialobj.toString());
        }catch (Exception er){
            Log.e("error",er.toString());
        }
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest sl_fetch_request = new JsonObjectRequest(Request.Method.POST, sl_fetch_url, serialobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response",response.toString());
                JSONArray list = new JSONArray();
                String slno  = "",qty = "",prodcode= "",lbval = "",ts = "";
                Cursor cr = null;
                try {
                  //  ts = String.valueOf((System.currentTimeMillis()/1000));
                    list = response.getJSONArray("list");
                    for(int l =0; l < list.length(); l++){
                        try{
                            JSONObject listobj = new JSONObject();
                            listobj = list.getJSONObject(l);
                            slno = listobj.getString("serial_no");
                            qty = listobj.getString("serial_qty");
                            prodcode = listobj.getString("product_code");
                            if(slno.substring(0,2).equalsIgnoreCase("LB")){
                                lbval = "Y";
                            }else{
                                lbval = "N";
                            }
                            cr = dbs_pa.rawQuery("Select * from snowithqty where v1 = '"+slno+"' ",null);
                            if(cr.getCount() > 0){
                                dbs_pa.execSQL("delete from snowithqty where v1 = '"+slno+"' ");
                                pawhsdbhelper.insertsnowithqty(slno,qty,"",lbval,"1",prodcode,"1");
                            }else{
                                pawhsdbhelper.insertsnowithqty(slno,qty,"",lbval,"1",prodcode,"1");
                            }
                        }catch (Exception er){
                            Log.e("error",Log.getStackTraceString(er));
                        }
                    }
                  //  cr.close();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",Log.getStackTraceString(error));
                showErrorDialog(""+error.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        sl_fetch_request.setRetryPolicy(new DefaultRetryPolicy(
                1500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Landpage.this).addToRequestQueue(sl_fetch_request);
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
}