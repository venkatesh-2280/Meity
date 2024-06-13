package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.google.zxing.integration.android.IntentIntegrator;

import org.json.JSONArray;
import org.json.JSONException;
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

public class ProductionTab extends AppCompatActivity {

    EditText e;
    int u = 0;
    int offc;
    ImageView qrcode;
    JSONArray castfar,castmaster;
    int pdc;
    Dialog dialog;
    ArrayList<String> arraysn = new ArrayList<>();
    ArrayList<String> arraysc = new ArrayList<>();
    ArrayList<String> arraymc = new ArrayList<>();
    ArrayList<String> arraymn = new ArrayList<>();
    ArrayList<String> arraycrc = new ArrayList<>();
    ArrayList<String> arraycrn = new ArrayList<>();
    ArrayList<String> arraycnc = new ArrayList<>();
    ArrayList<String> arraycnn = new ArrayList<>();
    ArrayList<String> arraysec = new ArrayList<>();
    ArrayList<String> arraysen = new ArrayList<>();
    ArrayList<String> arrayvn = new ArrayList<>();
    ArrayList<String> arrayvc = new ArrayList<>();
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    String pdid;
    String farmername;
    String pdslno;
    Button save,view;
    JSONObject jsonParam;
    AutoCompleteTextView ac1,ac2,ac3,ac4,ac5,ac6,ac7,ac8,ac9,lrp;
    List<Pojofar> pojofarlist;
    ArrayList<String> arrayn;
    ImageView master;
    ArrayList<String> array,array2;
    ArrayList<String> tfield = new ArrayList<>();
    MyRecyclerViewAdapterf adapterf;
    private IntentIntegrator qrScan;
    SQLiteDatabase dbs;
    ProgressDialog pdialog;
    String farname;
    List<Pojokyc> listitem;
    ArrayList<String> array3 = new ArrayList<>();
    ArrayList<String> array4 = new ArrayList<>();
    ArrayList<String> array5 = new ArrayList<>();
    int faid;
    ImageView nd;
    MyRecyclerViewAdapter adapter;;
    DBHelper db;
    int pdc2=0;
    JSONObject user3,userd;
    TextView title;
    String farmercode;
    AutoCompleteTextView elc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_production_tab);
        nd = (ImageView) findViewById(R.id.nd);
        listitem = new ArrayList<>();
        pojofarlist = new ArrayList<>();
        pdialog = new ProgressDialog(this);
        getSupportActionBar().hide();
        e = (EditText) findViewById(R.id.e);
        arrayn = new ArrayList<>();
        master = (ImageView)findViewById(R.id.master);

        ac1 = (AutoCompleteTextView)findViewById(R.id.ac1);
        ac2 = (AutoCompleteTextView)findViewById(R.id.ac2);
        ac3 = (AutoCompleteTextView)findViewById(R.id.ac3);
        ac4 = (AutoCompleteTextView)findViewById(R.id.ac4);
        ac5 = (AutoCompleteTextView)findViewById(R.id.ac5);
        ac6 = (AutoCompleteTextView)findViewById(R.id.ac6);
        ac7 = (AutoCompleteTextView)findViewById(R.id.ac7);
        ac8 = (AutoCompleteTextView)findViewById(R.id.ac8);
        ac9 = (AutoCompleteTextView)findViewById(R.id.ac9);
        lrp = (AutoCompleteTextView)findViewById(R.id.lrp);
        save = (Button)findViewById(R.id.save);
        view = (Button)findViewById(R.id.view);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        lrp.setText(""+sharedpreferences.getString("un",""));
ac8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ac9.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
//                    if (s.toString().length() == 1 && s.toString().startsWith("0")) {
//                        s.clear();
//                    }
                }
                catch (Exception e)
                {

                }
            }
        });

        ac9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {


                    if (s.toString().length() == 1 && s.toString().startsWith("0")) {
                       // s.clear();
                    }else if (s.length() > 0 && s.toString().startsWith(".")){
                        s.clear();
                    }
                    else {
                        if (ac8.getText().toString().equalsIgnoreCase("")) {

                        } else {
                            int x = Integer.parseInt(ac8.getText().toString());
                            int y = Integer.parseInt(ac9.getText().toString());


                            if (y > x) {
                                ac9.setText("");
                                final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Warning !")
//set message
                                        .setMessage("Please enter value less than or equal to actual production.")
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
                }
                catch (Exception e)
                {

                }
            }

        });

        nd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(ProductionTab.this);
                dialog.setContentView(R.layout.navimenu3);
                dialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
                dialog.setTitle("Title...");
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.70);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, height);
                //dialog.getWindow().setLayout(width, height);
                // set the custom dialog components - text, image and button

                RelativeLayout  r1 = (RelativeLayout) dialog.findViewById(R.id.r1);
                RelativeLayout  r2 = (RelativeLayout) dialog.findViewById(R.id.r2);

                RelativeLayout rm = (RelativeLayout) dialog.findViewById(R.id.rm);
                RelativeLayout trs = (RelativeLayout) dialog.findViewById(R.id.trs);
                RelativeLayout rlog = (RelativeLayout) dialog.findViewById(R.id.rlog);

                rm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        save();
                        // save2();
                        save3();
                        save4();
                      //  save5();
                        save6();
                    }
                });

                rlog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbs.execSQL("delete from production");
                        finish();
                    }
                });

                trs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isNetworkAvailable()) {

                            final SQLiteDatabase dbs = db.getWritableDatabase();
                            String selectQuery5 = "SELECT  * FROM production where v13 = 0";
                            Cursor cc = dbs.rawQuery(selectQuery5, null);

                            if (cc.getCount() == 0) {
                                final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("No Data Available")
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        // finish();
                                                        //rlsno=1;
                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                            } else {
                                String selectQuery2 = "SELECT  * FROM production where v13 = 0";

                                Cursor cursor2 = dbs.rawQuery(selectQuery2, null);
                                pdc = cursor2.getCount();


                                if (cursor2.moveToFirst()) {


                                    saveproduction(cursor2.getString(1), cursor2.getString(2), cursor2.getString(3), cursor2.getString(4), cursor2.getString(5),cursor2.getString(6),cursor2.getString(7),cursor2.getString(8),cursor2.getString(9),cursor2.getString(10),cursor2.getString(11),cursor2.getString(12),cursor2.getString(0),cursor2.getString(14));


                                    // Log.e("Check",""+cursor.getString(1));


                                }
                                offc = cc.getCount();
                                Log.e("NULL", "" + cc.getCount());
                            }
                        } else {
                            final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("No Internet Available")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                    // finish();
                                                    //rlsno=1;
                                                }
                                            }
//set negative button

                                    )
                                    .show();
                        }
                    }
                });

                ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        master.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                // save2();
                save3();
                save4();
              //  save5();
                save6();
            }
        });
        qrcode = (ImageView)findViewById(R.id.qrcode);
        db = new DBHelper(this);
        dbs = db.getWritableDatabase();
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan = new IntentIntegrator(ProductionTab.this);
                qrScan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                qrScan.setPrompt("Scan Farmer Code");
                qrScan.setBeepEnabled(true);
                qrScan.setCameraId(0);
                qrScan.setOrientationLocked(false);
                qrScan.setBarcodeImageEnabled(true);


                qrScan.initiateScan();
            }
        });
//
//        e.setOnLongClickListener(new View.OnLongClickListener() {
//
//            @Override
//            public boolean onLongClick(View v) {
//                {
//
//
//                }
//                //ADD HERE ABOUT CUT COPY PASTE
//                // TODO Auto-generated method stub
//                return false;
//            }
//        });

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(ProductionTab.this);
                dialog.setContentView(R.layout.suppliersearch2);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(ProductionTab.this));
                adapterf = new MyRecyclerViewAdapterf(ProductionTab.this, pojofarlist);
                dialog.getWindow().setLayout(width, height);

                elc = (AutoCompleteTextView) dialog.findViewById(R.id.elc);
                arrayn.clear();
                String selectQuery5 = "SELECT  * FROM farlist";

                Cursor cc = dbs.rawQuery(selectQuery5, null);
                Log.e("NULL", "" + cc.getCount());
                if (cc.getCount() != 0) {
                    if (cc.moveToFirst()) {
                        do {

                            if (arrayn.contains(cc.getString(3))) {

                            } else {
                                arrayn.add(cc.getString(3));
                            }
                            // Log.e("VAL",""+cursor.getString(11));

                        } while (cc.moveToNext());
                    }
                }


                ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(ProductionTab.this,
                        R.layout.spinnertext, arrayn);

                elc.setAdapter(adapterlist2n);

                elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        pojofarlist.clear();
                        // elc.setText(""+(String) parent.getItemAtPosition(position));

                        Cursor cursor = dbs.query("farlist", new String[]{"fid", "fc", "fn", "vi", "gp", "ta", "di", "id","sn"
                                }, "fn" + "=? COLLATE NOCASE",
                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
                        if (cursor.getCount() != 0) {
                            if (cursor.moveToFirst()) {
                                do {

                                    Pojofar pojofar = new Pojofar();

                                    pojofar.setId(cursor.getString(0));
                                    pojofar.setFc(cursor.getString(1));
                                    pojofar.setFn(cursor.getString(2));
                                    pojofar.setVi(cursor.getString(3));
                                    pojofar.setGp(cursor.getString(4));
                                    pojofar.setTa(cursor.getString(5));
                                    pojofar.setDi(cursor.getString(6));
                                    pojofar.setAid(cursor.getString(7));
                                    pojofar.setTf(cursor.getString(8));



                                    pojofarlist.add(pojofar);
                                    // array2.add(cursor.getString(11));
                                    // Log.e("VAL",""+cursor.getString(11));
                                    recyclerView.setAdapter(adapterf);
                                    // Log.e("VAL",""+cursor.getString(11));

                                } while (cursor.moveToNext());
                            }


                        }

                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
                    }
                });

                ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
