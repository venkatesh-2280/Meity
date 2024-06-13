package cdfi.fintantra.meity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.paytm.ecr.bluetooth.sdk.IConnectionStateListener;
import com.paytm.ecr.bluetooth.sdk.PaytmPayments;
import com.paytm.ecr.bluetooth.sdk.ResponseListener;
import com.paytm.ecr.bluetooth.sdk.constants.ConnectionError;
import com.paytm.ecr.bluetooth.sdk.constants.ConnectionState;
import com.paytm.ecr.bluetooth.sdk.model.Config;
import com.paytm.ecr.bluetooth.sdk.model.request.CancelRequest;
import com.paytm.ecr.bluetooth.sdk.model.request.ConnectionCheckRequest;
import com.paytm.ecr.bluetooth.sdk.model.request.SaleRequest;
import com.paytm.ecr.bluetooth.sdk.model.request.StatusCheckRequest;
import com.paytm.ecr.bluetooth.sdk.model.response.BaseResponse;
import com.service.aepsnew.Host;
import com.treebo.internetavailabilitychecker.InternetConnectivityListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Bookinvoicenew extends AppCompatActivity implements IConnectionStateListener, ResponseListener {
    AutoCompleteTextView eicd;
    EditText esl;
    String successstatus="1";
    StringBuilder stringBuilder;
    Dialog dialogpayment;
    String paymodecode,saleresponse, paytmbluetooth;
    String printqry;
    RelativeLayout rlocation;
    TextView txttimer;
    Spinner spinner;
    String tspr="";
    int salestatus = 0;

    PaytmPayments payments;
    String merchantId = "",orderID, aeps_merchantId = "";
    AppCompatButton cconnecton,osetting,reqapi;
    ImageView sconnecton;
    LinearLayout llpaytm;
    ArrayList<String> array1 = new ArrayList<>();
    ArrayList<String> array1n = new ArrayList<>();
    Button detail,header,payment;
    public LinearLayout l1,l2,l3,l4;
    MyRecyclerViewAdapter adapter;
    MyRecyclerViewAdapter2 adapter2;
    AutoCompleteTextView esupplier,esupplier2;
    TextView fponame,name;
    ImageView msearch,csearch;
    int ck=0;
    AutoCompleteTextView esuppliern;
    String code;
    String phn;
    int ta;
    String btnn = "0";
    String myr,itext;
    String mm = "0";
    String cq,newcq;
    JSONObject userd;
    String cqno;
    EditText newnet;
    LinearLayout lsup;
    ImageView bqa,bqd;
    String inno;
    int bq=0;
    TextView txtheader;
    RelativeLayout rdetail,rsummary,rpayment;
    ImageView im1,im2,im3;
    View v1,v2,v3;
    int bluestatus = 0;
    String foc="0";
    LinearLayout lch;
    String sun,suc,sup,susc,sum,sul;
    androidx.recyclerview.widget.RecyclerView recyclerViewp;
    MyRecyclerViewAdapter3 adapter3;
    MyRecyclerViewAdapterp adapterp;
    MyRecyclerViewAdapterd adapterd;
    EditText eamtpaid,erefno,epdate,ebal,inputsearch;
    Button view,view3,view2,viewp,lsave;
    Spinner spinnerm,spinnerl;
    ImageView back;
    int button = 0;
    ProgressDialog pdialog;
    String pyid,cname,innob,am;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    String payid,payno,payinamt,paybalamt,paymode,refno,paydate,payamt,paidamt,payindate;

    String lc2;
    String lc;
    int ct=0,ct2=0,ctt=0,ct22=0,c3=0,ct3=0;
    JSONObject jsonParam;
    String ss="";
    EditText chno;
    String at,atno,aqc,aqcd,aqsc,aqsc2,aqpc,aqpd,aqu,aqi,aqd,aqpco,aqi2,aty,aqid,inwid;

    String sname,slcn,sst,encodedimage="",ids,farco,ids2,tax,oth,trs,net,pr,pd,pt,pa,pq,pn,pc,psc,pco,pu,amt,id,bp,balamt;
    String sname2,slcn2,sst2,encodedimage2="",ids22,ids222,tax2,oth2,trs2,net2,pr2,pd2,pt2,pa2,pq2,pn2,pc2,psc2,pco2,pu2,amt2,id2;
    Dialog dialog,dialog2,dialog3;
    ImageView list;
    String mcode;

    String idn;
    String lcn;
    String prid;
    String trp="0",oths="0";
    int setflag = 0;
    ArrayList<String>  mStringList= new ArrayList<String>();
    ArrayList<String>  mStringList2= new ArrayList<String>();
    EditText edate,eamount,ediscount,enetamount,erate,eitem,equantity,etotal,ecount,elocation,etax,etransport,eothers,einwno,einwamt;
    private DatePicker datePicker;
    private Calendar calendar;
    double fi=0;
    String suppliername="",suppliername2="";
    AutoCompleteTextView item,supplier;
    ArrayList<String> array,array2,array3,array4,array5,arrayn,arrayn2,arrayd;
    TextView invoiceno,amount,balance,paid;

    Calendar myCalendar;
    Button badd;
    DBHelper db;
    List<pojoProduct> productlist;
    List<pojoProducttax> producttaxlist;
    List<pojoPayment> invoicelist;
    List<pojopaytax> paylist;
    List<Dialogpojo> dialoglist;
    String ts;
    ArrayAdapter<String> dataAdapter,dataAdapterl;
    ArrayList<String>categoriescode = new ArrayList<>();
    ArrayList<String> categoriespay = new ArrayList<String>();
    int response;
    String message = "";
    String txnrefrenceid;
    boolean statuscode;
     String flag = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_bookinvoicenew);

        rlocation = (RelativeLayout)findViewById(R.id.rlocation);
        item = (AutoCompleteTextView)findViewById(R.id.eitem);

        eicd = (AutoCompleteTextView)findViewById(R.id.eicd);
        esl = (EditText)findViewById(R.id.esl);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        esl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SnoActivity2.class);
                i.putExtra("TS",tspr);
                startActivity(i);
            }
        });

        array = new ArrayList<>();
        array2 = new ArrayList<>();
        array3 = new ArrayList<>();
        array4 = new ArrayList<>();
        array5 = new ArrayList<>();
        arrayn = new ArrayList<>();
        arrayn2 = new ArrayList<>();
        arrayd = new ArrayList<>();
        getSupportActionBar().hide();
        view = (Button)findViewById(R.id.view);
        view3 = (Button)findViewById(R.id.view3);
        view2 = (Button)findViewById(R.id.view2);
        viewp = (Button)findViewById(R.id.viewp);
        payment = (Button)findViewById(R.id.payment);
        back = (ImageView)findViewById(R.id.back);
        lsup = (LinearLayout)findViewById(R.id.lsup);
        pdialog = new ProgressDialog(this);
        invoiceno = (TextView)findViewById(R.id.invoiceno);
        amount = (TextView)findViewById(R.id.balance);
        balance = (TextView)findViewById(R.id.amount);
        paid = (TextView)findViewById(R.id.paid);
        llpaytm = (LinearLayout) findViewById(R.id.llpaytm);
        spinnerm = (Spinner)findViewById(R.id.spinnerm);
        spinnerl = (Spinner)findViewById(R.id.spinnerl);

        esuppliern = (AutoCompleteTextView) findViewById(R.id.esuppliern);
        newnet = (EditText)findViewById(R.id.newnet);
        bqa = (ImageView)findViewById(R.id.qa);
        bqd = (ImageView)findViewById(R.id.qd);
        msearch = (ImageView)findViewById(R.id.msearch);
        csearch = (ImageView)findViewById(R.id.csearch);
        lch = (LinearLayout)findViewById(R.id.lch);
        chno = (EditText)findViewById(R.id.chno);

        rdetail = (RelativeLayout)findViewById(R.id.rdetail);
        rsummary = (RelativeLayout)findViewById(R.id.rsummary);
        rpayment = (RelativeLayout)findViewById(R.id.rpayment);
        rpayment.setEnabled(false);

        im1 = (ImageView)findViewById(R.id.im1);
        im2 = (ImageView)findViewById(R.id.im2);
        im3 = (ImageView)findViewById(R.id.im3);


        sconnecton = findViewById(R.id.sconnecton);
        cconnecton = findViewById(R.id.cconnecton);
        osetting = findViewById(R.id.osetting);
        reqapi = findViewById(R.id.reqapi);


        payments = PaytmPayments.with(this);
        payments.init(new Config.Builder()
                .setStatusCheckOnSaleRequestEnabled(true).build());
        lsave = (Button)findViewById(R.id.lsave);


        ConnectionCheckRequest connectionCheckRequest = new ConnectionCheckRequest.Builder().build();
        String connectionCheckRequestId = String.valueOf(System.currentTimeMillis());
        com.paytm.ecr.bluetooth.sdk.model.Request<ConnectionCheckRequest> request1 = com.paytm.ecr.bluetooth.sdk.model.Request.of(connectionCheckRequest, connectionCheckRequestId);
        payments.doConnectionCheck(request1, Bookinvoicenew.this);

        salestatus = 20;

        sconnecton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectionCheckRequest connectionCheckRequest = new ConnectionCheckRequest.Builder().build();
                String connectionCheckRequestId = String.valueOf(System.currentTimeMillis());
                com.paytm.ecr.bluetooth.sdk.model.Request<ConnectionCheckRequest> request1 = com.paytm.ecr.bluetooth.sdk.model.Request.of(connectionCheckRequest, connectionCheckRequestId);
                payments.doConnectionCheck(request1, Bookinvoicenew.this);

                salestatus = 20;

               /* if (!payments.isConnected()) {
                    payments.openConnection(Bookinvoicenew.this);
                }
                else
                {
                    payments.closeConnection();
                }*/
            }
        });
        cconnecton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
        osetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payments.openBluetoothSettings();
            }
        });
        reqapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salestatus = 0;
                StatusCheckRequest.Builder builder = new StatusCheckRequest.Builder()
                        .setMerchantId((TextUtils.isEmpty(merchantId) ? null : merchantId))
                        .setOrderId(orderID);


                StatusCheckRequest statusCheckRequest = builder.build();
                String srRequestId = String.valueOf(System.currentTimeMillis());
                com.paytm.ecr.bluetooth.sdk.model.Request<StatusCheckRequest> request = com.paytm.ecr.bluetooth.sdk.model.Request.of(statusCheckRequest, srRequestId);
                payments.doStatusCheck(request, Bookinvoicenew.this);
            }
        });


        v1 = (View)findViewById(R.id.v1);
        v2 = (View)findViewById(R.id.v2);
        v3 = (View)findViewById(R.id.v3);

        newnet.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});

        txtheader = (TextView)findViewById(R.id.txtheader);
        final List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("Member");
        categories.add("Non Member");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerm.setAdapter(dataAdapter);
        spinnerm.setEnabled(false);
        fponame = (TextView)findViewById(R.id.fponame);
        name = (TextView)findViewById(R.id.name);
        fponame.setText(sharedpreferences.getString("rn",""));
        name.setText("Welcome "+sharedpreferences.getString("un",""));
        db =new  DBHelper(this);
        final SQLiteDatabase dbs = db.getWritableDatabase();




        Cursor cmid = dbs.rawQuery("select * from mercid where out_paytm_fpocode = '"+sharedpreferences.getString("oc1","")+"'",null);

        if(cmid.getCount()>0)
        {
            if (cmid.moveToFirst())
            {
                merchantId = cmid.getString(4);
            }
        }

        Cursor cmid_aeps = dbs.rawQuery("select * from aepsmercid where out_aeps_fpocode = '"+sharedpreferences.getString("oc1","")+"'",null);

        if(cmid_aeps.getCount()>0)
        {
            if (cmid_aeps.moveToFirst())
            {
                aeps_merchantId = cmid_aeps.getString(4);
            }
        }

        if(sharedpreferences.getString("rc","").equalsIgnoreCase("ROLE_ICD_MOBILE")||sharedpreferences.getString("cf","").equalsIgnoreCase("PrimaryOrSecondary")) {

            String selectQueryicd = "SELECT  * FROM icdlist ";
            if(sharedpreferences.getString("rc","").equalsIgnoreCase("ROLE_SECONDARY_IC")){
                selectQueryicd += " where v1 = '"+sharedpreferences.getString("oc","")+"' ";
            }
            Log.e("qry",selectQueryicd);
            Cursor cursor2ic = dbs.rawQuery(selectQueryicd, null);
            // Toast.makeText(this, ""+cursor2ic.getCount(), Toast.LENGTH_SHORT).show();
            if (cursor2ic.moveToFirst()) {
                do {

                    array1.add(cursor2ic.getString(1));
                    array1n.add(cursor2ic.getString(2));




                } while (cursor2ic.moveToNext());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (Bookinvoicenew.this, R.layout.spinnertext3, array1n);
                //Getting the instance of AutoCompleteTextView

                eicd.setThreshold(0);//will start working from first character
                eicd.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                eicd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eicd.showDropDown();
                        eicd.requestFocus();
                    }
                });
            }
        }
        else
        {
            eicd.setText(sharedpreferences.getString("rn",""));
            eicd.setEnabled(false);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("ocnew", sharedpreferences.getString("oc",""));
            editor.putString("oc1new", sharedpreferences.getString("oc",""));
            editor.commit();
            if(isNetworkAvailable())
            {
                dbs.execSQL("delete from product");

                pdialog.setCanceledOnTouchOutside(false);
                pdialog.setTitle("Loading.....");
                pdialog.show();
                final String url = Pojokyc.icdurl+"api/ICDMOBProduct/ICD_MOBILE_Product?org=" + sharedpreferences.getString("oc", "") + "&locn="+Pojokyc.instance+"&user=admin&lang=en_US";

// prepare the Request
                HttpsTrustManager.allowAllSSL();
                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // display response
                                try {
                                    JSONObject obj = response.getJSONObject("context");
                                    JSONArray cast = obj.getJSONArray("Detail");
                                    for (int i = 0; i < cast.length(); i++) {


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
                                        Log.e("CHK", "" + actor.getString("In_cgst"));
                                        String n13 = actor.getString("In_cgst");

                                        String n14 = actor.getString("In_sgst");
                                        String n15 = actor.getString("In_igst");
                                        String n16 = actor.getString("In_orgn_code");
                                        String n17 = actor.getString("In_ic_code");


                                        String n18 = actor.getString("In_value_addproduct_verified");



                                        db.insertContact(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17,n18);

                                    }

                                    Cursor cursor = dbs.query("product", new String[]{"In_ic_code","In_product_name","In_current_qty"
                                            }, "In_ic_code" + "=? COLLATE NOCASE",
                                            new String[]{sharedpreferences.getString("oc","")}, null, null, null, null);

                                    if(cursor.getCount()>0)
                                    {

                                        item.setEnabled(true);
                                        if (cursor.moveToFirst()) {
                                            do {

                                                if(cursor.getString(0).equalsIgnoreCase(sharedpreferences.getString("oc","")))
                                                {
                                                    if(array.contains(cursor.getString(1)))
                                                    {

                                                    }
                                                    else
                                                    {
                                                        array.add(cursor.getString(1));
                                                        array2.add(cursor.getString(2));
                                                    }
                                                }

                                            } while (cursor.moveToNext());
                                        }

                                        ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(Bookinvoicenew.this,
                                                R.layout.spinnertext, array);
                                        item.setAdapter(adapterlist);


                                    }


                                    pdialog.dismiss();

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
                VolleySingleton.getInstance(Bookinvoicenew.this).addToRequestQueue(getRequest);
            }
            else
            {
                Cursor cursor = dbs.query("product", new String[]{"In_ic_code","In_product_name","In_current_qty"
                        }, "In_ic_code" + "=? COLLATE NOCASE",
                        new String[]{sharedpreferences.getString("oc","")}, null, null, null, null);

                if(cursor.getCount()>0)
                {
                    item.setEnabled(true);
                    if (cursor.moveToFirst()) {
                        do {

                            if(cursor.getString(0).equalsIgnoreCase(sharedpreferences.getString("oc","")))
                            {
                                if(array.contains(cursor.getString(1)))
                                {

                                }
                                else
                                {
                                    array.add(cursor.getString(1));
                                    array2.add(cursor.getString(2));
                                }
                            }

                        } while (cursor.moveToNext());
                    }

                    ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(Bookinvoicenew.this,
                            R.layout.spinnertext, array);
                    item.setAdapter(adapterlist);

                }

                else
                {
                    item.setEnabled(false);
                    final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("No Item Available")
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
        }
        eicd.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // Toast.makeText(Bookinvoicenew.this, ""+array1.get(position), Toast.LENGTH_SHORT).show();


                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("ocnew", array1.get(position));
                editor.putString("oc1new", array1.get(position));
                editor.commit();
                item.setText("");
                erate.setText("");


                if(isNetworkAvailable())
                {
                    dbs.execSQL("delete from product");

                    pdialog.setCanceledOnTouchOutside(false);
                    pdialog.setTitle("Loading.....");
                    pdialog.show();
                    final String url = Pojokyc.icdurl+"api/ICDMOBProduct/ICD_MOBILE_Product?org=" + sharedpreferences.getString("ocnew", "") + "&locn="+Pojokyc.instance+"&user=admin&lang=en_US";

// prepare the Request
                    HttpsTrustManager.allowAllSSL();
                    JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // display response
                                    try {
                                        JSONObject obj = response.getJSONObject("context");
                                        JSONArray cast = obj.getJSONArray("Detail");
                                        for (int i = 0; i < cast.length(); i++) {


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
                                            Log.e("CHK", "" + actor.getString("In_cgst"));
                                            String n13 = actor.getString("In_cgst");

                                            String n14 = actor.getString("In_sgst");
                                            String n15 = actor.getString("In_igst");
                                            String n16 = actor.getString("In_orgn_code");
                                            String n17 = actor.getString("In_ic_code");


                                            String n18 = actor.getString("In_value_addproduct_verified");



                                            db.insertContact(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17,n18);

                                        }

                                        Cursor cursor = dbs.query("product", new String[]{"In_ic_code","In_product_name","In_current_qty"
                                                }, "In_ic_code" + "=? COLLATE NOCASE",
                                                new String[]{sharedpreferences.getString("ocnew","")}, null, null, null, null);

                                        if(cursor.getCount()>0)
                                        {

                                            item.setEnabled(true);
                                            if (cursor.moveToFirst()) {
                                                do {

                                                    if(cursor.getString(0).equalsIgnoreCase(sharedpreferences.getString("ocnew","")))
                                                    {
                                                        if(array.contains(cursor.getString(1)))
                                                        {

                                                        }
                                                        else
                                                        {
                                                            array.add(cursor.getString(1));
                                                            array2.add(cursor.getString(2));
                                                        }
                                                    }

                                                } while (cursor.moveToNext());
                                            }

                                            ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(Bookinvoicenew.this,
                                                    R.layout.spinnertext, array);
                                            item.setAdapter(adapterlist);


                                        }

                                        else
                                        {
                                            item.setEnabled(false);
                                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                                    .setTitle("Alert!")
//set message
                                                    .setMessage("No Item Available")
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
                                        pdialog.dismiss();

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
                    VolleySingleton.getInstance(Bookinvoicenew.this).addToRequestQueue(getRequest);
                }
                else
                {
                    Cursor cursor = dbs.query("product", new String[]{"In_ic_code","In_product_name","In_current_qty"
                            }, "In_ic_code" + "=? COLLATE NOCASE",
                            new String[]{sharedpreferences.getString("ocnew","")}, null, null, null, null);

                    if(cursor.getCount()>0)
                    {
                        item.setEnabled(true);
                        if (cursor.moveToFirst()) {
                            do {

                                if(cursor.getString(0).equalsIgnoreCase(sharedpreferences.getString("ocnew","")))
                                {
                                    if(array.contains(cursor.getString(1)))
                                    {

                                    }
                                    else
                                    {
                                        array.add(cursor.getString(1));
                                        array2.add(cursor.getString(2));
                                    }
                                }

                            } while (cursor.moveToNext());
                        }

                        ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(Bookinvoicenew.this,
                                R.layout.spinnertext, array);
                        item.setAdapter(adapterlist);

                    }

                    else
                    {
                        item.setEnabled(false);
                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Alert!")
//set message
                                .setMessage("No Item Available")
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


            }
        });
        spinnerm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if(position ==1)
                {
                    //  supplier.setEnabled(true);

//                // supplier.setText("");
//                   // suppliername2="";
//                   // elocation.setText("");
//                   // counttotal();
//                 elocation.setVisibility(View.VISIBLE);
//                    spinnerl.setVisibility(View.GONE);

                }
                else if(position == 2)
                {
                    // supplier.setEnabled(true);
                    // supplier.setText("");
//                    suppliername="";
//                   // counttotal();
//

                    // Toast.makeText(Bookinvoicenew.this, "Non Mem", Toast.LENGTH_SHORT).show();
//                    elocation.setVisibility(View.GONE);
//                    spinnerl.setVisibility(View.VISIBLE);
                    // supplier.setAdapter(null);


                }
                else
                {
//                    suppliername="";
//                    suppliername2="";
//                  //  supplier.setEnabled(false);
//                    supplier.setText("");

                    // supplier.setAdapter(null);
                }
                // your code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        spinnerl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if(spinnerl.getSelectedItem().toString().equalsIgnoreCase("Select"))
                {

                }
                else
                {

                    suppliername2 = (String) parentView.getItemAtPosition(position);
                    suppliername="";
                    // lcn="1";
                    mcode = "QCD_UOM_NONMEM";
                    //elocation.setText(suppliername2);
                    counttotal();
//
                }
                // your code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });




        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        getSupportActionBar().hide();

        productlist = new ArrayList<>();
        producttaxlist = new ArrayList<>();
        invoicelist = new ArrayList<>();
        paylist = new ArrayList<>();
        dialoglist = new ArrayList<>();
        list = (ImageView)findViewById(R.id.list);

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), VSlist.class);
                i.putExtra("F","2");
                startActivity(i);
            }
        });



        supplier = (AutoCompleteTextView)findViewById(R.id.esupplier);
        item.setThreshold(1);
        item.requestFocus();






        view = (Button)findViewById(R.id.view);
        view3 = (Button)findViewById(R.id.view3);
        view2 = (Button)findViewById(R.id.view2);
        edate = (EditText)findViewById(R.id.eddate);
        badd = (Button)findViewById(R.id.badd);

         spinner = (Spinner) findViewById(R.id.spinner);




        erate = (EditText)findViewById(R.id.erate);
        eamount = (EditText)findViewById(R.id.eamount);
        enetamount = (EditText)findViewById(R.id.enetamount);
        ediscount = (EditText)findViewById(R.id.ediscount);
        equantity = (EditText)findViewById(R.id.equantity);
        etotal = (EditText)findViewById(R.id.etotal);
        ecount = (EditText)findViewById(R.id.ecount);
        elocation = (EditText)findViewById(R.id.elocation);
        etax = (EditText)findViewById(R.id.etax);
        inputsearch = (EditText)findViewById(R.id.inputSearch);

        bqa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bq++;
                equantity.setText(""+bq);
            }
        });
        bqd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bq != 0) {
                    bq--;
                    equantity.setText("" + bq);
                }
                else
                {
                    equantity.setText("");
                }
            }
        });
        inputsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equalsIgnoreCase("")) {
                    adapterp.getFilter().filter("");
                } else {
                    try {
                        adapterp.getFilter().filter(charSequence);

                    } catch (Exception e) {
                        //   Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                    }


                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equalsIgnoreCase("")) {
                    adapterp.getFilter().filter("");
                } else {
                    try {
                        adapterp.getFilter().filter(charSequence);

                    } catch (Exception e) {
                        //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                if (editable.toString().equalsIgnoreCase("")) {
                    adapterp.getFilter().filter("");
                } else {
                    try {
                        adapterp.getFilter().filter(editable);

                    } catch (Exception e) {
                        //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


        etransport = (EditText)findViewById(R.id.etransport);
        eothers = (EditText)findViewById(R.id.eothers);

        einwamt = (EditText)findViewById(R.id.einwamt);
        einwno = (EditText)findViewById(R.id.einwno);

        eamtpaid = (EditText)findViewById(R.id.eamountpaid);
        erefno = (EditText)findViewById(R.id.erefno);

        ebal = (EditText)findViewById(R.id.ebalance);
        epdate = (EditText)findViewById(R.id.epdate);

        erate.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        eamount.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        enetamount.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        ediscount.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        etotal.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        etransport.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        eothers.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        einwamt.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        einwno.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        equantity.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});

        eamtpaid.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        ebal.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});


        String selectQueryl = "SELECT  * FROM state";

        Cursor cursorl = dbs.rawQuery(selectQueryl, null);
        array5.add("Select");
        if (cursorl.moveToFirst()) {
            do {

                array5.add(cursorl.getString(1));


            } while (cursorl.moveToNext());
            dataAdapterl = new ArrayAdapter<String>(Bookinvoicenew.this, android.R.layout.simple_spinner_item, array5);

            // Drop down layout style - list view with radio button
            dataAdapterl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinnerl.setAdapter(dataAdapterl);
        }
        lsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Date c = Calendar.getInstance().getTime();
