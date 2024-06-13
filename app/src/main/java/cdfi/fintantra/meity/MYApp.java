package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONObject;

public class MYApp extends AppCompatActivity {

    RelativeLayout r1,r2;
    ImageView logout;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_y_app);
        getSupportActionBar().hide();
        db = new DBHelper(this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        logout = (ImageView)findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable()) {
                    RequestQueue queue = Volley.newRequestQueue(MYApp.this);
                    final String url = Pojokyc.url+"/api/Mobile_FDR_Login/FdrLogin?org=logout&locn=chennai&user=admin&lang=en_US&instance=odisha&In_user_code="+sharedpreferences.getString("username","")+"&In_user_pwd=0";

// prepare the Request

                    JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // display response
                                    Log.d("Response", response.toString());
                                    SQLiteDatabase dbs = db.getWritableDatabase();
                                    dbs.execSQL("delete from product");
                                    dbs.execSQL("delete from supplierlist");
                                    dbs.execSQL("delete from customerlist");
                                    dbs.execSQL("delete from invoicelist");
                                    dbs.execSQL("delete from inwardlist");
                                    dbs.execSQL("delete from customerlistnm");
                                    dbs.execSQL("delete from productlist");
                                    dbs.execSQL("delete from productlist2");
                                    dbs.execSQL("delete from astock");
                                    dbs.execSQL("delete from astock2");
                                    dbs.execSQL("delete from paylist");
                                    dbs.execSQL("delete from invoice");
                                    dbs.execSQL("delete from state");
                                    dbs.execSQL("delete from tab");
                                    dbs.execSQL("delete from tablist");
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.clear();
                                    editor.apply();
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
            }
        });


        r1 = (RelativeLayout)findViewById(R.id.r1);

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Dashboard.class);
                startActivity(i);
            }
        });

        r2 = (RelativeLayout)findViewById(R.id.r2);

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Dashboard2.class);
                startActivity(i);
            }
        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }}
