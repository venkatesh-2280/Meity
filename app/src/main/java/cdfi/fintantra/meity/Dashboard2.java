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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Dashboard2 extends AppCompatActivity {
    ImageView nd;
    RelativeLayout rl1, rl2, rl3, r1, r2, r3, r6, rm, trs, rlog,rd;
    ImageView im, im2;
    String mPhoneNumber;
    String fmid,n2n;
    JSONObject user3;
    String adsfid;
    String afcode;
    Button newdsgn;
    JSONArray castbank,castfar,castemaster;
    ArrayList<String> arrayn;
    private static final String READ_PHONE_STATE = String.valueOf(1);
    DBHelper mydb;
    ProgressDialog pdialog;
    Dialog dialog;
    ArrayList<String> tfield = new ArrayList<>();
    JSONObject userd;
    String farid;
    Uri picUri,picUri2;
    String ui = "0", ui2="1";
    String encodedImage = "0",encodedImage2 = "0",encodedImage3="0";
    ByteArrayOutputStream byteArrayOutputStream,byteArrayOutputStream2,byteArrayOutputStream3;
    String imageFilePath,imageFilePath2;
    Bitmap thePic,thePic2;
    String code;
    final int CAMERA_CAPTURE = 1;
    private static final int CAMERA_REQUEST = 1888;

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    int offc = 0;
    int offc2 = 0;
    TextView titleh;
    String gf;
    JSONObject jsonParam;
    String n1, n10, n11, n12, n13,n2,n3,n4,n5;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_dashboard2);
        getSupportActionBar().hide();
        nd = (ImageView) findViewById(R.id.nd);
        im = (ImageView) findViewById(R.id.im);
        im2 = (ImageView) findViewById(R.id.im2);
        mydb = new DBHelper(this);
        pdialog = new ProgressDialog(this);
        arrayn = new ArrayList<>();



        // save();
        // save2();
        // save3();
        // save4();
