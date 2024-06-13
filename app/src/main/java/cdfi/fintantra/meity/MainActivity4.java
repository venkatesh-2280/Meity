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
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
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
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import cdfi.fintantra.meity.Pawhs.utils.MyConstants;

public class MainActivity4 extends AppCompatActivity {
    String laid, laslno;
    String codes, codem, codecr, codecn, codesn;
    ImageView lefta, righta, consent;
    private GpsTracker gpsTracker;
    AutoCompleteTextView tlt, tow, soiltype, tis, landvillage;
    EditText elnoa, elat, elon, vtilldate;
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
    int pg = 0;
    String[] tfs;
    String n19a;
    ImageView captureld;
    String usp;
    TextView title, dialogtext;
    RadioButton radioButton1, radioButton2;
    RadioGroup radioGroup;
    ImageView qrcode;
    int ul = 0;
    DatePickerDialog.OnDateSetListener date3;
    Button lsave, lview, addnewbank, addnewkyc, addnewsow, addnewland;
    EditText estate, sdt1, sdt2, sdt3, sdt4, sdt5;
    AutoCompleteTextView cyears, stmnt, ccty2s, stclf, ccts, cvars, stsnm;
    Button ssave, sview, exit;
    String sowsn;
    int sowid;
    int us = 0;
    TextView txtheader;
    ImageView im1, im2, im3, im4, im5, captureh, capturek;
    View v1, v2, v3, v4, v5;
    String ksty;
    int imagetype;
    AutoCompleteTextView gender, kttype, kstype;
    Button bview, save, ksave, kview;
    JSONObject jsonParam;
    int ub = 0;
    EditText searchfarmer;
    int uk = 0;
    ArrayList<String> array3 = new ArrayList<>();
    ArrayList<String> array4 = new ArrayList<>();
    ArrayList<String> array5 = new ArrayList<>();
    DBHelper db;
    String bankid;
    String kycrid;
    String fan;
    String sdc = "QCD_YES";
    String sdd = "QCD_YES";
    LinearLayout linear;
    int bstatus;
    JSONObject userd;
    String kycid;
    String farmerid;
    String bcode;
    JSONObject user3, userd2;
    MyRecyclerViewAdapterf adapterf;
    String kty;
    EditText accountno, eifsc, ebank, ebranch;
    final int CAMERA_CAPTURE = 1;
    private static final int CAMERA_REQUEST = 1888;
    EditText farmername, surname, fathername, mobileno, pincode, edob, eaddress, taluk, grama, district, hamlet, documentno, cdocumentno;
    AutoCompleteTextView village, elc;
    String[] genders = {"Male", "Female", "Transgender"};
    /*    String[] actype = {"ID", "Address", "Age"};
    String[] acsubtype = {"Pan card", "Voter Id","Driving Licence","Passport"};*/
    String[] actype = {}, actypecode = {};
    String[] acsubtype = {}, acsubtypecode = {};
    LinearLayout l1, l2, l3, l4, l5;
    String mv, md, mg, mt, ham;
    int faid = 0;
    Button bsave;
    String bbid;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    String ui = "0", ui2 = "0", ui3 = "0";
    String encodedImage = "0", encodedImage2 = "0", encodedImage3 = "0";
    ByteArrayOutputStream byteArrayOutputStream, byteArrayOutputStream2, byteArrayOutputStream3;
    String imageFilePath, imageFilePath2;
    Bitmap thePic, thePic2, thePic3;
    Uri picUri, picUri2;
    Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
    String regex
            = "^[A-PR-WYa-pr-wy][1-9]\\d"
            + "\\s?\\d{4}[1-9]$";
    Pattern pp = Pattern.compile(regex);
    ProgressDialog pdialog;
    Dialog dialog;
    MainActivity4.MyRecyclerViewAdapterb adapterb;
    List<Pojoloc> pojolocList;
    List<Pojofar> pojofarlist;
    Calendar myCalendar, cal2;
    MainActivity4.MyRecyclerViewAdapterd adapterd;
    SQLiteDatabase dbs;
    String modeflag = "I";
    ArrayList<String> arrayn, arraymobileno;
    MainActivity4.MyRecyclerViewAdapter adapter;
    List<Pojokyc> listitem;
    private IntentIntegrator qrScan;
    List<Pojobank> pojobankList;
    String farmer_filter_flag = "All", far_name = "", far_mobile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_main4);
        qrcode = (ImageView) findViewById(R.id.qrcode);
        getSupportActionBar().hide();
        farmername = (EditText) findViewById(R.id.farmername);
        surname = (EditText) findViewById(R.id.surname);
        fathername = (EditText) findViewById(R.id.fathername);
        mobileno = (EditText) findViewById(R.id.mobileno);
        pincode = (EditText) findViewById(R.id.pincode);
        edob = (EditText) findViewById(R.id.dob);
        vtilldate = (EditText) findViewById(R.id.vtilldate);
        eaddress = (EditText) findViewById(R.id.address);
        village = (AutoCompleteTextView) findViewById(R.id.village);
        kttype = (AutoCompleteTextView) findViewById(R.id.kttype);
        kstype = (AutoCompleteTextView) findViewById(R.id.kstype);
        taluk = (EditText) findViewById(R.id.taluk);
        documentno = (EditText) findViewById(R.id.documentno);
        cdocumentno = (EditText) findViewById(R.id.cdocumentno);
        estate = (EditText) findViewById(R.id.estate);
        grama = (EditText) findViewById(R.id.grama);
        district = (EditText) findViewById(R.id.district);
        hamlet = (EditText) findViewById(R.id.hamlet);
        searchfarmer = (EditText) findViewById(R.id.searchfarmer);
        captureh = (ImageView) findViewById(R.id.captureh);
        capturek = (ImageView) findViewById(R.id.capturek);
        txtheader = (TextView) findViewById(R.id.txtheader);
        pdialog = new ProgressDialog(this);
        linear = (LinearLayout) findViewById(R.id.linear);
        accountno = (EditText) findViewById(R.id.accountno);
        eifsc = (EditText) findViewById(R.id.eifsc);
        ebank = (EditText) findViewById(R.id.ebank);
        ebranch = (EditText) findViewById(R.id.ebranch);
        bsave = (Button) findViewById(R.id.bsave);
        listitem = new ArrayList<>();
        pojofarlist = new ArrayList<>();
        save = (Button) findViewById(R.id.save);
        ksave = (Button) findViewById(R.id.ksave);
        kview = (Button) findViewById(R.id.kview);

        exit = (Button) findViewById(R.id.exit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity4.this);
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

        if (sharedpreferences.getString("lo", "").equalsIgnoreCase("QCD_UNS_TAMIL")) {
            estate.setText("Tamil Nadu");
        } else {
            estate.setText("UP");
        }
        title = (TextView) findViewById(R.id.title);
        title.setText("" + sharedpreferences.getString("rn", ""));
        db = new DBHelper(this);
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan = new IntentIntegrator(MainActivity4.this);
                qrScan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                qrScan.setPrompt("Scan Farmer Code");
                qrScan.setBeepEnabled(true);
                qrScan.setCameraId(0);
                qrScan.setOrientationLocked(false);
                qrScan.setBarcodeImageEnabled(true);


                qrScan.initiateScan();
            }
        });
        lefta = (ImageView) findViewById(R.id.lefta);
        righta = (ImageView) findViewById(R.id.righta);
        dbs = db.getWritableDatabase();
        im1 = (ImageView) findViewById(R.id.im1);
        im2 = (ImageView) findViewById(R.id.im2);
        im3 = (ImageView) findViewById(R.id.im3);
        im4 = (ImageView) findViewById(R.id.im4);
        im5 = (ImageView) findViewById(R.id.im5);
        tlt = (AutoCompleteTextView) findViewById(R.id.tlt);
        tow = (AutoCompleteTextView) findViewById(R.id.tow);
        soiltype = (AutoCompleteTextView) findViewById(R.id.soiltype);
        landvillage = (AutoCompleteTextView) findViewById(R.id.landvillage);
        tis = (AutoCompleteTextView) findViewById(R.id.tis);

        elnoa = (EditText) findViewById(R.id.elnoa);
        elat = (EditText) findViewById(R.id.elat);
        elon = (EditText) findViewById(R.id.elon);

        lsave = (Button) findViewById(R.id.lsave);
        lview = (Button) findViewById(R.id.lview);

        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        captureld = (ImageView) findViewById(R.id.captureld);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int maxLengthll = 10;
        elnoa.setFilters(new InputFilter[]{new DecimalDigitsInputFilter2(10, 2)});
        v1 = (View) findViewById(R.id.v1);
        v2 = (View) findViewById(R.id.v2);
        v3 = (View) findViewById(R.id.v3);
        v4 = (View) findViewById(R.id.v4);
        v5 = (View) findViewById(R.id.v5);
        arrayn = new ArrayList<>();
        pojolocList = new ArrayList<>();
        pojobankList = new ArrayList<>();
        sdt1 = (EditText) findViewById(R.id.sdt1);
        sdt2 = (EditText) findViewById(R.id.sdt2);
        sdt3 = (EditText) findViewById(R.id.sdt3);
        sdt4 = (EditText) findViewById(R.id.sdt4);
        sdt5 = (EditText) findViewById(R.id.sdt5);
        sdt1.setFilters(new InputFilter[]{new DecimalDigitsInputFilter2(6, 2)});
        sdt2.setFilters(new InputFilter[]{new DecimalDigitsInputFilter2(6, 2)});
        sdt3.setFilters(new InputFilter[]{new DecimalDigitsInputFilter2(6, 2)});
        sdt4.setFilters(new InputFilter[]{new DecimalDigitsInputFilter2(6, 2)});
        sdt5.setFilters(new InputFilter[]{new DecimalDigitsInputFilter2(6, 2)});
        cyears = (AutoCompleteTextView) findViewById(R.id.cyears);
        stmnt = (AutoCompleteTextView) findViewById(R.id.stmnt);
        ccty2s = (AutoCompleteTextView) findViewById(R.id.ccty2s);
        stclf = (AutoCompleteTextView) findViewById(R.id.stclf);
        ccts = (AutoCompleteTextView) findViewById(R.id.ccts);
        cvars = (AutoCompleteTextView) findViewById(R.id.cvars);
        stsnm = (AutoCompleteTextView) findViewById(R.id.stsnm);

        ssave = (Button) findViewById(R.id.ssave);
        sview = (Button) findViewById(R.id.sview);
        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        l3 = (LinearLayout) findViewById(R.id.l3);
        l4 = (LinearLayout) findViewById(R.id.l4);
        l5 = (LinearLayout) findViewById(R.id.l5);
        bview = (Button) findViewById(R.id.bview);

        addnewbank = (Button) findViewById(R.id.addnewbank);
        addnewkyc = (Button) findViewById(R.id.addnewkyc);
        addnewsow = (Button) findViewById(R.id.addnewsow);
        addnewland = (Button) findViewById(R.id.addnewland);

        consent = (ImageView) findViewById(R.id.consent);

        consent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyConstants.consentflag = "0";
                Intent i = new Intent(MainActivity4.this, Consent.class);
                startActivity(i);
            }
        });

        addnewland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

        addnewsow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                us = 0;

                sowid = 0;
                sowsn = "";
                cyears.setText("");
                ccty2s.setText("");
                ccts.setText("");
                cvars.setText("");
                sdt1.setText("");
                sdt2.setText("");
                sdt3.setText("");
                sdt4.setText("");
                sdt5.setText("");
                stmnt.setText("");
                stclf.setText("");

                stsnm.setText("");
            }
        });

        addnewkyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ksty = "";
                kycid = "";
                kycrid = "";
                uk = 0;
                kttype.setText("");
                kty = "";
                ksty = "";
                kstype.setText("");
                documentno.setText("");
                cdocumentno.setText("");
                vtilldate.setText("");

                ui2 = "0";
                capturek.setImageResource(0);
                encodedImage2 = "0";
                capturek.setBackgroundResource(R.drawable.capture);
            }
        });
        addnewbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountno.setText("");
                ebank.setText("");
                ebranch.setText("");
                eifsc.setText("");
                bankid = "";
                bbid = "";
                ub = 0;
                // bsave.setText("Update");
                bcode = "0";
            }
        });
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
                if (s.length() == 10) {
//                    if(kstype.getText().toString().equalsIgnoreCase("Pan Card"))
                    if (ksty.equalsIgnoreCase("QCD_PRFI_PAN") || ksty.equalsIgnoreCase("QCD_PRFG_PAN")) {
                        Matcher matcher = pattern.matcher(s);
                        if (matcher.matches()) {
                            Log.i("Matching", "Yes");
                        } else {
                            documentno.setText("");
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                } else if (s.length() == 8) {
//                    if(kstype.getText().toString().equalsIgnoreCase("Passport"))
                    if (ksty.equalsIgnoreCase("QCD_PRFI_PASS") || ksty.equalsIgnoreCase("QCD_PRFA_PASS") || ksty.equalsIgnoreCase("QCD_PRFG_PASS")) {
                        Matcher matcher = pp.matcher(s);
                        if (matcher.matches()) {
                            Log.i("Matching", "Yes");
                        } else {
                            documentno.setText("");
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                Log.e("selectedsubtype", selecteditem);
                ksty = selecteditem;
                if (selecteditem.equalsIgnoreCase("QCD_PRFA_AADHAR") || selecteditem.equalsIgnoreCase("QCD_PRFG_AADHAR")) {
                    cdocumentno.setEnabled(true);
                    documentno.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    cdocumentno.setInputType(InputType.TYPE_CLASS_NUMBER);
                    int maxLength = 12;
                    documentno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                    cdocumentno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                } else if (selecteditem.equalsIgnoreCase("QCD_PRFI_PAN") || selecteditem.equalsIgnoreCase("QCD_PRFG_PAN")) {
                    int maxLength = 10;
                    documentno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                    cdocumentno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                    documentno.setInputType(InputType.TYPE_CLASS_TEXT);
                    cdocumentno.setInputType(InputType.TYPE_CLASS_TEXT);
                } else if (selecteditem.equalsIgnoreCase("QCD_PRFI_PASS") || selecteditem.equalsIgnoreCase("QCD_PRFA_PASS") || selecteditem.equalsIgnoreCase("QCD_PRFG_PASS")) {
                    int maxLength = 8;
                    documentno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                    documentno.setInputType(InputType.TYPE_CLASS_TEXT);
                    cdocumentno.setInputType(InputType.TYPE_CLASS_TEXT);
                } else if (selecteditem.equalsIgnoreCase("QCD_PRFI_VOTER") || selecteditem.equalsIgnoreCase("QCD_PRFA_VOTER") || selecteditem.equalsIgnoreCase("QCD_PRFG_VOTER")) {
                    int maxLength = 19;
                    documentno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                    documentno.setInputType(InputType.TYPE_CLASS_TEXT);
                    cdocumentno.setInputType(InputType.TYPE_CLASS_TEXT);
                } else if (selecteditem.equalsIgnoreCase("QCD_PRFI_DRIVE") || selecteditem.equalsIgnoreCase("QCD_PRFA_DRIVE") || selecteditem.equalsIgnoreCase("QCD_PRFG_DRIVE")) {
                    int maxLength = 12;
                    documentno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                    documentno.setInputType(InputType.TYPE_CLASS_TEXT);
                    cdocumentno.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    documentno.setInputType(InputType.TYPE_CLASS_TEXT);
                    cdocumentno.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });

    /*    kstype.addTextChangedListener(new TextWatcher() {

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
        });*/

        Cursor cursor2 = dbs.query("tablist", new String[]{"field"
                }, "tab" + "=?",
                new String[]{"Sowing Details"}, null, null, null, null);


        // Cursor cursor2 = dbs.rawQuery(selectQuery, null);

        Log.e("RESNEW", "" + cursor2.getCount());


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
                            Log.e("YEAR", "" + Arrays.toString(tfs));
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


                }

            } while (cursor2.moveToNext());
        }

        Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code", "out_master_description","out_depend_code"
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
            } while (cursor2mst.moveToNext());


            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (MainActivity4.this, R.layout.spinnertext3, arraysn);
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

                // Log.e("VALCHK",""+arg0.toString());
                arraymn.clear();
                arraymc.clear();
                arraycrc.clear();
                arraycrn.clear();
                array3.clear();
                // ccts.setText("Tap For Select Crop Name");
                codes = arraysc.get(position);


                Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                        }, "out_depend_code" + "=?",
                        new String[]{arraysc.get(position)}, null, null, null, null);


                if (cursor2mst.moveToFirst()) {

                    do {
                        if (cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_MONTHS")) {


                            arraymc.add(cursor2mst.getString(0));
                            arraymn.add(cursor2mst.getString(1));
                            if (cursor2mst.getString(1).equalsIgnoreCase("January")) {
                                array3.add("01");
                            } else if (cursor2mst.getString(1).equalsIgnoreCase("February")) {
                                array3.add("02");
                            } else if (cursor2mst.getString(1).equalsIgnoreCase("March")) {
                                array3.add("03");
                            } else if (cursor2mst.getString(1).equalsIgnoreCase("April")) {
                                array3.add("04");
                            } else if (cursor2mst.getString(1).equalsIgnoreCase("May")) {
                                array3.add("05");
                            } else if (cursor2mst.getString(1).replaceAll(" ", "").equalsIgnoreCase("June")) {
                                array3.add("06");
                            } else if (cursor2mst.getString(1).equalsIgnoreCase("July")) {
                                array3.add("07");
                            } else if (cursor2mst.getString(1).equalsIgnoreCase("August")) {
                                array3.add("08");
                            } else if (cursor2mst.getString(1).equalsIgnoreCase("September")) {
                                array3.add("09");
                            } else if (cursor2mst.getString(1).equalsIgnoreCase("October")) {
                                array3.add("10");
                            } else if (cursor2mst.getString(1).equalsIgnoreCase("November")) {
                                array3.add("11");
                            } else if (cursor2mst.getString(1).equalsIgnoreCase("December")) {
                                array3.add("12");
                            }
                            Log.e("MyAndroidClass", Arrays.toString(new ArrayList[]{array3}));
                            Log.e("MyAndroidClass2", cursor2mst.getString(1));
                        }
                        if (cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_CROPCLASS")) {
                            Log.e("VALCHK", "" + cursor2mst.getString(0));

                            arraycrc.add(cursor2mst.getString(0));
                            arraycrn.add(cursor2mst.getString(1));
                        }
                    } while (cursor2mst.moveToNext());


//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                            (MainActivity4.this, R.layout.spinnertext3, arraymn);
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
                            (MainActivity4.this, R.layout.spinnertext3, arraycrn);
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

                Log.e("VALCHK", "" + position);
                arraycnc.clear();
                arraycnn.clear();

                // ccts.setText("Tap For Select Crop Name");

                codecr = arraycrc.get(position);
                Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                        }, "out_depend_code" + "=?",
                        new String[]{arraycrc.get(position)}, null, null, null, null);


                if (cursor2mst.moveToFirst()) {

                    do {
                        if (cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_CROPNAME")) {


                            arraycnc.add(cursor2mst.getString(0));
                            arraycnn.add(cursor2mst.getString(1));
                        }

                    } while (cursor2mst.moveToNext());


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                            (MainActivity4.this, R.layout.spinnertext3, arraycnn);
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

                //ccts.setText("");
                stsnm.setText("");

                Log.e("VALCHK", "" + position);
                arrayvc.clear();
                arrayvn.clear();
                arraysec.clear();
                arraysen.clear();
                // ccts.setText("Tap For Select Crop Name");

                codecn = arraycnc.get(position);
                Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                        }, "out_depend_code" + "=?",
                        new String[]{arraycnc.get(position)}, null, null, null, null);


                if (cursor2mst.moveToFirst()) {

                    do {
                        if (cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_CROPVARIETY")) {


                            arrayvc.add(cursor2mst.getString(0));
                            arrayvn.add(cursor2mst.getString(1));
                        }

                    } while (cursor2mst.moveToNext());

                    for (int i = 0; i < arrayvc.size(); i++) {
                        // Log.e("VALCHK",""+position);


                        // ccts.setText("Tap For Select Crop Name");


                        Cursor cursor2mst2 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                }, "out_depend_code" + "=?",
                                new String[]{arrayvc.get(i)}, null, null, null, null);


                        if (cursor2mst2.moveToFirst()) {

                            do {
                                if (cursor2mst2.getString(2).equalsIgnoreCase("QCD_SOW_SEEDNAME")) {


                                    arraysec.add(cursor2mst2.getString(0));
                                    arraysen.add(cursor2mst2.getString(1));
                                }

                            } while (cursor2mst2.moveToNext());


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (MainActivity4.this, R.layout.spinnertext3, arraysen);
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
                            (MainActivity4.this, R.layout.spinnertext3, arrayvn);
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
//
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
                        cal2.set(Calendar.YEAR, year);
                        cal2.set(Calendar.MONTH, monthOfYear);
                        cal2.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                        updateLabel2();
                    }

                };
                // codem=arraymc.get(position);
                // String val = "[typeDIV[ID,Address,DOB,Date of joining]:subtypeDIV[Aadhar,Pan Card,Voterid,t1,t2]:document noDIV[0]:valid till dateDIV[0]]";


                //String selectQuery = "SELECT  * FROM tablist";


                // Cursor cursor2 = dbs.rawQuery(selectQuery, null);
            }
        });

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
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity4.this, android.R.style.Theme_Holo_Dialog, date3, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                // datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
//                datePickerDialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis()-86400000);


                datePickerDialog.show();

            }
        });

        stsnm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Log.e("CODE", "" + arraysec.get(position));
                // add the array list here..


                codesn = arraysec.get(position);
                // String val = "[typeDIV[ID,Address,DOB,Date of joining]:subtypeDIV[Aadhar,Pan Card,Voterid,t1,t2]:document noDIV[0]:valid till dateDIV[0]]";


                //String selectQuery = "SELECT  * FROM tablist";


                // Cursor cursor2 = dbs.rawQuery(selectQuery, null);
            }
        });


        captureld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    imagetype = 2;
                    picUri = FileProvider.getUriForFile(MainActivity4.this, getApplicationContext().getPackageName() + ".provider", createImageFile());
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
                if (sharedpreferences.getString("fid2", "").equalsIgnoreCase("")) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                } else {
                    addland();
                }
            }
        });

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
                        //  Toast.makeText(MainActivity4.this, "hi", Toast.LENGTH_SHORT).show();

                        if (tf[1].replaceAll(" ", "").equalsIgnoreCase("")) {

                        } else {
                            String tv = tf[1];
                            String tv2 = tv.substring(1, tv.length() - 1);
                            final String[] tfs = tv2.split(",");

                            tow.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                            (MainActivity4.this, R.layout.spinnertext3, tfs);
                                    //Getting the instance of AutoCompleteTextView

                                    tow.setThreshold(0);//will start working from first character
                                    tow.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                                    tow.showDropDown();
                                    tow.requestFocus();
                                }
                            });
                            // total number of textviews to add


                        }
                    }
                    if (tf[0].replaceAll(" ", "").equalsIgnoreCase("landtype")) {
                        //  Toast.makeText(MainActivity4.this, "hi", Toast.LENGTH_SHORT).show();

                        if (tf[1].replaceAll(" ", "").equalsIgnoreCase("")) {

                        } else {
                            String tv = tf[1];
                            String tv2 = tv.substring(1, tv.length() - 1);
                            final String[] tfs = tv2.split(",");


                            tlt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                            (MainActivity4.this, R.layout.spinnertext3, tfs);
                                    //Getting the instance of AutoCompleteTextView

                                    tlt.setThreshold(0);//will start working from first character
                                    tlt.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                                    tlt.showDropDown();
                                    tlt.requestFocus();
                                }
                            });


                        }
                    }
                    if (tf[0].replaceAll(" ", "").equalsIgnoreCase("soiltype")) {
                        //  Toast.makeText(MainActivity4.this, "hi", Toast.LENGTH_SHORT).show();

                        if (tf[1].replaceAll(" ", "").equalsIgnoreCase("")) {

                        } else {
                            String tv = tf[1];
                            String tv2 = tv.substring(1, tv.length() - 1);
                            final String[] tfs = tv2.split(",");


                            soiltype.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                            (MainActivity4.this, R.layout.spinnertext3, tfs);
                                    //Getting the instance of AutoCompleteTextView

                                    soiltype.setThreshold(0);//will start working from first character
                                    soiltype.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                                    soiltype.showDropDown();
                                    soiltype.requestFocus();
                                }
                            });


                        }
                    }
                    if (tf[0].replaceAll(" ", "").equalsIgnoreCase("irrigationsources")) {
                        //  Toast.makeText(MainActivity4.this, "hi", Toast.LENGTH_SHORT).show();

                        if (tf[1].replaceAll(" ", "").equalsIgnoreCase("")) {

                        } else {
                            String tv = tf[1];
                            String tv2 = tv.substring(1, tv.length() - 1);
                            final String[] tfs = tv2.split(",");


                            // total number of textviews to add


                            tis.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                            (MainActivity4.this, R.layout.spinnertext3, tfs);
                                    //Getting the instance of AutoCompleteTextView

                                    tis.setThreshold(0);//will start working from first character
                                    tis.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
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
                dialog = new Dialog(MainActivity4.this);
                dialog.setContentView(R.layout.tablist);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                TextView title = (TextView) dialog.findViewById(R.id.texttitle);

                dialog.getWindow().setLayout(width, height);
                title.setText("Land Details");

                androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity4.this));
                adapter = new MainActivity4.MyRecyclerViewAdapter(MainActivity4.this, listitem, 6);
                recyclerView.setAdapter(adapter);
                //String selectQuery = "SELECT  * FROM family where v10 = "+sharedpreferences.getString("fcode2","");
                listitem.clear();
                Cursor cursor = dbs.query("land", new String[]{"id", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8", "v9", "v10", "v11", "v12", "v13", "v14"
                        }, "v10" + "=?",
                        new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                // looping through all rows and adding to list
                Log.e("NULL", "" + cursor.getCount());
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

        kttype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                kstype.setText("");
                ksty = "";
                String selecteditem = actypecode[i];
                kty = actypecode[i];
                Log.e("actype", kty);
                Cursor cr = dbs.rawQuery("select out_master_code, out_master_description from masterl where out_depend_code = '" + selecteditem + "' and out_status_code = 'A' ", null);
                try {
                    acsubtype = new String[cr.getCount()];
                    acsubtypecode = new String[cr.getCount()];
                    if (cr.getCount() > 0) {
                        if (cr.moveToFirst()) {
                            for (int j = 0; j < cr.getCount(); j++) {
                                acsubtype[j] = cr.getString(cr.getColumnIndexOrThrow("out_master_description"));
                                acsubtypecode[j] = cr.getString(cr.getColumnIndexOrThrow("out_master_code"));
                                cr.moveToNext();
                            }
                        }
                    }
                } catch (Exception er) {
                    Log.e("error", Log.getStackTraceString(er));
                } finally {
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
        ssave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedpreferences.getString("fid2", "").equalsIgnoreCase("")) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                } else {
                    addsow();
                }
            }
        });
        sview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(MainActivity4.this);
                dialog.setContentView(R.layout.tablist);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                TextView title = (TextView) dialog.findViewById(R.id.texttitle);

                dialog.getWindow().setLayout(width, height);
                title.setText("Sowing Details");
                androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity4.this));
                adapter = new MainActivity4.MyRecyclerViewAdapter(MainActivity4.this, listitem, 21);
                recyclerView.setAdapter(adapter);
                //String selectQuery = "SELECT  * FROM kyc where fid = "+sharedpreferences.getString("fcode2","");
                listitem.clear();
                Cursor cursor = dbs.query("sowing", new String[]{"id", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8", "v9", "v10", "v11", "v12", "v13", "v14", "v15", "v16", "v17", "n1", "n2", "n3", "n4", "n5"
                        }, "v16" + "=?",
                        new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                // looping through all rows and adding to list
                Log.e("NULL", "" + cursor.getCount());
                if (cursor.moveToFirst()) {
                    do {
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
                dialog = new Dialog(MainActivity4.this);
                dialog.setContentView(R.layout.tablist);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                TextView title = (TextView) dialog.findViewById(R.id.texttitle);

                dialog.getWindow().setLayout(width, height);
                title.setText("KYC");


                // set up the RecyclerView
                androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity4.this));
                adapter = new MainActivity4.MyRecyclerViewAdapter(MainActivity4.this, listitem, 2);
                recyclerView.setAdapter(adapter);
                String selectQuery = "SELECT  * FROM kyc where fid = " + sharedpreferences.getString("fcode2", "");
                listitem.clear();
                Cursor cursor = dbs.query("kyc", new String[]{"id", "type", "subtype", "dno", "vtd", "tms", "stmc", "rid", "poto"
                        }, "fid" + "=?",
                        new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                // looping through all rows and adding to list
                Log.e("NULL", "" + cursor.getCount());
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

                dialog = new Dialog(MainActivity4.this);
                dialog.setContentView(R.layout.tablist);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                TextView title = (TextView) dialog.findViewById(R.id.texttitle);

                dialog.getWindow().setLayout(width, height);
                title.setText("Bank");

                androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity4.this));
                adapter = new MainActivity4.MyRecyclerViewAdapter(MainActivity4.this, listitem, 3);
                recyclerView.setAdapter(adapter);
                String selectQuery = "SELECT  * FROM bank where fid = " + sharedpreferences.getString("fcode2", "");
                listitem.clear();
                Cursor cursor = dbs.query("bank", new String[]{"id", "type", "ano", "bank", "ifsc", "branch", "dc", "dd", "typec", "bc", "bid"
                        }, "fid" + "=?",
                        new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                // looping through all rows and adding to list
                Log.e("NULL", "" + cursor.getCount());
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
        cal2 = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

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
        edob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity4.this, android.R.style.Theme_Holo_Dialog, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
            //    datePickerDialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis() - 86400000);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, -18);
                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

                datePickerDialog.show();

            }
        });

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                cal2.set(Calendar.YEAR, year);
                cal2.set(Calendar.MONTH, monthOfYear);


                cal2.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabel2();
            }

        };
        stmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity4.this, android.R.style.Theme_Holo_Dialog, date2, cal2
                        .get(Calendar.YEAR), cal2.get(Calendar.MONTH),
                        cal2.get(Calendar.DAY_OF_MONTH));