//                        finish();
//                        startActivity(getIntent());
                    }
                });

                dialog.show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(ProductionTab.this);
                dialog.setContentView(R.layout.tablist);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                TextView title = (TextView) dialog.findViewById(R.id.texttitle);

                dialog.getWindow().setLayout(width, height);
                String selectQuery2 = "SELECT  * FROM production";

                Cursor cursor2 = dbs.rawQuery(selectQuery2, null);

                if(cursor2.getCount()>0)
                {
                    Log.e("FFFF","we"+farmercode);
                    title.setText("Production");
                    androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ProductionTab.this));
                    adapter = new MyRecyclerViewAdapter(ProductionTab.this, listitem,35);
                    recyclerView.setAdapter(adapter);
                    //String selectQuery = "SELECT  * FROM kyc where fid = "+sharedpreferences.getString("fcode2","");
                    listitem.clear();
                    Cursor cursor = dbs.query("production", new String[]{"id","v1","v2","v3","v4","v5","v6", "v7","v8","v9","v10","v11","v12","v13"
                            }, "v12" + "=?",
                            new String[]{farmercode}, null, null, null, null);

                    // looping through all rows and adding to list
                    Log.e("NULL",""+cursor.getCount());
                    if (cursor.moveToFirst()) {
                        do {
                            Pojokyc pojokyc = new Pojokyc();

                            pojokyc.setPduid(cursor.getString(0));
                            pojokyc.setPdut1(cursor.getString(1));
                            pojokyc.setPdut2(cursor.getString(2));
                            pojokyc.setPdut3(cursor.getString(3));
                            pojokyc.setPdut4(cursor.getString(4));
                            pojokyc.setPdut5(cursor.getString(5));
                            pojokyc.setPdut6(cursor.getString(6));
                            pojokyc.setPdut7(cursor.getString(7));
                            pojokyc.setPdut8(cursor.getString(8));
                            pojokyc.setPdut9(cursor.getString(9));
                            pojokyc.setPduslno(cursor.getString(10));










                            // Log.e("Check",""+cursor.getString(1));

                            listitem.add(pojokyc);
                            // array2.add(cursor.getString(11));
                            // Log.e("VAL",""+cursor.getString(11));
                            recyclerView.setAdapter(adapter);
                        } while (cursor.moveToNext());

                    }

                    ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ac1.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty Year!")
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
                else if(ac2.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty Season!")
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
                else if(ac3.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty Crop Classification!")
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
                else if(ac4.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty Crop Name!")
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
//                else if(ac5.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
//                {
//                    final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
////set icon
//                            .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                            .setTitle("Alert!")
////set message
//                            .setMessage("Empty Crop Variety!")
////set positive button
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialogInterface, int i) {
//                                            //set what would happen when positive button is clicked
//
//                                        }
//                                    }
////set negative button
//
//                            )
//                            .show();
//                }
                else if(ac6.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty Seed Name!")
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

                else if(ac7.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty Seed Qty!")
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
                else if(ac8.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty Actual Production!")
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

                else if(ac9.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty Produce Available For Sale!")
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

                    if(u == 0)
                    {




                        ;
                        Cursor cursoraddr = dbs.query("production", new String[]{"id"
                                }, "v12" + "=?",
                                new String[]{farmercode}, null, null, null, null);
                        int x = cursoraddr.getCount();
                        x++;
                        String slno = String.valueOf(x);
                        db.insertproduction(ac1.getText().toString(),ac2.getText().toString(),ac3.getText().toString(),ac4.getText().toString(),ac5.getText().toString(),ac6.getText().toString(),farmername,ac8.getText().toString(),ac9.getText().toString(),slno, String.valueOf(faid),farmercode,"0",lrp.getText().toString());
                        String selectQuery2 = "SELECT  * FROM production where v13 = 0";

                        Cursor cursor2 = dbs.rawQuery(selectQuery2, null);
                        if (isNetworkAvailable()) {


                            pdc = cursor2.getCount();
                            if (cursor2.moveToFirst()) {


                                saveproduction(cursor2.getString(1), cursor2.getString(2), cursor2.getString(3), cursor2.getString(4), cursor2.getString(5),cursor2.getString(6),cursor2.getString(7),cursor2.getString(8),cursor2.getString(9),cursor2.getString(10),cursor2.getString(11),cursor2.getString(12),cursor2.getString(0),cursor2.getString(14));


                                // Log.e("Check",""+cursor.getString(1));


                            }
                        } else {

                            ac1.setText("");
                            ac2.setText("");
                            ac3.setText("");
                            ac4.setText("");
                            ac5.setText("");
                            ac6.setText("");
                           // ac7.setText("");
                            ac8.setText("");
                            ac9.setText("");

                            final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Productions Details Stored Locally!")
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
                    else
                    {
                        db.updateproduction(Integer.valueOf(pdid),ac1.getText().toString(),ac2.getText().toString(),ac3.getText().toString(),ac4.getText().toString(),ac5.getText().toString(),ac6.getText().toString(),farmername,ac8.getText().toString(),ac9.getText().toString(),pdslno, String.valueOf(faid),farmercode,"0",lrp.getText().toString());
                        String selectQuery2 = "SELECT  * FROM production where v13 = 0";

                        Cursor cursor2 = dbs.rawQuery(selectQuery2, null);
                        if (isNetworkAvailable()) {


                            pdc = cursor2.getCount();
                            if (cursor2.moveToFirst()) {


                                saveproduction(cursor2.getString(1), cursor2.getString(2), cursor2.getString(3), cursor2.getString(4), cursor2.getString(5),cursor2.getString(6),cursor2.getString(7),cursor2.getString(8),cursor2.getString(9),cursor2.getString(10),cursor2.getString(11),cursor2.getString(12),cursor2.getString(0),cursor2.getString(14));


                                // Log.e("Check",""+cursor.getString(1));


                            }
                        } else {

                            final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Productions Details Stored Locally!")
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
            }
        });
    }

    private void offlinesave() {

    }

    public class MyRecyclerViewAdapterf extends RecyclerView.Adapter<MyRecyclerViewAdapterf.ViewHolder> {

        private List<Pojofar> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapterf(Context context, List<Pojofar> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapterf.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.suplist5, parent, false);
            return new MyRecyclerViewAdapterf.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(final MyRecyclerViewAdapterf.ViewHolder holder, final int position) {
            final Pojofar pojofar = mData.get(position);
            holder.name.setText(pojofar.getFn());
            holder.ph.setText(pojofar.getVi());
            holder.lcn.setText(pojofar.getGp());
            holder.ty.setText(pojofar.getTa());
            holder.t5.setText(pojofar.getDi());
            holder.tf2.setText(pojofar.getTf());
            holder.fdistrict.setText(pojofar.getDi());
            // holder.llist.setBackgroundResource(R.drawable.rbutton);
            holder.llist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.llist.setBackgroundResource(R.drawable.rbutton2);

//                    bcode = pojobank.getBc();
//                    ebank.setText(pojobank.getBn());
//                    ebranch.setText(pojobank.getBrn());
//                    eifsc.setText(pojobank.getIfsc());
                     dialog.dismiss();
                    farmername = pojofar.getFn();
                    faid = Integer.parseInt(pojofar.getId());
                    e.setText(pojofar.getFn()+"//"+pojofar.getFc());
                    farmercode = pojofar.getFc();
                  //  loadFarmer(pojofar.getFc(),pojofar.getId(),pojofar.getFn());

                    loaddata();



                }
            });
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder  {
            TextView myTextView,trate,tamt,tnamt,tdis,tqty,name,ph,lcn,ty,t5,tf2,t6,fdistrict;
            ImageView del,ed;
            LinearLayout llist;

            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.fn);
                ph = itemView.findViewById(R.id.vi);
                lcn = itemView.findViewById(R.id.gp);
                ty = itemView.findViewById(R.id.ta);
                t5 = itemView.findViewById(R.id.di);
                tf2 = itemView.findViewById(R.id.tf2);
                llist = itemView.findViewById(R.id.llist);
                t6 = itemView.findViewById(R.id.t6);
                fdistrict = itemView.findViewById(R.id.fdistrict);
            }


        }

        // convenience method for getting data at click position




        // parent activity will implement this method to respond to click events

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

                dialog = new Dialog(ProductionTab.this);
                Log.e("RCODE", "" + data.getStringExtra("SCAN_RESULT"));
                String scanvalue = data.getStringExtra("SCAN_RESULT");
                if(isNetworkAvailable()) {
                    loadFarmer(scanvalue, "", "");
                }
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

    }

    public void loadFarmer(final String fc, final String faridnot, String farnamenot) {

        // dialog = new Dialog(ProductionTab.this);
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Fetching Farmer Information Please Wait.......");
        pdialog.show();
        dbs.execSQL("delete from farmerh");
        dbs.execSQL("delete from production");
        farmercode  = fc;



        try {





            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", "Flexi");
            userd.put("locnId", "Chennai");
            userd.put("userId", "admin");
            userd.put("localeId", "en_US");
            userd.put("instance", "up");

            JSONObject userd2 = new JSONObject();
            userd2.put("in_farmer_rowid", 0);
            userd2.put("in_farmer_code", fc);

            userd.put("Header",userd2);


            Log.e("OUTPUT",""+userd.toString());

        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

        //http://169.38.77.190:101/api/Mobile_FDR/Farmerbank
        //15.206.21.248:27/Farmerbank
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.url+"/api/Mobile_FDR_FList/mobFarmerSinglefetch",userd,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);

                        try {
                            // dialog.dismiss();
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("header");

                            String n1 = obj2.getString("in_farmer_name");
                            String n2 = obj2.getString("in_sur_name");
                            String n3 = obj2.getString("in_fhw_name");
                            String n4 = obj2.getString("in_reg_mobile_no");
                            String n5 = obj2.getString("in_gender_flag_desc");
                            String n6 = obj2.getString("in_farmer_dob");
                            String n7 = "";
                            String n8 = obj2.getString("in_farmer_pincode");
                            String n9 = obj2.getString("in_farmer_addr1");
                            String n10 = obj2.getString("in_farmer_village_desc");
                            String n11 = obj2.getString("in_farmer_panchayat_desc");
                            String n12 = obj2.getString("in_farmer_taluk_desc");
                            String n13 = obj2.getString("in_farmer_dist_desc");
                            String n14 = obj2.getString("in_farmer_state_desc");
                            String n15 = obj2.getString("in_farmer_country_desc");
                            String n16 = "1";
                            String n17 = "1";
                            String n18 = obj2.getString("in_farmer_village");
                            String n19 = obj2.getString("in_farmer_panchayat");
                            String n20 = obj2.getString("in_farmer_taluk");
                            String n21 = obj2.getString("in_farmer_dist");
                            String n22 = obj2.getString("in_farmer_rowid");
                            String n23 = obj2.getString("in_farmer_addr2");
                            String n24 = obj2.getString("in_photo_farmer");
                            final String farname = n1;
                            final String faridc = n22;
                            farmername = n1;
                            faid = Integer.parseInt(faridc);
                            e.setText(n1);
                        } catch (Exception e) {
                            FirebaseApp.initializeApp(ProductionTab.this);
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Long tsLong = System.currentTimeMillis()/1000;
                            String ts = tsLong.toString();
                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                            Log.e("Valuecheck",""+mRef.getRef());
                            mRef.child("name").setValue("SAVE");
                            mRef.child("date").setValue(date);
                            mRef.child("Error").setValue(response.toString());
                            mRef.child("Activity").setValue("PRODUCTION");
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);

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
                2500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(ProductionTab.this).addToRequestQueue(stringRequest);



        try {





            //userd = new JSONObject();

            user3 = new JSONObject();
            user3.put("orgnId", "Flexi");
            user3.put("locnId", "Chennai");
            user3.put("userId", "admin");
            user3.put("localeId", "en_US");
            user3.put("instance", "up");
            user3.put("farmer_code", farmercode);
            user3.put("entity_code", "EC_PRODUCTION");


            Log.e("OUTPUTP",""+user3.toString());

        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
        }


        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest17 = new JsonObjectRequest(Request.Method.POST,Pojokyc.url+"/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch",user3,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCCb", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONArray cast = obj.getJSONArray("farmerdetailfetchProduction");
                            for (int j = 0; j < cast.length(); j++) {
                                JSONObject actor = cast.getJSONObject(j);


                                String n1a = fc;
                               // String n2a = farname;
                                String n3a = String.valueOf(faid);
                                String n4a = actor.getString("year");
                                String n5a = actor.getString("season");
                                String n6a = actor.getString("cropClass_desc");
                                String n2a = actor.getString("croptype_desc");
                                String n7a = actor.getString("vareity");
                                String n8a = actor.getString("actualProduction");
                                String n9a = actor.getString("sno");
                                String n10a = actor.getString("seedName_desc");
                                String n11a = actor.getString("availabeForSale");





                                //db.insertproduction(n4a,n5a,n6a,n2a,n7a,n10a,"NIL",n8a,n11a,n9a,n3a,n1a,"1");



                            }
                            pdialog.dismiss();
                            dialog.dismiss();







                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);

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
        stringRequest17.setRetryPolicy(new DefaultRetryPolicy(
                6500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(ProductionTab.this).addToRequestQueue(stringRequest17);

    }

    public void save() {
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Master Sync.....");
        pdialog.show();
        SQLiteDatabase dbs = db.getWritableDatabase();
        dbs.execSQL("delete from masterl");
        try {


            DecimalFormat amountFormate = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", "Flexi");
            userd.put("locnId", sharedpreferences.getString("dateforsyncpd",""));
            userd.put("userId", "admin");
            userd.put("localeId", "en_US");
            userd.put("screen_Id", "FARMER");
            userd.put("instance", "up");

            Log.e("OUTPUT", "" + userd.toString());

        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

        //169.38.77.190:101/api/Mobile_FDR/Farmermaster
        //15.206.21.248:27/Farmermaster
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/Mobile_FDR/Farmermaster", userd,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC2", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                             castmaster  = obj.getJSONArray("detail");
                             save5();

                            // pdialog.dismiss();


                        } catch (JSONException e) {
                            FirebaseApp.initializeApp(ProductionTab.this);
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Long tsLong = System.currentTimeMillis()/1000;
                            String ts = tsLong.toString();
                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                            Log.e("Valuecheck",""+mRef.getRef());
                            mRef.child("name").setValue("MASTER");
                            mRef.child("date").setValue(date);
                            mRef.child("Error").setValue(response.toString());
                            mRef.child("Activity").setValue("PRODUCTIONM");
                            e.printStackTrace();
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);

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
    public void save6() {

        SQLiteDatabase dbs = db.getWritableDatabase();
        dbs.execSQL("delete from masterla");
        try {


            DecimalFormat amountFormate = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", "Flexi");
            userd.put("locnId", "Chennai");
            userd.put("userId", "admin");
            userd.put("localeId", "hi_IN");
            userd.put("instance", "up");
            userd.put("module", "FDR");

            Log.e("OUTPUT", "" + userd.toString());

        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

        //169.38.77.190:101/api/Mobile_FDR/Farmermaster
        //15.206.21.248:27/Farmermaster
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/PAWHS_NEW_Localization/Apilocalization", userd,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC2", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONArray cast = obj.getJSONArray("detail");
                            for (int i = 0; i < cast.length(); i++) {


                                JSONObject actor = cast.getJSONObject(i);


                                String n1 = actor.getString("out_master_rowid");
                                String n2 = actor.getString("out_parent_code");
                                String n3 = actor.getString("out_master_code");
                                String n4 = actor.getString("out_master_description");
                                String n5 = actor.getString("out_depend_code");
                                String n6 = actor.getString("out_depend_desc");
                                String n7 = actor.getString("out_locallang_flag");
                                String n8 = actor.getString("out_status_code");
                                String n9 = actor.getString("out_status_desc");

                                Log.e("Table2", "" + n1);

                                db.insertmasterla(n1, n2, n3, n4, n5, n6, n7, n8, n9);


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);

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
    public void save2() {
        SQLiteDatabase dbs = db.getWritableDatabase();
        dbs.execSQL("delete from bankm");


        try {


            DecimalFormat amountFormate = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", "Flexi");
            userd.put("locnId", "Chennai");
            userd.put("userId", "admin");
            userd.put("localeId", "en_US");
            userd.put("instance", "up");
            userd.put("FilterBy_Option", "ALL");
            userd.put("FilterBy_Code", ".");
            userd.put("FilterBy_FromValue", ".");
            userd.put("FilterBy_ToValue", ".");

            Log.e("OUTPUT", "" + userd.toString());

        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

        //http://169.38.77.190:101/api/Mobile_FDR/Farmerbank
        //15.206.21.248:27/Farmerbank
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/Mobile_FDR/Farmerbank", userd,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONArray cast = obj.getJSONArray("bankDtl");
                            for (int i = 0; i < cast.length(); i++) {


                                JSONObject actor = cast.getJSONObject(i);


                                String n1 = actor.getString("bank_rowid");
                                String n2 = actor.getString("bank_code");
                                String n3 = actor.getString("bank_name");
                                String n4 = actor.getString("branch_name");
                                String n5 = actor.getString("ifsc_code");
                                String n6 = actor.getString("status_desc");
                                Log.e("Table", "" + n1);

                                db.insertbankm(n1, n2, n3, n4, n5, n6);


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);

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

    public void save3() {
        SQLiteDatabase dbs = db.getWritableDatabase();
        dbs.execSQL("delete from tab");

        try {


            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", "Flexi");
            userd.put("locnId", "Chennai");
            userd.put("userId", "admin");
            userd.put("localeId", "en_US");
            userd.put("instance", "up");
            userd.put("FilterBy_Option", "ALL");
            userd.put("FilterBy_Code", ".");
            userd.put("FilterBy_FromValue", ".");
            userd.put("FilterBy_ToValue", ".");

            Log.e("OUTPUT", "" + userd.toString());

        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/Mobile_FDR/FarmerProfileDetail", userd,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {

                            Log.e("Res", "" + response);
                            JSONObject obj = response.getJSONObject("context");
                            JSONArray cast = obj.getJSONArray("farmerProfileDetails");

                            //


                            JSONObject actor = cast.getJSONObject(0);
                            String n1 = actor.getString("dynamic_list");
                            Log.e("Res", "" + cast.toString());
                            JSONArray jsonArray = new JSONArray(n1);
                            String[] strArr = new String[jsonArray.length()];

                            for (int j = 0; j < jsonArray.length(); j++) {
                                strArr[j] = jsonArray.getString(j);

                                JSONObject jsonObj = new JSONObject(strArr[j]);

                                Log.e("Res2", "" + jsonObj.getString("tab_name"));
                                db.inserttab(jsonObj.getString("tab_name"));

                            }
                            //  pdialog.dismiss();


                        } catch (JSONException e) {
                            FirebaseApp.initializeApp(ProductionTab.this);
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Long tsLong = System.currentTimeMillis()/1000;
                            String ts = tsLong.toString();
                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                            Log.e("Valuecheck",""+mRef.getRef());
                            mRef.child("name").setValue("TABVALUE");
                            mRef.child("date").setValue(date);
                            mRef.child("Error").setValue(response.toString());
                            mRef.child("Activity").setValue("PRODUCTIONPROFILE");
                            e.printStackTrace();
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);

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

    public void save4() {


        dbs.execSQL("delete from tablist");
        try {


            DecimalFormat amountFormate = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", "Flexi");
            userd.put("locnId", "Chennai");
            userd.put("userId", "admin");
            userd.put("localeId", "en_US");
            userd.put("instance", "up");
            userd.put("FilterBy_Option", "ALL");
            userd.put("FilterBy_Code", ".");
            userd.put("FilterBy_FromValue", ".");
            userd.put("FilterBy_ToValue", ".");

            Log.e("OUTPUTP", "" + userd.toString());

        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/Mobile_FDR/FarmerProfileDetail", userd,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONArray cast = obj.getJSONArray("farmerProfileDetails");
                            for (int i = 1; i < cast.length() + 1; i++) {


                                JSONObject actor = cast.getJSONObject(i);

                                //Log.e("GETTA",""+actor.getString("tab_name"));

                                String n1 = actor.getString("dynamic_list");
                                // Log.e("Res",""+n1.toString());
                                JSONArray jsonArray = new JSONArray(n1);
                                String[] strArr = new String[jsonArray.length()];
                                // Toast.makeText(FarmerReg.this, ""+jsonArray.length(), Toast.LENGTH_SHORT).show();

                                for (int j = 0; j < jsonArray.length(); j++) {
                                    strArr[j] = jsonArray.getString(j);
                                    tfield.clear();
                                    JSONObject jsonObj = new JSONObject(strArr[j]);

                                    //Log.e("GET2",""+jsonObj.getString("columnname"));

                                    JSONArray jsonArray2 = new JSONArray(jsonObj.getString("columnname"));
                                    //  Toast.makeText(FarmerReg.this, ""+jsonArray2.length(), Toast.LENGTH_SHORT).show();
                                    String[] strArr2 = new String[jsonArray2.length()];
                                    for (int k = 0; k < jsonArray2.length(); k++) {
                                        strArr2[k] = jsonArray2.getString(k);

                                        JSONObject jsonObj2 = new JSONObject(strArr2[k]);
                                        // Log.e("GETT",""+jsonObj2.getString("cname"));
                                        //tfield.add(jsonObj2.getString("cname"));

                                        JSONArray jsonArray3 = new JSONArray(jsonObj.getString("columnvalue"));
                                        String[] strArr3 = new String[jsonArray3.length()];
                                        for (int a = 0; a < jsonArray3.length(); a++) {
                                            strArr3[a] = jsonArray3.getString(a);
                                            JSONObject jsonObj3 = new JSONObject(strArr3[a]);
                                            String s = strArr2[k];
                                            // Log.e("GET4",""+s);

                                            //  Log.e("GET1",""+jsonObj2.getString("cname"));
                                            try {
                                                String as = jsonObj2.getString("cname") + "DIV" + "[" + jsonObj3.getString(jsonObj2.getString("cname")) + "]:";

                                                tfield.add(as);
                                                //Log.e("GETT",""+jsonObj2.getString("cname")+"//"+jsonObj3.getString(jsonObj2.getString("cname")));
                                                if (jsonObj3.getString(jsonObj2.getString("cname")).equalsIgnoreCase("null")) {
                                                    //  Log.e("NULl","++"+jsonObj3.getString(jsonObj2.getString("cname")));

                                                } else {


                                                    // [typeDIV[ID,Address,DOB,Date of joining]:subtypeDIV[Aadhar,Pan Card,Voterid,t1,t2]:document noDIV[0]:valid till dateDIV[0]]
                                                }


                                            } catch (Exception e) {
                                                //Log.e("GETT",""+jsonObj2.getString("cname")+"// 0");

                                            }

                                            // tfield.add(jsonObj2.getString("cname"));
                                        }


                                    }
                                    //Log.e("GET4",""+ Arrays.toString(new ArrayList[]{tfield}));

                                }

                                Log.e("NULl", "++" + actor.getString("tab_name") + "//" + tfield.toString());
                                db.inserttablist(actor.getString("tab_name"), tfield.toString());

                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);

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

    public void save5() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //TODO your background code


                dbs.execSQL("delete from farlist");
                try {


                    DecimalFormat amountFormate = new DecimalFormat("############.##");
                    amountFormate.setMinimumFractionDigits(2);
                    amountFormate.setMaximumFractionDigits(2);

                    Date cc = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                    //userd = new JSONObject();

                    userd = new JSONObject();
                    userd.put("orgnId", "Flexi");
                    userd.put("locnId", "Chennai");
                    userd.put("userId", "admin");
                    userd.put("localeId", "en_US");
                    userd.put("instance", "up");
                    userd.put("FilterBy_Option", "ALL");
                    userd.put("FilterBy_Code", sharedpreferences.getString("dateforsyncpd",""));
                    userd.put("FilterBy_FromValue", ".");
                    userd.put("FilterBy_ToValue", ".");

                    Log.e("OUTPUT", "" + userd.toString());

                } catch (Exception e) {
                    Log.e("OUTPUT", "" + e.getMessage());
                }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

                HttpsTrustManager.allowAllSSL();
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url+"/api/Mobile_FDR_FList/FarmerList", userd,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("CCCC", "" + response);
                                try {
                                    JSONObject obj = response.getJSONObject("context");
                                     castfar = obj.getJSONArray("list");
                                    new SomeTaskfarmer().execute();


                                } catch (JSONException e) {
                                    FirebaseApp.initializeApp(ProductionTab.this);
                                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                    String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                    Long tsLong = System.currentTimeMillis()/1000;
                                    String ts = tsLong.toString();
                                    DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                    Log.e("Valuecheck",""+mRef.getRef());
                                    mRef.child("name").setValue("FARMELIST");
                                    mRef.child("date").setValue(date);
                                    mRef.child("Error").setValue(response.toString());
                                    mRef.child("Activity").setValue("PRODUCTION");
                                    e.printStackTrace();
                                }


                            }


                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("CCCC", "" + error);

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
                        4500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                VolleySingleton.getInstance(ProductionTab.this).addToRequestQueue(stringRequest);
            }
        });


    }

    public void loaddata()
    {
        Cursor cursor2 = dbs.query("tablist", new String[]{"field"
                }, "tab" + "=?",
                new String[]{"Sowing Details"}, null, null, null, null);



        // Cursor cursor2 = dbs.rawQuery(selectQuery, null);

        Log.e("RESNEW",""+cursor2.getCount());


        if (cursor2.moveToFirst()) {
            do {

                Log.e("RES", "" + cursor2.getString(0));
                String vl = cursor2.getString(0);
                String vll = vl.substring(1, vl.length() - 2);
                Log.e("RES", "" + vll);

                String[] vl2 = vll.split(":,");
                for (int j = 0; j < vl2.length; j++) {
                    Log.e("RES", "" + vl2[j]);

                    String[] tf = vl2[j].split("DIV");
                    Log.e("TTT", "" + tf[0]);


                    if (tf[0].replaceAll(" ", "").equalsIgnoreCase("Year")) {
                        //  Toast.makeText(MainActivity3.this, "hi", Toast.LENGTH_SHORT).show();
                        // hsvssy.setVisibility(View.VISIBLE);
                        if (tf[1].replaceAll(" ", "").equalsIgnoreCase("")) {

                        } else {
                            String tv = tf[1];
                            String tv2 = tv.substring(1, tv.length() - 1);
                            String[] tfs = tv2.split(",");

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (ProductionTab.this, R.layout.spinnertext3, tfs);
                            //Getting the instance of AutoCompleteTextView

                            ac1.setThreshold(0);//will start working from first character
                            ac1.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                            ac1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ac1.showDropDown();
                                    ac1.requestFocus();
                                }
                            });



                        }
                    }
//                    if (tf[0].replaceAll(" ", "").equalsIgnoreCase("Season")) {
//                        //  Toast.makeText(MainActivity3.this, "hi", Toast.LENGTH_SHORT).show();
//                        // hsvssy.setVisibility(View.VISIBLE);
//                        if (tf[1].replaceAll(" ", "").equalsIgnoreCase("")) {
//
//                        } else {
//                            String tv = tf[1];
//                            String tv2 = tv.substring(1, tv.length() - 1);
//                            String[] tfs = tv2.split(",");
//                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                                    (ProductionTab.this, R.layout.spinnertext3, tfs);
//                            //Getting the instance of AutoCompleteTextView
//
//                            ac2.setThreshold(0);//will start working from first character
//                            ac2.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                            ac2.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    ac2.showDropDown();
//                                    ac2.requestFocus();
//                                }
//                            });
//
//
//
//
//                        }
//                    }


//                    if (tf[0].replaceAll(" ", "").equalsIgnoreCase("CropClassification")) {
//                        //  Toast.makeText(MainActivity3.this, "hi", Toast.LENGTH_SHORT).show();
//                        // hsvssy.setVisibility(View.VISIBLE);
//                        if (tf[1].replaceAll(" ", "").equalsIgnoreCase("")) {
//
//                        } else {
//                            String tv = tf[1];
//                            String tv2 = tv.substring(1, tv.length() - 1);
//                            String[] tfs = tv2.split(",");
//
//                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                                    (ProductionTab.this, R.layout.spinnertext3, tfs);
//                            //Getting the instance of AutoCompleteTextView
//
//                            ac3.setThreshold(0);//will start working from first character
//                            ac3.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                            ac3.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    ac3.showDropDown();
//                                    ac3.requestFocus();
//                                }
//                            });
//
//
//
//                        }
//                    }
                }

            } while (cursor2.moveToNext());
        }
        Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_depend_code"
                }, "out_parent_code" + "=?",
                new String[]{"QCD_SOW_SESSION"}, null, null, null, null);






        if (cursor2mst.moveToFirst()) {
            arraysc.clear();
            arraysn.clear();

            do {

                if(cursor2mst.getString(2).equalsIgnoreCase(sharedpreferences.getString("lo",""))) {

                    arraysc.add(cursor2mst.getString(0));
                    arraysn.add(cursor2mst.getString(1));
                }
            }while(cursor2mst.moveToNext());


            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (ProductionTab.this, R.layout.spinnertext3, arraysn);
            //Getting the instance of AutoCompleteTextView

            ac2.setThreshold(0);//will start working from first character
            ac2.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
            ac2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ac2.showDropDown();
                    ac2.requestFocus();
                }
            });
        }

        ac2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // add the array list here..

                ac3.setText("");
                ac4.setText("");
                ac6.setText("");
                //stclf.setText("");
               // stsnm.setText("");
                final SQLiteDatabase dbs = db.getWritableDatabase();
                // Log.e("VALCHK",""+arg0.toString());
                arraymn.clear();
                arraymc.clear();
                arraycrc.clear();
                arraycrn.clear();
                array3.clear();
                // ccts.setText("Tap For Select Crop Name");
                //codes=arraysc.get(position);


                Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_parent_code"
                        }, "out_depend_code" + "=?",
                        new String[]{arraysc.get(position)}, null, null, null, null);






                if (cursor2mst.moveToFirst()) {

                    do {
                        if(cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_MONTHS")) {


                            arraymc.add(cursor2mst.getString(0));
                            arraymn.add(cursor2mst.getString(1));

                            if(cursor2mst.getString(1).equalsIgnoreCase("January"))
                            {
                                array3.add("01");
                            }
                            else if(cursor2mst.getString(1).equalsIgnoreCase("February"))
                            {
                                array3.add("02");
                            }
                            else if(cursor2mst.getString(1).equalsIgnoreCase("March"))
                            {
                                array3.add("03");
                            }
                            else if(cursor2mst.getString(1).equalsIgnoreCase("April"))
                            {
                                array3.add("04");
                            }
                            else if(cursor2mst.getString(1).equalsIgnoreCase("May"))
                            {
                                array3.add("05");
                            }
                            else if(cursor2mst.getString(1).replaceAll(" ","").equalsIgnoreCase("June"))
                            {
                                array3.add("06");
                            }
                            else if(cursor2mst.getString(1).equalsIgnoreCase("July"))
                            {
                                array3.add("07");
                            }
                            else if(cursor2mst.getString(1).equalsIgnoreCase("August"))
                            {
                                array3.add("08");
                            }
                            else if(cursor2mst.getString(1).equalsIgnoreCase("September"))
                            {
                                array3.add("09");
                            }
                            else if(cursor2mst.getString(1).equalsIgnoreCase("October"))
                            {
                                array3.add("10");
                            }
                            else if(cursor2mst.getString(1).equalsIgnoreCase("November"))
                            {
                                array3.add("11");
                            }
                            else if(cursor2mst.getString(1).equalsIgnoreCase("December"))
                            {
                                array3.add("12");
                            }
                            Log.e("MyAndroidClass", Arrays.toString(new ArrayList[]{array3}));
                            Log.e("MyAndroidClass2", cursor2mst.getString(1));

                        }
                        if(cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_CROPCLASS")) {
                            Log.e("VALCHK",""+cursor2mst.getString(0));

                            arraycrc.add(cursor2mst.getString(0));
                            arraycrn.add(cursor2mst.getString(1));
                        }
                    }while(cursor2mst.moveToNext());


//
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                            (MainActivity3.this, R.layout.spinnertext3, arraymn);
//                    //Getting the instance of AutoCompleteTextView
//
//                    stmnt.setThreshold(0);//will start working from first character
//                    stmnt.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                    stmnt.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            stmnt.showDropDown();
//                            stmnt.requestFocus();
//                        }
//                    });
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                            (ProductionTab.this, R.layout.spinnertext3, arraycrn);
                    //Getting the instance of AutoCompleteTextView

                    ac3.setThreshold(0);//will start working from first character
                    ac3.setAdapter(adapter2);//setting the adapter data into the AutoCompleteTextView
                    ac3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ac3.showDropDown();
                            ac3.requestFocus();
                        }
                    });
                }

                // String val = "[typeDIV[ID,Address,DOB,Date of joining]:subtypeDIV[Aadhar,Pan Card,Voterid,t1,t2]:document noDIV[0]:valid till dateDIV[0]]";


                //String selectQuery = "SELECT  * FROM tablist";


                // Cursor cursor2 = dbs.rawQuery(selectQuery, null);
            }
        });
        ac3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // add the array list here..
                ac4.setText("");
                ac5.setText("");
                final SQLiteDatabase dbs = db.getWritableDatabase();
                Log.e("VALCHK",""+position);
                arraycnc.clear();
                arraycnn.clear();

                // ccts.setText("Tap For Select Crop Name");

               // codecr=arraycrc.get(position);
                Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_parent_code"
                        }, "out_depend_code" + "=?",
                        new String[]{arraycrc.get(position)}, null, null, null, null);




                Log.e("VALCHKCOUNT",""+cursor2mst.getCount());

                if (cursor2mst.moveToFirst()) {

                    do {
                        if(cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_CROPNAME")) {


                            arraycnc.add(cursor2mst.getString(0));
                            arraycnn.add(cursor2mst.getString(1));
                        }

                    }while(cursor2mst.moveToNext());



                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                            (ProductionTab.this, R.layout.spinnertext3, arraycnn);
                    //Getting the instance of AutoCompleteTextView

                    ac4.setThreshold(0);//will start working from first character
                    ac4.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                    ac4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ac4.showDropDown();
                            ac4.requestFocus();
                        }
                    });

                }

                // String val = "[typeDIV[ID,Address,DOB,Date of joining]:subtypeDIV[Aadhar,Pan Card,Voterid,t1,t2]:document noDIV[0]:valid till dateDIV[0]]";


                //String selectQuery = "SELECT  * FROM tablist";


                // Cursor cursor2 = dbs.rawQuery(selectQuery, null);
            }
        });
        ac4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // add the array list here..

                ac6.setText("");
                final SQLiteDatabase dbs = db.getWritableDatabase();
                Log.e("VALCHK",""+position);
                arrayvc.clear();
                arrayvn.clear();
                arraysec.clear();
                arraysen.clear();
                // ccts.setText("Tap For Select Crop Name");

               // codecn=arraycnc.get(position);
                Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_parent_code"
                        }, "out_depend_code" + "=?",
                        new String[]{arraycnc.get(position)}, null, null, null, null);






                if (cursor2mst.moveToFirst()) {

                    do {
                        if(cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_CROPVARIETY")) {


                            arrayvc.add(cursor2mst.getString(0));
                           // Log.e("VNAME",""+cursor2mst.getString(1));
                            arrayvn.add(cursor2mst.getString(1));
                        }

                    }while(cursor2mst.moveToNext());

                    for(int i = 0;i<arrayvc.size();i++)
                    {
                        // Log.e("VALCHK",""+position);


                        // ccts.setText("Tap For Select Crop Name");


                        Cursor cursor2mst2 = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_parent_code"
                                }, "out_depend_code" + "=?",
                                new String[]{arrayvc.get(i)}, null, null, null, null);






                        if (cursor2mst2.moveToFirst()) {

                            do {
                                if(cursor2mst2.getString(2).equalsIgnoreCase("QCD_SOW_SEEDNAME")) {


                                    arraysec.add(cursor2mst2.getString(0));
                                    arraysen.add(cursor2mst2.getString(1));
                                }

                            }while(cursor2mst2.moveToNext());



                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (ProductionTab.this, R.layout.spinnertext3, arraysen);
                            //Getting the instance of AutoCompleteTextView

                            ac6.setThreshold(0);//will start working from first character
                            ac6.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                            ac6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ac6.showDropDown();
                                    ac6.requestFocus();
                                }
                            });

                        }

                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                            (ProductionTab.this, R.layout.spinnertext3, arrayvn);
                    //Getting the instance of AutoCompleteTextView

                    ac5.setThreshold(0);//will start working from first character
                    ac5.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                    ac5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ac5.showDropDown();
                            ac5.requestFocus();
                        }
                    });

                }
                // String val = "[typeDIV[ID,Address,DOB,Date of joining]:subtypeDIV[Aadhar,Pan Card,Voterid,t1,t2]:document noDIV[0]:valid till dateDIV[0]]";


                //String selectQuery = "SELECT  * FROM tablist";


                // Cursor cursor2 = dbs.rawQuery(selectQuery, null);
            }
        });
        try {


            ac6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selected = (String) parent.getItemAtPosition(position);
                    Cursor cursor = dbs.query("masterl", new String[]{"out_depend_desc"
                            }, "out_master_code" + "=?",
                            new String[]{arraysec.get(position)}, null, null, null, null);

                    if(cursor.getCount()>0)
                    {
                        if(cursor.moveToFirst())
                        {
                            Log.e("VNAME",""+cursor.getString(0));
                            ac5.setText(""+cursor.getString(0));
                        }
                    }

                }
            });
        }
        catch (Exception e)
        {

        }

