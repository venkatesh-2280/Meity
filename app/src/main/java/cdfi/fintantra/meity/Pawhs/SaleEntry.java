package cdfi.fintantra.meity.Pawhs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cdfi.fintantra.meity.ExceptionHandler;
import cdfi.fintantra.meity.HttpsTrustManager;
import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import cdfi.fintantra.meity.Pawhs.model.PmListDao;
import cdfi.fintantra.meity.Pawhs.utils.DecimalDigitsInputFilter;
import cdfi.fintantra.meity.Pawhs.utils.Utility;
import cdfi.fintantra.meity.Pojobuyer;
import cdfi.fintantra.meity.Pojokyc;
import cdfi.fintantra.meity.R;
import cdfi.fintantra.meity.VolleySingleton;


public class SaleEntry extends AppCompatActivity {
    private PAWHSApplication pawhsApplication;
    TextView snob, item, uom, ivar, pud,byname,srcbuyer,srccrop,qpnew,txtquantity;
    EditText nob,edtrate,edtamount;
    Spinner spinnerb, spinnerv;
    EditText qcp,rmk,lname,lmob,vno;
    Uri picUri;
    String buycode="",buyname="",buymob="";
    Button view,slbtn_view,btnadd;
    int cnt;
    String updatestatus;
    String seno="0",snid="0";
    JSONObject jsonParam;
    Button save,cancel;
    Bitmap thePic,thePic2;
    List<Pojobuyer> pojobuyerList;
    ArrayList<String> arrayn;
    String ui;
    String batype="Yes";
    ProgressDialog pdialog;
    String imageFilePath,imageFilePath2;
    String encodedImage = "0",encodedImage2 = "0";
    ByteArrayOutputStream byteArrayOutputStream,byteArrayOutputStream2;
    final int CAMERA_CAPTURE = 200;
    private static final int CAMERA_REQUEST = 1888;
    AutoCompleteTextView textViewQty2;
    ArrayList vtypes = new ArrayList();
    ArrayList vtypesc = new ArrayList();
    String itemcod, itemname;
    MyRecyclerViewAdapterb adapterb;
    private SQLiteDataBaseHandler db;
    SQLiteDatabase dbs;
    ImageView capturebill;
    double numtest=0;
    private TextView textViewincrease, textViewDecrease;
    private List<String> productItemList;
    private List<String> sitem, cat, var;
    Dialog dialog;
    String ts,seid,LBqty="0",LBname="",oldsaleserialqty= "0";
    private String userName,orgnId,locnId,userId,localeId,orgnCode;
    private List<PmListDao> pmListDaoList;
    boolean isNetwork;
    RadioGroup rgbatch,rgtype;
    RadioButton rbbatch,rbnonbach,batch,nonbatch;
    LinearLayout llqp,llrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_sale_entry);
        getSupportActionBar().hide();
        pdialog = new ProgressDialog(this);
        pawhsApplication = PAWHSApplication.getGetInstance();
        userName = pawhsApplication.getPreferenceFromString(SaleEntry.this, ApiUtils.IN_USER_NAME);

        orgnId = pawhsApplication.getPreferenceFromString(SaleEntry.this, ApiUtils.ORGN_ID);
        locnId = pawhsApplication.getPreferenceFromString(SaleEntry.this, ApiUtils.LOCN_ID);
        userId = pawhsApplication.getPreferenceFromString(SaleEntry.this, ApiUtils.USER_ID);
        localeId = pawhsApplication.getPreferenceFromString(SaleEntry.this, ApiUtils.LOCALE_ID);
        orgnCode = pawhsApplication.getPreferenceFromString(SaleEntry.this, ApiUtils.ORGN_CODE);
        llrate = (LinearLayout) findViewById(R.id.llrate);
        snob = (TextView) findViewById(R.id.snob);
        nob = (EditText) findViewById(R.id.nob);
        edtrate = (EditText) findViewById(R.id.edtrate);
        edtamount = (EditText) findViewById(R.id.edtamount);
        item = (TextView) findViewById(R.id.item);
        uom = (TextView) findViewById(R.id.uom);
        ivar = (TextView) findViewById(R.id.ivar);
        pud = (TextView) findViewById(R.id.pud);
        txtquantity = (TextView) findViewById(R.id.txtquantity);
        save = (Button) findViewById(R.id.save);
        view = (Button) findViewById(R.id.view);
        byname = (TextView) findViewById(R.id.byname);
        cancel = (Button) findViewById(R.id.cancel);
        isNetwork = Utility.checkConnectivity(getApplicationContext());
        textViewQty2 = (AutoCompleteTextView) findViewById(R.id.txt_est_qty2);
        textViewincrease = (TextView) findViewById(R.id.txt_increase);
        textViewDecrease = (TextView) findViewById(R.id.txt_decrease);
        srcbuyer = (TextView) findViewById(R.id.srcbuyer);
        srccrop = (TextView) findViewById(R.id.srccrop);
        spinnerb = (Spinner) findViewById(R.id.spinnerb);
        spinnerv = (Spinner) findViewById(R.id.spinnerv);
        arrayn = new ArrayList<>();
        qcp = (EditText) findViewById(R.id.qcp);
        rmk = (EditText) findViewById(R.id.rmk);
        vno = (EditText) findViewById(R.id.vno);
        lname = (EditText) findViewById(R.id.lname);
        lmob = (EditText) findViewById(R.id.lmob);
        rgtype = (RadioGroup) findViewById(R.id.rgtype);
        rgbatch = (RadioGroup) findViewById(R.id.rgbatch);
        rbbatch = (RadioButton) findViewById(R.id.rbbatch);
        rbnonbach = (RadioButton) findViewById(R.id.rbnonbach);
        batch = (RadioButton) findViewById(R.id.batch);
        nonbatch = (RadioButton) findViewById(R.id.nonbatch);
        slbtn_view = (Button) findViewById(R.id.slbtn_view);
        btnadd = (Button) findViewById(R.id.addbtn);
        llqp = (LinearLayout) findViewById(R.id.llqp);
        qpnew = (TextView) findViewById(R.id.qp);
        textViewQty2.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        textViewQty2.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(18,2)});
        textViewQty2.setEnabled(false);
        snob.setEnabled(true);
        ArrayList bys = new ArrayList();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        // pud.setText(""+formattedDate);

        rgtype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.batch:
                        batype = "Yes";
                        llrate.setVisibility(View.GONE);
                        break;
                    case R.id.nonbatch:
                        batype = "No";
                        llrate.setVisibility(View.VISIBLE);
                        break;

                }
            }

        });
        pmListDaoList = new ArrayList<>();

        db = new SQLiteDataBaseHandler(SaleEntry.this);
        dbs = db.getWritableDatabase();
        pojobuyerList = new ArrayList<>();
        capturebill = (ImageView)findViewById(R.id.capturebill);
        capturebill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {

                    picUri = FileProvider.getUriForFile(SaleEntry.this, getApplicationContext().getPackageName() + ".provider", createImageFile());
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                    takePictureIntent.putExtra("return-data", true);
                    startActivityForResult(takePictureIntent, CAMERA_CAPTURE);// convert path to Uri
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), ""+Log.getStackTraceString(e), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        pud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        snob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!item.getText().toString().trim().equalsIgnoreCase("")){
                    Intent i = new Intent(getApplicationContext(), SnoActivity.class);
                    i.putExtra("TS",ts);
                    i.putExtra("PC",itemcod);
                    startActivity(i);
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Saleentrylist.class);

                startActivity(i);
            }
        });

        slbtn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!item.getText().toString().trim().equalsIgnoreCase("")){
                    Intent i = new Intent(getApplicationContext(), SnoActivity.class);
                    i.putExtra("TS",ts);
                    i.putExtra("PC",itemcod);
                    startActivity(i);
                }else{
                    showErrorDialog("Please Select Crop Name");
                }
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!item.getText().toString().trim().equalsIgnoreCase("")){
                    Intent i = new Intent(getApplicationContext(), SnoActivity.class);
                    i.putExtra("TS",ts);
                    i.putExtra("PC",itemcod);
                    startActivity(i);
                }
            }
        });

        String selectQuery22 = "SELECT  * FROM byr";

        Cursor cursor22 = dbs.rawQuery(selectQuery22, null);

        if (cursor22.moveToFirst()) {

            bys.add("");

            do {

                bys.add(cursor22.getString(2) + "---" + cursor22.getString(1));


                // total number of textviews to add


            } while (cursor22.moveToNext());
            final ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(SaleEntry.this,
                    R.layout.spinnertext, bys);

            spinnerb.setAdapter(adapterlist2n);
        } //

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemSearchDialog();
            }
        });
        srccrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemSearchDialog();
            }
        });
        String selectQuery = "SELECT  * FROM vtype";

        Cursor cursor = dbs.rawQuery(selectQuery, null);

        if(cursor.moveToFirst())
        {
            vtypesc.add("0");
            vtypes.add("");
            do
            {

                vtypesc.add(cursor.getString(2));
                vtypes.add(cursor.getString(3));






                // total number of textviews to add


            }while(cursor.moveToNext());
            final ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(SaleEntry.this,
                    R.layout.spinnertext, vtypes);

            spinnerv.setAdapter(adapterlist2n);
        } //


        textViewincrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(SaleEntry.this, "Hi", Toast.LENGTH_SHORT).show();
                numtest = numtest + 0.5;
                textViewQty2.setText("" + numtest);

            }
        });

        textViewDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numtest <= 0) {
                    numtest = 0;
                    textViewQty2.setText(numtest + "");
                }
                else
                {
                    numtest = numtest - 0.5;
                    textViewQty2.setText(numtest + "");
                }

            }

        });
        textViewQty2.addTextChangedListener(new TextWatcher() {
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

                try {
                    if(s.length() == 0)
                    {
                        numtest = 0;
                    }
                    else
                    {
                        numtest = Double.parseDouble(s.toString());
                    }
                }
                catch(Exception e)
                {

                }

                //  numtest = Double.parseDouble(s.toString());

                // TODO Auto-generated method stub
            }
        });
        textViewQty2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!item.getText().toString().trim().equalsIgnoreCase("")){
                /*    Intent i = new Intent(getApplicationContext(), SnoActivity.class);
                    i.putExtra("TS",ts);
                    i.putExtra("PC",itemcod);
                    startActivity(i);*/
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(updatestatus.equalsIgnoreCase("0")){
                    ArrayList<String> snary = new ArrayList<>();
                    Cursor cr = dbs.rawQuery("select * from sno where v3 = '"+ts+"' " +
                            " and v4 = '"+itemcod+"' ",null);
                    try{
                        if(cr.getCount() > 0){
                            snary.clear();
                            if(cr.moveToFirst()){
                                do{
                                    snary.add(cr.getString(cr.getColumnIndexOrThrow("v1")));
                                    dbs.execSQL("delete from qpar where v1 = '"+cr.getString(cr.getColumnIndexOrThrow("id"))+"' ");
                                    try{
                                        String snname = cr.getString(cr.getColumnIndexOrThrow("v1"));
                                        Double qtyy = Double.parseDouble(cr.getString(cr.getColumnIndexOrThrow("v6")));
                                        dbs.execSQL("update snowithqty set v2 = v2 + "+qtyy+" where v1 = '"+snname+"' ");
                                    }catch (Exception er){
                                        Log.e("error",Log.getStackTraceString(er));
                                    }
                                }while (cr.moveToNext());
                            }
                        }
                    }catch (Exception er){
                        Log.e("error",Log.getStackTraceString(er));
                    }finally {
                        cr.close();
                    }
                    for(int i=0; i < snary.size(); i++){
                        dbs.execSQL("delete from sno where v1 = '"+snary.get(i)+"' " +
                                "and v4 = '"+itemcod+"' ");
                    }
                   // dbs.execSQL("delete from sno");
                   // dbs.execSQL("delete from qpar");
                    dbs.execSQL("delete from qparnew where v1 = '"+ts+"' ");
                }
                finish();
            }
        });

        edtrate.addTextChangedListener(new TextWatcher() {
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
                try {
                   if(textViewQty2.getText().toString().equalsIgnoreCase(""))
                   {
                       showErrorDialog("Empty Quantity");
                       edtrate.setText("");
                   }
                   else
                   {
                       edtamount.setText(""+(Double.parseDouble(textViewQty2.getText().toString())*Double.parseDouble(edtrate.getText().toString())));
                   }
                }
                catch (Exception e)
                {

                }

                // TODO Auto-generated method stub
            }
        });

        srcbuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byname.performClick();
            }
        });

        byname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(SaleEntry.this);
                dialog.setContentView(R.layout.suppliersearch3);

                dialog.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                final RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(SaleEntry.this));
                adapterb = new MyRecyclerViewAdapterb(SaleEntry.this, pojobuyerList);
                dialog.getWindow().setLayout(width, height);
                arrayn.clear();
                // This will get the radiogroup
                EditText buynam = (EditText)dialog.findViewById(R.id.buyname
                        );
                EditText buymo = (EditText)dialog.findViewById(R.id.buymob);
                Button save = (Button) dialog.findViewById(R.id.save);
                RadioGroup rGroup = (RadioGroup)dialog.findViewById(R.id.rgtype);
                RelativeLayout rlregular = (RelativeLayout) dialog.findViewById(R.id.rlregular);
                RelativeLayout rlonetime = (RelativeLayout) dialog.findViewById(R.id.rlonetime);
                RadioButton regular = (RadioButton) dialog.findViewById(R.id.regular);
                RadioButton ontime = (RadioButton) dialog.findViewById(R.id.onetime);
                AutoCompleteTextView elc = (AutoCompleteTextView)dialog.findViewById(R.id.elc);
                rGroup.setVisibility(View.VISIBLE);

                buynam.setFilters(getFilter(",.1234567890@#$%&₹✓-+()*\"':;!?//_~`|•√π÷×¶∆}{=°^¥€¢£\\\\©®™℅[]>< "));
                if(batype.equalsIgnoreCase("Yes"))
                {
                    regular.setChecked(true);
                    regular.setVisibility(View.VISIBLE);
                    ontime.setVisibility(View.GONE);
                    rlonetime.setVisibility(View.GONE);
                    rlregular.setVisibility(View.VISIBLE);

                }
                else
                {
                    ontime.setVisibility(View.VISIBLE);
                    regular.setVisibility(View.VISIBLE);
                    rlonetime.setVisibility(View.VISIBLE);
                    rlregular.setVisibility(View.GONE);
                }
