package cdfi.fintantra.meity;

import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class NewService extends Service {

    // declaring object of MediaPlayer
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override

    // execution of service will start
    // on calling this method
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("ClearFromRecentService", "Service Started");
       // Toast.makeText(this, "started", Toast.LENGTH_SHORT).show();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        // creating a media player which
        // will play the audio of Default
        // ringtone in android device
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //Do something after 10000ms

                Log.d("ClearFromRecentService", "Service Distroyed");
                final String url = Pojokyc.url+"/api/Mobile_FDR_Login/commonmobLogin?org=flexi6&locn=up&user=logout&lang=en_US&In_user_code="+sharedpreferences.getString("uc","")+"&In_user_pwd=&version_number="+Pojokyc.ver;
                Log.e("URL",""+url);
                HttpsTrustManager.allowAllSSL();
                // Toast.makeText(getApplicationContext(), ""+url, Toast.LENGTH_SHORT).show();
                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new com.android.volley.Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response) {
                                // display response
                                Log.e("Response", response.toString());
                                Pojokyc.estatus = "1";
                               // System.exit(0);
                            }


                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(NewService.this, "Error:"+error, Toast.LENGTH_LONG).show();

                                Log.d("Error.Response", String.valueOf(error));
                            }
                        }
                );

// add it to the RequestQueue
                getRequest.setRetryPolicy(new DefaultRetryPolicy(
                        900000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                VolleySingleton.getInstance(NewService.this).addToRequestQueue(getRequest);

            }
        }, 3600000);

        // returns the status
        // of the program
        return START_STICKY;
    }

    @Override

    // execution of the service will
    // stop on calling this method
    public void onDestroy() {


        // stopping the process
        super.onDestroy();
    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e("ClearFromRecentService", "END");
        //Code here

       // stopSelf();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
       // Toast.makeText(this, "started", Toast.LENGTH_SHORT).show();
        return null;
    }
}
