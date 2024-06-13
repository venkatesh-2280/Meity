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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
;

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

public class StockAdjustment extends AppCompatActivity {
    ImageView list;
    Button  view,add,save;
    LinearLayout llrp;
    ArrayList<String> arraylrp = new ArrayList<>();
    ArrayList<String> array1 = new ArrayList<>();
    ArrayList<String> array1n = new ArrayList<>();
    AutoCompleteTextView elrp;
    MyRecyclerViewAdapter adapter;
    Spinner spinner;
    String lrp;
    Dialog dialog;
    String idn,cq,rqty;
    ProgressDialog pdialog;
    String id;
    String lc2,aq;
    String inn;
    Calendar myCalendar;
    TextView txt4;
    String lc;
    int ct=0,ct2=0,ctt=0,ct22=0,c3=0,ct3=0;
    LinearLayout lsn,lcn;
    JSONObject jsonParam;
    String at,atno,aqc,aqcd,aqsc,aqsc2,aqpc,aqpd,aqu,aqi,aqd,aqpco,aqi2,aty,aqid,inwid;

    String sname,slcn,sst,encodedimage="",ids,farco,ids2,tax,oth,trs,net,pr,pd,pt,pa,pq,pn,pc,psc,pco,pu,amt,idd,bp,balamt;
    String sname2,slcn2,sst2,encodedimage2="",ids22,ids222,tax2,oth2,trs2,net2,pr2,pd2,pt2,pa2,pq2,pn2,pc2,psc2,pco2,pu2,amt2,id2;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    EditText estock,eaqty,erqty,ecount;
    AutoCompleteTextView einwno,eicd;
    AutoCompleteTextView eitem,sn,cn;
    int f = 0;
    ArrayList<String> array,array2,array3,array4,array5,array3s,array3c;
    ArrayAdapter<String> dataAdapter,dataAdapterl;
    List<pojoSA> productlist;
    LinearLayout ltype;
    ArrayList<String>  mStringList= new ArrayList<String>();
    DBHelper db;
    TextView fponame,name;
    EditText eddate,eadj;
    List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_adjustment);
        getSupportActionBar().hide();
        pdialog = new ProgressDialog(this);
        db =new  DBHelper(this);
        stringList = new ArrayList<>();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        productlist = new ArrayList<>();
        list = (ImageView)findViewById(R.id.list);
        view = (Button)findViewById(R.id.views);
        add = (Button)findViewById(R.id.add);
        save = (Button)findViewById(R.id.save);
       // txt4 = (TextView)findViewById(R.id.txt4);
        ltype = (LinearLayout)findViewById(R.id.ltype);
        lsn = (LinearLayout)findViewById(R.id.lsn);
        lcn = (LinearLayout)findViewById(R.id.lcn);
        llrp = (LinearLayout)findViewById(R.id.llrp);
        eadj = (EditText)findViewById(R.id.eadj);
        ltype.setVisibility(View.GONE);
        eddate = (EditText)findViewById(R.id.eddate);
        eitem = (AutoCompleteTextView) findViewById(R.id.eitem);
        elrp = (AutoCompleteTextView) findViewById(R.id.elrp);
        sn = (AutoCompleteTextView) findViewById(R.id.sn);
        cn = (AutoCompleteTextView) findViewById(R.id.cn);
        eicd = (AutoCompleteTextView) findViewById(R.id.eicd);
        eitem.setThreshold(1);
        sn.setThreshold(1);
        cn.setThreshold(1);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),VSA.class);
                i.putExtra("F","2");
                startActivity(i);
            }
        });
        estock = (EditText)findViewById(R.id.estock);

        einwno = (AutoCompleteTextView) findViewById(R.id.einwno);

        eaqty = (EditText)findViewById(R.id.eaqty);
        erqty = (EditText)findViewById(R.id.erqty);
        ecount = (EditText)findViewById(R.id.ecount);
        spinner = (Spinner)findViewById(R.id.spinner);
        eaqty.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        erqty.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("OpeningBalance");
        categories.add("PICCType");
        categories.add("PurchaseReturn");
        categories.add("SaleReturn");
        //categories.add("Transfer To LRP");
      //  categories.add("Transfer From LRP");
        array = new ArrayList<>();
        array2 = new ArrayList<>();
        array3 = new ArrayList<>();
        array3s = new ArrayList<>();
        array3c = new ArrayList<>();
        array4 = new ArrayList<>();
        array5 = new ArrayList<>();
        final SQLiteDatabase dbs = db.getWritableDatabase();
        fponame = (TextView)findViewById(R.id.fponame);
        name = (TextView)findViewById(R.id.name);
        fponame.setText(sharedpreferences.getString("rn",""));
        name.setText(sharedpreferences.getString("un",""));
        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<String>(StockAdjustment.this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
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
        eddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerDialog datePickerDialog=new DatePickerDialog(StockAdjustment.this,android.R.style.Theme_Holo_Dialog, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                //following line to restrict future date selection
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        String selectQuerys = "SELECT  * FROM supplierlist";
        Cursor cursors = dbs.rawQuery(selectQuerys, null);
        if (cursors.moveToFirst()) {
            do {

                array3s.add(cursors.getString(2)+"--"+cursors.getString(1));
                // Log.e("VAL",""+cursor.getString(11));

            } while (cursors.moveToNext());
        }

        ArrayAdapter<String> adapterlist2 = new ArrayAdapter<String>(this,
                R.layout.spinnertext, array3s);
        sn.setAdapter(adapterlist2);
        String selectQueryicd = "SELECT  * FROM icdlist";

        Cursor cursor2ic = dbs.rawQuery(selectQueryicd, null);
        if(cursor2ic.moveToFirst())
        {
            do
            {
                array1.add(cursor2ic.getString(1));
                array1n.add(cursor2ic.getString(2));


            }while(cursor2ic.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (StockAdjustment.this, R.layout.spinnertext3, array1n);
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
        String selectQuerys22 = "SELECT  * FROM customerlist";
        Cursor cursors22 = dbs.rawQuery(selectQuerys22, null);
        if (cursors22.moveToFirst()) {
            do {

                array3c.add(cursors22.getString(2)+"--"+cursors22.getString(1));
                // Log.e("VAL",""+cursor.getString(11));

            } while (cursors22.moveToNext());
            String selectQuerys2 = "SELECT  * FROM customerlistnm";
            Cursor cursors2 = dbs.rawQuery(selectQuerys2, null);
            if(cursors2.getCount()!=0) {
                if (cursors2.moveToFirst()) {
                    do {

                        array3c.add(cursors2.getString(2)+"--"+cursors2.getString(1));
                        // Log.e("VAL",""+cursor.getString(11));

                    } while (cursors2.moveToNext());
                }
            }
        }

        ArrayAdapter<String> adapterlist22 = new ArrayAdapter<String>(StockAdjustment.this,
                R.layout.spinnertext, array3c);
        cn.setAdapter(adapterlist22);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = spinner.getSelectedItem().toString();

                if(isNetworkAvailable()) {
                    if (position == 3) {
//                    pdialog.setCanceledOnTouchOutside(false);
//                    pdialog.setTitle("Loading.....");
//                    pdialog.show();
                      //  txt4.setText("Inward No");
                        einwno.setHint("Inward No");
                        lsn.setVisibility(View.VISIBLE);
                        lcn.setVisibility(View.GONE);
                        llrp.setVisibility(View.GONE);
                        ltype.setVisibility(View.VISIBLE);

                    } else if (position == 4) {

                       // txt4.setText("Invoice No");
                        einwno.setHint("Invoice No");
                        lcn.setVisibility(View.VISIBLE);
                        llrp.setVisibility(View.GONE);
                        ltype.setVisibility(View.VISIBLE);
                        lsn.setVisibility(View.GONE);

                    }
                    else if (position == 5) {
                        try {

                            llrp.setVisibility(View.VISIBLE);

                            inn = "";
                            ltype.setVisibility(View.GONE);

                        }
                        catch (Exception e)
                        {

                        }

                    }


                    else if (position == 6) {

                        try {


                            llrp.setVisibility(View.VISIBLE);
                        inn = "";
                        ltype.setVisibility(View.GONE);

                        }
                        catch (Exception e)
                        {

                        }

                    }
                    else if (position == 1) {
                        try {
                            inn = "";
                            ltype.setVisibility(View.GONE);
                            einwno.setText("0");
                            llrp.setVisibility(View.GONE);
                            lcn.setVisibility(View.GONE);
                            lsn.setVisibility(View.GONE);
                            estock.setText("" + Math.round(Float.parseFloat(cq)));
                        }
                        catch (Exception e)
                        {

                        }

                    } else if (position == 2) {
                        spinner.setSelection(0);
                        ltype.setVisibility(View.GONE);
                        final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Not Available!")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //set what would happen when positive button is clicked

                                            }
                                        }
//set negative button

                                )
                                .show();                    }
                }
                else
                {
                    if (position == 2) {
                        spinner.setSelection(0);
                        ltype.setVisibility(View.GONE);
                        final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Not Available!")
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
                    else if (position == 5) {
                        try {

                            llrp.setVisibility(View.VISIBLE);

                            inn = "";
                            ltype.setVisibility(View.GONE);

                        }
                        catch (Exception e)
                        {

                        }

                    }


                    else if (position == 6) {

                        try {


                            llrp.setVisibility(View.VISIBLE);
                            inn = "";
                            ltype.setVisibility(View.GONE);

                        }
                        catch (Exception e)
                        {

                        }

                    }
                }


            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        eicd.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //Toast.makeText(Stockinwardnew.this, ""+array1.get(position), Toast.LENGTH_SHORT).show();


                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("ocnew", array1.get(position));
                editor.putString("oc1new", array1.get(position));
                editor.commit();


                if(isNetworkAvailable())
                {
                   // dbs.execSQL("delete from product");

                    pdialog.setCanceledOnTouchOutside(false);
                    pdialog.setTitle("Loading.....");
                    pdialog.show();
                    final String url = Pojokyc.icdurl+"api/ICDMOBProduct/ICD_MOBILE_Product?org=" + sharedpreferences.getString("orgnlvlcode", "") + "&locn="+Pojokyc.instance+"&user=admin&lang=en_US";

// prepare the Request
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
                                                new String[]{sharedpreferences.getString("orgnlvlcode","")}, null, null, null, null);

                                        if(cursor.getCount()>0)
                                        {

                                            eitem.setEnabled(true);
                                            if (cursor.moveToFirst()) {
                                                do {

                                                    if(cursor.getString(0).equalsIgnoreCase(sharedpreferences.getString("orgnlvlcode","")))
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

                                            ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(StockAdjustment.this,
                                                    R.layout.spinnertext, array);
                                            eitem.setAdapter(adapterlist);


                                        }

                                        else
                                        {
                                            eitem.setEnabled(false);
                                            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
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
                    VolleySingleton.getInstance(StockAdjustment.this).addToRequestQueue(getRequest);
                }
                else
                {
                    ltype.setVisibility(View.GONE);
                    Cursor cursor = dbs.query("product", new String[]{"In_ic_code","In_product_name","In_current_qty"
                            }, "In_ic_code" + "=? COLLATE NOCASE",
                            new String[]{sharedpreferences.getString("orgnlvlcode","")}, null, null, null, null);

                    if(cursor.getCount()>0)
                    {
                        eitem.setEnabled(true);
                        if (cursor.moveToFirst()) {
                            do {

                                if(cursor.getString(0).equalsIgnoreCase(sharedpreferences.getString("orgnlvlcode","")))
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

                        ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(StockAdjustment.this,
                                R.layout.spinnertext, array);
                        eitem.setAdapter(adapterlist);

                    }

                    else
                    {
                        eitem.setEnabled(false);
                        final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
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

//        if(sharedpreferences.getString("off","").equalsIgnoreCase("1"))
//        {
//            dbs.execSQL("delete from product");
//            list.setVisibility(View.GONE);
//            pdialog.setCanceledOnTouchOutside(false);
//            pdialog.setTitle("Loading.....");
//            pdialog.show();
//            final String url = "http://169.38.77.190:949/Deployable_image_uppro_pro/api/KANCHIICD_MOBILEAPP/ICD_MOBILE_Product?org=PKFPO&locn="+Pojokyc.instance+"&user=admin&lang=en_US";
//
//// prepare the Request
//            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                    new Response.Listener<JSONObject>()
//                    {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            // display response
//                            try {
//                                JSONObject obj = response.getJSONObject("context");
//                                JSONArray cast = obj.getJSONArray("Detail");
//                                for (int i=0; i<cast.length(); i++) {
//
//
//
//                                    JSONObject actor = cast.getJSONObject(i);
//
//                                    String n1 = actor.getString("In_product_code");
//                                    String n2 = actor.getString("In_product_name");
//                                    String n3 = actor.getString("In_productcategory_code");
//                                    String n4 = actor.getString("In_productcategory_desc");
//                                    String n5 = actor.getString("In_productsubcategory_code");
//                                    String n6 = actor.getString("In_productsubcategory_desc");
//                                    String n7 = actor.getString("In_uomtype_code");
//                                    String n8 = actor.getString("In_uomtype_desc");
//                                    String n9 = actor.getString("In_hsn_code");
//                                    String n10 = actor.getString("In_hsn_desc");
//                                    String n11 = actor.getString("In_base_price");
//                                    String n12 = actor.getString("In_current_qty");
//                                    Log.e("CHK",""+actor.getString("In_cgst"));
//                                    String n13 = actor.getString("In_cgst");
//
//                                    String n14 = actor.getString("In_sgst");
//                                    String n15 = actor.getString("In_igst");
//                                    String n16 = actor.getString("In_orgn_code");
//                                    String n17 = actor.getString("In_ic_code");
//
//                                    db.insertContact(n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16,n17);
//
//
//                                }
//                                pdialog.dismiss();
//
//                                String selectQuery = "SELECT  * FROM product";
//
//                                Cursor cursor = dbs.rawQuery(selectQuery, null);
//                                if (cursor.moveToFirst()) {
//                                    do {
//
//                                        array.add(cursor.getString(2));
//                                        array2.add(cursor.getString(11));
//                                        Log.e("VAL",""+cursor.getString(11));
//
//                                    } while (cursor.moveToNext());
//                                }
//
//                                ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(StockAdjustment.this,
//                                        android.R.layout.select_dialog_item, array);
//                                eitem.setAdapter(adapterlist);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            Log.d("Response", response.toString());
//                        }
//                    },
//                    new Response.ErrorListener()
//                    {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.d("Error.Response", String.valueOf(error));
//                        }
//                    }
//            );
//
//// add it to the RequestQueue
//            getRequest.setRetryPolicy(new DefaultRetryPolicy(
//                    50000,
//                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//            VolleySingleton.getInstance(StockAdjustment.this).addToRequestQueue(getRequest);
//        }
//        else
//        {
        list.setVisibility(View.VISIBLE);
        if(isNetworkAvailable())
        {
            String checkquery = "SELECT  * FROM astock2";
            Cursor ckcursor = dbs.rawQuery(checkquery, null);
            if(ckcursor.getCount()!=0)
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
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
//                dbs.execSQL("delete from product");
//                // list.setVisibility(View.GONE);
//                pdialog.setCanceledOnTouchOutside(false);
//                pdialog.setTitle("Loading.....");
//                pdialog.show();
//                final String url = Pojokyc.icdurl+"api/ICDMOBProduct/ICD_MOBILE_Product?org=" + sharedpreferences.getString("oc", "") + "&locn="+Pojokyc.instance+"&user=admin&lang=en_US";
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
//                                    pdialog.dismiss();
//
//                                    String selectQuery = "SELECT  * FROM product";
//
//                                    Cursor cursor = dbs.rawQuery(selectQuery, null);
//                                    if (cursor.moveToFirst()) {
//                                        do {
//
//                                            if(array.contains(cursor.getString(2)))
//                                            {
//
//                                            }
//                                            else
//                                            {
//                                                array.add(cursor.getString(2));
//                                                array2.add(cursor.getString(11));
//                                            }
//
//                                        } while (cursor.moveToNext());
//                                    }
//
//                                    ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(StockAdjustment.this,
//                                            R.layout.spinnertext, array);
//                                    eitem.setAdapter(adapterlist);
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
//                VolleySingleton.getInstance(StockAdjustment.this).addToRequestQueue(getRequest);
           }
        }
        else
        {
//            ltype.setVisibility(View.GONE);
//            String selectQuery = "SELECT  * FROM product";
//            Cursor cursor = dbs.rawQuery(selectQuery, null);
//
//            if (cursor.moveToFirst()) {
//                do {
//
//                    if(array.contains(cursor.getString(2)))
//                    {
//
//                    }
//                    else
//                    {
//                        array.add(cursor.getString(2));
//                        array2.add(cursor.getString(11));
//                    }
//
//                } while (cursor.moveToNext());
//            }
//
//            ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(StockAdjustment.this,
//                    R.layout.spinnertext, array);
//            eitem.setAdapter(adapterlist);
        }
//            String selectQuery = "SELECT  * FROM product";
//
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
//            eitem.setAdapter(adapterlist);
        //}
        String selectQuery2 = "SELECT  * FROM astock where flag = 0";

        Cursor cursor2 = dbs.rawQuery(selectQuery2, null);

        double tc=0,tc2=0;

        /// ecount.setText(""+cursor2.getCount());


        if (cursor2.moveToFirst()) {
            do {

                tc = Double.parseDouble(cursor2.getString(4));
                tc2  +=  tc ;

                array3.add(cursor2.getString(0));
                array2.add(cursor2.getString(6));
                array4.add(cursor2.getString(5));
                ecount.setText(" "+Math.round(tc2));
            } while (cursor2.moveToNext());
        }

        //////////////////////////////////////////////////////////






        //////////////////////////////////////////////////////////



        String selectQuery2l = "SELECT  * FROM lrplist";

        Cursor cursor2icv2 = dbs.rawQuery(selectQuery2l, null);
        if(cursor2icv2.moveToFirst()) {
            do {
                arraylrp.add(cursor2icv2.getString(1));


            } while (cursor2icv2.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (this, R.layout.spinnertext3, arraylrp);
            //Getting the instance of AutoCompleteTextView

            elrp.setThreshold(0);//will start working from first character
            elrp.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
            elrp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    elrp.showDropDown();
                    elrp.requestFocus();
                }
            });
        }
        elrp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ecount.getText().toString().equalsIgnoreCase("0"))
                {
                    //Toast.makeText(StockAdjustment.this, "No Data Available", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("No Data Available!")
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
                    if(isNetworkAvailable()) {
                        String checkquery = "SELECT  * FROM astock2";
                        Cursor ckcursor = dbs.rawQuery(checkquery, null);
                        if(ckcursor.getCount()!=0)
                        {
                            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
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
                            Date c = Calendar.getInstance().getTime();
                            SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
                            String formattedDate = df.format(c);
                            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
                            String formattedDate2 = df2.format(c);
                            String selectQuerys = "SELECT  * FROM astock2";
                            Cursor cursors = dbs.rawQuery(selectQuerys, null);
                            int co = cursors.getCount() + 1;
                            String inno = formattedDate + "/STKADJ000" + co;

                            SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
                            String formattedDate3 = dff.format(c);
                            db.astock2(array3.toString(), inno, formattedDate2, "0", array4.toString(), formattedDate3,sharedpreferences.getString("ocnew",""));

                            String selectQuery3 = "SELECT  * FROM astock2";
                            Cursor cursor3 = dbs.rawQuery(selectQuery3, null);
                            ct3 = cursor3.getCount();
                            if (cursor3.moveToFirst()) {
                                do {

                                    aqid = cursor3.getString(0);

                                    aqi = cursor3.getString(1);
                                    Log.e("HARI", "" + aqi);
                                    aqi2 = aqi.substring(1, aqi.length() - 1);
                                    aqd = cursor3.getString(6);
                                    save3();
                                } while (cursor3.moveToNext());
                            }
                        }

                    }
                    else
                    {
//                        Date c = Calendar.getInstance().getTime();
//                        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
//                        String formattedDate = df.format(c);
//                        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
//                        String formattedDate2 = df2.format(c);
//                        String selectQuerys = "SELECT  * FROM astock2";
//                        Cursor cursors = dbs.rawQuery(selectQuerys, null);
//                        int co = cursors.getCount() + 1;
//                        String inno = formattedDate + "/STKADJ000" + co;
//
//                        String lindno = sharedpreferences.getString("invoiceno","");
//                        String[] lindno2 = lindno.split("/");
//                        int xi = Integer.parseInt(lindno2[1])+1;
//                        String vv = String.valueOf(xi);
//
//                        einwno.setText(lindno2[0]+"/00"+vv);
//                        SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                        editor.putString("sano", einwno.getText().toString());
//
//                        editor.commit();
//
//                        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
//                        String formattedDate3 = dff.format(c);
//                        db.astock2(array3.toString(),inno,formattedDate2,"0",array4.toString(),formattedDate3,sharedpreferences.getString("ocnew",""));
//                        dbs.execSQL("UPDATE astock SET flag = 1 WHERE flag = 0");
//                        //finish();
//
//
//                        String selectQuery3 = "SELECT  * FROM astock2";
//                        Cursor cursor33 = dbs.rawQuery(selectQuery3, null);
//                        ct3 = cursor33.getCount();
//                        if (cursor33.moveToFirst()) {
//                            do {
//
//                                aqid = cursor33.getString(0);
//
//                                aqi = cursor33.getString(1);
//                                aqi2 = aqi.substring(1, aqi.length() - 1);
//                                aqd = cursor33.getString(6);
//                                String[] id = aqi2.split(",");
//
//
//                                for(int i = 0 ; i< id.length;i++) {
//
//
//                                    Cursor cursor4 = dbs.query("astock", new String[]{"item", "adjusttype",
//                                                    "inwardno", "stock", "aqty", "rqty",}, "id" + "=?",
//                                            new String[]{id[i]}, null, null, null, null);
//
//                                    if (cursor4.moveToFirst()) {
//                                        do {
//                                            at = cursor4.getString(0);
//                                            atno = cursor4.getString(4);
//                                            aty = "QCD_ICD_" + cursor4.getString(1);
//                                            String rqty = cursor4.getString(5);
//
//
//
//                                            // newcq = String.valueOf(z);
//
//
//
//                                            Cursor cursor3 = dbs.query("product", new String[]{"In_product_code", "In_product_name",
//                                                            "In_productcategory_code", "In_productcategory_desc", "In_productsubcategory_code", "In_productsubcategory_desc", "In_uomtype_desc","id"}, "In_product_name" + "=?",
//                                                    new String[]{at}, null, null, null, null);
//
//                                            if (cursor3.moveToFirst()) {
//                                                do {
//
//                                                    aqpco = cursor3.getString(0);
//                                                    aqc = cursor3.getString(2);
//                                                    aqcd = cursor3.getString(3);
//                                                    aqsc = cursor3.getString(4);
//                                                    aqsc2 = cursor3.getString(5);
//                                                    aqu = cursor3.getString(6);
//                                                    String ii = cursor3.getString(7);
//
//                                                    //Toast.makeText(this, ""+pco2, Toast.LENGTH_SHORT).show();
//                                                    dbs.execSQL("UPDATE product SET In_current_qty = " +rqty+ " WHERE id = "+ii);
//                                                }
//
//                                                while (cursor3.moveToNext());
//                                            }
//
//                                        }
//
//                                        while (cursor4.moveToNext());
//                                    }
//                                }
//                            } while (cursor33.moveToNext());
//                        }
//
//                        eicd.setEnabled(true);
//                        final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
////set icon
//                                .setIcon(android.R.drawable.ic_menu_save)
////set title
//                                .setTitle("Success!")
////set message
//                                .setMessage("Stock Adjustment Saved!")
////set positive button
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                //set what would happen when positive button is clicked
//                                                Intent in = new Intent(getApplicationContext(),Dashboard.class);
//                                                startActivity(in);
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





        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(StockAdjustment.this);
                dialog.setContentView(R.layout.stockadjlist);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                dialog.getWindow().setLayout(width, height);
                // set the custom dialog components - text, image and button


                // set up the RecyclerView
                androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(StockAdjustment.this));
                adapter = new MyRecyclerViewAdapter(StockAdjustment.this, productlist);


                String selectQuery = "SELECT  * FROM astock where flag = 0";
                productlist.clear();
                Cursor cursor = dbs.rawQuery(selectQuery, null);

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        pojoSA pojoSA = new pojoSA();

                        pojoSA.setId(cursor.getString(0));
                        pojoSA.setItem(cursor.getString(1));
                        pojoSA.setAt(cursor.getString(2));
                        pojoSA.setInwno(cursor.getString(3));
                        pojoSA.setSt(cursor.getString(4));
                        pojoSA.setAqty(cursor.getString(5));
                        pojoSA.setRqty(cursor.getString(6));

                        Log.e("Check",""+cursor.getString(1));

                        productlist.add(pojoSA);
                        // array2.add(cursor.getString(11));
                        // Log.e("VAL",""+cursor.getString(11));
                        recyclerView.setAdapter(adapter);
                    } while (cursor.moveToNext());

                }

                ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        eitem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

                if(isNetworkAvailable()) {
                    Cursor cursor2 = dbs.query("product", new String[]{"In_product_code",
                                    "In_base_price", "id", "In_current_qty"}, "In_product_name" + "=?",
                            new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);

                    if (cursor2.moveToFirst()) {
                        do {
                            double d = Double.parseDouble(cursor2.getString(3));
                            idn = cursor2.getString(2);
                            cq = cursor2.getString(3);
                            //Toast.makeText(Bookinvoicenew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

                            estock.setText("" + Math.round(Float.parseFloat(cq)));


                        }
                        while (cursor2.moveToNext());


                    }
                }
                else
                {
                    Cursor cursor2 = dbs.query("product", new String[]{"In_product_code",
                                    "In_base_price", "id", "In_current_qty"}, "In_product_name" + "=?",
                            new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);

                    if (cursor2.moveToFirst()) {
                        do {
                            double d = Double.parseDouble(cursor2.getString(3));
                            idn = cursor2.getString(2);
                            cq = cursor2.getString(3);
                            //Toast.makeText(Bookinvoicenew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

                            estock.setText("" +Math.round(Float.parseFloat(cq)));


                        }
                        while (cursor2.moveToNext());


                    }
                }
            }
        });

        eitem.addTextChangedListener(new TextWatcher() {

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
                   // eddate.setText("");
                    spinner.setSelection(0);
                    einwno.setText("");
                    eaqty.setText("");
                    erqty.setText("");
                }
                else
                {

                    //  erate.setText("");
                }

            }
        });

        sn.addTextChangedListener(new TextWatcher() {

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

                    einwno.setText("");
                    eaqty.setText("");
                    erqty.setText("");
                }
                else
                {

                    //  erate.setText("");
                }

            }
        });


        sn.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                pdialog.setCanceledOnTouchOutside(false);
                pdialog.setTitle("Loading.....");
                pdialog.show();
                inward();

            }
        });
        cn.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                pdialog.setCanceledOnTouchOutside(false);
                pdialog.setTitle("Loading.....");
                pdialog.show();
                invoice();

            }
        });

        einwno.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {

                inwstock();


            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(eitem.getText().toString().equalsIgnoreCase(""))
                {
                    // Toast.makeText(StockAdjustment.this, "Empty Item", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Enter Item Name!")
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
                else if(estock.getText().toString().equalsIgnoreCase(""))
                {
                    // Toast.makeText(StockAdjustment.this, "Empty Stock", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Empty Stock!")
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

                else if(eaqty.getText().toString().equalsIgnoreCase(""))
                {
                    //Toast.makeText(StockAdjustment.this, "Empty Adjust Qty", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Enter Adjust Qty!")
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
                else if(spinner.getSelectedItem().toString().equalsIgnoreCase("Select"))
                {
                    //Toast.makeText(StockAdjustment.this, "Empty Adjust Type", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Enter Adjust Type!")
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
                else if(spinner.getSelectedItem().toString().replaceAll(" ","").equalsIgnoreCase("transferfromlrp")&& elrp.getText().toString().equalsIgnoreCase(""))
                {
                    //Toast.makeText(StockAdjustment.this, "Empty Adjust Type", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Select LRP Name!")
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
                else if(spinner.getSelectedItem().toString().replaceAll(" ","").equalsIgnoreCase("transfertolrp")&& elrp.getText().toString().equalsIgnoreCase(""))
                {
                    //Toast.makeText(StockAdjustment.this, "Empty Adjust Type", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Select LRP Name!")
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

                    if(isNetworkAvailable()) {
//                        if(einwno.getText().toString().equalsIgnoreCase("")) {
//                            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
////set icon
//                                    .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                    .setTitle("Error!")
////set message
//                                    .setMessage("Enter Inward /Invoice No!")
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
                        if (f == 0) {

                            db.astock(eitem.getText().toString(), spinner.getSelectedItem().toString(), einwno.getText().toString(), estock.getText().toString(), eaqty.getText().toString(), erqty.getText().toString(), "0",elrp.getText().toString());
                            // Toast.makeText(StockAdjustment.this, "Successfully Submitted", Toast.LENGTH_SHORT).show();
                            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Successfully Added!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                    finish();
                                                    startActivity(getIntent());
                                                }
                                            }
//set negative button

                                    )
                                    .show();


                        } else {
                            db.updateastock(Integer.parseInt(id), eitem.getText().toString(), spinner.getSelectedItem().toString(), einwno.getText().toString(), estock.getText().toString(), eaqty.getText().toString(), erqty.getText().toString(), "0",elrp.getText().toString());
                            //Toast.makeText(StockAdjustment.this, "Successfully Update", Toast.LENGTH_SHORT).show();
                            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Successfully Updated!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                    finish();
                                                    startActivity(getIntent());
                                                }
                                            }
//set negative button

                                    )
                                    .show();
                            // finish();

                            finish();
                            startActivity(getIntent());
                        }

                    }
                    else
                    {
                        if (f == 0) {

                            eicd.setEnabled(false);

                            db.astock(eitem.getText().toString(), spinner.getSelectedItem().toString(), "0", estock.getText().toString(), eaqty.getText().toString(), erqty.getText().toString(), "0",elrp.getText().toString());
                            // Toast.makeText(StockAdjustment.this, "Successfully Submitted", Toast.LENGTH_SHORT).show();
                            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Successfully Added!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                    finish();
                                                    startActivity(getIntent());
                                                }
                                            }
//set negative button

                                    )
                                    .show();


                        } else {
                            eicd.setEnabled(false);
                            db.updateastock(Integer.parseInt(id), eitem.getText().toString(), spinner.getSelectedItem().toString(), "0", estock.getText().toString(), eaqty.getText().toString(), erqty.getText().toString(), "0",elrp.getText().toString());
                            //Toast.makeText(StockAdjustment.this, "Successfully Update", Toast.LENGTH_SHORT).show();
                            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Successfully Updated!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                    finish();
                                                    startActivity(getIntent());
                                                }
                                            }
