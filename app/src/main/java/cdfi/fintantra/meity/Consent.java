package cdfi.fintantra.meity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.net.Uri;
import android.nfc.TagLostException;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.common.util.IOUtils;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import cdfi.fintantra.meity.Pawhs.utils.MyConstants;

public class Consent extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton otp,attachment,result;
    String otporattach = "";
    RelativeLayout rlis;
    String atype,adate;
    Uri finaluri;
    Uri picUri;
    ScrollView pscroll,cscroll;
    Button viewattac,vclose;
    File finalfile;
    TextView tdate;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    AutoCompleteTextView doctype,docsubtype,remarks,filename,language;
    int column_index;
    ImageView verify,vimage;
    String encodedImage="";
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    Button agree,disagree;
    CheckBox checkBox,ischeckBox,checkbox1;
    JSONObject jsonParam;
    String imagePath,language_flag = "",flname="";
    String otps;
    String otpflag="Y",attacflag="N",isvflag="N";
    DBHelper dbHelper;
    SQLiteDatabase dbs;
    String resultimg = "";
    ProgressDialog pdialog;
    EditText mobileno;
    TextView ctext;
    TextView title,fname,fmno,fponame;
    String mobno = "",imageFilePath="";
    String[] languagearray = {};
    int reotp = 0;
    String checkval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consent);
        getSupportActionBar().hide();
        radioGroup = findViewById(R.id.radioGroup);
        otp = findViewById(R.id.radioButton1);
        attachment = findViewById(R.id.radioButton2);

        checkBox = findViewById(R.id.checkbox);
        checkbox1 = findViewById(R.id.checkbox1);
        ischeckBox = findViewById(R.id.ischeckbox);
        agree = findViewById(R.id.agree);
        disagree = findViewById(R.id.disagree);
        ctext = findViewById(R.id.ctext);
        title = findViewById(R.id.title);
        fname = findViewById(R.id.fname);
        fmno = findViewById(R.id.fmno);
        verify = findViewById(R.id.verify);
        rlis = findViewById(R.id.rlis);
        viewattac = findViewById(R.id.viewattac);
        vclose = findViewById(R.id.vclose);
        vimage = findViewById(R.id.vimage);
        tdate = findViewById(R.id.tdate);
        pscroll = findViewById(R.id.pscroll);

        ctext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        dbHelper = new DBHelper(this);
        dbs = dbHelper.getWritableDatabase();
        pdialog = new ProgressDialog(this);
        mobileno = (EditText)findViewById(R.id.mobileno);
        fponame = (TextView) findViewById(R.id.fponame);
        language = (AutoCompleteTextView) findViewById(R.id.language);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        languagearray = new String[3];
        languagearray[0] = "English";
        languagearray[1] = "Hindi";
        languagearray[2] = "Tamil";

        language_flag = "en_US";
        try {
            consent_msg();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        vclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vclose.setVisibility(View.GONE);
                vimage.setVisibility(View.GONE);
            }
        });
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        ArrayAdapter<String> langadapter = new ArrayAdapter<String>(Consent.this,R.layout.spinnertext,languagearray);
        language.setThreshold(1);
        language.setAdapter(langadapter);
        /*ctext.setScroller(new Scroller(Consent.this));
        ctext.setMaxLines(10);*/
        ctext.setVerticalScrollBarEnabled(true);
        ctext.setMovementMethod(new ScrollingMovementMethod());
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language.showDropDown();
            }
        });
        language.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = Arrays.asList(languagearray).indexOf(language.getText().toString());
                if(pos == 1){
                    language_flag = "hi_IN";
                }else if(pos == 0){
                    language_flag = "en_US";
                }else if(pos == 2){
                    language_flag = "ta_IN";
                }
                try {
                    consent_msg();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        otp.setChecked(true);
        fponame.setText(""+sharedpreferences.getString("rn",""));

        checkBox.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    checkbox1.setChecked(false);
                    // disable checkbox

                    agree.setText("Agree");
                }
            }
        });

        checkbox1.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    agree.setText("Dis-Agree");
                    checkBox.setChecked(false); // disable checkbox
                }
            }
        });

        title.setText(sharedpreferences.getString("rn",""));
        fname.setText(Pojokyc.farmerid);
        fmno.setText(Pojokyc.farmermobileno);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                int selectedId = radioGroup.getCheckedRadioButtonId();
