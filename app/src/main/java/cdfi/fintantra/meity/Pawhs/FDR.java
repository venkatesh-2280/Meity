package cdfi.fintantra.meity.Pawhs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import cdfi.fintantra.meity.Pawhs.formermodel.FormerDao;
import cdfi.fintantra.meity.Pojobank;
import cdfi.fintantra.meity.R;
import cdfi.fintantra.meity.VolleySingleton;

public class FDR extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9,ed10,ed11,ed12,ed13,bed1,bed2,bed3,bed4;
    Button save;
    Spinner spinner , spinner2,spinner3,spinner4;
    ImageView qrcode,captureh;
    List<Pojobank> pojobankList;
    String dbb="0";
    ArrayList<String> hamlet = new ArrayList<String>();
    String dbb2="0";
    String bcode;
    String encodedImage = "0",encodedImage2 = "0";
    ByteArrayOutputStream byteArrayOutputStream,byteArrayOutputStream2;
    String imageFilePath,imageFilePath2;
    Bitmap thePic,thePic2;
    SQLiteDataBaseHandler db;
    String code;
    Uri picUri;
    int page = 0;
    private PAWHSApplication pawhsApplication;
    ProgressDialog pdialog;
    JSONObject jsonParam;
    String ui="0";
    Dialog dialog;
    ArrayList<String> arrayn;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    LinearLayout l1,l2;
    Calendar myCalendar,myCalendar2;
    IntentIntegrator qrScan;
    String userid;
    MyRecyclerViewAdapterb adapterb;
    final int CAMERA_CAPTURE = 1;
    private static final int CAMERA_REQUEST = 1888;
    DatePickerDialog.OnDateSetListener date;
    String[] gender = { "Tap For Select Gender","Male","Female","Transgender" };
    String[] tab = { "Header", "Bank"};
    String[] atype = { "Tap For Select  Account Type", "Current","Savings","Temporary"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_d_r);
        getSupportActionBar().hide();
        db = new SQLiteDataBaseHandler(this);
        pojobankList = new ArrayList<>();
        ed1 = (EditText)findViewById(R.id.ed1);
        ed2 = (EditText)findViewById(R.id.ed2);
        ed3 = (EditText)findViewById(R.id.ed3);
        ed4 = (EditText)findViewById(R.id.ed4);
        ed5 = (EditText)findViewById(R.id.ed5);
        ed6 = (EditText)findViewById(R.id.ed6);
        ed7 = (EditText)findViewById(R.id.ed7);
        ed8 = (EditText)findViewById(R.id.ed8);
        ed9 = (EditText)findViewById(R.id.ed9);
        ed10 = (EditText)findViewById(R.id.ed10);
        ed11 = (EditText)findViewById(R.id.ed11);
        ed12 = (EditText)findViewById(R.id.ed12);
        ed13 = (EditText)findViewById(R.id.ed13);
        bed1 = (EditText)findViewById(R.id.bed1);
        bed2 = (EditText)findViewById(R.id.bed2);
        bed3 = (EditText)findViewById(R.id.bed3);
        bed4 = (EditText)findViewById(R.id.bed4);

        spinner = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);
        spinner4 = (Spinner)findViewById(R.id.spinner4);
        save = (Button)findViewById(R.id.but_save);
        qrcode = (ImageView)findViewById(R.id.qrcode);
        captureh = (ImageView)findViewById(R.id.captureh);

        l1 = (LinearLayout)findViewById(R.id.l1);
        l2 = (LinearLayout)findViewById(R.id.l2);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("fcode","");


        editor.commit();
        arrayn = new ArrayList<>();
        ed13.setText("India");
        ed12.setText("up");
        ed11.setText(sharedpreferences.getString("dis",""));
        ed10.setText(sharedpreferences.getString("tname",""));
        ed9.setText(sharedpreferences.getString("gname",""));
        ed8.setText(sharedpreferences.getString("vname",""));
        pawhsApplication = PAWHSApplication.getGetInstance();
        userid = pawhsApplication.getPreferenceFromString(this, ApiUtils.USER_ID);
        myCalendar = Calendar.getInstance();
        myCalendar2 = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, monthOfYear);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabel();
            }

        };
        ed5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FDR.this,android.R.style.Theme_Holo_Dialog , date, myCalendar
                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });
        captureh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {

                    picUri = FileProvider.getUriForFile(FDR.this, getApplicationContext().getPackageName() + ".provider", createImageFile());
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                    takePictureIntent.putExtra("return-data", true);
                    startActivityForResult(takePictureIntent, CAMERA_CAPTURE);// convert path to Uri
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1005);
                qrScan = new IntentIntegrator(FDR.this);
                qrScan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                qrScan.setPrompt("Scan Farmer Code");
                qrScan.setBeepEnabled(true);
                qrScan.setCameraId(0);
                qrScan.setOrientationLocked(false);
                qrScan.setBarcodeImageEnabled(true);


                qrScan.initiateScan();
            }
        });

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, atype);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tab);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);
        SQLiteDatabase dbs = db.getWritableDatabase();

        Cursor cursor = dbs.query("masterl", new String[]{"out_master_code","out_master_description"
                }, "out_depend_code" + "=? COLLATE NOCASE",
                new String[]{sharedpreferences.getString("vcode","")}, null, null, null, null);
        //  Toast.makeText(LoginActivity.this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
        if (cursor.getCount() != 0) {
            hamlet.add("Tap For Select Hamlet");

            if (cursor.moveToFirst()) {
                do {
                    hamlet.add(cursor.getString(1));


                } while (cursor.moveToNext());

                ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, hamlet);
                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner4.setAdapter(adapter4);
            }
        }

        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


                        Object item = parent.getItemAtPosition(pos);
                        //System.out.println(item.toString());

                        if(pos == 0)
                        {
                            l1.setVisibility(View.VISIBLE);
                            l2.setVisibility(View.GONE);
                            page=0;
                            if(dbb.equalsIgnoreCase("1"))
                            {
                                save.setEnabled(false);
                            }
                            else
                            {
                                save.setEnabled(true);
                            }
                        }
                        else
                        {
                            l1.setVisibility(View.GONE);
                            l2.setVisibility(View.VISIBLE);
                            page=1;
                            if(dbb2.equalsIgnoreCase("1"))
                            {
                                save.setEnabled(false);
                            }
                            else
                            {
                                save.setEnabled(true);
                            }
                        }


                        //prints the text in spinner item.

                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        bed2.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                {


                    dialog = new Dialog(FDR.this);
                    dialog.setContentView(R.layout.suppliersearch3);

                    dialog.setTitle("Title...");
                    int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                    int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                    final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                    recyclerView.setLayoutManager(new LinearLayoutManager(FDR.this));
                    adapterb = new MyRecyclerViewAdapterb(FDR.this, pojobankList);
                    dialog.getWindow().setLayout(width, height);
                    arrayn.clear();
                    final SQLiteDatabase dbs = db.getWritableDatabase();
                    final AutoCompleteTextView elc = (AutoCompleteTextView)dialog.findViewById(R.id.elc);
                    String selectQuery5 = "SELECT  * FROM bankm";
                    Cursor cc = dbs.rawQuery(selectQuery5, null);
                    if(cc.getCount()!=0) {
                        if (cc.moveToFirst()) {
                            do {

                                if(arrayn.contains(cc.getString(3)))
                                {

                                }
                                else
                                {
                                    arrayn.add(cc.getString(3));
                                }
                                // Log.e("VAL",""+cursor.getString(11));

                            } while (cc.moveToNext());
                        }
                    }


                    ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(FDR.this,
                            R.layout.spinnertext, arrayn);

                    elc.setAdapter(adapterlist2n);

                    elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick (AdapterView<?> parent, View view, int position, long id) {


                            pojobankList.clear();
                            // elc.setText(""+(String) parent.getItemAtPosition(position));

                            Cursor cursor = dbs.query("bankm", new String[]{"bank_code","bank_name","branch_name","ifsc_code"
                                    }, "bank_name" + "=? COLLATE NOCASE",
                                    new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
                            if(cursor.getCount()!=0)
                            {
                                if (cursor.moveToFirst()) {
                                    do {

                                        Pojobank pojobank = new Pojobank();

                                        pojobank.setBc(cursor.getString(0));
                                        pojobank.setBn(cursor.getString(1));
                                        pojobank.setBrn(cursor.getString(2));
                                        pojobank.setIfsc(cursor.getString(3));

                                        String dgp = cursor.getString(2);

                                        Log.e("NULL",""+dgp);
                                        // pojoloc.setS3(cursor.getString(1));

                                        pojobankList.add(pojobank);
                                        // array2.add(cursor.getString(11));
                                        // Log.e("VAL",""+cursor.getString(11));
                                        recyclerView.setAdapter(adapterb);

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
                //ADD HERE ABOUT CUT COPY PASTE
                // TODO Auto-generated method stub
                return false;
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(page == 0)
                {
                    if(ed1.getText().toString().equalsIgnoreCase(""))
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Farmer Name!")
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
                    else if(ed2.getText().toString().equalsIgnoreCase(""))
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Sur Name!")
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
                    else  if(ed3.getText().toString().equalsIgnoreCase(""))
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Father Name!")
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
                    else if(ed4.getText().toString().equalsIgnoreCase("")||ed4.getText().toString().length()<10)
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Mobile No/Invalid Mobile No!")
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
                    else if(spinner2.getSelectedItem().toString().replaceAll(" ","").equalsIgnoreCase("TapForSelectgender"))
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Gender!")
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
                    else  if(ed5.getText().toString().equalsIgnoreCase(""))
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Date Of Birth!")
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
//                    else  if(ttype.getText().toString().replaceAll(" ","").equalsIgnoreCase("TapForSelection"))
//                    {
//                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
////set icon
//                                .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                .setTitle("Error!")
////set message
//                                .setMessage("Empty Address Type!")
////set positive button
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                //set what would happen when positive button is clicked
//
//                                            }
//                                        }
////set negative button
//
//                                )
//                                .show();
//                    }
                    else  if(ed6.getText().toString().equalsIgnoreCase("")||ed6.getText().toString().length()<6)
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Pincode/Invalid Pincode!")
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
                    else if(ed8.getText().toString().equalsIgnoreCase(""))
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Village!")
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
                    else if(ed9.getText().toString().equalsIgnoreCase(""))
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Grama Panchayat!")
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
                    else if(ed10.getText().toString().equalsIgnoreCase(""))
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Taluk!")
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
                    else  if(ed11.getText().toString().equalsIgnoreCase(""))
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty District!")
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
                    else if(ed7.getText().toString().equalsIgnoreCase(""))
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Address!")
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
                    else if(spinner4.getSelectedItem().toString().replaceAll(" ","").equalsIgnoreCase("TapForSelecthamlet"))
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Hamlet!")
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
                        if(isNetworkAvailable())
                        {
                            farmersave();
                        }
                        else
                        {
                            Long tsLong = System.currentTimeMillis() / 1000;
                            String ts = tsLong.toString();
                            SharedPreferences.Editor editor = sharedpreferences.edit();

                            editor.putString("fcode", ts);
                            editor.putString("fid", "0");
                            String fan = "0";
                            int faid = Integer.parseInt("0");

                            String n1 = ed1.getText().toString();
                            String n2 = ed2.getText().toString();
                            String n3 = ed3.getText().toString();
                            String n4 = ed4.getText().toString();
                            String n5 = spinner2.getSelectedItem().toString();
                            String n6 = ed5.getText().toString();
                            String n7 = "";
                            String n8 = ed6.getText().toString();
                            String n9 = ed7.getText().toString();
                            String n10 = ed8.getText().toString();
                            String n11 = ed9.getText().toString();
                            String n12 = ed10.getText().toString();
                            String n13 = ed11.getText().toString();
                            String n14 = "up";
                            String n15 = "India";
                            String n16 = "0";
                            String n17 = "0";
                            String n18 = sharedpreferences.getString("vcode","");
                            String n19 = sharedpreferences.getString("gcode","");
                            String n20 = sharedpreferences.getString("tcode","");
                            String n21 = "QCD_UND_"+sharedpreferences.getString("dis","");
                            String n22 = ts;
                            String n23 = spinner4.getSelectedItem().toString();
                            String n25 = sharedpreferences.getString("oc1","");
                            String n26 = "";
                            String n27 = "";


                            try {
                                db.insertfarmer(n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16,n17,n18,n19,n20,n21,n22,n23,n25,n26,n27,ui);

                                FormerDao formerDao = new FormerDao(1, "0",
                                        "DUMFRMMOB/"+n22, "Non-Member", n3,
                                        n1, n21, n13,
                                        n20, n12, n19,
                                        n11, n18, n10);
                                db.addAllFarmerDetails(formerDao);
                                dbb = "1";
                                ed1.setEnabled(false);
                                ed2.setEnabled(false);
                                ed3.setEnabled(false);
                                ed4.setEnabled(false);
                                ed5.setEnabled(false);
                                ed6.setEnabled(false);
                                ed7.setEnabled(false);
                                ed8.setEnabled(false);
                                ed9.setEnabled(false);
                                ed10.setEnabled(false);
                                ed11.setEnabled(false);
                                ed12.setEnabled(false);
                                ed13.setEnabled(false);
                                spinner2.setEnabled(false);
                                save.setEnabled(false);
                                editor.commit();
                                final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                        .setIcon(android.R.drawable.ic_menu_save)
//set title
                                        .setTitle("Success!")
//set message
                                        .setMessage("Farmer Saved Locally!")
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
                            } catch (SQLiteException exception) {
                                Log.d("SQLite", "Error"+exception.toString());
                                final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Alert!")
//set message
                                        .setMessage("Farmer Detail Already Found!")
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked


                                                        // hjjh

                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                            }



                        }
                    }

                }
                else
                {

                    if (spinner3.getSelectedItem().toString().replaceAll(" ", "").equalsIgnoreCase("tapforselectaccounttype")) {


                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Account Type!")
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

                    } else if (bed1.getText().toString().equalsIgnoreCase("")) {

                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Account No!")
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

                    } else if (bed3.getText().toString().equalsIgnoreCase("")) {

                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Bank!")
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

                    } else if (bed4.getText().toString().equalsIgnoreCase("")) {

                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Empty Branch!")
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
                        if(isNetworkAvailable())
                        {

                            if(sharedpreferences.getString("fcode","").equalsIgnoreCase(""))
                            {
                                final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Please Fill and Save Header Details First!")
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
                                //Toast.makeText(FDR.this, "Hi", Toast.LENGTH_SHORT).show();
                                savebank(sharedpreferences.getString("fcode",""), 0,bed1.getText().toString(),spinner3.getSelectedItem().toString() , bcode, bed4.getText().toString(), bed1.getText().toString(), "QCD_YES", "QCD_YES","");

                            }
                        }
                        else
                        {
                            if(sharedpreferences.getString("fcode","").equalsIgnoreCase(""))
                            {
                                final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Please Fill and Save Header Details First!")
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
                            else {

                                db.insertbank(spinner3.getSelectedItem().toString(), bed1.getText().toString(), bed3.getText().toString(), bed2.getText().toString(), bed4.getText().toString(), "QCD_YES", "QCD_YES", "QCD_YES", sharedpreferences.getString("fcode", ""), "0", 0, "", bcode);
                                spinner3.setEnabled(false);
                                bed1.setEnabled(false);
                                bed2.setEnabled(false);
                                bed3.setEnabled(false);
                                bed4.setEnabled(false);
                                dbb2="1";
                                save.setEnabled(false);

                                final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                        .setIcon(android.R.drawable.ic_menu_save)
//set title
                                        .setTitle("Success!")
//set message
                                        .setMessage("Bank Added Locally!")
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked

                                                        // hjjh

                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                            }

                        }
                    }


                }

            }
        });
    }
    private File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        imageFilePath = image.getAbsolutePath();

        return image;
    }
    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(FDR.this);
    }
    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
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

            if(requestCode == 49374)
            {


////                dialog = new Dialog(FDR.this);
////                Log.e("RCODE",""+data.getStringExtra("SCAN_RESULT"));
////                String scanvalue = data.getStringExtra("SCAN_RESULT");
////                loadFarmer(scanvalue,"","");
                IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                if (scanningResult != null) {
                    String scanContent = scanningResult.getContents();
                    String scanFormat = scanningResult.getFormatName();
                    // process received data
                    processScannedData(scanContent);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
            if (requestCode == CAMERA_CAPTURE) {
                Uri uri = picUri;
                Log.d("picUri", picUri.toString());
                // performCrop();
                startCropImageActivity(uri);
            } else if (requestCode == 2) {

                Bundle extras = data.getExtras();
//get the cropped bitmap
                thePic = (Bitmap) extras.get("data");
                Log.e("PIC", "" + thePic);
                captureh.setImageResource(0);
                captureh.setImageBitmap(thePic);
                //vcap.setImageBitmap(thePic);


                byteArrayOutputStream = new ByteArrayOutputStream();
                thePic.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
                ui = thePic.toString();
                Log.e("JJJJJJ", "" + ui);
                try {
                    encodedImage = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {

                    try {


                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.getUri());
                        captureh.setImageResource(0);
                        captureh.setImageBitmap(bitmap);
                        // vcap.setImageBitmap(bitmap);
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        ui = result.getUri().toString();

                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());


                        encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                        Log.e("PIC", "" + encodedImage);
                        FileOutputStream fos = null;



//
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //  Log.e("PIC", "" + thePic);

//
                    //((ImageView) findViewById(R.id.img)).setImageURI(result.getUri());
                    // Toast.makeText(this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG).show();
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
                }
            }

        }

    }
    protected void processScannedData(String scanData){
        Log.d("Rajdeol",scanData);
        XmlPullParserFactory pullParserFactory;
        try {
            // init the parserfactory
            pullParserFactory = XmlPullParserFactory.newInstance();
            // get the parser
            XmlPullParser parser = pullParserFactory.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            String fstring2 = null;
            try {


                String scan = scanData.replaceAll("<PrintLetterBarcodeData", "Start|ADS|").replaceAll("/>", "}");
                String[] fstring = scan.split("Start");
                fstring2 = fstring[1].replace("|ADS|", "{").replaceFirst(" ", "").replaceAll(" ", ",");
                Log.e("Help", "" + fstring2);
            }
            catch(Exception e)
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Unable To Read!")
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

            try {

                final JSONObject obj = new JSONObject(fstring2);

                String tx1=null,tx2 = null,tx3 = null,tx4=null,tx5 = null,tx6=null,tx7=null,tx8=null,tx9,tx10=null,tx11=null,tx12=null;

                Log.e("AAdhar", obj.toString());
//                Log.e("AAdhar",""+obj.getString("uid"));
//                Log.e("AAdhar",""+obj.getString("name"));
//                Log.e("AAdhar",""+obj.getString("gender"));
//                Log.e("AAdhar",""+obj.getString("yob"));
//                    Log.e("AAdhar",""+obj.getString("co"));
//                    Log.e("AAdhar",""+obj.getString("vtc"));
//                    Log.e("AAdhar",""+obj.getString("po"));
//                    Log.e("AAdhar",""+obj.getString("dist"));
//                    Log.e("AAdhar",""+obj.getString("state"));
//                    Log.e("AAdhar",""+obj.getString("pc"));
                tx1 = obj.getString("name");

                try {
                    String v = obj.getString("co").substring(3);
                    tx2 = v;
                }
                catch(Exception e)
                {
                    tx2="";
                }

                try {
                    tx5 = obj.getString("po");
                }
                catch(Exception e)
                {
                    tx5="";
                }
                try {
                    tx6 = obj.getString("pc");
                }
                catch(Exception e)
                {
                    tx6="";
                }
                try {
                    tx7 = obj.getString("dist");
                }
                catch(Exception e)
                {
                    tx7="";
                }
                try {
                    tx8 = obj.getString("state");
                }
                catch(Exception e)
                {
                    tx8="";
                }
                tx9 = obj.toString();

                try
                {
                    tx4 = obj.getString("house")+","+obj.getString("street");
                    Log.e("AAdhar",""+obj.getString("house")+","+obj.getString("street"));
                }
                catch(Exception e)
                {
                    tx4="";
                }
                try
                {
                    Log.e("AAdhar",""+obj.getString("lm"));
                }
                catch(Exception e)
                {

                }
                try
                {
                    if(obj.getString("dob").contains("-"))
                    {
                        final String[] dob = obj.getString("dob").split("-");
                        tx3 = dob[0]+"/"+dob[1]+"/"+dob[2];

                        ed5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DatePickerDialog datePickerDialog = new DatePickerDialog(FDR.this,android.R.style.Theme_Holo_Dialog , date, myCalendar
                                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                                        myCalendar2.get(Calendar.DAY_OF_MONTH));
                                datePickerDialog.updateDate(Integer.parseInt(dob[2]),Integer.parseInt(dob[1])-1,Integer.parseInt(dob[0]));

                                datePickerDialog.show();

                            }
                        });
                    }
                    else
                    {
                        final String[] dob = obj.getString("dob").split("/");
                        tx3 = dob[0]+"/"+dob[1]+"/"+dob[2];

                        ed5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DatePickerDialog datePickerDialog = new DatePickerDialog(FDR.this,android.R.style.Theme_Holo_Dialog , date, myCalendar
                                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                                        myCalendar2.get(Calendar.DAY_OF_MONTH));
                                datePickerDialog.updateDate(Integer.parseInt(dob[2]),Integer.parseInt(dob[1])-1,Integer.parseInt(dob[0]));

                                datePickerDialog.show();

                            }
                        });
                    }

                    Log.e("AAdhar",""+obj.getString("dob"));
                }
                catch(Exception e)
                {


                    ed5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DatePickerDialog datePickerDialog = new DatePickerDialog(FDR.this,android.R.style.Theme_Holo_Dialog , date, myCalendar
                                    .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                                    myCalendar2.get(Calendar.DAY_OF_MONTH));
                            try {
                                datePickerDialog.updateDate(Integer.parseInt(obj.getString("yob")),0,Integer.parseInt("01"));
                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }
                            datePickerDialog.show();

                        }
                    });

                    tx3="01/01/"+obj.getString("yob");
                }
                try {
                    tx10 = obj.getString("gender");
                }
                catch(Exception e)
                {
                    tx10="Tap To Select Gender";
                }
                try {
                    tx11 = obj.getString("gname");
                }
                catch(Exception e)
                {
                    tx11="";
                }
                try {
                    tx12 = obj.getString("lm");
                }
                catch(Exception e)
                {
                    tx12="";
                }
                showaadhar(tx1,tx2,tx3,tx4,tx5,tx6,tx7,tx8,tx9,tx10,tx11,tx12);


            } catch (Throwable t) {
                Log.e("Help", "Could not parse malformed JSON: \"" + fstring2 + "\"");
            }




            parser.setInput(new StringReader(scanData));




            // display the data on screen
            //  displayScannedData();
        } catch (XmlPullParserException e) {

            Log.e("AAdhar",""+e);
            e.printStackTrace();
        }
    }//

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        ed5.setText(sdf.format(myCalendar2.getTime()));

    }
    public void  showaadhar(String tx1,String tx2,String tx3,String tx4,String tx5,String tx6,String tx7,String tx8,String tx9,String tx10,String tx11,String tx12)
    {

        final String n1 = tx1.replaceAll(","," ");
        final String n2 = tx2.replaceAll(","," ");
        final  String n3 = tx3.replaceAll(","," ");
        final String n6 = tx6.replaceAll(","," ");
        final String n10 = tx10.replaceAll(","," ");
        final String n11 = tx11.replaceAll(","," ");
        final String n4 = tx4.replaceAll(","," ");
        final String n5 = tx5.replaceAll(","," ");
        final String n7 = tx7.replaceAll(","," ");
        final String n12 = tx12.replaceAll(","," ");
        if(n5.equalsIgnoreCase(sharedpreferences.getString("vname",""))) {
            ed7.setText(n4 + " " + n12 + " " + n5 + " " + n7);
            ed1.setText(n1);
            if (n2.equalsIgnoreCase("")) {
                ed3.setText(n11);
            } else {
                ed3.setText(n2);
            }
            ed6.setText(n6);
            ed5.setText(n3);
            if (n10.equalsIgnoreCase("M") || n10.equalsIgnoreCase("Male")) {
                spinner2.setSelection(1);
            } else {
                spinner2.setSelection(2);
            }


            // startActivity(i);

        }
        else
        {
            final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                    .setTitle("Alert!")
//set message
                    .setMessage("Scanned Farmer's Village Does Not Match With Our Selected Village Shall we allow to modify it?")
//set positive button
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //set what would happen when positive button is clicked

                                    ed7.setText(n4 + " " + n12 + " " + n5 + " " + n7);
                                    ed1.setText(n1);
                                    if (n2.equalsIgnoreCase("")) {
                                        ed3.setText(n11);
                                    } else {
                                        ed3.setText(n2);
                                    }
                                    ed6.setText(n6);
                                    ed5.setText(n3);
                                    if (n10.equalsIgnoreCase("M") || n10.equalsIgnoreCase("Male")) {
                                        spinner2.setSelection(1);
                                    } else {
                                        spinner2.setSelection(2);
                                    }
                                    // hjjh

                                }
                            }