//                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });
        captureh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    imagetype = 0;
                    picUri = FileProvider.getUriForFile(MainActivity4.this, getApplicationContext().getPackageName() + ".provider", createImageFile());
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
                    picUri = FileProvider.getUriForFile(MainActivity4.this, getApplicationContext().getPackageName() + ".provider", createImageFile());
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                    takePictureIntent.putExtra("return-data", true);
                    startActivityForResult(takePictureIntent, CAMERA_CAPTURE);// convert path to Uri
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        gender = (AutoCompleteTextView) findViewById(R.id.gender);
        Cursor cursorgender = dbs.rawQuery("select * from masterl where out_parent_code = 'QCD_GENDER'", null);
        if (cursorgender.moveToFirst()) {
            do {
                gendername.add(cursorgender.getString(4));
            } while (cursorgender.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, R.layout.spinnertext3, genders);
        //Getting the instance of AutoCompleteTextView

        gender.setThreshold(0);//will start working from first character
        gender.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity4.this, R.layout.spinnertext3, genders);
                //Getting the instance of AutoCompleteTextView
                gender.setThreshold(0);//will start working from first character
                gender.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                gender.showDropDown();
                gender.requestFocus();
            }
        });

        Cursor cr = dbs.rawQuery("select out_master_code, out_master_description from masterl where out_parent_code = 'QCD_PRFTYPE' and out_status_code = 'A' ", null);
        try {
            actype = new String[cr.getCount()];
            actypecode = new String[cr.getCount()];
            if (cr.getCount() > 0) {
                if (cr.moveToFirst()) {
                    for (int i = 0; i < cr.getCount(); i++) {
                        actype[i] = cr.getString(cr.getColumnIndexOrThrow("out_master_description"));
                        actypecode[i] = cr.getString(cr.getColumnIndexOrThrow("out_master_code"));
                        cr.moveToNext();
                    }
                }
            }
        } catch (Exception er) {
            Log.e("error", Log.getStackTraceString(er));
        } finally {
            cr.close();

        }

        kttype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                        (MainActivity4.this, R.layout.spinnertext3, actype);
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
                        (MainActivity4.this, R.layout.spinnertext3, acsubtype);
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
                if (pg == 0) {

                } else {
                    pg--;

                    if (pg == 0) {
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
                    if (pg == 1) {
                        if (sharedpreferences.getString("fid2", "").equalsIgnoreCase("")) {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                        } else {
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
                            Cursor cursor = dbs.query("bank", new String[]{"id", "type", "ano", "bank", "ifsc", "branch", "dc", "dd", "typec", "bc", "bid"
                                    }, "fid" + "=?",
                                    new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                            // looping through all rows and adding to list
                            Log.e("NULL", "" + cursor.getCount());
                            if (cursor.getCount() > 0) {
                                if (cursor.moveToLast()) {

                                    accountno.setText(cursor.getString(2));
                                    ebank.setText(cursor.getString(3));
                                    ebranch.setText(cursor.getString(5));
                                    eifsc.setText(cursor.getString(4));
                                    bankid = cursor.getString(0);
                                    bbid = cursor.getString(10);
                                    ub = 1;
                                    // bsave.setText("Update");
                                    bcode = cursor.getString(9);


                                }

                            }
                        }
                    }
                    if (pg == 2) {
                        if (sharedpreferences.getString("fid2", "").equalsIgnoreCase("")) {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                        } else {
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
                            Cursor cursor = dbs.query("kyc", new String[]{"id", "type", "subtype", "dno", "vtd", "tms", "stmc", "rid", "poto"
                                    }, "fid" + "=?",
                                    new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                            // looping through all rows and adding to list
                            Log.e("NULL", "" + cursor.getCount());
                            if (cursor.getCount() > 0) {
                                if (cursor.moveToLast()) {


                                    ksty = cursor.getString(5);
                                    kycid = cursor.getString(0);
                                    kycrid = cursor.getString(7);
                                    uk = 1;

                                    kttype.setText(cursor.getString(1));
                                    kstype.setText(cursor.getString(2));
                                    kty = cursor.getString(cursor.getColumnIndexOrThrow("tms"));
                                    ksty = cursor.getString(cursor.getColumnIndexOrThrow("stmc"));
                                    documentno.setText(cursor.getString(3));
                                    vtilldate.setText(cursor.getString(4));
                                    cdocumentno.setText(cursor.getString(3));

                                    ui2 = cursor.getString(8);
                                    if (cursor.getString(8).equalsIgnoreCase("0")) {
                                        encodedImage2 = "0";
                                        ui2 = "0";
                                    } else {
                                        try {

                                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(cursor.getString(8)));

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
                            }
                        }


                    }
                    if (pg == 4) {
                        if (sharedpreferences.getString("fid2", "").equalsIgnoreCase("")) {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                        } else {
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
                            Cursor cursor = dbs.query("sowing", new String[]{"id", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8", "v9", "v10", "v11", "v12", "v13", "v14", "v15", "v16", "v17", "n1", "n2", "n3", "n4", "n5"
                                    }, "v16" + "=?",
                                    new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                            // looping through all rows and adding to list
                            Log.e("NULL", "" + cursor.getCount());
                            array4.clear();

                            if (cursor.getCount() > 0) {
                                if (cursor.moveToLast()) {


                                    us = 1;

                                    sowid = Integer.parseInt(cursor.getString(0));
                                    sowsn = cursor.getString(14);
                                    cyears.setText(cursor.getString(1));
                                    array4.add(cursor.getString(1));
                                    ccty2s.setText(cursor.getString(18));
                                    codes = cursor.getString(2);
                                    ccts.setText(cursor.getString(21));
                                    codecn = cursor.getString(3);
                                    cvars.setText(cursor.getString(4));
                                    sdt1.setText(cursor.getString(5));
                                    sdt2.setText(cursor.getString(6));
                                    sdt3.setText(cursor.getString(7));
                                    sdt4.setText(cursor.getString(8));
                                    sdt5.setText(cursor.getString(9));
                                    stmnt.setText(cursor.getString(19));
                                    codem = cursor.getString(10);
                                    stclf.setText(cursor.getString(20));
                                    codecr = cursor.getString(11);

                                    stsnm.setText(cursor.getString(22));
                                    codesn = cursor.getString(13);
                                    arraysc.clear();
                                    arraysn.clear();
                                    arraymn.clear();
                                    arraymc.clear();
                                    arraycrc.clear();
                                    arraycrn.clear();
                                    arraycnc.clear();
                                    arraycnn.clear();
                                    arrayvc.clear();
                                    arrayvn.clear();
                                    arraysec.clear();
                                    arraysen.clear();
                                    Cursor cursor2 = dbs.query("tablist", new String[]{"field"
                                            }, "tab" + "=?",
                                            new String[]{"Sowing Details"}, null, null, null, null);


                                    // Cursor cursor2 = dbs.rawQuery(selectQuery, null);

                                    Log.e("RESNEW", "" + cursor2.getCount());


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
                                                        Log.e("YEAR", "" + Arrays.toString(tfs));
                                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                                                (MainActivity4.this, R.layout.spinnertext3, tfs);
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

                                    Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                            }, "out_depend_code" + "=?",
                                            new String[]{codes}, null, null, null, null);


                                    if (cursor2mst.moveToFirst()) {

                                        do {
                                            if (cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_MONTHS")) {


                                                arraymc.add(cursor2mst.getString(0));
                                                arraymn.add(cursor2mst.getString(1));
                                                if (cursor2mst.getString(1).equalsIgnoreCase("January")) {
                                                    array3.add("01");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("February")) {
                                                    array3.add("02");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("March")) {
                                                    array3.add("03");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("April")) {
                                                    array3.add("04");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("May")) {
                                                    array3.add("05");
                                                } else if (cursor2mst.getString(1).replaceAll(" ", "").equalsIgnoreCase("June")) {
                                                    array3.add("06");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("July")) {
                                                    array3.add("07");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("August")) {
                                                    array3.add("08");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("September")) {
                                                    array3.add("09");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("October")) {
                                                    array3.add("10");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("November")) {
                                                    array3.add("11");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("December")) {
                                                    array3.add("12");
                                                }
                                                Log.e("MyAndroidClass", Arrays.toString(new ArrayList[]{array3}));
                                                Log.e("MyAndroidClass2", cursor2mst.getString(1));
                                            }
                                            if (cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_CROPCLASS")) {
                                                Log.e("VALCHK", "" + cursor2mst.getString(0));

                                                arraycrc.add(cursor2mst.getString(0));
                                                arraycrn.add(cursor2mst.getString(1));
                                            }
                                        } while (cursor2mst.moveToNext());


//                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                                                (MainActivity4.this, R.layout.spinnertext3, arraymn);
//                                        //Getting the instance of AutoCompleteTextView
//
//                                        stmnt.setThreshold(0);//will start working from first character
//                                        stmnt.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                                        stmnt.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//                                                stmnt.showDropDown();
//                                                stmnt.requestFocus();
//                                            }
//                                        });
                                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                                                (MainActivity4.this, R.layout.spinnertext3, arraycrn);
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

                                    Cursor cursor2mst1 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                            }, "out_depend_code" + "=?",
                                            new String[]{codecr}, null, null, null, null);


                                    if (cursor2mst1.moveToFirst()) {

                                        do {
                                            if (cursor2mst1.getString(2).equalsIgnoreCase("QCD_SOW_CROPNAME")) {


                                                arraycnc.add(cursor2mst1.getString(0));
                                                arraycnn.add(cursor2mst1.getString(1));
                                            }

                                        } while (cursor2mst1.moveToNext());


                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                                (MainActivity4.this, R.layout.spinnertext3, arraycnn);
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
                                    Cursor cursor2mst2 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                            }, "out_depend_code" + "=?",
                                            new String[]{codecn}, null, null, null, null);


                                    if (cursor2mst2.moveToFirst()) {

                                        do {
                                            if (cursor2mst2.getString(2).equalsIgnoreCase("QCD_SOW_CROPVARIETY")) {


                                                arrayvc.add(cursor2mst2.getString(0));
                                                arrayvn.add(cursor2mst2.getString(1));
                                            }

                                        } while (cursor2mst2.moveToNext());

                                        for (int i = 0; i < arrayvc.size(); i++) {
                                            // Log.e("VALCHK",""+position);


                                            // ccts.setText("Tap For Select Crop Name");


                                            Cursor cursor2mst32 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                                    }, "out_depend_code" + "=?",
                                                    new String[]{arrayvc.get(i)}, null, null, null, null);


                                            if (cursor2mst32.moveToFirst()) {

                                                do {
                                                    if (cursor2mst32.getString(2).equalsIgnoreCase("QCD_SOW_SEEDNAME")) {


                                                        arraysec.add(cursor2mst32.getString(0));
                                                        arraysen.add(cursor2mst32.getString(1));
                                                    }

                                                } while (cursor2mst32.moveToNext());


                                                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                                        (MainActivity4.this, R.layout.spinnertext3, arraysen);
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
                                                (MainActivity4.this, R.layout.spinnertext3, arrayvn);
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


                                    Cursor cursor2mst4 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description","out_depend_code"
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
                                        } while (cursor2mst4.moveToNext());


                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                                (MainActivity4.this, R.layout.spinnertext3, arraysn);
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
                                }
                            }

                        }
                    }
                    if (pg == 3) {
                        if (sharedpreferences.getString("fid2", "").equalsIgnoreCase("")) {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                        } else {
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

                            Cursor cursor = dbs.query("land", new String[]{"id", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8", "v9", "v10", "v11", "v12", "v13", "v14"
                                    }, "v10" + "=?",
                                    new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                            // looping through all rows and adding to list
                            Log.e("NULL", "" + cursor.getCount());

                            if (cursor.getCount() > 0) {
                                if (cursor.moveToLast()) {


                                    laid = cursor.getString(0);
                                    laslno = cursor.getString(8);
                                    tlt.setText(cursor.getString(1));
                                    tow.setText(cursor.getString(2));
                                    elnoa.setText(cursor.getString(3));
                                    soiltype.setText(cursor.getString(4));
                                    tis.setText(cursor.getString(5));
                                    elat.setText(cursor.getString(6));
                                    elon.setText(cursor.getString(7));
                                    landvillage.setText(cursor.getString(12));
                                    ui3 = cursor.getString(14);
                                    if (cursor.getString(13).equalsIgnoreCase("YES")) {
                                        radioButton1.setChecked(true);
                                        usp = "YES";
                                    } else {
                                        radioButton2.setChecked(true);
                                        usp = "NO";
                                    }
                                    ul = 1;
                                }
                            }
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
//                                    (MainActivity4.this, R.layout.spinnertext3, myList);
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
                            final ArrayList<String> myList = new ArrayList<String>();
                            myList.clear();
                            Cursor cursor2icv2 = dbs.query("masterl", new String[]{"out_master_description", "out_parent_code"
                                    }, "out_parent_code" + " LIKE ?",
                                    new String[]{"QCD_UN_VILLAGE"}, null, null, null, null);

                            if (cursor2icv2.moveToFirst()) {
                                do {
                                    myList.add(cursor2icv2.getString(0));


                                } while (cursor2icv2.moveToNext());
                            }

                            landvillage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                            (MainActivity4.this, R.layout.spinnertext3, myList);
                                    //Getting the instance of AutoCompleteTextView

                                    landvillage.setThreshold(0);//will start working from first character
                                    landvillage.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                                    landvillage.showDropDown();
                                    landvillage.requestFocus();
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

                if (pg == 4) {


                } else {
                    pg++;
                    if (pg == 1) {
                        if (sharedpreferences.getString("fid2", "").equalsIgnoreCase("")) {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                        } else {
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
                            Cursor cursor = dbs.query("bank", new String[]{"id", "type", "ano", "bank", "ifsc", "branch", "dc", "dd", "typec", "bc", "bid"
                                    }, "fid" + "=?",
                                    new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                            // looping through all rows and adding to list
                            Log.e("NULL", "" + cursor.getCount());
                            if (cursor.getCount() > 0) {
                                if (cursor.moveToLast()) {

                                    accountno.setText(cursor.getString(2));
                                    ebank.setText(cursor.getString(3));
                                    ebranch.setText(cursor.getString(5));
                                    eifsc.setText(cursor.getString(4));
                                    bankid = cursor.getString(0);
                                    bbid = cursor.getString(10);
                                    ub = 1;
                                    // bsave.setText("Update");
                                    bcode = cursor.getString(9);


                                }

                            }
                        }
                    }
                    if (pg == 2) {
                        if (sharedpreferences.getString("fid2", "").equalsIgnoreCase("")) {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                        } else {
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
                            Cursor cursor = dbs.query("kyc", new String[]{"id", "type", "subtype", "dno", "vtd", "tms", "stmc", "rid", "poto"
                                    }, "fid" + "=?",
                                    new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                            // looping through all rows and adding to list
                            Log.e("NULL", "" + cursor.getCount());
                            if (cursor.getCount() > 0) {
                                if (cursor.moveToLast()) {


                                    ksty = cursor.getString(5);
                                    kycid = cursor.getString(0);
                                    kycrid = cursor.getString(7);
                                    uk = 1;
                                    kttype.setText(cursor.getString(1));
                                    kstype.setText(cursor.getString(2));
                                    kty = cursor.getString(cursor.getColumnIndexOrThrow("tms"));
                                    ksty = cursor.getString(cursor.getColumnIndexOrThrow("stmc"));
                                    documentno.setText(cursor.getString(3));
                                    vtilldate.setText(cursor.getString(4));
                                    cdocumentno.setText(cursor.getString(3));

                                    ui2 = cursor.getString(8);
                                    if (cursor.getString(8).equalsIgnoreCase("0")) {
                                        encodedImage2 = "0";
                                        ui2 = "0";
                                    } else {
                                        try {

                                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(cursor.getString(8)));

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
                            }
                        }


                    }
                    if (pg == 4) {
                        if (sharedpreferences.getString("fid2", "").equalsIgnoreCase("")) {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                        } else {
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
                            Cursor cursor = dbs.query("sowing", new String[]{"id", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8", "v9", "v10", "v11", "v12", "v13", "v14", "v15", "v16", "v17", "n1", "n2", "n3", "n4", "n5"
                                    }, "v16" + "=?",
                                    new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                            // looping through all rows and adding to list
                            Log.e("NULL", "" + cursor.getCount());

                            if (cursor.getCount() > 0) {
                                if (cursor.moveToLast()) {


                                    us = 1;

                                    sowid = Integer.parseInt(cursor.getString(0));
                                    sowsn = cursor.getString(14);
                                    cyears.setText(cursor.getString(1));
                                    ccty2s.setText(cursor.getString(18));
                                    codes = cursor.getString(2);
                                    ccts.setText(cursor.getString(21));
                                    codecn = cursor.getString(3);
                                    cvars.setText(cursor.getString(4));
                                    sdt1.setText(cursor.getString(5));
                                    sdt2.setText(cursor.getString(6));
                                    sdt3.setText(cursor.getString(7));
                                    sdt4.setText(cursor.getString(8));
                                    sdt5.setText(cursor.getString(9));
                                    stmnt.setText(cursor.getString(19));
                                    codem = cursor.getString(10);
                                    stclf.setText(cursor.getString(20));
                                    codecr = cursor.getString(11);

                                    stsnm.setText(cursor.getString(22));
                                    codesn = cursor.getString(13);
                                    arraysc.clear();
                                    arraysn.clear();
                                    arraymn.clear();
                                    arraymc.clear();
                                    arraycrc.clear();
                                    arraycrn.clear();
                                    arraycnc.clear();
                                    arraycnn.clear();
                                    arrayvc.clear();
                                    arrayvn.clear();
                                    arraysec.clear();
                                    arraysen.clear();


                                    Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                            }, "out_depend_code" + "=?",
                                            new String[]{codes}, null, null, null, null);


                                    if (cursor2mst.moveToFirst()) {

                                        do {
                                            if (cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_MONTHS")) {


                                                arraymc.add(cursor2mst.getString(0));
                                                arraymn.add(cursor2mst.getString(1));
                                                if (cursor2mst.getString(1).equalsIgnoreCase("January")) {
                                                    array3.add("01");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("February")) {
                                                    array3.add("02");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("March")) {
                                                    array3.add("03");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("April")) {
                                                    array3.add("04");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("May")) {
                                                    array3.add("05");
                                                } else if (cursor2mst.getString(1).replaceAll(" ", "").equalsIgnoreCase("June")) {
                                                    array3.add("06");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("July")) {
                                                    array3.add("07");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("August")) {
                                                    array3.add("08");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("September")) {
                                                    array3.add("09");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("October")) {
                                                    array3.add("10");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("November")) {
                                                    array3.add("11");
                                                } else if (cursor2mst.getString(1).equalsIgnoreCase("December")) {
                                                    array3.add("12");
                                                }
                                                Log.e("MyAndroidClass", Arrays.toString(new ArrayList[]{array3}));
                                                Log.e("MyAndroidClass2", cursor2mst.getString(1));
                                            }
                                            if (cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_CROPCLASS")) {
                                                Log.e("VALCHK", "" + cursor2mst.getString(0));

                                                arraycrc.add(cursor2mst.getString(0));
                                                arraycrn.add(cursor2mst.getString(1));
                                            }
                                        } while (cursor2mst.moveToNext());


//                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                                                (MainActivity4.this, R.layout.spinnertext3, arraymn);
//                                        //Getting the instance of AutoCompleteTextView
//
//                                        stmnt.setThreshold(0);//will start working from first character
//                                        stmnt.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                                        stmnt.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//                                                stmnt.showDropDown();
//                                                stmnt.requestFocus();
//                                            }
//                                        });
                                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                                                (MainActivity4.this, R.layout.spinnertext3, arraycrn);
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

                                    Cursor cursor2mst1 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                            }, "out_depend_code" + "=?",
                                            new String[]{codecr}, null, null, null, null);


                                    if (cursor2mst1.moveToFirst()) {

                                        do {
                                            if (cursor2mst1.getString(2).equalsIgnoreCase("QCD_SOW_CROPNAME")) {


                                                arraycnc.add(cursor2mst1.getString(0));
                                                arraycnn.add(cursor2mst1.getString(1));
                                            }

                                        } while (cursor2mst1.moveToNext());


                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                                (MainActivity4.this, R.layout.spinnertext3, arraycnn);
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
                                    Cursor cursor2mst2 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                            }, "out_depend_code" + "=?",
                                            new String[]{codecn}, null, null, null, null);


                                    if (cursor2mst2.moveToFirst()) {

                                        do {
                                            if (cursor2mst2.getString(2).equalsIgnoreCase("QCD_SOW_CROPVARIETY")) {


                                                arrayvc.add(cursor2mst2.getString(0));
                                                arrayvn.add(cursor2mst2.getString(1));
                                            }

                                        } while (cursor2mst2.moveToNext());

                                        for (int i = 0; i < arrayvc.size(); i++) {
                                            // Log.e("VALCHK",""+position);


                                            // ccts.setText("Tap For Select Crop Name");


                                            Cursor cursor2mst32 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                                    }, "out_depend_code" + "=?",
                                                    new String[]{arrayvc.get(i)}, null, null, null, null);


                                            if (cursor2mst32.moveToFirst()) {

                                                do {
                                                    if (cursor2mst32.getString(2).equalsIgnoreCase("QCD_SOW_SEEDNAME")) {


                                                        arraysec.add(cursor2mst32.getString(0));
                                                        arraysen.add(cursor2mst32.getString(1));
                                                    }

                                                } while (cursor2mst32.moveToNext());


                                                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                                        (MainActivity4.this, R.layout.spinnertext3, arraysen);
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
                                                (MainActivity4.this, R.layout.spinnertext3, arrayvn);
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


                                    Cursor cursor2mst4 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description","out_depend_code"
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
                                        } while (cursor2mst4.moveToNext());


                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                                (MainActivity4.this, R.layout.spinnertext3, arraysn);
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


                                }
                            }

                        }
                    }
                    if (pg == 0) {
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
                    if (pg == 3) {
                        if (sharedpreferences.getString("fid2", "").equalsIgnoreCase("")) {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                        } else {
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
                            Cursor cursor = dbs.query("land", new String[]{"id", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8", "v9", "v10", "v11", "v12", "v13", "v14"
                                    }, "v10" + "=?",
                                    new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                            // looping through all rows and adding to list
                            Log.e("NULL", "" + cursor.getCount());

                            if (cursor.getCount() > 0) {
                                if (cursor.moveToLast()) {


                                    laid = cursor.getString(0);
                                    laslno = cursor.getString(8);
                                    tlt.setText(cursor.getString(1));
                                    tow.setText(cursor.getString(2));
                                    elnoa.setText(cursor.getString(3));
                                    soiltype.setText(cursor.getString(4));
                                    tis.setText(cursor.getString(5));
                                    elat.setText(cursor.getString(6));
                                    elon.setText(cursor.getString(7));
                                    landvillage.setText(cursor.getString(12));
                                    ui3 = cursor.getString(14);
                                    if (cursor.getString(13).equalsIgnoreCase("YES")) {
                                        radioButton1.setChecked(true);
                                        usp = "YES";
                                    } else {
                                        radioButton2.setChecked(true);
                                        usp = "NO";
                                    }
                                    ul = 1;
                                }
                            }
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
//                                    (MainActivity4.this, R.layout.spinnertext3, myList);
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
                            final ArrayList<String> myList = new ArrayList<String>();
                            myList.clear();
                            Cursor cursor2icv2 = dbs.query("masterl", new String[]{"out_master_description", "out_parent_code"
                                    }, "out_parent_code" + " LIKE ?",
                                    new String[]{"QCD_UN_VILLAGE"}, null, null, null, null);

                            if (cursor2icv2.moveToFirst()) {
                                do {
                                    myList.add(cursor2icv2.getString(0));


                                } while (cursor2icv2.moveToNext());
                            }

                            landvillage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                            (MainActivity4.this, R.layout.spinnertext3, myList);
                                    //Getting the instance of AutoCompleteTextView

                                    landvillage.setThreshold(0);//will start working from first character
                                    landvillage.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
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
                pg = 0;
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
                pg = 1;
                im1.setBackgroundResource(R.drawable.sheader);
                im2.setBackgroundResource(R.drawable.sbank2);
                im3.setBackgroundResource(R.drawable.skyc);
                im4.setBackgroundResource(R.drawable.sscd2);
                im5.setBackgroundResource(R.drawable.sland);
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.VISIBLE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.VISIBLE);
                v3.setVisibility(View.GONE);
                v4.setVisibility(View.GONE);
                v5.setVisibility(View.GONE);
                txtheader.setText("Bank");

                Cursor cursor = dbs.query("bank", new String[]{"id", "type", "ano", "bank", "ifsc", "branch", "dc", "dd", "typec", "bc", "bid"
                        }, "fid" + "=?",
                        new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                // looping through all rows and adding to list
                Log.e("NULL", "" + cursor.getCount());
                if (cursor.getCount() > 0) {
                    if (cursor.moveToLast()) {

                        accountno.setText(cursor.getString(2));
                        ebank.setText(cursor.getString(3));
                        ebranch.setText(cursor.getString(5));
                        eifsc.setText(cursor.getString(4));
                        bankid = cursor.getString(0);
                        bbid = cursor.getString(10);
                        ub = 1;
                        // bsave.setText("Update");
                        bcode = cursor.getString(9);


                    }

                }

            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg = 2;
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
                Cursor cursor = dbs.query("kyc", new String[]{"id", "type", "subtype", "dno", "vtd", "tms", "stmc", "rid", "poto"
                        }, "fid" + "=?",
                        new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                // looping through all rows and adding to list
                Log.e("NULL", "" + cursor.getCount());
                if (cursor.getCount() > 0) {
                    if (cursor.moveToLast()) {


                        ksty = cursor.getString(5);
                        kycid = cursor.getString(0);
                        kycrid = cursor.getString(7);
                        uk = 1;
                        kttype.setText(cursor.getString(1));
                        kstype.setText(cursor.getString(2));
                        kty = cursor.getString(cursor.getColumnIndexOrThrow("tms"));
                        ksty = cursor.getString(cursor.getColumnIndexOrThrow("stmc"));
                        documentno.setText(cursor.getString(3));
                        vtilldate.setText(cursor.getString(4));
                        cdocumentno.setText(cursor.getString(3));

                        ui2 = cursor.getString(8);
                        if (cursor.getString(8).equalsIgnoreCase("0")) {
                            encodedImage2 = "0";
                            ui2 = "0";
                        } else {
                            try {

                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(cursor.getString(8)));

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
                }

            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg = 3;
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
                Cursor cursor = dbs.query("sowing", new String[]{"id", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8", "v9", "v10", "v11", "v12", "v13", "v14", "v15", "v16", "v17", "n1", "n2", "n3", "n4", "n5"
                        }, "v16" + "=?",
                        new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                // looping through all rows and adding to list
                Log.e("NULL", "" + cursor.getCount());
                array4.clear();

                if (cursor.getCount() > 0) {
                    if (cursor.moveToLast()) {


                        us = 1;

                        sowid = Integer.parseInt(cursor.getString(0));
                        sowsn = cursor.getString(14);
                        cyears.setText(cursor.getString(1));
                        array4.add(cursor.getString(1));
                        ccty2s.setText(cursor.getString(18));
                        codes = cursor.getString(2);
                        ccts.setText(cursor.getString(21));
                        codecn = cursor.getString(3);
                        cvars.setText(cursor.getString(4));
                        sdt1.setText(cursor.getString(5));
                        sdt2.setText(cursor.getString(6));
                        sdt3.setText(cursor.getString(7));
                        sdt4.setText(cursor.getString(8));
                        sdt5.setText(cursor.getString(9));
                        stmnt.setText(cursor.getString(19));
                        codem = cursor.getString(10);
                        stclf.setText(cursor.getString(20));
                        codecr = cursor.getString(11);

                        stsnm.setText(cursor.getString(22));
                        codesn = cursor.getString(13);
                        arraysc.clear();
                        arraysn.clear();
                        arraymn.clear();
                        arraymc.clear();
                        arraycrc.clear();
                        arraycrn.clear();
                        arraycnc.clear();
                        arraycnn.clear();
                        arrayvc.clear();
                        arrayvn.clear();
                        arraysec.clear();
                        arraysen.clear();
                        Cursor cursor2 = dbs.query("tablist", new String[]{"field"
                                }, "tab" + "=?",
                                new String[]{"Sowing Details"}, null, null, null, null);


                        // Cursor cursor2 = dbs.rawQuery(selectQuery, null);

                        Log.e("RESNEW", "" + cursor2.getCount());


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
                                            Log.e("YEAR", "" + Arrays.toString(tfs));
                                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                                    (MainActivity4.this, R.layout.spinnertext3, tfs);
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


                        Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                }, "out_depend_code" + "=?",
                                new String[]{codes}, null, null, null, null);


                        if (cursor2mst.moveToFirst()) {

                            do {
                                if (cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_MONTHS")) {


                                    arraymc.add(cursor2mst.getString(0));
                                    arraymn.add(cursor2mst.getString(1));
                                    if (cursor2mst.getString(1).equalsIgnoreCase("January")) {
                                        array3.add("01");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("February")) {
                                        array3.add("02");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("March")) {
                                        array3.add("03");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("April")) {
                                        array3.add("04");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("May")) {
                                        array3.add("05");
                                    } else if (cursor2mst.getString(1).replaceAll(" ", "").equalsIgnoreCase("June")) {
                                        array3.add("06");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("July")) {
                                        array3.add("07");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("August")) {
                                        array3.add("08");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("September")) {
                                        array3.add("09");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("October")) {
                                        array3.add("10");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("November")) {
                                        array3.add("11");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("December")) {
                                        array3.add("12");
                                    }
                                    Log.e("MyAndroidClass", Arrays.toString(new ArrayList[]{array3}));
                                    Log.e("MyAndroidClass2", cursor2mst.getString(1));
                                }
                                if (cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_CROPCLASS")) {
                                    Log.e("VALCHK", "" + cursor2mst.getString(0));

                                    arraycrc.add(cursor2mst.getString(0));
                                    arraycrn.add(cursor2mst.getString(1));
                                }
                            } while (cursor2mst.moveToNext());


//                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                                (MainActivity4.this, R.layout.spinnertext3, arraymn);
//                        //Getting the instance of AutoCompleteTextView
//
//                        stmnt.setThreshold(0);//will start working from first character
//                        stmnt.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                        stmnt.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                stmnt.showDropDown();
//                                stmnt.requestFocus();
//                            }
//                        });
                            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                                    (MainActivity4.this, R.layout.spinnertext3, arraycrn);
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

                        Cursor cursor2mst1 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                }, "out_depend_code" + "=?",
                                new String[]{codecr}, null, null, null, null);


                        if (cursor2mst1.moveToFirst()) {

                            do {
                                if (cursor2mst1.getString(2).equalsIgnoreCase("QCD_SOW_CROPNAME")) {


                                    arraycnc.add(cursor2mst1.getString(0));
                                    arraycnn.add(cursor2mst1.getString(1));
                                }

                            } while (cursor2mst1.moveToNext());


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (MainActivity4.this, R.layout.spinnertext3, arraycnn);
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
                        Cursor cursor2mst2 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                }, "out_depend_code" + "=?",
                                new String[]{codecn}, null, null, null, null);


                        if (cursor2mst2.moveToFirst()) {

                            do {
                                if (cursor2mst2.getString(2).equalsIgnoreCase("QCD_SOW_CROPVARIETY")) {


                                    arrayvc.add(cursor2mst2.getString(0));
                                    arrayvn.add(cursor2mst2.getString(1));
                                }

                            } while (cursor2mst2.moveToNext());

                            for (int i = 0; i < arrayvc.size(); i++) {
                                // Log.e("VALCHK",""+position);


                                // ccts.setText("Tap For Select Crop Name");


                                Cursor cursor2mst32 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                        }, "out_depend_code" + "=?",
                                        new String[]{arrayvc.get(i)}, null, null, null, null);


                                if (cursor2mst32.moveToFirst()) {

                                    do {
                                        if (cursor2mst32.getString(2).equalsIgnoreCase("QCD_SOW_SEEDNAME")) {


                                            arraysec.add(cursor2mst32.getString(0));
                                            arraysen.add(cursor2mst32.getString(1));
                                        }

                                    } while (cursor2mst32.moveToNext());


                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                            (MainActivity4.this, R.layout.spinnertext3, arraysen);
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
                                    (MainActivity4.this, R.layout.spinnertext3, arrayvn);
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


                        Cursor cursor2mst4 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description","out_depend_code"
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
                            } while (cursor2mst4.moveToNext());


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (MainActivity4.this, R.layout.spinnertext3, arraysn);
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


                    }
                }


            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg = 4;
                im1.setBackgroundResource(R.drawable.sheader);
                im2.setBackgroundResource(R.drawable.sbank);
                im3.setBackgroundResource(R.drawable.skyc);
                im4.setBackgroundResource(R.drawable.sscd2);
                im5.setBackgroundResource(R.drawable.sland2);
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.VISIBLE);
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);
                v4.setVisibility(View.GONE);
                v5.setVisibility(View.VISIBLE);

                Cursor cursor = dbs.query("land", new String[]{"id", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8", "v9", "v10", "v11", "v12", "v13", "v14"
                        }, "v10" + "=?",
                        new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);

                // looping through all rows and adding to list
                Log.e("NULL", "" + cursor.getCount());

                if (cursor.getCount() > 0) {
                    if (cursor.moveToLast()) {


                        laid = cursor.getString(0);
                        laslno = cursor.getString(8);
                        tlt.setText(cursor.getString(1));
                        tow.setText(cursor.getString(2));
                        elnoa.setText(cursor.getString(3));
                        soiltype.setText(cursor.getString(4));
                        tis.setText(cursor.getString(5));
                        elat.setText(cursor.getString(6));
                        elon.setText(cursor.getString(7));
                        landvillage.setText(cursor.getString(12));
                        ui3 = cursor.getString(14);
                        if (cursor.getString(13).equalsIgnoreCase("YES")) {
                            radioButton1.setChecked(true);
                            usp = "YES";
                        } else {
                            radioButton2.setChecked(true);
                            usp = "NO";
                        }
                        ul = 1;
                    }
                }


                txtheader.setText("Land Details");

//                Cursor cursor2ic = dbs.query("masterl", new String[]{"out_master_code"
//                        }, "out_depend_code" + " LIKE ?",
//                        new String[]{"%"+mt+"%"}, null, null, null, null);
//                String[] tfs3;
//                Log.e("MST0",""+mt);
//                ArrayList<String> myList = new ArrayList<String>();
//                Log.e("MSTl",""+cursor2ic.getCount());
//                if(cursor2ic.moveToFirst())
//                {
//                    do
//                    {
//                        Log.e("MST",""+cursor2ic.getString(0));
//                        Cursor cursor2icv = dbs.query("masterl", new String[]{"out_master_code"
//                                }, "out_depend_code" + " LIKE ?",
//                                new String[]{"%"+cursor2ic.getString(0)+"%"}, null, null, null, null);
//
//                        if(cursor2icv.moveToFirst()) {
//                            do {
//                                Log.e("MST22",""+cursor2icv.getString(0));
//                                Cursor cursor2icv2 = dbs.query("masterl", new String[]{"out_master_description"
//                                        }, "out_master_code" + " LIKE ?",
//                                        new String[]{"%"+cursor2icv.getString(0)+"%"}, null, null, null, null);
//
//                                if(cursor2icv2.moveToFirst()) {
//                                    do {
//                                        myList.add(cursor2icv2.getString(0));
//
//
//                                    } while (cursor2icv2.moveToNext());
//                                }
//
//                            } while (cursor2icv.moveToNext());
//                        }
//
//                    }while(cursor2ic.moveToNext());
//                }
//                tfs3 = new String[cursor2ic.getCount()];
//
//                int icc = 0;
//
//                // create an empty array;
//
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                        (MainActivity4.this, R.layout.spinnertext3, myList);
//                //Getting the instance of AutoCompleteTextView
//
//                landvillage.setThreshold(0);//will start working from first character
//                landvillage.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                landvillage.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        landvillage.showDropDown();
//                        landvillage.requestFocus();
//                    }
//                });
                final ArrayList<String> myList = new ArrayList<String>();
                myList.clear();
                Cursor cursor2icv2 = dbs.query("masterl", new String[]{"out_master_description", "out_parent_code"
                        }, "out_parent_code" + " LIKE ?",
                        new String[]{"QCD_UN_VILLAGE"}, null, null, null, null);

                if (cursor2icv2.moveToFirst()) {
                    do {
                        myList.add(cursor2icv2.getString(0));


                    } while (cursor2icv2.moveToNext());
                }

                landvillage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (MainActivity4.this, R.layout.spinnertext3, myList);
                        //Getting the instance of AutoCompleteTextView

                        landvillage.setThreshold(0);//will start working from first character
                        landvillage.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//                        landvillage.showDropDown();
//                        landvillage.requestFocus();
                        landsearchpopupwindow();
                    }
                });

            }
        });

        searchfarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(MainActivity4.this);
                dialog.setContentView(R.layout.suppliersearch2);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                far_name = "";
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                dialog.getWindow().setLayout(width, height);
                dialogtext = dialog.findViewById(R.id.dialogtext);
                dialogtext.setText("Farmer Search");
                RadioGroup radioGroups = dialog.findViewById(R.id.radioGroup);
                RadioGroup radioGroup2 = dialog.findViewById(R.id.radioGroup2);
                RadioButton radioButton1 = dialog.findViewById(R.id.radioButton1);
                RadioButton radioButton2 = dialog.findViewById(R.id.radioButton2);
                RadioButton radioButton3 = dialog.findViewById(R.id.radioButton3);
                RadioButton radioButton4 = dialog.findViewById(R.id.radioButton4);
                LinearLayout linearradiob = dialog.findViewById(R.id.linearradiob);
                elc = (AutoCompleteTextView) dialog.findViewById(R.id.elc);
                TextView orlbl = dialog.findViewById(R.id.orlbl);
                final AutoCompleteTextView elc2 = (AutoCompleteTextView) dialog.findViewById(R.id.elc2);
                final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity4.this));
                adapterf = new MainActivity4.MyRecyclerViewAdapterf(MainActivity4.this, pojofarlist);
                orlbl.setVisibility(View.VISIBLE);
                linearradiob.setVisibility(View.VISIBLE);
                elc.setHint("Search Farmer Name");
                elc2.setVisibility(View.VISIBLE);
                elc2.setInputType(InputType.TYPE_CLASS_NUMBER);
                arrayn.clear();
                farmer_filter_flag = "All";
                farmer_searchlist(farmer_filter_flag);
                recyclerView.setAdapter(adapterf);
                radioButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        farmer_filter_flag = "All";
                        farmer_searchlist(farmer_filter_flag);
                        recyclerView.setAdapter(adapterf);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(false);
                        radioButton4.setChecked(false);
                    }
                });
                radioButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        farmer_filter_flag = "All";
                        farmer_searchlist(farmer_filter_flag);
                        recyclerView.setAdapter(adapterf);
                        radioButton1.setChecked(false);
                        radioButton3.setChecked(false);
                        radioButton4.setChecked(false);
                    }
                });
                radioButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        farmer_filter_flag = "N";
                        farmer_searchlist(farmer_filter_flag);
                        recyclerView.setAdapter(adapterf);
                        radioButton2.setChecked(false);
                        radioButton1.setChecked(false);
                        radioButton4.setChecked(false);
                    }
                });
                radioButton4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        farmer_filter_flag = "YY";
                        farmer_searchlist(farmer_filter_flag);
                        recyclerView.setAdapter(adapterf);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(false);
                        radioButton1.setChecked(false);
                    }
                });
