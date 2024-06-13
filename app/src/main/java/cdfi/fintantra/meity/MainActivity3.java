package cdfi.fintantra.meity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
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
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.zxing.integration.android.IntentResult;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cdfi.fintantra.meity.Pawhs.SQLiteDataBaseHandler;
import cdfi.fintantra.meity.Pawhs.formermodel.FormerDao;
import cdfi.fintantra.meity.Pawhs.utils.MyConstants;

public class MainActivity3 extends AppCompatActivity {

    EditText sdt1,sdt2,sdt3,sdt4,sdt5,vtilldate;
    AutoCompleteTextView cyears,stmnt,ccty2s,stclf,ccts,cvars,stsnm;
    Button ssave,sview,exit;
    String sowsn;
    String usp;
    String otps;
    String[] tfs;
    ImageView lefta,righta,consent;
    String codes,codem,codecr,codecn,codesn;
    Button addnew;
    String dubflag="N";
    DatePickerDialog.OnDateSetListener date;
    DatePickerDialog.OnDateSetListener date2;
    DatePickerDialog.OnDateSetListener date3;
    IntentIntegrator qrScan;
    int sowid;
    ImageView qrcode;
    String laid,laslno;
    private GpsTracker gpsTracker;
    AutoCompleteTextView tlt,tow,soiltype,tis,landvillage;
    EditText elnoa,elat,elon;
    ImageView captureld;
    RadioButton radioButton1,radioButton2;
    RadioGroup radioGroup;
    int pg=0;

    int ul=0;
    Button lsave,lview;

    TextView txtheader,dialogtext;
    ImageView im1,im2,im3,im4,im5,captureh,capturek;
    View v1,v2,v3,v4,v5;
    String ksty;
    int  imagetype;
    AutoCompleteTextView gender,kttype,kstype;
    Button bview,save,ksave,kview;
    JSONObject jsonParam;
    int ub = 0;
    int uk =0;
    DBHelper db;
    SQLiteDataBaseHandler dbpa;
    String bankid;
    String kycrid;
    int us = 0;
    String fan;
    String sdc = "QCD_YES";
    String sdd = "QCD_YES";
    LinearLayout linear;
    int bstatus;
    String kycid;
    String farmerid;
    String bcode;
    String kty;
    EditText accountno, eifsc, ebank, ebranch;
    final int CAMERA_CAPTURE = 1;
    private static final int CAMERA_REQUEST = 1888;
    EditText estate,farmername, surname, fathername, mobileno, pincode, edob, eaddress,taluk, grama, district,hamlet,documentno,cdocumentno;
    AutoCompleteTextView village,elc;
    RelativeLayout rc;
    String[] genders = {"Male", "Female", "Transgender"};
    //String[] actype = {"ID", "Address", "Age"}
    String[] actype = {},actypecode = {};
    //String[] acsubtype = {"Pan card", "Voter Id","Driving Licence","Passport"};
    String[] acsubtype = {},acsubtypecode = {};
    LinearLayout l1,l2,l3,l4,l5;
    String mv, md, mg, mt,ham;
    int faid = 0;
    Button bsave;
    String bbid;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    String ui = "0", ui2 = "0",ui3="0";
    String encodedImage = "0", encodedImage2 = "0",encodedImage3="0";
    ByteArrayOutputStream byteArrayOutputStream, byteArrayOutputStream2,byteArrayOutputStream3;
    String imageFilePath, imageFilePath2;
    Bitmap thePic, thePic2,thePic3;
    Uri picUri, picUri2;
    Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
    String regex
            = "^[A-PR-WYa-pr-wy][1-9]\\d"
            + "\\s?\\d{4}[1-9]$";
    Pattern pp = Pattern.compile(regex);
    ProgressDialog pdialog;
    Dialog dialog;
    MyRecyclerViewAdapterb adapterb;
    List<Pojoloc> pojolocList;
    Calendar myCalendar,myCalendar2,cal3;
    ArrayList<String> array3 = new ArrayList<>();
    ArrayList<String> array4 = new ArrayList<>();
    ArrayList<String> array5 = new ArrayList<>();
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
    ArrayList<String> gendername = new ArrayList<>();
    ArrayList<String> gendercode = new ArrayList<>();
    MainActivity3.MyRecyclerViewAdapterd adapterd;
    SQLiteDatabase dbs,dbspaw;
    String modeflag = "I";
    ArrayList<String> arrayn;
    MyRecyclerViewAdapter adapter;
    List<Pojokyc> listitem;
    List<Pojobank> pojobankList;
    TextView title;
    String farmercodes="";
    String fpoorgncodes="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_main3);
        getSupportActionBar().hide();
        farmername = (EditText)findViewById(R.id.farmername);
        surname = (EditText)findViewById(R.id.surname);
        fathername = (EditText)findViewById(R.id.fathername);
        mobileno = (EditText)findViewById(R.id.mobileno);
        pincode = (EditText)findViewById(R.id.pincode);
        edob = (EditText)findViewById(R.id.dob);
        consent = findViewById(R.id.consent);
        eaddress = (EditText)findViewById(R.id.address);
        village = (AutoCompleteTextView)findViewById(R.id.village);
        kttype = (AutoCompleteTextView)findViewById(R.id.kttype);
        kstype = (AutoCompleteTextView)findViewById(R.id.kstype);
        taluk = (EditText) findViewById(R.id.taluk);
        documentno = (EditText) findViewById(R.id.documentno);
        cdocumentno = (EditText) findViewById(R.id.cdocumentno);
        estate = (EditText) findViewById(R.id.estate);
        grama = (EditText) findViewById(R.id.grama);
        vtilldate = (EditText) findViewById(R.id.vtilldate);
        district = (EditText) findViewById(R.id.district);
        hamlet = (EditText) findViewById(R.id.hamlet);
        captureh = (ImageView)findViewById(R.id.captureh);
        capturek = (ImageView)findViewById(R.id.capturek);
        txtheader = (TextView)findViewById(R.id.txtheader);
        pdialog = new ProgressDialog(this);
        linear = (LinearLayout)findViewById(R.id.linear);
        accountno = (EditText)findViewById(R.id.accountno);


        eifsc = (EditText)findViewById(R.id.eifsc);
        ebank = (EditText)findViewById(R.id.ebank);
        ebranch = (EditText)findViewById(R.id.ebranch);
        bsave = (Button)findViewById(R.id.bsave);
        listitem = new ArrayList<>();
        save = (Button)findViewById(R.id.save);
        ksave = (Button)findViewById(R.id.ksave);
        kview = (Button)findViewById(R.id.kview);
        lefta = (ImageView)findViewById(R.id.lefta);
        righta = (ImageView)findViewById(R.id.righta);
        exit = (Button)findViewById(R.id.exit);

        consent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyConstants.consentflag = "1";
                Intent intent = new Intent(getApplicationContext(),Consent.class);
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity3.this);
                builder1.setMessage("Make sure to save before exit");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });



        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if(sharedpreferences.getString("lo","").equalsIgnoreCase("QCD_UNS_TAMIL"))
        {
            estate.setText("Tamil Nadu");
        }
        else
        {
            estate.setText("UP");
        }

        title = (TextView)findViewById(R.id.title) ;
        title.setText(""+sharedpreferences.getString("rn",""));
        db =new  DBHelper(this);
        dbpa = new SQLiteDataBaseHandler(this);
        dbs = db.getWritableDatabase();
        dbspaw = dbpa.getWritableDatabase();
        myCalendar2 = Calendar.getInstance();
        qrcode = (ImageView)findViewById(R.id.qrcode);
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1005);
                qrScan = new IntentIntegrator(MainActivity3.this);
                qrScan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                qrScan.setPrompt("Scan Farmer Code");
                qrScan.setBeepEnabled(true);
                qrScan.setCameraId(0);
                qrScan.setOrientationLocked(false);
                qrScan.setBarcodeImageEnabled(true);


                qrScan.initiateScan();
            }
        });
        sdt1 = (EditText)findViewById(R.id.sdt1);
        sdt2 = (EditText)findViewById(R.id.sdt2);
        sdt3 = (EditText)findViewById(R.id.sdt3);
        sdt4 = (EditText)findViewById(R.id.sdt4);
        sdt5 = (EditText)findViewById(R.id.sdt5);
        sdt1.setFilters(new InputFilter[] {new DecimalDigitsInputFilter2(6,2)});
        sdt2.setFilters(new InputFilter[] {new DecimalDigitsInputFilter2(6,2)});
        sdt3.setFilters(new InputFilter[] {new DecimalDigitsInputFilter2(6,2)});
        sdt4.setFilters(new InputFilter[] {new DecimalDigitsInputFilter2(6,2)});
        sdt5.setFilters(new InputFilter[] {new DecimalDigitsInputFilter2(6,2)});
        cyears = (AutoCompleteTextView)findViewById(R.id.cyears);
        stmnt = (AutoCompleteTextView)findViewById(R.id.stmnt);
        ccty2s = (AutoCompleteTextView)findViewById(R.id.ccty2s);
        stclf = (AutoCompleteTextView)findViewById(R.id.stclf);
        ccts = (AutoCompleteTextView)findViewById(R.id.ccts);
        cvars = (AutoCompleteTextView)findViewById(R.id.cvars);
        stsnm = (AutoCompleteTextView)findViewById(R.id.stsnm);

        ssave = (Button)findViewById(R.id.ssave);
        sview = (Button)findViewById(R.id.sview);

        tlt = (AutoCompleteTextView)findViewById(R.id.tlt);
        tow = (AutoCompleteTextView)findViewById(R.id.tow);
        soiltype = (AutoCompleteTextView)findViewById(R.id.soiltype);
        landvillage = (AutoCompleteTextView)findViewById(R.id.landvillage);
        tis = (AutoCompleteTextView)findViewById(R.id.tis);

        elnoa = (EditText)findViewById(R.id.elnoa);
        elat = (EditText)findViewById(R.id.elat);
        elon = (EditText)findViewById(R.id.elon);

        lsave = (Button)findViewById(R.id.lsave) ;
        lview = (Button)findViewById(R.id.lview) ;

        radioButton1 = (RadioButton)findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton)findViewById(R.id.radioButton2);
        captureld = (ImageView) findViewById(R.id.captureld);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int maxLengthll = 10;
        elnoa.setFilters(new InputFilter[] {new DecimalDigitsInputFilter2(10,2)});


        im1 = (ImageView)findViewById(R.id.im1);
        im2 = (ImageView)findViewById(R.id.im2);
        im3 = (ImageView)findViewById(R.id.im3);
        im4 = (ImageView)findViewById(R.id.im4);
        im5 = (ImageView)findViewById(R.id.im5);

        v1 = (View) findViewById(R.id.v1);
        v2 = (View) findViewById(R.id.v2);
        v3 = (View) findViewById(R.id.v3);
        v4 = (View) findViewById(R.id.v4);
        v5 = (View) findViewById(R.id.v5);
        arrayn = new ArrayList<>();
        pojolocList = new ArrayList<>();
        pojobankList = new ArrayList<>();
        l1 = (LinearLayout)findViewById(R.id.l1);
        l2 = (LinearLayout)findViewById(R.id.l2);
        l3 = (LinearLayout)findViewById(R.id.l3);
        l4 = (LinearLayout)findViewById(R.id.l4);
        l5 = (LinearLayout)findViewById(R.id.l5);
        bview = (Button)findViewById(R.id.bview);
        documentno.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                // cdocumentno.setText(s);
                if(s.length() == 10)
                {
//                    if(kstype.getText().toString().equalsIgnoreCase("Pan Card"))
                    if(ksty.equalsIgnoreCase("QCD_PRFI_PAN") || ksty.equalsIgnoreCase("QCD_PRFG_PAN") )
                    {
                        Matcher matcher = pattern.matcher(s);
                        if (matcher.matches()) {
                            Log.i("Matching","Yes");
                        }
                        else
                        {
                            documentno.setText("");
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Invalid Pan Number!")
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
                else if(s.length() == 8)
                {
//                    if(kstype.getText().toString().equalsIgnoreCase("Passport"))
                    if(ksty.equalsIgnoreCase("QCD_PRFI_PASS") || ksty.equalsIgnoreCase("QCD_PRFA_PASS") || ksty.equalsIgnoreCase("QCD_PRFG_PASS"))
                    {
                        Matcher matcher = pp.matcher(s);
                        if (matcher.matches()) {
                            Log.i("Matching","Yes");
                        }
                        else
                        {
                            documentno.setText("");
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Invalid Passport No!")
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

        kstype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selecteditem = acsubtypecode[i];
                Log.e("selectedsubtype",selecteditem);
                ksty = selecteditem;
                if(selecteditem.equalsIgnoreCase("QCD_PRFA_AADHAR") || selecteditem.equalsIgnoreCase("QCD_PRFG_AADHAR")){
                    cdocumentno.setEnabled(true);
                    documentno.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    cdocumentno.setInputType(InputType.TYPE_CLASS_NUMBER);
                    int maxLength = 12;
                    documentno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                    cdocumentno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                }else if(selecteditem.equalsIgnoreCase("QCD_PRFI_PAN") || selecteditem.equalsIgnoreCase("QCD_PRFG_PAN")){
                    int maxLength = 10;
                    documentno.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
                    cdocumentno.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
                    documentno.setInputType(InputType.TYPE_CLASS_TEXT);
                    cdocumentno.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                else if(selecteditem.equalsIgnoreCase("QCD_PRFI_PASS") || selecteditem.equalsIgnoreCase("QCD_PRFA_PASS") || selecteditem.equalsIgnoreCase("QCD_PRFG_PASS")){
                    int maxLength = 8;
                    documentno.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
                    documentno.setInputType(InputType.TYPE_CLASS_TEXT);
                    cdocumentno.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                else if(selecteditem.equalsIgnoreCase("QCD_PRFI_VOTER") || selecteditem.equalsIgnoreCase("QCD_PRFA_VOTER") || selecteditem.equalsIgnoreCase("QCD_PRFG_VOTER"))
                {
                    int maxLength = 19;
                    documentno.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
                    documentno.setInputType(InputType.TYPE_CLASS_TEXT);
                    cdocumentno.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                else if(selecteditem.equalsIgnoreCase("QCD_PRFI_DRIVE") || selecteditem.equalsIgnoreCase("QCD_PRFA_DRIVE") || selecteditem.equalsIgnoreCase("QCD_PRFG_DRIVE"))
                {
                    int maxLength = 12;
                    documentno.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
                    documentno.setInputType(InputType.TYPE_CLASS_TEXT);
                    cdocumentno.setInputType(InputType.TYPE_CLASS_TEXT);
                }else{
                    documentno.setInputType(InputType.TYPE_CLASS_TEXT);
                    cdocumentno.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });

/*
        kstype.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                try {


                    if (s.toString().equalsIgnoreCase("Aadhar")) {
                        cdocumentno.setEnabled(true);
                        documentno.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                        cdocumentno.setInputType(InputType.TYPE_CLASS_NUMBER);
                        int maxLength = 12;
                        ksty = "QCD_PRFI_AADHAR";
                        documentno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                        cdocumentno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                    } else if(s.toString().equalsIgnoreCase("pan card")){
                        int maxLength = 10;
                        documentno.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
                        cdocumentno.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});

                        ksty = "QCD_PRFI_PAN";
                        documentno.setInputType(InputType.TYPE_CLASS_TEXT);
                        cdocumentno.setInputType(InputType.TYPE_CLASS_TEXT);
                    }
                    else if(s.toString().equalsIgnoreCase("passport")){
                        int maxLength = 8;
                        documentno.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
                        documentno.setInputType(InputType.TYPE_CLASS_TEXT);
                        cdocumentno.setInputType(InputType.TYPE_CLASS_TEXT);
                        ksty = "QCD_PRFI_PASS";
                    }
                    else if(s.toString().equalsIgnoreCase("Voter id"))
                    {
                        ksty = "QCD_PRFI_VOTER";
                        int maxLength = 19;
                        documentno.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
                        documentno.setInputType(InputType.TYPE_CLASS_TEXT);
                        cdocumentno.setInputType(InputType.TYPE_CLASS_TEXT);
                    }
                    else if(s.toString().equalsIgnoreCase("Driving Licence"))
                    {
                        ksty = "QCD_PRFI_DRIVE";
                        int maxLength = 12;
                        documentno.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
                        documentno.setInputType(InputType.TYPE_CLASS_TEXT);
                        cdocumentno.setInputType(InputType.TYPE_CLASS_TEXT);
                    }
                }
                catch (Exception e)
                {

                }


            }
        });
*/



        /////////////////////////////////////////////////////////////////////////////



        Cursor cursor2 = dbs.query("tablist", new String[]{"field"
                }, "tab" + "=?",
                new String[]{"Sowing Details"}, null, null, null, null);


        final SQLiteDatabase dbs = db.getWritableDatabase();
      //   Cursor cursor2 = dbs.rawQuery(selectQuery, null);

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

//
//                    if (tf[0].replaceAll(" ", "").equalsIgnoreCase("Season")) {
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
//                                    (this, R.layout.spinnertext3, tfs);
//                            //Getting the instance of AutoCompleteTextView
//
//                            ccty2s.setThreshold(0);//will start working from first character
//                            ccty2s.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                            ccty2s.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    ccty2s.showDropDown();
//                                    ccty2s.requestFocus();
//                                }
//                            });
//
//
//
//                        }
//                    }
                    if (tf[0].replaceAll(" ", "").equalsIgnoreCase("Year")) {
                        //  Toast.makeText(MainActivity3.this, "hi", Toast.LENGTH_SHORT).show();
                        // hsvssy.setVisibility(View.VISIBLE);
                        if (tf[1].replaceAll(" ", "").equalsIgnoreCase("")) {

                        } else {
                            String tv = tf[1];
                            String tv2 = tv.substring(1, tv.length() - 1);
                            tfs = tv2.split(",");
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (this, R.layout.spinnertext3, tfs);
                            //Getting the instance of AutoCompleteTextView

                            cyears.setThreshold(0);//will start working from first character
                            cyears.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                            cyears.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    cyears.showDropDown();
                                    cyears.requestFocus();
                                }
                            });




                        }
                    }

//                    if (tf[0].replaceAll(" ", "").equalsIgnoreCase("Months")) {
//                        //  Toast.makeText(MainActivity3.this, "hi", Toast.LENGTH_SHORT).show();
//                        // hsvssy.setVisibility(View.VISIBLE);
//                        if (tf[1].replaceAll(" ", "").equalsIgnoreCase("")) {
//
//                        } else {
//                            String tv = tf[1];
//                            String tv2 = tv.substring(1, tv.length() - 1);
//                            String[] tfs = tv2.split(",");
//
//
//                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                                    (this, R.layout.spinnertext3, tfs);
//                            //Getting the instance of AutoCompleteTextView
//
//                            stmnt.setThreshold(0);//will start working from first character
//                            stmnt.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                            stmnt.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    stmnt.showDropDown();
//                                    stmnt.requestFocus();
//                                }
//                            });
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
//                                    (this, R.layout.spinnertext3, tfs);
//                            //Getting the instance of AutoCompleteTextView
//
//                            stclf.setThreshold(0);//will start working from first character
//                            stclf.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                            stclf.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    stclf.showDropDown();
//                                    stclf.requestFocus();
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
                    (MainActivity3.this, R.layout.spinnertext3, arraysn);
            //Getting the instance of AutoCompleteTextView

            ccty2s.setThreshold(0);//will start working from first character
            ccty2s.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
            ccty2s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ccty2s.showDropDown();
                    ccty2s.requestFocus();
                }
            });
        }
        ccty2s.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // add the array list here..

                ccts.setText("");
                stmnt.setText("");
                stclf.setText("");
                stsnm.setText("");
                final SQLiteDatabase dbs = db.getWritableDatabase();
                // Log.e("VALCHK",""+arg0.toString());
                arraymn.clear();
                arraymc.clear();
                arraycrc.clear();
                arraycrn.clear();
                array3.clear();
                // ccts.setText("Tap For Select Crop Name");
                codes=arraysc.get(position);


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
                            (MainActivity3.this, R.layout.spinnertext3, arraycrn);
                    //Getting the instance of AutoCompleteTextView

                    stclf.setThreshold(0);//will start working from first character
                    stclf.setAdapter(adapter2);//setting the adapter data into the AutoCompleteTextView
                    stclf.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            stclf.showDropDown();
                            stclf.requestFocus();
                        }
                    });
                }

                // String val = "[typeDIV[ID,Address,DOB,Date of joining]:subtypeDIV[Aadhar,Pan Card,Voterid,t1,t2]:document noDIV[0]:valid till dateDIV[0]]";


                //String selectQuery = "SELECT  * FROM tablist";


                // Cursor cursor2 = dbs.rawQuery(selectQuery, null);
            }
        });



        stclf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // add the array list here..
                ccts.setText("");
                stsnm.setText("");
                final SQLiteDatabase dbs = db.getWritableDatabase();
                 Log.e("VALCHK",""+position);
                arraycnc.clear();
                arraycnn.clear();

                // ccts.setText("Tap For Select Crop Name");

                codecr=arraycrc.get(position);
                Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_parent_code"
                        }, "out_depend_code" + "=?",
                        new String[]{arraycrc.get(position)}, null, null, null, null);






                if (cursor2mst.moveToFirst()) {

                    do {
                        if(cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_CROPNAME")) {


                            arraycnc.add(cursor2mst.getString(0));
                            arraycnn.add(cursor2mst.getString(1));
                        }

                    }while(cursor2mst.moveToNext());



                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                            (MainActivity3.this, R.layout.spinnertext3, arraycnn);
                    //Getting the instance of AutoCompleteTextView

                    ccts.setThreshold(0);//will start working from first character
                    ccts.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                    ccts.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ccts.showDropDown();
                            ccts.requestFocus();
                        }
                    });

                }

                // String val = "[typeDIV[ID,Address,DOB,Date of joining]:subtypeDIV[Aadhar,Pan Card,Voterid,t1,t2]:document noDIV[0]:valid till dateDIV[0]]";


                //String selectQuery = "SELECT  * FROM tablist";


                // Cursor cursor2 = dbs.rawQuery(selectQuery, null);
            }
        });



        ccts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // add the array list here..

                stsnm.setText("");
                final SQLiteDatabase dbs = db.getWritableDatabase();
                Log.e("VALCHK",""+position);
                arrayvc.clear();
                arrayvn.clear();
                arraysec.clear();
                arraysen.clear();
                // ccts.setText("Tap For Select Crop Name");

                codecn=arraycnc.get(position);
                Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_parent_code"
                        }, "out_depend_code" + "=?",
                        new String[]{arraycnc.get(position)}, null, null, null, null);






                if (cursor2mst.moveToFirst()) {

                    do {
                        if(cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_CROPVARIETY")) {


                            arrayvc.add(cursor2mst.getString(0));
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
                                    (MainActivity3.this, R.layout.spinnertext3, arraysen);
                            //Getting the instance of AutoCompleteTextView

                            stsnm.setThreshold(0);//will start working from first character
                            stsnm.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                            stsnm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    stsnm.showDropDown();
                                    stsnm.requestFocus();
                                }
                            });

                        }

                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                            (MainActivity3.this, R.layout.spinnertext3, arrayvn);
                    //Getting the instance of AutoCompleteTextView

                    cvars.setThreshold(0);//will start working from first character
                    cvars.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                    cvars.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cvars.showDropDown();
                            cvars.requestFocus();
                        }
                    });

                }
                // String val = "[typeDIV[ID,Address,DOB,Date of joining]:subtypeDIV[Aadhar,Pan Card,Voterid,t1,t2]:document noDIV[0]:valid till dateDIV[0]]";


                //String selectQuery = "SELECT  * FROM tablist";


                // Cursor cursor2 = dbs.rawQuery(selectQuery, null);
            }
        });


        stsnm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // add the array list here..


                codesn=arraysec.get(position);
                // String val = "[typeDIV[ID,Address,DOB,Date of joining]:subtypeDIV[Aadhar,Pan Card,Voterid,t1,t2]:document noDIV[0]:valid till dateDIV[0]]";


                //String selectQuery = "SELECT  * FROM tablist";


                // Cursor cursor2 = dbs.rawQuery(selectQuery, null);
            }
        });



