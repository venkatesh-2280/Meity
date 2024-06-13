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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import cdfi.fintantra.meity.MainActivity4;
import cdfi.fintantra.meity.Pawhs.utils.DecimalDigitsInputFilter;
import cdfi.fintantra.meity.Pawhs.utils.MyConstants;
import cdfi.fintantra.meity.Pojokyc;
import cdfi.fintantra.meity.Pojosno;
import cdfi.fintantra.meity.R;
import cdfi.fintantra.meity.Pawhs.api.ApiService;
import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import cdfi.fintantra.meity.Pawhs.model.PostSiNoDetailDao;
import cdfi.fintantra.meity.VolleySingleton;

public class SnoActivity extends AppCompatActivity implements View.OnClickListener {
    private PAWHSApplication pawhsApplication;
    private ApiService mAPIService;
    private String userName;
    private Context mContext;
    String aesn = "";
    ProgressDialog pdialog;
    private TextView textViewTitle;
    ArrayList sn = new ArrayList();
    ArrayList qp1 = new ArrayList();
    ArrayList dsn = new ArrayList();
    ArrayList qp2 = new ArrayList();
    ImageView qrcode,qrcodeF;
    String ts,pc;
    int m = 0;
    IntentIntegrator qrScan;
    private AutoCompleteTextView editTextSerialNo;
    EditText fsn,tsn;
    private Button buttonAdd,buttonClose,buttonCancel,mscan;
    List<Pojosno> slist;
    private RecyclerView recyclerViewSerial;
    MyRecyclerViewAdapterfarmer adapterf;
    private String orgnId, locnId, userId, localeId,oldqty;