//                result = (RadioButton) findViewById(selectedId);
//                otporattach = result.getText().toString();
//                if(otporattach.equalsIgnoreCase("OTP")){
//
//
//
//
//                }else if(otporattach.equalsIgnoreCase("Attachment")){
//
//
//                }
//
//            }
//        });
        otpflag = "Y";
        attacflag="N";

        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otpflag = "Y";
                attacflag="N";
            }
        });
        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cr = dbs.rawQuery("select * from attachment where farmercode = '"+Pojokyc.farmercode+"' ",null);
                try{
//                    if(cr.getCount() > 0){
//                        Intent intent = new Intent(Consent.this,AttachmentList.class);
//                        startActivity(intent);
//                    }else {
                        final Dialog dialog = new Dialog(Consent.this);
                        dialog.setContentView(R.layout.attachment);
                        final ImageView attachment;
                        String[] doctypes = {"Address Proof","Business Plan","ID Proof"};
                        String[] docsubtypes = {"Driving License","Family Card","Voter ID"};
                        final Button bsave,bcancel;
                        CheckBox verifycheckbox;
                        doctype = dialog.findViewById(R.id.doctype);
                        docsubtype = dialog.findViewById(R.id.docsubtype);
                        filename = dialog.findViewById(R.id.filename);
                        bsave = dialog.findViewById(R.id.bsave);
                        bcancel = dialog.findViewById(R.id.but_cancl);
                        attachment = dialog.findViewById(R.id.attachment);
                        verifycheckbox = dialog.findViewById(R.id.verifycheckbox);

                        filename.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                attachment.callOnClick();
                            }
                        });

                        verifycheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if(b){
                                    bsave.setVisibility(View.VISIBLE);
                                    isvflag = "Y";
                                }else{
                                    bsave.setVisibility(View.GONE);
                                    isvflag = "N";
                                }
                            }
                        });

                        ArrayAdapter<String> dadapter = new ArrayAdapter<String>(Consent.this,R.layout.spinnertext,doctypes);
                        doctype.setThreshold(1);
                        doctype.setAdapter(dadapter);

                        ArrayAdapter<String> dsadapter = new ArrayAdapter<String>(Consent.this,R.layout.spinnertext,docsubtypes);
                        docsubtype.setThreshold(1);
                        docsubtype.setAdapter(dsadapter);

                        attachment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                           /* if(doctype.getText().toString().equalsIgnoreCase("")){
                                Toast.makeText(Consent.this, "Please Select Doc Type", Toast.LENGTH_SHORT).show();
                            }else if(docsubtype.getText().toString().equalsIgnoreCase("")){
                                Toast.makeText(Consent.this, "Please Select DocSub Type", Toast.LENGTH_SHORT).show();
                            }else{*/
//                                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//                                photoPickerIntent.setType("image/*");
//                                startActivityForResult(photoPickerIntent, 1);
                                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                try {

                                    picUri = FileProvider.getUriForFile(Consent.this, getApplicationContext().getPackageName() + ".provider", createImageFile());
                                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                                    takePictureIntent.putExtra("return-data", true);
                                    startActivityForResult(takePictureIntent, 1);// convert path to Uri
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


//                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                                    intent.setType("*/*");
//                                    startActivityForResult(intent, 1);
                                //   }
                            }
                        });

                        bsave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                           /* if(doctype.getText().toString().equalsIgnoreCase("")){
                                Toast.makeText(Consent.this, "Please Select Doc Type", Toast.LENGTH_SHORT).show();
                            }else if(docsubtype.getText().toString().equalsIgnoreCase("")){
                                Toast.makeText(Consent.this, "Please Select DocSub Type", Toast.LENGTH_SHORT).show();
                            }else*/ if(filename.getText().toString().equalsIgnoreCase("")){
                                    Toast.makeText(Consent.this, "Please Upload File", Toast.LENGTH_SHORT).show();
                                }else{
                                    rlis.setVisibility(View.VISIBLE);
                                    otp.setEnabled(false);
                                    Date c = Calendar.getInstance().getTime();
                                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                    String formattedDate = df.format(c);
                                    dbHelper.insertattachment(String.valueOf(finalfile), formattedDate, flname,"",Pojokyc.farmercode,isvflag);
                                    attacflag = "Y";
                                    otpflag="N";
                                    Toast.makeText(Consent.this, "Attachment Added Successfully", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        });

                        bcancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                  //  }
                }catch (Exception er){
                    Log.e("error",er.toString());
                }finally {
                    cr.close();
                }

            }
        });
        viewattac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    try {
                        Cursor cursor = dbs.rawQuery("select * from attachment where farmercode = '"+Pojokyc.farmercode+"'",null);
                        String path="";
                        if(cursor.getCount()>0) {
                            if (cursor.moveToFirst()) {
                                path = cursor.getString(cursor.getColumnIndexOrThrow("doctype"));
                            }


                            File imgFile = new File(path);
                            if (imgFile.exists()) {
                                Uri uri = FileProvider.getUriForFile(Consent.this, getApplicationContext().getPackageName() + ".provider", imgFile);
                                String mime = getContentResolver().getType(uri);

                                // Open file with user selected app
                                try {
                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_VIEW);
                                    intent.setDataAndType(uri, mime);
                                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    startActivity(intent);
                                } catch (Exception e) {

                                }
                            }
                            else
                            {
                                if(encodedImage.equalsIgnoreCase(""))
                                {

                                }
                                else
                                {
                                    String encodedstring = encodedImage;
                                    byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
                                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);


                                    vimage.setImageBitmap(decodedByte);
                                    vimage.setVisibility(View.VISIBLE);
                                    vclose.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                        else
                        {
                            String encodedstring = encodedImage;
                            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);


                            vimage.setImageBitmap(decodedByte);
                            vimage.setVisibility(View.VISIBLE);
                            vclose.setVisibility(View.VISIBLE);


                        }
                    }
                    catch (Exception e)
                    {

                    }

            }
        });

        if(MyConstants.consentflag.equalsIgnoreCase("0"))
        {
            consent_fetch_api();
        }
        else
        {
            localfetch();
        }
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(otpflag.equalsIgnoreCase("N")&&attacflag.equalsIgnoreCase("N"))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Consent.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("INFO!")
//set message
                            .setMessage("Please Select Atleast One Consent Type")
