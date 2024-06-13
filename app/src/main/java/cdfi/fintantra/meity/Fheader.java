package cdfi.fintantra.meity;

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
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
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

public class Fheader extends AppCompatActivity {

    Button exit;
    DatePickerDialog.OnDateSetListener date;
    IntentIntegrator qrScan;
    ImageView qrcode;
    private GpsTracker gpsTracker;
    EditText elat, elon;
    ImageView captureld;
    ImageView captureh, capturek;
    int imagetype;
    AutoCompleteTextView gender;
    Button save;
    JSONObject jsonParam;

    DBHelper db;

    String fan;

    String farmerid;

    final int CAMERA_CAPTURE = 1;

    EditText farmername, surname, fathername, mobileno, pincode, edob, eaddress, taluk, grama, district, hamlet;
    AutoCompleteTextView village, elc;
    String[] genders = {"Male", "Female", "Transgender"};


    String mv, md, mg, mt, ham;
    int faid = 0;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    String ui = "0", ui2 = "0", ui3 = "0";
    String encodedImage = "0", encodedImage2 = "0", encodedImage3 = "0";
    ByteArrayOutputStream byteArrayOutputStream, byteArrayOutputStream2, byteArrayOutputStream3;
    String imageFilePath;
    Bitmap thePic, thePic2, thePic3;
    Uri picUri;

    String regex
            = "^[A-PR-WYa-pr-wy][1-9]\\d"
            + "\\s?\\d{4}[1-9]$";

    ProgressDialog pdialog;
    Dialog dialog;

    List<Pojoloc> pojolocList;
    Calendar myCalendar, myCalendar2;

