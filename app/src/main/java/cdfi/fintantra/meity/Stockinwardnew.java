package cdfi.fintantra.meity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Base64;
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
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Stockinwardnew extends AppCompatActivity {
    Button detail,header,viewcap;
    Dialog dialog,dialog2;
    TextView name;
    String itext2,pyid;
    String billno2;
    JSONObject usedd;
    ImageView list;
    int fp = 0;
    String inwid;
    String itext;
    String cqno;
    LinearLayout lch;
    LinearLayout licd,l3;
    EditText otherip,othercs;
    ImageView eye1,eye2;
    TextView inwnum,amount,balance,paid;
    EditText eamtpaid,erefno,epdate,ebal,inputsearch;
    Spinner spinner;
    Button viewp;
    String ss="",myr;
    TextView txtheader;
    RelativeLayout rdetail,rsummary;
    ImageView im1,im2;
    View v1,v2;
    ImageView cap,vcap;
    String suppliername;
    String trp,oths;
    EditText ebillno;
    String billno;
    int ta;
    String cq;
    int ct=0,ct2=0,ctt=0,ct22=0,c3=0,ct3=0;
    JSONObject jsonParam;
    String at,atno,aqc,aqcd,aqsc,aqsc2,aqpc,aqpd,aqu,aqi,aqd,aqpco,aqi2,aty,aqid;
    String lc2;
    String lc;
    String sname,slcn,sst,encodedimage="",ids,farco,ids2,tax,oth,trs,net,pr,pd,pt,pa,pq,pn,pc,psc,pco,pu,amt,id,bp,balamt;
    String sname2,slcn2,sst2,encodedimage2="",ids22,ids222,tax2,oth2,trs2,net2,pr2,pd2,pt2,pa2,pq2,pn2,pc2,psc2,pco2,pu2,amt2,id2;
    Uri picUri;
    String ui = "0";
    String encodedImage = "0";
    EditText newnet;
    ImageView bqa,bqd;
    int bq=0;
    ByteArrayOutputStream byteArrayOutputStream;
    String imageFilePath;
    Bitmap thePic;
    String code;
    final int CAMERA_CAPTURE = 1;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    LinearLayout l1,l2;
    MyRecyclerViewAdapter adapter;
    MyRecyclerViewAdapter2 adapter2;
    Button view,view3,view2,bsave;
    String idn;
    TextView fponame;
    AutoCompleteTextView eicd;
    EditText esl;
    String prid;
    int setflag = 0;
    ArrayList<String>  mStringList= new ArrayList<String>();
    ArrayList<String>  mStringList2= new ArrayList<String>();
    EditText edate,eamount,ediscount,enetamount,erate,eitem,equantity,etotal,ecount,elocation,etax,etransport,eothers,einwno,einwamt;
    private DatePicker datePicker;
    private Calendar calendar;
    double fi=0;
    String tspr="";
    AutoCompleteTextView item,supplier;
    ArrayList<String> array,array2,array3;
    ProgressDialog pdialog;
    Calendar myCalendar;
    EditText ebalance;
    Button badd;
    DBHelper db;
    RelativeLayout rpayment;
    ImageView im3;
    int ck = 0;
    View v3;
    List<pojoProduct> productlist;
    List<pojoProducttax> producttaxlist;
    ArrayList<String> array1 = new ArrayList<>();
    ArrayList<String> array1n = new ArrayList<>();
EditText chno;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_stockinwardnew);

        rdetail = (RelativeLayout)findViewById(R.id.rdetail);
        rsummary = (RelativeLayout)findViewById(R.id.rsummary);

        licd = (LinearLayout)findViewById(R.id.licd);
        eamtpaid = (EditText)findViewById(R.id.eamountpaid);
        erefno = (EditText)findViewById(R.id.erefno);

        ebal = (EditText)findViewById(R.id.ebalance);
        epdate = (EditText)findViewById(R.id.epdate);
        ebalance = (EditText)findViewById(R.id.ebalance);
        rpayment = (RelativeLayout)findViewById(R.id.rpayment);
        rpayment.setEnabled(false);
        chno = (EditText)findViewById(R.id.chno);
        im1 = (ImageView)findViewById(R.id.im1);
        im2 = (ImageView)findViewById(R.id.im2);
        im3 = (ImageView)findViewById(R.id.im3);

        v1 = (View)findViewById(R.id.v1);
        v2 = (View)findViewById(R.id.v2);
        v3 = (View)findViewById(R.id.v3);
       lch = (LinearLayout) findViewById(R.id.lch);
        l3 = (LinearLayout)findViewById(R.id.l3);

        txtheader = (TextView)findViewById(R.id.txtheader);
        inwnum = (TextView)findViewById(R.id.inwnum);
        amount = (TextView)findViewById(R.id.balance);
        balance = (TextView)findViewById(R.id.amount);
        paid = (TextView)findViewById(R.id.paid);

        name =(TextView)findViewById(R.id.name);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        name.setText("Welcome "+sharedpreferences.getString("un",""));
        getSupportActionBar().hide();
        db =new  DBHelper(this);
        cap = (ImageView)findViewById(R.id.cap);
        vcap = (ImageView)findViewById(R.id.vcap);
        eye1 = (ImageView)findViewById(R.id.eye1);
        eye2 = (ImageView)findViewById(R.id.eye2);
        pdialog = new ProgressDialog(this);
        vcap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vcap.setVisibility(View.GONE);
            }
        });
        viewcap = (Button)findViewById(R.id.viewcap);
        newnet = (EditText)findViewById(R.id.newnet);
        esl = (EditText)findViewById(R.id.esl);
        othercs = (EditText)findViewById(R.id.othercs);
        otherip = (EditText)findViewById(R.id.otherip);


        esl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SnoActivity2.class);
                i.putExtra("TS",tspr);
                startActivity(i);
            }
        });


        bqa = (ImageView)findViewById(R.id.qa);
        bqd = (ImageView)findViewById(R.id.qd);
        ebillno = (EditText)findViewById(R.id.ebillno);
        viewcap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vcap.setVisibility(View.VISIBLE);
            }
        });

        productlist = new ArrayList<>();
        producttaxlist = new ArrayList<>();
        fponame = (TextView)findViewById(R.id.fponame);
        fponame.setText(sharedpreferences.getString("rn",""));
        list = (ImageView)findViewById(R.id.list);

        cap.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
//                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
//                {
//                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
//                }
//                else
//                {
//                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
//                }

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    picUri = FileProvider.getUriForFile(Stockinwardnew.this, getApplicationContext().getPackageName() + ".provider", createImageFile());
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                    takePictureIntent.putExtra("return-data", true);
                    startActivityForResult(takePictureIntent, CAMERA_CAPTURE);// convert path to Uri
                } catch (IOException e) {
                    e.printStackTrace();
                }

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

        Date cc = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(cc);