//                radioGroups.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                         try {
//                           //  radioGroup2.clearCheck();
//                             RadioButton checkedbutton = (RadioButton) radioGroup.findViewById(i);
//                             String rbutton = getResources().getResourceEntryName(checkedbutton.getId());
//                             Log.e("button name", rbutton);
//
//                             if (rbutton.equalsIgnoreCase("radiobutton1")) {
//
//                                 radioButton1.setChecked(true);
//
//
//                             } else if (rbutton.equalsIgnoreCase("radiobutton2")) {
//                                 farmer_filter_flag = "Y";
//                                 radioButton2.setChecked(true);
//                                // radioGroup2.clearCheck();
//
//                             }
//
//
//
//                             if(radioButton3.isChecked())
//                             {
//                                 radioButton3.setChecked(false);
//                             }
//                             if(radioButton4.isChecked())
//                             {
//                                 radioButton4.setChecked(false);
//                             }
//                         }
//                         catch (Exception e)
//                         {
//
//                         }
//
//                    }
//                });
//
//
//                radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                        try {
//                            //radioGroups.clearCheck();
//                            RadioButton checkedbutton = (RadioButton) radioGroup.findViewById(i);
//                            String rbutton = getResources().getResourceEntryName(checkedbutton.getId());
//                            Log.e("button name", rbutton);
//                            if (rbutton.equalsIgnoreCase("radiobutton3")) {
//                                farmer_filter_flag = "N";
//                                radioButton3.setChecked(true);
//
//
//                            } else if (rbutton.equalsIgnoreCase("radiobutton4")) {
//                                farmer_filter_flag = "YY";
//                                radioButton4.setChecked(true);
//                               // radioGroups.clearCheck();
//
//                            }
//
//                            farmer_searchlist(farmer_filter_flag);
//                            recyclerView.setAdapter(adapterf);
//                            if(radioButton1.isChecked())
//                            {
//                                radioButton1.setChecked(false);
//                            }
//                            if(radioButton2.isChecked())
//                            {
//                                radioButton2.setChecked(false);
//                            }
//                        }
//                        catch (Exception e)
//                        {
//
//                        }
//
//                    }
//                });
                radioButton1.setChecked(true);
                String selectQuery5 = "";
                if (farmer_filter_flag.equalsIgnoreCase("ALL")) {
                    selectQuery5 = "SELECT  * FROM farlist where fpoorgn_code = '" + sharedpreferences.getString("oc1", "") + "'";
                } else {
                    selectQuery5 = "SELECT  * FROM farlist where v3 = '" + farmer_filter_flag + "' and fpoorgn_code = '" + sharedpreferences.getString("oc1", "") + "'";
                }


                Cursor cc = dbs.rawQuery(selectQuery5, null);
                Log.e("NULL", "" + cc.getCount());
                arraymobileno = new ArrayList<>();
                if (cc.getCount() != 0) {
                    if (cc.moveToFirst()) {
                        do {

                            if (arrayn.contains(cc.getString(3).trim())) {

                            } else {
                                arrayn.add(cc.getString(3).trim());
                                arraymobileno.add(cc.getString(cc.getColumnIndexOrThrow("v2")));
                            }
                            // Log.e("VAL",""+cursor.getString(11));

                        } while (cc.moveToNext());
                    }
                }


                ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(MainActivity4.this,
                        R.layout.spinnertext, arrayn);
                ArrayAdapter<String> mobilesearchadapter = new ArrayAdapter<>(MainActivity4.this,
                        R.layout.spinnertext, arraymobileno);
                elc2.setAdapter(mobilesearchadapter);
                elc2.setThreshold(0);
                elc.setAdapter(adapterlist2n);
                elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int pos = arrayn.indexOf(elc.getText().toString());
                        if (pos >= 0) {
                            far_name = arrayn.get(pos);
                        }
                        far_mobile = "";
                        farmer_searchlist(farmer_filter_flag);
                        recyclerView.setAdapter(adapterf);
                    }
                });
                elc2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        int pos = arraymobileno.indexOf(elc2.getText().toString());
                        if (pos >= 0) {
                            far_mobile = arraymobileno.get(pos);
                            far_name = "";
                            farmer_searchlist(farmer_filter_flag);
                            recyclerView.setAdapter(adapterf);
                        }
                    }
                });

                ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        far_name = "";