//set negative button

                                    )
                                    .show();
                            // finish();

                            finish();
                            startActivity(getIntent());
                        }
                    }

                }
            }
        });

        eaqty.addTextChangedListener(new TextWatcher() {

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


                if(s.length() != 0)
                    getAmt();

                else
                {
                    erqty.setText("");
                }

            }
        });




    }
    public  class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

        private List<pojoSA> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<pojoSA> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.stockadjlist2, parent, false);
            return new MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, final int position) {
            final pojoSA pojoSA = mData.get(position);
            holder.myTextView.setText(pojoSA.getItem());
            holder.qty.setText(pojoSA.getSt());
            holder.rqty.setText(pojoSA.getRqty());
            holder.aqty.setText(pojoSA.getAqty());

            holder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.deletestock(Integer.valueOf(pojoSA.getId()));
                    productlist.remove(position);
                    adapter.notifyDataSetChanged();

                }
            });
            holder.ed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    f = 1;
                    dialog.dismiss();
                    add.setText("Update");
                    int spinnerPosition = dataAdapter.getPosition(pojoSA.getAt());
                    spinner.setSelection(spinnerPosition);
                    eitem.setText(pojoSA.getItem());
                    estock.setText(pojoSA.getSt());
                    eaqty.setText(pojoSA.getAqty());
                    erqty.setText(pojoSA.getRqty());
                    einwno.setText(pojoSA.getInwno());
                    id = pojoSA.getId();


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
            TextView myTextView,qty,aqty,rqty;
            ImageView ed,del;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.itm);
                qty = itemView.findViewById(R.id.qty);
                aqty = itemView.findViewById(R.id.aqty);
                rqty = itemView.findViewById(R.id.rqty);
                ed = (ImageView)itemView.findViewById(R.id.ed);
                del = (ImageView)itemView.findViewById(R.id.del);

            }


        }

        // convenience method for getting data at click position


        // allows clicks events to be caught


        // parent activity will implement this method to respond to click events

    }
    public void getAmt()
    {
        try {


            if (isNetworkAvailable()) {
                if(einwno.getText().toString().equalsIgnoreCase("")) {
                    if (spinner.getSelectedItem().toString().equalsIgnoreCase("PurchaseReturn")) {
                        double x = Double.parseDouble(estock.getText().toString());
                        double y = Double.parseDouble(eaqty.getText().toString());

                        if (y > x) {
                            //Toast.makeText(this, "Unable to Add", Toast.LENGTH_SHORT).show();
                            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Types qty Does Not Match With Existance Qty!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    pdialog.dismiss();

                                                    //set what would happen when positive button is clicked

                                                }
                                            }
//set negative button

                                    )
                                    .show();
                            eaqty.setText("");
                        } else {
                            double z = x - y;
                            erqty.setText("" + Math.round(z));
                        }
                    } else if (spinner.getSelectedItem().toString().equalsIgnoreCase("SaleReturn")) {
                        double x = Double.parseDouble(estock.getText().toString());
                        double y = Double.parseDouble(eaqty.getText().toString());

                        if (y > x) {
                            //Toast.makeText(this, "Unable to Add", Toast.LENGTH_SHORT).show();
                            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Typed qty Does Not Match With Existance Qty!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    pdialog.dismiss();

                                                    //set what would happen when positive button is clicked

                                                }
                                            }
//set negative button

                                    )
                                    .show();
                            eaqty.setText("");
                        } else {
                            double z = x + y;
                            erqty.setText("" + Math.round(z));
                        }
                    } else if((spinner.getSelectedItem().toString().equalsIgnoreCase("openingbalance"))) {
                        double x = Double.parseDouble(estock.getText().toString());
                        double y = Double.parseDouble(eaqty.getText().toString());
                        double z = x + y;
                        erqty.setText("" + Math.round(z));
                    }
                    else if((spinner.getSelectedItem().toString().replaceAll(" ","").equalsIgnoreCase("transfertolrp"))){
                        double x = Double.parseDouble(estock.getText().toString());
                        double y = Double.parseDouble(eaqty.getText().toString());
                        double z = x - y;
                        if(x == 0 || y > x)
                        {
                            eaqty.setText("");
                            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Entered qty Does Not Match With Existing Qty!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    pdialog.dismiss();

                                                    //set what would happen when positive button is clicked

                                                }
                                            }