//                System.out.println("Current time => " + c);
//
//                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//                String formattedDate = df.format(c);
//                Toast.makeText(Bookinvoicenew.this, ""+formattedDate, Toast.LENGTH_SHORT).show();
//
//                SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                editor.putString("date", formattedDate);
//                editor.commit();

                if(button == 0 && btnn.equalsIgnoreCase("0")) {
                    if(etransport.getText().toString().equalsIgnoreCase(""))
                    {
                        trp = "0";
                    }
                    else
                    {
                        trp = etransport.getText().toString();

                    }
                    if(eothers.getText().toString().equalsIgnoreCase(""))
                    {
                        oths = "0";
                    }
                    else
                    {
                        oths = eothers.getText().toString();

                    }


                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
                    String formattedDate = df.format(c);
                    // Toast.makeText(Bookinvoicenew.this, ""+formattedDate, Toast.LENGTH_SHORT).show();
                    if (supplier.getText().toString().equalsIgnoreCase("")) {
                        // Toast.makeText(Bookinvoicenew.this, "Supplier Name is Empty", Toast.LENGTH_SHORT).show();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Please Enter Customer Number!")
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
                    else if (elocation.getText().toString().equalsIgnoreCase("")) {
                        // Toast.makeText(Bookinvoicenew.this, "Supplier Location is Empty", Toast.LENGTH_SHORT).show();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Please Enter Location !")
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
//                   else  if (etransport.getText().toString().equalsIgnoreCase("")) {
//                       // Toast.makeText(Bookinvoicenew.this, "Transport is Empty", Toast.LENGTH_SHORT).show();
//                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
////set icon
//                                .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                .setTitle("Error!")
////set message
//                                .setMessage("Please Enter Transport Amount !")
////set positive button
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                //set what would happen when positive button is clicked
//
//                                            }
//                                        }
////set negative button
//
//                                )
//                                .show();
//
//                    }
                    else  if (supplier.getText().toString().equalsIgnoreCase("")) {
                        // Toast.makeText(Bookinvoicenew.this, "Transport is Empty", Toast.LENGTH_SHORT).show();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Please Enter Mobile No !")
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
                    else  if (supplier.getText().toString().length() < 10) {
                        // Toast.makeText(Bookinvoicenew.this, "Transport is Empty", Toast.LENGTH_SHORT).show();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Please Enter 10 Digit Mobile No !")
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
                    else  if (esuppliern.getText().toString().equalsIgnoreCase("")) {
                        // Toast.makeText(Bookinvoicenew.this, "Transport is Empty", Toast.LENGTH_SHORT).show();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Please Enter Name !")
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
                    else  if (edate.getText().toString().equalsIgnoreCase("")) {
                        // Toast.makeText(Bookinvoicenew.this, "Date is Empty", Toast.LENGTH_SHORT).show();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Please Enter Date !")
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
//                    else if (eothers.getText().toString().equalsIgnoreCase("")) {
//                       // Toast.makeText(Bookinvoicenew.this, "Others is Empty", Toast.LENGTH_SHORT).show();
//                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
////set icon
//                                .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                .setTitle("Error!")
////set message
//                                .setMessage("Please Enter Others Amount !")
////set positive button
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                //set what would happen when positive button is clicked
//
//                                            }
//                                        }
////set negative button
//
//                                )
//                                .show();
//
//                    }
                    else {
                        if(isNetworkAvailable()) {
                            String checkquery = "SELECT  * FROM invoicelist where flag = 0";
                            Cursor ckcursor = dbs.rawQuery(checkquery, null);
                            if(ckcursor.getCount()!=0)
                            {
                                final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Alert!")
//set message
                                        .setMessage("There is a some data not synced with server please perform Transaction Sync to proceed further!")
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        finish();

                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                            }
                            else
                            {
                                Long tsLong = System.currentTimeMillis() / 1000;
                                String ts = tsLong.toString();
                                String selectQuerys = "SELECT  * FROM invoicelist";
                                Cursor cursors = dbs.rawQuery(selectQuerys, null);
                                int co = cursors.getCount() + 1;
                                inno = ts + "/" + formattedDate + "/INV000" + co;
                                // einwno.setText(inno);
                                if (lcn.equalsIgnoreCase("0")) {
                                    db.insertinvoice(inno, edate.getText().toString(), esuppliern.getText().toString(), elocation.getText().toString(), etax.getText().toString(), trp,oths, einwamt.getText().toString(), sharedpreferences.getString("ids2", ""), sharedpreferences.getString("itemlist2", ""), sharedpreferences.getString("ocnew",""), "0", code, "0", "", supplier.getText().toString(), mcode);

                                    db.invoice(inno, einwamt.getText().toString(), einwamt.getText().toString(), "", "", "", esuppliern.getText().toString(), "1");
                                    if (mm.equalsIgnoreCase("1")) {
                                        //db.insertcustomernm("0", esuppliern.getText().toString(), "", supplier.getText().toString(), spinnerl.getSelectedItem().toString(), "", code, "NM", supplier.getText().toString(),sharedpreferences.getString("ocnew",""));
                                    }
                                    //   Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
                                    SQLiteDatabase dbs = db.getWritableDatabase();
                                    // dbs.execSQL("UPDATE productlist2 SET flag = 1 WHERE flag = 0");
                                    String selectQuery2 = "SELECT  * FROM invoicelist where flag = 0";
                                    Cursor cursor = dbs.rawQuery(selectQuery2, null);
                                    ctt = cursor.getCount();
                                    if (cursor.moveToFirst()) {
                                        do {
                                            id2 = cursor.getString(0);
                                            sname2 = cursor.getString(3);
                                            slcn2 = cursor.getString(4);
                                            sst2 = cursor.getString(4);
                                            net2 = cursor.getString(8);
                                            tax2 = cursor.getString(5);
                                            trs2 = cursor.getString(6);
                                            oth2 = cursor.getString(7);
                                            ids22 = cursor.getString(9);
                                            phn = cursor.getString(16);
                                            lc2 = cursor.getString(13);

                                            ids222 = ids22.substring(1, ids22.length() - 1);
                                            // Toast.makeText(Dashboard.this, ""+ids22, Toast.LENGTH_SHORT).show();
                                            Cursor cursori = dbs.query("invoice", new String[]{"balance",
                                                    }, "invoiceno" + "=?",
                                                    new String[]{cursor.getString(1)}, null, null, null, null);
                                            if (cursori.moveToFirst()) {
                                                do {
                                                    // idn = cursor2.getString(2);
                                                    //Toast.makeText(Bookinvoicenew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

                                                    balamt = cursori.getString(0);


                                                }
                                                while (cursori.moveToNext());


                                            }

                                            Cursor cursorfc = dbs.query("customerlist", new String[]{"farmer_code"
                                                    }, "phone" + "=?" + " and " + "farmer_name" + "=? COLLATE NOCASE",
                                                    new String[]{phn, sname2}, null, null, null, null);

                                            if (cursorfc.moveToFirst()) {
                                                do {

                                                    farco = cursorfc.getString(0);
                                                }
                                                while (cursorfc.moveToNext());


                                            }
                                            //Toast.makeText(Dashboard.this, ""+ids, Toast.LENGTH_SHORT).show();

                                            save2();


                                        } while (cursor.moveToNext());
                                    }
                                } else {

                                    if (spinnerl.getSelectedItem().toString().equalsIgnoreCase("SELECT")) {
                                        // Toast.makeText(Bookinvoicenew.this, "Please Select Location", Toast.LENGTH_SHORT).show();
                                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                                .setTitle("Error!")
//set message
                                                .setMessage("Please Select Location !")
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
                                        db.insertinvoice(inno, edate.getText().toString(), esuppliern.getText().toString(), spinnerl.getSelectedItem().toString(), etax.getText().toString(), trp,oths, einwamt.getText().toString(), sharedpreferences.getString("ids2", ""), sharedpreferences.getString("itemlist2", ""), sharedpreferences.getString("ocnew",""), "0", code, "0", "", supplier.getText().toString(), mcode);
                                        db.invoice(inno, einwamt.getText().toString(), einwamt.getText().toString(), "", "", "", esuppliern.getText().toString(), "1");
                                        if (mm.equalsIgnoreCase("1")) {
                                            //db.insertcustomernm("0", esuppliern.getText().toString(), "", supplier.getText().toString(), spinnerl.getSelectedItem().toString(), "", code, "NM", supplier.getText().toString(),sharedpreferences.getString("ocnew",""));
                                        }
                                        // Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
                                        SQLiteDatabase dbs = db.getWritableDatabase();
                                        // dbs.execSQL("UPDATE productlist2 SET flag = 1 WHERE flag = 0");
                                        String selectQuery2 = "SELECT  * FROM invoicelist where flag = 0";
                                        Cursor cursor = dbs.rawQuery(selectQuery2, null);
                                        ctt = cursor.getCount();
                                        if (cursor.moveToFirst()) {
                                            do {
                                                id2 = cursor.getString(0);
                                                sname2 = cursor.getString(3);
                                                slcn2 = cursor.getString(4);
                                                sst2 = cursor.getString(4);
                                                net2 = cursor.getString(8);
                                                tax2 = cursor.getString(5);
                                                trs2 = cursor.getString(6);
                                                oth2 = cursor.getString(7);
                                                ids22 = cursor.getString(9);
                                                phn = cursor.getString(16);
                                                lc2 = cursor.getString(13);
                                                ids222 = ids22.substring(1, ids22.length() - 1);
                                                // Toast.makeText(Dashboard.this, ""+ids22, Toast.LENGTH_SHORT).show();
                                                Cursor cursori = dbs.query("invoice", new String[]{"balance",
                                                        }, "invoiceno" + "=?",
                                                        new String[]{cursor.getString(1)}, null, null, null, null);
                                                if (cursori.moveToFirst()) {
                                                    do {
                                                        // idn = cursor2.getString(2);
                                                        //Toast.makeText(Bookinvoicenew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

                                                        balamt = cursori.getString(0);


                                                    }
                                                    while (cursori.moveToNext());


                                                }
                                                farco = "0";
                                                //Toast.makeText(Dashboard.this, ""+ids, Toast.LENGTH_SHORT).show();

                                                save2();


                                            } while (cursor.moveToNext());
                                        }
                                    }

                                }
                            }

                        }
                        else
                        {
                            Long tsLong = System.currentTimeMillis()/1000;
                            String ts = tsLong.toString();
                            String selectQuerys = "SELECT  * FROM invoicelist";
                            Cursor cursors = dbs.rawQuery(selectQuerys, null);
                            int co = cursors.getCount() + 1;
                            inno = ts+"/"+formattedDate + "/INV000" + co;

                            if(lcn.equalsIgnoreCase("0")) {
                                // einwno.setText(""+inno);
                                String lindno = sharedpreferences.getString("invoiceno","");
                                String[] lindno2 = lindno.split("/");
                                int xi = Integer.parseInt(lindno2[1])+1;
                                String vv = String.valueOf(xi);
                                if(vv.length() == 1)
                                {
                                    einwno.setText(lindno2[0]+"/0000"+vv);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();

                                    editor.putString("invoiceno", einwno.getText().toString());

                                    editor.commit();
                                    //Toast.makeText(Stockinwardnew.this, lindno2[0]+"/00"+vv, Toast.LENGTH_SHORT).show();
                                    db.insertinvoice(inno, edate.getText().toString(), esuppliern.getText().toString(), elocation.getText().toString(), etax.getText().toString(), trp,oths, einwamt.getText().toString(), sharedpreferences.getString("ids2", ""), sharedpreferences.getString("itemlist2", ""), sharedpreferences.getString("ocnew",""), "0", code, "0",lindno2[0]+"/0000"+vv,supplier.getText().toString(),mcode);
                                    eicd.setEnabled(true);
                                    esl.setEnabled(false);
                                    item.setEnabled(false);
                                }
                                else if(vv.length() == 2)
                                {
                                    einwno.setText(lindno2[0]+"/000"+vv);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();

                                    editor.putString("invoiceno", einwno.getText().toString());

                                    editor.commit();
                                    //Toast.makeText(Stockinwardnew.this, lindno2[0]+"/00"+vv, Toast.LENGTH_SHORT).show();
                                    db.insertinvoice(inno, edate.getText().toString(), esuppliern.getText().toString(), elocation.getText().toString(), etax.getText().toString(), trp,oths, einwamt.getText().toString(), sharedpreferences.getString("ids2", ""), sharedpreferences.getString("itemlist2", ""), sharedpreferences.getString("ocnew",""), "0", code, "0",lindno2[0]+"/000"+vv,supplier.getText().toString(),mcode);
                                    eicd.setEnabled(true);
                                    esl.setEnabled(false);
                                    item.setEnabled(false);
                                }
                                else  if(vv.length() == 3)
                                {
                                    einwno.setText(lindno2[0]+"/00"+vv);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();

                                    editor.putString("invoiceno", einwno.getText().toString());

                                    editor.commit();
                                    //Toast.makeText(Stockinwardnew.this, lindno2[0]+"/00"+vv, Toast.LENGTH_SHORT).show();
                                    db.insertinvoice(inno, edate.getText().toString(), esuppliern.getText().toString(), elocation.getText().toString(), etax.getText().toString(), trp,oths, einwamt.getText().toString(), sharedpreferences.getString("ids2", ""), sharedpreferences.getString("itemlist2", ""), sharedpreferences.getString("ocnew",""), "0", code, "0",lindno2[0]+"/00"+vv,supplier.getText().toString(),mcode);
                                    eicd.setEnabled(true);
                                    esl.setEnabled(false);
                                    item.setEnabled(false);
                                }
                                else if(vv.length() == 4)
                                {
                                    einwno.setText(lindno2[0]+"/0"+vv);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();

                                    editor.putString("invoiceno", einwno.getText().toString());

                                    editor.commit();
                                    // Toast.makeText(Stockinwardnew.this, "0"+vv, Toast.LENGTH_SHORT).show();
                                    db.insertinvoice(inno, edate.getText().toString(), esuppliern.getText().toString(), elocation.getText().toString(), etax.getText().toString(), trp,oths, einwamt.getText().toString(), sharedpreferences.getString("ids2", ""), sharedpreferences.getString("itemlist2", ""), sharedpreferences.getString("ocnew",""), "0", code, "0",lindno2[0]+"/0"+vv,supplier.getText().toString(),mcode);
                                    eicd.setEnabled(true);
                                    esl.setEnabled(false);
                                    item.setEnabled(false);
                                }
                                else
                                {
                                    einwno.setText(lindno2[0]+"/"+vv);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();

                                    editor.putString("invoiceno", einwno.getText().toString());

                                    editor.commit();
                                    db.insertinvoice(inno, edate.getText().toString(), esuppliern.getText().toString(), elocation.getText().toString(), etax.getText().toString(), trp,oths, einwamt.getText().toString(), sharedpreferences.getString("ids2", ""), sharedpreferences.getString("itemlist2", ""), sharedpreferences.getString("ocnew",""), "0", code, "0",lindno2[0]+"/"+vv,supplier.getText().toString(),mcode);
                                    eicd.setEnabled(true);
                                    esl.setEnabled(false);
                                    item.setEnabled(false);
                                    // Toast.makeText(Stockinwardnew.this, ""+vv, Toast.LENGTH_SHORT).show();
                                }
                                db.invoice(inno, einwamt.getText().toString(), einwamt.getText().toString(), "", "", "", esuppliern.getText().toString(), "1");
                                // Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
                                if(mm.equalsIgnoreCase("1")) {
                                   // db.insertcustomernm("0", esuppliern.getText().toString(), "", supplier.getText().toString(), spinnerl.getSelectedItem().toString(), "", code, "NM",supplier.getText().toString(),sharedpreferences.getString("ocnew",""));
                                }

                                final SQLiteDatabase dbs = db.getWritableDatabase();
                                dbs.execSQL("UPDATE productlist2 SET flag = 1 WHERE flag = 0");
                                String selectQuery2 = "SELECT  * FROM invoicelist where flag = 0";
                                Cursor cursor = dbs.rawQuery(selectQuery2, null);
                                ctt = cursor.getCount();
                                if (cursor.moveToFirst()) {
                                    do {

                                        ids22 = cursor.getString(9);

                                        ids222 = ids22.substring(1, ids22.length() - 1);
                                        // Toast.makeText(Dashboard.this, ""+ids22, Toast.LENGTH_SHORT).show();




                                    } while (cursor.moveToNext());
                                }
                                String[] id = ids222.split(",");


                                for(int i = 0 ; i< id.length;i++) {

                                    String selectQuery = "SELECT  * FROM productlist2 where id =" + id[i];
                                    Cursor cursor2 = dbs.rawQuery(selectQuery, null);
                                    // Toast.makeText(this, ""+id[0]+"//"+cursor2.getCount(), Toast.LENGTH_SHORT).show();
                                    if (cursor2.moveToFirst()) {
                                        do {

                                            pn2 = cursor2.getString(1);
                                            pq2 = cursor2.getString(2);


                                            Cursor cursor3 = dbs.query("product", new String[]{"In_product_code",
                                                            "In_productcategory_code", "In_productsubcategory_code", "In_hsn_code", "In_cgst", "In_sgst", "In_base_price", "In_current_qty", "id"}, "In_product_name" + "=?",
                                                    new String[]{pn2}, null, null, null, null);

                                            if (cursor3.moveToFirst()) {
                                                do {
                                                    String ii = cursor3.getString(8);
                                                    newcq = cursor3.getString(7);
                                                    double yy = Double.parseDouble(pq2);
                                                    double xx = Double.parseDouble(newcq);
                                                    double zz = xx - yy;

                                                    dbs.execSQL("UPDATE product SET In_current_qty = " + zz + " WHERE id = " + ii);
                                                }

                                                while (cursor3.moveToNext());
                                            }
                                        }

                                        while (cursor2.moveToNext());
                                    }
                                }

                                // finish();
                                final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                        .setIcon(android.R.drawable.ic_menu_save)
//set title
                                        .setTitle("Success!")
//set message
                                        .setMessage("Invoice Saved")
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        //  Intent in = new Intent(getApplicationContext(), Dashboard.class);
                                                        //startActivity(in);
                                                        amount.setText(einwamt.getText().toString());
                                                        balance.setText(einwamt.getText().toString());
                                                        paid.setText("0");
                                                        invoiceno.setText(einwno.getText().toString());
                                                        itext = inno;
                                                        innob = inno;
                                                        cname = esuppliern.getText().toString();
                                                        rpayment.setEnabled(true);
                                                        Cursor cursor2 = dbs.query("invoicelist", new String[]{"sid","id"
                                                                }, "invoiceno" + "=?",
                                                                new String[]{inno}, null, null, null, null);

                                                        if (cursor2.moveToFirst()) {
                                                            do {
                                                                pyid = cursor2.getString(1);
                                                                Log.e("PYID",""+pyid);
                                                            } while (cursor2.moveToNext());
                                                        }
                                                        seten();
                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                            }
                            else {
                                // einwno.setText(""+inno);
                                if (spinnerl.getSelectedItem().toString().equalsIgnoreCase("SELECT")) {
                                    Toast.makeText(Bookinvoicenew.this, "Please Select Location", Toast.LENGTH_SHORT).show();
                                } else {

                                    String lindno = sharedpreferences.getString("invoiceno","");
                                    String[] lindno2 = lindno.split("/");
                                    int xi = Integer.parseInt(lindno2[1])+1;
                                    String vv = String.valueOf(xi);
                                    if(vv.length() == 1)
                                    {
                                        einwno.setText(lindno2[0]+"/0000"+vv);
                                        SharedPreferences.Editor editor = sharedpreferences.edit();

                                        editor.putString("invoiceno", einwno.getText().toString());

                                        editor.commit();
                                        //Toast.makeText(Stockinwardnew.this, lindno2[0]+"/00"+vv, Toast.LENGTH_SHORT).show();
                                        db.insertinvoice(inno, edate.getText().toString(), esuppliern.getText().toString(), spinnerl.getSelectedItem().toString(), etax.getText().toString(), trp,oths, einwamt.getText().toString(), sharedpreferences.getString("ids2", ""), sharedpreferences.getString("itemlist2", ""), sharedpreferences.getString("ocnew",""), "0", code, "0",lindno2[0]+"/0000"+vv,supplier.getText().toString(),mcode);
                                        eicd.setEnabled(true);
                                        esl.setEnabled(false);
                                        item.setEnabled(false);
                                    }
                                    else if(vv.length() == 2)
                                    {
                                        einwno.setText(lindno2[0]+"/000"+vv);
                                        SharedPreferences.Editor editor = sharedpreferences.edit();

                                        editor.putString("invoiceno", einwno.getText().toString());

                                        editor.commit();
                                        //Toast.makeText(Stockinwardnew.this, lindno2[0]+"/00"+vv, Toast.LENGTH_SHORT).show();
                                        db.insertinvoice(inno, edate.getText().toString(), esuppliern.getText().toString(), spinnerl.getSelectedItem().toString(), etax.getText().toString(), trp,oths, einwamt.getText().toString(), sharedpreferences.getString("ids2", ""), sharedpreferences.getString("itemlist2", ""), sharedpreferences.getString("ocnew",""), "0", code, "0",lindno2[0]+"/000"+vv,supplier.getText().toString(),mcode);
                                        eicd.setEnabled(true);
                                        esl.setEnabled(false);
                                        item.setEnabled(false);
                                    }
                                    else  if(vv.length() == 3)
                                    {
                                        einwno.setText(lindno2[0]+"/00"+vv);
                                        SharedPreferences.Editor editor = sharedpreferences.edit();

                                        editor.putString("invoiceno", einwno.getText().toString());

                                        editor.commit();
                                        //Toast.makeText(Stockinwardnew.this, lindno2[0]+"/00"+vv, Toast.LENGTH_SHORT).show();
                                        db.insertinvoice(inno, edate.getText().toString(), esuppliern.getText().toString(), spinnerl.getSelectedItem().toString(), etax.getText().toString(), trp,oths, einwamt.getText().toString(), sharedpreferences.getString("ids2", ""), sharedpreferences.getString("itemlist2", ""), sharedpreferences.getString("ocnew",""), "0", code, "0",lindno2[0]+"/00"+vv,supplier.getText().toString(),mcode);
                                        eicd.setEnabled(true);
                                        esl.setEnabled(false);
                                        item.setEnabled(false);
                                    }
                                    else if(vv.length() == 4)
                                    {
                                        einwno.setText(lindno2[0]+"/0"+vv);
                                        SharedPreferences.Editor editor = sharedpreferences.edit();

                                        editor.putString("invoiceno", einwno.getText().toString());

                                        editor.commit();
                                        // Toast.makeText(Stockinwardnew.this, "0"+vv, Toast.LENGTH_SHORT).show();
                                        db.insertinvoice(inno, edate.getText().toString(), esuppliern.getText().toString(), spinnerl.getSelectedItem().toString(), etax.getText().toString(), trp,oths, einwamt.getText().toString(), sharedpreferences.getString("ids2", ""), sharedpreferences.getString("itemlist2", ""), sharedpreferences.getString("ocnew",""), "0", code, "0",lindno2[0]+"/0"+vv,supplier.getText().toString(),mcode);
                                        eicd.setEnabled(true);
                                        esl.setEnabled(false);
                                        item.setEnabled(false);
                                    }
                                    else
                                    {
                                        einwno.setText(lindno2[0]+"/"+vv);
                                        SharedPreferences.Editor editor = sharedpreferences.edit();

                                        editor.putString("invoiceno", einwno.getText().toString());

                                        editor.commit();
                                        db.insertinvoice(inno, edate.getText().toString(), esuppliern.getText().toString(), spinnerl.getSelectedItem().toString(), etax.getText().toString(), trp,oths, einwamt.getText().toString(), sharedpreferences.getString("ids2", ""), sharedpreferences.getString("itemlist2", ""), sharedpreferences.getString("ocnew",""), "0", code, "0",lindno2[0]+"/"+vv,supplier.getText().toString(),mcode);
                                        eicd.setEnabled(true);
                                        esl.setEnabled(false);
                                        item.setEnabled(false);
                                        // Toast.makeText(Stockinwardnew.this, ""+vv, Toast.LENGTH_SHORT).show();
                                    }


                                    db.invoice(inno, einwamt.getText().toString(), einwamt.getText().toString(), "", "", "", esuppliern.getText().toString(), "1");
                                    // Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();

                                    if(mm.equalsIgnoreCase("1")) {
                                       // db.insertcustomernm("0", esuppliern.getText().toString(), "", supplier.getText().toString(), spinnerl.getSelectedItem().toString(), "", code, "NM",supplier.getText().toString(),sharedpreferences.getString("ocnew",""));
                                    }
                                    final SQLiteDatabase dbs = db.getWritableDatabase();
                                    // dbs.execSQL("UPDATE productlist2 SET flag = 1 WHERE flag = 0");

                                    //  SQLiteDatabase dbs = db.getWritableDatabase();
                                    dbs.execSQL("UPDATE productlist2 SET flag = 1 WHERE flag = 0");
                                    String selectQuery2 = "SELECT  * FROM invoicelist where flag = 0";
                                    Cursor cursor = dbs.rawQuery(selectQuery2, null);
                                    ctt = cursor.getCount();
                                    if (cursor.moveToFirst()) {
                                        do {

                                            ids22 = cursor.getString(9);

                                            ids222 = ids22.substring(1, ids22.length() - 1);
                                            // Toast.makeText(Dashboard.this, ""+ids22, Toast.LENGTH_SHORT).show();




                                        } while (cursor.moveToNext());
                                    }
                                    String[] id = ids222.split(",");


                                    for(int i = 0 ; i< id.length;i++) {

                                        String selectQuery = "SELECT  * FROM productlist2 where id =" + id[i];
                                        Cursor cursor2 = dbs.rawQuery(selectQuery, null);
                                        // Toast.makeText(this, ""+id[0]+"//"+cursor2.getCount(), Toast.LENGTH_SHORT).show();
                                        if (cursor2.moveToFirst()) {
                                            do {

                                                pn2 = cursor2.getString(1);
                                                pq2 = cursor2.getString(2);


                                                Cursor cursor3 = dbs.query("product", new String[]{"In_product_code",
                                                                "In_productcategory_code", "In_productsubcategory_code", "In_hsn_code", "In_cgst", "In_sgst", "In_base_price", "In_current_qty", "id"}, "In_product_name" + "=?",
                                                        new String[]{pn2}, null, null, null, null);

                                                if (cursor3.moveToFirst()) {
                                                    do {
                                                        String ii = cursor3.getString(8);
                                                        newcq = cursor3.getString(7);
                                                        double yy = Double.parseDouble(pq2);
                                                        double xx = Double.parseDouble(newcq);
                                                        double zz = xx - yy;

                                                        dbs.execSQL("UPDATE product SET In_current_qty = " + zz + " WHERE id = " + ii);
                                                    }

                                                    while (cursor3.moveToNext());
                                                }
                                            }

                                            while (cursor2.moveToNext());
                                        }
                                    }

                                    // finish();
                                    final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                                            .setTitle("Success!")
//set message
                                            .setMessage("Invoice Saved")
//set positive button
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            //set what would happen when positive button is clicked
                                                            //  Intent in = new Intent(getApplicationContext(), Dashboard.class);
                                                            //startActivity(in);
                                                            amount.setText(einwamt.getText().toString());
                                                            balance.setText(einwamt.getText().toString());
                                                            paid.setText("0");
                                                            invoiceno.setText(einwno.getText().toString());
                                                            itext = inno;
                                                            innob = inno;
                                                            cname = esuppliern.getText().toString();
                                                            rpayment.setEnabled(true);
                                                            Cursor cursor2 = dbs.query("invoicelist", new String[]{"sid","id"
                                                                    }, "invoiceno" + "=?",
                                                                    new String[]{inno}, null, null, null, null);

                                                            if (cursor2.moveToFirst()) {
                                                                do {
                                                                    pyid = cursor2.getString(1);
                                                                    Log.e("PYID",""+pyid);

                                                                } while (cursor2.moveToNext());
                                                            }
                                                            rpayment.setEnabled(true);
                                                            seten();
                                                        }
                                                    }
//set negative button

                                            )
                                            .show();


                                }
                            }
                        }

                    }
                }
                else if(button == 1 && btnn.equalsIgnoreCase("1"))
                {
                    if(spinner.getSelectedItem().toString().equalsIgnoreCase("Select Payment Mode"))
                    {
                        // Toast.makeText(Bookinvoicenew.this, "Select Paymode", Toast.LENGTH_SHORT).show();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Select Paymode")
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
                    else if(eamtpaid.getText().toString().equalsIgnoreCase(""))
                    {
                        // Toast.makeText(Bookinvoicenew.this, "Enter Amount Paid", Toast.LENGTH_SHORT).show();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Enter Amount Paid")
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
                    else if(erefno.getText().toString().equalsIgnoreCase(""))
                    {
                        //Toast.makeText(Bookinvoicenew.this, "Enter Reference No", Toast.LENGTH_SHORT).show();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Enter Reference No")
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
                    else if (epdate.getText().toString().equalsIgnoreCase(""))
                    {
                        //Toast.makeText(Bookinvoicenew.this, "Select Date", Toast.LENGTH_SHORT).show();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Select Date")
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
                    else if(ck==1&&chno.getText().toString().equalsIgnoreCase(""))
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Enter Cheque No")
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
                    else {

                        if(paymodecode.equalsIgnoreCase("QCD_AEPS_PAYTM")) {

                           getorderID();

//                            final Handler handler = new Handler(Looper.getMainLooper());
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    //Do something after 10 sec
//
//                                }
//                            }, 10000);
                        }
                        else if(paymodecode.equalsIgnoreCase("QCD_AEPS_AEPS"))
                        {

                            getorderid_aeps();
                           /* Intent intent   = new Intent(Bookinvoicenew.this, Host.class);
                            intent.putExtra("username","R005155");
                            startActivityForResult(intent,1000);*/


                        }
                        else {
                            callpayment();
                        }
                    }


                }

            }
        });

        // looping through all rows and adding to list
        String selectQuery2 = "SELECT  * FROM productlist2 where flag = 0";
        final Cursor cursor2 = dbs.rawQuery(selectQuery2, null);
        if(cursor2.getCount() == 0)
        {
            ecount.setText("");
        }
        else
        {
            counttotal();
        }

        String selectQuerys = "SELECT  * FROM customerlist";
        Cursor cursors = dbs.rawQuery(selectQuerys, null);
        if (cursors.moveToFirst()) {
            do {

                array3.add(cursors.getString(9));
                // Log.e("VAL",""+cursor.getString(11));

            } while (cursors.moveToNext());
            String selectQuerys2 = "SELECT  * FROM customerlistnm";
            Cursor cursors2 = dbs.rawQuery(selectQuerys2, null);
            if(cursors2.getCount()!=0) {
                if (cursors2.moveToFirst()) {
                    do {

                        array3.add(cursors2.getString(9));
                        // Log.e("VAL",""+cursor.getString(11));

                    } while (cursors2.moveToNext());
                }
            }
        }

        ArrayAdapter<String> adapterlist2 = new ArrayAdapter<String>(Bookinvoicenew.this,
                R.layout.spinnertext, array3);
        // supplier.setAdapter(adapterlist2);
        list.setVisibility(View.GONE);

        if(isNetworkAvailable())
        {
            String checkquery = "SELECT  * FROM invoicelist where flag = 0";
            Cursor ckcursor = dbs.rawQuery(checkquery, null);
            if(ckcursor.getCount()!=0)
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("There is a some data not synced with server please perform Transaction Sync to proceed further!")
//set positive button
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //set what would happen when positive button is clicked
                                        finish();

                                    }
                                }
//set negative button

                        )
                        .show();
            }
            else {


            }
        }
        else
        {

        }
        supplier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

                suppliername = (String) parent.getItemAtPosition(position);
                suppliername2="";
                // elocation.setVisibility(View.VISIBLE);
                // spinnerl.setVisibility(View.GONE);

                counttotal();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                paymodecode = categoriescode.get(position);
                Log.e("PAYCODE",""+paymodecode);

                if(paymodecode.equalsIgnoreCase("QCD_AEPS_PAYTM"))
                {
                    lsave.setText("Pay Now");
                    lsave.setEnabled(false);
                    lsave.setBackgroundResource(R.drawable.rbutton);

                    checkconnection();

                    erefno.setEnabled(false);
                    erefno.setText("**Auto Generate**");
                   // getorderID();
                 sconnecton.setVisibility(View.VISIBLE);


                 Cursor cinvoice = dbs.rawQuery("select * from invoicelist where inweb = '"+invoiceno.getText().toString()+"'",null);
                 if(cinvoice.getCount()>0)
                 {
                     if(cinvoice.moveToFirst())
                     {
                         String ids = cinvoice.getString(9);
                         Log.e("INVOICE IDS",""+ids);
                         String ids2 = ids.substring(1, ids.length() - 1);
                         String[] finalids = ids2.split(",");
                          stringBuilder = new StringBuilder();

                         for(int i = 0 ; i< finalids.length;i++)
                         {
                             Cursor cproduct = dbs.rawQuery("select * from productlist2 where id ='" + finalids[i]+"'",null);
                             if(cproduct.getCount()>0)
                             {
                                 if(cproduct.moveToFirst()) {
                                     do {
                                         String cgstp="",sgstp="",cgsta="",sgsta="",tamt="",toamt="",namt="";

                                         namt = cproduct.getString(6);
                                         Cursor cursor2 = dbs.query("product", new String[]{"In_cgst",
                                                         "In_sgst", "In_igst", "In_hsn_code"}, "In_product_name" + "=?",
                                                 new String[]{ cproduct.getString(1)}, null, null, null, null);
                                         if (cursor2.moveToFirst()) {
                                            // do {

                                                cgstp =  cursor2.getString(0);
                                                sgstp = cursor2.getString(1);
                                                float fcgst = Float.parseFloat(cgstp);
                                                float fsgst = Float.parseFloat(sgstp);
                                             double c2 = Double.parseDouble(String.valueOf(fcgst));
                                             double ca = Double.parseDouble(namt)/100;
                                             double ca2 = c2*ca;
                                             cgsta = ""+String.format("%.2f", ca2);
                                             double s2 = Double.parseDouble(String.valueOf(fsgst));
                                             double sa = Double.parseDouble(namt)/100;
                                             double sa2 = s2*sa;
                                             sgsta = ""+String.format("%.2f", sa2);

                                             double c = Double.parseDouble(cgsta);
                                             double s = Double.parseDouble(sgsta);
                                             double rl = Double.parseDouble(namt);

                                             double cg = (c/100) * rl;
                                             double sg = (s/100) * rl;

                                             double ff = c+s;
                                             double f = Double.parseDouble(namt)+ff;
                                             DecimalFormat amountFormate  = new DecimalFormat("############.##");
                                             amountFormate.setMinimumFractionDigits(2);
                                             amountFormate.setMaximumFractionDigits(2);

                                             tamt = ""+String.format("%.2f", ff);
                                             toamt = ""+String.format("%.2f", rl);



//                                             }
//                                             while (cursor2.moveToNext());
                                         }


                                         stringBuilder.append("ProductName=" + cproduct.getString(1) + "&Qty=" + cproduct.getString(2) + "&Rate=" + cproduct.getString(3) + "&Discount=" + cproduct.getString(5) + "&NetAmount=" + cproduct.getString(6)+"&CGST="+cgstp+"&SGST="+sgstp+"&CGSTAMT="+cgsta+"&SGSTAMT="+sgsta+"&TaxableAmount="+tamt+"&TotalAmount="+toamt+"&");
                                     }while (cproduct.moveToNext());
                                 }


                             }
                         }
                     }
                     Log.e("PRINTINFO",""+stringBuilder.toString());
                 }

                }
                else if(paymodecode.equalsIgnoreCase("QCD_AEPS_AEPS"))
                {
                    erefno.setEnabled(false);
                    erefno.setText("**Auto Generate**");
                    sconnecton.setVisibility(View.GONE);
                    lsave.setText("Pay Now");
                    lsave.setBackgroundResource(R.drawable.rbutton3);
                }
                else
                {
                    lsave.setText("Submit");
                    sconnecton.setVisibility(View.GONE);
                    lsave.setEnabled(true);
                    erefno.setEnabled(true);
                    erefno.setText("");
                }