//        ac3.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
//                //adapter.getFilter().filter(cs);
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                //Toast.makeText(getApplicationContext(),"before text change",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//                final SQLiteDatabase dbs = db.getWritableDatabase();
//                // Log.e("VALCHK",""+arg0.toString());
//                array3.clear();
//                // ccts.setText("Tap For Select Crop Name");
//
//
//                Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code"
//                        }, "out_master_description" + "=?",
//                        new String[]{arg0.toString()}, null, null, null, null);
//
//
//
//
//
//
//                if (cursor2mst.moveToFirst()) {
//                    do {
//                        Log.e("VALCHK",""+cursor2mst.getString(0));
//
//                        String mcode = cursor2mst.getString(0);
//
//
//                        Cursor cursor2mst2 = dbs.query("masterl", new String[]{"out_master_description"
//                                }, "out_depend_code" + "=?",
//                                new String[]{mcode}, null, null, null, null);
//
//
//
//
//
//
//
//
//
//
//                        if (cursor2mst2.moveToFirst()) {
//                            // array3.clear();
//                            do {
//
//
//                                Log.e("VALCHK",""+cursor2mst2.getString(0));
//                                array3.add(cursor2mst2.getString(0));
//
//
//
//                                // Log.e("TTT", "" + tf[0]);
//
//
//
//
//                                //  Toast.makeText(MainActivity3.this, "hi", Toast.LENGTH_SHORT).show();
//                                // hsvssy.setVisibility(View.VISIBLE);
//
//
//
//
//
//
//                                // total number of textviews to add
//
//
//
//
//
//
//
//
//
//                            } while (cursor2mst2.moveToNext());
//                        }
//
//
//                    } while (cursor2mst.moveToNext());
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                            (ProductionTab.this, R.layout.spinnertext3, array3);
//                    //Getting the instance of AutoCompleteTextView
//
//                    ac4.setThreshold(0);//will start working from first character
//                    ac4.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                    ac4.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            ac4.showDropDown();
//                            ac4.requestFocus();
//                        }
//                    });
//                }
//
//                // String val = "[typeDIV[ID,Address,DOB,Date of joining]:subtypeDIV[Aadhar,Pan Card,Voterid,t1,t2]:document noDIV[0]:valid till dateDIV[0]]";
//
//
//                //String selectQuery = "SELECT  * FROM tablist";
//
//
//                // Cursor cursor2 = dbs.rawQuery(selectQuery, null);
//
//
//
//            }
//        });
//        ac4.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
//                //adapter.getFilter().filter(cs);
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                //Toast.makeText(getApplicationContext(),"before text change",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//                array4.clear();
//                //ccts.setText("Tap For Select Crop Name");
//                // cvars.setText("Tap For Select Variety");
//
//                final SQLiteDatabase dbs = db.getWritableDatabase();
//                // Log.e("VALCHK",""+arg0.toString());
//
//
//                Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code"
//                        }, "out_master_description" + "=?",
//                        new String[]{arg0.toString()}, null, null, null, null);
//
//
//
//
//
//
//                if (cursor2mst.moveToFirst()) {
//                    do {
//                        Log.e("VALCHK",""+cursor2mst.getString(0));
//
//                        String mcode = cursor2mst.getString(0);
//
//
//                        Cursor cursor2mst2 = dbs.query("masterl", new String[]{"out_master_description","out_parent_code"
//                                }, "out_depend_code" + "=?",
//                                new String[]{mcode}, null, null, null, null);
//
//
//
//
//
//
//
//
//
//
//                        if (cursor2mst2.moveToFirst()) {
//
//
//                            do {
//                                //array4.clear();
//
//                                if(cursor2mst2.getString(1).equalsIgnoreCase("QCD_CROPVARIETY"))
//                                {
//                                    Log.e("VALCHK",""+cursor2mst2.getString(0));
//                                    if(array4.contains(cursor2mst2.getString(0))) {
//
//                                    }
//                                    else
//                                    {
//                                        array4.add(cursor2mst2.getString(0));
//                                    }
//                                }
//
//
//
//                                // Log.e("TTT", "" + tf[0]);
//
//
//
//
//                                //  Toast.makeText(MainActivity3.this, "hi", Toast.LENGTH_SHORT).show();
//                                // hsvssy.setVisibility(View.VISIBLE);
//
//
//
//
//
//
//                                // total number of textviews to add
//
//
//
//
//
//
//
//
//
//
//                            } while (cursor2mst2.moveToNext());
//
//                        }
//
//                    } while (cursor2mst.moveToNext());
//                }
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                        (ProductionTab.this, R.layout.spinnertext3, array4);
//                //Getting the instance of AutoCompleteTextView
//
//                ac5.setThreshold(0);//will start working from first character
//                ac5.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                ac5.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ac5.showDropDown();
//                        ac5.requestFocus();
//                    }
//                });
//                // String val = "[typeDIV[ID,Address,DOB,Date of joining]:subtypeDIV[Aadhar,Pan Card,Voterid,t1,t2]:document noDIV[0]:valid till dateDIV[0]]";
//
//
//                //String selectQuery = "SELECT  * FROM tablist";
//
//
//                // Cursor cursor2 = dbs.rawQuery(selectQuery, null);
//
//
//
//            }
//        });
//        ac5.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
//                //adapter.getFilter().filter(cs);
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                //Toast.makeText(getApplicationContext(),"before text change",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//                array5.clear();
//                //ccts.setText("Tap For Select Crop Name");
//                // stsnm.setText("Tap For Select Seed Name");
//
//                final SQLiteDatabase dbs = db.getWritableDatabase();
//                // Log.e("VALCHK",""+arg0.toString());
//
//
//                Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code","out_parent_code"
//                        }, "out_master_description" + "=?",
//                        new String[]{arg0.toString()}, null, null, null, null);
//
//
//
//
//
//
//                if (cursor2mst.moveToFirst()) {
//                    do {
//                        if(cursor2mst.getString(1).equalsIgnoreCase("QCD_CROPSUBCAT"))
//                        {
//                            Log.e("VALCHK",""+cursor2mst.getString(0));
//
//                            String mcode = cursor2mst.getString(0);
//
//
//                            Cursor cursor2mst2 = dbs.query("masterl", new String[]{"out_master_description"
//                                    }, "out_depend_code" + "=?",
//                                    new String[]{mcode}, null, null, null, null);
//
//
//
//
//
//
//
//
//
//
//                            if (cursor2mst2.moveToFirst()) {
//
//                                do {
//                                    // array5.clear();
//
//
//                                    Log.e("VALCHK",""+cursor2mst2.getString(0));
//                                    array5.add(cursor2mst2.getString(0));
//
//
//
//                                    // Log.e("TTT", "" + tf[0]);
//
//
//
//
//                                    //  Toast.makeText(MainActivity3.this, "hi", Toast.LENGTH_SHORT).show();
//                                    // hsvssy.setVisibility(View.VISIBLE);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//                                } while (cursor2mst2.moveToNext());
//
//                            }
//                        }
//
//                    } while (cursor2mst.moveToNext());
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                            (ProductionTab.this, R.layout.spinnertext3, array5);
//                    //Getting the instance of AutoCompleteTextView
//
//                    ac6.setThreshold(0);//will start working from first character
//                    ac6.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                    ac6.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            ac6.showDropDown();
//                            ac6.requestFocus();
//                        }
//                    });
//                }
//
//                // String val = "[typeDIV[ID,Address,DOB,Date of joining]:subtypeDIV[Aadhar,Pan Card,Voterid,t1,t2]:document noDIV[0]:valid till dateDIV[0]]";
//
//
//                //String selectQuery = "SELECT  * FROM tablist";
//
//
//                // Cursor cursor2 = dbs.rawQuery(selectQuery, null);
//
//
//
//            }
//        });