//set negative button

                    )
                    .show();
        }


    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void farmersave()
    {

        pdialog = new ProgressDialog(FDR.this);


        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();

        try {


            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", "flexi");
            user.put("locnId", "chennai");
            user.put("userId", "fdrmob");
            user.put("localeId", "en_US");
            user.put("instance", "up");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("in_farmer_rowid",0);
            user2.put("in_farmer_code","0");
            user2.put("in_version_no",1);
            user2.put("in_photo_farmer",encodedImage);

            user2.put("in_farmer_name",ed1.getText().toString());
            user2.put("in_sur_name",ed2.getText().toString());
            user2.put("in_fhw_name",ed3.getText().toString());
            String[] dob = ed5.getText().toString().split("/");
            user2.put("in_farmer_dob",dob[2]+"/"+dob[1]+"/"+dob[0]);
            user2.put("in_farmer_addr1",ed7.getText().toString());
            user2.put("in_farmer_addr2",spinner4.getSelectedItem().toString());
            user2.put("in_farmer_country","QCD_UN_IND");
            user2.put("in_farmer_state","QCD_UNS_UP");

            user2.put("in_farmer_dist","QCD_UND_"+sharedpreferences.getString("dis",""));
            user2.put("in_farmer_taluk",sharedpreferences.getString("tcode",""));
            user2.put("in_farmer_panchayat",sharedpreferences.getString("gcode",""));
            user2.put("in_farmer_village",sharedpreferences.getString("vcode",""));
            user2.put("in_farmer_pincode",ed6.getText().toString());
            user2.put("in_marital_status","");

            user2.put("in_gender_flag",spinner2.getSelectedItem().toString());
            user2.put("in_reg_mobile_no",ed4.getText().toString());
            user2.put("in_status_code","A");
            user2.put("in_mode_flag","I");
            user2.put("in_row_timestamp","");
            user2.put("in_created_by", userid);
            user2.put("in_modified_by", userid);


            user.put("Header",user2);
            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
        }



//
        //169.38.77.190:101

        Log.e("URL",""+ApiUtils.TEST_URL_API+"Mobile_FDR_FHeader/NewMobileFarmerHeader");

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, ApiUtils.TEST_URL_API+"Mobile_FDR_FHeader/NewMobileFarmerHeader",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {
                            pdialog.dismiss();
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("header");



                            final String frid = obj2.getString("in_farmer_rowid");
                            final String fn = obj2.getString("in_farmer_code");

                            if(frid.equalsIgnoreCase("0"))
                            {
                                JSONObject obj3 = response.getJSONObject("applicationException");
                                final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Alert!")
//set message
                                        .setMessage(""+obj3.getString("errorDescription"))
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked


                                                        // hjjh

                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                            }
                            else
                            {
                                // fname.setText("Farmername:"+fn);
                                SharedPreferences.Editor editor = sharedpreferences.edit();

                                editor.putString("fcode", fn);
                                editor.putString("fid", frid);
                                String  fan = fn;
                                int  faid = Integer.parseInt(frid);

                                String n1 = ed1.getText().toString();
                                String n2 = ed2.getText().toString();
                                String n3 = ed3.getText().toString();
                                String n4 = ed4.getText().toString();
                                String n5 = spinner2.getSelectedItem().toString();
                                String n6 = ed5.getText().toString();
                                String n7 = "";
                                String n8 = ed6.getText().toString();
                                String n9 = ed7.getText().toString();
                                String n10 = ed8.getText().toString();
                                String n11 = ed9.getText().toString();
                                String n12 = ed10.getText().toString();
                                String n13 = ed11.getText().toString();
                                String n14 = "up";
                                String n15 = "India";
                                String n16 = "1";
                                String n17 = "0";
                                String n18 = sharedpreferences.getString("vcode","");
                                String n19 = sharedpreferences.getString("gcode","");;
                                String n20 = sharedpreferences.getString("tcode","");;
                                String n21 = "QCD_UND_"+sharedpreferences.getString("dis","");;
                                String n22 = frid;
                                String n23 = "";

                                editor.commit();

                                FormerDao formerDao = new FormerDao(1, frid,
                                        fn, "Non-Member", n3,
                                        n1, n21, n13,
                                        n20, n12, n19,
                                        n11, n18, n10);
                                db.addAllFarmerDetails(formerDao);

                                dbb = "1";
                                ed1.setEnabled(false);
                                ed2.setEnabled(false);
                                ed3.setEnabled(false);
                                ed4.setEnabled(false);
                                ed5.setEnabled(false);
                                ed6.setEnabled(false);
                                ed7.setEnabled(false);
                                ed8.setEnabled(false);
                                ed9.setEnabled(false);
                                ed10.setEnabled(false);
                                ed11.setEnabled(false);
                                ed12.setEnabled(false);
                                ed13.setEnabled(false);
                                spinner2.setEnabled(false);
                                save.setEnabled(false);
                                final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                        .setIcon(android.R.drawable.ic_menu_save)
//set title
                                        .setTitle("Success!")
//set message
                                        .setMessage("Farmer Succesfully Saved ! Farmer Code:"+fn)
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        // finish();
                                                        //modeflag = "U";
                                                        spinner.setEnabled(true);

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
                            final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Error Inserting Farmer")
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
                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Error Inserting Farmer")
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
    public class MyRecyclerViewAdapterb extends RecyclerView.Adapter<MyRecyclerViewAdapterb.ViewHolder> {

        private List<Pojobank> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapterb(Context context, List<Pojobank> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapterb.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.suplist3, parent, false);
            return new MyRecyclerViewAdapterb.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(final MyRecyclerViewAdapterb.ViewHolder holder, final int position) {
            final Pojobank pojobank = mData.get(position);

            holder.name.setText(pojobank.getBc());
            holder.ph.setText(pojobank.getBn());
            holder.lcn.setText(pojobank.getBrn());
            holder.ty.setText(pojobank.getIfsc());
            // holder.llist.setBackgroundResource(R.drawable.rbutton);
            holder.llist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //holder.llist.setBackgroundResource(R.drawable.rbutton2);

                    bcode = pojobank.getBc();
                    bed4.setText(pojobank.getBn());
                    bed3.setText(pojobank.getBrn());
                    bed2.setText(pojobank.getIfsc());
                    dialog.dismiss();


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
            TextView myTextView,trate,tamt,tnamt,tdis,tqty,name,ph,lcn,ty;
            ImageView del,ed;
            LinearLayout llist;

            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.bc);
                ph = itemView.findViewById(R.id.bn);
                lcn = itemView.findViewById(R.id.brn);
                ty = itemView.findViewById(R.id.ifsc);
                llist = itemView.findViewById(R.id.llist);





            }


        }

        // convenience method for getting data at click position




        // parent activity will implement this method to respond to click events

    }


    public  void savebank(String fc, int id, String an, String at, String bc, String be, String ifc, String dd, String dc, final String idk)
    {

        pdialog = new ProgressDialog(FDR.this);

        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();

        try {


            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", "flexi");
            user.put("locnId", "chennai");
            user.put("userId", "fdrmob");
            user.put("localeId", "en_US");
            user.put("instance", "up");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("in_farmer_code",fc);
            user2.put("in_farmerbank_rowid",0);
            user2.put("in_bank_acc_no",an);
            user2.put("in_bank_acc_type",at);

            user2.put("in_bank_code",bc);
            user2.put("in_branch_code",be);
            user2.put("in_ifsc_code",ifc);
            user2.put("in_dflt_dr_acc",dd);
            user2.put("in_dflt_cr_acc",dc);
            user2.put("in_status_code","A");
            user2.put("in_mode_flag","I");
            user2.put("in_created_by", userid);
            user2.put("in_modified_by", userid);



            user.put("Bank",user2);
            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
        }





        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,ApiUtils.TEST_URL_API+"Mobile_FDR_FHeader/NewMobileFarmerBank",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {
                            pdialog.dismiss();
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("bank");
                            final String frid = obj2.getString("in_farmerbank_rowid");
                            Log.e("CCCC", "" + frid);

                            if(frid.equalsIgnoreCase("0")) {


                                pdialog.dismiss();
                                final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Error Inserting Bank")
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
                            else
                            {
                                spinner3.setEnabled(false);
                                bed1.setEnabled(false);
                                bed2.setEnabled(false);
                                bed3.setEnabled(false);
                                bed4.setEnabled(false);
                                dbb2="1";
                                save.setEnabled(false);






                                final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                        .setIcon(android.R.drawable.ic_menu_save)
//set title
                                        .setTitle("Success!")
//set message
                                        .setMessage("Bank Succesfully Added !")
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
                            final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Error Inserting Bank")
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
                        final AlertDialog alertDialog = new AlertDialog.Builder(FDR.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Error Inserting Bank")
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

}