//                if(position == 1)
//                {
//
//                    lch.setVisibility(View.GONE);
//                    ck=0;
//
//                }
//                else if(position == 2)
//                {
//                    lch.setVisibility(View.VISIBLE);
//                    ck=1;
//
//                }
                // your code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
//        supplier.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                dialog = new Dialog(Bookinvoicenew.this);
//                dialog.setContentView(R.layout.suppliersearch);
//                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
//                dialog.setTitle("Title...");
//                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
//                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
//                final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
//                recyclerView.setLayoutManager(new LinearLayoutManager(Bookinvoicenew.this));
//                adapterd = new MyRecyclerViewAdapterd(Bookinvoicenew.this, dialoglist);
//                dialog.getWindow().setLayout(width, height);
//
//                esupplier = (AutoCompleteTextView)dialog.findViewById(R.id.esupplier);
//                esupplier2 = (AutoCompleteTextView)dialog.findViewById(R.id.esupplier2);
//                esupplier2.setVisibility(View.GONE);
//                esupplier.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                        if (charSequence.toString().equalsIgnoreCase("")) {
//                            dialoglist.clear();
//                            recyclerView.setAdapter(adapterd);
//                        } else {
//                            try {
//
//
//                            } catch (Exception e) {
//                                //   Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();
//
//                            }
//
//
//                        }
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                        if (charSequence.toString().equalsIgnoreCase("")) {
//                            dialoglist.clear();
//                            recyclerView.setAdapter(adapterd);
//                        } else {
//                            try {
//
//
//                            } catch (Exception e) {
//                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable editable) {
//                        //after the change calling the method and passing the search input
//                        if (editable.toString().equalsIgnoreCase("")) {
//                            dialoglist.clear();
//                            recyclerView.setAdapter(adapterd);
//                        } else {
//                            try {
//
//
//                            } catch (Exception e) {
//                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    }
//                });
//
//                esupplier2.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                        if (charSequence.toString().equalsIgnoreCase("")) {
//                            dialoglist.clear();
//                            recyclerView.setAdapter(adapterd);
//                        } else {
//                            try {
//
//
//                            } catch (Exception e) {
//                                //   Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();
//
//                            }
//
//
//                        }
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                        if (charSequence.toString().equalsIgnoreCase("")) {
//                            dialoglist.clear();
//                            recyclerView.setAdapter(adapterd);
//                        } else {
//                            try {
//
//
//                            } catch (Exception e) {
//                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable editable) {
//                        //after the change calling the method and passing the search input
//                        if (editable.toString().equalsIgnoreCase("")) {
//                            dialoglist.clear();
//                            recyclerView.setAdapter(adapterd);
//                        } else {
//                            try {
//
//
//                            } catch (Exception e) {
//                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    }
//                });
//                Button select = (Button)dialog.findViewById(R.id.select);
//                String selectQuerys = "SELECT  * FROM customerlist";
//                Cursor cursors = dbs.rawQuery(selectQuerys, null);
//                if (cursors.moveToFirst()) {
//                    do {
//
//                        if(arrayn.contains(cursors.getString(9)))
//                        {
//
//                        }
//                        else
//                        {
//                            arrayn.add(cursors.getString(9));
//                        }
//                        // Log.e("VAL",""+cursor.getString(11));
//
//                    } while (cursors.moveToNext());
//                    String selectQuerys2 = "SELECT  * FROM customerlistnm";
//                    Cursor cursors2 = dbs.rawQuery(selectQuerys2, null);
//                    if(cursors2.getCount()!=0) {
//                        if (cursors2.moveToFirst()) {
//                            do {
//
//                                if(arrayn.contains(cursors2.getString(9)))
//                                {
//
//                                }
//                                else
//                                {
//                                    arrayn.add(cursors2.getString(9));
//                                }
//                                // Log.e("VAL",""+cursor.getString(11));
//
//                            } while (cursors2.moveToNext());
//                        }
//                    }
//                }
//
//
//
//                ArrayAdapter<String> adapterlist2 = new ArrayAdapter<String>(Bookinvoicenew.this,
//                        R.layout.spinnertext, arrayn);
//                esupplier.setAdapter(adapterlist2);
//
//
//                // set the custom dialog components - text, image and button
//                esupplier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
//
//
//                        dialoglist.clear();
//                        esupplier2.setText("");
//
//                        Cursor cursor = dbs.query("customerlist", new String[]{"farmer_code","farmer_name",
//                                        "state_name","farmer_state_code","mor","phone","id"}, "phone" + "=?",
//                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
//                        if(cursor.getCount()!=0)
//                        {
//                            if (cursor.moveToFirst()) {
//                                do {
//
//                                    Dialogpojo dialogpojo = new Dialogpojo();
//
//                                    dialogpojo.setId(cursor.getString(6));
//                                    dialogpojo.setFcode(cursor.getString(0));
//                                    dialogpojo.setName(cursor.getString(1));
//                                    dialogpojo.setLcn(cursor.getString(2));
//                                    dialogpojo.setCode(cursor.getString(3));
//                                    dialogpojo.setMem(cursor.getString(4));
//                                    dialogpojo.setPh(cursor.getString(5));
//                                    dialogpojo.setType("Member");
//
//
//                                    dialoglist.add(dialogpojo);
//                                    // array2.add(cursor.getString(11));
//                                    // Log.e("VAL",""+cursor.getString(11));
//                                    recyclerView.setAdapter(adapterd);
//                                    // Log.e("VAL",""+cursor.getString(11));
//
//                                } while (cursor.moveToNext());
//                            }
//
//
//
//                        }
//                        Cursor cursors2n = dbs.query("customerlistnm", new String[]{"farmer_code","farmer_name",
//                                        "state_name","farmer_state_code","mor","phone","id"}, "phone" + "=?",
//                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
//
//                        if(cursors2n.getCount()!=0) {
//                            if (cursors2n.moveToFirst()) {
//                                do {
//
//                                    Dialogpojo dialogpojo = new Dialogpojo();
//
//                                    dialogpojo.setId(cursors2n.getString(6));
//
//                                    dialogpojo.setFcode(cursors2n.getString(0));
//                                    dialogpojo.setName(cursors2n.getString(1));
//                                    dialogpojo.setLcn(cursors2n.getString(2));
//                                    dialogpojo.setCode(cursors2n.getString(3));
//                                    dialogpojo.setMem(cursors2n.getString(4));
//                                    dialogpojo.setPh(cursors2n.getString(5));
//                                    dialogpojo.setType("Non-Member");
//
//
//                                    dialoglist.add(dialogpojo);
//                                    // array2.add(cursor.getString(11));
//                                    // Log.e("VAL",""+cursor.getString(11));
//                                    recyclerView.setAdapter(adapterd);
//                                    // Log.e("VAL",""+cursor.getString(11));
//
//                                } while (cursors2n.moveToNext());
//                            }
//                        }
//
//
//                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(esupplier.getWindowToken(), 0);
//                    }
//                });
//
//
//                String selectQuerys2 = "SELECT  * FROM customerlist";
//                Cursor cursors2 = dbs.rawQuery(selectQuerys2, null);
//                if (cursors2.moveToFirst()) {
//                    do {
//
//                        if(arrayn2.contains(cursors2.getString(2)))
//                        {
//
//                        }
//                        else
//                        {
//                            arrayn2.add(cursors2.getString(2));
//                        }
//                        // Log.e("VAL",""+cursor.getString(11));
//
//                    } while (cursors2.moveToNext());
//                    String selectQuerys2n = "SELECT  * FROM customerlistnm";
//                    Cursor cursors2n = dbs.rawQuery(selectQuerys2n, null);
//                    if(cursors2n.getCount()!=0) {
//                        if (cursors2n.moveToFirst()) {
//                            do {
//
//                                if(arrayn2.contains(cursors2n.getString(2)))
//                                {
//
//                                }
//                                else
//                                {
//                                    arrayn2.add(cursors2n.getString(2));
//                                }
//                                // Log.e("VAL",""+cursor.getString(11));
//
//                            } while (cursors2n.moveToNext());
//                        }
//                    }
//
//                }
//                ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(Bookinvoicenew.this,
//                        R.layout.spinnertext, arrayn2);
//
//                esupplier2.setAdapter(adapterlist2n);
//                // set up the RecyclerView
//
//                esupplier2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
//
//
//                        dialoglist.clear();
//                        esupplier.setText("");
//                        Cursor cursor = dbs.query("customerlist", new String[]{"farmer_code","farmer_name",
//                                        "state_name","farmer_state_code","mor","phone","id"}, "farmer_name" + "=? COLLATE NOCASE",
//                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
//                        if(cursor.getCount()!=0)
//                        {
//                            if (cursor.moveToFirst()) {
//                                do {
//
//                                    Dialogpojo dialogpojo = new Dialogpojo();
//
//                                    dialogpojo.setId(cursor.getString(6));
//                                    dialogpojo.setFcode(cursor.getString(0));
//                                    dialogpojo.setName(cursor.getString(1));
//                                    dialogpojo.setLcn(cursor.getString(2));
//                                    dialogpojo.setCode(cursor.getString(3));
//                                    dialogpojo.setMem(cursor.getString(4));
//                                    dialogpojo.setPh(cursor.getString(5));
//                                    dialogpojo.setType("Member");
//
//                                    dialoglist.add(dialogpojo);
//                                    // array2.add(cursor.getString(11));
//                                    // Log.e("VAL",""+cursor.getString(11));
//                                    recyclerView.setAdapter(adapterd);
//                                    // Log.e("VAL",""+cursor.getString(11));
//
//                                } while (cursor.moveToNext());
//                            }
//
//
//                        }
//                        Cursor cursors2n = dbs.query("customerlistnm", new String[]{"farmer_code","farmer_name",
//                                        "state_name","farmer_state_code","mor","phone","id"}, "farmer_name" + "=? COLLATE NOCASE",
//                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
//                        if(cursors2n.getCount()!=0) {
//                            if (cursors2n.moveToFirst()) {
//                                do {
//
//                                    Dialogpojo dialogpojo = new Dialogpojo();
//
//                                    dialogpojo.setId(cursors2n.getString(6));
//                                    dialogpojo.setFcode(cursors2n.getString(0));
//                                    dialogpojo.setName(cursors2n.getString(1));
//                                    dialogpojo.setLcn(cursors2n.getString(2));
//                                    dialogpojo.setCode(cursors2n.getString(3));
//                                    dialogpojo.setMem(cursors2n.getString(4));
//                                    dialogpojo.setPh(cursors2n.getString(5));
//                                    dialogpojo.setType("Non-Member");
//
//                                    dialoglist.add(dialogpojo);
//                                    // array2.add(cursor.getString(11));
//                                    // Log.e("VAL",""+cursor.getString(11));
//                                    recyclerView.setAdapter(adapterd);
//                                    // Log.e("VAL",""+cursor.getString(11));
//
//                                } while (cursors2n.moveToNext());
//                            }
//                        }
//                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(esupplier.getWindowToken(), 0);
//                    }
//                });
//
//                select.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        if(esupplier.getText().toString().equalsIgnoreCase(""))
////                        {
////                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//////set icon
////                                    .setIcon(android.R.drawable.ic_dialog_alert)
//////set title
////                                    .setTitle("Error!")
//////set message
////                                    .setMessage("Empty Customer Mobile Number")
//////set positive button
////                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
////                                                @Override
////                                                public void onClick(DialogInterface dialogInterface, int i) {
////                                                    //set what would happen when positive button is clicked
////
////                                                }
////                                            }
//////set negative button
////
////                                    )
////                                    .show();
////                        }
////                        else if(esupplier2.getText().toString().equalsIgnoreCase(""))
////                        {
////                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//////set icon
////                                    .setIcon(android.R.drawable.ic_dialog_alert)
//////set title
////                                    .setTitle("Error!")
//////set message
////                                    .setMessage("Empty Customer Name")
//////set positive button
////                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
////                                                @Override
////                                                public void onClick(DialogInterface dialogInterface, int i) {
////                                                    //set what would happen when positive button is clicked
////
////                                                }
////                                            }
//////set negative button
////
////                                    )
////                                    .show();
////                        }
////                        else
////                        {
////                            suppliername = esupplier.getText().toString()
////                            ;
////                            sun = esupplier2.getText().toString();
////                            suppliername2="";
////                            esupplier.setText(suppliername);
////                            dialog.dismiss();
////                            supplier.setText(suppliername);
////                            // elocation.setVisibility(View.VISIBLE);
////                            // spinnerl.setVisibility(View.GONE);
////                            lsup.setVisibility(View.VISIBLE);
////                            counttotal();
////                        }
//
//                        supplier.setFocusable(true);
//                        esuppliern.setFocusable(true);
//                        dialog.dismiss();
//                    }
//                });
//
//                ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
//                // if button is clicked, close the custom dialog
//                dialogButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
////                        finish();
////                        startActivity(getIntent());
//                    }
//                });
//
//                dialog.show();
//            }
//        });


        msearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(Bookinvoicenew.this);
                dialog.setContentView(R.layout.suppliersearch);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(Bookinvoicenew.this));
                adapterd = new MyRecyclerViewAdapterd(Bookinvoicenew.this, dialoglist);
                dialog.getWindow().setLayout(width, height);

                esupplier = (AutoCompleteTextView)dialog.findViewById(R.id.esupplier);
                esupplier2 = (AutoCompleteTextView)dialog.findViewById(R.id.esupplier2);
                TextView dialogtext = (TextView) dialog.findViewById(R.id.dialogtext);
                dialogtext.setVisibility(View.VISIBLE);
                dialogtext.setText("Customer Search by Mobile");
                esupplier.setInputType(InputType.TYPE_CLASS_NUMBER);

                esupplier2.setVisibility(View.GONE);
                esupplier.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().equalsIgnoreCase("")) {
                            dialoglist.clear();
                            recyclerView.setAdapter(adapterd);
                        } else {
                            try {


                            } catch (Exception e) {
                                //   Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                            }


                        }
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().equalsIgnoreCase("")) {
                            dialoglist.clear();
                            recyclerView.setAdapter(adapterd);
                        } else {
                            try {


                            } catch (Exception e) {
                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        //after the change calling the method and passing the search input
                        if (editable.toString().equalsIgnoreCase("")) {
                            dialoglist.clear();
                            recyclerView.setAdapter(adapterd);
                        } else {
                            try {


                            } catch (Exception e) {
                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });

                esupplier2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().equalsIgnoreCase("")) {
                            dialoglist.clear();
                            recyclerView.setAdapter(adapterd);
                        } else {
                            try {


                            } catch (Exception e) {
                                //   Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                            }


                        }
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().equalsIgnoreCase("")) {
                            dialoglist.clear();
                            recyclerView.setAdapter(adapterd);
                        } else {
                            try {


                            } catch (Exception e) {
                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        //after the change calling the method and passing the search input
                        if (editable.toString().equalsIgnoreCase("")) {
                            dialoglist.clear();
                            recyclerView.setAdapter(adapterd);
                        } else {
                            try {


                            } catch (Exception e) {
                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
                Button select = (Button)dialog.findViewById(R.id.select);
                String selectQuerys = "SELECT  * FROM customerlist";
                Cursor cursors = dbs.rawQuery(selectQuerys, null);
                if (cursors.moveToFirst()) {
                    do {

                        if(arrayn.contains(cursors.getString(9)))
                        {

                        }
                        else
                        {
                            arrayn.add(cursors.getString(9));
                        }



                        // Log.e("VAL",""+cursor.getString(11));

                    } while (cursors.moveToNext());
                    String selectQuerys2 = "SELECT  * FROM customerlistnm";
                    Cursor cursors2 = dbs.rawQuery(selectQuerys2, null);
                    if(cursors2.getCount()!=0) {
                        if (cursors2.moveToFirst()) {
                            do {

                                if(arrayn.contains(cursors2.getString(9)))
                                {

                                }
                                else
                                {
                                    arrayn.add(cursors2.getString(9));
                                }
                                // Log.e("VAL",""+cursor.getString(11));

                            } while (cursors2.moveToNext());
                        }
                    }
                }



                ArrayAdapter<String> adapterlist2 = new ArrayAdapter<String>(Bookinvoicenew.this,
                        R.layout.spinnertext, arrayn);
                esupplier.setAdapter(adapterlist2);


                // set the custom dialog components - text, image and button
                esupplier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {


                        dialoglist.clear();
                        esupplier2.setText("");

                        Cursor cursor = dbs.query("customerlist", new String[]{"farmer_code","farmer_name",
                                        "village_name","farmer_state_code","mor","phone","id","surname","fhwname"}, "phone" + "=?",
                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
                        if(cursor.getCount()!=0)
                        {
                            if (cursor.moveToFirst()) {
                                do {

                                    Dialogpojo dialogpojo = new Dialogpojo();

                                    dialogpojo.setId(cursor.getString(6));
                                    dialogpojo.setFcode(cursor.getString(0));
                                    dialogpojo.setName(cursor.getString(1));
                                    dialogpojo.setLcn(cursor.getString(2));
                                    dialogpojo.setCode(cursor.getString(3));
                                    dialogpojo.setMem(cursor.getString(4));
                                    dialogpojo.setPh(cursor.getString(5));
                                    dialogpojo.setSn(cursor.getString(7));
                                    dialogpojo.setFhn(cursor.getString(8));
                                    dialogpojo.setType("Member");


                                    dialoglist.add(dialogpojo);
                                    // array2.add(cursor.getString(11));
                                    // Log.e("VAL",""+cursor.getString(11));
                                    recyclerView.setAdapter(adapterd);
                                    // Log.e("VAL",""+cursor.getString(11));

                                } while (cursor.moveToNext());
                            }



                        }
                        Cursor cursors2n = dbs.query("customerlistnm", new String[]{"farmer_code","farmer_name",
                                        "village_name","farmer_state_code","mor","phone","id","surname","fhwname"}, "phone" + "=?",
                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);

                        if(cursors2n.getCount()!=0) {
                            if (cursors2n.moveToFirst()) {
                                do {

                                    Dialogpojo dialogpojo = new Dialogpojo();

                                    dialogpojo.setId(cursors2n.getString(6));

                                    dialogpojo.setFcode(cursors2n.getString(0));
                                    dialogpojo.setName(cursors2n.getString(1));
                                    dialogpojo.setLcn(cursors2n.getString(2));
                                    dialogpojo.setCode(cursors2n.getString(3));
                                    dialogpojo.setMem(cursors2n.getString(4));
                                    dialogpojo.setPh(cursors2n.getString(5));
                                    dialogpojo.setSn(cursors2n.getString(7));
                                    dialogpojo.setFhn(cursors2n.getString(8));
                                    dialogpojo.setType("Non-Member");


                                    dialoglist.add(dialogpojo);
                                    // array2.add(cursor.getString(11));
                                    // Log.e("VAL",""+cursor.getString(11));
                                    recyclerView.setAdapter(adapterd);
                                    // Log.e("VAL",""+cursor.getString(11));

                                } while (cursors2n.moveToNext());
                            }
                        }

                        flag = "1";

                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(esupplier.getWindowToken(), 0);
                    }
                });


                String selectQuerys2 = "SELECT  * FROM customerlist";
                Cursor cursors2 = dbs.rawQuery(selectQuerys2, null);
                if (cursors2.moveToFirst()) {
                    do {

                        if(arrayn2.contains(cursors2.getString(9)))
                        {

                        }
                        else
                        {
                            arrayn2.add(cursors2.getString(9));
                        }
                        // Log.e("VAL",""+cursor.getString(11));

                    } while (cursors2.moveToNext());
                    String selectQuerys2n = "SELECT  * FROM customerlistnm";
                    Cursor cursors2n = dbs.rawQuery(selectQuerys2n, null);
                    if(cursors2n.getCount()!=0) {
                        if (cursors2n.moveToFirst()) {
                            do {

                                if(arrayn2.contains(cursors2n.getString(9)))
                                {

                                }
                                else
                                {
                                    arrayn2.add(cursors2n.getString(9));
                                }
                                // Log.e("VAL",""+cursor.getString(11));

                            } while (cursors2n.moveToNext());
                        }
                    }

                }
                ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(Bookinvoicenew.this,
                        R.layout.spinnertext, arrayn2);

                esupplier2.setAdapter(adapterlist2n);
                // set up the RecyclerView

                esupplier2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {


                        dialoglist.clear();
                        esupplier.setText("");
                        Cursor cursor = dbs.query("customerlist", new String[]{"farmer_code","farmer_name",
                                        "village_name","farmer_state_code","mor","phone","id","surname","fhwname"}, "farmer_name" + "=? COLLATE NOCASE",
                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
                        if(cursor.getCount()!=0)
                        {
                            if (cursor.moveToFirst()) {
                                do {

                                    Dialogpojo dialogpojo = new Dialogpojo();

                                    dialogpojo.setId(cursor.getString(6));
                                    dialogpojo.setFcode(cursor.getString(0));
                                    dialogpojo.setName(cursor.getString(1));
                                    dialogpojo.setLcn(cursor.getString(2));
                                    dialogpojo.setCode(cursor.getString(3));
                                    dialogpojo.setMem(cursor.getString(4));
                                    dialogpojo.setPh(cursor.getString(5));
                                    dialogpojo.setSn(cursor.getString(7));
                                    dialogpojo.setFhn(cursor.getString(8));
                                    dialogpojo.setType("Member");

                                    dialoglist.add(dialogpojo);
                                    // array2.add(cursor.getString(11));
                                    // Log.e("VAL",""+cursor.getString(11));
                                    recyclerView.setAdapter(adapterd);
                                    // Log.e("VAL",""+cursor.getString(11));

                                } while (cursor.moveToNext());
                            }


                        }
                        Cursor cursors2n = dbs.query("customerlistnm", new String[]{"farmer_code","farmer_name",
                                        "village_name","farmer_state_code","mor","phone","id","surname","fhwname"}, "farmer_name" + "=? COLLATE NOCASE",
                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
                        if(cursors2n.getCount()!=0) {
                            if (cursors2n.moveToFirst()) {
                                do {

                                    Dialogpojo dialogpojo = new Dialogpojo();

                                    dialogpojo.setId(cursors2n.getString(6));
                                    dialogpojo.setFcode(cursors2n.getString(0));
                                    dialogpojo.setName(cursors2n.getString(1));
                                    dialogpojo.setLcn(cursors2n.getString(2));
                                    dialogpojo.setCode(cursors2n.getString(3));
                                    dialogpojo.setMem(cursors2n.getString(4));
                                    dialogpojo.setPh(cursors2n.getString(5));
                                    dialogpojo.setSn(cursor.getString(7));
                                    dialogpojo.setFhn(cursor.getString(8));
                                    dialogpojo.setType("Non-Member");

                                    dialoglist.add(dialogpojo);
                                    // array2.add(cursor.getString(11));
                                    // Log.e("VAL",""+cursor.getString(11));
                                    recyclerView.setAdapter(adapterd);
                                    // Log.e("VAL",""+cursor.getString(11));

                                } while (cursors2n.moveToNext());
                            }
                        }
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(esupplier.getWindowToken(), 0);
                    }
                });

                select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                     spinnerm.setSelection(2);
//                     // esuppliern.setText(cursor2nn.getString(2));
//                     spinnerl.setEnabled(true);
//                     lcn="1";
//                     mcode = "QCD_UOM_NONMEM";
//                     elocation.setText("0");
//                    // elocation.setVisibility(View.GONE);
//                     rlocation.setVisibility(View.GONE);
//                     spinnerl.setVisibility(View.VISIBLE);
//                     spinnerl.setSelection(0);
//                     supplier.setText("");
//                     esuppliern.setText("");
                        dialog.dismiss();
                        Intent i = new Intent(getApplicationContext(), Fheader.class);
                        startActivity(i);
//                        if(esupplier.getText().toString().equalsIgnoreCase(""))
//                        {
//                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
////set icon
//                                    .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                    .setTitle("Error!")
////set message
//                                    .setMessage("Empty Customer Mobile Number")
////set positive button
//                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialogInterface, int i) {
//                                                    //set what would happen when positive button is clicked
//
//                                                }
//                                            }
////set negative button
//
//                                    )
//                                    .show();
//                        }
//                        else if(esupplier2.getText().toString().equalsIgnoreCase(""))
//                        {
//                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
////set icon
//                                    .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                    .setTitle("Error!")
////set message
//                                    .setMessage("Empty Customer Name")
////set positive button
//                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialogInterface, int i) {
//                                                    //set what would happen when positive button is clicked
//
//                                                }
//                                            }
////set negative button
//
//                                    )
//                                    .show();
//                        }
//                        else
//                        {
//                            suppliername = esupplier.getText().toString()
//                            ;
//                            sun = esupplier2.getText().toString();
//                            suppliername2="";
//                            esupplier.setText(suppliername);
//                            dialog.dismiss();
//                            supplier.setText(suppliername);
//                            // elocation.setVisibility(View.VISIBLE);
//                            // spinnerl.setVisibility(View.GONE);
//                            lsup.setVisibility(View.VISIBLE);
//                            counttotal();
//                        }
                    }
                });

                ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
//                        finish();
//                        startActivity(getIntent());
                    }
                });

                dialog.show();
            }
        });

        spinnerm.setSelection(2);
        // esuppliern.setText(cursor2nn.getString(2));
        spinnerl.setEnabled(true);
        lcn="1";
        mcode = "QCD_UOM_NONMEM";
        elocation.setText("0");
        //  elocation.setVisibility(View.GONE);
        rlocation.setVisibility(View.GONE);
        spinnerl.setVisibility(View.VISIBLE);
        csearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(Bookinvoicenew.this);
                dialog.setContentView(R.layout.suppliersearch);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(Bookinvoicenew.this));
                adapterd = new MyRecyclerViewAdapterd(Bookinvoicenew.this, dialoglist);
                dialog.getWindow().setLayout(width, height);

                esupplier = (AutoCompleteTextView)dialog.findViewById(R.id.esupplier);
                esupplier2 = (AutoCompleteTextView)dialog.findViewById(R.id.esupplier2);
                TextView dialogtext = (TextView) dialog.findViewById(R.id.dialogtext);
                dialogtext.setVisibility(View.VISIBLE);
                dialogtext.setText("Customer Search by Name");
                esupplier.setVisibility(View.GONE);
                esupplier.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().equalsIgnoreCase("")) {
                            dialoglist.clear();
                            recyclerView.setAdapter(adapterd);
                        } else {
                            try {


                            } catch (Exception e) {
                                //   Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                            }


                        }
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().equalsIgnoreCase("")) {
                            dialoglist.clear();
                            recyclerView.setAdapter(adapterd);
                        } else {
                            try {


                            } catch (Exception e) {
                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        //after the change calling the method and passing the search input
                        if (editable.toString().equalsIgnoreCase("")) {
                            dialoglist.clear();
                            recyclerView.setAdapter(adapterd);
                        } else {
                            try {


                            } catch (Exception e) {
                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });

                esupplier2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().equalsIgnoreCase("")) {
                            dialoglist.clear();
                            recyclerView.setAdapter(adapterd);
                        } else {
                            try {


                            } catch (Exception e) {
                                //   Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                            }


                        }
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.toString().equalsIgnoreCase("")) {
                            dialoglist.clear();
                            recyclerView.setAdapter(adapterd);
                        } else {
                            try {


                            } catch (Exception e) {
                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        //after the change calling the method and passing the search input
                        if (editable.toString().equalsIgnoreCase("")) {
                            dialoglist.clear();
                            recyclerView.setAdapter(adapterd);
                        } else {
                            try {


                            } catch (Exception e) {
                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
                Button select = (Button)dialog.findViewById(R.id.select);
                String selectQuerys = "SELECT  * FROM customerlist";
                Cursor cursors = dbs.rawQuery(selectQuerys, null);
                if (cursors.moveToFirst()) {
                    do {


                        if(arrayn.contains(cursors.getString(2)))
                        {

                        }
                        else
                        {
                            arrayn.add(cursors.getString(2));
                        }

                        // Log.e("VAL",""+cursor.getString(11));

                    } while (cursors.moveToNext());
                    String selectQuerys2 = "SELECT  * FROM customerlistnm";
                    Cursor cursors2 = dbs.rawQuery(selectQuerys2, null);
                    if(cursors2.getCount()!=0) {
                        if (cursors2.moveToFirst()) {
                            do {


                                if(arrayn.contains(cursors2.getString(2)))
                                {

                                }
                                else
                                {
                                    arrayn.add(cursors2.getString(2));
                                }

                                // Log.e("VAL",""+cursor.getString(11));

                            } while (cursors2.moveToNext());
                        }
                    }
                }



                ArrayAdapter<String> adapterlist2 = new ArrayAdapter<String>(Bookinvoicenew.this,
                        R.layout.spinnertext, arrayn);
                esupplier.setAdapter(adapterlist2);


                // set the custom dialog components - text, image and button
                esupplier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {


                        dialoglist.clear();
                        esupplier2.setText("");

                        Cursor cursor = dbs.query("customerlist", new String[]{"farmer_code","farmer_name",
                                        "village_name","farmer_state_code","mor","phone","id","surname","fhwname"}, "phone" + "=?",
                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
                        if(cursor.getCount()!=0)
                        {
                            if (cursor.moveToFirst()) {
                                do {

                                    Dialogpojo dialogpojo = new Dialogpojo();

                                    dialogpojo.setId(cursor.getString(6));
                                    dialogpojo.setFcode(cursor.getString(0));
                                    dialogpojo.setName(cursor.getString(1));
                                    dialogpojo.setLcn(cursor.getString(2));
                                    dialogpojo.setCode(cursor.getString(3));
                                    dialogpojo.setMem(cursor.getString(4));
                                    dialogpojo.setPh(cursor.getString(5));
                                    dialogpojo.setSn(cursor.getString(7));
                                    dialogpojo.setFhn(cursor.getString(8));
                                    dialogpojo.setType("Member");


                                    dialoglist.add(dialogpojo);
                                    // array2.add(cursor.getString(11));
                                    // Log.e("VAL",""+cursor.getString(11));
                                    recyclerView.setAdapter(adapterd);
                                    // Log.e("VAL",""+cursor.getString(11));

                                } while (cursor.moveToNext());
                            }



                        }
                        Cursor cursors2n = dbs.query("customerlistnm", new String[]{"farmer_code","farmer_name",
                                        "village_name","farmer_state_code","mor","phone","id","surname","fhwname"}, "farmer_name" + "=? COLLATE NOCASE",
                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
                        if(cursors2n.getCount()!=0) {
                            if (cursors2n.moveToFirst()) {
                                do {

                                    Dialogpojo dialogpojo = new Dialogpojo();

                                    dialogpojo.setId(cursors2n.getString(6));
                                    dialogpojo.setFcode(cursors2n.getString(0));
                                    dialogpojo.setName(cursors2n.getString(1));
                                    dialogpojo.setLcn(cursors2n.getString(2));
                                    dialogpojo.setCode(cursors2n.getString(3));
                                    dialogpojo.setMem(cursors2n.getString(4));
                                    dialogpojo.setPh(cursors2n.getString(5));
                                    dialogpojo.setSn(cursor.getString(7));
                                    dialogpojo.setFhn(cursor.getString(8));
                                    dialogpojo.setType("Non-Member");


                                    dialoglist.add(dialogpojo);
                                    // array2.add(cursor.getString(11));
                                    // Log.e("VAL",""+cursor.getString(11));
                                    recyclerView.setAdapter(adapterd);
                                    // Log.e("VAL",""+cursor.getString(11));

                                } while (cursors2n.moveToNext());
                            }
                        }


                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(esupplier.getWindowToken(), 0);
                    }
                });


                String selectQuerys2 = "SELECT  * FROM customerlist";
                Cursor cursors2 = dbs.rawQuery(selectQuerys2, null);
                if (cursors2.moveToFirst()) {
                    do {


                        if(arrayn2.contains(cursors2.getString(2)))
                        {

                        }
                        else
                        {
                            arrayn2.add(cursors2.getString(2));
                        }

                        // Log.e("VAL",""+cursor.getString(11));

                    } while (cursors2.moveToNext());
                    String selectQuerys2n = "SELECT  * FROM customerlistnm";
                    Cursor cursors2n = dbs.rawQuery(selectQuerys2n, null);
                    if(cursors2n.getCount()!=0) {
                        if (cursors2n.moveToFirst()) {
                            do {


                                if(arrayn2.contains(cursors2n.getString(2)))
                                {

                                }
                                else
                                {
                                    arrayn2.add(cursors2n.getString(2));
                                }

                                // Log.e("VAL",""+cursor.getString(11));

                            } while (cursors2n.moveToNext());
                        }
                    }

                }
                ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(Bookinvoicenew.this,
                        R.layout.spinnertext, arrayn2);

                esupplier2.setAdapter(adapterlist2n);
                // set up the RecyclerView

                esupplier2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {


                        dialoglist.clear();
                        esupplier.setText("");
                        Cursor cursor = dbs.query("customerlist", new String[]{"farmer_code","farmer_name",
                                        "village_name","farmer_state_code","mor","phone","id","surname","fhwname"}, "farmer_name" + "=? COLLATE NOCASE",
                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
                        if(cursor.getCount()!=0)
                        {
                            if (cursor.moveToFirst()) {
                                do {

                                    Dialogpojo dialogpojo = new Dialogpojo();

                                    dialogpojo.setId(cursor.getString(6));
                                    dialogpojo.setFcode(cursor.getString(0));
                                    dialogpojo.setName(cursor.getString(1));
                                    dialogpojo.setLcn(cursor.getString(2));
                                    dialogpojo.setCode(cursor.getString(3));
                                    dialogpojo.setMem(cursor.getString(4));
                                    dialogpojo.setPh(cursor.getString(5));
                                    dialogpojo.setSn(cursor.getString(7));
                                    dialogpojo.setFhn(cursor.getString(8));
                                    dialogpojo.setType("Member");

                                    dialoglist.add(dialogpojo);
                                    // array2.add(cursor.getString(11));
                                    // Log.e("VAL",""+cursor.getString(11));
                                    recyclerView.setAdapter(adapterd);
                                    // Log.e("VAL",""+cursor.getString(11));

                                } while (cursor.moveToNext());
                            }


                        }
                        Cursor cursors2n = dbs.query("customerlistnm", new String[]{"farmer_code","farmer_name",
                                        "village_name","farmer_state_code","mor","phone","id","surname","fhwname"}, "farmer_name" + "=? COLLATE NOCASE",
                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
                        if(cursors2n.getCount()!=0) {
                            if (cursors2n.moveToFirst()) {
                                do {

                                    Dialogpojo dialogpojo = new Dialogpojo();

                                    dialogpojo.setId(cursors2n.getString(6));
                                    dialogpojo.setFcode(cursors2n.getString(0));
                                    dialogpojo.setName(cursors2n.getString(1));
                                    dialogpojo.setLcn(cursors2n.getString(2));
                                    dialogpojo.setCode(cursors2n.getString(3));
                                    dialogpojo.setMem(cursors2n.getString(4));
                                    dialogpojo.setPh(cursors2n.getString(5));
                                    dialogpojo.setSn(cursors2n.getString(7));
                                    dialogpojo.setFhn(cursors2n.getString(8));
                                    dialogpojo.setType("Non-Member");

                                    dialoglist.add(dialogpojo);
                                    // array2.add(cursor.getString(11));
                                    // Log.e("VAL",""+cursor.getString(11));
                                    recyclerView.setAdapter(adapterd);
                                    // Log.e("VAL",""+cursor.getString(11));

                                } while (cursors2n.moveToNext());
                            }
                        }
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(esupplier.getWindowToken(), 0);
                    }
                });

                select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        spinnerm.setSelection(2);
//                        // esuppliern.setText(cursor2nn.getString(2));
//                        spinnerl.setEnabled(true);
//                        lcn="1";
//                        mcode = "QCD_UOM_NONMEM";
//                        elocation.setText("0");
//                       // elocation.setVisibility(View.GONE);
//                        rlocation.setVisibility(View.GONE);
//                        spinnerl.setVisibility(View.VISIBLE);
//                        spinnerl.setSelection(0);
//                        supplier.setText("");
//                        esuppliern.setText("");
//                        dialog.dismiss();

                        dialog.dismiss();
                        Intent i = new Intent(getApplicationContext(),Fheader.class);
                        startActivity(i);
//                        if(esupplier.getText().toString().equalsIgnoreCase(""))
//                        {
//                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
////set icon
//                                    .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                    .setTitle("Error!")
////set message
//                                    .setMessage("Empty Customer Mobile Number")
////set positive button
//                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialogInterface, int i) {
//                                                    //set what would happen when positive button is clicked
//
//                                                }
//                                            }
////set negative button
//
//                                    )
//                                    .show();
//                        }
//                        else if(esupplier2.getText().toString().equalsIgnoreCase(""))
//                        {
//                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
////set icon
//                                    .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                    .setTitle("Error!")
////set message
//                                    .setMessage("Empty Customer Name")
////set positive button
//                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialogInterface, int i) {
//                                                    //set what would happen when positive button is clicked
//
//                                                }
//                                            }
////set negative button
//
//                                    )
//                                    .show();
//                        }
//                        else
//                        {
//                            suppliername = esupplier.getText().toString()
//                            ;
//                            sun = esupplier2.getText().toString();
//                            suppliername2="";
//                            esupplier.setText(suppliername);
//                            dialog.dismiss();
//                            supplier.setText(suppliername);
//                            // elocation.setVisibility(View.VISIBLE);
//                            // spinnerl.setVisibility(View.GONE);
//                            lsup.setVisibility(View.VISIBLE);
//                            counttotal();
//                        }
                    }
                });

                ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
//                        finish();
//                        startActivity(getIntent());
                    }
                });

                dialog.show();
            }
        });



