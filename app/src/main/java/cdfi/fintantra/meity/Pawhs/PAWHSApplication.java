package cdfi.fintantra.meity.Pawhs;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class PAWHSApplication extends Application {

    public static PAWHSApplication getInstance;
    private SharedPreferences mySharedPreferences;
    private static final String MyPREFERENCES = "PawhsMyPrefs";
    private SharedPreferences.Editor editor;

    public static PAWHSApplication getGetInstance() {
        if (getInstance == null) {
            getInstance = new PAWHSApplication();
        }

        return getInstance;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mySharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    }

       public void storePreference(Context context, String key, String data) {
        try {
            mySharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            editor = mySharedPreferences.edit();
            editor.putString(key, data);
            editor.apply();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getPreferenceFromString(Context context, String key) {
        String data="";

        try {
            mySharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            data = mySharedPreferences.getString(key, null);

        } catch (Exception e) {
            return null;
        }
        return data;
    }

    public void storePermissionBoolean(Context context, String key, boolean data) {
        try {
            mySharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            editor = mySharedPreferences.edit();
            editor.putBoolean(key, data);
            editor.apply();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Boolean getPreferenceFromPermissionBoolean(Context context, String key) {
        boolean data;

        try {
            mySharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            data = mySharedPreferences.getBoolean(key, false);

        } catch (Exception e) {
            return null;
        }
        return data;
    }



    public void clearPreferenceData(Context context, String key) {
        try {
            mySharedPreferences = context.getSharedPreferences(MyPREFERENCES, 0);
            editor = mySharedPreferences.edit();
            editor.clear();
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearPreferenceParticularData(Context context, String key) {
        if (mySharedPreferences.contains(key)) {
            editor.remove(key);
            editor.apply();
        }
    }
}