//set negative button

                                    )
                                    .show();
                        }
                        else
                        {
                            erqty.setText("" + Math.round(z));
                        }

                    }
                    else if((spinner.getSelectedItem().toString().replaceAll(" ","").equalsIgnoreCase("transferfromlrp"))){
                        double x = Double.parseDouble(estock.getText().toString());
                        double y = Double.parseDouble(eaqty.getText().toString());
                        double z = x + y;
                        erqty.setText("" + Math.round(z));
                    }
                }
                else
                {
                    if (spinner.getSelectedItem().toString().equalsIgnoreCase("PurchaseReturn")) {
                        double x = Double.parseDouble(estock.getText().toString());
                        double y = Double.parseDouble(eaqty.getText().toString());

                        if (y > x) {
                            //Toast.makeText(this, "Unable to Add", Toast.LENGTH_SHORT).show();
                            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Entered qty Does Not Match With Existing Qty!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    pdialog.dismiss();

                                                    //set what would happen when positive button is clicked

                                                }
                                            }
//set negative button

                                    )
                                    .show();
                            eaqty.setText("");
                        } else {
                            double z = x - y;
                            erqty.setText("" + Math.round(z));
                        }
                    } else if (spinner.getSelectedItem().toString().equalsIgnoreCase("SaleReturn")) {
                        double x = Double.parseDouble(estock.getText().toString());
                        double y = Double.parseDouble(eaqty.getText().toString());

                        if (y > x) {
                            //Toast.makeText(this, "Unable to Add", Toast.LENGTH_SHORT).show();
                            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Typed qty Does Not Match With Existance Qty!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    pdialog.dismiss();

                                                    //set what would happen when positive button is clicked

                                                }
                                            }
