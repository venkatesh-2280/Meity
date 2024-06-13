package cdfi.fintantra.meity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberMapping extends AppCompatActivity {
    TextView title;
    int memcount =0;
    int enterflag = 0;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    List<Pojofar> pojofarlist;
    RecyclerView membermap;
    MyRecyclerViewAdapterm adapterm;
    DBHelper db;
    SQLiteDatabase dbs;
    AutoCompleteTextView elc;
    ArrayList<String> arrayn;
    String far_name = "";
    Button clear;
    String orgncode = "";
    JSONObject jsonParam;
    Button mapmember;
    String farmercode = "";
    String fpoorgncode = "";
    ArrayList<String> memid = new ArrayList<>();
    Boolean flag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_mapping);
        getSupportActionBar().hide();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        title = findViewById(R.id.title);
        elc = findViewById(R.id.elc);
        clear = findViewById(R.id.clear);
        mapmember = findViewById(R.id.mapmember);
        title.setText(""+sharedpreferences.getString("rn",""));
        db =new DBHelper(this);
        dbs = db.getWritableDatabase();
        pojofarlist = new ArrayList<>();
        arrayn = new ArrayList<>();
        arrayn.clear();
        membermap = findViewById(R.id.itm);
        membermap.setLayoutManager(new LinearLayoutManager(MemberMapping.this));
        adapterm = new MyRecyclerViewAdapterm(MemberMapping.this, pojofarlist);
       farmer_searchlist();
        membermap.setAdapter(adapterm);
        orgncode = sharedpreferences.getString("oc1", "");
        Cursor cr1;
      //      if(Pojokyc.mapfcode.equalsIgnoreCase("")) {
                cr1 = dbs.rawQuery("select * from farlist where fpoorgn_code <> '"+sharedpreferences.getString("oc1","")+"' and fc NOT IN (select fc from farlist where fpoorgn_code = '"+sharedpreferences.getString("oc1","")+"' ) ;", null);
       //     }
         /*   else
            {
                Log.e("Hello", "Hello World");
                cr1 = dbs.rawQuery("select * from farlist where fc='"+Pojokyc.mapfcode+"'", null);
                Log.e("Hello", ""+cr1.getCount());
            }*/
            if(cr1.getCount()>0){
                if(cr1.moveToFirst()){
                    do{
                        if(cr1.getString(16).equalsIgnoreCase(sharedpreferences.getString("lo",""))) {
                            if (arrayn.contains(cr1.getString(3))) {

                            } else {
                                arrayn.add(cr1.getString(3));
                            }
                        }
                    }while (cr1.moveToNext());
                }
            }
            ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(MemberMapping.this,
                    R.layout.spinnertext, arrayn);
            elc.setAdapter(adapterlist2n);
            elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = arrayn.indexOf(elc.getText().toString());
                    if(pos >= 0){
                        far_name = arrayn.get(pos);
                    }
                    farmernamesearch();
                    membermap.setAdapter(adapterm);
                }
            });

            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    elc.setText("");
                    farmer_searchlist();
                    membermap.setAdapter(adapterm);
                }
            });

            mapmember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("MEMIDSIZE",""+memid.size());
                    if(memid.size() == 0){
                        final AlertDialog alertDialog = new AlertDialog.Builder(MemberMapping.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("INFO!")
//set message
                                .setMessage("Please select a member")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //set what would happen when positive button is clicked


                                            }
                                        }
