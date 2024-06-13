package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.zxing.integration.android.IntentIntegrator;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GPSActivity extends AppCompatActivity {

    EditText dateofvisit,istime,ictime,pstime,pctime,scanval;
    Button save,cancel;
    int gpsrowid = 0;
    String lat = "",lng = "";
    String scanvalue = "",FPOcode = "",iudflag = "";
    ImageView qrcode;
    String modeflag= "I";
    SQLiteDatabase dbs;
    DBHelper db;
    private IntentIntegrator qrScan;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    ProgressDialog pdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsactivity);
        getSupportActionBar().hide();
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        db = new DBHelper(this);
        dbs = db.getWritableDatabase();
        statusCheck();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        pdialog = new ProgressDialog(this);
        dateofvisit = findViewById(R.id.dateofvisit);
        istime = findViewById(R.id.istime);
        ictime = findViewById(R.id.ictime);
        pstime = findViewById(R.id.pstime);
        pctime = findViewById(R.id.pctime);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
        qrcode = findViewById(R.id.qrcode);
        scanval = findViewById(R.id.scanval);
        FPOcode = sharedpreferences.getString("oc1","");
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan = new IntentIntegrator(GPSActivity.this);
                qrScan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                qrScan.setPrompt("Scan FPO Code");
                qrScan.setBeepEnabled(true);
                qrScan.setCameraId(0);
                qrScan.setOrientationLocked(false);
                qrScan.setBarcodeImageEnabled(true);
                qrScan.initiateScan();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        istime.setEnabled(false);
        ictime.setEnabled(false);
        pstime.setEnabled(false);
        pctime.setEnabled(false);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        String formattedDate = df.format(c);
        dateofvisit.setText(""+formattedDate);

        istime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settime(0);
            }
        });
        ictime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!istime.getText().toString().equalsIgnoreCase("")){
                    settime(1);
                }else{
                    alertdialog("Please Select Input Center Start Time First","Info","0");
                }
            }
        });
        pstime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settime(2);
            }
        });
        pctime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!pstime.getText().toString().equalsIgnoreCase("")){
                    settime(3);
                }else {
                    alertdialog("Please Select Produce Aggregation Start Time First","Info","0");
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(scanvalue.equalsIgnoreCase("")){
                    final AlertDialog alertDialog = new AlertDialog.Builder(GPSActivity.this)
//set icon
                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                            .setTitle("INFO!")
//set message
                            .setMessage("Qr Code Value Can't Be Blank!")
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

             //     else if(!istime.getText().toString().equalsIgnoreCase("") ||!pstime.getText().toString().equalsIgnoreCase(""))
              //  {
//                 if(!istime.getText().toString().equalsIgnoreCase("") && ictime.getText().toString().equalsIgnoreCase(""))
//                 {
//                     final AlertDialog alertDialog = new AlertDialog.Builder(GPSActivity.this)
////set icon
//                             .setIcon(android.R.drawable.ic_menu_save)
////set title
//                             .setTitle("INFO!")
////set message
//                             .setMessage("Input Close Time Can't Be Blank!")
////set positive button
//                             .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                         @Override
//                                         public void onClick(DialogInterface dialogInterface, int i) {
//                                             //set what would happen when positive button is clicked
//                                             // finish();
//
//                                         }
//                                     }
////set negative button
//
//                             )
//                             .show();
//                 }
//                 else  if(!pstime.getText().toString().equalsIgnoreCase("") && pctime.getText().toString().equalsIgnoreCase(""))
//                 {
//                     final AlertDialog alertDialog = new AlertDialog.Builder(GPSActivity.this)
////set icon
//                             .setIcon(android.R.drawable.ic_menu_save)
////set title
//                             .setTitle("INFO!")
////set message
//                             .setMessage("Produce Close Time Can't Be Blank!")
////set positive button
//                             .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                         @Override
//                                         public void onClick(DialogInterface dialogInterface, int i) {
//                                             //set what would happen when positive button is clicked
//                                             // finish();
//
//                                         }
//                                     }
////set negative button
//
//                             )
//                             .show();
//                 }
//                 else
//                 {
                    else
                {
                    if(isNetworkAvailable()){
                         if(iudflag.equalsIgnoreCase("U")){
                             dbs.execSQL("Update gpsactivity set " +
                                   //  "qrvalue = '"+scanvalue+"' , " +
                                     "latlng = '"+lat+"/"+lng+"', istime = '"+istime.getText().toString()+"', " +
                                     "ictime = '"+ictime.getText().toString()+"', pstime = '"+pstime.getText().toString()+"', " +
                                     "pctime = '"+pctime.getText().toString()+"' where " +
                                     "userid = '"+sharedpreferences.getString("uc","")+"' and " +
                                     "cdate = '"+getcurentdate()+"' and flag = '1' ");
                             savegpsapicall();
                             //alertdialog("Gps Saved Successfully","Success","1");
                         }else {
                             savegpsapicall();
                         }
                     }else{
                         if(iudflag.equalsIgnoreCase("U")){
                             dbs.execSQL("Update gpsactivity set " +
                                     //"qrvalue = '"+scanvalue+"' , " +
                                     "latlng = '"+lat+"/"+lng+"', istime = '"+istime.getText().toString()+"', " +
                                     "ictime = '"+ictime.getText().toString()+"', pstime = '"+pstime.getText().toString()+"', " +
                                     "pctime = '"+pctime.getText().toString()+"' where " +
                                     "userid = '"+sharedpreferences.getString("uc","")+"' and " +
                                     "cdate = '"+getcurentdate()+"' and flag = '0' ");
                             alertdialog("Gps Locally Saved Successfully","Success","1");
                         }else {
                             boolean r = db.insertgps(scanvalue,lat+"/"+lng,formatDate(dateofvisit.getText().toString(),"dd-MM-yyyy HH:mm:ss","yyyy-MM-dd HH:mm:ss"),istime.getText().toString(),ictime.getText().toString(),pstime.getText().toString(),pctime.getText().toString(),"0",sharedpreferences.getString("uc",""),"0");
                             if(r){
                                 alertdialog("Gps Locally Saved Successfully","Success","1");
                             }
                         }
                     }
                 }


               // }
//                else
//                {
//                    final AlertDialog alertDialog = new AlertDialog.Builder(GPSActivity.this)
////set icon
//                            .setIcon(android.R.drawable.ic_menu_save)
////set title
//                            .setTitle("INFO!")
////set message
//                            .setMessage("Please Select Atleast One Start Time!")
////set positive button
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialogInterface, int i) {
//                                            //set what would happen when positive button is clicked
//                                            // finish();
//
//                                        }
//                                    }
////set negative button
//
//                            )
//                            .show();
//                }

            }
        });
