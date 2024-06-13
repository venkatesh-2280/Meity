package cdfi.fintantra.meity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Issueconfirm extends AppCompatActivity {

    AutoCompleteTextView einw,eitem,erstock,eastock,eremarks;
    Button bsave;
    EditText euom;
    String org,pcode,pname,rqty,iid;
    JSONObject jsonParam;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    DBHelper mydb;
    ProgressDialog pdialog;
    ArrayList<String> array1 = new ArrayList<>();
    String [] grns ={};
    ArrayList<PojoGrn> grnarraylist = new ArrayList<>();
    GrnListAdapter grnListAdapter;
    RecyclerView grnrecyclerView;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_issueconfirm);
        getSupportActionBar().hide();

        einw = (AutoCompleteTextView)findViewById(R.id.einw);
        eitem = (AutoCompleteTextView)findViewById(R.id.eitem);
        erstock = (AutoCompleteTextView)findViewById(R.id.erstock);
        eastock = (AutoCompleteTextView)findViewById(R.id.eastock);
        eremarks = (AutoCompleteTextView)findViewById(R.id.eremarks);
        mydb = new DBHelper(this);
        pdialog = new ProgressDialog(this);
        bsave = (Button)findViewById(R.id.bsave);
        euom = (EditText) findViewById(R.id.euom);
        pdialog = new ProgressDialog(this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        final SQLiteDatabase dbs = mydb.getWritableDatabase();
        String selectQueryicd = "SELECT  * FROM inw WHERE v5 = 0";

        Cursor cursor2ic = dbs.rawQuery(selectQueryicd, null);
        if(cursor2ic.moveToFirst())
        {
            do
            {

                if(cursor2ic.getString(1).equalsIgnoreCase(sharedpreferences.getString("oc","")))
                {

                }
                else
                {
                    array1.add(cursor2ic.getString(1));

                }



            }while(cursor2ic.moveToNext());

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (Issueconfirm.this, R.layout.spinnertext3, array1);
        //Getting the instance of AutoCompleteTextView

        einw.setThreshold(0);//will start working from first character
       // einw.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        einw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   /* einw.showDropDown();
                    einw.requestFocus();*/
                inwardnumbersearchpopupload();
            }
        });

        einw.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

             /*   //Toast.makeText(Stockinwardnew.this, ""+array1.get(position), Toast.LENGTH_SHORT).show();
                Cursor cursor2 = dbs.query("inw", new String[]{"v1",
                                "v2","v3","v4","id"}, "v1" + "=?",
                        new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);

                if (cursor2.moveToFirst()) {
                    do {

                        pcode = cursor2.getString(1);
                        pname = cursor2.getString(2);
                        eitem.setText(pname);
                        erstock.setText(""+cursor2.getString(3));

                        Cursor cursor22 = dbs.query("product", new String[]{"In_product_code",
                                        "In_base_price","id","In_current_qty","In_ic_code","In_uomtype_desc"}, "In_product_code" + "=?",
                                new String[]{pcode}, null, null, null, null);

                        if (cursor22.moveToFirst()) {



                                    euom.setText("" + cursor22.getString(5));



                                //Toast.makeText(Stockinwardnew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

                                // erate.setText("" + d);





                        }

                        iid = cursor2.getString(4).toString();


                        //Toast.makeText(Stockinwardnew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

                        // erate.setText("" + d);


                    }
                    while (cursor2.moveToNext());


                }



*/
            }
        });

        eastock.addTextChangedListener(new TextWatcher() {
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

                if(s.length()>0)
                {
                    try {


                        double x = Double.parseDouble(s.toString());
                        double y = Double.parseDouble(erstock.getText().toString());
                        if(x>y)
                        {

                            eastock.setText("");
                            final AlertDialog alertDialog = new AlertDialog.Builder(Issueconfirm.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Invalid Stock!")
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
                        }


                    }
                    catch (Exception e)
                    {

                    }
                }

                // TODO Auto-generated method stub
            }
        });

        bsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(einw.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Issueconfirm.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Empty Inward No!")
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
                }
                else  if(eitem.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Issueconfirm.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Empty Item!")
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
                }
                else  if(erstock.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Issueconfirm.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Empty Received Stock!")
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
                }
                else  if(eastock.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Issueconfirm.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Empty Accepted Stock!")
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
                }
                else  if(eremarks.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Issueconfirm.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Empty Remarks!")
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
                }
                else
                {
                    if(isNetworkAvailable()) {
                        save();
                    }
                    else
                    {

                        mydb.inserticonfirm(einw.getText().toString(),pcode,eastock.getText().toString(),eremarks.getText().toString(),"0");
                        einw.setText("");
                        eitem.setText("");
                        erstock.setText("");
                        eastock.setText("");
                        eremarks.setText("");
                        final SQLiteDatabase dbs = mydb.getWritableDatabase();
                        dbs.execSQL("UPDATE inw SET v5 = 1 WHERE id = "+iid);
                        final AlertDialog alertDialog = new AlertDialog.Builder(Issueconfirm.this)
//set icon
                                .setIcon(android.R.drawable.ic_menu_save)
//set title
                                .setTitle("Success!")
//set message
                                .setMessage("Stock Confirmed!")
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
                    }
                }

            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void save()
    {


        try {



            DecimalFormat amountFormate  = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(cc);
            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", sharedpreferences.getString("oc",""));
            user.put("locnId", "up");
            user.put("userId", sharedpreferences.getString("uc",""));
            user.put("localeId", "en_US");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("IOU_inward_rowid","0");
            user2.put("In_orgn_code",sharedpreferences.getString("oc",""));
            user2.put("In_ic_code",sharedpreferences.getString("oc",""));
            user2.put("In_inward_code","");

            user2.put("In_grn_no",einw.getText().toString());
            user2.put("In_grn_date",formattedDate);
            user2.put("In_supplier_name","");
            user2.put("In_supplier_location","");
            user2.put("In_from_state","");
            user2.put("In_Remarks",eremarks.getText().toString());
            user2.put("In_status_code","A");
            user2.put("In_process_status","QCD_INWARD_CONFIRMISSUE");
            user2.put("In_row_timestamp",formattedDate);
            user2.put("In_mode_flag","U");
            user2.put("In_bill_image","");
            user2.put("In_transport_amount","0");
            user2.put("In_others","0");

            user2.put("In_loading_unloading_cost","0");
            user2.put("In_local_transport_cost","0");
            user2.put("In_local_loading_unloading_cost","0");

            JSONArray notebookUsers2 = new JSONArray();
            user.put("Header",user2);
            JSONObject user4 = new JSONObject();
            user4.put("In_inwarddtl_rowid",0);
            user4.put("In_inward_code","");
            user4.put("In_grn_no","0");
            user4.put("In_product_catg_code","");
            user4.put("In_product_subcatg_code","");
            user4.put("In_product_code",pcode);
            user4.put("In_uomtype_code","");
            user4.put("In_batch_no","");
            user4.put("In_received_qty",eastock.getText().toString());
            user4.put("In_product_amount","0");
            user4.put("In_tax_amount","0");
            user4.put("In_transport_amount",0);
            user4.put("In_discount","0");
            user4.put("In_net_amount","0");
            user4.put("In_status_code","A");
            user4.put("In_mode_flag","U");
            JSONArray notebookUserssno = new JSONArray();
            JSONObject usersno = new JSONObject();
            usersno.put("In_inwardslno_rowid","0");
            usersno.put("In_inwarddtl_rowid",0);
            usersno.put("In_slno","");
            usersno.put("In_no_of_bags","");
            usersno.put("In_grn_no","");
            usersno.put("In_product_catg_code","");
            usersno.put("In_product_subcatg_code","");
            usersno.put("In_product_code","");
            usersno.put("In_status_code","");
            usersno.put("In_mode_flag","U");
            notebookUserssno.put(usersno);

            user4.put("Slnoinfo",notebookUserssno);
            notebookUsers2.put(user4);




            user.put("Detail",notebookUsers2);


            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
        }


        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();


        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.icdurl+"api/ICDMOBInward/newSaveStockInward",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("Header");

                            String inrid = obj2.getString("IOU_inward_rowid");
                            String inrno = obj2.getString("In_grn_no");

                            Log.e("CCCC", "Hi" + inrid);

                            if (inrid.equalsIgnoreCase("0")) {
                                pdialog.dismiss();
                                final AlertDialog alertDialog = new AlertDialog.Builder(Issueconfirm.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Error Inserting Inward !")
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
                                FirebaseApp.initializeApp(Issueconfirm.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis()/1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                Log.e("Valuecheck",""+mRef.getRef());
                                mRef.child("name").setValue("SAVEAPI");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("ISSUECONFIRM");

                            } else {
                                pdialog.dismiss();
                                final SQLiteDatabase dbs = mydb.getWritableDatabase();
                               // dbs.execSQL("delete from inw WHERE id = "+iid);

                                dbs.execSQL("UPDATE inw SET v5 = 1 WHERE id = "+iid);
                                final AlertDialog alertDialog = new AlertDialog.Builder(Issueconfirm.this)
//set icon
                                        .setIcon(android.R.drawable.ic_menu_save)
//set title
                                        .setTitle("Success!")
//set message
                                        .setMessage("Stock Conformed !" +
                                                "Inward No :"+inrno)
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        // dbs.execSQL("UPDATE invoicelist SET inweb = " + inrno + " WHERE id = " + id2);

                                                        finish();
                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                            }
                        }
                        catch (Exception e)
                        {

                        }


                    }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);

                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Issueconfirm.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Error Inserting Inward!")
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

                        FirebaseApp.initializeApp(Issueconfirm.this);
                        FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        Long tsLong = System.currentTimeMillis()/1000;
                        String ts = tsLong.toString();
                        DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                        Log.e("Valuecheck",""+mRef.getRef());
                        mRef.child("name").setValue("SAVEAPI");
                        mRef.child("date").setValue(date);
                        mRef.child("Error").setValue(error.getMessage().toString());
                        mRef.child("Activity").setValue("ISSUECONFIRM");
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
    public void inwardnumbersearchpopupload(){
        dialog = new Dialog(Issueconfirm.this);
        dialog.setContentView(R.layout.suppliersearch2);
        dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
        dialog.setTitle("Title...");
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
        grnrecyclerView = dialog.findViewById(R.id.itm);
        TextView dialogtext = dialog.findViewById(R.id.dialogtext);
        final AutoCompleteTextView elc = (AutoCompleteTextView) dialog.findViewById(R.id.elc);
        ImageView cls = dialog.findViewById(R.id.cls);
        dialogtext.setText("Inward Search");
        grnrecyclerView.setLayoutManager(new LinearLayoutManager(Issueconfirm.this));
        dialog.getWindow().setLayout(width, height);
        load_grn_list("");
        ArrayAdapter<String> adaptergrn = new ArrayAdapter<String>
                (Issueconfirm.this, R.layout.spinnertext, grns);
        elc.setAdapter(adaptergrn);
        elc.setThreshold(0);
        elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = Arrays.asList(grns).indexOf(elc.getText().toString());
                if(pos >= 0){
                   String t_grn = grns[pos];
                   load_grn_list(t_grn);
                }
            }
        });
        cls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void load_grn_list(String grnno){

        final SQLiteDatabase dbs = mydb.getWritableDatabase();
        String selectQueryicd = "SELECT  * FROM inw WHERE v5 = 0 ";
        if(!grnno.equalsIgnoreCase("") && grnno != null){
            selectQueryicd += " and v1 = '"+grnno+"' ";
        }
        Log.e("qry",selectQueryicd);
        Cursor cursor2ic = dbs.rawQuery(selectQueryicd, null);
        try{
            grns = new String[cursor2ic.getCount()];
            if(cursor2ic.moveToFirst())
            {

                for(int i = 0; i < cursor2ic.getCount(); i++){
                    PojoGrn modelgrn = new PojoGrn();
                    if(cursor2ic.getString(1).equalsIgnoreCase(sharedpreferences.getString("oc","")))
                    {

                    }else {
                        grns[i] = cursor2ic.getString(1);
                        Log.e("grns",grns[i]);
                        modelgrn.setGrnno(cursor2ic.getString(cursor2ic.getColumnIndexOrThrow("v1")));
                        modelgrn.setProduct(cursor2ic.getString(cursor2ic.getColumnIndexOrThrow("v3")));
                        modelgrn.setProductcat(cursor2ic.getString(cursor2ic.getColumnIndexOrThrow("v2")));
                        modelgrn.setQty(cursor2ic.getString(cursor2ic.getColumnIndexOrThrow("v4")));
                        modelgrn.setId(cursor2ic.getString(cursor2ic.getColumnIndexOrThrow("id")));
                        grnarraylist.add(modelgrn);
                    }
                    cursor2ic.moveToNext();
                }
            }
        }catch (Exception er){
            Log.e("error",er.toString());
        }finally {
            cursor2ic.close();
            if(grnarraylist.size() >= 0){
                grnrecyclerView.setLayoutManager(new LinearLayoutManager(Issueconfirm.this));
                grnListAdapter = new GrnListAdapter(Issueconfirm.this, grnarraylist);
                grnrecyclerView.setAdapter(grnListAdapter);
            }
        }
    }

    public class GrnListAdapter extends RecyclerView.Adapter<GrnListAdapter.ViewHolder>{
        private List<PojoGrn> mData;
        private LayoutInflater mInflater;
        // data is passed into the constructor
        GrnListAdapter(Context context, List<PojoGrn> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }
        @NonNull
        @Override
        public GrnListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.grn_list, parent, false);
            return new GrnListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final GrnListAdapter.ViewHolder holder, int position) {
            final PojoGrn modelgrn = mData.get(position);
            holder.tgrn.setText(modelgrn.getGrnno());
            holder.tpcat.setText(modelgrn.getProductcat());
            holder.tp.setText(modelgrn.getProduct());
            holder.tqty.setText(modelgrn.getQty());

            holder.llist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.llist.setBackgroundResource(R.drawable.rbutton2);
                    einw.setText(modelgrn.getGrnno());
                    final SQLiteDatabase dbs = mydb.getWritableDatabase();
                    //Toast.makeText(Stockinwardnew.this, ""+array1.get(position), Toast.LENGTH_SHORT).show();
                    pcode = modelgrn.getProductcat();
                    pname = modelgrn.getProduct();
                    eitem.setText(pname);
                    erstock.setText(modelgrn.getQty());
                    iid = modelgrn.getId();
                    /*Cursor cursor2 = dbs.query("inw", new String[]{"v1",
                                    "v2","v3","v4","id"}, "v1" + "=?",
                            new String[]{modelgrn.getGrnno()}, null, null, null, null);*/
                    Cursor cursor22 = dbs.query("product", new String[]{"In_product_code",
                                    "In_base_price","id","In_current_qty","In_ic_code","In_uomtype_desc"}, "In_product_code" + "=?",
                            new String[]{pcode}, null, null, null, null);
                    try {
                      /*  if (cursor2.moveToFirst()) {
                            do {
                                pcode = cursor2.getString(1);
                                pname = cursor2.getString(2);
                                eitem.setText(pname);
                                erstock.setText(""+cursor2.getString(3));

                                Cursor cursor22 = dbs.query("product", new String[]{"In_product_code",
                                                "In_base_price","id","In_current_qty","In_ic_code","In_uomtype_desc"}, "In_product_code" + "=?",
                                        new String[]{pcode}, null, null, null, null);

                                if (cursor22.moveToFirst()) {

                                    euom.setText("" + cursor22.getString(5));

                                    //Toast.makeText(Stockinwardnew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

                                    // erate.setText("" + d);

                                }

                                iid = cursor2.getString(4).toString();

                                //Toast.makeText(Stockinwardnew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();

                                // erate.setText("" + d);

                            }
                            while (cursor2.moveToNext());
                        }*/
                        if (cursor22.moveToFirst()) {

                            euom.setText("" + cursor22.getString(cursor22.getColumnIndexOrThrow("In_uomtype_desc")));
                            //Toast.makeText(Stockinwardnew.this, ""+cursor2.getString(2), Toast.LENGTH_SHORT).show();
                            // erate.setText("" + d);
                        }
                      //  iid = cursor2.getString(4).toString();
                    }catch (Exception er){
                        Log.e("error",er.toString());
                    }finally {
                        cursor22.close();
                        dialog.dismiss();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder  {
            TextView t1,tgrn,t2,tpcat,t3,tp,t4,tqty;
            LinearLayout llist;

            ViewHolder(View itemView) {
                super(itemView);
                t1 = itemView.findViewById(R.id.t1);
                tgrn = itemView.findViewById(R.id.tgrn);
                t2 = itemView.findViewById(R.id.t2);
                tpcat = itemView.findViewById(R.id.tpcat);
                t3 = itemView.findViewById(R.id.t3);
                tp = itemView.findViewById(R.id.tp);
                t4 = itemView.findViewById(R.id.t4);
                tqty = itemView.findViewById(R.id.tqty);
                llist = itemView.findViewById(R.id.llist);

            }
        }
    }

}