// This will get the radiobutton in the radiogroup that is checked
//                RadioButton checkedRadioButton = (RadioButton)rGroup.findViewById(rGroup.getCheckedRadioButtonId());


               save.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       if(buynam.getText().toString().equalsIgnoreCase(""))
                       {
                           showErrorDialog("Empty Buyer Name");
                       }
                       else if(buymo.getText().toString().length() < 10 )
                       {
                           showErrorDialog("Mobile No Cannot Be less than 10 digits");
                       }
                       else
                       {
                           byname.setText(""+buynam.getText().toString());
                           buyname = buynam.getText().toString();
                           buymob = ""+buymo.getText().toString();
                           buycode="";
                           dialog.dismiss();
                       }
                   }
               });

                rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.regular:

                                rlregular.setVisibility(View.VISIBLE);
                                rlonetime.setVisibility(View.GONE);

                                break;
                            case R.id.onetime:
                                rlregular.setVisibility(View.GONE);
                                rlonetime.setVisibility(View.VISIBLE);
                                elc.setText("");
                                pojobuyerList.clear();
                                recyclerView.setAdapter(adapterb);
                                break;

                        }
                    }

                });
                final SQLiteDatabase dbs = db.getWritableDatabase();

                String selectQuery5 = "SELECT  * FROM byr";
                Cursor cc = dbs.rawQuery(selectQuery5, null);
                if(cc.getCount()!=0) {
                    if (cc.moveToFirst()) {
                        do {

                            if(arrayn.contains(cc.getString(1)))
                            {

                            }
                            else
                            {
                                arrayn.add(cc.getString(1));
                            }
                            // Log.e("VAL",""+cursor.getString(11));

                        } while (cc.moveToNext());
                    }
                }


                ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(SaleEntry.this,
                        R.layout.spinnertext, arrayn);

                elc.setAdapter(adapterlist2n);

                elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {


                        pojobuyerList.clear();
                        // elc.setText(""+(String) parent.getItemAtPosition(position));

                        Cursor cursor = dbs.query("byr", new String[]{"id","v1","v2","v3"
                                }, "v1" + "=? COLLATE NOCASE",
                                new String[]{(String) parent.getItemAtPosition(position)}, null, null, null, null);
                        if(cursor.getCount()!=0)
                        {
                            if (cursor.moveToFirst()) {
                                do {

                                    Pojobuyer pojobuyer = new Pojobuyer();

                                    pojobuyer.setId(cursor.getString(0));
                                    pojobuyer.setBn(cursor.getString(1));
                                    pojobuyer.setBc(cursor.getString(2));





                                    pojobuyerList.add(pojobuyer);

                                    recyclerView.setAdapter(adapterb);



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
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buyname.equalsIgnoreCase(""))
                {
                    showErrorDialog("Empty Buyer");
                }
                else if(batype.equalsIgnoreCase("Yes") && (buycode.equalsIgnoreCase("")))
                {
                    showErrorDialog("One Time Buyer Not Applicable For Batch");
                }
                else if(item.getText().toString().equalsIgnoreCase(""))
                {
                    showErrorDialog("Empty Item");
                }
                else if(textViewQty2.getText().toString().equalsIgnoreCase(""))
                {
                    showErrorDialog("Empty Quantity");
                }
//                else if(snob.getText().toString().equalsIgnoreCase(""))
//                {
//                    showErrorDialog("Empty Serial No of Bags");
//                }
//                else if(qcp.getText().toString().equalsIgnoreCase(""))
//                {
//                    showErrorDialog("Empty QC Persons");
//
//                }

                else if(pud.getText().toString().equalsIgnoreCase(""))
                {
                    showErrorDialog("Empty Pick Up Date");

                }
                else if(edtrate.getText().toString().equalsIgnoreCase("")&&batype.equalsIgnoreCase("No"))
                {
                    showErrorDialog("Empty Rate");

                }
                else if(rbnonbach.isChecked() && (Double.parseDouble(textViewQty2.getText().toString().trim()) > Double.parseDouble(LBqty))){
                    showErrorDialog("Qty Cannot Be Higher Than Loose Bag Quantity , Availbale Loose Bag Quantity is "+LBqty+" .");
                }
//                else if(vno.getText().toString().equalsIgnoreCase(""))
//                {
//                    showErrorDialog("Empty Vehicle No");
//
//                }
//                else if(lname.getText().toString().equalsIgnoreCase(""))
//                {
//                    showErrorDialog("Empty LRP Name");
//
//                }
//                else if(lmob.getText().toString().equalsIgnoreCase("")|| lmob.getText().toString().length()<10)
//                {
//                    showErrorDialog("Empty LRP Mobile No / Invalid LRP Mobile No");
//
//                }
//                else  if(spinnerv.getSelectedItem().toString().equalsIgnoreCase(""))
//                {
//                    showErrorDialog("Empty Vehicle Type");
//                }
//                else if(encodedImage.equalsIgnoreCase("0"))
//                {
//                    showErrorDialog("Empty Image");
//
//                }
//                else if(nob.getText().toString().equalsIgnoreCase(""))
//                {
//                    showErrorDialog("Please Enter No of Bags");
//
//                }
                else
                {
//                    //Toast.makeText(SaleEntry.this, "Success", Toast.LENGTH_SHORT).show();
//                    double x = Double.parseDouble(textViewQty2.getText().toString());
//
//                    if(cnt<x)
//                    {
//
//                        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
//                                SaleEntry.this);
//
//// Setting Dialog Title
//                        alertDialog2.setTitle("Confirm...");
//
//// Setting Dialog Message
//                        alertDialog2.setMessage("No of Serial bags is less than the total quantity.Are you sure you want to continue?");
//
//// Setting Icon to Dialog
//
//
//// Setting Positive "Yes" Btn
//                        alertDialog2.setPositiveButton("YES",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // Write your code here to execute after dialog
//                                        savesaleentry();
//                                    }
//                                });
//// Setting Negative "NO" Btn
//                        alertDialog2.setNegativeButton("NO",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // Write your code here to execute after dialog
//
//                                        dialog.cancel();
//                                    }
//                                });
//
//// Showing Alert Dialog
//                        alertDialog2.show();
//
//                    }
//                    else {
//                        savesaleentry();
//                    }
//                    if(rbnonbach.isChecked()){
//                       try{
//                           Double oldq = 0.0, result = 0.0;
//                           oldq = Double.parseDouble(LBqty);
//                           Log.e("oldqty",""+oldq);
//                           if(updatestatus.equalsIgnoreCase("0")){
//                               db.insertsno(LBname,"1",ts,itemcod,"0",textViewQty2.getText().toString().trim());
//                               result = oldq - (Double.parseDouble(textViewQty2.getText().toString().trim()));
//                           }else if(updatestatus.equalsIgnoreCase("1")){
//                               dbs.execSQL("update sno set v6 = '"+textViewQty2.getText().toString().trim()+"' where v1 = '"+LBname+"' ");
//                               result = oldq - (Double.parseDouble(textViewQty2.getText().toString().trim()));
//                           }
//                           Log.e("oldqty1",""+result);
//                           dbs.execSQL("update snowithqty set v2 = '"+result+"' where v1 = '"+LBname+"' ");
//                           savesaleentry();
//                       }catch (Exception er){
//                           Log.e("error",er.toString());
//                       }
//                    }else{
                        savesaleentry();
                   // }
                }
            }
        });
        rgbatch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rbbatch){
                    textViewincrease.setEnabled(false);
                    textViewDecrease.setEnabled(false);
                    textViewQty2.setEnabled(false);
                    nob.setEnabled(true);
                    snob.setEnabled(true);
                   /* if(updatestatus.equalsIgnoreCase("0")){
                        textViewQty2.setText("");
                    }*/
                }
                else if(i==R.id.rbnonbach){
                    textViewincrease.setEnabled(true);
                    textViewDecrease.setEnabled(true);
                    textViewQty2.setEnabled(true);
                    nob.setEnabled(false);
                    //snob.setEnabled(false);
                   /* if(updatestatus.equalsIgnoreCase("0")){
                        //textViewQty2.setText("");
                        nob.setText("");
                        deletesn();
                    }*/
                }
            }
        });
        Intent i = getIntent();
        seid = i.getStringExtra("SEID");
        if(seid.equalsIgnoreCase(""))
        {
            Long tsLong = System.currentTimeMillis()/1000;
            ts = tsLong.toString();
            Log.e("TST",""+ts);
            updatestatus = "0";
        }
        else {
            item.setEnabled(false);
            srccrop.setEnabled(false);
            updatestatus = "1";
            String selectQueryed = "SELECT  * FROM saleentry where id = "+seid;

            final Cursor cursored = dbs.rawQuery(selectQueryed, null);
            Log.e("COUNT",""+cursor.getCount());

            // db.insertsaleentry(buycode,buyname,item.getText().toString(),itemcod,ivar.getText().toString(),uom.getText().toString(),textViewQty2.getText().toString(),qcp.getText().toString(),pud.getText().toString(),rmk.getText().toString(),spinnerv.getSelectedItem().toString(),vno.getText().toString(),lname.getText().toString(),lmob.getText().toString(),ui,ts,"0",seno,snid,"");

            //  db.insertpurchaseentry(farmerCode,farmerName,memberType,itemCode,itemName,textViewQty2.getText().toString(),edt_price.getText().toString(),textViewValue.getText().toString(),textViewNoofBag.getText().toString(),edt_remarks.getText().toString(),ui,spinner.getSelectedItem().toString(),vno.getText().toString(),spinner2.getSelectedItem().toString(),qcp.getText().toString(),lrpname.getText().toString(),lrpno.getText().toString(),textViewPickUpdate.getText().toString(),buycode,buyname,ts,"0",lnom,peoid,"I");

            // looping through all rows and adding to list
            if (cursored.moveToFirst()) {
                spinnerb.setSelection(0);
                //  spinnerv.setSelection(0);
                spinnerv.setSelection(((ArrayAdapter<String>)spinnerv.getAdapter()).getPosition(cursored.getString(11)));

                ivar.setText(cursored.getString(5));
                uom.setText(cursored.getString(6));
                txtquantity.setText("Quantity Sale (in "+cursored.getString(6)+")");
                byname.setText(cursored.getString(2));
                textViewQty2.setText(cursored.getString(7));
                oldsaleserialqty = cursored.getString(7);
                numtest = Double.parseDouble(cursored.getString(7));
                ts = cursored.getString(16);
                ui = cursored.getString(15);
                if(cursored.getString(14).equalsIgnoreCase("Yes"))
                {
                    llrate.setVisibility(View.GONE);
                    batype = cursored.getString(14);
                    batch.setChecked(true);
                    nonbatch.setEnabled(false);
                }
                else
                {
                    llrate.setVisibility(View.VISIBLE);
                    batype = cursored.getString(14);
                    batch.setEnabled(false);
                    nonbatch.setChecked(true);
                    edtrate.setText(""+cursored.getString(13));
                    byname.setClickable(false);
                    srcbuyer.setClickable(false);
                    btnadd.setClickable(false);
                    slbtn_view.setClickable(false);
                    snob.setClickable(false);
                    edtrate.setFocusable(false);
                    qcp.setFocusable(false);
                    pud.setClickable(false);
                    rmk.setFocusable(false);
                    capturebill.setClickable(false);
                    save.setClickable(false);
                    save.setVisibility(View.GONE);
                    try {


                    edtamount.setText(""+(Double.parseDouble(cursored.getString(7))+Double.parseDouble(cursored.getString(13))));
                    }
                    catch (Exception e)
                    {

                    }
                }

                //snob.setText("");
                nob.setText(""+cursored.getString(21));
                fetchsn();
                seno= cursored.getString(18);
                snid = cursored.getString(19);
                qcp.setText(cursored.getString(8));
                pud.setText(cursored.getString(9));
                rmk.setText(cursored.getString(10));
                vno.setText(cursored.getString(12));
                lmob.setText(cursored.getString(14));
                lname.setText(cursored.getString(13));
                item.setText(cursored.getString(3));
                itemname=cursored.getString(3);
                itemcod=cursored.getString(4);
                buycode = cursored.getString(1);
                buyname = cursored.getString(2);
                buymob = cursored.getString(11);
                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(cursored.getString(15)));

                    byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                    //Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());
                    capturebill.setImageBitmap(bitmap);


                    encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                    Log.e("ECS",""+encodedImage);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            LB_qty_fetch();
        }
      //  rbbatch.setChecked(true);
        qpnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SaleEntry.this,QparSalenew.class);
                i.putExtra("PC",itemcod);
                i.putExtra("TS",ts);
                startActivity(i);
            }
        });
    }

    private void showDateDialog() {

        final Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(SaleEntry.this, android.R.style.Theme_Holo_Dialog,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String month = null, day = null;
                        int m, d;

                        m = monthOfYear + 1;
                        if (m < 10) {

                            month = "0" + m;
                        } else {
                            month = String.valueOf(m);
                        }
                        if (dayOfMonth < 10) {

                            day = "0" + dayOfMonth;
                        } else {
                            day = String.valueOf(dayOfMonth);
                        }

                        pud.setText(day + "-" + month + "-" + year);


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();


    }

    private void showItemSearchDialog() {

        dialog = new Dialog(SaleEntry.this);
        dialog.setContentView(R.layout.custom_search_item_dialog);
        //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
        dialog.setTitle("Title...");
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
        final RecyclerView recyclerView = dialog.findViewById(R.id.itm);
        recyclerView.setLayoutManager(new LinearLayoutManager(SaleEntry.this));
        dialog.getWindow().setLayout(width, height);

        final AutoCompleteTextView elc = (AutoCompleteTextView) dialog.findViewById(R.id.elc);
        final AutoCompleteTextView elc2 = (AutoCompleteTextView) dialog.findViewById(R.id.elc2);
        final AutoCompleteTextView elc3 = (AutoCompleteTextView) dialog.findViewById(R.id.elc3);


        productItemList = new ArrayList<>();
        cat = new ArrayList<>();
        var = new ArrayList<>();
        productItemList.clear();
        cat.clear();
        var.clear();
        productItemList = db.getProductNameAndCode();
        cat = db.getProductNameAndCode2();
        var = db.getProductNameAndCode3();
        HashSet hs = new HashSet();

        hs.addAll(productItemList); // demoArrayList= name of arrayList from which u want to remove duplicates

        productItemList.clear();
        productItemList.addAll(hs);

        HashSet hs2 = new HashSet();

        hs2.addAll(cat); // demoArrayList= name of arrayList from which u want to remove duplicates

        cat.clear();
        cat.addAll(hs2);

        HashSet hs3 = new HashSet();

        hs3.addAll(var); // demoArrayList= name of arrayList from which u want to remove duplicates

        var.clear();
        var.addAll(hs3);

        final ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(SaleEntry.this,
                R.layout.support_simple_spinner_dropdown_item, productItemList);

        elc.setAdapter(adapterlist2n);
        final ArrayAdapter<String> adapterlist2nn = new ArrayAdapter<String>(SaleEntry.this,
                R.layout.support_simple_spinner_dropdown_item, cat);

        elc2.setAdapter(adapterlist2nn);
        final ArrayAdapter<String> adapterlist2nnn = new ArrayAdapter<String>(SaleEntry.this,
                R.layout.support_simple_spinner_dropdown_item, var);

        elc3.setAdapter(adapterlist2nnn);


        elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                pmListDaoList.clear();
                String productName = elc.getText().toString();
                pmListDaoList = db.getProductMasterAllProductDetails(productName);

                MyRecyclerViewAdapterItem adapterf = new MyRecyclerViewAdapterItem(SaleEntry.this, pmListDaoList);
                recyclerView.setAdapter(adapterf);

                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
            }
        });
        elc2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                pmListDaoList.clear();
                String productName = elc2.getText().toString();
                pmListDaoList = db.getProductMasterAllProductDetails2(productName);

                MyRecyclerViewAdapterItem adapterf = new MyRecyclerViewAdapterItem(SaleEntry.this, pmListDaoList);
                recyclerView.setAdapter(adapterf);

                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
            }
        });
        elc3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                pmListDaoList.clear();
                String productName = elc3.getText().toString();
                pmListDaoList = db.getProductMasterAllProductDetails3(productName);

                MyRecyclerViewAdapterItem adapterf = new MyRecyclerViewAdapterItem(SaleEntry.this, pmListDaoList);
                recyclerView.setAdapter(adapterf);

                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
            }
        });
        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public class MyRecyclerViewAdapterItem extends RecyclerView.Adapter<MyRecyclerViewAdapterItem.ViewHolder> {

        private List<PmListDao> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        public MyRecyclerViewAdapterItem(Context context, List<PmListDao> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.custom_recyclerview_product, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final PmListDao pojofar = mData.get(position);
            holder.textViewProductName.setText(pojofar.getOut_product_name());
            holder.textViewProductCode.setText(pojofar.getOut_product_code());
            holder.txt3.setText(pojofar.getOut_uomtype_code());
            holder.txt4.setText(pojofar.getOut_product_category());
            holder.txt5.setText(pojofar.getOut_crop_variety());
            //     holder.textViewProductAgg.setText(pojofar.getOut_agg_code());

            holder.llist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemcod = pojofar.getOut_product_code();
                    itemname = pojofar.getOut_product_name();
                    //   aggCode = pojofar.getOut_agg_code();
                    uom.setText(pojofar.getOut_uomtype_code());
                    txtquantity.setText("Quantity Sale (in "+pojofar.getOut_uomtype_code()+")");
                    ivar.setText(pojofar.getOut_crop_variety());
                    item.setText(itemname);
                    dialog.dismiss();
                    LB_qty_fetch();
                    Log.e("lbavlqty",LBqty);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textViewProductName, textViewProductCode, txt3, txt4, txt5;
            LinearLayout llist;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewProductName = itemView.findViewById(R.id.txt_productName);
                textViewProductCode = itemView.findViewById(R.id.txt_productcode);
                //  textViewProductAgg = itemView.findViewById(R.id.txt_agg);
                txt3 = itemView.findViewById(R.id.txtuom);
                txt4 = itemView.findViewById(R.id.txtcat);
                txt5 = itemView.findViewById(R.id.txtvar);
                llist = itemView.findViewById(R.id.llist);
            }
        }
    }

    public void fetchsn() {
     /*   Cursor cursor = dbs.query("sno", new String[]{"v1","v2","v3"
                }, "v3" + "=? COLLATE NOCASE",
                new String[]{ts}, null, null, null, null);*/
        LB_qty_fetch();
        Cursor cursor = dbs.rawQuery("select v1, v2, v3,v6 from sno where v3 = '"+ts+"' " +
                " and v4 = '"+itemcod+"' and v5 != '2' ",null);
        Log.e("COUNT", "" + cursor.getCount());
        // nob.setText("" + cursor.getCount());
        snob.setText("" + cursor.getCount());
       // textViewQty2.setText(""+cursor.getCount());
        ///cnt = cursor.getCount();

        double x=0,y=0;
        String lbflag = "";
        // looping through all rows and adding to list
        if(cursor.getCount() > 0){
            if (cursor.moveToFirst()) {
                do {
                    /*y = Integer.parseInt(cursor.getString(2));
                    x+=y;
                    // snob.setText("" + x+"(Serial Bags)");
                    // nob.setText("" + x);
                    cnt = x;

                    ///String[] pn = cursor.getString(1).split("-");
                    //*/
                    try{
                        String ssqty = cursor.getString(cursor.getColumnIndexOrThrow("v6"));
                        x += Double.parseDouble(ssqty);
                        if(!cursor.getString(cursor.getColumnIndexOrThrow("v1")).substring(0,2).equalsIgnoreCase("LB")){
                            lbflag = "N";
                        }else{
                            Double ans = Double.parseDouble(LBqty) + Double.parseDouble(ssqty);
                            LBqty = String.valueOf(ans);
                        }
                    }catch (Exception er){
                        Log.e("error",er.toString());
                    }

                } while (cursor.moveToNext());

            }
            slbtn_view.setVisibility(View.VISIBLE);
            slbtn_view.setText("Edit/View");
            btnadd.setVisibility(View.GONE);
            item.setEnabled(false);
            srccrop.setEnabled(false);
            textViewQty2.setText(""+x);
        }else{
            //slbtn_view.setVisibility(View.GONE);
           // btnadd.setVisibility(View.GONE);
        }
        if(lbflag.equalsIgnoreCase("N")){
            llqp.setVisibility(View.GONE);
            rbbatch.setChecked(true);
            rbnonbach.setEnabled(false);
        }else{
           // snob.setText("");
           // slbtn_view.setVisibility(View.GONE);
           // btnadd.setVisibility(View.GONE);
            llqp.setVisibility(View.VISIBLE);
            rbnonbach.setChecked(true);
            if(x > 0){
            rbbatch.setEnabled(false);
            }
            else{
                textViewQty2.setText("");
                textViewQty2.setEnabled(false);
            }
        }
    }
    public void deletesn() {
        dbs.execSQL("delete from sno where v3 = '"+ts+"' and v4 = '"+itemcod+"' ");
        fetchsn();
    }

    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        fetchsn();
        fetchqp();
        try {
            if(textViewQty2.getText().toString().equalsIgnoreCase(""))
            {
                //showErrorDialog("Empty Quantity");
                //edtrate.setText("");
            }
            else
            {
                edtamount.setText(""+(Double.parseDouble(textViewQty2.getText().toString())*Double.parseDouble(edtrate.getText().toString())));
            }
        }
        catch (Exception e)
        {

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
                .setRequestedSize(320, 320, CropImageView.RequestSizeOptions.RESIZE_INSIDE)
                .setMinCropWindowSize(0,0)
                .setAspectRatio(1,1)
                .setCropShape(CropImageView.CropShape.OVAL)
                .start(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);




        if(resultCode == RESULT_OK)
        {

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
                capturebill.setImageResource(0);
                capturebill.setImageBitmap(thePic);
                //vcap.setImageBitmap(thePic);


                byteArrayOutputStream = new ByteArrayOutputStream();
                thePic.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                ui = thePic.toString();
                Log.e("JJJJJJ", "" + ui);
                try {
                    encodedImage = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = null;
                if (resultCode == RESULT_OK) {

                    try {
                        result = CropImage.getActivityResult(data);

                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.getUri());
                        // capturebill.setImageResource(0);
                        // capturebill.setImageBitmap(bitmap);
                        // vcap.setImageBitmap(bitmap);
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        ui = result.getUri().toString();

                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                        Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());


                        //encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                        Log.e("PIC", "" + encodedImage);
                        FileOutputStream fos = null;
                        byte[] imageInByte = byteArrayOutputStream.toByteArray();
                        long lengthbmp = imageInByte.length;
                        lengthbmp = lengthbmp/1024;
                        // int fsize = Math.toIntExact(lengthbmp / 1024);

                        Log.e("PICSize", "" + lengthbmp+"KB");
                        if(lengthbmp >300)
                        {
                            encodedImage = "0";
                            showErrorDialog("Please Reduce The Size Of The Image");
                        }
                        else
                        {
                            capturebill.setImageResource(0);
                            capturebill.setImageBitmap(bitmap);
                            encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                            Log.e("PIC", "" + encodedImage);
                        }


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
    private void showErrorDialog(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Info!")
                .setMessage(s)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showErrorDialog2(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Info!")
                .setMessage(s)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        finish();
                    }
                })
                .show();
    }
    @Override
    public void onBackPressed() {
        //this is only needed if you have specific things
        //that you want to do when the user presses the back button.
        /* your specific things...*/
        // dbs.execSQL("delete from sno");
        // dbs.execSQL("delete from qpar");
        super.onBackPressed();
        cancel.callOnClick();
    }

    public void savesaleentry()
    {

        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();
        try {
            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document",userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", orgnCode);
            user.put("locnId", locnId);
            //user.put("userId", userId);
            user.put("userId", pawhsApplication.getPreferenceFromString(SaleEntry.this, "IN_USER_CODE"));
            //user.put("localeId", localeId);
            user.put("localeId", "sale");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("in_Sale_rowid",Integer.parseInt(snid));
            user2.put("in_SaleNo",seno);
            //String[] by = spinnerb.getSelectedItem().toString().split("---");
            user2.put("In_Buyer_code",buycode);
            user2.put("In_Buyer_name",buyname);

            user2.put("in_Item_Code",itemcod);
            user2.put("in_Item_Name",item.getText().toString());

            user2.put("in_Sale_Qty",Double.parseDouble(String.valueOf(numtest)));
            if(batype.equalsIgnoreCase("Yes")) {
                user2.put("in_Sale_Price", 0);
                user2.put("in_Sale_Value", 0);
            }
            else
            {
                user2.put("in_Sale_Price", Double.parseDouble(edtrate.getText().toString()));
                user2.put("in_Sale_Value", Double.parseDouble(edtamount.getText().toString()));
            }
            user2.put("in_advance_amt",0);
            if(snob.getText().toString().equalsIgnoreCase(""))
            {
                user2.put("in_no_of_bags",0);
            }
            else
            {
                user2.put("in_no_of_bags",Integer.parseInt(snob.getText().toString()));
            }

            user2.put("in_Status","Active");

            if(snid.equalsIgnoreCase("0")) {
                user2.put("in_mode_flag", "I");
            }
            else
            {
                user2.put("in_mode_flag", "U");
            }
            user2.put("in_sale_remarks",rmk.getText().toString());
            user2.put("in_sale_attach",encodedImage);
            if(spinnerv.getSelectedItem().toString().equalsIgnoreCase(""))
            {
                user2.put("in_vehicle_type","NA");
            }
            else
            {
                user2.put("in_vehicle_type",spinnerv.getSelectedItem().toString());
            }
            if(vno.getText().toString().equalsIgnoreCase(""))
            {
                user2.put("in_vehicle_no","NA");
            }
            else
            {
                user2.put("in_vehicle_no",vno.getText().toString());
            }
            if(qcp.getText().toString().equalsIgnoreCase(""))
            {
                user2.put("in_qcperson_name","NA");
            }
            else
            {
                user2.put("in_qcperson_name",qcp.getText().toString());
            }

//            if(lname.getText().toString().equalsIgnoreCase(""))
//            {
                user2.put("in_LRP_Name","NA");
//            }
//            else
//            {
//                user2.put("in_LRP_Name",lname.getText().toString());
//            }
//
//            if(lmob.getText().toString().equalsIgnoreCase(""))
//            {
                user2.put("In_LRP_Mobile_no","NA");
//            }
//            else
//            {
//                user2.put("In_LRP_Mobile_no",lmob.getText().toString());
//            }




            user2.put("In_Payment_type","");
            user2.put("In_Bank_acc_no","");
            user2.put("In_cheque_no","");
            user2.put("In_whs_code","");
            user2.put("In_whs_name","");
            user2.put("In_batch_type",batype);
            user2.put("In_buyer_mobileno",buymob);

            String[] dt = pud.getText().toString().split("-");
            user2.put("In_Saledate",dt[2]+"-"+dt[1]+"-"+dt[0]);
            // user2.put("In_Saledate","");


            user.put("Header",user2);



            JSONArray snodetails = new JSONArray();
            final SQLiteDatabase dbs = db.getWritableDatabase();
            //String selectQuery = "SELECT  * FROM sno";
            //Cursor cursor2 = dbs.rawQuery(selectQuery, null);

         /*   Cursor cursor2 = dbs.query("sno", new String[]{"id","v1","v2","v3"
                    }, "v3" + "=? COLLATE NOCASE",
                    new String[]{ts}, null, null, null, null);*/
            Cursor cursor2 = dbs.rawQuery("select * from sno where v3 = '"+ts+"' " +
                    " and v4 = '"+itemcod+"' ",null);
            if (cursor2.moveToFirst()) {
                do {
                    JSONObject snolist = new JSONObject();
                    if(cursor2.getString(2).equalsIgnoreCase("1")) {
                        snolist.put("in_slno_row_id", 0);
                        //snolist.put("in_slno", cursor2.getString(1));
                        snolist.put("in_slno", cursor2.getString(cursor2.getColumnIndexOrThrow("v1")));
                        snolist.put("in_temp1", "");
                        snolist.put("in_temp2", "");
                        if(cursor2.getString(cursor2.getColumnIndexOrThrow("v5")).equalsIgnoreCase("0")){
                            snolist.put("in_mode_flag", "I");
                        }else if(cursor2.getString(cursor2.getColumnIndexOrThrow("v5")).equalsIgnoreCase("1")){
                            snolist.put("in_mode_flag", "U");
                        }else if(cursor2.getString(cursor2.getColumnIndexOrThrow("v5")).equalsIgnoreCase("2")){
                            snolist.put("in_mode_flag", "D");
                        }
                        snolist.put("in_qty",cursor2.getString(cursor2.getColumnIndexOrThrow("v6")));
                        JSONArray qpar = new JSONArray();
                        String selectQuery2 = "SELECT  * FROM qpar WHERE v1 =" + cursor2.getString(0);
                        Cursor cursorq = dbs.rawQuery(selectQuery2, null);
                        if (cursorq.moveToFirst()) {
                            do {
                                JSONObject qparlist = new JSONObject();

                                qparlist.put("In_qly_dtl_rowid", 0);
                                //qparlist.put("In_slno", cursor2.getString(1));
                                qparlist.put("In_slno", cursor2.getString(cursor2.getColumnIndexOrThrow("v1")));
                                String[] q = cursorq.getString(2).split("-");
                                qparlist.put("In_qly_code", q[0]);
                                if (cursorq.getString(3).equalsIgnoreCase("Yes")) {
                                    qparlist.put("In_actual_value", 1);
                                } else if (cursorq.getString(3).equalsIgnoreCase("No")) {
                                    qparlist.put("In_actual_value", 2);
                                } else {
                                    qparlist.put("In_actual_value", Double.parseDouble(cursorq.getString(3)));
                                }
                                qparlist.put("In_wr_qly_value", 0);
                                qparlist.put("in_estimate_qly_value", 0);
                                qparlist.put("in_mode_flag", "I");
                                qpar.put(qparlist);

                            }

                            while (cursorq.moveToNext());
                        }
                        snolist.put("QlyDetail", qpar);
                        snodetails.put(snolist);
                    }
                    else
                    {
                      try{
                          int x = Integer.parseInt(cursor2.getString(2));
                          int y;
                         // String[] v = cursor2.getString(1).split("-");
                    //      String[] v = cursor2.getString(1).split("_");
                        //  String[] v2 = v[0].split(" ");
                         // y = Integer.parseInt(v2[1]);
                          for (int i = 0;i<x;i++)
                          {
                              snolist.put("in_slno_row_id", 0);
                              //snolist.put("in_slno", v2[0]+""+y);
                              snolist.put("in_slno", cursor2.getString(cursor2.getColumnIndexOrThrow("v1")));
                              snolist.put("in_temp1", "");
                              snolist.put("in_temp2", "");
                              if(cursor2.getString(cursor2.getColumnIndexOrThrow("v5")).equalsIgnoreCase("0")){
                                  snolist.put("in_mode_flag", "I");
                              }else if(cursor2.getString(cursor2.getColumnIndexOrThrow("v5")).equalsIgnoreCase("1")){
                                  snolist.put("in_mode_flag", "U");
                              }else if(cursor2.getString(cursor2.getColumnIndexOrThrow("v5")).equalsIgnoreCase("2")){
                                  snolist.put("in_mode_flag", "D");
                              }
                              snolist.put("in_qty",cursor2.getString(cursor2.getColumnIndexOrThrow("v6")));

                              //y++;
                              JSONArray qpar = new JSONArray();
                              String selectQuery2 = "SELECT  * FROM qpar WHERE v1 =" + cursor2.getString(0);
                              Cursor cursorq = dbs.rawQuery(selectQuery2, null);
                              if (cursorq.moveToFirst()) {
                                  do {
                                      JSONObject qparlist = new JSONObject();

                                      qparlist.put("In_qly_dtl_rowid", 0);
                                      //qparlist.put("In_slno", cursor2.getString(1));
                                      qparlist.put("In_slno", cursor2.getString(cursor2.getColumnIndexOrThrow("v1")));
                                      String[] q = cursorq.getString(2).split("-");
                                      qparlist.put("In_qly_code", q[0]);
                                      if (cursorq.getString(3).equalsIgnoreCase("Yes")) {
                                          qparlist.put("In_actual_value", 1);
                                      } else if (cursorq.getString(3).equalsIgnoreCase("No")) {
                                          qparlist.put("In_actual_value", 2);
                                      } else {
                                          qparlist.put("In_actual_value", Double.parseDouble(cursorq.getString(3)));
                                      }

                                      qparlist.put("In_wr_qly_value", 0);
                                      qparlist.put("in_estimate_qly_value", 0);
                                      qparlist.put("in_mode_flag", "I");


                                      qpar.put(qparlist);

                                  }

                                  while (cursorq.moveToNext());
                              }
                              snolist.put("QlyDetail", qpar);
                              snodetails.put(snolist);

                          }
                      }catch (Exception er){
                          Log.e("error",Log.getStackTraceString(er));
                      }
                    }
                }

                while (cursor2.moveToNext());
            }
            user.put("SlnoDetail",snodetails);
            Log.e("sljson",""+snodetails.toString());

            JSONArray qualitydtl = new JSONArray();
            final SQLiteDatabase dbsq = db.getWritableDatabase();
            Cursor qpcur =dbsq.query("qparnew", new String[]{"id","v1","v2","v3"
                    }, "v1" + "=? COLLATE NOCASE",
                    new String[]{ts}, null, null, null, null);
            try{
                if(qpcur.getCount() > 0 ){
                    if(qpcur.moveToFirst()){
                        do{
                            JSONObject qparlist = new JSONObject();

                            qparlist.put("In_qly_dtl_rowid",0);

                            String[] q = qpcur.getString(2).split("-");
                            qparlist.put("In_qly_code",q[0]);
                            if(qpcur.getString(3).equalsIgnoreCase("Yes"))
                            {
                                qparlist.put("in_actual_value",1);
                            }
                            else if(qpcur.getString(3).equalsIgnoreCase("No"))
                            {
                                qparlist.put("in_actual_value",2);
                            }
                            else
                            {
                                qparlist.put("in_actual_value", Double.parseDouble(qpcur.getString(3)));
                            }

                            qparlist.put("In_wr_qly_value",0);
                            qparlist.put("in_estimate_qly_value", 0);
                            qparlist.put("in_mode_flag","I");





                            qualitydtl.put(qparlist);
                        }while (qpcur.moveToNext());
                    }
                }
            }catch (Exception er){
                Log.e("error",Log.getStackTraceString(er));
            }finally {
                qpcur.close();
            }
            user.put("QlyDetail1",qualitydtl);






            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
        }


        if(isNetwork)
        {
            HttpsTrustManager.allowAllSSL();
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,ApiUtils.TEST_URL_API+"New_PAWHS_SaleEntry/New_Pawhs_SaleEntry_Save",jsonParam,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("CCCC", "" + response);

                            try {
                                JSONObject obj = response.getJSONObject("context");
                                JSONObject obj2 = obj.getJSONObject("header");

                                if(obj2.getString("in_SaleNo").equalsIgnoreCase("null"))
                                {
                                    showErrorDialog(""+obj.getString("errorMsg"));
                                }
                                else
                                {
                                    Pojokyc.saleno="";
                                    dbs.execSQL("Update sno set v5 = '1' where v5 = '0' ");
                                    if(updatestatus.equalsIgnoreCase("0"))
                                    {
                                        db.insertsaleentry(buycode,buyname,item.getText().toString(),itemcod,ivar.getText().toString(),uom.getText().toString(),textViewQty2.getText().toString(),qcp.getText().toString(),pud.getText().toString(),rmk.getText().toString(),buymob,vno.getText().toString(),edtrate.getText().toString(), batype,ui,ts,"1",obj2.getString("in_SaleNo"),obj2.getString("in_Sale_rowid"),"U",nob.getText().toString());


                                        // dbs.execSQL("delete from sno");
                                        //dbs.execSQL("delete from qpar");
                                        spinnerb.setSelection(0);
                                        spinnerv.setSelection(0);
                                        ivar.setText("");
                                        uom.setText("");
                                        byname.setText("");
                                        textViewQty2.setText("");
                                        snob.setText("");
                                        nob.setText("");
                                        qcp.setText("");
                                        pud.setText("");
                                        rmk.setText("");
                                        vno.setText("");
                                        lmob.setText("");
                                        lname.setText("");
                                        item.setText("");
                                        buycode="";
                                        buyname="";
                                        buymob="";
                                        capturebill.setImageDrawable(null);
                                        capturebill.setBackgroundResource(R.drawable.capture);
                                        encodedImage="0";
                                        showErrorDialog2("Saved Successfully !!! Sale No is"+obj2.getString("in_SaleNo"));
                                        Long tsLong = System.currentTimeMillis()/1000;
                                        ts = tsLong.toString();
                                    }
                                    else
                                    {
                                        db.updatesaleentry(Integer.valueOf(seid),buycode,buyname,item.getText().toString(),itemcod,ivar.getText().toString(),uom.getText().toString(),textViewQty2.getText().toString(),qcp.getText().toString(),pud.getText().toString(),rmk.getText().toString(),buymob,vno.getText().toString(),edtrate.getText().toString(), batype,ui,ts,"1",obj2.getString("in_SaleNo"),obj2.getString("in_Sale_rowid"),"U",nob.getText().toString());

                                        // dbs.execSQL("delete from sno");
                                        //dbs.execSQL("delete from qpar");
                                        updatestatus = "0";
                                        item.setEnabled(false);
                                        srccrop.setEnabled(false);
                                        spinnerb.setSelection(0);
                                        spinnerv.setSelection(0);
                                        ivar.setText("");
                                        uom.setText("");
                                        byname.setText("");
                                        textViewQty2.setText("");
                                        snob.setText("");
                                        nob.setText("");
                                        qcp.setText("");
                                        pud.setText("");
                                        rmk.setText("");
                                        vno.setText("");
                                        lmob.setText("");
                                        lname.setText("");
                                        item.setText("");
                                        buycode="";
                                        buyname="";
                                        buymob="";
                                        capturebill.setImageDrawable(null);
                                        capturebill.setBackgroundResource(R.drawable.capture);
                                        encodedImage="0";
                                        showErrorDialog2("Updated Successfully");
                                        Long tsLong = System.currentTimeMillis()/1000;
                                        ts = tsLong.toString();
                                    }


                                }
                            }
                            catch (Exception e)
                            {

                            }
                            pdialog.dismiss();

                        }



                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

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
        else
        {
            Pojokyc.saleno="";
            if(updatestatus.equalsIgnoreCase("0")) {

                db.insertsaleentry(buycode, buyname, item.getText().toString(), itemcod, ivar.getText().toString(), uom.getText().toString(), textViewQty2.getText().toString(), qcp.getText().toString(), pud.getText().toString(), rmk.getText().toString(), buymob, vno.getText().toString(), edtrate.getText().toString(), batype, ui, ts, "0", seno, snid, "I",nob.getText().toString());

                // dbs.execSQL("delete from sno");
                //dbs.execSQL("delete from qpar");
                spinnerb.setSelection(0);
                spinnerv.setSelection(0);
                ivar.setText("");
                uom.setText("");
                byname.setText("");
                textViewQty2.setText("");
                snob.setText("");
                nob.setText("");
                qcp.setText("");
                pud.setText("");
                rmk.setText("");
                vno.setText("");
                lmob.setText("");
                lname.setText("");
                item.setText("");
                buycode = "";
                buyname = "";
                buymob="";
                capturebill.setImageDrawable(null);
                capturebill.setBackgroundResource(R.drawable.capture);
                encodedImage = "0";
                pdialog.dismiss();
                showErrorDialog2("Sale Entry Saved Successfully in Local !!!");
                Long tsLong = System.currentTimeMillis() / 1000;
                ts = tsLong.toString();
            }
            else
            {
                db.updatesaleentry(Integer.valueOf(seid),buycode, buyname, item.getText().toString(), itemcod, ivar.getText().toString(), uom.getText().toString(), textViewQty2.getText().toString(), qcp.getText().toString(), pud.getText().toString(), rmk.getText().toString(), buymob, vno.getText().toString(), edtrate.getText().toString(), batype, ui, ts, "0", seno, snid, "U",nob.getText().toString());

                // dbs.execSQL("delete from sno");
                //dbs.execSQL("delete from qpar");
                spinnerb.setSelection(0);
                spinnerv.setSelection(0);
                updatestatus ="0";
                item.setEnabled(false);
                srccrop.setEnabled(false);
                ivar.setText("");
                uom.setText("");
                byname.setText("");
                textViewQty2.setText("");
                snob.setText("");
                nob.setText("");
                qcp.setText("");
                pud.setText("");
                rmk.setText("");
                vno.setText("");
                lmob.setText("");
                lname.setText("");
                item.setText("");
                buycode = "";
                buyname = "";
                buymob="";

                capturebill.setImageDrawable(null);
                capturebill.setBackgroundResource(R.drawable.capture);
                encodedImage = "0";
                pdialog.dismiss();
                showErrorDialog2("Sale Entry Updated Successfully in Local !!!");
                Long tsLong = System.currentTimeMillis() / 1000;
                ts = tsLong.toString();
            }
        }





    }

    public class MyRecyclerViewAdapterb extends RecyclerView.Adapter<MyRecyclerViewAdapterb.ViewHolder> {

        private List<Pojobuyer> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapterb(Context context, List<Pojobuyer> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.buyerlist, parent, false);
            return new ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final Pojobuyer pojobank = mData.get(position);

            holder.bcode.setText(pojobank.getBc());
            holder.bname.setText(pojobank.getBn());

            // holder.llist.setBackgroundResource(R.drawable.rbutton);
            holder.llist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    buyname=pojobank.getBn();
                    buycode=pojobank.getBc();
                    byname.setText(""+buyname);

                    //holder.llist.setBackgroundResource(R.drawable.rbutton2);


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
            TextView bcode,bname;
            ImageView del,ed;
            LinearLayout llist;

            ViewHolder(View itemView) {
                super(itemView);
                bcode = itemView.findViewById(R.id.bcode);
                bname = itemView.findViewById(R.id.bname);

                llist = itemView.findViewById(R.id.llist);

            }
        }

        // convenience method for getting data at click position

        // parent activity will implement this method to respond to click events

    }
    public void fetchqp() {
        SQLiteDatabase dbs = db.getWritableDatabase();

        Cursor cursor = dbs.query("qparnew", new String[]{"v1","v2","v3"
                }, "v1"  + "=? COLLATE NOCASE",
                new String[]{ts}, null, null, null, null);
        Log.e("COUNT", ""+cursor.getCount());

        //textViewQualityPara.setText(""  + cursor.getCount());
        //textViewSINo.setText("" +  cursor.getCount() +"(Serial Bags)");

        StringBuilder s = new StringBuilder(10000);
        // looping through all rows and adding to list
        if(cursor.getCount() > 0){

            if (cursor.moveToFirst()) {
                do {
                    String[] pn = cursor.getString(1).split("-");
                    s.append(pn[1] +  "-" +cursor.getString(2) + " \n");
                    qpnew.setText(s);
                    //

                } while (cursor.moveToNext());

            }
        }else {
            qpnew.setText("");
        }
    }
    public void LB_qty_fetch(){
        Cursor cr = dbs.rawQuery("select * from snowithqty where v4 = 'Y' and v6 = '"+itemcod+"' ",null);
        try{
            if(cr.getCount() > 0){
                if(cr.moveToFirst()){
                    LBqty = cr.getString(cr.getColumnIndexOrThrow("v2"));
                    LBname = cr.getString(cr.getColumnIndexOrThrow("v1")) ;
                    Log.e("oldqty00",LBqty+" , "+LBname);
                }
            }else{
                LBqty = "0";
            }
        }catch (Exception er){
            Log.e("error",Log.getStackTraceString(er));
        }finally {
            cr.close();
        }
    }
    public static InputFilter[] getFilter(String blockChars) {
        return new InputFilter[]{(source, start, end, dest, dstart, dend) -> {
            for (int i = start; i < end; i++) {
                if (source != null && blockChars.contains("" + source.charAt(i))) {
                    return source.subSequence(start, i);
                }
            }
            return null;
        }};
    }
}