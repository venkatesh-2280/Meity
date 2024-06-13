package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;
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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

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

public class Paymentlist2 extends AppCompatActivity {
    public LinearLayout l1, l2, l3, l4,l44;
    Calendar myCalendar;
    List<pojopaytax> paylist;
    TextView fponame, name;
    String cname;
    int ck = 0;
    Spinner spinner;
    LinearLayout l;
    String cqno = "0";
    String ss ="0";
    TextView txtheader;
    RelativeLayout rdetail,rsummary,rpayment;
    ImageView im1,im2,im3;
    View v1,v2,v3;
    JSONObject userd;
    int ta;
    List<pojoProducttax> producttaxlist;
    Paymentlist2.MyRecyclerViewAdapter2 adapter2;
    AutoCompleteTextView esupplier,esupplier2;
    JSONObject jsonParam;
    Paymentlist2.MyRecyclerViewAdapter3 adapter3;
    String payid, payno, payinamt, paybalamt, paymode, refno, paydate, payamt, paidamt, payindate;
    EditText edate,eamount,ediscount,enetamount,erate,eitem,equantity,etotal,ecount,elocation,etax,etransport,eothers,einwno,einwamt;

    androidx.recyclerview.widget.RecyclerView recyclerViewp;
    AutoCompleteTextView esuppliern;
    Paymentlist2.MyRecyclerViewAdapterp adapterp;
    EditText chno;
    Paymentlist2.MyRecyclerViewAdapter adapter;
    EditText eamtpaid, erefno, epdate, ebal, inputsearch,newnet;
    Button view, view3, view2, viewp, lsave;
    Spinner spinnerm, spinnerl;
    ImageView back;
    int button = 0;
    ProgressDialog pdialog;
    List<pojoProduct> productlist;
    String pyid, innob, am;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    TextView invoiceno, amount, balance, paid;
    String itext;
    Button detail,header,payment;


    Dialog dialog, dialog2, dialog3;

    DBHelper db;
    LinearLayout lch;