//set negative button

                                    )
                                    .show();
                            eaqty.setText("");
                        } else {
                            double z = x - y;
                            erqty.setText("" + Math.round(z));
                        }
                    } else if((spinner.getSelectedItem().toString().equalsIgnoreCase("openingbalance"))){
                        double x = Double.parseDouble(estock.getText().toString());
                        double y = Double.parseDouble(eaqty.getText().toString());
                        double z = x + y;
                        erqty.setText("" + Math.round(z));
                    }
                    else if((spinner.getSelectedItem().toString().replaceAll(" ","").equalsIgnoreCase("transfertolrp"))){
                        double x = Double.parseDouble(estock.getText().toString());
                        double y = Double.parseDouble(eaqty.getText().toString());
                        double z = x - y;
                        if(x == 0 || y > x)
                        {
                            eaqty.setText("");
                            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Entered qty Does Not Match With Existing Qty!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    pdialog.dismiss();

                                                    //set what would happen when positive button is clicked

                                                }
                                            }
//set negative button

                                    )
                                    .show();
                        }
                        else
                        {
                            erqty.setText("" + Math.round(z));
                        }
                    }
                    else if((spinner.getSelectedItem().toString().replaceAll(" ","").equalsIgnoreCase("transferfromlrp"))){
                        double x = Double.parseDouble(estock.getText().toString());
                        double y = Double.parseDouble(eaqty.getText().toString());
                        double z = x + y;
                        erqty.setText("" + Math.round(z));
                    }
                }
            } else {
                if (spinner.getSelectedItem().toString().equalsIgnoreCase("PurchaseReturn")) {
                    double x = Double.parseDouble(estock.getText().toString());
                    double y = Double.parseDouble(eaqty.getText().toString());

                    if (y > x) {
                        //Toast.makeText(this, "Unable to Add", Toast.LENGTH_SHORT).show();
                        final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Types qty Does Not Match With Existance Qty!")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                pdialog.dismiss();

                                                //set what would happen when positive button is clicked

                                            }
                                        }
//set negative button

                                )
                                .show();
                        eaqty.setText("");
                    } else {
                        double z = x - y;
                        erqty.setText("" + Math.round(z));
                    }
                } else if (spinner.getSelectedItem().toString().equalsIgnoreCase("SaleReturn")) {
                    double x = Double.parseDouble(estock.getText().toString());
                    double y = Double.parseDouble(eaqty.getText().toString());

                    if (y > x) {
                        //Toast.makeText(this, "Unable to Add", Toast.LENGTH_SHORT).show();
                        final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Typed qty Does Not Match With Existance Qty!")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                pdialog.dismiss();

                                                //set what would happen when positive button is clicked

                                            }
                                        }