//        final SQLiteDatabase dbs = mydb.getWritableDatabase();
//         String selectQuery5 = "SELECT  * FROM masterl";
//
//         Cursor cc = dbs.rawQuery(selectQuery5, null);
//         if(cc.getCount() == 0) {
//             pdialog.setCanceledOnTouchOutside(false);
//             pdialog.setTitle("Loading.....");
//             pdialog.show();
//             //rm.setEnabled(false);
//             save();
//         }


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("phn", "");
        editor.putString("uns", "adsfdrm001");



        editor.commit();

        newdsgn = (Button) findViewById(R.id.newdsgn);
        newdsgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity3.class);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("frm", "1");
                editor.putString("fcode", "");
                editor.putString("fid", "");

                editor.commit();
                startActivity(i);
            }
        });
        titleh = (TextView) findViewById(R.id.title);
        titleh.setText(sharedpreferences.getString("rn",""));
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Dashboard2.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.orglist);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                lp.gravity = Gravity.CENTER;

                dialog.getWindow().setAttributes(lp);
                final SQLiteDatabase dbs = mydb.getWritableDatabase();
                String selectQuery = "SELECT  * FROM orgn";
                // looping through all rows and adding to list
                Cursor cursor = dbs.rawQuery(selectQuery, null);
                Log.e("NULL",""+cursor.getCount());
                List<String> org2 = new ArrayList<String>();
                org2.add("Tap to Select FPO");
                final List<String> org1 = new ArrayList<String>();
                org1.add("0");
                final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);
                if (cursor.moveToFirst()) {
                    do {
                        org1.add(cursor.getString(2));
                        org2.add(cursor.getString(3));






                        // Log.e("Check",""+cursor.getString(1));


                        // array2.add(cursor.getString(11));
                        // Log.e("VAL",""+cursor.getString(11));

                    } while (cursor.moveToNext());
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Dashboard2.this, R.layout.spinnertext, org2);

                    // Drop down layout style - list view with radio button
                    dataAdapter.setDropDownViewResource(R.layout.spinnertext);

                    // attaching data adapter to spinner
                    spinner.setAdapter(dataAdapter);
                }

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {

                        if(position>0)
                        {


                            SharedPreferences.Editor editor = sharedpreferences.edit();

                            editor.putString("orgn",org1.get(position));

                            editor.commit();

                            final ProgressDialog pd = new ProgressDialog(Dashboard2.this);
                            pd.setMessage("Loading");
                            pd.show();
                            Intent i = new Intent(getApplicationContext(), MainActivity3.class);


                            editor.putString("frm", "1");
                            editor.putString("fcode", "");
                            editor.putString("fid", "");

                            editor.commit();
                            //startActivity(i);
                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    pd.dismiss();

                                }

                            }, 2000);




                        }

                    } // to close the onItemSelected
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });
                ImageView dialogButton = (ImageView) dialog.findViewById(R.id.close);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

               // dialog.show();
                Intent i = new Intent(getApplicationContext(), MainActivity3.class);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("frm", "1");
                editor.putString("fcode", "");
                editor.putString("fid", "");

                editor.commit();


                startActivity(i);
               // pd.dismiss();
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkAvailable()) {
                    final ProgressDialog pd = new ProgressDialog(Dashboard2.this);
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
            public void onClick(View v) {
                dialog = new Dialog(Dashboard2.this);
                dialog.setContentView(R.layout.navimenu2);
                dialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
                dialog.setTitle("Title...");
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.70);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                //dialog.getWindow().setLayout(width, height);
                // set the custom dialog components - text, image and button

                r1 = (RelativeLayout) dialog.findViewById(R.id.r1);
                r2 = (RelativeLayout) dialog.findViewById(R.id.r2);
                rd = (RelativeLayout) dialog.findViewById(R.id.rd);
                rm = (RelativeLayout) dialog.findViewById(R.id.rm);
                trs = (RelativeLayout) dialog.findViewById(R.id.trs);
                rlog = (RelativeLayout) dialog.findViewById(R.id.rlog);

                rd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), DuplicateFarmerList.class);

                        startActivity(i);
                    }
                });
                r1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialog = new Dialog(Dashboard2.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.orglist);
                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                        lp.copyFrom(dialog.getWindow().getAttributes());
                        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                        lp.gravity = Gravity.CENTER;

                        dialog.getWindow().setAttributes(lp);
                        final SQLiteDatabase dbs = mydb.getWritableDatabase();
                        String selectQuery = "SELECT  * FROM orgn";
                        // looping through all rows and adding to list
                        Cursor cursor = dbs.rawQuery(selectQuery, null);
                        Log.e("NULL",""+cursor.getCount());
                        List<String> org2 = new ArrayList<String>();
                        org2.add("Tap to Select FPO");
                        final List<String> org1 = new ArrayList<String>();
                        org1.add("0");
                        final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);
                        if (cursor.moveToFirst()) {
                            do {
                                org1.add(cursor.getString(2));
                                org2.add(cursor.getString(3));






                                // Log.e("Check",""+cursor.getString(1));


                                // array2.add(cursor.getString(11));
                                // Log.e("VAL",""+cursor.getString(11));

                            } while (cursor.moveToNext());
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Dashboard2.this, R.layout.spinnertext, org2);

                            // Drop down layout style - list view with radio button
                            dataAdapter.setDropDownViewResource(R.layout.spinnertext);

                            // attaching data adapter to spinner
                            spinner.setAdapter(dataAdapter);
                        }

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                            {

                                if(position>0)
                                {


                                    SharedPreferences.Editor editor = sharedpreferences.edit();

                                    editor.putString("orgn",org1.get(position));

                                    editor.commit();

                                    final ProgressDialog pd = new ProgressDialog(Dashboard2.this);
                                    pd.setMessage("Loading");
                                    pd.show();
                                    Intent i = new Intent(getApplicationContext(), MainActivity3.class);


                                    editor.putString("frm", "1");
                                    editor.putString("fcode", "");
                                    editor.putString("fid", "");

                                    editor.commit();
                                 ///   startActivity(i);
                                    new Handler().postDelayed(new Runnable() {

                                        @Override
                                        public void run() {
                                            pd.dismiss();

                                        }

                                    }, 2000);




                                }

                            } // to close the onItemSelected
                            public void onNothingSelected(AdapterView<?> parent)
                            {

                            }
                        });
                        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.close);
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                       // dialog.show();
                        Intent i = new Intent(getApplicationContext(), MainActivity3.class);
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString("frm", "1");
                        editor.putString("fcode", "");
                        editor.putString("fid", "");

                        editor.commit();


                        startActivity(i);
                    }
                });
                r2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isNetworkAvailable()) {
                            final ProgressDialog pd = new ProgressDialog(Dashboard2.this);
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

                rlog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isNetworkAvailable()) {
                            RequestQueue queue = Volley.newRequestQueue(Dashboard2.this);
                            final String url = Pojokyc.url+"/api/Mobile_FDR_Login/FdrLogin?org=logout&locn=chennai&user=admin&lang=en_US&instance=odisha&In_user_code="+sharedpreferences.getString("username","")+"&In_user_pwd=0";

// prepare the Request

                            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            // display response
                                            Log.d("Response", response.toString());

//                                            SharedPreferences.Editor editor = sharedpreferences.edit();
//                                            editor.clear();
//                                            editor.apply();
                                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
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
                            Toast.makeText(Dashboard2.this, "No Network Available", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                rm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


//                        Toast.makeText(Dashboard2.this, "Please Wait", Toast.LENGTH_SHORT).show();
//
////                        pdialog.setCanceledOnTouchOutside(false);
////                        pdialog.setTitle("Master Sync.....");
////                        pdialog.show();
////                        rm.setEnabled(false);
////                     //   save();
////                        save2();
////                        save3();
////                        save4();
////                        save5();
////                        save6();
//
//                        dialog.dismiss();
//
//                        final Dialog dialog2 = new Dialog(Dashboard2.this);
//                        dialog2.setContentView(R.layout.orglist2);
//                        //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
//                        dialog2.setTitle("Title...");
//                        int width = WindowManager.LayoutParams.MATCH_PARENT;
//                        int height = WindowManager.LayoutParams.MATCH_PARENT;
//
//                        dialog2.getWindow().setLayout(width, height);
//
//                        final Spinner spinner = (Spinner) dialog2.findViewById(R.id.spinner);
//                        final Spinner spinner2 = (Spinner) dialog2.findViewById(R.id.spinner2);
//                        final Spinner spinner3 = (Spinner) dialog2.findViewById(R.id.spinner3);
//                        final Spinner spinner4 = (Spinner) dialog2.findViewById(R.id.spinner4);
//                        final Button button = (Button) dialog2.findViewById(R.id.button);
//
//
//                        pdialog = new ProgressDialog(Dashboard2.this);
//                        pdialog.setCanceledOnTouchOutside(false);
//                        pdialog.setTitle("Loading.....");
//                        pdialog.show();
//                        // final SQLiteDataBaseHandler db = new SQLiteDataBaseHandler(Dashboard2.this);
//                        final SQLiteDatabase dbs = mydb.getWritableDatabase();
//                        //dbs.execSQL("delete from fpoad");
//
//
//                        final List<String> lvil = new ArrayList<String>();
//                        final List<String> lvilc = new ArrayList<String>();
//                        final List<String> lvil2 = new ArrayList<String>();
//                        lvil.add("Tap to Select Village");
//                        lvilc.add("0");
//                        final List<String> lgr = new ArrayList<String>();
//                        final List<String> lgrc = new ArrayList<String>();
//                        final List<String> lgr2 = new ArrayList<String>();
//                        lgrc.add("0");
//                        lgr.add("Tap to Select Gramapanchayat");
//                        final List<String> lta = new ArrayList<String>();
//                        final List<String> ltac = new ArrayList<String>();
//                        lta.add("Tap to Select Taluk");
//                        ltac.add("0");
//
//                        final String url = ApiUtils.TEST_URL_API+"New_PAWHS_Login/PAWHSGramFetch?org=NKFPO&instance="+ApiUtils.instance;
//
//                        Log.e("URL",""+url);
//
//// prepare the Request
//
//                        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                                new Response.Listener<JSONObject>()
//                                {
//                                    @Override
//                                    public void onResponse(JSONObject response) {
//                                        // display response
//                                        Log.e("Response", response.toString());
//
//
//                                        try {
//                                            JSONObject obj = response.getJSONObject("gramFetchContext");
//                                            JSONArray cast = obj.getJSONArray("list");
//                                            for (int i = 0; i < cast.length(); i++) {
//
//
//                                                JSONObject actor = cast.getJSONObject(i);
//
//
//                                                String n1 = actor.getString("country_mst");
//                                                String n2 = actor.getString("country_desc");
//                                                String n3 = actor.getString("state_mst");
//                                                String n4 = actor.getString("state_desc");
//                                                String n5 = actor.getString("dist_mst");
//                                                String n6 = actor.getString("dist_desc");
//                                                String n7 = actor.getString("taluk_mst");
//                                                String n8 = actor.getString("taluk_desc");
//                                                String n9 = actor.getString("panchayat_mst");
//                                                String n10 = actor.getString("panchayat_desc");
//                                                String n11 = actor.getString("village_mst");
//                                                String n12 = actor.getString("village_desc");
//                                                String n13 = actor.getString("pincode");
////                                                                            if(lta.contains(n8))
////                                                                            {
////
////                                                                            }
////                                                                            else
////                                                                            {
////                                                                                lta.add(n8);
////                                                                                ltac.add(n7);
////                                                                            }
//                                                if(lgr.contains(n10))
//                                                {
//
//                                                }
//                                                else
//                                                {
//                                                    lgr.add(n10);
//                                                    lgrc.add(n9);
//                                                }
//                                                lgr2.add(n9);
//                                                lvil2.add(n11);
//
//
//                                                Log.e("Table2", "" + n3);
//
//                                                // mydb.insertaddress(n1, n2, n3, n4, n5, n6, n7, n8, n9,n10,n11,n12,n13);
//                                                spinner.setVisibility(View.GONE);
//
//                                                button.setVisibility(View.VISIBLE);
//                                                // spinner2.setVisibility(View.VISIBLE);
//                                                // pdialog.dismiss();
//                                                spinner3.setVisibility(View.VISIBLE);
//                                                spinner4.setVisibility(View.VISIBLE);
//
//                                            }
//                                            pdialog.dismiss();
//                                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Dashboard2.this, R.layout.spinner_item2, lta);
//
//                                            // Drop down layout style - list view with radio button
//                                            dataAdapter.setDropDownViewResource(R.layout.spinner_item2);
//
//                                            // attaching data adapter to spinner
//                                            spinner2.setAdapter(dataAdapter);
//
//                                            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(Dashboard2.this, R.layout.spinner_item2, lgr);
//
//                                            // Drop down layout style - list view with radio button
//                                            dataAdapter2.setDropDownViewResource(R.layout.spinner_item2);
//
//                                            // attaching data adapter to spinner
//                                            spinner3.setAdapter(dataAdapter2);
//
//                                            ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(Dashboard2.this, R.layout.spinner_item2, lvil);
//
//                                            // Drop down layout style - list view with radio button
//                                            dataAdapter3.setDropDownViewResource(R.layout.spinner_item2);
//
//                                            // attaching data adapter to spinner
//                                            spinner4.setAdapter(dataAdapter3);
//
//                                            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//                                            {
//                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                                                {
//
//                                                    if(position>0)
//                                                    {
//                                                        SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                                                        editor.putString("tcode","QCD_UNT_"+(String) parent.getItemAtPosition(position));
//                                                        editor.putString("tname", (String) parent.getItemAtPosition(position));
//
//                                                        editor.commit();
//                                                        Cursor cursor = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_depend_desc"
//                                                                }, "out_depend_code" + "=? COLLATE NOCASE",
//                                                                new String[]{"QCD_UNT_"+(String) parent.getItemAtPosition(position)}, null, null, null, null);
//                                                        //  Toast.makeText(Dashboard2.this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
//                                                        if (cursor.getCount() != 0) {
//                                                            lgr.clear();
//                                                            lgrc.clear();
//                                                            lgr.add("Tap To Select Gramapanchayat");
//                                                            lgrc.add("0");
//                                                            if (cursor.moveToFirst()) {
//                                                                do {
//                                                                    if(lgr2.contains(cursor.getString(0)))
//                                                                    {
//
//                                                                        Cursor cursor2 = dbs.query("masterl", new String[]{"out_depend_desc"
//                                                                                }, "out_master_code" + "=? COLLATE NOCASE",
//                                                                                new String[]{"QCD_UNT_"+(String) parent.getItemAtPosition(position)}, null, null, null, null);
//                                                                        if (cursor2.moveToFirst()) {
//
//                                                                            editor.putString("dis",cursor2.getString(0));
//
//                                                                            editor.commit();
//                                                                        }
//
//                                                                        lgr.add(cursor.getString(1));
//                                                                        lgrc.add(cursor.getString(0));
//                                                                    }
//
//
//
//                                                                } while (cursor.moveToNext());
//
//
//                                                                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(Dashboard2.this, R.layout.spinner_item2, lgr);
//
//                                                                // Drop down layout style - list view with radio button
//                                                                dataAdapter2.setDropDownViewResource(R.layout.spinner_item2);
//
//                                                                // attaching data adapter to spinner
//                                                                spinner3.setAdapter(dataAdapter2);
//                                                            }
//
//
//                                                        }
//                                                    }
//
//                                                } // to close the onItemSelected
//                                                public void onNothingSelected(AdapterView<?> parent)
//                                                {
//
//                                                }
//                                            });
//
//                                            spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//                                            {
//                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                                                {
//
//                                                    if(position>0)
//                                                    {
//                                                        SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                                                        editor.putString("gcode","QCD_UNG_"+(String) parent.getItemAtPosition(position));
//                                                        editor.putString("gname", (String) parent.getItemAtPosition(position));
//
//                                                        Log.e("CHK",""+"QCD_UNG_"+(String) parent.getItemAtPosition(position));
//                                                        Log.e("CHKN",""+(String) parent.getItemAtPosition(position));
//
//                                                        editor.commit();
//                                                        Cursor cursor = dbs.query("masterl", new String[]{"out_master_code","out_master_description"
//                                                                }, "out_depend_code" + "=? COLLATE NOCASE",
//                                                                new String[]{"QCD_UNG_"+(String) parent.getItemAtPosition(position)}, null, null, null, null);
//                                                        Cursor cursor2 = dbs.query("masterl", new String[]{"out_depend_code","out_depend_desc"
//                                                                }, "out_master_code" + "=? COLLATE NOCASE",
//                                                                new String[]{"QCD_UNG_"+(String) parent.getItemAtPosition(position)}, null, null, null, null);
//                                                        if(cursor2.moveToNext())
//                                                        {
//
//
//                                                            editor.putString("tcode",cursor2.getString(0));
//                                                            editor.putString("tname", cursor2.getString(1));
//
//                                                            editor.commit();
//                                                            Cursor cursor3 = dbs.query("masterl", new String[]{"out_depend_desc"
//                                                                    }, "out_master_code" + "=? COLLATE NOCASE",
//                                                                    new String[]{cursor2.getString(0)}, null, null, null, null);
//                                                            if (cursor3.moveToFirst()) {
//
//                                                                editor.putString("dis",cursor3.getString(0));
//
//                                                                editor.commit();
//                                                            }
//                                                        }
//
//                                                        Log.e("CHKN2",""+cursor.getCount());
//                                                        //  Toast.makeText(Dashboard2.this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
//                                                        if (cursor.getCount() != 0) {
//                                                            lvil.clear();
//                                                            lvilc.clear();
//                                                            lvil.add("Tap To Select Village");
//                                                            lvilc.add("0");
//                                                            if (cursor.moveToFirst()) {
//                                                                do {
//                                                                    if(lvil2.contains(cursor.getString(0)))
//                                                                    {
//
//                                                                        lvil.add(cursor.getString(1));
//                                                                        lvilc.add(cursor.getString(0));
//                                                                    }
//
//
//
//                                                                } while (cursor.moveToNext());
//
//
//                                                                ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(Dashboard2.this, R.layout.spinner_item2, lvil);
//
//                                                                // Drop down layout style - list view with radio button
//                                                                dataAdapter3.setDropDownViewResource(R.layout.spinner_item2);
//
//                                                                // attaching data adapter to spinner
//                                                                spinner4.setAdapter(dataAdapter3);
//                                                            }
//
//
//                                                        }
//                                                    }
//
//                                                } // to close the onItemSelected
//                                                public void onNothingSelected(AdapterView<?> parent)
//                                                {
//
//                                                }
//                                            });
//
//
//                                            spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//                                            {
//                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                                                {
//
//                                                    if(position>0)
//                                                    {
//
//
//                                                        SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                                                        editor.putString("vcode","QCD_UNV_"+(String) parent.getItemAtPosition(position));
//                                                        editor.putString("vname", (String) parent.getItemAtPosition(position));
//
//                                                        editor.commit();
//
//
//
//
//
//
//                                                    }
//
//                                                } // to close the onItemSelected
//                                                public void onNothingSelected(AdapterView<?> parent)
//                                                {
//
//                                                }
//                                            });
//
//                                        } catch (JSONException e) {
//                                            FirebaseApp.initializeApp(Dashboard2.this);
//                                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");
//
//                                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//                                            Long tsLong = System.currentTimeMillis()/1000;
//                                            String ts = tsLong.toString();
//                                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
//                                            Log.e("Valuecheck",""+mRef.getRef());
//                                            mRef.child("name").setValue("GPLIST");
//                                            mRef.child("date").setValue(date);
//                                            mRef.child("Error").setValue(response.toString());
//                                            mRef.child("Activity").setValue("MASTER");
//                                            // Toast.makeText(Dashboard2.this, "Invalid Login", Toast.LENGTH_SHORT).show();
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                },
//                                new Response.ErrorListener()
//                                {
//                                    @Override
//                                    public void onErrorResponse(VolleyError error) {
//                                        Toast.makeText(Dashboard2.this, "Error:"+error.getMessage(), Toast.LENGTH_LONG).show();
//
//                                        Log.d("Error.Response", String.valueOf(error));
//                                    }
//                                }
//                        );
//
//// add it to the RequestQueue
//                        getRequest.setRetryPolicy(new DefaultRetryPolicy(
//                                90000,
//                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                        VolleySingleton.getInstance(Dashboard2.this).addToRequestQueue(getRequest);
////                                                    startActivity(new Intent(mContext, HomePageActivity.class));
////                                                    finish();
//
//                        button.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                                //  callQualityParameterJsonDetails();
//
//                                if(spinner3.getSelectedItem().toString().equalsIgnoreCase("Tap to select gramapanchayat"))
//                                {
//                                    showErrorDialog("Please Select Gramapanchayat");
//                                }
//                                else  if(spinner4.getSelectedItem().toString().equalsIgnoreCase("Tap to select village"))
//                                {
//                                    showErrorDialog("Please Select Village");
//                                }
//                                else
//                                {
//                                    pdialog.setCanceledOnTouchOutside(false);
//                                    pdialog.setTitle("Master Sync.....");
//                                    pdialog.show();
//                                    dialog2.dismiss();
//                                    // rm.setEnabled(false);
//                                    //   save();
//                                    save2();
//                                    save3();
//                                     save4();
//                                    save5s();
//                                    save6();
//                                    save7();
//
//                                }
//                            }
//                        });
//                       // dialog2.show();

                        pdialog.setCanceledOnTouchOutside(false);
                        pdialog.setTitle("Master Sync.....");
                        pdialog.show();
                       // dialog2.dismiss();
                        rm.setEnabled(false);
                           save();
                       // save2();
                        save3();
                        save4();
                        save5();
                        save6();
                        save7();


//
                    }
                });

                trs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (isNetworkAvailable()) {

                            final SQLiteDatabase dbs = mydb.getWritableDatabase();
                            String selectQuery5 = "SELECT  * FROM farmerh where flag = 0";
                            Cursor cc = dbs.rawQuery(selectQuery5, null);

                            if (cc.getCount() == 0) {
                                final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard2.this)
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
                            final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard2.this)
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

    public void save() {
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Master Sync Is In Process.....");
        pdialog.show();
        SQLiteDatabase dbs = mydb.getWritableDatabase();
       // dbs.execSQL("delete from masterl");
        try {


            DecimalFormat amountFormate = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", sharedpreferences.getString("oc1",""));
            userd.put("locnId", sharedpreferences.getString("dateforsync",""));

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
                             castemaster = obj.getJSONArray("detail");
                             save2();

                           // pdialog.dismiss();


                        } catch (JSONException e) {
                            FirebaseApp.initializeApp(Dashboard2.this);
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Long tsLong = System.currentTimeMillis()/1000;
                            String ts = tsLong.toString();
                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                            Log.e("Valuecheck",""+mRef.getRef());
                            mRef.child("name").setValue("MASTER");
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

            Log.e("OUTPUT", "" + userd.toString());

        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

        //http://169.38.77.190:101/api/Mobile_FDR/Farmerbank
        //15.206.21.248:27/Farmerbank
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


                                    JsonObjectRequest stringRequest2 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/Mobile_FDR_FList/FarmerList", userd,
                                            new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    Log.e("CCCC", "" + response);
                                                    try {
                                                        JSONObject obj = response.getJSONObject("context");
                                                        castfar = obj.getJSONArray("list");
                                                         new SomeTask().execute();

                                                        //  pdialog.dismiss();




                                                    } catch (JSONException e) {
                                                        FirebaseApp.initializeApp(Dashboard2.this);
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
                                    VolleySingleton.getInstance(Dashboard2.this).addToRequestQueue(stringRequest2);
                                }
                            });
                        } catch (JSONException e) {
                            FirebaseApp.initializeApp(Dashboard2.this);
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Long tsLong = System.currentTimeMillis()/1000;
                            String ts = tsLong.toString();
                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                            Log.e("Valuecheck",""+mRef.getRef());
                            mRef.child("name").setValue("BANKLIST");
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
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
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
                            FirebaseApp.initializeApp(Dashboard2.this);
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Long tsLong = System.currentTimeMillis()/1000;
                            String ts = tsLong.toString();
                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                            Log.e("Valuecheck",""+mRef.getRef());
                            mRef.child("name").setValue("PROFILE");
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
                            FirebaseApp.initializeApp(Dashboard2.this);
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Long tsLong = System.currentTimeMillis()/1000;
                            String ts = tsLong.toString();
                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                            Log.e("Valuecheck",""+mRef.getRef());
                            mRef.child("name").setValue("PROFILE");
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
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void save5() {




    }


    public void offlinesave() {
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
                user2.put("in_fpo_orgncode",cc.getString(7));
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

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/Mobile_FDR_Offlinesave/MFarmerProfileSave", jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        Log.e("CCCC", "" + response);

                        try {


                            if (response.getString("in_farmer_rowid").equalsIgnoreCase("0")) {

//                                pdialog.dismiss();
//                                final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard2.this)
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
                                   // Toast.makeText(Dashboard2.this, ""+offc+"/"+offc2, Toast.LENGTH_SHORT).show();

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
                                        final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard2.this)
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
                                if(cursoraddr.moveToFirst()) {
                                    saveads(cursoraddr.getString(0));
                                }
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
                                    final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard2.this)
//set icon
                                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                            .setTitle("Success!")
//set message
                                            .setMessage("Transaction Completed!(DUPLICATE FARMER:"+cc6.getCount())
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
                        final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard2.this)
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onBackPressed() {
    finish();
    }


    public void saveads(String id)
    {
        int id2 = Integer.parseInt(id);
        final SQLiteDatabase dbs = mydb.getWritableDatabase();
        try {


            jsonParam = new JSONObject();

            JSONObject user = new JSONObject();
            user.put("orgnId", "flexi");
            user.put("locnId", "chennai");
            user.put("userId", "fdrmob");
            user.put("localeId", "en_US");
            user.put("instance", Pojokyc.instance);
            user.put("farmer_code",  afcode );
            jsonParam.put("context", user);
            JSONObject user2 = new JSONObject();

            String selectQueryads = "SELECT  * FROM ads where id = "+id2;

            Cursor cursor2 = dbs.rawQuery(selectQueryads, null);


            if(cursor2.moveToFirst())
            {

                user2.put("EC_ADS_LF", cursor2.getString(1));
                user2.put("EC_ADS_EDUCATION", cursor2.getString(2));
                user2.put("EC_ADS_CASTE", cursor2.getString(3));
                user2.put("EC_ADS_BANKACC", cursor2.getString(4));
                user2.put("EC_ADS_OICLABOUR", cursor2.getString(5));
                user2.put("EC_ADS_HOUSE", cursor2.getString(6));
                // user2.put("EC_ADS_HOUSEPucca", cursor2.getString(7));
                user2.put("EC_ADS_OICBusiness", cursor2.getString(8));
                user2.put("EC_ADS_OICJOB", cursor2.getString(9));
                user2.put("EC_ADS_SHCard", cursor2.getString(10));
                user2.put("EC_ADS_PISFPO", cursor2.getString(11));
                user2.put("EC_ADS_PISGOVT", cursor2.getString(12));
                user2.put("EC_ADS_PISTRADER", cursor2.getString(13));
                user2.put("EC_ADS_ISFPond", cursor2.getString(14));
                user2.put("EC_ADS_ISCanal", cursor2.getString(15));
                user2.put("EC_ADS_ISTubewell", cursor2.getString(16));
                user2.put("EC_ADS_TRACTOR", cursor2.getString(17));
                user2.put("EC_ADS_LoanBank", cursor2.getString(18));
                user2.put("EC_ADS_LoanMFI", cursor2.getString(19));
                user2.put("EC_ADS_LoanVill", cursor2.getString(20));
                user2.put("EC_ADS_INSCrop", cursor2.getString(21));
                user2.put("EC_ADS_INSHealth", cursor2.getString(22));
                user2.put("EC_ADS_INSLife", cursor2.getString(23));
                user2.put("EC_ADS_INPLTRADER", cursor2.getString(24));
                user2.put("EC_ADS_RMCReg", cursor2.getString(25));
                user2.put("EC_ADS_eNAMReg", cursor2.getString(26));
                user2.put("EC_ADS_PMKisanNidhi", cursor2.getString(27));
                user2.put("EC_ADS_Kalia", cursor2.getString(28));
                user2.put("EC_ADS_SMPFPO", cursor2.getString(29));
                user2.put("EC_ADS_SMPRMC", cursor2.getString(30));
                user2.put("EC_ADS_SMPLTRADER", cursor2.getString(31));
                user2.put("EC_ADS_MSTFPO", cursor2.getString(32));
                user2.put("EC_ADS_MSTRMC", cursor2.getString(33));
                user2.put("EC_ADS_MSTLTRAADER", cursor2.getString(34));
                user2.put("EC_ADS_INPFPO", cursor2.getString(35));
                user2.put("EC_ADS_INPSOCIETY", cursor2.getString(36));
                user2.put("EC_ADS_OMSPucca", cursor2.getString(37));
                user2.put("EC_ADS_OMSKuccha", cursor2.getString(38));
                user2.put("EC_ADS_DPPlatform", cursor2.getString(39));
                user2.put("EC_ADS_DPPucca", cursor2.getString(40));
                user2.put("EC_ADS_DPKuccha", cursor2.getString(41));
                user2.put("EC_ADS_DPROAD", cursor2.getString(42));
                user2.put("EC_ADS_maizesoldMonth3", cursor2.getString(43));
                user2.put("EC_ADS_maizesoldMonth13", cursor2.getString(44));
                user2.put("EC_ADS_maizesoldMonth01", cursor2.getString(45));
                user2.put("EC_ADS_WhetherAggregator", cursor2.getString(46));
                user2.put("EC_ADS_IFPC", cursor2.getString(47));
                user2.put("EC_ADS_RCLPHM", cursor2.getString(48));
                user2.put("EC_ADS_RCLPest", cursor2.getString(49));
                user2.put("EC_ADS_RCLWeather", cursor2.getString(50));
                user2.put("EC_ADS_MAIZE_DD", cursor2.getString(51));
                user2.put("EC_ADS_MAIZE_F", cursor2.getString(52));
                user2.put("EC_ADS_MAIZE_M", cursor2.getString(53));

                user2.put("EC_ADS_WorkingNo", cursor2.getString(56));
                user2.put("EC_ADS_FamilyNo", cursor2.getString(57));
                user2.put("EC_ADS_AadharNo", cursor2.getString(58));
                user2.put("EC_ADS_20_21RA_Veg", cursor2.getString(59));
                user2.put("EC_ADS_20_21RA_Paddy", cursor2.getString(60));
                user2.put("EC_ADS_20_21RA_MAIZE", cursor2.getString(61));
                user2.put("EC_ADS_19_20RA_Veg", cursor2.getString(62));
                user2.put("EC_ADS_19_20RA_Paddy", cursor2.getString(63));
                user2.put("EC_ADS_19_20RA_MAIZE", cursor2.getString(64));
                user2.put("EC_ADS_20_21KA_Veg", cursor2.getString(65));
                user2.put("EC_ADS_20_21KA_Paddy", cursor2.getString(66));
                user2.put("EC_ADS_20_21KA_MAIZE", cursor2.getString(67));
                user2.put("EC_ADS_19_20KA_Veg", cursor2.getString(68));
                user2.put("EC_ADS_19_20KA_Paddy", cursor2.getString(69));
                user2.put("EC_ADS_19_20KA_MAIZE", cursor2.getString(70));
                user2.put("EC_ADS_Leaseland", cursor2.getString(71));
                user2.put("EC_ADS_Ownland", cursor2.getString(72));
                user2.put("EC_ADS_Smallruminants", cursor2.getString(73));
                user2.put("EC_ADS_Poultry", cursor2.getString(74));
                user2.put("EC_ADS_Largeruminants", cursor2.getString(75));
                user2.put("EC_ADS_Ragi2021", cursor2.getString(76));
                user2.put("EC_ADS_Ragi2020", cursor2.getString(77));
                user2.put("EC_ADS_MaizeyieldKhRa", cursor2.getString(78));
                user2.put("EC_ADS_Maizeyieldqtlacre", cursor2.getString(79));
                user2.put("EC_ADS_FPCsharepaidRs", cursor2.getString(80));
                user2.put("EC_ADS_FSBags", cursor2.getString(81));
                user2.put("EC_ADS_FSLoose", cursor2.getString(82));
                user2.put("EC_ADS_FSCob", cursor2.getString(83));
                user2.put("EC_ADS_PAYRMC", cursor2.getString(84));
                user2.put("EC_ADS_PAYLTRADER", cursor2.getString(85));
                user2.put("EC_ADS_PAYHAT", cursor2.getString(86));
                user2.put("EC_ADS_SMRMC", cursor2.getString(87));
                user2.put("EC_ADS_SMLTRADER", cursor2.getString(88));
                user2.put("EC_ADS_SMHat", cursor2.getString(89));
                user2.put("EC_ADS_WRFinance", cursor2.getString(90));
                user2.put("EC_ADS_WHSubsidy",cursor2.getString(91));
                user2.put("EC_ADS_GROUPNAME", cursor2.getString(95));
                user2.put("EC_ADS_LTHNAME", cursor2.getString(96));
                user2.put("EC_ADS_LTHMOBILENO", cursor2.getString(97));
                user2.put("EC_ADS_NOOFMMACHINE", cursor2.getString(98));
                user2.put("EC_ADS_NTRACTORVILL", cursor2.getString(99));
                user2.put("EC_ADS_DISWAREHOUSE", cursor2.getString(100));
                user2.put("EC_ADS_WAREHOUSETYPE", cursor2.getString(101));
                user2.put("EC_ADS_ConcernLRPME",cursor2.getString(102));
                user2.put("EC_ADS_NFMMIGRATED",cursor2.getString(54));
                user2.put("EC_ADS_SRCLWeather", cursor2.getString(103));
                user2.put("EC_ADS_SRCLPest",cursor2.getString(104));
                user2.put("EC_ADS_SRCLPHM",cursor2.getString(105));
                user2.put("EC_ADS_BankAccNo",cursor2.getString(106));
                user2.put("EC_ADS_BankName", cursor2.getString(107));
                user2.put("EC_ADS_BankBranName",cursor2.getString(109));
                user2.put("EC_ADS_BankIFSC",cursor2.getString(108));



            }




            user.put("Header", user2);
            Log.e("OUTPUT", "" + jsonParam.toString());
        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//
        //169.38.77.190:101

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_ProfileADS/MFarmerProfileADSSave", jsonParam,
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
                       // pdialog.dismiss();

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
                2500000,
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
        VolleySingleton.getInstance(Dashboard2.this).addToRequestQueue(getRequest);
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

    public void save5s() {

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
                    userd.put("orgnId", "NKFPO");
                    userd.put("locnId", "up");
                    userd.put("userId", "admin");
                    userd.put("localeId", "en_US");
                    userd.put("instance", "up");
                    userd.put("FilterBy_Option", "ALL");
                    userd.put("FilterBy_Code", sharedpreferences.getString("tcode", ""));
                    userd.put("FilterBy_FromValue", sharedpreferences.getString("gcode", ""));
                    userd.put("FilterBy_ToValue", sharedpreferences.getString("vcode", ""));

                    Log.e("OUTPUT5s", "" + userd.toString());

                } catch (Exception e) {
                    Log.e("OUTPUT", "" + e.getMessage());
                }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();


                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/PAWHS_NEW_Farmerlist/new_pawhs_Farmer_List", userd,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("CCCCF", "" + response);
                                try {
                                    JSONObject obj = response.getJSONObject("context");
                                    JSONArray cast = obj.getJSONArray("list");
                                    for (int j = 0; j < cast.length(); j++) {
                                        JSONObject actor = cast.getJSONObject(j);


                                        String n1 = actor.getString("farmer_rowid");
                                        String n2 = actor.getString("farmer_code");
                                        String n3 = actor.getString("farmer_name");
                                        String n4 = actor.getString("farmer_village_desc");
                                        String n5 = actor.getString("farmer_panchayat_desc");
                                        String n6 = actor.getString("farmer_taluk_desc");
                                        String n7 = actor.getString("farmer_dist_desc");

                                        String n8 = actor.getString("fhw_name");
                                        String n9 = actor.getString("farmer_surname");
                                        String n10 = "NA";
                                        String n11 = "NA";
                                        String n12 = actor.getString("farmer_dob");


                                        //String[] dob2 = n12.split("/");
                                        //Log.e("DATE",""+dob2[2]+"/"+dob2[1]+"/"+dob2[1]);

                                        mydb.inserfarlist(n1, n2, n3, n8, n4, n5, n6, n7,n9,n10,n11,n12,"1","0","0","0");


                                    }


                                    // pdialog.dismiss();




                                } catch (JSONException e) {
                                    FirebaseApp.initializeApp(Dashboard2.this);
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
                VolleySingleton.getInstance(Dashboard2.this).addToRequestQueue(stringRequest);
            }
        });


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
                    String query = "Select * from bankm where bank_code = '" + actor.getString("bank_code") + "'";
                    Cursor cursor = dbs.rawQuery(query, null);
                    if(sharedpreferences.getString("dateforsync","").equalsIgnoreCase(".")) {

                        mydb.insertbankm(n1, n2, n3, n4, n5, n6);
                    }
                    else {


                        try {

                            if (cursor.getCount() > 0) {
                                dbs.execSQL("Delete from bankm where ifsc_code = '" + actor.getString("ifsc_code") + "'");
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
                    //String n9 = "NA";
                    String n9 = actor.getString("farmer_surname");
                    String n10 = "NA";
                    String n11 = "NA";
                    // String n12 = "NA";
                    String n12 = actor.getString("farmer_dob");


                    //String[] dob2 = n12.split("/");
                    //Log.e("DATE",""+dob2[2]+"/"+dob2[1]+"/"+dob2[1]);
                    String query = "Select * from farlist where fc = '"+n2+"'";
                    Cursor cursor = dbs.rawQuery(query , null);

                    if(sharedpreferences.getString("dateforsync","").equalsIgnoreCase(".")) {

                        mydb.inserfarlist(n1, n2, n3, n8, n4, n5, n6, n7, n9, n10, n11, n12, "1","0","0","0");
                    }
                    else {
                        try {
                            if (cursor.getCount() > 0) {
                                dbs.execSQL("Delete from farlist where fc = '" + n2 + "'");
                                mydb.inserfarlist(n1, n2, n3, n8, n4, n5, n6, n7, n9, n10, n11, n12, "1","0","0","0");
                            } else {
                                mydb.inserfarlist(n1, n2, n3, n8, n4, n5, n6, n7, n9, n10, n11, n12, "1","0","0","0");
                            }
                        } finally {
                            cursor.close();
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

                    Log.e("Table2", "" + n3);
                    Cursor cr1 = dbs.rawQuery("select * from masterl where out_master_code='"+n3+"'",null);
                    if(sharedpreferences.getString("dateforsync","").equalsIgnoreCase(".")) {
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

            editor.putString("dateforsync", getDate1());

            editor.commit();

            final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard2.this)
//set icon
                    .setIcon(android.R.drawable.ic_menu_save)
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
    public static String getDate1() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