    List<pojoPayment> invoicelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentlist2);
        getSupportActionBar().hide();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        fponame = (TextView) findViewById(R.id.fponame);
        name = (TextView) findViewById(R.id.name);
        db = new DBHelper(this);
        myCalendar = Calendar.getInstance();
        final SQLiteDatabase dbs = db.getWritableDatabase();
        fponame.setText(sharedpreferences.getString("rn", ""));
        name.setText("Welcome " + sharedpreferences.getString("un", ""));
        invoicelist = new ArrayList<>();
        inputsearch = (EditText) findViewById(R.id.inputSearch);
        paylist = new ArrayList<>();
        l=(LinearLayout)findViewById(R.id.l);
        productlist = new ArrayList<>();
        spinnerm = (Spinner)findViewById(R.id.spinnerm);
        pdialog = new ProgressDialog(this);
        detail = (Button)findViewById(R.id.detail);
        header = (Button)findViewById(R.id.header);
        payment = (Button)findViewById(R.id.payment);
        l3 = (LinearLayout) findViewById(R.id.l3);
        l4 = (LinearLayout) findViewById(R.id.l44);
        l1 = (LinearLayout) findViewById(R.id.l1);
        l44 = (LinearLayout) findViewById(R.id.l4);
        l2 = (LinearLayout) findViewById(R.id.l2);
        rdetail = (RelativeLayout)findViewById(R.id.rdetail);
        rsummary = (RelativeLayout)findViewById(R.id.rsummary);
        rpayment = (RelativeLayout)findViewById(R.id.rpayment);
        rpayment.setEnabled(true);

        im1 = (ImageView)findViewById(R.id.im1);
        im2 = (ImageView)findViewById(R.id.im2);
        im3 = (ImageView)findViewById(R.id.im3);

        v1 = (View)findViewById(R.id.v1);
        v2 = (View)findViewById(R.id.v2);
        v3 = (View)findViewById(R.id.v3);

        txtheader = (TextView)findViewById(R.id.txtheader);
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
          spinner = (Spinner) findViewById(R.id.spinner);
        List<String> categories = new ArrayList<String>();
        categories.add("Select Payment Mode");
        categories.add("CASH");
        categories.add("CHEQUE");
        categories.add("CARD");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Paymentlist2.this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        lsave = (Button)findViewById(R.id.lsave);
        viewp = (Button)findViewById(R.id.viewp);
        back = (ImageView) findViewById(R.id.back);
        invoiceno = (TextView) findViewById(R.id.invoiceno);
        amount = (TextView) findViewById(R.id.balance);
        balance = (TextView) findViewById(R.id.amount);
        paid = (TextView) findViewById(R.id.paid);
        esuppliern = (AutoCompleteTextView) findViewById(R.id.esuppliern);
        esupplier = (AutoCompleteTextView) findViewById(R.id.esupplier);
        recyclerViewp = findViewById(R.id.ptm);
        erate = (EditText)findViewById(R.id.erate);
        eamount = (EditText)findViewById(R.id.eamount);
        enetamount = (EditText)findViewById(R.id.enetamount);
        ediscount = (EditText)findViewById(R.id.ediscount);
        newnet = (EditText)findViewById(R.id.newnet);
        equantity = (EditText)findViewById(R.id.equantity);
        etotal = (EditText)findViewById(R.id.etotal);
        ecount = (EditText)findViewById(R.id.ecount);
        elocation = (EditText)findViewById(R.id.elocation);
        etax = (EditText)findViewById(R.id.etax);
        etransport = (EditText)findViewById(R.id.etransport);
        eothers = (EditText)findViewById(R.id.eothers);
        view = (Button)findViewById(R.id.view);
        view3 = (Button)findViewById(R.id.view3);
        view2 = (Button)findViewById(R.id.view2);
        edate = (EditText)findViewById(R.id.eddate);
        einwamt = (EditText)findViewById(R.id.einwamt);
        einwno = (EditText)findViewById(R.id.einwno);
        l3 = (LinearLayout) findViewById(R.id.l3);
        l4 = (LinearLayout) findViewById(R.id.l44);
        chno = (EditText)findViewById(R.id.chno);
        eamtpaid = (EditText)findViewById(R.id.eamountpaid);
        erefno = (EditText)findViewById(R.id.erefno);
        lch = (LinearLayout)findViewById(R.id.lch);
        ebal = (EditText)findViewById(R.id.ebalance);
        epdate = (EditText)findViewById(R.id.epdate);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l4.setVisibility(View.GONE);
                l3.setVisibility(View.VISIBLE);l.setVisibility(View.GONE);

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
                DatePickerDialog datePickerDialog = new DatePickerDialog(Paymentlist2.this,android.R.style.Theme_Holo_Dialog , date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        eamtpaid.addTextChangedListener(new TextWatcher() {

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


                if(s.length() != 0)
                {
                    double x = Double.parseDouble(amount.getText().toString());
                    double y = Double.parseDouble(eamtpaid.getText().toString());
                    if(y>x)
                    {
                        eamtpaid.setText("");
                        // Toast.makeText(Paymentlist2.this, "Invalid amount", Toast.LENGTH_SHORT).show();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist2.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Balance Amount is "+x)
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
                        double z = x - y;
                        DecimalFormat amountFormate = new DecimalFormat("############.##");
                        amountFormate.setMinimumFractionDigits(2);
                        amountFormate.setMaximumFractionDigits(2);
                        // einwamt.setText(""+fi));
                        ebal.setText("" + z);
                    }
                }
                else
                {
                    //  getAmt2();
                    ebal.setText("");
                    //  erate.setText("");
                }

            }
        });
        recyclerViewp.setLayoutManager(new LinearLayoutManager(Paymentlist2.this));
        adapterp = new Paymentlist2.MyRecyclerViewAdapterp(Paymentlist2.this, invoicelist);
        invoicelist.clear();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if(position == 1)
                {

                    lch.setVisibility(View.GONE);
                    ck=0;

                }
                else if(position == 2)
                {
                    lch.setVisibility(View.VISIBLE);
                    ck=1;

                }
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

        String selectQuery = "SELECT  * FROM inward";

        Cursor cursor = dbs.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                pojoPayment pojoPayment = new pojoPayment();

                String id = cursor.getString(0);

                pojoPayment.setId(cursor.getString(0));

                String[] v = cursor.getString(1).split("DIV");

                pojoPayment.setInno(v[1]);
                pojoPayment.setInno2(v[0]);
                pojoPayment.setBal("" + Float.parseFloat(cursor.getString(2)));
                pojoPayment.setAmt("" + Float.parseFloat(cursor.getString(3)));
                pojoPayment.setRefno(cursor.getString(5));
                pojoPayment.setCname(cursor.getString(7));




                Log.e("Check1", "" + cursor.getString(1));
                Log.e("Check2", "" + cursor.getString(2));
                Log.e("Check3", "" + cursor.getString(3));
                Log.e("Check4", "" + cursor.getString(5));
                Log.e("Check5", "" + cursor.getString(7));

                invoicelist.add(pojoPayment);
                // array2.add(cursor.getString(11));
                // Log.e("VAL",""+cursor.getString(11));
                recyclerViewp.setAdapter(adapterp);
            } while (cursor.moveToNext());

        }
        viewp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3 = new Dialog(Paymentlist2.this);
                dialog3.setContentView(R.layout.viewpaylist);
                dialog3.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog3.setTitle("Title...");
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);

                dialog3.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                // set the custom dialog components - text, image and button

                // set up the RecyclerView
                androidx.recyclerview.widget.RecyclerView recyclerView = dialog3.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(Paymentlist2.this));
                adapter3 = new Paymentlist2.MyRecyclerViewAdapter3(Paymentlist2.this, paylist);

                Cursor cursor = dbs.query("paylist2", new String[]{"paymode",
                                "date", "refno", "amountpaid"}, "invoiceno" + "=?",
                        new String[]{itext}, null, null, null, null);
                paylist.clear();
                //   Cursor cursor = dbs.rawQuery(selectQuery, null);
                Log.e("CCK", "C" + cursor.getCount());

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {

                        pojopaytax pojopaytax = new pojopaytax();

                        //   String id = cursor.getString(0);
                        pojopaytax.setPaymode(cursor.getString(0));
                        pojopaytax.setAmt("" + Float.parseFloat(cursor.getString(3)));
                        pojopaytax.setRefno(cursor.getString(2));
                        pojopaytax.setDate(cursor.getString(1));



                        Log.e("Checknow", "" + cursor.getString(0));

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

        lsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinner.getSelectedItem().toString().equalsIgnoreCase("Select Payment Mode"))
                {
                    // Toast.makeText(Paymentlist2.this, "Select Paymode", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist2.this)
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
                    // Toast.makeText(Paymentlist2.this, "Enter Amount Paid", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist2.this)
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
                    //Toast.makeText(Paymentlist2.this, "Enter Reference No", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist2.this)
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
                    //Toast.makeText(Paymentlist2.this, "Select Date", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist2.this)
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
                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist2.this)
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
                    if (isNetworkAvailable()) {
                        String checkquery = "SELECT  * FROM paylist2 where uflag = 0";
                        Cursor ckcursor = dbs.rawQuery(checkquery, null);
                        // Toast.makeText(Paymentlist2.this, ""+ckcursor.getCount(), Toast.LENGTH_SHORT).show();
                        if(ckcursor.getCount() != 0)
                        {
                            final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist2.this)
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

                            db.updateinward(Integer.parseInt(pyid.toString()), itext.toString(), ebal.getText().toString(), balance.getText().toString(), amount.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), cname, "0");

                            if (ck == 0) {


                                db.paylist2(itext, spinner.getSelectedItem().toString(), eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "0", "0");
                            } else {
                                Log.e("HELLO", "" + spinner.getSelectedItem().toString());
                                db.paylist2(itext, spinner.getSelectedItem().toString(), eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "0", chno.getText().toString());

                            }
                            // Toast.makeText(Paymentlist2.this, "Successfully submitted", Toast.LENGTH_SHORT).show();
                            l3.setVisibility(View.VISIBLE);
                            l.setVisibility(View.GONE);
                            l4.setVisibility(View.GONE);
                            //finish();

                            savep();
                        }
                    }
                    else
                    {
//                        db.updateinvoice(Integer.parseInt(pyid.toString()), innob.toString(), ebal.getText().toString(), balance.getText().toString(), "", "", "", cname.toString(), "0");
//
//                        if(ck == 0) {
//
//
//                            db.paylist(itext, spinner.getSelectedItem().toString(), eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "0","0");
//                        }
//                        else
//                        {
//                            db.paylist(itext, spinner.getSelectedItem().toString(), eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "0",chno.getText().toString());
//
//                        }
//                        // Toast.makeText(Paymentlist2.this, "Successfully submitted", Toast.LENGTH_SHORT).show();
//                        l3.setVisibility(View.VISIBLE);
//                        l4.setVisibility(View.GONE);
//                        l.setVisibility(View.GONE);
//                        final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist2.this)
////set icon
//                                .setIcon(android.R.drawable.ic_menu_save)
////set title
//                                .setTitle("Success!")
////set message
//                                .setMessage("Payment Completed")
////set positive button
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                //set what would happen when positive button is clicked
//                                                finish();
//
//                                            }
//                                        }
////set negative button
//
//                                )
//                                .show();
                    }
                }

            }
        });


    }

    public class MyRecyclerViewAdapterp extends RecyclerView.Adapter<Paymentlist2.MyRecyclerViewAdapterp.ViewHolder> implements Filterable {

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
        public Paymentlist2.MyRecyclerViewAdapterp.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewpaylist2p, parent, false);
            return new Paymentlist2.MyRecyclerViewAdapterp.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(Paymentlist2.MyRecyclerViewAdapterp.ViewHolder holder, int position) {
            final pojoPayment pojoPayment = mDatafilter.get(position);
            holder.myTextView.setText(pojoPayment.getInno2());
            holder.bal.setText("" + Float.parseFloat(pojoPayment.getBal()));
            holder.pbalance.setText("" + Float.parseFloat(pojoPayment.getAmt()));
            double x = Double.parseDouble(pojoPayment.getAmt());
            double y = Double.parseDouble(pojoPayment.getBal());

            try {
                double z = y - x;
                Log.e("VAL", "" + z);
                String f = String.valueOf(z).replaceAll("-", "");
                holder.ppaid.setText(""+f);
            }
            catch (Exception e)
            {

            }

            holder.paid.setText("" + pojoPayment.getCname());
            cname =  pojoPayment.getCname();
            holder.rpay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    itext = pojoPayment.getInno2()+"DIV"+pojoPayment.getInno();
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
                    balance.setText("" + Float.parseFloat(pojoPayment.getBal()));
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
                    paid.setText("" + Float.parseFloat(f));
                    final SQLiteDatabase dbs = db.getWritableDatabase();
                    if (isNetworkAvailable()) {


                        dbs.execSQL("delete from paylist2");



                                // Toast.makeText(Paymentlist2.this, "id="+cursor2.getString(0), Toast.LENGTH_SHORT).show();
                                final String url = Pojokyc.icdurl+"api/ICDStockInward/Inwardpaymentcollection?org=NKPCL/00001&locn="+Pojokyc.instance+"&user=admin&lang=en_US&invoice_rowid=" + pojoPayment.getInno();
                                Log.e("HELLO",""+url);

// prepare the Request
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

                                                        db.paylist2(itext, c, amm, r, d[0], "", "0", "1", "0");

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
                                VolleySingleton.getInstance(Paymentlist2.this).addToRequestQueue(getRequest);




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
                            Log.e("NNNNN", "I am Called" + charSequence);

                            if (row.getCname().toLowerCase().startsWith(String.valueOf(charSequence))) {
                                filteredList.add(row);
                            }


                        }

                        mDatafilter = filteredList;

                        Log.e("Check", "" + mDatafilter.toString());


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
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView myTextView, amt, bal, paid,ppaid,pbalance;
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
    public class MyRecyclerViewAdapter3 extends RecyclerView.Adapter<Paymentlist2.MyRecyclerViewAdapter3.ViewHolder> {

        private List<pojopaytax> mData;
        private LayoutInflater mInflater;

        // data is passed into the constructor
        MyRecyclerViewAdapter3(Context context, List<pojopaytax> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public Paymentlist2.MyRecyclerViewAdapter3.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewpayment, parent, false);
            return new Paymentlist2.MyRecyclerViewAdapter3.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(Paymentlist2.MyRecyclerViewAdapter3.ViewHolder holder, int position) {
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


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void savep()
    {


        try {
            DecimalFormat amountFormate  = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
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
            String[] v = itext.split("DIV");


            user2.put("IOU_invoice_rowid",0);
            user2.put("IOU_invoice_no",v[0]);
            user2.put("In_invoice_date",epdate.getText().toString());
            user2.put("In_invoice_amount",Double.parseDouble(eamtpaid.getText().toString()));
            user2.put("In_balance_amount",Double.parseDouble(ebal.getText().toString()));
            user2.put("In_payment_mode","QCD_AEPS_"+spinner.getSelectedItem().toString());
            user2.put("In_ref_no",erefno.getText().toString());
            user2.put("In_payment_date",epdate.getText().toString());
            user2.put("In_payment_amount",Double.parseDouble(eamtpaid.getText().toString()));
            user2.put("In_payment_response","Success");
            user2.put("In_status_code","A");
            user2.put("In_row_timestamp",formattedDate);
            user2.put("In_mode_flag","I");
            user2.put("In_check_no",cqno);

            user.put("Header",user2);

            JSONArray notebookUsers2 = new JSONArray();

            JSONObject user4 = new JSONObject();
            user4.put("In_paymentcollection_rowid",0);
            user4.put("In_payment_type","");
            user4.put("In_payment_no",v[0]);
            user4.put("In_balance_amount",Double.parseDouble(ebal.getText().toString()));
            user4.put("In_payment_amount",Double.parseDouble(eamtpaid.getText().toString()));
            user4.put("In_payment_mode","QCD_AEPS_"+spinner.getSelectedItem().toString());
            user4.put("In_ref_no",erefno.getText().toString());
            user4.put("In_payment_date",epdate.getText().toString());
            user4.put("In_process_status","A");
            user4.put("In_paid_amount",Double.parseDouble(eamtpaid.getText().toString()));
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


        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.icdurl+"api/ICDStockInward/new_inward_payment",jsonParam,
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
                        dbs.execSQL("UPDATE paylist2 SET uflag = 1");
//
                        final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist2.this)
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
                                .show();

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
                        final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist2.this)
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
    private void updateLabel2() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        epdate.setText(sdf.format(myCalendar.getTime()));
    }
    public void getvalues()
    {
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
        Cursor cursor2 = dbs.query("invoicelist", new String[]{"date","supplier","location","tax","transport","others","invoiceamount","phone","mem","ids","inweb"
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

                if(cursor2.getString(2).equalsIgnoreCase("odisha"))
                {
                    ss = "0";
                }
                else
                {
                    ss="1";
                }

                final String ii = cursor2.getString(9);
                String nid = ii;
                String nid2 = nid.substring(1, nid.length() - 1);
                String[] id = nid2.split(",");
                productlist.clear();

                double tl,tl2=0,tc=0,tc2=0;
                for(int i = 0 ; i< id.length;i++) {

                    String selectQuery = "SELECT  * FROM productlist2 where id =" + id[i];
                    Cursor cursor = dbs.rawQuery(selectQuery, null);
                    if (cursor.moveToFirst()) {
                        do {
                            tl = Double.parseDouble(cursor.getString(6));
                            tl2  +=  tl ;
                            tc = Double.parseDouble(cursor.getString(2));
                            tc2  +=  tc ;

                            etotal.setText(""+tl2);
                            newnet.setText(""+tl2);
                            ecount.setText(" "+tc2);

                        } while (cursor.moveToNext());

                    }

                }

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog = new Dialog(Paymentlist2.this);
                        dialog.setContentView(R.layout.viewitem);
                        dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                        dialog.setTitle("Title...");
                        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);

                        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                        // set the custom dialog components - text, image and button


                        androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Paymentlist2.this));
                        adapter = new Paymentlist2.MyRecyclerViewAdapter(Paymentlist2.this, productlist);
                        String nid = ii;
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
                        }
                    }
                });

                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(etax.getText().toString().equalsIgnoreCase(""))
                        {
                            ta = Integer.parseInt(newnet.getText().toString());
                        }
                        else
                        {
                            ta = Integer.parseInt(newnet.getText().toString())  + Integer.parseInt(etax.getText().toString());
                        }
                        Intent i = new Intent(getApplicationContext(),Viewstockinward.class);
                        i.putExtra("FRM","1");
                        i.putExtra("T",etransport.getText().toString());
                        i.putExtra("O",eothers.getText().toString());
                        i.putExtra("BT","2");
                        i.putExtra("II",ii);
                        i.putExtra("ss",ss);
                        i.putExtra("ta",String.valueOf(ta));
                        startActivity(i);
                        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    }
                });
                view3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2 = new Dialog(Paymentlist2.this);
                        dialog2.setContentView(R.layout.viewtaxd);
                        dialog2.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                        dialog2.setTitle("Title...");
                        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);

                        dialog2.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                        // set the custom dialog components - text, image and button

                        // set up the RecyclerView
                        androidx.recyclerview.widget.RecyclerView recyclerView = dialog2.findViewById(R.id.itm);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Paymentlist2.this));
                        adapter2 = new Paymentlist2.MyRecyclerViewAdapter2(Paymentlist2.this, producttaxlist);


                        producttaxlist.clear();

                        String nid = ii;
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
                if(cursor2.getString(8).equalsIgnoreCase("QCD_CUS_MEM"))
                {
                    spinnerm.setSelection(1);
                }
                else
                {
                    spinnerm.setSelection(0);
                }

            } while (cursor2.moveToNext());
        }
    }


    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<Paymentlist2.MyRecyclerViewAdapter.ViewHolder> {

        private List<pojoProduct> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<pojoProduct> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public Paymentlist2.MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewitemlist, parent, false);
            return new Paymentlist2.MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(Paymentlist2.MyRecyclerViewAdapter.ViewHolder holder, final int position) {
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

    public class MyRecyclerViewAdapter2 extends RecyclerView.Adapter<Paymentlist2.MyRecyclerViewAdapter2.ViewHolder> {

        private List<pojoProducttax> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapter2(Context context, List<pojoProducttax> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public Paymentlist2.MyRecyclerViewAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewtax, parent, false);
            return new Paymentlist2.MyRecyclerViewAdapter2.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(Paymentlist2.MyRecyclerViewAdapter2.ViewHolder holder, int position) {
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
                double c = Double.parseDouble(holder.cgst.getText().toString());
                double s = Double.parseDouble(holder.sgst.getText().toString());

                double c2 = Double.parseDouble(holder.cgst.getText().toString());
                double ca = Double.parseDouble(pojoProducttax.getNamt())/100;
                double ca2 = c2*ca;
                holder.cgst2.setText(""+ca2);
                double s2 = Double.parseDouble(holder.sgst.getText().toString());
                double sa = Double.parseDouble(pojoProducttax.getNamt())/100;
                double sa2 = s2*sa;
                holder.sgst2.setText(""+sa2);
                double rl = Double.parseDouble(pojoProducttax.getNamt());

                double cg = (c/100) * rl;
                double sg = (s/100) * rl;
                double f = cg+sg;
                double ff = c+s;
                DecimalFormat amountFormate  = new DecimalFormat("############.##");
                amountFormate.setMinimumFractionDigits(2);
                amountFormate.setMaximumFractionDigits(2);

                holder.tamt.setText(""+f);
                holder.tamt1.setText("" + rl);

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

                    holder.tamt.setText("" + f);
                    holder.tamt1.setText("" + rl);
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
                    holder.igst2.setText(""+sa2);
                    double rl = Double.parseDouble(pojoProducttax.getNamt());

                    double cg = (c / 100) * rl;
                    double sg = (s / 100) * rl;
                    double ig = (i / 100) * rl;
                    double f = ig;
                    double ff = i;
                    DecimalFormat amountFormate = new DecimalFormat("############.##");
                    amountFormate.setMinimumFractionDigits(2);
                    amountFormate.setMaximumFractionDigits(2);

                    holder.tamt.setText("" + f);
                    holder.tamt1.setText("" + rl);
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
}