package cdfi.fintantra.meity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Loginicd extends AppCompatActivity {
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {

            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
    };
    Button login;
    DBHelper db;
    RequestQueue queue;
    EditText username,password,edip;
    TextView copyright;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    String bst = "0";
    ToggleButton tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_loginicd);
        getSupportActionBar().hide();
        setTitle("Input Center Digitizer");
        tb = (ToggleButton)findViewById(R.id.tb);
        db =new  DBHelper(this);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Save the state here
                if(isChecked)
                {
                    Toast.makeText(Loginicd.this, "Online", Toast.LENGTH_SHORT).show();
                    bst = "1";
                }
                else
                {
                    Toast.makeText(Loginicd.this, "Offline", Toast.LENGTH_SHORT).show();
                    bst = "0";
                }
            }
        });
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        edip = (EditText) findViewById(R.id.edip);
        queue = Volley.newRequestQueue(this);
        login = (Button)findViewById(R.id.login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        copyright = findViewById(R.id.copyright);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        copyright.setText("Copyright Fintantra "+String.valueOf(year));
        fcheck();
        edip.addTextChangedListener(new TextWatcher() {
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
               // Pojokyc.icdurl = "http://"+edip.getText().toString()+":9006/";
                // TODO Auto-generated method stub
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(Loginicd.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(Loginicd.this, "Please Enter Password", Toast.LENGTH_SHORT).show();

                }
                else
                {

                    //ttp://169.38.77.190:101/api/Mobile_FDR_Login/FdrLogin?org=flexi&locn=chennai&user=admin&lang=en_US&instance=upisha&In_user_code=vino&In_user_pwd=123
                    final SQLiteDatabase dbs = db.getWritableDatabase();
                    dbs.execSQL("DELETE FROM icdlist");
                    final String url = Pojokyc.icdurl+"api/ICDMOBLogin/icdmob_user_validation?org=flexi6&locn="+Pojokyc.instance+"&user=admin&lang=en_US&In_user_code="+username.getText().toString()+"&In_user_pwd="+password.getText().toString()+"&version_number="+Pojokyc.ver;
                    Log.e("URL",""+url);

// prepare the Request

                    JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>()
                            {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // display response
                                    Log.e("Response", response.toString());

                                    JSONObject sys  = null;
                                    try {
                                        sys = response.getJSONObject("context");
                                        JSONObject obj = sys.getJSONObject("Header");
                                        String un = obj.getString("In_user_name");
                                        String uc = obj.getString("In_user_code");
                                        String rc = obj.getString("In_role_code");
                                        String rn = obj.getString("In_role_name");
                                        String oc = obj.getString("In_orgn_code");
                                        String lo = obj.getString("In_location");
                                        String cf = obj.getString("config");
                                        // String oc1[] = obj.getString("in_orgn_code").split("/");
                                        JSONArray cast = sys.getJSONArray("Detail");
                                        for (int i = 0; i < cast.length(); i++) {


                                            JSONObject actor = cast.getJSONObject(i);


                                            String n1 = actor.getString("In_orgnlvl_code");
                                            String n2 = actor.getString("In_orgnlvl_name");

                                            Log.e("Table", "" + n1);

                                            db.inserticdlist(n1, n2);


                                        }
                                        //  String rs = obj.getString("in_Reponse");
                                        SharedPreferences.Editor editor = sharedpreferences.edit();

                                        editor.putString("un", un);
                                        editor.putString("rc", rc);
                                        editor.putString("oc", oc);
                                        editor.putString("rn", rn);
                                        editor.putString("uc", uc);
                                        editor.putString("lo", lo);
                                        editor.putString("oc1", oc);
                                        editor.putString("login", "1");
                                        editor.putString("off","1");
                                        editor.putString("cf",cf);
                                        editor.putString("username",username.getText().toString());
                                        editor.commit();

                                        if(rc.equalsIgnoreCase(""))
                                        {
                                            showErrorDialog(obj.getString("In_Reponse"));
                                        }

                                        else
                                        {
                                            Toast.makeText(Loginicd.this, "Welcome "+rn , Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(),Dashboard.class);
                                            startActivity(i);
                                            overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                                        }

                                    } catch (JSONException e) {
                                        FirebaseApp.initializeApp(Loginicd.this);
                                        FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                        Long tsLong = System.currentTimeMillis()/1000;
                                        String ts = tsLong.toString();
                                        DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                        Log.e("Valuecheck",""+mRef.getRef());
                                        mRef.child("name").setValue("LOGIN");
                                        mRef.child("date").setValue(date);
                                        mRef.child("Error").setValue(response.toString());
                                        mRef.child("Activity").setValue("LOGINICD");
                                        Toast.makeText(Loginicd.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Loginicd.this, "Error:"+error.getMessage(), Toast.LENGTH_LONG).show();

                                    Log.d("Error.Response", String.valueOf(error));
                                }
                            }
                    );

// add it to the RequestQueue
                    getRequest.setRetryPolicy(new DefaultRetryPolicy(
                            90000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    VolleySingleton.getInstance(Loginicd.this).addToRequestQueue(getRequest);

                }



            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity(); // or finish();
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
    private void showErrorDialog(String s) {
        new AlertDialog.Builder(this)
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
    public void fcheck()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        if (sharedpreferences.getString("LAST_LAUNCH_DATEICD","nodate").contains(currentDate)){
            // Date matches. User has already Launched the app once today. So do nothing.
            Log.e("LAUNCHFIST","NO");
        }
        else
        {
            Log.e("LAUNCHFIST","YES");
            // Display dialog text here......
            // Do all other actions for first time launch in the day...

            // Set the last Launched date to today.
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("LAST_LAUNCH_DATEICD", currentDate);
            editor.putString("dateforsyncicd", ".");
            editor.commit();
        }
    }
}