//        cvars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
//                                    long id) {
//                stsnm.setText("");
//
//            }
//        });
        /////////////////////////////////////////////////////////////////////////////


        Cursor cursor2l = dbs.query("tablist", new String[]{"field"
                }, "tab" + "=?",
                new String[]{"Land"}, null, null, null, null);
        // String val = "[typeDIV[ID,Address,DOB,Date of joining]:subtypeDIV[Aadhar,Pan Card,Voterid,t1,t2]:document noDIV[0]:valid till dateDIV[0]]";


        //String selectQuery = "SELECT  * FROM tablist";


        // Cursor cursor2l = dbs.rawQuery(selectQuery, null);


        if (cursor2l.moveToFirst()) {
            do {
                Log.e("RES", "" + cursor2l.getString(0));
                String vl = cursor2l.getString(0);
                String vll = vl.substring(1, vl.length() - 2);
                Log.e("RES", "" + vll);

                String[] vl2 = vll.split(":,");
                for (int j = 0; j < vl2.length; j++) {
                    Log.e("RES", "" + vl2[j]);

                    String[] tf = vl2[j].split("DIV");
                    Log.e("TTT", "" + tf[0]);
                    if (tf[0].replaceAll(" ", "").equalsIgnoreCase("Ownership")) {
                        //  Toast.makeText(MainActivity3.this, "hi", Toast.LENGTH_SHORT).show();

                        if (tf[1].replaceAll(" ", "").equalsIgnoreCase("")) {

                        } else {
                            String tv = tf[1];
                            String tv2 = tv.substring(1, tv.length() - 1);
                            String[] tfs = tv2.split(",");

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (this, R.layout.spinnertext3, tfs);
                            //Getting the instance of AutoCompleteTextView

                            tow.setThreshold(0);//will start working from first character
                            tow.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                            tow.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    tow.showDropDown();
                                    tow.requestFocus();
                                }
                            });
                            // total number of textviews to add



                        }
                    }
                    if (tf[0].replaceAll(" ", "").equalsIgnoreCase("landtype")) {
                        //  Toast.makeText(MainActivity3.this, "hi", Toast.LENGTH_SHORT).show();

                        if (tf[1].replaceAll(" ", "").equalsIgnoreCase("")) {

                        } else {
                            String tv = tf[1];
                            String tv2 = tv.substring(1, tv.length() - 1);
                            String[] tfs = tv2.split(",");


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (this, R.layout.spinnertext3, tfs);
                            //Getting the instance of AutoCompleteTextView

                            tlt.setThreshold(0);//will start working from first character
                            tlt.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                            tlt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    tlt.showDropDown();
                                    tlt.requestFocus();
                                }
                            });


                        }
                    }
                    if (tf[0].replaceAll(" ", "").equalsIgnoreCase("soiltype")) {
                        //  Toast.makeText(MainActivity3.this, "hi", Toast.LENGTH_SHORT).show();

                        if (tf[1].replaceAll(" ", "").equalsIgnoreCase("")) {

                        } else {
                            String tv = tf[1];
                            String tv2 = tv.substring(1, tv.length() - 1);
                            String[] tfs = tv2.split(",");


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (this, R.layout.spinnertext3, tfs);
                            //Getting the instance of AutoCompleteTextView

                            soiltype.setThreshold(0);//will start working from first character
                            soiltype.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                            soiltype.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    soiltype.showDropDown();
                                    soiltype.requestFocus();
                                }
                            });


                        }
                    }
                    if (tf[0].replaceAll(" ", "").equalsIgnoreCase("irrigationsources")) {
                        //  Toast.makeText(MainActivity3.this, "hi", Toast.LENGTH_SHORT).show();

                        if (tf[1].replaceAll(" ", "").equalsIgnoreCase("")) {

                        } else {
                            String tv = tf[1];
                            String tv2 = tv.substring(1, tv.length() - 1);
                            String[] tfs = tv2.split(",");


                            // total number of textviews to add

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (this, R.layout.spinnertext3, tfs);
                            //Getting the instance of AutoCompleteTextView

                            tis.setThreshold(0);//will start working from first character
                            tis.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                            tis.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    tis.showDropDown();
                                    tis.requestFocus();
                                }
                            });
                        }
                    }

                }


            } while (cursor2l.moveToNext());
        }

        lview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(MainActivity3.this);
                dialog.setContentView(R.layout.tablist);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                TextView title = (TextView)dialog.findViewById(R.id.texttitle);

                dialog.getWindow().setLayout(width, height);
                title.setText("Land Details");

                androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity3.this));
                adapter = new MyRecyclerViewAdapter(MainActivity3.this, listitem,6);
                recyclerView.setAdapter(adapter);
                //String selectQuery = "SELECT  * FROM family where v10 = "+sharedpreferences.getString("fcode","");
                listitem.clear();
                Cursor cursor = dbs.query("land", new String[]{"id", "v1", "v2", "v3", "v4", "v5", "v6","v7","v8","v9","v10","v11","v12","v13","v14"
                        }, "v10" + "=?",
                        new String[]{sharedpreferences.getString("fcode","")}, null, null, null, null);

                // looping through all rows and adding to list
                Log.e("NULL",""+cursor.getCount());
                if (cursor.moveToFirst()) {
                    do {
                        Pojokyc pojokyc = new Pojokyc();

                        pojokyc.setLaid(cursor.getString(0));
                        pojokyc.setLat1(cursor.getString(1));
                        pojokyc.setLat2(cursor.getString(2));
                        pojokyc.setLat3(cursor.getString(3));
                        pojokyc.setLat4(cursor.getString(4));
                        pojokyc.setLat5(cursor.getString(5));
                        pojokyc.setLat6(cursor.getString(6));
                        pojokyc.setLat7(cursor.getString(7));
                        pojokyc.setLat8(cursor.getString(12));
                        pojokyc.setLat9(cursor.getString(13));
                        pojokyc.setLat10(cursor.getString(14));
                        pojokyc.setLaslno(cursor.getString(8));



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
        });




        ////////////////////////////////////////////////////////////////////


        kttype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                kstype.setText("");
                ksty = "";
                String selecteditem = actypecode[i];
                kty = actypecode[i];
                Log.e("actype",kty);
                Cursor cr = dbs.rawQuery("select out_master_code, out_master_description from masterl where out_depend_code = '"+selecteditem+"' and out_status_code = 'A' ",null);
                try{
                    acsubtype = new String[cr.getCount()];
                    acsubtypecode = new String[cr.getCount()];
                    if(cr.getCount() > 0){
                        if(cr.moveToFirst()){
                            for(int j = 0; j < cr.getCount(); j++){
                                acsubtype[j] = cr.getString(cr.getColumnIndexOrThrow("out_master_description"));
                                acsubtypecode[j] = cr.getString(cr.getColumnIndexOrThrow("out_master_code"));
                                cr.moveToNext();
                            }
                        }
                    }
                }catch (Exception er){
                    Log.e("error",Log.getStackTraceString(er));
                }finally {
                    cr.close();
                }
            }
        });
/*        kttype.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                try {


                    if (s.toString().equalsIgnoreCase("Id")) {
                        kty = "QCD_PRF_ID";
                    } else if(s.toString().equalsIgnoreCase("Address")){

                        kty = "QCD_PRF_ADDR";
                    }
                    else if(s.toString().equalsIgnoreCase("age")){
                        kty = "QCD_PRF_AGE";
                    }

                }
                catch (Exception e)
                {

                }


            }
        });*/
        
        sview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(MainActivity3.this);
                dialog.setContentView(R.layout.tablist);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                TextView title = (TextView)dialog.findViewById(R.id.texttitle);

                dialog.getWindow().setLayout(width, height);
                title.setText("Sowing Details");
                androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity3.this));
                adapter = new MyRecyclerViewAdapter(MainActivity3.this, listitem,21);
                recyclerView.setAdapter(adapter);
                //String selectQuery = "SELECT  * FROM kyc where fid = "+sharedpreferences.getString("fcode","");
                listitem.clear();
                Cursor cursor = dbs.query("sowing", new String[]{"id","v1", "v2", "v3", "v4", "v5", "v6", "v7","v8","v9","v10","v11","v12","v13","v14","v15","v16","v17","n1","n2","n3","n4","n5"
                        }, "v16" + "=?",
                        new String[]{sharedpreferences.getString("fcode","")}, null, null, null, null);

                // looping through all rows and adding to list

                if (cursor.moveToFirst()) {
                    do {
                        Log.e("NULL",""+cursor.getString(18));
                        Log.e("NULL",""+cursor.getString(19));
                        Log.e("NULL",""+cursor.getString(20));
                        Log.e("NULL",""+cursor.getString(21));
                        Log.e("NULL",""+cursor.getString(22));
                        Pojokyc pojokyc = new Pojokyc();

                        pojokyc.setSdid(cursor.getString(0));
                        pojokyc.setSd1(cursor.getString(1));
                        pojokyc.setSd2(cursor.getString(2));
                        pojokyc.setSd3(cursor.getString(3));
                        pojokyc.setSd4(cursor.getString(4));
                        pojokyc.setSd5(cursor.getString(5));
                        pojokyc.setSd6(cursor.getString(6));
                        pojokyc.setSd7(cursor.getString(7));
                        pojokyc.setSd8(cursor.getString(8));
                        pojokyc.setSd9(cursor.getString(9));
                        pojokyc.setSd10(cursor.getString(10));
                        pojokyc.setSd11(cursor.getString(11));
                        pojokyc.setSd12(cursor.getString(12));
                        pojokyc.setSd13(cursor.getString(13));
                        pojokyc.setSdsn(cursor.getString(14));
                        pojokyc.setSdfid(cursor.getString(15));
                        pojokyc.setSdfc(cursor.getString(16));
                        pojokyc.setE1(cursor.getString(18));
                        pojokyc.setE2(cursor.getString(19));
                        pojokyc.setE3(cursor.getString(20));
                        pojokyc.setE4(cursor.getString(21));
                        pojokyc.setE5(cursor.getString(22));






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
        });
        kview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(MainActivity3.this);
                dialog.setContentView(R.layout.tablist);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                TextView title = (TextView)dialog.findViewById(R.id.texttitle);

                dialog.getWindow().setLayout(width, height);
                title.setText("KYC");





                // set up the RecyclerView
                androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity3.this));
                adapter = new MyRecyclerViewAdapter(MainActivity3.this, listitem,2);
                recyclerView.setAdapter(adapter);
                String selectQuery = "SELECT  * FROM kyc where fid = "+sharedpreferences.getString("fcode","");
                listitem.clear();
                Cursor cursor = dbs.query("kyc", new String[]{"id", "type", "subtype", "dno", "vtd", "tms", "stmc","rid","poto"
                        }, "fid" + "=?",
                        new String[]{sharedpreferences.getString("fcode","")}, null, null, null, null);

                // looping through all rows and adding to list
                Log.e("NULL",""+cursor.getCount());
                if (cursor.moveToFirst()) {
                    do {
                        Pojokyc pojokyc = new Pojokyc();

                        pojokyc.setT(cursor.getString(1));
                        pojokyc.setSt(cursor.getString(2));
                        pojokyc.setId(cursor.getString(0));
                        pojokyc.setDno(cursor.getString(3));
                        pojokyc.setVtd(cursor.getString(4));
                        pojokyc.setTmc(cursor.getString(5));
                        pojokyc.setStmc(cursor.getString(6));
                        pojokyc.setKrid(cursor.getString(7));
                        pojokyc.setPoto(cursor.getString(8));


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
        });
        bview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new Dialog(MainActivity3.this);
                dialog.setContentView(R.layout.tablist);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                TextView title = (TextView)dialog.findViewById(R.id.texttitle);

                dialog.getWindow().setLayout(width, height);
                title.setText("Bank");

                androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity3.this));
                adapter = new MyRecyclerViewAdapter(MainActivity3.this, listitem,3);
                recyclerView.setAdapter(adapter);
                String selectQuery = "SELECT  * FROM bank where fid = "+sharedpreferences.getString("fcode","");
                listitem.clear();
                Cursor cursor = dbs.query("bank", new String[]{"id", "type", "ano", "bank", "ifsc", "branch", "dc","dd","typec","bc","bid"
                        }, "fid" + "=?",
                        new String[]{sharedpreferences.getString("fcode","")}, null, null, null, null);

                // looping through all rows and adding to list
                Log.e("NULL",""+cursor.getCount());
                if (cursor.moveToFirst()) {
                    do {
                        Pojokyc pojokyc = new Pojokyc();

                        pojokyc.setAt(cursor.getString(1));
                        pojokyc.setAn(cursor.getString(2));
                        pojokyc.setBid(cursor.getString(0));
                        pojokyc.setBn(cursor.getString(3));
                        pojokyc.setBi(cursor.getString(4));
                        pojokyc.setBr(cursor.getString(5));
                        pojokyc.setDc(cursor.getString(6));
                        pojokyc.setDd(cursor.getString(7));
                        pojokyc.setAtc(cursor.getString(8));
                        pojokyc.setBco(cursor.getString(9));
                        pojokyc.setBbid(cursor.getString(10));


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
        });

        myCalendar = Calendar.getInstance();
        myCalendar2 = Calendar.getInstance();
         date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabel();
            }

        };
        date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);



                updateLabel2();
            }

        };

        date3 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);



                updateLabel3();
            }

        };
        vtilldate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity3.this,android.R.style.Theme_Holo_Dialog , date3, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                // datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