//        esuppliern.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                dialog = new Dialog(Bookinvoicenew.this);
//                dialog.setContentView(R.layout.suppliersearch);
//                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
//                dialog.setTitle("Title...");
//                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
//                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
//                final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
//                recyclerView.setLayoutManager(new LinearLayoutManager(Bookinvoicenew.this));
//                adapterd = new MyRecyclerViewAdapterd(Bookinvoicenew.this, dialoglist);
//                dialog.getWindow().setLayout(width, height);
//
//                  esupplier = (AutoCompleteTextView)dialog.findViewById(R.id.esupplier);
//                  esupplier2 = (AutoCompleteTextView)dialog.findViewById(R.id.esupplier2);
//                  esupplier.setVisibility(View.GONE);
//                esupplier.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                        if (charSequence.toString().equalsIgnoreCase("")) {
//                            dialoglist.clear();
//                            recyclerView.setAdapter(adapterd);
//                        } else {
//                            try {
//
//
//                            } catch (Exception e) {
//                                //   Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();
//
//                            }
//
//
//                        }
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                        if (charSequence.toString().equalsIgnoreCase("")) {
//                            dialoglist.clear();
//                            recyclerView.setAdapter(adapterd);
//                        } else {
//                            try {
//
//
//                            } catch (Exception e) {
//                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable editable) {
//                        //after the change calling the method and passing the search input
//                        if (editable.toString().equalsIgnoreCase("")) {
//                            dialoglist.clear();
//                            recyclerView.setAdapter(adapterd);
//                        } else {
//                            try {
//
//
//                            } catch (Exception e) {
//                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    }
//                });
//
//                esupplier2.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                        if (charSequence.toString().equalsIgnoreCase("")) {
//                            dialoglist.clear();
//                            recyclerView.setAdapter(adapterd);
//                        } else {
//                            try {
//
//
//                            } catch (Exception e) {
//                                //   Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();
//
//                            }
//
//
//                        }
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                        if (charSequence.toString().equalsIgnoreCase("")) {
//                            dialoglist.clear();
//                            recyclerView.setAdapter(adapterd);
//                        } else {
//                            try {
//
//
//                            } catch (Exception e) {
//                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable editable) {
//                        //after the change calling the method and passing the search input
//                        if (editable.toString().equalsIgnoreCase("")) {
//                            dialoglist.clear();
//                            recyclerView.setAdapter(adapterd);
//                        } else {
//                            try {
//
//
//                            } catch (Exception e) {
//                                //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    }
//                });
//                Button select = (Button)dialog.findViewById(R.id.select);
//                String selectQuerys = "SELECT  * FROM customerlist";
//                Cursor cursors = dbs.rawQuery(selectQuerys, null);
//                if (cursors.moveToFirst()) {
//                    do {
//
//                        if(arrayn.contains(cursors.getString(9)))
//                        {
//
//                        }
//                        else
//                        {
//                            arrayn.add(cursors.getString(9));
//                        }
//                        // Log.e("VAL",""+cursor.getString(11));
//
//                    } while (cursors.moveToNext());
//                    String selectQuerys2 = "SELECT  * FROM customerlistnm";
//                    Cursor cursors2 = dbs.rawQuery(selectQuerys2, null);
//                    if(cursors2.getCount()!=0) {
//                        if (cursors2.moveToFirst()) {
//                            do {
//
//                                if(arrayn.contains(cursors2.getString(9)))
//                                {
//
//                                }
//                                else
//                                {
//                                    arrayn.add(cursors2.getString(9));
//                                }
//                                // Log.e("VAL",""+cursor.getString(11));
//
//                            } while (cursors2.moveToNext());
//                        }
//                    }
//                }
//
//
//
//                ArrayAdapter<String> adapterlist2 = new ArrayAdapter<String>(Bookinvoicenew.this,
//                        R.layout.spinnertext, arrayn);
//                esupplier.setAdapter(adapterlist2);
//
//
//                // set the custom dialog components - text, image and button
//                esupplier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
//
//
//                        dialoglist.clear();
//                        esupplier2.setText("");
//
//                        Cursor cursor = dbs.query("customerlist", new String[]{"farmer_code","farmer_name",
//                                        "state_name","farmer_state_code","mor","phone","id"}, "phone" + "=?",
//                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
//                        if(cursor.getCount()!=0)
//                        {
//                            if (cursor.moveToFirst()) {
//                                do {
//
//                                    Dialogpojo dialogpojo = new Dialogpojo();
//
//                                    dialogpojo.setId(cursor.getString(6));
//                                    dialogpojo.setFcode(cursor.getString(0));
//                                    dialogpojo.setName(cursor.getString(1));
//                                    dialogpojo.setLcn(cursor.getString(2));
//                                    dialogpojo.setCode(cursor.getString(3));
//                                    dialogpojo.setMem(cursor.getString(4));
//                                    dialogpojo.setPh(cursor.getString(5));
//                                    dialogpojo.setType("Member");
//
//
//                                    dialoglist.add(dialogpojo);
//                                    // array2.add(cursor.getString(11));
//                                    // Log.e("VAL",""+cursor.getString(11));
//                                    recyclerView.setAdapter(adapterd);
//                                    // Log.e("VAL",""+cursor.getString(11));
//
//                                } while (cursor.moveToNext());
//                            }
//
//
//
//                        }
//                        Cursor cursors2n = dbs.query("customerlistnm", new String[]{"farmer_code","farmer_name",
//                                        "state_name","farmer_state_code","mor","phone","id"}, "phone" + "=?",
//                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
//
//                        if(cursors2n.getCount()!=0) {
//                            if (cursors2n.moveToFirst()) {
//                                do {
//
//                                    Dialogpojo dialogpojo = new Dialogpojo();
//
//                                    dialogpojo.setId(cursors2n.getString(6));
//
//                                    dialogpojo.setFcode(cursors2n.getString(0));
//                                    dialogpojo.setName(cursors2n.getString(1));
//                                    dialogpojo.setLcn(cursors2n.getString(2));
//                                    dialogpojo.setCode(cursors2n.getString(3));
//                                    dialogpojo.setMem(cursors2n.getString(4));
//                                    dialogpojo.setPh(cursors2n.getString(5));
//                                    dialogpojo.setType("Non-Member");
//
//
//                                    dialoglist.add(dialogpojo);
//                                    // array2.add(cursor.getString(11));
//                                    // Log.e("VAL",""+cursor.getString(11));
//                                    recyclerView.setAdapter(adapterd);
//                                    // Log.e("VAL",""+cursor.getString(11));
//
//                                } while (cursors2n.moveToNext());
//                            }
//                        }
//
//
//                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(esupplier.getWindowToken(), 0);
//                    }
//                });
//
//
//                String selectQuerys2 = "SELECT  * FROM customerlist";
//                Cursor cursors2 = dbs.rawQuery(selectQuerys2, null);
//                if (cursors2.moveToFirst()) {
//                    do {
//
//                        if(arrayn2.contains(cursors2.getString(2)))
//                        {
//
//                        }
//                        else
//                        {
//                            arrayn2.add(cursors2.getString(2));
//                        }
//                        // Log.e("VAL",""+cursor.getString(11));
//
//                    } while (cursors2.moveToNext());
//                    String selectQuerys2n = "SELECT  * FROM customerlistnm";
//                    Cursor cursors2n = dbs.rawQuery(selectQuerys2n, null);
//                    if(cursors2n.getCount()!=0) {
//                        if (cursors2n.moveToFirst()) {
//                            do {
//
//                                if(arrayn2.contains(cursors2n.getString(2)))
//                                {
//
//                                }
//                                else
//                                {
//                                    arrayn2.add(cursors2n.getString(2));
//                                }
//                                // Log.e("VAL",""+cursor.getString(11));
//
//                            } while (cursors2n.moveToNext());
//                        }
//                    }
//
//                }
//                ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(Bookinvoicenew.this,
//                        R.layout.spinnertext, arrayn2);
//
//                esupplier2.setAdapter(adapterlist2n);
//                // set up the RecyclerView
//
//                esupplier2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
//
//
//                        dialoglist.clear();
//                        esupplier.setText("");
//                        Cursor cursor = dbs.query("customerlist", new String[]{"farmer_code","farmer_name",
//                                        "state_name","farmer_state_code","mor","phone","id"}, "farmer_name" + "=? COLLATE NOCASE",
//                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
//                        if(cursor.getCount()!=0)
//                        {
//                        if (cursor.moveToFirst()) {
//                            do {
//
//                                Dialogpojo dialogpojo = new Dialogpojo();
//
//                                dialogpojo.setId(cursor.getString(6));
//                                dialogpojo.setFcode(cursor.getString(0));
//                                dialogpojo.setName(cursor.getString(1));
//                                dialogpojo.setLcn(cursor.getString(2));
//                                dialogpojo.setCode(cursor.getString(3));
//                                dialogpojo.setMem(cursor.getString(4));
//                                dialogpojo.setPh(cursor.getString(5));
//                                dialogpojo.setType("Member");
//
//                                dialoglist.add(dialogpojo);
//                                // array2.add(cursor.getString(11));
//                                // Log.e("VAL",""+cursor.getString(11));
//                                recyclerView.setAdapter(adapterd);
//                                // Log.e("VAL",""+cursor.getString(11));
//
//                            } while (cursor.moveToNext());
//                        }
//
//
//                        }
//                        Cursor cursors2n = dbs.query("customerlistnm", new String[]{"farmer_code","farmer_name",
//                                        "state_name","farmer_state_code","mor","phone","id"}, "farmer_name" + "=? COLLATE NOCASE",
//                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
//                        if(cursors2n.getCount()!=0) {
//                            if (cursors2n.moveToFirst()) {
//                                do {
//
//                                    Dialogpojo dialogpojo = new Dialogpojo();
//
//                                    dialogpojo.setId(cursors2n.getString(6));
//                                    dialogpojo.setFcode(cursors2n.getString(0));
//                                    dialogpojo.setName(cursors2n.getString(1));
//                                    dialogpojo.setLcn(cursors2n.getString(2));
//                                    dialogpojo.setCode(cursors2n.getString(3));
//                                    dialogpojo.setMem(cursors2n.getString(4));
//                                    dialogpojo.setPh(cursors2n.getString(5));
//                                    dialogpojo.setType("Non-Member");
//
//                                    dialoglist.add(dialogpojo);
//                                    // array2.add(cursor.getString(11));
//                                    // Log.e("VAL",""+cursor.getString(11));
//                                    recyclerView.setAdapter(adapterd);
//                                    // Log.e("VAL",""+cursor.getString(11));
//
//                                } while (cursors2n.moveToNext());
//                            }
//                        }
//                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(esupplier.getWindowToken(), 0);
//                    }
//                });
//
//                select.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        supplier.setFocusable(true);
//                        esuppliern.setFocusable(true);
//                        dialog.dismiss();
////                        if(esupplier.getText().toString().equalsIgnoreCase(""))
////                        {
////                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//////set icon
////                                    .setIcon(android.R.drawable.ic_dialog_alert)
//////set title
////                                    .setTitle("Error!")
//////set message
////                                    .setMessage("Empty Customer Mobile Number")
//////set positive button
////                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
////                                                @Override
////                                                public void onClick(DialogInterface dialogInterface, int i) {
////                                                    //set what would happen when positive button is clicked
////
////                                                }
////                                            }
//////set negative button
////
////                                    )
////                                    .show();
////                        }
////                        else if(esupplier2.getText().toString().equalsIgnoreCase(""))
////                        {
////                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//////set icon
////                                    .setIcon(android.R.drawable.ic_dialog_alert)
//////set title
////                                    .setTitle("Error!")
//////set message
////                                    .setMessage("Empty Customer Name")
//////set positive button
////                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
////                                                @Override
////                                                public void onClick(DialogInterface dialogInterface, int i) {
////                                                    //set what would happen when positive button is clicked
////
////                                                }
////                                            }
//////set negative button
////
////                                    )
////                                    .show();
////                        }
////                        else
////                        {
////                            suppliername = esupplier.getText().toString()
////                            ;
////                            sun = esupplier2.getText().toString();
////                            suppliername2="";
////                            esupplier.setText(suppliername);
////                            dialog.dismiss();
////                            supplier.setText(suppliername);
////                            // elocation.setVisibility(View.VISIBLE);
////                            // spinnerl.setVisibility(View.GONE);
////                            lsup.setVisibility(View.VISIBLE);
////                            counttotal();
////                        }
//                    }
//                });
//
//                ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
//                // if button is clicked, close the custom dialog
//                dialogButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
////                        finish();
////                        startActivity(getIntent());
//                    }
//                });
//
//                dialog.show();
//            }
//        });


        supplier.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() == 1 && s.toString().startsWith("0")) {
                    s.clear();
                }
                else if(s.toString().length() == 1 && s.toString().startsWith("."))
                {
                    s.clear();
                }
                else if(s.toString().length()==0)
                {

                    //  equantity.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                esuppliern.setEnabled(true);

                if(s.length() == 0)
                {
                    spinnerl.setSelection(0);
                    elocation.setText("");
                    etax.setText("");
                    esuppliern.setText("");
                    einwamt.setText("");
                    ss="";
                    flag = "0";
                }

                if(s.length()==10)
                {

                    if (array3.contains(s.toString())) {


                        //elocation.setVisibility(View.VISIBLE);

                         /*   rlocation.setVisibility(View.VISIBLE);
                            spinnerl.setVisibility(View.GONE);*/

                        if(flag.equalsIgnoreCase("1"))
                        {
                            rlocation.setVisibility(View.VISIBLE);
                            spinnerl.setVisibility(View.GONE);

                        }else{
                            rlocation.setVisibility(View.VISIBLE);
                            spinnerl.setVisibility(View.GONE);
                            // Toast.makeText(Bookinvoicenew.this, "Enter Amount Paid", Toast.LENGTH_SHORT).show();
                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("This number already available. So please search valid member")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                    flag = "0";
                                                }
                                            }
//set negative button

                                    )
                                    .show();
                        }

                        // true
                    }
                    else
                    {
                        //Toast.makeText(Bookinvoicenew.this, "hi", Toast.LENGTH_SHORT).show();
                        // elocation.setVisibility(View.GONE);
                        rlocation.setVisibility(View.GONE);
                        spinnerl.setVisibility(View.VISIBLE);
                        // esuppliern.setEnabled(true);
                        spinnerm.setSelection(2);
                        spinner.setEnabled(true);
                        mm="1";

                    }
                }

            }
        });
        // looping through all rows and adding to list

