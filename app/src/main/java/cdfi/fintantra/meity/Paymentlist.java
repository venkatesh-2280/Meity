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
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Paymentlist extends AppCompatActivity implements IConnectionStateListener, ResponseListener {
    PaytmPayments payments;
    String merchantId = "", orderID, paylistid, aeps_merchantId = "";
    AppCompatButton cconnecton, osetting, reqapi;
    ImageView sconnecton;
    LinearLayout llpaytm;
    int salestatus = 0;
    String ap, pdt, ab;
    String successstatus = "1";
    StringBuilder stringBuilder;
    Dialog dialogpayment;
    public LinearLayout l1, l2, l3, l4, l44;
    Calendar myCalendar;
    String paymodecode;
    String saleresponse, paytmbluetooth;
    List<pojopaytax> paylist;
    TextView fponame, name, txttimer;
    int ck = 0;
    LinearLayout l;
    String cqno = "0";
    String ss = "0";
    TextView txtheader;
    RelativeLayout rdetail, rsummary, rpayment;
    ImageView im1, im2, im3;
    View v1, v2, v3;
    JSONObject userd;
    double ta;
    List<pojoProducttax> producttaxlist;
    Paymentlist.MyRecyclerViewAdapter2 adapter2;
    AutoCompleteTextView esupplier, esupplier2;
    JSONObject jsonParam;
    Paymentlist.MyRecyclerViewAdapter3 adapter3;
    String payid, payno, payinamt, paybalamt, paymode, refno, paydate, payamt, paidamt, payindate;
    EditText edate, eamount, ediscount, enetamount, erate, eitem, equantity, etotal, ecount, elocation, etax, etransport, eothers, einwno, einwamt;
    String ts;
    androidx.recyclerview.widget.RecyclerView recyclerViewp;
    AutoCompleteTextView esuppliern;
    Paymentlist.MyRecyclerViewAdapterp adapterp;
    EditText chno;
    Paymentlist.MyRecyclerViewAdapter adapter;
    EditText eamtpaid, erefno, epdate, ebal, inputsearch, newnet;
    Button view, view3, view2, viewp, lsave;
    Spinner spinnerm, spinnerl;
    ImageView back;
    int button = 0;
    ProgressDialog pdialog;
    List<pojoProduct> productlist;
    String pyid, cname, innob, am;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    TextView invoiceno, amount, balance, paid;
    String itext;
    Button detail, header, payment;


    Dialog dialog, dialog2, dialog3;

    DBHelper db;
    LinearLayout lch;

    List<pojoPayment> invoicelist;
    ArrayList<String> categoriescode = new ArrayList<>();
    ArrayList<String> categoriespay = new ArrayList<String>();
    int response;
    String message = "";
    String txnrefrenceid;
    boolean statuscode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentlist);
        getSupportActionBar().hide();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        fponame = (TextView) findViewById(R.id.fponame);
        name = (TextView) findViewById(R.id.name);
        db = new DBHelper(this);
        myCalendar = Calendar.getInstance();
        final SQLiteDatabase dbs = db.getWritableDatabase();
        Cursor cmid = dbs.rawQuery("select * from mercid where out_paytm_fpocode = '" + sharedpreferences.getString("oc1", "") + "'", null);

        if (cmid.getCount() > 0) {
            if (cmid.moveToFirst()) {
                merchantId = cmid.getString(4);
                // Toast.makeText(Paymentlist.this,""+merchantId,Toast.LENGTH_SHORT).show();
            }
        }

        Cursor cmid_aeps = dbs.rawQuery("select * from aepsmercid where out_aeps_fpocode = '" + sharedpreferences.getString("oc1", "") + "'", null);

        if (cmid_aeps.getCount() > 0) {
            if (cmid_aeps.moveToFirst()) {
                aeps_merchantId = cmid_aeps.getString(4);
            }
        }

        fponame.setText(sharedpreferences.getString("rn", ""));
        name.setText("Welcome " + sharedpreferences.getString("un", ""));
        invoicelist = new ArrayList<>();
        inputsearch = (EditText) findViewById(R.id.inputSearch);
        paylist = new ArrayList<>();
        l = (LinearLayout) findViewById(R.id.l);
        productlist = new ArrayList<>();
        spinnerm = (Spinner) findViewById(R.id.spinnerm);
        pdialog = new ProgressDialog(this);
        detail = (Button) findViewById(R.id.detail);
        header = (Button) findViewById(R.id.header);
        payment = (Button) findViewById(R.id.payment);
        l3 = (LinearLayout) findViewById(R.id.l3);
        l4 = (LinearLayout) findViewById(R.id.l44);
        l1 = (LinearLayout) findViewById(R.id.l1);
        l44 = (LinearLayout) findViewById(R.id.l4);
        l2 = (LinearLayout) findViewById(R.id.l2);
        rdetail = (RelativeLayout) findViewById(R.id.rdetail);
        rsummary = (RelativeLayout) findViewById(R.id.rsummary);
        rpayment = (RelativeLayout) findViewById(R.id.rpayment);
        rpayment.setEnabled(true);
        llpaytm = (LinearLayout) findViewById(R.id.llpaytm);
        im1 = (ImageView) findViewById(R.id.im1);
        im2 = (ImageView) findViewById(R.id.im2);
        im3 = (ImageView) findViewById(R.id.im3);

        v1 = (View) findViewById(R.id.v1);
        v2 = (View) findViewById(R.id.v2);
        v3 = (View) findViewById(R.id.v3);

        txtheader = (TextView) findViewById(R.id.txtheader);
        detail.setBackgroundResource(R.drawable.rbutton4);
        header.setBackgroundResource(R.drawable.rbutton4);
        payment.setBackgroundResource(R.drawable.rbutton6);
        l1.setVisibility(View.GONE);
        l2.setVisibility(View.GONE);
        l3.setVisibility(View.VISIBLE);
        l4.setVisibility(View.GONE);

        producttaxlist = new ArrayList<>();

        rdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                detail.setBackgroundResource(R.drawable.rbutton6);
                l2.setVisibility(View.GONE);
                l1.setVisibility(View.VISIBLE);
                l3.setVisibility(View.GONE);
                l.setVisibility(View.VISIBLE);
                l4.setVisibility(View.VISIBLE);
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
        sconnecton = findViewById(R.id.sconnecton);
        cconnecton = findViewById(R.id.cconnecton);
        osetting = findViewById(R.id.osetting);
        reqapi = findViewById(R.id.reqapi);

        payments = PaytmPayments.with(this);
        payments.init(new Config.Builder()
                .setStatusCheckOnSaleRequestEnabled(true).build());

        ConnectionCheckRequest connectionCheckRequest = new ConnectionCheckRequest.Builder().build();
        String connectionCheckRequestId = String.valueOf(System.currentTimeMillis());
        com.paytm.ecr.bluetooth.sdk.model.Request<ConnectionCheckRequest> request1 = com.paytm.ecr.bluetooth.sdk.model.Request.of(connectionCheckRequest, connectionCheckRequestId);
        payments.doConnectionCheck(request1, Paymentlist.this);

        salestatus = 20;

        sconnecton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (!payments.isConnected()) {
                    payments.openConnection(Paymentlist.this);
                } else {
                    payments.closeConnection();
                }