//                datePickerDialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis()-86400000);


                datePickerDialog.show();

            }
        });

        edob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity3.this,android.R.style.Theme_Holo_Dialog , date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
               // datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
               // datePickerDialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis()-86400000);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, -18);
                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

                datePickerDialog.show();

            }
        });

        stmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity3.this,android.R.style.Theme_Holo_Dialog , date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });
        captureh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    imagetype = 0;
                    picUri = FileProvider.getUriForFile(MainActivity3.this, getApplicationContext().getPackageName() + ".provider", createImageFile());
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                    takePictureIntent.putExtra("return-data", true);
                    startActivityForResult(takePictureIntent, CAMERA_CAPTURE);// convert path to Uri
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        capturek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    imagetype = 1;
                    picUri = FileProvider.getUriForFile(MainActivity3.this, getApplicationContext().getPackageName() + ".provider", createImageFile());
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                    takePictureIntent.putExtra("return-data", true);
                    startActivityForResult(takePictureIntent, CAMERA_CAPTURE);// convert path to Uri
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        cyears.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                              array4.clear();
                                              array4.add(tfs[position]);
                                              // add the array list here..

                                              final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

                                                  @Override
                                                  public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                                        int dayOfMonth) {
                                                      // TODO Auto-generated method stub
                                                      myCalendar.set(Calendar.YEAR, year);
                                                      myCalendar.set(Calendar.MONTH, monthOfYear);
                                                      myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                                                      updateLabel2();
                                                  }

                                              };

                                          }
                                      });
        gender = (AutoCompleteTextView)findViewById(R.id.gender);


        Cursor cursorgender = dbs.rawQuery("select * from masterl where out_parent_code = 'QCD_GENDER'",null);
        if(cursorgender.moveToFirst())
        {
            do {
                gendername.add(cursorgender.getString(4));
            }while (cursorgender.moveToNext());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, R.layout.spinnertext3, gendername);
        //Getting the instance of AutoCompleteTextView

        gender.setThreshold(0);//will start working from first character
        gender.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView


        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender.showDropDown();
                gender.requestFocus();
            }
        });

        Cursor cr = dbs.rawQuery("select out_master_code, out_master_description from masterl where out_parent_code = 'QCD_PRFTYPE' and out_status_code = 'A' ",null);
        try{
            actype = new String[cr.getCount()];
            actypecode = new String[cr.getCount()];
            if(cr.getCount() > 0){
                if(cr.moveToFirst()){
                   for(int i = 0; i < cr.getCount(); i++){
                       actype[i] = cr.getString(cr.getColumnIndexOrThrow("out_master_description"));
                       actypecode[i] = cr.getString(cr.getColumnIndexOrThrow("out_master_code"));
                       cr.moveToNext();
                   }
                }
            }
        }catch (Exception er){
            Log.e("error",Log.getStackTraceString(er));
        }finally {
            cr.close();

        }




        kttype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                        (MainActivity3.this, R.layout.spinnertext3, actype);
                //Getting the instance of AutoCompleteTextView

                kttype.setThreshold(0);//will start working from first character
                kttype.setAdapter(adapter2);//setting the adapter data into the AutoCompleteTextView
                kttype.showDropDown();
                kttype.requestFocus();
            }
        });


        kstype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                        (MainActivity3.this, R.layout.spinnertext3, acsubtype);
                //Getting the instance of AutoCompleteTextView

                kstype.setThreshold(0);//will start working from first character
                kstype.setAdapter(adapter3);//setting the adapter data into the AutoCompleteTextView
                kstype.showDropDown();
                kstype.requestFocus();
            }
        });

        lefta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pg==0)
                {

                }
                else
                {
                    pg--;

                    if(pg == 0)
                    {
                        im1.setBackgroundResource(R.drawable.sheader2);
                        im2.setBackgroundResource(R.drawable.sbank);
                        im3.setBackgroundResource(R.drawable.skyc);
                        im4.setBackgroundResource(R.drawable.sscd2);
                        im5.setBackgroundResource(R.drawable.sland);
                        l1.setVisibility(View.VISIBLE);
                        l2.setVisibility(View.GONE);
                        l3.setVisibility(View.GONE);
                        l4.setVisibility(View.GONE);
                        l5.setVisibility(View.GONE);
                        v1.setVisibility(View.VISIBLE);
                        v2.setVisibility(View.GONE);
                        v3.setVisibility(View.GONE);
                        v4.setVisibility(View.GONE);
                        v5.setVisibility(View.GONE);
                        txtheader.setText("Header");
                    }
                    if(pg == 1)
                    {
                        if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                        {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Please Enter Header Details First!")
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
                            im1.setBackgroundResource(R.drawable.sheader);
                            im2.setBackgroundResource(R.drawable.sbank2);
                            im3.setBackgroundResource(R.drawable.skyc);
                            im4.setBackgroundResource(R.drawable.sscd2);
                            im5.setBackgroundResource(R.drawable.sland);
                            l1.setVisibility(View.GONE);
                            l2.setVisibility(View.VISIBLE);
                            l3.setVisibility(View.GONE);
                            l4.setVisibility(View.GONE);
                            l5.setVisibility(View.GONE);
                            v1.setVisibility(View.GONE);
                            v2.setVisibility(View.VISIBLE);
                            v3.setVisibility(View.GONE);
                            v4.setVisibility(View.GONE);
                            v5.setVisibility(View.GONE);
                            txtheader.setText("Bank");
                        }
                    }
                    if(pg == 2)
                    {
                        if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                        {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Please Enter Header Details First!")
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
                            im1.setBackgroundResource(R.drawable.sheader);
                            im2.setBackgroundResource(R.drawable.sbank);
                            im3.setBackgroundResource(R.drawable.skyc2);
                            im4.setBackgroundResource(R.drawable.sscd2);
                            im5.setBackgroundResource(R.drawable.sland);
                            l1.setVisibility(View.GONE);
                            l2.setVisibility(View.GONE);
                            l3.setVisibility(View.VISIBLE);
                            l4.setVisibility(View.GONE);
                            l5.setVisibility(View.GONE);
                            v1.setVisibility(View.GONE);
                            v2.setVisibility(View.GONE);
                            v3.setVisibility(View.VISIBLE);
                            v4.setVisibility(View.GONE);
                            v5.setVisibility(View.GONE);
                            txtheader.setText("KYC");
                        }


                    }
                    if(pg == 4)
                    {
                        if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                        {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Please Enter Header Details First!")
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
                            im1.setBackgroundResource(R.drawable.sheader);
                            im2.setBackgroundResource(R.drawable.sbank);
                            im3.setBackgroundResource(R.drawable.skyc);
                            im4.setBackgroundResource(R.drawable.sscd);
                            im5.setBackgroundResource(R.drawable.sland);
                            l1.setVisibility(View.GONE);
                            l2.setVisibility(View.GONE);
                            l3.setVisibility(View.GONE);
                            l4.setVisibility(View.VISIBLE);
                            l5.setVisibility(View.GONE);
                            v1.setVisibility(View.GONE);
                            v2.setVisibility(View.GONE);
                            v3.setVisibility(View.GONE);
                            v4.setVisibility(View.VISIBLE);
                            v5.setVisibility(View.GONE);
                            txtheader.setText("Sowing & Cropping Details");
                        }
                    }
                    if(pg == 3)
                    {
                        if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                        {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Please Enter Header Details First!")
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
                            im1.setBackgroundResource(R.drawable.sheader);
                            im2.setBackgroundResource(R.drawable.sbank);
                            im3.setBackgroundResource(R.drawable.skyc);
                            im4.setBackgroundResource(R.drawable.sscd2);
                            im5.setBackgroundResource(R.drawable.sland2);
                            v1.setVisibility(View.GONE);
                            v2.setVisibility(View.GONE);
                            v3.setVisibility(View.GONE);
                            v4.setVisibility(View.GONE);
                            l1.setVisibility(View.GONE);
                            l2.setVisibility(View.GONE);
                            l3.setVisibility(View.GONE);
                            l4.setVisibility(View.GONE);
                            l5.setVisibility(View.VISIBLE);
                            v5.setVisibility(View.VISIBLE);
                            txtheader.setText("Land Details");
//                            Cursor cursor2ic = dbs.query("masterl", new String[]{"out_master_code"
//                                    }, "out_depend_code" + " LIKE ?",
//                                    new String[]{"%"+mt+"%"}, null, null, null, null);
//                            String[] tfs3;
//                            Log.e("MST0",""+mt);
//
//                            Log.e("MSTl",""+cursor2ic.getCount());
//                            if(cursor2ic.moveToFirst())
//                            {
//                                do
//                                {
//                                    Log.e("MST",""+cursor2ic.getString(0));
//                                    Cursor cursor2icv = dbs.query("masterl", new String[]{"out_master_code"
//                                            }, "out_depend_code" + " LIKE ?",
//                                            new String[]{"%"+cursor2ic.getString(0)+"%"}, null, null, null, null);
//
//                                    if(cursor2icv.moveToFirst()) {
//                                        do {
//                                            Log.e("MST22",""+cursor2icv.getString(0));
//                                            Cursor cursor2icv2 = dbs.query("masterl", new String[]{"out_master_description"
//                                                    }, "out_master_code" + " LIKE ?",
//                                                    new String[]{"%"+cursor2icv.getString(0)+"%"}, null, null, null, null);
//
//                                            if(cursor2icv2.moveToFirst()) {
//                                                do {
//                                                    myList.add(cursor2icv2.getString(0));
//
//
//                                                } while (cursor2icv2.moveToNext());
//                                            }
//
//                                        } while (cursor2icv.moveToNext());
//                                    }
//
//                                }while(cursor2ic.moveToNext());
//                            }
//                            tfs3 = new String[cursor2ic.getCount()];
//
//                            int icc = 0;

                            // create an empty array;
                            ArrayList<String> myList = new ArrayList<String>();
                            myList.clear();
                            Cursor cursor2icv2 = dbs.query("masterl", new String[]{"out_master_description","out_parent_code"
                                    }, "out_parent_code" + " LIKE ?",
                                    new String[]{"QCD_UN_VILLAGE"}, null, null, null, null);

                            if(cursor2icv2.moveToFirst()) {
                                do {
                                    myList.add(cursor2icv2.getString(0));


                                } while (cursor2icv2.moveToNext());
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (MainActivity3.this, R.layout.spinnertext3, myList);
                            //Getting the instance of AutoCompleteTextView

                            landvillage.setThreshold(0);//will start working from first character
                            landvillage.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                            landvillage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    landvillage.showDropDown();
                                    landvillage.requestFocus();
                                  //  landsearchpopupwindow();
                                }
                            });

                        }
                    }
                }

            }
        });

        righta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pg==4)
                {


                }
                else
                {
                    pg++;
                    if(pg == 1)
                    {
                        if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                        {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Please Enter Header Details First!")
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
                            im1.setBackgroundResource(R.drawable.sheader);
                            im2.setBackgroundResource(R.drawable.sbank2);
                            im3.setBackgroundResource(R.drawable.skyc);
                            im4.setBackgroundResource(R.drawable.sscd2);
                            im5.setBackgroundResource(R.drawable.sland);
                            l1.setVisibility(View.GONE);
                            l2.setVisibility(View.VISIBLE);
                            l3.setVisibility(View.GONE);
                            l4.setVisibility(View.GONE);
                            l5.setVisibility(View.GONE);
                            v1.setVisibility(View.GONE);
                            v2.setVisibility(View.VISIBLE);
                            v3.setVisibility(View.GONE);
                            v4.setVisibility(View.GONE);
                            v5.setVisibility(View.GONE);
                            txtheader.setText("Bank");
                        }
                    }
                    if(pg == 2)
                    {
                        if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                        {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Please Enter Header Details First!")
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
                            im1.setBackgroundResource(R.drawable.sheader);
                            im2.setBackgroundResource(R.drawable.sbank);
                            im3.setBackgroundResource(R.drawable.skyc2);
                            im4.setBackgroundResource(R.drawable.sscd2);
                            im5.setBackgroundResource(R.drawable.sland);
                            l1.setVisibility(View.GONE);
                            l2.setVisibility(View.GONE);
                            l3.setVisibility(View.VISIBLE);
                            l4.setVisibility(View.GONE);
                            l5.setVisibility(View.GONE);
                            v1.setVisibility(View.GONE);
                            v2.setVisibility(View.GONE);
                            v3.setVisibility(View.VISIBLE);
                            v4.setVisibility(View.GONE);
                            v5.setVisibility(View.GONE);
                            txtheader.setText("KYC");
                        }


                    }
                    if(pg == 4)
                    {
                        if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                        {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Please Enter Header Details First!")
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
                            im1.setBackgroundResource(R.drawable.sheader);
                            im2.setBackgroundResource(R.drawable.sbank);
                            im3.setBackgroundResource(R.drawable.skyc);
                            im4.setBackgroundResource(R.drawable.sscd);
                            im5.setBackgroundResource(R.drawable.sland);
                            l1.setVisibility(View.GONE);
                            l2.setVisibility(View.GONE);
                            l3.setVisibility(View.GONE);
                            l4.setVisibility(View.VISIBLE);
                            l5.setVisibility(View.GONE);
                            v1.setVisibility(View.GONE);
                            v2.setVisibility(View.GONE);
                            v3.setVisibility(View.GONE);
                            v4.setVisibility(View.VISIBLE);
                            v5.setVisibility(View.GONE);
                            txtheader.setText("Sowing & Cropping Details");
                        }
                    }
                    if(pg == 0)
                    {
                        im1.setBackgroundResource(R.drawable.sheader2);
                        im2.setBackgroundResource(R.drawable.sbank);
                        im3.setBackgroundResource(R.drawable.skyc);
                        im4.setBackgroundResource(R.drawable.sscd2);
                        im5.setBackgroundResource(R.drawable.sland);
                        l1.setVisibility(View.VISIBLE);
                        l2.setVisibility(View.GONE);
                        l3.setVisibility(View.GONE);
                        l4.setVisibility(View.GONE);
                        l5.setVisibility(View.GONE);
                        v1.setVisibility(View.VISIBLE);
                        v2.setVisibility(View.GONE);
                        v3.setVisibility(View.GONE);
                        v4.setVisibility(View.GONE);
                        v5.setVisibility(View.GONE);
                        txtheader.setText("Header");
                    }
                    if(pg == 3)
                    {
                        if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                        {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Please Enter Header Details First!")
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
                            im1.setBackgroundResource(R.drawable.sheader);
                            im2.setBackgroundResource(R.drawable.sbank);
                            im3.setBackgroundResource(R.drawable.skyc);
                            im4.setBackgroundResource(R.drawable.sscd2);
                            im5.setBackgroundResource(R.drawable.sland2);
                            v1.setVisibility(View.GONE);
                            v2.setVisibility(View.GONE);
                            v3.setVisibility(View.GONE);
                            v4.setVisibility(View.GONE);
                            l1.setVisibility(View.GONE);
                            l2.setVisibility(View.GONE);
                            l3.setVisibility(View.GONE);
                            l4.setVisibility(View.GONE);
                            l5.setVisibility(View.VISIBLE);
                            v5.setVisibility(View.VISIBLE);
                            txtheader.setText("Land Details");
//                            Cursor cursor2ic = dbs.query("masterl", new String[]{"out_master_code"
//                                    }, "out_depend_code" + " LIKE ?",
//                                    new String[]{"%"+mt+"%"}, null, null, null, null);
//                            String[] tfs3;
//                            Log.e("MST0",""+mt);
//                            ArrayList<String> myList = new ArrayList<String>();
//                            Log.e("MSTl",""+cursor2ic.getCount());
//                            if(cursor2ic.moveToFirst())
//                            {
//                                do
//                                {
//                                    Log.e("MST",""+cursor2ic.getString(0));
//                                    Cursor cursor2icv = dbs.query("masterl", new String[]{"out_master_code"
//                                            }, "out_depend_code" + " LIKE ?",
//                                            new String[]{"%"+cursor2ic.getString(0)+"%"}, null, null, null, null);
//
//                                    if(cursor2icv.moveToFirst()) {
//                                        do {
//                                            Log.e("MST22",""+cursor2icv.getString(0));
//                                            Cursor cursor2icv2 = dbs.query("masterl", new String[]{"out_master_description"
//                                                    }, "out_master_code" + " LIKE ?",
//                                                    new String[]{"%"+cursor2icv.getString(0)+"%"}, null, null, null, null);
//
//                                            if(cursor2icv2.moveToFirst()) {
//                                                do {
//                                                    myList.add(cursor2icv2.getString(0));
//
//
//                                                } while (cursor2icv2.moveToNext());
//                                            }
//
//                                        } while (cursor2icv.moveToNext());
//                                    }
//
//                                }while(cursor2ic.moveToNext());
//                            }
//                            tfs3 = new String[cursor2ic.getCount()];
//
//                            int icc = 0;
//
//                            // create an empty array;
//
//                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                                    (MainActivity3.this, R.layout.spinnertext3, myList);
//                            //Getting the instance of AutoCompleteTextView
//
//                            landvillage.setThreshold(0);//will start working from first character
//                            landvillage.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                            landvillage.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    landvillage.showDropDown();
//                                    landvillage.requestFocus();
//                                }
//                            });
                            ArrayList<String> myList = new ArrayList<String>();
                            myList.clear();
                            Cursor cursor2icv2 = dbs.query("masterl", new String[]{"out_master_description","out_parent_code"
                                    }, "out_parent_code" + " LIKE ?",
                                    new String[]{"QCD_UN_VILLAGE"}, null, null, null, null);

                            if(cursor2icv2.moveToFirst()) {
                                do {
                                    myList.add(cursor2icv2.getString(0));


                                } while (cursor2icv2.moveToNext());
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (MainActivity3.this, R.layout.spinnertext3, myList);
                            //Getting the instance of AutoCompleteTextView

                            landvillage.setThreshold(0);//will start working from first character
                            landvillage.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                            landvillage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    landvillage.showDropDown();
                                    landvillage.requestFocus();
                                }
                            });

                        }
                    }
                }

            }
        });
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg=0;
                im1.setBackgroundResource(R.drawable.sheader2);
                im2.setBackgroundResource(R.drawable.sbank);
                im3.setBackgroundResource(R.drawable.skyc);
                im4.setBackgroundResource(R.drawable.sscd2);
                im5.setBackgroundResource(R.drawable.sland);
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);
                v4.setVisibility(View.GONE);
                v5.setVisibility(View.GONE);
                txtheader.setText("Header");

            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg=1;
                if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Please Enter Header Details First!")
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
                    im1.setBackgroundResource(R.drawable.sheader);
                    im2.setBackgroundResource(R.drawable.sbank2);
                    im3.setBackgroundResource(R.drawable.skyc);
                    im4.setBackgroundResource(R.drawable.sscd2);
                    im5.setBackgroundResource(R.drawable.sland);
                    l1.setVisibility(View.GONE);
                    l2.setVisibility(View.VISIBLE);
                    l3.setVisibility(View.GONE);
                    l4.setVisibility(View.GONE);
                    l5.setVisibility(View.GONE);
                    v1.setVisibility(View.GONE);
                    v2.setVisibility(View.VISIBLE);
                    v3.setVisibility(View.GONE);
                    v4.setVisibility(View.GONE);
                    v5.setVisibility(View.GONE);
                    txtheader.setText("Bank");
                }


            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg = 2;

                if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Please Enter Header Details First!")
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
                    im1.setBackgroundResource(R.drawable.sheader);
                    im2.setBackgroundResource(R.drawable.sbank);
                    im3.setBackgroundResource(R.drawable.skyc2);
                    im4.setBackgroundResource(R.drawable.sscd2);
                    im5.setBackgroundResource(R.drawable.sland);
                    l1.setVisibility(View.GONE);
                    l2.setVisibility(View.GONE);
                    l3.setVisibility(View.VISIBLE);
                    l4.setVisibility(View.GONE);
                    l5.setVisibility(View.GONE);
                    v1.setVisibility(View.GONE);
                    v2.setVisibility(View.GONE);
                    v3.setVisibility(View.VISIBLE);
                    v4.setVisibility(View.GONE);
                    v5.setVisibility(View.GONE);
                    txtheader.setText("KYC");
                }


            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg = 3;
                if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Please Enter Header Details First!")
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
                    im1.setBackgroundResource(R.drawable.sheader);
                    im2.setBackgroundResource(R.drawable.sbank);
                    im3.setBackgroundResource(R.drawable.skyc);
                    im4.setBackgroundResource(R.drawable.sscd);
                    im5.setBackgroundResource(R.drawable.sland);
                    l1.setVisibility(View.GONE);
                    l2.setVisibility(View.GONE);
                    l3.setVisibility(View.GONE);
                    l4.setVisibility(View.VISIBLE);
                    l5.setVisibility(View.GONE);
                    v1.setVisibility(View.GONE);
                    v2.setVisibility(View.GONE);
                    v3.setVisibility(View.GONE);
                    v4.setVisibility(View.VISIBLE);
                    v5.setVisibility(View.GONE);
                    txtheader.setText("Sowing & Cropping Details");
                }


            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg = 4;
                if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Please Enter Header Details First!")
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
                    im1.setBackgroundResource(R.drawable.sheader);
                    im2.setBackgroundResource(R.drawable.sbank);
                    im3.setBackgroundResource(R.drawable.skyc);
                    im4.setBackgroundResource(R.drawable.sscd2);
                    im5.setBackgroundResource(R.drawable.sland2);
                    v1.setVisibility(View.GONE);
                    v2.setVisibility(View.GONE);
                    v3.setVisibility(View.GONE);
                    v4.setVisibility(View.GONE);
                    l1.setVisibility(View.GONE);
                    l2.setVisibility(View.GONE);
                    l3.setVisibility(View.GONE);
                    l4.setVisibility(View.GONE);
                    l5.setVisibility(View.VISIBLE);
                    v5.setVisibility(View.VISIBLE);
                    txtheader.setText("Land Details");
//                    Cursor cursor2ic = dbs.query("masterl", new String[]{"out_master_code"
//                            }, "out_depend_code" + " LIKE ?",
//                            new String[]{"%"+mt+"%"}, null, null, null, null);
//                    String[] tfs3;
//                    Log.e("MST0",""+mt);
//                    ArrayList<String> myList = new ArrayList<String>();
//                    Log.e("MSTl",""+cursor2ic.getCount());
//                    if(cursor2ic.moveToFirst())
//                    {
//                        do
//                        {
//                            Log.e("MST",""+cursor2ic.getString(0));
//                            Cursor cursor2icv = dbs.query("masterl", new String[]{"out_master_code"
//                                    }, "out_depend_code" + " LIKE ?",
//                                    new String[]{"%"+cursor2ic.getString(0)+"%"}, null, null, null, null);
//
//                            if(cursor2icv.moveToFirst()) {
//                                do {
//                                    Log.e("MST22",""+cursor2icv.getString(0));
//                                    Cursor cursor2icv2 = dbs.query("masterl", new String[]{"out_master_description"
//                                            }, "out_master_code" + " LIKE ?",
//                                            new String[]{"%"+cursor2icv.getString(0)+"%"}, null, null, null, null);
//
//                                    if(cursor2icv2.moveToFirst()) {
//                                        do {
//                                            myList.add(cursor2icv2.getString(0));
//
//
//                                        } while (cursor2icv2.moveToNext());
//                                    }
//
//                                } while (cursor2icv.moveToNext());
//                            }
//
//                        }while(cursor2ic.moveToNext());
//                    }
//                    tfs3 = new String[cursor2ic.getCount()];
//
//                    int icc = 0;
//
//                    // create an empty array;
//
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                            (MainActivity3.this, R.layout.spinnertext3, myList);
//                    //Getting the instance of AutoCompleteTextView
//
//                    landvillage.setThreshold(0);//will start working from first character
//                    landvillage.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                    landvillage.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            landvillage.showDropDown();
//                            landvillage.requestFocus();
//                        }
//                    });
                    ArrayList<String> myList = new ArrayList<String>();
                    myList.clear();
                    Cursor cursor2icv2 = dbs.query("masterl", new String[]{"out_master_description","out_parent_code"
                            }, "out_parent_code" + " LIKE ?",
                            new String[]{"QCD_UN_VILLAGE"}, null, null, null, null);

                    if(cursor2icv2.moveToFirst()) {
                        do {
                            myList.add(cursor2icv2.getString(0));


                        } while (cursor2icv2.moveToNext());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                            (MainActivity3.this, R.layout.spinnertext3, myList);
                    //Getting the instance of AutoCompleteTextView

                    landvillage.setThreshold(0);//will start working from first character
                    landvillage.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                    landvillage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            landsearchpopupwindow();
                        }
                    });

                }

            }
        });
        captureld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    imagetype = 2;
                    picUri = FileProvider.getUriForFile(MainActivity3.this, getApplicationContext().getPackageName() + ".provider", createImageFile());
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                    takePictureIntent.putExtra("return-data", true);
                    startActivityForResult(takePictureIntent, CAMERA_CAPTURE);// convert path to Uri
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        lsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Please Enter Header Details First!")
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
                    addland();
                }
            }
        });

        ksave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Please Enter Header Details First!")
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
                    addkyc();
                }
            }
        });

        bsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Please Enter Header Details First!")
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
                    addbank();
                }
            }
        });

        ssave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedpreferences.getString("fid","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Error!")
//set message
                            .setMessage("Please Enter Header Details First!")
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
                    addsow();
                }
            }
        });

       eifsc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               eifsc.setEnabled(false);
               dialog = new Dialog(MainActivity3.this);
               dialog.setContentView(R.layout.suppliersearch3);
               dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
               dialog.setTitle("Title...");
               int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
               int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
               final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
               recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity3.this));
               adapterb = new MainActivity3.MyRecyclerViewAdapterb(MainActivity3.this, pojobankList);
               dialog.getWindow().setLayout(width, height);
               dialogtext = dialog.findViewById(R.id.dialogtext);
               dialogtext.setText("IFSC Search");
               arrayn.clear();
               elc = (AutoCompleteTextView)dialog.findViewById(R.id.elc);
               String selectQuery5 = "SELECT  * FROM bankm";
               Cursor cc = dbs.rawQuery(selectQuery5, null);
               if(cc.getCount()!=0) {
                   if (cc.moveToFirst()) {
                       do {

                           if(arrayn.contains(cc.getString(5)))
                           {

                           }
                           else
                           {
                               arrayn.add(cc.getString(5));
                           }
                           // Log.e("VAL",""+cursor.getString(11));

                       } while (cc.moveToNext());
                   }
               }


               ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(MainActivity3.this,
                       R.layout.spinnertext, arrayn);

               elc.setAdapter(adapterlist2n);

               elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick (AdapterView<?> parent, View view, int position, long id) {


                       pojobankList.clear();
                       // elc.setText(""+(String) parent.getItemAtPosition(position));

                       Cursor cursor = dbs.query("bankm", new String[]{"bank_code","bank_name","branch_name","ifsc_code"
                               }, "ifsc_code" + "=? COLLATE NOCASE",
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
                                   bstatus = 0;
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
                       eifsc.setEnabled(true);
                       dialog.dismiss();
//                        finish();
//                        startActivity(getIntent());
                   }
               });

               dialog.show();
           }
       });
      village.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             // pdialog.setMessage("Loading...");
             // pdialog.show();
              dialog = new Dialog(MainActivity3.this);
              dialog.setContentView(R.layout.suppliersearch2);
              dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
              dialog.setTitle("Title...");
              int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
              int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
              final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
              recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity3.this));
              adapterd = new MainActivity3.MyRecyclerViewAdapterd(MainActivity3.this, pojolocList);
              dialog.getWindow().setLayout(width, height);

              elc = (AutoCompleteTextView)dialog.findViewById(R.id.elc);
              dialogtext = dialog.findViewById(R.id.dialogtext);
              dialogtext.setText("Village Search");

              arrayn.clear();
              Toast.makeText(MainActivity3.this, "Please Wait", Toast.LENGTH_LONG).show();
//              Cursor cc = dbs.query("masterl", new String[]{"out_master_description","out_depend_code"
//                      }, "out_parent_code" + "=?",
//                      new String[]{"QCD_UN_VILLAGE"}, null, null, null, null);
              Cursor cc = dbs.rawQuery("select out_master_description,out_depend_code from masterl where out_parent_code = 'QCD_UN_VILLAGE'",null);
              if(cc.getCount()!=0) {
                  if (cc.moveToFirst()) {
                      do {
                          if(arrayn.contains(cc.getString(0).trim()))
                          {

                          }
                          else
                          {
                              arrayn.add(cc.getString(0));
                          }
                      } while (cc.moveToNext());
                  }
              }