//set positive button
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //set what would happen when positive button is clicked
                                         //   finish();

                                        }
                                    }
//set negative button

                            )
                            .show();
                   // Toast.makeText(getApplicationContext(),"Please Select Atleast One Consent Type",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(checkBox.isChecked() || checkbox1.isChecked())
                    {
                        if(otpflag.equalsIgnoreCase("Y")) {
                            pdialog.setCanceledOnTouchOutside(false);
                            pdialog.setTitle("Sending OTP Please Wait.......");
                            pdialog.show();
                            //  save.setEnabled(false);
                            reotp = 0;
                            Toast.makeText(Consent.this, "Loading Please Wait.....", Toast.LENGTH_SHORT).show();
                            Random rand = new Random();
                            final int otp = rand.nextInt(9999);
                            final String url = "https://www.smsgatewayhub.com/api/mt/SendSMS?APIKey=cEieSKfwykGjjqMpHHHHHHHH&senderid=GNSAIN&channel=2&DCS=0&flashsms=0&number=91"+Pojokyc.farmermobileno+"&text=Your OTP is "+otp+". Please click below link to view the Consent Form before keying the OTP http://169.38.77.190:82/TAC/TAC -FLEXICODE";
                            Log.e("URL",""+url);
// prepare the Request
                            HttpsTrustManager.allowAllSSL();
                            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                                    new Response.Listener<JSONObject>()
                                    {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            pdialog.dismiss();
                                            attachment.setEnabled(false);

                                            verify.setVisibility(View.VISIBLE);
                                            // display response
                                            final Dialog dialog = new Dialog(Consent.this);
                                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                            dialog.setCancelable(false);
                                            dialog.setContentView(R.layout.otppage);
                                            dialog.setCanceledOnTouchOutside(false);



                                            Button dialogButton = (Button) dialog.findViewById(R.id.botp);
                                            final Button cotp = (Button) dialog.findViewById(R.id.cotp);
                                            final EditText eotp = (EditText) dialog.findViewById(R.id.eotp);
                                            final Button rotp = (Button) dialog.findViewById(R.id.rotp);

                                            rotp.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    resendotp();
                                                }
                                            });
                                            cotp.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    dialog.dismiss();
                                                }
                                            });
                                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String otpval = "";
                                                    if(reotp > 0){
                                                        otpval = String.valueOf(reotp);
                                                    }else{
                                                        otpval = String.valueOf(otp);
                                                    }
                                                    if(eotp.getText().toString().equalsIgnoreCase(otpval))
                                                    {

                                                        otps = eotp.getText().toString();
                                                        dialog.dismiss();
                                                        // save();
                                                      //  Toast.makeText(Consent.this, "Valid OTP", Toast.LENGTH_SHORT).show();
                                                        saveconsent();
                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(Consent.this, "In Valid OTP", Toast.LENGTH_SHORT).show();
                                                        // dialog.dismiss();
                                                    }

                                                }
                                            });

                                            dialog.show();
                                            Log.d("Response", response.toString());
                                        }
                                    },
                                    new Response.ErrorListener()
                                    {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.d("Error.Response", String.valueOf(error));
                                        }
                                    }
                            );