//                        finish();
//                        startActivity(getIntent());
                    }
                });

                dialog.show();
            }
        });
        if (sharedpreferences.getString("frm", "").equalsIgnoreCase("2")) {
            try {

                if (isNetworkAvailable()) {

                    String selectQuery55 = "SELECT  * FROM farmerh where flag = 0";
                    Cursor ccc = dbs.rawQuery(selectQuery55, null);

                    if (ccc.getCount() == 0) {

                    } else {
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Please Do The Transaction FDR To Continue")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //set what would happen when positive button is clicked
                                                finish();
                                                //rlsno=1;
                                            }
                                        }
//set negative button

                                )
                                .show();
                    }


                    String selectQuery5 = "SELECT  * FROM farmerh where faid =" + sharedpreferences.getString("farmer1", "");
                    Cursor cc = dbs.rawQuery(selectQuery5, null);
                    if (cc.getCount() != 0) {
                        if (cc.moveToFirst()) {

                            farmerid = cc.getString(0);

                            searchfarmer.setText(cc.getString(1));
                            farmername.setText(cc.getString(1));
                            surname.setText(cc.getString(2));
                            fathername.setText(cc.getString(3));
                            mobileno.setText(cc.getString(4));
                            gender.setText(cc.getString(5));
                            pincode.setText(cc.getString(8));
                            edob.setText(cc.getString(6));
                            eaddress.setText(cc.getString(9));
                            village.setText(cc.getString(10));
                            grama.setText(cc.getString(11));
                            taluk.setText(cc.getString(12));
                            district.setText(cc.getString(13));
                            hamlet.setText(cc.getString(23));
                            ui = cc.getString(24);

                            if (ui.equalsIgnoreCase("0")) {
                                encodedImage = "0";
                                ui = "0";
                            } else if (ui.equalsIgnoreCase("1")) {
                                captureh.setBackgroundResource(R.drawable.captured);
                                ui = "1";
                            } else {
                                try {

                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(ui));

                                    byteArrayOutputStream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                                    //Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());


                                    encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                                    captureh.setImageResource(0);
                                    captureh.setImageBitmap(bitmap);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            modeflag = "U";
                            faid = Integer.parseInt(sharedpreferences.getString("farmer1", ""));
                            fan = sharedpreferences.getString("farmer2", "");

                            SharedPreferences.Editor editor = sharedpreferences.edit();

                            editor.putString("fcode2", fan);
                            editor.putString("fid2", String.valueOf(faid));


                            editor.commit();
                            mv = cc.getString(18);
                            mg = cc.getString(19);
                            mt = cc.getString(20);
                            md = cc.getString(21);
                            ham = "QCD_UNH_" + cc.getString(23);
//                            if (cc.getString(5).equalsIgnoreCase("male")) {
//                                gf = "QCD_GENDER_MALE";
//
//                            }
//                            if (cc.getString(5).equalsIgnoreCase("female")) {
//
//                                gf = "QCD_GENDER_FEMALE";
//                            }
//                            if (cc.getString(5).equalsIgnoreCase("transgender")) {
//
//                                gf = "QCD_GENDER_TRANSGENDER";
//                            }


                        }
                    }

                }
            } catch (Exception e) {

            }

        }
        ksave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedpreferences.getString("fid2", "").equalsIgnoreCase("")) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                } else {
                    addkyc();
                }
            }
        });

        bsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedpreferences.getString("fid2", "").equalsIgnoreCase("")) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                } else {
                    addbank();
                }
            }
        });


        eifsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                eifsc.setEnabled(false);
                dialog = new Dialog(MainActivity4.this);
                dialog.setContentView(R.layout.suppliersearch3);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity4.this));
                adapterb = new MainActivity4.MyRecyclerViewAdapterb(MainActivity4.this, pojobankList);
                dialog.getWindow().setLayout(width, height);
                dialogtext = dialog.findViewById(R.id.dialogtext);
                dialogtext.setText("IFSC Search");
                arrayn.clear();
                elc = (AutoCompleteTextView) dialog.findViewById(R.id.elc);
                String selectQuery5 = "SELECT  * FROM bankm";
                Cursor cc = dbs.rawQuery(selectQuery5, null);
                if (cc.getCount() != 0) {
                    if (cc.moveToFirst()) {
                        do {

                            if (arrayn.contains(cc.getString(5))) {

                            } else {
                                arrayn.add(cc.getString(5));
                            }
                            // Log.e("VAL",""+cursor.getString(11));

                        } while (cc.moveToNext());
                    }
                }


                ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(MainActivity4.this,
                        R.layout.spinnertext, arrayn);

                elc.setAdapter(adapterlist2n);

                elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        pojobankList.clear();
                        // elc.setText(""+(String) parent.getItemAtPosition(position));

                        Cursor cursor = dbs.query("bankm", new String[]{"bank_code", "bank_name", "branch_name", "ifsc_code"
                                }, "ifsc_code" + "=? COLLATE NOCASE",
                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
                        if (cursor.getCount() != 0) {
                            if (cursor.moveToFirst()) {
                                do {

                                    Pojobank pojobank = new Pojobank();

                                    pojobank.setBc(cursor.getString(0));
                                    pojobank.setBn(cursor.getString(1));
                                    pojobank.setBrn(cursor.getString(2));
                                    pojobank.setIfsc(cursor.getString(3));

                                    String dgp = cursor.getString(2);

                                    Log.e("NULL", "" + dgp);
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
                dialog = new Dialog(MainActivity4.this);
                dialog.setContentView(R.layout.suppliersearch2);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity4.this));
                adapterd = new MainActivity4.MyRecyclerViewAdapterd(MainActivity4.this, pojolocList);
                dialog.getWindow().setLayout(width, height);
                dialogtext = dialog.findViewById(R.id.dialogtext);
                dialogtext.setText("Village Search");

                elc = (AutoCompleteTextView) dialog.findViewById(R.id.elc);
                arrayn.clear();
                Cursor cc = dbs.query("masterl", new String[]{"out_master_description"
                        }, "out_parent_code" + "=?",
                        new String[]{"QCD_UN_VILLAGE"}, null, null, null, null);
                if (cc.getCount() != 0) {
                    if (cc.moveToFirst()) {
                        do {

                            if (arrayn.contains(cc.getString(0).trim())) {

                            } else {
                                arrayn.add(cc.getString(0));
                            }
                            // Log.e("VAL",""+cursor.getString(11));

                        } while (cc.moveToNext());
                    }
                }


                ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(MainActivity4.this,
                        R.layout.spinnertext, arrayn);

                elc.setAdapter(adapterlist2n);

                elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


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
                        Cursor cursor = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_depend_code", "out_parent_code"
                                }, "out_master_description" + " LIKE ?",
                                new String[]{"%" + (String) parent.getItemAtPosition(position) + "%"}, null, null, null, null);
                        if (cursor.getCount() != 0) {
                            if (cursor.moveToFirst()) {
                                do {

                                    if (cursor.getString(3).equalsIgnoreCase("QCD_UN_VILLAGE")) {
                                        Pojoloc pojoloc = new Pojoloc();
                                        pojoloc.setS1(cursor.getString(0));
                                        pojoloc.setS2(cursor.getString(1));

                                        String dgp = cursor.getString(2);

                                        Cursor cc = dbs.query("masterl", new String[]{"out_master_description", "out_master_code"
                                                }, "out_depend_code" + "=?",
                                                new String[]{pojoloc.getS1()}, null, null, null, null);
                                        if (cc.getCount() != 0) {
                                            if (cc.moveToFirst()) {
                                                // do {

                                                pojoloc.setS9(cc.getString(1));
                                                pojoloc.setS10(cc.getString(0));


                                                //  } while (cc.moveToNext());
                                            }
                                        }

                                        Log.e("NULL", "" + dgp);
                                        // pojoloc.setS3(cursor.getString(1));
                                        Cursor cursorg = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_depend_code"
                                                }, "out_master_code" + "=? COLLATE NOCASE",
                                                new String[]{dgp}, null, null, null, null);
                                        if (cursorg.getCount() != 0) {
                                            if (cursorg.moveToFirst()) {
                                                // do {


                                                pojoloc.setS3(cursorg.getString(0));
                                                pojoloc.setS4(cursorg.getString(1));
                                                String dt = cursorg.getString(2);

                                                // pojoloc.setS3(cursor.getString(1));
                                                Cursor cursort = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_depend_code"
                                                        }, "out_master_code" + "=? COLLATE NOCASE",
                                                        new String[]{dt}, null, null, null, null);
                                                if (cursort.getCount() != 0) {
                                                    if (cursort.moveToFirst()) {
                                                        //  do {


                                                        pojoloc.setS5(cursort.getString(0));
                                                        pojoloc.setS6(cursort.getString(1));
                                                        String dd = cursort.getString(2);
                                                        Log.e("NULL", "" + dd);


                                                        // pojoloc.setS3(cursor.getString(1));


                                                        Cursor cursord = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_depend_code"
                                                                }, "out_master_code" + "=? COLLATE NOCASE",
                                                                new String[]{dd}, null, null, null, null);
                                                        if (cursord.getCount() != 0) {
                                                            if (cursord.moveToFirst()) {
                                                                // do {


                                                                pojoloc.setS7(cursord.getString(0));
                                                                pojoloc.setS8(cursord.getString(1));


                                                                // pojoloc.setS3(cursor.getString(1));


                                                                Log.e("LLOOCC", "" + cursord.getString(2) + "//" + sharedpreferences.getString("lo", ""));
                                                                if (cursord.getString(2).equalsIgnoreCase(sharedpreferences.getString("lo", ""))) {
                                                                    pojolocList.add(pojoloc);
                                                                } else {
                                                                    Toast.makeText(MainActivity4.this, "Selected Village Not Belongs To Selected Village", Toast.LENGTH_SHORT).show();
                                                                }
                                                                // array2.add(cursor.getString(11));
                                                                // Log.e("VAL",""+cursor.getString(11));
                                                                // recyclerView.setAdapter(adapterd);
                                                                // Log.e("VAL",""+cursor.getString(11));

                                                                // } while (cursord.moveToNext());
                                                            }


                                                        }
                                                        // array2.add(cursor.getString(11));
                                                        // Log.e("VAL",""+cursor.getString(11));
                                                        // recyclerView.setAdapter(adapterd);
                                                        // Log.e("VAL",""+cursor.getString(11));

                                                        // } while (cursort.moveToNext());
                                                    }


                                                }

                                                // pojolocList.add(pojoloc);
                                                // array2.add(cursor.getString(11));
                                                // Log.e("VAL",""+cursor.getString(11));
                                                // recyclerView.setAdapter(adapterd);
                                                // Log.e("VAL",""+cursor.getString(11));

                                                //  } while (cursorg.moveToNext());
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
                if (farmername.getText().toString().equalsIgnoreCase("")) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