//              try {
//                  if (cc.getCount() != 0) {
//                      if (cc.moveToFirst()) {
//                          do {
//
//                              String villagedesc = cc.getString(0).trim();
//
//                              Cursor cgrama = dbs.rawQuery("select out_depend_code from masterl where out_master_code = '" + cc.getString(1) + "'", null);
//                              try {
//                                 // if (cgrama.getCount() > 0) {
//                                      if (cgrama.moveToFirst()) {
//                                          Cursor ctaluk = dbs.rawQuery("select out_depend_code from masterl where out_master_code = '" + cgrama.getString(0) + "'", null);
//                                          try {
//
//
//                                            //  if (ctaluk.getCount() > 0) {
//                                                  if (ctaluk.moveToFirst()) {
//                                                      Cursor cdis = dbs.rawQuery("select out_depend_code from masterl where out_master_code = '" + ctaluk.getString(0) + "'", null);
//                                                      try {
//                                                          //if (cdis.getCount() > 0) {
//                                                              if (cdis.moveToFirst()) {
//                                                                  Log.e("DISLOG", "" + cdis.getString(0));
//                                                                  if (cdis.getString(0).equalsIgnoreCase(sharedpreferences.getString("lo", "")))
//                                                                      if (arrayn.contains(villagedesc)) {
//
//                                                                      } else {
//                                                                          arrayn.add(villagedesc);
//                                                                      }
//                                                              }
//                                                         // }
//                                                      } finally {
//                                                          cdis.close();
//                                                      }
//                                                  }
//                                             // }
//                                          } finally {
//                                              ctaluk.close();
//                                          }
//                                      }
//                                //  }
//                              } finally {
//                                  cgrama.close();
//                              }
//                              // Log.e("VAL",""+cursor.getString(11));
//
//                          } while (cc.moveToNext());
//                      }
//
//                  }
//                 // pdialog.dismiss();
//              }
//              finally {
//                  cc.close();
//              }

              ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(MainActivity3.this,
                      R.layout.spinnertext, arrayn);

              elc.setAdapter(adapterlist2n);

              elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  @Override
                  public void onItemClick (AdapterView<?> parent, View view, int position, long id) {


                      pojolocList.clear();
                      // elc.setText(""+(String) parent.getItemAtPosition(position));
//                            Cursor cursorh = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_depend_code","out_parent_code"
//                                    }, "out_master_description" + " LIKE ?",
//                                    new String[]{"%"+ (String) parent.getItemAtPosition(position)+ "%"}, null, null, null, null);
//                            if(cursorh.getCount()!=0)
//                            {
//                                if (cursorh.moveToFirst()) {
//                                    do {
//                                       if(cursorh.getString(3).equalsIgnoreCase("QCD_UN_HAMLET"))
//                                       {
//                                           Pojoloc pojoloc = new Pojoloc();
//
//                                           pojoloc.setS9(cursorh.getString(0));
//                                           pojoloc.setS10(cursorh.getString(1));
//
//                                           String hmv = cursorh.getString(2);
//                                           Log.e("NULL",""+hmv);
                      Cursor cursor = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_depend_code","out_parent_code"
                              }, "out_master_description" + " LIKE ?",
                              new String[]{"%"+ (String) parent.getItemAtPosition(position)+ "%"}, null, null, null, null);
                      if(cursor.getCount()!=0)
                      {
                          if (cursor.moveToFirst()) {
                              do {

                                  if(cursor.getString(3).equalsIgnoreCase("QCD_UN_VILLAGE"))
                                  {
                                      Pojoloc pojoloc = new Pojoloc();
                                      pojoloc.setS1(cursor.getString(0));
                                      pojoloc.setS2(cursor.getString(1));

                                      String dgp = cursor.getString(2);

                                      Cursor cc = dbs.query("masterl", new String[]{"out_master_description","out_master_code"
                                              }, "out_depend_code" + "=?",
                                              new String[]{pojoloc.getS1()}, null, null, null, null);
                                      if(cc.getCount()!=0) {
                                          if (cc.moveToFirst()) {
                                             // do {

                                                  pojoloc.setS9(cc.getString(1));
                                                  pojoloc.setS10(cc.getString(0));



                                             // } while (cc.moveToNext());
                                          }
                                      }

                                      Log.e("NULL",""+dgp);
                                      // pojoloc.setS3(cursor.getString(1));
                                      Cursor cursorg = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_depend_code"
                                              }, "out_master_code" + "=? COLLATE NOCASE",
                                              new String[]{dgp}, null, null, null, null);
                                      if(cursorg.getCount()!=0)
                                      {
                                          if (cursorg.moveToFirst()) {
                                           //   do {



                                                  pojoloc.setS3(cursorg.getString(0));
                                                  pojoloc.setS4(cursorg.getString(1));
                                                  String dt = cursorg.getString(2);

                                                  // pojoloc.setS3(cursor.getString(1));
                                                  Cursor cursort = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_depend_code"
                                                          }, "out_master_code" + "=? COLLATE NOCASE",
                                                          new String[]{dt}, null, null, null, null);
                                                  if(cursort.getCount()!=0)
                                                  {
                                                      if (cursort.moveToFirst()) {
                                                        //  do {



                                                              pojoloc.setS5(cursort.getString(0));
                                                              pojoloc.setS6(cursort.getString(1));
                                                              String dd = cursort.getString(2);
                                                              Log.e("NULL",""+dd);


                                                              // pojoloc.setS3(cursor.getString(1));


                                                              Cursor cursord = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_depend_code"
                                                                      }, "out_master_code" + "=? COLLATE NOCASE",
                                                                      new String[]{dd}, null, null, null, null);
                                                              if(cursord.getCount()!=0)
                                                              {
                                                                  if (cursord.moveToFirst()) {
                                                                    //  do {



                                                                          pojoloc.setS7(cursord.getString(0));
                                                                          pojoloc.setS8(cursord.getString(1));


                                                                          // pojoloc.setS3(cursor.getString(1));


                                                                      Log.e("LLOOCC",""+cursord.getString(2)+"//"+sharedpreferences.getString("lo",""));
                                                                      if(cursord.getString(2).equalsIgnoreCase(sharedpreferences.getString("lo",""))) {
                                                                          pojolocList.add(pojoloc);
                                                                      }
                                                                      else
                                                                      {
                                                                          Toast.makeText(MainActivity3.this, "Selected Village Not Belongs To Selected FPO", Toast.LENGTH_SHORT).show();
                                                                      }
                                                                          // array2.add(cursor.getString(11));
                                                                          // Log.e("VAL",""+cursor.getString(11));
                                                                          // recyclerView.setAdapter(adapterd);
                                                                          // Log.e("VAL",""+cursor.getString(11));

                                                                    //  } while (cursord.moveToNext());
                                                                  }


                                                              }
                                                              // array2.add(cursor.getString(11));
                                                              // Log.e("VAL",""+cursor.getString(11));
                                                              // recyclerView.setAdapter(adapterd);
                                                              // Log.e("VAL",""+cursor.getString(11));

                                                        //  } while (cursort.moveToNext());
                                                      }


                                                  }

                                                  // pojolocList.add(pojoloc);
                                                  // array2.add(cursor.getString(11));
                                                  // Log.e("VAL",""+cursor.getString(11));
                                                  // recyclerView.setAdapter(adapterd);
                                                  // Log.e("VAL",""+cursor.getString(11));

                                             // } while (cursorg.moveToNext());
                                          }


                                      }

                                      // pojolocList.add(pojoloc);
                                      // array2.add(cursor.getString(11));
                                      // Log.e("VAL",""+cursor.getString(11));

                                      // Log.e("VAL",""+cursor.getString(11));
                                  }





                              } while (cursor.moveToNext());
                          }
                          recyclerView.setAdapter(adapterd);


                      }

//                                       }
//                                        //Log.e("NULL",""+dgp)
//                                    } while (cursorh.moveToNext());
//                                }
//
//
//                            }



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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(farmername.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
//                    else if(surname.getText().toString().equalsIgnoreCase(""))
//                    {
//                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
////set icon
//                                .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                .setTitle("Error!")
////set message
//                                .setMessage("Empty Sur Name!")
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
                else  if(fathername.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                else if(mobileno.getText().toString().equalsIgnoreCase("")||mobileno.getText().toString().length()<10)
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                else if(gender.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                else  if(edob.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
//                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                else  if(pincode.getText().toString().equalsIgnoreCase("")||pincode.getText().toString().length()<6)
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                else if(village.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                else if(grama.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                else if(taluk.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                else  if(district.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
//                    
                else
                {



                    if(isNetworkAvailable()) {

                       save();
//                        pdialog.setCanceledOnTouchOutside(false);
//                        pdialog.setTitle("Sending OTP Please Wait.......");
//                        pdialog.show();
//                      //  save.setEnabled(false);
//
//                        Toast.makeText(MainActivity3.this, "Loading Please Wait.....", Toast.LENGTH_SHORT).show();
//                        Random rand = new Random();
//                        final int otp = rand.nextInt(9999);
//                        final String url = "https://www.smsgatewayhub.com/api/mt/SendSMS?APIKey=cEieSKfwykGjjqMpHHHHHHHH&senderid=GNSAIN&channel=2&DCS=0&flashsms=0&number=91"+mobileno.getText().toString()+"&text=Your OTP is "+otp+". Please click below link to view the Consent Form before keying the OTP http://169.38.77.190:82/TAC/TAC -FLEXICODE";
//                        Log.e("URL",""+url);
//
//// prepare the Request
//                        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                                new Response.Listener<JSONObject>()
//                                {
//                                    @Override
//                                    public void onResponse(JSONObject response) {
//                                        pdialog.dismiss();
//                                        // display response
//                                        final Dialog dialog = new Dialog(MainActivity3.this);
//                                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                                        dialog.setCancelable(false);
//                                        dialog.setContentView(R.layout.otppage);
//                                        dialog.setCanceledOnTouchOutside(false);
//
//
//
//                                        Button dialogButton = (Button) dialog.findViewById(R.id.botp);
//                                        final EditText eotp = (EditText) dialog.findViewById(R.id.eotp);
//                                        dialogButton.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//
//                                                if(eotp.getText().toString().equalsIgnoreCase(String.valueOf(otp)))
//                                                {
//
//                                                    otps = eotp.getText().toString();
//                                                    dialog.dismiss();
//                                                   // save();
//                                                    Toast.makeText(MainActivity3.this, "Valid OTP", Toast.LENGTH_SHORT).show();
//                                                }
//                                                else
//                                                {
//                                                    Toast.makeText(MainActivity3.this, "In Valid OTP", Toast.LENGTH_SHORT).show();
//                                                   // dialog.dismiss();
//                                                }
//
//                                            }
//                                        });
//
//                                        dialog.show();
//                                        Log.d("Response", response.toString());
//                                    }
//                                },
//                                new Response.ErrorListener()
//                                {
//                                    @Override
//                                    public void onErrorResponse(VolleyError error) {
//                                        Log.d("Error.Response", String.valueOf(error));
//                                    }
//                                }
//                        );
//
//// add it to the RequestQueue
//                        getRequest.setRetryPolicy(new DefaultRetryPolicy(
//                                50000,
//                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                        VolleySingleton.getInstance(MainActivity3.this).addToRequestQueue(getRequest);


                    }
                    else {

                        final SQLiteDatabase dbs = db.getWritableDatabase();
                        Cursor c1 = dbs.query("farlist", new String[]{"fc"
                                }, "fn" + "=? COLLATE NOCASE" + " AND " + "v1" + "=? COLLATE NOCASE" + " AND " + "sn" + "=? COLLATE NOCASE" + " AND " + "v4" + "=? COLLATE NOCASE",
                                new String[]{farmername.getText().toString(), surname.getText().toString(), fathername.getText().toString(), edob.getText().toString()}, null, null, null, null);


                        Cursor c2 = dbs.query("farlist", new String[]{"fc"
                                }, "fn" + "=? COLLATE NOCASE" + " AND " + "sn" + "=? COLLATE NOCASE" + " AND "  + "v4" + "=? COLLATE NOCASE",
                                new String[]{farmername.getText().toString(), fathername.getText().toString(), edob.getText().toString()}, null, null, null, null);

                        Cursor c3 = dbs.query("farlist", new String[]{"fc"
                                }, "fn" + "=? COLLATE NOCASE" + " AND " + "vi" + "=? COLLATE NOCASE" +  " AND " + "v4" + "=? COLLATE NOCASE",
                                new String[]{farmername.getText().toString(), village.getText().toString(), edob.getText().toString()}, null, null, null, null);

                        Cursor c4 = dbs.query("farlist", new String[]{"fc"
                                }, "fn" + "=? COLLATE NOCASE" + " AND " + "v1" + "=? COLLATE NOCASE" + " AND " + "sn" + "=? COLLATE NOCASE" ,
                                new String[]{farmername.getText().toString(), surname.getText().toString(), fathername.getText().toString()}, null, null, null, null);


                        Cursor c5 = dbs.query("farlist", new String[]{"fc"
                                }, "fn" + "=? COLLATE NOCASE" + " AND " + "v3" + "=? COLLATE NOCASE",
                                new String[]{farmername.getText().toString(), "NA"}, null, null, null, null);

                        Cursor c6 = dbs.query("farlist", new String[]{"fc"
                                }, "fn" + "=? COLLATE NOCASE" + " AND " + "sn" + "=? COLLATE NOCASE" + " AND " + "v4" + "=? COLLATE NOCASE",
                                new String[]{farmername.getText().toString(), fathername.getText().toString(), edob.getText().toString()}, null, null, null, null);

                        if(c1.getCount()>0)
                        {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Duplicate Farmer - Combination of FarmerName/Surname/FHWName/DOB!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                    potdupoffline();
                                                }
                                            }
//set negative button

                                    )
                                    .show();
                        }
                        else
                        {
                            if(c2.getCount()>0)
                            {
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Duplicate Farmer - Combination of FarmerName/FHWName/DOB!")
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        potdupoffline();
                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                            }
                            else
                            {
                                if(c3.getCount()>0)
                                {
                                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                            .setTitle("Error!")
//set message
                                            .setMessage("Duplicate Farmer - Combination of FarmerName/DOB!")
//set positive button
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            //set what would happen when positive button is clicked
                                                            potdupoffline();
                                                        }
                                                    }
//set negative button

                                            )
                                            .show();
                                }
                                else
                                {
                                    if(c4.getCount()>0)
                                    {
                                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                                .setTitle("Error!")
//set message
                                                .setMessage("Duplicate Farmer - Combination of FarmerName!")
//set positive button
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                //set what would happen when positive button is clicked
                                                                potdupoffline();
                                                            }
                                                        }
//set negative button

                                                )
                                                .show();
                                    }
                                    else
                                    {
                                        if(c5.getCount()>0)
                                        {
                                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                                    .setTitle("Error!")
//set message
                                                    .setMessage("Duplicate Farmer - Combination of FarmerName!")
//set positive button
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    //set what would happen when positive button is clicked
                                                                    potdupoffline();
                                                                }
                                                            }
//set negative button

                                                    )
                                                    .show();
                                        }
                                        else
                                        {
                                            if(c6.getCount()>0)
                                            {
                                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                                        .setTitle("Error!")
//set message
                                                        .setMessage("Duplicate Farmer - Combination of FarmerName/FHWName/DOB!")
//set positive button
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        //set what would happen when positive button is clicked
                                                                        potdupoffline();
                                                                    }
                                                                }
//set negative button

                                                        )
                                                        .show();
                                            }
                                            else
                                            {


                        Long tsLong = System.currentTimeMillis() / 1000;
                        String ts = tsLong.toString();
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString("fcode", ts);
                        editor.putString("fid", "0");
                        fan = "0";
                        faid = Integer.parseInt("0");

                        String n1 = farmername.getText().toString();
                        String n2 = surname.getText().toString();
                        String n3 = fathername.getText().toString();
                        String n4 = mobileno.getText().toString();
                        String n5 = gender.getText().toString();
                        String n6 = edob.getText().toString();
                        String n7 = sharedpreferences.getString("orgn", "");
                        String n8 = pincode.getText().toString();
                        String n9 = eaddress.getText().toString();
                        String n10 = village.getText().toString();
                        String n11 = grama.getText().toString();
                        String n12 = taluk.getText().toString();
                        String n13 = district.getText().toString();
                        String n14 = "UP";
                        String n15 = "India";
                        String n16 = "0";
                        String n17 = "0";
                        String n18 = mv;
                        String n19 = mg;
                        String n20 = mt;
                        String n21 = md;
                        String n22 = ts;
                        String n23 = hamlet.getText().toString();
                        String n24 = "";
                        String n25 = sharedpreferences.getString("oc1","");
                        String n26 = "";
                        String n27 = "";


                        try {
                            db.insertfarmer(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20, n21, n22, n23, n24, n25, n26, ui);
                            //spinner.setEnabled(true);
                            editor.commit();
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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

                                                    //fname.setText(farmername.getText().toString());
                                                    //set what would happen when positive button is clicked
                                                    save.setEnabled(false);

                                                }
                                            }
//set negative button

                                    )
                                    .show();
                        } catch (SQLiteException exception) {
                            potdupoffline();

//                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
////set icon
//                                    .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                    .setTitle("Alert!")
////set message
//                                    .setMessage("Farmer Detail Already Found!")
//
//
////set positive button
//                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialogInterface, int i) {
//
//
//
//                                                }
//                                            }
////set negative button
//
//                                    )
//                                    .show();

                        }
                    }}}}}}



                    }
                    //Toast.makeText(MainActivity3.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addnew = (Button)findViewById(R.id.addnew);

        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("focde", "");
                editor.putString("fid", "");

                editor.commit();

                modeflag = "I";

                fan = "0";
                faid = 0;


                encodedImage = "0";


                farmername.setText("");
                surname.setText("");
                fathername.setText("");
                mobileno.setText("");
                gender.setText("");
                edob.setText("");
                pincode.setText("");
                eaddress.setText("");
                village.setText("");
                grama.setText("");
                taluk.setText("");
                district.setText("");
                hamlet.setText("");
                captureh.setImageResource(0);
                captureh.setBackgroundResource(R.drawable.capture);

            }
        });
        elnoa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() > 0 && editable.toString().startsWith(".")){
                    editable.clear();
                }
            }
        });
    }

    private void potdupoffline(){
        Cursor cr3 = dbs.rawQuery("select * from farlist where fn='"+farmername.getText().toString()+"'" +
                "and sn='"+fathername.getText().toString()+"'and v4='"+edob.getText().toString()+"'",null);
        if(cr3.getCount()>0){
            if(cr3.moveToFirst()){
                do{
                    Log.e("fpoorgncodes",cr3.getString(cr3.getColumnIndexOrThrow("fpoorgn_code")));
                    Log.e("fpoorgncodes",village.getText().toString());
                    fpoorgncodes = cr3.getString(cr3.getColumnIndexOrThrow("fpoorgn_code"));
                }while(cr3.moveToNext());
            }
        }
        if(!fpoorgncodes.equals(sharedpreferences.getString("oc1",""))){
            Cursor cr1 = dbs.rawQuery("select * from farlist where fn='"+farmername.getText().toString()+"'" +
                    "and sn='"+fathername.getText().toString()+"'and v4='"+edob.getText().toString()+"'",null);
            Log.e("counts", String.valueOf(cr1.getCount()));
            if(cr1.getCount()>0){
                if(cr1.moveToFirst()){
                    do{
                        Pojokyc.mapfcode = cr1.getString(cr1.getColumnIndexOrThrow("fc"));
                        Log.e("details", Pojokyc.mapfcode);
                        Intent intent = new Intent(MainActivity3.this,MemberMapping.class);
                        startActivity(intent);

                    }while(cr1.moveToNext());
                }
            }
        }else{
            final AlertDialog dialog1 = new AlertDialog.Builder(MainActivity3.this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Alert!")
                    .setMessage("Entered Farmer is already in this fpo")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        }
    }
    private boolean isNetworkAvailable() {
        
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public class MyRecyclerViewAdapterd extends RecyclerView.Adapter<MainActivity3.MyRecyclerViewAdapterd.ViewHolder> {

        private List<Pojoloc> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapterd(Context context, List<Pojoloc> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MainActivity3.MyRecyclerViewAdapterd.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.suplist2, parent, false);
            return new MainActivity3.MyRecyclerViewAdapterd.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(final MainActivity3.MyRecyclerViewAdapterd.ViewHolder holder, final int position) {
            final Pojoloc pojoloc = mData.get(position);
            holder.name.setText(pojoloc.getS2());
            holder.ph.setText(pojoloc.getS4());
            holder.lcn.setText(pojoloc.getS6());
            holder.ty.setText(pojoloc.getS8());
            holder.ham.setText(pojoloc.getS10());
            //holder.llist.setBackgroundResource(R.drawable.rbutton);
            holder.llist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.llist.setBackgroundResource(R.drawable.rbutton2);
                    if(pg==4){
                        landvillage.setText(pojoloc.getS2());
                    }else{
                        village.setText(pojoloc.getS2());
                        grama.setText(pojoloc.getS4());
                        taluk.setText(pojoloc.getS6());
                        district.setText(pojoloc.getS8());
                        hamlet.setText(pojoloc.getS10());
                        mv = pojoloc.getS1();
                        mg = pojoloc.getS3();
                        mt = pojoloc.getS5();
                        md = pojoloc.getS7();
                        ham = pojoloc.getS9();
                    }
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
            TextView myTextView,trate,tamt,tnamt,tdis,tqty,name,ph,lcn,ty,ham;
            ImageView del,ed;
            LinearLayout llist;

            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.tvn);
                ph = itemView.findViewById(R.id.tgpn);
                lcn = itemView.findViewById(R.id.ttn);
                ty = itemView.findViewById(R.id.tdn);
                llist = itemView.findViewById(R.id.llist);
                ham = itemView.findViewById(R.id.ham);





            }


        }

        // convenience method for getting data at click position




        // parent activity will implement this method to respond to click events

    }
    public void save()
    {
       // consent.setVisibility(View.VISIBLE);

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
            user.put("instance", Pojokyc.instance);
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("in_farmer_rowid",faid);
            user2.put("in_farmer_code",fan);
            user2.put("in_version_no",1);
            user2.put("in_photo_farmer",encodedImage);

            user2.put("in_farmer_name",farmername.getText().toString());

            if(surname.getText().toString().equalsIgnoreCase(""))
            {
                user2.put("in_sur_name",farmername.getText().toString());
            }
            else
            {

                user2.put("in_sur_name",surname.getText().toString());
            }

            user2.put("in_fhw_name",fathername.getText().toString());
            user2.put("in_farmer_dob",edob.getText().toString());
            user2.put("in_farmer_addr1",eaddress.getText().toString());
            user2.put("in_farmer_addr2",hamlet.getText().toString());
            user2.put("in_farmer_country","QCD_UN_IND");
            //user2.put("in_farmer_state","QCD_UNS_UP");
            user2.put("in_farmer_state",sharedpreferences.getString("lo",""));
            user2.put("in_farmer_dist",md);
            user2.put("in_farmer_taluk",mt);
            user2.put("in_farmer_panchayat",mg);
            user2.put("in_farmer_village",mv);
            user2.put("in_farmer_pincode",pincode.getText().toString());
            user2.put("in_marital_status","");

            String gencode = "";

            Cursor cgender = dbs.rawQuery("select out_master_code from masterl where out_master_description = '"+gender.getText().toString()+"'",null);
            if(cgender.moveToFirst())
            {
                gencode = cgender.getString(0);
            }


            user2.put("in_gender_flag",gencode);

            user2.put("in_reg_mobile_no",mobileno.getText().toString());

            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);
            SharedPreferences.Editor mobilenos = sharedPreferences.edit();
            mobilenos.putString("mob",mobileno.getText().toString());
            Log.e("savemob", mobileno.getText().toString());
            mobilenos.commit();
            user2.put("in_status_code","A");
            user2.put("in_mode_flag",modeflag);
            user2.put("in_row_timestamp","");
            //user2.put("in_farmer_otp",Integer.parseInt(otps));
           // user2.put("in_farmer_doc","");
            user2.put("in_fpo_orgncode",sharedpreferences.getString("oc1",""));
            user2.put("in_created_by", sharedpreferences.getString("un","").toUpperCase()+""+sharedpreferences.getString("phn",""));
            user2.put("in_modified_by", sharedpreferences.getString("un","").toUpperCase()+""+sharedpreferences.getString("phn",""));
            user2.put("in_dup_flag",dubflag);

            user.put("Header",user2);
            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
        }