    private List<PostSiNoDetailDao> postSiNoDetailDaoList=new ArrayList<>();
    SearchView searchViewFarmerName;
    private SerialNoListAdapter serialNoListAdapter;
    private String lotno;
    SQLiteDatabase dbs;
    SQLiteDataBaseHandler db;
    Button madd,but_upd;
    LinearLayout l1,linear_single,linear_singlebutton;
    EditText  reqqty,ttl_qty,countms;
    TextView availqty,uom_lbl,totalavailablequantity,ttl_saleqty;
    ArrayList<String> serialnos = new ArrayList<>();
    ArrayList<String> serialnosonly = new ArrayList<>();
    ArrayList<String> qty = new ArrayList<>();
    RelativeLayout rl_sl;
    RadioGroup rg_serial;
    RadioButton rb_ss,rb_ms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.serial_screen);
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
        textViewTitle.setText("Welcome " + userName + "\n" + "Sale Entry");
        db = new SQLiteDataBaseHandler(SnoActivity.this);
        dbs = db.getWritableDatabase();
        searchViewFarmerName=(SearchView) findViewById(R.id.searchFarmerName);
        editTextSerialNo = (AutoCompleteTextView) findViewById(R.id.edt_serialno);
        buttonAdd = (Button) findViewById(R.id.but_add);
        buttonClose = (Button) findViewById(R.id.but_close);
        buttonCancel = (Button) findViewById(R.id.but_cancel);
        mscan = (Button) findViewById(R.id.mscan);
        l1 = (LinearLayout)findViewById(R.id.l1);
      //  editTextSerialNo.setEnabled(false);
        slist = new ArrayList<>();
        Intent i = getIntent();
        ts = i.getStringExtra("TS");
        pc = i.getStringExtra("PC");
        madd = (Button)findViewById(R.id.madd);
        fsn = (EditText)findViewById(R.id.fsno);
        tsn = (EditText)findViewById(R.id.tsno);
        recyclerViewSerial=(RecyclerView)findViewById(R.id.recycle_serialno);
        recyclerViewSerial.setLayoutManager(new LinearLayoutManager(mContext));
        qrcode = (ImageView)findViewById(R.id.qrcode);
        qrcodeF = (ImageView) findViewById(R.id.qrcodeF);
        reqqty = (EditText)findViewById(R.id.reqqty);
        editTextSerialNo.requestFocus();
        availqty = (TextView) findViewById(R.id.availqty);
        totalavailablequantity = (TextView) findViewById(R.id.totalavailablequantity);
        but_upd = (Button) findViewById(R.id.but_upd);
        ttl_qty = (EditText) findViewById(R.id.ttl_qty);
        uom_lbl = (TextView) findViewById(R.id.uom_lbl);
        rl_sl = (RelativeLayout) findViewById(R.id.rl_sl);
        ttl_saleqty = (TextView) findViewById(R.id.ttl_saleqty);
        rg_serial = (RadioGroup)findViewById(R.id.rg_serial);
        rb_ss = (RadioButton)findViewById(R.id.rb_ss);
        rb_ms = (RadioButton)findViewById(R.id.rb_ms);
        linear_single = (LinearLayout)findViewById(R.id.linear_single);
        linear_singlebutton = (LinearLayout)findViewById(R.id.linear_singlebutton);
        countms = (EditText)findViewById(R.id.countms);
        countms.setInputType(InputType.TYPE_CLASS_NUMBER);
        availqty.setInputType(InputType.TYPE_CLASS_NUMBER |InputType.TYPE_NUMBER_FLAG_DECIMAL);
        reqqty.setInputType(InputType.TYPE_CLASS_NUMBER |InputType.TYPE_NUMBER_FLAG_DECIMAL);
        availqty.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(18,2)});
        reqqty.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(18,2)});
    /*    lb_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_y:
                        editTextSerialNo.setEnabled(true);
                        loadserialdropdown("Y");
                        break;
                    case R.id.rb_n:
                        editTextSerialNo.setEnabled(true);
                        loadserialdropdown("N");
                        break;
                }
            }
        });*/
        rg_serial.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioGroup.getCheckedRadioButtonId() == R.id.rb_ss){
                    linear_single.setVisibility(View.VISIBLE);
                    linear_singlebutton.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.GONE);
                }else if(radioGroup.getCheckedRadioButtonId() == R.id.rb_ms){
                    l1.setVisibility(View.VISIBLE);
                    linear_single.setVisibility(View.GONE);
                    linear_singlebutton.setVisibility(View.GONE);
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
                qrScan = new IntentIntegrator(SnoActivity.this);
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
                qrScan = new IntentIntegrator(SnoActivity.this);
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
        loadserialdropdown("");
        editTextSerialNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (SnoActivity.this, R.layout.spinnertext3, serialnos);
                editTextSerialNo.setAdapter(adapter);
                editTextSerialNo.setThreshold(0);
                editTextSerialNo.showDropDown();
            }
        });



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
        buttonAdd.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
        buttonClose.setOnClickListener(this);
        mscan.setOnClickListener(this);
        madd.setOnClickListener(this);

        fetchsn();

        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fsn.getText().toString().equalsIgnoreCase(""))
                {
                    showErrorDialog("Empty From Serial No");
                }
                /*else if(tsn.getText().toString().equalsIgnoreCase(""))
                {
                    showErrorDialog("Empty To Serial No");
                }*/
                else if (countms.getText().toString().trim().equalsIgnoreCase("")) {
                    showErrorDialog("Empty Count ");
                }
                else
                {
                    try {
                        if (fsn.getText().toString().length() ==8)
                        {
//                            if(String.valueOf(fsn.getText().toString().charAt(0)).equalsIgnoreCase(String.valueOf(tsn.getText().toString().charAt(0))))
//                            {
                                try {
                                    //Toast.makeText(pawhsApplication, "Serial No Generating Please Wait....", Toast.LENGTH_SHORT).show();
                                    String v1 = fsn.getText().toString().substring(1);
                                  //  String v2 = tsn.getText().toString().substring(1);
                                    String fc = String.valueOf(fsn.getText().toString().charAt(0));

                                    // Toast.makeText(pawhsApplication, ""+v1[1].toString(), Toast.LENGTH_SHORT).show();
                                    int x = Integer.parseInt(v1);
                                  //  int y = Integer.parseInt(v2);
                                    int z = Math.abs(Integer.parseInt(countms.getText().toString()));

                                    dsn.clear();
                                    for (int i = 0; i < (z + 1); i++) {
                                        if (Integer.toString(x).length() == 7) {
                                            if (sn.contains(fc + "" + x)) {
                                                dsn.add(fc + "" + x);
                                            }
                                            else {
                                                String val = serialnumberchk(fc + "" + x);
                                                if(!val.equalsIgnoreCase("")){
                                                    db.insertsno(fc + "" + x , "1", ts,pc,"0",val);
                                                    sn.add(fc + "" + x);
                                                    updatesnowitqty(fc + "" + x,val,"-");
                                                }
                                                else{
                                                    dsn.add(fc + "" + x);
                                                }
                                            }
                                        } else if (Integer.toString(x).length() == 6) {
                                            if (sn.contains(fc + "0" + x)) {
                                                dsn.add(fc + "0" + x);
                                            }
                                            else {
                                                String val = serialnumberchk(fc + "0" + x);
                                                if(!val.equalsIgnoreCase("")) {
                                                    db.insertsno(fc + "0" + x, "1", ts,pc,"0",val);
                                                    sn.add(fc + "0" + x);
                                                    updatesnowitqty(fc + "0" + x,val,"-");
                                                }else{
                                                    dsn.add(fc + "0" + x);
                                                }
                                            }
                                        } else if (Integer.toString(x).length() == 5) {
                                            if (sn.contains(fc + "00" + x)) {
                                                dsn.add(fc + "00" + x);
                                            }
                                            else {
                                                String val = serialnumberchk(fc + "00" + x);
                                                if(!val.equalsIgnoreCase("")) {
                                                    db.insertsno(fc + "00" + x, "1", ts,pc,"0",val);
                                                    sn.add(fc + "00" + x);
                                                    updatesnowitqty(fc + "00" + x,val,"-");
                                                }else{
                                                    dsn.add(fc + "00" + x);
                                                }
                                            }
                                        } else if (Integer.toString(x).length() == 4) {
                                            if (sn.contains(fc + "000" + x)) {
                                                dsn.add(fc + "000" + x);
                                            }
                                            else {
                                                String val = serialnumberchk(fc + "000" + x);
                                                if(!val.equalsIgnoreCase("")) {
                                                    db.insertsno(fc + "000" + x, "1", ts,pc,"0",val);
                                                    sn.add(fc + "000" + x);
                                                    updatesnowitqty(fc + "000" + x,val,"-");
                                                }else{
                                                    dsn.add(fc + "000" + x);
                                                }
                                            }
                                        } else if (Integer.toString(x).length() == 3) {
                                            if (sn.contains(fc + "0000" + x)) {
                                                dsn.add(fc + "0000" + x);
                                            }
                                            else {
                                                String  val = serialnumberchk(fc + "0000" + x);
                                                if(!val.equalsIgnoreCase("")) {
                                                    db.insertsno(fc + "0000" + x , "1", ts,pc,"0",val);
                                                    sn.add(fc + "0000" + x);
                                                    updatesnowitqty(fc + "0000" + x,val,"-");
                                                }else{
                                                    dsn.add(fc + "0000" + x);
                                                }
                                            }
                                        } else if (Integer.toString(x).length() == 2) {
                                            if (sn.contains(fc + "00000" + x)) {
                                                dsn.add(fc + "00000" + x);
                                            }
                                            else {
                                                String val = serialnumberchk(fc + "00000" + x);
                                                if(!val.equalsIgnoreCase("")) {
                                                    db.insertsno(fc + "00000" + x, "1", ts,pc,"0",val);
                                                    sn.add(fc + "00000" + x);
                                                    updatesnowitqty(fc + "00000" + x,val,"-");
                                                }else{
                                                    dsn.add(fc + "00000" + x);
                                                }
                                            }
                                        } else if (Integer.toString(x).length() == 1) {
                                            if (sn.contains(fc + "000000" + x)) {
                                                dsn.add(fc + "000000" + x);
                                            }
                                            else {
                                                String val = serialnumberchk(fc + "000000" + x);
                                                if(!val.equalsIgnoreCase("")) {
                                                    db.insertsno(fc + "000000" + x , "1", ts,pc,"0",val);
                                                    sn.add(fc + "000000" + x);
                                                    updatesnowitqty(fc + "000000" + x,val,"-");
                                                }else {
                                                    dsn.add(fc + "000000" + x);
                                                }
                                            }
                                        }


                                        x++;


                                    }
                                    if(dsn.size()>0)
                                    {
                                        showErrorDialog(Arrays.toString(new ArrayList[]{dsn})+" These serial numbers are already available or Invalid so unable to add it.");
                                    }
                                    // Toast.makeText(getApplicationContext(), ""+z, Toast.LENGTH_SHORT).show();
                                    // db.insertsno(fsn.getText().toString()+"-"+tsn.getText().toString(), String.valueOf(z),ts);
                                    l1.setVisibility(View.GONE);
                                    fetchsn();
                                    loadserialdropdown("");
                                    fsn.setText("");
                                    tsn.setText("");
                                    countms.setText("");
                                }
                                catch (Exception e)
                                {
                                    Log.e("ERROR",""+e);
                                    // Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_SHORT).show();
                                    showErrorDialog("Invalid Serial No");
                                }
                           /* }
                            else
                            {
                                showErrorDialog("Serial Number Starting Character Should Be Same");
                            }*/
                            // do something..
                        }
                        else
                        {
                            showErrorDialog("Serial No Length Should Be 8");
                        }
                    }
                    catch (Exception e)
                    {
                        showErrorDialog("Invalid Serial No");
                    }
                }
            }
        });
        editTextSerialNo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = (String) adapterView.getItemAtPosition(i);
                String[] serilno = selected.split("-");
                editTextSerialNo.setText(serilno[0]);
                String slno = editTextSerialNo.getText().toString().trim();
                int selectemitem = serialnosonly.indexOf(slno);
                if(selectemitem >= 0){
                    fetch_qty();
                }else{
                    showErrorDialog("Selected Serial no is invalid");
                    editTextSerialNo.setText("");
                }
            }
        });
        editTextSerialNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String slno = editTextSerialNo.getText().toString().trim();
                int selectemitem = serialnosonly.indexOf(slno);
                if(selectemitem >= 0){
                    fetch_qty();
                }
            }
        });

        availqty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
              /*  if(!editable.toString().equalsIgnoreCase("")){
                    reqqty.setEnabled(true);
                }else {
                    reqqty.setEnabled(false);
                }*/
            }
        });
        reqqty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().startsWith(".")){
                    editable.clear();
                }else if(editable.length() > 0 && !editable.toString().equalsIgnoreCase("")){
                    try{
                        double a = Double.parseDouble(availqty.getText().toString().trim());
                        double b = Double.parseDouble(editable.toString().trim());
                        if(b > a){
                            showErrorDialog("Sale Qty Cannot be Higher than Avilable Qty");
                            editable.clear();
                        }
                    }catch (Exception er){
                        Log.e("error",Log.getStackTraceString(er));
                    }
                }
            }
        });
        but_upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(editTextSerialNo.getText().toString().trim().equalsIgnoreCase("")){
                        showErrorDialog("Serial No Cannot be Empty");
                    }else if(reqqty.getText().toString().trim().equalsIgnoreCase("")){
                        showErrorDialog("Sale Qty Cannot be Empty");
                    }else if(Double.parseDouble(reqqty.getText().toString().trim()) <= 0){
                        showErrorDialog("Sale Qty Cannot be Zero");
                    }
                    else{
                        String sl = editTextSerialNo.getText().toString().trim();
                        double avlqty = Double.parseDouble(availqty.getText().toString().trim());
                        double reqty = Double.parseDouble(reqqty.getText().toString().trim());
                        double res = avlqty - reqty;
                        if(!oldqty.equalsIgnoreCase("")){
                            Log.e("updqry","update sno set v6 = '"+reqty+"' " +
                                    " where v1 = '"+sl+"' ");
                            dbs.execSQL("update sno set v6 = '"+reqty+"' " +
                                    " where v1 = '"+sl+"' and v4 = '"+pc+"' ");
                            updatesnowitqty(sl, String.valueOf(res),"=");
                            editTextSerialNo.setEnabled(true);
                            editTextSerialNo.setText("");
                            reqqty.setText("");
                            availqty.setText("");
                            buttonAdd.setVisibility(View.VISIBLE);
                            but_upd.setVisibility(View.GONE);
                            fetchsn();
                            loadserialdropdown("");
                            rb_ss.setChecked(false);
                        }
                    }
                }catch (Exception er){
                    Log.e("error",Log.getStackTraceString(er));
                }
            }
        });
        Cursor cur = null;
        try{
            cur = dbs.rawQuery("select * from ProductMasterAllProduct where PmapOutPawhsCode = '"+ pc +"' ",null);
            if(cur.getCount()>0){
                if(cur.moveToFirst()){
                    uom_lbl.setText("("+cur.getString(cur.getColumnIndexOrThrow("out_uomtype_code"))+")");
                }
            }
        }catch (Exception er){
            Log.e("error",Log.getStackTraceString(er));
        }finally {
            cur.close();
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
                            for (int i = 0; i < (y+1); i++) {
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
                    }else{
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
        int id=view.getId();
        switch (id){
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
            case R.id.but_cancel:
 //                finish();
                if(isNetworkAvailable()) {

                    checkserialno();
                }
                else
                {
                    finish();
                }
                break;
            default:
                break;
        }
    }

    private void addSerialNo() {
        try{
            String serialNo= editTextSerialNo.getText().toString();
            Log.e("TST",""+ts);
       /* if(serialNo.isEmpty()){
            showErrorDialog("Empty Serial No");
        }
        else */
            if(editTextSerialNo.getText().toString().equalsIgnoreCase("")){
                showErrorDialog("Empty Sl No");
            }
            else if(reqqty.getText().toString().equalsIgnoreCase("")){
                showErrorDialog("Empty Sale Qty");
            }else if(Double.parseDouble(reqqty.getText().toString().trim()) <= 0){
                showErrorDialog("Sale Qty Cannot be Zero");
            }
            else {

                if(sn.contains(serialNo))
                {
                    showErrorDialog("Serial No Already Exists");
                }
                else
                {
                    String getserial ="";
                    if(serialNo.equalsIgnoreCase("")){
                        getSerialno(reqqty.getText().toString());
                    }else {
                        getserial = serialNo;
                        //db.insertsno(serialNo + "_" +reqqty.getText().toString() ,"1",ts);
                        db.insertsno(getserial ,"1",ts,pc,"0" ,reqqty.getText().toString());
                        //db.insertsnowithqty(serialNo,reqqty.getText().toString(),"0");
                        //updatesnowitqty(serialNo,reqqty.getText().toString(),"-");
                        updatesnowitqty(getserial,reqqty.getText().toString(),"-");
                        editTextSerialNo.setText("");
                        availqty.setText("");
                        fetchsn();
                        //sn.add(serialNo);
                        sn.add(getserial);
                        fetch_qty();
                        linear_single.setVisibility(View.VISIBLE);
                        linear_singlebutton.setVisibility(View.VISIBLE);
                    }

                }
            }
        }catch (Exception e){
            Log.e("error",Log.getStackTraceString(e));
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

        }



    }
    protected void processScannedData(String scanData){
        Log.e("SCAN DATA",scanData);
        XmlPullParserFactory pullParserFactory;
        try {
            // init the parserfactory
            pullParserFactory = XmlPullParserFactory.newInstance();
            // get the parser
            XmlPullParser parser = pullParserFactory.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            String fstring2 = null;
            try {

                Log.e("Scan Data",""+scanData);
                String[] data = scanData.split("-");
                String qry = "Select * from snowithqty where v1 = '"+data[0]+"'";
                Cursor cr  = dbs.rawQuery(qry,null);
                try{

                    qty.clear();
                    if(cr.getCount() > 0){
                        if(cr.moveToFirst()){
                            if(rb_ms.isChecked()){
                                fsn.setText(scanData);
                            }else {
                                editTextSerialNo.setText(scanData);
                            }
                        }
                    }
                    else{
                        showErrorDialog("Invalid Serial No");
                    }

                }catch (Exception er){
                    Log.e("error",Log.getStackTraceString(er));
                }finally {
                    cr.close();
                }


            }
            catch(Exception e)
            {

            }






            parser.setInput(new StringReader(scanData));




            // display the data on screen
            //  displayScannedData();
        } catch (XmlPullParserException e) {

            Log.e("AAdhar",""+e);
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
            View view = mInflater.inflate(R.layout.serial_list_screen3, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            final Pojosno pojofar = mData.get(position);
            try{
              //  String dtl[] = pojofar.getSn().split("_");
                holder.name.setText(pojofar.getSn());
                holder.txt_qty.setText(pojofar.getQty());
                if(pojofar.getSn().substring(0,2).equalsIgnoreCase("LB")){
                    holder.llist.setVisibility(View.GONE);
                }
                else
                {
                    holder.llist.setVisibility(View.VISIBLE);
                }
            }catch (Exception er){
                Log.e("error",Log.getStackTraceString(er));
            }
            //holder.name.setText(pojofar.getSn());


            if(pojofar.getQp().equalsIgnoreCase("0"))
            {
                holder.llist.setText("Add QP");
                holder.llist.setBackgroundResource(R.drawable.rbutton3);

            }
            else
            {
                holder.llist.setBackgroundResource(R.drawable.rbutton2);
                holder.llist.setText("Add/View QP");
            }


            holder.img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    dbs.delete("sno", "id" + "=" + pojofar.getId(), null);
                    dbs.execSQL("update sno set v5 = '2' where id = '"+pojofar.getId()+"' ");
                    dbs.delete("qpar", "v1" + "=" + pojofar.getId(), null);
                    try{
                   //     String  snn[] = pojofar.getSn().split("_");
                        sn.remove(pojofar.getSn());
                        updatesnowitqty(pojofar.getSn(), pojofar.getQty(), "+");
                    }catch (Exception er){
                        Log.e("error",Log.getStackTraceString(er));
                    }
                    editTextSerialNo.setText("");
                    availqty.setText("");
                    fetchsn();
                    loadserialdropdown("");
                }
            });

            holder.llist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), Qpar.class);
                    i.putExtra("SID",pojofar.getId());
                    i.putExtra("PC",pc);
                    startActivity(i);
                }
            });
            holder.llist1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 try{
                     rb_ss.setChecked(true);
                     editTextSerialNo.setText(holder.name.getText().toString());
                     String qty1 = serialnumberchk(holder.name.getText().toString());
                     if(qty1.equalsIgnoreCase("")){
                         qty1 = "0";
                     }
                     double ans = Double.parseDouble(qty1) + Double.parseDouble(holder.txt_qty.getText().toString());
                     availqty.setText(""+ans);
                     reqqty.setText(holder.txt_qty.getText().toString());
                     editTextSerialNo.setEnabled(false);
                     buttonAdd.setVisibility(View.GONE);
                     but_upd.setVisibility(View.VISIBLE);
                     oldqty = holder.txt_qty.getText().toString();
                 }catch (Exception er){
                     Log.e("error",Log.getStackTraceString(er));
                 }
                }
            });
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
            Button llist,llist1;
            ImageView img_delete;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                String lbval  = "";
                name = itemView.findViewById(R.id.txt_serialNo);
                llist = itemView.findViewById(R.id.llist);
                img_delete = itemView.findViewById(R.id.img_delete);
                txt_qty = itemView.findViewById(R.id.txt_qty);
                llist1 = itemView.findViewById(R.id.llist1);
                /*for(int i = 0; i < arraylist.size(); i++){
                   try{
                       if(arraylist.get(i).getSn().substring(0,2).equalsIgnoreCase("LB")){
                           lbval = arraylist.get(i).getSn().substring(0,2);
                       }
                   }catch (Exception er){
                       Log.e("error",Log.getStackTraceString(er));
                   }
                }
                if(lbval.equalsIgnoreCase("LB")){
                    llist.setVisibility(View.GONE);
                }*/
            }

        }
    }
    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        fetchsn();
        loadserialdropdown("");
    }
    public  void fetchsn()
    {
        sn.clear();
      /*  Cursor cursor = dbs.query("sno", new String[]{"id","v1","v2","v3"
                }, "v3" + "=? COLLATE NOCASE",
                new String[]{ts}, null, null, null, null);*/
        String selectQuery = "Select * from sno where v3 = ? COLLATE NOCASE and v4 = ? COLLATE NOCASE and v5 != ? ";
        Cursor cursor = dbs.rawQuery(selectQuery, new String[]{ts,pc,"2"});
        slist.clear();

        if(cursor.getCount()==0)
        {
            recyclerViewSerial.setAdapter(adapterf);
            adapterf.notifyDataSetChanged();
        }
        double count = 0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pojosno pojosno = new Pojosno();

                pojosno.setId(cursor.getString(cursor.getColumnIndexOrThrow("id")));
                pojosno.setSn(cursor.getString(cursor.getColumnIndexOrThrow("v1")));
                pojosno.setQty(cursor.getString(cursor.getColumnIndexOrThrow("v6")));
                try{
                    sn.add(cursor.getString(cursor.getColumnIndexOrThrow("v1")));
                    count += Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("v6")));
                }catch (Exception er){
                    Log.e("error",er.toString());
                }
                String selectQuery2 = "SELECT  * FROM qpar WHERE v1 ="+cursor.getString(cursor.getColumnIndexOrThrow("id"));
                Cursor cursor2 = dbs.rawQuery(selectQuery2, null);

                if(cursor2.getCount()==0)
                {
                    pojosno.setQp("0");
                }
                else
                {
                    pojosno.setQp("1");
                }
                /*Cursor cr = dbs.rawQuery("Select * from snowithqty where v1 = '"+cursor.getString(1)+"' and v3 = '"+ts+"' ",null);
                try{
                    if(cr.getCount() > 0){
                        if(cr.moveToFirst()){
                            pojosno.setQty(cr.getString(cr.getColumnIndexOrThrow("v2")));
                        }
                    }
                }catch (Exception er){
                    Log.e("error",Log.getStackTraceString(er));
                }finally {
                    cr.close();
                }*/
                slist.add(pojosno);
                // array2.add(cursor.getString(11));
                // Log.e("VAL",""+cursor.getString(11));
                adapterf = new MyRecyclerViewAdapterfarmer(mContext, slist);
                recyclerViewSerial.setAdapter(adapterf);

                adapterf.notifyDataSetChanged();
            } while (cursor.moveToNext());

        }
        ttl_qty.setText(""+count);
        ttl_saleqty.setText("Total Sale Qty: "+count);
    }
    public  void maddnew()
    {
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
/*        Cursor cursor = dbs.rawQuery("SELECT * FROM sno WHERE v3 = '" + ts + "'", null);
        if (cursor.getCount() > 0) {
            pdialog.setCanceledOnTouchOutside(false);
            pdialog.setTitle("Uploading Please Wait.......");
            pdialog.show();
            JSONObject jsonObject = new JSONObject();
            try {

                JSONArray jsonArray = null;
                jsonObject.put("Instance", Pojokyc.instance);
                jsonObject.put("sale_no", Pojokyc.saleno);
                jsonArray = new JSONArray();

                if (cursor.moveToFirst()) {
                    do {


                        JSONObject jsonObject2 = new JSONObject();
                        try{
                            String serial [] = cursor.getString(cursor.getColumnIndexOrThrow("v1")).split("/");
                            jsonObject2.put("serial_no", serial[0]);
                        }catch (Exception er){
                            Log.e("error",er.toString());
                        }
                        jsonObject2.put("err_serial_no", "");
                        jsonArray.put(jsonObject2);


                    } while (cursor.moveToNext());
                    jsonObject.put("List", jsonArray);

                }

                Log.e("SERIALNO", "" + jsonObject);
            } catch (Exception e) {

            }
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, ApiUtils.TEST_URL_API + "New_PAWHS_SaleEntry/Pawhs_SaleEntry_checkdub", jsonObject,
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
       // finish();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void fetch_qty(){
        reqqty.setText("");
//        availqty.setText("");
        int val = 0;
        String qry = "select * from snowithqty where v1 = '"+editTextSerialNo.getText().toString().trim()+"' " +
                " and v6 = '"+pc+"' and v7 != '2' ";
        Log.e("qry",qry);
        Cursor cr  = dbs.rawQuery(qry,null);
        try{
            if(cr.getCount()>0){
                if(cr.moveToFirst()){
                    availqty.setText(""+cr.getString(cr.getColumnIndexOrThrow("v2")));
                    reqqty.setText(""+cr.getString(cr.getColumnIndexOrThrow("v2")));
                  //  val = Integer.parseInt(cr.getString(cr.getColumnIndexOrThrow("v2")));
                }
            }else {
                //availqty.setText("");
                loadserialdropdown("");
            }
        }catch (Exception er){
            Log.e("error",Log.getStackTraceString(er));
        }finally {
            cr.close();
        }
    }
    public void updatesnowitqty(String sno,String qty,String flag){
        if(flag.equalsIgnoreCase("-")){
            dbs.execSQL("update snowithqty set v2 = v2 - "+Double.parseDouble(qty)+" where v1 = '"+sno+"' and v6 = '"+pc+"' ");
        }else if(flag.equalsIgnoreCase("+")){
            dbs.execSQL("update snowithqty set v2 = v2 + "+Double.parseDouble(qty)+" where v1 = '"+sno+"' and v6 = '"+pc+"' ");
        }else if(flag.equalsIgnoreCase("=")){
            dbs.execSQL("update snowithqty set v2 = '"+qty+"' where v1 = '"+sno+"' and v6 = '"+pc+"' ");
        }
    }
    public String serialnumberchk(String sno){
        String x = "";
        String qry = "select * from snowithqty where v1 = '"+sno+"' and v6  = '"+pc+"'  and v7 != '2' ";
        Log.e("slqry",qry);
        Cursor cr = dbs.rawQuery(qry,null);
        try{
            if(cr.getCount() > 0){
                if(cr.moveToFirst()){
                    x = cr.getString(cr.getColumnIndexOrThrow("v2"));
                }
            }
        }catch (Exception er){
            Log.e("error",Log.getStackTraceString(er));
        }finally {
            cr.close();
        }
        Log.e("serl",""+x);
        return x;
    }
    public void loadserialdropdown(String lbflag){
        double avlqty= 0;
        lbflag = "N";   // the line added for dropdown only serialnumbers not loosebags by thianes 29/06/2022
        String qry = "Select * from snowithqty where v6 = '"+pc+"'  and v7 != '2' and v2 <> '0.0' ORDER by v4 DESC";
//        if(!lbflag.equalsIgnoreCase("")){
//            qry += " and v4 = '"+lbflag+"' ";
//        }
        Cursor cr  = dbs.rawQuery(qry,null);
        try{
            serialnos.clear();
            serialnosonly.clear();
            qty.clear();
            if(cr.getCount() > 0){
                if(cr.moveToFirst()){
                    do{
                        serialnos.add(cr.getString(cr.getColumnIndexOrThrow("v1"))+"-(Qty : "+cr.getString(cr.getColumnIndexOrThrow("v2"))+")");
                        serialnosonly.add(cr.getString(cr.getColumnIndexOrThrow("v1")));
                        qty.add(cr.getString(cr.getColumnIndexOrThrow("v2")));
                        avlqty += Double.parseDouble(cr.getString(cr.getColumnIndexOrThrow("v2")));
                    }while (cr.moveToNext());
                }
            }
          /*  else {
                showErrorDialog("There is no Serial number is Available for Product code: "+pc+"!");
            }*/
        }catch (Exception er){
            Log.e("error",Log.getStackTraceString(er));
        }finally {
            cr.close();
        }
       // availqty.setText(""+avlqty);
        totalavailablequantity.setText("Total Available Quantity :"+avlqty);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (SnoActivity.this, R.layout.spinnertext3, serialnos);
        editTextSerialNo.setAdapter(adapter);
        editTextSerialNo.setThreshold(0);
      /*  if(adapter.getCount() > 0 && !lbflag.equalsIgnoreCase("")){
            editTextSerialNo.setListSelection(0);
            editTextSerialNo.setText(adapter.getItem(0),false);
        }*/
    }
    public void getSerialno(String qty){
        Cursor cr = dbs.rawQuery("select * from snowithqty where v2 = '"+qty+"' and v6 = '"+pc+"'  and v7 != '2' ",null);
        try{
            if(cr.getCount() > 0){
                if(cr.moveToFirst()){

                        if(qty.equalsIgnoreCase(cr.getString(cr.getColumnIndexOrThrow("v2")))){
                            //db.insertsno(serialNo + "_" +reqqty.getText().toString() ,"1",ts);
                            db.insertsno(cr.getString(cr.getColumnIndexOrThrow("v1")),"1",ts,pc,"0",reqqty.getText().toString() );
                            //db.insertsnowithqty(serialNo,reqqty.getText().toString(),"0");
                            //updatesnowitqty(serialNo,reqqty.getText().toString(),"-");
                            updatesnowitqty(cr.getString(cr.getColumnIndexOrThrow("v1")),reqqty.getText().toString(),"-");
                            editTextSerialNo.setText("");
                            availqty.setText("");
                            fetchsn();
                            //sn.add(serialNo);
                            sn.add(cr.getString(cr.getColumnIndexOrThrow("v1")));
                            fetch_qty();
                        }

                }
            }
            else
            {
                Cursor cr1 = dbs.rawQuery("SELECT * from snowithqty where v6 = '"+pc+"'  and v7 != '2' " +
                        " ORDER by CAST(v2 as decimal) desc ",null);
                if(cr1.getCount() > 0){
                    if(cr1.moveToFirst()){

                        double a = Double.parseDouble(cr1.getString(cr1.getColumnIndexOrThrow("v2")));
                        double result =  (Double.parseDouble(qty)) - a;
                        Log.e("resu",""+result);
                        if(result<0)
                        {
                            db.insertsno(cr1.getString(cr1.getColumnIndexOrThrow("v1")),"1",ts,pc,"0", qty );
                            //db.insertsnowithqty(serialNo,reqqty.getText().toString(),"0");
                            //updatesnowitqty(serialNo,reqqty.getText().toString(),"-");
                            updatesnowitqty(cr1.getString(cr1.getColumnIndexOrThrow("v1")),qty,"-");
                            editTextSerialNo.setText("");
                            availqty.setText("");
                            fetchsn();
                            //sn.add(serialNo);
                            sn.add(cr1.getString(cr1.getColumnIndexOrThrow("v1")));
                            fetch_qty();
                        }
                        else
                        {
                            db.insertsno(cr1.getString(cr1.getColumnIndexOrThrow("v1")),"1",ts,pc,"0" ,cr1.getString(cr1.getColumnIndexOrThrow("v2")) );
                            updatesnowitqty(cr1.getString(cr1.getColumnIndexOrThrow("v1")),cr1.getString(cr1.getColumnIndexOrThrow("v2")),"-");
                            editTextSerialNo.setText("");
                            availqty.setText("");
                            fetchsn();
                            //sn.add(serialNo);
                            sn.add(cr1.getString(cr1.getColumnIndexOrThrow("v1")));
                            fetch_qty();
                            getSerialno(""+result);
                        }

                    }
                }
            }
        }catch (Exception er){
            Log.e("error",Log.getStackTraceString(er));
        }finally {
            cr.close();
        }
       try{

       }catch (Exception er){
           Log.e("error",Log.getStackTraceString(er));
       }
    }
}