//set negative button

                                )
                                .show();
                        eaqty.setText("");
                    } else {
                        double z = x + y;
                        erqty.setText("" + Math.round(z));
                    }
                } else if((spinner.getSelectedItem().toString().equalsIgnoreCase("openingbalance"))){
                    double x = Double.parseDouble(estock.getText().toString());
                    double y = Double.parseDouble(eaqty.getText().toString());
                    double z = x + y;
                    erqty.setText("" + Math.round(z));
                }
                else if((spinner.getSelectedItem().toString().replaceAll(" ","").equalsIgnoreCase("transfertolrp"))){
                    double x = Double.parseDouble(estock.getText().toString());
                    double y = Double.parseDouble(eaqty.getText().toString());
                    double z = x - y;
                    if(x == 0 || y > x)
                    {
                        eaqty.setText("");
                        final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Entered qty Does Not Match With Existing Qty!")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                pdialog.dismiss();

                                                //set what would happen when positive button is clicked

                                            }
                                        }
//set negative button

                                )
                                .show();
                    }
                    else
                    {
                        erqty.setText("" + Math.round(z));
                    }
                }
                else if((spinner.getSelectedItem().toString().replaceAll(" ","").equalsIgnoreCase("transferfromlrp"))){
                    double x = Double.parseDouble(estock.getText().toString());
                    double y = Double.parseDouble(eaqty.getText().toString());
                    double z = x + y;
                    erqty.setText("" + Math.round(z));
                }
            }
        }
        catch (Exception e)
        {

        }

    }
    public void save3()
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

            user2.put("IOU_adjustment_rowid","0");
            user2.put("In_orgn_code",sharedpreferences.getString("ocnew",""));
            user2.put("In_ic_code",sharedpreferences.getString("ocnew",""));
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
                final SQLiteDatabase dbs = db.getWritableDatabase();

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
            }



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


        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.icdurl+"api/ICDMOBStockadj/newSaveStockAdjustment",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        c3++;
                        final SQLiteDatabase dbs = db.getWritableDatabase();
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("Header");

                            String inrid = obj2.getString("IOU_adjustment_rowid");
                            final String inrno = obj2.getString("In_adjustment_no");
                            Log.e("CCCC", "Hi" + inrid);

                            if (inrid.equalsIgnoreCase("0")) {
                                dbs.execSQL("Delete FROM  astock2 WHERE id = " + aqid);
                                pdialog.dismiss();

                                // Toast.makeText(StockAdjustment.this, "Unable To Insert", Toast.LENGTH_SHORT).show();
                                final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
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
                                                        finish();
                                                        startActivity(getIntent());
                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                                FirebaseApp.initializeApp(StockAdjustment.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis()/1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                Log.e("Valuecheck",""+mRef.getRef());
                                mRef.child("name").setValue("SAVEAPI");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("STOCKADJ");
                            } else {


                                pdialog.dismiss();
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
                                dbs.execSQL("UPDATE astock SET flag = 1 WHERE flag = 0");
                                // Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                                dbs.execSQL("Delete FROM  astock2 WHERE id = " + aqid);
                                Log.e("CCCC", "SA");
                                eadj.setText(""+inrno);
                                // finish();
                                final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Success!")
//set message
                                        .setMessage("Stock Adjustment Inserted !" +
                                                "Adjustmentno No :"+inrno)
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        // dbs.execSQL("UPDATE invoicelist SET inweb = " + inrno + " WHERE id = " + id2);
                                                        //  db.upinvoice(Integer.valueOf(id2),inrno);
                                                        finish();
                                                    }
                                                }
