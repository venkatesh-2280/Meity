package cdfi.fintantra.meity.Pawhs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cdfi.fintantra.meity.ExceptionHandler;
import cdfi.fintantra.meity.Pawhs.utils.DecimalDigitsInputFilter;
import cdfi.fintantra.meity.Pawhs.utils.MyConstants;
import cdfi.fintantra.meity.Pojokyc;
import cdfi.fintantra.meity.Pojosno;
import cdfi.fintantra.meity.R;
import cdfi.fintantra.meity.Pawhs.api.ApiService;
import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import cdfi.fintantra.meity.Pawhs.model.PostSiNoDetailDao;
import cdfi.fintantra.meity.VolleySingleton;

public class SnoActivitype extends AppCompatActivity implements View.OnClickListener {

    private PAWHSApplication pawhsApplication;
    private ApiService mAPIService;
    private String userName;
    private Context mContext;
    private TextView textViewTitle,lbl_uom;
    ArrayList sn = new ArrayList();
    ArrayList qp1 = new ArrayList();
    ArrayList dsn = new ArrayList();

    ArrayList qp2 = new ArrayList();
    ImageView qrcode,qrcodeF;
    String ts, pc;
    int m = 0;
    IntentIntegrator qrScan;
    private EditText editTextSerialNo,edt_qty,minqty,ttl_qty,countms;
    SearchView searchViewFarmerName;
    private Button buttonAdd, buttonClose, buttonCancel, buttoncancels;
    List<Pojosno> slist;
    EditText fsn, tsn;
    private RecyclerView recyclerViewSerial;
    MyRecyclerViewAdapterfarmer adapterf;
    private String orgnId, locnId, userId, localeId;

    private List<PostSiNoDetailDao> postSiNoDetailDaoList = new ArrayList<>();

