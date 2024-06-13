package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Dashboard extends AppCompatActivity {
    JSONObject userd;
    ImageView nd,imit;
    TextView fponame,name;
    String farid, gf, encodedImage;
    ByteArrayOutputStream byteArrayOutputStream,byteArrayOutputStream2;
    String fmid,n1,n10,n11,n12,n13,adsfid;
    String icid,icgrn,pcode2,icqty,icrmk;
    String lrp,itid,itorg,tstock,pcode;
    RelativeLayout rl1,rl2,rl3,r1,r2,r3,r6,rl4,r7,r6p;
    SharedPreferences sharedpreferences;
    String inn,rqty;
    SQLiteDatabase dbs;
    int ct5n=0;
    JSONArray castcus,castms;
    String tspr,orgnnew,orgnnew2,orgnew3;
    int ctest = 0;
    int finalcount ,fcnt=0;
    public static final String MyPREFERENCES = "MyPrefs" ;
    DBHelper mydb;
    String iid;
    String uflag;
    String billno,billno2;
    String paybalamt,refno,paidamt,paymode,payindate,cinv;
    ProgressDialog pdialog;
    String cqno;
    String myr,myr2,mcode,phn;
    String lc2;
    String lc;
    String inid;
    int ct4,ct5,ct6;
    LinearLayout linward;

    ProgressBar pb;
    int ct=0,ct2=0,ctt=0,ct22=0,c3=0,ct3=0;
    JSONObject jsonParam;
    String at,atno,aqc,aqcd,aqsc,aqsc2,aqpc,aqpd,aqu,aqi,aqd,aqpco,aqi2,aty,aqid,inwid;
    String payid,paydate,payamt;
    String sname,slcn,sst,encodedimage="",ids,farco,ids2,tax,oth,trs,net,pr,pd,pt,pa,pq,pn,pc,psc,pco,pu,amt,id,bp,balamt;
    String sname2,slcn2,sst2,encodedimage2="",ids22,ids222,tax2,oth2,trs2,net2,pr2,pd2,pt2,pa2,pq2,pn2,pc2,psc2,pco2,pu2,amt2,id2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        mydb = new DBHelper(this);
        pdialog = new ProgressDialog(this);
        linward = (LinearLayout)findViewById(R.id.linward);
        rl1 = (RelativeLayout)findViewById(R.id.rl1);
        rl2 = (RelativeLayout)findViewById(R.id.rl2);
        rl3 = (RelativeLayout)findViewById(R.id.rl3);
        rl4 = (RelativeLayout)findViewById(R.id.rl4);
        pb = (ProgressBar)findViewById(R.id.pb);
        fponame = (TextView)findViewById(R.id.fponame);
        name = (TextView)findViewById(R.id.name);

        imit = (ImageView)findViewById(R.id.imit) ;


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        name.setText("Welcome "+sharedpreferences.getString("un",""));
        fponame.setText(sharedpreferences.getString("rn",""));
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c);


        String channel = (sharedpreferences.getString("date", ""));
        if(channel.equalsIgnoreCase(formattedDate))
        {

        }
        else
        {
            //   Toast.makeText(this, "You Last Sync date was in "+channel+" so please sync again to process further", Toast.LENGTH_SHORT).show();
        }

        // Toast.makeText(this, ""+channel, Toast.LENGTH_SHORT).show();



        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();

        rl1.clearAnimation();
        rl1.startAnimation(anim);
        rl2.clearAnimation();
        rl2.startAnimation(anim);
        rl3.clearAnimation();
        rl3.startAnimation(anim);
        nd = (ImageView) findViewById(R.id.nd);
        Log.e("PPP",sharedpreferences.getString("oc",""));

       if(sharedpreferences.getString("cf","").equalsIgnoreCase("Individual"))
       {
           rl4.setVisibility(View.GONE);
           rl3.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent i = new Intent(getApplicationContext(),StockAdjustment.class);
                   startActivity(i);
                   overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
               }
           });
       }
       else
       {
           if(sharedpreferences.getString("rc","").equalsIgnoreCase("ROLE_PRIMARY_IC"))
           {
               rl1.setVisibility(View.VISIBLE);
               rl4.setVisibility(View.VISIBLE);
               rl3.setVisibility(View.VISIBLE);
               rl2.setVisibility(View.VISIBLE);
               TextView ttimt = (TextView)findViewById(R.id.ttimt);
               ttimt.setText("Issue/Transfer to Secondary Input Center");
               rl4.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent i = new Intent(getApplicationContext(),Issuetransfer.class);
                       startActivity(i);
                       overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                   }
               });
               rl3.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent i = new Intent(getApplicationContext(),StockAdjustment2.class);
                       startActivity(i);
                       overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                   }
               });
           }
           else
           {
               rl1.setVisibility(View.GONE);
               linward.setVisibility(View.GONE);

               rl4.setVisibility(View.VISIBLE);
               rl3.setVisibility(View.VISIBLE);
               rl2.setVisibility(View.VISIBLE);
               TextView ttimt = (TextView)findViewById(R.id.ttimt);
               ttimt.setText("Issue/Transfer Confirmation");
               rl4.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent i = new Intent(getApplicationContext(),Issueconfirm.class);
                       startActivity(i);
                       overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                   }
               });
               rl3.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent i = new Intent(getApplicationContext(),StockAdjustment2.class);
                       startActivity(i);
                       overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                   }
               });
           }
       }

        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SQLiteDatabase dbs = mydb.getWritableDatabase();
                dbs.execSQL("delete from productlist");
                dbs.execSQL("delete from icdip");
                dbs.execSQL("delete from icdoc");
                Intent i = new Intent(getApplicationContext(),Stockinwardnew.class);
                startActivity(i);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("bi", "0");
                editor.putString("rfs", "1");
                editor.commit();
                final SQLiteDatabase dbs = mydb.getWritableDatabase();
                dbs.execSQL("delete from productlist2");
                Intent i = new Intent(getApplicationContext(), Bookinvoicenew.class);
                startActivity(i);

                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });





        nd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(Dashboard.this);
                dialog.setContentView(R.layout.navimenu);
                dialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
                dialog.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                // set the custom dialog components - text, image and button

                r1 = (RelativeLayout)dialog.findViewById(R.id.r1);
                r2 = (RelativeLayout)dialog.findViewById(R.id.r2);
                r3 = (RelativeLayout)dialog.findViewById(R.id.r3);
                r6 = (RelativeLayout)dialog.findViewById(R.id.r6);
                r6p = (RelativeLayout)dialog.findViewById(R.id.r6p);
                r7 = (RelativeLayout)dialog.findViewById(R.id.r7);
                RelativeLayout rlo = (RelativeLayout)dialog.findViewById(R.id.rlo);
                RelativeLayout rtrs = (RelativeLayout)dialog.findViewById(R.id.rtrs);
                final RelativeLayout rmast  = (RelativeLayout)dialog.findViewById(R.id.rmast);
                TextView tuser = (TextView)dialog.findViewById(R.id.tuser);
                TextView fname = (TextView)dialog.findViewById(R.id.fname);
                TextView tissue = (TextView)dialog.findViewById(R.id.tissue);

                tuser.setText("Welcome "+sharedpreferences.getString("un",""));
                fname.setText(sharedpreferences.getString("rn",""));
                if(sharedpreferences.getString("cf","").equalsIgnoreCase("Individual"))
                {
                    r7.setVisibility(View.GONE);
                    r3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getApplicationContext(),StockAdjustment.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                        }
                    });
                }
                else {
                    if (sharedpreferences.getString("rc", "").equalsIgnoreCase("ROLE_PRIMARY_IC")) {
                        r1.setVisibility(View.VISIBLE);
                        r2.setVisibility(View.VISIBLE);
                        r3.setVisibility(View.VISIBLE);
                        r6.setVisibility(View.VISIBLE);
                        r7.setVisibility(View.VISIBLE);
                        tissue.setText("Issue/Transfer to Secondary Input Center");
                        r7.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(getApplicationContext(), Issuetransfer.class);
                                startActivity(i);
                                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                            }
                        });
                        r3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getApplicationContext(),StockAdjustment2.class);
                                startActivity(i);
                                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                            }
                        });
                    } else {
                        r1.setVisibility(View.GONE);
                        r2.setVisibility(View.VISIBLE);
                        r3.setVisibility(View.VISIBLE);
                        r6.setVisibility(View.VISIBLE);
                        r7.setVisibility(View.VISIBLE);
                        tissue.setText("Issue/Transfer Confirmation");
                        r7.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(getApplicationContext(), Issueconfirm.class);
                                startActivity(i);
                                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                            }
                        });
                        r3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getApplicationContext(),StockAdjustment2.class);
                                startActivity(i);
                                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                            }
                        });
                    }
                }


                rtrs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                            final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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



                rmast.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        pdialog.setCanceledOnTouchOutside(false);
                        pdialog.setTitle("Master Syncing.....");
                        pdialog.show();
                        rmast.setEnabled(false);
                        SQLiteDatabase dbs = mydb.getWritableDatabase();
                        dbs.execSQL("delete from product");
                        dbs.execSQL("delete from supplierlist");
                       // dbs.execSQL("delete from customerlist");
                        dbs.execSQL("delete from lrplist");
                        dbs.execSQL("delete from inw");
                        dbs.execSQL("delete from oci");
                       // dbs.execSQL("delete from masterl");


                        // pb.setVisibility(View.VISIBLE);



                       // dbs.execSQL("delete from masterl");
                        try {


                            DecimalFormat amountFormate = new DecimalFormat("############.##");
                            amountFormate.setMinimumFractionDigits(2);
                            amountFormate.setMaximumFractionDigits(2);

                            Date cc = Calendar.getInstance().getTime();
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                            //userd = new JSONObject();

                             userd = new JSONObject();
                            userd.put("orgnId", "Flexi");
                            userd.put("locnId", sharedpreferences.getString("dateforsyncicd",""));
                            userd.put("userId", "admin");
                            userd.put("localeId", "en_US");
                            userd.put("screen_Id", "FARMER");
                            userd.put("instance", Pojokyc.instance);

                            Log.e("OUTPUT", "" + userd.toString());

                        } catch (Exception e) {
                            Log.e("OUTPUT", "" + e.getMessage());
                        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

                        //169.38.77.190:101/api/Mobile_FDR/Farmermaster
                        //15.206.21.248:27/Farmermaster
                        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/Mobile_FDR/Farmermaster", userd,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Log.e("CCCC2", "" + response);
                                        try {
                                            JSONObject obj = response.getJSONObject("context");
                                             castms = obj.getJSONArray("detail");




                                                final String url3 = Pojokyc.icdurl+"api/ICDMOBList/Customers?org="+sharedpreferences.getString("orgnlvlcode","")+"&locn="+Pojokyc.instance+"&user="+sharedpreferences.getString("dateforsyncicd","")+"&lang=en_US";

// prepare the Request
                                                Log.e("URL",""+url3);
                                                JsonObjectRequest getRequest3 = new JsonObjectRequest(Request.Method.GET, url3, null,
                                                        new Response.Listener<JSONObject>()
                                                        {
                                                            @Override
                                                            public void onResponse(JSONObject response) {
                                                                // display response
                                                                Log.e("Response", response.toString());
                                                                try {
                                                                    JSONObject obj = response.getJSONObject("context");
                                                                    castcus = obj.getJSONArray("CustomerList");
                                                                    new SomeTaskcus().execute();

                                                                    // rmast.setEnabled(true);

                                                                } catch (JSONException e) {
                                                                    FirebaseApp.initializeApp(Dashboard.this);
                                                                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                                                    String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                                                    Long tsLong = System.currentTimeMillis()/1000;
                                                                    String ts = tsLong.toString();
                                                                    DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                                                    Log.e("Valuecheck",""+mRef.getRef());
                                                                    mRef.child("name").setValue("FARMERLIST");
                                                                    mRef.child("date").setValue(date);
                                                                    mRef.child("Error").setValue(response.toString());
                                                                    mRef.child("Activity").setValue("MASTER");
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
                                                getRequest3.setRetryPolicy(new DefaultRetryPolicy(
                                                        90000,
                                                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                                                VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(getRequest3);



                                            // pdialog.dismiss();

                                           // pdialog.dismiss();
                                            rmast.setEnabled(true);

                                        } catch (JSONException e) {
                                            FirebaseApp.initializeApp(Dashboard.this);
                                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                            Long tsLong = System.currentTimeMillis()/1000;
                                            String ts = tsLong.toString();
                                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                            Log.e("Valuecheck",""+mRef.getRef());
                                            mRef.child("name").setValue("MASTERAPI");
                                            mRef.child("date").setValue(date);
                                            mRef.child("Error").setValue(response.toString());
                                            mRef.child("Activity").setValue("MASTER");
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
                        VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(stringRequest);
                        if(sharedpreferences.getString("rc","").equalsIgnoreCase("ROLE_SECONDARY_IC"))
                        {
                            final String url = Pojokyc.icdurl+"api/ICDMOBInward/IssueList?org="+sharedpreferences.getString("orgnlvlcode","")+"&locn="+Pojokyc.instance+"&user=admin&lang=en_US";
                            Log.e("URL",""+url);

// prepare the Request
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
                                                FirebaseApp.initializeApp(Dashboard.this);
                                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                                Long tsLong = System.currentTimeMillis()/1000;
                                                String ts = tsLong.toString();
                                                DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                                Log.e("Valuecheck",""+mRef.getRef());
                                                mRef.child("name").setValue("ISSUELIST");
                                                mRef.child("date").setValue(date);
                                                mRef.child("Error").setValue(response.toString());
                                                mRef.child("Activity").setValue("MASTER");
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
                            VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(getRequest);
                        }
                        RequestQueue queue = Volley.newRequestQueue(Dashboard.this);
                        final String url = Pojokyc.icdurl+"api/ICDMOBProduct/ICD_MOBILE_Product?org="+sharedpreferences.getString("orgnlvlcode","")+"&locn="+Pojokyc.instance+"&user=admin&lang=en_US";
                        Log.e("URL",""+url);

// prepare the Request
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
                                            FirebaseApp.initializeApp(Dashboard.this);
                                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                            Long tsLong = System.currentTimeMillis()/1000;
                                            String ts = tsLong.toString();
                                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                            Log.e("Valuecheck",""+mRef.getRef());
                                            mRef.child("name").setValue("PRODUCTLIST");
                                            mRef.child("date").setValue(date);
                                            mRef.child("Error").setValue(response.toString());
                                            mRef.child("Activity").setValue("MASTER");
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
                        VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(getRequest);







                        final String url2 = Pojokyc.icdurl+"api/ICDMOBList/ICD_Supplier_list?org="+sharedpreferences.getString("orgnlvlcode","")+"&locn="+Pojokyc.instance+"&user=admin&lang=en_US&filterby_option=all&filterby_code=.&filterby_fromvalue=.&filterby_tovalue=.";

// prepare the Request
                                                Log.e("URL",""+url2);

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
                                            FirebaseApp.initializeApp(Dashboard.this);
                                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                            Long tsLong = System.currentTimeMillis()/1000;
                                            String ts = tsLong.toString();
                                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                            Log.e("Valuecheck",""+mRef.getRef());
                                            mRef.child("name").setValue("SUPPLIERLIST");
                                            mRef.child("date").setValue(date);
                                            mRef.child("Error").setValue(response.toString());
                                            mRef.child("Activity").setValue("MASTER");
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
                        getRequest2.setRetryPolicy(new DefaultRetryPolicy(
                                50000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(getRequest2);


                        final String url2l = Pojokyc.icdurl+"api/ICDMOBStockadj/StockadjLRP?org=flexi&locn="+Pojokyc.instance+"&user=admin&lang=en_US&In_orgn_code="+sharedpreferences.getString("orgnlvlcode","");

// prepare the Request
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
                                            FirebaseApp.initializeApp(Dashboard.this);
                                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                            Long tsLong = System.currentTimeMillis()/1000;
                                            String ts = tsLong.toString();
                                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                            Log.e("Valuecheck",""+mRef.getRef());
                                            mRef.child("name").setValue("LRPLIST");
                                            mRef.child("date").setValue(date);
                                            mRef.child("Error").setValue(response.toString());
                                            mRef.child("Activity").setValue("MASTER");
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
                        getRequest2.setRetryPolicy(new DefaultRetryPolicy(
                                50000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(getRequest2l);






                        final String url4 = Pojokyc.icdurl+"api/ICDMOBList/allmasterlist?org=QCD_UN_STATE&locn="+Pojokyc.instance+"&user=admin&lang=en_US&parent_code=QCD_UN_STATE";

// prepare the Request
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
                                            FirebaseApp.initializeApp(Dashboard.this);
                                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                            Long tsLong = System.currentTimeMillis()/1000;
                                            String ts = tsLong.toString();
                                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                            Log.e("Valuecheck",""+mRef.getRef());
                                            mRef.child("name").setValue("ALLMASTERAPI");
                                            mRef.child("date").setValue(date);
                                            mRef.child("Error").setValue(response.toString());
                                            mRef.child("Activity").setValue("MASTER");
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
                        VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(getRequest4);


                        final String url4s = Pojokyc.icdurl+"api/ICDMOBList/allmasterlist?org=QCD_UN_STATE&locn="+Pojokyc.instance+"&user=admin&lang=en_US&parent_code=QCD_ICD_OTHERCOST";

// prepare the Request
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
                                            FirebaseApp.initializeApp(Dashboard.this);
                                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                            Long tsLong = System.currentTimeMillis()/1000;
                                            String ts = tsLong.toString();
                                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                            Log.e("Valuecheck",""+mRef.getRef());
                                            mRef.child("name").setValue("OTHERCOST");
                                            mRef.child("date").setValue(date);
                                            mRef.child("Error").setValue(response.toString());
                                            mRef.child("Activity").setValue("MASTER");
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
                        getRequest4s.setRetryPolicy(new DefaultRetryPolicy(
                                90000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(getRequest4s);
                        String oc1[] = sharedpreferences.getString("oc","").split("/");
                        final String url5 = Pojokyc.icdurl+"api/ICDMOBList/geticdtransaction?org="+oc1[0]+"&locn="+Pojokyc.instance+"&user=dsfdsf&lang=en_US";
                        Log.e("URL",""+url5);

// prepare the Request
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
                                            FirebaseApp.initializeApp(Dashboard.this);
                                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                            Long tsLong = System.currentTimeMillis()/1000;
                                            String ts = tsLong.toString();
                                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                            Log.e("Valuecheck",""+mRef.getRef());
                                            mRef.child("name").setValue("LASTNUM");
                                            mRef.child("date").setValue(date);
                                            mRef.child("Error").setValue(response.toString());
                                            mRef.child("Activity").setValue("MASTER");
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
                        VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(getRequest5);

                    }
                });
                r1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final SQLiteDatabase dbs = mydb.getWritableDatabase();
                        dbs.execSQL("delete from productlist");
                        dbs.execSQL("delete from icdip");
                        dbs.execSQL("delete from icdoc");
                        Intent i = new Intent(getApplicationContext(),Stockinwardnew.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    }
                });
                r2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                });


                r6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(),Paymentlist.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    }
                });
                r6p.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(),Paymentlist2.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    }
                });
                rlo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(isNetworkAvailable()) {
                            RequestQueue queue = Volley.newRequestQueue(Dashboard.this);
                            final String url = Pojokyc.icdurl+"api/ICDMOBLogin/user_validation?org=logout&locn="+Pojokyc.instance+"&user=admin&lang=en_US&In_user_code="+sharedpreferences.getString("username", "") + "In_user_pwd=0";

// prepare the Request

                            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            // display response
                                            Log.d("Response", response.toString());
                                            SQLiteDatabase dbs = mydb.getWritableDatabase();
                                            dbs.execSQL("delete from product");
                                            //dbs.execSQL("delete from supplierlist");
                                            //dbs.execSQL("delete from customerlist");
                                            dbs.execSQL("delete from invoicelist");
                                            dbs.execSQL("delete from inwardlist");
                                            //dbs.execSQL("delete from customerlistnm");
                                            dbs.execSQL("delete from productlist");
                                            dbs.execSQL("delete from productlist2");
                                            dbs.execSQL("delete from astock");
                                            dbs.execSQL("delete from astock2");
                                            dbs.execSQL("delete from paylist");
                                            dbs.execSQL("delete from invoice");
                                           // dbs.execSQL("delete from state");
                                            //dbs.execSQL("delete from tab");
                                            //dbs.execSQL("delete from tablist");
//                                            SharedPreferences.Editor editor = sharedpreferences.edit();
//                                            editor.clear();
//                                            editor.apply();
                                            Intent i = new Intent(getApplicationContext(), Loginicd.class);
                                            startActivity(i);
                                            finish();

                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    }
                            );