//set negative button

                                        )
                                        .show();
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
                        final SQLiteDatabase dbs = db.getWritableDatabase();
                        dbs.execSQL("Delete FROM  astock2 WHERE id = " + aqid);
                        //Toast.makeText(getApplicationContext(),"Unable to Insert",Toast.LENGTH_SHORT).show();
                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
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
                                                finish();
                                                startActivity(getIntent());
                                            }
                                        }
//set negative button

                                )
                                .show();
                        FirebaseApp.initializeApp(StockAdjustment.this);
                        FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        Long tsLong = System.currentTimeMillis()/1000;
                        String ts = tsLong.toString();
                        DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                        Log.e("Valuecheck",""+mRef.getRef());
                        mRef.child("name").setValue("SAVEAPI");
                        mRef.child("date").setValue(date);
                        mRef.child("Error").setValue(error.getMessage().toString());
                        mRef.child("Activity").setValue("STOCKADJ");
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
    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        eddate.setText(sdf.format(myCalendar.getTime()));
    }

    public void inward()
    {
        if(sn.getText().toString().equalsIgnoreCase(""))
        {
           // eddate.setText("");
            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                    .setTitle("Error!")
//set message
                    .setMessage("Enter Supplier Name!")
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
        else if(spinner.getSelectedItem().toString().equalsIgnoreCase("Select"))
        {
           // eddate.setText("");
            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                    .setTitle("Error!")
//set message
                    .setMessage("Select Type!")
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

            String[] spn = sn.getText().toString().split("--");
            final String url = Pojokyc.icdurl+"api/ICDMOBList/get_adjustment_count?org=flexi&locn="+Pojokyc.instance+"&user=admin&lang=en_us&customer_code="+spn[1]+"&product_code=&adjustment_type=QCD_ICD_PURCHASERETURN&adjustment_date="+eddate.getText().toString();
            Log.e("URL",""+url);
// prepare the Request
            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            stringList.clear();
                            // display response
                            try {
                                JSONObject obj = response.getJSONObject("context");
                                JSONArray cast = obj.getJSONArray("List");
                                for (int i = 0; i < cast.length(); i++) {


                                    JSONObject actor = cast.getJSONObject(i);
                                    if(actor.getString("product_name").equalsIgnoreCase(eitem.getText().toString()))
                                    {
                                        stringList.add(actor.getString("grn_or_invoice_no"));
                                        String ivno =actor.getString("grn_or_invoice_no");
                                        String p =actor.getString("product_name");
                                        String ps =actor.getString("adjustment_qty");
                                        SharedPreferences.Editor editor = sharedpreferences.edit();

                                        editor.putString(ivno+"/"+p, ps);

                                        editor.commit();
                                    }

                                    // Log.e("CKKK",""+actor.getString("grn_or_invoice_no"));


                                }
                                if(stringList.size() == 0)
                                {
                                    final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                            .setTitle("Error!")
//set message
                                            .setMessage("No Inward Found!")
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
                                    einwno.setEnabled(false);
                                }
                                else {
                                    einwno.setEnabled(true);
                                    pdialog.dismiss();

                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                            (StockAdjustment.this, android.R.layout.select_dialog_item, stringList);
                                    //Getting the instance of AutoCompleteTextView
                                    //    AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
                                    einwno.setThreshold(1);//will start working from first character
                                    einwno.setAdapter(adapter);
                                }

                            } catch (JSONException e) {
                                pdialog.dismiss();
                                final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("NO Inward Found!")
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        pdialog.dismiss();

                                                        //set what would happen when positive button is clicked

                                                    }
                                                }
//set negative button

                                        )
                                        .show();
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
            VolleySingleton.getInstance(StockAdjustment.this).addToRequestQueue(getRequest);
        }
    }

    public void invoice()
    {
        if(cn.getText().toString().equalsIgnoreCase(""))
        {
           // eddate.setText("");
            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                    .setTitle("Error!")
//set message
                    .setMessage("Enter Customer Name!")
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
        else if(spinner.getSelectedItem().toString().equalsIgnoreCase("Select"))
        {
           // eddate.setText("");
            final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                    .setTitle("Error!")
//set message
                    .setMessage("Select Type!")
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

            String[] fnn = cn.getText().toString().split("--");
            final String url = Pojokyc.icdurl+"api/ICDMOBList/get_adjustment_count?org=flexi&locn="+Pojokyc.instance+"&user=admin&lang=en_us&customer_code="+fnn[1]+"&product_code=&adjustment_type=QCD_ICD_SaleReturn&adjustment_date="+eddate.getText().toString();

// prepare the Request
            Log.e("URL",""+url);
            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            stringList.clear();
                            // display response
                            try {
                                JSONObject obj = response.getJSONObject("context");
                                JSONArray cast = obj.getJSONArray("List");
                                for (int i = 0; i < cast.length(); i++) {


                                    JSONObject actor = cast.getJSONObject(i);
                                    if(actor.getString("product_name").equalsIgnoreCase(eitem.getText().toString()))
                                    {
                                        stringList.add(actor.getString("grn_or_invoice_no"));
                                        String ivno =actor.getString("grn_or_invoice_no");
                                        String p =actor.getString("product_name");
                                        String ps =actor.getString("adjustment_qty");
                                        SharedPreferences.Editor editor = sharedpreferences.edit();

                                        editor.putString(ivno+"/"+p, ps);

                                        editor.commit();
                                    }

                                    // Log.e("CKKK",""+actor.getString("grn_or_invoice_no"));


                                }
                                if(stringList.size() == 0)
                                {
                                    final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                            .setTitle("Error!")
//set message
                                            .setMessage("No Invoice Found!")
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
                                    einwno.setEnabled(false);
                                }
                                else {
                                    einwno.setEnabled(true);
                                    pdialog.dismiss();

                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                            (StockAdjustment.this, android.R.layout.select_dialog_item, stringList);
                                    //Getting the instance of AutoCompleteTextView
                                    //    AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
                                    einwno.setThreshold(1);//will start working from first character
                                    einwno.setAdapter(adapter);
                                }

                            } catch (JSONException e) {
                                pdialog.dismiss();
                                final AlertDialog alertDialog = new AlertDialog.Builder(StockAdjustment.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("No Invoice Found!")
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        pdialog.dismiss();

                                                        //set what would happen when positive button is clicked

                                                    }
                                                }
//set negative button

                                        )
                                        .show();
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
            VolleySingleton.getInstance(StockAdjustment.this).addToRequestQueue(getRequest);
        }
    }
    public void inwstock()
    {
        estock.setText(""+Math.round(Float.parseFloat(sharedpreferences.getString(einwno.getText().toString()+"/"+eitem.getText().toString(),""))));
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
