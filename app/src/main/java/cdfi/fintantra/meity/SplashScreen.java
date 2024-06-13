package cdfi.fintantra.meity;

import androidx.core.app.ActivityOptionsCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;



public class SplashScreen extends Activity {
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);

    }
    /** Called when the activity is first created. */
    Thread splashTread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        StartAnimations();
    }
    private void StartAnimations() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fadein);
        anim.reset();
        RelativeLayout l=(RelativeLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        final ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
      ///  iv.startAnimation(anim);

        Button gstart =  (Button)findViewById(R.id.gstart);
        gstart.clearAnimation();
        gstart.startAnimation(anim);

        gstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SplashScreen.this,
//                        MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);
//                SplashScreen.this.finish();
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(SplashScreen.this,iv,"imageshare");

                Intent intent = new Intent(SplashScreen.this,
                        MainActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent,activityOptionsCompat.toBundle());
                //finish();

            }
        });

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 1000) {
                        sleep(100);
                        waited += 100;
                    }
                    String login = (sharedpreferences.getString("login", ""));

                    if(login.equalsIgnoreCase("1"))
                    {
                        Intent intent = new Intent(SplashScreen.this,
                                MYApp.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(SplashScreen.this,
                                Loginicd.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    }
                    //SplashScreen.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                   // SplashScreen.this.finish();
                }

            }
        };
        splashTread.start();

    }

}