*/
                ConnectionCheckRequest connectionCheckRequest = new ConnectionCheckRequest.Builder().build();
                String connectionCheckRequestId = String.valueOf(System.currentTimeMillis());
                com.paytm.ecr.bluetooth.sdk.model.Request<ConnectionCheckRequest> request = com.paytm.ecr.bluetooth.sdk.model.Request.of(connectionCheckRequest, connectionCheckRequestId);
                payments.doConnectionCheck(request, Paymentlist.this);

                salestatus = 20;
            }
        });
        cconnecton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           //     payments.closeConnection();

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
                StatusCheckRequest.Builder builder = new StatusCheckRequest.Builder()
                        .setMerchantId((TextUtils.isEmpty(merchantId) ? null : merchantId))
                        .setOrderId(orderID);


                StatusCheckRequest statusCheckRequest = builder.build();
                String srRequestId = String.valueOf(System.currentTimeMillis());
                com.paytm.ecr.bluetooth.sdk.model.Request<StatusCheckRequest> request = com.paytm.ecr.bluetooth.sdk.model.Request.of(statusCheckRequest, srRequestId);
                payments.doStatusCheck(request, Paymentlist.this);
            }
        });
        rsummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                header.setBackgroundResource(R.drawable.rbutton6);
                l2.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
                l.setVisibility(View.VISIBLE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.VISIBLE);
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
        });
        rpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Spinner click listener


                payment.setBackgroundResource(R.drawable.rbutton6);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
                l4.setVisibility(View.VISIBLE);
                header.setBackgroundResource(R.drawable.rbutton4);
                detail.setBackgroundResource(R.drawable.rbutton4);
                im1.setBackgroundResource(R.drawable.details);
                im2.setBackgroundResource(R.drawable.summary);
                im3.setBackgroundResource(R.drawable.payment2);
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.VISIBLE);
                txtheader.setText("Payment");


            }
        });

        Cursor cr = dbs.rawQuery("select out_master_code as code, out_master_description from masterl where out_parent_code = 'QCD_AEPS_PAYMENT_MODE' and out_status_code = 'A' ", null);
        try {
            categoriescode.clear();
            categoriespay.clear();
            categoriespay.add("Select Payment Mode");
            categoriescode.add("");
            if (cr.getCount() > 0) {
                if (cr.moveToFirst()) {
                    do {
                        categoriescode.add(cr.getString(cr.getColumnIndexOrThrow("code")));
                        categoriespay.add(cr.getString(cr.getColumnIndexOrThrow("out_master_description")));
                    } while (cr.moveToNext());
                }
            }
        } catch (Exception er) {
            Log.e("error", Log.getStackTraceString(er));
        } finally {
            cr.close();
        }

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
    /*    List<String> categories = new ArrayList<String>();
        categories.add("Select Payment Mode");
        categories.add("CASH");
        categories.add("CHEQUE");
        categories.add("CARD");*/


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Paymentlist.this, android.R.layout.simple_spinner_item, categoriespay);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        lsave = (Button) findViewById(R.id.lsave);
        viewp = (Button) findViewById(R.id.viewp);
        back = (ImageView) findViewById(R.id.back);
        invoiceno = (TextView) findViewById(R.id.invoiceno);
        amount = (TextView) findViewById(R.id.balance);
        balance = (TextView) findViewById(R.id.amount);
        paid = (TextView) findViewById(R.id.paid);
        esuppliern = (AutoCompleteTextView) findViewById(R.id.esuppliern);
        esupplier = (AutoCompleteTextView) findViewById(R.id.esupplier);
        recyclerViewp = findViewById(R.id.ptm);
        erate = (EditText) findViewById(R.id.erate);
        eamount = (EditText) findViewById(R.id.eamount);
        enetamount = (EditText) findViewById(R.id.enetamount);
        ediscount = (EditText) findViewById(R.id.ediscount);
        newnet = (EditText) findViewById(R.id.newnet);
        equantity = (EditText) findViewById(R.id.equantity);
        etotal = (EditText) findViewById(R.id.etotal);
        ecount = (EditText) findViewById(R.id.ecount);
        elocation = (EditText) findViewById(R.id.elocation);
        etax = (EditText) findViewById(R.id.etax);
        etransport = (EditText) findViewById(R.id.etransport);
        eothers = (EditText) findViewById(R.id.eothers);
        view = (Button) findViewById(R.id.view);
        view3 = (Button) findViewById(R.id.view3);
        view2 = (Button) findViewById(R.id.view2);
        edate = (EditText) findViewById(R.id.eddate);
        einwamt = (EditText) findViewById(R.id.einwamt);
        einwno = (EditText) findViewById(R.id.einwno);
        l3 = (LinearLayout) findViewById(R.id.l3);
        l4 = (LinearLayout) findViewById(R.id.l44);
        chno = (EditText) findViewById(R.id.chno);
        eamtpaid = (EditText) findViewById(R.id.eamountpaid);
        erefno = (EditText) findViewById(R.id.erefno);
        lch = (LinearLayout) findViewById(R.id.lch);
        ebal = (EditText) findViewById(R.id.ebalance);
        epdate = (EditText) findViewById(R.id.epdate);
        eamtpaid.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l4.setVisibility(View.GONE);
                l3.setVisibility(View.VISIBLE);
                l.setVisibility(View.GONE);

            }
        });
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
        epdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Paymentlist.this, android.R.style.Theme_Holo_Dialog, date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        eamtpaid.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() == 1 && s.toString().startsWith("00")) {
                    s.clear();
                } else if (s.toString().length() == 1 && s.toString().startsWith(".")) {
                    s.clear();
                } else if (s.toString().length() == 0) {
                    //erate.setText("");
                    //getAmt2();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {


                if (s.length() != 0) {
                    double x = 0;
                    double y = 0;
                    try {
                        x = Double.parseDouble(amount.getText().toString());
                        y = Double.parseDouble(eamtpaid.getText().toString());
                    } catch (Exception e) {

                    }
                    if (y > x) {
                        eamtpaid.setText("");
                        // Toast.makeText(Paymentlist.this, "Invalid amount", Toast.LENGTH_SHORT).show();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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
                        // einwamt.setText(""+(fi));

                        ebal.setText(String.format("%.2f", z));
                    }
                } else {
                    //  getAmt2();
                    ebal.setText("");
                    //  erate.setText("");
                }

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                paymodecode = categoriescode.get(position);

                Log.e("PAYCODE", "" + paymodecode);

                if (paymodecode.equalsIgnoreCase("QCD_AEPS_PAYTM")) {
                    lsave.setText("Pay Now");
                    lsave.setBackgroundResource(R.drawable.rbutton);
                    erefno.setEnabled(false);
                    erefno.setText("**Auto Generate**");
                    // getorderID();
                    sconnecton.setVisibility(View.VISIBLE);
                    lsave.setEnabled(false);

                    checkconnection();

                } else if (paymodecode.equalsIgnoreCase("QCD_AEPS_AEPS")) {
                    erefno.setEnabled(false);
                    erefno.setText("**Auto Generate**");
                    sconnecton.setVisibility(View.GONE);
                    lsave.setText("Pay Now");
                    lsave.setBackgroundResource(R.drawable.rbutton3);
                } else {
                    erefno.setText("");
                    lsave.setText("Submit");
                    erefno.setEnabled(true);
                    sconnecton.setVisibility(View.GONE);
                    lsave.setEnabled(true);
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
        inputsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               /* if (charSequence.toString().equalsIgnoreCase("")) {
                  //  adapterp.getFilter().filter("");
                    Log.e("listerchar", String.valueOf(charSequence));
                    Log.e("listerchar", String.valueOf(i));
                    Log.e("listerchar", String.valueOf(i1));
                    Log.e("listerchar", String.valueOf(i2));
                    searchlist();
                } else {
                    try {
                        adapterp.getFilter().filter(charSequence);

                    } catch (Exception e) {
                        //   Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                    }


                }*/
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               /* if (charSequence.toString().equalsIgnoreCase("")) {
                    adapterp.getFilter().filter("");
                } else {
                    try {
                        adapterp.getFilter().filter(charSequence);

                    } catch (Exception e) {
                        //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                    }
                }*/
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                if (editable.toString().equalsIgnoreCase("")) {
                    adapterp.getFilter().filter("");
                    searchlist();
                } else {
                    try {
                        adapterp.getFilter().filter(editable);

                    } catch (Exception e) {
                        //  Toast.makeText(getApplicationContext(),"Please Perform Master Sync",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


           searchlist();
//        String selectQuery = "SELECT  * FROM invoice";
//
//        Cursor cursor = dbs.rawQuery(selectQuery, null);
//
//        // looping through all prows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//
//                pojoPayment pojoPayment = new pojoPayment();
//
//                String id = cursor.getString(0);
//
//                pojoPayment.setId(cursor.getString(0));
//
//                pojoPayment.setInno(cursor.getString(1));
//
//                Cursor cursori = dbs.query("invoicelist", new String[]{"inweb",
//                        }, "invoiceno" + "=?",
//                        new String[]{cursor.getString(1)}, null, null, null, null);
//                if (cursori.moveToFirst()) {
//                    do {
//                        if (cursori.getString(0).equalsIgnoreCase("")) {
//                            pojoPayment.setInno2("");
//                        } else {
//                            // idn = cursor2.getString(2);
//                            //Toast.makeText(Paymentlist.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();
//
//                            pojoPayment.setInno2(cursori.getString(0));
//                        }
//
//
//                    }
//                    while (cursori.moveToNext());
//
//
//                }
//                try {
//
//                    pojoPayment.setBal("" + (Float.parseFloat(cursor.getString(2))));
//                }
//                catch(Exception e)
//                {
//                    pojoPayment.setBal("");
//                }
//                try {
//
//
//                    pojoPayment.setAmt("" + (Float.parseFloat(cursor.getString(3))));
//                }
//                catch (Exception e)
//                {
//                    pojoPayment.setAmt("");
//                }
//                pojoPayment.setRefno(cursor.getString(5));
//                pojoPayment.setCname(cursor.getString(7));
//
//
//                Log.e("Check", "" + cursor.getString(7));
//
//                invoicelist.add(pojoPayment);
//                // array2.add(cursor.getString(11));
//                // Log.e("VAL",""+cursor.getString(11));
//                recyclerViewp.setAdapter(adapterp);
//            } while (cursor.moveToNext());
//
//        }
        viewp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3 = new Dialog(Paymentlist.this);
                dialog3.setContentView(R.layout.viewpaylist);
                dialog3.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog3.setTitle("Title...");
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);

                dialog3.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                // set the custom dialog components - text, image and button

                // set up the RecyclerView
                androidx.recyclerview.widget.RecyclerView recyclerView = dialog3.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(Paymentlist.this));
                adapter3 = new Paymentlist.MyRecyclerViewAdapter3(Paymentlist.this, paylist);
                paylist.clear();
                JSONObject user = new JSONObject();
                try {


                    user.put("orgnId", sharedpreferences.getString("oc1", ""));
                    user.put("locnId", Pojokyc.instance);
                    user.put("userId", sharedpreferences.getString("uc", ""));
                    user.put("localeId", "en_US");
                    user.put("invoice_no", itext);


                    Log.e("OUTPUTINVOICELIST", "" + user.toString());
                } catch (Exception e) {
                    Log.e("OUTPUTORDERID", "" + Log.getStackTraceString(e));
                    Log.e("OUTPUT", "" + user.toString());
                }
                HttpsTrustManager.allowAllSSL();
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "api/ICDInvoice/InvoicePaymentList", user,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("CCCCINVOICE", "" + response);
                                try {
                                    JSONObject obj = response.getJSONObject("context");
                                    JSONArray obj2 = obj.getJSONArray("list");
                                    Log.e("INNNN",""+obj2.length());
                                    for (int i = 0; i < obj2.length(); i++) {
                                        JSONObject object = obj2.getJSONObject(i);
                                        pojopaytax pojopaytax = new pojopaytax();

                        //   String id = cursor.getString(0);
                        pojopaytax.setPaymode(object.getString("out_paymode"));
                        pojopaytax.setAmt(object.getString("out_totalinvoice_amount"));
                        pojopaytax.setRefno(object.getString("out_reference_no"));
                        pojopaytax.setDate(object.getString("out_invoice_date"));
                        pojopaytax.setMsg("");
                        pojopaytax.setId(object.getString("out_invoice_rowid"));


                       // Log.e("Checknow", "" + cursor.getString(0));

                        paylist.add(pojopaytax);
                        // array2.add(cursor.getString(11));
                        // Log.e("VAL",""+cursor.getString(11));
                        recyclerView.setAdapter(adapter3);
                                    }
                                } catch (Exception e) {

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
                VolleySingleton.getInstance(Paymentlist.this).addToRequestQueue(stringRequest);
//                Cursor cursor = dbs.query("paylist", new String[]{"paymode",
//                                "date", "refno", "amountpaid", "msg", "id"}, "invoiceno" + "=?",
//                        new String[]{itext}, null, null, null, null);
//
//                //   Cursor cursor = dbs.rawQuery(selectQuery, null);
//                Log.e("CCK", "C" + cursor.getCount() + "" + itext);
//
//                // looping through all rows and adding to list
//                if (cursor.moveToFirst()) {
//                    do {
//
//                        pojopaytax pojopaytax = new pojopaytax();
//
//                        //   String id = cursor.getString(0);
//                        pojopaytax.setPaymode(cursor.getString(0));
//                        pojopaytax.setAmt("" + (Float.parseFloat(cursor.getString(3))));
//                        pojopaytax.setRefno(cursor.getString(2));
//                        pojopaytax.setDate(cursor.getString(1));
//                        pojopaytax.setMsg(cursor.getString(4));
//                        pojopaytax.setId(cursor.getString(5));
//
//
//                        Log.e("Checknow", "" + cursor.getString(0));
//
//                        paylist.add(pojopaytax);
//                        // array2.add(cursor.getString(11));
//                        // Log.e("VAL",""+cursor.getString(11));
//                        recyclerView.setAdapter(adapter3);
//                    } while (cursor.moveToNext());
//
//                }

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

        lsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner.getSelectedItem().toString().equalsIgnoreCase("Select Payment Mode")) {
                    // Toast.makeText(Paymentlist.this, "Select Paymode", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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
                } else if (eamtpaid.getText().toString().equalsIgnoreCase("")) {
                    // Toast.makeText(Paymentlist.this, "Enter Amount Paid", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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

                } else if (erefno.getText().toString().equalsIgnoreCase("")) {
                    //Toast.makeText(Paymentlist.this, "Enter Reference No", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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

                } else if (epdate.getText().toString().equalsIgnoreCase("")) {
                    //Toast.makeText(Paymentlist.this, "Select Date", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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

                } else if (ck == 1 && chno.getText().toString().equalsIgnoreCase("")) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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
                } else {

                    if (paymodecode.equalsIgnoreCase("QCD_AEPS_PAYTM")) {

                        getorderID();

//                            final Handler handler = new Handler(Looper.getMainLooper());
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    //Do something after 10 sec
//
//                                }
//                            }, 10000);
                    } else if (paymodecode.equalsIgnoreCase("QCD_AEPS_AEPS")) {

                        getorderid_aeps();

                        /*Intent intent   = new Intent(Paymentlist.this, Host.class);
                        //intent.putExtra("username","R005155");

                        intent.putExtra("username", "R005155");
                        intent.putExtra("amount", "100");
                        intent.putExtra("txntype", "1");
                        intent.putExtra("clienttxnid","har1234");


                        startActivityForResult(intent,1000);*/
                    } else {
                        callpayment();
                    }

                }

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

                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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
                } catch (Exception e) {

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

    private void searchlist() {
        recyclerViewp.setLayoutManager(new LinearLayoutManager(Paymentlist.this));
        adapterp = new Paymentlist.MyRecyclerViewAdapterp(Paymentlist.this, invoicelist);
        invoicelist.clear();
        JSONObject user = new JSONObject();
        try {


            user.put("orgnId", sharedpreferences.getString("oc1", ""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc", ""));
            user.put("localeId", "en_US");


            Log.e("OUTPUTINVOICELIST", "" + user.toString());
        } catch (Exception e) {
            Log.e("OUTPUTORDERID", "" + Log.getStackTraceString(e));
            Log.e("OUTPUT", "" + user.toString());
        }


        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "api/ICDInvoice/InvoiceList", user,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCCINVOICE", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONArray obj2 = obj.getJSONArray("list");
                            Log.e("INNNN",""+obj2.length());
                            for (int i = 0; i < obj2.length(); i++) {
                                JSONObject object = obj2.getJSONObject(i);
                                pojoPayment pojoPayment = new pojoPayment();
                                Log.e("INNNN",""+object.getString("out_invoice_rowid"));

                                pojoPayment.setId(object.getString("out_invoice_rowid"));
                                pojoPayment.setInno2(object.getString("out_invoice_no"));
                                pojoPayment.setBal(object.getString("out_totalinvoice_amount"));
                                pojoPayment.setAmt(object.getString("out_balance_amount"));
                                pojoPayment.setRefno("");
                                pojoPayment.setCname(object.getString("out_customer_name"));
                                pojoPayment.setInno(object.getString("out_invoice_no"));
                                invoicelist.add(pojoPayment);
//
                                recyclerViewp.setAdapter(adapterp);
                            }
                        } catch (Exception e) {

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
        VolleySingleton.getInstance(Paymentlist.this).addToRequestQueue(stringRequest);

    }

    private void getorderid_aeps() {
        try {

            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document", userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", sharedpreferences.getString("oc1", ""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc", ""));
            user.put("localeId", "en_US");
            userd.put("context", user);
            JSONObject user2 = new JSONObject();


            user2.put("In_orgn_code", sharedpreferences.getString("oc1", ""));
            user2.put("In_invoice_no", invoiceno.getText().toString());
            user2.put("IOU_aepso_rowid", 0);
            String[] d = epdate.getText().toString().split("/");
            user2.put("In_aepso_date", d[2] + "-" + d[1] + "-" + d[0]);
            user2.put("In_channel", "M");
            user2.put("In_invoice_amount", eamtpaid.getText().toString());
            user2.put("In_balance_amount", ebal.getText().toString());
            user2.put("In_status_code", "A");
            user2.put("In_mode_flag", "I");


            user.put("Header", user2);


            Log.e("OUTPUTODERID", "" + jsonParam.toString());
        } catch (Exception e) {
            Log.e("OUTPUTORDERID", "" + Log.getStackTraceString(e));
            Log.e("OUTPUT", "" + jsonParam.toString());
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
                            orderID = "";
                            orderID = obj2.getString("In_AEPSO_Orderid");
                            Log.e("orderID", orderID);
                            erefno.setText(orderID);
                            if (!orderID.isEmpty()) {
                                Intent intent = new Intent(Paymentlist.this, Host.class);
                                //intent.putExtra("username","R005155");

                                intent.putExtra("username", aeps_merchantId);
                                Log.e("aeps_merchantId", aeps_merchantId);
                                intent.putExtra("amount", eamtpaid.getText().toString());
                                intent.putExtra("txntype", "1");
                                intent.putExtra("clienttxnid", "MEITY" + ts);


                                startActivityForResult(intent, 1000);
                            }
                        } catch (Exception e) {

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
        VolleySingleton.getInstance(Paymentlist.this).addToRequestQueue(stringRequest);


    }


    public class MyRecyclerViewAdapterp extends RecyclerView.Adapter<Paymentlist.MyRecyclerViewAdapterp.ViewHolder> implements Filterable {

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
        public Paymentlist.MyRecyclerViewAdapterp.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewpaylist2, parent, false);
            return new Paymentlist.MyRecyclerViewAdapterp.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(Paymentlist.MyRecyclerViewAdapterp.ViewHolder holder, int position) {
try{
            final pojoPayment pojoPayment = mDatafilter.get(position);
            holder.myTextView.setText(pojoPayment.getInno2());
            double x;
            double y;
            try {
                holder.bal.setText("" + (Float.parseFloat(pojoPayment.getBal())));
                holder.pbalance.setText("" + (Float.parseFloat(pojoPayment.getAmt())));
                x = Double.parseDouble(pojoPayment.getAmt());
                y = Double.parseDouble(pojoPayment.getBal());
            } catch (Exception e) {
                holder.bal.setText("" + "0.00");
                holder.pbalance.setText("" + "0.00");
                x = Double.parseDouble("0.00");
                y = Double.parseDouble("0.00");
            }

            try {
                double z = y - x;
                Log.e("VAL", "" + z);
                String f = String.valueOf(String.format("%.2f", z)).replaceAll("-", "");
                holder.ppaid.setText("" + f);
            } catch (Exception e) {
                Log.e("error", e.toString());
            }

            holder.paid.setText("" + pojoPayment.getCname());
            holder.rpay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*if (!payments.isConnected()) {
                        payments.openConnection(Paymentlist.this);
                    }*/
                    itext = pojoPayment.getInno();
                    l3.setVisibility(View.GONE);
                    l.setVisibility(View.VISIBLE);
                    l4.setVisibility(View.VISIBLE);
                    getvalues();
                    payment.setBackgroundResource(R.drawable.rbutton6);
                    l2.setVisibility(View.GONE);
                    l3.setVisibility(View.GONE);
                    l.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.GONE);
                    l4.setVisibility(View.VISIBLE);
                    header.setBackgroundResource(R.drawable.rbutton4);
                    detail.setBackgroundResource(R.drawable.rbutton4);
                    im1.setBackgroundResource(R.drawable.details);
                    im2.setBackgroundResource(R.drawable.summary);
                    im3.setBackgroundResource(R.drawable.payment2);
                    v1.setVisibility(View.GONE);
                    v2.setVisibility(View.GONE);
                    v3.setVisibility(View.VISIBLE);
                    txtheader.setText("Payment");

                    invoiceno.setText(pojoPayment.getInno2());
                    amount.setText(pojoPayment.getAmt());
                    balance.setText("" + (Float.parseFloat(pojoPayment.getBal())));
                    double x = Double.parseDouble(pojoPayment.getAmt());
                    double y = Double.parseDouble(pojoPayment.getBal());
                    pyid = pojoPayment.getId();
                    cname = pojoPayment.getCname();
                    innob = pojoPayment.getInno();
                    am = pojoPayment.getAmt();
                    final DecimalFormat amountFormate = new DecimalFormat("############.##");
                    amountFormate.setMinimumFractionDigits(2);
                    amountFormate.setMaximumFractionDigits(2);
                    double z = x - y;
                    Log.e("VAL", "" + z);
                    String f = String.valueOf(amountFormate.format(z)).replaceAll("-", "");
//
                    paid.setText("" + (Float.parseFloat(f)));
                    final SQLiteDatabase dbs = db.getWritableDatabase();
                    if (isNetworkAvailable()) {
//
//
//                        dbs.execSQL("delete from paylist where fstatus != 2");
//                        Cursor cursor2 = dbs.query("invoicelist", new String[]{"sid",
//                                }, "invoiceno" + "=?",
//                                new String[]{pojoPayment.getInno()}, null, null, null, null);
//
//                        if (cursor2.moveToFirst()) {
//                            do {

                        //Log.e("CKK", "" + cursor2.getString(0));
                        payid = pojoPayment.getId();
                        // Toast.makeText(Paymentlist.this, "id="+cursor2.getString(0), Toast.LENGTH_SHORT).show();
                        final String url = Pojokyc.icdurl + "api/ICDMOBProduct/paymentcollection?org=NKPCL/00001&locn=" + Pojokyc.instance + "&user=admin&lang=en_US&invoice_rowid=" + payid;
                        Log.e("HELLO", "" + url);

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
                                            amount.setText("" + (Float.parseFloat(bl)));

                                            double x = Double.parseDouble(payamt);
                                            double y = Double.parseDouble(bl);
                                            double z = x - y;
                                            String f = String.valueOf(amountFormate.format(z)).replaceAll("-", "");
                                            paid.setText("" + (Float.parseFloat(f)));
                                            JSONArray cast = obj.getJSONArray("Detail");
                                            for (int i = 0; i < cast.length(); i++) {


                                                JSONObject actor = cast.getJSONObject(i);
                                                String[] d = actor.getString("In_payment_date").split(" ");
                                                // Log.e("CKK",""+d[0]+"//"+invoiceno.getText().toString());
                                                String c = actor.getString("In_payment_mode_desc");
                                                String amm = actor.getString("In_paid_amount");
                                                String r = actor.getString("In_ref_no");
                                                //  Log.e("CKK",""+c+"//"+amm+"//"+r);

                                                db.paylist(itext, c, amm, r, d[0], "", "0", "1", "0", "1", "Success");

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
                        VolleySingleton.getInstance(Paymentlist.this).addToRequestQueue(getRequest);


//                            } while (cursor2.moveToNext());
//                        }
                    }


                }
            });
        }catch(Exception e){

        }

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
                    FilterResults filterResults = null;

                    try {
                        Log.e("NNNNN", "I am Called");
                        String charString = charSequence.toString().toLowerCase();
                        if (charString.isEmpty()) {

                            mDatafilter = mData;
                        } else {
                            List<pojoPayment> filteredList = new ArrayList<>();
                            for (pojoPayment row : mData) {
                                Log.e("NNNNN", "I am Called" + charSequence);

                                if (row.getCname().toLowerCase().startsWith(String.valueOf(charSequence))) {
                                    filteredList.add(row);
                                }


                            }

                            mDatafilter = filteredList;

                            Log.e("Check", "" + mDatafilter.toString());


                        }


                        filterResults = new FilterResults();
                        filterResults.values = mDatafilter;

                    }catch (Exception e){

                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    recyclerViewp.getRecycledViewPool().clear();
                    try {
                        mDatafilter = (ArrayList<pojoPayment>) filterResults.values;
                    }catch (Exception e){

                    }
                    notifyDataSetChanged();
                }
            };
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView myTextView, amt, bal, paid, ppaid, pbalance;
            RelativeLayout rpay;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.itm);
                bal = itemView.findViewById(R.id.amount);

                amt = itemView.findViewById(R.id.bal);

                paid = itemView.findViewById(R.id.paid);
                ppaid = itemView.findViewById(R.id.ppaid);
                pbalance = itemView.findViewById(R.id.pbalance);

                rpay = itemView.findViewById(R.id.rpay);
            }


        }

        // convenience method for getting data at click position


        // allows clicks events to be caught


        // parent activity will implement this method to respond to click events

    }

    public class MyRecyclerViewAdapter3 extends RecyclerView.Adapter<Paymentlist.MyRecyclerViewAdapter3.ViewHolder> {

        private List<pojopaytax> mData;
        private LayoutInflater mInflater;

        // data is passed into the constructor
        MyRecyclerViewAdapter3(Context context, List<pojopaytax> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public Paymentlist.MyRecyclerViewAdapter3.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewpayment, parent, false);
            return new Paymentlist.MyRecyclerViewAdapter3.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(Paymentlist.MyRecyclerViewAdapter3.ViewHolder holder, int position) {
            pojopaytax pojopaytax = mData.get(position);
            holder.myTextView.setText(pojopaytax.getPaymode());
            holder.rn.setText(pojopaytax.getRefno());
            holder.dt.setText(pojopaytax.getDate());
            holder.a.setText(pojopaytax.getAmt());
            holder.sts.setText(pojopaytax.getMsg());
            if (pojopaytax.getMsg().equalsIgnoreCase("Pending")) {
                holder.statusapi.setVisibility(View.VISIBLE);
            }

            holder.statusapi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (paytmbluetooth.equalsIgnoreCase("1")) {
                        orderID = pojopaytax.getRefno();
                        ap = pojopaytax.getAmt();
                        paylistid = pojopaytax.getId();
                        pdt = pojopaytax.getDate();
                        paymodecode = "QCD_AEPS_PAYTM";

                        Toast.makeText(Paymentlist.this, "Checking Payment Status", Toast.LENGTH_SHORT).show();
                        salestatus = 6;
                        StatusCheckRequest.Builder builder = new StatusCheckRequest.Builder()
                                .setMerchantId((TextUtils.isEmpty(merchantId) ? null : merchantId))
                                .setOrderId(pojopaytax.getRefno());
                        // .setPrintInfo("printInfo://values?merchantTxnId=82938938983&caNumber=34567777&billNumber=xyz123")
                        //  .setGstInformation("gstInformation://values?gstIn=08TESTF0078P1ZP&gstBrkUp=CGST:10|SGST:10|IGST:10|CESS:10|GSTIncentive:10|GSTPCT:10&invoiceNo="+invoiceno.getText().toString()+"&invoiceDate=20220720142919")


                        StatusCheckRequest statusCheckRequest = builder.build();
                        String statusCheckRequestRequestId = String.valueOf(System.currentTimeMillis());
                        com.paytm.ecr.bluetooth.sdk.model.Request<StatusCheckRequest> request = com.paytm.ecr.bluetooth.sdk.model.Request.of(statusCheckRequest, statusCheckRequestRequestId);
                        payments.doStatusCheck(request, Paymentlist.this);
                    } else {
                        Toast.makeText(Paymentlist.this, "EDC Machine Not Connected", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView myTextView, rn, dt, a, sts;
            RelativeLayout rpay;
            Button statusapi;


            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.pm);
                rn = itemView.findViewById(R.id.rn);
                dt = itemView.findViewById(R.id.dt);
                a = itemView.findViewById(R.id.a);
                sts = itemView.findViewById(R.id.sts);
                statusapi = itemView.findViewById(R.id.statusapi);

            }


        }

        // convenience method for getting data at click position

        // allows clicks events to be caught


        // parent activity will implement this method to respond to click events

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void savep() {


        try {
            DecimalFormat amountFormate = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            String formattedDate = df.format(cc);
            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document", userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", sharedpreferences.getString("oc", ""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc", ""));
            user.put("localeId", "en_US");
            userd.put("context", user);
            JSONObject user2 = new JSONObject();
            String[] d1;
            if (epdate.getText().toString().equalsIgnoreCase("")) {
                d1 = pdt.toString().split("/");
            } else {
                d1 = epdate.getText().toString().split("/");
            }


            user2.put("IOU_invoice_rowid", payid);
            user2.put("IOU_invoice_no", payno);
            user2.put("In_invoice_date", paydate);
            user2.put("In_invoice_amount", payamt);
            if (ebal.getText().toString().equalsIgnoreCase("")) {
                user2.put("In_balance_amount", amount.getText().toString());
            } else {
                user2.put("In_balance_amount", ebal.getText().toString());
            }
            user2.put("In_payment_mode", paymodecode);
            if (paymodecode.equalsIgnoreCase("QCD_AEPS_PAYTM") || paymodecode.equalsIgnoreCase("QCD_AEPS_AEPS")) {
                user2.put("In_ref_no", orderID);
            } else {
                user2.put("In_ref_no", erefno.getText().toString());
            }
            user2.put("In_payment_date", d1[2] + "/" + d1[1] + "/" + d1[0]);
            if (eamtpaid.getText().toString().equalsIgnoreCase("")) {
                user2.put("In_payment_amount", ap);
            } else {
                user2.put("In_payment_amount", eamtpaid.getText().toString());
            }

            user2.put("In_payment_response", "Success");
            user2.put("In_status_code", "A");
            user2.put("In_row_timestamp", formattedDate);
            user2.put("In_mode_flag", "I");
            user2.put("In_check_no", cqno);

            user.put("Header", user2);

            JSONArray notebookUsers2 = new JSONArray();

            JSONObject user4 = new JSONObject();
            user4.put("In_paymentcollection_rowid", "0");
            user4.put("In_payment_type", "");
            user4.put("In_payment_no", payno);
            if (ebal.getText().toString().equalsIgnoreCase("")) {
                user4.put("In_balance_amount", amount.getText().toString());
            } else {
                user4.put("In_balance_amount", ebal.getText().toString());
            }
            if (eamtpaid.getText().toString().equalsIgnoreCase("")) {
                user4.put("In_payment_amount", ap);
            } else {
                user4.put("In_payment_amount", eamtpaid.getText().toString());
            }
            user4.put("In_paym  ent_mode", paymodecode);
            if (erefno.getText().toString().equalsIgnoreCase("")) {
                user4.put("In_ref_no", orderID);
            } else {
                user4.put("In_ref_no", erefno.getText().toString());
            }

            user4.put("In_payment_date", d1[2] + "/" + d1[1] + "/" + d1[0]);
            user4.put("In_process_status", "A");
            if (eamtpaid.getText().toString().equalsIgnoreCase("")) {
                user4.put("In_paid_amount", "" + ap);
            } else {
                user4.put("In_paid_amount", "" + eamtpaid.getText().toString());
            }

            user4.put("In_mode_flag", "" + "I");


            notebookUsers2.put(user4);


            user.put("Detail", notebookUsers2);

            Log.e("OUTPUTINVOICE", "" + jsonParam.toString());
        } catch (Exception e) {
            Log.e("OUTPUTINVOICE", "" + Log.getStackTraceString(e));
            Log.e("OUTPUT", "" + jsonParam.toString());
        }


        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();


        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "api/ICDMOBInvoice/mobnewsavePaymentCollection", jsonParam,
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
//                            SQLiteDatabase dbs = db.getWritableDatabase();
                        // Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
//                            dbs.execSQL("UPDATE invoicelist SET flag = 1 WHERE id = "+id2);
//                            dbs.execSQL("UPDATE invoicelist SET sid = "+inrid+" WHERE id = "+id2);
//                            Log.e("CCCC","Invoice");
                        // finish();
                        SQLiteDatabase dbs = db.getWritableDatabase();
                       // dbs.execSQL("UPDATE paylist SET uflag = 1");
                        try {

                            userd = new JSONObject();
                            userd.put("senderid", "SMSTST");
                            userd.put("msg_desc", "Thanks for your purchase from " + sharedpreferences.getString("rn", "") + " for INR " + (Float.parseFloat(payamt)) + " against Invoice " + payno + " dated " + paydate + " We have received payment of INR " + (Float.parseFloat(paidamt)) + " and the Balance payable is " + (Float.parseFloat(paybalamt)));
                            userd.put("mobile_no", "8124873354");


                            Log.e("OUTPUT", "" + userd.toString());

                        } catch (Exception e) {
                            Log.e("OUTPUT", "" + e.getMessage());
                        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();


                        HttpsTrustManager.allowAllSSL();
                        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "api/SendSMS/SendSMS", userd,
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
                        VolleySingleton.getInstance(Paymentlist.this).addToRequestQueue(stringRequest);
//                        final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
////set icon
//                                .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                .setTitle("Success!")
////set message
//                                .setMessage("Payment Completed!")
////set positive button
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                //set what would happen when positive button is clicked
//
//                                                finish();
//
//                                            }
//                                        }
////set negative button
//
//                                )
//                                .show();

                        if (paymodecode.equalsIgnoreCase("QCD_AEPS_PAYTM")) {
                            saveStatus("1");
                        } else if (paymodecode.equalsIgnoreCase("QCD_AEPS_AEPS")) {
                            aepso_saveStatus("1");
                        } else {
                            final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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
//                        final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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
//                                //.show();
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

    private void updateLabel2() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        if (sdf.format(myCalendar.getTime()).toString().compareTo(edate.getText().toString()) >= 0) {

            epdate.setText(sdf.format(myCalendar.getTime()));
        } else {
            epdate.setText("");
            final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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

    public void getvalues() {
        List<String> categories = new ArrayList<String>();

        categories.add("Non Member");
        categories.add("Member");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerm.setAdapter(dataAdapter);
        spinnerm.setEnabled(false);
        final SQLiteDatabase dbs = db.getWritableDatabase();
        Cursor cursor2 = dbs.query("invoicelist", new String[]{"date", "supplier", "location", "tax", "transport", "others", "invoiceamount", "phone", "mem", "ids", "inweb"
                }, "invoiceno" + "=?",
                new String[]{itext}, null, null, null, null);

        if (cursor2.moveToFirst()) {
            do {
                // Log.e("HELLO",""+cursor2.getString(8));

                einwamt.setText(cursor2.getString(6));
                edate.setText(cursor2.getString(0));
                elocation.setText(cursor2.getString(2));
                esupplier.setText(cursor2.getString(7));
                esuppliern.setText(cursor2.getString(1));
                einwno.setText(cursor2.getString(10));
                etax.setText(cursor2.getString(3));
                etransport.setText(cursor2.getString(4));
                eothers.setText(cursor2.getString(5));

                if (cursor2.getString(2).equalsIgnoreCase("odisha")) {
                    ss = "0";
                } else {
                    ss = "1";
                }

                final String ii = cursor2.getString(9);
                String nid = ii;
                String nid2 = nid.substring(1, nid.length() - 1);
                String[] id = nid2.split(",");
                productlist.clear();

                double tl, tl2 = 0, tc = 0, tc2 = 0;
                for (int i = 0; i < id.length; i++) {

                    String selectQuery = "SELECT  * FROM productlist2 where id =" + id[i];
                    Cursor cursor = dbs.rawQuery(selectQuery, null);
                    if (cursor.moveToFirst()) {
                        do {
                            tl = Double.parseDouble(cursor.getString(6));
                            tl2 += tl;
                            tc = Double.parseDouble(cursor.getString(2));
                            tc2 += tc;

                            etotal.setText("" + (tl2));
                            newnet.setText("" + (tl2));
                            ecount.setText(" " + (tc2));

                        } while (cursor.moveToNext());

                    }

                }

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog = new Dialog(Paymentlist.this);
                        dialog.setContentView(R.layout.viewitem);
                        dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                        dialog.setTitle("Title...");
                        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);

                        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                        // set the custom dialog components - text, image and button


                        androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Paymentlist.this));
                        adapter = new Paymentlist.MyRecyclerViewAdapter(Paymentlist.this, productlist);
                        String nid = ii;
                        String nid2 = nid.substring(1, nid.length() - 1);
                        String[] id = nid2.split(",");
                        productlist.clear();


                        for (int i = 0; i < id.length; i++) {
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
                            EditText ecount2 = (EditText) dialog.findViewById(R.id.ecount);
                            EditText etotal2 = (EditText) dialog.findViewById(R.id.etotal);
                            ecount2.setText("" + ecount.getText().toString());
                            etotal2.setText("" + etotal.getText().toString());

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
                });

                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (etax.getText().toString().equalsIgnoreCase("")) {
                            ta = Double.parseDouble(newnet.getText().toString());
                        } else {
                            ta = Double.parseDouble(newnet.getText().toString()) + Double.parseDouble(etax.getText().toString());
                        }
                        Intent i = new Intent(getApplicationContext(), Viewstockinward.class);
                        i.putExtra("FRM", "1");
                        i.putExtra("T", etransport.getText().toString());
                        i.putExtra("O", eothers.getText().toString());
                        i.putExtra("BT", "2");
                        i.putExtra("II", ii);
                        i.putExtra("ss", ss);
                        i.putExtra("ta", String.valueOf(ta));
                        startActivity(i);
                        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    }
                });
                view3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2 = new Dialog(Paymentlist.this);
                        dialog2.setContentView(R.layout.viewtaxd);
                        dialog2.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                        dialog2.setTitle("Title...");
                        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);

                        dialog2.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                        // set the custom dialog components - text, image and button

                        // set up the RecyclerView
                        androidx.recyclerview.widget.RecyclerView recyclerView = dialog2.findViewById(R.id.itm);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Paymentlist.this));
                        adapter2 = new Paymentlist.MyRecyclerViewAdapter2(Paymentlist.this, producttaxlist);


                        producttaxlist.clear();

                        String nid = ii;
                        String nid2 = nid.substring(1, nid.length() - 1);
                        String[] id = nid2.split(",");


                        for (int i = 0; i < id.length; i++) {
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

                                            pojoProducttax.setCgst("" + (Float.parseFloat(cursor2.getString(0))));
                                            pojoProducttax.setSgst("" + (Float.parseFloat(cursor2.getString(1))));
                                            pojoProducttax.setIgst("" + (Float.parseFloat(cursor2.getString(2))));
                                            pojoProducttax.setHsn(cursor2.getString(3));


                                        }
                                        while (cursor2.moveToNext());
                                    }


                                    Log.e("Check", "" + cursor.getString(1));


                                    producttaxlist.add(pojoProducttax);
                                    recyclerView.setAdapter(adapter2);

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
                });
                if (cursor2.getString(8).equalsIgnoreCase("QCD_CUS_MEM")) {
                    spinnerm.setSelection(1);
                } else {
                    spinnerm.setSelection(0);
                }

            } while (cursor2.moveToNext());
        }
    }


    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<Paymentlist.MyRecyclerViewAdapter.ViewHolder> {

        private List<pojoProduct> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<pojoProduct> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public Paymentlist.MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewitemlist, parent, false);
            return new Paymentlist.MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(Paymentlist.MyRecyclerViewAdapter.ViewHolder holder, final int position) {
            final pojoProduct pojoProduct = mData.get(position);
            holder.myTextView.setText(pojoProduct.getItem());
            holder.tamt.setText(pojoProduct.getAmt());
            holder.tnamt.setText(pojoProduct.getNamt());
            holder.tqty.setText(pojoProduct.getQty());
            holder.trate.setText(pojoProduct.getRate());
            holder.tdis.setText(pojoProduct.getDis());

            holder.del.setVisibility(View.GONE);
            holder.ed.setVisibility(View.GONE);


            holder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    db.deleteProductlist2(Integer.valueOf(pojoProduct.getId()));
                    productlist.remove(position);
                    adapter.notifyDataSetChanged();
                    // counttotal();

                }
            });
            holder.ed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //setflag = 1;
                    dialog.dismiss();
                    // badd.setText("Update");

                    //item.setText(pojoProduct.getItem());
                    erate.setText(pojoProduct.getRate());
                    equantity.setText(pojoProduct.getQty());
                    eamount.setText(pojoProduct.getAmt());
                    enetamount.setText(pojoProduct.getNamt());
                    ediscount.setText(pojoProduct.getDis());
                    //  prid = pojoProduct.getId();


                }
            });
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView myTextView, trate, tamt, tnamt, tdis, tqty;
            ImageView del, ed;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.t1);
                tamt = itemView.findViewById(R.id.amt);
                tnamt = itemView.findViewById(R.id.namt);
                tdis = itemView.findViewById(R.id.dis);
                trate = itemView.findViewById(R.id.rate);
                tqty = itemView.findViewById(R.id.qty);

                del = (ImageView) itemView.findViewById(R.id.del);
                ed = (ImageView) itemView.findViewById(R.id.ed);


            }


        }

        // convenience method for getting data at click position


        // parent activity will implement this method to respond to click events

    }

    public class MyRecyclerViewAdapter2 extends RecyclerView.Adapter<Paymentlist.MyRecyclerViewAdapter2.ViewHolder> {

        private List<pojoProducttax> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapter2(Context context, List<pojoProducttax> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public Paymentlist.MyRecyclerViewAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewtax, parent, false);
            return new Paymentlist.MyRecyclerViewAdapter2.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(Paymentlist.MyRecyclerViewAdapter2.ViewHolder holder, int position) {
            pojoProducttax pojoProducttax = mData.get(position);
            if (ss.equalsIgnoreCase("")) {

            } else if (ss.equalsIgnoreCase("0")) {
                // holder.lcg.setVisibility(View.VISIBLE);
                // holder.lsg.setVisibility(View.VISIBLE);
                // holder.ltax.setVisibility(View.GONE);
                holder.myTextView.setText(pojoProducttax.getItem());
                holder.cgst.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
                holder.sgst.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
                holder.igst.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
                try {
                    String[] vl = pojoProducttax.getHsn().split("_");
                    holder.hsn.setText(vl[2]);
                } catch (Exception e) {
                    holder.hsn.setText(pojoProducttax.getHsn());
                }
                holder.cgst.setText(pojoProducttax.getCgst());
                holder.sgst.setText(pojoProducttax.getSgst());
                holder.igst.setText("0");
                double c = Double.parseDouble(holder.cgst.getText().toString());
                double s = Double.parseDouble(holder.sgst.getText().toString());

                double c2 = Double.parseDouble(holder.cgst.getText().toString());
                double ca = Double.parseDouble(pojoProducttax.getNamt()) / 100;
                double ca2 = (c2 * ca);
                holder.cgst2.setText("" + (ca2));
                double s2 = Double.parseDouble(holder.sgst.getText().toString());
                double sa = Double.parseDouble(pojoProducttax.getNamt()) / 100;
                double sa2 = (s2 * sa);
                holder.sgst2.setText("" + (sa2));
                double rl = Double.parseDouble(pojoProducttax.getNamt());

                double cg = ((c / 100) * rl);
                double sg = ((s / 100) * rl);
                double f = (cg + sg);
                double ff = c + s;
                DecimalFormat amountFormate = new DecimalFormat("############.##");
                amountFormate.setMinimumFractionDigits(2);
                amountFormate.setMaximumFractionDigits(2);

                holder.tamt.setText("" + (f));
                holder.tamt1.setText("" + (rl));

            } else if (ss.equalsIgnoreCase("1")) {
                if (pojoProducttax.getIgst().equalsIgnoreCase("0.0000")) {
                    holder.myTextView.setText(pojoProducttax.getItem());
                    holder.cgst.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
                    holder.sgst.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
                    holder.igst.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});

                    try {
                        String[] vl = pojoProducttax.getHsn().split("_");
                        holder.hsn.setText(vl[2]);
                    } catch (Exception e) {
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
                    double ff = c + s;
                    DecimalFormat amountFormate = new DecimalFormat("############.##");
                    amountFormate.setMinimumFractionDigits(2);
                    amountFormate.setMaximumFractionDigits(2);

                    holder.tamt.setText("" + (f));
                    holder.tamt1.setText("" + (rl));
                } else {

                    //  holder.lcg.setVisibility(View.GONE);
                    // holder.lsg.setVisibility(View.GONE);
                    holder.myTextView.setText(pojoProducttax.getItem());
                    holder.cgst.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
                    holder.sgst.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
                    holder.igst.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2)});
                    try {
                        String[] vl = pojoProducttax.getHsn().split("_");
                        holder.hsn.setText(vl[2]);
                    } catch (Exception e) {
                        holder.hsn.setText(pojoProducttax.getHsn());
                    }
                    holder.cgst.setText("0");
                    holder.sgst.setText("0");
                    holder.igst.setText(pojoProducttax.getIgst());
                    double c = Double.parseDouble(holder.cgst.getText().toString());
                    double s = Double.parseDouble(holder.sgst.getText().toString());
                    double i = Double.parseDouble(holder.igst.getText().toString());

                    double sa = Double.parseDouble(pojoProducttax.getNamt()) / 100;
                    double sa2 = (i * sa);
                    holder.igst2.setText("" + (sa2));
                    double rl = Double.parseDouble(pojoProducttax.getNamt());

                    double cg = (c / 100) * rl;
                    double sg = (s / 100) * rl;
                    double ig = (i / 100) * rl;
                    double f = (ig);
                    double ff = i;
                    DecimalFormat amountFormate = new DecimalFormat("############.##");
                    amountFormate.setMinimumFractionDigits(2);
                    amountFormate.setMaximumFractionDigits(2);

                    holder.tamt.setText("" + (f));
                    holder.tamt1.setText("" + (rl));
                }
            }
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView myTextView, hsn, tamt, cgst, sgst, igst, tamt1, cgst2, sgst2, igst2;
            LinearLayout ltax, lcg, lsg;

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

    public void callpayment() {
        final SQLiteDatabase dbs = db.getWritableDatabase();
        if (isNetworkAvailable()) {
//            String checkquery = "SELECT  * FROM paylist where uflag = 0";
//            Cursor ckcursor = dbs.rawQuery(checkquery, null);
//            // Toast.makeText(Paymentlist.this, ""+ckcursor.getCount(), Toast.LENGTH_SHORT).show();
//            if (ckcursor.getCount() != 0) {
//                final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
////set icon
//                        .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                        .setTitle("Alert!")
////set message
//                        .setMessage("There is a some data not synced with server please perform Transaction Sync to proceed further!")
////set positive button
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        //set what would happen when positive button is clicked
//                                        finish();
//
//                                    }
//                                }
////set negative button
//
//                        )
//                        .show();
//            } else {
//
//                db.updateinvoice(Integer.parseInt(pyid.toString()), innob.toString(), ebal.getText().toString(), balance.getText().toString(), "", "", "", cname.toString(), "0");
//
//                if (ck == 0) {
//
//
//                    db.paylist(itext, paymodecode, eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "0", "0", "1", "Success");
//                } else {
//                    //  Log.e("HELLOONE",""+spinner.getSelectedItem().toString());
//                    db.paylist(itext, paymodecode, eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "0", chno.getText().toString(), "1", "Success");
//
//                }
//                // Toast.makeText(Paymentlist.this, "Successfully submitted", Toast.LENGTH_SHORT).show();
//                l3.setVisibility(View.VISIBLE);
//                l.setVisibility(View.GONE);
//                l4.setVisibility(View.GONE);
//                //finish();
//
//                Cursor cursorfc = dbs.query("invoice", new String[]{"balance", "amount", "paymode", "refno", "date"
//                        }, "invoiceno" + "=?",
//                        new String[]{itext}, null, null, null, null);
//
//                if (cursorfc.moveToFirst()) {
//                    do {
//
//                        paybalamt = cursorfc.getString(0);
//
//
//                        Cursor cursorfc2 = dbs.query("paylist", new String[]{"amountpaid", "refno", "date", "paymode", "ckno"
//                                }, "invoiceno" + "=?",
//                                new String[]{itext}, null, null, null, null);
//
//                        if (cursorfc2.moveToLast()) {
//
//
//                            refno = cursorfc2.getString(1);
//                            paidamt = cursorfc2.getString(0);
//                            paymode = cursorfc2.getString(3);
//                            payindate = cursorfc2.getString(2);
//                            cqno = cursorfc2.getString(4);
//
//
//                            savep();
//                        }
//
//
//                    }
//
//                    while (cursorfc.moveToNext());
//
//
//                }
//            }

            savep();

        } else {
//            db.updateinvoice(Integer.parseInt(pyid.toString()), innob.toString(), ebal.getText().toString(), balance.getText().toString(), "", "", "", cname.toString(), "0");
//
//            if (ck == 0) {
//
//
//                db.paylist(itext, paymodecode, eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "0", "0", "1", "Success");
//            } else {
//                db.paylist(itext, paymodecode, eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "0", chno.getText().toString(), "1", "Success");
//
//            }
//            // Toast.makeText(Paymentlist.this, "Successfully submitted", Toast.LENGTH_SHORT).show();
//            l3.setVisibility(View.VISIBLE);
//            l4.setVisibility(View.GONE);
//            l.setVisibility(View.GONE);
//            final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
////set icon
//                    .setIcon(android.R.drawable.ic_menu_save)
////set title
//                    .setTitle("Success!")
////set message
//                    .setMessage("Payment Completed")
////set positive button
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    //set what would happen when positive button is clicked
//                                    finish();
//
//                                }
//                            }
////set negative button
//
//                    )
//                    .show();
        }
    }

    public void getorderID() {
        try {
            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document", userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", sharedpreferences.getString("oc1", ""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc", ""));
            user.put("localeId", "en_US");
            userd.put("context", user);
            JSONObject user2 = new JSONObject();
            user2.put("In_orgn_code", sharedpreferences.getString("oc1", ""));
            user2.put("In_invoice_no", invoiceno.getText().toString());
            user2.put("IOU_po_rowid", 0);
            String[] d = epdate.getText().toString().split("/");
            user2.put("In_po_date", d[2] + "-" + d[1] + "-" + d[0]);
            user2.put("In_channel", "M");
            user2.put("In_invoice_amount", eamtpaid.getText().toString());
            user2.put("In_balance_amount", ebal.getText().toString());
            user2.put("In_status_code", "A");
            user2.put("In_mode_flag", "I");
            user.put("Header", user2);
            Log.e("OUTPUTODERID", "" + jsonParam.toString());
        } catch (Exception e) {
            Log.e("OUTPUTORDERID", "" + Log.getStackTraceString(e));
            Log.e("OUTPUT", "" + jsonParam.toString());
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
                            orderID = "";
                            orderID = obj2.getString("In_PO_Orderid");
                            dialogpayment = new Dialog(Paymentlist.this);
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
                            txttimer = dialogpayment.findViewById(R.id.txttimer);
                            Button lcancel = dialogpayment.findViewById(R.id.bcancel);
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
                                    payments.doCancel(request, Paymentlist.this);
                                    // Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
                                    dialogpayment.dismiss();
                                }
                            });


                            dialogpayment.show();
                            int ppaam = Integer.parseInt(eamtpaid.getText().toString()) * 100;
                            SaleRequest.Builder builder = new SaleRequest.Builder()
                                    .setMerchantId((TextUtils.isEmpty(merchantId) ? null : merchantId))
                                    .setOrderId(orderID)
                                    // .setPrintInfo("printInfo://values?ProductName=PADDY0078P1ZP&QTY=2")
                                    //.setGstInformation("gstInformation://values?gstIn=08TESTF0078P1ZP&gstBrkUp=CGST:10|SGST:10|IGST:10|CESS:10|GSTIncentive:10|GSTPCT:10&invoiceNo="+invoiceno.getText().toString()+"&invoiceDate=20220720142919")
                                    .setAmount(String.valueOf(ppaam));

                            SaleRequest saleRequest = builder.build();
                            String saleRequestId = String.valueOf(System.currentTimeMillis());
                            com.paytm.ecr.bluetooth.sdk.model.Request<SaleRequest> request = com.paytm.ecr.bluetooth.sdk.model.Request.of(saleRequest, saleRequestId);
                            payments.doSale(request, Paymentlist.this);
                            final Handler handler = new Handler(Looper.getMainLooper());
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Do something after 10 sec
                                    if (salestatus == 0) {
                                        dialogpayment.dismiss();
                                        salestatus = 9;
                                        db.paylist(itext, "PAYTM", eamtpaid.getText().toString(), orderID, epdate.getText().toString(), "", "0", "1", "0", "2", "Pending");

                                        final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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
                        } catch (Exception e) {

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
        VolleySingleton.getInstance(Paymentlist.this).addToRequestQueue(stringRequest);


    }

    @Override
    public void onStatusUpdated(ConnectionState connectionState) {
        Log.e("connectionState", String.valueOf(connectionState));
        Toast.makeText(this, "Success :" + connectionState, Toast.LENGTH_SHORT).show();
        if (connectionState.toString().equalsIgnoreCase("CONNECTED")) {
            lsave.setEnabled(true);
            sconnecton.setBackgroundResource(R.drawable.eblue);
            lsave.setBackgroundResource(R.drawable.rbutton3);
        } else {
            sconnecton.setBackgroundResource(R.drawable.grayblue);
            lsave.setEnabled(false);
            lsave.setBackgroundResource(R.drawable.rbutton);
        }
    }

    @Override
    public void onError(ConnectionError connectionError) {
        Toast.makeText(this, "Error :" + connectionError, Toast.LENGTH_SHORT).show();

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
                    erefno.setText(tx[1].substring(1, tx[1].length() - 1));

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
                        payments.doStatusCheck(request, Paymentlist.this);

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
//
//                         )
//                         .show();
                    } else {
                        salestatus = 15;

                        StatusCheckRequest.Builder builder = new StatusCheckRequest.Builder()
                                .setMerchantId((TextUtils.isEmpty(merchantId) ? null : merchantId))
                                .setOrderId(orderID);
                        // .setPrintInfo("printInfo://values?merchantTxnId=82938938983&caNumber=34567777&billNumber=xyz123")
                        //  .setGstInformation("gstInformation://values?gstIn=08TESTF0078P1ZP&gstBrkUp=CGST:10|SGST:10|IGST:10|CESS:10|GSTIncentive:10|GSTPCT:10&invoiceNo="+invoiceno.getText().toString()+"&invoiceDate=20220720142919")


                        StatusCheckRequest statusCheckRequest = builder.build();
                        String statusCheckRequestRequestId = String.valueOf(System.currentTimeMillis());
                        com.paytm.ecr.bluetooth.sdk.model.Request<StatusCheckRequest> request = com.paytm.ecr.bluetooth.sdk.model.Request.of(statusCheckRequest, statusCheckRequestRequestId);
                        payments.doStatusCheck(request, Paymentlist.this);
                    }
                } catch (Exception e) {

                    dialogpayment.dismiss();


                    ;
                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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
                final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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
            } else if (salestatus == 6) {

                SQLiteDatabase dbs = db.getWritableDatabase();
                Log.e("SALE API3", "" + response.toString());
                dialog3.dismiss();
                try {

                    String[] res = response.toString().split(",");
                    Log.e("TXTid", "" + res[3]);

                    String[] tx = res[3].split("=");
                    String[] scode = res[41].split("=");
                    // erefno.setText(tx[1].substring( 1, tx[1].length() - 1 ));

                    if (scode[1].substring(1, scode[1].length() - 1).equalsIgnoreCase("101")) {
                       // dbs.execSQL("update paylist set msg = 'Success' , fstatus = '1' where id = '" + paylistid + "'");
                        saleresponse = response.toString();

                        savep();
                    } else {
                        String[] emsg = res[42].split("=");
                      //  dbs.execSQL("update paylist set msg = '" + emsg[1] + "' , fstatus = '1' where id = '" + paylistid + "'");
                        saleresponse = response.toString();
                        saveStatus("2");

                        final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("INFO!")
//set message
                                .setMessage(emsg[1] + "!")
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

                        // db.paylist(itext, "PAYTM", eamtpaid.getText().toString(), orderID, epdate.getText().toString(), "", "0","1","0","2",""+emsg[1].substring( 1, emsg[1].length() - 1 ));


                    }
                } catch (Exception e) {

                    dialogpayment.dismiss();


                    ;
                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
//set icon
                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                            .setTitle("INFO!")
//set message
                            .setMessage("Payment Not Completed")
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
            } else if (salestatus == 15) {
                String[] res = response.toString().split(",");
                String[] emsg = res[42].split("=");
                saleresponse = response.toString();
                saveStatus("2");
                db.paylist(itext, "PAYTM", eamtpaid.getText().toString(), orderID, epdate.getText().toString(), "", "0", "1", "0", "2", "" + emsg[1].substring(1, emsg[1].length() - 1));
                dialogpayment.dismiss();
                final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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
                            Toast.makeText(Paymentlist.this,val, Toast.LENGTH_SHORT).show();

                }else if(val.equalsIgnoreCase("ERROR")){
                    paytmbluetooth = "0";

                    String[] scode = res[2].split("=");

                    String[] emsg = scode[1].split("=");
                    saleresponse = response.toString();

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(Paymentlist.this, emsg[0], Toast.LENGTH_SHORT).show();

                        }
                    });


                }


            }else {
                successstatus = "0";
                Log.e("SALE API2", "" + response.toString());
                saleresponse = response.toString();
                savep();
            }
        } catch (Exception e) {

        }


    }

    public void saveStatus(String status) {
        try {

            String[] res = saleresponse.toString().split(",");
            Log.e("TXTid", "" + res[3]);

            String[] tx = res[3].split("=");
            String[] scode = res[41].split("=");
            Log.e("VAL1", "" + scode[1]);
            String[] smsg = res[42].split("=");
            Log.e("VAL2", "" + smsg[1]);
            String[] abank = res[39].split("=");
            Log.e("VAL3", "" + abank[1]);
            String[] svcreated = res[36].split("=");
            Log.e("VAL4", "" + svcreated[1]);
            String[] boapplied = res[33].split("=");
            Log.e("VAL5", "" + boapplied[1]);
            String[] bmid = res[21].split("=");
            Log.e("VAL6", "" + bmid[1]);
            String[] btid = res[22].split("=");
            Log.e("VAL7", "" + btid[1]);
            String[] brscode = res[20].split("=");
            Log.e("VAL8", "" + brscode[1]);
            String[] cschme = res[19].split("=");
            Log.e("VAL9", "" + cschme[1]);
            String[] ctype = res[18].split("=");
            Log.e("VAL10", "" + ctype[1]);
            String[] pmethod = res[17].split("=");
            Log.e("VAL11", "" + pmethod[1]);
            String[] aid = res[16].split("=");
            Log.e("VAL12", "" + aid[1]);
            String[] tid = res[15].split("=");
            Log.e("VAL13", "" + tid[1]);
            String[] ttype = res[10].split("=");
            Log.e("VAL14", "" + ttype[1]);
            String[] issuebank = res[9].split("=");
            Log.e("VAL15", "" + issuebank[1]);
            String[] cno = res[8].split("=");
            Log.e("VAL16", "" + cno[1]);
            String[] rrn = res[7].split("=");
            Log.e("VAL17", "" + rrn[1]);
            String[] aucode = res[3].split("=");
            Log.e("VAL18", "" + aucode[1]);
            String[] txid = res[2].split("=");
            String[] inno = res[12].split("=");
            Log.e("VAL19", "" + txid[1]);


            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document", userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", sharedpreferences.getString("oc1", ""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc", ""));
            user.put("localeId", "en_US");
            userd.put("context", user);
            JSONObject user2 = new JSONObject();


            user2.put("IOU_pth_rowid", 0);
            user2.put("In_merchantId", merchantId);
            user2.put("In_orderId", orderID);
            if (epdate.getText().toString().equalsIgnoreCase("")) {
                String[] d = pdt.toString().split("/");
                user2.put("In_txnId", tx[1].substring(1, tx[1].length() - 1));
            } else {
                String[] d = epdate.getText().toString().split("/");
                user2.put("In_txnId", tx[1].substring(1, tx[1].length() - 1));
            }


            user2.put("In_authCode", "");
            user2.put("In_rrn", rrn[1].substring(1, rrn[1].length() - 1));
            user2.put("In_cardNo", cno[1].substring(1, cno[1].length() - 1));
            user2.put("In_issuingBank", issuebank[1].substring(1, issuebank[1].length() - 1));
            if (eamtpaid.getText().toString().equalsIgnoreCase("")) {
                user2.put("In_amount", ap);
            } else {
                user2.put("In_amount", eamtpaid.getText().toString());
            }

            user2.put("In_txnType", "PAYTM");
            user2.put("In_invoiceNumber", inno[1].substring(1, inno[1].length() - 1));
            user2.put("In_extendInfo", "");
            user2.put("In_printInfo", "");
            user2.put("In_tid", tid[1].substring(1, tid[1].length() - 1));
            user2.put("In_aid", aid[1].substring(1, aid[1].length() - 1));
            user2.put("In_payMethod", pmethod[1].substring(1, pmethod[1].length() - 1));
            user2.put("In_cardType", ctype[1].substring(1, ctype[1].length() - 1));
            user2.put("In_cardScheme", cschme[1].substring(1, cschme[1].length() - 1));
            user2.put("In_bankResponseCode", brscode[1].substring(1, brscode[1].length() - 1));
            user2.put("In_bankMid", bmid[1].substring(1, bmid[1].length() - 1));
            user2.put("In_bankTid", btid[1].substring(1, btid[1].length() - 1));
            user2.put("In_productManufacturer", "");
            user2.put("In_productCategory", "");
            user2.put("In_productSerialNoType", "");
            user2.put("In_productSerialNoValue", "");
            user2.put("In_productName", "");
            user2.put("In_emiTxnType", "");
            user2.put("In_emiTenure", "");
            user2.put("In_emiInterestRate", "");
            user2.put("In_emiMonthlyAmount", "");
            user2.put("In_emiTotalAmount", "");
            user2.put("In_bankOfferApplied", boapplied[1].substring(1, boapplied[1].length() - 1));
            user2.put("In_bankOfferType", "");
            user2.put("In_bankOfferAmount", "");
            user2.put("In_subventionCreated", svcreated[1].substring(1, svcreated[1].length() - 1));
            user2.put("In_subventionType", "");
            user2.put("In_subventionOfferAmount", "");
            user2.put("In_acquiringBank", "");
            user2.put("In_virtualPaymentAddress", "");
            user2.put("In_statusCode", scode[1].substring(1, scode[1].length() - 1));
            user2.put("In_statusMessage", smsg[1].substring(1, smsg[1].length() - 2));
            user2.put("In_mode_flag", "I");


            user.put("Header", user2);


            Log.e("OUTPUTSALE", "" + jsonParam.toString());
        } catch (Exception e) {
            Log.e("OUTPUTSALE", "" + Log.getStackTraceString(e));
            Log.e("OUTPUT", "" + jsonParam.toString());
        }


        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "api/ICDMOB_PAYTM/PaymentHistoryResponseSave", jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCCHISTORY", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("Header");

                            orderID = obj2.getString("IOU_pth_rowid");
                            //Toast.makeText(Paymentlist.this, ""+status, Toast.LENGTH_SHORT).show();
                            if (!orderID.equalsIgnoreCase("0")) {
                                try {
                                    dialogpayment.dismiss();
                                } catch (Exception e) {

                                }

                                if (status.equalsIgnoreCase("1")) {
                                    db.updateinvoice(Integer.parseInt(pyid.toString()), innob.toString(), ebal.getText().toString(), balance.getText().toString(), "", "", "", cname.toString(), "0");

                                    db.paylist(itext, paymodecode, eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "1", "0", "1", "Success");

                                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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

                        } catch (Exception e) {

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
        VolleySingleton.getInstance(Paymentlist.this).addToRequestQueue(stringRequest);


    }

    public void checkconnection() {
        if (paytmbluetooth.equalsIgnoreCase("1")) {
            //Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
            lsave.setEnabled(true);
            sconnecton.setBackgroundResource(R.drawable.eblue);
            lsave.setBackgroundResource(R.drawable.rbutton3);
        } else {
            //Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show();
            sconnecton.setBackgroundResource(R.drawable.grayblue);
            lsave.setEnabled(false);
            lsave.setBackgroundResource(R.drawable.rbutton);
        }
    }

    public void reverseTimer(int Seconds) {

        new CountDownTimer(Seconds * 1000 + 1000, 1000) {

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
            Log.e("aeps_message", String.valueOf(message));

            Toast.makeText(getApplicationContext(), "" + message, Toast.LENGTH_SHORT).show();

            if (response == 1) {
                showdialog("Info", "Success");

                savep();
            } else if (response == 0) {
                showdialog("Info", message);
                aepso_saveStatus("2");
            }


        } else {
            Toast.makeText(getApplicationContext(), "Empty Data", Toast.LENGTH_SHORT).show();
        }


    }

    private void aepso_saveStatus(String status) {
        try {


            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document", userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", sharedpreferences.getString("oc1", ""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc", ""));
            user.put("localeId", "en_US");
            userd.put("context", user);
            JSONObject user2 = new JSONObject();


            user2.put("IOU_pth_rowid", 0);
            user2.put("In_merchantId", aeps_merchantId);
            user2.put("In_orderId", orderID);

            user2.put("In_txnId", "" + txnrefrenceid);


            user2.put("In_authCode", "");
            user2.put("In_rrn", "");
            user2.put("In_cardNo", "");
            user2.put("In_issuingBank", "");

            user2.put("In_amount", eamtpaid.getText().toString());


            user2.put("In_txnType", "AEPS");
            user2.put("In_invoiceNumber", invoiceno.getText().toString());
            user2.put("In_extendInfo", "");
            user2.put("In_printInfo", "");

            user2.put("txnrefrenceid", 0);

            user2.put("In_aid", "");
            user2.put("In_payMethod", "AADHAR PAY");
            user2.put("In_cardType", "");
            user2.put("In_cardScheme", "");
            user2.put("In_bankResponseCode", "" + response);
            user2.put("In_bankMid", "");
            user2.put("In_bankTid", "");
            user2.put("In_productManufacturer", "");
            user2.put("In_productCategory", "");
            user2.put("In_productSerialNoType", "");
            user2.put("In_productSerialNoValue", "");
            user2.put("In_productName", "");
            user2.put("In_emiTxnType", "");
            user2.put("In_emiTenure", "");
            user2.put("In_emiInterestRate", "");
            user2.put("In_emiMonthlyAmount", "");
            user2.put("In_emiTotalAmount", "");
            user2.put("In_bankOfferApplied", "");
            user2.put("In_bankOfferType", "");
            user2.put("In_bankOfferAmount", "");
            user2.put("In_subventionCreated", "");
            user2.put("In_subventionType", "");
            user2.put("In_subventionOfferAmount", "");
            user2.put("In_acquiringBank", "");
            user2.put("In_virtualPaymentAddress", "");
            user2.put("In_statusCode", "" + response);
            user2.put("In_statusMessage", message);
            user2.put("In_mode_flag", "I");


            user.put("Header", user2);


            Log.e("OUTPUTSALE", "" + jsonParam.toString());
        } catch (Exception e) {
            Log.e("OUTPUTSALE", "" + Log.getStackTraceString(e));
            Log.e("OUTPUT", "" + jsonParam.toString());
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
                            if (!orderID.equalsIgnoreCase("0")) {
                                /*try {
                                    dialogpayment.dismiss();
                                } catch (Exception e) {

                                }

                                if (status.equalsIgnoreCase("1")) {
                                   db.updateinvoice(Integer.parseInt(pyid.toString()), innob.toString(), ebal.getText().toString(), balance.getText().toString(), "", "", "", cname.toString(), "0");

                                    db.paylist(itext, paymodecode, eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "1","0","1","Success");

                                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
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
                        } catch (Exception e) {

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
        VolleySingleton.getInstance(Paymentlist.this).addToRequestQueue(stringRequest);


    }

    public void showdialog(String title, String message) {
        final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
//set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                .setTitle(title + "!")
//set message
                .setMessage(message + "!")
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