//
// Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                // makeUseOfNewLocation(location);
                lat = ""+location.getLatitude();
                lng = ""+location.getLongitude();
                Log.e("LOCAL GPS", "" + location.getLatitude() + "/" + location.getLongitude());
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

// Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        loaddata();
        istime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ictime.setText("");
            }
        });
        pstime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                pctime.setText("");
            }
        });
        ictime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
               try{
                   String istimeary [] = istime.getText().toString().split(" ");
                   String ictimeary [] = editable.toString().toString().split(" ");
                   String istimeary1 [] = istimeary[0].split(":");
                   String ictimeary1 [] = ictimeary[0].split(":");
                   if(istimeary[1].equalsIgnoreCase("PM")){
                       istimeary1[0] = String.valueOf(Integer.parseInt(istimeary1[0]) + 12);
                   }
                   if(ictimeary[1].equalsIgnoreCase("PM")){
                       ictimeary1[0] = String.valueOf(Integer.parseInt(ictimeary1[0]) + 12);
                   }
                   if(Integer.parseInt(ictimeary1[0]) < Integer.parseInt(istimeary1[0])){
                       alertdialog("Input Center Close Time Can't be Less Than Input Center Start Time","Info","0");
                       editable.clear();
                   }else if(Integer.parseInt(ictimeary1[0]) == Integer.parseInt(istimeary1[0])){
                       if(Integer.parseInt(ictimeary1[1]) < Integer.parseInt(istimeary1[1])){
                           alertdialog("Input Center Close Time Can't be Less Than Input Center Start Time","Info","0");
                           editable.clear();
                       }else if(Integer.parseInt(ictimeary1[1]) == Integer.parseInt(istimeary1[1])){
                           alertdialog("Input Center Close Time Can't be Equal to the Input Center Start Time","Info","0");
                           editable.clear();
                       }
                   }
               }catch (Exception er){
                   Log.e("error",Log.getStackTraceString(er));
               }
            }
        });
        pctime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try{
                    String pstimeary [] = pstime.getText().toString().split(" ");
                    String pctimeary [] = editable.toString().toString().split(" ");
                    String pstimeary1 [] = pstimeary[0].split(":");
                    String pctimeary1 [] = pctimeary[0].split(":");
                    if(pstimeary[1].equalsIgnoreCase("PM")){
                        pstimeary1[0] = String.valueOf(Integer.parseInt(pstimeary1[0]) + 12);
                    }
                    if(pctimeary[1].equalsIgnoreCase("PM")){
                        pctimeary1[0] = String.valueOf(Integer.parseInt(pctimeary1[0]) + 12);
                    }
                    if(Integer.parseInt(pctimeary1[0]) < Integer.parseInt(pstimeary1[0])){
                        alertdialog("Produce Aggregation Close Time Can't be Less Than Produce Aggregation Start Time","Info","0");
                        editable.clear();
                    }else if(Integer.parseInt(pctimeary1[0]) == Integer.parseInt(pstimeary1[0])){
                        if(Integer.parseInt(pctimeary1[1]) < Integer.parseInt(pstimeary1[1])){
                            alertdialog("Produce Aggregation Close Time Can't be Less Than Produce Aggregation Start Time","Info","0");
                            editable.clear();
                        }else if(Integer.parseInt(pctimeary1[1]) == Integer.parseInt(pstimeary1[1])){
                            alertdialog("Produce Aggregation Close Time Can't be Equal to the Produce Aggregation Start Time","Info","0");
                            editable.clear();
                        }
                    }
                }catch (Exception er){
                    Log.e("error",Log.getStackTraceString(er));
                }
            }
        });

    }

    public void settime(final int f)
    {
        Calendar mcurrentTime = Calendar.getInstance();
        final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        final int minute = mcurrentTime.get(Calendar.MINUTE);
        final TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(GPSActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String AM_PM = " AM";
                String mm_precede = "";
                if (selectedHour >= 12) {
                    AM_PM = " PM";
                    if (selectedHour >=13 && selectedHour < 24) {
                        selectedHour -= 12;
                    }
                    else {
                        selectedHour = 12;
                    }
                } else if (selectedHour == 0) {
                    selectedHour = 12;
                }
                if (minute < 10) {
                    mm_precede = "0";
                }
                if(f == 0) {
                    istime.setText( selectedHour + ":" + mm_precede + minute + AM_PM);
                    ictime.setEnabled(true);
                }
                if(f == 1) {
                    ictime.setText( selectedHour + ":" + mm_precede + minute + AM_PM);
                }
                if(f == 2) {
                    pstime.setText( selectedHour + ":" + mm_precede + minute + AM_PM);
                    pctime.setEnabled(true);
                }
                if(f == 3) {
                    pctime.setText( selectedHour + ":" + mm_precede + minute + AM_PM);
                }
            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
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


            if (requestCode == 49374) {


                Log.e("RCODE", "" + data.getStringExtra("SCAN_RESULT"));
                 scanvalue = data.getStringExtra("SCAN_RESULT");
                Toast.makeText(this, ""+scanvalue, Toast.LENGTH_SHORT).show();

//                IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//                if (scanningResult != null) {
//                    String scanContent = scanningResult.getContents();
//                    String scanFormat = scanningResult.getFormatName();
//                    // process received data
//                    processScannedData(scanContent);
//                }else{
//                    Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
//                    toast.show();
//                }

            }

        }
        if(scanvalue.equalsIgnoreCase(FPOcode)){
            istime.setEnabled(true);
            ictime.setEnabled(true);
            pstime.setEnabled(true);
            pctime.setEnabled(true);
            scanval.setText(""+scanvalue);
            qrcode.setEnabled(false);
        }else{
            scanvalue = "";
            alertdialog("Invalid FPO","Info","0");
        }

    }

    private void savegpsapicall(){
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Saving ...");
        pdialog.show();
        final JSONObject header = new JSONObject(),context = new JSONObject(),
                document = new JSONObject(),gpsobj = new JSONObject();
        try{
            header.put("in_usergps_id",gpsrowid);
            header.put("in_qrvalue",scanvalue);
            header.put("in_latitude",lat);
            header.put("in_longitude",lng);
            header.put("in_date",formatDate(dateofvisit.getText().toString(),"dd-MM-yyyy HH:mm:ss","yyyy-MM-dd HH:mm:ss"));
            header.put("in_input_start_time",istime.getText().toString());
            header.put("in_input_close_time",ictime.getText().toString());
            header.put("in_pa_start_time",pstime.getText().toString());
            header.put("in_pa_close_time",pctime.getText().toString());
            header.put("in_usercode",sharedpreferences.getString("uc",""));
            header.put("in_mode_flag",modeflag );
            context.put("orgnId","flexi");
            context.put("locnId", "chennai");
            context.put("userId", "admin");
            context.put("localeId", "en_US");
            context.put("instance", Pojokyc.instance);

            context.put("header",header);

            document.put("context",context);

            gpsobj.put("document",document);
        }catch (Exception er){
            Log.e("error",er.toString());
        }
        Log.e("Jsonbody",gpsobj.toString());
        String gpssaveurl = Pojokyc.url + "/api/FDR_Constent/gpssave";
        Log.e("url",gpssaveurl);
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
                 alertdialog("GPS Saved Successfully" ,"Success","1");
                    db.insertgps(scanvalue,lat+"/"+lng,formatDate(dateofvisit.getText().toString(),"dd-MM-yyyy","yyyy-MM-dd"),istime.getText().toString(),ictime.getText().toString(),pstime.getText().toString(),pctime.getText().toString(),"1",sharedpreferences.getString("uc",""), String.valueOf(gpsid));

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pdialog.dismiss();
                Log.e("response",error.toString());
                alertdialog(error.toString(),"Info","0");
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
        VolleySingleton.getInstance(GPSActivity.this).addToRequestQueue(gps_save_request);
       // Toast.makeText(GPSActivity.this, "Success", Toast.LENGTH_SHORT).show();
    }

    public static String formatDate (String date, String initDateFormat, String endDateFormat)  {
        String parsedDate = "";
        try{
            Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
            parsedDate = formatter.format(initDate);

        }catch (Exception er){
            Log.e("error",er.toString());
            parsedDate = date; //incase of any exception in parse return same input value
        }
        return parsedDate;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void alertdialog(String message, String title, final String flag){
        final AlertDialog alertDialog = new AlertDialog.Builder(GPSActivity.this)
//set icon
                .setIcon(android.R.drawable.ic_menu_save)
//set title
                .setTitle(title+"!")
//set message
                .setMessage(message+" !")
//set positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what would happen when positive button is clicked
                                // finish();
                               if(flag.equalsIgnoreCase("1")){
                                   finish();
                               }
                            }
                        }