//
        //169.38.77.190:101
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.url+"/api/Mobile_FDR_FHeader/NewMobileFarmerHeader",jsonParam,
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

                                if(obj3.getString("errorDescription").equalsIgnoreCase("Potential Duplicate")){

                                    Cursor cr2 = dbs.rawQuery("select * from farlist where fn='"+farmername.getText().toString()+"'and sn='"+fathername.getText().toString()+"'" +
                                            "and vi='"+village.getText().toString()+"'", null);
                                    if(cr2.getCount()>0){
                                        if(cr2.moveToFirst()){
                                            do{
                                                Log.e("fpoorgncodes",cr2.getString(cr2.getColumnIndexOrThrow("fpoorgn_code")));
                                                Log.e("fpoorgncodes",village.getText().toString());
                                                fpoorgncodes = cr2.getString(cr2.getColumnIndexOrThrow("fpoorgn_code"));
                                            }while(cr2.moveToNext());
                                        }
                                    }

                                    final AlertDialog dialog = new AlertDialog.Builder(MainActivity3.this)
                                            .setIcon(android.R.drawable.ic_dialog_alert)
                                            .setTitle("Alert!")
                                            .setMessage("Entered Farmer available in another fpo are you sure you want this farmer to map in this fpo")
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    if(!fpoorgncodes.equals(sharedpreferences.getString("oc1",""))){
                                                        Cursor cr1 = dbs.rawQuery("select * from farlist where fn='"+farmername.getText().toString()+"'" +
                                                                "and sn='"+fathername.getText().toString()+"'and v4='"+edob.getText().toString()+"'",null);
                                                        Log.e("counts", String.valueOf(cr1.getCount()));
                                                        if(cr1.getCount()>0){
                                                            if(cr1.moveToFirst()){
                                                                do{
                                                                    Pojokyc.mapfcode = cr1.getString(cr1.getColumnIndexOrThrow("fc"));
                                                                    Log.e("details", Pojokyc.mapfcode);
                                                                    Intent intent = new Intent(MainActivity3.this,MemberMapping.class);
                                                                    startActivity(intent);
                                                                    //membermap(farmercodes);

                                                                }while(cr1.moveToNext());
                                                            }
                                                        }
                                                    }else{
                                                        final AlertDialog dialog1 = new AlertDialog.Builder(MainActivity3.this)
                                                                .setIcon(android.R.drawable.ic_dialog_alert)
                                                                .setTitle("Alert!")
                                                                .setMessage("Entered Farmer is already in this fpo")
                                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        dialogInterface.dismiss();
                                                                    }
                                                                })
                                                                .show();
                                                    }
                                                }
                                            })
                                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    finish();
                                                }
                                            })
                                            .show();
                                }else{
                                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                            .setTitle("Alert!")
//set message
                                            .setMessage(""+obj3.getString("errorDescription"))
//set positive button
//                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                                        //set what would happen when positive button is clicked
//                                                       // dialogInterface.dismiss();
//                                                        dubflag = "Y";
//                                                        save();
//                                                    }
//                                                }
////set negative button
//
//                                        )
                                            .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // dubflag = "N";
                                                    //  finish();

                                                }
                                            })

                                            .show();
                                }
                            }
                            else
                            {
                                //fname.setText("Farmername:"+fn);
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                farmerid = frid;
                                editor.putString("fcode", fn);
                                editor.putString("fid", frid);
                                fan = fn;
                                faid = Integer.parseInt(frid);

                                String n1 = farmername.getText().toString();
                                String n2 = surname.getText().toString();
                                String n3 = fathername.getText().toString();
                                String n4 = mobileno.getText().toString();
                                String n5 = gender.getText().toString();
                                String n6 = edob.getText().toString();
                                String n7 = sharedpreferences.getString("orgn","");
                                String n8 = pincode.getText().toString();
                                String n9 = eaddress.getText().toString();
                                String n10 = village.getText().toString();
                                String n11 = grama.getText().toString();
                                String n12 = taluk.getText().toString();
                                String n13 = district.getText().toString();
                                String n14="";
                                if(sharedpreferences.getString("lo","").equalsIgnoreCase("QCD_UNS_TAMIL"))
                                {
                                     n14 = "Tamil Nadu";
                                }
                                else
                                {
                                     n14 = "UP";
                                }

                                String n15 = "India";
                                String n16 = "1";
                                String n17 = "0";
                                String n18 = mv;
                                String n19 = mg;
                                String n20 = mt;
                                String n21 = md;
                                String n22 = frid;
                                String n23 = hamlet.getText().toString();
                                String n24 = "";
                                String n25 = sharedpreferences.getString("oc1","");
                                String n26 = "";
                                String n27 = "";


                                editor.commit();


                                Pojokyc.farmercode = fn;
                                Pojokyc.farmerid = farmername.getText().toString();
                                Pojokyc.farmermobileno = mobileno.getText().toString();
                                Pojokyc.farmeraddress = n9.toString();
                                if(modeflag.equalsIgnoreCase("I")) {

                                    try {
                                        db.insertfarmer(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20, n21, n22,n23,n24,n25,n26,ui);
                                        db.inserfarlist(frid, fn, n1, n3, n10, n11, n12, n13,n2,n4,"N",n6,"1",sharedpreferences.getString("oc1",""),"0","0");
                                        db.insertcustomer(fn,n1,n10,n9,n14,mv,sharedpreferences.getString("lo",""),"M",n4,sharedpreferences.getString("oc",""),n2,n3);
                                        FormerDao formerDao = new FormerDao(1, frid,
                                                fn, "Member", n3,
                                                n1, md, n13,
                                                mt, n12, mg,
                                                n11, mv, n10);
                                        dbpa.addAllFarmerDetails(formerDao);
                                    } catch (SQLiteException exception) {
                                        Log.d("SQLite", "Error"+exception.toString());
                                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                                else
                                {

                                    try {
                                        db.updatefarmer(Integer.valueOf(farmerid),n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20, n21, n22,n23,ui);
                                        db.updatefarlist(fn,frid,fn,n1,n3,n10,n11,n12,n13,n2,n4,n6,"1");
                                        dbpa.updatefarmer(fn,frid,fn,n3,n1,md,n13,mt,n12,mg,n11,mv,n10);
                                        db.updatecustomer(fn,n1,n10,n9,n18,n4,n2,n3);
                                    } catch (SQLiteException exception) {
                                        Log.d("SQLite", "Error"+exception.toString());
                                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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

                                    //db.
                                    // (frid, fn, n1, "0", n10, n11, n12, n13);
                                }
                                modeflag = "U";
                                consent.setVisibility(View.VISIBLE);
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                                                        modeflag = "U";
                                                        save.setEnabled(false);
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
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                            FirebaseApp.initializeApp(MainActivity3.this);
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Long tsLong = System.currentTimeMillis()/1000;
                            String ts = tsLong.toString();
                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                            Log.e("Valuecheck",""+mRef.getRef());
                            mRef.child("name").setValue("SAVEFARMER");
                            mRef.child("date").setValue(date);
                            mRef.child("Error").setValue(response.toString());
                            mRef.child("Activity").setValue("MAINACTIVITY3");
                        }

                    }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);
                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
    private void membermap(final String farmercodes){
        jsonParam = new JSONObject();
        JSONObject userd = new JSONObject();
        try {
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", fpoorgncodes);
            user.put("locnId", "up");
            user.put("userId", "en_US");
            user.put("localeId", "admin");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();
            user2.put("In_fpoorgn_code",fpoorgncodes);
            user.put("Header",user2);
            JSONArray user4 = new JSONArray();
            JSONObject user3 = new JSONObject();
            user3.put("In_fpomember_rowid", 0);
            user3.put("In_fpomember_code", "0");
            user3.put("In_farmer_code",farmercodes);
            user3.put("In_status_code", "A");
            user3.put("In_row_timestamp", "");
            user3.put("In_mode_flag", "I");
            user4.put(user3);
            user.put("Map",user4);
            Log.e("farmercode", farmercodes);
            Log.e("OUTPUT", jsonParam.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpsTrustManager.allowAllSSL();
        final String url = Pojokyc.icdurl+"api/FISFarmermapping/newFPOFarmerMap";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("responses", "" + response);
                        try {

                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("header");
                            JSONObject obj3 = response.getJSONObject("applicationException");

                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                                                    finish();

                                                }
                                            }
//set negative button

                                    )
                                    .setCancelable(false)
                                    .show();

                            if(obj3.getString("errorNumber").equalsIgnoreCase("Success"))
                            {
                                dbs.execSQL("update farlist set fpoorgn_code='"+fpoorgncodes+"' where fc='"+farmercodes+"'");
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
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edob.setText(sdf.format(myCalendar.getTime()));

    }
    private void updateLabel3() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        vtilldate.setText(sdf.format(myCalendar.getTime()));

    }
    private void updateLabel2() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        String dtt= sdf.format(myCalendar.getTime());
        String[] dt2 = dtt.split("/");

        if(array4.contains(dt2[2]))
        {
            if(array3.contains(dt2[1]))
            {
                stmnt.setText(sdf.format(myCalendar.getTime()));

            }
            else
            {
                stmnt.setText("");
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("This session -"+ccty2s.getText().toString()+" is applicable for "+ Arrays.toString(new ArrayList[]{arraymn})+" Please Select valid Month")
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
            stmnt.setText("");
            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                    .setTitle("Error!")
//set message
                    .setMessage("Selected  Year is "+cyears.getText().toString()+" Please select valid Year")
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
                .start(MainActivity3.this);
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


//                dialog = new Dialog(FarmerRegs.this);
//                Log.e("RCODE",""+data.getStringExtra("SCAN_RESULT"));
//                String scanvalue = data.getStringExtra("SCAN_RESULT");
//                loadFarmer(scanvalue,"","");
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
                if(imagetype == 0)
                {
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
                }

                else if(imagetype == 2)
                {

                    Bundle extras = data.getExtras();
//get the cropped bitmap
                    thePic3 = (Bitmap) extras.get("data");
                    Log.e("PIC", "" + thePic3);
                    captureld.setImageResource(0);
                    captureld.setImageBitmap(thePic3);
                    //vcap.setImageBitmap(thePic);


                    byteArrayOutputStream3 = new ByteArrayOutputStream();
                    thePic3.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream3);
                    ui3 = thePic3.toString();
                    Log.e("JJJJJJ", "" + ui3);
                    try {
                        encodedImage3 = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream3.toByteArray(), Base64.DEFAULT), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                }
                else
                {
                    Bundle extras = data.getExtras();
//get the cropped bitmap
                    thePic2 = (Bitmap) extras.get("data");
                    Log.e("PIC", "" + thePic2);
                    capturek.setImageResource(0);
                    capturek.setImageBitmap(thePic2);
                    //vcap.setImageBitmap(thePic);


                    byteArrayOutputStream2 = new ByteArrayOutputStream();
                    thePic2.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream2);
                    ui2 = thePic2.toString();
                    Log.e("JJJJJJ", "" + ui);
                    try {
                        encodedImage2 = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream2.toByteArray(), Base64.DEFAULT), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {

                    try {

                        if(imagetype == 0)
                        {
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
                        }
                        else if(imagetype == 2) {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.getUri());
                            captureld.setImageResource(0);
                            captureld.setImageBitmap(bitmap);
                            gpsTracker = new GpsTracker(MainActivity3.this);
                            if (gpsTracker.canGetLocation()) {
                                double latitude = gpsTracker.getLatitude();
                                double longitude = gpsTracker.getLongitude();
                                elat.setText("" + latitude);
                                elon.setText("" + longitude);
                                byteArrayOutputStream3 = new ByteArrayOutputStream();
                                ui3 = result.getUri().toString();

                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream3);
                                Log.e("NJNJN", "" + byteArrayOutputStream3.toByteArray());


                                encodedImage3 = Base64.encodeToString(byteArrayOutputStream3.toByteArray(), Base64.DEFAULT);
                                Log.e("PIC", "" + encodedImage3);

                            }
                        }
                        else
                        {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.getUri());
                            capturek.setImageResource(0);
                            capturek.setImageBitmap(bitmap);
                            // vcap.setImageBitmap(bitmap);
                            byteArrayOutputStream2 = new ByteArrayOutputStream();
                            ui2 = result.getUri().toString();
                            Log.e("HIII",""+ui2);

                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream2);
                            Log.e("NJNJN", "" + byteArrayOutputStream2.toByteArray());


                            encodedImage2 = Base64.encodeToString(byteArrayOutputStream2.toByteArray(), Base64.DEFAULT);
                            Log.e("PIC", "" + encodedImage2);
                            FileOutputStream fos = null;
                        }

                        


//                        try {
//                            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
//                            if (!root.exists()) {
//                                root.mkdirs();
//                            }
//                            File gpxfile = new File(root, "nowrap.txt");
//                            FileWriter writer = new FileWriter(gpxfile);
//                            writer.append(encodedImage);
//                            writer.flush();
//                            writer.close();
//                            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
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
    public void addbank()
    {



            if (ub == 0) {
                if (accountno.getText().toString().equalsIgnoreCase("") ) {
                  
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                  
                } else if (ebank.getText().toString().equalsIgnoreCase("")) {
                  
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                
                } else if (ebranch.getText().toString().equalsIgnoreCase("")) {
                    
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                   
                } else {

                    try {

                        if(isNetworkAvailable())
                        {
                            db.insertbank("QCD_ACC_SAVINGS", accountno.getText().toString(), ebank.getText().toString(), eifsc.getText().toString(), ebranch.getText().toString(), "QCD_YES", "QCD_YES", "QCD_YES", sharedpreferences.getString("fcode", ""), "0", 0, "", bcode);

                            String selectQuery2 = "SELECT  * FROM bank where flag = 0";

                            Cursor cursor2 = dbs.rawQuery(selectQuery2, null);
                            if (cursor2.moveToFirst()) {


                                savebank(cursor2.getString(9), Integer.parseInt(cursor2.getString(11)), cursor2.getString(2), cursor2.getString(12), cursor2.getString(13), cursor2.getString(5), cursor2.getString(4), cursor2.getString(7), cursor2.getString(6), cursor2.getString(0));


                                // Log.e("Check",""+cursor.getString(1));


                            }
                        }
                        else
                        {
                            db.insertbank("QCD_ACC_SAVINGS", accountno.getText().toString(), ebank.getText().toString(), eifsc.getText().toString(), ebranch.getText().toString(), "QCD_YES", "QCD_YES", "QCD_YES", sharedpreferences.getString("fcode", ""), "0", 0, "", bcode);

                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Bank Saved Locally Successfully!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                    sdc = "QCD_YES";
                                                    sdd = "QCD_YES";

                                                    accountno.setText("");
                                                    ebank.setText("");
                                                    ebranch.setText("");
                                                    eifsc.setText("");

                                                }
                                            }
//set negative button

                                    )
                                    .show();
                        }
                      


                     
                    } catch (SQLiteException exception) {
                        Log.d("SQLite", "Error" + exception.toString());

                       

                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Alert!")
//set message
                                    .setMessage("Bank Detail Already Found!")
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
            } else {
                if (accountno.getText().toString().equalsIgnoreCase("")) {
                   
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                 
                } else if (ebank.getText().toString().equalsIgnoreCase("")) {
                 
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                   
                } else if (ebranch.getText().toString().equalsIgnoreCase("")) {
                  
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                   
                } else {

                    try {

                        if(isNetworkAvailable()) {


                            db.updatebank(Integer.valueOf(bankid), "QCD_ACC_SAVINGS", accountno.getText().toString(), ebank.getText().toString(), eifsc.getText().toString(), ebranch.getText().toString(), "QCD_YES", "QCD_YES", "QCD_YES", sharedpreferences.getString("fcode", ""), "0", Integer.parseInt(bbid), "", bcode);
                            String selectQuery2 = "SELECT  * FROM bank where flag = 0";

                            Cursor cursor2 = dbs.rawQuery(selectQuery2, null);

                            if (cursor2.moveToFirst()) {


                                savebank(cursor2.getString(9), Integer.parseInt(cursor2.getString(11)), cursor2.getString(2), cursor2.getString(12), cursor2.getString(13), cursor2.getString(5), cursor2.getString(4), cursor2.getString(7), cursor2.getString(6), cursor2.getString(0));


                                // Log.e("Check",""+cursor.getString(1));


                            }
                        }
                        else
                        {
                            db.updatebank(Integer.valueOf(bankid), "QCD_ACC_SAVINGS", accountno.getText().toString(), ebank.getText().toString(), eifsc.getText().toString(), ebranch.getText().toString(), "QCD_YES", "QCD_YES", "QCD_YES", sharedpreferences.getString("fcode", ""), "0", Integer.parseInt(bbid), "", bcode);

                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_menu_save)
//set title
                                    .setTitle("Success!")
//set message
                                    .setMessage("Bank Updated Locally Successfully!")
//set positive button
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //set what would happen when positive button is clicked
                                                    sdc = "QCD_YES";
                                                    sdd = "QCD_YES";

                                                    accountno.setText("");
                                                    ebank.setText("");
                                                    ebranch.setText("");
                                                    eifsc.setText("");

                                                }
                                            }
//set negative button

                                    )
                                    .show();
                        }
                  
                    } catch (SQLiteException exception) {
                        Log.d("SQLite", "Error" + exception.toString());
                       
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Alert!")
//set message
                                    .setMessage("Bank Detail Already Found!")
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
    public class MyRecyclerViewAdapterb extends RecyclerView.Adapter<MainActivity3.MyRecyclerViewAdapterb.ViewHolder> {

        private List<Pojobank> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapterb(Context context, List<Pojobank> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MainActivity3.MyRecyclerViewAdapterb.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.suplist3, parent, false);
            return new MainActivity3.MyRecyclerViewAdapterb.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(final MainActivity3.MyRecyclerViewAdapterb.ViewHolder holder, final int position) {
            final Pojobank pojobank = mData.get(position);
            if(bstatus == 0) {
                holder.name.setText(pojobank.getBc());
                holder.ph.setText(pojobank.getBn());
                holder.lcn.setText(pojobank.getBrn());
                holder.ty.setText(pojobank.getIfsc());
                // holder.llist.setBackgroundResource(R.drawable.rbutton);
                holder.llist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eifsc.setEnabled(true);
                        holder.llist.setBackgroundResource(R.drawable.rbutton2);

                        bcode = pojobank.getBc();
                        ebank.setText(pojobank.getBn());
                        ebranch.setText(pojobank.getBrn());
                        eifsc.setText(pojobank.getIfsc());
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
        Log.e("NULL",""+id);
        if(id == 0)
        {
            modeflag = "I";
        }
        else
        {
            modeflag = "U";
        }


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
            user.put("instance", Pojokyc.instance);
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("in_farmer_code",fc);
            user2.put("in_farmerbank_rowid",id);
            user2.put("in_bank_acc_no",an);
            user2.put("in_bank_acc_type","QCD_ACC_SAVINGS");

            user2.put("in_bank_code",bc);
            user2.put("in_branch_code",be);
            user2.put("in_ifsc_code",ifc);
            user2.put("in_dflt_dr_acc",dd);
            user2.put("in_dflt_cr_acc",dc);
            user2.put("in_status_code","A");
            user2.put("in_mode_flag",modeflag);
            user2.put("in_created_by", sharedpreferences.getString("un","").toUpperCase()+""+sharedpreferences.getString("phn",""));
            user2.put("in_modified_by", sharedpreferences.getString("un","").toUpperCase()+""+sharedpreferences.getString("phn",""));



            user.put("Bank",user2);
            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
        }




        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.url+"/api/Mobile_FDR_FHeader/NewMobileFarmerBank",jsonParam,
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
                            bankid = frid;
                            ub=0;

                            if(frid.equalsIgnoreCase("0")) {


                                pdialog.dismiss();
                                if(ub == 0)
                                {
                                    dbs.execSQL("DELETE FROM bank WHERE id = (SELECT MAX(id) FROM bank);");
                                }
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                                FirebaseApp.initializeApp(MainActivity3.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis()/1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                Log.e("Valuecheck",""+mRef.getRef());
                                mRef.child("name").setValue("SAVEBANK");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("MAINACTIVITY3");

                            }
                            else
                            {


                          
                                final SQLiteDatabase dbs = db.getWritableDatabase();

                                dbs.execSQL("UPDATE bank SET flag = 1 WHERE id = " + idk);
                                dbs.execSQL("UPDATE bank SET bid = "+frid+" WHERE id = " + idk);
                                //   dbs.execSQL("DELETE FROM bank WHERE id = " + idk);
                                sdc = "QCD_YES";
                                sdd = "QCD_YES";

                                accountno.setText("");
                                ebank.setText("");
                                ebranch.setText("");
                                eifsc.setText("");
                                pdialog.dismiss();
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                        .setIcon(android.R.drawable.ic_menu_save)
//set title
                                        .setTitle("Success!")
//set message
                                        .setMessage("Bank Inserted Successfully")
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
                            if(ub == 0)
                            {
                                dbs.execSQL("DELETE FROM bank WHERE id = (SELECT MAX(id) FROM bank);");
                            }
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
                            FirebaseApp.initializeApp(MainActivity3.this);
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Long tsLong = System.currentTimeMillis()/1000;
                            String ts = tsLong.toString();
                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                            Log.e("Valuecheck",""+mRef.getRef());
                            mRef.child("name").setValue("SAVEBANK");
                            mRef.child("date").setValue(date);
                            mRef.child("Error").setValue(response.toString());
                            mRef.child("Activity").setValue("MAINACTIVITY3");
                        }

                    }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);
                        pdialog.dismiss();
                        if(ub == 0)
                        {
                            dbs.execSQL("DELETE FROM bank WHERE id = (SELECT MAX(id) FROM bank);");
                        }
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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
    public  class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

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
            View view = mInflater.inflate(R.layout.tablistdetails2, parent, false);
            return new MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the textview2 in each row
        @Override
        public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, final int position) {
            final Pojokyc pojokyc = mData.get(position);

            if(p == 3)
            {
                holder.lbank.setVisibility(View.VISIBLE);
                holder.lkyc.setVisibility(View.GONE);
                holder.lsow.setVisibility(View.GONE);
                holder.lland.setVisibility(View.GONE);

                holder.tan.setText(pojokyc.getAn());
                holder.tb.setText(pojokyc.getBn());
                holder.tbr.setText(pojokyc.getBr());
                holder.ti.setText(pojokyc.getBi());


//                holder.del2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//
//                        db.deletebank(Integer.valueOf(pojokyc.getBid()));
//                        listitem.remove(position);
//                        adapter.notifyDataSetChanged();
//
//                        //counttotal();
//
//                    }
//                });

                holder.ed2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        accountno.setText(pojokyc.getAn());
                        ebank.setText(pojokyc.getBn());
                        ebranch.setText(pojokyc.getBr());
                        eifsc.setText(pojokyc.getBi());
                        bankid = pojokyc.getBid();
                        bbid = pojokyc.getBbid();

                        dialog.dismiss();
                        ub=1;
                       // bsave.setText("Update");

                        bcode = pojokyc.getBco();




                    }
                });
                holder.lbank.setVisibility(View.VISIBLE);



            }
            if(p == 2)
            {
                holder.lbank.setVisibility(View.GONE);
                holder.lsow.setVisibility(View.GONE);
                holder.lkyc.setVisibility(View.VISIBLE);
                holder.lland.setVisibility(View.GONE);
                holder.type.setText(pojokyc.getT());
                holder.stype.setText(pojokyc.getSt());
                holder.dn.setText(pojokyc.getDno());
                holder.vtd.setText(pojokyc.getVtd());
               // holder.dn.setText(pojokyc.getDno());


                ksty = pojokyc.getStmc();

//                holder.del.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//
//                        db.dkyc(Integer.valueOf(pojokyc.getId()));
//                        listitem.remove(position);
//                        adapter.notifyDataSetChanged();
//
//                        //counttotal();
//
//                    }
//                });
                holder.ed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //ksave.setText("Update");

                        dialog.dismiss();

                        ksty = pojokyc.getStmc();
                        kycid = pojokyc.getId();
                        kycrid = pojokyc.getKrid();
                        uk = 1;
                        kttype.setText(pojokyc.getT());
                        kstype.setText(pojokyc.getSt());
                        kty =  getmastercode(pojokyc.getT());
                        ksty = getmastercode(pojokyc.getSt());
                        documentno.setText(pojokyc.getDno());
                        vtilldate.setText(pojokyc.getVtd());
                        cdocumentno.setText(pojokyc.getDno());

                        ui2 = pojokyc.getPoto();
                        if (pojokyc.getPoto().equalsIgnoreCase("0")) {
                            encodedImage2 = "0";
                            ui2 = "0";
                        } else {
                            try {

                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(pojokyc.getPoto()));

                                byteArrayOutputStream2 = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream2);
                                //Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());


                                encodedImage2 = Base64.encodeToString(byteArrayOutputStream2.toByteArray(), Base64.DEFAULT);
                                capturek.setImageResource(0);
                                capturek.setImageBitmap(bitmap);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
                    }

            if(p == 21)
            {

                holder.lbank.setVisibility(View.GONE);
                holder.lsow.setVisibility(View.VISIBLE);
                holder.lkyc.setVisibility(View.GONE);
                holder.lland.setVisibility(View.GONE);
                holder.ts1.setText(pojokyc.getSd1());
                holder.ts2.setText(pojokyc.getE1());
                holder.ts3.setText(pojokyc.getE4());
                holder.ts4.setText(pojokyc.getSd4());
                holder.ts5.setText(pojokyc.getSd5());
                holder.ts6.setText(pojokyc.getSd6());
                holder.ts7.setText(pojokyc.getSd7());
                holder.ts8.setText(pojokyc.getE3());
                holder.ts9.setText(pojokyc.getSd9());
                holder.ts10.setText(pojokyc.getE2());
                 //holder.ts11.setText(pojokyc.getSd8());
                holder.ts12.setText(pojokyc.getSd8());
                holder.ts11.setText(pojokyc.getE5());




                holder.ed3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        us =1;
                        array4.clear();

                        sowid = Integer.parseInt(pojokyc.getSdid());
                        sowsn = pojokyc.getSdsn();
                        cyears.setText(pojokyc.getSd1());
                        array4.add(pojokyc.getSd1());
                        ccty2s.setText(pojokyc.getE1());
                        codes = pojokyc.getSd2();
                        ccts.setText(pojokyc.getE4());
                       codecn=pojokyc.getSd3();
                        cvars.setText(pojokyc.getSd4());
                        sdt1.setText(pojokyc.getSd5());
                        sdt2.setText(pojokyc.getSd6());
                        sdt3.setText(pojokyc.getSd7());
                        sdt4.setText(pojokyc.getSd8());
                        sdt5.setText(pojokyc.getSd9());
                        stmnt.setText(pojokyc.getE2());
                        codem=pojokyc.getSd10();
                        stclf.setText(pojokyc.getE3());
                        codecr=pojokyc.getSd11();


                        stsnm.setText(pojokyc.getE5());
                        codesn=pojokyc.getSd13();


                        arraysc.clear();
                        arraysn.clear();
                        arraymn.clear();
                        arraymc.clear();
                        arraycrc.clear();
                        arraycrn.clear();
                        array3.clear();
                        arraycnc.clear();
                        arraycnn.clear();
                        arrayvc.clear();
                        arrayvn.clear();
                        arraysec.clear();
                        arraysen.clear();
                        Cursor cursor2 = dbs.query("tablist", new String[]{"field"
                                }, "tab" + "=?",
                                new String[]{"Sowing Details"}, null, null, null, null);


                        final SQLiteDatabase dbs = db.getWritableDatabase();
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
                                        //  Toast.makeText(MainActivity4.this, "hi", Toast.LENGTH_SHORT).show();
                                        // hsvssy.setVisibility(View.VISIBLE);
                                        if (tf[1].replaceAll(" ", "").equalsIgnoreCase("")) {

                                        } else {

                                            String tv = tf[1];
                                            String tv2 = tv.substring(1, tv.length() - 1);
                                            tfs = tv2.split(",");
                                            Log.e("YEAR",""+ Arrays.toString(tfs));
                                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                                    (MainActivity3.this, R.layout.spinnertext3, tfs);
                                            //Getting the instance of AutoCompleteTextView

                                            cyears.setThreshold(0);//will start working from first character
                                            cyears.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                                            cyears.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    cyears.showDropDown();
                                                    cyears.requestFocus();
                                                }
                                            });




                                        }
                                    }



                                }

                            } while (cursor2.moveToNext());
                        }

                        Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_parent_code"
                                }, "out_depend_code" + "=?",
                                new String[]{codes}, null, null, null, null);






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