//        edate.setText(formattedDate);



      //  epdate.setText(formattedDate);
        epdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Stockinwardnew.this,android.R.style.Theme_Holo_Dialog , date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),VSlist.class);
                i.putExtra("F","1");
                startActivity(i);
            }
        });
        array = new ArrayList<>();
        array2 = new ArrayList<>();
        array3 = new ArrayList<>();
        item = (AutoCompleteTextView)findViewById(R.id.eitem);
        eicd = (AutoCompleteTextView)findViewById(R.id.eicd);
        item.setThreshold(1);
        


        supplier = (AutoCompleteTextView)findViewById(R.id.esupplier);
        supplier.setThreshold(1);
        final SQLiteDatabase dbs = db.getWritableDatabase();
        String selectQueryicd = "SELECT  * FROM icdlist";

        Cursor cursor2ic = dbs.rawQuery(selectQueryicd, null);
        if(cursor2ic.moveToFirst())
        {
            do
            {

                array1.add(cursor2ic.getString(1));
                Log.e("icdname", cursor2ic.getString(1));
                Log.e("icdname11", sharedpreferences.getString("ocnew",""));
                array1n.add(cursor2ic.getString(2));



            }while(cursor2ic.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (Stockinwardnew.this, R.layout.spinnertext3, array1n);
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
        eicd.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //Toast.makeText(Stockinwardnew.this, ""+array1.get(position), Toast.LENGTH_SHORT).show();


                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("ocnew", array1.get(position));
                editor.putString("oc1new", array1.get(position));

                editor.commit();
                String selectQuerys = "SELECT  * FROM supplierlist";
                array3.clear();

                Cursor cursors = dbs.rawQuery(selectQuerys, null);
                // Toast.makeText(Stockinwardnew.this, ""+cursors.getCount(), Toast.LENGTH_SHORT).show();
                if (cursors.moveToFirst()) {
                    do {
                        if(cursors.getString(7).equalsIgnoreCase(sharedpreferences.getString("ocnew","")))
                        {
                            array3.add(cursors.getString(2));
                            Log.e("VAL",""+cursors.getString(2));
                        }





                    } while (cursors.moveToNext());
                }

                ArrayAdapter<String> adapterlist2 = new ArrayAdapter<String>(Stockinwardnew.this,
                        R.layout.spinnertext, array3);
                supplier.setAdapter(adapterlist2);

                if(isNetworkAvailable())
                {
                    dbs.execSQL("delete from product");

                    pdialog.setCanceledOnTouchOutside(false);
                    pdialog.setTitle("Loading.....");
                    pdialog.show();
                    final String url = Pojokyc.icdurl+"api/ICDMOBProduct/ICD_MOBILE_Product?org=" + sharedpreferences.getString("orgnlvlcode", "") + "&locn="+Pojokyc.instance+"&user=admin&lang=en_US";
                    Log.e("PRODUCTURL",""+url);

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
                                                new String[]{sharedpreferences.getString("orgnlvlcode","")}, null, null, null, null);

                                        if(cursor.getCount()>0)
                                        {

                                            item.setEnabled(true);
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

                                            ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(Stockinwardnew.this,
                                                    R.layout.spinnertext, array);
                                            item.setAdapter(adapterlist);


                                        }

                                        else
                                        {
                                            item.setEnabled(false);
                                            final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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
                    VolleySingleton.getInstance(Stockinwardnew.this).addToRequestQueue(getRequest);
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

                        ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(Stockinwardnew.this,
                                R.layout.spinnertext, array);
                        item.setAdapter(adapterlist);

                    }

                    else
                    {
                        item.setEnabled(false);
                        final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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




        view = (Button)findViewById(R.id.view);
        view3 = (Button)findViewById(R.id.view3);
        view2 = (Button)findViewById(R.id.view2);
        edate = (EditText)findViewById(R.id.eddate);
        badd = (Button)findViewById(R.id.badd);
        spinner = (Spinner) findViewById(R.id.spinner);


        bsave = (Button)findViewById(R.id.bsave);
        //item.requestFocus();


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

        erate = (EditText)findViewById(R.id.erate);
        eamount = (EditText)findViewById(R.id.eamount);
        enetamount = (EditText)findViewById(R.id.enetamount);
        ediscount = (EditText)findViewById(R.id.ediscount);
        equantity = (EditText)findViewById(R.id.equantity);
        etotal = (EditText)findViewById(R.id.etotal);
        ecount = (EditText)findViewById(R.id.ecount);
        elocation = (EditText)findViewById(R.id.elocation);
        etax = (EditText)findViewById(R.id.etax);
        eye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(item.getText().toString().equalsIgnoreCase(""))
                {

                }
                else
                {
                    Intent i = new Intent(getApplicationContext(),Othercosticd.class);
                    i.putExtra("TS",tspr);
                    Pojokyc.item = item.getText().toString();
                    Pojokyc.quantity = equantity.getText().toString();
                    Pojokyc.rate = erate.getText().toString();
                    Pojokyc.amount = eamount.getText().toString();

                    startActivity(i);
                }

            }
        });
        eye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(item.getText().toString().equalsIgnoreCase(""))
                {

                }
                else
                {
                    Intent i = new Intent(getApplicationContext(),Otherinputsicd.class);
                    i.putExtra("TS",tspr);
                    Pojokyc.item = item.getText().toString();
                    Pojokyc.quantity = equantity.getText().toString();
                    Pojokyc.rate = erate.getText().toString();
                    Pojokyc.amount = eamount.getText().toString();

                    startActivity(i);
                }

            }
        });
        etransport = (EditText)findViewById(R.id.etransport);
        eothers = (EditText)findViewById(R.id.eothers);

        einwamt = (EditText)findViewById(R.id.einwamt);
        einwno = (EditText)findViewById(R.id.einwno);
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
        list.setVisibility(View.GONE);



        if(sharedpreferences.getString("cf","").equalsIgnoreCase("Individual"))
        {
            licd.setVisibility(View.VISIBLE);
            if(isNetworkAvailable())
            {
                String checkquery = "SELECT  * FROM inwardlist where flag = 0";
                Cursor ckcursor = dbs.rawQuery(checkquery, null);
                if(ckcursor.getCount()!=0)
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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

        }
        else
        {
            String selectQuerys = "SELECT  * FROM supplierlist";
            Cursor cursors = dbs.rawQuery(selectQuerys, null);
            if (cursors.moveToFirst()) {
                do {

                    array3.add(cursors.getString(2));
                    // Log.e("VAL",""+cursor.getString(11));

                } while (cursors.moveToNext());
            }

            ArrayAdapter<String> adapterlist2 = new ArrayAdapter<String>(this,
                    R.layout.spinnertext, array3);
            supplier.setAdapter(adapterlist2);
            supplier.setThreshold(0);

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("ocnew", sharedpreferences.getString("oc",""));
            editor.putString("oc1new",sharedpreferences.getString("oc",""));
            editor.commit();
            licd.setVisibility(View.GONE);
            if(isNetworkAvailable())
            {
                String checkquery = "SELECT  * FROM inwardlist where flag = 0";
                Cursor ckcursor = dbs.rawQuery(checkquery, null);
                if(ckcursor.getCount()!=0)
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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

                    //  Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();


                    dbs.execSQL("delete from product");

                    pdialog.setCanceledOnTouchOutside(false);
                    pdialog.setTitle("Loading.....");
                    pdialog.show();
                    final String url = Pojokyc.icdurl+"api/ICDMOBProduct/ICD_MOBILE_Product?org=" + sharedpreferences.getString("orgnlvlcode", "") + "&locn="+Pojokyc.instance+"&user=admin&lang=en_US";
                    Log.e("PRODUCTURL",""+url);
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
                                            Log.e("CHK",""+actor.getString("In_cgst"));
                                            String n13 = actor.getString("In_cgst");

                                            String n14 = actor.getString("In_sgst");
                                            String n15 = actor.getString("In_igst");
                                            String n16 = actor.getString("In_orgn_code");
                                            String n17 = actor.getString("In_ic_code");


                                            String n18 = actor.getString("In_value_addproduct_verified");



                                            db.insertContact(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17,n18);

                                        }

                                        String selectQuery = "SELECT  * FROM product";
                                        Cursor cursor = dbs.rawQuery(selectQuery, null);

                                        if (cursor.moveToFirst()) {
                                            do {

                                                if(array.contains(cursor.getString(2)))
                                                {

                                                }
                                                else
                                                {
                                                    array.add(cursor.getString(2));
                                                    array2.add(cursor.getString(11));
                                                }

                                            } while (cursor.moveToNext());
                                        }

                                        ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(Stockinwardnew.this,
                                                R.layout.spinnertext, array);
                                        item.setAdapter(adapterlist);
                                        item.setThreshold(0);
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
                    VolleySingleton.getInstance(Stockinwardnew.this).addToRequestQueue(getRequest);
                }

            }
            else
            {
                String selectQuery = "SELECT  * FROM product";
                Cursor cursor = dbs.rawQuery(selectQuery, null);

                if (cursor.moveToFirst()) {
                    do {

                        if(array.contains(cursor.getString(2)))
                        {

                        }
                        else
                        {
                            array.add(cursor.getString(2));
                            array2.add(cursor.getString(11));
                        }

                    } while (cursor.moveToNext());
                }

                ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(Stockinwardnew.this,
                        R.layout.spinnertext, array);
                item.setAdapter(adapterlist);
                item.setThreshold(0);
            }
        }


        String selectQuery2 = "SELECT  * FROM productlist where flag = 0";
        Cursor cursor2 = dbs.rawQuery(selectQuery2, null);
        if(cursor2.getCount() == 0)
        {
            ecount.setText("");
        }
        else
        {
            counttotal();
        }
//        if(sharedpreferences.getString("off","").equalsIgnoreCase("1"))
//        {
//            list.setVisibility(View.GONE);
//            dbs.execSQL("delete from product");
//            dbs.execSQL("delete from supplierlist");
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
//
//                                    db.insertContact(n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16,n17);
//
//                                }
//
//                                String selectQuery = "SELECT  * FROM product";
//                                Cursor cursor = dbs.rawQuery(selectQuery, null);
//
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
//                                ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(Stockinwardnew.this,
//                                        android.R.layout.select_dialog_item, array);
//                                item.setAdapter(adapterlist);
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
//            VolleySingleton.getInstance(Stockinwardnew.this).addToRequestQueue(getRequest);
//
//            final String url2 = "http://169.38.77.190:949/Deployable_image_uppro_pro/api/KANCHIICD_MOBILEAPP/ICD_Supplier_list?org=flexi&locn="+Pojokyc.instance+"&user=admin&lang=en_US&filterby_option=All&filterby_code=.&filterby_fromvalue=.&filterby_tovalue=.";
//
//// prepare the Request
//            JsonObjectRequest getRequest2 = new JsonObjectRequest(Request.Method.GET, url2, null,
//                    new Response.Listener<JSONObject>()
//                    {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            // display response
//                            try {
//                                JSONObject obj = response.getJSONObject("context");
//                                JSONArray cast = obj.getJSONArray("List");
//                                for (int i=0; i<cast.length(); i++) {
//
//
//
//                                    JSONObject actor = cast.getJSONObject(i);
//                                    String n1 = actor.getString("In_supplier_code");
//                                    String n2 = actor.getString("In_supplier_name");
//                                    String n3 = actor.getString("In_pan_no");
//                                    String n4 = actor.getString("In_supplier_state_code");
//                                    String n5 = actor.getString("In_supplier_state_desc");
//                                    String n6 = actor.getString("In_supplier_location");
//
//
//
//                                    db.insertsupplier(n1,n2,n3,n4,n5,n6);
//
//
//                                }
//
//                                String selectQuerys = "SELECT  * FROM supplierlist";
//                                Cursor cursors = dbs.rawQuery(selectQuerys, null);
//                                if (cursors.moveToFirst()) {
//                                    do {
//
//                                        array3.add(cursors.getString(2));
//                                        // Log.e("VAL",""+cursor.getString(11));
//
//                                    } while (cursors.moveToNext());
//                                }
//
//                                ArrayAdapter<String> adapterlist2 = new ArrayAdapter<String>(Stockinwardnew.this,
//                                        android.R.layout.select_dialog_item, array3);
//                                supplier.setAdapter(adapterlist2);
//                                pdialog.dismiss();
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
//            getRequest2.setRetryPolicy(new DefaultRetryPolicy(
//                    50000,
//                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//            VolleySingleton.getInstance(Stockinwardnew.this).addToRequestQueue(getRequest2);
//
//
//        }
//        else
//        {



        //}


//        etax.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog2 = new Dialog(Stockinwardnew.this);
//                dialog2.setContentView(R.layout.viewtaxd);
//                dialog2.getWindow().getAttributes().windowAnimations = R.anim.fadein;
//                dialog2.setTitle("Title...");
//                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
//                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
//
//                dialog2.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//                // set the custom dialog components - text, image and button
//
//                // set up the RecyclerView
//                androidx.recyclerview.widget.RecyclerView recyclerView = dialog2.findViewById(R.id.itm);
//                recyclerView.setLayoutManager(new LinearLayoutManager(Stockinwardnew.this));
//                adapter2 = new MyRecyclerViewAdapter2(Stockinwardnew.this, producttaxlist);
//
//                String selectQuery = "SELECT  * FROM productlist where flag = 0";
//                producttaxlist.clear();
//                Cursor cursor = dbs.rawQuery(selectQuery, null);
//
//                // looping through all rows and adding to list
//                if (cursor.moveToFirst()) {
//                    do {
//
//                        pojoProducttax pojoProducttax = new pojoProducttax();
//
//                        String id = cursor.getString(0);
//
//                        pojoProducttax.setRate(cursor.getString(3));
//                        pojoProducttax.setItem(cursor.getString(1));
//                        pojoProducttax.setNamt(cursor.getString(6));
//
//
//                        Cursor cursor2 = dbs.query("product", new String[]{"In_cgst",
//                                        "In_sgst","In_igst","In_hsn_code"}, "In_product_name" + "=?",
//                                new String[]{cursor.getString(1)}, null, null, null, null);
//
//                        if (cursor2.moveToFirst()) {
//                            do {
//
//
//
//                                pojoProducttax.setCgst(""+Float.parseFloat(cursor2.getString(0))));
//                                pojoProducttax.setSgst(""+Float.parseFloat(cursor2.getString(1))));
//                                pojoProducttax.setIgst(""+Float.parseFloat(cursor2.getString(2))));
//                                pojoProducttax.setHsn(cursor2.getString(3));
//
//
//
//                            }
//                            while (cursor2.moveToNext());
//                        }
//
//                        Log.e("Check",""+cursor.getString(1));
//
//                        if(ss.equalsIgnoreCase("")) {
//
//
//                        }
//                        else
//                        {
//                            producttaxlist.add(pojoProducttax);
//                            recyclerView.setAdapter(adapter2);
//                        }
//                        // array2.add(cursor.getString(11));
//                        // Log.e("VAL",""+cursor.getString(11));
//
//                    } while (cursor.moveToNext());
//
//                }
//
//                ImageView dialogButton = (ImageView) dialog2.findViewById(R.id.cls);
//                // if button is clicked, close the custom dialog
//                dialogButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog2.dismiss();
//                    }
//                });
//
//                dialog2.show();
//            }
//        });
        supplier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

                suppliername = (String) parent.getItemAtPosition(position);