//        if(sharedpreferences.getString("off","").equalsIgnoreCase("1"))
//        {
//            if(sharedpreferences.getString("bi","").equalsIgnoreCase("0")) {
//                dbs.execSQL("delete from product");
//
//                dbs.execSQL("delete from customerlist");
//                list.setVisibility(View.GONE);
//                pdialog.setCanceledOnTouchOutside(false);
//                pdialog.setTitle("Loading.....");
//                pdialog.show();
//                final String url = "http://169.38.77.190:949/Deployable_image_uppro_pro/api/KANCHIICD_MOBILEAPP/ICD_MOBILE_Product?org=PKFPO&locn="+Pojokyc.instance+"&user=admin&lang=en_US";
//
//// prepare the Request
//                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                // display response
//                                try {
//                                    JSONObject obj = response.getJSONObject("context");
//                                    JSONArray cast = obj.getJSONArray("Detail");
//                                    for (int i = 0; i < cast.length(); i++) {
//
//
//                                        JSONObject actor = cast.getJSONObject(i);
//
//                                        String n1 = actor.getString("In_product_code");
//                                        String n2 = actor.getString("In_product_name");
//                                        String n3 = actor.getString("In_productcategory_code");
//                                        String n4 = actor.getString("In_productcategory_desc");
//                                        String n5 = actor.getString("In_productsubcategory_code");
//                                        String n6 = actor.getString("In_productsubcategory_desc");
//                                        String n7 = actor.getString("In_uomtype_code");
//                                        String n8 = actor.getString("In_uomtype_desc");
//                                        String n9 = actor.getString("In_hsn_code");
//                                        String n10 = actor.getString("In_hsn_desc");
//                                        String n11 = actor.getString("In_base_price");
//                                        String n12 = actor.getString("In_current_qty");
//                                        Log.e("CHK", "" + actor.getString("In_cgst"));
//                                        String n13 = actor.getString("In_cgst");
//
//                                        String n14 = actor.getString("In_sgst");
//                                        String n15 = actor.getString("In_igst");
//                                        String n16 = actor.getString("In_orgn_code");
//                                        String n17 = actor.getString("In_ic_code");
//
//                                        db.insertContact(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17);
//
//
//                                    }
//
//                                    String selectQuery = "SELECT  * FROM product";
//                                    Cursor cursor = dbs.rawQuery(selectQuery, null);
//                                    if (cursor.moveToFirst()) {
//                                        do {
//
//                                            array.add(cursor.getString(2));
//                                            array2.add(cursor.getString(11));
//                                            Log.e("VAL", "" + cursor.getString(11));
//
//                                        } while (cursor.moveToNext());
//                                    }
//
//                                    ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(Bookinvoicenew.this,
//                                            android.R.layout.select_dialog_item, array);
//                                    item.setAdapter(adapterlist);
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                                Log.d("Response", response.toString());
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Log.d("Error.Response", String.valueOf(error));
//                            }
//                        }
//                );
//
//// add it to the RequestQueue
//                getRequest.setRetryPolicy(new DefaultRetryPolicy(
//                        50000,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                VolleySingleton.getInstance(Bookinvoicenew.this).addToRequestQueue(getRequest);
//
//                final String url3 = "http://169.38.77.190:949/Deployable_image_uppro_pro/api/KANCHIICD_MOBILEAPP/Customers?org=PKFPO%2F00001&locn=flexi&user="+Pojokyc.instance+"o&lang=en_US";
//
//// prepare the Request
//                JsonObjectRequest getRequest3 = new JsonObjectRequest(Request.Method.GET, url3, null,
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                // display response
//                                Log.e("Response", response.toString());
//                                try {
//                                    JSONObject obj = response.getJSONObject("context");
//                                    JSONArray cast = obj.getJSONArray("CustomerList");
//                                    for (int i = 0; i < cast.length(); i++) {
//
//
//                                        JSONObject actor = cast.getJSONObject(i);
//                                        String n1 = actor.getString("farmer_code");
//                                        String n2 = actor.getString("farmer_name");
//                                        String n3 = actor.getString("village_name");
//                                        String n4 = actor.getString("farmer_address");
//                                        String n5 = actor.getString("state_name");
//                                        String n6 = actor.getString("farmer_village_code");
//                                        String n7 = actor.getString("farmer_state_code");
//
//
//                                        db.insertcustomer(n1, n2, n3, n4, n5, n6, n7);
//
//
//                                    }
//                                    String selectQuerys = "SELECT  * FROM customerlist";
//                                    Cursor cursors = dbs.rawQuery(selectQuerys, null);
//                                    if (cursors.moveToFirst()) {
//                                        do {
//
//                                            array3.add(cursors.getString(2));
//                                            // Log.e("VAL",""+cursor.getString(11));
//
//                                        } while (cursors.moveToNext());
//                                    }
//
//                                    ArrayAdapter<String> adapterlist2 = new ArrayAdapter<String>(Bookinvoicenew.this,
//                                            android.R.layout.select_dialog_item, array3);
//                                    supplier.setAdapter(adapterlist2);
//                                    pdialog.dismiss();
//                                    SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                                    editor.putString("bi", "1");
//
//                                    editor.commit();
//
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Log.e("Response", error.toString());
//                                Log.d("Error.Response", String.valueOf(error));
//                            }
//                        }
//                );
//
//// add it to the RequestQueue
//                getRequest3.setRetryPolicy(new DefaultRetryPolicy(
//                        90000,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                VolleySingleton.getInstance(Bookinvoicenew.this).addToRequestQueue(getRequest3);
//            }
//            else
//            {
//                String selectQuery = "SELECT  * FROM product";
//                Cursor cursor = dbs.rawQuery(selectQuery, null);
//                if (cursor.moveToFirst()) {
//                    do {
//
//                        array.add(cursor.getString(2));
//                        array2.add(cursor.getString(11));
//                        Log.e("VAL",""+cursor.getString(11));
//
//                    } while (cursor.moveToNext());
//                }
//
//                ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(this,
//                        android.R.layout.select_dialog_item, array);
//                item.setAdapter(adapterlist);
//
//                String selectQuerys = "SELECT  * FROM customerlist";
//                Cursor cursors = dbs.rawQuery(selectQuerys, null);
//                if (cursors.moveToFirst()) {
//                    do {
//
//                        array3.add(cursors.getString(2));
//                        // Log.e("VAL",""+cursor.getString(11));
//
//                    } while (cursors.moveToNext());
//                }
//
//                ArrayAdapter<String> adapterlist2 = new ArrayAdapter<String>(this,
//                        android.R.layout.select_dialog_item, array3);
//                supplier.setAdapter(adapterlist2);
//            }
//        }
//        else
//        {
//            String selectQuery = "SELECT  * FROM product";
//            Cursor cursor = dbs.rawQuery(selectQuery, null);
//            if (cursor.moveToFirst()) {
//                do {
//
//                    array.add(cursor.getString(2));
//                    array2.add(cursor.getString(11));
//                    Log.e("VAL",""+cursor.getString(11));
//
//                } while (cursor.moveToNext());
//            }
//
//            ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(this,
//                    android.R.layout.select_dialog_item, array);
//            item.setAdapter(adapterlist);


        // }

        item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor2 = dbs.query("product", new String[]{"In_product_code",
                                "In_base_price","id","In_current_qty","id","In_ic_code"}, "In_product_name" + "=?",
                        new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);

                if (cursor2.moveToFirst()) {
                    do {
                        if(cursor2.getString(3).toString().equalsIgnoreCase("0.0000"))
                        {
                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("No Stocks Available")
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
                            item.setText("");
                        }


                        else {

                            if(cursor2.getString(5).equalsIgnoreCase(sharedpreferences.getString("ocnew",""))) {


                                Long tsLong = System.currentTimeMillis() / 1000;
                                tspr = tsLong.toString() + "BINV";
                                esl.setEnabled(true);
                                cq = cursor2.getString(3);
                                double d = Double.parseDouble(cursor2.getString(1));
                                idn = cursor2.getString(2);

                                Toast.makeText(Bookinvoicenew.this, "" + cursor2.getString(5), Toast.LENGTH_SHORT).show();

                                erate.setText("" + String.format("%.2f", d));
                            }
                        }


                    }
                    while (cursor2.moveToNext());


                }
            }
        });

        equantity.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() == 1 && s.toString().startsWith("00")) {
                    s.clear();
                }
                else if(s.toString().length() == 1 && s.toString().startsWith("."))
                {
                    s.clear();
                }
                else if(s.toString().length()==0)
                {
                    //  equantity.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {


                if(s.length() != 0)
                    getAmt();
                else
                {
                    eamount.setText("");
                }

            }
        });

        erate.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() == 1 && s.toString().startsWith("00")) {
                    s.clear();
                }
                else if(s.toString().length() == 1 && s.toString().startsWith("."))
                {
                    s.clear();
                }
                else if(s.toString().length()==0)
                {
                    //erate.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {


                if(s.length() != 0)
                    getAmt2();
                else
                {
                    //  erate.setText("");
                }

            }
        });

        etransport.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() == 1 && s.toString().startsWith("00")) {
                    s.clear();
                }
                else if(s.toString().length() == 1 && s.toString().startsWith("."))
                {
                    s.clear();
                }
                else if(s.toString().length()==0)
                {
                    //  equantity.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.toString().startsWith(".")){
                    etransport.setText("");
                }
                else if(s.length() != 0 || s.toString().equalsIgnoreCase("")){
                getAmtt();
                }
                else if(!s.toString().equalsIgnoreCase("") && s.length() != 0)
                {
                    try {
                        if(eothers.getText().toString().equalsIgnoreCase(""))
                        {
                            DecimalFormat amountFormate  = new DecimalFormat("############.##");
                            amountFormate.setMinimumFractionDigits(2);
                            amountFormate.setMaximumFractionDigits(2);
                            einwamt.setText(""+(""+String.format("%.2f", fi)));
                            double t = Double.parseDouble(etotal.getText().toString());
                            double ta = Double.parseDouble(etax.getText().toString());

                            double fi =t +ta;

                            einwamt.setText(""+(""+String.format("%.2f", fi)));
                        }
                        else
                        {
                            DecimalFormat amountFormate  = new DecimalFormat("############.##");
                            amountFormate.setMinimumFractionDigits(2);
                            amountFormate.setMaximumFractionDigits(2);
                            einwamt.setText(""+(""+String.format("%.2f", fi)));
                            double t = Double.parseDouble(etotal.getText().toString());
                            double ta = Double.parseDouble(etax.getText().toString());
                            double to = Double.parseDouble(eothers.getText().toString());

                            double fi =t +ta+to;

                            einwamt.setText(""+(""+String.format("%.2f", fi)));
                        }

                    }
                    catch (Exception e)
                    {

                    }
                }

            }
        });


        eothers.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() == 1 && s.toString().startsWith("00")) {
                    s.clear();
                }
                else if(s.toString().length() == 1 && s.toString().startsWith("."))
                {
                    s.clear();
                }
                else if(s.toString().length()==0)
                {
                    //  equantity.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.toString().startsWith(".")){
                    eothers.setText("");
                }
                else if(s.length() != 0 || s.toString().trim().equalsIgnoreCase("")){
                    getAmto();
                }
                else if(!s.toString().equalsIgnoreCase("") && s.length() != 0)
                {
                    try {
                        if(etransport.getText().toString().equalsIgnoreCase("")) {
                            DecimalFormat amountFormate = new DecimalFormat("############.##");
                            amountFormate.setMinimumFractionDigits(2);
                            amountFormate.setMaximumFractionDigits(2);
                            einwamt.setText(""+(""+String.format("%.2f", fi)));
                            double t = Double.parseDouble(etotal.getText().toString());
                            double ta = Double.parseDouble(etax.getText().toString());

                            double fi = t + ta;

                            einwamt.setText(""+(""+String.format("%.2f", fi)));
                        }
                        else
                        {
                            DecimalFormat amountFormate = new DecimalFormat("############.##");
                            amountFormate.setMinimumFractionDigits(2);
                            amountFormate.setMaximumFractionDigits(2);
                            einwamt.setText(""+(""+String.format("%.2f", fi)));
                            double t = Double.parseDouble(etotal.getText().toString());
                            double ta = Double.parseDouble(etax.getText().toString());
                            double tt = Double.parseDouble(etransport.getText().toString());

                            double fi = t + ta+tt;

                            einwamt.setText(""+(""+String.format("%.2f", fi)));
                        }
                    }
                    catch (Exception e)
                    {

                    }
                }

            }
        });


        ediscount.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() == 1 && s.toString().startsWith("00")) {
                    s.clear();
                }
                else if(s.toString().length() == 1 && s.toString().startsWith("."))
                {
                    s.clear();
                }
                else if(s.toString().length()==0)
                {
                    //erate.setText("");
                    getAmt2();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

try {
    if (s.length() != 0)

        getAmt3();

    else {
        getAmt2();
        //  erate.setText("");
    }
}
catch (Exception e)
{

}

            }
        });


        eamtpaid.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() == 1 && s.toString().startsWith("00")) {
                    s.clear();
                }
                else if(s.toString().length() == 1 && s.toString().startsWith("."))
                {
                    s.clear();
                }
                else if(s.toString().length()==0)
                {
                    //erate.setText("");
                    getAmt2();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                try {


                    if (s.length() != 0) {

                        if(s.toString().startsWith(".",0))
                        {

                        }
                        else {


                            double x = Double.parseDouble(amount.getText().toString());
                            double y = Double.parseDouble(eamtpaid.getText().toString());
                            if (y > x) {
                                eamtpaid.setText("");
                                // Toast.makeText(Bookinvoicenew.this, "Invalid amount", Toast.LENGTH_SHORT).show();
                                final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Balance Amount is " + x)
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
                            } else {
                                double z = x - y;
                                DecimalFormat amountFormate = new DecimalFormat("############.##");
                                amountFormate.setMinimumFractionDigits(2);
                                amountFormate.setMaximumFractionDigits(2);
                                einwamt.setText("" + ("" + String.format("%.2f", fi)));
                                ebal.setText("" + ("" + String.format("%.2f", z)));
                            }
                        }
                    } else {
                        //  getAmt2();
                        ebal.setText("");
                        //  erate.setText("");
                    }
                }
                catch(Exception e)
                {

                }

            }
        });



        etax.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() == 1 && s.toString().startsWith("0")) {
                    s.clear();
                }
                else if(s.toString().length() == 1 && s.toString().startsWith("."))
                {
                    s.clear();
                }
                else if(s.toString().length()==0)
                {
                    //erate.setText("");
                    // getAmt2();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {


                if(s.length() != 0) {
                    // getAmt3();
                    // etransport.setEnabled(true);
                    //eothers.setEnabled(true);
                }
                else
                {
                    // getAmt2();
                    // etransport.setEnabled(false);
                    // eothers.setEnabled(false);
                    //  erate.setText("");
                }

            }
        });

        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  String ltype1 = "";
                if (item.getText().toString().equalsIgnoreCase("")) {

                } else {
                    Log.e("itemgettext", item.getText().toString().trim());
                    boolean contains = Arrays.asList(""+array).contains(item.getText().toString().trim());
                    if (contains) {

                    } else {
                        ltype1 = "invalid";
                    }
                }
*/
                if(item.getText().toString().equalsIgnoreCase(""))
                {
                    //Toast.makeText(Bookinvoicenew.this, "Please Enter Item", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Please Enter Item !")
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
                }/*else if(ltype1.equalsIgnoreCase("invalid"))
                {
                    //Toast.makeText(Bookinvoicenew.this, "Please Enter Item", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Please Enter valid item !")
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
                }*/
                else if(eamount.getText().toString().equalsIgnoreCase(""))
                {
                    // Toast.makeText(Bookinvoicenew.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Please Enter Amount !")
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

                else if(erate.getText().toString().equalsIgnoreCase(""))
                {
                    //Toast.makeText(Bookinvoicenew.this, "Please Enter Rate", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Please Enter Rate !")
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
                else if(equantity.getText().toString().equalsIgnoreCase(""))
                {
                    //Toast.makeText(Bookinvoicenew.this, "Please Enter Rate", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Please Enter Quantity !")
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
                else {
                    if(setflag == 0) {
                        if (ediscount.getText().toString().equalsIgnoreCase("")) {
                            bq = 0;
                            eicd.setEnabled(false);
                            db.insertproduct2(item.getText().toString(), equantity.getText().toString(), erate.getText().toString(), eamount.getText().toString(), "0", enetamount.getText().toString(),idn,"0",tspr);
                            // Toast.makeText(Bookinvoicenew.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                            counttotal();
                            item.setText("");
                            erate.setText("");
                            equantity.setText("");
                            ediscount.setText("");
                            eamount.setText("");
                            enetamount.setText("");
                            esl.setText("");
                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Product Added Successfully!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                    item.requestFocus();
                                                }
                                            }
//set negative button

                                    )
                                    .show();



                        } else {
                            bq = 0;
                            eicd.setEnabled(false);
                            db.insertproduct2(item.getText().toString(), equantity.getText().toString(), erate.getText().toString(), eamount.getText().toString(), ediscount.getText().toString(), enetamount.getText().toString(),idn,"0",tspr);
                            //  Toast.makeText(Bookinvoicenew.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                            counttotal();
                            item.setText("");
                            erate.setText("");
                            equantity.setText("");
                            ediscount.setText("");
                            eamount.setText("");
                            enetamount.setText("");
                            esl.setText("");
                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Product Added Successfully!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                    item.requestFocus();
                                                }
                                            }
//set negative button

                                    )
                                    .show();
                        }
//                        finish();
//                        startActivity(getIntent());
                    }
                    else
                    {
                        if (ediscount.getText().toString().equalsIgnoreCase("")) {
                            bq = 0;
                            eicd.setEnabled(false);
                            db.updateproduct2(Integer.valueOf(prid),item.getText().toString(), equantity.getText().toString(), erate.getText().toString(), eamount.getText().toString(), "", enetamount.getText().toString(),idn,"0",tspr);
                            // Toast.makeText(Bookinvoicenew.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                            counttotal();
                            item.setText("");
                            erate.setText("");
                            equantity.setText("");
                            item.setEnabled(true);
                            ediscount.setText("");
                            item.showDropDown();
                            eamount.setText("");
                            enetamount.setText("");
                            esl.setText("");
                            badd.setText("Add");
                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Product Updated Successfully!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                    item.requestFocus();
                                                }
                                            }
//set negative button

                                    )
                                    .show();

                        } else {
                            bq = 0;
                            eicd.setEnabled(false);
                            db.updateproduct2(Integer.valueOf(prid),item.getText().toString(), equantity.getText().toString(), erate.getText().toString(), eamount.getText().toString(), ediscount.getText().toString(), enetamount.getText().toString(),idn,"0",tspr);
                            // Toast.makeText(Bookinvoicenew.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                            counttotal();
                            item.setText("");
                            erate.setText("");
                            item.setEnabled(true);
                            item.showDropDown();
                            equantity.setText("");
                            ediscount.setText("");
                            eamount.setText("");
                            enetamount.setText("");
                            esl.setText("");
                            badd.setText("Add");
                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Product Updated Successfully!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                    item.requestFocus();
                                                }
                                            }
//set negative button

                                    )
                                    .show();

                        }
//                        finish();
//                        startActivity(getIntent());
                    }
                }

            }
        });





        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel2();
            }

        };


        Date cc = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(cc);
     //  edate.setText(formattedDate);
     //  epdate.setText(formattedDate);

        edate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Bookinvoicenew.this,android.R.style.Theme_Holo_Dialog , date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        epdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Bookinvoicenew.this,android.R.style.Theme_Holo_Dialog , date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double ta;
                    if (etax.getText().toString().equalsIgnoreCase("")) {
                        ta = Double.parseDouble(newnet.getText().toString());
                    } else {
                        ta = Double.parseDouble(newnet.getText().toString()) + Double.parseDouble(etax.getText().toString());
                    }
                    Intent i = new Intent(getApplicationContext(), Viewstockinward.class);
                    i.putExtra("FRM", "1");
                    i.putExtra("T", etransport.getText().toString());
                    i.putExtra("O", eothers.getText().toString());
                    i.putExtra("BT", btnn);
                    i.putExtra("ss", ss);
                    i.putExtra("II", "ii");
                    i.putExtra("ta", String.valueOf(ta));
                    startActivity(i);
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

                }
                catch(Exception e)
                {

                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnn.equalsIgnoreCase("0")) {
                    dialog = new Dialog(Bookinvoicenew.this);
                    dialog.setContentView(R.layout.viewitem);
                    dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                    dialog.setTitle("Title...");
                    int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                    int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);

                    dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                    // set the custom dialog components - text, image and button


                    androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Bookinvoicenew.this));
                    adapter = new Bookinvoicenew.MyRecyclerViewAdapter(Bookinvoicenew.this, productlist);
                    String selectQuery = "SELECT  * FROM productlist2 where flag = 0";
                    productlist.clear();
                    Cursor cursor = dbs.rawQuery(selectQuery, null);

                    // looping through all rows and adding to list
                    if (cursor.moveToFirst()) {
                        do {
                            pojoProduct pojoProduct = new pojoProduct();

                            pojoProduct.setId(cursor.getString(0));
                            pojoProduct.setItem(cursor.getString(1));
                            pojoProduct.setQty(cursor.getString(2));
                            pojoProduct.setRate(cursor.getString(3));
                            pojoProduct.setAmt(cursor.getString(4));
                            pojoProduct.setDis(cursor.getString(5));
                            pojoProduct.setNamt(cursor.getString(6));
                            pojoProduct.setId2(cursor.getString(7));
                            pojoProduct.setTspr(cursor.getString(9));
                            Log.e("Check", "" + cursor.getString(1));

                            productlist.add(pojoProduct);
                            // array2.add(cursor.getString(11));
                            // Log.e("VAL",""+cursor.getString(11));
                            recyclerView.setAdapter(adapter);
                        } while (cursor.moveToNext());

                    }

                    // set up the RecyclerView


                    EditText ecount2 = (EditText) dialog.findViewById(R.id.ecount);
                    EditText etotal2 = (EditText) dialog.findViewById(R.id.etotal);
                    ecount2.setText(""+ecount.getText().toString());
                    etotal2.setText(""+etotal.getText().toString());
                    ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
//                        finish();
//                        startActivity(getIntent());
                        }
                    });

                    dialog.show();
                } else {

                    dialog = new Dialog(Bookinvoicenew.this);
                    dialog.setContentView(R.layout.viewitem);
                    dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                    dialog.setTitle("Title...");
                    int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                    int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);

                    dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                    // set the custom dialog components - text, image and button


                    androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Bookinvoicenew.this));
                    adapter = new Bookinvoicenew.MyRecyclerViewAdapter(Bookinvoicenew.this, productlist);
                    String nid = sharedpreferences.getString("ids2","");
                    String nid2 = nid.substring(1, nid.length() - 1);
                    String[] id = nid2.split(",");
                    productlist.clear();


                    for(int i = 0 ; i< id.length;i++) {
                        final SQLiteDatabase dbs = db.getWritableDatabase();
                        String selectQuery = "SELECT  * FROM productlist2 where id =" + id[i];
                        Cursor cursor = dbs.rawQuery(selectQuery, null);
                        if (cursor.moveToFirst()) {
                            do {
                                pojoProduct pojoProduct = new pojoProduct();

                                pojoProduct.setId(cursor.getString(0));
                                pojoProduct.setItem(cursor.getString(1));
                                pojoProduct.setQty(cursor.getString(2));
                                pojoProduct.setRate(cursor.getString(3));
                                pojoProduct.setAmt(cursor.getString(4));
                                pojoProduct.setDis(cursor.getString(5));
                                pojoProduct.setNamt(cursor.getString(6));
                                pojoProduct.setId2(cursor.getString(7));
                                Log.e("Check", "" + cursor.getString(1));

                                productlist.add(pojoProduct);
                                // array2.add(cursor.getString(11));
                                // Log.e("VAL",""+cursor.getString(11));
                                recyclerView.setAdapter(adapter);
                            } while (cursor.moveToNext());

                        }

                        // set up the RecyclerView


                        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
                        // if button is clicked, close the custom dialog
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
//                        finish();
//                        startActivity(getIntent());
                            }
                        });

                        dialog.show();
                    }
                }
            }
        });


        //////////////////////Tax Calculation///////////////////////////////////////




        ////////////////////////////////////////////////////////////////////////////////////




        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btnn.equalsIgnoreCase("0")) {
                    dialog2 = new Dialog(Bookinvoicenew.this);
                    dialog2.setContentView(R.layout.viewtaxd);
                    dialog2.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                    dialog2.setTitle("Title...");
                    int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                    int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);

                    dialog2.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                    // set the custom dialog components - text, image and button

                    // set up the RecyclerView
                    androidx.recyclerview.widget.RecyclerView recyclerView = dialog2.findViewById(R.id.itm);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Bookinvoicenew.this));
                    adapter2 = new Bookinvoicenew.MyRecyclerViewAdapter2(Bookinvoicenew.this, producttaxlist);

                    String selectQuery = "SELECT  * FROM productlist2 where flag = 0";
                    producttaxlist.clear();
                    Cursor cursor = dbs.rawQuery(selectQuery, null);

                    // looping through all rows and adding to list
                    if (cursor.moveToFirst()) {
                        do {

                            pojoProducttax pojoProducttax = new pojoProducttax();

                            String id = cursor.getString(0);

                            pojoProducttax.setRate(cursor.getString(3));
                            pojoProducttax.setItem(cursor.getString(1));
                            pojoProducttax.setNamt(cursor.getString(6));


                            Cursor cursor2 = dbs.query("product", new String[]{"In_cgst",
                                            "In_sgst", "In_igst", "In_hsn_code"}, "In_product_name" + "=?",
                                    new String[]{cursor.getString(1)}, null, null, null, null);

                            if (cursor2.moveToFirst()) {
                                do {

                                    pojoProducttax.setCgst("" + Float.parseFloat(cursor2.getString(0)));
                                    pojoProducttax.setSgst("" + Float.parseFloat(cursor2.getString(1)));
                                    pojoProducttax.setIgst("" + Float.parseFloat(cursor2.getString(2)));
                                    pojoProducttax.setHsn(cursor2.getString(3));


                                }
                                while (cursor2.moveToNext());
                            }

                            Log.e("Check", "" + cursor.getString(1));

                            if (ss.equalsIgnoreCase("")) {


                            } else {
                                producttaxlist.add(pojoProducttax);
                                recyclerView.setAdapter(adapter2);
                            }
                        } while (cursor.moveToNext());

                    }

                    ImageView dialogButton = (ImageView) dialog2.findViewById(R.id.cls);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog2.dismiss();
                        }
                    });

                    dialog2.show();
                }
                else
                {
                    dialog2 = new Dialog(Bookinvoicenew.this);
                    dialog2.setContentView(R.layout.viewtaxd);
                    dialog2.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                    dialog2.setTitle("Title...");
                    int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                    int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);

                    dialog2.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                    // set the custom dialog components - text, image and button

                    // set up the RecyclerView
                    androidx.recyclerview.widget.RecyclerView recyclerView = dialog2.findViewById(R.id.itm);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Bookinvoicenew.this));
                    adapter2 = new Bookinvoicenew.MyRecyclerViewAdapter2(Bookinvoicenew.this, producttaxlist);


                    producttaxlist.clear();

                    String nid = sharedpreferences.getString("ids2","");
                    String nid2 = nid.substring(1, nid.length() - 1);
                    String[] id = nid2.split(",");


                    for(int i = 0 ; i< id.length;i++) {
                        final SQLiteDatabase dbs = db.getWritableDatabase();
                        String selectQuery = "SELECT  * FROM productlist2 where id =" + id[i];
                        Cursor cursor = dbs.rawQuery(selectQuery, null);
                        // looping through all rows and adding to list
                        if (cursor.moveToFirst()) {
                            do {

                                pojoProducttax pojoProducttax = new pojoProducttax();

                                String idd = cursor.getString(0);

                                pojoProducttax.setRate(cursor.getString(3));
                                pojoProducttax.setItem(cursor.getString(1));
                                pojoProducttax.setNamt(cursor.getString(6));


                                Cursor cursor2 = dbs.query("product", new String[]{"In_cgst",
                                                "In_sgst", "In_igst", "In_hsn_code"}, "In_product_name" + "=?",
                                        new String[]{cursor.getString(1)}, null, null, null, null);

                                if (cursor2.moveToFirst()) {
                                    do {

                                        pojoProducttax.setCgst("" + Float.parseFloat(cursor2.getString(0)));
                                        pojoProducttax.setSgst("" + Float.parseFloat(cursor2.getString(1)));
                                        pojoProducttax.setIgst("" + Float.parseFloat(cursor2.getString(2)));
                                        pojoProducttax.setHsn(cursor2.getString(3));


                                    }
                                    while (cursor2.moveToNext());
                                }


                                Log.e("Check", "" + cursor.getString(1));

                                if (ss.equalsIgnoreCase("")) {


                                } else {
                                    producttaxlist.add(pojoProducttax);
                                    recyclerView.setAdapter(adapter2);
                                }
                            } while (cursor.moveToNext());

                        }
                    }

                    ImageView dialogButton = (ImageView) dialog2.findViewById(R.id.cls);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog2.dismiss();
                        }
                    });

                    dialog2.show();
                }
            }
        });




        viewp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3 = new Dialog(Bookinvoicenew.this);
                dialog3.setContentView(R.layout.viewpaylist);
                dialog3.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog3.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                dialog3.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                // set the custom dialog components - text, image and button

                // set up the RecyclerView
                androidx.recyclerview.widget.RecyclerView recyclerView = dialog3.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(Bookinvoicenew.this));
                adapter3 = new Bookinvoicenew.MyRecyclerViewAdapter3(Bookinvoicenew.this, paylist);

                Cursor cursor = dbs.query("paylist", new String[]{"paymode",
                                "date","refno","amountpaid"}, "invoiceno" + "=?",
                        new String[]{itext}, null, null, null, null);
                paylist.clear();
                //   Cursor cursor = dbs.rawQuery(selectQuery, null);
                Log.e("CCK","C"+cursor.getCount());

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {

                        pojopaytax pojopaytax = new pojopaytax();

                        //   String id = cursor.getString(0);

                        pojopaytax.setAmt(""+Float.parseFloat(cursor.getString(3)));
                        pojopaytax.setRefno(cursor.getString(2));
                        pojopaytax.setDate(cursor.getString(1));
                        pojopaytax.setPaymode(cursor.getString(0));



                        Log.e("Check",""+cursor.getString(1));

                        paylist.add(pojopaytax);
                        // array2.add(cursor.getString(11));
                        // Log.e("VAL",""+cursor.getString(11));
                        recyclerView.setAdapter(adapter3);
                    } while (cursor.moveToNext());

                }

                ImageView dialogButton = (ImageView) dialog3.findViewById(R.id.cls);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog3.dismiss();
                    }
                });

                dialog3.show();
            }
        });



        detail = (Button)findViewById(R.id.detail);
        header = (Button)findViewById(R.id.header);
        l1 =(LinearLayout)findViewById(R.id.l1);
        l2 = (LinearLayout)findViewById(R.id.l2);
        l3 = (LinearLayout)findViewById(R.id.l3);
        l4 = (LinearLayout)findViewById(R.id.l4);

        detail.setBackgroundResource(R.drawable.rbutton6);
        header.setBackgroundResource(R.drawable.rbutton4);
        payment.setBackgroundResource(R.drawable.rbutton4);
        l1.setVisibility(View.VISIBLE);
        l2.setVisibility(View.GONE);
        l3.setVisibility(View.GONE);
        l4.setVisibility(View.GONE);
        lsave.setVisibility(View.GONE);


        rdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badd.setVisibility(View.VISIBLE);
                view.setVisibility(View.VISIBLE);
                lsave.setVisibility(View.GONE);
                detail.setBackgroundResource(R.drawable.rbutton6);
                l2.setVisibility(View.GONE);
                l1.setVisibility(View.VISIBLE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                header.setBackgroundResource(R.drawable.rbutton4);
                payment.setBackgroundResource(R.drawable.rbutton4);

                im1.setBackgroundResource(R.drawable.detail2);
                im2.setBackgroundResource(R.drawable.summary);
                im3.setBackgroundResource(R.drawable.payment);
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);
                txtheader.setText("Details");
            }
        });

        rsummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cr = dbs.rawQuery("select * from productlist2 ",null);
                try{
                    if(cr.getCount() > 0){
                        button=0;
                        badd.setVisibility(View.GONE);
                        view.setVisibility(View.GONE);
                        lsave.setVisibility(View.VISIBLE);
                        header.setBackgroundResource(R.drawable.rbutton6);
                        l2.setVisibility(View.VISIBLE);
                        l1.setVisibility(View.GONE);
                        l3.setVisibility(View.GONE);
                        l4.setVisibility(View.GONE);
                        detail.setBackgroundResource(R.drawable.rbutton4);
                        payment.setBackgroundResource(R.drawable.rbutton4);
                        im1.setBackgroundResource(R.drawable.details);
                        im2.setBackgroundResource(R.drawable.summary2);
                        im3.setBackgroundResource(R.drawable.payment);
                        v1.setVisibility(View.GONE);
                        v2.setVisibility(View.VISIBLE);
                        v3.setVisibility(View.GONE);
                        txtheader.setText("Summary");
                    }
                }catch (Exception er){
                    Log.e("error",er.toString());
                }finally {
                    cr.close();
                }
            }
        });
        rpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button = 1;
                badd.setVisibility(View.GONE);
                view.setVisibility(View.GONE);
                lsave.setVisibility(View.VISIBLE);

                im1.setBackgroundResource(R.drawable.details);
                im2.setBackgroundResource(R.drawable.summary);
                im3.setBackgroundResource(R.drawable.payment2);
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.VISIBLE);
                txtheader.setText("Payment");


                // Spinner click listener

                Cursor cr  =  dbs.rawQuery("select out_master_code as code, out_master_description from masterl where out_parent_code = 'QCD_AEPS_PAYMENT_MODE' and out_status_code = 'A' ",null);
                try{
                   categoriescode.clear();
                   categoriespay.clear();
                    categoriespay.add("Select Payment Mode");
                    categoriescode.add("");
                    if(cr.getCount() > 0){
                      if(cr.moveToFirst()){
                          do{
                              categoriescode.add(cr.getString(cr.getColumnIndexOrThrow("code")));
                              categoriespay.add(cr.getString(cr.getColumnIndexOrThrow("out_master_description")));
                          }while (cr.moveToNext());
                      }
                    }
                }catch (Exception er){
                    Log.e("error",Log.getStackTraceString(er));
                }finally {
                    cr.close();
                }
                // Spinner Drop down elements
              /*  List<String> categories = new ArrayList<String>();

                categories.add("CASH");
                categories.add("CHEQUE");
                categories.add("CARD");*/


                // Creating adapter for spinner
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Bookinvoicenew.this, android.R.layout.simple_spinner_item, categoriespay);

                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner
                spinner.setAdapter(dataAdapter);
                payment.setBackgroundResource(R.drawable.rbutton6);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l1.setVisibility(View.GONE);
                l4.setVisibility(View.VISIBLE);
                eamtpaid.requestFocus();
                header.setBackgroundResource(R.drawable.rbutton4);
                detail.setBackgroundResource(R.drawable.rbutton4);

                recyclerViewp = findViewById(R.id.ptm);
                recyclerViewp.setLayoutManager(new LinearLayoutManager(Bookinvoicenew.this));
                adapterp = new Bookinvoicenew.MyRecyclerViewAdapterp(Bookinvoicenew.this, invoicelist);
                invoicelist.clear();


//                String selectQuery = "SELECT  * FROM invoice";
//
//                Cursor cursor = dbs.rawQuery(selectQuery, null);
//
//                // looping through all rows and adding to list
//                if (cursor.moveToFirst()) {
//                    do {
//
//                        pojoPayment pojoPayment = new pojoPayment();
//
//                        String id = cursor.getString(0);
//
//                        pojoPayment.setId(cursor.getString(0));
//
//                        pojoPayment.setInno(cursor.getString(1));
//
//                        Cursor cursori = dbs.query("invoicelist", new String[]{"inweb",
//                                }, "invoiceno" + "=?",
//                                new String[]{cursor.getString(1)}, null, null, null, null);
//                        if (cursori.moveToFirst()) {
//                            do {
//                                if(cursori.getString(0).equalsIgnoreCase(""))
//                                {
//                                    pojoPayment.setInno2("");
//                                }else {
//                                    // idn = cursor2.getString(2);
//                                    //Toast.makeText(Bookinvoicenew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();
//
//                                    pojoPayment.setInno2(cursori.getString(0));
//                                }
//
//
//                            }
//                            while (cursori.moveToNext());
//
//
//                        }
//
//                       // pojoPayment.setBal(""+einwamt.getText().toString());
//                       // pojoPayment.setAmt(""+einwamt.getText().toString());
//                       // pojoPayment.setRefno(cursor.getString(5));
//                       // pojoPayment.setCname(cursor.getString(7));
//
//
//
//
//                        Log.e("Check",""+cursor.getString(7));
//
//                        invoicelist.add(pojoPayment);
//                        // array2.add(cursor.getString(11));
//                        // Log.e("VAL",""+cursor.getString(11));
//                       ///recyclerViewp.setAdapter(adapterp);
//                    } while (cursor.moveToNext());
//
//                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l4.setVisibility(View.GONE);
                l3.setVisibility(View.VISIBLE);
            }
        });
        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
               // Toast.makeText(Bookinvoicenew.this, "Available", Toast.LENGTH_SHORT).show();
                // network available
            }

            @Override
            public void onLost(Network network) {

                try {

                    dialogpayment.dismiss();
                    salestatus = 9;
                    db.paylist(itext, "PAYTM", eamtpaid.getText().toString(), orderID, epdate.getText().toString(), "", "0", "1", "0", "2", "Pending");

                    final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                            .setIcon(android.R.drawable.presence_offline)
//set title
                            .setTitle("Network Error!")
//set message
                            .setMessage("Check Your Internet Connection")
//set positive button
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            //set what would happen when positive button is clicked
                                            finish();

                                        }
                                    }