// add it to the RequestQueue
                            getRequest.setRetryPolicy(new DefaultRetryPolicy(
                                    50000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            VolleySingleton.getInstance(Consent.this).addToRequestQueue(getRequest);
                        }
                        else
                        {
                            if(ischeckBox.isChecked()) {
                                saveconsent();
                            }
                            else
                            {
                                final AlertDialog alertDialog = new AlertDialog.Builder(Consent.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("INFO!")
//set message
                                        .setMessage("Please select Is-Verified to continue")
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                      //  finish();

                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                               // Toast.makeText(getApplicationContext(),"Please Fill Isverified CheckBox",Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                    else
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(Consent.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("INFO!")
//set message
                                .setMessage("Please Agree or Dis-Agree The Consent To Continue")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //set what would happen when positive button is clicked
                                              //  finish();

                                            }
                                        }
//set negative button

                                )
                                .show();
                       // Toast.makeText(getApplicationContext(),"Please Agree The Consent To Continue",Toast.LENGTH_LONG).show();


                    }
                }
            }
        });
        disagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
            if (resultCode == Activity.RESULT_OK) {
                //Uri selectedImage = data.getData();

                //String filePath = getPath(selectedImage);
                finalfile = new File(imageFilePath);
                String file_extn = imageFilePath.substring(imageFilePath.lastIndexOf(".") + 1);
                resultimg = imageFilePath.substring(imageFilePath.lastIndexOf("/") + 1);
                filename.setText(resultimg);
                flname = file_extn;
                Log.e("filename", resultimg);
                    try {
                        attacflag = "Y";
                        verify.setVisibility(View.VISIBLE);
                        Bitmap bm = BitmapFactory.decodeFile(imageFilePath);
                        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG, 60, bOut);
                        encodedImage = Base64.encodeToString(bOut.toByteArray(), Base64.DEFAULT);
                    }
                    catch (Exception e)
                    {

                    }

                try {
                    if (file_extn.equals("img") || file_extn.equals("jpg") || file_extn.equals("jpeg") || file_extn.equals("gif") || file_extn.equals("png")) {
                        //FINE
                    } else {
                        //NOT IN REQUIRED FORMAT
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
    }
public  String base64frompath(String filename)
{
    String base64 = "";
    try {/*from   w w w .  ja  va  2s  .  c om*/
        File files = new File(filename);
        byte[] buffer = new byte[(int) files.length() + 100];
        @SuppressWarnings("resource")
        int length = new FileInputStream(files).read(buffer);
        base64 = Base64.encodeToString(buffer, 0, length,
                Base64.DEFAULT);
        Log.e("BASE64",""+base64);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return base64;
}
    public String getPath(Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        column_index = cursor
                .getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        imagePath = cursor.getString(column_index);
        Log.e("imagepath", imagePath);

        return cursor.getString(column_index);
    }

    public void saveconsent()
    {
        if(checkBox.isChecked()){
            checkval = "Y";
            if(otpflag.equalsIgnoreCase("Y"))
            {
                checkval = "AO";
            }
            if(attacflag.equalsIgnoreCase("Y"))
            {
                checkval = "AA";
            }
        }
        else if(checkbox1.isChecked()){
            checkval = "D";
            if(otpflag.equalsIgnoreCase("Y"))
            {
                checkval = "DO";
            }
            if(attacflag.equalsIgnoreCase("Y"))
            {
                checkval = "DA";
            }
        }
        Log.e("checkval",""+checkval);
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Submitting.......");
        pdialog.show();
        try {
            reotp = 0;
            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", "flexi");
            user.put("locnId", "chennai");
            user.put("userId", "admin");
            user.put("localeId", "en_US");
            user.put("instance", Pojokyc.instance);
            userd.put("context",user);
            JSONObject user2 = new JSONObject();
            user2.put("in_farmerconsent_rowid",0);
            user2.put("in_template_id",Pojokyc.templateid);
            user2.put("in_consent_owner_id",Pojokyc.farmercode);
            user2.put("in_consent_to",sharedpreferences.getString("oc1",""));
            user2.put("in_lang_code",language_flag);
            user2.put("in_otp",otps);
            user2.put("in_otp_flag",otpflag);
            user2.put("in_isverified",checkval);
            user2.put("in_attach_consent",encodedImage);
            user2.put("in_attachment_flag",attacflag);
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
            String formattedDate = df.format(c);
            user2.put("in_verified_date",formattedDate);
            user2.put("in_attach_type",flname);
            user2.put("In_mobile_no",Pojokyc.farmermobileno);
            user2.put("in_status_code","A");
            user2.put("in_mode_flag","I");
            user.put("header",user2);
            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
        }
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,Pojokyc.url+"/api/FDR_Constent/FarmerConstentsave",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("header");
                            if(!obj2.getString("in_farmerconsent_rowid").equalsIgnoreCase("0"))
                            {

                                Cursor cursor = dbs.rawQuery("Select * from templatefarmer where in_consent_owner_id = '"+Pojokyc.farmercode+"'",null);
                                if(cursor.getCount()>0)
                                {
                                    if(cursor.moveToFirst())
                                    {
                                        if(otpflag.equalsIgnoreCase("Y")) {
                                            dbs.execSQL("update templatefarmer set in_otp_flag = 'Y' where in_consent_owner_id = '" + Pojokyc.farmercode + "'");
                                        }
                                        else {
                                            dbs.execSQL("update templatefarmer set in_attachment_flag = 'Y' where in_consent_owner_id = '" + Pojokyc.farmercode + "'");
                                        }
                                    }
                                }
                                else
                                {
                                    dbHelper.inserttemplatefarmer("0",Pojokyc.templateid,Pojokyc.farmercode,sharedpreferences.getString("oc1",""),"en_US",ctext.getText().toString(),otpflag,checkval,attacflag);
                                }
                                pdialog.dismiss();

                                dbs.execSQL("update farlist set v3 = '"+checkval+"' where fc = '"+Pojokyc.farmercode+"'");

                                final AlertDialog alertDialog = new AlertDialog.Builder(Consent.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("INFO!")
//set message
                                        .setMessage("Success")
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

                            }
                            else
                            {
                                pdialog.dismiss();
                                final AlertDialog alertDialog = new AlertDialog.Builder(Consent.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("INFO!")
//set message
                                        .setMessage("Failed")
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


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);
                        pdialog.dismiss();
                        final AlertDialog alertDialog = new AlertDialog.Builder(Consent.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Error!")
//set message
                                .setMessage("Something Went Wrong")
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
    public void consent_msg() throws ParseException {
        Cursor cursor = dbs.rawQuery("Select * from template where lang_code = '"+language_flag+"'",null);
        if(cursor.getCount()>0)
        {
            if(cursor.moveToFirst())
            {
                do {
//                String tempmsg1 = "",tempmsg2 = "",tempmsg3 = "";
//                if(language_flag.equalsIgnoreCase("en_Us")){
//                    tempmsg1 = "From ";
//                    tempmsg2 = " To ";
//                    tempmsg3 = "We do not collect personal information for any purpose other than to respond to you (for example, to respond to your queries). If you choose to provide us with personal information like filling out a Contact Us form with an e-mail address or postal address, and submitting it to us through the website, we use that information to respond to your message, and to help you to get the information you have requested. Our website never collects information or creates individual profiles for commercial marketing. While you must provide an email address for a localised response to any incoming questions or comments to us, we recommend that you do NOT include any other personal information.";
//                }else if(language_flag.equalsIgnoreCase("ta_In")){
//                    tempmsg1 = "இருந்து";
//                    tempmsg2 = " செய்ய ";
//                    tempmsg3 = "உங்களுக்குப் பதிலளிப்பதைத் தவிர (உதாரணமாக, உங்கள் கேள்விகளுக்குப் பதிலளிக்க) தனிப்பட்ட தகவல்களை நாங்கள் சேகரிப்பதில்லை. மின்னஞ்சல் முகவரி அல்லது அஞ்சல் முகவரியுடன் எங்களைத் தொடர்புகொள்ளும் படிவத்தை நிரப்பி, இணையதளம் மூலம் எங்களுக்குச் சமர்ப்பிப்பது போன்ற தனிப்பட்ட தகவலை எங்களுக்கு வழங்க நீங்கள் தேர்வுசெய்தால், உங்கள் செய்திக்கு பதிலளிக்கவும், உங்களுக்கு உதவவும் அந்தத் தகவலைப் பயன்படுத்துகிறோம். நீங்கள் கோரிய தகவலைப் பெறுங்கள். எங்கள் வலைத்தளம் ஒருபோதும் தகவல்களைச் சேகரிக்காது அல்லது வணிகச் சந்தைப்படுத்துதலுக்கான தனிப்பட்ட சுயவிவரங்களை உருவாக்காது. எங்களிடம் வரும் ஏதேனும் கேள்விகள் அல்லது கருத்துகளுக்கு உள்ளூர்மயமாக்கப்பட்ட பதிலுக்கான மின்னஞ்சல் முகவரியை நீங்கள் வழங்க வேண்டும் என்றாலும், வேறு எந்த தனிப்பட்ட தகவலையும் சேர்க்க வேண்டாம் என்று பரிந்துரைக்கிறோம்.";
//                }
//                else if(language_flag.equalsIgnoreCase("hi_In")){
//                    tempmsg1 = "से ";
//                    tempmsg2 = " सेवा ";
//                    tempmsg3 = "हम आपको जवाब देने के अलावा किसी अन्य उद्देश्य के लिए व्यक्तिगत जानकारी एकत्र नहीं करते हैं (उदाहरण के लिए, आपके प्रश्नों का उत्तर देने के लिए)। यदि आप हमें व्यक्तिगत जानकारी प्रदान करना चुनते हैं, जैसे ई-मेल पते या डाक पते के साथ हमसे संपर्क करें फॉर्म भरना, और इसे वेबसाइट के माध्यम से हमें सबमिट करना, तो हम उस जानकारी का उपयोग आपके संदेश का जवाब देने के लिए करते हैं, और आपकी मदद करने के लिए आपके द्वारा मांगी गई जानकारी प्राप्त करें। हमारी वेबसाइट कभी भी जानकारी एकत्र नहीं करती है या वाणिज्यिक विपणन के लिए व्यक्तिगत प्रोफाइल नहीं बनाती है। जबकि आपको हमें आने वाले किसी भी प्रश्न या टिप्पणियों के स्थानीयकृत प्रतिक्रिया के लिए एक ईमेल पता प्रदान करना होगा, हम अनुशंसा करते हैं कि आप कोई अन्य व्यक्तिगत जानकारी शामिल न करें।";
//
//                }
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a", Locale.getDefault());
                    String currentDateandTime = sdf.format(new Date());
                    Date date1 = sdf.parse(currentDateandTime);
                    Date date2 = sdf.parse(cursor.getString(cursor.getColumnIndexOrThrow("effective_to")));
                    if (date1.compareTo(date2) < 0) {

                       // Toast.makeText(this, "" + currentDateandTime, Toast.LENGTH_SHORT).show();
                        Pojokyc.templateid = cursor.getString(cursor.getColumnIndexOrThrow("template_id"));
                        ctext.setMovementMethod(new ScrollingMovementMethod());
                        ctext.setText("" + cursor.getString(cursor.getColumnIndexOrThrow("template_consent")).replaceAll("###FPONAME###", sharedpreferences.getString("rn", "")).replaceAll("###FARMERNAME###", Pojokyc.farmerid).replaceAll("###FARMERADDRESS###", Pojokyc.farmeraddress));
                    }
                    //ctext.setText(tempmsg1 + Pojokyc.farmerid + tempmsg2 + sharedpreferences.getString("oc1","") + tempmsg3);

                }while (cursor.moveToNext());
            }
        }
    }
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case 1:
//                if (resultCode == -1) {
//                    Uri fileUri = data.getData();
//                    finaluri = fileUri;
//                    Log.e("URIDA", "" + fileUri);
//                    String fullPath = FileUtils.getPath(this, fileUri);
//                    File file = new File(fullPath);
//
//                    int file_size = Integer.parseInt(String.valueOf(file.length() / 1024));
//                    if (file_size > 300) {
//                        final AlertDialog alertDialog = new AlertDialog.Builder(Consent.this)
////set icon
//                                .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                .setTitle("INFO!")
////set message
//                                .setMessage("Please Select The File Below 300 KB")
////set positive button
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                //set what would happen when positive button is clicked
//                                                // finish();
//                                            }
//                                        }
////set negative button
//
//                                )
//                                .show();
//                       //   HCDialog.showDialog(AttachmentSaveActivity.this, "Please Select The File Below 1MB", "0");
//                    } else {
//                        viewattac.setVisibility(View.VISIBLE);
//                        Log.e("FP", "FP=" + fullPath.substring(fullPath.lastIndexOf("/") + 1));
//                        filename.setText("" + fullPath.substring(fullPath.lastIndexOf("/") + 1));
//                        //size.setText("" + file_size + " KB");
//                        encodedImage = base64frompath(fullPath);
//                        File f = new File(Environment.getExternalStorageDirectory(), "PGA" + "/" + "01");
//                        if (!f.exists()) {
//                            f.mkdirs();
//                        }
//                        // String sourcePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TongueTwister/tt_temp.3gp";
//                        File source = new File(fullPath);
//                        finalfile  = source;
//
//                        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TongueTwister/tt_1A.3gp";
//                        File destination = new File(f + "/" + fullPath.substring(fullPath.lastIndexOf("/") + 1));
//                        flname = f + "/" + fullPath.substring(fullPath.lastIndexOf("/") + 1);
//                        Log.e("flname1", flname);
//                        try {
//                            copyFile(source, destination);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//                break;
//        }
//    }

    public void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.getParentFile().exists())
            destFile.getParentFile().mkdirs();

        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    private String GetString(String filepath) throws IOException {
        InputStream inputStream = new FileInputStream(filepath);
        byte[] byteArray = IOUtils.toByteArray(inputStream);
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        Log.e("ENCODED", "" + encoded);
        return encoded;
    }
    public void consent_fetch_api(){
        JSONObject consobj = new JSONObject();
        try {
            consobj.put("orgnId",sharedpreferences.getString("oc1",""));
            consobj.put("locnId","chennai");
            consobj.put("userId","admin");
            consobj.put("localeId","en_US");
            consobj.put("instance",Pojokyc.instance);
            consobj.put("In_farmer_code",Pojokyc.farmercode);
            Log.e("OUTPUTK",""+consobj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest consentfetchreq = new JsonObjectRequest(Request.Method.POST, ApiUtils.TEST_URL_API + "FDR_Constent/FarmerConstentfetch", consobj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("responsecons",response.toString());
                        try {
                            JSONObject context = response.getJSONObject("context");
                            JSONArray fdrconstentDtl = context.getJSONArray("fdrconstentDtl");
                           // for(int i =0 ; i < fdrconstentDtl.length(); i++){
                                JSONObject cons = fdrconstentDtl.getJSONObject(0);
                            Cursor cursor = dbs.rawQuery("select * from templatefarmer where in_consent_owner_id = '"+Pojokyc.farmercode+"'",null);

                            if(cursor.getCount()>0)
                            {
                                dbs.execSQL("delete from templatefarmer where in_consent_owner_id = '"+Pojokyc.farmercode+"'");
                                dbHelper.inserttemplatefarmer(cons.getString("in_attach_type")+"/"+cons.getString("in_verified_date"),cons.getString("in_template_id"),cons.getString("in_consent_owner_id"),cons.getString("in_consent_to"),cons.getString("in_lang_code"),cons.getString("template_consent"),cons.getString("in_otp_flag"),"Y",cons.getString("in_attachment_flag"));

                            }
                            else
                            {
                                dbHelper.inserttemplatefarmer(cons.getString("in_attach_type")+"/"+cons.getString("in_verified_date"),cons.getString("in_template_id"),cons.getString("in_consent_owner_id"),cons.getString("in_consent_to"),cons.getString("in_lang_code"),cons.getString("template_consent"),cons.getString("in_otp_flag"),"Y",cons.getString("in_attachment_flag"));

                            }
                            encodedImage = cons.getString("in_attach_consent");
                            if(cons.getString("in_lang_code").equalsIgnoreCase("en_US")) {
                                language.setText("English");
                            }
                            else if(cons.getString("in_lang_code").equalsIgnoreCase("hi_IN"))
                            {
                                language.setText("Hindi");
                            }
                            else
                            {
                                language.setText("Tamil");
                            }
                            ctext.setMovementMethod(new ScrollingMovementMethod());
                            ctext.setText(cons.getString("template_consent").replaceAll("###FPONAME###",sharedpreferences.getString("rn","")).replaceAll("###FARMERNAME###",Pojokyc.farmerid).replaceAll("###FARMERADDRESS###",Pojokyc.farmeraddress));
                            tdate.setVisibility(View.VISIBLE);
                            tdate.setText("Date of Consent"+cons.getString("in_verified_date"));
                            localfetch();

//                                if(cons.getString("in_otp_flag").equalsIgnoreCase("Y"))
//                                {
//                                 otpflag = "N";
//                                 otp.setEnabled(false);
//                                }
//                            if(cons.getString("in_attachment_flag").equalsIgnoreCase("Y"))
//                            {
//                             attacflag = "N";
//                                attachment.setEnabled(false);
//                            }
//                            if(cons.getString("in_otp_flag").equalsIgnoreCase("Y")&&cons.getString("in_attachment_flag").equalsIgnoreCase("Y"))
//                            {
//                               agree.setEnabled(false);
//                            }
//                            if(!cons.getString("in_attach_consent").equalsIgnoreCase(""))
//                            {
//                                viewattac.setVisibility(View.VISIBLE);
//                            }

                          //  }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        consentfetchreq.setRetryPolicy(new DefaultRetryPolicy(
                80000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Consent.this).addToRequestQueue(consentfetchreq);
    }
    public void localfetch()
    {
        Cursor cursor = dbs.rawQuery("select * from templatefarmer where in_consent_owner_id = '"+Pojokyc.farmercode+"'",null);

        if(cursor.getCount()>0)
        {
            if(cursor.moveToFirst())
            {
                try {
                   // tdate.setVisibility(View.VISIBLE);
                   // tdate.setText(""+adate);
//                    String[] div = cursor.getString(0).split("/");
//                    atype = div[0];
//                    adate = div[1];


                }
                catch (Exception e)
                {

                }

                if(cursor.getString(cursor.getColumnIndexOrThrow("in_otp_flag")).equalsIgnoreCase("Y"))
                {
                    otpflag = "N";
                    otp.setEnabled(false);
                    attacflag = "N";
                    otp.setChecked(true);

                    attachment.setEnabled(false);
                    agree.setEnabled(false);
                    if(cursor.getString(cursor.getColumnIndexOrThrow("in_isverified")).equalsIgnoreCase("DO")) {
                        checkBox.setChecked(true);
                    }
                    if(cursor.getString(cursor.getColumnIndexOrThrow("in_isverified")).equalsIgnoreCase("AO")) {
                        checkbox1.setChecked(true);
                        agree.setText("Dis-Agree");
                    }
                    checkBox.setEnabled(false);
                    checkbox1.setEnabled(false);
                    language.setEnabled(false);
                }
                if(cursor.getString(cursor.getColumnIndexOrThrow("in_attachment_flag")).equalsIgnoreCase("Y"))
                {

                    if(cursor.getString(cursor.getColumnIndexOrThrow("in_isverified")).equalsIgnoreCase("DA")){
                        checkBox.setChecked(true);
                    }
                    if(cursor.getString(cursor.getColumnIndexOrThrow("in_isverified")).equalsIgnoreCase("AA")) {
                        checkbox1.setChecked(true);
                        agree.setText("Dis-Agree");
                    }
                    attachment.setChecked(true);
                    otpflag = "N";
                    otp.setEnabled(false);
                    attacflag = "N";
                    language.setEnabled(false);
                    attachment.setEnabled(false);
                    viewattac.setVisibility(View.VISIBLE);
                    checkbox1.setEnabled(false);
                    agree.setEnabled(false);
                    checkBox.setChecked(true);
                    checkBox.setEnabled(false);
                }
                if(cursor.getString(cursor.getColumnIndexOrThrow("in_otp_flag")).equalsIgnoreCase("Y")&&cursor.getString(cursor.getColumnIndexOrThrow("in_attachment_flag")).equalsIgnoreCase("Y"))
                {
                    agree.setEnabled(false);
                }

            }
        }

    }
    public  void resendotp()
    {
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Sending OTP Please Wait.......");
        pdialog.show();
        //  save.setEnabled(false);

        Toast.makeText(Consent.this, "Loading Please Wait.....", Toast.LENGTH_SHORT).show();
        Random rand = new Random();
        final int otp = rand.nextInt(9999);
        final String url = "https://www.smsgatewayhub.com/api/mt/SendSMS?APIKey=cEieSKfwykGjjqMpHHHHHHHH&senderid=GNSAIN&channel=2&DCS=0&flashsms=0&number=91"+Pojokyc.farmermobileno+"&text=Your OTP is "+otp+". Please click below link to view the Consent Form before keying the OTP http://169.38.77.190:82/TAC/TAC -FLEXICODE";
        Log.e("URL",""+url);
        reotp = otp;
// prepare the Request
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        pdialog.dismiss();

                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );

// add it to the RequestQueue
        getRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Consent.this).addToRequestQueue(getRequest);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mScaleGestureDetector.onTouchEvent(event);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        // when a scale gesture is detected, use it to resize the image
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            vimage.setScaleX(mScaleFactor);
            vimage.setScaleY(mScaleFactor);
            return true;
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
}