//set negative button

                                )
                                .setCancelable(false)
                                .show();
                    }else{

                        if(isNetworkAvailable()) {
                            for (int i = 0 ; i<memid.size();i++) {
                                membermap(memid.get(i));
                            }
                        }
                        else
                        {
                            for (int i = 0 ; i<memid.size();i++) {
                                db.membermapping("",memid.get(i),"","0");
                            }
                            final AlertDialog alertDialog = new AlertDialog.Builder(MemberMapping.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("INFO!")
//set message
                                    .setMessage("Member Mapped Successfully....")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                   // finish();
                                                    Intent intent = new Intent(MemberMapping.this,Landpage.class);
                                                    startActivity(intent);

                                                }
                                            }
//set negative button

                                    )
                                    .setCancelable(false)
                                    .show();
                        }
                    }
                }
            });



    }

    private class MyRecyclerViewAdapterm extends RecyclerView.Adapter<MyRecyclerViewAdapterm.Viewholder>{

        private List<Pojofar> mData;
        private LayoutInflater mInflater;

        public MyRecyclerViewAdapterm(Context context,List<Pojofar> data) {

            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        @NonNull
        @Override
        public MyRecyclerViewAdapterm.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.memmaplist, parent, false);
            return new MyRecyclerViewAdapterm.Viewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyRecyclerViewAdapterm.Viewholder holder, int position) {
            final Pojofar pojofar = mData.get(position);
            holder.name.setText(pojofar.getFn());
            holder.ph.setText(pojofar.getVi());
            holder.lcn.setText(pojofar.getGp());
            holder.ty.setText(pojofar.getTa());
            holder.t5.setText(pojofar.getMobilenumber());
            holder.tf2.setText(pojofar.getTf());

            holder.chkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(memid.contains(pojofar.getFc()))
                    {
                        Log.e("MEMID",""+pojofar.getFc());
                        memid.remove(pojofar.getFc());


                    }
                    else
                    {
                        Log.e("MEMID",""+pojofar.getFc());
                        memid.add(pojofar.getFc());
                    }
                }
            });


        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class Viewholder extends RecyclerView.ViewHolder {
            TextView myTextView,trate,tamt,tnamt,tdis,tqty,name,ph,lcn,ty,t5,tf2;
            LinearLayout llist;
            CheckBox chkbox;
            public Viewholder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.fn);
                ph = itemView.findViewById(R.id.vi);
                lcn = itemView.findViewById(R.id.gp);
                ty = itemView.findViewById(R.id.ta);
                t5 = itemView.findViewById(R.id.di);
                tf2 = itemView.findViewById(R.id.tf2);
                llist = itemView.findViewById(R.id.llist);
                chkbox = itemView.findViewById(R.id.chkbox);
            }
        }
    }

    private void farmernamesearch(){
        orgncode = sharedpreferences.getString("oc1", "");
        Cursor cursor = dbs.rawQuery("select * from farlist where fn='"+far_name+"' and fpoorgn_code <> '"+sharedpreferences.getString("oc1","")+"' and fc NOT IN (select fc from farlist where fpoorgn_code = '"+sharedpreferences.getString("oc1","")+"' ) ;", null);
        pojofarlist.clear();
        if(cursor.getCount() > 0){
            if(cursor.moveToFirst()){
                do{
                    Pojofar pojofar = new Pojofar();
                    pojofar.setId(cursor.getString(cursor.getColumnIndexOrThrow("fid")));
                    pojofar.setFc(cursor.getString(cursor.getColumnIndexOrThrow("fc")));
                    pojofar.setFn(cursor.getString(cursor.getColumnIndexOrThrow("fn")));
                    pojofar.setVi(cursor.getString(cursor.getColumnIndexOrThrow("vi")));
                    pojofar.setGp(cursor.getString(cursor.getColumnIndexOrThrow("gp")));
                    pojofar.setTa(cursor.getString(cursor.getColumnIndexOrThrow("ta")));
                    pojofar.setDi(cursor.getString(cursor.getColumnIndexOrThrow("di")));
                    pojofar.setAid(cursor.getString(cursor.getColumnIndexOrThrow("id")));
                    pojofar.setTf(cursor.getString(cursor.getColumnIndexOrThrow("sn")));
                    pojofar.setMobilenumber(cursor.getString(cursor.getColumnIndexOrThrow("v2")));
                    pojofarlist.add(pojofar);
                }while (cursor.moveToNext());
            }
        }
    }

    private void farmer_searchlist(){
        orgncode = sharedpreferences.getString("oc1", "");
        Cursor cursor;
        if(Pojokyc.mapfcode.equalsIgnoreCase("")){
            enterflag = 0;
            //cursor = dbs.rawQuery("select distinct * from farlist where fpoorgn_code <> '"+orgncode+"'", null);
            cursor = dbs.rawQuery("select * from farlist where fpoorgn_code <> '"+sharedpreferences.getString("oc1","")+"' and fc NOT IN (select fc from farlist where fpoorgn_code = '"+sharedpreferences.getString("oc1","")+"' ) ;", null);
        }else {
            enterflag = 1;
         //   Log.e("Hello", "Hello Wotrld");
            cursor = dbs.rawQuery("select * from farlist where fc='"+Pojokyc.mapfcode+"'", null);
           //Log.e("Hello", ""+cursor.getCount());
        }
        pojofarlist.clear();
        if(cursor.getCount() > 0){
            if(cursor.moveToFirst()){
                do{


                       Pojofar pojofar = new Pojofar();
                       pojofar.setId(cursor.getString(cursor.getColumnIndexOrThrow("fid")));
                       pojofar.setFc(cursor.getString(cursor.getColumnIndexOrThrow("fc")));
                       pojofar.setFn(cursor.getString(cursor.getColumnIndexOrThrow("fn")));
                       pojofar.setVi(cursor.getString(cursor.getColumnIndexOrThrow("vi")));
                       pojofar.setGp(cursor.getString(cursor.getColumnIndexOrThrow("gp")));
                       pojofar.setTa(cursor.getString(cursor.getColumnIndexOrThrow("ta")));
                       pojofar.setDi(cursor.getString(cursor.getColumnIndexOrThrow("di")));
                       pojofar.setAid(cursor.getString(cursor.getColumnIndexOrThrow("id")));
                       pojofar.setTf(cursor.getString(cursor.getColumnIndexOrThrow("sn")));
                       pojofar.setFpoorgncode(cursor.getString(cursor.getColumnIndexOrThrow("fpoorgn_code")));
                       pojofar.setMobilenumber(cursor.getString(cursor.getColumnIndexOrThrow("v2")));
                       pojofarlist.add(pojofar);

                }while (cursor.moveToNext());
            }
        }
        else
        {
            if(enterflag == 1)
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MemberMapping.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("INFO!")
//set message
                        .setMessage("Farmer Already Belongs To This FPO")
//set positive button
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //set what would happen when positive button is clicked
                                        Intent intent = new Intent(MemberMapping.this,Landpage.class);
                                        startActivity(intent);

                                    }
                                }