//set negative button

                            )

                            .setCancelable(false)
                            .show();
                }

                catch (Exception e)
                {

                }
               // Toast.makeText(Bookinvoicenew.this, "Not Available", Toast.LENGTH_SHORT).show();

                // network unavailable
            }
        };

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback);
        } else {
            NetworkRequest request = new NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build();
            connectivityManager.registerNetworkCallback(request, networkCallback);
        }

    }

    private void getorderid_aeps() {
        try {

            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", sharedpreferences.getString("oc1",""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc",""));
            user.put("localeId", "en_US");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();


            user2.put("In_orgn_code",sharedpreferences.getString("oc1",""));
            user2.put("In_invoice_no",invoiceno.getText().toString());
            user2.put("IOU_aepso_rowid",0);
            String[] d = epdate.getText().toString().split("/");
            user2.put("In_aepso_date",d[2]+"-"+d[1]+"-"+d[0]);
            user2.put("In_channel","M");
            user2.put("In_invoice_amount",eamtpaid.getText().toString());
            user2.put("In_balance_amount",ebal.getText().toString());
            user2.put("In_status_code","A");
            user2.put("In_mode_flag","I");


            user.put("Header",user2);


            Log.e("OUTPUTODERID",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUTORDERID",""+Log.getStackTraceString(e));
            Log.e("OUTPUT",""+jsonParam.toString());
        }


        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "api/ICDMOB_AEPS/OrderIdGenerationAEPS", jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("Header");
                            orderID ="";
                            orderID = obj2.getString("In_AEPSO_Orderid");
                            Log.e("orderID", orderID);
                            erefno.setText(orderID);
                            if(!orderID.isEmpty()){
                                Intent intent   = new Intent(Bookinvoicenew.this, Host.class);
                                //intent.putExtra("username","R005155");

                                intent.putExtra("username", aeps_merchantId);
                                intent.putExtra("amount",eamtpaid.getText().toString() );
                                intent.putExtra("txntype", "1");
                                intent.putExtra("clienttxnid","MEITY"+ts);


                                startActivityForResult(intent,1000);
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
                4500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Bookinvoicenew.this).addToRequestQueue(stringRequest);


    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edate.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabel2() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        if(sdf.format(myCalendar.getTime()).toString().compareTo(edate.getText().toString())>=0) {

            epdate.setText(sdf.format(myCalendar.getTime()));
        }
        else {
            epdate.setText("");
            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                    .setTitle("Info!")
//set message
                    .setMessage("Invalid Date!")
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

    @Override
    public void onStatusUpdated(ConnectionState connectionState) {
        Toast.makeText(this, "Success :"+connectionState, Toast.LENGTH_SHORT).show();
        if(connectionState.toString().equalsIgnoreCase("CONNECTED"))
        {
            lsave.setEnabled(true);
            sconnecton.setBackgroundResource(R.drawable.eblue);
            lsave.setBackgroundResource(R.drawable.rbutton3);
        }
        else
        {
            sconnecton.setBackgroundResource(R.drawable.grayblue);
            lsave.setEnabled(false);
            lsave.setBackgroundResource(R.drawable.rbutton);
        }
    }

    @Override
    public void onError(ConnectionError connectionError) {
        Toast.makeText(this, "Error :"+connectionError, Toast.LENGTH_SHORT).show();

    }
// Response{status=SUCCESS, data=StatusCheckResponse{merchantId='null', orderId='M15072022NKPCL0000003', txnId='20220715111212800110168414303874558', authCode='000541', txnDate='15 Jul 2022, 10:52:15 AM', rrn='000011958444', cardNo='541919******9568', issuingBank='HDFC Bank', amount='100', txnType='CARD', invoiceNumber='000078', extendInfo='', printInfo='', tid='70000584', aid='A0000000041010', payMethod='DEBIT_CARD', cardType='DEBIT_CARD', cardScheme='MASTER', bankResponseCode='00', bankMid='5PT000070033009', bankTid='5P700584', productManufacturer='', productCategory='', productSerialNoType='', productSerialNoValue='', productName='', emiTxnType='', emiTenure='', emiInterestRate='', emiMonthlyAmount='', emiTotalAmount='', bankOfferApplied='false', bankOfferType='', bankOfferAmount='', subventionCreated='false', subventionType='', subventionOfferAmount='', acquiringBank='Paytm Payments Bank', virtualPaymentAddress='', statusCode='101', statusMessage='Transaction was successful'}, error=null, requestId='1657862526185'}
    @Override
    public void onResponse(com.paytm.ecr.bluetooth.sdk.model.Response<? extends BaseResponse> response) {
      //  reqapi.setVisibility(View.VISIBLE);

        try {

            if (salestatus == 0) {
                try {

                    salestatus = 1;
                    Log.e("SALE API", "" + response);
                    // saleresponse = response.toString();
                    String[] res = response.toString().split(",");
                    Log.e("TXTid", "" + res[3]);

                    String[] tx = res[3].split("=");
                    String[] scode = res[41].split("=");
                    erefno.setText(orderID);

                    if (scode[1].substring(1, scode[1].length() - 1).equalsIgnoreCase("101")) {
                        // savep();
                        StatusCheckRequest.Builder builder = new StatusCheckRequest.Builder()
                                .setMerchantId((TextUtils.isEmpty(merchantId) ? null : merchantId))
                                .setOrderId(orderID);
                        // .setPrintInfo("printInfo://values?merchantTxnId=82938938983&caNumber=34567777&billNumber=xyz123")
                        //  .setGstInformation("gstInformation://values?gstIn=08TESTF0078P1ZP&gstBrkUp=CGST:10|SGST:10|IGST:10|CESS:10|GSTIncentive:10|GSTPCT:10&invoiceNo="+invoiceno.getText().toString()+"&invoiceDate=20220720142919")


                        StatusCheckRequest statusCheckRequest = builder.build();
                        String statusCheckRequestRequestId = String.valueOf(System.currentTimeMillis());
                        com.paytm.ecr.bluetooth.sdk.model.Request<StatusCheckRequest> request = com.paytm.ecr.bluetooth.sdk.model.Request.of(statusCheckRequest, statusCheckRequestRequestId);
                        payments.doStatusCheck(request, Bookinvoicenew.this);

//                 final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
////set icon
//                         .setIcon(android.R.drawable.ic_menu_save)
////set title
//                         .setTitle("INFO!")
////set message
//                         .setMessage("{" + res[42])
////set positive button
//                         .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                     @Override
//                                     public void onClick(DialogInterface dialogInterface, int i) {
//                                         //set what would happen when positive button is clicked
//                                         finish();
//
//                                     }
//                                 }
////set negative button
////set negative button
//
//                         )
//                         .show();
                    } else {
                        salestatus=15;

                        StatusCheckRequest.Builder builder = new StatusCheckRequest.Builder()
                                .setMerchantId((TextUtils.isEmpty(merchantId) ? null : merchantId))
                                .setOrderId(orderID);
                        // .setPrintInfo("printInfo://values?merchantTxnId=82938938983&caNumber=34567777&billNumber=xyz123")
                        //  .setGstInformation("gstInformation://values?gstIn=08TESTF0078P1ZP&gstBrkUp=CGST:10|SGST:10|IGST:10|CESS:10|GSTIncentive:10|GSTPCT:10&invoiceNo="+invoiceno.getText().toString()+"&invoiceDate=20220720142919")


                        StatusCheckRequest statusCheckRequest = builder.build();
                        String statusCheckRequestRequestId = String.valueOf(System.currentTimeMillis());
                        com.paytm.ecr.bluetooth.sdk.model.Request<StatusCheckRequest> request = com.paytm.ecr.bluetooth.sdk.model.Request.of(statusCheckRequest, statusCheckRequestRequestId);
                        payments.doStatusCheck(request, Bookinvoicenew.this);

                    }
                } catch (Exception e) {

                    dialogpayment.dismiss();


                    ;
                    final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                            .setTitle("INFO!")
//set message
                            .setMessage("Payment Not Completed Please Try Again")
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
                            .show();
                }


            } else if (salestatus == 3) {
                salestatus = 4;
                final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                        .setIcon(android.R.drawable.ic_menu_save)
//set title
                        .setTitle("INFO!")
//set message
                        .setMessage("Transaction Cancelled")
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
                        .show();
            }
            else if(salestatus == 15)
            {
                String[] res = response.toString().split(",");
                String[] emsg = res[42].split("=");
                saleresponse = response.toString();
                saveStatus("2");
                db.paylist(itext, "PAYTM", eamtpaid.getText().toString(), orderID, epdate.getText().toString(), "", "0", "1", "0", "2", "" + emsg[1].substring(1, emsg[1].length() - 1));
                dialogpayment.dismiss();
                final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                        .setIcon(android.R.drawable.ic_menu_save)
//set title
                        .setTitle("INFO!")
//set message
                        .setMessage(emsg[1].substring(1, emsg[1].length() - 1))
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
                        .show();
            } else if(salestatus == 20){

                Log.e("SALE API3", "" + response.toString());

                String[] res = response.toString().split(",");

                String[] status = res[0].split("=");

                String[] statuses = status[1].split("=");
                String val = statuses[0];

                if(val.equalsIgnoreCase("SUCCESS")){

                    paytmbluetooth = "1";
                    Toast.makeText(Bookinvoicenew.this,val, Toast.LENGTH_SHORT).show();

                }else if(val.equalsIgnoreCase("ERROR")){
                    paytmbluetooth = "0";

                    String[] scode = res[2].split("=");

                    String[] emsg = scode[1].split("=");
                    saleresponse = response.toString();

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(Bookinvoicenew.this, emsg[0], Toast.LENGTH_SHORT).show();

                        }
                    });


                }


            }
            else {
                successstatus = "0";
                Log.e("SALE API2", "" + response.toString());
                saleresponse = response.toString();
                savep();
            }
        }
        catch (Exception e)
        {

        }


    }





    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<Bookinvoicenew.MyRecyclerViewAdapter.ViewHolder> {

        private List<pojoProduct> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<pojoProduct> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public Bookinvoicenew.MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewitemlist, parent, false);
            return new Bookinvoicenew.MyRecyclerViewAdapter.ViewHolder(view);
        }


        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(Bookinvoicenew.MyRecyclerViewAdapter.ViewHolder holder, final int position) {
            final pojoProduct pojoProduct = mData.get(position);
            holder.myTextView.setText(pojoProduct.getItem());
            holder.tamt.setText(pojoProduct.getAmt());
            holder.tnamt.setText(pojoProduct.getNamt());
            holder.tqty.setText(pojoProduct.getQty());
            holder.trate.setText(pojoProduct.getRate());
            holder.tdis.setText(pojoProduct.getDis());
            if(btnn.equalsIgnoreCase("1"))
            {
                holder.del.setVisibility(View.GONE);
                holder.ed.setVisibility(View.GONE);
            }

            holder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    db.deleteProductlist2(Integer.valueOf(pojoProduct.getId()));
                    productlist.remove(position);
                    adapter.notifyDataSetChanged();
                    counttotal();
                }
            });
            holder.ed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setflag = 1;
                    dialog.dismiss();
                    badd.setText("Update");
                    tspr = pojoProduct.getTspr();
                    fetchsn();
                    item.setText(pojoProduct.getItem());
                    item.setEnabled(false);
                    item.dismissDropDown();
                    erate.setText(pojoProduct.getRate());
                    equantity.setText(pojoProduct.getQty());
                    eamount.setText(pojoProduct.getAmt());
                    enetamount.setText(pojoProduct.getNamt());
                    ediscount.setText(pojoProduct.getDis());
                    prid = pojoProduct.getId();



                }
            });
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder  {
            TextView myTextView,trate,tamt,tnamt,tdis,tqty;
            ImageView del,ed;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.t1);
                tamt = itemView.findViewById(R.id.amt);
                tnamt = itemView.findViewById(R.id.namt);
                tdis = itemView.findViewById(R.id.dis);
                trate = itemView.findViewById(R.id.rate);
                tqty = itemView.findViewById(R.id.qty);

                del = (ImageView)itemView.findViewById(R.id.del);
                ed = (ImageView)itemView.findViewById(R.id.ed);



            }


        }

        // convenience method for getting data at click position




        // parent activity will implement this method to respond to click events

    }


    public class MyRecyclerViewAdapter2 extends RecyclerView.Adapter<Bookinvoicenew.MyRecyclerViewAdapter2.ViewHolder> {

        private List<pojoProducttax> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapter2(Context context, List<pojoProducttax> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public Bookinvoicenew.MyRecyclerViewAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewtax, parent, false);
            return new Bookinvoicenew.MyRecyclerViewAdapter2.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(Bookinvoicenew.MyRecyclerViewAdapter2.ViewHolder holder, int position) {
            pojoProducttax pojoProducttax = mData.get(position);
            if(ss.equalsIgnoreCase(""))
            {

            }
            else if(ss.equalsIgnoreCase("0"))
            {
                // holder.lcg.setVisibility(View.VISIBLE);
                // holder.lsg.setVisibility(View.VISIBLE);
                // holder.ltax.setVisibility(View.GONE);
                holder.myTextView.setText(pojoProducttax.getItem());
                holder.cgst.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
                holder.sgst.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
                holder.igst.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
                try {
                    String[] vl = pojoProducttax.getHsn().split("_");
                    holder.hsn.setText(vl[2]);
                }
                catch (Exception e)
                {
                    holder.hsn.setText(pojoProducttax.getHsn());
                }
                holder.cgst.setText(pojoProducttax.getCgst());
                holder.sgst.setText(pojoProducttax.getSgst());
                holder.igst.setText("0");

                double c2 = Double.parseDouble(holder.cgst.getText().toString());
                double ca = Double.parseDouble(pojoProducttax.getNamt())/100;
                double ca2 = c2*ca;
                holder.cgst2.setText(""+(""+String.format("%.2f", ca2)));
                double s2 = Double.parseDouble(holder.sgst.getText().toString());
                double sa = Double.parseDouble(pojoProducttax.getNamt())/100;
                double sa2 = s2*sa;
                holder.sgst2.setText(""+(""+String.format("%.2f", sa2)));

                double c = Double.parseDouble(holder.cgst.getText().toString());
                double s = Double.parseDouble(holder.sgst.getText().toString());
                double rl = Double.parseDouble(pojoProducttax.getNamt());

                double cg = (c/100) * rl;
                double sg = (s/100) * rl;
                double f = cg+sg;
                double ff = c+s;
                DecimalFormat amountFormate  = new DecimalFormat("############.##");
                amountFormate.setMinimumFractionDigits(2);
                amountFormate.setMaximumFractionDigits(2);

                holder.tamt.setText(""+(""+String.format("%.2f", f)));
                holder.tamt1.setText("" + (""+String.format("%.2f", rl)));

            }
            else if(ss.equalsIgnoreCase("1"))
            {
                if(pojoProducttax.getIgst().equalsIgnoreCase("0.0000")) {
                    holder.myTextView.setText(pojoProducttax.getItem());
                    holder.cgst.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
                    holder.sgst.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
                    holder.igst.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});

                    try {
                        String[] vl = pojoProducttax.getHsn().split("_");
                        holder.hsn.setText(vl[2]);
                    }
                    catch (Exception e)
                    {
                        holder.hsn.setText(pojoProducttax.getHsn());
                    }

                    holder.cgst.setText(pojoProducttax.getCgst());
                    holder.sgst.setText(pojoProducttax.getSgst());
                    holder.igst.setText(pojoProducttax.getIgst());
                    double c = Double.parseDouble(holder.cgst.getText().toString());
                    double s = Double.parseDouble(holder.sgst.getText().toString());
                    double i = Double.parseDouble(holder.igst.getText().toString());
                    double rl = Double.parseDouble(pojoProducttax.getNamt());

                    double cg = (c / 100) * rl;
                    double sg = (s / 100) * rl;
                    double f = 0;
                    double ff = c+s;
                    DecimalFormat amountFormate = new DecimalFormat("############.##");
                    amountFormate.setMinimumFractionDigits(2);
                    amountFormate.setMaximumFractionDigits(2);

                    holder.tamt.setText("" + (""+String.format("%.2f", f)));
                    holder.tamt1.setText("" + (""+String.format("%.2f", rl)));
                }
                else
                {

                    //  holder.lcg.setVisibility(View.GONE);
                    // holder.lsg.setVisibility(View.GONE);
                    holder.myTextView.setText(pojoProducttax.getItem());
                    holder.cgst.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
                    holder.sgst.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
                    holder.igst.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
                    try {
                        String[] vl = pojoProducttax.getHsn().split("_");
                        holder.hsn.setText(vl[2]);
                    }
                    catch (Exception e)
                    {
                        holder.hsn.setText(pojoProducttax.getHsn());
                    }
                    holder.cgst.setText("0");
                    holder.sgst.setText("0");
                    holder.igst.setText(pojoProducttax.getIgst());
                    double c = Double.parseDouble(holder.cgst.getText().toString());
                    double s = Double.parseDouble(holder.sgst.getText().toString());
                    double i = Double.parseDouble(holder.igst.getText().toString());

                    double sa = Double.parseDouble(pojoProducttax.getNamt())/100;
                    double sa2 = i*sa;
                    holder.igst2.setText(""+(""+String.format("%.2f", sa2)));

                    double rl = Double.parseDouble(pojoProducttax.getNamt());

                    double cg = (c / 100) * rl;
                    double sg = (s / 100) * rl;
                    double ig = (i / 100) * rl;
                    double f = ig;
                    double ff = i;
                    DecimalFormat amountFormate = new DecimalFormat("############.##");
                    amountFormate.setMinimumFractionDigits(2);
                    amountFormate.setMaximumFractionDigits(2);

                    holder.tamt.setText("" + (""+String.format("%.2f", f)));
                    holder.tamt1.setText("" + (""+String.format("%.2f", rl)));
                }
            }
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder  {
            TextView myTextView,hsn,tamt,cgst,sgst,igst,tamt1,cgst2,sgst2,igst2;
            LinearLayout ltax,lcg,lsg;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.t1);
                hsn = itemView.findViewById(R.id.hsn);
                tamt = itemView.findViewById(R.id.tamt);
                tamt1 = itemView.findViewById(R.id.tamt2);
                cgst = itemView.findViewById(R.id.cgst);
                sgst = itemView.findViewById(R.id.sgst);
                igst = itemView.findViewById(R.id.igst);
                cgst2 = itemView.findViewById(R.id.cgsta);
                sgst2 = itemView.findViewById(R.id.sgsta);
                igst2 = itemView.findViewById(R.id.igsta);
                ltax = itemView.findViewById(R.id.ltax);
                lcg = itemView.findViewById(R.id.lcg);
                lsg = itemView.findViewById(R.id.lsg);

            }


        }

        // convenience method for getting data at click position


        // allows clicks events to be caught


        // parent activity will implement this method to respond to click events

    }
    public void  getAmt()
    {
        if(erate.getText().toString().equalsIgnoreCase("")   )
        {

        }
        else {


            /// double n = Double.parseDouble(cq);
            double x = Double.parseDouble(equantity.getText().toString());
            double xx = Double.parseDouble(cq);
            if(x>xx)
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Currently "+xx+" Stocks are Available")
//set positive button
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        bq=0;
                                        //set what would happen when positive button is clicked
                                        // finish();
                                    }
                                }