//        ac3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
//                                    long id) {
//                ac4.setText("");
//                ac5.setText("");
//                ac6.setText("");
//
//            }
//        });
//        ac4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
//                                    long id) {
//                ac5.setText("");
//                ac6.setText("");
//
//            }
//        });
//        ac5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
//                                    long id) {
//                ac6.setText("");
//
//            }
//        });
    }

    public  void saveproduction(String v1,String v2, String v3, String v4, final String v5, String v6,String v7,String v8,String v9,String v10,String v11,String v12,final String id,String v13)
    {


//        Log.e("NULL",""+v1);
////
//        Log.e("NULL",""+v2);
//        Log.e("NULL",""+v3);
//       Log.e("NULL",""+v4);
//        Log.e("NULLV",""+v5);
//        Log.e("NULLS",""+v6);
//       Log.e("NULL",""+v7);
//        Log.e("NULL",""+v8);
//        Log.e("NULL",""+v9);
        // Log.e("NULL",""+v10);
        // Log.e("NULL",""+v11);
        //Log.e("NULL",""+v12);
        //  Log.e("NULL",""+v13);
        //Log.e("NULL",""+v14);
        // Log.e("NULL",""+v15);
        //Log.e("NULL",""+v16);



        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();

        try {



            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", "Flexi");
            user.put("locnId", "chennai");
            user.put("userId", v13);
            user.put("localeId", "en_US");
            user.put("instance", "up");

            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("inout_farmer_rowid",Integer.parseInt(v11));
            user2.put("inout_farmer_code",v12);
            user2.put("inout_version_no",1);
            user2.put("entitygrp_code","EC_PRODUCTION");
            user2.put("in_created_by", v7);
            // user2.put("in_modified_by", sharedpreferences.getString("un","").toUpperCase()+""+sharedpreferences.getString("phn",""));
            user.put("Header",user2);

            JSONObject ob1 = new JSONObject();
            try {
                ob1.put("in_farmerdetail_rowid", 0);
                ob1.put("in_entitygrp_code", "EC_PRODUCTION");
                ob1.put("in_entity_code", "EC_PROD_Year");
                ob1.put("in_row_slno", "");
                ob1.put("in_entity_value", v1);
                ob1.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob2 = new JSONObject();
            try {
                ob2.put("in_farmerdetail_rowid", 0);
                ob2.put("in_entitygrp_code", "EC_PRODUCTION");
                ob2.put("in_entity_code", "EC_PROD_Season");
                ob2.put("in_row_slno", "");
                ob2.put("in_entity_value", v2);
                ob2.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob3 = new JSONObject();
            try {
                ob3.put("in_farmerdetail_rowid", 0);
                ob3.put("in_entitygrp_code", "EC_PRODUCTION");
                ob3.put("in_entity_code", "EC_PROD_CROPCLASS");
                ob3.put("in_row_slno", "");
                ob3.put("in_entity_value", ""+v3);
                ob3.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob4 = new JSONObject();
            try {
                ob4.put("in_farmerdetail_rowid", 0);
                ob4.put("in_entitygrp_code", "EC_PRODUCTION");
                ob4.put("in_entity_code", "EC_PROD_CROPTYPE");
                ob4.put("in_row_slno", "");
                ob4.put("in_entity_value", ""+v4);
                ob4.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob5 = new JSONObject();
            try {
                ob5.put("in_farmerdetail_rowid", 0);
                ob5.put("in_entitygrp_code", "EC_PRODUCTION");
                ob5.put("in_entity_code", "EC_PROD_Variety");
                ob5.put("in_row_slno", "");
                ob5.put("in_entity_value", ""+v5);
                ob5.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob6 = new JSONObject();
            try {
                ob6.put("in_farmerdetail_rowid", 0);
                ob6.put("in_entitygrp_code", "EC_PRODUCTION");
                ob6.put("in_entity_code", "EC_PROD_SEED_NAME");
                ob6.put("in_row_slno", "");
                ob6.put("in_entity_value", ""+v6);
                ob6.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob7 = new JSONObject();
            try {
                ob7.put("in_farmerdetail_rowid", 0);
                ob7.put("in_entitygrp_code", "EC_PRODUCTION");
                ob7.put("in_entity_code", "EC_PROD_ActualProduction");
                ob7.put("in_row_slno", "");
                ob7.put("in_entity_value", v8);
                ob7.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob8 = new JSONObject();
            try {
                ob8.put("in_farmerdetail_rowid", 0);
                ob8.put("in_entitygrp_code", "EC_PRODUCTION");
                ob8.put("in_entity_code", "EC_PROD_AVAILABLE_FOR_SALE");
                ob8.put("in_row_slno", "");
                ob8.put("in_entity_value", v9);
                ob8.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            JSONObject ob9 = new JSONObject();
            try {
                ob9.put("in_farmerdetail_rowid", 0);
                ob9.put("in_entitygrp_code", "EC_PRODUCTION");
                ob9.put("in_entity_code", "EC_PRODUCTION_LANDTYPE");
                ob9.put("in_row_slno", "");
                ob9.put("in_entity_value", "");
                ob9.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob10 = new JSONObject();
            try {
                ob10.put("in_farmerdetail_rowid", 0);
                ob10.put("in_entitygrp_code", "EC_PRODUCTION");
                ob10.put("in_entity_code", "EC_PROD_LRPNAME");
                ob10.put("in_row_slno", "");
                ob10.put("in_entity_value", v13);
                ob10.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            JSONArray jsonArray = new JSONArray();

            jsonArray.put(ob1);
            jsonArray.put(ob2);
            jsonArray.put(ob3);
            jsonArray.put(ob4);
            jsonArray.put(ob5);
            jsonArray.put(ob6);
            jsonArray.put(ob7);
            jsonArray.put(ob8);
            jsonArray.put(ob9);
            jsonArray.put(ob10);






            user.put("Detail",jsonArray);
            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT2",""+e.getMessage());
        }




        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.url+"/api/Mobile_FDR_FDetail/NewMobileFarmerCrop",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {
                            pdialog.dismiss();
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("header");
                            final String frid = obj2.getString("inout_farmer_rowid");
                            Log.e("CCCC", "" + frid);

                            if(frid.equalsIgnoreCase("0")) {


                                pdc2++;
                                final SQLiteDatabase dbs = db.getWritableDatabase();

                                //dbs.execSQL("UPDATE address SET flag = 1 WHERE id = " + idk);
                                //  dbs.execSQL("DELETE FROM sowing WHERE id = " + id);
                                dbs.execSQL("UPDATE production SET v13 = 1 WHERE id = " + id);


                                String selectQuery2 = "SELECT  * FROM production where v13 = 0";

                                Cursor cursor2 = dbs.rawQuery(selectQuery2, null);


                                if (cursor2.moveToFirst()) {


                                    saveproduction(cursor2.getString(1), cursor2.getString(2), cursor2.getString(3), cursor2.getString(4), cursor2.getString(5),cursor2.getString(6),cursor2.getString(7),cursor2.getString(8),cursor2.getString(9),cursor2.getString(10),cursor2.getString(11),cursor2.getString(12),cursor2.getString(0),cursor2.getString(14));


                                    // Log.e("Check",""+cursor.getString(1));


                                }
                                if(pdc == pdc2) {

                                    final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                                            .setTitle("Success!")
//set message
                                            .setMessage("Production Data Successfully Captured. Thanks!")
//set positive button
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            //set what would happen when positive button is clicked
                                                            // finish();
                                                            pdc = 0;
                                                            pdc2 = 0;
                                                            ac1.setText("");
                                                            ac2.setText("");
                                                            ac3.setText("");
                                                            ac4.setText("");
                                                            ac5.setText("");
                                                            ac6.setText("");
                                                            //ac7.setText("");
                                                            ac8.setText("");
                                                            ac9.setText("");
                                                            startActivity(getIntent());
                                                        }
                                                    }
//set negative button

                                            )
                                            .show();
                                }



                            }
                            else
                            {
                                pdialog.dismiss();
                                final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Error Inserting Production Details")
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
                        }
                        catch (Exception e)
                        {
                            pdialog.dismiss();
                            final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Error Inserting Production Details")
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

                    }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);
                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Error Inserting Production Details")
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
                2500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public  class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>
    {

        private List<Pojokyc> mData;
        private LayoutInflater mInflater;
        int p ;


        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<Pojokyc> data, int page) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
            this.p = page;
        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.producelist, parent, false);
            return new MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the textview2 in each row
        @Override
        public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, final int position) {
            final Pojokyc pojokyc = mData.get(position);

































            if(p == 35)
            {
                // Log.e("FFFF","we");
                // Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();
                holder.t1.setText(pojokyc.getPdut1());
                holder.t2.setText(pojokyc.getPdut2());
                holder.t3.setText(pojokyc.getPdut3());
                holder.t4.setText(pojokyc.getPdut4());
                holder.t5.setText(pojokyc.getPdut5());
                holder.t6.setText(pojokyc.getPdut6());
                holder.t7.setText(pojokyc.getPdut7());
                holder.t8.setText(pojokyc.getPdut8());
                holder.t9.setText(pojokyc.getPdut9());




                holder.ed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ac1.setText(pojokyc.getPdut1());
                        ac2.setText(pojokyc.getPdut2());
                        ac3.setText(pojokyc.getPdut3());
                        ac4.setText(pojokyc.getPdut4());
                        ac5.setText(pojokyc.getPdut5());
                        ac6.setText(pojokyc.getPdut6());
                        ac7.setText(pojokyc.getPdut7());
                        ac8.setText(pojokyc.getPdut8());
                        ac9.setText(pojokyc.getPdut9());


                        loaddata();
                        pdid = pojokyc.getPduid();
                        pdslno = pojokyc.getPduslno();
                        u = 1;

                        dialog.dismiss();
                    }
                });








            }


























        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
            ImageView ed;


            ViewHolder(View itemView) {
                super(itemView);

                t1=itemView.findViewById(R.id.t1);
                t2=itemView.findViewById(R.id.t2);
                t3=itemView.findViewById(R.id.t3);
                t4=itemView.findViewById(R.id.t4);
                t5=itemView.findViewById(R.id.t5);
                t6=itemView.findViewById(R.id.t6);
                t7=itemView.findViewById(R.id.t7);
                t8=itemView.findViewById(R.id.t8);
                t9=itemView.findViewById(R.id.t9);
                ed=itemView.findViewById(R.id.ed);

            }


        }

        // convenience method for getting data at click position


        // allows clicks events to be caught


        // parent activity will implement this method to respond to click events

    }
    class SomeTaskfarmer extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // super.onPreExecute();
            // progressLayout.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {



                for (int j = 0; j < castfar.length(); j++) {
                    JSONObject actor = castfar.getJSONObject(j);


                    String n1 = actor.getString("farmer_rowid");
                    String n2 = actor.getString("farmer_code");
                    String n3 = actor.getString("farmer_name");
                    String n4 = actor.getString("farmer_village_desc");
                    String n5 = actor.getString("farmer_panchayat_desc");
                    String n6 = actor.getString("farmer_taluk_desc");
                    String n7 = actor.getString("farmer_dist_desc");
                     String n8 = actor.getString("father_name");
                    String query = "Select * from farlist where fc = '"+n2+"'";
                    Cursor cursor = dbs.rawQuery(query , null);
                    if(sharedpreferences.getString("dateforsyncpd","").equalsIgnoreCase(".")) {

                        db.inserfarlist(n1, n2, n3, n8, n4, n5, n6, n7, "", "", "", "", "","0","0","0");
                    }
                    else
                    {
                        try {
                            if (cursor.getCount() > 0) {
                                dbs.execSQL("Delete from farlist where fc = '" + n2 + "'");
                                db.inserfarlist(n1, n2, n3, n8, n4, n5, n6, n7, "", "", "", "", "","0","0","0");

                            }
                            else
                            {
                                db.inserfarlist(n1, n2, n3, n8, n4, n5, n6, n7, "", "", "", "", "","0","0","0");

                            }
                            } finally {
                                cursor.close();
                            }
                    }


                }
                for (int i = 0; i < castmaster.length(); i++) {


                    JSONObject actor = castmaster.getJSONObject(i);


                    String n1 = actor.getString("out_master_rowid");
                    String n2 = actor.getString("out_parent_code");
                    String n3 = actor.getString("out_master_code");
                    String n4 = actor.getString("out_master_description");
                    String n5 = actor.getString("out_depend_code");
                    String n6 = actor.getString("out_depend_desc");
                    String n7 = actor.getString("out_locallang_flag");
                    String n8 = actor.getString("out_status_code");
                    String n9 = actor.getString("out_status_desc");

                    Log.e("Table2", "" + n3);
                    Cursor cr1 = dbs.rawQuery("select * from masterl where out_master_code='"+n3+"'",null);

                    if(sharedpreferences.getString("dateforsync","").equalsIgnoreCase(".")) {
                        try {
                            if (cr1.getCount() > 0) {
                                dbs.execSQL("Delete from masterl where out_master_code = '" + n3 + "'");
                                db.insertmasterl(n1, n2, n3, n4, n5, n6, n7, n8, n9);
                            } else {
                                db.insertmasterl(n1, n2, n3, n4, n5, n6, n7, n8, n9);
                            }
                        } finally {
                            cr1.close();
                        }                    }
                    else
                    {

                        try {
                            if (cr1.getCount() > 0) {
                                dbs.execSQL("Delete from masterl where out_master_code = '" + n3 + "'");
                                db.insertmasterl(n1, n2, n3, n4, n5, n6, n7, n8, n9);
                            } else {
                                db.insertmasterl(n1, n2, n3, n4, n5, n6, n7, n8, n9);
                            }
                        } finally {
                            cr1.close();
                        }
                    }



                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            //callLotnoListDetails();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            pdialog.dismiss();
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("dateforsyncpd", getDate1());
            editor.commit();
            final AlertDialog alertDialog = new AlertDialog.Builder(ProductionTab.this)
//set icon
                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                    .setTitle("Success!")
//set message
                    .setMessage("Master Sync Completed!")
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
            //progressLayout.setVisibility(View.GONE);
            // showCompleteDialog();

            // callLotnoListDetailsOfApproveWare("WareHouse");

        }
    }
    @Override
    public void onBackPressed() {
      Intent i = new Intent(getApplicationContext(),Landpage.class);
      startActivity(i);
      finish();
    }
    public static String getDate1() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}