//                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                else if (fathername.getText().toString().equalsIgnoreCase("")) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                } else if (mobileno.getText().toString().equalsIgnoreCase("") || mobileno.getText().toString().length() < 10) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                } else if (gender.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                } else if (edob.getText().toString().equalsIgnoreCase("")) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
//                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                else if (pincode.getText().toString().equalsIgnoreCase("") || pincode.getText().toString().length() < 6) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                } else if (village.getText().toString().equalsIgnoreCase("")) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                } else if (grama.getText().toString().equalsIgnoreCase("")) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                } else if (taluk.getText().toString().equalsIgnoreCase("")) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                } else if (district.getText().toString().equalsIgnoreCase("")) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                else {


                    if (isNetworkAvailable()) {

                        if (sharedpreferences.getString("fid2", "").equalsIgnoreCase("")) {
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Error!")
//set message
                                    .setMessage("Unable to insert new farmer here!")
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
                            save();
                        }

                    } else {

//                        Long tsLong = System.currentTimeMillis() / 1000;
//                        String ts = tsLong.toString();
//                        SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                        editor.putString("fcode2", ts);
//                        editor.putString("fid2", "0");
//                        fan = "0";
//                        faid = Integer.parseInt("0");
//
//                        String n1 = farmername.getText().toString();
//                        String n2 = surname.getText().toString();
//                        String n3 = fathername.getText().toString();
//                        String n4 = mobileno.getText().toString();
//                        String n5 = gender.getText().toString();
//                        String n6 = edob.getText().toString();
//                        String n7 = "";
//                        String n8 = pincode.getText().toString();
//                        String n9 = eaddress.getText().toString();
//                        String n10 = village.getText().toString();
//                        String n11 = grama.getText().toString();
//                        String n12 = taluk.getText().toString();
//                        String n13 = district.getText().toString();
//                        String n14 = "UP";
//                        String n15 = "India";
//                        String n16 = "0";
//                        String n17 = "0";
//                        String n18 = mv;
//                        String n19 = mg;
//                        String n20 = mt;
//                        String n21 = md;
//                        String n22 = ts;
//                        String n23 = hamlet.getText().toString();
//
//
//                        try {
//                            db.updatefarmer(Integer.valueOf(farmerid),n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16,n17,n18,n19,n20,n21,n22,n23,ui);
//                            //spinner.setEnabled(true);
//                            editor.commit();
//                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
////set icon
//                                    .setIcon(android.R.drawable.ic_menu_save)
////set title
//                                    .setTitle("Success!")
////set message
//                                    .setMessage("Farmer Saved Locally!")
////set positive button
//                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialogInterface, int i) {
//
//                                                    //fname.setText(farmername.getText().toString());
//                                                    //set what would happen when positive button is clicked
//
//                                                }
//                                            }
////set negative button
//
//                                    )
//                                    .show();
//                        } catch (SQLiteException exception) {
//                            Log.d("SQLite", "Error"+exception.toString());
//                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
////set icon
//                                    .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                    .setTitle("Alert!")
////set message
//                                    .setMessage("Farmer Detail Already Found!")
////set positive button
//                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialogInterface, int i) {
//                                                    //set what would happen when positive button is clicked
//
//
//                                                    // hjjh
//
//                                                }
//                                            }
////set negative button
//
//                                    )
//                                    .show();
//                        }


                    }
                    //Toast.makeText(MainActivity4.this, "Success", Toast.LENGTH_SHORT).show();
                }
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
                if (editable.length() > 0 && editable.toString().startsWith(".")) {
                    editable.clear();
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

    public class MyRecyclerViewAdapterd extends RecyclerView.Adapter<MainActivity4.MyRecyclerViewAdapterd.ViewHolder> {

        private List<Pojoloc> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapterd(Context context, List<Pojoloc> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MainActivity4.MyRecyclerViewAdapterd.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.suplist2, parent, false);
            return new MainActivity4.MyRecyclerViewAdapterd.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(final MainActivity4.MyRecyclerViewAdapterd.ViewHolder holder, final int position) {
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
                    if (pg == 4) {
                        landvillage.setText(pojoloc.getS2());
                    } else {
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
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView myTextView, trate, tamt, tnamt, tdis, tqty, name, ph, lcn, ty, ham;
            ImageView del, ed;
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

    public void save() {

        consent.setVisibility(View.GONE);
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();

        try {

            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document", userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", "flexi");
            user.put("locnId", "chennai");
            user.put("userId", "fdrmob");
            user.put("localeId", "en_US");
            user.put("instance", Pojokyc.instance);
            userd.put("context", user);
            JSONObject user2 = new JSONObject();

            user2.put("in_farmer_rowid", faid);
            user2.put("in_farmer_code", fan);
            user2.put("in_version_no", 1);
            user2.put("in_photo_farmer", encodedImage);

            user2.put("in_farmer_name", farmername.getText().toString());

            if (surname.getText().toString().equalsIgnoreCase("")) {
                user2.put("in_sur_name", farmername.getText().toString());
            } else {

                user2.put("in_sur_name", surname.getText().toString());
            }

            user2.put("in_fhw_name", fathername.getText().toString());
            user2.put("in_farmer_dob", edob.getText().toString());
            user2.put("in_farmer_addr1", eaddress.getText().toString());
            user2.put("in_farmer_addr2", hamlet.getText().toString());
            user2.put("in_farmer_country", "QCD_UN_IND");
//            user2.put("in_farmer_state","QCD_UNS_UP");
            user2.put("in_farmer_state", sharedpreferences.getString("lo", ""));
            user2.put("in_farmer_dist", md);
            user2.put("in_farmer_taluk", mt);
            user2.put("in_farmer_panchayat", mg);
            user2.put("in_farmer_village", mv);
            user2.put("in_farmer_pincode", pincode.getText().toString());
            user2.put("in_marital_status", "");

            String gencode = "";

            Cursor cgender = dbs.rawQuery("select out_master_code from masterl where out_master_description = '" + gender.getText().toString() + "'", null);
            if (cgender.moveToFirst()) {
                gencode = cgender.getString(0);
            }


            user2.put("in_gender_flag", gencode);


            user2.put("in_reg_mobile_no", mobileno.getText().toString());
            user2.put("in_status_code", "A");
            user2.put("in_mode_flag", "U");
            user2.put("in_row_timestamp", "");
            user2.put("in_fpo_orgncode", sharedpreferences.getString("oc1", ""));
            user2.put("in_created_by", sharedpreferences.getString("un", "").toUpperCase() + "" + sharedpreferences.getString("phn", ""));
            user2.put("in_modified_by", sharedpreferences.getString("un", "").toUpperCase() + "" + sharedpreferences.getString("phn", ""));


            user.put("Header", user2);
            Log.e("OUTPUT", "" + jsonParam.toString());
        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//
        //169.38.77.190:101
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FHeader/NewMobileFarmerHeader", jsonParam,
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
                            final SQLiteDataBaseHandler dbpa = new SQLiteDataBaseHandler(MainActivity4.this);
                            SQLiteDatabase dbspa = dbpa.getWritableDatabase();

                            if (fn.equalsIgnoreCase("null")) {
                                JSONObject obj3 = response.getJSONObject("applicationException");
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Alert!")
//set message
                                        .setMessage("" + obj3.getString("errorDescription"))
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
                            } else {
                                //fname.setText("Farmername:"+fn);
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                farmerid = frid;
                                editor.putString("fcode2", fn);
                                editor.putString("fid2", frid);
                                fan = fn;
                                faid = Integer.parseInt(frid);

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
                                String n14 = "";
                                if (sharedpreferences.getString("lo", "").equalsIgnoreCase("QCD_UNS_TAMIL")) {
                                    n14 = "Tamil Nadu";
                                } else {
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


                                editor.commit();


                                if (modeflag.equalsIgnoreCase("I")) {

                                    try {
                                        db.updatefarmer(Integer.valueOf(farmerid), n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20, n21, n22, n23, ui);
                                        db.updatefarlist(fn, frid, fn, n1, n3, n10, n11, n12, n13, n2, n4, n6, "1");
                                        dbpa.updatefarmer(fn, frid, fn, n3, n1, md, n13, mt, n12, mg, n11, mv, n10);
                                        db.updatecustomer(fn, n1, n10, n9, n18, n4, n2, n3);
                                        //dbs.execSQL("delete from farlist where fc = '"+fn+"'");

                                        //  db.inserfarlist(frid, fn, n1, "0", n10, n11, n12, n13);
                                    } catch (SQLiteException exception) {
                                        Log.d("SQLite", "Error" + exception.toString());
                                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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


                                } else {

                                    try {
                                        db.updatefarmer(Integer.valueOf(farmerid), n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20, n21, n22, n23, ui);
                                        db.updatecustomer(fn, n1, n10, n9, n18, n4, n2, n3);
                                        db.updatefarlist(fn, frid, fn, n1, n3, n10, n11, n12, n13, n2, n4, n6, "1");
                                        dbpa.updatefarmer(fn, frid, fn, n3, n1, md, n13, mt, n12, mg, n11, mv, n10);

                                    } catch (SQLiteException exception) {
                                        Log.d("SQLite", "Error" + exception.toString());
                                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

                                    //db.inserfarlist(frid, fn, n1, "0", n10, n11, n12, n13);
                                }
                                modeflag = "U";
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
//set icon
                                        .setIcon(android.R.drawable.ic_menu_save)
//set title
                                        .setTitle("Success!")
//set message
                                        .setMessage("Farmer Succesfully Saved ! Farmer Code:" + fn)
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        // finish();
                                                        modeflag = "U";

                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                            }


                        } catch (Exception e) {
                            pdialog.dismiss();
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                            FirebaseApp.initializeApp(MainActivity4.this);
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Long tsLong = System.currentTimeMillis() / 1000;
                            String ts = tsLong.toString();
                            DatabaseReference mRef = database.getReference().child(sharedpreferences.getString("un", "")).child(ts);
                            Log.e("Valuecheck", "" + mRef.getRef());
                            mRef.child("name").setValue("SAVEFARMER");
                            mRef.child("date").setValue(date);
                            mRef.child("Error").setValue(response.toString());
                            mRef.child("Activity").setValue("MAINACTIVITY4");
                        }

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);
                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

        String dtt = sdf.format(cal2.getTime());
        String[] dt2 = dtt.split("/");

        if (array4.contains(dt2[2])) {
            if (array3.contains(dt2[1])) {
                stmnt.setText(sdf.format(cal2.getTime()));

            } else {
                stmnt.setText("");
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("This session -" + ccty2s.getText().toString() + " is applicable for " + Arrays.toString(new ArrayList[]{arraymn}) + " Please Select valid Month")
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

        } else {
            stmnt.setText("");
            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
//set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                    .setTitle("Error!")
//set message
                    .setMessage("Selected  Year is " + cyears.getText().toString() + " Please select valid Year")
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
                .start(MainActivity4.this);
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
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
            if (requestCode == 49374) {

                dialog = new Dialog(MainActivity4.this);
                Log.e("RCODE", "" + data.getStringExtra("SCAN_RESULT"));
                String scanvalue = data.getStringExtra("SCAN_RESULT");
                loadFarmer(scanvalue, "", "");
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
            if (requestCode == CAMERA_CAPTURE) {
                Uri uri = picUri;
                Log.d("picUri", picUri.toString());
                // performCrop();
                startCropImageActivity(uri);
            } else if (requestCode == 2) {
                if (imagetype == 0) {
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
                } else if (imagetype == 2) {

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

                } else {
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

                        if (imagetype == 0) {
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
                        } else if (imagetype == 2) {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.getUri());
                            captureld.setImageResource(0);
                            captureld.setImageBitmap(bitmap);
                            gpsTracker = new GpsTracker(MainActivity4.this);
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
                                //FileOutputStream fos = null;

                            }
                        } else {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.getUri());
                            capturek.setImageResource(0);
                            capturek.setImageBitmap(bitmap);
                            // vcap.setImageBitmap(bitmap);
                            byteArrayOutputStream2 = new ByteArrayOutputStream();
                            ui2 = result.getUri().toString();
                            Log.e("HIII", "" + ui2);

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

    public void addbank() {


        if (ub == 0) {
            if (accountno.getText().toString().equalsIgnoreCase("")) {

                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Invalid Account No!")
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

                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

                    if (isNetworkAvailable()) {
                        db.insertbank("QCD_ACC_SAVINGS", accountno.getText().toString(), ebank.getText().toString(), eifsc.getText().toString(), ebranch.getText().toString(), "QCD_YES", "QCD_YES", "QCD_YES", sharedpreferences.getString("fcode2", ""), "0", 0, "", bcode);

                        String selectQuery2 = "SELECT  * FROM bank where flag = 0";

                        Cursor cursor2 = dbs.rawQuery(selectQuery2, null);
                        if (cursor2.moveToFirst()) {


                            savebank(cursor2.getString(9), Integer.parseInt(cursor2.getString(11)), cursor2.getString(2), cursor2.getString(12), cursor2.getString(13), cursor2.getString(5), cursor2.getString(4), cursor2.getString(7), cursor2.getString(6), cursor2.getString(0));


                            // Log.e("Check",""+cursor.getString(1));


                        }
                    } else {
//                        db.insertbank("QCD_ACC_SAVINGS", accountno.getText().toString(), ebank.getText().toString(), eifsc.getText().toString(), ebranch.getText().toString(), "QCD_YES", "QCD_YES", "QCD_YES", sharedpreferences.getString("fcode2", ""), "0", 0, "", bcode);
//                        ub=1;
//                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
////set icon
//                                .setIcon(android.R.drawable.ic_menu_save)
////set title
//                                .setTitle("Success!")
////set message
//                                .setMessage("Bank Saved Locally Successfully!")
////set positive button
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                //set what would happen when positive button is clicked
//                                                sdc = "QCD_YES";
//                                                sdd = "QCD_YES";
//
//                                                accountno.setText("");
//                                                ebank.setText("");
//                                                ebranch.setText("");
//                                                eifsc.setText("");
//
//                                            }
//                                        }
////set negative button
//
//                                )
//                                .show();
                    }


                } catch (SQLiteException exception) {
                    Log.d("SQLite", "Error" + exception.toString());


                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

                    if (isNetworkAvailable()) {


                        db.updatebank(Integer.valueOf(bankid), "QCD_ACC_SAVINGS", accountno.getText().toString(), ebank.getText().toString(), eifsc.getText().toString(), ebranch.getText().toString(), "QCD_YES", "QCD_YES", "QCD_YES", sharedpreferences.getString("fcode2", ""), "0", Integer.parseInt(bbid), "", bcode);
                        String selectQuery2 = "SELECT  * FROM bank where flag = 0";

                        Cursor cursor2 = dbs.rawQuery(selectQuery2, null);

                        if (cursor2.moveToFirst()) {


                            savebank(cursor2.getString(9), Integer.parseInt(cursor2.getString(11)), cursor2.getString(2), cursor2.getString(12), cursor2.getString(13), cursor2.getString(5), cursor2.getString(4), cursor2.getString(7), cursor2.getString(6), cursor2.getString(0));


                            // Log.e("Check",""+cursor.getString(1));


                        }
                    } else {
//                        db.updatebank(Integer.valueOf(bankid), "QCD_ACC_SAVINGS", accountno.getText().toString(), ebank.getText().toString(), eifsc.getText().toString(), ebranch.getText().toString(), "QCD_YES", "QCD_YES", "QCD_YES", sharedpreferences.getString("fcode2", ""), "0", Integer.parseInt(bbid), "", bcode);
//
//                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
////set icon
//                                .setIcon(android.R.drawable.ic_menu_save)
////set title
//                                .setTitle("Success!")
////set message
//                                .setMessage("Bank Updated Locally Successfully!")
////set positive button
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                //set what would happen when positive button is clicked
//                                                sdc = "QCD_YES";
//                                                sdd = "QCD_YES";
//
//                                                accountno.setText("");
//                                                ebank.setText("");
//                                                ebranch.setText("");
//                                                eifsc.setText("");
//
//                                            }
//                                        }
////set negative button
//
//                                )
//                                .show();
                    }

                } catch (SQLiteException exception) {
                    Log.d("SQLite", "Error" + exception.toString());

                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

    public class MyRecyclerViewAdapterb extends RecyclerView.Adapter<MainActivity4.MyRecyclerViewAdapterb.ViewHolder> {

        private List<Pojobank> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapterb(Context context, List<Pojobank> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MainActivity4.MyRecyclerViewAdapterb.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.suplist3, parent, false);
            return new MainActivity4.MyRecyclerViewAdapterb.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(final MainActivity4.MyRecyclerViewAdapterb.ViewHolder holder, final int position) {
            final Pojobank pojobank = mData.get(position);
            if (bstatus == 0) {
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
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView myTextView, trate, tamt, tnamt, tdis, tqty, name, ph, lcn, ty;
            ImageView del, ed;
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

    public void savebank(String fc, int id, String an, String at, String bc, String be, String ifc, String dd, String dc, final String idk) {
        Log.e("NULL", "" + id);
        if (id == 0) {
            modeflag = "I";
        } else {
            modeflag = "U";
        }


        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();

        try {


            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document", userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", "flexi");
            user.put("locnId", "chennai");
            user.put("userId", "fdrmob");
            user.put("localeId", "en_US");
            user.put("instance", Pojokyc.instance);
            userd.put("context", user);
            JSONObject user2 = new JSONObject();

            user2.put("in_farmer_code", fc);
            user2.put("in_farmerbank_rowid", id);
            user2.put("in_bank_acc_no", an);
            user2.put("in_bank_acc_type", "QCD_ACC_SAVINGS");

            user2.put("in_bank_code", bc);
            user2.put("in_branch_code", be);
            user2.put("in_ifsc_code", ifc);
            user2.put("in_dflt_dr_acc", dd);
            user2.put("in_dflt_cr_acc", dc);
            user2.put("in_status_code", "A");
            user2.put("in_mode_flag", modeflag);
            user2.put("in_created_by", sharedpreferences.getString("un", "").toUpperCase() + "" + sharedpreferences.getString("phn", ""));
            user2.put("in_modified_by", sharedpreferences.getString("un", "").toUpperCase() + "" + sharedpreferences.getString("phn", ""));


            user.put("Bank", user2);
            Log.e("OUTPUT", "" + jsonParam.toString());
        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FHeader/NewMobileFarmerBank", jsonParam,
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
                            ub = 0;

                            if (frid.equalsIgnoreCase("0")) {


                                pdialog.dismiss();
                                if (ub == 0) {
                                    dbs.execSQL("DELETE FROM bank WHERE id = (SELECT MAX(id) FROM bank);");
                                }
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                                FirebaseApp.initializeApp(MainActivity4.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis() / 1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef = database.getReference().child(sharedpreferences.getString("un", "")).child(ts);
                                Log.e("Valuecheck", "" + mRef.getRef());
                                mRef.child("name").setValue("SAVEBANK");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("MAINACTIVITY4");

                            } else {


                                dbs.execSQL("UPDATE bank SET flag = 1 WHERE id = " + idk);
                                dbs.execSQL("UPDATE bank SET bid = " + frid + " WHERE id = " + idk);
                                //   dbs.execSQL("DELETE FROM bank WHERE id = " + idk);
                                sdc = "QCD_YES";
                                sdd = "QCD_YES";

                                accountno.setText("");
                                ebank.setText("");
                                ebranch.setText("");
                                eifsc.setText("");
                                pdialog.dismiss();
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                        } catch (Exception e) {
                            pdialog.dismiss();
                            if (ub == 0) {
                                dbs.execSQL("DELETE FROM bank WHERE id = (SELECT MAX(id) FROM bank);");
                            }
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                        if (ub == 0) {
                            dbs.execSQL("DELETE FROM bank WHERE id = (SELECT MAX(id) FROM bank);");
                        }
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MainActivity4.MyRecyclerViewAdapter.ViewHolder> {

        private List<Pojokyc> mData;
        private LayoutInflater mInflater;
        int p;


        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<Pojokyc> data, int page) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
            this.p = page;
        }

        // inflates the row layout from xml when needed
        @Override
        public MainActivity4.MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.tablistdetails2, parent, false);
            return new MainActivity4.MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the textview2 in each row
        @Override
        public void onBindViewHolder(MainActivity4.MyRecyclerViewAdapter.ViewHolder holder, final int position) {
            final Pojokyc pojokyc = mData.get(position);

            if (p == 3) {
                holder.lbank.setVisibility(View.VISIBLE);
                holder.lkyc.setVisibility(View.GONE);


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
                        ub = 1;
                        // bsave.setText("Update");

                        bcode = pojokyc.getBco();


                    }
                });
                holder.lbank.setVisibility(View.VISIBLE);


            }
            if (p == 2) {
                holder.lbank.setVisibility(View.GONE);
                holder.lkyc.setVisibility(View.VISIBLE);
                holder.type.setText(pojokyc.getT());
                holder.stype.setText(pojokyc.getSt());
                holder.dn.setText(pojokyc.getDno());
                holder.vtd.setText(pojokyc.getVtd());

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
                        try {
                            kty = getmastercode(pojokyc.getT());
                            ksty = getmastercode(pojokyc.getSt());
                        } catch (Exception er) {
                            Log.e("error", Log.getStackTraceString(er));
                        }
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
            if (p == 21) {
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

                        array4.clear();
                        us = 1;

                        sowid = Integer.parseInt(pojokyc.getSdid());
                        sowsn = pojokyc.getSdsn();
                        cyears.setText(pojokyc.getSd1());
                        array4.add(pojokyc.getSd1());
                        ccty2s.setText(pojokyc.getE1());
                        codes = pojokyc.getSd2();
                        ccts.setText(pojokyc.getE4());
                        codecn = pojokyc.getSd3();
                        cvars.setText(pojokyc.getSd4());
                        sdt1.setText(pojokyc.getSd5());
                        sdt2.setText(pojokyc.getSd6());
                        sdt3.setText(pojokyc.getSd7());
                        sdt4.setText(pojokyc.getSd8());
                        sdt5.setText(pojokyc.getSd9());
                        stmnt.setText(pojokyc.getE2());
                        codem = pojokyc.getSd10();
                        stclf.setText(pojokyc.getE3());
                        codecr = pojokyc.getSd11();


                        stsnm.setText(pojokyc.getE5());
                        codesn = pojokyc.getSd13();


                        arraysc.clear();
                        arraysn.clear();
                        arraymn.clear();
                        arraymc.clear();
                        arraycrc.clear();
                        arraycrn.clear();
                        arraycnc.clear();
                        arraycnn.clear();
                        arrayvc.clear();
                        arrayvn.clear();
                        arraysec.clear();
                        arraysen.clear();
                        Cursor cursor2 = dbs.query("tablist", new String[]{"field"
                                }, "tab" + "=?",
                                new String[]{"Sowing Details"}, null, null, null, null);


                        // Cursor cursor2 = dbs.rawQuery(selectQuery, null);

                        Log.e("RESNEW", "" + cursor2.getCount());


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
                                            Log.e("YEAR", "" + Arrays.toString(tfs));
                                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                                    (MainActivity4.this, R.layout.spinnertext3, tfs);
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


                        Cursor cursor2mst = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                }, "out_depend_code" + "=?",
                                new String[]{codes}, null, null, null, null);


                        if (cursor2mst.moveToFirst()) {

                            do {
                                if (cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_MONTHS")) {


                                    arraymc.add(cursor2mst.getString(0));
                                    arraymn.add(cursor2mst.getString(1));
                                    if (cursor2mst.getString(1).equalsIgnoreCase("January")) {
                                        array3.add("01");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("February")) {
                                        array3.add("02");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("March")) {
                                        array3.add("03");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("April")) {
                                        array3.add("04");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("May")) {
                                        array3.add("05");
                                    } else if (cursor2mst.getString(1).replaceAll(" ", "").equalsIgnoreCase("June")) {
                                        array3.add("06");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("July")) {
                                        array3.add("07");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("August")) {
                                        array3.add("08");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("September")) {
                                        array3.add("09");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("October")) {
                                        array3.add("10");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("November")) {
                                        array3.add("11");
                                    } else if (cursor2mst.getString(1).equalsIgnoreCase("December")) {
                                        array3.add("12");
                                    }
                                    Log.e("MyAndroidClass", Arrays.toString(new ArrayList[]{array3}));
                                    Log.e("MyAndroidClass2", cursor2mst.getString(1));
                                }
                                if (cursor2mst.getString(2).equalsIgnoreCase("QCD_SOW_CROPCLASS")) {
                                    Log.e("VALCHK", "" + cursor2mst.getString(0));

                                    arraycrc.add(cursor2mst.getString(0));
                                    arraycrn.add(cursor2mst.getString(1));
                                }
                            } while (cursor2mst.moveToNext());


//
//                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                                    (MainActivity4.this, R.layout.spinnertext3, arraymn);
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
                                    (MainActivity4.this, R.layout.spinnertext3, arraycrn);
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

                        Cursor cursor2mst1 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                }, "out_depend_code" + "=?",
                                new String[]{codecr}, null, null, null, null);


                        if (cursor2mst1.moveToFirst()) {

                            do {
                                if (cursor2mst1.getString(2).equalsIgnoreCase("QCD_SOW_CROPNAME")) {


                                    arraycnc.add(cursor2mst1.getString(0));
                                    arraycnn.add(cursor2mst1.getString(1));
                                }

                            } while (cursor2mst1.moveToNext());


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (MainActivity4.this, R.layout.spinnertext3, arraycnn);
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
                        Cursor cursor2mst2 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                }, "out_depend_code" + "=?",
                                new String[]{codecn}, null, null, null, null);


                        if (cursor2mst2.moveToFirst()) {

                            do {
                                if (cursor2mst2.getString(2).equalsIgnoreCase("QCD_SOW_CROPVARIETY")) {


                                    arrayvc.add(cursor2mst2.getString(0));
                                    arrayvn.add(cursor2mst2.getString(1));
                                }

                            } while (cursor2mst2.moveToNext());

                            for (int i = 0; i < arrayvc.size(); i++) {
                                // Log.e("VALCHK",""+position);


                                // ccts.setText("Tap For Select Crop Name");


                                Cursor cursor2mst32 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_parent_code"
                                        }, "out_depend_code" + "=?",
                                        new String[]{arrayvc.get(i)}, null, null, null, null);


                                if (cursor2mst32.moveToFirst()) {

                                    do {
                                        if (cursor2mst32.getString(2).equalsIgnoreCase("QCD_SOW_SEEDNAME")) {


                                            arraysec.add(cursor2mst32.getString(0));
                                            arraysen.add(cursor2mst32.getString(1));
                                        }

                                    } while (cursor2mst32.moveToNext());


                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                            (MainActivity4.this, R.layout.spinnertext3, arraysen);
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
                                    (MainActivity4.this, R.layout.spinnertext3, arrayvn);
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


                        Cursor cursor2mst4 = dbs.query("masterl", new String[]{"out_master_code", "out_master_description","out_depend_code"
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
                            } while (cursor2mst4.moveToNext());


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (MainActivity4.this, R.layout.spinnertext3, arraysn);
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

            if (p == 6) {

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
                        if (pojokyc.getLat9().equalsIgnoreCase("YES")) {
                            radioButton1.setChecked(true);
                            usp = "YES";
                        } else {
                            radioButton2.setChecked(true);
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
            TextView mytextview2, type, stype, dn, vtd, tat, tan, tb, ti, tbr, tdd, tdc, tas, adt, adp, ada, adv, adg, adta, addi;
            TextView tc1, tc2, tc3, tc4, tc5, tc6, tc7, tc8;

            TextView ft1, ft2, ft3, ft4, ft5, ft6, ft7;
            TextView pdt1, pdt2, pdt3, pdt4, pdt5, pdt6, pdt7, pdt8;
            TextView lsd1, lsd2, lsd3, lsd4, lsd5;
            TextView adt2, adt3, adt4, adt1, adt5, adt6;
            TextView ld1, ld2, ld3, ld4, ld5, ld6, ld7, ld8, ld9, ld10, ld11;

            TextView la1, la2, la3, la4, la5, la6, la7, la8, la9;
            TextView lr1, lr2, lr3, lr4;

            TextView in1, in2, in3, in4, in5, in6, in7, in8, in9, in10;

            TextView sh1, sh2, sh3, sh4;

            TextView bu1, bu2, bu3, bu4;
            TextView tn1, tn2, tn3, tn4, tn5, tn6, tn7;
            TextView sk1, sk2, sk3, sk4, sk5, sk6;
            TextView sl1, sl2, sl3, sl4, sl5, sl6, sl7, sl8, sl9, sl10;
            TextView pd1, pd2, pd3, pd4, pd5;
            TextView ip1, ip2, ip3, ip4, ip5, ip6, ip7, ip8, ip9, ip10, ip11, ip12;

            TextView ts1, ts2, ts3, ts4, ts5, ts6, ts7, ts8, ts9, ts10, ts11, ts12, ts13;
            TextView lq1, lq2, lq3, lq4;
            LinearLayout lbank, lkyc, lsow, lland;
            RelativeLayout r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, rsd, rlr, rsh, rbu, rtn, rstk, rslt, rpd, rip, rlq;
            ImageView ed21, del21, ed20, del20, ed19, del19, ed18, del18, ed17, del17, ed16, del16, del15, ed15, del14, ed14, ed13, del13, ed, del, ed2, del2, ed3, del3, del4, ed4, del5, ed5, ed6, del6, del7, ed7, del8, ed8, del9, ed9, del10, ed10, ed12, del12, ed11, del11;

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

                ts1 = itemView.findViewById(R.id.sdt1);
                ts2 = itemView.findViewById(R.id.sdt2);
                ts3 = itemView.findViewById(R.id.sdt3);
                ts4 = itemView.findViewById(R.id.sdt4);
                ts5 = itemView.findViewById(R.id.sdt5);
                ts6 = itemView.findViewById(R.id.sdt6);
                ts7 = itemView.findViewById(R.id.sdt7);
                ts8 = itemView.findViewById(R.id.sdt8);
                ts9 = itemView.findViewById(R.id.sdt9);
                ts10 = itemView.findViewById(R.id.sdt10);
                ts11 = itemView.findViewById(R.id.sdt11);
                ts12 = itemView.findViewById(R.id.sdt12);

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
                lbank = itemView.findViewById(R.id.lbank);
                lkyc = itemView.findViewById(R.id.lkyc);
                lsow = itemView.findViewById(R.id.lsow);
                lland = itemView.findViewById(R.id.lland);


            }


        }

        // convenience method for getting data at click position


        // allows clicks events to be caught


        // parent activity will implement this method to respond to click events

    }

    public void addkyc() {
        if (uk == 0) {
            if (kttype.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {


                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

            } else if (ksty.equalsIgnoreCase("QCD_PRFI_FAMILYCARD") && (documentno.getText().toString().trim().length() < 6 || documentno.getText().toString().trim().length() > 20)) {
                if (documentno.getText().toString().trim().length() < 6) {
                    showdialog("Error", "Family Card Document No Cannot Be Less Than 6 Letters");
                } else if (documentno.getText().toString().trim().length() > 20) {
                    showdialog("Error", "Family Card Document No Cannot Be Greater Than 20 Letters");
                }
            }
//            else if (cdocumentno.getText().toString().equalsIgnoreCase("")) {
//
//                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
////set icon
//                        .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                        .setTitle("Error!")
////set message
//                        .setMessage("Empty Document No!")
////set positive button
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        //set what would happen when positive button is clicked
//
//                                    }
//                                }
////set negative button
//
//                        )
//                        .show();
//
//            }

            else {
                //  if(documentno.getText().toString().equalsIgnoreCase(cdocumentno.getText().toString()))
                // {
                if (isNetworkAvailable()) {
                    db.insertkyc(kttype.getText().toString(), kstype.getText().toString(), documentno.getText().toString(), vtilldate.getText().toString(), sharedpreferences.getString("fcode2", ""), "0", "0", kty, ksty, 0, ui2);


                    capturek.setImageResource(0);
                    capturek.setBackgroundResource(R.drawable.capture);
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


                        savekyc(cursor2.getString(0), cursor2.getString(8), cursor2.getString(9), cursor2.getString(3), cursor2.getString(4), cursor2.getString(5), Integer.parseInt(cursor2.getString(10)), cursor2.getString(11));


                        // Log.e("Check",""+cursor.getString(1));


                    }
                } else {
//                        db.insertkyc(kttype.getText().toString(), kstype.getText().toString(), documentno.getText().toString(), "", sharedpreferences.getString("fcode2", ""), "0", "0", kty, ksty, 0, ui2);
//                        uk=1;
//                        documentno.setText("");
//                        cdocumentno.setText("");
//                        kttype.setText("");
//                        kstype.setText("");
//                        capturek.setImageResource(0);
//                        capturek.setBackgroundResource(R.drawable.capture);
//                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
////set icon
//                                .setIcon(android.R.drawable.ic_menu_save)
////set title
//                                .setTitle("Success!")
////set message
//                                .setMessage("KYC Details saved locally succesfully")
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
                }
                // }
//                else
//                {
//                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
////set icon
//                            .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                            .setTitle("Error!")
////set message
//                            .setMessage("Document No does not match with Confirm Document No!")
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

            }
        } else {

            if (kttype.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {


                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

            } else if (ksty.equalsIgnoreCase("QCD_PRFI_FAMILYCARD") && (documentno.getText().toString().trim().length() < 6 || documentno.getText().toString().trim().length() > 20)) {
                if (documentno.getText().toString().trim().length() < 6) {
                    showdialog("Error", "Family Card Document No Cannot Be Less Than 6 Letters");
                } else if (documentno.getText().toString().trim().length() > 20) {
                    showdialog("Error", "Family Card Document No Cannot Be Greater Than 20 Letters");
                }
            }
//            else if (cdocumentno.getText().toString().equalsIgnoreCase("")) {
//
//                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
////set icon
//                        .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                        .setTitle("Error!")
////set message
//                        .setMessage("Empty Document No!")
////set positive button
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        //set what would happen when positive button is clicked
//
//                                    }
//                                }
////set negative button
//
//                        )
//                        .show();
//
//            }

            else {
                // if(documentno.getText().toString().equalsIgnoreCase(cdocumentno.getText().toString()))
                // {
                if (isNetworkAvailable()) {
                    db.updatekyc(Integer.valueOf(kycid), kttype.getText().toString(), kstype.getText().toString(), documentno.getText().toString(), vtilldate.getText().toString(), sharedpreferences.getString("fcode2", ""), "0", "0", kty, ksty, Integer.parseInt(kycrid), ui2);

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


                        savekyc(cursor2.getString(0), cursor2.getString(8), cursor2.getString(9), cursor2.getString(3), cursor2.getString(4), cursor2.getString(5), Integer.parseInt(cursor2.getString(10)), cursor2.getString(11));


                        // Log.e("Check",""+cursor.getString(1));


                    }
                } else {

//                        db.updatekyc(Integer.valueOf(kycid), kttype.getText().toString(), kstype.getText().toString(), documentno.getText().toString(), "", sharedpreferences.getString("fcode2", ""), "0", "0", kty, ksty, Integer.parseInt(kycrid), ui2);
//                        documentno.setText("");
//                        cdocumentno.setText("");
//                        kttype.setText("");
//                        kstype.setText("");
//                        capturek.setImageResource(0);
//                        capturek.setBackgroundResource(R.drawable.capture);
//                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
////set icon
//                                .setIcon(android.R.drawable.ic_menu_save)
////set title
//                                .setTitle("Success!")
////set message
//                                .setMessage("KYC Details Updated locally succesfully")
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
                }
//                }
//                else
//                {
//                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
////set icon
//                            .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                            .setTitle("Error!")
////set message
//                            .setMessage("Document No does not match with Confirm Document No!")
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

            }

        }
    }

    public void savekyc(final String idk, String type, String stype, String dno, String vtd, String fc, int kid, String ui2) {

        if (kid == 0) {
            modeflag = "I";
        } else {
            modeflag = "U";
        }

        if (ui2.equalsIgnoreCase("0")) {
            encodedImage2 = "0";
        } else {
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
        String finaldate = "";
        try {

            String[] dat = vtd.split("/");
            if (dat[0].length() > 2) {
                finaldate = vtd;
            } else {
                finaldate = dat[2] + "/" + dat[1] + "/" + dat[0];
            }
        } catch (Exception e) {

        }
        try {


            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document", userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", "flexi");
            user.put("locnId", "chennai");
            user.put("userId", "fdrmob");
            user.put("localeId", "en_US");
            user.put("instance", Pojokyc.instance);
            userd.put("context", user);
            JSONObject user2 = new JSONObject();

            user2.put("in_farmer_code", fc);
            user2.put("in_farmerkyc_rowid", kid);
            user2.put("in_proof_type", type);
            Log.e("TTTYPEEE", "" + type);
            user2.put("in_proof_doc", stype);

            user2.put("in_proof_doc_no", dno);
            user2.put("in_proof_doc_adharno", dno);
            user2.put("in_proof_valid_till", finaldate);
            user2.put("in_status_code", "A");
            user2.put("in_mode_flag", modeflag);
            user2.put("in_created_by", sharedpreferences.getString("un", "").toUpperCase() + "" + sharedpreferences.getString("phn", ""));
            user2.put("in_modified_by", sharedpreferences.getString("un", "").toUpperCase() + "" + sharedpreferences.getString("phn", ""));
            user2.put("in_photo_kyc", encodedImage2);

            user.put("KYC", user2);
            Log.e("OUTPUT", "" + jsonParam.toString());
        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FHeader/NewMobileFarmerkyc", jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {
                            pdialog.dismiss();
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("kyc");
                            final String frid = obj2.getString("in_farmerkyc_rowid");

                            if (frid.equalsIgnoreCase("0")) {

                                pdialog.dismiss();
                                if (uk == 0) {
                                    dbs.execSQL("DELETE FROM kyc WHERE id = (SELECT MAX(id) FROM kyc);");
                                }
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                                FirebaseApp.initializeApp(MainActivity4.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis() / 1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef = database.getReference().child(sharedpreferences.getString("un", "")).child(ts);
                                Log.e("Valuecheck", "" + mRef.getRef());
                                mRef.child("name").setValue("SAVEKYC");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("MAINACTIVITY4");


                            } else {


                                dbs.execSQL("UPDATE kyc SET f1 = 1 WHERE id = " + idk);
                                dbs.execSQL("UPDATE kyc SET rid = " + frid + " WHERE id = " + idk);
                                //  dbs.execSQL("DELETE  FROM kyc WHERE id = " + idk);

                                kycrid = frid;
                                uk = 0;
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                        } catch (Exception e) {
                            pdialog.dismiss();
                            if (uk == 0) {
                                dbs.execSQL("DELETE FROM kyc WHERE id = (SELECT MAX(id) FROM kyc);");
                            }
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                            FirebaseApp.initializeApp(MainActivity4.this);
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            Long tsLong = System.currentTimeMillis() / 1000;
                            String ts = tsLong.toString();
                            DatabaseReference mRef = database.getReference().child(sharedpreferences.getString("un", "")).child(ts);
                            Log.e("Valuecheck", "" + mRef.getRef());
                            mRef.child("name").setValue("SAVEKYC");
                            mRef.child("date").setValue(date);
                            mRef.child("Error").setValue(response.toString());
                            mRef.child("Activity").setValue("MAINACTIVITY4");
                        }

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);
                        pdialog.dismiss();
                        if (uk == 0) {
                            dbs.execSQL("DELETE FROM kyc WHERE id = (SELECT MAX(id) FROM kyc);");
                        }
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

    public class MyRecyclerViewAdapterf extends RecyclerView.Adapter<MainActivity4.MyRecyclerViewAdapterf.ViewHolder> {

        private List<Pojofar> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapterf(Context context, List<Pojofar> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MainActivity4.MyRecyclerViewAdapterf.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.suplist5, parent, false);
            return new MainActivity4.MyRecyclerViewAdapterf.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(final MainActivity4.MyRecyclerViewAdapterf.ViewHolder holder, final int position) {
            final Pojofar pojofar = mData.get(position);
            holder.name.setText(pojofar.getFn());
            holder.ph.setText(pojofar.getVi());
            holder.lcn.setText(pojofar.getGp());
            holder.ty.setText(pojofar.getTa());
//            holder.t5.setText(pojofar.getDi());
            holder.t5.setText(pojofar.getMobilenumber());
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
//                    dialog.dismiss();
                    consent.setVisibility(View.VISIBLE);
                    Pojokyc.farmercode = pojofar.getFc();
                    loadFarmer(pojofar.getFc(), pojofar.getId(), pojofar.getFn());
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
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView myTextView, trate, tamt, tnamt, tdis, tqty, name, ph, lcn, ty, t5, tf2, t6, fdistrict;
            ImageView del, ed;
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

    public void loadFarmer(final String fc, final String faridnot, String farnamenot) {

        // dialog = new Dialog(MainActivity4.this);
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Downloading...");
        pdialog.setMessage("Fetching Farmer Profile Information...");
        pdialog.show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                dialog.dismiss();
                pdialog.dismiss();
            }

        }, 1000);

        dbs.execSQL("delete from farmerh");
        dbs.execSQL("delete from bank");
        dbs.execSQL("delete from kyc");
        dbs.execSQL("delete from address");
        dbs.execSQL("delete from crop");
        dbs.execSQL("delete from sowing");
        dbs.execSQL("delete from family");
        dbs.execSQL("delete from personal");
        dbs.execSQL("delete from live");
        dbs.execSQL("delete from asset");
        dbs.execSQL("delete from loan");
        dbs.execSQL("delete from land");
        dbs.execSQL("delete from loanr");
        dbs.execSQL("delete from insurance");
        dbs.execSQL("delete from shareh");
        dbs.execSQL("delete from business");
        dbs.execSQL("delete from training");
        dbs.execSQL("delete from stock");
        dbs.execSQL("delete from sale");
        dbs.execSQL("delete from production");
        dbs.execSQL("delete from input");
        dbs.execSQL("delete from loanq");
        dbs.execSQL("delete from ads");

        try {


            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", "Flexi");
            userd.put("locnId", "Chennai");
            userd.put("userId", "admin");
            userd.put("localeId", "en_US");
            userd.put("instance", Pojokyc.instance);

            JSONObject userd2 = new JSONObject();
            userd2.put("in_farmer_rowid", 0);
            userd2.put("in_farmer_code", fc);

            userd.put("Header", userd2);


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
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FList/mobFarmerSinglefetch", userd,
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
                            try {
                                byte[] decodedString = Base64.decode(n24, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                captureh.setImageBitmap(decodedByte);
                            } catch (Exception e) {

                            }
                            String n25 = obj2.getString("in_farmer_code");
                            String n26 = sharedpreferences.getString("oc1", "");
                            String n27 = "";
                            String n28 = "";
                            final String farname = n1;
                            final String faridc = n22;
                            Pojokyc.farmercode = n25;
                            Pojokyc.farmerid = n1;
                            Pojokyc.farmermobileno = n4;
                            Pojokyc.farmeraddress = n9.toString();
                            consent.setBackgroundResource(R.drawable.cs);
                            try {
                                Cursor c = dbs.rawQuery("select v3 from farlist where fc = '" + Pojokyc.farmercode + "'", null);
                                if (c.getCount() > 0) {
                                    if (c.moveToFirst()) {
                                        if (!c.getString(c.getColumnIndexOrThrow("v3")).equalsIgnoreCase("N")) {
                                            consent.setBackgroundResource(R.drawable.cs2);
                                        }
                                    }
                                }
                            } catch (Exception e) {

                            }

                            if (n24.equalsIgnoreCase("0")) {
                                captureh.setImageResource(0);
                                encodedImage = "0";
                                ui = "0";
                            } else if (n24.equalsIgnoreCase("1")) {
                                captureh.setImageResource(0);
                                captureh.setBackgroundResource(R.drawable.captured);
                                ui = "1";
                            }


                            db.insertfarmer(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20, n21, n22, n23, n24, n26, n27, n28);


                            String selectQuery5 = "SELECT  * FROM farmerh where faid =" + faridc;
                            Cursor cc = dbs.rawQuery(selectQuery5, null);
                            if (cc.getCount() != 0) {
                                if (cc.moveToFirst()) {

                                    farmerid = cc.getString(0);

                                    searchfarmer.setText(cc.getString(1));
                                    farmername.setText(cc.getString(1));
                                    surname.setText(cc.getString(2));
                                    fathername.setText(cc.getString(3));
                                    mobileno.setText(cc.getString(4));
                                    gender.setText(cc.getString(5));
                                    pincode.setText(cc.getString(8));
                                    edob.setText(cc.getString(6));
                                    eaddress.setText(cc.getString(9));
                                    village.setText(cc.getString(10));
                                    grama.setText(cc.getString(11));
                                    taluk.setText(cc.getString(12));
                                    district.setText(cc.getString(13));
                                    hamlet.setText(cc.getString(23));
                                    faid = Integer.parseInt(faridc);
                                    fan = fc;

                                    SharedPreferences.Editor editor = sharedpreferences.edit();


                                    editor.putString("farmer1", String.valueOf(faid));
                                    editor.putString("farmer2", fan);
                                    editor.putString("fcode2", fan);
                                    editor.putString("fid2", String.valueOf(faid));
                                    mv = cc.getString(18);
                                    mg = cc.getString(19);
                                    mt = cc.getString(20);
                                    md = cc.getString(21);
                                    ham = cc.getString(23);
                                    modeflag = "U";


                                    editor.commit();

                                }
                            }
                            try {


                                JSONArray cast = obj.getJSONArray("kyc");
                                for (int j = 0; j < cast.length(); j++) {
                                    JSONObject actor = cast.getJSONObject(j);


                                    String n1k = actor.getString("in_farmerkyc_rowid");
                                    String n2k = actor.getString("in_proof_type");
                                    String n3k = actor.getString("in_proof_type_desc");
                                    String n4k = actor.getString("in_proof_doc");
                                    String n5k = actor.getString("in_proof_doc_desc");
                                    String n6k = actor.getString("in_proof_doc_no");
                                    String n7k = actor.getString("in_proof_doc_adharno");
                                    String n8k = actor.getString("in_proof_valid_till");

                                    db.insertkyc(n3k, n5k, n6k, n8k, fc.toString(), "1", "0", n2k, n4k, Integer.parseInt(n1k), ui2);


                                }
                            } catch (Exception e) {

                            }

                            try {


                                JSONArray cast = obj.getJSONArray("bank");
                                for (int j = 0; j < cast.length(); j++) {
                                    JSONObject actor = cast.getJSONObject(j);


                                    String n1k = actor.getString("in_bank_acc_type_desc");
                                    String n2k = actor.getString("in_bank_acc_no");
                                    String n3k = actor.getString("in_bank_desc");
                                    String n4k = actor.getString("in_ifsc_code");
                                    String n5k = actor.getString("in_branch_code");
                                    String n6k = actor.getString("in_dflt_cr_acc");
                                    String n7k = actor.getString("in_dflt_dr_acc");
                                    String n8k = "";
                                    String n9k = fc;
                                    String n10k = "1";
                                    String n11k = actor.getString("in_farmerbank_rowid");
                                    String n12k = actor.getString("in_bank_acc_type");
                                    String n13k = actor.getString("in_bank_code");

                                    db.insertbank(n1k, n2k, n3k, n4k, n5k, n6k, n7k, n8k, n9k, n10k, Integer.parseInt(n11k), n12k, n13k);


                                }
                            } catch (Exception e) {

                            }
                            try {


                                //userd = new JSONObject();

                                userd = new JSONObject();
                                userd.put("orgnId", "Flexi");
                                userd.put("locnId", "Chennai");
                                userd.put("userId", "admin");
                                userd.put("localeId", "en_US");
                                userd.put("instance", Pojokyc.instance);
                                userd.put("farmer_code", fc);
                                userd.put("entity_code", "EC_ADDR");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", userd,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCa", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetch");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("addR_TYPE");
                                                    String n5a = "0";
                                                    String n6a = actor.getString("eC_PINCODE");
                                                    String n7a = actor.getString("eC_ADDR1");
                                                    String n8a = actor.getString("eC_VILLAGE_desc");
                                                    String n9a = actor.getString("eC_VILLAGE");
                                                    String n10a = actor.getString("eC_GRAMPAN_desc");
                                                    String n11a = actor.getString("eC_GRAMPAN");
                                                    String n12a = actor.getString("eC_TALUK_desc");
                                                    String n13a = actor.getString("eC_TALUK");
                                                    String n14a = actor.getString("eC_DIST_desc");
                                                    String n15a = actor.getString("eC_DIST");
                                                    String n16a = "1";
                                                    String n17a = actor.getString("sno");

                                                    db.insertaddress(n1a, n2a, n3a, n4a, n5a, n6a, n7a, n8a, n9a, n10a, n11a, n12a, n13a, n14a, n15a, n16a, n17a);


                                                }
                                                // pdialog.dismiss();


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
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest);


                            try {


                                //userd = new JSONObject();

                                userd2 = new JSONObject();
                                userd2.put("orgnId", "Flexi");
                                userd2.put("locnId", "Chennai");
                                userd2.put("userId", "admin");
                                userd2.put("localeId", "en_US");
                                userd2.put("instance", Pojokyc.instance);
                                userd2.put("farmer_code", fc);
                                userd2.put("entity_code", "EC_CROP");


                                Log.e("OUTPUT", "" + userd2.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest2 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", userd2,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCc", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchcrop");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("year_desc");
                                                    String n5a = actor.getString("season_desc");
                                                    String n6a = actor.getString("croptype_desc");
                                                    String n7a = actor.getString("vareity");
                                                    String n8a = actor.getString("acres");
                                                    String n9a = actor.getString("sno");


                                                    db.insertcrop(n4a, n5a, n6a, n7a, n8a, "0", "0", "0", n9a, n3a, n1a, "1");


                                                }
                                                // pdialog.dismiss();


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
                            stringRequest2.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest2);


                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_CROP_SOWING");


                                Log.e("OUTPUTS", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest3 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCsow", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchcropsowing");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("year");
                                                    String n5a = actor.getString("season");
                                                    String n6a = actor.getString("croptype");
                                                    String n7a = actor.getString("vareity");
                                                    String n8a = actor.getString("seedQty");
                                                    String n9a = actor.getString("sowingArea");
                                                    String n10a = actor.getString("expectedYield");
                                                    String n11a = actor.getString("expectedYieldToBeSold");
                                                    String n12a = actor.getString("expectedPrice");
                                                    String n13a = actor.getString("sowingDate");
                                                    String n14a = actor.getString("cropclassification");
                                                    String n15a = actor.getString("deweedingDate");
                                                    String n16a = actor.getString("seedName");
                                                    String n17a = actor.getString("sno");
                                                    String n18a = actor.getString("season_desc");

//                                                    Cursor cursor2mst4 = dbs.query("masterl", new String[]{"out_master_code","out_master_description"
//                                                            }, "out_master_code" + "=?",
//                                                            new String[]{actor.getString("months")}, null, null, null, null);
//                                                    if(cursor2mst4.moveToFirst()) {
//                                                         n19a = cursor2mst4.getString(1);
//                                                    }
                                                    String n20a = actor.getString("cropclassification_desc");
                                                    String n21a = actor.getString("croptype_desc");
                                                    String n22a = actor.getString("seedName_desc");


                                                    db.insertsowing(n4a, n5a, n6a, n7a, n8a, n9a, n10a, n12a, n11a, n13a, n14a, n15a, n16a, n17a, n3a, n1a, "1", n18a, n13a, n20a, n21a, n22a);


                                                }
                                                //  pdialog.dismiss();


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
                            stringRequest3.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest3);

                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_FAMILYDET");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest4 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCc", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchFamily");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("familyType");
                                                    String n5a = actor.getString("familyMemberName");

                                                    String[] d = actor.getString("dob").split("T");
                                                    String n6a = d[0];
                                                    String n7a = actor.getString("relationship");
                                                    String n8a = actor.getString("highEduQuali");
                                                    String n9a = actor.getString("occupation");
                                                    String n10a = actor.getString("gender");
                                                    String n11a = actor.getString("sno");


                                                    db.insertfamily(n4a, n5a, n6a, n7a, n8a, n9a, n10a, n11a, n3a, n1a, "1");


                                                }
                                                //  pdialog.dismiss();


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
                            stringRequest4.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest4);

                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_PER_FARMER");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest5 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCc", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchpersonal");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("maritalStatus");
                                                    String n5a = actor.getString("phoneLandline");


                                                    String n6a = actor.getString("mobile");
                                                    String n7a = actor.getString("eMailId");
                                                    String n8a = actor.getString("educationalQuali");
                                                    String n9a = actor.getString("religion");
                                                    String n10a = actor.getString("caste");
                                                    String n11a = actor.getString("minority");
                                                    String n12a = actor.getString("sno");


                                                    db.insertpersonal(n4a, n5a, n6a, n7a, n8a, n9a, n10a, n11a, n12a, n3a, n1a, "1");


                                                }
                                                // pdialog.dismiss();


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
                            stringRequest5.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest5);

                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_LIVESTOCKDET");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest6 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCc", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchLIVESTOCK");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("lsdtype");
                                                    String n5a = actor.getString("lsdsubtype");


                                                    String n6a = actor.getString("lsdownship");
                                                    String n7a = actor.getString("lsdcount");
                                                    String n8a = actor.getString("lsdpur");
                                                    String n9a = actor.getString("sno");


                                                    db.insertlive(n4a, n5a, n6a, n7a, n8a, n9a, n3a, n1a, "1");


                                                }
                                                //  pdialog.dismiss();


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
                            stringRequest6.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest6);

                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_ASSET");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest7 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCc", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchAssets");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("adtype");
                                                    String n5a = actor.getString("adsubtype");


                                                    String n6a = actor.getString("adcount");
                                                    String n7a = actor.getString("adpuryear");
                                                    String n8a = actor.getString("adserialno");
                                                    String n9a = actor.getString("adpurpose");
                                                    String n10a = actor.getString("sno");


                                                    db.insertasset(n4a, n5a, n6a, n7a, n8a, n9a, n10a, n3a, n1a, "1");


                                                }
                                                ////pdialog.dismiss();


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
                            stringRequest7.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest7);


                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_LOANDET");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest8 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCc", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchLoan");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("ldloantype");
                                                    String n5a = actor.getString("ldinstype");
                                                    String n6a = actor.getString("ldinsname");
                                                    String n7a = actor.getString("ldinsbranch");
                                                    String n8a = actor.getString("ldaccno");

                                                    String[] lsd = actor.getString("ldstartdate").split("T");
                                                    String n9a = lsd[0];
                                                    String[] led = actor.getString("ldenddate").split("T");
                                                    String n10a = led[0];
                                                    String n11a = actor.getString("ldamount");
                                                    String n12a = actor.getString("ldtenor");
                                                    String n13a = actor.getString("ldinterest");
                                                    String n14a = actor.getString("ldemi");
                                                    String n15a = actor.getString("sno");


                                                    db.insertloan(n4a, n5a, n6a, n7a, n8a, n9a, n10a, n11a, n12a, n13a, n14a, n15a, n3a, n1a, "1");


                                                }
                                                // pdialog.dismiss();


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
                            stringRequest8.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest8);

                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_OWNEDLAND");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest9 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCc", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchOWNEDLAND");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);

                                                    String v1, v2, v3, v4;
                                                    // Log.e("SUBS",""+actor.getString("landType").toString().substring(0,3));

                                                    if (actor.getString("landType").toString().substring(0, 3).equalsIgnoreCase("QCD")) {
                                                        v1 = actor.getString("landType_desc");

                                                    } else {
                                                        v1 = actor.getString("landType");
                                                    }
                                                    String n4a = v1;
                                                    if (actor.getString("ownership").toString().substring(0, 3).equalsIgnoreCase("QCD")) {
                                                        v2 = actor.getString("ownership_desc");

                                                    } else {
                                                        v2 = actor.getString("ownership");
                                                    }
                                                    String n5a = v2;
                                                    String n6a = actor.getString("noof_Acres");
                                                    if (actor.getString("soilType").toString().substring(0, 3).equalsIgnoreCase("QCD")) {
                                                        v3 = actor.getString("soilType_desc");

                                                    } else {
                                                        v3 = actor.getString("soilType");
                                                    }
                                                    String n7a = v3;

                                                    if (actor.getString("irrigation").toString().substring(0, 3).equalsIgnoreCase("QCD")) {
                                                        v4 = actor.getString("irrigation_desc");

                                                    } else {
                                                        v4 = actor.getString("irrigation");
                                                    }
                                                    String n8a = v4;
                                                    String n9a = actor.getString("latitude");
                                                    String n10a = actor.getString("longitude");
                                                    String n11a = actor.getString("sno");
                                                    String n12a = actor.getString("landVillage");
                                                    String n13a = actor.getString("polyHouse");


                                                    db.insertland(n4a, n5a, n6a, n7a, n8a, n9a, n10a, n11a, n3a, n1a, "1", n12a, n13a, "");


                                                }
                                                // pdialog.dismiss();


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
                            stringRequest9.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest9);

                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_LOAN_REPAY");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest10 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCc", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchLoanRepay");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("loanaccno");
                                                    String n5a = actor.getString("loanrepaymode");
                                                    String n6a = actor.getString("loanrepaydate");
                                                    String n7a = actor.getString("loanrepayamt");
                                                    String n8a = actor.getString("sno");


                                                    db.insertloanr(n4a, n5a, n6a, n7a, n8a, n3a, n1a, "1");


                                                }
                                                //  pdialog.dismiss();


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
                            stringRequest10.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest10);

                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_INSURANCE");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest11 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCc", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchInsurance");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("insurancE_TYPE");
                                                    String n5a = actor.getString("insureR_NAME");
                                                    String n6a = actor.getString("agencY_NAME");
                                                    String n7a = actor.getString("policY_NO");
                                                    String n8a = actor.getString("insureD_ON");
                                                    String n9a = actor.getString("policY_DATE");
                                                    String n10a = actor.getString("maturitY_DATE");
                                                    String n11a = actor.getString("premium");
                                                    String n12a = actor.getString("paymenT_MODE");
                                                    String n13a = actor.getString("nominee");
                                                    String n14a = actor.getString("sno");


                                                    db.insertinsurance(n4a, n5a, n6a, n7a, n8a, n9a, n10a, n11a, n12a, n13a, n14a, n3a, n1a, "1");


                                                }
                                                // pdialog.dismiss();


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
                            stringRequest11.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest11);

                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_SHARE");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest12 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCc", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchShareholding");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("fpoName");
                                                    String n5a = actor.getString("figName");
                                                    String n6a = actor.getString("shares");
                                                    String n7a = actor.getString("shareAmount");
                                                    String n8a = actor.getString("sno");


                                                    db.insertshare(n4a, n5a, n6a, n7a, n8a, n3a, n1a, "1");


                                                }
                                                //  pdialog.dismiss();


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
                            stringRequest12.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest12);

                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_BUSINESS");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest13 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCb", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchBusiness");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("businessSegment");
                                                    String n5a = actor.getString("description");
                                                    String n6a = actor.getString("period");
                                                    String n7a = actor.getString("businessAmount");
                                                    String n8a = actor.getString("sno");


                                                    db.insertbusiness(n4a, n5a, n6a, n7a, n8a, n3a, n1a, "1");


                                                }
                                                //  pdialog.dismiss();


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
                            stringRequest13.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest13);
                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_TRAINING");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest14 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCb", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchTraining");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("traiN_YEAR");
                                                    String n5a = actor.getString("traiN_Season");
                                                    String n6a = actor.getString("traiN_Agenda");
                                                    String n7a = actor.getString("traiN_Duration");
                                                    String n8a = actor.getString("traiN_Place");
                                                    String n9a = actor.getString("traiN_Date");
                                                    String n10a = actor.getString("traiN_ExperDet");
                                                    String n11a = actor.getString("sno");


                                                    db.inserttraining(n4a, n5a, n6a, n7a, n8a, n9a, n10a, n11a, n3a, n1a, "1");


                                                }
                                                //  pdialog.dismiss();


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
                            stringRequest14.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest14);


                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_STOCK");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest15 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCb", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchStock");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("stock_year");
                                                    String n5a = actor.getString("stock_Season");
                                                    String n6a = actor.getString("stock_Type");
                                                    String n7a = actor.getString("stock_Variety");
                                                    String n8a = actor.getString("stock_Unsold");
                                                    String n9a = actor.getString("sno");


                                                    db.insertstock(n4a, n5a, n6a, n7a, "0", n8a, n9a, n3a, n1a, "1");


                                                }
                                                //   pdialog.dismiss();


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
                            stringRequest15.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest15);

                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_SALE");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest16 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCb", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchSALE");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("salE_Year");
                                                    String n5a = actor.getString("salE_Season");
                                                    String n6a = actor.getString("salE_Croptype");
                                                    String n7a = actor.getString("salE_Vareity");
                                                    String n8a = actor.getString("salE_Qty");
                                                    String n9a = actor.getString("salE_Price");
                                                    String n10a = actor.getString("salE_Value");
                                                    String n11a = actor.getString("salE_Buyer");
                                                    String n12a = actor.getString("salE_Terms");
                                                    String n13a = actor.getString("salE_Remark");
                                                    String n14a = actor.getString("sno");


                                                    db.insertsale(n4a, n5a, n6a, n7a, n8a, n9a, n10a, n11a, n12a, n13a, n14a, n3a, n1a, "1");


                                                }
                                                // pdialog.dismiss();


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
                            stringRequest16.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest16);

                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_PRODUCTION");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest17 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
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
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("year");
                                                    String n5a = actor.getString("season");
                                                    String n6a = actor.getString("croptype");
                                                    String n7a = actor.getString("vareity");
                                                    String n8a = actor.getString("actualProduction");
                                                    String n9a = actor.getString("sno");


                                                    //  db.insertproduction(n4a,n5a,n6a,n7a,n8a,n9a,n3a,n1a,"1");


                                                }
                                                // pdialog.dismiss();


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
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest17);

                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_INPUTS");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest18 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCb", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchInputs");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("eC_INP_Year");
                                                    String n5a = actor.getString("eC_INP_Season");
                                                    String n6a = actor.getString("eC_INP_CropType");
                                                    String n7a = actor.getString("eC_INP_Variety");
                                                    String n8a = actor.getString("eC_INP_ICName");
                                                    String n9a = actor.getString("eC_INP_Type");
                                                    String n10a = actor.getString("eC_INP_Name");
                                                    String[] d = actor.getString("eC_INP_UsedDate").split("T");
                                                    String n11a = d[0];
                                                    String n12a = actor.getString("eC_INP_Qty");
                                                    String n13a = actor.getString("eC_INP_Rate");
                                                    String n14a = actor.getString("eC_INP_Amount");
                                                    String n15a = actor.getString("eC_INP_Remarks");

                                                    String n16a = actor.getString("sno");


                                                    db.insertinput(n4a, n5a, n6a, n7a, n8a, n9a, n10a, n11a, n12a, n13a, n14a, n15a, n16a, n3a, n1a, "1");


                                                }
                                                // pdialog.dismiss();


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
                            stringRequest18.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest18);

                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "Flexi");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_LOAN_REQUIRMENT");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest19 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCb", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchLoanRequrement");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1a = fc;
                                                    String n2a = farname;
                                                    String n3a = String.valueOf(faid);
                                                    String n4a = actor.getString("reQ_TYPE");
                                                    String n5a = actor.getString("reQ_AMOUNT");
                                                    String n6a = actor.getString("descripition");
                                                    String n7a = actor.getString("required");
                                                    String n8a = actor.getString("sno");


                                                    db.insertloanq(n4a, n5a, n6a, n7a, n8a, n3a, n1a, "1");


                                                }
                                                // pdialog.dismiss();


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
                            stringRequest19.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest19);
                            try {


                                //userd = new JSONObject();

                                user3 = new JSONObject();
                                user3.put("orgnId", "");
                                user3.put("locnId", "Chennai");
                                user3.put("userId", "admin");
                                user3.put("localeId", "en_US");
                                user3.put("instance", Pojokyc.instance);
                                user3.put("farmer_code", fc);
                                user3.put("entity_code", "EC_ADS");


                                Log.e("OUTPUT", "" + userd.toString());

                            } catch (Exception e) {
                                Log.e("OUTPUT", "" + e.getMessage());
                            }
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest stringRequest20 = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetailFetch/Farmerdetailfetch", user3,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Log.e("CCCCb", "" + response);
                                            try {
                                                JSONObject obj = response.getJSONObject("context");
                                                JSONArray cast = obj.getJSONArray("farmerdetailfetchADS");
                                                for (int j = 0; j < cast.length(); j++) {
                                                    JSONObject actor = cast.getJSONObject(j);


                                                    String n1 = actor.getString("eC_ADS_LF");
                                                    String n2 = actor.getString("eC_ADS_EDUCATION");
                                                    String n3 = actor.getString("eC_ADS_CASTE");
                                                    String n4 = actor.getString("eC_ADS_BANKACC");
                                                    String n5 = actor.getString("eC_ADS_OICLABOUR");
                                                    String n6 = actor.getString("eC_ADS_HOUSE");

                                                    String n7 = "";
                                                    String n8 = actor.getString("eC_ADS_OICBusiness");
                                                    String n9 = actor.getString("eC_ADS_OICJOB");
                                                    String n10 = actor.getString("eC_ADS_SHCard");
                                                    String n11 = actor.getString("eC_ADS_PISFPO");
                                                    String n12 = actor.getString("eC_ADS_PISGOVT");
                                                    String n13 = actor.getString("eC_ADS_PISTRADER");
                                                    String n14 = actor.getString("eC_ADS_ISFPond");
                                                    String n15 = actor.getString("eC_ADS_ISCanal");
                                                    String n16 = actor.getString("eC_ADS_ISTubewell");
                                                    String n17 = actor.getString("eC_ADS_TRACTOR");
                                                    String n18 = actor.getString("eC_ADS_LoanBank");
                                                    String n19 = actor.getString("eC_ADS_LoanMFI");
                                                    String n20 = actor.getString("eC_ADS_LoanVill");
                                                    String n21 = actor.getString("eC_ADS_INSCrop");
                                                    String n22 = actor.getString("eC_ADS_INSHealth");
                                                    String n23 = actor.getString("eC_ADS_INSLife");
                                                    String n24 = actor.getString("eC_ADS_INPLTRADER");
                                                    String n25 = actor.getString("eC_ADS_RMCReg");
                                                    String n26 = actor.getString("eC_ADS_eNAMReg");

                                                    String n27 = actor.getString("eC_ADS_PMKisanNidhi");
                                                    String n28 = actor.getString("eC_ADS_Kalia");
                                                    String n29 = actor.getString("eC_ADS_SMPFPO");
                                                    String n30 = actor.getString("eC_ADS_SMPRMC");
                                                    String n31 = actor.getString("eC_ADS_SMPLTRADER");
                                                    String n32 = actor.getString("eC_ADS_MSTFPO");
                                                    String n33 = actor.getString("eC_ADS_MSTRMC");
                                                    String n34 = actor.getString("eC_ADS_MSTLTRAADER");
                                                    String n35 = actor.getString("eC_ADS_INPFPO");
                                                    String n36 = actor.getString("eC_ADS_INPSOCIETY");
                                                    String n37 = actor.getString("eC_ADS_OMSPucca");
                                                    String n38 = actor.getString("eC_ADS_OMSKuccha");
                                                    String n39 = actor.getString("eC_ADS_DPPlatform");
                                                    String n40 = actor.getString("eC_ADS_DPPucca");
                                                    String n41 = actor.getString("eC_ADS_DPKuccha");
                                                    String n42 = actor.getString("eC_ADS_DPROAD");
                                                    String n43 = actor.getString("eC_ADS_maizesoldMonth3");
                                                    String n44 = actor.getString("eC_ADS_maizesoldMonth13");
                                                    String n45 = actor.getString("eC_ADS_maizesoldMonth01");

                                                    String n46 = actor.getString("eC_ADS_WhetherAggregator");
                                                    String n47 = actor.getString("eC_ADS_IFPC");
                                                    String n48 = actor.getString("eC_ADS_RCLPHM");
                                                    String n49 = actor.getString("eC_ADS_RCLPest");
                                                    String n50 = actor.getString("eC_ADS_RCLWeather");
                                                    String n51 = actor.getString("eC_ADS_MAIZE_DD");
                                                    String n52 = actor.getString("eC_ADS_MAIZE_F");
                                                    String n53 = actor.getString("eC_ADS_MAIZE_M");
                                                    //  Log.e("HOO","hi");
                                                    String n54 = actor.getString("eC_ADS_NFMMIGRATED");
                                                    String n55 = "0";
                                                    String n56 = actor.getString("eC_ADS_WorkingNo");
                                                    String n57 = actor.getString("eC_ADS_FamilyNo");
                                                    String n58 = actor.getString("eC_ADS_AadharNo");

                                                    String n59 = actor.getString("eC_ADS_20_21RA_Veg");
                                                    String n60 = actor.getString("eC_ADS_20_21RA_Paddy");
                                                    String n61 = actor.getString("eC_ADS_20_21RA_MAIZE");
                                                    String n62 = actor.getString("eC_ADS_19_20RA_Veg");
                                                    String n63 = actor.getString("eC_ADS_19_20RA_Paddy");
                                                    String n64 = actor.getString("eC_ADS_19_20RA_MAIZE");
                                                    String n65 = actor.getString("eC_ADS_20_21KA_Veg");
                                                    String n66 = actor.getString("eC_ADS_20_21KA_Paddy");
                                                    String n67 = actor.getString("eC_ADS_20_21KA_MAIZE");
                                                    String n68 = actor.getString("eC_ADS_19_20KA_Veg");
                                                    String n69 = actor.getString("eC_ADS_19_20KA_Paddy");
                                                    String n70 = actor.getString("eC_ADS_19_20KA_MAIZE");

                                                    String n71 = actor.getString("eC_ADS_Leaseland");
                                                    String n72 = actor.getString("eC_ADS_Ownland");
                                                    String n73 = actor.getString("eC_ADS_Smallruminants");
                                                    String n74 = actor.getString("eC_ADS_Poultry");
                                                    String n75 = actor.getString("eC_ADS_Largeruminants");
                                                    String n76 = actor.getString("eC_ADS_Ragi2021");
                                                    String n77 = actor.getString("eC_ADS_Ragi2020");
                                                    String n78 = actor.getString("eC_ADS_MaizeyieldKhRa");
                                                    String n79 = actor.getString("eC_ADS_Maizeyieldqtlacre");
                                                    String n80 = actor.getString("eC_ADS_FPCsharepaidRs");
                                                    String n81 = actor.getString("eC_ADS_FSBags");
                                                    String n82 = actor.getString("eC_ADS_FSLoose");
                                                    String n83 = actor.getString("eC_ADS_FSCob");
                                                    String n84 = actor.getString("eC_ADS_PAYRMC");
                                                    String n85 = actor.getString("eC_ADS_PAYLTRADER");
                                                    String n86 = actor.getString("eC_ADS_PAYHAT");
                                                    String n87 = actor.getString("eC_ADS_SMRMC");
                                                    String n88 = actor.getString("eC_ADS_SMLTRADER");
                                                    String n89 = actor.getString("eC_ADS_SMHat");
                                                    String n90 = actor.getString("eC_ADS_WRFinance");
                                                    String n91 = actor.getString("eC_ADS_WHSubsidy");

                                                    String n92 = actor.getString("eC_ADS_GROUPNAME");
                                                    String n93 = actor.getString("eC_ADS_LTHNAME");
                                                    String n94 = actor.getString("eC_ADS_LTHMOBILENO");
                                                    String n95 = actor.getString("eC_ADS_NOOFMMACHINE");
                                                    String n96 = actor.getString("eC_ADS_NTRACTORVILL");
                                                    String n97 = actor.getString("eC_ADS_DISWAREHOUSE");
                                                    String n98 = actor.getString("eC_ADS_WAREHOUSETYPE");
                                                    String n99 = actor.getString("eC_ADS_ConcernLRPME");
                                                    String n100 = actor.getString("eC_ADS_SRCLWeather");
                                                    String n101 = actor.getString("eC_ADS_SRCLPest");
                                                    String n102 = actor.getString("eC_ADS_SRCLPHM");
                                                    String n103 = actor.getString("eC_ADS_BankAccNo");
                                                    String n104 = actor.getString("eC_ADS_BankName");
                                                    String n105 = actor.getString("eC_ADS_BankBranName");
                                                    String n106 = actor.getString("eC_ADS_BankIFSC");
                                                    String n107 = actor.getString("eC_ADS_SHARECERT");
                                                    String n108 = actor.getString("eC_ADS_SHARECERTNO");


                                                    db.insertads(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20, n21, n22, n23, n24, n25, n26, n27, n28, n29, n30, n31, n32, n33, n34, n35, n36, n37, n38, n39, n40, n41, n42, n43, n44, n45, n46, n47, n48, n49, n50, n51, n52, n53, n54, n55, n56, n57, n58, n59, n60, n61, n62, n63, n64, n65, n66, n67, n68, n69, n70, n71, n72, n73, n74, n75, n76, n77, n78, n79, n80, n81, n82, n83, n84, n85, n86, n87, n88, n89, n90, n91, sharedpreferences.getString("fid2", ""), sharedpreferences.getString("fcode2", ""), "0", n92, n93, n94, n95, n96, n97, n98, n99, n100, n101, n102, n103, n104, n105, n106, n107, n108);


                                                }
                                                // pdialog.dismiss();


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
                            stringRequest19.setRetryPolicy(new DefaultRetryPolicy(
                                    6500000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest20);
                        } catch (Exception e) {
                            Log.e("INFAR", "" + e);

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
        VolleySingleton.getInstance(MainActivity4.this).addToRequestQueue(stringRequest);
    }

    public void addsow() {

        if (us == 0) {
            if (cyears.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
//set iconim
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
            } else if (ccty2s.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (ccts.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (stmnt.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (stclf.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Error!")
//set message
                        .setMessage("Select Calssification!")
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
            } else if (stsnm.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (sdt1.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (sdt2.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (sdt3.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (sdt4.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (sdt5.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else {

                if (isNetworkAvailable()) {
                    Cursor cursoraddr = dbs.query("sowing", new String[]{"id"
                            }, "v16" + "=?",
                            new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);
                    int x = cursoraddr.getCount();
                    x++;
                    String slno = String.valueOf(x);
                    db.insertsowing(cyears.getText().toString(), codes, codecn, cvars.getText().toString(), sdt1.getText().toString(), sdt2.getText().toString(), sdt3.getText().toString(), sdt4.getText().toString(), sdt5.getText().toString(), codem, codecr, "", codesn, slno, sharedpreferences.getString("fid", ""), sharedpreferences.getString("fcode2", ""), "0", ccty2s.getText().toString(), stmnt.getText().toString(), stclf.getText().toString(), ccts.getText().toString(), stsnm.getText().toString());
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


                        savesowing(cursor2.getString(0), cursor2.getString(1), cursor2.getString(2), cursor2.getString(3), cursor2.getString(4), cursor2.getString(5), cursor2.getString(6), cursor2.getString(7), cursor2.getString(8), cursor2.getString(9), cursor2.getString(19), cursor2.getString(11), cursor2.getString(12), cursor2.getString(13), cursor2.getString(14), cursor2.getString(15), cursor2.getString(16), cursor2.getString(17));


                        // Log.e("Check",""+cursor.getString(1));


                    }
                } else {
//                    Cursor cursoraddr = dbs.query("sowing", new String[]{"id"
//                            }, "v16" + "=?",
//                            new String[]{sharedpreferences.getString("fcode2","")}, null, null, null, null);
//                    int x = cursoraddr.getCount();
//                    x++;
//                    String slno = String.valueOf(x);
//                    db.insertsowing(cyears.getText().toString(),ccty2s.getText().toString(),ccts.getText().toString(),cvars.getText().toString(),sdt1.getText().toString(),sdt2.getText().toString(),sdt3.getText().toString(),sdt4.getText().toString(),sdt5.getText().toString(),stmnt.getText().toString(),stclf.getText().toString(),"",stsnm.getText().toString(),slno,sharedpreferences.getString("fid2",""),sharedpreferences.getString("fcode2",""),"0");
//                    cyears.setText("");
//                    ccty2s.setText("");
//                    ccts.setText("");
//                    cvars.setText("");
//                    stmnt.setText("");
//                    stclf.setText("");
//                    stsnm.setText("");
//                    sdt1.setText("");
//                    sdt2.setText("");
//                    sdt3.setText("");
//                    sdt4.setText("");
//                    sdt5.setText("");
//                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
////set icon
//                            .setIcon(android.R.drawable.ic_menu_save)
////set title
//                            .setTitle("Success!")
////set message
//                            .setMessage("Sowing and Croping Details Saved Locally!")
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
                }

            }
        } else {
            if (cyears.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (ccty2s.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (ccts.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (stmnt.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (stclf.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (stsnm.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (sdt1.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (sdt2.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (sdt3.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (sdt4.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (sdt5.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else {
                if (isNetworkAvailable()) {
                    db.updatesowing(sowid, cyears.getText().toString(), codes, codecn, cvars.getText().toString(), sdt1.getText().toString(), sdt2.getText().toString(), sdt3.getText().toString(), sdt4.getText().toString(), sdt5.getText().toString(), codem, codecr, "", codesn, sowsn, sharedpreferences.getString("fid", ""), sharedpreferences.getString("fcode2", ""), "0", ccty2s.getText().toString(), stmnt.getText().toString(), stclf.getText().toString(), ccts.getText().toString(), stsnm.getText().toString());
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


                        savesowing(cursor2.getString(0), cursor2.getString(1), cursor2.getString(2), cursor2.getString(3), cursor2.getString(4), cursor2.getString(5), cursor2.getString(6), cursor2.getString(7), cursor2.getString(8), cursor2.getString(9), cursor2.getString(19), cursor2.getString(11), cursor2.getString(12), cursor2.getString(13), cursor2.getString(14), cursor2.getString(15), cursor2.getString(16), cursor2.getString(17));


                        // Log.e("Check",""+cursor.getString(1));


                    }
                } else {
//                    db.updatesowing(sowid,cyears.getText().toString(),ccty2s.getText().toString(),ccts.getText().toString(),cvars.getText().toString(),sdt1.getText().toString(),sdt2.getText().toString(),sdt3.getText().toString(),sdt4.getText().toString(),sdt5.getText().toString(),stmnt.getText().toString(),stclf.getText().toString(),"",stsnm.getText().toString(),sowsn,sharedpreferences.getString("fid2",""),sharedpreferences.getString("fcode2",""),"0");
//                    cyears.setText("");
//                    ccty2s.setText("");
//                    ccts.setText("");
//                    cvars.setText("");
//                    stmnt.setText("");
//                    stclf.setText("");
//                    stsnm.setText("");
//                    sdt1.setText("");
//                    sdt2.setText("");
//                    sdt3.setText("");
//                    sdt4.setText("");
//                    sdt5.setText("");
//                    us = 0;
//                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
////set icon
//                            .setIcon(android.R.drawable.ic_menu_save)
////set title
//                            .setTitle("Success!")
////set message
//                            .setMessage("Sowing and Croping Details Updated Locally!")
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
                }
            }

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void savesowing(final String id, String v1, String v2, String v3, String v4, String v5, String v6, String v7, final String v8, String v9, String v10, String v11, String v12, String v13, String v14, String v15, String v16, String v17) {


        Log.e("NULL", "" + v1);
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

        String vname = null;

        Cursor cursor = dbs.query("masterl", new String[]{"out_depend_code"
                }, "out_master_code" + " LIKE ?",
                new String[]{"%" + v13 + "%"}, null, null, null, null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    vname = cursor.getString(0);
                } while (cursor.moveToNext());
            }
        }
        String finaldate = "";
        try {

            String[] dat = v10.split("/");
            if (dat[0].length() > 2) {
                finaldate = v10;
            } else {
                finaldate = dat[2] + "/" + dat[1] + "/" + dat[0];
            }
        } catch (Exception e) {

        }
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();

        try {


            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document", userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", String.valueOf(v14));
            user.put("locnId", "chennai");
            user.put("userId", "fdrmob");
            user.put("localeId", "en_US");
            user.put("instance", Pojokyc.instance);

            userd.put("context", user);
            JSONObject user2 = new JSONObject();

            user2.put("inout_farmer_rowid", Integer.parseInt(sharedpreferences.getString("fid2", "")));
            user2.put("inout_farmer_code", sharedpreferences.getString("fcode2", ""));
            user2.put("inout_version_no", 1);
            user2.put("entitygrp_code", "EC_CROP_SOWING");
            user2.put("in_created_by", sharedpreferences.getString("un", "").toUpperCase() + "" + sharedpreferences.getString("phn", ""));
            // user2.put("in_modified_by", sharedpreferences.getString("un","").toUpperCase()+""+sharedpreferences.getString("phn",""));
            user.put("Header", user2);

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
                ob10.put("in_entity_value", finaldate);
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


            user.put("Detail", jsonArray);
            Log.e("OUTPUT", "" + jsonParam.toString());
        } catch (Exception e) {
            Log.e("OUTPUT2", "" + e.getMessage());
        }


        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetail/NewMobileFarmerCrop", jsonParam,
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

                            if (frid.equalsIgnoreCase("0")) {


                                //dbs.execSQL("UPDATE address SET flag = 1 WHERE id = " + idk);
                                //  dbs.execSQL("DELETE FROM sowing WHERE id = " + id);
                                // dbs.execSQL("UPDATE sowing SET v17 = 1 WHERE id = " + id);
                                dbs.execSQL("UPDATE sowing SET v17 = 1 WHERE id = " + id);


                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                                FirebaseApp.initializeApp(MainActivity4.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis() / 1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef = database.getReference().child(sharedpreferences.getString("un", "")).child(ts);
                                Log.e("Valuecheck", "" + mRef.getRef());
                                mRef.child("name").setValue("SAVESOWING");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("MAINACTIVITY4");


                            } else {
                                pdialog.dismiss();
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                                FirebaseApp.initializeApp(MainActivity4.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis() / 1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef = database.getReference().child(sharedpreferences.getString("un", "")).child(ts);
                                Log.e("Valuecheck", "" + mRef.getRef());
                                mRef.child("name").setValue("SAVESOWING");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("MAINACTIVITY4");
                            }
                        } catch (Exception e) {
                            pdialog.dismiss();
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                        }

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);
                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

    public void addland() {

        if (ul == 0) {
            if (tlt.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (tow.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (soiltype.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (tis.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (landvillage.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (elnoa.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty No of Acres!")
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
            } else if (radioGroup.getCheckedRadioButtonId() == -1) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else {
                if (radioButton1.isChecked()) {
                    usp = "YES";
                } else {
                    usp = "NO";
                }

                if (isNetworkAvailable()) {
                    Cursor cursoraddr = dbs.query("land", new String[]{"id"
                            }, "v10" + "=?",
                            new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);
                    int x = cursoraddr.getCount();
                    x++;
                    String slno = String.valueOf(x);
                    db.insertland(tlt.getText().toString(), tow.getText().toString(), elnoa.getText().toString(), soiltype.getText().toString(), tis.getText().toString(), elat.getText().toString(), elon.getText().toString(), slno, sharedpreferences.getString("fid2", ""), sharedpreferences.getString("fcode2", ""), "0", landvillage.getText().toString(), usp, ui3);
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


                        saveland(cursor2.getString(1), cursor2.getString(2), cursor2.getString(3), cursor2.getString(4), cursor2.getString(5), cursor2.getString(6), cursor2.getString(7), cursor2.getString(8), cursor2.getString(0), cursor2.getString(12), cursor2.getString(13), cursor2.getString(14));


                        // Log.e("Check",""+cursor.getString(1));


                    }
                } else {
//                    Cursor cursoraddr = dbs.query("land", new String[]{"id"
//                            }, "v10" + "=?",
//                            new String[]{sharedpreferences.getString("fcode2", "")}, null, null, null, null);
//                    int x = cursoraddr.getCount();
//                    x++;
//                    String slno = String.valueOf(x);
//                    db.insertland(tlt.getText().toString(),tow.getText().toString(),elnoa.getText().toString(),soiltype.getText().toString(),tis.getText().toString(),elat.getText().toString(),elon.getText().toString(),slno,sharedpreferences.getString("fid2",""),sharedpreferences.getString("fcode2",""),"0",landvillage.getText().toString(),usp,ui3);
//                    elnoa.setText("");
//                    elat.setText("");
//                    elon.setText("");
//                    captureld.setImageResource(0);
//                    captureld.setBackgroundResource(R.drawable.capture);
//                    landvillage.setText("");
//                    radioButton1.setChecked(false);
//                    radioButton2.setChecked(false);
//                    tlt.setText("");
//                    tow.setText("");
//                    soiltype.setText("");
//                    tis.setText("");
//                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
////set icon
//                            .setIcon(android.R.drawable.ic_menu_save)
////set title
//                            .setTitle("Success!")
////set message
//                            .setMessage("Land Details Saved Locally!")
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
                }

            }
        } else {
            if (tlt.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (tow.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (soiltype.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (tis.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (landvillage.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else if (elnoa.getText().toString().replaceAll(" ", "").equalsIgnoreCase("")) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Alert!")
//set message
                        .setMessage("Empty No of Acres!")
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
            } else if (radioGroup.getCheckedRadioButtonId() == -1) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
            } else {

                if (isNetworkAvailable()) {
                    db.updateland(Integer.valueOf(laid), tlt.getText().toString(), tow.getText().toString(), elnoa.getText().toString(), soiltype.getText().toString(), tis.getText().toString(), elat.getText().toString(), elon.getText().toString(), laslno, sharedpreferences.getString("fid2", ""), sharedpreferences.getString("fcode2", ""), "0", landvillage.getText().toString(), usp, ui3);

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


                        saveland(cursor2.getString(1), cursor2.getString(2), cursor2.getString(3), cursor2.getString(4), cursor2.getString(5), cursor2.getString(6), cursor2.getString(7), cursor2.getString(8), cursor2.getString(0), cursor2.getString(12), cursor2.getString(13), cursor2.getString(14));


                        // Log.e("Check",""+cursor.getString(1));


                    }

                } else {
//                    db.updateland(Integer.valueOf(laid),tlt.getText().toString(),tow.getText().toString(),elnoa.getText().toString(),soiltype.getText().toString(),tis.getText().toString(),elat.getText().toString(),elon.getText().toString(),laslno,sharedpreferences.getString("fid2",""),sharedpreferences.getString("fcode2",""),"0",landvillage.getText().toString(),usp,ui3);
//                    elnoa.setText("");
//                    elat.setText("");
//                    elon.setText("");
//                    captureld.setImageResource(0);
//                    captureld.setBackgroundResource(R.drawable.capture);
//                    landvillage.setText("");
//                    radioButton1.setChecked(false);
//                    radioButton2.setChecked(false);
//                    tlt.setText("");
//                    tow.setText("");
//                    soiltype.setText("");
//                    tis.setText("");
//                    ul = 0;
//                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
////set icon
//                            .setIcon(android.R.drawable.ic_menu_save)
////set title
//                            .setTitle("Success!")
////set message
//                            .setMessage("Land Details Saved Locally!")
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
                }

            }
        }

    }

    public void saveland(String v1, String v2, String v3, String v4, final String v5, String v6, String v7, String v8, final String id, String v12, String v13, String v14) {


        Log.e("NULL", "" + v1);
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
            jsonParam.put("document", userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", String.valueOf(v8));
            user.put("locnId", "chennai");
            user.put("userId", "fdrmob");
            user.put("localeId", "en_US");
            user.put("instance", Pojokyc.instance);

            userd.put("context", user);
            JSONObject user2 = new JSONObject();

            user2.put("inout_farmer_rowid", Integer.parseInt(sharedpreferences.getString("fid2", "")));
            user2.put("inout_farmer_code", sharedpreferences.getString("fcode2", ""));
            user2.put("inout_version_no", 1);
            user2.put("entitygrp_code", "EC_OWNEDLAND");
            user2.put("in_created_by", sharedpreferences.getString("un", "").toUpperCase() + "" + sharedpreferences.getString("phn", ""));
            // user2.put("in_modified_by", sharedpreferences.getString("un","").toUpperCase()+""+sharedpreferences.getString("phn",""));
            user.put("Header", user2);

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
            }
            JSONObject ob9 = new JSONObject();
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
            }
            JSONObject ob10 = new JSONObject();
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


            user.put("Detail", jsonArray);
            user.put("Dtlownland_picture", jsonArray2);
            Log.e("OUTPUT", "" + jsonParam.toString());
        } catch (Exception e) {
            Log.e("OUTPUT2", "" + e.getMessage());
        }


        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.url + "/api/Mobile_FDR_FDetail/NewMobileFarmerCrop", jsonParam,
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

                            if (frid.equalsIgnoreCase("0")) {


                                //dbs.execSQL("UPDATE address SET flag = 1 WHERE id = " + idk);
                                //  dbs.execSQL("DELETE FROM sowing WHERE id = " + id);
                                dbs.execSQL("UPDATE land SET v11 = 1 WHERE id = " + id);


                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                                FirebaseApp.initializeApp(MainActivity4.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis() / 1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef = database.getReference().child(sharedpreferences.getString("un", "")).child(ts);
                                Log.e("Valuecheck", "" + mRef.getRef());
                                mRef.child("name").setValue("SAVELAND");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("MAINACTIVITY4");


                            } else {
                                pdialog.dismiss();
                                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                                FirebaseApp.initializeApp(MainActivity4.this);
                                FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

                                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                                Long tsLong = System.currentTimeMillis() / 1000;
                                String ts = tsLong.toString();
                                DatabaseReference mRef = database.getReference().child(sharedpreferences.getString("un", "")).child(ts);
                                Log.e("Valuecheck", "" + mRef.getRef());
                                mRef.child("name").setValue("SAVELAND");
                                mRef.child("date").setValue(date);
                                mRef.child("Error").setValue(response.toString());
                                mRef.child("Activity").setValue("MAINACTIVITY4");
                            }
                        } catch (Exception e) {
                            pdialog.dismiss();
                            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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
                        }

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);
                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Please make sure the data is stored before exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity4.super.onBackPressed();
                    }
                }).create().show();
    }

    public void farmer_searchlist(String flag) {


        pojofarlist.clear();
        // elc.setText(""+(String) parent.getItemAtPosition(position));
        String qry = "";
        if (flag.equalsIgnoreCase("ALL")) {
            qry = "select * from farlist ";
        } else if (flag.equalsIgnoreCase("N")) {
            qry = "select * from farlist where v3 = '" + flag + "'";
        } else if (flag.equalsIgnoreCase("Y")) {
            qry = "select * from farlist where v3 = 'AA' or v3 = 'AO'";
        } else if (flag.equalsIgnoreCase("YY")) {
            qry = "select * from farlist where v3 = 'DA' or v3 = 'DO'";
        }


        if (!far_name.equalsIgnoreCase("")) {
            if (flag.equalsIgnoreCase("ALL")) {

                qry += " where fn = '" + far_name + "' and fpoorgn_code='" + sharedpreferences.getString("oc1", "") + "'";
            } else {
                qry += " and fn = '" + far_name + "' and fpoorgn_code='" + sharedpreferences.getString("oc1", "") + "'";
            }
        } else if (!far_mobile.equalsIgnoreCase("")) {
            if (flag.equalsIgnoreCase("ALL")) {
                qry += " where v2 = '" + far_mobile + "' and fpoorgn_code='" + sharedpreferences.getString("oc1", "") + "' ";
            } else {
                qry += " and v2 = '" + far_mobile + "' and fpoorgn_code='" + sharedpreferences.getString("oc1", "") + "'";
            }
        } else {
            qry += "";
        }
        Log.e("query", qry);

       /* Cursor cursor = dbs.query("farlist", new String[]{"fid", "fc", "fn", "vi", "gp", "ta", "di", "id","sn"
                }, "fn" + "=? COLLATE NOCASE",
                new String[]{""}, null, null, null, null);*/
        Cursor cursor = dbs.rawQuery(qry, null);
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {


                    if (cursor.getString(cursor.getColumnIndexOrThrow("fpoorgn_code")).equalsIgnoreCase(sharedpreferences.getString("oc1", ""))) {

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
                    }
                    // array2.add(cursor.getString(11));
                    // Log.e("VAL",""+cursor.getString(11));
                    // Log.e("VAL",""+cursor.getString(11));

                } while (cursor.moveToNext());
            }


        }
        far_name = "";
        far_mobile = "";
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            Cursor c = dbs.rawQuery("select v3 from farlist where fc = '" + Pojokyc.farmercode + "'", null);
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    if (!c.getString(c.getColumnIndexOrThrow("v3")).equalsIgnoreCase("N")) {
                        consent.setBackgroundResource(R.drawable.cs2);
                    }
                }
            }
        } catch (Exception e) {

        }
        // put your code here...

    }

    public String getmastercode(String description) {
        String result = "";
        Cursor cursor = dbs.rawQuery("Select * from masterl where out_master_description = '" + description + "' ", null);
        try {
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndexOrThrow("out_master_code"));
                }
            }
        } catch (Exception e) {
            Log.e("error", Log.getStackTraceString(e));
        } finally {
            cursor.close();
        }
        return result;
    }

    public void showdialog(String title, String message) {
        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity4.this)
//set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                .setTitle(title + "!")
//set message
                .setMessage(message + "!")
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
        dialog = new Dialog(MainActivity4.this);
        dialog.setContentView(R.layout.suppliersearch2);
        dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
        dialog.setTitle("Title...");
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
        final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity4.this));
        adapterd = new MainActivity4.MyRecyclerViewAdapterd(MainActivity4.this, pojolocList);
        dialog.getWindow().setLayout(width, height);
        elc = (AutoCompleteTextView) dialog.findViewById(R.id.elc);
        dialogtext = dialog.findViewById(R.id.dialogtext);
        dialogtext.setText("Village Search");
        arrayn.clear();
        Cursor cc = dbs.query("masterl", new String[]{"out_master_description"
                }, "out_parent_code" + "=?",
                new String[]{"QCD_UN_VILLAGE"}, null, null, null, null);
        if (cc.getCount() != 0) {
            if (cc.moveToFirst()) {
                do {
                    if (arrayn.contains(cc.getString(0).trim())) {

                    } else {
                        arrayn.add(cc.getString(0));
                    }
                } while (cc.moveToNext());
            }
        }

        ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(MainActivity4.this,
                R.layout.spinnertext, arrayn);

        elc.setAdapter(adapterlist2n);

        elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pojolocList.clear();
                Cursor cursor = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_depend_code", "out_parent_code"
                        }, "out_master_description" + " LIKE ?",
                        new String[]{"%" + (String) parent.getItemAtPosition(position) + "%"}, null, null, null, null);
                if (cursor.getCount() != 0) {
                    if (cursor.moveToFirst()) {
                        do {

                            if (cursor.getString(3).equalsIgnoreCase("QCD_UN_VILLAGE")) {
                                Pojoloc pojoloc = new Pojoloc();
                                pojoloc.setS1(cursor.getString(0));
                                pojoloc.setS2(cursor.getString(1));

                                String dgp = cursor.getString(2);

                                Cursor cc = dbs.query("masterl", new String[]{"out_master_description", "out_master_code"
                                        }, "out_depend_code" + "=?",
                                        new String[]{pojoloc.getS1()}, null, null, null, null);
                                if (cc.getCount() != 0) {
                                    if (cc.moveToFirst()) {
                                        pojoloc.setS9(cc.getString(1));
                                        pojoloc.setS10(cc.getString(0));
                                    }
                                }

                                Log.e("NULL", "" + dgp);
                                // pojoloc.setS3(cursor.getString(1));
                                Cursor cursorg = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_depend_code"
                                        }, "out_master_code" + "=? COLLATE NOCASE",
                                        new String[]{dgp}, null, null, null, null);
                                if (cursorg.getCount() != 0) {
                                    if (cursorg.moveToFirst()) {
                                        pojoloc.setS3(cursorg.getString(0));
                                        pojoloc.setS4(cursorg.getString(1));
                                        String dt = cursorg.getString(2);

                                        Cursor cursort = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_depend_code"
                                                }, "out_master_code" + "=? COLLATE NOCASE",
                                                new String[]{dt}, null, null, null, null);
                                        if (cursort.getCount() != 0) {
                                            if (cursort.moveToFirst()) {
                                                pojoloc.setS5(cursort.getString(0));
                                                pojoloc.setS6(cursort.getString(1));
                                                String dd = cursort.getString(2);
                                                Log.e("NULL", "" + dd);

                                                Cursor cursord = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_depend_code"
                                                        }, "out_master_code" + "=? COLLATE NOCASE",
                                                        new String[]{dd}, null, null, null, null);
                                                if (cursord.getCount() != 0) {
                                                    if (cursord.moveToFirst()) {
                                                        pojoloc.setS7(cursord.getString(0));
                                                        pojoloc.setS8(cursord.getString(1));
                                                        Log.e("LLOOCC", "" + cursord.getString(2) + "//" + sharedpreferences.getString("lo", ""));
                                                        if (cursord.getString(2).equalsIgnoreCase(sharedpreferences.getString("lo", ""))) {
                                                            pojolocList.add(pojoloc);
                                                        } else {
                                                            Toast.makeText(MainActivity4.this, "Selected Village Not Belongs To Selected Village", Toast.LENGTH_SHORT).show();
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