//set negative button

                        )
                        .show();
                equantity.setText("");
            }
            else {
                double y = Double.parseDouble(erate.getText().toString());


                eamount.setText(""+String.format("%.2f",((float) ((x * y)))));
                enetamount.setText(""+String.format("%.2f",(float) ((x * y))));
            }
        }
    }

    public void  getAmt2() {
        if (equantity.getText().toString().equalsIgnoreCase("") ) {

        } else {

            if(ediscount.getText().toString().equalsIgnoreCase("")) {

                double x = Double.parseDouble(equantity.getText().toString());
                double y = Double.parseDouble(erate.getText().toString());


                DecimalFormat amountFormate = new DecimalFormat("############.##");
                amountFormate.setMinimumFractionDigits(2);
                amountFormate.setMaximumFractionDigits(2);

                eamount.setText("" +String.format("%.2f", ((float) ((x * y)))));
                enetamount.setText("" +String.format("%.2f", ((float) ((x * y)))));
            }
            else
            {
                double x = Double.parseDouble(equantity.getText().toString());
                double y = Double.parseDouble(erate.getText().toString());
                double z = Double.parseDouble(ediscount.getText().toString());


                DecimalFormat amountFormate = new DecimalFormat("############.##");
                amountFormate.setMinimumFractionDigits(2);
                amountFormate.setMaximumFractionDigits(2);

                eamount.setText("" + String.format("%.2f",(float) ((x * y))));
                enetamount.setText("" +String.format("%.2f", ((float) ((x * y)-z))));
            }
        }
    }

    public void  getAmt3() {
        if (equantity.getText().toString().equalsIgnoreCase("") || erate.getText().toString().equalsIgnoreCase("")) {

        } else {

            double x = Double.parseDouble(equantity.getText().toString());
            double y = Double.parseDouble(erate.getText().toString());
            double z = Double.parseDouble(ediscount.getText().toString());
            double za = Double.parseDouble(eamount.getText().toString());



            double zf = (z/100)*za;
           if(z>(x*y))
           {
               ediscount.setText("");
               final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                       .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                       .setTitle("Error!")
//set message
                       .setMessage("Discount Amount Can't Be Greater Than Actual Amount")
//set positive button
                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialogInterface, int i) {
                                       //set what would happen when positive button is clicked
                                       item.requestFocus();
                                   }
                               }
//set negative button

                       )
                       .show();
           }
           else {

               enetamount.setText("" + String.format("%.2f", (float) ((x * y) - z)));
           }
        }
    }
    public void  getAmtt() {
        try{
            double trans = 0.0;
            if(!etransport.getText().toString().trim().equalsIgnoreCase("") && etransport.getText().toString().length() > 0 && !etransport.getText().toString().startsWith(".")){
             trans = Double.parseDouble(etransport.getText().toString());
            }
            if (eothers.getText().toString().equalsIgnoreCase("") ) {
                double x = trans;
                double t = Double.parseDouble(etotal.getText().toString());
                double ta = 0;
                if(etax.getText().toString().equalsIgnoreCase(""))
                {
                    ta = 0;

                }
                else
                {
                    ta = Double.parseDouble(etax.getText().toString());

                }

                double fi = x +t +ta;

                DecimalFormat amountFormate  = new DecimalFormat("############.##");
                amountFormate.setMinimumFractionDigits(2);
                amountFormate.setMaximumFractionDigits(2);
                einwamt.setText(""+(""+String.format("%.2f", fi)));
            } else {

                double x = trans;
                double y = Double.parseDouble(eothers.getText().toString());
                double t = Double.parseDouble(etotal.getText().toString());
                double ta = 0;
                if(etax.getText().toString().equalsIgnoreCase(""))
                {
                    ta = 0;

                }
                else
                {
                    ta = Double.parseDouble(etax.getText().toString());

                }

                double fi = x +y +t +ta;

                DecimalFormat amountFormate  = new DecimalFormat("############.##");
                amountFormate.setMinimumFractionDigits(2);
                amountFormate.setMaximumFractionDigits(2);
                einwamt.setText(""+(""+String.format("%.2f", fi)));
            }
        }catch (Exception er){
            Log.e("error",Log.getStackTraceString(er));
        }
    }

    public void  getAmto() {
        try{
            double other = 0.0;
            if(!eothers.getText().toString().trim().equalsIgnoreCase("") && eothers.getText().toString().trim().length() > 0 ){
                other = Double.parseDouble(eothers.getText().toString());
            }
            if (etransport.getText().toString().equalsIgnoreCase("") ) {

                double y = other;
                double t = Double.parseDouble(etotal.getText().toString());
                double ta = 0;
                if(etax.getText().toString().equalsIgnoreCase(""))
                {
                    ta =0;

                }
                else
                {
                    ta = Double.parseDouble(etax.getText().toString());

                }

                double fi = y +t +ta;

                DecimalFormat amountFormate  = new DecimalFormat("############.##");
                amountFormate.setMinimumFractionDigits(2);
                amountFormate.setMaximumFractionDigits(2);
                einwamt.setText(""+(""+String.format("%.2f", fi)));

            } else {

                double x = Double.parseDouble(etransport.getText().toString());
                double y = other;
                double t = Double.parseDouble(etotal.getText().toString());
                double ta = 0;
                if(etax.getText().toString().equalsIgnoreCase(""))
                {
                    ta = 0;

                }
                else
                {
                    ta = Double.parseDouble(etax.getText().toString());

                }

                double fi = x +y +t +ta;
                DecimalFormat amountFormate  = new DecimalFormat("############.##");
                amountFormate.setMinimumFractionDigits(2);
                amountFormate.setMaximumFractionDigits(2);
                einwamt.setText(""+(""+String.format("%.2f", fi)));
            }
        }catch (Exception e){
            Log.e("error",Log.getStackTraceString(e));
        }
    }

    public class MyRecyclerViewAdapter3 extends RecyclerView.Adapter<MyRecyclerViewAdapter3.ViewHolder> {

        private List<pojopaytax> mData;
        private LayoutInflater mInflater;

        // data is passed into the constructor
        MyRecyclerViewAdapter3(Context context, List<pojopaytax> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapter3.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewpayment, parent, false);
            return new MyRecyclerViewAdapter3.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(MyRecyclerViewAdapter3.ViewHolder holder, int position) {
            pojopaytax pojopaytax = mData.get(position);
            holder.myTextView.setText(pojopaytax.getPaymode());
            holder.rn.setText(pojopaytax.getRefno());
            holder.dt.setText(pojopaytax.getDate());
            holder.a.setText(pojopaytax.getAmt());

        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder  {
            TextView myTextView,rn,dt,a;
            RelativeLayout rpay;


            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.pm);
                rn = itemView.findViewById(R.id.rn);
                dt = itemView.findViewById(R.id.dt);
                a = itemView.findViewById(R.id.a);

            }


        }

        // convenience method for getting data at click position

        // allows clicks events to be caught


        // parent activity will implement this method to respond to click events

    }


    public class MyRecyclerViewAdapterp extends RecyclerView.Adapter<MyRecyclerViewAdapterp.ViewHolder> implements Filterable {

        private List<pojoPayment> mData;
        private List<pojoPayment> mDatafilter;
        private LayoutInflater mInflater;

        // data is passed into the constructor
        MyRecyclerViewAdapterp(Context context, List<pojoPayment> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
            this.mDatafilter = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapterp.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewpaylist2, parent, false);
            return new MyRecyclerViewAdapterp.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(MyRecyclerViewAdapterp.ViewHolder holder, int position) {
            final pojoPayment pojoPayment = mDatafilter.get(position);
            holder.myTextView.setText(pojoPayment.getInno2());
            holder.bal.setText(""+Float.parseFloat(pojoPayment.getBal()));
            holder.amt.setText(""+Float.parseFloat(pojoPayment.getAmt()));
            double x = Double.parseDouble(pojoPayment.getAmt());
            double y = Double.parseDouble(pojoPayment.getBal());

            double z = x - y ;
            Log.e("VAL",""+z);
            String f = String.valueOf(z).replaceAll("-","");

            holder.paid.setText(""+pojoPayment.getCname());
            holder.rpay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    l3.setVisibility(View.GONE);
                    l4.setVisibility(View.VISIBLE);
                    itext = pojoPayment.getInno();
                    lsave.setVisibility(View.VISIBLE);

                    invoiceno.setText(pojoPayment.getInno2());
                    amount.setText(pojoPayment.getAmt());
                    balance.setText(""+Float.parseFloat(pojoPayment.getBal()));
                    double x = Double.parseDouble(pojoPayment.getAmt());
                    double y = Double.parseDouble(pojoPayment.getBal());
                    pyid = pojoPayment.getId();
                    cname = pojoPayment.getCname();
                    innob = pojoPayment.getInno();
                    am = pojoPayment.getAmt();
                    final DecimalFormat amountFormate  = new DecimalFormat("############.##");
                    amountFormate.setMinimumFractionDigits(2);
                    amountFormate.setMaximumFractionDigits(2);
                    double z = x - y ;
                    Log.e("VAL",""+z);
                    String f = String.valueOf(amountFormate.format(z)).replaceAll("-","");
//
                    paid.setText(""+Float.parseFloat(f));
                    final SQLiteDatabase dbs = db.getWritableDatabase();
                    if(isNetworkAvailable()) {




                        dbs.execSQL("delete from paylist where fstatus != 2");
                        Cursor cursor2 = dbs.query("invoicelist", new String[]{"sid",
                                }, "invoiceno" + "=?",
                                new String[]{pojoPayment.getInno()}, null, null, null, null);

                        if (cursor2.moveToFirst()) {
                            do {

                                Log.e("CKK", "" + cursor2.getString(0));
                                payid = cursor2.getString(0);
                                // Toast.makeText(Bookinvoicenew.this, "id="+cursor2.getString(0), Toast.LENGTH_SHORT).show();
                                final String url = Pojokyc.icdurl+"api/ICDMOBProduct/paymentcollection?org=NKPCL/00001&locn="+Pojokyc.instance+"&user=admin&lang=en_US&invoice_rowid=" + payid;

// prepare the Request
                                HttpsTrustManager.allowAllSSL();
                                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                // display response
                                                try {
                                                    SQLiteDatabase dbs = db.getWritableDatabase();

                                                    JSONObject obj = response.getJSONObject("context");
                                                    JSONObject obj2 = obj.getJSONObject("Header");

                                                    payno = obj2.getString("In_invoice_no");
                                                    paydate = obj2.getString("In_invoice_date");
                                                    payamt = obj2.getString("In_invoice_amount");
                                                    String bl = obj2.getString("In_balance_amount");
                                                    amount.setText("" + Float.parseFloat(bl));

                                                    double x = Double.parseDouble(payamt);
                                                    double y = Double.parseDouble(bl);
                                                    double z = x - y;
                                                    String f = String.valueOf(amountFormate.format(z)).replaceAll("-", "");
                                                    paid.setText("" + Float.parseFloat(f));
                                                    JSONArray cast = obj.getJSONArray("Detail");
                                                    for (int i = 0; i < cast.length(); i++) {


                                                        JSONObject actor = cast.getJSONObject(i);
                                                        String[] d = actor.getString("In_payment_date").split(" ");
                                                        // Log.e("CKK",""+d[0]+"//"+invoiceno.getText().toString());
                                                        String c = actor.getString("In_payment_mode_desc");
                                                        String amm = actor.getString("In_paid_amount");
                                                        String r = actor.getString("In_ref_no");
                                                        //  Log.e("CKK",""+c+"//"+amm+"//"+r);

                                                        db.paylist(itext, c, amm, r, d[0], "", "0","1","0","1","Success");

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
                                VolleySingleton.getInstance(Bookinvoicenew.this).addToRequestQueue(getRequest);


                            } while (cursor2.moveToNext());
                        }
                    }


                }
            });

        }



        // total number of rows
        @Override
        public int getItemCount() {
            return mDatafilter.size();
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {


                    Log.e("NNNNN", "I am Called");
                    String charString = charSequence.toString().toLowerCase();
                    if (charString.isEmpty()) {

                        mDatafilter = mData;
                    } else {
                        List<pojoPayment> filteredList = new ArrayList<>();
                        for (pojoPayment row : mData) {
                            Log.e("NNNNN", "I am Called"+charSequence);

                            if(row.getCname().toLowerCase().startsWith(String.valueOf(charSequence)))
                            {
                                filteredList.add(row);
                            }


                        }

                        mDatafilter = filteredList;

                        Log.e("Check",""+mDatafilter.toString());



                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mDatafilter;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    recyclerViewp.getRecycledViewPool().clear();
                    mDatafilter = (ArrayList<pojoPayment>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder  {
            TextView myTextView,amt,bal,paid;
            RelativeLayout rpay;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.itm);
                bal = itemView.findViewById(R.id.amount);

                amt = itemView.findViewById(R.id.bal);

                paid = itemView.findViewById(R.id.paid);

                rpay = itemView.findViewById(R.id.rpay);
            }


        }

        // convenience method for getting data at click position


        // allows clicks events to be caught


        // parent activity will implement this method to respond to click events

    }
    public void save2()
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
            user.put("orgnId", sharedpreferences.getString("ocnew",""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc",""));
            user.put("localeId", "en_US");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();
            String[] d = edate.getText().toString().split("/");
            user2.put("IOU_invoice_rowid","0");
            user2.put("In_orgn_code",sharedpreferences.getString("ocnew1",""));
            user2.put("In_invoice_no","0");
            user2.put("In_ic_code",sharedpreferences.getString("ocnew",""));
            user2.put("In_invoice_date",d[2]+"-"+d[1]+"-"+d[0]);
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
                final SQLiteDatabase dbs = db.getWritableDatabase();
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
                        myr = cursor2.getString(4);
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
                user4.put("In_product_amount",myr);
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


        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();

        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.icdurl+"api/ICDMOBInvoice/newsaveInvoice",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        ct22++;
                        final SQLiteDatabase dbs = db.getWritableDatabase();
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("Header");

                            String inrid = obj2.getString("IOU_invoice_rowid");
                            final String inrno = obj2.getString("In_invoice_no");
                            Log.e("CCCC","Hi"+inrid);

                            if(inrid.equalsIgnoreCase("0"))
                            {
                                dbs.execSQL("DELETE FROM invoicelist WHERE id = " + id2);
                                pdialog.dismiss();
                                //Toast.makeText(Bookinvoicenew.this, "Unable To Insert", Toast.LENGTH_SHORT).show();
                                final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
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
                                FirebaseApp.initializeApp(Bookinvoicenew.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis()/1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                Log.e("Valuecheck",""+mRef.getRef());
                                mRef.child("name").setValue("SAVEAPI");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("BOOKINVOICE");
                            }
                            else {


                                dbs.execSQL("UPDATE productlist2 SET flag = 1 WHERE flag = 0");

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


                                pdialog.dismiss();
                                eicd.setEnabled(true);
                                esl.setEnabled(false);
                                item.setEnabled(false);
                                // Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                                dbs.execSQL("UPDATE invoicelist SET flag = 1 WHERE id = " + id2);
                                dbs.execSQL("UPDATE invoicelist SET sid = " + inrid + " WHERE id = " + id2);
                                einwno.setText(""+inrno);
                                Log.e("CCCC", "Invoice");

                                try {

                                    userd = new JSONObject();
                                    userd.put("senderid", "SMSTST");
                                    userd.put("msg_desc", "Thanks for your purchase from "+sharedpreferences.getString("rn","")+" for INR "+Float.parseFloat(einwamt.getText().toString())+" against Invoice "+inrno+" dated "+edate.getText().toString());
                                    userd.put("mobile_no", "8124873354");


                                    Log.e("OUTPUT", "" + userd.toString());

                                } catch (Exception e) {
                                    Log.e("OUTPUT", "" + e.getMessage());
                                }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

                                HttpsTrustManager.allowAllSSL();
                                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl+"api/SendSMS/SendSMS", userd,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                Log.e("CCCC", "" + response);





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
                                        4500000,
                                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                                VolleySingleton.getInstance(Bookinvoicenew.this).addToRequestQueue(stringRequest);
                                button = 1;
                                badd.setVisibility(View.GONE);
                                view.setVisibility(View.GONE);
                                lsave.setVisibility(View.VISIBLE);

                                im1.setBackgroundResource(R.drawable.details);
                                im2.setBackgroundResource(R.drawable.summary);
                                im3.setBackgroundResource(R.drawable.payment2);
                                v1.setVisibility(View.GONE);
                                v2.setVisibility(View.GONE);
                                v3.setVisibility(View.VISIBLE);
                                txtheader.setText("Payment");


                                // Spinner click listener

                                Cursor cr  =  dbs.rawQuery("select out_master_code as code, out_master_description from masterl where out_parent_code = 'QCD_AEPS_PAYMENT_MODE' and out_status_code = 'A' ",null);
                                try{
                                    categoriescode.clear();
                                    categoriespay.clear();
                                    categoriespay.add("Select Payment Mode");
                                    categoriescode.add("");
                                    if(cr.getCount() > 0){
                                        if(cr.moveToFirst()){
                                            do{
                                                categoriescode.add(cr.getString(cr.getColumnIndexOrThrow("code")));
                                                categoriespay.add(cr.getString(cr.getColumnIndexOrThrow("out_master_description")));
                                            }while (cr.moveToNext());
                                        }
                                    }
                                }catch (Exception er){
                                    Log.e("error",Log.getStackTraceString(er));
                                }finally {
                                    cr.close();
                                }
                                // Spinner Drop down elements
              /*  List<String> categories = new ArrayList<String>();

                categories.add("CASH");
                categories.add("CHEQUE");
                categories.add("CARD");*/


                                // Creating adapter for spinner
                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Bookinvoicenew.this, android.R.layout.simple_spinner_item, categoriespay);

                                // Drop down layout style - list view with radio button
                                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                // attaching data adapter to spinner
                                spinner.setAdapter(dataAdapter);
                                payment.setBackgroundResource(R.drawable.rbutton6);
                                l2.setVisibility(View.GONE);
                                l3.setVisibility(View.GONE);
                                l1.setVisibility(View.GONE);
                                l4.setVisibility(View.VISIBLE);
                                eamtpaid.requestFocus();
                                header.setBackgroundResource(R.drawable.rbutton4);
                                detail.setBackgroundResource(R.drawable.rbutton4);

                                recyclerViewp = findViewById(R.id.ptm);
                                recyclerViewp.setLayoutManager(new LinearLayoutManager(Bookinvoicenew.this));
                                adapterp = new Bookinvoicenew.MyRecyclerViewAdapterp(Bookinvoicenew.this, invoicelist);
                                invoicelist.clear();
                                final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Success!")
//set message
                                        .setMessage("Invoice Inserted !" +
                                                "Invoice No :"+inrno)
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        // dbs.execSQL("UPDATE invoicelist SET inweb = " + inrno + " WHERE id = " + id2);
                                                        db.upinvoice(Integer.valueOf(id2),inrno);
                                                        // finish();
                                                        //startActivity(getIntent());
                                                        rpayment.setEnabled(true);
                                                        seten();
                                                        final SQLiteDatabase dbs = db.getWritableDatabase();
                                                        itext = inno;
                                                        innob = inno;
                                                        cname = esuppliern.getText().toString();
                                                        if(isNetworkAvailable()) {


                                                            final DecimalFormat amountFormate  = new DecimalFormat("############.##");
                                                            amountFormate.setMinimumFractionDigits(2);
                                                            amountFormate.setMaximumFractionDigits(2);

                                                            dbs.execSQL("delete from paylist where fstatus != 2");
                                                            Cursor cursor2 = dbs.query("invoicelist", new String[]{"sid","id"
                                                                    }, "invoiceno" + "=?",
                                                                    new String[]{inno}, null, null, null, null);

                                                            if (cursor2.moveToFirst()) {
                                                                do {

                                                                    Log.e("CKK", "" + cursor2.getString(0));
                                                                    payid = cursor2.getString(0);
                                                                    pyid = cursor2.getString(1);
                                                                    // Toast.makeText(Bookinvoicenew.this, "id="+cursor2.getString(0), Toast.LENGTH_SHORT).show();
                                                                    final String url = Pojokyc.icdurl+"api/ICDMOBProduct/paymentcollection?org=PKFPO&locn="+Pojokyc.instance+"&user=admin&lang=en_US&invoice_rowid=" + payid;

// prepare the Request
                                                                    HttpsTrustManager.allowAllSSL();
                                                                    JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                                                                            new Response.Listener<JSONObject>() {
                                                                                @Override
                                                                                public void onResponse(JSONObject response) {
                                                                                    // display response
                                                                                    try {
                                                                                        SQLiteDatabase dbs = db.getWritableDatabase();

                                                                                        JSONObject obj = response.getJSONObject("context");
                                                                                        JSONObject obj2 = obj.getJSONObject("Header");

                                                                                        payno = obj2.getString("In_invoice_no");
                                                                                        paydate = obj2.getString("In_invoice_date");
                                                                                        payamt = obj2.getString("In_invoice_amount");
                                                                                        String bl = obj2.getString("In_balance_amount");
                                                                                        amount.setText("" + Float.parseFloat(bl));
                                                                                        balance.setText(""+Float.parseFloat(payamt));
                                                                                        invoiceno.setText(payno);

                                                                                        double x = Double.parseDouble(payamt);
                                                                                        double y = Double.parseDouble(bl);
                                                                                        double z = x - y;
                                                                                        String f = String.valueOf(amountFormate.format(z)).replaceAll("-", "");
                                                                                        paid.setText("" + Float.parseFloat(f));
                                                                                        JSONArray cast = obj.getJSONArray("Detail");
                                                                                        for (int i = 0; i < cast.length(); i++) {


                                                                                            JSONObject actor = cast.getJSONObject(i);
                                                                                            String[] d = actor.getString("In_payment_date").split(" ");
                                                                                            // Log.e("CKK",""+d[0]+"//"+invoiceno.getText().toString());
                                                                                            String c = actor.getString("In_payment_mode_desc");
                                                                                            String amm = actor.getString("In_paid_amount");
                                                                                            String r = actor.getString("In_ref_no");
                                                                                            //  Log.e("CKK",""+c+"//"+amm+"//"+r);

                                                                                            db.paylist(itext, c, amm, r, d[0], "", "0","1","0","1","Success");

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
                                                                    VolleySingleton.getInstance(Bookinvoicenew.this).addToRequestQueue(getRequest);


                                                                } while (cursor2.moveToNext());
                                                            }
                                                        }
                                                    }
                                                }
//set negative button

                                        )
                                        .show();
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
                        final SQLiteDatabase dbs = db.getWritableDatabase();
                        dbs.execSQL("DELETE FROM invoicelist WHERE id = " + id2);
                        //Toast.makeText(getApplicationContext(),"Unable to Insert",Toast.LENGTH_SHORT).show();
                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
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
                        FirebaseApp.initializeApp(Bookinvoicenew.this);
                        FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        Long tsLong = System.currentTimeMillis()/1000;
                        String ts = tsLong.toString();
                        DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                        Log.e("Valuecheck",""+mRef.getRef());
                        mRef.child("name").setValue("SAVEAPI");
                        mRef.child("date").setValue(date);
                        mRef.child("Error").setValue(error.getMessage());
                        mRef.child("Activity").setValue("BOOKINVOICE");
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
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
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


            String[] d1 = epdate.getText().toString().split("/");
            user2.put("IOU_invoice_rowid",payid);
            user2.put("IOU_invoice_no",payno);

            user2.put("In_invoice_date",paydate);
            user2.put("In_invoice_amount",payamt);
            user2.put("In_balance_amount",ebal.getText().toString());
            user2.put("In_payment_mode",paymodecode);
            if(paymodecode.equalsIgnoreCase("QCD_AEPS_PAYTM") || paymodecode.equalsIgnoreCase("QCD_AEPS_AEPS"))
            {
                user2.put("In_ref_no", orderID);
            }
            else {
                user2.put("In_ref_no", erefno.getText().toString());
            }
            user2.put("In_payment_date",d1[2]+"/"+d1[1]+"/"+d1[0]);
            user2.put("In_payment_amount",eamtpaid.getText().toString());
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
            user4.put("In_payment_no",payno);
            user4.put("In_balance_amount",ebal.getText().toString());
            user4.put("In_payment_amount",eamtpaid.getText().toString());
            user4.put("In_payment_mode",paymodecode);
            user4.put("In_ref_no",erefno.getText().toString());
            user4.put("In_payment_date",d1[2]+"/"+d1[1]+"/"+d1[0]);
            user4.put("In_process_status","A");
            user4.put("In_paid_amount",""+eamtpaid.getText().toString());
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


        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();


        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.icdurl+"api/ICDMOBInvoice/mobnewsavePaymentCollection",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);


//                        try {
//                            JSONObject obj = response.getJSONObject("context");
//                            JSONObject obj2 = obj.getJSONObject("Header");
//
//                            String inrid = obj2.getString("IOU_invoice_rowid");
//                            Log.e("CCCC","Hi"+inrid);
//
                        pdialog.dismiss();
                        SQLiteDatabase dbs = db.getWritableDatabase();
                        dbs.execSQL("UPDATE paylist SET uflag = 1 where uflag = 0");
                        // Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
//                            dbs.execSQL("UPDATE invoicelist SET flag = 1 WHERE id = "+id2);
//                            dbs.execSQL("UPDATE invoicelist SET sid = "+inrid+" WHERE id = "+id2);
//                            Log.e("CCCC","Invoice");
                        // finish();

                        try {

                            userd = new JSONObject();
                            userd.put("senderid", "SMSTST");
                            userd.put("msg_desc", "Thanks for your purchase from "+sharedpreferences.getString("rn","")+" for INR "+Float.parseFloat(payamt)+" against Invoice "+payno+" dated "+paydate+" We have received payment of INR "+Float.parseFloat(paidamt)+" and the Balance payable is "+Float.parseFloat(paybalamt));
                            userd.put("mobile_no", "8124873354");


                            Log.e("OUTPUT", "" + userd.toString());

                        } catch (Exception e) {
                            Log.e("OUTPUT", "" + e.getMessage());
                        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();


                        HttpsTrustManager.allowAllSSL();
                        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl+"api/SendSMS/SendSMS", userd,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Log.e("CCCC", "" + response);



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
                                4500000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        VolleySingleton.getInstance(Bookinvoicenew.this).addToRequestQueue(stringRequest);

                        if(paymodecode.equalsIgnoreCase("QCD_AEPS_PAYTM")) {
                            saveStatus("1");
                        }
                        else
                        {
                            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Payment Completed!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                    //set what would happen when positive button is clicked
                                                    finish();

                                                }
                                            }
//set negative button

                                    )
                                    .setCancelable(true)
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
//                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
////set icon
//                                .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                .setTitle("Error!")
////set message
//                                .setMessage("Payment Error!")
////set positive button
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                //set what would happen when positive button is clicked
//
//                                            }
//                                        }
////set negative button
//
//                                )
//                               // .show();
                        FirebaseApp.initializeApp(Bookinvoicenew.this);
                        FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        Long tsLong = System.currentTimeMillis()/1000;
                        String ts = tsLong.toString();
                        DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                        Log.e("Valuecheck",""+mRef.getRef());
                        mRef.child("name").setValue("SAVEAPI");
                        mRef.child("date").setValue(date);
                        mRef.child("Error").setValue(error.getMessage().toString());
                        mRef.child("Activity").setValue("BOOKINVOICE");
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

    public void counttotal()
    {
        double tl,tl2=0,tc=0,tc2=0;

        SQLiteDatabase dbs = db.getWritableDatabase();
        String selectQuery2 = "SELECT  * FROM productlist2 where flag = 0";
        final Cursor cursor2 = dbs.rawQuery(selectQuery2, null);
        if(cursor2.getCount() == 0)
        {
            etotal.setText("");
            newnet.setText("");
            etax.setText("");
            einwamt.setText("");
            fi = 0;
        }
        // ecount.setText(" "+cursor2.getCount());
        mStringList.clear();
        mStringList2.clear();
        if (cursor2.moveToFirst()) {
            do {

                tl = Double.parseDouble(cursor2.getString(6));
                tl2  +=  tl ;
                tc = Double.parseDouble(cursor2.getString(2));
                tc2  +=  tc ;
                mStringList2.add(cursor2.getString(0));
                mStringList.add(cursor2.getString(0));
                mStringList.add(cursor2.getString(1));
                mStringList.add(cursor2.getString(2));
                mStringList.add(cursor2.getString(3));
                mStringList.add(cursor2.getString(4));
                mStringList.add(cursor2.getString(5));
                mStringList.add(cursor2.getString(6));
                mStringList.add(cursor2.getString(7));
                mStringList.add(cursor2.getString(8));



                etotal.setText(""+tl2);
                newnet.setText(""+String.format("%.2f",tl2));
                ecount.setText(" "+tc2);

            } while (cursor2.moveToNext());
            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString("itemlist2", mStringList.toString());
            editor.putString("ids2", mStringList2.toString());

            editor.commit();
            Log.e("List",""+sharedpreferences.getString("ids2",""));

        }

        if(suppliername.equalsIgnoreCase(""))
        {

        }
        else
        {


            Cursor cursor2n = dbs.query("customerlist", new String[]{"state_name","farmer_state_code","farmer_name","mor"
                    }, "phone" + "=?" + " and " + "farmer_name" + "=?",
                    new String[]{suppliername,sun}, null, null, null, null);


            if(cursor2n.getCount()==0)
            {
                Cursor cursor2nn = dbs.query("customerlistnm", new String[]{"state_name","farmer_state_code","farmer_name","mor"
                        }, "phone" + "=?" + " and " + "farmer_name" + "=?",
                        new String[]{suppliername,sun}, null, null, null, null);

                if (cursor2nn.moveToFirst()) {
                    do {

                        //Toast.makeText(Bookinvoicenew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();


                        //elocation.setText("" + cursor2nn.getString(0));
                        // elocation.setVisibility(View.GONE);
                        rlocation.setVisibility(View.GONE);
                        spinnerl.setVisibility(View.VISIBLE);
                        esuppliern.setText(""+cursor2nn.getString(2));
                        // esuppliern.setEnabled(false);
                        spinnerm.setSelection(2);
                        // esuppliern.setText(cursor2nn.getString(2));
                        code = cursor2nn.getString(1);
                        Log.e("LOCN",""+cursor2nn.getString(0));
                        int spinnerPosition = dataAdapterl.getPosition(cursor2nn.getString(0));
                        spinnerl.setSelection(spinnerPosition);
                        //Log.e("LOC",""+cursor2n.getString(2));
                        spinnerl.setEnabled(false);
                        lcn="1";
                        mcode = "QCD_UOM_NONMEM";

                        if(sharedpreferences.getString("lo","").equalsIgnoreCase(cursor2nn.getString(1)))
                        {
                            // Toast.makeText(Bookinvoicenew.this, "Same State", Toast.LENGTH_SHORT).show();
                            ss="0";

                        }
                        else
                        {
                            // Toast.makeText(Bookinvoicenew.this, "Out State", Toast.LENGTH_SHORT).show();
                            ss="1";

                        }

                        view3.setEnabled(true);

                        String selectQueryt = "SELECT  * FROM productlist2 where flag = 0";
                        producttaxlist.clear();
                        Cursor cursort = dbs.rawQuery(selectQueryt, null);
                        fi = 0;
                        // looping through all rows and adding to list
                        if (cursort.moveToFirst()) {
                            do {



                                String idd = cursort.getString(0);
                                String r = cursort.getString(6);
                                String ri = cursort.getString(1);



                                Cursor cursort2 = dbs.query("product", new String[]{"In_cgst",
                                                "In_sgst","In_igst","In_hsn_desc"}, "In_product_name" + "=?",
                                        new String[]{ri}, null, null, null, null);

                                if (cursort2.moveToFirst()) {
                                    //do {

                                        DecimalFormat amountFormate = new DecimalFormat("############.##");
                                        amountFormate.setMinimumFractionDigits(2);
                                        amountFormate.setMaximumFractionDigits(2);
                                        if(ss.equalsIgnoreCase("0")) {


                                            double c = Double.parseDouble(cursort2.getString(0));
                                            double s = Double.parseDouble(cursort2.getString(1));
                                            double rl = Double.parseDouble(r);

                                            double cg = (c / 100) * rl;
                                            double sg = (s / 100) * rl;
                                            double f = cg + sg;

                                            fi += f;

                                            etax.setText(""+(""+String.format("%.2f", fi)));
                                            // Toast.makeText(this, "hi"+rl, Toast.LENGTH_SHORT).show();
                                            try {
                                                double t = Double.parseDouble(etotal.getText().toString());
                                                double ta = Double.parseDouble(etax.getText().toString());

                                                double fi = t + ta;

                                                einwamt.setText(""+(""+String.format("%.2f", fi)));
                                            } catch (Exception e) {

                                            }
                                        }
                                        else
                                        {


                                            if(cursort2.getString(2).equalsIgnoreCase("0.0000")) {

                                                double c = Double.parseDouble(cursort2.getString(0));
                                                double s = Double.parseDouble(cursort2.getString(1));
                                                double rl = Double.parseDouble(r);

                                                double cg = (c / 100) * rl;
                                                double sg = (s / 100) * rl;
                                                double f = 0;

                                                fi += f;

                                                etax.setText(""+(""+String.format("%.2f", fi)));
                                                try {
                                                    double t = Double.parseDouble(etotal.getText().toString());
                                                    double ta = Double.parseDouble(etax.getText().toString());

                                                    double fi = t + ta;

                                                    einwamt.setText(""+(""+String.format("%.2f", fi)));
                                                } catch (Exception e) {

                                                }
                                            }
                                            else
                                            {

                                                double c = Double.parseDouble(cursort2.getString(0));
                                                double s = Double.parseDouble(cursort2.getString(1));
                                                double ig = Double.parseDouble(cursort2.getString(2));
                                                double rl = Double.parseDouble(r);

                                                double cg = (c / 100) * rl;
                                                double sg = (s / 100) * rl;
                                                double ig2 = (ig / 100) * rl;
                                                double f =  ig2;

                                                fi += f;

                                                etax.setText(""+(""+String.format("%.2f", fi)));
                                                try {
                                                    double t = Double.parseDouble(etotal.getText().toString());
                                                    double ta = Double.parseDouble(etax.getText().toString());

                                                    double fi = t + ta;

                                                    einwamt.setText(""+(""+String.format("%.2f", fi)));
                                                } catch (Exception e) {

                                                }
                                            }
                                        }

                                  //  }                                    while (cursort2.moveToNext());
                                }

//                Log.e("Check",""+cursor.getString(1));


                                // array2.add(cursor.getString(11));
                                // Log.e("VAL",""+cursor.getString(11));

                            } while (cursort.moveToNext());

                        }




                    }
                    while (cursor2nn.moveToNext());


                }
                if(cursor2n.getCount()==0 && cursor2nn.getCount() == 0)
                {
                    // elocation.setVisibility(View.GONE);
                    rlocation.setVisibility(View.GONE);
                    spinnerl.setVisibility(View.VISIBLE);
                    spinnerl.setEnabled(true);
                    spinnerl.setSelection(0);
                    etax.setText("");
                    ss = "";
                    lcn="1";
                    einwamt.setText("");

                    esuppliern.setText(sun);
                }
            }


            if (cursor2n.moveToFirst()) {
                do {

                    //Toast.makeText(Bookinvoicenew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();


                    if(cursor2n.getString(3).equalsIgnoreCase("M")) {
                        elocation.setText("" + cursor2n.getString(0));
                        esuppliern.setText(""+cursor2n.getString(2));
                        // esuppliern.setEnabled(false);
                        spinnerm.setSelection(1);
                        code = cursor2n.getString(1);
                        lcn ="0";
                        mcode = "QCD_CUS_MEM";
                    }
                    else
                    {
                        //  elocation.setVisibility(View.GONE);
                        rlocation.setVisibility(View.GONE);
                        spinnerl.setVisibility(View.VISIBLE);
                        esuppliern.setText(""+cursor2n.getString(2));
                        // esuppliern.setEnabled(false);
                        spinnerm.setSelection(2);
                        code = cursor2n.getString(1);
                        int spinnerPosition = dataAdapterl.getPosition(cursor2n.getString(2));
                        Log.e("LOC",""+cursor2n.getString(2));
                        spinnerl.setSelection(spinnerPosition);
                        spinnerl.setEnabled(false);
                        lcn="1";
                        mcode = "QCD_UOM_NONMEM";
                    }
                    if(sharedpreferences.getString("lo","").equalsIgnoreCase(cursor2n.getString(1)))
                    {
                        // Toast.makeText(Bookinvoicenew.this, "Same State", Toast.LENGTH_SHORT).show();
                        ss="0";

                    }
                    else
                    {
                        // Toast.makeText(Bookinvoicenew.this, "Out State", Toast.LENGTH_SHORT).show();
                        ss="1";

                    }

                    view3.setEnabled(true);

                    String selectQueryt = "SELECT  * FROM productlist2 where flag = 0";
                    producttaxlist.clear();
                    Cursor cursort = dbs.rawQuery(selectQueryt, null);
                    fi = 0;
                    // looping through all rows and adding to list
                    if (cursort.moveToFirst()) {
                        do {



                            String idd = cursort.getString(0);
                            String r = cursort.getString(6);
                            String ri = cursort.getString(1);



                            Cursor cursort2 = dbs.query("product", new String[]{"In_cgst",
                                            "In_sgst","In_igst","In_hsn_desc"}, "In_product_name" + "=?",
                                    new String[]{ri}, null, null, null, null);

                            if (cursort2.moveToFirst()) {
                               // do {

                                    DecimalFormat amountFormate = new DecimalFormat("############.##");
                                    amountFormate.setMinimumFractionDigits(2);
                                    amountFormate.setMaximumFractionDigits(2);
                                    if(ss.equalsIgnoreCase("0")) {


                                        double c = Double.parseDouble(cursort2.getString(0));
                                        double s = Double.parseDouble(cursort2.getString(1));
                                        double rl = Double.parseDouble(r);

                                        double cg = (c / 100) * rl;
                                        double sg = (s / 100) * rl;
                                        double f = cg + sg;

                                        fi += f;

                                        etax.setText(""+(""+String.format("%.2f", fi)));
                                        // Toast.makeText(this, "hi"+rl, Toast.LENGTH_SHORT).show();
                                        try {
                                            double t = Double.parseDouble(etotal.getText().toString());
                                            double ta = Double.parseDouble(etax.getText().toString());

                                            double fi = t + ta;

                                            einwamt.setText(""+(""+String.format("%.2f", fi)));
                                        } catch (Exception e) {

                                        }
                                    }
                                    else
                                    {


                                        if(cursort2.getString(2).equalsIgnoreCase("0.0000")) {

                                            double c = Double.parseDouble(cursort2.getString(0));
                                            double s = Double.parseDouble(cursort2.getString(1));
                                            double rl = Double.parseDouble(r);

                                            double cg = (c / 100) * rl;
                                            double sg = (s / 100) * rl;
                                            double f = 0;

                                            fi += f;

                                            etax.setText(""+(""+String.format("%.2f", fi)));
                                            try {
                                                double t = Double.parseDouble(etotal.getText().toString());
                                                double ta = Double.parseDouble(etax.getText().toString());

                                                double fi = t + ta;

                                                einwamt.setText(""+(""+String.format("%.2f", fi)));
                                            } catch (Exception e) {

                                            }
                                        }
                                        else
                                        {

                                            double c = Double.parseDouble(cursort2.getString(0));
                                            double s = Double.parseDouble(cursort2.getString(1));
                                            double ig = Double.parseDouble(cursort2.getString(2));
                                            double rl = Double.parseDouble(r);

                                            double cg = (c / 100) * rl;
                                            double sg = (s / 100) * rl;
                                            double ig2 = (ig / 100) * rl;
                                            double f =ig2;

                                            fi += f;

                                            etax.setText(""+(""+String.format("%.2f", fi)));
                                            try {
                                                double t = Double.parseDouble(etotal.getText().toString());
                                                double ta = Double.parseDouble(etax.getText().toString());

                                                double fi = t + ta;

                                                einwamt.setText(""+(""+String.format("%.2f", fi)));
                                            } catch (Exception e) {

                                            }
                                        }
                                    }

                               // }                                while (cursort2.moveToNext());
                            }

//                Log.e("Check",""+cursor.getString(1));


                            // array2.add(cursor.getString(11));
                            // Log.e("VAL",""+cursor.getString(11));

                        } while (cursort.moveToNext());

                    }




                }
                while (cursor2n.moveToNext());


            }
        }
        if(suppliername2.equalsIgnoreCase(""))
        {

        }
        else
        {
            Cursor cursor2nn = dbs.query("state", new String[]{"sname","scode"
                    }, "sname" + "=?",
                    new String[]{suppliername2}, null, null, null, null);

            if (cursor2nn.moveToFirst()) {
                do {

                    //Toast.makeText(Bookinvoicenew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

                    elocation.setText("" + cursor2nn.getString(0));
                    if(sharedpreferences.getString("lo","").equalsIgnoreCase(cursor2nn.getString(1)))
                    {
                        //  Toast.makeText(Bookinvoicenew.this, "Same State", Toast.LENGTH_SHORT).show();
                        ss="0";

                    }
                    else
                    {
                        //  Toast.makeText(Bookinvoicenew.this, "Out State", Toast.LENGTH_SHORT).show();
                        ss="1";

                    }
                    code = cursor2nn.getString(1);
                    view3.setEnabled(true);

                    String selectQueryt = "SELECT  * FROM productlist2 where flag = 0";
                    producttaxlist.clear();
                    Cursor cursort = dbs.rawQuery(selectQueryt, null);
                    fi = 0;
                    // looping through all rows and adding to list
                    if (cursort.moveToFirst()) {
                        do {



                            String idd = cursort.getString(0);
                            String r = cursort.getString(6);
                            String ri = cursort.getString(1);



                            Cursor cursort2 = dbs.query("product", new String[]{"In_cgst",
                                            "In_sgst","In_igst","In_hsn_desc"}, "In_product_name" + "=?",
                                    new String[]{ri}, null, null, null, null);

                            if (cursort2.moveToFirst()) {
                              //  do {


                                    if(ss.equalsIgnoreCase("0")) {



                                        double c = Double.parseDouble(cursort2.getString(0));
                                        double s = Double.parseDouble(cursort2.getString(1));
                                        double rl = Double.parseDouble(r);

                                        double cg = (c / 100) * rl;
                                        double sg = (s / 100) * rl;
                                        double f = cg + sg;

                                        fi += f;
                                        DecimalFormat amountFormate = new DecimalFormat("############.##");
                                        amountFormate.setMinimumFractionDigits(2);
                                        amountFormate.setMaximumFractionDigits(2);
                                        etax.setText(""+(""+String.format("%.2f", fi)));
                                        try {
                                            double t = Double.parseDouble(etotal.getText().toString());
                                            double ta = Double.parseDouble(etax.getText().toString());

                                            double fi = t + ta;

                                            einwamt.setText(""+(""+String.format("%.2f", fi)));
                                        } catch (Exception e) {

                                        }
                                    }
                                    else
                                    {


                                        if(cursort2.getString(2).equalsIgnoreCase("0.0000")) {

                                            double c = Double.parseDouble(cursort2.getString(0));
                                            double s = Double.parseDouble(cursort2.getString(1));
                                            double rl = Double.parseDouble(r);

                                            double cg = (c / 100) * rl;
                                            double sg = (s / 100) * rl;
                                            double f = 0;

                                            fi += f;
                                            DecimalFormat amountFormate = new DecimalFormat("############.##");
                                            amountFormate.setMinimumFractionDigits(2);
                                            amountFormate.setMaximumFractionDigits(2);
                                            etax.setText(""+(""+String.format("%.2f", fi)));
                                            try {
                                                double t = Double.parseDouble(etotal.getText().toString());
                                                double ta = Double.parseDouble(etax.getText().toString());

                                                double fi = t + ta;

                                                einwamt.setText(""+(""+String.format("%.2f", fi)));
                                            } catch (Exception e) {

                                            }
                                        }
                                        else
                                        {
                                            double c = Double.parseDouble(cursort2.getString(0));
                                            double s = Double.parseDouble(cursort2.getString(1));
                                            double ig = Double.parseDouble(cursort2.getString(2));
                                            double rl = Double.parseDouble(r);

                                            double cg = (c / 100) * rl;
                                            double sg = (s / 100) * rl;
                                            double ig2 = (ig / 100) * rl;
                                            double f = ig2;

                                            fi += f;
                                            DecimalFormat amountFormate = new DecimalFormat("############.##");
                                            amountFormate.setMinimumFractionDigits(2);
                                            amountFormate.setMaximumFractionDigits(2);
                                            etax.setText(""+(""+String.format("%.2f", fi)));
                                            try {
                                                double t = Double.parseDouble(etotal.getText().toString());
                                                double ta = Double.parseDouble(etax.getText().toString());

                                                double fi = t + ta;

                                                einwamt.setText(""+(""+String.format("%.2f", fi)));
                                            } catch (Exception e) {

                                            }
                                        }
                                    }

                             //   }                                while (cursort2.moveToNext());
                            }

//                Log.e("Check",""+cursor.getString(1));


                            // array2.add(cursor.getString(11));
                            // Log.e("VAL",""+cursor.getString(11));

                        } while (cursort.moveToNext());

                    }




                }
                while (cursor2nn.moveToNext());


            }
        }
        if(etransport.getText().toString().equalsIgnoreCase(""))
        {

        }
        else
        {
            getAmtt();
        }
        if(eothers.getText().toString().equalsIgnoreCase(""))
        {

        }
        else
        {
            getAmto();
        }
    }


    public class MyRecyclerViewAdapterd extends RecyclerView.Adapter<MyRecyclerViewAdapterd.ViewHolder> {

        private List<Dialogpojo> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapterd(Context context, List<Dialogpojo> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapterd.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.suplist, parent, false);
            return new MyRecyclerViewAdapterd.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(final MyRecyclerViewAdapterd.ViewHolder holder, final int position) {
            final Dialogpojo dialogpojo = mData.get(position);
            holder.name.setText(dialogpojo.getName());
            holder.ph.setText(dialogpojo.getPh());
            holder.lcn.setText(dialogpojo.getLcn());
            holder.ty.setText(dialogpojo.getType());
            holder.tsur.setText(dialogpojo.getSn());
            holder.tfhw.setText(dialogpojo.getFhn());
            //holder.llist.setBackgroundResource(R.drawable.rbutton);
            holder.llist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // holder.llist.setBackgroundResource(R.drawable.rbutton2);

                    // esupplier.setText(""+dialogpojo.getPh());
                    //esupplier2.setText(""+dialogpojo.getName());
                    //notifyDataSetChanged();
                    //  holder.llist.setBackgroundResource(R.drawable.rbutton);
                    suppliername = dialogpojo.getPh()
                    ;
                    sun = dialogpojo.getName();
                    // Toast.makeText(Bookinvoicenew.this, "Hi", Toast.LENGTH_SHORT).show();
                    Log.e("LOC",""+suppliername+"//"+sun);
                    suppliername2="";
                    esupplier.setText(suppliername);
                    dialog.dismiss();
                    supplier.setText(suppliername);
                    // elocation.setVisibility(View.VISIBLE);
                     //spinnerl.setVisibility(View.GONE);
                    lsup.setVisibility(View.VISIBLE);
                    counttotal();

                    esuppliern.setEnabled(false);

                }
            });
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder  {
            TextView myTextView,trate,tamt,tnamt,tfhw,tsur,name,ph,lcn,ty;
            ImageView del,ed;
            LinearLayout llist;

            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.tname);
                ph = itemView.findViewById(R.id.tphone);
                lcn = itemView.findViewById(R.id.tloc);
                ty = itemView.findViewById(R.id.ttype);
                llist = itemView.findViewById(R.id.llist);
                tsur = itemView.findViewById(R.id.tsur);
                tfhw = itemView.findViewById(R.id.tfhw);





            }


        }

        // convenience method for getting data at click position




        // parent activity will implement this method to respond to click events

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public  void seten()
    {
        btnn= "1";
        item.setEnabled(false);
        erate.setEnabled(false);
        badd.setEnabled(false);
        ediscount.setEnabled(false);
        bqa.setEnabled(false);
        bqd.setEnabled(false);
        edate.setEnabled(false);
        supplier.setEnabled(false);
        esuppliern.setEnabled(false);
        spinnerl.setEnabled(false);
        elocation.setEnabled(false);
        etransport.setEnabled(false);
        eothers.setEnabled(false);
        equantity.setEnabled(false);

    }
    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        fetchsn();


    }

    public void fetchsn() {SQLiteDatabase dbs = db.getWritableDatabase();

        Cursor cursor = dbs.query("sno", new String[]{"v1","v2","v3"
                }, "v3" + "=? COLLATE NOCASE",
                new String[]{tspr}, null, null, null, null);
        Log.e("COUNT", "" + cursor.getCount());

        //esl.setText("" + cursor.getCount()+"(Serial no of Item)");
        esl.setText("" + cursor.getCount());


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {


                ///String[] pn = cursor.getString(1).split("-");
                //


            } while (cursor.moveToNext());

        }
    }


    public void callpayment()
    {
        final SQLiteDatabase dbs = db.getWritableDatabase();
        //Log.e("Spinner Value",""+spinner.getSelectedItem().toString());
        if (isNetworkAvailable()) {
            String checkquery = "SELECT  * FROM paylist where uflag = 0";
            Cursor ckcursor = dbs.rawQuery(checkquery, null);
            if(ckcursor.getCount() != 0)
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("There is a some data not synced with server please perform Transaction Sync to proceed further!")
//set positive button
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //set what would happen when positive button is clicked
                                        finish();

                                    }
                                }