    private SerialNoListAdapter serialNoListAdapter;
    private String lotno,selectionflag = "";
    SQLiteDatabase dbs;
    SQLiteDataBaseHandler db;
    ProgressDialog pdialog;
    Button madd, mscan,but_upd;
    LinearLayout l1,ll_lb,linear_single;
    String aesn = "",lb_flag = "N";
    RadioGroup rg_lb,rg_serial;
    RadioButton rb_y,rb_n,rb_ss,rb_ms;
    RelativeLayout rl_sl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_sno_activitype);
        mContext = this;
        pawhsApplication = PAWHSApplication.getGetInstance();
        userName = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.IN_USER_NAME);

        orgnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_ID);
        locnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCN_ID);
        userId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.USER_ID);
        localeId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCALE_ID);

        Bundle bundle = getIntent().getExtras();


        initView();

    }

    private void initView() {
        pdialog = new ProgressDialog(this);
        mAPIService = ApiUtils.getAPIService();
        getSupportActionBar().hide();
        textViewTitle = (TextView) findViewById(R.id.txt_title);
        textViewTitle.setText("Welcome " + userName + "\n" + "Purchase Entry");
        db = new SQLiteDataBaseHandler(SnoActivitype.this);
        dbs = db.getWritableDatabase();
        insertexsitserialno();
        editTextSerialNo = (EditText) findViewById(R.id.edt_serialno);
        buttonAdd = (Button) findViewById(R.id.but_add);
        buttonClose = (Button) findViewById(R.id.but_close);
        buttonCancel = (Button) findViewById(R.id.but_cancel);
        buttoncancels = (Button) findViewById(R.id.but_cancel2);
        searchViewFarmerName=(SearchView) findViewById(R.id.searchFarmerName);
        fsn = (EditText) findViewById(R.id.fsno);
        tsn = (EditText) findViewById(R.id.tsno);

        madd = (Button) findViewById(R.id.madd);
        mscan = (Button) findViewById(R.id.mscan);
        l1 = (LinearLayout) findViewById(R.id.l1);
        rg_lb = (RadioGroup)findViewById(R.id.rg_lb);
        rb_y = (RadioButton) findViewById(R.id.rb_y);
        rb_n = (RadioButton) findViewById(R.id.rb_n);
        rg_serial = (RadioGroup)findViewById(R.id.rg_serial);
        rb_ss = (RadioButton) findViewById(R.id.rb_ss);
        rb_ms = (RadioButton) findViewById(R.id.rb_ms);

        slist = new ArrayList<>();
        Intent i = getIntent();
        ts = i.getStringExtra("TS");
        pc = i.getStringExtra("PC");

        selectionflag = i.getStringExtra("selctionflag");
        recyclerViewSerial = (RecyclerView) findViewById(R.id.recycle_serialno);
        recyclerViewSerial.setLayoutManager(new LinearLayoutManager(mContext));
        qrcode = (ImageView) findViewById(R.id.qrcode);
        qrcodeF = (ImageView) findViewById(R.id.qrcodeF);
        edt_qty = (EditText) findViewById(R.id.edt_qty);
        edt_qty.requestFocus();
        minqty = (EditText) findViewById(R.id.minqty);
        but_upd = (Button) findViewById(R.id.but_upd);
        ttl_qty = (EditText) findViewById(R.id.ttl_qty);
        lbl_uom = (TextView) findViewById(R.id.lbl_uom);
        ll_lb = (LinearLayout) findViewById(R.id.ll_lb);
        rl_sl = (RelativeLayout) findViewById(R.id.rl_sl);
        linear_single = (LinearLayout) findViewById(R.id.linear_single);
        countms = (EditText) findViewById(R.id.countms);
        countms.setInputType(InputType.TYPE_CLASS_NUMBER);
        edt_qty.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        edt_qty.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(18,2)});
        minqty.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        minqty.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(18,2)});
        rg_lb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rb_y){
                    editTextSerialNo.setEnabled(false);
                    rl_sl.setVisibility(View.GONE);
                    mscan.setVisibility(View.GONE);
                    try{
                        Long tsLong = System.currentTimeMillis()/1000;
                        editTextSerialNo.setText("LB"+tsLong);
                    }catch (Exception er){
                        Log.e("error",Log.getStackTraceString(er));
                    }
                    lb_flag = "Y";
                }else if(i == R.id.rb_n){
                    rl_sl.setVisibility(View.VISIBLE);
                    editTextSerialNo.setEnabled(true);
                    editTextSerialNo.setText("");
                    edt_qty.setText("");
                    mscan.setVisibility(View.VISIBLE);
                    lb_flag = "N";
                }
            }
        });
        rg_serial.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioGroup.getCheckedRadioButtonId() == R.id.rb_ss){
                    linear_single.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.GONE);
                }else if(radioGroup.getCheckedRadioButtonId() == R.id.rb_ms){
                    linear_single.setVisibility(View.GONE);
                    l1.setVisibility(View.VISIBLE);
                }
            }
        });


        try{
            if(selectionflag.equalsIgnoreCase("LB")){
                rl_sl.setVisibility(View.GONE);
                rb_y.setChecked(true);
                rb_n.setEnabled(false);
            }else{
                rl_sl.setVisibility(View.VISIBLE);
                rb_n.setChecked(true);
                rb_y.setEnabled(false);
            }
        }catch (Exception er){
            Log.e("error",Log.getStackTraceString(er));
        }
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1005);
                qrScan = new IntentIntegrator(SnoActivitype.this);
                qrScan.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                qrScan.setPrompt("Scan Bar Code");
                qrScan.setBeepEnabled(true);
                qrScan.setCameraId(0);
                qrScan.setOrientationLocked(false);
                qrScan.setBarcodeImageEnabled(true);
                qrScan.initiateScan();
            }
        });

        qrcodeF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScan = new IntentIntegrator(SnoActivitype.this);
                qrScan.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                qrScan.setPrompt("Scan Bar Code");
                qrScan.setBeepEnabled(true);
                qrScan.setCameraId(0);
                qrScan.setOrientationLocked(false);
                qrScan.setBarcodeImageEnabled(true);
                qrScan.initiateScan();
            }
        });

        //  postSiNoDetailDaoList=new ArrayList<>();

        buttonAdd.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
        buttoncancels.setOnClickListener(this);
        buttonClose.setOnClickListener(this);
        madd.setOnClickListener(this);
        mscan.setOnClickListener(this);

        adapterf = new MyRecyclerViewAdapterfarmer(mContext, slist);
        searchViewFarmerName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(adapterf!=null){
                    adapterf.filter(newText);
                }

                return false;
            }
        });
        fetchsn();


        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fsn.getText().toString().equalsIgnoreCase("")) {
                    showErrorDialog("Empty From Serial No");
                }/* else if (tsn.getText().toString().equalsIgnoreCase("")) {
                    showErrorDialog("Empty To Serial No");
                }*/
                else if (countms.getText().toString().trim().equalsIgnoreCase("")) {
                    showErrorDialog("Empty Count ");
                }
                else if(minqty.getText().toString().trim().equalsIgnoreCase("")){
                    showErrorDialog("Empty Minimum Qty");
                }
                else {

                    // do something..

                    try {
                        if (fsn.getText().toString().length() == 8) {

//                            if (String.valueOf(fsn.getText().toString().charAt(0)).equalsIgnoreCase(String.valueOf(tsn.getText().toString().charAt(0)))) {
                                try {
                                    //Toast.makeText(pawhsApplication, "Serial No Generating Please Wait....", Toast.LENGTH_SHORT).show();
                                    String v1 = fsn.getText().toString().substring(1);
                                   // String v2 = tsn.getText().toString().substring(1);
                                    String fc = String.valueOf(fsn.getText().toString().charAt(0));
                                    String mq = minqty.getText().toString().trim();
                                    // Toast.makeText(pawhsApplication, ""+v1[1].toString(), Toast.LENGTH_SHORT).show();
                                    int x = Integer.parseInt(v1);
                                   // int y = Integer.parseInt(countms.getText().toString());
                                    int z = Math.abs(Integer.parseInt(countms.getText().toString()));
                                    dsn.clear();
                                    for (int i = 0; i < z; i++) {
                                        if (Integer.toString(x).length() == 7) {
                                            if (sn.contains((fc + "" + x).toLowerCase())||sn.contains((fc + "" + x).toUpperCase())) {
                                                dsn.add(fc + "" + x);
                                            }
                                            else {
                                              //  db.insertsnope(fc + "" + x, "1", ts);
                                                db.insertsnowithqty(fc + "" + x, mq, ts,lb_flag,"1",MyConstants.productcode,"0");
                                              //  sn.add(fc + "" + x);
                                            }
                                        } else if (Integer.toString(x).length() == 6) {
                                            if (sn.contains((fc + "0" + x).toLowerCase())||sn.contains((fc + "0" + x).toUpperCase())) {
                                                dsn.add(fc + "0" + x);
                                            }
                                            else {
                                                //db.insertsnope(fc + "0" + x, "1", ts);
                                                db.insertsnowithqty(fc + "0" + x, mq, ts,lb_flag,"1",MyConstants.productcode,"0");
                                             //   sn.add(fc + "0" + x);
                                            }
                                        } else if (Integer.toString(x).length() == 5) {
                                            if (sn.contains((fc + "00" + x).toLowerCase())||sn.contains((fc + "00" + x).toUpperCase())) {
                                                dsn.add(fc + "00" + x);
                                            }
                                            else {
                                               // db.insertsnope(fc + "00" + x, "1", ts);
                                                db.insertsnowithqty(fc + "00" + x, mq, ts,lb_flag,"1",MyConstants.productcode,"0");
                                             //   sn.add(fc + "00" + x);
                                            }
                                        } else if (Integer.toString(x).length() == 4) {
                                            if (sn.contains((fc + "000" + x).toLowerCase())||sn.contains((fc + "000" + x).toUpperCase())) {
                                                dsn.add(fc + "000" + x);
                                            }
                                            else {
                                              //  db.insertsnope(fc + "000" + x, "1", ts);
                                                db.insertsnowithqty(fc + "000" + x, mq, ts,lb_flag,"1",MyConstants.productcode,"0");
                                              //  sn.add(fc + "000" + x);
                                            }
                                        } else if (Integer.toString(x).length() == 3) {
                                            if (sn.contains((fc + "0000" + x).toLowerCase())||sn.contains((fc + "0000" + x).toUpperCase())) {
                                                dsn.add(fc + "0000" + x);
                                            }
                                            else {
                                               // db.insertsnope(fc + "0000" + x, "1", ts);
                                                db.insertsnowithqty(fc + "0000" + x, mq, ts,lb_flag,"1",MyConstants.productcode,"0");
                                             //   sn.add(fc + "0000" + x);
                                            }
                                        } else if (Integer.toString(x).length() == 2) {
                                            if (sn.contains((fc + "00000" + x).toLowerCase())||sn.contains((fc + "00000" + x).toUpperCase())) {
                                                dsn.add(fc + "00000" + x);
                                            }
                                            else {

                                              //  db.insertsnope(fc + "00000" + x, "1", ts);
                                                db.insertsnowithqty(fc + "00000" + x, mq, ts,lb_flag,"1",MyConstants.productcode,"0");
                                             //   sn.add(fc + "00000" + x);
                                            }
                                        } else if (Integer.toString(x).length() == 1) {
                                            if (sn.contains((fc + "000000" + x).toLowerCase())||sn.contains((fc + "000000" + x).toUpperCase())) {
                                                dsn.add(fc + "000000" + x);
                                            }
                                            else {
                                               // db.insertsnope(fc + "000000" + x, "1", ts);
                                                db.insertsnowithqty(fc + "000000" + x, mq, ts,lb_flag,"1",MyConstants.productcode,"0");
                                              //  sn.add(fc + "000000" + x);
                                            }
                                        }


                                        x++;


                                    }
                                    if(dsn.size()>0)
                                    {
                                        showErrorDialog(Arrays.toString(new ArrayList[]{dsn})+" These serial numbers are already available so unable to add it");
                                    }
                                    // Toast.makeText(getApplicationContext(), ""+z, Toast.LENGTH_SHORT).show();
                                    // db.insertsno(fsn.getText().toString()+"-"+tsn.getText().toString(), String.valueOf(z),ts);
                                   // l1.setVisibility(View.GONE);
                                    fetchsn();
                                    fsn.setText("");
                                    tsn.setText("");
                                    minqty.setText("");
                                    countms.setText("");
                                } catch (Exception e) {
                                    Log.e("ERROR", "" + e);
                                    // Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_SHORT).show();
                                    showErrorDialog("Invalid Serial No");
                                }
                            /*}
                            else {
                                showErrorDialog("Serial Number Starting Character Should Be Same");
                            }*/
                            // do something..


                        } else {
                            showErrorDialog("Serial No Length Should Be 8");
                        }
                    } catch (Exception e) {
                        showErrorDialog("Invalid Serial No");
                    }
                }
            }
        });
        but_upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double q  = 0;
                try{
                    if(!edt_qty.getText().toString().trim().equalsIgnoreCase("")){
                        q = Double.parseDouble(edt_qty.getText().toString().trim());
                    }else{
                        q = 0;
                    }
                }
                catch (Exception er){
                    Log.e("error",Log.getStackTraceString(er));
                    q = 0;
                }
                String serialNo = editTextSerialNo.getText().toString().trim();
                Log.e("TST", "" + ts);
                if (serialNo.isEmpty()) {
                    showErrorDialog("Empty Serial No");
                }else if(serialNo.length() < 8){
                    showErrorDialog("Serial No Cannot be Less Than 8 digits");
                }
                else if(edt_qty.getText().toString().trim().equalsIgnoreCase("")){
                    showErrorDialog("Empty Qty");
                }else if(q <= 0){
                    showErrorDialog("Qty Cannot be Zero");
                }else{
                    //db.insertsnowithqty(serialNo,edt_qty.getText().toString().trim(),ts);
                    dbs.execSQL("Update snowithqty set v2 = '"+edt_qty.getText().toString().trim()+"' " +
                            " where v3= '"+ts+"' and v1 = '"+serialNo+"' and v6 = '"+MyConstants.productcode+"' ");
                    editTextSerialNo.setText("");
                    editTextSerialNo.setEnabled(true);
                    edt_qty.setText("0");
                    but_upd.setVisibility(View.GONE);
                    buttonAdd.setVisibility(View.VISIBLE);
                    fetchsn();
                    linear_single.setVisibility(View.GONE);
                    rb_ss.setChecked(false);
                }
            }
        });
        Cursor cr = null;
        try{
            cr = dbs.rawQuery("select * from ProductMasterAllProduct where PmapOutPawhsCode = '"+ MyConstants.productcode +"' ",null);
            if(cr.getCount()>0){
                if(cr.moveToFirst()){
                    lbl_uom.setText("("+cr.getString(cr.getColumnIndexOrThrow("out_uomtype_code"))+")");
                }
            }
        }catch (Exception er){
            Log.e("error",Log.getStackTraceString(er));
        }finally {
            cr.close();
        }

        countms.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try{
                    if(editable.length() > 0 && !editable.toString().trim().equalsIgnoreCase("")){
                        if(!fsn.getText().toString().trim().equalsIgnoreCase("")) {
                            String fc = String.valueOf(fsn.getText().toString().charAt(0));
                            String v1 = fsn.getText().toString().substring(1);
                            int x = Integer.parseInt(v1);
                            int y = Integer.parseInt(editable.toString());
                            for (int i = 0; i < y; i++) {
//                            Log.e("test1",""+i);
//                            Log.e("test",""+i);
                                if (String.valueOf(x).length() == 7) {


                                    tsn.setText(fc + "" + x);

                                } else if (String.valueOf(x).length() == 6) {

                                    tsn.setText(fc + "0" + x);

                                } else if (String.valueOf(x).length() == 5) {

                                    tsn.setText(fc + "00" + x);

                                } else if (String.valueOf(x).length() == 4) {

                                    tsn.setText(fc + "000" + x);

                                } else if (String.valueOf(x).length() == 3) {

                                    tsn.setText(fc + "0000" + x);

                                } else if (String.valueOf(x).length() == 2) {

                                    tsn.setText(fc + "00000" + x);

                                } else if (String.valueOf(x).length() == 1) {

                                    tsn.setText(fc + "000000" + x);

                                }
                                x++;
                            }

                        }else{
                            showErrorDialog("From Serial Number Cannot be Empty");
                            editable.clear();
                        }
                    }
                    else{
                        tsn.setText("");
                    }
                }catch (Exception er){
                    Log.e("error",Log.getStackTraceString(er));
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.but_add:
                addSerialNo();

                break;
            case R.id.but_close:
                if(isNetworkAvailable()) {

                    checkserialno();
                }
                else
                {
                    finish();
                }
 //                 finish();
                break;

            case R.id.mscan:
                maddnew();
                break;
            case R.id.but_cancel2:
                if(isNetworkAvailable()) {

                    checkserialno();
                }
                else
                {
                    finish();
                }
//                 finish();
                break;
            case R.id.but_cancel:
                if(isNetworkAvailable()) {

                    checkserialno();
                }
                else
                {
                    finish();
                }
//                 finish();
                break;
            default:
                break;
        }
    }

    private void addSerialNo() {
        double q  = 0;
        try{
            if(!edt_qty.getText().toString().trim().equalsIgnoreCase("")){
                q = Double.parseDouble(edt_qty.getText().toString().trim());
            }else{
                q = 0;
            }
        }
        catch (Exception er){
            Log.e("error",Log.getStackTraceString(er));
            q = 0;
        }
        String serialNo = editTextSerialNo.getText().toString();
        Log.e("TST", "" + ts);
        if (serialNo.isEmpty()) {
            showErrorDialog("Empty Serial No");
        }else if(editTextSerialNo.getText().toString().trim().length() < 8){
            showErrorDialog("Serial No Cannot be Less Than 8 digits");
        }
        else if(edt_qty.getText().toString().trim().equalsIgnoreCase("")){
            showErrorDialog("Empty Qty");
        }else if(q <= 0){
            showErrorDialog("Qty Cannot be Zero");
        }
        else {

            if (sn.contains(serialNo.toLowerCase())||sn.contains(serialNo.toUpperCase())) {
                showErrorDialog("Serial No Already Exists");
            }
            else {

                db.insertsnowithqty(serialNo,edt_qty.getText().toString().trim(),ts,lb_flag,"1",MyConstants.productcode,"0");
                if(lb_flag.equalsIgnoreCase("Y")){
                    try{
                        Long tsLong = System.currentTimeMillis()/1000;
                        editTextSerialNo.setText("LB"+tsLong);
                    }catch (Exception er){
                        Log.e("error",Log.getStackTraceString(er));
                    }
                }else{
                    editTextSerialNo.setText("");
                }
                edt_qty.setText("0");
                fetchsn();
               // db.insertsnope(serialNo, "1", ts);
                linear_single.setVisibility(View.VISIBLE);
                //buttoncancels.setVisibility(View.VISIBLE);
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
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }

        }


    }

    protected void processScannedData(String scanData) {
        Log.e("SCAN DATA", scanData);
        XmlPullParserFactory pullParserFactory;
        try {
            // init the parserfactory
            pullParserFactory = XmlPullParserFactory.newInstance();
            // get the parser
            XmlPullParser parser = pullParserFactory.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            String fstring2 = null;
            try {
                Log.e("Scan Data", "" + scanData);
                if(rb_ms.isChecked()){
                    fsn.setText(scanData);
                }else{
                    editTextSerialNo.setText(scanData);
                }
            } catch (Exception e) {

            }


            parser.setInput(new StringReader(scanData));


            // display the data on screen
            //  displayScannedData();
        } catch (XmlPullParserException e) {

            Log.e("AAdhar", "" + e);
            e.printStackTrace();
        }
    }//

    public class MyRecyclerViewAdapterfarmer extends RecyclerView.Adapter<MyRecyclerViewAdapterfarmer.ViewHolder> {

        private List<Pojosno> mData;
        private LayoutInflater mInflater;
        private List<Pojosno> arraylist;

        // data is passed into the constructor
        public MyRecyclerViewAdapterfarmer(Context context, List<Pojosno> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
            this.arraylist = new ArrayList<Pojosno>();
            this.arraylist.addAll(data);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.serial_list_screen_new_purchase, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Pojosno pojofar = mData.get(position);
            /*if(lb_flag.equalsIgnoreCase("Y")){
                holder.llist.setVisibility(View.GONE);
            }*/
            try{
                if(selectionflag.equalsIgnoreCase("LB") && pojofar.getSn().substring(0,2).equalsIgnoreCase("LB")){
                    holder.llist.setVisibility(View.VISIBLE);
                }else if(selectionflag.equalsIgnoreCase("sl") && !pojofar.getSn().substring(0,2).equalsIgnoreCase("LB")){
                    holder.llist.setVisibility(View.VISIBLE);
                }
            }catch (Exception er){
                Log.e("error",Log.getStackTraceString(er));
                holder.llist.setVisibility(View.VISIBLE);
            }
            holder.name.setText(pojofar.getSn());
            holder.txt_qty.setText(pojofar.getQty());
            //holder.llist.setVisibility(View.GONE);


//            if(pojofar.getQp().equalsIgnoreCase("0"))
//            {
//                holder.llist.setText("Add QP");
//            }
//            else
//            {
//                holder.llist.setText("Add/View QP");
//            }
            holder.llist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rb_ss.setChecked(true);
                    editTextSerialNo.setText(pojofar.getSn());
                    editTextSerialNo.setEnabled(false);
                    edt_qty.setText(pojofar.getQty());
                    but_upd.setVisibility(View.VISIBLE);
                    buttonAdd.setVisibility(View.GONE);
                }
            });
            holder.img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // dbs.delete("snope", "id" + "=" + pojofar.getId(), null);
                    //  dbs.delete("qpar", "v1" + "=" + pojofar.getId(), null);
                   /* rb_n.setChecked(true);
                    rb_n.setEnabled(true);*/
                    editTextSerialNo.setText("");
                    edt_qty.setText("");
