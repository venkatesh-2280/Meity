package cdfi.fintantra.meity;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class AppController extends Application implements LifecycleObserver {

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    ///////////////////////////////////////////////
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onEnterForeground() {
        Log.d("AppController", "Foreground");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
        String currentDateandTime = sdf.format(new Date());

        Date date = null;
        try {
            date = sdf.parse(currentDateandTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

       // calendar.add(Calendar.HOUR, 12);


        stopService(new Intent( this, NewService.class ));
        if(Pojokyc.estatus.equalsIgnoreCase("1")) {
            Intent in = new Intent(getApplicationContext(), SingleLogin.class);
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);

        }

        Log.e("Time hereAPP ",""+calendar.getTime());
        SharedPreferences sharedpreferences;
         String MyPREFERENCES = "MyPrefs" ;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        //if(sharedpreferences.getString("offtime","").equalsIgnoreCase(calendar.getTime().toString()))
        //if(calendar.getTime().toString().equalsIgnoreCase("Thu Aug 05 18:50:00 GMT+05:30 2021"))
        if(calendar.getTime().toString().compareTo(sharedpreferences.getString("offtime",""))>=0)
        {
            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString("offtime","");

            editor.commit();
            Intent i = new Intent(getApplicationContext(),SingleLogin.class);
       i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(i);
        }
//        Intent i = new Intent(getApplicationContext(),MainActivity.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(i);
        isAppInBackground(false);
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground() {
        Log.d("AppController", "Background");
        isAppInBackground(true);
        startService(new Intent( this, NewService.class ) );
//        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                //Do something after 10000ms
//
//                Log.d("ClearFromRecentService", "Service Distroyed");
//                final String url = Pojokyc.url+"/api/Mobile_FDR_Login/commonmobLogin?org=flexi6&locn=up&user=logout&lang=en_US&In_user_code="+sharedpreferences.getString("uc","")+"&In_user_pwd=&version_number="+Pojokyc.ver;
//                Log.e("URL",""+url);
//                HttpsTrustManager.allowAllSSL();
//                // Toast.makeText(getApplicationContext(), ""+url, Toast.LENGTH_SHORT).show();
//                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                        new com.android.volley.Response.Listener<JSONObject>()
//                        {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                // display response
//                                Log.e("Response", response.toString());
//                               // System.exit(0);
//                              Pojokyc.estatus = "1";
//
//
//                            }
//
//
//                        },
//                        new Response.ErrorListener()
//                        {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Toast.makeText(AppController.this, "Error:"+error, Toast.LENGTH_LONG).show();
//
//                                Log.d("Error.Response", String.valueOf(error));
//                            }
//                        }
//                );
//
//// add it to the RequestQueue
//                getRequest.setRetryPolicy(new DefaultRetryPolicy(
//                        900000,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                VolleySingleton.getInstance(AppController.this).addToRequestQueue(getRequest);
//
//            }
//        }, 600000);
    }
///////////////////////////////////////////////



    // Adding some callbacks for test and log
    public interface ValueChangeListener {
        void onChanged(Boolean value);
    }
    private ValueChangeListener visibilityChangeListener;
    public void setOnVisibilityChangeListener(ValueChangeListener listener) {
        this.visibilityChangeListener = listener;
    }
    private void isAppInBackground(Boolean isBackground) {
        Log.e("Hari","background");

        if (null != visibilityChangeListener) {

            visibilityChangeListener.onChanged(isBackground);
        }
    }
    private static AppController mInstance;
    public static AppController getInstance() {
        return mInstance;
    }



    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        Log.e("mainhari", "onCreate fired");
        // addObserver
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

}