    Fheader.MyRecyclerViewAdapterd adapterd;
    SQLiteDatabase dbs;
    String modeflag = "I";
    ArrayList<String> arrayn;

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fheader);
        pojolocList = new ArrayList<>();
        getSupportActionBar().hide();
        arrayn = new ArrayList<>();
        pdialog = new ProgressDialog(this);
        farmername = (EditText) findViewById(R.id.farmername);
        surname = (EditText) findViewById(R.id.surname);
        fathername = (EditText) findViewById(R.id.fathername);
        mobileno = (EditText) findViewById(R.id.mobileno);
        pincode = (EditText) findViewById(R.id.pincode);
        edob = (EditText) findViewById(R.id.dob);
        eaddress = (EditText) findViewById(R.id.address);
        village = (AutoCompleteTextView) findViewById(R.id.village);
        grama = (EditText) findViewById(R.id.grama);
        district = (EditText) findViewById(R.id.district);
        hamlet = (EditText) findViewById(R.id.hamlet);
        captureh = (ImageView) findViewById(R.id.captureh);
        save = (Button) findViewById(R.id.save);
        exit = (Button) findViewById(R.id.exit);
        taluk = (EditText) findViewById(R.id.taluk);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mobileno.setInputType(InputType.TYPE_CLASS_NUMBER);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        db = new DBHelper(this);
        dbs = db.getWritableDatabase();
        myCalendar2 = Calendar.getInstance();
        qrcode = (ImageView) findViewById(R.id.qrcode);
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1005);
                qrScan = new IntentIntegrator(Fheader.this);
                qrScan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                qrScan.setPrompt("Scan Farmer Code");
                qrScan.setBeepEnabled(true);
                qrScan.setCameraId(0);
                qrScan.setOrientationLocked(false);
                qrScan.setBarcodeImageEnabled(true);


                qrScan.initiateScan();
            }
        });

        myCalendar = Calendar.getInstance();
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
        edob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Fheader.this,android.R.style.Theme_Holo_Dialog , date, myCalendar
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
                    picUri = FileProvider.getUriForFile(Fheader.this, getApplicationContext().getPackageName() + ".provider", createImageFile());
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                    takePictureIntent.putExtra("return-data", true);
                    startActivityForResult(takePictureIntent, CAMERA_CAPTURE);// convert path to Uri
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        gender = (AutoCompleteTextView) findViewById(R.id.gender);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, R.layout.spinnertext3, genders);
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

        village.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(Fheader.this);
                dialog.setContentView(R.layout.suppliersearch2);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(Fheader.this));
                adapterd = new Fheader.MyRecyclerViewAdapterd(Fheader.this, pojolocList);
                dialog.getWindow().setLayout(width, height);

                elc = (AutoCompleteTextView) dialog.findViewById(R.id.elc);
                arrayn.clear();
                Cursor cc = dbs.query("masterl", new String[]{"out_master_description"
                        }, "out_parent_code" + "=?",
                        new String[]{"QCD_UN_VILLAGE"}, null, null, null, null);
                if (cc.getCount() != 0) {
                    if (cc.moveToFirst()) {
                        do {

                            if (arrayn.contains(cc.getString(0))) {

                            } else {
                                arrayn.add(cc.getString(0));
                            }
                            // Log.e("VAL",""+cursor.getString(11));

                        } while (cc.moveToNext());
                    }
                }


                ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(Fheader.this,
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
                                                do {

                                                    pojoloc.setS9(cc.getString(1));
                                                    pojoloc.setS10(cc.getString(0));


                                                } while (cc.moveToNext());
                                            }
                                        }

                                        Log.e("NULL", "" + dgp);
                                        // pojoloc.setS3(cursor.getString(1));
                                        Cursor cursorg = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_depend_code"
                                                }, "out_master_code" + "=? COLLATE NOCASE",
                                                new String[]{dgp}, null, null, null, null);
                                        if (cursorg.getCount() != 0) {
                                            if (cursorg.moveToFirst()) {
                                                do {


                                                    pojoloc.setS3(cursorg.getString(0));
                                                    pojoloc.setS4(cursorg.getString(1));
                                                    String dt = cursorg.getString(2);

                                                    // pojoloc.setS3(cursor.getString(1));
                                                    Cursor cursort = dbs.query("masterl", new String[]{"out_master_code", "out_master_description", "out_depend_code"
                                                            }, "out_master_code" + "=? COLLATE NOCASE",
                                                            new String[]{dt}, null, null, null, null);
                                                    if (cursort.getCount() != 0) {
                                                        if (cursort.moveToFirst()) {
                                                            do {


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
                                                                        do {


                                                                            pojoloc.setS7(cursord.getString(0));
                                                                            pojoloc.setS8(cursord.getString(1));


                                                                            // pojoloc.setS3(cursor.getString(1));


                                                                            pojolocList.add(pojoloc);
                                                                            // array2.add(cursor.getString(11));
                                                                            // Log.e("VAL",""+cursor.getString(11));
                                                                            // recyclerView.setAdapter(adapterd);
                                                                            // Log.e("VAL",""+cursor.getString(11));

                                                                        } while (cursord.moveToNext());
                                                                    }


                                                                }
                                                                // array2.add(cursor.getString(11));
                                                                // Log.e("VAL",""+cursor.getString(11));
                                                                // recyclerView.setAdapter(adapterd);
                                                                // Log.e("VAL",""+cursor.getString(11));

                                                            } while (cursort.moveToNext());
                                                        }


                                                    }

                                                    // pojolocList.add(pojoloc);
                                                    // array2.add(cursor.getString(11));
                                                    // Log.e("VAL",""+cursor.getString(11));
                                                    // recyclerView.setAdapter(adapterd);
                                                    // Log.e("VAL",""+cursor.getString(11));

                                                } while (cursorg.moveToNext());
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
                    final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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
                else if (fathername.getText().toString().equalsIgnoreCase("")) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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
                    final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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
                    final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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
                    final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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
                else if (pincode.getText().toString().equalsIgnoreCase("") || pincode.getText().toString().length() < 6) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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
                    final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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
                    final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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
                    final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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
                    final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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
                        save();
                    } else {

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
                        String n7 = sharedpreferences.getString("orgn","");
                        String n8 = pincode.getText().toString();
                        String n9 = eaddress.getText().toString();
                        String n10 = village.getText().toString();
                        String n11 = grama.getText().toString();
                        String n12 = taluk.getText().toString();
                        String n13 = district.getText().toString();
                        String n14 = "Uttar Pradesh";
                        String n15 = "India";
                        String n16 = "0";
                        String n17 = "0";
                        String n18 = mv;
                        String n19 = mg;
                        String n20 = mt;
                        String n21 = md;
                        String n22 = ts;
                        String n23 = hamlet.getText().toString();
                        String n25 = sharedpreferences.getString("oc1","");
                        String n26 = "";
                        String n27 = "";


                        try {
                            db.insertfarmer(n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16,n17,n18,n19,n20,n21,n22,n23,n25,n26,n27,ui);

                            db.insertcustomernm("0", n1, n10, n9,n14 , n18, "QCD_UNS_UP", "NM", n4, sharedpreferences.getString("ocnew", ""),n2,n3);

                            //spinner.setEnabled(true);
                            editor.commit();
                            final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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

                                                    finish();

                                                    //fname.setText(farmername.getText().toString());
                                                    //set what would happen when positive button is clicked

                                                }
                                            }
