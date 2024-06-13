package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

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
import java.util.Locale;
import java.util.Map;

public class Issuetransfer extends AppCompatActivity {

    AutoCompleteTextView eicd,eitem,estock,estockto,erstock;
    Button bsave;
    String org;
    EditText euom;
    JSONObject jsonParam;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    DBHelper mydb;
    ProgressDialog pdialog;
    String pcode;
    ArrayList<String> array1 = new ArrayList<>();
    ArrayList<String> array1n = new ArrayList<>();
    ArrayList<String> array = new ArrayList<>();
    ArrayList<String> array2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));

        setContentView(R.layout.activity_issuetransfer);
        getSupportActionBar().hide();
        eicd = (AutoCompleteTextView)findViewById(R.id.eicd);
        eitem = (AutoCompleteTextView)findViewById(R.id.eitem);
        estock = (AutoCompleteTextView)findViewById(R.id.estock);
        estockto = (AutoCompleteTextView)findViewById(R.id.estockto);
        erstock = (AutoCompleteTextView)findViewById(R.id.erstock);
        euom = (EditText) findViewById(R.id.euom);

        estockto.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});

        mydb = new DBHelper(this);
        pdialog = new ProgressDialog(this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        bsave = (Button)findViewById(R.id.bsave);

        final SQLiteDatabase dbs = mydb.getWritableDatabase();
        String selectQueryicd = "SELECT  * FROM icdlist";

        Cursor cursor2ic = dbs.rawQuery(selectQueryicd, null);
        if(cursor2ic.moveToFirst())
        {
            do
            {

                if(cursor2ic.getString(1).equalsIgnoreCase(sharedpreferences.getString("orgnlvlcode","")))
                {

                }
                else
                {
                    array1.add(cursor2ic.getString(1));
                    array1n.add(cursor2ic.getString(2));
                }



            }while(cursor2ic.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (Issuetransfer.this, R.layout.spinnertext3, array1n);
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
                org = array1.get(position);




            }
        });

        if(isNetworkAvailable())
        {


                //  Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();


                dbs.execSQL("delete from product");

                pdialog.setCanceledOnTouchOutside(false);
                pdialog.setTitle("Loading.....");
                pdialog.show();
                final String url = Pojokyc.icdurl+"api/ICDMOBProduct/ICD_MOBILE_Product?org=" + sharedpreferences.getString("orgnlvlcode", "") + "&locn=up&user=admin&lang=en_US";

// prepare the Request
                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // display response
                                try {

                                    Log.e("Respo",""+response);
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



                                        mydb.insertContact(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17,n18);

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
                                                array2.add(cursor.getString(12));
                                            }

                                        } while (cursor.moveToNext());
                                    }

                                    ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(Issuetransfer.this,
                                            R.layout.spinnertext, array);
                                    eitem.setAdapter(adapterlist);
                                    eitem.setThreshold(0);
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
                VolleySingleton.getInstance(Issuetransfer.this).addToRequestQueue(getRequest);


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
                        array2.add(cursor.getString(12));
                    }

                } while (cursor.moveToNext());
            }

            ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(Issuetransfer.this,
                    R.layout.spinnertext, array);
            eitem.setAdapter(adapterlist);
            eitem.setThreshold(0);
        }

        eitem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {


                Cursor cursor2 = dbs.query("product", new String[]{"In_product_code",
                                "In_base_price","id","In_current_qty","In_ic_code","In_uomtype_desc"}, "In_product_name" + "=?",
                        new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);

                if (cursor2.moveToFirst()) {
                    do {
                        if(cursor2.getString(4).equalsIgnoreCase(sharedpreferences.getString("orgnlvlcode",""))) {
                            double d = Double.parseDouble(cursor2.getString(1));
                            estock.setText("" + cursor2.getString(3));
                            euom.setText("" + cursor2.getString(5));
                            pcode = cursor2.getString(0);
                        }

                        //Toast.makeText(Stockinwardnew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

                        // erate.setText("" + d);


                    }
                    while (cursor2.moveToNext());


                }
            }
        });

        estockto.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.length()>0)
                {
                    try {


                        double x = Double.parseDouble(s.toString());
                        double y = Double.parseDouble(estock.getText().toString());
                        if(x>y)
                        {
                            estockto.setText("");
                            erstock.setText("");
                            final AlertDialog alertDialog = new AlertDialog.Builder(Issuetransfer.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Invalid Stock!")
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
                            erstock.setText(""+(y-x));
                        }

                    }
                    catch (Exception e)
                    {

                    }
                }

                // TODO Auto-generated method stub
            }
        });

        bsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eicd.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Issuetransfer.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Empty ICD!")
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
                else  if(eitem.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Issuetransfer.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Empty Item!")
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
                else  if(estock.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Issuetransfer.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Empty Available Stock!")
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
                else  if(estockto.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Issuetransfer.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Empty Stock To ICD!")
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
                else  if(erstock.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Issuetransfer.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Empty Revised Stock!")
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
                        save();
                    }
                    else
                    {
                        mydb.insertitransfer(org,pcode,estockto.getText().toString(),"");

                        final AlertDialog alertDialog = new AlertDialog.Builder(Issuetransfer.this)
//set icon
                                .setIcon(android.R.drawable.ic_menu_save)
//set title
                                .setTitle("Success!")
//set message
                                .setMessage("Issue Transfer Completed !")
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
                        eicd.setText("");
                        eitem.setText("");
                        estock.setText("");
                        estockto.setText("");
                        erstock.setText("");
                    }
                }

            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void save()
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
            user.put("locnId", "up");
            user.put("userId", sharedpreferences.getString("uc",""));
            user.put("localeId", "en_US");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("IOU_inward_rowid","0");
            user2.put("In_orgn_code",sharedpreferences.getString("oc",""));
            user2.put("In_ic_code",org);
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
            user4.put("In_received_qty",estockto.getText().toString());
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
                                final AlertDialog alertDialog = new AlertDialog.Builder(Issuetransfer.this)
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
                                FirebaseApp.initializeApp(Issuetransfer.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis()/1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                Log.e("Valuecheck",""+mRef.getRef());
                                mRef.child("name").setValue("SAVEAPI");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("ISSUETRANSFER");

                            } else {
                                pdialog.dismiss();
                                final AlertDialog alertDialog = new AlertDialog.Builder(Issuetransfer.this)
//set icon
                                        .setIcon(android.R.drawable.ic_menu_save)
//set title
                                        .setTitle("Success!")
//set message
                                        .setMessage("Stock Transfered !" +
                                                "Inward No :"+inrno)
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        // dbs.execSQL("UPDATE invoicelist SET inweb = " + inrno + " WHERE id = " + id2);

                                                        finish();
                                                    }
                                                }
//set negative button

                                        )
                                        .show();
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
                        final AlertDialog alertDialog = new AlertDialog.Builder(Issuetransfer.this)
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

                        FirebaseApp.initializeApp(Issuetransfer.this);
                        FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        Long tsLong = System.currentTimeMillis()/1000;
                        String ts = tsLong.toString();
                        DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                        Log.e("Valuecheck",""+mRef.getRef());
                        mRef.child("name").setValue("SAVEAPI");
                        mRef.child("date").setValue(date);
                        mRef.child("Error").setValue(error.getMessage().toString());
                        mRef.child("Activity").setValue("ISSUECONFIRM");
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

}