//                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                                    (MainActivity3.this, R.layout.spinnertext3, arraymn);
//                            //Getting the instance of AutoCompleteTextView
//
//                            stmnt.setThreshold(0);//will start working from first character
//                            stmnt.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                            stmnt.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    stmnt.showDropDown();
//                                    stmnt.requestFocus();
//                                }
//                            });
                            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                                    (MainActivity3.this, R.layout.spinnertext3, arraycrn);
                            //Getting the instance of AutoCompleteTextView

                            stclf.setThreshold(0);//will start working from first character
                            stclf.setAdapter(adapter2);//setting the adapter data into the AutoCompleteTextView
                            stclf.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    stclf.showDropDown();
                                    stclf.requestFocus();
                                }
                            });
                        }

                        Cursor cursor2mst1 = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_parent_code"
                                }, "out_depend_code" + "=?",
                                new String[]{codecr}, null, null, null, null);






                        if (cursor2mst1.moveToFirst()) {

                            do {
                                if(cursor2mst1.getString(2).equalsIgnoreCase("QCD_SOW_CROPNAME")) {


                                    arraycnc.add(cursor2mst1.getString(0));
                                    arraycnn.add(cursor2mst1.getString(1));
                                }

                            }while(cursor2mst1.moveToNext());



                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (MainActivity3.this, R.layout.spinnertext3, arraycnn);
                            //Getting the instance of AutoCompleteTextView

                            ccts.setThreshold(0);//will start working from first character
                            ccts.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                            ccts.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ccts.showDropDown();
                                    ccts.requestFocus();
                                }
                            });

                        }
                        Cursor cursor2mst2 = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_parent_code"
                                }, "out_depend_code" + "=?",
                                new String[]{codecn}, null, null, null, null);






                        if (cursor2mst2.moveToFirst()) {

                            do {
                                if(cursor2mst2.getString(2).equalsIgnoreCase("QCD_SOW_CROPVARIETY")) {


                                    arrayvc.add(cursor2mst2.getString(0));
                                    arrayvn.add(cursor2mst2.getString(1));
                                }

                            }while(cursor2mst2.moveToNext());

                            for(int i = 0;i<arrayvc.size();i++)
                            {
                                // Log.e("VALCHK",""+position);


                                // ccts.setText("Tap For Select Crop Name");


                                Cursor cursor2mst32 = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_parent_code"
                                        }, "out_depend_code" + "=?",
                                        new String[]{arrayvc.get(i)}, null, null, null, null);






                                if (cursor2mst32.moveToFirst()) {

                                    do {
                                        if(cursor2mst32.getString(2).equalsIgnoreCase("QCD_SOW_SEEDNAME")) {


                                            arraysec.add(cursor2mst32.getString(0));
                                            arraysen.add(cursor2mst32.getString(1));
                                        }

                                    }while(cursor2mst32.moveToNext());



                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                            (MainActivity3.this, R.layout.spinnertext3, arraysen);
                                    //Getting the instance of AutoCompleteTextView

                                    stsnm.setThreshold(0);//will start working from first character
                                    stsnm.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                                    stsnm.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            stsnm.showDropDown();
                                            stsnm.requestFocus();
                                        }
                                    });

                                }

                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (MainActivity3.this, R.layout.spinnertext3, arrayvn);
                            //Getting the instance of AutoCompleteTextView

                            cvars.setThreshold(0);//will start working from first character
                            cvars.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                            cvars.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    cvars.showDropDown();
                                    cvars.requestFocus();
                                }
                            });

                        }


                        Cursor cursor2mst4 = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_depend_code"
                                }, "out_parent_code" + "=?",
                                new String[]{"QCD_SOW_SESSION"}, null, null, null, null);






                        if (cursor2mst4.moveToFirst()) {
                            arraysc.clear();
                            arraysn.clear();

                            do {

                                if(cursor2mst4.getString(2).equalsIgnoreCase(sharedpreferences.getString("lo",""))) {


                                    arraysc.add(cursor2mst4.getString(0));
                                    arraysn.add(cursor2mst4.getString(1));
                                }
                            }while(cursor2mst4.moveToNext());


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (MainActivity3.this, R.layout.spinnertext3, arraysn);
                            //Getting the instance of AutoCompleteTextView

                            ccty2s.setThreshold(0);//will start working from first character
                            ccty2s.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                            ccty2s.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ccty2s.showDropDown();
                                    ccty2s.requestFocus();
                                }
                            });
                        }
                        dialog.dismiss();



                    }
                });



            }

            if(p == 6)
            {

                holder.lbank.setVisibility(View.GONE);
                holder.lsow.setVisibility(View.GONE);
                holder.lkyc.setVisibility(View.GONE);
                holder.lland.setVisibility(View.VISIBLE);

                holder.la1.setText(pojokyc.getLat1());
                holder.la2.setText(pojokyc.getLat2());
                holder.la3.setText(pojokyc.getLat3());
                holder.la4.setText(pojokyc.getLat4());
                holder.la5.setText(pojokyc.getLat5());
                holder.la6.setText(pojokyc.getLat6());
                holder.la7.setText(pojokyc.getLat7());
                holder.la8.setText(pojokyc.getLat8());
                holder.la9.setText(pojokyc.getLat9());

                holder.ed4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        laid = pojokyc.getLaid();
                        laslno = pojokyc.getLaslno();
                        tlt.setText(pojokyc.getLat1());
                        tow.setText(pojokyc.getLat2());
                        elnoa.setText(pojokyc.getLat3());
                        soiltype.setText(pojokyc.getLat4());
                        tis.setText(pojokyc.getLat5());
                        elat.setText(pojokyc.getLat6());
                        elon.setText(pojokyc.getLat7());
                        landvillage.setText(pojokyc.getLat8());
                        ui3 = pojokyc.getLat10();
                        radioGroup.clearCheck();
                        if(pojokyc.getLat9().equalsIgnoreCase("YES"))
                        {
                            radioButton1.setChecked(true);
                            radioButton2.setChecked(false);

                            usp = "YES";
                        }
                        else
                        {
                            radioButton2.setChecked(true);
                            radioButton1.setChecked(false);
                            usp = "NO";
                        }
                        ul = 1;

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
            TextView mytextview2,type,stype,dn,vtd,tat,tan,tb,ti,tbr,tdd,tdc,tas,adt,adp,ada,adv,adg,adta,addi;
            TextView tc1,tc2,tc3,tc4,tc5,tc6,tc7,tc8;

            TextView ft1,ft2,ft3,ft4,ft5,ft6,ft7;
            TextView pdt1,pdt2,pdt3,pdt4,pdt5,pdt6,pdt7,pdt8;
            TextView lsd1,lsd2,lsd3,lsd4,lsd5;
            TextView adt2,adt3,adt4,adt1,adt5,adt6;
            TextView ld1,ld2,ld3,ld4,ld5,ld6,ld7,ld8,ld9,ld10,ld11;

            TextView la1,la2,la3,la4,la5,la6,la7,la8,la9;
            TextView lr1,lr2,lr3,lr4;

            TextView in1,in2,in3,in4,in5,in6,in7,in8,in9,in10;

            TextView sh1,sh2,sh3,sh4;

            TextView bu1,bu2,bu3,bu4;
            TextView tn1,tn2,tn3,tn4,tn5,tn6,tn7;
            TextView sk1,sk2,sk3,sk4,sk5,sk6;
            TextView sl1,sl2,sl3,sl4,sl5,sl6,sl7,sl8,sl9,sl10;
            TextView pd1,pd2,pd3,pd4,pd5;
            TextView ip1,ip2,ip3,ip4,ip5,ip6,ip7,ip8,ip9,ip10,ip11,ip12;

            TextView ts1,ts2,ts3,ts4,ts5,ts6,ts7,ts8,ts9,ts10,ts11,ts12,ts13;
            TextView lq1,lq2,lq3,lq4;
            LinearLayout lbank,lkyc,lsow,lland;
            RelativeLayout r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,rsd,rlr,rsh,rbu,rtn,rstk,rslt,rpd,rip,rlq;
            ImageView ed21,del21,ed20,del20,ed19,del19,ed18,del18,ed17,del17,ed16,del16,del15,ed15,del14,ed14,ed13,del13,ed,del,ed2,del2,ed3,del3,del4,ed4,del5,ed5,ed6,del6,del7,ed7,del8,ed8,del9,ed9,del10,ed10,ed12,del12,ed11,del11;

            ViewHolder(View itemView) {
                super(itemView);
                mytextview2 = itemView.findViewById(R.id.txt1);

                tan = itemView.findViewById(R.id.tan);
                tb = itemView.findViewById(R.id.tb);
                ti = itemView.findViewById(R.id.ti);
                tbr = itemView.findViewById(R.id.tbr);
                type = itemView.findViewById(R.id.type);
                stype = itemView.findViewById(R.id.stype);
                dn = itemView.findViewById(R.id.dn);
                vtd = itemView.findViewById(R.id.vtill);
                ed = itemView.findViewById(R.id.ed);
                ed2 = itemView.findViewById(R.id.ed2);
                ed3 = itemView.findViewById(R.id.ed3);

                ts1=itemView.findViewById(R.id.sdt1);
                ts2=itemView.findViewById(R.id.sdt2);
                ts3=itemView.findViewById(R.id.sdt3);
                ts4=itemView.findViewById(R.id.sdt4);
                ts5=itemView.findViewById(R.id.sdt5);
                ts6=itemView.findViewById(R.id.sdt6);
                ts7=itemView.findViewById(R.id.sdt7);
                ts8=itemView.findViewById(R.id.sdt8);
                ts9=itemView.findViewById(R.id.sdt9);
                ts10=itemView.findViewById(R.id.sdt10);
                ts11=itemView.findViewById(R.id.sdt11);
                ts12=itemView.findViewById(R.id.sdt12);

                ed4 = itemView.findViewById(R.id.ed4);

                la1 = itemView.findViewById(R.id.lat1);
                la2 = itemView.findViewById(R.id.lat2);
                la3 = itemView.findViewById(R.id.lat3);
                la4 = itemView.findViewById(R.id.lat4);
                la5 = itemView.findViewById(R.id.lat5);
                la6 = itemView.findViewById(R.id.lat6);
                la7 = itemView.findViewById(R.id.lat7);
                la8 = itemView.findViewById(R.id.lat8);
                la9 = itemView.findViewById(R.id.lat9);
                lbank=itemView.findViewById(R.id.lbank);
                lkyc=itemView.findViewById(R.id.lkyc);
                lsow=itemView.findViewById(R.id.lsow);
                lland=itemView.findViewById(R.id.lland);


            }


        }

        // convenience method for getting data at click position


        // allows clicks events to be caught


        // parent activity will implement this method to respond to click events

    }

    public void addkyc()
    {
     if(uk == 0)
     {
         if (kttype.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {

             
                 final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                         .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                         .setTitle("Error!")
//set message
                         .setMessage("Empty Type!")
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
    

         } else if (kstype.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
            
                 final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                         .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                         .setTitle("Error!")
//set message
                         .setMessage("Empty Sub Type!")
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
          
         } else if (documentno.getText().toString().equalsIgnoreCase("")) {
           
                 final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                         .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                         .setTitle("Error!")
//set message
                         .setMessage("Empty Document No!")
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
   
         }else if(ksty.equalsIgnoreCase("QCD_PRFI_FAMILYCARD") && (documentno.getText().toString().trim().length() < 6 || documentno.getText().toString().trim().length() > 20)){
             if(documentno.getText().toString().trim().length() < 6){
                 showdialog("Error","Family Card Document No Cannot Be Less Than 6 Letters");
             }else if(documentno.getText().toString().trim().length() > 20){
                 showdialog("Error","Family Card Document No Cannot Be Greater Than 20 Letters");
             }
         }
//         else if (cdocumentno.getText().toString().equalsIgnoreCase("")) {
//
//             final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
////set icon
//                     .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                     .setTitle("Error!")
////set message
//                     .setMessage("Empty Document No!")
////set positive button
//                     .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                 @Override
//                                 public void onClick(DialogInterface dialogInterface, int i) {
//                                     //set what would happen when positive button is clicked
//
//                                 }
//                             }
////set negative button
//
//                     )
//                     .show();
//
//         }
         
         else
         {
           //  if(documentno.getText().toString().equalsIgnoreCase(cdocumentno.getText().toString()))
            // {
                 if(isNetworkAvailable()) {
                     db.insertkyc(kttype.getText().toString(), kstype.getText().toString(), documentno.getText().toString(), vtilldate.getText().toString(), sharedpreferences.getString("fcode", ""), "0", "0", kty, ksty, 0, ui2);


                     capturek.setImageResource(0);
                     capturek.setBackgroundResource(R.drawable.capture);
                     documentno.setText("");
                     cdocumentno.setText("");
                     kttype.setText("");
                     kstype.setText("");
                     kty = "";
                     ksty = "";
                     vtilldate.setText("");
                   //  uk=1;
                     String selectQuery2 = "SELECT  * FROM kyc where f1 = 0";

                     Cursor cursor2 = dbs.rawQuery(selectQuery2, null);
                     if (cursor2.moveToFirst()) {
                      


                         savekyc(cursor2.getString(0), cursor2.getString(8), cursor2.getString(9), cursor2.getString(3), cursor2.getString(4), cursor2.getString(5), Integer.parseInt(cursor2.getString(10)),cursor2.getString(11));
                      


                         // Log.e("Check",""+cursor.getString(1));


                     }
                 }
                 else
                 {
                     db.insertkyc(kttype.getText().toString(), kstype.getText().toString(), documentno.getText().toString(), vtilldate.getText().toString(), sharedpreferences.getString("fcode", ""), "0", "0", kty, ksty, 0, ui2);
                    // uk=1;
                     documentno.setText("");
                     cdocumentno.setText("");
                     kttype.setText("");
                     kstype.setText("");
                     kty = "";
                     ksty  = "";
                     vtilldate.setText("");
                     capturek.setImageResource(0);
                     capturek.setBackgroundResource(R.drawable.capture);
                     final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                             .setIcon(android.R.drawable.ic_menu_save)
//set title
                             .setTitle("Success!")
//set message
                             .setMessage("KYC Details saved locally succesfully")
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
           //  }
//             else
//             {
//                 final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
////set icon
//                         .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                         .setTitle("Error!")
////set message
//                         .setMessage("Document No does not match with Confirm Document No!")
////set positive button
//                         .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                     @Override
//                                     public void onClick(DialogInterface dialogInterface, int i) {
//                                         //set what would happen when positive button is clicked
//
//                                     }
//                                 }
////set negative button
//
//                         )
//                         .show();
//             }
             
         }
     }
     else{

         if (kttype.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {


             final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                     .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                     .setTitle("Error!")
//set message
                     .setMessage("Empty Type!")
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


         } else if (kstype.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {

             final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                     .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                     .setTitle("Error!")
//set message
                     .setMessage("Empty Sub Type!")
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

         } else if (documentno.getText().toString().equalsIgnoreCase("")) {

             final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                     .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                     .setTitle("Error!")
//set message
                     .setMessage("Empty Document No!")
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

         }else if(ksty.equalsIgnoreCase("QCD_PRFI_FAMILYCARD") && (documentno.getText().toString().trim().length() < 6 || documentno.getText().toString().trim().length() > 20)){
             if(documentno.getText().toString().trim().length() < 6){
                 showdialog("Error","Family Card Document No Cannot Be Less Than 6 Letters");
             }else if(documentno.getText().toString().trim().length() > 20){
                 showdialog("Error","Family Card Document No Cannot Be Greater Than 20 Letters");
             }
         }
//         else if (cdocumentno.getText().toString().equalsIgnoreCase("")) {
//
//             final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
////set icon
//                     .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                     .setTitle("Error!")
////set message
//                     .setMessage("Empty Document No!")
////set positive button
//                     .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                 @Override
//                                 public void onClick(DialogInterface dialogInterface, int i) {
//                                     //set what would happen when positive button is clicked
//
//                                 }
//                             }
////set negative button
//
//                     )
//                     .show();
//
//         }

         else
         {
           //  if(documentno.getText().toString().equalsIgnoreCase(cdocumentno.getText().toString()))
            // {
                 if(isNetworkAvailable()) {
                     db.updatekyc(Integer.valueOf(kycid), kttype.getText().toString(), kstype.getText().toString(), documentno.getText().toString(), vtilldate.getText().toString(), sharedpreferences.getString("fcode", ""), "0", "0", kty, ksty, Integer.parseInt(kycrid), ui2);

                     documentno.setText("");
                     cdocumentno.setText("");
                     kttype.setText("");
                     kstype.setText("");
                     kty = "";
                     ksty = "";
                     vtilldate.setText("");
                     String selectQuery2 = "SELECT  * FROM kyc where f1 = 0";

                     Cursor cursor2 = dbs.rawQuery(selectQuery2, null);
                     if (cursor2.moveToFirst()) {



                         savekyc(cursor2.getString(0), cursor2.getString(8), cursor2.getString(9), cursor2.getString(3), cursor2.getString(4), cursor2.getString(5), Integer.parseInt(cursor2.getString(10)),cursor2.getString(11));



                         // Log.e("Check",""+cursor.getString(1));


                     }
                 }
                 else
                 {

                     db.updatekyc(Integer.valueOf(kycid), kttype.getText().toString(), kstype.getText().toString(), documentno.getText().toString(), vtilldate.getText().toString(), sharedpreferences.getString("fcode", ""), "0", "0", kty, ksty, Integer.parseInt(kycrid), ui2);
                     documentno.setText("");
                     cdocumentno.setText("");
                     kttype.setText("");
                     kstype.setText("");
                     kty = "";
                     ksty = "";
                     vtilldate.setText("");
                     capturek.setImageResource(0);
                     capturek.setBackgroundResource(R.drawable.capture);
                     final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                             .setIcon(android.R.drawable.ic_menu_save)
//set title
                             .setTitle("Success!")
//set message
                             .setMessage("KYC Details Updated locally succesfully")
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
//                 }
//             else
//             {
//                 final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
////set icon
//                         .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                         .setTitle("Error!")
////set message
//                         .setMessage("Document No does not match with Confirm Document No!")
////set positive button
//                         .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                     @Override
//                                     public void onClick(DialogInterface dialogInterface, int i) {
//                                         //set what would happen when positive button is clicked
//
//                                     }
//                                 }
////set negative button
//
//                         )
//                         .show();
//             }

         }
         
     }
    }

    public void savekyc(final String idk, String type, String stype, String dno, String vtd, String fc,int kid,String ui2)
    {

        if(kid == 0)
        {
            modeflag = "I";
        }
        else
        {
            modeflag = "U";
        }

        if(ui2.equalsIgnoreCase("0"))
        {
            encodedImage2 = "0";
        }
        else
        {
            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(ui2));

                byteArrayOutputStream2 = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream2);
                //Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());


                encodedImage2 = Base64.encodeToString(byteArrayOutputStream2.toByteArray(), Base64.DEFAULT);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();
        String  finaldate = "";
        try {

            String[] dat = vtd.split("/");
            if(dat[0].length()>2)
            {
                finaldate = vtd;
            }
            else
            {
                finaldate = dat[2]+"/"+dat[1]+"/"+dat[0];
            }
        }
        catch (Exception e)
        {

        }
        try {


            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", "flexi");
            user.put("locnId", "chennai");
            user.put("userId", "fdrmob");
            user.put("localeId", "en_US");
            user.put("instance", Pojokyc.instance);
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("in_farmer_code",fc);
            user2.put("in_farmerkyc_rowid",kid);
            user2.put("in_proof_type",type);
            user2.put("in_proof_doc",stype);

            user2.put("in_proof_doc_no",dno);
            user2.put("in_proof_doc_adharno",dno);
            user2.put("in_proof_valid_till",finaldate);
            user2.put("in_status_code","A");
            user2.put("in_mode_flag",modeflag);
            user2.put("in_created_by", sharedpreferences.getString("un","").toUpperCase()+""+sharedpreferences.getString("phn",""));
            user2.put("in_modified_by", sharedpreferences.getString("un","").toUpperCase()+""+sharedpreferences.getString("phn",""));
            user2.put("in_photo_kyc",encodedImage2);

            user.put("KYC",user2);
            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
        }




        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.url+"/api/Mobile_FDR_FHeader/NewMobileFarmerkyc",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {
                            pdialog.dismiss();
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("kyc");
                            final String frid = obj2.getString("in_farmerkyc_rowid");

                            if(frid.equalsIgnoreCase("0")) {

                                pdialog.dismiss();
                                if(uk == 0)
                                {
                                    dbs.execSQL("DELETE FROM kyc WHERE id = (SELECT MAX(id) FROM kyc);");
                                }
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Error Inserting KYC")
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
                                FirebaseApp.initializeApp(MainActivity3.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis()/1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                Log.e("Valuecheck",""+mRef.getRef());
                                mRef.child("name").setValue("SAVEKYC");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("MAINACTIVITY3");


                            }
                            else
                            {



                              
                                final SQLiteDatabase dbs = db.getWritableDatabase();

                                dbs.execSQL("UPDATE kyc SET f1 = 1 WHERE id = " + idk);
                                dbs.execSQL("UPDATE kyc SET rid = "+frid+" WHERE id = " + idk);
                                //  dbs.execSQL("DELETE  FROM kyc WHERE id = " + idk);

                                kycrid = frid;
                                uk=0;
                                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                                            .setTitle("Success!")
//set message
                                            .setMessage("KYC Succesfully Added !")
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
                            if(uk == 0)
                            {
                                dbs.execSQL("DELETE FROM kyc WHERE id = (SELECT MAX(id) FROM kyc);");
                            }
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Error Inserting KYC")
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
                            FirebaseApp.initializeApp(MainActivity3.this);
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Long tsLong = System.currentTimeMillis()/1000;
                            String ts = tsLong.toString();
                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                            Log.e("Valuecheck",""+mRef.getRef());
                            mRef.child("name").setValue("SAVEKYC");
                            mRef.child("date").setValue(date);
                            mRef.child("Error").setValue(response.toString());
                            mRef.child("Activity").setValue("MAINACTIVITY3");
                        }

                    }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);
                        pdialog.dismiss();
                        if(uk == 0)
                        {
                            dbs.execSQL("DELETE FROM kyc WHERE id = (SELECT MAX(id) FROM kyc);");
                        }
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Error Inserting KYC")
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
    
    public void addsow()
    {
        
        if(us == 0)
        {
            if(cyears.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Select Year!")
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
            else if(ccty2s.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Select Crop Season!")
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
            else if(ccts.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Select Crop Name!")
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
            else if(stmnt.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Select Date!")
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
            else if(stclf.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Select Classification!")
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

            else if(stsnm.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Select Seed Name!")
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
            else if(sdt1.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Empty Seeds Qty!")
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
            else if(sdt2.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Empty sowing area!")
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
            else if(sdt3.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Empty Yield expected by farmer (in Quintals)!")
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
            else if(sdt4.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Empty Expected Price!")
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
            else if(sdt5.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Empty Expected quantity to be sold (in Quintals)!")
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
                    Cursor cursoraddr = dbs.query("sowing", new String[]{"id"
                            }, "v16" + "=?",
                            new String[]{sharedpreferences.getString("fcode","")}, null, null, null, null);
                    int x = cursoraddr.getCount();
                    x++;
                    String slno = String.valueOf(x);
                    db.insertsowing(cyears.getText().toString(),codes,codecn,cvars.getText().toString(),sdt1.getText().toString(),sdt2.getText().toString(),sdt3.getText().toString(),sdt4.getText().toString(),sdt5.getText().toString(),codem,codecr,"",codesn,slno,sharedpreferences.getString("fid",""),sharedpreferences.getString("fcode",""),"0",ccty2s.getText().toString(),stmnt.getText().toString(),stclf.getText().toString(),ccts.getText().toString(),stsnm.getText().toString());
                    cyears.setText("");
                    ccty2s.setText("");
                    ccts.setText("");
                    cvars.setText("");
                    stmnt.setText("");
                    stclf.setText("");
                    stsnm.setText("");
                    sdt1.setText("");
                    sdt2.setText("");
                    sdt3.setText("");
                    sdt4.setText("");
                    sdt5.setText("");
                    String selectQuery2 = "SELECT  * FROM sowing where v17 = 0";

                    Cursor cursor2 = dbs.rawQuery(selectQuery2, null);
                    if (cursor2.moveToFirst()) {


                        savesowing(cursor2.getString(0),cursor2.getString(1),cursor2.getString(2),cursor2.getString(3),cursor2.getString(4) ,cursor2.getString(5), cursor2.getString(6), cursor2.getString(7), cursor2.getString(8), cursor2.getString(9), cursor2.getString(19),cursor2.getString(11),cursor2.getString(12),cursor2.getString(13),cursor2.getString(14),cursor2.getString(15),cursor2.getString(16),cursor2.getString(17));


                        // Log.e("Check",""+cursor.getString(1));


                    }
                }
                else
                {
                    Cursor cursoraddr = dbs.query("sowing", new String[]{"id"
                            }, "v16" + "=?",
                            new String[]{sharedpreferences.getString("fcode","")}, null, null, null, null);
                    int x = cursoraddr.getCount();
                    x++;
                    String slno = String.valueOf(x);
                    db.insertsowing(cyears.getText().toString(),codes,codecn,cvars.getText().toString(),sdt1.getText().toString(),sdt2.getText().toString(),sdt3.getText().toString(),sdt4.getText().toString(),sdt5.getText().toString(),codem,codecr,"",codesn,slno,sharedpreferences.getString("fid",""),sharedpreferences.getString("fcode",""),"0",ccty2s.getText().toString(),stmnt.getText().toString(),stclf.getText().toString(),ccts.getText().toString(),stsnm.getText().toString());
                    ccty2s.setText("");
                    ccts.setText("");
                    cvars.setText("");
                    stmnt.setText("");
                    stclf.setText("");
                    stsnm.setText("");
                    sdt1.setText("");
                    sdt2.setText("");
                    sdt3.setText("");
                    sdt4.setText("");
                    sdt5.setText("");
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                            .setTitle("Success!")
//set message
                            .setMessage("Sowing and Cropping Details Saved Locally!")
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
        else
        {
            if(cyears.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Select Year!")
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
            else if(ccty2s.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Select Crop Season!")
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
            else if(ccts.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Select Crop Name!")
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
            else if(stmnt.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Select Date!")
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
            else if(stclf.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Select Crop Classification!")
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

            else if(stsnm.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Select Seed Name!")
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
            else if(sdt1.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Empty Seeds Qty!")
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
            else if(sdt2.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Empty sowing area!")
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
            else if(sdt3.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Empty Yield expected by farmer (in Quintals)!")
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
            else if(sdt4.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Empty Expected Price!")
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

            else if(sdt5.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Empty Expected quantity to be sold (in Quintals)!")
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
                    db.updatesowing(sowid,cyears.getText().toString(),codes,codecn,cvars.getText().toString(),sdt1.getText().toString(),sdt2.getText().toString(),sdt3.getText().toString(),sdt4.getText().toString(),sdt5.getText().toString(),codem,codecr,"",codesn,sowsn,sharedpreferences.getString("fid",""),sharedpreferences.getString("fcode",""),"0",ccty2s.getText().toString(),stmnt.getText().toString(),stclf.getText().toString(),ccts.getText().toString(),stsnm.getText().toString());
                    cyears.setText("");
                    ccty2s.setText("");
                    ccts.setText("");
                    cvars.setText("");
                    stmnt.setText("");
                    stclf.setText("");
                    stsnm.setText("");
                    sdt1.setText("");
                    sdt2.setText("");
                    sdt3.setText("");
                    sdt4.setText("");
                    sdt5.setText("");
                    us = 0;
                    String selectQuery2 = "SELECT  * FROM sowing where v17 = 0";

                    Cursor cursor2 = dbs.rawQuery(selectQuery2, null);
                    if (cursor2.moveToFirst()) {


                        savesowing(cursor2.getString(0),cursor2.getString(1),cursor2.getString(2),cursor2.getString(3),cursor2.getString(4) ,cursor2.getString(5), cursor2.getString(6), cursor2.getString(7), cursor2.getString(8), cursor2.getString(9), cursor2.getString(19),cursor2.getString(11),cursor2.getString(12),cursor2.getString(13),cursor2.getString(14),cursor2.getString(15),cursor2.getString(16),cursor2.getString(17));


                        // Log.e("Check",""+cursor.getString(1));


                    }
                }
                else
                {
                    db.updatesowing(sowid,cyears.getText().toString(),codes,codecn,cvars.getText().toString(),sdt1.getText().toString(),sdt2.getText().toString(),sdt3.getText().toString(),sdt4.getText().toString(),sdt5.getText().toString(),codem,codecr,"",codesn,sowsn,sharedpreferences.getString("fid",""),sharedpreferences.getString("fcode",""),"0",ccty2s.getText().toString(),stmnt.getText().toString(),stclf.getText().toString(),ccts.getText().toString(),stsnm.getText().toString());
                    cyears.setText("");
                    ccty2s.setText("");
                    ccts.setText("");
                    cvars.setText("");
                    stmnt.setText("");
                    stclf.setText("");
                    stsnm.setText("");
                    sdt1.setText("");
                    sdt2.setText("");
                    sdt3.setText("");
                    sdt4.setText("");
                    sdt5.setText("");
                    us = 0;
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                            .setTitle("Success!")
//set message
                            .setMessage("Sowing and Cropping Details Updated Locally!")
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
    @RequiresApi(api = Build.VERSION_CODES.N)
    public  void savesowing(final String id, String v1, String v2, String v3, String v4, String v5, String v6, String v7, final String v8, String v9, String v10, String v11, String v12, String v13, String v14, String v15, String v16, String v17)
    {


        Log.e("NULL",""+v1);
//
//        Log.e("NULL",""+v2);
//        Log.e("NULL",""+v3);
//        Log.e("NULL",""+v4);
//        Log.e("NULL",""+v5);
//        Log.e("NULL",""+v6);
//        Log.e("NULL",""+v7);
//        Log.e("NULL",""+v8);
//        Log.e("NULL",""+v9);
         Log.e("NULL",""+v10);
        // Log.e("NULL",""+v11);
        //Log.e("NULL",""+v12);
        //  Log.e("NULL",""+v13);
        //Log.e("NULL",""+v14);
        // Log.e("NULL",""+v15);
        //Log.e("NULL",""+v16);


        String vname = null;

        Cursor cursor = dbs.query("masterl", new String[]{"out_depend_code"
                }, "out_master_code" + " LIKE ?",
                new String[]{"%"+v13+"%"}, null, null, null, null);
        if(cursor.getCount()>0)
        {
            if(cursor.moveToFirst())
            {
                do {
                    vname = cursor.getString(0);
                }while(cursor.moveToNext());
            }
        }
        String  finaldate = "";
        try {

          String[] dat = v10.split("/");
          if(dat[0].length()>2)
          {
              finaldate = v10;
          }
          else
          {
              finaldate = dat[2]+"/"+dat[1]+"/"+dat[0];
          }
        }
        catch (Exception e)
        {

        }

        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();

        try {



            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", String.valueOf(v14));
            user.put("locnId", "chennai");
            user.put("userId", "fdrmob");
            user.put("localeId", "en_US");
            user.put("instance", Pojokyc.instance);

            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("inout_farmer_rowid",Integer.parseInt(sharedpreferences.getString("fid","")));
            user2.put("inout_farmer_code",sharedpreferences.getString("fcode",""));
            user2.put("inout_version_no",1);
            user2.put("entitygrp_code","EC_CROP_SOWING");
            user2.put("in_created_by", sharedpreferences.getString("un","").toUpperCase()+""+sharedpreferences.getString("phn",""));
            // user2.put("in_modified_by", sharedpreferences.getString("un","").toUpperCase()+""+sharedpreferences.getString("phn",""));
            user.put("Header",user2);

            JSONObject ob1 = new JSONObject();
            try {
                ob1.put("in_farmerdetail_rowid", 0);
                ob1.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob1.put("in_entity_code", "EC_CROP_SOWING_QTY");
                ob1.put("in_row_slno", String.valueOf(v14));
                ob1.put("in_entity_value", v5);
                ob1.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            JSONObject ob2 = new JSONObject();
            try {
                ob2.put("in_farmerdetail_rowid", 0);
                ob2.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob2.put("in_entity_code", "EC_CROP_SOWING_Vareity");
                ob2.put("in_row_slno", String.valueOf(v14));
                ob2.put("in_entity_value", vname);
                ob2.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob3 = new JSONObject();
            try {
                ob3.put("in_farmerdetail_rowid", 0);
                ob3.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob3.put("in_entity_code", "EC_CROP_SOWING_Croptype");
                ob3.put("in_row_slno", String.valueOf(v14));
                ob3.put("in_entity_value", v3);
                ob3.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob4 = new JSONObject();
            try {
                ob4.put("in_farmerdetail_rowid", 0);
                ob4.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob4.put("in_entity_code", "EC_CROP_SOWING_Season");
                ob4.put("in_row_slno", String.valueOf(v14));
                ob4.put("in_entity_value", v2);
                ob4.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob5 = new JSONObject();
            try {
                ob5.put("in_farmerdetail_rowid", 0);
                ob5.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob5.put("in_entity_code", "EC_CROP_SOWING_Year");
                ob5.put("in_row_slno", String.valueOf(v14));
                ob5.put("in_entity_value", v1);
                ob5.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob6 = new JSONObject();
            try {
                ob6.put("in_farmerdetail_rowid", 0);
                ob6.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob6.put("in_entity_code", "EC_CROP_SOWING_AREA");
                ob6.put("in_row_slno", String.valueOf(v14));
                ob6.put("in_entity_value", v6);
                ob6.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob7 = new JSONObject();
            try {
                ob7.put("in_farmerdetail_rowid", 0);
                ob7.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob7.put("in_entity_code", "EC_CROP_SOWING_EXPECYIELD");
                ob7.put("in_row_slno", String.valueOf(v14));
                ob7.put("in_entity_value", v7);
                ob7.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob8 = new JSONObject();
            try {
                ob8.put("in_farmerdetail_rowid", 0);
                ob8.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob8.put("in_entity_code", "EC_CROP_SOWING_SURPLUS");
                ob8.put("in_row_slno", String.valueOf(v14));
                ob8.put("in_entity_value", "");
                ob8.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob9 = new JSONObject();
            try {
                ob9.put("in_farmerdetail_rowid", 0);
                ob9.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob9.put("in_entity_code", "EC_CROP_SOWING_EXPECPRICE");
                ob9.put("in_row_slno", String.valueOf(v14));
                ob9.put("in_entity_value", v8);
                ob9.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob10 = new JSONObject();
            try {
                ob10.put("in_farmerdetail_rowid", 0);
                ob10.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob10.put("in_entity_code", "EC_CROP_SOWING_SOWDATE");
                ob10.put("in_row_slno", String.valueOf(v14));
                ob10.put("in_entity_value", String.valueOf(finaldate));
                ob10.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob11 = new JSONObject();
            try {
                ob11.put("in_farmerdetail_rowid", 0);
                ob11.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob11.put("in_entity_code", "EC_CROP_SOWING_TRANSDATE");
                ob11.put("in_row_slno", String.valueOf(v14));
                ob11.put("in_entity_value", "");
                ob11.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob12 = new JSONObject();
            try {
                ob12.put("in_farmerdetail_rowid", 0);
                ob12.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob12.put("in_entity_code", "EC_CROP_SOWING_DWEEDDATE");
                ob12.put("in_row_slno", String.valueOf(v14));
                ob12.put("in_entity_value", v12);
                ob12.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob13 = new JSONObject();
            try {
                ob13.put("in_farmerdetail_rowid", 0);
                ob13.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob13.put("in_entity_code", "EC_CROP_SOWING_HARDATE");
                ob13.put("in_row_slno", String.valueOf(v14));
                ob13.put("in_entity_value", v13);
                ob13.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            JSONObject ob14 = new JSONObject();
            try {
                ob14.put("in_farmerdetail_rowid", 0);
                ob14.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob14.put("in_entity_code", "EC_CROP_SOWING_CROPCLASS");
                ob14.put("in_row_slno", String.valueOf(v14));
                ob14.put("in_entity_value", v11);
                ob14.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            JSONObject ob15 = new JSONObject();
            try {
                ob15.put("in_farmerdetail_rowid", 0);
                ob15.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob15.put("in_entity_code", "EC_CROP_SOWING_Month");
                ob15.put("in_row_slno", String.valueOf(v14));
                ob15.put("in_entity_value", "");
                ob15.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

           

            JSONObject ob16 = new JSONObject();
            try {
                ob16.put("in_farmerdetail_rowid", 0);
                ob16.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob16.put("in_entity_code", "EC_CROP_SOWING_SEED_NAME");
                ob16.put("in_row_slno", String.valueOf(v14));
                ob16.put("in_entity_value", v13);
                ob16.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob17 = new JSONObject();
            try {
                ob17.put("in_farmerdetail_rowid", 0);
                ob17.put("in_entitygrp_code", "EC_CROP_SOWING");
                ob17.put("in_entity_code", "EC_CROP_SOWING_EXPECYIELDTOBESOLD");
                ob17.put("in_row_slno", String.valueOf(v14));
                ob17.put("in_entity_value", v9);
                ob17.put("in_mode_flag", "I");

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
            jsonArray.put(ob11);
            jsonArray.put(ob12);
            jsonArray.put(ob13);
            jsonArray.put(ob14);
            jsonArray.put(ob15);
            jsonArray.put(ob16);
            jsonArray.put(ob17);



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


                       
                                final SQLiteDatabase dbs = db.getWritableDatabase();

                                //dbs.execSQL("UPDATE address SET flag = 1 WHERE id = " + idk);
                                //  dbs.execSQL("DELETE FROM sowing WHERE id = " + id);
                                dbs.execSQL("UPDATE sowing SET v17 = 1 WHERE id = " + id);


                         
                                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                                            .setTitle("Success!")
//set message
                                            .setMessage("Sowing Details Succesfully Added !")
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
                                pdialog.dismiss();
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Error Inserting Sowing Details")
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
                                FirebaseApp.initializeApp(MainActivity3.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis()/1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                Log.e("Valuecheck",""+mRef.getRef());
                                mRef.child("name").setValue("SAVESOWING");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("MAINACTIVITY3");
                            }
                        }
                        catch (Exception e)
                        {
                            pdialog.dismiss();
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Error Inserting Sowing Details")
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
                            FirebaseApp.initializeApp(MainActivity3.this);
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Long tsLong = System.currentTimeMillis()/1000;
                            String ts = tsLong.toString();
                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                            Log.e("Valuecheck",""+mRef.getRef());
                            mRef.child("name").setValue("SAVESOWING");
                            mRef.child("date").setValue(date);
                            mRef.child("Error").setValue(response.toString());
                            mRef.child("Activity").setValue("MAINACTIVITY3");
                        }

                    }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);
                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Error Inserting Sowing Details")
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

    public void addland()
    {
        
        if(ul == 0)
        {
            if(tlt.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty Land Type!")
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
            else if(tow.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty Ownership!")
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
            else if(soiltype.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty Soil Type!")
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
            else if(tis.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty Irrigation Source!")
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
            else if(landvillage.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty village!")
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
            else if(elnoa.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty Plot size !")
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
            else if (radioGroup.getCheckedRadioButtonId() == -1)
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty Used As Polyhouse!")
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
                if(radioButton1.isChecked())
                {
                    usp = "YES";
                }
                else
                {
                    usp = "NO";
                }

                if(isNetworkAvailable())
                {
                    Cursor cursoraddr = dbs.query("land", new String[]{"id"
                            }, "v10" + "=?",
                            new String[]{sharedpreferences.getString("fcode", "")}, null, null, null, null);
                    int x = cursoraddr.getCount();
                    x++;
                    String slno = String.valueOf(x);
                    db.insertland(tlt.getText().toString(),tow.getText().toString(),elnoa.getText().toString(),soiltype.getText().toString(),tis.getText().toString(),elat.getText().toString(),elon.getText().toString(),slno,sharedpreferences.getString("fid",""),sharedpreferences.getString("fcode",""),"0",landvillage.getText().toString(),usp,ui3);
                    elnoa.setText("");
                    elat.setText("");
                    elon.setText("");
                    captureld.setImageResource(0);
                    captureld.setBackgroundResource(R.drawable.capture);
                    landvillage.setText("");
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    tlt.setText("");
                    tow.setText("");
                    soiltype.setText("");
                    tis.setText("");
                    String selectQuery2 = "SELECT  * FROM land where v11 = 0";

                    Cursor cursor2 = dbs.rawQuery(selectQuery2, null);
                    if (cursor2.moveToFirst()) {


                        saveland(cursor2.getString(1), cursor2.getString(2), cursor2.getString(3), cursor2.getString(4), cursor2.getString(5), cursor2.getString(6), cursor2.getString(7),cursor2.getString(8),cursor2.getString(0),cursor2.getString(12),cursor2.getString(13),cursor2.getString(14));


                        // Log.e("Check",""+cursor.getString(1));sowsn


                    }
                }
                else
                {
                    Cursor cursoraddr = dbs.query("land", new String[]{"id"
                            }, "v10" + "=?",
                            new String[]{sharedpreferences.getString("fcode", "")}, null, null, null, null);
                    int x = cursoraddr.getCount();
                    x++;
                    String slno = String.valueOf(x);
                    db.insertland(tlt.getText().toString(),tow.getText().toString(),elnoa.getText().toString(),soiltype.getText().toString(),tis.getText().toString(),elat.getText().toString(),elon.getText().toString(),slno,sharedpreferences.getString("fid",""),sharedpreferences.getString("fcode",""),"0",landvillage.getText().toString(),usp,ui3);
                    elnoa.setText("");
                    elat.setText("");
                    elon.setText("");
                    captureld.setImageResource(0);
                    captureld.setBackgroundResource(R.drawable.capture);
                    landvillage.setText("");
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    tlt.setText("");
                    tow.setText("");
                    soiltype.setText("");
                    tis.setText("");
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                            .setTitle("Success!")
//set message
                            .setMessage("Land Details Saved Locally!")
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
        else
        {
            if(tlt.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty Land Type!")
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
            else if(tow.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty Ownership!")
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
            else if(soiltype.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty Soil Type!")
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
            else if(tis.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty Irrigation Source!")
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
            else if(landvillage.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty village!")
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
            else if(elnoa.getText().toString().replaceAll(" ","").equalsIgnoreCase(""))
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty No Plot size!")
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
            else if (radioGroup.getCheckedRadioButtonId() == -1)
            {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty Used As Polyhouse!")
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
                if(radioButton1.isChecked())
                {
                    usp = "YES";
                }
                else
                {
                    usp = "NO";
                }

                if(isNetworkAvailable())
                {
                    db.updateland(Integer.valueOf(laid),tlt.getText().toString(),tow.getText().toString(),elnoa.getText().toString(),soiltype.getText().toString(),tis.getText().toString(),elat.getText().toString(),elon.getText().toString(),laslno,sharedpreferences.getString("fid",""),sharedpreferences.getString("fcode",""),"0",landvillage.getText().toString(),usp,ui3);

                    elnoa.setText("");
                    elat.setText("");
                    elon.setText("");
                    captureld.setImageResource(0);
                    captureld.setBackgroundResource(R.drawable.capture);
                    landvillage.setText("");
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    tlt.setText("");
                    tow.setText("");
                    soiltype.setText("");
                    tis.setText("");
                    ul = 0;
                    String selectQuery2 = "SELECT  * FROM land where v11 = 0";
                    Cursor cursor2 = dbs.rawQuery(selectQuery2, null);
                    if (cursor2.moveToFirst()) {


                        saveland(cursor2.getString(1), cursor2.getString(2), cursor2.getString(3), cursor2.getString(4), cursor2.getString(5), cursor2.getString(6), cursor2.getString(7),cursor2.getString(8),cursor2.getString(0),cursor2.getString(12),cursor2.getString(13),cursor2.getString(14));


                        // Log.e("Check",""+cursor.getString(1));


                    }
                }
                else
                {
                    db.updateland(Integer.valueOf(laid),tlt.getText().toString(),tow.getText().toString(),elnoa.getText().toString(),soiltype.getText().toString(),tis.getText().toString(),elat.getText().toString(),elon.getText().toString(),laslno,sharedpreferences.getString("fid",""),sharedpreferences.getString("fcode",""),"0",landvillage.getText().toString(),usp,ui3);
                    elnoa.setText("");
                    elat.setText("");
                    elon.setText("");
                    captureld.setImageResource(0);
                    captureld.setBackgroundResource(R.drawable.capture);
                    landvillage.setText("");
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    tlt.setText("");
                    tow.setText("");
                    soiltype.setText("");
                    tis.setText("");
                    ul = 0;
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                            .setTitle("Success!")
//set message
                            .setMessage("Land Details Saved Locally!")
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

    public  void saveland(String v1,String v2, String v3, String v4, final String v5, String v6, String v7, String v8,final String id,String v12,String v13,String v14)
    {


        Log.e("NULL",""+v1);
//
//        Log.e("NULL",""+v2);
//        Log.e("NULL",""+v3);
//        Log.e("NULL",""+v4);
//        Log.e("NULL",""+v5);
//        Log.e("NULL",""+v6);
//        Log.e("NULL",""+v7);
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
            user.put("orgnId", String.valueOf(v8));
            user.put("locnId", "chennai");
            user.put("userId", "fdrmob");
            user.put("localeId", "en_US");
            user.put("instance", Pojokyc.instance);

            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("inout_farmer_rowid",Integer.parseInt(sharedpreferences.getString("fid","")));
            user2.put("inout_farmer_code",sharedpreferences.getString("fcode",""));
            user2.put("inout_version_no",1);
            user2.put("entitygrp_code","EC_OWNEDLAND");
            user2.put("in_created_by", sharedpreferences.getString("un","").toUpperCase()+""+sharedpreferences.getString("phn",""));
            // user2.put("in_modified_by", sharedpreferences.getString("un","").toUpperCase()+""+sharedpreferences.getString("phn",""));
            user.put("Header",user2);

            JSONObject ob1 = new JSONObject();
            try {
                ob1.put("in_farmerdetail_rowid", 0);
                ob1.put("in_entitygrp_code", "EC_OWNEDLAND");
                ob1.put("in_entity_code", "EC_OWNED_Ownership");
                ob1.put("in_row_slno", String.valueOf(v8));
                ob1.put("in_entity_value", v2);
                ob1.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob2 = new JSONObject();
            try {
                ob2.put("in_farmerdetail_rowid", 0);
                ob2.put("in_entitygrp_code", "EC_OWNEDLAND");
                ob2.put("in_entity_code", "EC_OWNED_LandType");
                ob2.put("in_row_slno", String.valueOf(v8));
                ob2.put("in_entity_value", v1);
                ob2.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob3 = new JSONObject();
            try {
                ob3.put("in_farmerdetail_rowid", 0);
                ob3.put("in_entitygrp_code", "EC_OWNEDLAND");
                ob3.put("in_entity_code", "EC_OWNED_Noof_Acres");
                ob3.put("in_row_slno", String.valueOf(v8));
                ob3.put("in_entity_value", v3);
                ob3.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob4 = new JSONObject();
            try {
                ob4.put("in_farmerdetail_rowid", 0);
                ob4.put("in_entitygrp_code", "EC_OWNEDLAND");
                ob4.put("in_entity_code", "EC_OWNED_SoilType");
                ob4.put("in_row_slno", String.valueOf(v8));
                ob4.put("in_entity_value", v4);
                ob4.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob5 = new JSONObject();
            try {
                ob5.put("in_farmerdetail_rowid", 0);
                ob5.put("in_entitygrp_code", "EC_OWNEDLAND");
                ob5.put("in_entity_code", "EC_OWNED_Irrigation");
                ob5.put("in_row_slno", String.valueOf(v8));
                ob5.put("in_entity_value", v5);
                ob5.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob6 = new JSONObject();
            try {
                ob6.put("in_farmerdetail_rowid", 0);
                ob6.put("in_entitygrp_code", "EC_OWNEDLAND");
                ob6.put("in_entity_code", "EC_OWNED_Latitude");
                ob6.put("in_row_slno", String.valueOf(v8));
                ob6.put("in_entity_value", v6);
                ob6.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob7 = new JSONObject();
            try {
                ob7.put("in_farmerdetail_rowid", 0);
                ob7.put("in_entitygrp_code", "EC_OWNEDLAND");
                ob7.put("in_entity_code", "EC_OWNED_Longitude");
                ob7.put("in_row_slno", String.valueOf(v8));
                ob7.put("in_entity_value", v7);
                ob7.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JSONObject ob8 = new JSONObject();
            try {
                ob8.put("in_farmerdetail_rowid", 0);
                ob8.put("in_entitygrp_code", "EC_OWNEDLAND");
                ob8.put("in_entity_code", "EC_OWNED_Village");
                ob8.put("in_row_slno", String.valueOf(v8));
                ob8.put("in_entity_value", v12);
                ob8.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  JSONObject ob9 = new JSONObject();
            try {
                ob9.put("in_farmerdetail_rowid", 0);
                ob9.put("in_entitygrp_code", "EC_OWNEDLAND");
                ob9.put("in_entity_code", "EC_OWNED_polyhouse");
                ob9.put("in_row_slno", String.valueOf(v8));
                ob9.put("in_entity_value", v13);
                ob9.put("in_mode_flag", "I");

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  JSONObject ob10 = new JSONObject();
            try {
                ob10.put("in_farmerdetail_rowid", 0);
                ob10.put("in_entitygrp_code", "EC_OWNEDLAND");
                ob10.put("in_entity_code", "EC_OWNED_picture");
                ob10.put("in_row_slno", String.valueOf(v8));
                ob10.put("in_entity_value", encodedImage3);
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
            JSONArray jsonArray2 = new JSONArray();
            jsonArray2.put(ob10);





            user.put("Detail",jsonArray);
            user.put("Dtlownland_picture",jsonArray2);
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



                                final SQLiteDatabase dbs = db.getWritableDatabase();

                                //dbs.execSQL("UPDATE address SET flag = 1 WHERE id = " + idk);
                                //  dbs.execSQL("DELETE FROM sowing WHERE id = " + id);
                                dbs.execSQL("UPDATE land SET v11 = 1 WHERE id = " + id);




                                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                                            .setTitle("Success!")
//set message
                                            .setMessage("Land Details Succesfully Added !")
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
                                pdialog.dismiss();
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Error Inserting Land Details")
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
                                FirebaseApp.initializeApp(MainActivity3.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis()/1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                                Log.e("Valuecheck",""+mRef.getRef());
                                mRef.child("name").setValue("SAVELAND");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("MAINACTIVITY3");
                            }
                        }
                        catch (Exception e)
                        {
                            pdialog.dismiss();
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Error Inserting Land Details")
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
                            FirebaseApp.initializeApp(MainActivity3.this);
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Long tsLong = System.currentTimeMillis()/1000;
                            String ts = tsLong.toString();
                            DatabaseReference mRef =  database.getReference().child(sharedpreferences.getString("un","")).child(ts);
                            Log.e("Valuecheck",""+mRef.getRef());
                            mRef.child("name").setValue("SAVELAND");
                            mRef.child("date").setValue(date);
                            mRef.child("Error").setValue(response.toString());
                            mRef.child("Activity").setValue("MAINACTIVITY3");
                        }

                    }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);
                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Error Inserting Family Details")
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
                4500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
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
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
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

                        edob.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity3.this,android.R.style.Theme_Holo_Dialog , date, myCalendar
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

                        edob.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity3.this,android.R.style.Theme_Holo_Dialog , date, myCalendar
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


                    edob.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity3.this,android.R.style.Theme_Holo_Dialog , date, myCalendar
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



            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if(eventType == XmlPullParser.START_DOCUMENT) {
                    Log.d("Rajdeol","Start document");

                } else if((eventType == XmlPullParser.START_TAG && DataAttributes.AADHAAR_DATA_TAG.equals(parser.getName()))) {



                } else if(eventType == XmlPullParser.END_TAG) {
                    Log.d("Rajdeol","End tag "+parser.getName());
                } else if(eventType == XmlPullParser.TEXT) {
                    Log.d("Rajdeol","Text "+parser.getText());
                }
                // update eventType
                eventType = parser.next();
            }
            // display the data on screen
            //  displayScannedData();
        } catch (XmlPullParserException e) {

            Log.e("AAdhar",""+e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//

    public void  showaadhar(String tx1,String tx2,String tx3,String tx4,String tx5,String tx6,String tx7,String tx8,String tx9,String tx10,String tx11,String tx12)
    {

        String n1 = tx1.replaceAll(","," ");
        String n2 = tx2.replaceAll(","," ");
        String n3 = tx3.replaceAll(","," ");
        String n6 = tx6.replaceAll(","," ");
        String n10 = tx10.replaceAll(","," ");
        String n11 = tx11.replaceAll(","," ");
        String n4 = tx4.replaceAll(","," ");
        String n5 = tx5.replaceAll(","," ");
        String n7 = tx7.replaceAll(","," ");
        String n12 = tx12.replaceAll(","," ");

        eaddress.setText(n4+" "+n12+" "+n5+" "+n7);
        farmername.setText(n1);
        if(n2.equalsIgnoreCase(""))
        {
            fathername.setText(n11);
        }
        else {
            fathername.setText(n2);
        }
        pincode.setText(n6);
        edob.setText(n3);
        if(n10.equalsIgnoreCase("M") || n10.equalsIgnoreCase("Male")) {
            gender.setText("Male");
        }
        else
        {
            gender.setText("Female");
        }

        Intent i = new Intent(getApplicationContext(),QRView.class);
        i.putExtra("tx1",tx1);
        i.putExtra("tx2",tx2);
        i.putExtra("tx3",tx3);
        i.putExtra("tx4",tx4);
        i.putExtra("tx5",tx5);
        i.putExtra("tx6",tx6);
        i.putExtra("tx7",tx7);
        i.putExtra("tx8",tx8);
        i.putExtra("tx9",tx9);
        // startActivity(i);




    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Please make sure the data is stored before exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity3.super.onBackPressed();
                    }
                }).create().show();
    }

    public String getmastercode(String description){
        String result = "";
        Cursor cursor = dbs.rawQuery("Select * from masterl where out_master_description = '"+description+"' ",null);
        try{
            if(cursor.getCount() > 0 ){
                if(cursor.moveToFirst()){
                    result = cursor.getString(cursor.getColumnIndexOrThrow("out_master_code"));
                }
            }
        }catch (Exception e){
            Log.e("error",Log.getStackTraceString(e));
        }finally {
            cursor.close();
        }
        return result;
    }

    public void showdialog(String title,String message){
        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity3.this)
//set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                .setTitle(title+"!")
//set message
                .setMessage(message+"!")
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
    private void landsearchpopupwindow() {
        dialog = new Dialog(MainActivity3.this);
        dialog.setContentView(R.layout.suppliersearch2);
        dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
        dialog.setTitle("Title...");
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
        final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity3.this));
        adapterd = new MainActivity3.MyRecyclerViewAdapterd(MainActivity3.this, pojolocList);
        dialog.getWindow().setLayout(width, height);
        elc = (AutoCompleteTextView)dialog.findViewById(R.id.elc);
        dialogtext = dialog.findViewById(R.id.dialogtext);
        dialogtext.setText("Village Search");
        arrayn.clear();
        //Toast.makeText(MainActivity3.this, "Please Wait", Toast.LENGTH_SHORT).show();

//        Cursor cc = dbs.query("masterl", new String[]{"out_master_description","out_depend_code"
//                }, "out_parent_code" + "=?",
//                new String[]{"QCD_UN_VILLAGE"}, null, null, null, null);

        Cursor cc = dbs.rawQuery("select out_master_description,out_depend_code from masterl where out_parent_code = 'QCD_UN_VILLAGE'",null);

        if(cc.getCount()!=0) {
            if (cc.moveToFirst()) {
                do {
                    if(arrayn.contains(cc.getString(0).trim()))
                    {

                    }
                    else
                    {
                        arrayn.add(cc.getString(0));
                    }
                } while (cc.moveToNext());
            }
        }
//
//        try {
//            if (cc.getCount() != 0) {
//                if (cc.moveToFirst()) {
//                    do {
//
//                        String villagedesc = cc.getString(0).trim();
//                        Cursor cgrama = dbs.rawQuery("select out_depend_code from masterl where out_master_code = '" + cc.getString(1) + "'", null);
//                        try {
//                            if (cgrama.getCount() > 0) {
//                                if (cgrama.moveToFirst()) {
//                                    Cursor ctaluk = dbs.rawQuery("select out_depend_code from masterl where out_master_code = '" + cgrama.getString(0) + "'", null);
//                                    try {
//
//
//                                        if (ctaluk.getCount() > 0) {
//                                            if (ctaluk.moveToFirst()) {
//                                                Cursor cdis = dbs.rawQuery("select out_depend_code from masterl where out_master_code = '" + ctaluk.getString(0) + "'", null);
//                                                try {
//                                                    if (cdis.getCount() > 0) {
//                                                        if (cdis.moveToFirst()) {
//                                                            Log.e("DISLOG", "" + cdis.getString(0));
//                                                            if (cdis.getString(0).equalsIgnoreCase(sharedpreferences.getString("lo", "")))
//                                                                if (arrayn.contains(villagedesc)) {
//
//                                                                } else {
//                                                                    arrayn.add(villagedesc);
//                                                                }
//                                                        }
//                                                    }
//                                                } finally {
//                                                    cdis.close();
//                                                }
//                                            }
//                                        }
//                                    } finally {
//                                        ctaluk.close();
//                                    }
//                                }
//                            }
//                        } finally {
//                            cgrama.close();
//                        }
//                        // Log.e("VAL",""+cursor.getString(11));
//
//                    } while (cc.moveToNext());
//                }
//            }
//        }
//        finally {
//            cc.close();
//        }

        ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(MainActivity3.this,
                R.layout.spinnertext, arrayn);

        elc.setAdapter(adapterlist2n);

        elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                pojolocList.clear();
                Cursor cursor = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_depend_code","out_parent_code"
                        }, "out_master_description" + " LIKE ?",
                        new String[]{"%"+ (String) parent.getItemAtPosition(position)+ "%"}, null, null, null, null);
                if(cursor.getCount()!=0)
                {
                    if (cursor.moveToFirst()) {
                        do {

                            if(cursor.getString(3).equalsIgnoreCase("QCD_UN_VILLAGE"))
                            {
                                Pojoloc pojoloc = new Pojoloc();
                                pojoloc.setS1(cursor.getString(0));
                                pojoloc.setS2(cursor.getString(1));

                                String dgp = cursor.getString(2);

                                Cursor cc = dbs.query("masterl", new String[]{"out_master_description","out_master_code"
                                        }, "out_depend_code" + "=?",
                                        new String[]{pojoloc.getS1()}, null, null, null, null);
                                if(cc.getCount()!=0) {
                                    if (cc.moveToFirst()) {
                                        pojoloc.setS9(cc.getString(1));
                                        pojoloc.setS10(cc.getString(0));
                                    }
                                }

                                Log.e("NULL",""+dgp);
                                // pojoloc.setS3(cursor.getString(1));
                                Cursor cursorg = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_depend_code"
                                        }, "out_master_code" + "=? COLLATE NOCASE",
                                        new String[]{dgp}, null, null, null, null);
                                if(cursorg.getCount()!=0)
                                {
                                    if (cursorg.moveToFirst()) {
                                        pojoloc.setS3(cursorg.getString(0));
                                        pojoloc.setS4(cursorg.getString(1));
                                        String dt = cursorg.getString(2);

                                        Cursor cursort = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_depend_code"
                                                }, "out_master_code" + "=? COLLATE NOCASE",
                                                new String[]{dt}, null, null, null, null);
                                        if(cursort.getCount()!=0)
                                        {
                                            if (cursort.moveToFirst()) {
                                                pojoloc.setS5(cursort.getString(0));
                                                pojoloc.setS6(cursort.getString(1));
                                                String dd = cursort.getString(2);
                                                Log.e("NULL",""+dd);

                                                Cursor cursord = dbs.query("masterl", new String[]{"out_master_code","out_master_description","out_depend_code"
                                                        }, "out_master_code" + "=? COLLATE NOCASE",
                                                        new String[]{dd}, null, null, null, null);
                                                if(cursord.getCount()!=0)
                                                {
                                                    if (cursord.moveToFirst()) {
                                                        pojoloc.setS7(cursord.getString(0));
                                                        pojoloc.setS8(cursord.getString(1));
                                                        Log.e("LLOOCC",""+cursord.getString(2)+"//"+sharedpreferences.getString("lo",""));
                                                        if(cursord.getString(2).equalsIgnoreCase(sharedpreferences.getString("lo",""))) {
                                                            pojolocList.add(pojoloc);
                                                        }
                                                        else
                                                        {
                                                            Toast.makeText(MainActivity3.this, "Selected Village Not Belongs To Selected FPO", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } while (cursor.moveToNext());
                    }
                    recyclerView.setAdapter(adapterd);
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
            }
        });

        dialog.show();
    }

}