//                    dbs.delete("snowithqty", "id" + "=" + pojofar.getId(), null);
                    dbs.execSQL("update snowithqty set v7 = 2 where id = '"+pojofar.getId()+"' ");
                    int a = sn.indexOf(pojofar.getSn());
                    sn.remove(a);
           //         sn.remove(pojofar.getSn());
                    fetchsn();

                }
            });

//            holder.llist.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(getApplicationContext(), Qpar.class);
//                    i.putExtra("SID",pojofar.getId());
//                    i.putExtra("PC",pc);
//                    startActivity(i);
//
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
        public  void filter(String charText) {

            //this.arraylist.addAll(mData);
            Log.e("Hello","hi"+arraylist.size());
            charText = charText.toLowerCase(Locale.getDefault());
            mData.clear();
            if (charText.length() == 0) {
                mData.addAll(arraylist);
            } else {
                for (Pojosno pojope : arraylist) {
                    if (pojope.getSn().toLowerCase(Locale.getDefault()).contains(charText)) {
                        mData.add(pojope);
                    }
                }
            }
            adapterf.notifyDataSetChanged();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name, fhw, ph, lcn, ty, t5,txt_qty;
            Button llist;
            ImageView img_delete;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.txt_serialNo);
                llist = itemView.findViewById(R.id.llist);
                img_delete = itemView.findViewById(R.id.img_delete);
                txt_qty = itemView.findViewById(R.id.txt_qty);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // put your code here...
       // insertexsitserialno();
        fetchsn();
    }

    public void fetchsn() {
       // sn.clear();
        String looseflag = "";
        String qry = "Select * from snowithqty where v3 = '"+ts+"' and v6 = '"+MyConstants.productcode+"' and v7 != '2' ";
/*        Cursor cursor = dbs.query("snope", new String[]{"id", "v1", "v2", "v3"
                }, "v3" + "=? COLLATE NOCASE",
                new String[]{ts}, null, null, null, null);*/
       /* Cursor cursor = dbs.query("snowithqty", new String[]{"id", "v1", "v2", "v3","v4"
                }, "v3" + "=? COLLATE NOCASE",
                new String[]{ts}, null, null, null, null);*/
        Cursor cursor = dbs.rawQuery(qry,null);
        slist.clear();
        //  Cursor cursor = dbs.rawQuery(selectQuery, null);

        if (cursor.getCount() == 0) {
            recyclerViewSerial.setAdapter(adapterf);
            adapterf.notifyDataSetChanged();
        }
        double count = 0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pojosno pojosno = new Pojosno();

//                if (cursor.getString(2).equalsIgnoreCase("1")) {
             /*   if (cursor.getString(3).equalsIgnoreCase("1")) {

                    pojosno.setId(cursor.getString(0));
                    pojosno.setSn(cursor.getString(1));
                    sn.add(cursor.getString(1));
                    pojosno.setQty(cursor.getString(cursor.getColumnIndexOrThrow("v2")));
                } else {*/
                    pojosno.setId(cursor.getString(cursor.getColumnIndexOrThrow("id")));
                    //String[] v1 =cursor.getString(1).split(" ");
                    pojosno.setSn(cursor.getString(cursor.getColumnIndexOrThrow("v1")));
                  //  sn.add(cursor.getString(1));
                    pojosno.setQty(cursor.getString(cursor.getColumnIndexOrThrow("v2")));
              //  }
                count += Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("v2")));
                looseflag = cursor.getString(cursor.getColumnIndexOrThrow("v4"));
                slist.add(pojosno);
                // array2.add(cursor.getString(11));
                // Log.e("VAL",""+cursor.getString(11));
                adapterf = new MyRecyclerViewAdapterfarmer(mContext, slist);
                recyclerViewSerial.setAdapter(adapterf);
                adapterf.notifyDataSetChanged();
            } while (cursor.moveToNext());

        }
      /*  if(looseflag.equalsIgnoreCase("Y")){
            rb_y.setChecked(true);
            rb_n.setEnabled(false);
        }else if(looseflag.equalsIgnoreCase("N")){
            rb_y.setEnabled(false);
            rb_n.setChecked(true);
        }*/
       /* if(slist.size() == 0){
            rb_n.setEnabled(true);
            rb_y.setEnabled(true);
        }*/
        ttl_qty.setText(""+count);
        insertexsitserialno();
    }

    public void maddnew() {


        m++;

//        Toast.makeText(pawhsApplication, ""+(m%2), Toast.LENGTH_SHORT).show();

//        if((m % 2) == 0)
//        {


        l1.setVisibility(View.VISIBLE);
//        }
//        else
//        {
//
//            l1.setVisibility(View.VISIBLE);
//        }
    }
    public void checkserialno()
    {
        finish();
//        Cursor cursor = dbs.rawQuery("SELECT * FROM snope WHERE v3 = '" + ts + "'", null);
  /*      Cursor cursor = dbs.rawQuery("SELECT * FROM snowithqty WHERE v3 = '" + ts + "'", null);
        if (cursor.getCount() > 0) {
            pdialog.setCanceledOnTouchOutside(false);
            pdialog.setTitle("Uploading Please Wait.......");
            pdialog.show();
            JSONObject jsonObject = new JSONObject();
            try {

                JSONArray jsonArray = null;
                jsonObject.put("Instance", Pojokyc.instance);
                jsonObject.put("purchase_no", Pojokyc.purno);
                jsonArray = new JSONArray();

                if (cursor.moveToFirst()) {
                    do {


                        JSONObject jsonObject2 = new JSONObject();
                        jsonObject2.put("serial_no", cursor.getString(cursor.getColumnIndexOrThrow("v1")));
                        jsonObject2.put("err_serial_no", "");
                        jsonArray.put(jsonObject2);


                    } while (cursor.moveToNext());
                    jsonObject.put("List", jsonArray);

                }

                Log.e("SERIALNO", "" + jsonObject);
            } catch (Exception e) {

            }
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, ApiUtils.TEST_URL_API + "New_PAWHS_SaleEntry/Pawhs_PurchaseEntry_checkdub", jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("CCCC", "" + response);
                            try {
                                JSONArray jsonArray = response.getJSONArray("list");
                                if (jsonArray.length() == 0) {
                                    finish();
                                    //Toast.makeText(getApplicationContext(), "Valid", Toast.LENGTH_SHORT).show();
                                } else {
                                    StringBuffer sb = null;
                                    aesn = "";

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                        aesn += jsonObject1.getString("err_serial_no")+",";


                                        Log.e("SERIAL NO :", "" + jsonObject1.getString("err_serial_no"));
                                    }
                                    sb = new StringBuffer(aesn);
                                    sb.deleteCharAt(sb.length() - 1);
                                    showErrorDialog(String.valueOf(sb));
                                }

                            } catch (Exception e) {

                            }
                            pdialog.dismiss();


                        }


                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pdialog.dismiss();

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
            finish();
        }*/
    }

    @Override
    public void onBackPressed() {
        if(isNetworkAvailable()) {

            checkserialno();
        }
        else
        {
            finish();
        }
       //  finish();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void insertexsitserialno()
    {
        sn.clear();
        String qry = "select * from snowithqty where v7 != '2'";
        Cursor cr = dbs.rawQuery(qry,null);
        try{

                 if(cr.getCount()>0)
                 {
                     if(cr.moveToFirst())
                     {
                         do {
                             sn.add(cr.getString(cr.getColumnIndexOrThrow("v1")));

                         }while (cr.moveToNext());
                     }
                 }
                //  sn.add(serialNo);

        }catch (Exception er){
            Log.e("error",er.toString());
        }finally {
            cr.close();
        }
    }
}