//                Cursor cursor2 = dbs.query("supplierlist", new String[]{"In_supplier_state_desc","In_supplier_state_code"
//                                }, "In_supplier_name" + "=?",
//                        new String[]{suppliername}, null, null, null, null);
//
//                if (cursor2.moveToFirst()) {
//                    do {
//
//                        //Toast.makeText(Stockinwardnew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();
//
//                        elocation.setText("" + cursor2.getString(0));
//                        code = cursor2.getString(1);
//                        if(sharedpreferences.getString("lo","").equalsIgnoreCase(code))
//                        {
//                            Toast.makeText(Stockinwardnew.this, "Same State", Toast.LENGTH_SHORT).show();
//                            ss="0";
//
//                        }
//                        else
//                        {
//                            Toast.makeText(Stockinwardnew.this, "Out State", Toast.LENGTH_SHORT).show();
//                            ss="1";
//
//                        }
//
//                        view3.setEnabled(true);
//
//                        String selectQueryt = "SELECT  * FROM productlist where flag = 0";
//                        producttaxlist.clear();
//                        Cursor cursort = dbs.rawQuery(selectQueryt, null);
//
//                        // looping through all rows and adding to list
//                        if (cursort.moveToFirst()) {
//                            do {
//
//
//
//                                String idd = cursort.getString(0);
//                                String r = cursort.getString(3);
//                                String it = cursort.getString(1);
//
//
//
//                                Cursor cursort2 = dbs.query("product", new String[]{"In_cgst",
//                                                "In_sgst","In_igst","In_hsn_desc"}, "In_product_name" + "=?",
//                                        new String[]{it}, null, null, null, null);
//
//                                if (cursort2.moveToFirst()) {
//                                    do {
//
//
//                                        if(ss.equalsIgnoreCase("0")) {
//
//
//                                            double c = Double.parseDouble(cursort2.getString(0));
//                                            double s = Double.parseDouble(cursort2.getString(1));
//                                            double rl = Double.parseDouble(r);
//
//                                            double cg = (c / 100) * rl;
//                                            double sg = (s / 100) * rl;
//                                            double f = cg + sg;
//
//                                            fi += f;
//                                            DecimalFormat amountFormate = new DecimalFormat("############.##");
//                                            amountFormate.setMinimumFractionDigits(2);
//                                            amountFormate.setMaximumFractionDigits(2);
//                                            etax.setText(amountFormate.format(fi));
//                                            try {
//                                                double t = Double.parseDouble(etotal.getText().toString());
//                                                double ta = Double.parseDouble(etax.getText().toString());
//
//                                                double fi = t + ta;
//
//                                                einwamt.setText(amountFormate.format(fi));
//                                            } catch (Exception e) {
//
//                                            }
//                                        }
//                                        else
//                                        {
//
//
//                                            if(cursort2.getString(2).equalsIgnoreCase("0.0000")) {
//
//                                                double c = Double.parseDouble(cursort2.getString(0));
//                                                double s = Double.parseDouble(cursort2.getString(1));
//                                                double rl = Double.parseDouble(r);
//
//                                                double cg = (c / 100) * rl;
//                                                double sg = (s / 100) * rl;
//                                                double f = cg + sg;
//
//                                                fi += f;
//                                                DecimalFormat amountFormate = new DecimalFormat("############.##");
//                                                amountFormate.setMinimumFractionDigits(2);
//                                                amountFormate.setMaximumFractionDigits(2);
//                                                etax.setText(amountFormate.format(fi));
//                                                try {
//                                                    double t = Double.parseDouble(etotal.getText().toString());
//                                                    double ta = Double.parseDouble(etax.getText().toString());
//
//                                                    double fi = t + ta;
//
//                                                    einwamt.setText(amountFormate.format(fi));
//                                                } catch (Exception e) {
//
//                                                }
//                                            }
//                                            else
//                                            {
//                                                double c = Double.parseDouble(cursort2.getString(0));
//                                                double s = Double.parseDouble(cursort2.getString(1));
//                                                double ig = Double.parseDouble(cursort2.getString(2));
//                                                double rl = Double.parseDouble(r);
//
//                                                double cg = (c / 100) * rl;
//                                                double sg = (s / 100) * rl;
//                                                double ig2 = (ig / 100) * rl;
//                                                double f = cg + sg + ig2;
//
//                                                fi += f;
//                                                DecimalFormat amountFormate = new DecimalFormat("############.##");
//                                                amountFormate.setMinimumFractionDigits(2);
//                                                amountFormate.setMaximumFractionDigits(2);
//                                                etax.setText(amountFormate.format(fi));
//                                                try {
//                                                    double t = Double.parseDouble(etotal.getText().toString());
//                                                    double ta = Double.parseDouble(etax.getText().toString());
//
//                                                    double fi = t + ta;
//
//                                                    einwamt.setText(amountFormate.format(fi));
//                                                } catch (Exception e) {
//
//                                                }
//                                            }
//                                        }
//
//                                    }
//                                    while (cursort2.moveToNext());
//                                }
//
////                Log.e("Check",""+cursor.getString(1));
//
//
//                                // array2.add(cursor.getString(11));
//                                // Log.e("VAL",""+cursor.getString(11));
//
//                            } while (cursort.moveToNext());
//
//                        }
//
//
//
//
//                    }
//                    while (cursor2.moveToNext());
//
//
//                }
                counttotal();
            }
        });
        // looping through all rows and adding to list


        bsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(fp == 0)
               {
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
                   // Toast.makeText(Stockinwardnew.this, ""+formattedDate, Toast.LENGTH_SHORT).show();
                   if(supplier.getText().toString().equalsIgnoreCase(""))
                   {
                       // Toast.makeText(Stockinwardnew.this, "Supplier Name is Empty", Toast.LENGTH_SHORT).show();
                       final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
//set icon
                               .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                               .setTitle("Error!")
//set message
                               .setMessage("Please Enter Supplier Name !")
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
                   else if(elocation.getText().toString().equalsIgnoreCase(""))
                   {
                       //Toast.makeText(Stockinwardnew.this, "Supplier Location is Empty", Toast.LENGTH_SHORT).show();
                       final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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
//                else if(etransport.getText().toString().equalsIgnoreCase(""))
//                {
//                    //Toast.makeText(Stockinwardnew.this, "Transport is Empty", Toast.LENGTH_SHORT).show();
//                    final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
////set icon
//                            .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                            .setTitle("Error!")
////set message
//                            .setMessage("Please Enter Transport Amount !")
////set positive button
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialogInterface, int i) {
//                                            //set what would happen when positive button is clicked
//
//                                        }
//                                    }
////set negative button
//
//                            )
//                            .show();
//
//                }
                   else if(edate.getText().toString().equalsIgnoreCase(""))
                   {
                       //Toast.makeText(Stockinwardnew.this, "Date is Empty", Toast.LENGTH_SHORT).show();
                       final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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
                else if(ebillno.getText().toString().equalsIgnoreCase(""))
                {
                    //Toast.makeText(Stockinwardnew.this, "Others is Empty", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Please Enter Bill No !")
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
//                else if(ui.equalsIgnoreCase("0"))
//                {
//                    //Toast.makeText(Stockinwardnew.this, "Bill Capture is Empty", Toast.LENGTH_SHORT).show();
//                    final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
////set icon
//                            .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                            .setTitle("Error!")
////set message
//                            .setMessage("Please Capture Bill Image!")
////set positive button
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialogInterface, int i) {
//                                            //set what would happen when positive button is clicked
//
//                                        }
//                                    }
////set negative button
//
//                            )
//                            .show();
//
//                }
                   else
                   {
                       if(isNetworkAvailable())
                       {
                           String checkquery = "SELECT  * FROM inwardlist where flag = 0";
                           Cursor ckcursor = dbs.rawQuery(checkquery, null);
                           if(ckcursor.getCount()!=0)
                           {
                               final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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

                               Long tsLong = System.currentTimeMillis() / 1000;
                               String ts = tsLong.toString();
                               String selectQuerys = "SELECT  * FROM inwardlist";
                               Cursor cursors = dbs.rawQuery(selectQuerys, null);
                               int co = cursors.getCount() + 1;
                               String inno = ts + "/" + formattedDate + "/INWDNO000" + co;
                               // einwno.setText(inno);
                               db.insertinward(inno, edate.getText().toString(), supplier.getText().toString(), elocation.getText().toString(), etax.getText().toString(), trp,oths, einwamt.getText().toString(), sharedpreferences.getString("ids", ""), sharedpreferences.getString("itemlist", ""), "", "0", ui, code, "",ebillno.getText().toString());
                               // Toast.makeText(getApplicationContext(),"Submitted Successfully",Toast.LENGTH_SHORT).show();
                               SQLiteDatabase dbs = db.getWritableDatabase();

                               // finish();
                               String selectQuery = "SELECT  * FROM inwardlist where flag = 0";
                               Cursor cursor2 = dbs.rawQuery(selectQuery, null);
                               Log.e("CCCC", "IC" + cursor2.getCount());
                               ct = cursor2.getCount();
                               if (cursor2.moveToFirst()) {
                                   do {
                                       id = cursor2.getString(0);
                                       inwid = cursor2.getString(1);
                                       sname = cursor2.getString(3);
                                       slcn = cursor2.getString(4);
                                       sst = cursor2.getString(4);
                                       net = cursor2.getString(8);
                                       tax = cursor2.getString(5);
                                       trs = cursor2.getString(6);
                                       oth = cursor2.getString(7);
                                       ids = cursor2.getString(9);
                                       billno = cursor2.getString(16);
                                       ids2 = ids.substring(1, ids.length() - 1);
                                       lc = cursor2.getString(14);

                                       //Toast.makeText(Dashboard.this, ""+ids, Toast.LENGTH_SHORT).show();
                                       if(cursor2.getString(13).equalsIgnoreCase("0"))
                                       {
                                           save();

                                       }
                                       else {
                                           final Uri imageUri = Uri.parse(cursor2.getString(13));
                                           final InputStream imageStream;
                                           try {
                                               imageStream = getContentResolver().openInputStream(imageUri);
                                               final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                                               encodedimage = encodeImage(selectedImage);
                                               save();
                                           } catch (FileNotFoundException e) {
                                               e.printStackTrace();
                                           }
                                       }


                                   } while (cursor2.moveToNext());
                               }


                           }

                       }
                       else
                       {
                           Long tsLong = System.currentTimeMillis()/1000;
                           String ts = tsLong.toString();
                           String selectQuerys = "SELECT  * FROM inwardlist";
                           Cursor cursors = dbs.rawQuery(selectQuerys, null);
                           int co = cursors.getCount() + 1;
                           String inno = ts+"/"+formattedDate+"/INWDNO000"+co;

                           String lindno = sharedpreferences.getString("inwardno","");
                           String[] lindno2 = lindno.split("/");
                           int xi = Integer.parseInt(lindno2[1])+1;
                           String vv = String.valueOf(xi);
                           if(vv.length() == 1)
                           {
                               einwno.setText(lindno2[0]+"/0000"+vv);
                               SharedPreferences.Editor editor = sharedpreferences.edit();

                               editor.putString("inwardno", einwno.getText().toString());

                               editor.commit();
                               db.insertinward(inno,edate.getText().toString(),supplier.getText().toString(),elocation.getText().toString(),etax.getText().toString(),etransport.getText().toString(),eothers.getText().toString(),einwamt.getText().toString(),sharedpreferences.getString("ids",""),sharedpreferences.getString("itemlist",""),sharedpreferences.getString("ocnew",""),"0",ui,code,lindno2[0]+"/0000"+vv,ebillno.getText().toString());
                               //Toast.makeText(Stockinwardnew2.this, lindno2[0]+"/00"+vv, Toast.LENGTH_SHORT).show();

                           }
                           else if(vv.length() == 2)
                           {
                               einwno.setText(lindno2[0]+"/000"+vv);
                               SharedPreferences.Editor editor = sharedpreferences.edit();

                               editor.putString("inwardno", einwno.getText().toString());

                               editor.commit();
                               db.insertinward(inno,edate.getText().toString(),supplier.getText().toString(),elocation.getText().toString(),etax.getText().toString(),etransport.getText().toString(),eothers.getText().toString(),einwamt.getText().toString(),sharedpreferences.getString("ids",""),sharedpreferences.getString("itemlist",""),sharedpreferences.getString("ocnew",""),"0",ui,code,lindno2[0]+"/000"+vv,ebillno.getText().toString());
                               //Toast.makeText(Stockinwardnew2.this, lindno2[0]+"/00"+vv, Toast.LENGTH_SHORT).show();

                           }
                           else if(vv.length() == 3)
                           {
                               einwno.setText(lindno2[0]+"/00"+vv);
                               SharedPreferences.Editor editor = sharedpreferences.edit();

                               editor.putString("inwardno", einwno.getText().toString());

                               editor.commit();
                               db.insertinward(inno,edate.getText().toString(),supplier.getText().toString(),elocation.getText().toString(),etax.getText().toString(),etransport.getText().toString(),eothers.getText().toString(),einwamt.getText().toString(),sharedpreferences.getString("ids",""),sharedpreferences.getString("itemlist",""),sharedpreferences.getString("ocnew",""),"0",ui,code,lindno2[0]+"/00"+vv,ebillno.getText().toString());
                               //Toast.makeText(Stockinwardnew2.this, lindno2[0]+"/00"+vv, Toast.LENGTH_SHORT).show();

                           }
                           else if(vv.length() == 4)
                           {
                               // Toast.makeText(Stockinwardnew2.this, "0"+vv, Toast.LENGTH_SHORT).show();
                               einwno.setText(lindno2[0]+"/0"+vv);
                               SharedPreferences.Editor editor = sharedpreferences.edit();

                               editor.putString("inwardno", einwno.getText().toString());

                               editor.commit();
                               db.insertinward(inno,edate.getText().toString(),supplier.getText().toString(),elocation.getText().toString(),etax.getText().toString(),etransport.getText().toString(),eothers.getText().toString(),einwamt.getText().toString(),sharedpreferences.getString("ids",""),sharedpreferences.getString("itemlist",""),sharedpreferences.getString("ocnew",""),"0",ui,code,lindno2[0]+"/0"+vv,ebillno.getText().toString());
                           }
                           else
                           {
                               einwno.setText(lindno2[0]+"/"+vv);
                               SharedPreferences.Editor editor = sharedpreferences.edit();

                               editor.putString("inwardno", einwno.getText().toString());

                               editor.commit();
                               db.insertinward(inno,edate.getText().toString(),supplier.getText().toString(),elocation.getText().toString(),etax.getText().toString(),etransport.getText().toString(),eothers.getText().toString(),einwamt.getText().toString(),sharedpreferences.getString("ids",""),sharedpreferences.getString("itemlist",""),sharedpreferences.getString("ocnew",""),"0",ui,code,lindno2[0]+"/"+vv,ebillno.getText().toString());
                               // Toast.makeText(Stockinwardnew2.this, ""+vv, Toast.LENGTH_SHORT).show();
                           }
                           eicd.setEnabled(true);
                           esl.setEnabled(false);
                           item.setEnabled(false);
                           eicd.setEnabled(true);
                           //Toast.makeText(getApplicationContext(),"Submitted Successfully",Toast.LENGTH_SHORT).show();
                           SQLiteDatabase dbs = db.getWritableDatabase();

                           //  SQLiteDatabase dbs = db.getWritableDatabase();

                           // finish();
                           String selectQuery2 = "SELECT  * FROM inwardlist where flag = 0";
                           Cursor cursor22 = dbs.rawQuery(selectQuery2, null);
                           Log.e("CCCC","IC"+cursor22.getCount());
                           ct = cursor22.getCount();
                           if (cursor22.moveToFirst()) {
                               do {
                                   id = cursor22.getString(0);
                                   inwid = cursor22.getString(1);
                                   sname = cursor22.getString(3);
                                   slcn = cursor22.getString(4);
                                   sst = cursor22.getString(4);
                                   net = cursor22.getString(8);
                                   tax = cursor22.getString(5);
                                   trs = cursor22.getString(6);
                                   oth = cursor22.getString(7);
                                   ids = cursor22.getString(9);
                                   ids2 = ids.substring(1, ids.length() - 1);
                                   lc = cursor22.getString(14);
                                   billno = cursor22.getString(16);

                                   //Toast.makeText(Dashboard.this, ""+ids, Toast.LENGTH_SHORT).show();
                                   if(cursor22.getString(13).equalsIgnoreCase("0"))
                                   {
                                       // save();
                                   }
                                   else {
                                       final Uri imageUri = Uri.parse(cursor22.getString(13));
                                       final InputStream imageStream;
                                       try {
                                           imageStream = getContentResolver().openInputStream(imageUri);
                                           final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                                           encodedimage = encodeImage(selectedImage);
                                           // save();
                                       } catch (FileNotFoundException e) {
                                           e.printStackTrace();
                                       }
                                   }






                               } while (cursor22.moveToNext());
                           }
                           dbs.execSQL("UPDATE productlist SET flag = 1 WHERE flag = 0");
                           // finish();

                           String[] id = ids2.split(",");

                           for(int i = 0 ; i< id.length;i++)
                           {

                               String selectQuery = "SELECT  * FROM productlist where id ="+id[i];
                               Cursor cursor2 = dbs.rawQuery(selectQuery, null);
                               if (cursor2.moveToFirst()) {
                                   do {
                                       pn = cursor2.getString(1);
                                       pq = cursor2.getString(2);


                                       Cursor cursor3 = dbs.query("product", new String[]{"In_product_code",
                                                       "In_productcategory_code","In_productsubcategory_code","In_uomtype_code","In_cgst","In_sgst","In_current_qty","id"}, "In_product_name" + "=?",
                                               new String[]{pn}, null, null, null, null);

                                       if (cursor3.moveToFirst()) {
                                           do {

                                               String ii = cursor3.getString(7);
                                               double yy = Double.parseDouble(pq);
                                               double xx = Double.parseDouble(cursor3.getString(6));
                                               double zz = xx+yy;

                                               // newcq = String.valueOf(z);
                                               dbs.execSQL("UPDATE product SET In_current_qty = " +zz+ " WHERE id = "+ii);
                                           }

                                           while (cursor3.moveToNext());
                                       }
                                   }

                                   while (cursor2.moveToNext());
                               }


                           }

                           final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
//set icon
                                   .setIcon(android.R.drawable.ic_menu_save)
//set title
                                   .setTitle("Success!")
//set message
                                   .setMessage("Successfully Submited!")
//set positive button
                                   .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                               @Override
                                               public void onClick(DialogInterface dialogInterface, int i) {
                                                   //set what would happen when positive button is clicked
                                                   Intent in = new Intent(getApplicationContext(),Landpage.class);
                                                   startActivity(in);
                                               }
                                           }
//set negative button

                                   )
                                   .show();
                       }



                   }
               }
               else
               {
                   if(spinner.getSelectedItem().toString().equalsIgnoreCase("Select Payment Mode"))
                   {
                       // Toast.makeText(Stockinwardnew.this, "Select Paymode", Toast.LENGTH_SHORT).show();
                       final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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
                       // Toast.makeText(Stockinwardnew.this, "Enter Amount Paid", Toast.LENGTH_SHORT).show();
                       final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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
                       //Toast.makeText(Stockinwardnew.this, "Enter Reference No", Toast.LENGTH_SHORT).show();
                       final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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
                       //Toast.makeText(Stockinwardnew.this, "Select Date", Toast.LENGTH_SHORT).show();
                       final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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
                       final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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
                   else
                   {
                       if(isNetworkAvailable())
                       {
                           Log.e("CHECK",""+ebalance.getText());
                           db.updateinward(Integer.parseInt(pyid.toString()), itext2.toString(), ebalance.getText().toString(), balance.getText().toString(), amount.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), supplier.getText().toString(), "0");

                           if (ck == 0) {


                               db.paylist(itext, spinner.getSelectedItem().toString(), eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "0", "0","","");
                           } else {
                               db.paylist(itext, spinner.getSelectedItem().toString(), eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "0", chno.getText().toString(),"","");

                           }

                           if(chno.getText().toString().equalsIgnoreCase(""))
                           {
                               cqno = "";
                           }
                           else
                           {
                               cqno = chno.getText().toString();
                           }
                           savep();
                       }
                   }
               }

            }
        });


        item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                esl.setEnabled(true);
                Long tsLong = System.currentTimeMillis()/1000;
                tspr = tsLong.toString()+"SINW";


                Cursor cursor2 = dbs.query("product", new String[]{"In_product_code",
                                "In_base_price","id","In_current_qty","valueadded"}, "In_product_name" + "=?",
                        new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);

                if (cursor2.moveToFirst()) {
                    do {
                        double d = Double.parseDouble(cursor2.getString(1));
                        idn = cursor2.getString(2);
                        cq= cursor2.getString(2);
                        if(cursor2.getString(4).equalsIgnoreCase("Yes"))
                        {
                            erate.setEnabled(false);
                            erate.setText("0");
                        }
                        else
                        {
                            erate.setEnabled(true);
                            erate.setText("");
                        }
                        //Toast.makeText(Stockinwardnew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

                        // erate.setText("" + d);


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
                else if( s.toString().startsWith("."))
                {
                    s.clear();
                }
                else if(s.toString().length()==0)
                {
                    //  equantity.setText("");
                }else if(s.length() != 0) {
                    getAmt();
                }
                else
                {
                    eamount.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {


            }
        });

        erate.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() == 1 && s.toString().startsWith("0")) {
                   // s.clear();
                }
                else if(s.toString().startsWith("."))
                {
                    s.clear();
                }
                else if(s.toString().length()==0)
                {
                    //erate.setText("");
                }else if(s.length() != 0) {
                    getAmt2();
                }
                else
                {
                    //  erate.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {


            }
        });

        etransport.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() == 1 && s.toString().startsWith("0")) {
                   // s.clear();
                }
                else if(s.toString().length() == 1 && s.toString().startsWith("."))
                {
                   // s.clear();
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
                    getAmtt();
                else
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


//        eothers.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                if (s.toString().length() == 1 && s.toString().startsWith("0")) {
//                    s.clear();
//                }
//                else if(s.toString().length() == 1 && s.toString().startsWith("."))
//                {
//                    s.clear();
//                }
//                else if(s.toString().length()==0)
//                {
//                    //  equantity.setText("");
//                }
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start,
//                                          int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start,
//                                      int before, int count) {
//
//
//                if(s.length() != 0)
//                    getAmto();
//                else
//                {
//                    try {
//                        if(etransport.getText().toString().equalsIgnoreCase("")) {
//                            DecimalFormat amountFormate = new DecimalFormat("############.##");
//                            amountFormate.setMinimumFractionDigits(2);
//                            amountFormate.setMaximumFractionDigits(2);
//                            einwamt.setText(""+fi);
//                            double t = Double.parseDouble(etotal.getText().toString());
//                            double ta = Double.parseDouble(etax.getText().toString());
//
//                            double fi = t + ta;
//
//                            einwamt.setText(""+fi);
//                        }
//                        else
//                        {
//                            DecimalFormat amountFormate = new DecimalFormat("############.##");
//                            amountFormate.setMinimumFractionDigits(2);
//                            amountFormate.setMaximumFractionDigits(2);
//                            einwamt.setText(""+fi);
//                            double t = Double.parseDouble(etotal.getText().toString());
//                            double ta = Double.parseDouble(etax.getText().toString());
//                            double tt = Double.parseDouble(etransport.getText().toString());
//
//                            double fi = t + ta+tt;
//
//                            einwamt.setText(""+fi);
//                        }
//                    }
//                    catch (Exception e)
//                    {
//
//                    }
//                }
//
//            }
//        });


        othercs.addTextChangedListener(new TextWatcher() {

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


                if(s.length() != 0)
                    getAmt3();
                else
                {
                    getAmt2();
                    //  erate.setText("");
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
                    //  etransport.setEnabled(true);
                    // eothers.setEnabled(true);
                }
                else
                {
                    // getAmt2();
                    //etransport.setEnabled(false);
                    // eothers.setEnabled(false);
                    //  erate.setText("");
                }

            }
        });

        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getText().toString().equalsIgnoreCase(""))
                {
                    // Toast.makeText(Stockinwardnew.this, "Please Enter Item", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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
                }
                else if(eamount.getText().toString().equalsIgnoreCase(""))
                {
                    //Toast.makeText(Stockinwardnew.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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
                    //Toast.makeText(Stockinwardnew.this, "Please Enter Rate", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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
                    //Toast.makeText(Stockinwardnew.this, "Please Enter Rate", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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

                            db.insertproduct(item.getText().toString(), equantity.getText().toString(), erate.getText().toString(), eamount.getText().toString(), othercs.getText().toString(), enetamount.getText().toString(),idn,"0",tspr);
                            //Toast.makeText(Stockinwardnew.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                            counttotal();
                            eicd.setEnabled(false);
                            item.setText("");
                            erate.setText("");
                            equantity.setText("");
                            ediscount.setText("");
                            eamount.setText("");
                            enetamount.setText("");
                            esl.setText("");
                            otherip.setText("");
                            othercs.setText("");
                            eicd.setEnabled(false);
                            final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Product Added Successfully....")
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
                            db.insertproduct(item.getText().toString(), equantity.getText().toString(), erate.getText().toString(), eamount.getText().toString(), othercs.getText().toString(), enetamount.getText().toString(),idn,"0",tspr);
                            //Toast.makeText(Stockinwardnew.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                            counttotal();
                            eicd.setEnabled(false);
                            item.setText("");
                            esl.setText("");
                            erate.setText("");
                            equantity.setText("");
                            ediscount.setText("");
                            eamount.setText("");
                            enetamount.setText("");
                            otherip.setText("");
                            othercs.setText("");
                            final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Product Added Successfully....")
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
//                       finish();
//                       startActivity(getIntent());
                    }
                    else
                    {
                        if (ediscount.getText().toString().equalsIgnoreCase("")) {
                            bq = 0;
                            db.updateproduct(Integer.valueOf(prid),item.getText().toString(), equantity.getText().toString(), erate.getText().toString(), eamount.getText().toString(), "", enetamount.getText().toString(),idn,"0",tspr);
                            // Toast.makeText(Stockinwardnew.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                            counttotal();
                            item.setText("");
                            erate.setText("");
                            equantity.setText("");
                            ediscount.setText("");
                            otherip.setText("");
                            othercs.setText("");
                            eamount.setText("");
                            esl.setText("");
                            item.setEnabled(true);
                            item.showDropDown();
                            enetamount.setText("");
                            badd.setText("Add");
                            eicd.setEnabled(false);
                            final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Product Updated Successfully....")
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
                            db.updateproduct(Integer.valueOf(prid),item.getText().toString(), equantity.getText().toString(), erate.getText().toString(), eamount.getText().toString(), ediscount.getText().toString(), enetamount.getText().toString(),idn,"0",tspr);
                            //Toast.makeText(Stockinwardnew.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                            counttotal();
                            item.setText("");
                            erate.setText("");
                            eicd.setEnabled(false);
                            equantity.setText("");
                            ediscount.setText("");
                            otherip.setText("");
                            othercs.setText("");
                            eamount.setText("");
                            item.setEnabled(true);
                            item.showDropDown();
                            esl.setText("");
                            enetamount.setText("");
                            badd.setText("Add");
                            final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Product Updated Successfully....")
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
//                       finish();
//                       startActivity(getIntent());
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

       // edate.setText(formattedDate);

        edate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Stockinwardnew.this,android.R.style.Theme_Holo_Dialog , date, myCalendar
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
                    double ta = 0;
                    if (etax.getText().toString().equalsIgnoreCase("")) {
                         ta = Double.parseDouble(newnet.getText().toString());
                    } else {
                         ta = Double.parseDouble(newnet.getText().toString()) + Double.parseDouble(etax.getText().toString());
                    }


                    Intent i = new Intent(getApplicationContext(), Viewstockinward.class);
                    i.putExtra("FRM", "0");
                    i.putExtra("T", etransport.getText().toString());
                    i.putExtra("O", eothers.getText().toString());
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
//        einwamt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(),Viewstockinward.class);
//                i.putExtra("FRM","0");
//                i.putExtra("T",etransport.getText().toString());
//                i.putExtra("O",eothers.getText().toString());
//                i.putExtra("ss",ss);
//                i.putExtra("II","ii");
//                startActivity(i);
//                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
//            }
//        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(Stockinwardnew.this);
                dialog.setContentView(R.layout.viewitem);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                // set the custom dialog components - text, image and button





                androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(Stockinwardnew.this));
                adapter = new MyRecyclerViewAdapter(Stockinwardnew.this, productlist);
                String selectQuery = "SELECT  * FROM productlist where flag = 0";
                productlist.clear();
                Cursor cursor = dbs.rawQuery(selectQuery, null);

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        pojoProduct pojoProduct = new pojoProduct();

                        pojoProduct.setId(cursor.getString(0));
                        pojoProduct.setItem(cursor.getString(1));
                        pojoProduct.setQty(cursor.  getString(2));
                        pojoProduct.setRate(cursor.getString(3));
                        pojoProduct.setAmt(cursor.getString(4));
                        pojoProduct.setDis(cursor.getString(5));
                        pojoProduct.setNamt(cursor.getString(6));
                        pojoProduct.setId2(cursor.getString(7));
                        pojoProduct.setTspr(cursor.getString(9));
                        Log.e("Check",""+cursor.getString(1));

                        productlist.add(pojoProduct);
                        // array2.add(cursor.getString(11));
                        // Log.e("VAL",""+cursor.getString(11));
                        recyclerView.setAdapter(adapter);
                    } while (cursor.moveToNext());

                }

                // set up the RecyclerView



                ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
                EditText ecount2 = (EditText) dialog.findViewById(R.id.ecount);
                EditText etotal2 = (EditText) dialog.findViewById(R.id.etotal);
                ecount2.setText(""+ecount.getText().toString());
                etotal2.setText(""+etotal.getText().toString());
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


        //////////////////////Tax Calculation///////////////////////////////////////




        ////////////////////////////////////////////////////////////////////////////////////




        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2 = new Dialog(Stockinwardnew.this);
                dialog2.setContentView(R.layout.viewtaxd);
                dialog2.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog2.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                dialog2.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                // set the custom dialog components - text, image and button

                // set up the RecyclerView
                androidx.recyclerview.widget.RecyclerView recyclerView = dialog2.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(Stockinwardnew.this));
                adapter2 = new MyRecyclerViewAdapter2(Stockinwardnew.this, producttaxlist);

                String selectQuery = "SELECT  * FROM productlist where flag = 0";
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
                                        "In_sgst","In_igst","In_hsn_code"}, "In_product_name" + "=?",
                                new String[]{cursor.getString(1)}, null, null, null, null);

                        if (cursor2.moveToFirst()) {
                            do {



                                pojoProducttax.setCgst(""+Float.parseFloat(cursor2.getString(0)));
                                pojoProducttax.setSgst(""+Float.parseFloat(cursor2.getString(1)));
                                pojoProducttax.setIgst(""+Float.parseFloat(cursor2.getString(2)));
                                pojoProducttax.setHsn(cursor2.getString(3));



                            }
                            while (cursor2.moveToNext());
                        }

                        Log.e("Check",""+cursor.getString(1));

                        if(ss.equalsIgnoreCase("")) {


                        }
                        else
                        {
                            producttaxlist.add(pojoProducttax);
                            recyclerView.setAdapter(adapter2);
                        }
                        // array2.add(cursor.getString(11));
                        // Log.e("VAL",""+cursor.getString(11));

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
        });
        detail = (Button)findViewById(R.id.detail);
        header = (Button)findViewById(R.id.header);
        l1 =(LinearLayout)findViewById(R.id.l1);
        l2 = (LinearLayout)findViewById(R.id.l2);

        detail.setBackgroundResource(R.drawable.rbutton6);
        header.setBackgroundResource(R.drawable.rbutton4);
        l1.setVisibility(View.VISIBLE);
        l2.setVisibility(View.GONE);
        bsave.setVisibility(View.GONE);
        badd.setVisibility(View.VISIBLE);
        view.setVisibility(View.VISIBLE);
        rdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail.setBackgroundResource(R.drawable.rbutton6);
                l2.setVisibility(View.GONE);
                l1.setVisibility(View.VISIBLE);
                header.setBackgroundResource(R.drawable.rbutton4);
                bsave.setVisibility(View.GONE);
                badd.setVisibility(View.VISIBLE);
                view.setVisibility(View.VISIBLE);
                im1.setBackgroundResource(R.drawable.detail2);
                im2.setBackgroundResource(R.drawable.summary);
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);
                txtheader.setText("Details");
                im3.setBackgroundResource(R.drawable.payment);
                v3.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);


            }
        });

        rsummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cr = dbs.rawQuery("select * from productlist ",null);
                try{
                    if(cr.getCount() > 0){
                        header.setBackgroundResource(R.drawable.rbutton6);
                        l2.setVisibility(View.VISIBLE);
                        l1.setVisibility(View.GONE);
                        bsave.setVisibility(View.VISIBLE);
                        badd.setVisibility(View.GONE);
                        view.setVisibility(View.GONE);
                        txtheader.setText("Summary");
                        im1.setBackgroundResource(R.drawable.details);
                        im2.setBackgroundResource(R.drawable.summary2);
                        v1.setVisibility(View.GONE);
                        v2.setVisibility(View.VISIBLE);
                        detail.setBackgroundResource(R.drawable.rbutton4);
                        im3.setBackgroundResource(R.drawable.payment);
                        v3.setVisibility(View.GONE);
                        l3.setVisibility(View.GONE);
                    }
                }catch (Exception er){
                    Log.e("error",er.toString());
                }finally {
                    cr.close();
                }

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


                if(s.length() != 0)
                {
                    double x = Double.parseDouble(amount.getText().toString());
                    double y = Double.parseDouble(eamtpaid.getText().toString());
                    if(y>x)
                    {
                        eamtpaid.setText("");
                        // Toast.makeText(Bookinvoicenew.this, "Invalid amount", Toast.LENGTH_SHORT).show();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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
                        einwamt.setText(""+(""+String.format("%.2f", fi)));
                        ebal.setText("" + (""+String.format("%.2f", z)));
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
        rpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l2.setVisibility(View.GONE);
                l1.setVisibility(View.GONE);
                l3.setVisibility(View.VISIBLE);
               // header.setBackgroundResource(R.drawable.rbutton4);
                bsave.setVisibility(View.VISIBLE);
                badd.setVisibility(View.GONE);
                view.setVisibility(View.GONE);
                im1.setBackgroundResource(R.drawable.details);
                im2.setBackgroundResource(R.drawable.summary);
                im3.setBackgroundResource(R.drawable.payment2);
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.VISIBLE);
                txtheader.setText("Payment");
                fp = 1;

                List<String> categories = new ArrayList<String>();
                categories.add("Select Payment Mode");
                categories.add("CASH");
                categories.add("CHEQUE");
                categories.add("CARD");
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Stockinwardnew.this, android.R.layout.simple_spinner_item, categories);

                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner
                spinner.setAdapter(dataAdapter);
            }
        });





    }
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edate.setText(sdf.format(myCalendar.getTime()));
    }
    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

        private List<pojoProduct> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<pojoProduct> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewitemlist, parent, false);
            return new MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            final pojoProduct pojoProduct = mData.get(position);
            holder.myTextView.setText(pojoProduct.getItem());
            holder.tamt.setText(pojoProduct.getAmt());
            holder.tnamt.setText(pojoProduct.getNamt());
            holder.tqty.setText(pojoProduct.getQty());
            holder.trate.setText(pojoProduct.getRate());
            holder.tdis.setText(pojoProduct.getDis());

            holder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    db.deleteProductlist(Integer.valueOf(pojoProduct.getId()));
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
                    erate.setText(pojoProduct.getRate());
                    equantity.setText(pojoProduct.getQty());
                    eamount.setText(pojoProduct.getAmt());
                    enetamount.setText(pojoProduct.getNamt());
                    ediscount.setText(pojoProduct.getDis());
                    item.setEnabled(false);
                    item.dismissDropDown();
                    fetchsn();
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


    public class MyRecyclerViewAdapter2 extends RecyclerView.Adapter<MyRecyclerViewAdapter2.ViewHolder> {

        private List<pojoProducttax> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapter2(Context context, List<pojoProducttax> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewtax, parent, false);
            return new MyRecyclerViewAdapter2.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(MyRecyclerViewAdapter2.ViewHolder holder, int position) {
            pojoProducttax pojoProducttax = mData.get(position);





            if(ss.equalsIgnoreCase(""))
            {

            }
            else if(ss.equalsIgnoreCase("0"))
            {
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
                double sa =Double.parseDouble(pojoProducttax.getNamt())/100;
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
            LinearLayout ltax;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.t1);
                hsn = itemView.findViewById(R.id.hsn);
                tamt = itemView.findViewById(R.id.tamt);
                cgst = itemView.findViewById(R.id.cgst);
                sgst = itemView.findViewById(R.id.sgst);
                igst = itemView.findViewById(R.id.igst);
                cgst2 = itemView.findViewById(R.id.cgsta);
                sgst2 = itemView.findViewById(R.id.sgsta);
                igst2 = itemView.findViewById(R.id.igsta);
                ltax = itemView.findViewById(R.id.ltax);
                tamt1 = itemView.findViewById(R.id.tamt2);

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
            try{
                double x = Double.parseDouble(equantity.getText().toString());
                double y = Double.parseDouble(erate.getText().toString());

                DecimalFormat amountFormate  = new DecimalFormat("############.##");
                amountFormate.setMinimumFractionDigits(2);
                amountFormate.setMaximumFractionDigits(2);

                eamount.setText(""+String.format("%.2f", ((float) ((x * y)))));
                enetamount.setText(""+String.format("%.2f", ((float) ((x * y)))));
            }catch (Exception er){
                Log.e("error",er.toString());
            }
        }
    }

    public void  getAmt2() {
        if (equantity.getText().toString().equalsIgnoreCase("") ) {

        } else {
            try{
                if(othercs.getText().toString().equalsIgnoreCase("")) {

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
                    double z = Double.parseDouble(othercs.getText().toString());


                    DecimalFormat amountFormate = new DecimalFormat("############.##");
                    amountFormate.setMinimumFractionDigits(2);
                    amountFormate.setMaximumFractionDigits(2);

                    eamount.setText("" + ((float) ((x * y))));
                    enetamount.setText("" + ((float) ((x * y)+z)));
                }
            }catch (Exception er){
                Log.e("error",er.toString());
            }
        }
    }

    public void  getAmt3() {
        if (equantity.getText().toString().equalsIgnoreCase("") || erate.getText().toString().equalsIgnoreCase("")) {

        } else {

            double x = Double.parseDouble(equantity.getText().toString());
            double y = Double.parseDouble(erate.getText().toString());
            double z = Double.parseDouble(othercs.getText().toString());
            double za = Double.parseDouble(eamount.getText().toString());

            double zf = (z/100)*za;


            enetamount.setText(("" +String.format("%.2f",(float) ((x * y) +( z)))));
        }
    }
    public void  getAmtt() {
       try
       {
           if (eothers.getText().toString().equalsIgnoreCase("") ) {
               double x = Double.parseDouble(etransport.getText().toString());
               double t = Double.parseDouble(etotal.getText().toString());
               double ta = 0;
               if(x>t)
               {
                   etransport.setText("");
                   final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
//set icon
                           .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                           .setTitle("Error!")
//set message
                           .setMessage("Other discount can't be greater than total amount")
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
                   if (etax.getText().toString().equalsIgnoreCase("")) {
                       ta = 0;

                   } else {
                       ta = Double.parseDouble(etax.getText().toString());

                   }

                   double fi = (t + ta) - x;
                   DecimalFormat amountFormate = new DecimalFormat("############.##");
                   amountFormate.setMinimumFractionDigits(2);
                   amountFormate.setMaximumFractionDigits(2);
                   einwamt.setText("" + ("" + String.format("%.2f", fi)));
               }
           } else {


               double x = Double.parseDouble(etransport.getText().toString());
               double y = Double.parseDouble(eothers.getText().toString());
               double t = Double.parseDouble(etotal.getText().toString());
               double ta = 0;
               if (x > t) {
                   etransport.setText("");
                   final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
//set icon
                           .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                           .setTitle("Error!")
//set message2323
                           .setMessage("Other discount can't be greater than total amount")
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
                   if (etax.getText().toString().equalsIgnoreCase("")) {
                       ta = 0;

                   } else {
                       ta = Double.parseDouble(etax.getText().toString());

                   }

                   double fi = (y + t + ta) - x;
                   DecimalFormat amountFormate = new DecimalFormat("############.##");
                   amountFormate.setMinimumFractionDigits(2);
                   amountFormate.setMaximumFractionDigits(2);
                   einwamt.setText("" + ("" + String.format("%.2f", fi)));
               }
           }

       }
       catch (Exception e)
       {

       }



    }

    public void  getAmto() {
        if (etransport.getText().toString().equalsIgnoreCase("") ) {

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

            double fi = y +t +ta;

            DecimalFormat amountFormate  = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);
            einwamt.setText(""+(""+String.format("%.2f", fi)));
        } else {

            double x = Double.parseDouble(etransport.getText().toString());
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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
////            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            Bundle extras = data.getExtras();
//            thePic = (Bitmap) extras.get("data");
//            cap.setImageBitmap(thePic);
//            vcap.setImageBitmap(thePic);
//        }

        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_CAPTURE) {
                Uri uri = picUri;
                Log.d("picUri", picUri.toString());
                // performCrop();
                startCropImageActivity(uri);
            } else if (requestCode == 2) {
                Bundle extras = data.getExtras();
//get the cropped bitmap
                thePic = (Bitmap) extras.get("data");
                Log.e("PIC", "" + thePic);
                cap.setImageBitmap(thePic);
                vcap.setImageBitmap(thePic);


                byteArrayOutputStream = new ByteArrayOutputStream();
                thePic.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
                ui = thePic.toString();
                Log.e("JJJJJJ", "" + ui);
                try {
                    encodedImage = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.getUri());
                        cap.setImageBitmap(bitmap);
                        vcap.setImageBitmap(bitmap);
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        ui = result.getUri().toString();

                        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
                        Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());


                        encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                        Log.e("PIC", "" + encodedImage);
                        FileOutputStream fos = null;

