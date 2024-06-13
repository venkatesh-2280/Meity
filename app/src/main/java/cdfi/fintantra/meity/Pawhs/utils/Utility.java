package cdfi.fintantra.meity.Pawhs.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    private static ConnectivityManager connectivityManager;

    public static boolean checkConnectivity(Context context){
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info= connectivityManager.getActiveNetworkInfo();
        Log.i(MyConstants.TAG,"NetworkInfo: "+ info);

        if(info!=null && info.isConnected()){
            return true;
        }else{
//			Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            Log.i(MyConstants.TAG,"No network");
            return false;
        }
    }

    public static boolean isValidEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void loadFragment(FragmentManager fragmentManager, int parentView, Fragment fragment){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(parentView,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }


    public static void expand(final View v) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) v.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Expansion speed of 1dp/ms
        a.setDuration(((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density))*3);
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Collapse speed of 1dp/ms
        //  a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        a.setDuration(((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density)) * 3);
        v.startAnimation(a);
    }

    public static long[] getOfferDateAndTime(String toDate){
        //CurrentDateTime
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        long[] offerDateTime = new long[0];

        try {
            Date date1 = sdf.parse(currentDateandTime);
            Date date2 = sdf.parse(toDate);
            offerDateTime= printDifference(date1,date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return offerDateTime;
    }

    private static long[] printDifference(Date startDate, Date endDate) {
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        List<Long> longValue=new ArrayList<>();
        longValue.add(elapsedDays);
        longValue.add(elapsedHours);
        longValue.add(elapsedMinutes);
        longValue.add(elapsedSeconds);

        long[] dateAnTime=new long[longValue.size()];
        for (int i = 0; i < longValue.size(); i++){
            dateAnTime[i] = longValue.get(i);
        }

        return dateAnTime;
    }

    public static String convertDateFormat(String date){
       /* String[] items = date.split(" ");
        String convertDate=items[0];*/

        String[] itemDate = date.split("-");
        String monthName=theMonth(Integer.parseInt(itemDate[1])-1);
        String convertedDate=itemDate[2]+"-"+monthName+"-"+itemDate[0];


       //return convertDate;
       return convertedDate;

    }

    public static String convertSpecificMonthFormat(String date){
       /* String[] items = date.split(" ");
        String convertDate=items[0];*/

        String[] itemDate = date.split("-");
        String monthName=theMonth(Integer.parseInt(itemDate[1])-1);
        String convertedDate=itemDate[0]+"-"+monthName+"-"+itemDate[2];


        //return convertDate;
        return convertedDate;

    }

    public static String[] splitRange(String range){
        return range.split("to");
    }
    public static String splitTValue(String range){
        String[] items = range.split("T");
        String splitvalue=items[0];
        return splitvalue;
    }

    public static String[] splitRangeName(String range){
        return range.split(",");
    }

    public static String convertCrDateServerFormat(String date){
        SimpleDateFormat spf=new SimpleDateFormat("dd-MM-yyyy");
        Date newDate= null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf= new SimpleDateFormat("yyyy-MM-dd");
        date = spf.format(newDate);
        System.out.println(date);
        return date;
    }

    public static String theMonth(int month){
        String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return monthNames[month];
    }
    }