//set negative button

                        )
                        .show();
            }
            else
            {

                db.updateinvoice(Integer.parseInt(pyid.toString()), innob.toString(), ebal.getText().toString(), balance.getText().toString(), "", "", "", cname.toString(), "0");
                if (ck == 0) {


                    db.paylist(itext, paymodecode, eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "0", "0","1","Success");
                } else {
                    db.paylist(itext, paymodecode, eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "0", chno.getText().toString(),"1","Success");

                }
                // Toast.makeText(Bookinvoicenew.this, "Successfully submitted", Toast.LENGTH_SHORT).show();
                //l3.setVisibility(View.VISIBLE);
                // l4.setVisibility(View.GONE);
                //finish();

                Cursor cursorfc = dbs.query("invoice", new String[]{"balance", "amount", "paymode", "refno", "date"
                        }, "invoiceno" + "=?",
                        new String[]{itext}, null, null, null, null);

                if (cursorfc.moveToFirst()) {
                    do {

                        paybalamt = cursorfc.getString(0);


                        Cursor cursorfc2 = dbs.query("paylist", new String[]{"amountpaid", "refno", "date", "paymode", "ckno"
                                }, "invoiceno" + "=?",
                                new String[]{itext}, null, null, null, null);

                        if (cursorfc2.moveToFirst()) {



                            refno = cursorfc2.getString(1);
                            paidamt = cursorfc2.getString(0);
                            paymode = cursorfc2.getString(3);
                            payindate = cursorfc2.getString(2);
                            cqno = cursorfc2.getString(4);




                                savep();

                            // savep();
                        }


                    }

                    while (cursorfc.moveToNext());


                }
            }

        }
        else
        {
            db.updateinvoice(Integer.parseInt(pyid.toString()), innob.toString(), ebal.getText().toString(), balance.getText().toString(), "", "", "", cname.toString(), "0");

            if(ck == 0) {


                db.paylist(itext, paymodecode, eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "0","0","1","Success");
            }
            else
            {
                db.paylist(itext, paymodecode, eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "0",chno.getText().toString(),"1","Success");

            }
            // Toast.makeText(Bookinvoicenew.this, "Successfully submitted", Toast.LENGTH_SHORT).show();
            // l3.setVisibility(View.VISIBLE);
            // l4.setVisibility(View.GONE);
            final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                    .setTitle("Success!")
//set message
                    .setMessage("Payment Completed")
//set positive button
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //set what would happen when positive button is clicked
                                    finish();

                                }
                            }
//set negative button

                    )
                    .setCancelable(true)
                    .show();
        }
    }



    public void getorderID()
    {
        try {

            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", sharedpreferences.getString("oc1",""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc",""));
            user.put("localeId", "en_US");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();


            user2.put("In_orgn_code",sharedpreferences.getString("oc1",""));
            user2.put("In_invoice_no",invoiceno.getText().toString());
            user2.put("IOU_po_rowid",0);
            String[] d = epdate.getText().toString().split("/");
            user2.put("In_po_date",d[2]+"-"+d[1]+"-"+d[0]);
            user2.put("In_channel","M");
            user2.put("In_invoice_amount",eamtpaid.getText().toString());
            user2.put("In_balance_amount",ebal.getText().toString());
            user2.put("In_status_code","A");
            user2.put("In_mode_flag","I");


            user.put("Header",user2);








            Log.e("OUTPUTODERID",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUTORDERID",""+Log.getStackTraceString(e));
            Log.e("OUTPUT",""+jsonParam.toString());
        }




        HttpsTrustManager.allowAllSSL();
                        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "api/ICDMOB_PAYTM/OrderIdGeneration", jsonParam,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Log.e("CCCC", "" + response);
                                         try {
                                             JSONObject obj = response.getJSONObject("context");
                                             JSONObject obj2 = obj.getJSONObject("Header");
                                             orderID ="";
                                             orderID = obj2.getString("In_PO_Orderid");

                                             dialogpayment = new Dialog(Bookinvoicenew.this);
                                             dialogpayment.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                             dialogpayment.setCancelable(false);
                                             dialogpayment.setContentView(R.layout.paymentdialog);
                                             dialogpayment.setCanceledOnTouchOutside(false);

                                             dialogpayment.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                             WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                                             layoutParams.copyFrom(dialogpayment.getWindow().getAttributes());
                                             layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                                             layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
                                             dialogpayment.getWindow().setAttributes(layoutParams);
                                             Button lcancel = dialogpayment.findViewById(R.id.bcancel);
                                             txttimer = dialogpayment.findViewById(R.id.txttimer);
                                             reverseTimer(60);
                                             lcancel.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     salestatus = 3;

                                                     CancelRequest.Builder builder = new CancelRequest.Builder()
                                                             .setMerchantId(TextUtils.isEmpty(merchantId) ? null : merchantId)
                                                             .setOrderId(orderID);
                                                     CancelRequest cancelRequest = builder.build();
                                                     String cancelRequestId = String.valueOf(System.currentTimeMillis());
                                                     com.paytm.ecr.bluetooth.sdk.model.Request<CancelRequest> request = com.paytm.ecr.bluetooth.sdk.model.Request.of(cancelRequest, cancelRequestId);
                                                     payments.doCancel(request, Bookinvoicenew.this);
                                                     // Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
                                                     dialogpayment.dismiss();
                                                 }
                                             });



                                             dialogpayment.show();

                                             Long tsLong = System.currentTimeMillis()/1000;
                                             String ts = tsLong.toString();
                                             int ppaam = Integer.parseInt(eamtpaid.getText().toString()) * 100;
                                             salestatus = 0;
                                             SaleRequest.Builder builder = new SaleRequest.Builder()
                                                     .setMerchantId((TextUtils.isEmpty(merchantId) ? null : merchantId))
                                                     .setOrderId(orderID)
                                                     .setPrintInfo("printInfo://values?"+stringBuilder.toString().replaceAll("-","").replaceAll("%","").replaceAll("\\(","").replaceAll("\\)","").replaceAll(" ","").replaceAll("\\.","_")+"InvoiceAmount="+eamtpaid.getText().toString().replaceAll("\\.","_"))
                                                   //  .setGstInformation("gstInformation://values?gstIn=08TESTF0078P1ZP&gstBrkUp=CGST:10|SGST:10|IGST:10|CESS:10|GSTIncentive:10|GSTPCT:10&invoiceNo="+invoiceno.getText().toString()+"&invoiceDate=20220720142919")
                                                     .setAmount(String.valueOf(ppaam));

                                             SaleRequest saleRequest = builder.build();
                                             String saleRequestId = String.valueOf(System.currentTimeMillis());
                                             com.paytm.ecr.bluetooth.sdk.model.Request<SaleRequest> request = com.paytm.ecr.bluetooth.sdk.model.Request.of(saleRequest, saleRequestId);
                                             payments.doSale(request, Bookinvoicenew.this);
                                             final Handler handler = new Handler(Looper.getMainLooper());
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Do something after 10 sec
                                    if(salestatus == 0)
                                    {
                                        dialogpayment.dismiss();
                                        salestatus = 9;
                                        db.paylist(itext, "PAYTM", eamtpaid.getText().toString(), orderID, epdate.getText().toString(), "", "0","1","0","2","Pending");

                                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                                .setTitle("INFO!")
//set message
                                                .setMessage("Payment Pending!")
//set positive button
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                                //set what would happen when positive button is clicked
                                                                finish();

                                                            }
                                                        }
//set negative button

                                                )

                                                .setCancelable(false)
                                                .show();
                                    }

                                   // Log.e("Do something after 10 sec","");

                                }
                            }, 60000);
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
                                4500000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        VolleySingleton.getInstance(Bookinvoicenew.this).addToRequestQueue(stringRequest);


}


    public void saveStatus(String status)
    {
        try {

            String[] res = saleresponse.toString().split(",");
            Log.e("TXTid", "" + res[3]);

            String[] tx = res[3].split("=");
            String[] scode = res[41].split("=");
            Log.e("VAL1",""+scode[1]);
            String[] smsg = res[42].split("=");
            Log.e("VAL2",""+smsg[1]);
            String[] abank = res[39].split("=");
            Log.e("VAL3",""+abank[1]);
            String[] svcreated = res[36].split("=");
            Log.e("VAL4",""+svcreated[1]);
            String[] boapplied = res[33].split("=");
            Log.e("VAL5",""+boapplied[1]);
            String[] bmid = res[21].split("=");
            Log.e("VAL6",""+bmid[1]);
            String[] btid = res[22].split("=");
            Log.e("VAL7",""+btid[1]);
            String[] brscode = res[20].split("=");
            Log.e("VAL8",""+brscode[1]);
            String[] cschme = res[19].split("=");
            Log.e("VAL9",""+cschme[1]);
            String[] ctype = res[18].split("=");
            Log.e("VAL10",""+ctype[1]);
            String[] pmethod = res[17].split("=");
            Log.e("VAL11",""+pmethod[1]);
            String[] aid = res[16].split("=");
            Log.e("VAL12",""+aid[1]);
            String[] tid = res[15].split("=");
            Log.e("VAL13",""+tid[1]);
            String[] ttype = res[10].split("=");
            Log.e("VAL14",""+ttype[1]);
            String[] issuebank = res[9].split("=");
            Log.e("VAL15",""+issuebank[1]);
            String[] cno = res[8].split("=");
            Log.e("VAL16",""+cno[1]);
            String[] rrn = res[7].split("=");
            Log.e("VAL17",""+rrn[1]);
            String[] aucode = res[3].split("=");
            Log.e("VAL18",""+aucode[1]);
            String[] txid = res[2].split("=");
            String[] inno = res[12].split("=");
            Log.e("VAL19",""+txid[1]);


            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", sharedpreferences.getString("oc1",""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc",""));
            user.put("localeId", "en_US");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();


            user2.put("IOU_pth_rowid",0);
            user2.put("In_merchantId",merchantId);
            user2.put("In_orderId",orderID);
            String[] d = epdate.getText().toString().split("/");
            user2.put("In_txnId",tx[1].substring( 1, tx[1].length() - 1 ));
            user2.put("In_txnDate",d[2]+"-"+d[1]+"-"+d[0]);
            user2.put("In_rrn",rrn[1].substring( 1, rrn[1].length() - 1 ));
            user2.put("In_cardNo",cno[1].substring( 1, cno[1].length() - 1 ));
            user2.put("In_authCode","");
            user2.put("In_issuingBank",issuebank[1].substring( 1, issuebank[1].length() - 1 ));
            user2.put("In_amount",eamtpaid.getText().toString());
            user2.put("In_txnType","PAYTM");
            user2.put("In_invoiceNumber",inno[1].substring( 1, inno[1].length() - 1 ));
            user2.put("In_extendInfo","");
            user2.put("In_printInfo","");
            user2.put("In_tid",tid[1].substring( 1, tid[1].length() - 1 ));
            user2.put("In_aid",aid[1].substring( 1, aid[1].length() - 1 ));
            user2.put("In_payMethod",pmethod[1].substring( 1, pmethod[1].length() - 1 ));
            user2.put("In_cardType",ctype[1].substring( 1, ctype[1].length() - 1 ));
            user2.put("In_cardScheme",cschme[1].substring( 1, cschme[1].length() - 1 ));
            user2.put("In_bankResponseCode",brscode[1].substring( 1, brscode[1].length() - 1 ));
            user2.put("In_bankMid",bmid[1].substring( 1, bmid[1].length() - 1 ));
            user2.put("In_bankTid",btid[1].substring( 1, btid[1].length() - 1 ));
            user2.put("In_productManufacturer","");
            user2.put("In_productCategory","");
            user2.put("In_productSerialNoType","");
            user2.put("In_productSerialNoValue","");
            user2.put("In_productName","");
            user2.put("In_emiTxnType","");
            user2.put("In_emiTenure","");
            user2.put("In_emiInterestRate","");
            user2.put("In_emiMonthlyAmount","");
            user2.put("In_emiTotalAmount","");
            user2.put("In_bankOfferApplied",boapplied[1].substring( 1, boapplied[1].length() - 1 ));
            user2.put("In_bankOfferType","");
            user2.put("In_bankOfferAmount","");
            user2.put("In_subventionCreated",svcreated[1].substring( 1, svcreated[1].length() - 1 ));
            user2.put("In_subventionType","");
            user2.put("In_subventionOfferAmount","");
            user2.put("In_acquiringBank","");
            user2.put("In_virtualPaymentAddress","");
            user2.put("In_statusCode",scode[1].substring( 1, scode[1].length() - 1 ));
            user2.put("In_statusMessage",smsg[1].substring( 1, smsg[1].length() - 2 ));
            user2.put("In_mode_flag","I");


            user.put("Header",user2);








            Log.e("OUTPUTSALE",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUTSALE",""+Log.getStackTraceString(e));
            Log.e("OUTPUT",""+jsonParam.toString());
        }









        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "api/ICDMOB_PAYTM/PaymentHistoryResponseSave", jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("Header");

                            orderID = obj2.getString("IOU_pth_rowid");
                            if(!orderID.equalsIgnoreCase("0"))
                            {

                                dialogpayment.dismiss();
                                if(status.equalsIgnoreCase("1")) {
                                    db.updateinvoice(Integer.parseInt(pyid.toString()), innob.toString(), ebal.getText().toString(), balance.getText().toString(), "", "", "", cname.toString(), "0");

                                    db.paylist(itext, paymodecode, eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "1","0","1","Success");

                                    final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                            .setTitle("Success!")
//set message
                                            .setMessage("Payment Completed!")
//set positive button
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {

                                                            //set what would happen when positive button is clicked
                                                            finish();

                                                        }
                                                    }
//set negative button

                                            )

                                            .setCancelable(false)
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
                4500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Bookinvoicenew.this).addToRequestQueue(stringRequest);


    }

//    @Override
//    public void onBackPressed() {
//
//        if(paymodecode.equalsIgnoreCase("QCD_AEPS_PAYTM"))
//        {
//            if(successstatus.equalsIgnoreCase("1"))
//            {
//                final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
////set icon
//                        .setIcon(android.R.drawable.ic_menu_save)
////set title
//                        .setTitle("INFO!")
////set message
//                        .setMessage("Back Not Allowed At Time Point ")
////set positive button
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        //set what would happen when positive button is clicked
//                                        //finish();
//
//                                    }
//                                }
////set negative button
//
//                        )
//                        .show();
//            }
//            else
//            {
//                finish();
//            }
//        }
//        else
//        {
//            finish();
//        }
//    }

    public  void checkconnection()
    {
        if(paytmbluetooth.equalsIgnoreCase("1"))
        {
            //Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
            lsave.setEnabled(true);
            sconnecton.setBackgroundResource(R.drawable.eblue);
            lsave.setBackgroundResource(R.drawable.rbutton3);
        }
        else
        {
           //Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show();
            sconnecton.setBackgroundResource(R.drawable.grayblue);
            lsave.setEnabled(false);
            lsave.setBackgroundResource(R.drawable.rbutton);
        }
    }
    public void reverseTimer(int Seconds){

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                txttimer.setText("TIME : " + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                txttimer.setText("Completed");
            }
        }.start();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            statuscode = data.getBooleanExtra("status", false);
            response = data.getIntExtra("response", 0);
            message = data.getStringExtra("message");
            txnrefrenceid = data.getStringExtra("txnrefrenceid");


            Toast.makeText(getApplicationContext(), ""+ message, Toast.LENGTH_SHORT).show();
            
            if(response == 1){
                showdialog("Info","Success");
                aepso_saveStatus("1");
                savep();
            }
            else if(response == 0){
                showdialog("Info",message);
                aepso_saveStatus("2");
            }


        } else {
            Toast.makeText(getApplicationContext(), "Empty Data", Toast.LENGTH_SHORT).show();
        }


    }

    private void aepso_saveStatus(String status) {
        try {
            try {



                jsonParam = new JSONObject();
                JSONObject userd = new JSONObject();
                jsonParam.put("document",userd);
                JSONObject user = new JSONObject();
                user.put("orgnId", sharedpreferences.getString("oc1",""));
                user.put("locnId", Pojokyc.instance);
                user.put("userId", sharedpreferences.getString("uc",""));
                user.put("localeId", "en_US");
                userd.put("context",user);
                JSONObject user2 = new JSONObject();


                user2.put("IOU_pth_rowid",0);
                user2.put("In_merchantId",aeps_merchantId);
                user2.put("In_orderId",orderID);

                user2.put("In_txnId",""+txnrefrenceid);



                user2.put("In_authCode","");
                user2.put("In_rrn","");
                user2.put("In_cardNo","");
                user2.put("In_issuingBank","");

                user2.put("In_amount",eamtpaid.getText().toString());


                user2.put("In_txnType","AEPS");
                user2.put("In_invoiceNumber",invoiceno.getText().toString());
                user2.put("In_extendInfo","");
                user2.put("In_printInfo","");

                user2.put("txnrefrenceid", 0);

                user2.put("In_aid","");
                user2.put("In_payMethod","AADHAR PAY");
                user2.put("In_cardType","");
                user2.put("In_cardScheme","");
                user2.put("In_bankResponseCode",""+response);
                user2.put("In_bankMid","");
                user2.put("In_bankTid","");
                user2.put("In_productManufacturer","");
                user2.put("In_productCategory","");
                user2.put("In_productSerialNoType","");
                user2.put("In_productSerialNoValue","");
                user2.put("In_productName","");
                user2.put("In_emiTxnType","");
                user2.put("In_emiTenure","");
                user2.put("In_emiInterestRate","");
                user2.put("In_emiMonthlyAmount","");
                user2.put("In_emiTotalAmount","");
                user2.put("In_bankOfferApplied","");
                user2.put("In_bankOfferType","");
                user2.put("In_bankOfferAmount","");
                user2.put("In_subventionCreated","");
                user2.put("In_subventionType","");
                user2.put("In_subventionOfferAmount","");
                user2.put("In_acquiringBank","");
                user2.put("In_virtualPaymentAddress","");
                user2.put("In_statusCode",""+response);
                user2.put("In_statusMessage",message);
                user2.put("In_mode_flag","I");


                user.put("Header",user2);










                Log.e("OUTPUTSALE",""+jsonParam.toString());
            }
            catch(Exception e)
            {
                Log.e("OUTPUTSALE",""+Log.getStackTraceString(e));
                Log.e("OUTPUT",""+jsonParam.toString());
            }









            HttpsTrustManager.allowAllSSL();
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "api/ICDMOB_PAYTM/PaymentHistoryResponseSaveAEPS", jsonParam,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("CCCC", "" + response);
                            try {
                                JSONObject obj = response.getJSONObject("context");
                                JSONObject obj2 = obj.getJSONObject("Header");

                                orderID = obj2.getString("IOU_pth_rowid");
                                if(!orderID.equalsIgnoreCase("0"))
                                {

                                   /* dialogpayment.dismiss();
                                    if(status.equalsIgnoreCase("1")) {
                                        db.updateinvoice(Integer.parseInt(pyid.toString()), innob.toString(), ebal.getText().toString(), balance.getText().toString(), "", "", "", cname.toString(), "0");

                                        db.paylist(itext, paymodecode, eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "1","0","1","Success");

                                        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                                .setTitle("Success!")
//set message
                                                .setMessage("Payment Completed!")
//set positive button
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                                //set what would happen when positive button is clicked
                                                                finish();

                                                            }
                                                        }
//set negative button

                                                )

                                                .setCancelable(false)
                                                .show();
                                    }*/
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
                    4500000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(Bookinvoicenew.this).addToRequestQueue(stringRequest);


        }
        catch(Exception e)
        {
            Log.e("OUTPUTSALE",""+Log.getStackTraceString(e));
            Log.e("OUTPUT",""+jsonParam.toString());
        }









        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "api/ICDMOB_PAYTM/PaymentHistoryResponseSaveAEPS", jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCCHISTORY", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("Header");

                            orderID = obj2.getString("IOU_pth_rowid");
                            //Toast.makeText(Paymentlist.this, ""+status, Toast.LENGTH_SHORT).show();
                            if(!orderID.equalsIgnoreCase("0"))
                            {
                                try {
                                    dialogpayment.dismiss();
                                }
                                catch(Exception e)
                                {

                                }

                                if(status.equalsIgnoreCase("1")) {
                                    db.updateinvoice(Integer.parseInt(pyid.toString()), innob.toString(), ebal.getText().toString(), balance.getText().toString(), "", "", "", cname.toString(), "0");

                                    db.paylist(itext, paymodecode, eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "1","0","1","Success");

                                    final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                            .setTitle("Success!")
//set message
                                            .setMessage("Payment Completed!")
//set positive button
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {

                                                            //set what would happen when positive button is clicked
                                                            finish();

                                                        }
                                                    }
//set negative button

                                            )
                                            .setCancelable(false)
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
                4500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Bookinvoicenew.this).addToRequestQueue(stringRequest);


    }


    public void showdialog(String title,String message){
        final AlertDialog alertDialog = new AlertDialog.Builder(Bookinvoicenew.this)
//set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                .setTitle(title+"!")
//set message
                .setMessage(message+"!")
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