//                        try {
//                            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
//                            if (!root.exists()) {
//                                root.mkdirs();
//                            }
//                            File gpxfile = new File(root, "nowrap.txt");
//                            FileWriter writer = new FileWriter(gpxfile);
//                            writer.append(encodedImage);
//                            writer.flush();
//                            writer.close();
//                            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //  Log.e("PIC", "" + thePic);

//
                    //((ImageView) findViewById(R.id.img)).setImageURI(result.getUri());
                    // Toast.makeText(this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG).show();
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
                }
            }

        }

    }
    private File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        imageFilePath = image.getAbsolutePath();

        return image;
    }
    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(Stockinwardnew.this);
    }
    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,60,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

//    @Override
//    public void onBackPressed() {
//       Intent i = new Intent(getApplicationContext(),Dashboard.class);
//       startActivity(i);
//    }

    public void save()
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
            user.put("orgnId", sharedpreferences.getString("ocnew",""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc",""));
            user.put("localeId", "en_US");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("IOU_inward_rowid","0");
            user2.put("In_orgn_code",sharedpreferences.getString("ocnew",""));
            user2.put("In_ic_code",sharedpreferences.getString("ocnew",""));

            String[] d = edate.getText().toString().split("/");

            user2.put("In_inward_code",billno2);

            user2.put("In_grn_no","");
            user2.put("In_grn_date",d[2]+"-"+d[1]+"-"+d[0]);
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
                final SQLiteDatabase dbs = db.getWritableDatabase();
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
                user4.put("In_discount","0");
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
                        ct2++;
                        final SQLiteDatabase dbs = db.getWritableDatabase();
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("Header");

                            String inrid = obj2.getString("IOU_inward_rowid");
                            String inrno = obj2.getString("In_grn_no");
                            inwid = inrid;

                            Log.e("CCCC", "Hi" + inrid);

                            if (inrid.equalsIgnoreCase("0")) {
                                pdialog.dismiss();
                                dbs.execSQL("DELETE FROM inwardlist WHERE id = " + id);
                                // Toast.makeText(Stockinwardnew.this, "Unable To Insert", Toast.LENGTH_SHORT).show();
                                final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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
                                FirebaseApp.initializeApp(Stockinwardnew.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis()/1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                Log.e("Valuecheck",""+mRef.getRef());
                                mRef.child("name").setValue("SAVEAPI");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("Stockinwardnew");
                            } else {

                                db.inward(inrno+"DIV"+inrid, einwamt.getText().toString(), einwamt.getText().toString(), "", "", "", supplier.getText().toString(), "1");
                                // Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                                dbs.execSQL("UPDATE inwardlist SET flag = 1 WHERE id = " + id);
                                Log.e("CCCC", "Inward");
                                //finish();
                                pdialog.dismiss();
                                itext = inrno;
                                itext2 = inrno+"DIV"+inrid;
                                einwno.setText(""+inrno);
                                dbs.execSQL("UPDATE productlist SET flag = 1 WHERE flag = 0");
                                db.upinward(Integer.valueOf(id),inrno+"DIV"+inrid);
                                eicd.setEnabled(true);
                                esl.setEnabled(false);
                                item.setEnabled(false);
                                eicd.setEnabled(true);
                                rpayment.setEnabled(true);
                                rdetail.setEnabled(false);
                                rsummary.setEnabled(false);
                                inwnum.setText(""+inrno);
                                amount.setText(""+einwamt.getText().toString());
                                balance.setText(""+einwamt.getText().toString());
                                paid.setText("");
                                final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
//set icon
                                        .setIcon(android.R.drawable.ic_menu_save)
//set title
                                        .setTitle("Success!")
//set message
                                        .setMessage("Inward Inserted !" +
                                                "Inward No :"+inrno)
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        // dbs.execSQL("UPDATE invoicelist SET inweb = " + inrno + " WHERE id = " + id2);

                                                        finish();

//                                                        l2.setVisibility(View.GONE);
//                                                        l1.setVisibility(View.GONE);
//                                                        l3.setVisibility(View.VISIBLE);
//                                                        // header.setBackgroundResource(R.drawable.rbutton4);
//                                                        bsave.setVisibility(View.VISIBLE);
//                                                        badd.setVisibility(View.GONE);
//                                                        view.setVisibility(View.GONE);
//                                                        im1.setBackgroundResource(R.drawable.details);
//                                                        im2.setBackgroundResource(R.drawable.summary);
//                                                        im3.setBackgroundResource(R.drawable.payment2);
//                                                        v1.setVisibility(View.GONE);
//                                                        v2.setVisibility(View.GONE);
//                                                        v3.setVisibility(View.VISIBLE);
//                                                        txtheader.setText("Payment");
//                                                        fp = 1;
//
//                                                        List<String> categories = new ArrayList<String>();
//                                                        categories.add("Select Payment Mode");
//                                                        categories.add("CASH");
//                                                        categories.add("CHEQUE");
//                                                        categories.add("CARD");
//                                                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Stockinwardnew.this, android.R.layout.simple_spinner_item, categories);
//
//                                                        // Drop down layout style - list view with radio button
//                                                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                                                        // attaching data adapter to spinner
//                                                        spinner.setAdapter(dataAdapter);
//
//                                                        Cursor cursor2 = dbs.query("inward", new String[]{"id"
//                                                                }, "invoiceno" + "=?",
//                                                                new String[]{itext2}, null, null, null, null);
//
//                                                        if (cursor2.moveToFirst()) {
//                                                            do {
//                                                                pyid = cursor2.getString(0);
//                                                                Log.e("PYID",""+pyid);
//                                                            } while (cursor2.moveToNext());
//                                                        }
                                                    }
                                                }
//set negative button

                                        )
                                        .setCancelable(false)
                                        .show();

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
                        final SQLiteDatabase dbs = db.getWritableDatabase();
                        dbs.execSQL("DELETE FROM inwardlist WHERE id = " + id);
                        // Toast.makeText(getApplicationContext(),"Unable to Insert",Toast.LENGTH_SHORT).show();
                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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

                        FirebaseApp.initializeApp(Stockinwardnew.this);
                        FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        Long tsLong = System.currentTimeMillis()/1000;
                        String ts = tsLong.toString();
                        DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                        Log.e("Valuecheck",""+mRef.getRef());
                        mRef.child("name").setValue("SAVEAPI");
                        mRef.child("date").setValue(date);
                        mRef.child("Error").setValue(error.getMessage());
                        mRef.child("Activity").setValue("Stockinwardnew");
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

    public void counttotal()
    {
        final SQLiteDatabase dbs = db.getWritableDatabase();
        String selectQuery2 = "SELECT  * FROM productlist where flag = 0";
        Cursor cursor2 = dbs.rawQuery(selectQuery2, null);
        if(cursor2.getCount() == 0)
        {
            etotal.setText("");
            newnet.setText("");
            etax.setText("");
            einwamt.setText("");
            fi = 0;
        }

        double tl=0,tl2=0,tc=0,tc2=0;



        // looping through all rows and adding to list
        mStringList2.clear();
        mStringList.clear();
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

            editor.putString("itemlist", mStringList.toString());
            editor.putString("ids", mStringList2.toString());

            editor.commit();
            Log.e("List",""+sharedpreferences.getString("ids",""));


            if(supplier.getText().toString().equalsIgnoreCase(""))
            {

            }
            else
            {
                Cursor cursor2n = dbs.query("supplierlist", new String[]{"In_supplier_state_desc","In_supplier_state_code"
                        }, "In_supplier_name" + "=?",
                        new String[]{suppliername}, null, null, null, null);

                if (cursor2n.moveToFirst()) {
                    do {

                        //Toast.makeText(Stockinwardnew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

                        elocation.setText("" + cursor2n.getString(0));
                        code = cursor2n.getString(1);
                        Log.e("CODE",""+code);
                        if(sharedpreferences.getString("lo","").equalsIgnoreCase(code))
                        {
                            //  Toast.makeText(Stockinwardnew.this, "Same State", Toast.LENGTH_SHORT).show();
                            ss="0";

                        }
                        else
                        {
                            // Toast.makeText(Stockinwardnew.this, "Out State", Toast.LENGTH_SHORT).show();
                            ss="1";

                        }

                        view3.setEnabled(true);

                        String selectQueryt = "SELECT  * FROM productlist where flag = 0";
                        producttaxlist.clear();
                        Cursor cursort = dbs.rawQuery(selectQueryt, null);
                        //Toast.makeText(this, ""+cursort.getCount(), Toast.LENGTH_SHORT).show();
                        fi = 0;
                        // looping through all rows and adding to list
                        if (cursort.moveToFirst()) {
                            do {



                                String idd = cursort.getString(0);
                                String r = cursort.getString(6);
                                String it = cursort.getString(1);




                                Cursor cursort2 = dbs.query("product", new String[]{"In_cgst",
                                                "In_sgst","In_igst","In_hsn_desc"}, "In_product_name" + "=?",
                                        new String[]{it}, null, null, null, null);


                                if (cursort2.moveToFirst()) {
                                   // do {


                                        if(ss.equalsIgnoreCase("0")) {


                                            double c = Double.parseDouble(cursort2.getString(0));
                                            double s = Double.parseDouble(cursort2.getString(1));
                                            double rl = Double.parseDouble(r);

                                            double cg = (c / 100) * rl;
                                            double sg = (s / 100) * rl;
                                            double f = cg + sg;

                                            fi += f;
                                            //Log.e("CHECKV",""+fi));
                                            DecimalFormat amountFormate = new DecimalFormat("############.##");
                                            amountFormate.setMinimumFractionDigits(2);
                                            amountFormate.setMaximumFractionDigits(2);
                                            etax.setText(""+String.format("%.2f", fi));
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
                                                etax.setText(""+String.format("%.2f", fi));
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

                                   // }                                    while (cursort2.moveToNext());
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
    }

    public void check()
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
            user.put("orgnId", "PKFPO/00001");
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc",""));
            user.put("localeId", "en_US");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("IOU_inward_rowid","0");
            user2.put("In_orgn_code",sharedpreferences.getString("ocnew",""));
            user2.put("In_ic_code",sharedpreferences.getString("ocnew",""));
            user2.put("In_inward_code",inwid);

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
            user2.put("In_bill_image","");
            user2.put("In_transport_amount",trs);
            user2.put("In_others",oth);


            user.put("Header",user2);

            String[] id = ids2.split(",");
            JSONArray notebookUsers2 = new JSONArray();
            for(int i = 0 ; i< id.length;i++)
            {
                final SQLiteDatabase dbs = db.getWritableDatabase();
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

                        Cursor cursor3 = dbs.query("product", new String[]{"In_product_code",
                                        "In_productcategory_code","In_productsubcategory_code","In_uomtype_code","In_cgst","In_sgst","In_current_qty","id"}, "In_product_name" + "=?",
                                new String[]{pn}, null, null, null, null);

                        if (cursor3.moveToFirst()) {
                            do {
                                DecimalFormat format = new DecimalFormat("##########.##");
                                pco = cursor3.getString(0);
                                pc = cursor3.getString(1);
                                psc = cursor3.getString(2);
                                pu = cursor3.getString(3);
                                double c = Double.parseDouble(cursor3.getString(4));
                                double s = Double.parseDouble(cursor3.getString(5));
                                double rl = Double.parseDouble(amt);

                                double cg = (c/100)*rl;
                                double sg = (s/100)*rl;
                                double f = cg+sg;
                                pt = format.format(f).toString();
                                double x = Double.parseDouble(pt);
                                double y = Double.parseDouble(amt);

                                double z = x + y;
                                pa = format.format(z).toString();

                            }

                            while (cursor3.moveToNext());
                        }
                    }

                    while (cursor2.moveToNext());
                }

                JSONObject user4 = new JSONObject();
                user4.put("In_inwarddtl_rowid","0");
                user4.put("In_inward_code",inwid);
                user4.put("In_grn_no","0");
                user4.put("In_product_catg_code",pc);
                user4.put("In_product_subcatg_code",psc);
                user4.put("In_product_code",pco);
                user4.put("In_uomtype_code",pu);
                user4.put("In_batch_no","");
                user4.put("In_received_qty",pq);
                user4.put("In_product_amount",myr);
                user4.put("In_tax_amount",""+pt);
                user4.put("In_transport_amount",0);
                user4.put("In_discount",pd);
                user4.put("In_net_amount",""+pa);
                user4.put("In_status_code","A");
                user4.put("In_mode_flag","I");

                notebookUsers2.put(user4);
            }



            user.put("Detail",notebookUsers2);

            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
        }



    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        fetchsn();


    }

    public void fetchsn() {SQLiteDatabase dbs = db.getWritableDatabase();

    double val = 0,val2=0;

        Cursor cursor = dbs.query("sno", new String[]{"v1","v2","v3"
                }, "v3" + "=? COLLATE NOCASE",
                new String[]{tspr}, null, null, null, null);
        Log.e("COUNT", "" + cursor.getCount());

//        esl.setText("" + cursor.getCount()+"(Serial no of Item)");
        esl.setText("" + cursor.getCount());

        Cursor cursori = dbs.query("icdip", new String[]{"v1"
                }, "v1" + "=? COLLATE NOCASE",
                new String[]{tspr}, null, null, null, null);
        Log.e("COUNTI", "" + cursori.getCount());

        otherip.setText("" + cursori.getCount()+"(Other Inputs)");


        Cursor cursoro = dbs.query("icdoc", new String[]{"id","v1","v2","v3","v4","v5","v6"
                }, "v1" + "=? COLLATE NOCASE",
                new String[]{tspr}, null, null, null, null);
        Log.e("COUNTO", "" + tspr);

        // looping through all rows and adding to list
        if (cursoro.moveToFirst()) {
            do {

                if(cursoro.getString(4).equalsIgnoreCase("Base Price"))
                {
                    if(cursoro.getString(5).equalsIgnoreCase("Add"))
                    {
                       val+=Double.parseDouble(cursoro.getString(6));
                    }
                    else
                    {
                        val-=Double.parseDouble(cursoro.getString(6));
                    }
                    othercs.setText(""+String.format("%.2f",val));
                }
                else
                {

                }


                ///String[] pn = cursor.getString(1).split("-");
                //


            } while (cursoro.moveToNext());

        }

        String selectQuery2 = "SELECT  * FROM icdoc";
        Cursor cursoro2 = dbs.rawQuery(selectQuery2, null);

        if (cursoro2.moveToFirst()) {
            do {

                if(cursoro2.getString(4).equalsIgnoreCase("Base Price"))
                {

                }
                else
                {
                    if(cursoro2.getString(5).equalsIgnoreCase("Add"))
                    {
                        val2+=Double.parseDouble(cursoro2.getString(6));
                    }
                    else
                    {
                        val2-=Double.parseDouble(cursoro2.getString(6));
                    }
                    eothers.setText(""+String.format("%.2f",val2));
                }


                ///String[] pn = cursor.getString(1).split("-");
                //


            } while (cursoro2.moveToNext());

        }
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
            user.put("orgnId", sharedpreferences.getString("ocnew",""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc",""));
            user.put("localeId", "en_US");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();


            user2.put("IOU_invoice_rowid",0);
            user2.put("IOU_invoice_no",itext);
            String[] d = epdate.getText().toString().split("/");
            user2.put("In_invoice_date",d[2]+"-"+d[1]+"-"+d[0]);
            user2.put("In_invoice_amount",Double.parseDouble(eamtpaid.getText().toString()));
            user2.put("In_balance_amount",Double.parseDouble(ebalance.getText().toString()));
            user2.put("In_payment_mode","QCD_AEPS_"+spinner.getSelectedItem().toString());
            user2.put("In_ref_no",erefno.getText().toString());
            user2.put("In_payment_date",d[2]+"-"+d[1]+"-"+d[0]);
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
            user4.put("In_payment_no",itext);
            user4.put("In_balance_amount",Double.parseDouble(ebalance.getText().toString()));
            user4.put("In_payment_amount",Double.parseDouble(eamtpaid.getText().toString()));
            user4.put("In_payment_mode","QCD_AEPS_"+spinner.getSelectedItem().toString());
            user4.put("In_ref_no",erefno.getText().toString());
            user4.put("In_payment_date",d[2]+"-"+d[1]+"-"+d[0]);
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

        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.icdurl+"api/ICDStockInward/new_inward_payment",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCCP", "" + response);




                                pdialog.dismiss();
                                SQLiteDatabase dbs = db.getWritableDatabase();
                                dbs.execSQL("UPDATE paylist2 SET uflag = 1 where uflag = 0");

                                final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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


//
//                            String inrid = obj2.getString("IOU_invoice_rowid");
//                            Log.e("CCCC","Hi"+inrid);
//

//                        // Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
////                            dbs.execSQL("UPDATE invoicelist SET flag = 1 WHERE id = "+id2);
////                            dbs.execSQL("UPDATE invoicelist SET sid = "+inrid+" WHERE id = "+id2);
////                            Log.e("CCCC","Invoice");
//                        // finish();
//
//                        try {
//
//                            userd = new JSONObject();
//                            userd.put("senderid", "SMSTST");
//                            userd.put("msg_desc", "Thanks for your purchase from "+sharedpreferences.getString("rn","")+" for INR "+Float.parseFloat(payamt)+" against Invoice "+payno+" dated "+paydate+" We have received payment of INR "+Float.parseFloat(paidamt)+" and the Balance payable is "+Float.parseFloat(paybalamt));
//                            userd.put("mobile_no", "8124873354");
//
//
//                            Log.e("OUTPUT", "" + userd.toString());
//
//                        } catch (Exception e) {
//                            Log.e("OUTPUT", "" + e.getMessage());
//                        }
//
//
////        pdialog.setCanceledOnTouchOutside(false);
////        pdialog.setTitle("Uploading Please Wait.......");
////        pdialog.show();
//
//
//                        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl+"api/SendSMS/SendSMS", userd,
//                                new Response.Listener<JSONObject>() {
//                                    @Override
//                                    public void onResponse(JSONObject response) {
//                                        Log.e("CCCC", "" + response);
//
//
//
//                                    }
//
//
//                                },
//                                new Response.ErrorListener() {
//                                    @Override
//                                    public void onErrorResponse(VolleyError error) {
//                                        Log.e("CCCC", "" + error);
//
//                                        //on error storing the name to sqlite with status unsynced
//                                        // Toast.makeText(Demo.this, "Farmer "+n+" SuccessFull Added to Sync List" , Toast.LENGTH_SHORT).show();
//
//                                    }
//                                }) {
//                            @Override
//                            protected Map<String, String> getParams() throws AuthFailureError {
//                                Map<String, String> params = new HashMap<>();
//
//                                return params;
//                            }
//                        };
//                        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                                4500000,
//                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                        VolleySingleton.getInstance(Stockinwardnew.this).addToRequestQueue(stringRequest);


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
                        final AlertDialog alertDialog = new AlertDialog.Builder(Stockinwardnew.this)
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
                        FirebaseApp.initializeApp(Stockinwardnew.this);
                        FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        Long tsLong = System.currentTimeMillis()/1000;
                        String ts = tsLong.toString();
                        DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                        Log.e("Valuecheck",""+mRef.getRef());
                        mRef.child("name").setValue("PSAVEAPI");
                        mRef.child("date").setValue(date);
                        mRef.child("Error").setValue(error.getMessage().toString());
                        mRef.child("Activity").setValue("Stockinwardnew");
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

        epdate.setText(sdf.format(myCalendar.getTime()));
    }
}