// add it to the RequestQueue
                            queue.add(getRequest);





                        }
                        else
                        {
                            Toast.makeText(Dashboard.this, "No Network Available", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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


    }


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
            user.put("orgnId", orgnnew);
            user.put("locnId", Pojokyc.instance);
            user.put("userId", orgnnew);
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
            user2.put("In_transport_amount",trs);
            user2.put("In_others",oth);
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
                                    double c = Math.round(Float.parseFloat(cursor3.getString(4)));
                                    double s = Math.round(Float.parseFloat(cursor3.getString(5)));
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
                                    double c = Math.round(Float.parseFloat(cursor3.getString(8)));

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
                user4.put("In_discount",pd);
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




                notebookUsers2.put(user4);
            }



            user.put("Detail",notebookUsers2);

            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
        }





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
                                final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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
                                FirebaseApp.initializeApp(Dashboard.this);
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
                                    final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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


                                            ct2++;
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }







                                }
//                                final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
////set icon
//                                        .setIcon(android.R.drawable.ic_menu_save)
////set title
//                                        .setTitle("Success!")
////set message
//                                        .setMessage("Inward Inserted !" +
//                                                "Inward No :"+inrno)
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
                        final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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
            user.put("orgnId", orgnnew2);
            user.put("locnId", Pojokyc.instance);
            user.put("userId", orgnnew2);
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
                                    double c = Math.round(Float.parseFloat(cursor3.getString(4)));
                                    double s = Math.round(Float.parseFloat(cursor3.getString(5)));
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
                                    double c = Math.round(Float.parseFloat(cursor3.getString(9)));
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
                                //Toast.makeText(Dashboard.this, "Unable To Insert", Toast.LENGTH_SHORT).show();
                                final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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



                                dbs.execSQL("UPDATE invoicelist SET flag = 1 WHERE id = " + id2);
                                dbs.execSQL("UPDATE invoicelist SET sid = " + inrid + " WHERE id = " + id2);
                                mydb.upinvoice(Integer.valueOf(id2),inrno);
                                String selectQuery2n = "SELECT  * FROM invoicelist where flag = 0";
                                Cursor cursor = dbs.rawQuery(selectQuery2n, null);
                                if (cursor.moveToFirst()) {

                                    id2 = cursor.getString(0);
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
                                    // Toast.makeText(Dashboard.this, ""+ids22, Toast.LENGTH_SHORT).show();
                                    Cursor cursori = dbs.query("invoice", new String[]{"balance",
                                            }, "invoiceno" + "=?",
                                            new String[]{cursor.getString(1)}, null, null, null, null);
                                    if (cursori.moveToFirst()) {
                                        do {
                                            // idn = cursor2.getString(2);
                                            //Toast.makeText(Dashboard.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

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
                                    //Toast.makeText(Dashboard.this, ""+ids, Toast.LENGTH_SHORT).show();

                                    save2();




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

                                    final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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
                        final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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
                                final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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
                                    save3();

                                }
                                // eadj.setText(""+inrno);
                                // finish();
                                fcnt++;

                                if(fcnt == finalcount)
                                {
                                    pdialog.dismiss();

                                    final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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
                        final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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




            user2.put("IOU_invoice_rowid",payid);
            user2.put("IOU_invoice_no",cinv);
            user2.put("In_invoice_date",paydate);
            user2.put("In_invoice_amount",payamt);
            user2.put("In_balance_amount",paybalamt);
            user2.put("In_payment_mode","QCD_AEPS_"+paymode);
            user2.put("In_ref_no",refno);
            user2.put("In_payment_date",payindate);
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
            user4.put("In_payment_mode","QCD_AEPS_"+paymode);
            user4.put("In_ref_no",refno);
            user4.put("In_payment_date",payindate);
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





        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.icdurl+"api/KANCHIICD_MOBILEAPP/mobnewsavePaymentCollection",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        fcnt++;
                        final SQLiteDatabase dbs = mydb.getWritableDatabase();
                        dbs.execSQL("UPDATE paylist SET uflag = 1 WHERE id = " + iid);




                        String selectQuery4 = "SELECT  * FROM invoice where flag = 0";
                        String selectQuery5 = "SELECT  * FROM paylist where uflag = 0";
                        Cursor cursorp2 = dbs.rawQuery(selectQuery5, null);
                        Cursor cursorp = dbs.rawQuery(selectQuery4, null);
                        // Toast.makeText(Dashboard.this, ""+cursor2.getCount(), Toast.LENGTH_SHORT).show();
                        if(cursorp2.getCount()!=0) {

                            if (cursorp2.moveToFirst()) {
                                String cinv2 = cursorp2.getString(1);
                                inid = cursorp2.getString(0);
                                if (cursorp.moveToFirst()) {


                                    // iid = cursorp.getString(0);
                                    // String cinv2 = cursorp.getString(1);


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

                                    if (cursorfc2.getCount() != 0) {

                                        if (cursorfc2.moveToFirst()) {


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


                                }
                            }
                        }

//                        try {
//                            JSONObject obj = response.getJSONObject("context");
//                            JSONObject obj2 = obj.getJSONObject("Header");
//
//                            String inrid = obj2.getString("IOU_invoice_rowid");
//                            Log.e("CCCC","Hi"+inrid);
//


                        if(fcnt == finalcount) {
                            pdialog.dismiss();

                            final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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
                        final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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

    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void ms()
    {
        // dialog.dismiss();
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Master Syncing.....");
        pdialog.show();
        //rmast.setEnabled(false);
        SQLiteDatabase dbs = mydb.getWritableDatabase();
        dbs.execSQL("delete from product");
        dbs.execSQL("delete from supplierlist");
       // dbs.execSQL("delete from customerlist");
        dbs.execSQL("delete from lrplist");
        //dbs.execSQL("delete from masterl");

        // pb.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(Dashboard.this);
        final String url = Pojokyc.icdurl+"api/ICDMOBProduct/ICD_MOBILE_Product?org="+sharedpreferences.getString("orgnlvlcode","")+"&locn="+Pojokyc.instance+"&user=admin&lang=en_US";

// prepare the Request
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
        VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(getRequest);







        final String url2 = Pojokyc.icdurl+"api/ICDMOBList/ICD_Supplier_list?org="+sharedpreferences.getString("orgnlvlcode","")+"&locn="+Pojokyc.instance+"&user=admin&lang=en_US&filterby_option=all&filterby_code=.&filterby_fromvalue=.&filterby_tovalue=.";

// prepare the Request
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
        getRequest2.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(getRequest2);

        final String url2l = Pojokyc.icdurl+"api/ICDMOBStockadj/StockadjLRP?org=flexi&locn="+Pojokyc.instance+"&user=admin&lang=en_US&In_orgn_code="+sharedpreferences.getString("orgnlvlcode","");

// prepare the Request
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
        getRequest2.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(getRequest2l);



        try {


            DecimalFormat amountFormate = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", "Flexi");
            userd.put("locnId", sharedpreferences.getString("dateforsyncicd",""));
            userd.put("userId", "admin");
            userd.put("localeId", "en_US");
            userd.put("screen_Id", "FARMER");
            userd.put("instance", Pojokyc.instance);

            Log.e("OUTPUT", "" + userd.toString());

        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

        //169.38.77.190:101/api/Mobile_FDR/Farmermaster
        //15.206.21.248:27/Farmermaster
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/Mobile_FDR/Farmermaster", userd,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC2", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            castms = obj.getJSONArray("detail");




                            final String url3 = Pojokyc.icdurl+"api/ICDMOBList/Customers?org="+sharedpreferences.getString("orgnlvlcode","")+"&locn="+Pojokyc.instance+"&user="+sharedpreferences.getString("dateforsyncicd","")+"&lang=en_US";

// prepare the Request
                            Log.e("URL",""+url3);
                            JsonObjectRequest getRequest3 = new JsonObjectRequest(Request.Method.GET, url3, null,
                                    new Response.Listener<JSONObject>()
                                    {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            // display response
                                            Log.e("Response", response.toString());
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                castcus = obj.getJSONArray("CustomerList");
                                                new SomeTaskcus().execute();

                                                // rmast.setEnabled(true);

                                            } catch (JSONException e) {
                                                FirebaseApp.initializeApp(Dashboard.this);
                                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                                Long tsLong = System.currentTimeMillis()/1000;
                                                String ts = tsLong.toString();
                                                DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                                Log.e("Valuecheck",""+mRef.getRef());
                                                mRef.child("name").setValue("FARMERLIST");
                                                mRef.child("date").setValue(date);
                                                mRef.child("Error").setValue(response.toString());
                                                mRef.child("Activity").setValue("MASTER");
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
                            getRequest3.setRetryPolicy(new DefaultRetryPolicy(
                                    90000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(getRequest3);



                            // pdialog.dismiss();

                            // pdialog.dismiss();


                        } catch (JSONException e) {
                            FirebaseApp.initializeApp(Dashboard.this);
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Long tsLong = System.currentTimeMillis()/1000;
                            String ts = tsLong.toString();
                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                            Log.e("Valuecheck",""+mRef.getRef());
                            mRef.child("name").setValue("MASTERAPI");
                            mRef.child("date").setValue(date);
                            mRef.child("Error").setValue(response.toString());
                            mRef.child("Activity").setValue("MASTER");
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
        VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(stringRequest);



        final String url4 = Pojokyc.icdurl+"api/ICDMOBList/allmasterlist?org=QCD_UN_STATE&locn="+Pojokyc.instance+"&user=admin&lang=en_US&parent_code=QCD_UN_STATE";

// prepare the Request
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
        VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(getRequest4);
        String oc1[] = sharedpreferences.getString("oc","").split("/");
        final String url5 = Pojokyc.icdurl+"api/ICDMOBList/geticdtransaction?org="+oc1[0]+"&locn=sdfsdf&user=dsfdsf&lang=en_US";

// prepare the Request
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
        VolleySingleton.getInstance(Dashboard.this).addToRequestQueue(getRequest5);
    }

//    public  void  psave()
//    {
//        final SQLiteDatabase dbs = mydb.getWritableDatabase();
//
//        String selectQuery4 = "SELECT  * FROM invoice where flag = 0";
//        String selectQuery5 = "SELECT  * FROM paylist where uflag = 0";
//
//
//        Cursor cursorp = dbs.rawQuery(selectQuery4, null);
//        Cursor cursorp2 = dbs.rawQuery(selectQuery5, null);
//        if(cursorp2.getCount()!=0) {
//
//            if (cursorp2.moveToFirst()) {
//                inid = cursorp2.getString(0);;
//                String cinv2 = cursorp2.getString(1);
//                if (cursorp.moveToFirst()) {
//
//
//
//
//
//                    Cursor cursorfc22 = dbs.query("invoicelist", new String[]{"date", "sid", "invoiceamount", "inweb"
//                            }, "invoiceno" + "=?",
//                            new String[]{cinv2}, null, null, null, null);
//
//                    if (cursorfc22.moveToFirst()) {
//                        do {
//
//                            payid = cursorfc22.getString(1);
//                            paydate = cursorfc22.getString(0);
//                            payamt = cursorfc22.getString(2);
//                            cinv = cursorfc22.getString(3);
//                        }
//
//                        while (cursorfc22.moveToNext());
//
//
//                    }
//
//
//                    Cursor cursorfc2 = dbs.query("paylist", new String[]{"amountpaid", "refno", "date", "paymode", "bal", "id", "uflag","ckno"
//                            }, "id" + "=?",
//                            new String[]{inid}, null, null, null, null);
//                    // Toast.makeText(Dashboard.this, ""+inid, Toast.LENGTH_SHORT).show();
//                    if (cursorfc2.getCount() != 0) {
//
//                        if (cursorfc2.moveToFirst()) {
//
//                            //Toast.makeText(Dashboard.this, "Hi" + cursorfc2.getString(6), Toast.LENGTH_SHORT).show();
//
//
//                            uflag = cursorfc2.getString(6);
//
//
//                            iid = cursorfc2.getString(5);
//                            refno = cursorfc2.getString(1);
//                            paidamt = cursorfc2.getString(0);
//                            paymode = cursorfc2.getString(3);
//                            payindate = cursorfc2.getString(2);
//                            paybalamt = cursorfc2.getString(4);
//                            cqno = cursorfc2.getString(7);
//
//
//                            if (uflag.equalsIgnoreCase("0")) {
//
//                                savep();
//                            }
//                        }
//                    }
//
//
//                }
//            }
//
//        }
//    }

    public void save7()
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
                                final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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



                                        save7();

                                    }

                                }



                                if(fcnt == finalcount)
                                {
                                    pdialog.dismiss();

                                    final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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
                        final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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

    public void save8()
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
                                final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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



                                        save8();

                                    }

                                }


                                if(fcnt == finalcount)
                                {
                                    pdialog.dismiss();

                                    final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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
                        final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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

    public void savetrs()
    {
        String selectQuery = "SELECT  * FROM inwardlist where flag = 0";
        String selectQuery2 = "SELECT  * FROM invoicelist where flag = 0";
        String selectQuery3 = "SELECT  * FROM astock2";
        String selectQuery6 = "SELECT  * FROM itransfer";
        String selectQuery4 = "SELECT  * FROM invoice where flag = 0";
        String selectQuery5 = "SELECT  * FROM paylist where uflag = 0";
        String selectQuery7 = "SELECT  * FROM iconfirm  WHERE v5 = 0";
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

        if(isNetworkAvailable())
        {
            if (finalcount != 0) {
                pdialog.setCanceledOnTouchOutside(false);
                pdialog.setTitle("Uploading Loading.....");
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
                        // Toast.makeText(Dashboard.this, ""+ids22, Toast.LENGTH_SHORT).show();
                        Cursor cursori = dbs.query("invoice", new String[]{"balance",
                                }, "invoiceno" + "=?",
                                new String[]{cursor.getString(1)}, null, null, null, null);
                        if (cursori.moveToFirst()) {
                            do {
                                // idn = cursor2.getString(2);
                                //Toast.makeText(Dashboard.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

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
                        //Toast.makeText(Dashboard.this, ""+ids, Toast.LENGTH_SHORT).show();

                        save2();




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
                        save3();

                    }

                }

                if (cursor5.getCount() == 0) {

                } else {


                    if (cursor5.moveToFirst()) {


                        itid = cursor5.getString(0);
                        pcode = cursor5.getString(2);
                        itorg = cursor5.getString(1);
                        tstock = cursor5.getString(3);



                        save7();

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



                        save8();

                    }

                }

                // Toast.makeText(Dashboard.this, ""+cursor2.getCount(), Toast.LENGTH_SHORT).show();
                if(cursorp2.getCount()!=0) {

                    if (cursorp2.moveToFirst()) {
                        inid = cursorp2.getString(0);;
                        String cinv2 = cursorp2.getString(1);
                        if (cursorp.moveToFirst()) {





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
                            // Toast.makeText(Dashboard.this, ""+inid, Toast.LENGTH_SHORT).show();
                            if (cursorfc2.getCount() != 0) {

                                if (cursorfc2.moveToFirst()) {

                                    //Toast.makeText(Dashboard.this, "Hi" + cursorfc2.getString(6), Toast.LENGTH_SHORT).show();


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


                        }
                    }

                }
            }
            else
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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
            final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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
    public void offlinesave() {
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

                user2.put("in_fpo_orgncode", sharedpreferences.getString("oc", ""));
                user2.put("in_created_by", sharedpreferences.getString("un", "").toUpperCase() + "" + sharedpreferences.getString("phn", ""));
                user2.put("in_modified_by", sharedpreferences.getString("un", "").toUpperCase() + "" + sharedpreferences.getString("phn", ""));
                user.put("Header", user2);

                Log.e("OUTPUT", "" + jsonParam.toString());
            }
        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());

        }
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


                                final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(Dashboard.this)
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
                                        final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
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


    class SomeTaskcus extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // super.onPreExecute();

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SQLiteDatabase dbs = mydb.getWritableDatabase();
            try {

                for (int i=0; i<castcus.length(); i++) {



                    JSONObject actor = castcus.getJSONObject(i);
                    String n1 = actor.getString("farmer_code");
                    String n2 = actor.getString("farmer_name");
                    String n3 = actor.getString("village_name");
                    String n4 = actor.getString("farmer_address");
                    String n5 = actor.getString("state_name");
                    String n6 = actor.getString("farmer_village_code");
                    String n7 = actor.getString("farmer_state_code");
                    String n8 =  actor.getString("phone_no");
                    String n9 =  actor.getString("ic_code");
                    String n10 =  actor.getString("sur_name");
                    String n11 =  actor.getString("fhw_name");

                    Cursor cr1 = dbs.rawQuery("select * from customerlist where farmer_code='"+n1+"'",null);
                    if(sharedpreferences.getString("dateforsyncicd","").equalsIgnoreCase(".")) {
                        mydb.insertcustomer(n1,n2,n3,n4,n5,n6,n7,"M",n8,n9,n10,n11);
                    }else{
                        try {
                            if (cr1.getCount() > 0) {
                                dbs.execSQL("Delete from customerlist where farmer_code = '" + n1 + "'");
                                mydb.insertcustomer(n1,n2,n3,n4,n5,n6,n7,"M",n8,n9,n10,n11);
                            } else {
                                mydb.insertcustomer(n1,n2,n3,n4,n5,n6,n7,"M",n8,n9,n10,n11);
                            }
                        } finally {
                            cr1.close();
                        }
                    }





                }
                for (int i = 0; i < castms.length(); i++) {


                    JSONObject actor = castms.getJSONObject(i);


                    String n1 = actor.getString("out_master_rowid");
                    String n2 = actor.getString("out_parent_code");
                    String n3 = actor.getString("out_master_code");
                    String n4 = actor.getString("out_master_description");
                    String n5 = actor.getString("out_depend_code");
                    String n6 = actor.getString("out_depend_desc");
                    String n7 = actor.getString("out_locallang_flag");
                    String n8 = actor.getString("out_status_code");
                    String n9 = actor.getString("out_status_desc");

                    Log.e("Table2", "" + n3);
                    Cursor cr1 = dbs.rawQuery("select * from masterl where out_master_code='"+n3+"'",null);
                    if(sharedpreferences.getString("dateforsyncicd","").equalsIgnoreCase(".")) {
                        try {
                            if (cr1.getCount() > 0) {
                                dbs.execSQL("Delete from masterl where out_master_code = '" + n3 + "'");
                                mydb.insertmasterl(n1, n2, n3, n4, n5, n6, n7, n8, n9);
                            } else {
                                mydb.insertmasterl(n1, n2, n3, n4, n5, n6, n7, n8, n9);
                            }
                        } finally {
                            cr1.close();
                        }

                    }
                    else
                    {

                     // mydb.insertmasterl(n1, n2, n3, n4, n5, n6, n7, n8, n9);


                        try {
                            if (cr1.getCount() > 0) {
                                dbs.execSQL("Delete from masterl where out_master_code = '" + n3 + "'");
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
            editor.putString("dateforsyncicd", getDate1());
            editor.commit();
            final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this)
//set icon
                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                    .setTitle("Success!")
//set message
                    .setMessage("Master Sync Completed")
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
    public static String getDate1() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