//set negative button

                )
                .setCancelable(false)
                .show();
    }
    public void loaddata(){
        Cursor cr  = dbs.rawQuery("Select * from gpsactivity where " +
                "userid = '"+sharedpreferences.getString("uc","")+"' " +
                "and cdate = '"+getcurentdate()+"' ",null);
        try{
            if(cr.getCount() > 0){
                if(cr.moveToFirst()){
                    scanval.setText(cr.getString(cr.getColumnIndexOrThrow("qrvalue")));
                    scanvalue = cr.getString(cr.getColumnIndexOrThrow("qrvalue"));
                    if(!scanvalue.equalsIgnoreCase("")){
                        istime.setEnabled(true);
                        ictime.setEnabled(false);
                        pstime.setEnabled(true);
                        pctime.setEnabled(false);
                        qrcode.setEnabled(false);
                    }
                    modeflag = "U";
                    istime.setText(cr.getString(cr.getColumnIndexOrThrow("istime")));

                    ictime.setText(cr.getString(cr.getColumnIndexOrThrow("ictime")));
                    pstime.setText(cr.getString(cr.getColumnIndexOrThrow("pstime")));

                    pctime.setText(cr.getString(cr.getColumnIndexOrThrow("pctime")));
                    if(!istime.getText().toString().isEmpty())
                    {
                        istime.setEnabled(false);
                        ictime.setEnabled(true);
                    }
                    if(!ictime.getText().toString().isEmpty())
                    {
                        ictime.setEnabled(false);
                    }
                    if(!pstime.getText().toString().isEmpty())
                    {
                        pstime.setEnabled(false);
                        pctime.setEnabled(true);
                    }
                    if(!pctime.getText().toString().isEmpty())
                    {
                        pctime.setEnabled(false);
                    }
                    if(!istime.getText().toString().isEmpty()&&!ictime.getText().toString().isEmpty()&&!pstime.getText().toString().isEmpty()&&!pctime.getText().toString().isEmpty())
                    {
                        save.setEnabled(false);
                    }

                    gpsrowid = Integer.parseInt(cr.getString(cr.getColumnIndexOrThrow("rowid")));
                    try{
                        String latlng [] = cr.getString(cr.getColumnIndexOrThrow("latlng")).split("/");
                        lat = latlng[0];
                        lng = latlng[1];
                    }catch (Exception er){
                        Log.e("error",er.toString());
                    }

                }
                iudflag = "U";
            }
        }catch (Exception er){
            Log.e("error",er.toString());
        }finally {
            cr.close();
        }

    }
    public static String getcurentdate(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = df.format(c);
        return formattedDate;
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}