//set negative button

                        )
                        .setCancelable(false)
                        .show();
            }

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void membermapoffline(){

    }

    private void membermap(final String fcode){
            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            try {
                jsonParam.put("document",userd);
                JSONObject user = new JSONObject();
                user.put("orgnId", sharedpreferences.getString("oc1",""));
                user.put("locnId", "up");
                user.put("userId", "en_US");
                user.put("localeId", "admin");
                userd.put("context",user);
                JSONObject user2 = new JSONObject();
                user2.put("In_fpoorgn_code",sharedpreferences.getString("oc1",""));
                user.put("Header",user2);
                JSONArray user4 = new JSONArray();
                JSONObject user3 = new JSONObject();
                user3.put("In_fpomember_rowid", 0);
                user3.put("In_fpomember_code", "0");
                user3.put("In_farmer_code", fcode);
                user3.put("In_status_code", "A");
                user3.put("In_row_timestamp", "");
                user3.put("In_mode_flag", "I");
                user4.put(user3);
                user.put("Map",user4);
                Log.e("farmercode", fcode);
                Log.e("OUTPUT", jsonParam.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            final String url = Pojokyc.icdurl+"api/FISFarmermapping/newFPOFarmerMap";
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonParam,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("responses", "" + response);
                            try {
                                memcount++;
                                JSONObject obj = response.getJSONObject("context");
                                JSONObject obj2 = obj.getJSONObject("header");
                                JSONObject obj3 = response.getJSONObject("applicationException");
                               if(memcount == memid.size()) {
                                   final AlertDialog alertDialog = new AlertDialog.Builder(MemberMapping.this)
//set icon
                                           .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                           .setTitle("INFO!")
//set message
                                           .setMessage("" + obj3.getString("errorDescription"))
//set positive button
                                           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                       @Override
                                                       public void onClick(DialogInterface dialogInterface, int i) {
                                                           //set what would happen when positive button is clicked
                                                           Intent intent = new Intent(MemberMapping.this,Landpage.class);
                                                           startActivity(intent);

                                                       }
                                                   }
//set negative button

                                           )
                                           .setCancelable(false)
                                           .show();
                               }
                                if(obj3.getString("errorNumber").equalsIgnoreCase("Success"))
                                {
                                    dbs.execSQL("update farlist set fpoorgn_code='"+obj2.getString("in_fpoorgn_code")+"' where fc='"+fcode+"'");
                                    //Pojokyc.mapfcode = "";
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MemberMapping.this,Landpage.class);
        startActivity(intent);
        finish();
    }
}