//set negative button

                                    )
                                    .show();
                        } catch (SQLiteException exception) {
                            Log.d("SQLite", "Error"+exception.toString());
                            final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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
                    //Toast.makeText(MainActivity3.this, "Success", Toast.LENGTH_SHORT).show();
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

    public class MyRecyclerViewAdapterd extends RecyclerView.Adapter<Fheader.MyRecyclerViewAdapterd.ViewHolder> {

        private List<Pojoloc> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapterd(Context context, List<Pojoloc> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public Fheader.MyRecyclerViewAdapterd.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.suplist2, parent, false);
            return new Fheader.MyRecyclerViewAdapterd.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(final Fheader.MyRecyclerViewAdapterd.ViewHolder holder, final int position) {
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
            user.put("instance", "up");
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
            user2.put("in_farmer_state", "QCD_UNS_OR");
            user2.put("in_farmer_dist", md);
            user2.put("in_farmer_taluk", mt);
            user2.put("in_farmer_panchayat", mg);
            user2.put("in_farmer_village", mv);
            user2.put("in_farmer_pincode", pincode.getText().toString());
            user2.put("in_marital_status", "");

            if (gender.getText().toString().equalsIgnoreCase("Male")) {
                user2.put("in_gender_flag", "QCD_GENDER_MALE");
            } else if (gender.getText().toString().equalsIgnoreCase("female")) {
                user2.put("in_gender_flag", "QCD_GENDER_FEMALE");
            } else if (gender.getText().toString().equalsIgnoreCase("transgender")) {
                user2.put("in_gender_flag", "QCD_GENDER_TRANSGENDER");
            }

            user2.put("in_reg_mobile_no", mobileno.getText().toString());
            user2.put("in_status_code", "A");
            user2.put("in_mode_flag", modeflag);
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

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "/api/Mobile_FDR_FHeader/NewMobileFarmerHeader", jsonParam,
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


                            if (frid.equalsIgnoreCase("0")) {
                                JSONObject obj3 = response.getJSONObject("applicationException");
                                final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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
                                String n7 = sharedpreferences.getString("orgn", "");
                                String n8 = pincode.getText().toString();
                                String n9 = eaddress.getText().toString();
                                String n10 = village.getText().toString();
                                String n11 = grama.getText().toString();
                                String n12 = taluk.getText().toString();
                                String n13 = district.getText().toString();
                                String n14 = "Uttar Pradesh";
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


                                db.insertcustomernm(fn, n1, n10, n9,n14 , n18, "QCD_UNS_UP", "NM", n4, sharedpreferences.getString("ocnew", ""),n2,n3);
                                final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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
                                                        finish();

                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                            }


                        } catch (Exception e) {
                            pdialog.dismiss();
                            final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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
                        final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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
                .start(Fheader.this);
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
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
                    toast.show();
                }

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
                            gpsTracker = new GpsTracker(Fheader.this);
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

    protected void processScannedData(String scanData) {
        Log.d("Rajdeol", scanData);
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
            } catch (Exception e) {
                final AlertDialog alertDialog = new AlertDialog.Builder(Fheader.this)
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

                String tx1 = null, tx2 = null, tx3 = null, tx4 = null, tx5 = null, tx6 = null, tx7 = null, tx8 = null, tx9, tx10 = null, tx11 = null, tx12 = null;

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
                } catch (Exception e) {
                    tx2 = "";
                }

                try {
                    tx5 = obj.getString("po");
                } catch (Exception e) {
                    tx5 = "";
                }
                try {
                    tx6 = obj.getString("pc");
                } catch (Exception e) {
                    tx6 = "";
                }
                try {
                    tx7 = obj.getString("dist");
                } catch (Exception e) {
                    tx7 = "";
                }
                try {
                    tx8 = obj.getString("state");
                } catch (Exception e) {
                    tx8 = "";
                }
                tx9 = obj.toString();

                try {
                    tx4 = obj.getString("house") + "," + obj.getString("street");
                    Log.e("AAdhar", "" + obj.getString("house") + "," + obj.getString("street"));
                } catch (Exception e) {
                    tx4 = "";
                }
                try {
                    Log.e("AAdhar", "" + obj.getString("lm"));
                } catch (Exception e) {

                }
                try {
                    if (obj.getString("dob").contains("-")) {
                        final String[] dob = obj.getString("dob").split("-");
                        tx3 = dob[0] + "/" + dob[1] + "/" + dob[2];

                        edob.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DatePickerDialog datePickerDialog = new DatePickerDialog(Fheader.this, android.R.style.Theme_Holo_Dialog, date, myCalendar
                                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                                        myCalendar2.get(Calendar.DAY_OF_MONTH));
                                datePickerDialog.updateDate(Integer.parseInt(dob[2]), Integer.parseInt(dob[1]) - 1, Integer.parseInt(dob[0]));

                                datePickerDialog.show();

                            }
                        });
                    } else {
                        final String[] dob = obj.getString("dob").split("/");
                        tx3 = dob[0] + "/" + dob[1] + "/" + dob[2];

                        edob.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DatePickerDialog datePickerDialog = new DatePickerDialog(Fheader.this, android.R.style.Theme_Holo_Dialog, date, myCalendar
                                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                                        myCalendar2.get(Calendar.DAY_OF_MONTH));
                                datePickerDialog.updateDate(Integer.parseInt(dob[2]), Integer.parseInt(dob[1]) - 1, Integer.parseInt(dob[0]));

                                datePickerDialog.show();

                            }
                        });
                    }

                    Log.e("AAdhar", "" + obj.getString("dob"));
                } catch (Exception e) {


                    edob.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DatePickerDialog datePickerDialog = new DatePickerDialog(Fheader.this, android.R.style.Theme_Holo_Dialog, date, myCalendar
                                    .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                                    myCalendar2.get(Calendar.DAY_OF_MONTH));
                            try {
                                datePickerDialog.updateDate(Integer.parseInt(obj.getString("yob")), 0, Integer.parseInt("01"));
                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }
                            datePickerDialog.show();

                        }
                    });

                    tx3 = "01/01/" + obj.getString("yob");
                }
                try {
                    tx10 = obj.getString("gender");
                } catch (Exception e) {
                    tx10 = "Tap To Select Gender";
                }
                try {
                    tx11 = obj.getString("gname");
                } catch (Exception e) {
                    tx11 = "";
                }
                try {
                    tx12 = obj.getString("lm");
                } catch (Exception e) {
                    tx12 = "";
                }
                showaadhar(tx1, tx2, tx3, tx4, tx5, tx6, tx7, tx8, tx9, tx10, tx11, tx12);


            } catch (Throwable t) {
                Log.e("Help", "Could not parse malformed JSON: \"" + fstring2 + "\"");
            }


            parser.setInput(new StringReader(scanData));


            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    Log.d("Rajdeol", "Start document");

                } else if ((eventType == XmlPullParser.START_TAG && DataAttributes.AADHAAR_DATA_TAG.equals(parser.getName()))) {


                } else if (eventType == XmlPullParser.END_TAG) {
                    Log.d("Rajdeol", "End tag " + parser.getName());
                } else if (eventType == XmlPullParser.TEXT) {
                    Log.d("Rajdeol", "Text " + parser.getText());
                }
                // update eventType
                eventType = parser.next();
            }
            // display the data on screen
            //  displayScannedData();
        } catch (XmlPullParserException e) {

            Log.e("AAdhar", "" + e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//

    public void showaadhar(String tx1, String tx2, String tx3, String tx4, String tx5, String tx6, String tx7, String tx8, String tx9, String tx10, String tx11, String tx12) {

        String n1 = tx1.replaceAll(",", " ");
        String n2 = tx2.replaceAll(",", " ");
        String n3 = tx3.replaceAll(",", " ");
        String n6 = tx6.replaceAll(",", " ");
        String n10 = tx10.replaceAll(",", " ");
        String n11 = tx11.replaceAll(",", " ");
        String n4 = tx4.replaceAll(",", " ");
        String n5 = tx5.replaceAll(",", " ");
        String n7 = tx7.replaceAll(",", " ");
        String n12 = tx12.replaceAll(",", " ");

        eaddress.setText(n4 + " " + n12 + " " + n5 + " " + n7);
        farmername.setText(n1);
        if (n2.equalsIgnoreCase("")) {
            fathername.setText(n11);
        } else {
            fathername.setText(n2);
        }
        pincode.setText(n6);
        edob.setText(n3);
        if (n10.equalsIgnoreCase("M") || n10.equalsIgnoreCase("Male")) {
            gender.setText("Male");
        } else {
            gender.setText("Female");
        }

        Intent i = new Intent(getApplicationContext(), QRView.class);
        i.putExtra("tx1", tx1);
        i.putExtra("tx2", tx2);
        i.putExtra("tx3", tx3);
        i.putExtra("tx4", tx4);
        i.putExtra("tx5", tx5);
        i.putExtra("tx6", tx6);
        i.putExtra("tx7", tx7);
        i.putExtra("tx8", tx8);
        i.putExtra("tx9", tx9);
        // startActivity(i);


    }
}