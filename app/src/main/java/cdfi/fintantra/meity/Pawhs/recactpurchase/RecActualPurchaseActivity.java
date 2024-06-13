package cdfi.fintantra.meity.Pawhs.recactpurchase;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.google.zxing.qrcode.QRCodeWriter;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import cdfi.fintantra.meity.DBHelper;
import cdfi.fintantra.meity.ExceptionHandler;
import cdfi.fintantra.meity.HttpsTrustManager;
import cdfi.fintantra.meity.Pawhs.FDR;
import cdfi.fintantra.meity.Pawhs.PAWHSApplication;

import cdfi.fintantra.meity.Pawhs.SaleEntry;
import cdfi.fintantra.meity.Pawhs.model.PostQtyDetailDao;
import cdfi.fintantra.meity.Pojobuyer;
import cdfi.fintantra.meity.Pawhs.Purchaseentrylist;
import cdfi.fintantra.meity.Pawhs.Qparpe;
import cdfi.fintantra.meity.Pojokyc;
import cdfi.fintantra.meity.R;
import cdfi.fintantra.meity.Pawhs.SQLiteDataBaseHandler;
import cdfi.fintantra.meity.Pawhs.SnoActivitype;
import cdfi.fintantra.meity.VolleySingleton;
import cdfi.fintantra.meity.Pawhs.api.ApiService;
import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import cdfi.fintantra.meity.Pawhs.formermodel.FormerDao;
import cdfi.fintantra.meity.Pawhs.model.ActProListDao;
import cdfi.fintantra.meity.Pawhs.model.OtherDetailDao;
import cdfi.fintantra.meity.Pawhs.model.PmListDao;
import cdfi.fintantra.meity.Pawhs.model.PostOtherDetailDao;
import cdfi.fintantra.meity.Pawhs.model.PostSiNoDetailDao;
import cdfi.fintantra.meity.Pawhs.model.QtyDetailDao;
import cdfi.fintantra.meity.Pawhs.model.SiNoDetailDao;
import cdfi.fintantra.meity.Pawhs.model.TempOtherDetailDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.ActualListDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.EstActAppContextDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.EstimateActualApprovedListDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.EstimateListDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.LotnoDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.NewEstimateProcurementSingleDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.SingleContextDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.SingleLotnoDao;
import cdfi.fintantra.meity.Pawhs.model.singleactualprocmodel.SingleActProcContextDao;
import cdfi.fintantra.meity.Pawhs.model.singleactualprocmodel.SingleActualHeaderDao;
import cdfi.fintantra.meity.Pawhs.model.singleactualprocmodel.SingleActualProcurementDao;
import cdfi.fintantra.meity.Pawhs.utils.DecimalDigitsInputFilter;
import cdfi.fintantra.meity.Pawhs.utils.MyConstants;
import cdfi.fintantra.meity.Pawhs.utils.Utility;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static cdfi.fintantra.meity.Pawhs.api.ApiUtils.EST_ACT_VALUE;
import static cdfi.fintantra.meity.Pawhs.api.ApiUtils.SL_NO_VALUE;


public class RecActualPurchaseActivity extends AppCompatActivity implements View.OnClickListener {
    private PAWHSApplication pawhsApplication;
    private ApiService mAPIService;
    private String userName;
    String lnom="",peoid="0";
    private List<String> sitem,cat,var;
    private List<String> sdate;
    private Context mContext;
    String updatestatus;
    double cnt;
    String status,modeflag;
    ImageView qrgen;
    TextView tfc;
    JSONObject jsonParam;
    EditText lrpname,lrpno;
    RelativeLayout rlb;
    String bycode,byname;
    int estatus = 1;
    Spinner spinner,spinner2,spinner3;
    String[] vtype = { "", "Truck","Tractor"};
    String frm;
    String[] Des = { "", "Direct to buyer","Warehouse"};
    String[] vstatus = { "", "Milled &amp; Loose","Cob","Milled &amp; Packing"};
    QRCodeWriter writer = new QRCodeWriter();
    ImageView capturebill;
    String ui;
    String ts;
    MyRecyclerViewAdapterb adapterb;
    private List<FormerDao> formerDaoList;
    String imageFilePath,imageFilePath2;
    Bitmap thePic,thePic2;
    int cstatus=0;
    String buycode="",buyname="";
    List<Pojobuyer> pojobuyerList;
    ArrayList<String> arrayn;
    EditText qcp,vno;
    private List<String> farmerCodeList;
    private List<String> farmerNameList;
    int pstatus = 0;

    String code;
    Uri picUri;
    String encodedImage = "0",encodedImage2 = "0";
    ByteArrayOutputStream byteArrayOutputStream,byteArrayOutputStream2;
    final int CAMERA_CAPTURE = 200;
    private static final int CAMERA_REQUEST = 1888;
    LinearLayout linear_advance;
    private TextView textViewTitle,txt_uom,txt_var,srcfarmer,srcfarmerc,srccrop;
    private RecyclerView recyclerViewQuality;
    private RecyclerView recyclerViewOtherCost;
    private String orgnId, locnId, userId, localeId, orgnCode;
    private SQLiteDataBaseHandler db;
    AutoCompleteTextView textViewQty,textViewQty2;
    private RelativeLayout progressLayout;
    private PostOtherDetailDao postOtherDetailDao;
    private List<PostOtherDetailDao> postOtherDetailDaoList;
    private List<String> lotNoList;
    private List<LotnoDao> lotnoDaoList;
    private List<EstimateListDao> estimateListDaoList;
    // private QualityListParameterAdapteraap qualityListParameterAdapter;
    //private OtherCostAdapter2 otherCostAdapter2;
    private List<SingleLotnoDao> singleLotnoDaoList;
    private List<PostOtherDetailDao> postFilterOtherDetailDaoList=new ArrayList<>();
    private List<String> productItemList;
    private List<PmListDao> pmListDaoList;

    List<String> sea = new ArrayList<String>();
    private Dialog dialog,dialog2,dialog3;

    private TextView txt_state,txt_village,txt_panchayat,txt_district,txt_mobileno,textViewLotNo, textViewFarmerCode, textViewFarmerName, textViewMember, textViewItemName, textViewValue, textViewPickUpdate;

    private TextView txt_year,textViewQualityPara, textViewSINo, textViewOtherCost, textViewTotalCost,byrname;
    EditText textViewNoofBag;

    private Button buttonSave, buttonView, buttonCancel,btn_view,btn_add;

    private ImageView imageViewEditItem;
    ImageView viewqp,viewoc;
    private TextView textViewincrease, textViewDecrease,txtquantity,txt_lbl_ttlamt,txt_lbl_purchaserate;
    private LinearLayout linearLotno, linearOthercost, linearQualityPara, linearSino;
    double numtest = 0;
    double num1 = 0;
    double num2 = 0;
    RelativeLayout rpt;
    Spinner spinnerpt,spinnerb,spinnerss;
    LinearLayout lcq,lbno;
    EditText cqno,bno;

    private EditText edt_price, edt_remarks, edt_advance;
    ArrayList vtypes = new ArrayList();
    ArrayList vtypesc = new ArrayList();
    ArrayList dstn = new ArrayList();
    ArrayList dstnc = new ArrayList();
    ArrayList bys = new ArrayList();
    private String lotno, farmerCode, farmerName, memberType, itemName, itemCode, quantity, price, calculatevalue, estimateDate;

    private List<PostSiNoDetailDao> postSiNoDetailDaoList;
    private List<PostQtyDetailDao> postQtyDetailDaoList;
    private List<PostQtyDetailDao> postQtyDetailQtyNameDaoList;

    private List<TempOtherDetailDao> tempOtherDetailDaoList;
    ProgressDialog pdialog;
    boolean isNetwork;
    private String edtActValue="";
    private ActProListDao actProListDaoEdit;
    private SingleActualHeaderDao singleActualHeaderDao;
    private List<QtyDetailDao> qtyDetailDaoList;
    private List<OtherDetailDao> otherDetailDaoList;
    private List<SiNoDetailDao> siNoDetailDaoList;
    private List<ActualListDao> actualListDaoList;
    private int otherTotalCost=0;
    private double advanceValue=0;
    String tableid;
    String peid,oldlbqty = "0",lbvals = "";;
    RadioGroup rg_bags;
    RadioButton rb_by,rb_bn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.rec_actual_purchase_screen);
        getSupportActionBar().hide();
        mContext = this;
        pawhsApplication = PAWHSApplication.getGetInstance();
        userName = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.IN_USER_NAME);

        orgnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_ID);
        locnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCN_ID);
        userId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.USER_ID);
        localeId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCALE_ID);
        orgnCode = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_CODE);
        pdialog = new ProgressDialog(this);
        db = new SQLiteDataBaseHandler(mContext);
        MyConstants.productcode = "";

        initView();

    }

    private void initView() {

        isNetwork = Utility.checkConnectivity(getApplicationContext());

        mAPIService = ApiUtils.getAPIService();
        textViewTitle = (TextView) findViewById(R.id.txt_title);

        Intent i = getIntent();
        frm = i.getStringExtra("Frm");
        peid = i.getStringExtra("PEID");

        if(frm.equalsIgnoreCase("Approved"))
        {
            textViewTitle.setText("Welcome " + userName + "\n" + "To Advance Payments");

        }
        else
        {
            textViewTitle.setText("Welcome " + userName + "\n" + "Purchase Entry");
        }





        progressLayout = (RelativeLayout) findViewById(R.id.progressLayout);
        rlb = (RelativeLayout) findViewById(R.id.rlb);

        txt_village = (TextView) findViewById(R.id.txt_village);
        txt_panchayat = (TextView) findViewById(R.id.txt_panchayat);
        txt_district = (TextView) findViewById(R.id.txt_district);
        txt_mobileno = (TextView) findViewById(R.id.txt_mobileno);
        txt_state = (TextView) findViewById(R.id.txt_state);
        txt_state.setText(""+Pojokyc.paloc);
        txt_year = (TextView) findViewById(R.id.txt_year);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        txt_year.setText(""+year);
        textViewLotNo = (TextView) findViewById(R.id.txt_lot_no);
        textViewFarmerCode = (TextView) findViewById(R.id.txt_act_formercode);
        textViewFarmerName = (TextView) findViewById(R.id.txt_act_farmerName);
        textViewMember = (TextView) findViewById(R.id.txt_act_MemberType);
        textViewItemName = (TextView) findViewById(R.id.txt_act_Item);
        txtquantity = (TextView) findViewById(R.id.txtquantity );
        txt_lbl_ttlamt = (TextView) findViewById(R.id.txt_lbl_ttlamt);
        txt_lbl_purchaserate = (TextView) findViewById(R.id.txt_lbl_purchaserate);
        textViewQty=(AutoCompleteTextView) findViewById(R.id.txt_act_qty);
        textViewQty2=(AutoCompleteTextView) findViewById(R.id.txt_act_qty2);
        textViewValue = (TextView) findViewById(R.id.edt_act_value);
        srcfarmer = (TextView) findViewById(R.id.srcfarmer);
        srcfarmerc = (TextView) findViewById(R.id.srcfarmerc);
        srccrop = (TextView) findViewById(R.id.srccrop);
        tfc = (TextView) findViewById(R.id.tfc);
        //String text = "<font color=#00000>First Color</font> <font color=#ffcc00>Second Color</font>";
        //tfc.setText(Html.fromHtml(text));
        //tfc.setText("Farmer Code"+" ");
        byrname = (TextView) findViewById(R.id.byrname);
        arrayn = new ArrayList<>();
        pojobuyerList = new ArrayList<>();
        textViewFarmerCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFarmerCodeDialog();
            }
        });
        srcfarmerc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFarmerCodeDialog();
            }
        });

        textViewFarmerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFarmerNameDialog();
            }
        });
        srcfarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFarmerNameDialog();
            }
        });
        qcp = (EditText)findViewById(R.id.qcp);
        vno = (EditText)findViewById(R.id.vno);
        lrpname = (EditText)findViewById(R.id.lrpname);
        lrpno = (EditText)findViewById(R.id.lrpno);

        rpt = (RelativeLayout)findViewById(R.id.rpt);
        spinnerpt = (Spinner)findViewById(R.id.spinnerpt);
        spinnerb = (Spinner)findViewById(R.id.spinnerb);
        spinnerss = (Spinner)findViewById(R.id.spinnerseason);
        lcq = (LinearLayout)findViewById(R.id.lcq);
        lbno = (LinearLayout)findViewById(R.id.lbno);
        cqno = (EditText)findViewById(R.id.cqno);
        bno = (EditText)findViewById(R.id.bno);
        btn_view = (Button) findViewById(R.id.btn_view);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setEnabled(false);
        rg_bags = (RadioGroup)findViewById(R.id.rg_bags);
        rb_by = (RadioButton) findViewById(R.id.rb_by);
        rb_bn = (RadioButton) findViewById(R.id.rb_bn);

        textViewQty2.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        textViewQty2.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(18,2)});
       // sea.add("");
        sea.add("Rabi");
        ArrayAdapter<String> dataAdaptersea = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sea);

        // Drop down layout style - list view with radio button
        //dataAdaptersea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdaptersea.setDropDownViewResource(R.layout.spinnertext3);

        // attaching data adapter to spinner
        spinnerss.setAdapter(dataAdaptersea);
        List<String> ptype = new ArrayList<String>();
        ptype.add("");
        ptype.add("Cash");
        ptype.add("Bank Account");
        ptype.add("Cheque");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinnertext, ptype);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinner_item2);

        // attaching data adapter to spinner
        spinnerpt.setAdapter(dataAdapter);

        spinnerpt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(position == 2)
                {
                    // do your stuff
                    lbno.setVisibility(View.VISIBLE);
                    lcq.setVisibility(View.GONE);
                    cstatus=1;
                    pstatus = 1;

                }
                else if(position == 3)
                {
                    lbno.setVisibility(View.GONE);
                    lcq.setVisibility(View.VISIBLE);
                    cstatus=2;
                    pstatus = 1;
                }
                else
                {
                    lbno.setVisibility(View.GONE);
                    lcq.setVisibility(View.GONE);
                    cstatus=0;
                    pstatus = 0;

                }

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        qrgen = (ImageView)findViewById(R.id.qrgen);

        spinner = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.spinner2);


        final SQLiteDatabase dbs = db.getWritableDatabase();

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
            final ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(mContext,
                    R.layout.spinnertext, vtypes);

            spinner.setAdapter(adapterlist2n);
        } //
        String selectQuery2 = "SELECT  * FROM dstn";

        Cursor cursor2 = dbs.rawQuery(selectQuery2, null);

        if(cursor2.moveToFirst())
        {
            dstnc.add("0");
            dstn.add("");
            do
            {

                dstnc.add(cursor2.getString(2));
                dstn.add(cursor2.getString(3));






                // total number of textviews to add


            }while(cursor2.moveToNext());
            final ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(mContext,
                    R.layout.spinnertext, dstn);

            spinner2.setAdapter(adapterlist2n);
        } //

        String selectQuery22 = "SELECT  * FROM byr";

        Cursor cursor22 = dbs.rawQuery(selectQuery22, null);

        if(cursor22.moveToFirst())
        {

            bys.add("");

            do
            {

                bys.add(cursor22.getString(2)+"-"+cursor22.getString(1));






                // total number of textviews to add


            }while(cursor22.moveToNext());
            final ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(mContext,
                    R.layout.spinnertext, bys);

            spinnerb.setAdapter(adapterlist2n);
        } //

        byrname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(RecActualPurchaseActivity.this);
                dialog.setContentView(R.layout.suppliersearch3);

                dialog.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                final RecyclerView recyclerView = dialog.findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(RecActualPurchaseActivity.this));
                adapterb = new MyRecyclerViewAdapterb(RecActualPurchaseActivity.this, pojobuyerList);
                dialog.getWindow().setLayout(width, height);
                arrayn.clear();
                final SQLiteDatabase dbs = db.getWritableDatabase();
                final AutoCompleteTextView elc = (AutoCompleteTextView)dialog.findViewById(R.id.elc);
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


                ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(RecActualPurchaseActivity.this,
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
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equalsIgnoreCase("Direct to Buyer"))
                {
                    // do your stuff
                    rlb.setVisibility(View.VISIBLE);


                }

                else
                {
                    rlb.setVisibility(View.GONE);


                }

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        capturebill = (ImageView)findViewById(R.id.capturebill);
        capturebill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {

                    picUri = FileProvider.getUriForFile(RecActualPurchaseActivity.this, getApplicationContext().getPackageName() + ".provider", createImageFile());
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                    takePictureIntent.putExtra("return-data", true);
                    startActivityForResult(takePictureIntent, CAMERA_CAPTURE);// convert path to Uri
                } catch (IOException e) {
                    e.printStackTrace();
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
                    if(s.toString().trim().isEmpty() || (Double.parseDouble(s.toString()) <= 0))
                    {
                    //    linearSino.setEnabled(true);
                        numtest = 0;
                    }
                    else if (Double.parseDouble(s.toString()) > 0)
                    {
                     //   linearSino.setEnabled(false);
                        numtest = Double.parseDouble(s.toString());
                        try {
                            if (!edt_price.getText().toString().isEmpty()) {
                                if (!edt_price.getText().toString().equalsIgnoreCase(".")) {
                                    try {
                                        num1 = Double.parseDouble(textViewQty2.getText().toString());
                                        num2 = Double.parseDouble(edt_price.getText().toString());
                                        double multipleValue = num1 * num2;
                                        double value = Double.parseDouble(new DecimalFormat("##.##").format(multipleValue));
                                        //  textViewValue.setText("" + multipleValue);
                                        //  textViewValue.setText("" + value);
                                        //   String firstNumberAsString = new DecimalFormat().format(value);
                                        textViewValue.setText("" + new DecimalFormat("##.##").format(value));
                                        textViewTotalCost.setText("" + new DecimalFormat("##.##").format(value));
                                        try {
                                            double x = Double.parseDouble(textViewOtherCost.getText().toString());
                                            double z = value-x;
                                            textViewTotalCost.setText("" + new DecimalFormat("##.##").format(z));
                                        }
                                        catch (Exception e)
                                        {

                                        }
                                    }
                                    catch(Exception e)
                                    {

                                    }
                                    if(!textViewTotalCost.getText().toString().isEmpty()){

                                        double x = Double.parseDouble(textViewOtherCost.getText().toString());
                                        double totalCost = Double.parseDouble(textViewValue.getText().toString())-x;
                                        textViewTotalCost.setText("" + new DecimalFormat("##.##").format(totalCost));
                                    }
                                }
                /*    num1= Double.parseDouble(textViewQty.getText().toString());
                    num2= Double.parseDouble(edt_price.getText().toString());
                    double multipleValue=num1*num2;
                    double value =Double.parseDouble(new DecimalFormat("##.##").format(multipleValue));
                    //  textViewValue.setText("" + multipleValue);
                    textViewValue.setText("" + value);*/
                            } else {
                                textViewValue.setText("");
                            }
                        }
                        catch (Exception e)
                        {

                        }

                    }
                }
                catch(Exception e)
                {

                }

                //  numtest = Double.parseDouble(s.toString());

                // TODO Auto-generated method stub
            }
        });
        textViewPickUpdate = (TextView) findViewById(R.id.txtview_date);

        imageViewEditItem = (ImageView) findViewById(R.id.img_edit);
        txt_uom = (TextView) findViewById(R.id.txt_uom);
        txt_var = (TextView) findViewById(R.id.txt_var);
        textViewQualityPara = (TextView) findViewById(R.id.txt_act_quality);
        textViewincrease = (TextView) findViewById(R.id.txt_increase);
        textViewDecrease = (TextView) findViewById(R.id.txt_decrease);

        buttonSave = (Button) findViewById(R.id.but_save);
        buttonView = (Button) findViewById(R.id.but_view);
        buttonCancel = (Button) findViewById(R.id.but_cancel);
        if(frm.equalsIgnoreCase("Actual"))
        {
            buttonView.setVisibility(View.VISIBLE);
        }
        else
        {
            buttonView.setVisibility(View.GONE);
        }

        linearLotno = (LinearLayout) findViewById(R.id.linear_lotno);
        linearQualityPara = (LinearLayout) findViewById(R.id.linear_qualitypara);
        linearSino = (LinearLayout) findViewById(R.id.linear_sino);
        linearOthercost = (LinearLayout) findViewById(R.id.linear_othercost);
        linearQualityPara.setEnabled(true);
      //  linearSino.setEnabled(false);
        linearOthercost.setEnabled(false);


        textViewSINo = (TextView) findViewById(R.id.txt_act_sl);
        textViewNoofBag = (EditText) findViewById(R.id.txt_act_noofbag);
        textViewOtherCost = (TextView) findViewById(R.id.txt_act_othercost);
        textViewTotalCost = (TextView) findViewById(R.id.txt_act_totalcost);
        linear_advance=(LinearLayout)findViewById(R.id.linear_advance);


        viewqp=(ImageView)findViewById(R.id.viewqp);
        viewoc=(ImageView) findViewById(R.id.viewoc);

        edt_price = (EditText) findViewById(R.id.edt_act_price);
        edt_price.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(6, 2)});
        edt_remarks = (EditText) findViewById(R.id.edt_remarks);
        edt_advance = (EditText) findViewById(R.id.edt_advance);
        // edt_advance.setFilters( new InputFilter[]{ new InputFilterMinMax( "1" , "1000000" )}) ;
        edt_advance.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(6, 2)});

        lotnoDaoList = new ArrayList<>();
        estimateListDaoList = new ArrayList<>();
        singleLotnoDaoList = new ArrayList<>();
        pmListDaoList = new ArrayList<>();

        //Edit
        singleActualHeaderDao = new SingleActualHeaderDao();
        qtyDetailDaoList = new ArrayList<>();
        otherDetailDaoList = new ArrayList<>();
        siNoDetailDaoList = new ArrayList<>();
        actualListDaoList=new ArrayList<>();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, getResources()
                .getStringArray(R.array.qty));
        String selection;
        textViewQty.setAdapter(arrayAdapter);
        textViewQty.setCursorVisible(false);
        textViewQty.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textViewQty.showDropDown();
                String sqty = (String) parent.getItemAtPosition(position);
                numtest = Double.parseDouble(sqty);
                textViewQty2.setText(sqty);

            }
        });

        textViewQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                textViewQty.showDropDown();
            }
        });
        textViewQty.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
                // TODO Auto-generated method stub
                textViewQty.showDropDown();

                return false;
            }
        });
        if (isNetwork == true) {
            //getLotNoListDetails();
        } else {
            estimateListDaoList = db.getAllEstimateList();
        }


        postSiNoDetailDaoList = new ArrayList<>();
        postQtyDetailDaoList = new ArrayList<>();
        postQtyDetailQtyNameDaoList = new ArrayList<>();
        postOtherDetailDaoList = new ArrayList<>();
        tempOtherDetailDaoList = new ArrayList<>();

        textViewincrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numtest = numtest + 0.5;
                textViewQty2.setText("" + numtest);
                if (!edt_price.getText().toString().isEmpty()){
                    if(!edt_price.getText().toString().equalsIgnoreCase(".")) {
                        num1 = Double.parseDouble(textViewQty2.getText().toString());
                        num2 = Double.parseDouble(edt_price.getText().toString());
                        double multipleValue = num1 * num2;
                        double value = Double.parseDouble(new DecimalFormat("##.##").format(multipleValue));
                        //  textViewValue.setText("" + multipleValue);
                        //textViewValue.setText("" + value);
                        //  String firstNumberAsString = new DecimalFormat().format(value);
                        textViewValue.setText("" + new DecimalFormat("##.##").format(value));
                        textViewTotalCost.setText("" + new DecimalFormat("##.##").format(value));
                        try {
                            double x = Double.parseDouble(textViewOtherCost.getText().toString());
                            double z = value-x;
                            textViewTotalCost.setText("" + new DecimalFormat("##.##").format(z));




                        }
                        catch (Exception e)
                        {

                        }
                    }
                }else {
                    textViewValue.setText("");
                }
            }
        });

        textViewDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numtest < 0) {
                    numtest = 0;
                    textViewQty2.setText(numtest + "");
                }
                if (numtest > 0) {
                    numtest = numtest - 0.5;
                    textViewQty2.setText(numtest + "");
                    if (!edt_price.getText().toString().isEmpty()){
                        if(!edt_price.getText().toString().equalsIgnoreCase(".")) {
                            num1 = Double.parseDouble(textViewQty2.getText().toString());
                            num2 = Double.parseDouble(edt_price.getText().toString());
                            double multipleValue = num1 * num2;
                            double value = Double.parseDouble(new DecimalFormat("##.##").format(multipleValue));
                            //  textViewValue.setText("" + multipleValue);
                            // textViewValue.setText("" + value);
                            //   String firstNumberAsString = new DecimalFormat().format(value);
                            textViewValue.setText("" + new DecimalFormat("##.##").format(value));
                            textViewTotalCost.setText("" + new DecimalFormat("##.##").format(value));
                            try {
                                double x = Double.parseDouble(textViewOtherCost.getText().toString());
                                double z = value-x;
                                textViewTotalCost.setText("" + new DecimalFormat("##.##").format(z));




                            }
                            catch (Exception e)
                            {

                            }

                        }
                    }else {
                        textViewValue.setText("");
                    }
                }
            }
        });

        edt_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(textViewQty2.getText().toString().isEmpty())
                {

                }
                else
                {
                    try {
                        if (!edt_price.getText().toString().isEmpty()) {
                            if (!edt_price.getText().toString().equalsIgnoreCase(".")) {
                                try {


                                    num1 = Double.parseDouble(textViewQty2.getText().toString());
                                    num2 = Double.parseDouble(edt_price.getText().toString());
                                    double multipleValue = num1 * num2;
                                    double value = Double.parseDouble(new DecimalFormat("##.##").format(multipleValue));
                                    //  textViewValue.setText("" + multipleValue);
                                    //  textViewValue.setText("" + value);
                                    //   String firstNumberAsString = new DecimalFormat().format(value);
                                    textViewValue.setText("" + new DecimalFormat("##.##").format(value));
                                    textViewTotalCost.setText("" + new DecimalFormat("##.##").format(value));
                                    try {
                                        double x = Double.parseDouble(textViewOtherCost.getText().toString());
                                        double z = value-x;
                                        textViewTotalCost.setText("" + new DecimalFormat("##.##").format(z));




                                    }
                                    catch (Exception e)
                                    {

                                    }
                                }
                                catch(Exception e)
                                {

                                }
                                if(!textViewTotalCost.getText().toString().isEmpty()){

                                    double x = Double.parseDouble(textViewOtherCost.getText().toString());
                                    double totalCost = Double.parseDouble(textViewValue.getText().toString())-x;
                                    textViewTotalCost.setText("" + new DecimalFormat("##.##").format(totalCost));
                                }
                            }
                /*    num1= Double.parseDouble(textViewQty.getText().toString());
                    num2= Double.parseDouble(edt_price.getText().toString());
                    double multipleValue=num1*num2;
                    double value =Double.parseDouble(new DecimalFormat("##.##").format(multipleValue));
                    //  textViewValue.setText("" + multipleValue);
                    textViewValue.setText("" + value);*/
                        } else {
                            textViewValue.setText("");
                        }
                    }
                    catch (Exception e)
                    {

                    }

                }



            }
        });
        textViewQty2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //onclicklistener
        linearLotno.setOnClickListener(this);
        textViewPickUpdate.setOnClickListener(this);
        linearQualityPara.setOnClickListener(this);
        linearSino.setOnClickListener(this);
        imageViewEditItem.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
        linearOthercost.setOnClickListener(this);
        textViewItemName.setOnClickListener(this);



        viewqp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2 = new Dialog(mContext);
                dialog2.setContentView(R.layout.custom_aapq_dialog);
                //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog2.setTitle("Title...");
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                final RecyclerView recyclerView = dialog2.findViewById(R.id.recycle_quality);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                dialog2.getWindow().setLayout(width, height);

                recyclerViewQuality = (RecyclerView) dialog2.findViewById(R.id.recycle_quality);
                recyclerViewQuality.setLayoutManager(new LinearLayoutManager(mContext));
                if(postQtyDetailDaoList!=null && postQtyDetailDaoList.size()>0){
                    setAdapter();
                }








                ImageView dialogButton = (ImageView) dialog2.findViewById(R.id.cls);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //  callQualityParameterJsonDetails();
                        dialog2.dismiss();
                    }
                });

                dialog2.show();
            }
        });
        srccrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showItemSearchDialog();
            }
        });
        viewoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog3 = new Dialog(mContext);
                dialog3.setContentView(R.layout.custom_aapq_dialog);
                //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog3.setTitle("Title...");
                int width2 = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height2 = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                final RecyclerView recyclerView2 = dialog3.findViewById(R.id.recycle_quality);
                recyclerView2.setLayoutManager(new LinearLayoutManager(mContext));
                dialog3.getWindow().setLayout(width2, height2);

                recyclerViewOtherCost = (RecyclerView) dialog3.findViewById(R.id.recycle_quality);
                recyclerViewOtherCost.setLayoutManager(new LinearLayoutManager(mContext));
                postOtherDetailDao = new PostOtherDetailDao();
                tempOtherDetailDaoList = new ArrayList<>();

                postFilterOtherDetailDaoList = postOtherDetailDaoList;

                if(postFilterOtherDetailDaoList!=null && postFilterOtherDetailDaoList.size()>0){

                    for(int i=0;i<postFilterOtherDetailDaoList.size();i++){
                        if(postFilterOtherDetailDaoList.get(i).getIn_packaging_cost()>0) {
                            tempOtherDetailDaoList.add(new TempOtherDetailDao("Packing Cost", String.valueOf(postFilterOtherDetailDaoList.get(i).getIn_packaging_cost())));
                        }

                        if(postFilterOtherDetailDaoList.get(i).getIn_transporting_cost()>0) {
                            tempOtherDetailDaoList.add(new TempOtherDetailDao("Transportation Cost", String.valueOf(postFilterOtherDetailDaoList.get(i).getIn_transporting_cost())));
                        }
                        if(postFilterOtherDetailDaoList.get(i).getIn_unpacking_cost()>0) {
                            tempOtherDetailDaoList.add(new TempOtherDetailDao("UnPacking Cost", String.valueOf(postFilterOtherDetailDaoList.get(i).getIn_unpacking_cost())));
                        }
                        if(postFilterOtherDetailDaoList.get(i).getIn_local_packaging_cost()>0) {
                            tempOtherDetailDaoList.add(new TempOtherDetailDao("Local Packing Cost", String.valueOf(postFilterOtherDetailDaoList.get(i).getIn_local_packaging_cost())));
                        }
                        if(postFilterOtherDetailDaoList.get(i).getIn_local_transporting_cost()>0) {
                            tempOtherDetailDaoList.add(new TempOtherDetailDao("Local Transportation Cost", String.valueOf(postFilterOtherDetailDaoList.get(i).getIn_local_transporting_cost())));
                        }

                    }


                }

                if(tempOtherDetailDaoList!=null && tempOtherDetailDaoList.size()>0){
                    setAdapterothers();
                }








                ImageView dialogButton2 = (ImageView) dialog3.findViewById(R.id.cls);
                dialogButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //  callQualityParameterJsonDetails();
                        dialog3.dismiss();
                    }
                });

                dialog3.show();
            }
        });


        if(peid.equalsIgnoreCase(""))
        {
            Long tsLong = System.currentTimeMillis()/1000;
            ts = tsLong.toString();
            Log.e("TST",""+ts);
            updatestatus = "0";
        }
        else
        {
            updatestatus = "1";
            textViewItemName.setEnabled(false);
            srccrop.setEnabled(false);
            String selectQueryed = "SELECT  * FROM purchaseentry where id = "+peid;

            final Cursor cursored = dbs.rawQuery(selectQueryed, null);
            Log.e("COUNT",""+cursor.getCount());


            //  db.insertpurchaseentry(farmerCode,farmerName,memberType,itemCode,itemName,textViewQty2.getText().toString(),edt_price.getText().toString(),textViewValue.getText().toString(),textViewNoofBag.getText().toString(),edt_remarks.getText().toString(),ui,spinner.getSelectedItem().toString(),vno.getText().toString(),spinner2.getSelectedItem().toString(),qcp.getText().toString(),lrpname.getText().toString(),lrpno.getText().toString(),textViewPickUpdate.getText().toString(),buycode,buyname,ts,"0",lnom,peoid,"I");

            // looping through all rows and adding to list
            if (cursored.moveToFirst()) {

                ts = cursored.getString(21);
                //textViewLotNo.setText("");
                //  txt_uom.setText("");
                //txt_var.setText("");
                tableid = cursored.getString(0);
                peoid = cursored.getString(24);
                lnom = cursored.getString(23);


                textViewFarmerCode.setText(cursored.getString(1));
                farmerCode=cursored.getString(1);
                showfardetails(farmerCode);
                textViewFarmerName.setText(cursored.getString(2));
                farmerName = cursored.getString(2);
                textViewMember.setText(cursored.getString(3));
                memberType=cursored.getString(3);
                itemCode= cursored.getString(4);
                textViewItemName.setText(cursored.getString(5));
                itemName = cursored.getString(5);
                edt_price.setText(cursored.getString(7));
                textViewValue.setText(cursored.getString(8));
                //textViewQualityPara.setText("");
                textViewNoofBag.setText(cursored.getString(9));
                //textViewSINo.setText("");
                // textViewOtherCost.setText("");
                //textViewTotalCost.setText("");
                Cursor cursoruom = dbs.query("ProductMasterAllProduct", new String[]{"out_uomtype_code","out_crop_variety"
                        }, "PmapOutProductCode" + "=?",
                        new String[]{itemCode}, null, null, null, null);

                if(cursoruom.moveToFirst())
                {
                    txt_uom.setText(cursoruom.getString(0));
                    txtquantity.setText("Quantity Procure (in "+cursoruom.getString(0)+")");
                    txt_lbl_ttlamt.setText("Total Amount (for "+cursoruom.getString(0)+")");
                    txt_lbl_purchaserate.setText("Purchase Rate (for "+cursoruom.getString(0)+")");
                    Pojokyc.productuom = cursoruom.getString(0);
                    txt_var.setText(cursoruom.getString(1));
                }
                textViewPickUpdate.setText(cursored.getString(18));
                edt_remarks.setText(cursored.getString(10));
                edt_advance.setText("");
                lrpno.setText(cursored.getString(17));
                qcp.setText(cursored.getString(15));
                vno.setText(cursored.getString(13));
                //encodedImage="0";
                lrpname.setText(cursored.getString(16));
                spinner.setSelection(0);
                spinner2.setSelection(0);
                // capturebill.setImageResource(0);
                spinnerpt.setSelection(0);
                bno.setText("");
                cqno.setText("");

                if(cursored.getString(14).equalsIgnoreCase("Direct To Buyer"))
                {
                    rlb.setVisibility(View.VISIBLE);
                }
                ui = cursored.getString(11);

                spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(cursored.getString(12)));
                spinner2.setSelection(((ArrayAdapter<String>)spinner2.getAdapter()).getPosition(cursored.getString(14)));
                spinnerss.setSelection(((ArrayAdapter<String>)spinnerss.getAdapter()).getPosition(cursored.getString(20)));
                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(cursored.getString(11)));

                    byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                    //Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());
                    capturebill.setImageBitmap(bitmap);


                    encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                    Log.e("ECS",""+encodedImage);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                byrname.setText(cursored.getString(20));
                buycode = cursored.getString(19);
                buyname = cursored.getString(20);

                //capturebill.setBackgroundResource(R.drawable.capture);
                numtest = Double.parseDouble(cursored.getString(6));
                textViewQty.setText("" + numtest);
                textViewQty2.setText("" + numtest);
                if(textViewSINo.getText().toString().equalsIgnoreCase("") || Double.parseDouble(textViewSINo.getText().toString().trim()) <= 0){
                    oldlbqty = textViewQty2.getText().toString();
                }
            }
        }
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!textViewItemName.getText().toString().trim().equalsIgnoreCase("")){
                    Intent is = new Intent(getApplicationContext(), SnoActivitype.class);
                    is.putExtra("TS",ts);
                    MyConstants.productcode = itemCode;
                    startActivity(is);
                }
            }
        });

        textViewQty2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   if(!textViewItemName.getText().toString().trim().equalsIgnoreCase("")){
                    Intent is = new Intent(getApplicationContext(), SnoActivitype.class);
                    is.putExtra("TS",ts);
                    is.putExtra("selctionflag","LB");
                    MyConstants.productcode = itemCode;
                    startActivity(is);
                }*/
            }
        });

        rg_bags.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioGroup.getCheckedRadioButtonId() == R.id.rb_by){
                    textViewQty2.setEnabled(false);
                    linearSino.setEnabled(true);
                    btn_add.setEnabled(true);
                }else if(radioGroup.getCheckedRadioButtonId() == R.id.rb_bn){
                    textViewQty2.setEnabled(true);
                    linearSino.setEnabled(false);
                    btn_add.setEnabled(false);
                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!textViewItemName.getText().toString().trim().equalsIgnoreCase("")){
                    Intent is = new Intent(getApplicationContext(), SnoActivitype.class);
                    is.putExtra("TS",ts);
                    MyConstants.productcode = itemCode;
                    is.putExtra("selctionflag","sl");
                    startActivity(is);
                }else{
                    showErrorDialog("Please Select Crop Name");
                }
            }
        });
    }

    private void getLotNoListDetails() {
        /*progressLayout.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        LotNoContextDao lotNoContextDao = new LotNoContextDao();
        lotNoContextDao.setOrgnId(orgnCode);
        lotNoContextDao.setLocnId(locnId);
        lotNoContextDao.setUserId(userId);
        lotNoContextDao.setLocaleId(localeId);
        lotNoContextDao.setFilterBy_Option("ALL");
        lotNoContextDao.setFilterBy_Code(".");
        lotNoContextDao.setFilterBy_FromValue(".");
        lotNoContextDao.setFilterBy_ToValue(".");

        mAPIService.getEstProcLotNOList(lotNoContextDao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewEstimateProcurementListDao>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewEstimateProcurementListDao newEstimateProcurementListDao) {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressLayout.setVisibility(View.GONE);
                        lotnoDaoList=newEstimateProcurementListDao.getLotNoContextDao().getLotnoDaoList();

                    }
                });*/

        progressLayout.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        EstActAppContextDao estActAppContextDao = new EstActAppContextDao();
        estActAppContextDao.setOrgnId(orgnCode);
        //  estActAppContextDao.setLocnId(locnId);
        estActAppContextDao.setLocnId(ApiUtils.instance);
        estActAppContextDao.setUserId(userId);
        estActAppContextDao.setLocaleId(localeId);
        estActAppContextDao.setStatus("EstimateApproved");

        mAPIService.getEstProcLotNOListDetails(estActAppContextDao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EstimateActualApprovedListDao>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(EstimateActualApprovedListDao estimateActualApprovedListDao) {
                        estimateListDaoList = estimateActualApprovedListDao.getEstActAppContextDao().getEstimateListDaoList();

                        db.deleteEstimateList(mContext);

                        List<EstimateListDao> estimateListDaoList = estimateActualApprovedListDao.getEstActAppContextDao().getEstimateListDaoList();

                        for (int i = 0; i < estimateListDaoList.size(); i++) {

                            EstimateListDao estimateListDao = new EstimateListDao(1, estimateListDaoList.get(i).getOut_Estm_rowid(),
                                    estimateListDaoList.get(i).getOut_LotNo(), estimateListDaoList.get(i).getOut_agg_code(), estimateListDaoList.get(i).getOut_Farmer_Code(),
                                    estimateListDaoList.get(i).getOut_Farmer_Name(), estimateListDaoList.get(i).getOut_Member_Type(), estimateListDaoList.get(i).getOut_Item_Code(),
                                    estimateListDaoList.get(i).getOut_Item_Name(), estimateListDaoList.get(i).getOut_Estimated_Qty(), estimateListDaoList.get(i).getOut_Estimated_Price(),
                                    estimateListDaoList.get(i).getOut_Estimated_Value(), estimateListDaoList.get(i).getOut_Remarks(), estimateListDaoList.get(i).getOut_Status(), estimateListDaoList.get(i).getOut_Estimated_PickupDate(), "No");
                            db.addAllEstimateListDetails(estimateListDao);

                        }

                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressLayout.setVisibility(View.GONE);

                    }
                });

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.but_save:
                callActualProcSaveMethod();

                break;
            case R.id.but_cancel:
                deletedata();
                finish();
                break;
            case R.id.but_view:
                // startActivity(new Intent(mContext,RecAutualPurchaseViewActivity.class));
                Intent intent1 = new Intent(mContext, Purchaseentrylist.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.linear_lotno:
                showLotnoDialog();
                break;
            case R.id.txtview_date:
                showDateDialog();
                break;
            case R.id.img_edit:
                showItemSearchDialog();
                break;
            case R.id.linear_qualitypara:
                Intent i = new Intent(getApplicationContext(), Qparpe.class);
                i.putExtra("TS",ts);
                i.putExtra("PC",itemCode);
                startActivity(i);

                break;
            case R.id.linear_sino:
                if(!textViewItemName.getText().toString().trim().equalsIgnoreCase("")){
                    Intent is = new Intent(getApplicationContext(), SnoActivitype.class);
                    is.putExtra("TS",ts);
                    MyConstants.productcode = itemCode;
                    is.putExtra("selctionflag","sl");
                    startActivity(is);
                }
                break;
            case R.id.linear_othercost:

                break;

            case R.id.txt_act_Item:
                showItemSearchDialog();
            default:
                break;

        }

    }
    private void setAdapter() {
//        qualityListParameterAdapter = new QualityListParameterAdapteraap(mContext, postQtyDetailDaoList);
//        recyclerViewQuality.setAdapter(qualityListParameterAdapter);
//        qualityListParameterAdapter.notifyDataSetChanged();
    }
    private void setAdapterothers() {

//        otherCostAdapter2 = new OtherCostAdapter2(mContext, tempOtherDetailDaoList);
//        recyclerViewOtherCost.setAdapter(otherCostAdapter2);
//        otherCostAdapter2.notifyDataSetChanged();

    }
    private void callActualProcSaveMethod() {


        String lotnumber = textViewLotNo.getText().toString();
        final String farmercode = textViewFarmerCode.getText().toString();
        final String farmername = textViewFarmerName.getText().toString();
        final String membertype = textViewMember.getText().toString();
        String itemname = textViewItemName.getText().toString();
        final String qty = textViewQty2.getText().toString();


        final String price = edt_price.getText().toString();
        final String value = textViewValue.getText().toString();
        final String remarks = edt_remarks.getText().toString();
        final String advanceAmount = edt_advance.getText().toString();
        String quality = textViewQualityPara.getText().toString();
        String siNoBags = textViewSINo.getText().toString();
        final String noofbags = textViewNoofBag.getText().toString();
        String otherCost = textViewOtherCost.getText().toString();
        final String pickupdate = textViewPickUpdate.getText().toString();
        final String eqcp = qcp.getText().toString();
        final String evno = vno.getText().toString();
        final String vst=spinner.getSelectedItem().toString();
        final String dsn=spinner2.getSelectedItem().toString();
        final String lrpn =lrpname.getText().toString();
        final String lrpmn =lrpno.getText().toString();
        String vst2="",dsn2="";
        final String ebn,ecq,spt;
        final SQLiteDatabase dbs = db.getWritableDatabase();
        Cursor cursor2 = dbs.query("vtype", new String[]{"v1", "v2", "v3"
                }, "v3" + "=?",
                new String[]{vst}, null, null, null, null);


        if(cursor2.moveToFirst())
        {
            do {
                vst2= cursor2.getString(1);

            }while(cursor2.moveToNext());
        }

        Cursor cursor2d = dbs.query("dstn", new String[]{"v1", "v2", "v3"
                }, "v3" + "=?",
                new String[]{dsn}, null, null, null, null);


        if(cursor2d.moveToFirst())
        {
            do {
                dsn2= cursor2d.getString(1);

            }while(cursor2d.moveToNext());
        }
        double totalCost = 0;
        if (!textViewTotalCost.getText().toString().isEmpty()) {
            totalCost = Double.parseDouble(textViewTotalCost.getText().toString());
        }



        if (!advanceAmount.isEmpty()) {
            advanceValue = Double.parseDouble(advanceAmount);
        }

        if (itemname.isEmpty()) {
            showErrorDialog("Please Select Crop Name");
        } else if (qty.equalsIgnoreCase("0")||qty.equalsIgnoreCase("0.0")) {
            showErrorDialog("Estimated Qty Mustbe Greater Than 0 ");
        } else if (price.isEmpty()) {
            showErrorDialog("Please Enter Rate");
        }
        else if (farmername.isEmpty()) {
            showErrorDialog("Please Enter Farmer");
        } else if (pickupdate.isEmpty()) {
            showErrorDialog("Please Pick up Date");
        }

        else if (spinnerss.getSelectedItem().toString().isEmpty()) {
            showErrorDialog("Please Select Season");
        }
//        else if (quality.isEmpty()) {
//            showErrorDialog("Please Select Quality Parameters  ");
//        }
//          else if (siNoBags.isEmpty()) {
//            showErrorDialog("Please Select SI.No.of Bags");
////        } else if (otherCost.isEmpty()) {
////            showErrorDialog("Please Select Other Cost ");
//        }
//        else if (lrpmn.isEmpty()&& lrpmn.length()<10) {
//            showErrorDialog("Invalid Lrp Mobile No");
////        } else if (otherCost.isEmpty()) {
////            showErrorDialog("Please Select Other Cost ");
//        }
//        else if(textViewNoofBag.getText().toString().equalsIgnoreCase(""))
//        {
//            showErrorDialog("Please Enter No of Bags");
//        }
//        else if (eqcp.isEmpty()) {
//            showErrorDialog("Please Enter QC Person ");
//        }
//        else if (evno.isEmpty()) {
//            showErrorDialog("Please Enter Vehicle No ");
//        }
//        else if (encodedImage.equalsIgnoreCase("0")) {
//            showErrorDialog("Please Capture Image");
//        }
//
//        else if (spinner.getSelectedItem().toString().equalsIgnoreCase("")) {
//            showErrorDialog("Please Select Vehicle Type ");
//        }
//
//        else if (spinner2.getSelectedItem().toString().equalsIgnoreCase("")) {
//            showErrorDialog("Please Select Destination ");
//        }
//        else if (pstatus == 1 && spinnerpt.getSelectedItem().toString().equalsIgnoreCase("")) {
//            showErrorDialog("Please Select Payment Type ");
//        }
//        else if (lrpn.isEmpty()) {
//            showErrorDialog("Please Enter LRP Name");
//        }
//        else if (!(Double.parseDouble(price) > 0)) {
//            //  Toast.makeText(mCon
//            //  text, "Price Should not be 0 ", Toast.LENGTH_SHORT).show();
//            showErrorDialog("Price Should not be 0 ");
//        } else if (advanceValue > totalCost) {
//            showErrorDialog("Advance Should be below Total Cost");
//        }
//        else if(cstatus == 1 && bno.getText().toString().equalsIgnoreCase(""))
//        {
//            showErrorDialog("Please Enter Bank Account No");
//        }
//        else if(cstatus == 2 && cqno.getText().toString().equalsIgnoreCase(""))
//        {
//            showErrorDialog("Please Enter Cheque No");
//        }
        else

        {

//              if(spinnerb.getSelectedItem().toString().equalsIgnoreCase(""))
//              {
//                  bycode="";
//                  byname="";
//              }
//              else
//              {
//                  String[] bycn = spinnerb.getSelectedItem().toString().split("-");
//                  bycode=bycn[0];
//                  byname=bycn[1];
//              }
            if(spinnerpt.getSelectedItem().toString().equalsIgnoreCase(""))
            {
                spt="";
            }
            else
            {
                spt = spinnerpt.getSelectedItem().toString();
            }
            if(bno.getText().toString().equalsIgnoreCase(""))
            {
                ebn="";
            }
            else
            {
                ebn = bno.getText().toString();
            }
            if(cqno.getText().toString().equalsIgnoreCase(""))
            {
                ecq="";
            }
            else
            {
                ecq = cqno.getText().toString();
            }

            if(isNetwork)
            {
                savepe();
//                  double x = Double.parseDouble(textViewQty2.getText().toString());
//
//                  if(cnt<x)
//                  {
//
//                      AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
//                              RecActualPurchaseActivity.this);
//
//// Setting Dialog Title
//                      alertDialog2.setTitle("Confirm...");
//
//// Setting Dialog Message
//                      alertDialog2.setMessage("No of Serial bags is less than the total quantity.Are you sure you want to continue?");
//
//// Setting Icon to Dialog
//
//
//// Setting Positive "Yes" Btn
//                      alertDialog2.setPositiveButton("YES",
//                              new DialogInterface.OnClickListener() {
//                                  public void onClick(DialogInterface dialog, int which) {
//                                      // Write your code here to execute after dialog
//                                      savepe();
//                                  }
//                              });
//// Setting Negative "NO" Btn
//                      alertDialog2.setNegativeButton("NO",
//                              new DialogInterface.OnClickListener() {
//                                  public void onClick(DialogInterface dialog, int which) {
//                                      // Write your code here to execute after dialog
//
//                                      dialog.cancel();
//                                  }
//                              });
//
//// Showing Alert Dialog
//                      alertDialog2.show();
//
//                  }
//                  else {
//                      savepe();
//                  }
            }
            else
            {
                Pojokyc.purno="";
// code for inser/update snowith qty for loosebags  -start
//                if(textViewSINo.getText().toString().equalsIgnoreCase("") || Double.parseDouble(textViewSINo.getText().toString()) <= 0){
                if(lbvals.equalsIgnoreCase("LB")){
                    Cursor cr = dbs.rawQuery("select * from snowithqty where v1 = '"+"LB"+itemCode+"' ",null);
                    try{
                        if(cr.getCount() > 0){
                            Double exstqtty = 0.0,exqty2 = 0.00;
                            if(cr.moveToFirst()){
                                if(!cr.getString(cr.getColumnIndexOrThrow("v2")).equalsIgnoreCase("") && cr.getString(cr.getColumnIndexOrThrow("v2")) != null){
                                    exstqtty = Double.parseDouble(cr.getString(cr.getColumnIndexOrThrow("v2")));
                                    Log.e("qty1",""+exqty2);
                                }
                                exqty2 =  Double.parseDouble(textViewQty2.getText().toString()) - Double.parseDouble(oldlbqty);
                                Log.e("qty2",""+exqty2);
                                        /*if(exqty2 < 0){

                                        }else if(exqty2 >= 0){

                                        }*/
                                exstqtty += exqty2;
                                Log.e("qty3",""+exstqtty);
                                exstqtty += Double.parseDouble(textViewQty2.getText().toString());
                                dbs.execSQL("update snowithqty set v2 = '"+exstqtty+"' where v1 = '"+"LB"+itemCode+"' ");
                            }
                        }
                        else{

                            db.insertsnowithqty("LB"+itemCode,textViewQty2.getText().toString(),ts,"Y","1",itemCode,"0");
                        }
                    }catch (Exception er){
                        Log.e("error",Log.getStackTraceString(er));
                    }finally {
                        cr.close();
                    }
                }
// code for inser/update snowith qty for loosebags  -end
                if(updatestatus.equalsIgnoreCase("0"))
                {
//                     double x = Double.parseDouble(textViewQty2.getText().toString());
//                     if(cnt<x) {
//                         AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
//                                 RecActualPurchaseActivity.this);
//
//// Setting Dialog Title
//                         alertDialog2.setTitle("Confirm...");
//
//// Setting Dialog Message
//                         alertDialog2.setMessage("No of Serial bags is less than the total quantity.Are you sure you want to continue?");
//
//// Setting Icon to Dialog
//
//
//// Setting Positive "Yes" Btn
//                         alertDialog2.setPositiveButton("YES",
//                                 new DialogInterface.OnClickListener() {
//                                     public void onClick(DialogInterface dialog, int which) {
//                                         // Write your code here to execute after dialog
//                                         savepe();
//                                     }
//                                 });
//// Setting Negative "NO" Btn
//                         alertDialog2.setNegativeButton("NO",
//                                 new DialogInterface.OnClickListener() {
//                                     public void onClick(DialogInterface dialog, int which) {
//                                         // Write your code here to execute after dialog
//
//                                         dialog.cancel();
//                                     }
//                                 });
//
//// Showing Alert Dialog
//                         alertDialog2.show();
//                     }
//
//                     else
//                     {
                    db.insertpurchaseentry(farmerCode,farmerName,memberType,itemCode,itemName,textViewQty2.getText().toString(),edt_price.getText().toString(),textViewValue.getText().toString(),textViewSINo.getText().toString(),edt_remarks.getText().toString(),ui,spinner.getSelectedItem().toString(),vno.getText().toString(),spinner2.getSelectedItem().toString(),qcp.getText().toString(),lrpname.getText().toString(),lrpno.getText().toString(),textViewPickUpdate.getText().toString(),buycode,buyname,ts,"0",lnom,peoid,"I");

                    pdialog.dismiss();

                    textViewLotNo.setText("");
                    txt_uom.setText("");
                    txt_var.setText("");
                    textViewFarmerCode.setText("");
                    textViewFarmerName.setText("");
                    textViewMember.setText("");
                    textViewItemName.setText("");
                    edt_price.setText("");
                    textViewValue.setText("");
                    textViewQualityPara.setText("");
                    textViewNoofBag.setText("");
                    textViewSINo.setText("");
                    textViewOtherCost.setText("");
                    textViewTotalCost.setText("");
                    textViewPickUpdate.setText("");
                    edt_remarks.setText("");
                    edt_advance.setText("");
                    lrpno.setText("");
                    qcp.setText("");
                    vno.setText("");
                    encodedImage="0";
                    lrpname.setText("");
                    spinner.setSelection(0);
                    spinner2.setSelection(0);
                    capturebill.setImageResource(0);
                    spinnerpt.setSelection(0);
                    bno.setText("");
                    cqno.setText("");
                    byrname.setText("");

                    capturebill.setBackgroundResource(R.drawable.capture);
                    numtest = 0;
                    textViewQty.setText("" + numtest);
                    textViewQty2.setText("" + numtest);
                    showErrorDialog2("Purchase Entry Saved Locally Successfully");
                    Long tsLong = System.currentTimeMillis()/1000;
                    ts = tsLong.toString();



                }
                else
                {
                    db.updatepurchaseentry(Integer.valueOf(tableid),farmerCode,farmerName,memberType,itemCode,itemName,textViewQty2.getText().toString(),edt_price.getText().toString(),textViewValue.getText().toString(),textViewNoofBag.getText().toString(),edt_remarks.getText().toString(),ui,spinner.getSelectedItem().toString(),vno.getText().toString(),spinner2.getSelectedItem().toString(),qcp.getText().toString(),lrpname.getText().toString(),lrpno.getText().toString(),textViewPickUpdate.getText().toString(),buycode,buyname,ts,"0",lnom,peoid,"I");

                    pdialog.dismiss();

                    textViewLotNo.setText("");
                    txt_uom.setText("");
                    txt_var.setText("");
                    textViewFarmerCode.setText("");
                    textViewFarmerName.setText("");
                    textViewMember.setText("");
                    textViewItemName.setText("");
                    edt_price.setText("");
                    textViewValue.setText("");
                    textViewQualityPara.setText("");
                    textViewNoofBag.setText("");
                    textViewSINo.setText("");
                    textViewOtherCost.setText("");
                    textViewTotalCost.setText("");
                    textViewPickUpdate.setText("");
                    edt_remarks.setText("");
                    edt_advance.setText("");
                    lrpno.setText("");
                    qcp.setText("");
                    vno.setText("");
                    encodedImage="0";
                    lrpname.setText("");
                    spinner.setSelection(0);
                    spinner2.setSelection(0);
                    capturebill.setImageResource(0);
                    spinnerpt.setSelection(0);
                    bno.setText("");
                    cqno.setText("");
                    byrname.setText("");

                    capturebill.setBackgroundResource(R.drawable.capture);
                    numtest = 0;
                    textViewQty.setText("" + numtest);
                    textViewQty2.setText("" + numtest);
                    showErrorDialog2("Purchase Entry Updated Locally Successfully");
                    Long tsLong = System.currentTimeMillis()/1000;
                    ts = tsLong.toString();
                }
            }
        }

//        try {
//            BitMatrix bitMatrix = writer.encode(textViewLotNo.getText().toString(), BarcodeFormat.QR_CODE, 512, 512);
//            int width = bitMatrix.getWidth();
//            int height = bitMatrix.getHeight();
//            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//            for (int x = 0; x < width; x++) {
//                for (int y = 0; y < height; y++) {
//                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
//                }
//            }
//           qrgen.setImageBitmap(bmp);
//            qrgen.setVisibility(View.VISIBLE);
//
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
    }

    private void showStatusDialog(String s) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Success!")
                .setMessage(s)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        postOtherDetailDaoList.clear();
                        postSiNoDetailDaoList.clear();
                        postQtyDetailDaoList.clear();

                    }
                })
                .show();
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

    private void showLotNoDialog(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Success!")
                .setMessage(s)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getLotNoListDetails();
                        dialog.dismiss();
                        postOtherDetailDaoList.clear();
                        postSiNoDetailDaoList.clear();
                        postQtyDetailDaoList.clear();
                    }
                })
                .show();
    }


    private void showItemSearchDialog() {

        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.custom_search_item_dialog);
        //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
        dialog.setTitle("Title...");
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
        final RecyclerView recyclerView = dialog.findViewById(R.id.itm);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
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

        final ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(mContext,
                R.layout.support_simple_spinner_dropdown_item, productItemList);

        elc.setAdapter(adapterlist2n);
        final ArrayAdapter<String> adapterlist2nn = new ArrayAdapter<String>(mContext,
                R.layout.support_simple_spinner_dropdown_item, cat);

        elc2.setAdapter(adapterlist2nn);
        final ArrayAdapter<String> adapterlist2nnn = new ArrayAdapter<String>(mContext,
                R.layout.support_simple_spinner_dropdown_item, var);

        elc3.setAdapter(adapterlist2nnn);


        elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                pmListDaoList.clear();
                String productName = elc.getText().toString();
                pmListDaoList = db.getProductMasterAllProductDetails(productName);

                MyRecyclerViewAdapterItem adapterf = new MyRecyclerViewAdapterItem(mContext, pmListDaoList);
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

                MyRecyclerViewAdapterItem adapterf = new MyRecyclerViewAdapterItem(mContext, pmListDaoList);
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

                MyRecyclerViewAdapterItem adapterf = new MyRecyclerViewAdapterItem(mContext, pmListDaoList);
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

//    private void showDateDialog() {
//
//        final Calendar calendar = Calendar.getInstance();
//        int mYear = calendar.get(Calendar.YEAR);
//        int mMonth = calendar.get(Calendar.MONTH);
//        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
//        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, android.R.style.Theme_Holo_Dialog,
//                new DatePickerDialog.OnDateSetListener() {
//
//                    @Override
//                    public void onDateSet(DatePicker view, int year,
//                                          int monthOfYear, int dayOfMonth) {
//                        String month = Utility.theMonth((monthOfYear));
//                        textViewPickUpdate.setText(dayOfMonth + "-" + month + "-" + year);
//
//
//                    }
//                }, mYear, mMonth, mDay);
//        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
//        datePickerDialog.show();
//
//    }

    private void showLotnoDialog() {

        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.custom_lotno_dialog);
        //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
        dialog.setTitle("Title...");
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
        final RelativeLayout progressLayout1=(RelativeLayout) dialog.findViewById(R.id.progressLayout);
        final RecyclerView recyclerView = dialog.findViewById(R.id.recycle_lotno);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        dialog.getWindow().setLayout(width, height);

        final AutoCompleteTextView elc = (AutoCompleteTextView) dialog.findViewById(R.id.elc);
        final AutoCompleteTextView elc2 = (AutoCompleteTextView) dialog.findViewById(R.id.elc2);
        final AutoCompleteTextView elc3 = (AutoCompleteTextView) dialog.findViewById(R.id.elc3);
        elc.setInputType(elc.getInputType());
        elc2.setInputType(elc2.getInputType());
        elc3.setInputType(elc3.getInputType());
        lotNoList = new ArrayList<>();
        sitem = new ArrayList<>();
        sdate = new ArrayList<>();
        lotNoList.clear();
        sdate.clear();
        sitem.clear();







       /* for(int i=0;i<lotnoDaoList.size();i++){
            lotNoList.add(lotnoDaoList.get(i).getOut_LotNo());
        }*/
        for(int i = 0; i< estimateListDaoList.size(); i++){

            if(frm.equalsIgnoreCase("Actual"))
            {
                if(estimateListDaoList.get(i).getOut_Status().equalsIgnoreCase("Estimate")) {
                    lotNoList.add(estimateListDaoList.get(i).getOut_Farmer_Name());
                }
            }
            else
            {
                if(estimateListDaoList.get(i).getOut_Status().equalsIgnoreCase("Approved")) {
                    lotNoList.add(estimateListDaoList.get(i).getOut_Farmer_Name());
                }
            }



            // Log.e("HELLO",""+estimateListDaoList.get(i).getOut_Status());
        }

        HashSet hs = new HashSet();

        hs.addAll(lotNoList); // demoArrayList= name of arrayList from which u want to remove duplicates

        lotNoList.clear();
        lotNoList.addAll(hs);
        final ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(mContext,
                R.layout.support_simple_spinner_dropdown_item, lotNoList);

        elc.setAdapter(adapterlist2n);

        for(int i = 0; i< estimateListDaoList.size(); i++){
            if(frm.equalsIgnoreCase("Actual"))
            {
                if(estimateListDaoList.get(i).getOut_Status().equalsIgnoreCase("Estimate")) {
                    sitem.add(estimateListDaoList.get(i).getOut_Item_Name());
                }
            }
            else
            {
                if(estimateListDaoList.get(i).getOut_Status().equalsIgnoreCase("Approved")) {
                    sitem.add(estimateListDaoList.get(i).getOut_Item_Name());
                }
            }
        }

        HashSet hs2 = new HashSet();

        hs2.addAll(sitem); // demoArrayList= name of arrayList from which u want to remove duplicates

        sitem.clear();
        sitem.addAll(hs2);
        final ArrayAdapter<String> adapterlist3n = new ArrayAdapter<String>(mContext,
                R.layout.support_simple_spinner_dropdown_item, sitem);

        elc2.setAdapter(adapterlist3n);


        for(int i = 0; i< estimateListDaoList.size(); i++){
            if(frm.equalsIgnoreCase("Actual"))
            {
                if(estimateListDaoList.get(i).getOut_Status().equalsIgnoreCase("Estimate")) {
                    sdate.add(estimateListDaoList.get(i).getOut_Estimated_PickupDate());
                }
            }
            else
            {
                if(estimateListDaoList.get(i).getOut_Status().equalsIgnoreCase("Approved")) {
                    sdate.add(estimateListDaoList.get(i).getOut_Estimated_PickupDate());
                }
            }
        }

        HashSet hs3 = new HashSet();

        hs3.addAll(sdate); // demoArrayList= name of arrayList from which u want to remove duplicates

        sdate.clear();
        sdate.addAll(hs3);
        final ArrayAdapter<String> adapterlist4n = new ArrayAdapter<String>(mContext,
                R.layout.support_simple_spinner_dropdown_item, sdate);

        elc3.setAdapter(adapterlist4n);


        elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String lotNo = elc.getText().toString();
                elc2.setText("");
                elc3.setText("");
//                if(isNetwork==true){
//                    getSingleLotNOdetails(lotNo,progressLayout1);
//
//                    RecActualPurchaseActivity.MyRecyclerViewAdapterLotNo adapterf = new RecActualPurchaseActivity.MyRecyclerViewAdapterLotNo(mContext, singleLotnoDaoList);
//                    recyclerView.setAdapter(adapterf);
//
//                   ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
//                }else {

                List<EstimateListDao> estimateListDaos=db.getParticularLotnoDetails(lotNo,frm);
                singleLotnoDaoList.clear();
                for(int i=0;i<estimateListDaos.size();i++){
                    singleLotnoDaoList.add(new SingleLotnoDao(estimateListDaos.get(i).getOut_Estm_rowid(),estimateListDaos.get(i).getOut_LotNo(),
                            estimateListDaos.get(i).getOut_agg_code(),estimateListDaos.get(i).getOut_Farmer_Code(),estimateListDaos.get(i).getOut_Farmer_Name(),
                            estimateListDaos.get(i).getOut_Member_Type(),estimateListDaos.get(i).getOut_Item_Code(),estimateListDaos.get(i).getOut_Item_Name(),
                            Double.parseDouble(estimateListDaos.get(i).getOut_Estimated_Qty()), Double.parseDouble(estimateListDaos.get(i).getOut_Estimated_Price()), Double.parseDouble(estimateListDaos.get(i).getOut_Estimated_Value()),
                            estimateListDaos.get(i).getOut_Estimated_PickupDate(),estimateListDaos.get(i).getOut_Remarks(),estimateListDaos.get(i).getOut_Status()));

                }

                MyRecyclerViewAdapterLotNo adapterf = new MyRecyclerViewAdapterLotNo(mContext, singleLotnoDaoList);
                recyclerView.setAdapter(adapterf);

                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);

                //   }

            }
        });
        elc2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String lotNo = elc2.getText().toString();
                elc.setText("");
                elc3.setText("");
//                if(isNetwork==true){
//                    getSingleLotNOdetails(lotNo,progressLayout1);
//
//                    RecActualPurchaseActivity.MyRecyclerViewAdapterLotNo adapterf = new RecActualPurchaseActivity.MyRecyclerViewAdapterLotNo(mContext, singleLotnoDaoList);
//                    recyclerView.setAdapter(adapterf);
//
//                   ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
//                }else {

                List<EstimateListDao> estimateListDaos=db.getParticularLotnoDetails2(lotNo,frm);
                singleLotnoDaoList.clear();
                for(int i=0;i<estimateListDaos.size();i++){
                    singleLotnoDaoList.add(new SingleLotnoDao(estimateListDaos.get(i).getOut_Estm_rowid(),estimateListDaos.get(i).getOut_LotNo(),
                            estimateListDaos.get(i).getOut_agg_code(),estimateListDaos.get(i).getOut_Farmer_Code(),estimateListDaos.get(i).getOut_Farmer_Name(),
                            estimateListDaos.get(i).getOut_Member_Type(),estimateListDaos.get(i).getOut_Item_Code(),estimateListDaos.get(i).getOut_Item_Name(),
                            Double.parseDouble(estimateListDaos.get(i).getOut_Estimated_Qty()), Double.parseDouble(estimateListDaos.get(i).getOut_Estimated_Price()), Double.parseDouble(estimateListDaos.get(i).getOut_Estimated_Value()),
                            estimateListDaos.get(i).getOut_Estimated_PickupDate(),estimateListDaos.get(i).getOut_Remarks(),estimateListDaos.get(i).getOut_Status()));
                }

                MyRecyclerViewAdapterLotNo adapterf = new MyRecyclerViewAdapterLotNo(mContext, singleLotnoDaoList);
                recyclerView.setAdapter(adapterf);

                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc2.getWindowToken(), 0);

                //   }

            }
        });
        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        elc3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String lotNo = elc3.getText().toString();
                elc.setText("");
                elc2.setText("");
//                if(isNetwork==true){
//                    getSingleLotNOdetails(lotNo,progressLayout1);
//
//                    RecActualPurchaseActivity.MyRecyclerViewAdapterLotNo adapterf = new RecActualPurchaseActivity.MyRecyclerViewAdapterLotNo(mContext, singleLotnoDaoList);
//                    recyclerView.setAdapter(adapterf);
//
//                   ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
//                }else {

                List<EstimateListDao> estimateListDaos=db.getParticularLotnoDetails3(lotNo,frm);
                singleLotnoDaoList.clear();
                for(int i=0;i<estimateListDaos.size();i++){
                    singleLotnoDaoList.add(new SingleLotnoDao(estimateListDaos.get(i).getOut_Estm_rowid(),estimateListDaos.get(i).getOut_LotNo(),
                            estimateListDaos.get(i).getOut_agg_code(),estimateListDaos.get(i).getOut_Farmer_Code(),estimateListDaos.get(i).getOut_Farmer_Name(),
                            estimateListDaos.get(i).getOut_Member_Type(),estimateListDaos.get(i).getOut_Item_Code(),estimateListDaos.get(i).getOut_Item_Name(),
                            Double.parseDouble(estimateListDaos.get(i).getOut_Estimated_Qty()), Double.parseDouble(estimateListDaos.get(i).getOut_Estimated_Price()), Double.parseDouble(estimateListDaos.get(i).getOut_Estimated_Value()),
                            estimateListDaos.get(i).getOut_Estimated_PickupDate(),estimateListDaos.get(i).getOut_Remarks(),estimateListDaos.get(i).getOut_Status()));
                }

                MyRecyclerViewAdapterLotNo adapterf = new MyRecyclerViewAdapterLotNo(mContext, singleLotnoDaoList);
                recyclerView.setAdapter(adapterf);

                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc3.getWindowToken(), 0);

                //   }

            }
        });
        dialog.show();

    }

    private void getSingleLotNOdetails(String lotNo, final RelativeLayout progressLayout1) {

        progressLayout1.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        singleLotnoDaoList.clear();

        SingleContextDao singleContextDao = new SingleContextDao();
        singleContextDao.setOrgnId(orgnCode);
        // singleContextDao.setLocnId(locnId);
        singleContextDao.setLocnId(ApiUtils.instance);
        singleContextDao.setUserId(userId);
        singleContextDao.setLocaleId(localeId);
        singleContextDao.setIn_Estm_rowid(0);
        singleContextDao.setIn_LotNo(lotNo);

        mAPIService.getSingleLotNoDetails(singleContextDao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewEstimateProcurementSingleDao>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewEstimateProcurementSingleDao newEstimateProcurementSingleDao) {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressLayout1.setVisibility(View.GONE);
                        singleLotnoDaoList.add(newEstimateProcurementSingleDao.getSingleContextDao().getSingleLotnoDao());

                    }
                });
    }

    public class MyRecyclerViewAdapterLotNo extends RecyclerView.Adapter<MyRecyclerViewAdapterLotNo.ViewHolder> {
        private List<SingleLotnoDao> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        public MyRecyclerViewAdapterLotNo(Context context, List<SingleLotnoDao> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.custom_recyclerview_lotno, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final SingleLotnoDao pojofar = mData.get(position);
            holder.txtlotNo.setText(pojofar.getIn_LotNo());
            holder.txtFName.setText(pojofar.getIn_Farmer_Name());
            holder.txtFCode.setText(pojofar.getIn_Farmer_Code());
            holder.txtMember.setText(pojofar.getIn_Member_Type());
            holder.txtICode.setText(pojofar.getIn_Item_Code());
            holder.txtIName.setText(pojofar.getIn_Item_Name());
            holder.txtQty.setText("" + pojofar.getIn_Estimated_Qty());
            holder.txtPrice.setText("" + pojofar.getIn_Estimated_Price());
            holder.txtStatus.setText(pojofar.getOut_Status());
            final SQLiteDatabase dbs = db.getWritableDatabase();
            Cursor cursoruom = dbs.query("ProductMasterAllProduct", new String[]{"out_uomtype_code"
                    }, "PmapOutProductCode" + "=?",
                    new String[]{pojofar.getIn_Item_Code()}, null, null, null, null);

            if(cursoruom.moveToFirst())
            {
                holder.txtuom.setText(cursoruom.getString(0));
            }

            holder.llist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if(pojofar.getOut_Status().equalsIgnoreCase("Estimate"))
//                    {

                    linearQualityPara.setEnabled(true);
                    linearSino.setEnabled(true);
                    linearOthercost.setEnabled(true);
                    status="Actual";
                    modeflag = "I";
                    rpt.setVisibility(View.VISIBLE);
                    estatus = 0;
                    rlb.setVisibility(View.VISIBLE);
                    viewqp.setVisibility(View.GONE);
                    viewoc.setVisibility(View.GONE);
                    lotno=pojofar.getIn_LotNo();
                    farmerCode = pojofar.getIn_Farmer_Code();
                    farmerName = pojofar.getIn_Farmer_Name();
                    memberType = pojofar.getIn_Member_Type();
                    itemCode=pojofar.getIn_Item_Code();
                    final SQLiteDatabase dbs = db.getWritableDatabase();
                    Cursor cursoruom = dbs.query("ProductMasterAllProduct", new String[]{"out_uomtype_code","out_crop_variety"
                            }, "PmapOutProductCode" + "=?",
                            new String[]{itemCode}, null, null, null, null);

                    if(cursoruom.moveToFirst())
                    {
                        txt_uom.setText(cursoruom.getString(0));
                        txtquantity.setText("Quantity Procure (in "+cursoruom.getString(0)+")");
                        txt_lbl_ttlamt.setText("Total Amount (for "+cursoruom.getString(0)+")");
                        txt_lbl_purchaserate.setText("Purchase Rate (for "+cursoruom.getString(0)+")");
                        Pojokyc.productuom = cursoruom.getString(0);
                        txt_var.setText(cursoruom.getString(1));
                    }
                    itemName=pojofar.getIn_Item_Name();
                    price= String.valueOf(pojofar.getIn_Estimated_Price());
                    quantity= String.valueOf(pojofar.getIn_Estimated_Qty());
                    numtest= Double.parseDouble(String.valueOf(pojofar.getIn_Estimated_Qty()));
                    calculatevalue= String.valueOf(pojofar.getIn_Estimated_Value());
                    estimateDate=pojofar.getIn_Estimated_PickDate();



                    textViewLotNo.setText(lotno);
                    textViewFarmerName.setText(farmerName);
                    textViewMember.setText(memberType);
                    textViewFarmerCode.setText(farmerCode);
                    textViewItemName.setText(itemName);
                    edt_price.setText(price);
                    //textViewQty.setText(quantity);
                    textViewQty2.setText(quantity);
                    numtest = Double.parseDouble(quantity);
                    textViewValue.setText("" + new DecimalFormat("##.##").format(Double.parseDouble(calculatevalue)));
                    // textViewValue.setText(calculatevalue);
                    if(estimateDate!=null && !estimateDate.isEmpty()){
                        if(isNetwork==true){
                            textViewPickUpdate.setText(estimateDate);
                        }else {
                            textViewPickUpdate.setText(estimateDate);
                        }

                    }else {
                        textViewPickUpdate.setText("");
                    }
                    textViewLotNo.setEnabled(true);
                    textViewFarmerName.setEnabled(true);
                    textViewFarmerCode.setEnabled(true);
                    textViewMember.setEnabled(true);
                    textViewItemName.setEnabled(true);
                    textViewQty.setEnabled(true);
                    textViewQty2.setEnabled(true);
                    edt_price.setEnabled(true);
                    textViewValue.setEnabled(true);
                    textViewNoofBag.setEnabled(true);
                    textViewSINo.setEnabled(true);
                    edt_remarks.setEnabled(true);
                    textViewQualityPara.setEnabled(true);
                    textViewOtherCost.setEnabled(true);
                    linearLotno.setEnabled(true);
                    linearQualityPara.setEnabled(true);
                    linearSino.setEnabled(true);
                    linearOthercost.setEnabled(true);
                    textViewincrease.setEnabled(true);
                    textViewDecrease.setEnabled(true);
                    qcp.setEnabled(true);
                    vno.setEnabled(true);
                    spinner.setEnabled(true);
                    spinner2.setEnabled(true);
//                    }
//
//                    else
//                    {
//                        lotno=pojofar.getIn_LotNo();
//                        farmerCode = pojofar.getIn_Farmer_Code();
//                        farmerName = pojofar.getIn_Farmer_Name();
//                        memberType = pojofar.getIn_Member_Type();
//                        itemCode=pojofar.getIn_Item_Code();
//                        itemName=pojofar.getIn_Item_Name();
//                        final SQLiteDatabase dbs = db.getWritableDatabase();
//                        Cursor cursoruom = dbs.query("ProductMasterAllProduct", new String[]{"out_uomtype_code","out_crop_variety"
//                                }, "PmapOutProductCode" + "=?",
//                                new String[]{itemCode}, null, null, null, null);
//
//                        if(cursoruom.moveToFirst())
//                        {
//                            txt_uom.setText(cursoruom.getString(0));
//                            txt_var.setText(cursoruom.getString(1));
//                        }
//                        linearQualityPara.setEnabled(true);
//                        linearSino.setEnabled(true);
//                        linearOthercost.setEnabled(true);
//                        status="Approved";
//                        rpt.setVisibility(View.VISIBLE);
//                        pstatus = 1;
//                        modeflag = "U";
//                        estatus = 1;
//                        price= String.valueOf(pojofar.getIn_Estimated_Price());
//                        quantity= String.valueOf(pojofar.getIn_Estimated_Qty());
//                        numtest= Double.parseDouble(String.valueOf(pojofar.getIn_Estimated_Qty()));
//                        calculatevalue= String.valueOf(pojofar.getIn_Estimated_Value());
//                        estimateDate=pojofar.getIn_Estimated_PickDate();
//                        textViewLotNo.setText(lotno);
//                        textViewFarmerName.setText(farmerName);
//                        textViewMember.setText(memberType);
//                        textViewFarmerCode.setText(farmerCode);
//                        textViewItemName.setText(itemName);
//                        edt_price.setText(price);
//                        encodedImage="1";
//                        capturebill.setEnabled(false);
//                        //textViewQty.setText(quantity);
//                        textViewQty2.setText(quantity);
//                        numtest = Double.parseDouble(quantity);
//                        textViewValue.setText("" + new DecimalFormat("##.##").format(Double.parseDouble(calculatevalue)));
//                        // textViewValue.setText(calculatevalue);
//                        if(estimateDate!=null && !estimateDate.isEmpty()){
//                            if(isNetwork==true){
//                                textViewPickUpdate.setText(estimateDate);
//                            }else {
//                                textViewPickUpdate.setText(estimateDate);
//                            }
//
//                        }else {
//                            textViewPickUpdate.setText("");
//                        }
//                        linear_advance.setVisibility(View.VISIBLE);
//                        viewqp.setVisibility(View.VISIBLE);
//                        viewoc.setVisibility(View.VISIBLE);
//                        imageViewEditItem.setEnabled(false);
//                        edt_price.setEnabled(false);
//                        if(isNetwork) {
//                            callSingleActualProcurementDetails(pojofar.getIn_Estm_rowid(), pojofar.getIn_LotNo());
//                        }
//                        else
//                        {
//                            List<SingleActualHeaderDao> singleActualHeaderDaoList=db.getOfflineWareHouseSingleHeaderListDetailsRelatedLotNO(lotno);
//                            for (int i=0;i<singleActualHeaderDaoList.size();i++){
//                                singleActualHeaderDao=singleActualHeaderDaoList.get(i);
//                            }
//                            qtyDetailDaoList=db.getOfflineWareHouseQtyDetailsList(lotno);
//                            otherDetailDaoList=db.getOfflineWareHouseOtherDetails(lotno);
//                            siNoDetailDaoList=db.getOfflineWareHouseSiNoDetailsList(lotno);
//
//                            setActualValuesoffline();
//                        }
//                        //  textViewLotNo.setEnabled(false);
//                        // textViewFarmerName.setEnabled(false);
//                        //textViewFarmerCode.setEnabled(false);
//                        textViewMember.setEnabled(false);
//                        textViewItemName.setEnabled(false);
//                        textViewQty.setEnabled(false);
//                        textViewQty2.setEnabled(false);
//                        edt_price.setEnabled(false);
//                        textViewValue.setEnabled(false);
//                        textViewNoofBag.setEnabled(false);
//                        textViewSINo.setEnabled(false);
//                        textViewQualityPara.setEnabled(false);
//                        textViewOtherCost.setEnabled(false);
//                        linearLotno.setEnabled(false);
//                        linearQualityPara.setEnabled(false);
//                        edt_remarks.setEnabled(false);
//                        linearSino.setEnabled(false);
//                        linearOthercost.setEnabled(false);
//                        textViewincrease.setEnabled(false);
//                        textViewDecrease.setEnabled(false);
//                        qcp.setEnabled(false);
//                        vno.setEnabled(false);
//                        spinner.setEnabled(false);
//                        spinner2.setEnabled(false);
//                    }


                    dialog.dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView txtlotNo, txtFName, txtFCode, txtMember, txtICode, txtIName, txtQty, txtPrice, txtStatus,txtuom;
            LinearLayout llist;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtlotNo = itemView.findViewById(R.id.txt_ln);
                txtFName = itemView.findViewById(R.id.txt_fn);
                txtFCode = itemView.findViewById(R.id.txt_fc);
                txtMember = itemView.findViewById(R.id.txt_mt);
                txtICode = itemView.findViewById(R.id.txt_ic);
                txtIName = itemView.findViewById(R.id.txt_in);
                txtuom = itemView.findViewById(R.id.txt_inn);
                txtQty = itemView.findViewById(R.id.txt_qty);
                txtPrice = itemView.findViewById(R.id.txt_price);
                txtStatus = itemView.findViewById(R.id.txt_status);
                llist = itemView.findViewById(R.id.llist);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SL_NO_VALUE) {
            if (data != null) {
                if (!edtActValue.equalsIgnoreCase("editactual")) {
                    postSiNoDetailDaoList = data.getParcelableArrayListExtra("SERIAL_LIST");
                } else {
                    List<PostSiNoDetailDao> temppostSiNoDetailDaoList = data.getParcelableArrayListExtra("SERIAL_LIST");
                    postSiNoDetailDaoList.clear();
                    for (int i = 0; i < temppostSiNoDetailDaoList.size(); i++) {
                        postSiNoDetailDaoList.add(new PostSiNoDetailDao(temppostSiNoDetailDaoList.get(i).getIn_slno_row_id(), temppostSiNoDetailDaoList.get(i).getIn_slno(), temppostSiNoDetailDaoList.get(i).getIn_temp1(), temppostSiNoDetailDaoList.get(i).getIn_temp2(), "I"));

                    }

                }
                //  postSiNoDetailDaoList = data.getParcelableArrayListExtra("SERIAL_LIST");
                if (postSiNoDetailDaoList != null && postSiNoDetailDaoList.size() > 0) {
                    List<String> siname = new ArrayList<>();

                    for (int i = 0; i < postSiNoDetailDaoList.size(); i++) {
                        siname.add(postSiNoDetailDaoList.get(i).getIn_slno());
                    }
                    String s = TextUtils.join(", ", siname);
                    String count = String.valueOf(siname.size());
                    textViewSINo.setText(s);
                    textViewNoofBag.setText(count);


                    Log.v(MyConstants.TAG, postSiNoDetailDaoList.toString());
                }
            }


        }
        if (requestCode == EST_ACT_VALUE) {
            if (data != null) {
                actProListDaoEdit = data.getParcelableExtra("EDIT_ACTUAL_VALUE");
                edtActValue = data.getStringExtra("EDIT_ACT_VALUE");
                if (actProListDaoEdit != null) {
                    actualListDaoList = db.getOfflineApproveListDetailsRelatedLotno(actProListDaoEdit.getOut_lotno());
                    Log.v(MyConstants.TAG, actualListDaoList.toString());
                    List<SingleActualHeaderDao> singleActualHeaderDaoList = db.getOfflineAppSingleHeaderListDetailsRelatedLotNO(actProListDaoEdit.getOut_lotno());
                    for (int i = 0; i < singleActualHeaderDaoList.size(); i++) {
                        singleActualHeaderDao = singleActualHeaderDaoList.get(i);
                    }
                    qtyDetailDaoList = db.getOfflineApproveQtyDetailsList(actProListDaoEdit.getOut_lotno());
                    otherDetailDaoList = db.getOfflineApproveOtherDetails(actProListDaoEdit.getOut_lotno());
                    siNoDetailDaoList = db.getOfflineApproveSiNoDetailsList(actProListDaoEdit.getOut_lotno());
                    String date = null;
                    if(actualListDaoList.get(0).getOut_actual_date()!=null){
                        date=actualListDaoList.get(0).getOut_actual_date();
                    }
                    // setActualValues(actualListDaoList.get(0).getOut_actual_date());
                    setActualValues(date);

                    if(edtActValue!=null){
                        if(edtActValue.equalsIgnoreCase("editactual")){
                            linearLotno.setEnabled(false);
                        }else {
                            linearLotno.setEnabled(true);
                        }


                    }


                }
            }


        }

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

                        // vcap.setImageBitmap(bitmap);
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        ui = result.getUri().toString();

                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                        Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());



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

    private void setActualValues(String pickupdate) {
        modeflag="U";

        Log.e("FLAG",""+modeflag);
        lotno=singleActualHeaderDao.getIoU_lotno();
        farmerCode = singleActualHeaderDao.getIn_farmer_code();
        farmerName = singleActualHeaderDao.getIn_farmer_name();
        memberType = singleActualHeaderDao.getIn_member_type();
        itemCode = singleActualHeaderDao.getIn_item_code();
        itemName = singleActualHeaderDao.getIn_item_name();


        textViewLotNo.setText(singleActualHeaderDao.getIoU_lotno());
        textViewFarmerName.setText(singleActualHeaderDao.getIn_farmer_name());
        textViewFarmerCode.setText(singleActualHeaderDao.getIn_farmer_code());
        textViewMember.setText(singleActualHeaderDao.getIn_member_type());
        textViewItemName.setText(singleActualHeaderDao.getIn_item_name());
        textViewQty2.setText("" + singleActualHeaderDao.getIn_actual_qty());
        edt_price.setText("" + singleActualHeaderDao.getIn_actual_price());
        textViewValue.setText("" + new DecimalFormat("##.##").format(singleActualHeaderDao.getIn_actual_value()));
        textViewTotalCost.setText("" + new DecimalFormat("##.##").format(singleActualHeaderDao.getIn_actual_value()));
        textViewNoofBag.setText("" + singleActualHeaderDao.getIn_no_of_bags());
        spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(singleActualHeaderDao.getIn_vehicle_type()));
        spinner2.setSelection(((ArrayAdapter<String>)spinner2.getAdapter()).getPosition(singleActualHeaderDao.getIn_destination()));
        // singleActualHeaderDao.getIn_row_timestamp()
        textViewPickUpdate.setText(pickupdate);
        qcp.setText(singleActualHeaderDao.getIn_qcperson_name());
        vno.setText(singleActualHeaderDao.getIn_vehicle_no());
        lrpname.setText(singleActualHeaderDao.getIn_LRP_Name());
        lrpno.setText(singleActualHeaderDao.getIn_LRP_Mobile_no());
        final SQLiteDatabase dbs = db.getWritableDatabase();
        try {


            Cursor c = dbs.rawQuery("SELECT * FROM AppSingleHeader WHERE AppSingleHeaderKeyId = '" + singleActualHeaderDao.getId() + "'", null);


            if (c.moveToNext()) {
                encodedImage = c.getString(21);
                byte[] imageAsBytes = Base64.decode(c.getString(21).getBytes(), Base64.DEFAULT);


                capturebill.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
            }
        }
        catch (Exception e)
        {
            encodedImage="0";
        }
        numtest = singleActualHeaderDao.getIn_actual_qty();
        textViewSINo.setEnabled(true);
        edt_remarks.setEnabled(true);
        textViewQualityPara.setEnabled(true);
        textViewOtherCost.setEnabled(true);
        linearQualityPara.setEnabled(true);
        linearSino.setEnabled(true);
        linearOthercost.setEnabled(true);

        try {


            Cursor cursoruom = dbs.query("ProductMasterAllProduct", new String[]{"out_uomtype_code", "out_crop_variety"
                    }, "PmapOutProductCode" + "=?",
                    new String[]{singleActualHeaderDao.getIn_item_code()}, null, null, null, null);

            if (cursoruom.moveToFirst()) {
                txt_uom.setText(cursoruom.getString(0));
                txtquantity.setText("Quantity Procure (in "+cursoruom.getString(0)+")");
                txt_lbl_ttlamt.setText("Total Amount (for "+cursoruom.getString(0)+")");
                txt_lbl_purchaserate.setText("Purchase Rate (for "+cursoruom.getString(0)+")");
                Pojokyc.productuom = cursoruom.getString(0);
                txt_var.setText(cursoruom.getString(1));
            }
        }
        catch(Exception e)
        {

        }
        if (singleActualHeaderDao.getIn_advance_amt() > 0) {
            edt_advance.setText("" + singleActualHeaderDao.getIn_advance_amt());
        } else {
            edt_advance.setText("");
        }

        edt_remarks.setText(singleActualHeaderDao.getIn_actual_remarks());

        if (siNoDetailDaoList != null && siNoDetailDaoList.size() > 0) {
            List<String> siname = new ArrayList<>();
            postSiNoDetailDaoList.clear();

            for (int i = 0; i < siNoDetailDaoList.size(); i++) {
                siname.add(siNoDetailDaoList.get(i).getIn_slno());
                if (siNoDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("S")) {
                    //    bulkSInoDetailsDaoList.set(i,new BulkSInoDetailsDao(bulkSInoDetailsDaoList.get(i).getIn_slno_row_id(),bulkSInoDetailsDaoList.get(i).getIn_slno(),bulkSInoDetailsDaoList.get(i).getIn_temp1(),bulkSInoDetailsDaoList.get(i).getIn_temp2(),"U"));
                    postSiNoDetailDaoList.add(new PostSiNoDetailDao(siNoDetailDaoList.get(i).getIn_slno_row_id(), siNoDetailDaoList.get(i).getIn_slno(), siNoDetailDaoList.get(i).getIn_temp1(), siNoDetailDaoList.get(i).getIn_temp2(), "I"));
                } else if (siNoDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("I")) {
                    //    bulkSInoDetailsDaoList.set(i,new BulkSInoDetailsDao(bulkSInoDetailsDaoList.get(i).getIn_slno_row_id(),bulkSInoDetailsDaoList.get(i).getIn_slno(),bulkSInoDetailsDaoList.get(i).getIn_temp1(),bulkSInoDetailsDaoList.get(i).getIn_temp2(),"U"));
                    postSiNoDetailDaoList.add(new PostSiNoDetailDao(siNoDetailDaoList.get(i).getIn_slno_row_id(), siNoDetailDaoList.get(i).getIn_slno(), siNoDetailDaoList.get(i).getIn_temp1(), siNoDetailDaoList.get(i).getIn_temp2(), "I"));
                }else if (siNoDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("U")) {
                    //    bulkSInoDetailsDaoList.set(i,new BulkSInoDetailsDao(bulkSInoDetailsDaoList.get(i).getIn_slno_row_id(),bulkSInoDetailsDaoList.get(i).getIn_slno(),bulkSInoDetailsDaoList.get(i).getIn_temp1(),bulkSInoDetailsDaoList.get(i).getIn_temp2(),"U"));
                    postSiNoDetailDaoList.add(new PostSiNoDetailDao(siNoDetailDaoList.get(i).getIn_slno_row_id(), siNoDetailDaoList.get(i).getIn_slno(), siNoDetailDaoList.get(i).getIn_temp1(), siNoDetailDaoList.get(i).getIn_temp2(), "I"));
                }
            }
            String s = TextUtils.join(", ", siname);
            String count = String.valueOf(siname.size());
            textViewSINo.setText(s);
        }
        if (qtyDetailDaoList != null && qtyDetailDaoList.size() > 0) {
            List<String> qtyname = new ArrayList<>();
            postQtyDetailDaoList.clear();

            for (int i = 0; i < qtyDetailDaoList.size(); i++) {
                qtyname.add(qtyDetailDaoList.get(i).getIn_qty_name()+"-"+qtyDetailDaoList.get(i).getIn_actual_value()+" \n");
                if (qtyDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("S")) {
                    // bulkQtyDetailDaoList.set(i,new BulkQtyDetailDao(bulkQtyDetailDaoList.get(i).getIn_qty_row_id(),bulkQtyDetailDaoList.get(i).getIn_qty_code(),bulkQtyDetailDaoList.get(i).getIn_qty_name(),bulkQtyDetailDaoList.get(i).getIn_actual_value(),bulkQtyDetailDaoList.get(i).getIn_actual_value(),"U"));
                    postQtyDetailDaoList.add(new PostQtyDetailDao(qtyDetailDaoList.get(i).getIn_qty_row_id(), qtyDetailDaoList.get(i).getIn_qty_code(), qtyDetailDaoList.get(i).getIn_qty_name(), qtyDetailDaoList.get(i).getIn_actual_value(), qtyDetailDaoList.get(i).getIn_actual_value(), "I"));
                }else  if (qtyDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("I")) {
                    // bulkQtyDetailDaoList.set(i,new BulkQtyDetailDao(bulkQtyDetailDaoList.get(i).getIn_qty_row_id(),bulkQtyDetailDaoList.get(i).getIn_qty_code(),bulkQtyDetailDaoList.get(i).getIn_qty_name(),bulkQtyDetailDaoList.get(i).getIn_actual_value(),bulkQtyDetailDaoList.get(i).getIn_actual_value(),"U"));
                    postQtyDetailDaoList.add(new PostQtyDetailDao(qtyDetailDaoList.get(i).getIn_qty_row_id(), qtyDetailDaoList.get(i).getIn_qty_code(), qtyDetailDaoList.get(i).getIn_qty_name(), qtyDetailDaoList.get(i).getIn_actual_value(), qtyDetailDaoList.get(i).getIn_actual_value(), "I"));
                }else  if (qtyDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("U")) {
                    // bulkQtyDetailDaoList.set(i,new BulkQtyDetailDao(bulkQtyDetailDaoList.get(i).getIn_qty_row_id(),bulkQtyDetailDaoList.get(i).getIn_qty_code(),bulkQtyDetailDaoList.get(i).getIn_qty_name(),bulkQtyDetailDaoList.get(i).getIn_actual_value(),bulkQtyDetailDaoList.get(i).getIn_actual_value(),"U"));
                    postQtyDetailDaoList.add(new PostQtyDetailDao(qtyDetailDaoList.get(i).getIn_qty_row_id(), qtyDetailDaoList.get(i).getIn_qty_code(), qtyDetailDaoList.get(i).getIn_qty_name(), qtyDetailDaoList.get(i).getIn_actual_value(), qtyDetailDaoList.get(i).getIn_actual_value(), "I"));
                }
            }
            String s = TextUtils.join("", qtyname);
            textViewQualityPara.setText(s);
        }
        if (otherDetailDaoList != null && otherDetailDaoList.size() > 0) {
            List<String> qtyname = new ArrayList<>();
            List<Integer> valueList = new ArrayList<>();
            postOtherDetailDaoList.clear();


            for (int i = 0; i < otherDetailDaoList.size(); i++) {
                Log.e("Flag",""+otherDetailDaoList.get(i).getIn_mode_flag());

                if (otherDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("S")) {
                    /*bulkOtherDetailDaoList.set(i,new BulkOtherDetailDao(bulkOtherDetailDaoList.get(i).getIn_otherdtl_row_id(),bulkOtherDetailDaoList.get(i).getIn_packaging_cost(),
                            bulkOtherDetailDaoList.get(i).getIn_transporting_cost(),bulkOtherDetailDaoList.get(i).getIn_unpacking_cost(),
                            bulkOtherDetailDaoList.get(i).getIn_local_packaging_cost(),bulkOtherDetailDaoList.get(i).getIn_local_transporting_cost(),
                            bulkOtherDetailDaoList.get(i).getIn_temp_cost(),bulkOtherDetailDaoList.get(i).getIn_temp_cost1(),bulkOtherDetailDaoList.get(i).getIn_temp_cost2(),"U"));*/
                    postOtherDetailDaoList.add(new PostOtherDetailDao(otherDetailDaoList.get(i).getIn_Other_row_id(), otherDetailDaoList.get(i).getIn_packaging_cost(),
                            otherDetailDaoList.get(i).getIn_transporting_cost(), otherDetailDaoList.get(i).getIn_unpacking_cost(),
                            otherDetailDaoList.get(i).getIn_local_packaging_cost(), otherDetailDaoList.get(i).getIn_local_transporting_cost(),
                            otherDetailDaoList.get(i).getIn_temp_cost(), otherDetailDaoList.get(i).getIn_temp_cost1(), otherDetailDaoList.get(i).getIn_temp_cost2(), "I"));
                }else if (otherDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("I")) {
                    /*bulkOtherDetailDaoList.set(i,new BulkOtherDetailDao(bulkOtherDetailDaoList.get(i).getIn_otherdtl_row_id(),bulkOtherDetailDaoList.get(i).getIn_packaging_cost(),
                            bulkOtherDetailDaoList.get(i).getIn_transporting_cost(),bulkOtherDetailDaoList.get(i).getIn_unpacking_cost(),
                            bulkOtherDetailDaoList.get(i).getIn_local_packaging_cost(),bulkOtherDetailDaoList.get(i).getIn_local_transporting_cost(),
                            bulkOtherDetailDaoList.get(i).getIn_temp_cost(),bulkOtherDetailDaoList.get(i).getIn_temp_cost1(),bulkOtherDetailDaoList.get(i).getIn_temp_cost2(),"U"));*/
                    postOtherDetailDaoList.add(new PostOtherDetailDao(otherDetailDaoList.get(i).getIn_Other_row_id(), otherDetailDaoList.get(i).getIn_packaging_cost(),
                            otherDetailDaoList.get(i).getIn_transporting_cost(), otherDetailDaoList.get(i).getIn_unpacking_cost(),
                            otherDetailDaoList.get(i).getIn_local_packaging_cost(), otherDetailDaoList.get(i).getIn_local_transporting_cost(),
                            otherDetailDaoList.get(i).getIn_temp_cost(), otherDetailDaoList.get(i).getIn_temp_cost1(), otherDetailDaoList.get(i).getIn_temp_cost2(), "I"));
                }else if (otherDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("U")) {
                    /*bulkOtherDetailDaoList.set(i,new BulkOtherDetailDao(bulkOtherDetailDaoList.get(i).getIn_otherdtl_row_id(),bulkOtherDetailDaoList.get(i).getIn_packaging_cost(),
                            bulkOtherDetailDaoList.get(i).getIn_transporting_cost(),bulkOtherDetailDaoList.get(i).getIn_unpacking_cost(),
                            bulkOtherDetailDaoList.get(i).getIn_local_packaging_cost(),bulkOtherDetailDaoList.get(i).getIn_local_transporting_cost(),
                            bulkOtherDetailDaoList.get(i).getIn_temp_cost(),bulkOtherDetailDaoList.get(i).getIn_temp_cost1(),bulkOtherDetailDaoList.get(i).getIn_temp_cost2(),"U"));*/
                    postOtherDetailDaoList.add(new PostOtherDetailDao(otherDetailDaoList.get(i).getIn_Other_row_id(), otherDetailDaoList.get(i).getIn_packaging_cost(),
                            otherDetailDaoList.get(i).getIn_transporting_cost(), otherDetailDaoList.get(i).getIn_unpacking_cost(),
                            otherDetailDaoList.get(i).getIn_local_packaging_cost(), otherDetailDaoList.get(i).getIn_local_transporting_cost(),
                            otherDetailDaoList.get(i).getIn_temp_cost(), otherDetailDaoList.get(i).getIn_temp_cost1(), otherDetailDaoList.get(i).getIn_temp_cost2(), "I"));
                }
                if (otherDetailDaoList.get(i).getIn_packaging_cost() > 0) {
                    qtyname.add("Packing Cost");
                    valueList.add(otherDetailDaoList.get(i).getIn_packaging_cost());
                }
                if (otherDetailDaoList.get(i).getIn_transporting_cost() > 0) {
                    qtyname.add("Transportation Cost");
                    valueList.add(otherDetailDaoList.get(i).getIn_transporting_cost());
                }
                if (otherDetailDaoList.get(i).getIn_unpacking_cost() > 0) {
                    qtyname.add("UnPacking Cost");
                    valueList.add(otherDetailDaoList.get(i).getIn_unpacking_cost());
                }
                if (otherDetailDaoList.get(i).getIn_local_packaging_cost() > 0) {
                    qtyname.add("Local Packing Cost");
                    valueList.add(otherDetailDaoList.get(i).getIn_local_packaging_cost());
                }
                if (otherDetailDaoList.get(i).getIn_local_transporting_cost() > 0) {
                    qtyname.add("Local Transportation Cost");
                    valueList.add(otherDetailDaoList.get(i).getIn_local_transporting_cost());
                }
            }
            String s= TextUtils.join(", ",qtyname);
            int totalPrice = 0;
            for(int i = 0 ; i < valueList.size(); i++) {
                totalPrice += valueList.get(i);
            }
            textViewOtherCost.setText(""+totalPrice);
            int total=grandTotal2(valueList);
            String ptf = String.valueOf(singleActualHeaderDao.getIn_advance_amt());
            double totalCost;
            if(ptf.equalsIgnoreCase(""))
            {
                totalCost=singleActualHeaderDao.getIn_actual_value()-total;
            }
            else

            {
                totalCost=(singleActualHeaderDao.getIn_actual_value()-total)-singleActualHeaderDao.getIn_advance_amt();
            }



            // textViewTotalCost.setText(""+totalCost);
            //   textViewTotalCost.setText(""+total);

        }
    }

    private int grandTotalInt(List<Integer> items) {

        int totalPrice = 0;
        for (int i = 0; i < items.size(); i++) {
            totalPrice += items.get(i);
        }

        return totalPrice;
    }

    private int grandTotal(List<TempOtherDetailDao> items){

        int totalPrice = 0;
        for(int i = 0 ; i < items.size(); i++) {
            totalPrice += Integer.parseInt(items.get(i).getAmount());
        }

        return totalPrice;
    }
    private int grandTotal2(List<Integer> items){

        int totalPrice = 0;
        for(int i = 0 ; i < items.size(); i++) {
            totalPrice += items.get(i);
        }

        return totalPrice;
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
                    itemCode = pojofar.getOut_product_code();
                    itemName = pojofar.getOut_product_name();
                    //   aggCode = pojofar.getOut_agg_code();
                    txt_uom.setText(pojofar.getOut_uomtype_code());
                    txtquantity.setText("Quantity Procure (in "+pojofar.getOut_uomtype_code()+")");
                    txt_lbl_ttlamt.setText("Total Amount (for "+pojofar.getOut_uomtype_code()+")");
                    txt_lbl_purchaserate.setText("Purchase Rate (for "+pojofar.getOut_uomtype_code()+")");
                    Pojokyc.productuom = pojofar.getOut_uomtype_code();
                    txt_var.setText(pojofar.getOut_crop_variety());
                    textViewItemName.setText(itemName);


                    dialog.dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textViewProductName, textViewProductCode,txt3,txt4,txt5;
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
    private void callSingleActualProcurementDetails(int id, String lotno) {
        progressLayout.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);




        SingleActProcContextDao singleActProcContextDao = new SingleActProcContextDao();
        singleActProcContextDao.setOrgnId(orgnCode);
        // singleActProcContextDao.setLocnId(locnId);
        singleActProcContextDao.setLocnId(ApiUtils.instance);
        singleActProcContextDao.setUserId(userId);
        singleActProcContextDao.setLocaleId(localeId);
        singleActProcContextDao.setIOU_act_rowid(id);
        singleActProcContextDao.setIOU_lotno(lotno);
        singleActProcContextDao.setIOU_agg_code(orgnCode);

        mAPIService.postSingleActualProcurementDetails(singleActProcContextDao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SingleActualProcurementDao>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SingleActualProcurementDao singleActualProcurementDao) {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressLayout.setVisibility(View.GONE);

                        singleActualHeaderDao=singleActualProcurementDao.getSingleActProcContextDao().getSingleActualHeaderDao();
                        qtyDetailDaoList=singleActualProcurementDao.getSingleActProcContextDao().getQtyDetailDaoList();
                        otherDetailDaoList=singleActualProcurementDao.getSingleActProcContextDao().getOtherDetailDaoList();
                        siNoDetailDaoList=singleActualProcurementDao.getSingleActProcContextDao().getSiNoDetailDaoList();
                        setActualValues();
                    }
                });
    }

    private void setActualValues() {
        modeflag="U";
        textViewLotNo.setText(singleActualHeaderDao.getIoU_lotno());
        textViewFarmerName.setText(singleActualHeaderDao.getIn_farmer_name());
        textViewFarmerCode.setText(singleActualHeaderDao.getIn_farmer_code());
        textViewMember.setText(singleActualHeaderDao.getIn_member_type());
        textViewItemName.setText(singleActualHeaderDao.getIn_item_name());

        textViewQty2.setText(""+singleActualHeaderDao.getIn_actual_qty());
        numtest = Double.parseDouble(String.valueOf(singleActualHeaderDao.getIn_actual_qty()));
        //textViewQty.setText(""+singleActualHeaderDao.getIn_actual_qty());
        edt_price.setText(""+singleActualHeaderDao.getIn_actual_price());
        textViewValue.setText(""+new DecimalFormat("##.##").format(singleActualHeaderDao.getIn_actual_value()));
        textViewTotalCost.setText(""+new DecimalFormat("##.##").format(singleActualHeaderDao.getIn_actual_value()));
        textViewNoofBag.setText(""+singleActualHeaderDao.getIn_no_of_bags());

        qcp.setText(singleActualHeaderDao.getIn_qcperson_name());
        vno.setText(singleActualHeaderDao.getIn_vehicle_no());
        lrpname.setText(singleActualHeaderDao.getIn_LRP_Name());
        lrpno.setText(singleActualHeaderDao.getIn_LRP_Mobile_no());
        spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(singleActualHeaderDao.getIn_vehicle_type()));
        spinner2.setSelection(((ArrayAdapter<String>)spinner2.getAdapter()).getPosition(singleActualHeaderDao.getIn_destination()));
        final SQLiteDatabase dbs = db.getWritableDatabase();
        try {


            Cursor c = dbs.rawQuery("SELECT * FROM AppSingleHeader WHERE AppSingleHeaderKeyId = '" + singleActualHeaderDao.getId() + "'", null);


            if (c.moveToNext()) {
                encodedImage = c.getString(21);
                byte[] imageAsBytes = Base64.decode(c.getString(21).getBytes(), Base64.DEFAULT);


                capturebill.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
            }
        }
        catch (Exception e)
        {
            encodedImage="0";
        }
        try {

            Cursor cursoruom = dbs.query("ProductMasterAllProduct", new String[]{"out_uomtype_code", "out_crop_variety"
                    }, "PmapOutProductCode" + "=?",
                    new String[]{singleActualHeaderDao.getIn_item_code()}, null, null, null, null);

            if (cursoruom.moveToFirst()) {
                txt_uom.setText(cursoruom.getString(0));
                txtquantity.setText("Quantity Procure (in "+cursoruom.getString(0)+")");
                txt_lbl_ttlamt.setText("Total Amount (for "+cursoruom.getString(0)+")");
                txt_lbl_purchaserate.setText("Purchase Rate (for "+cursoruom.getString(0)+")");
                Pojokyc.productuom = cursoruom.getString(0);
                txt_var.setText(cursoruom.getString(1));
            }
        }
        catch(Exception e)
        {

        }
        String[] dt = singleActualHeaderDao.getIn_row_timestamp().split("-");
        //textViewPickUpdate.setText(dt[2]+"-"+dt[1]+"-"+dt[0]);
        edt_advance.setText(""+singleActualHeaderDao.getIn_advance_amt());
        edt_remarks.setText(singleActualHeaderDao.getIn_approved_remarks());

        if(siNoDetailDaoList!=null && siNoDetailDaoList.size()>0){
            List<String> siname=new ArrayList<>();

            for(int i=0;i<siNoDetailDaoList.size();i++){
                siname.add(siNoDetailDaoList.get(i).getIn_slno());
                if(siNoDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("S")){
                    //    bulkSInoDetailsDaoList.set(i,new BulkSInoDetailsDao(bulkSInoDetailsDaoList.get(i).getIn_slno_row_id(),bulkSInoDetailsDaoList.get(i).getIn_slno(),bulkSInoDetailsDaoList.get(i).getIn_temp1(),bulkSInoDetailsDaoList.get(i).getIn_temp2(),"U"));
                    postSiNoDetailDaoList.add(new PostSiNoDetailDao(siNoDetailDaoList.get(i).getIn_slno_row_id(),siNoDetailDaoList.get(i).getIn_slno(),siNoDetailDaoList.get(i).getIn_temp1(),siNoDetailDaoList.get(i).getIn_temp2(),"I"));
                }
            }
            String s= TextUtils.join(", ",siname);
            String count= String.valueOf(siname.size());
            textViewSINo.setText(s);
        }
        if(qtyDetailDaoList!=null && qtyDetailDaoList.size()>0){
            List<String> qtyname=new ArrayList<>();
            List<String> qtyvalue=new ArrayList<>();

            for(int i=0;i<qtyDetailDaoList.size();i++){
                qtyname.add(qtyDetailDaoList.get(i).getIn_qty_name()+"-"+qtyDetailDaoList.get(i).getIn_actual_value()+" \n");

                if(qtyDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("S")){
                    // bulkQtyDetailDaoList.set(i,new BulkQtyDetailDao(bulkQtyDetailDaoList.get(i).getIn_qty_row_id(),bulkQtyDetailDaoList.get(i).getIn_qty_code(),bulkQtyDetailDaoList.get(i).getIn_qty_name(),bulkQtyDetailDaoList.get(i).getIn_actual_value(),bulkQtyDetailDaoList.get(i).getIn_actual_value(),"U"));
                    postQtyDetailDaoList.add(new PostQtyDetailDao(qtyDetailDaoList.get(i).getIn_qty_row_id(),qtyDetailDaoList.get(i).getIn_qty_code(),qtyDetailDaoList.get(i).getIn_qty_name(),qtyDetailDaoList.get(i).getIn_actual_value(),qtyDetailDaoList.get(i).getIn_actual_value(),"I"));
                }
            }
            String s= TextUtils.join("",qtyname);
            textViewQualityPara.setText(s);
        }
        if(otherDetailDaoList!=null && otherDetailDaoList.size()>0){
            List<String> qtyname=new ArrayList<>();
            List<Integer> valueList=new ArrayList<>();

            for(int i=0;i<otherDetailDaoList.size();i++){
                if(otherDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("S")){
                    /*bulkOtherDetailDaoList.set(i,new BulkOtherDetailDao(bulkOtherDetailDaoList.get(i).getIn_otherdtl_row_id(),bulkOtherDetailDaoList.get(i).getIn_packaging_cost(),
                            bulkOtherDetailDaoList.get(i).getIn_transporting_cost(),bulkOtherDetailDaoList.get(i).getIn_unpacking_cost(),
                            bulkOtherDetailDaoList.get(i).getIn_local_packaging_cost(),bulkOtherDetailDaoList.get(i).getIn_local_transporting_cost(),
                            bulkOtherDetailDaoList.get(i).getIn_temp_cost(),bulkOtherDetailDaoList.get(i).getIn_temp_cost1(),bulkOtherDetailDaoList.get(i).getIn_temp_cost2(),"U"));*/
                    postOtherDetailDaoList.add(new PostOtherDetailDao(otherDetailDaoList.get(i).getIn_Other_row_id(),otherDetailDaoList.get(i).getIn_packaging_cost(),
                            otherDetailDaoList.get(i).getIn_transporting_cost(),otherDetailDaoList.get(i).getIn_unpacking_cost(),
                            otherDetailDaoList.get(i).getIn_local_packaging_cost(),otherDetailDaoList.get(i).getIn_local_transporting_cost(),
                            otherDetailDaoList.get(i).getIn_temp_cost(),otherDetailDaoList.get(i).getIn_temp_cost1(),otherDetailDaoList.get(i).getIn_temp_cost2(),"I"));
                }
                if(otherDetailDaoList.get(i).getIn_packaging_cost()>0){
                    qtyname.add("Packing Cost");
                    valueList.add(otherDetailDaoList.get(i).getIn_packaging_cost());
                }
                if(otherDetailDaoList.get(i).getIn_transporting_cost()>0){
                    qtyname.add("Transportation Cost");
                    valueList.add(otherDetailDaoList.get(i).getIn_transporting_cost());
                }
                if(otherDetailDaoList.get(i).getIn_unpacking_cost()>0){
                    qtyname.add("UnPacking Cost");
                    valueList.add(otherDetailDaoList.get(i).getIn_unpacking_cost());
                }
                if(otherDetailDaoList.get(i).getIn_local_packaging_cost()>0){
                    qtyname.add("Local Packing Cost");
                    valueList.add(otherDetailDaoList.get(i).getIn_local_packaging_cost());
                }
                if(otherDetailDaoList.get(i).getIn_local_transporting_cost()>0){
                    qtyname.add("Local Transportation Cost");
                    valueList.add(otherDetailDaoList.get(i).getIn_local_transporting_cost());
                }
            }
            String s= TextUtils.join(", ",qtyname);
            int totalPrice = 0;
            for(int i = 0 ; i < valueList.size(); i++) {
                totalPrice += valueList.get(i);
            }
            textViewOtherCost.setText(""+totalPrice);
            int total=grandTotal2(valueList);
            String ptf = String.valueOf(singleActualHeaderDao.getIn_advance_amt());
            double totalCost;
            if(ptf.equalsIgnoreCase(""))
            {
                totalCost=singleActualHeaderDao.getIn_actual_value()-total;
            }
            else

            {
                totalCost=(singleActualHeaderDao.getIn_actual_value()-total)-singleActualHeaderDao.getIn_advance_amt();
            }



            // textViewTotalCost.setText(""+totalCost);
            //    textViewTotalCost.setText(""+total);

        }

    }
    private void setActualValuesoffline() {
        textViewLotNo.setText(singleActualHeaderDao.getIoU_lotno());
        textViewFarmerName.setText(singleActualHeaderDao.getIn_farmer_name());
        textViewFarmerCode.setText(singleActualHeaderDao.getIn_farmer_code());
        textViewMember.setText(singleActualHeaderDao.getIn_member_type());
        textViewItemName.setText(singleActualHeaderDao.getIn_item_name());

        textViewQty2.setText(""+singleActualHeaderDao.getIn_actual_qty());
        numtest = Double.parseDouble(String.valueOf(singleActualHeaderDao.getIn_actual_qty()));
        //textViewQty.setText(""+singleActualHeaderDao.getIn_actual_qty());
        edt_price.setText(""+singleActualHeaderDao.getIn_actual_price());
        textViewValue.setText(""+new DecimalFormat("##.##").format(singleActualHeaderDao.getIn_actual_value()));
        textViewTotalCost.setText(""+new DecimalFormat("##.##").format(singleActualHeaderDao.getIn_actual_value()));
        textViewNoofBag.setText(""+singleActualHeaderDao.getIn_no_of_bags());

        qcp.setText(singleActualHeaderDao.getIn_qcperson_name());
        vno.setText(singleActualHeaderDao.getIn_vehicle_no());
        lrpname.setText(singleActualHeaderDao.getIn_LRP_Name());
        lrpno.setText(singleActualHeaderDao.getIn_LRP_Mobile_no());
        spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(singleActualHeaderDao.getIn_vehicle_type()));
        spinner2.setSelection(((ArrayAdapter<String>)spinner2.getAdapter()).getPosition(singleActualHeaderDao.getIn_destination()));
        final SQLiteDatabase dbs = db.getWritableDatabase();
        try {


            Cursor c = dbs.rawQuery("SELECT * FROM AppSingleHeader WHERE AppSingleHeaderKeyId = '" + singleActualHeaderDao.getId() + "'", null);


            if (c.moveToNext()) {
                encodedImage = c.getString(21);
                byte[] imageAsBytes = Base64.decode(c.getString(21).getBytes(), Base64.DEFAULT);


                capturebill.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
            }
        }
        catch (Exception e)
        {
            encodedImage="0";
        }
        try {

            Cursor cursoruom = dbs.query("ProductMasterAllProduct", new String[]{"out_uomtype_code", "out_crop_variety"
                    }, "PmapOutProductCode" + "=?",
                    new String[]{singleActualHeaderDao.getIn_item_code()}, null, null, null, null);

            if (cursoruom.moveToFirst()) {
                txt_uom.setText(cursoruom.getString(0));
                txtquantity.setText("Quantity Procure (in "+cursoruom.getString(0)+")");
                txt_lbl_ttlamt.setText("Total Amount (for "+cursoruom.getString(0)+")");
                txt_lbl_purchaserate.setText("Purchase Rate (for "+cursoruom.getString(0)+")");
                Pojokyc.productuom = cursoruom.getString(0);
                txt_var.setText(cursoruom.getString(1));
            }
        }
        catch(Exception e)
        {

        }
        String[] dt = singleActualHeaderDao.getIn_row_timestamp().split("-");
        //textViewPickUpdate.setText(dt[2]+"-"+dt[1]+"-"+dt[0]);
        edt_advance.setText(""+singleActualHeaderDao.getIn_advance_amt());
        edt_remarks.setText(singleActualHeaderDao.getIn_approved_remarks());
        edt_advance.setText(""+singleActualHeaderDao.getIn_advance_amt());
        // edt_remarks.setText(singleActualHeaderDao.getIn_approved_remarks());
        textViewSINo.setEnabled(true);
        edt_remarks.setEnabled(true);
        linearQualityPara.setEnabled(true);
        linearSino.setEnabled(true);
        linearOthercost.setEnabled(true);
        if(siNoDetailDaoList!=null && siNoDetailDaoList.size()>0){
            List<String> siname=new ArrayList<>();

            for(int i=0;i<siNoDetailDaoList.size();i++){
                siname.add(siNoDetailDaoList.get(i).getIn_slno());
                if(siNoDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("S")){
                    //    bulkSInoDetailsDaoList.set(i,new BulkSInoDetailsDao(bulkSInoDetailsDaoList.get(i).getIn_slno_row_id(),bulkSInoDetailsDaoList.get(i).getIn_slno(),bulkSInoDetailsDaoList.get(i).getIn_temp1(),bulkSInoDetailsDaoList.get(i).getIn_temp2(),"U"));
                    postSiNoDetailDaoList.add(new PostSiNoDetailDao(siNoDetailDaoList.get(i).getIn_slno_row_id(),siNoDetailDaoList.get(i).getIn_slno(),siNoDetailDaoList.get(i).getIn_temp1(),siNoDetailDaoList.get(i).getIn_temp2(),"I"));
                }
            }
            String s= TextUtils.join(", ",siname);
            String count= String.valueOf(siname.size());
            textViewSINo.setText(s);
            // textViewNoofBag.setText(""+count);
        }
        if(qtyDetailDaoList!=null && qtyDetailDaoList.size()>0){
            List<String> qtyname=new ArrayList<>();

            for(int i=0;i<qtyDetailDaoList.size();i++){
                qtyname.add(qtyDetailDaoList.get(i).getIn_qty_name()+" \n");
                if(qtyDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("S")){
                    // bulkQtyDetailDaoList.set(i,new BulkQtyDetailDao(bulkQtyDetailDaoList.get(i).getIn_qty_row_id(),bulkQtyDetailDaoList.get(i).getIn_qty_code(),bulkQtyDetailDaoList.get(i).getIn_qty_name(),bulkQtyDetailDaoList.get(i).getIn_actual_value(),bulkQtyDetailDaoList.get(i).getIn_actual_value(),"U"));
                    postQtyDetailDaoList.add(new PostQtyDetailDao(qtyDetailDaoList.get(i).getIn_qty_row_id(),qtyDetailDaoList.get(i).getIn_qty_code(),qtyDetailDaoList.get(i).getIn_qty_name(),qtyDetailDaoList.get(i).getIn_actual_value(),qtyDetailDaoList.get(i).getIn_actual_value(),"I"));
                }
            }
            String s= TextUtils.join("",qtyname);
            textViewQualityPara.setText(s);
        }
        if(otherDetailDaoList!=null && otherDetailDaoList.size()>0){
            List<String> qtyname=new ArrayList<>();
            List<Integer> valueList=new ArrayList<>();

            for(int i=0;i<otherDetailDaoList.size();i++){
                if(otherDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("S")){
                    /*bulkOtherDetailDaoList.set(i,new BulkOtherDetailDao(bulkOtherDetailDaoList.get(i).getIn_otherdtl_row_id(),bulkOtherDetailDaoList.get(i).getIn_packaging_cost(),
                            bulkOtherDetailDaoList.get(i).getIn_transporting_cost(),bulkOtherDetailDaoList.get(i).getIn_unpacking_cost(),
                            bulkOtherDetailDaoList.get(i).getIn_local_packaging_cost(),bulkOtherDetailDaoList.get(i).getIn_local_transporting_cost(),
                            bulkOtherDetailDaoList.get(i).getIn_temp_cost(),bulkOtherDetailDaoList.get(i).getIn_temp_cost1(),bulkOtherDetailDaoList.get(i).getIn_temp_cost2(),"U"));*/
                    postOtherDetailDaoList.add(new PostOtherDetailDao(otherDetailDaoList.get(i).getIn_Other_row_id(),otherDetailDaoList.get(i).getIn_packaging_cost(),
                            otherDetailDaoList.get(i).getIn_transporting_cost(),otherDetailDaoList.get(i).getIn_unpacking_cost(),
                            otherDetailDaoList.get(i).getIn_local_packaging_cost(),otherDetailDaoList.get(i).getIn_local_transporting_cost(),
                            otherDetailDaoList.get(i).getIn_temp_cost(),otherDetailDaoList.get(i).getIn_temp_cost1(),otherDetailDaoList.get(i).getIn_temp_cost2(),"I"));
                }
                if(otherDetailDaoList.get(i).getIn_packaging_cost()>0){
                    qtyname.add("Packing Cost");
                    valueList.add(otherDetailDaoList.get(i).getIn_packaging_cost());
                }
                if(otherDetailDaoList.get(i).getIn_transporting_cost()>0){
                    qtyname.add("Transportation Cost");
                    valueList.add(otherDetailDaoList.get(i).getIn_transporting_cost());
                }
                if(otherDetailDaoList.get(i).getIn_unpacking_cost()>0){
                    qtyname.add("UnPacking Cost");
                    valueList.add(otherDetailDaoList.get(i).getIn_unpacking_cost());
                }
                if(otherDetailDaoList.get(i).getIn_local_packaging_cost()>0){
                    qtyname.add("Local Packing Cost");
                    valueList.add(otherDetailDaoList.get(i).getIn_local_packaging_cost());
                }
                if(otherDetailDaoList.get(i).getIn_local_transporting_cost()>0){
                    qtyname.add("Local Transportation Cost");
                    valueList.add(otherDetailDaoList.get(i).getIn_local_transporting_cost());
                }
            }
            String s= TextUtils.join(", ",qtyname);
            int totalPrice = 0;
            for(int i = 0 ; i < valueList.size(); i++) {
                totalPrice += valueList.get(i);
            }
            textViewOtherCost.setText(""+totalPrice);
            int total=grandTotal2(valueList);

            String ptf = String.valueOf(singleActualHeaderDao.getIn_advance_amt());
            double totalCost;
            double y = Double.parseDouble(calculatevalue);
            if(ptf.equalsIgnoreCase(""))
            {
                totalCost=y-total;
            }
            else

            {
                totalCost=(y-total)-singleActualHeaderDao.getIn_advance_amt();
            }

            // textViewTotalCost.setText(""+new DecimalFormat("##.##").format(totalCost));
            //    textViewTotalCost.setText(""+total);

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
    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }
    private void showFarmerCodeDialog() {

        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.custom_search_farmer_dialog);
        //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
        dialog.setTitle("Title...");
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
        final RecyclerView recyclerView = dialog.findViewById(R.id.itm);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        dialog.getWindow().setLayout(width, height);

        final AutoCompleteTextView elc = (AutoCompleteTextView) dialog.findViewById(R.id.elc);
        elc.setHint("Search FarmerCode");
        farmerCodeList = new ArrayList<>();
        farmerCodeList.clear();
        farmerCodeList = db.getFarmerCode();
        HashSet hs = new HashSet();

        hs.addAll(farmerCodeList); // demoArrayList= name of arrayList from which u want to remove duplicates

        farmerCodeList.clear();
        farmerCodeList.addAll(hs);

        final ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(mContext,
                R.layout.support_simple_spinner_dropdown_item, farmerCodeList);

        elc.setAdapter(adapterlist2n);

        formerDaoList = new ArrayList<>();
        elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                formerDaoList.clear();
                String farmerCode = elc.getText().toString();
                formerDaoList = db.getAllFarmerDataDetails(farmerCode, "FARMER_CODE");

                MyRecyclerViewAdapterfarmer adapterf = new MyRecyclerViewAdapterfarmer(mContext, formerDaoList);
                recyclerView.setAdapter(adapterf);

                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
            }
        });

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
        Button anf = (Button)dialog.findViewById(R.id.anf);

        anf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FDR.class);
                startActivity(i);
                dialog.dismiss();
            }
        });
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public class MyRecyclerViewAdapterfarmer extends RecyclerView.Adapter<MyRecyclerViewAdapterfarmer.ViewHolder> {

        private List<FormerDao> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        public MyRecyclerViewAdapterfarmer(Context context, List<FormerDao> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.custom_recycleview_farmer, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final FormerDao pojofar = mData.get(position);
            holder.name.setText(pojofar.getFarmer_name());
            holder.fhw.setText(pojofar.getFhw_name());
            holder.ph.setText(pojofar.getFarmer_village_desc());
            holder.lcn.setText(pojofar.getFarmer_panchayat_desc());
            holder.ty.setText(pojofar.getFarmer_taluk_desc());
            holder.t5.setText(pojofar.getFarmer_dist_desc());

            holder.llist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    farmerCode = pojofar.getFarmer_code();
                    farmerName = pojofar.getFarmer_name();
                    memberType = pojofar.getFarmer();

                    textViewFarmerName.setText(farmerName);
                    textViewMember.setText(memberType);
                    textViewFarmerCode.setText(farmerCode);
                    showfardetails(farmerCode);


                    dialog.dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name, fhw, ph, lcn, ty, t5;
            LinearLayout llist;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.fn);
                fhw = itemView.findViewById(R.id.fhn);
                ph = itemView.findViewById(R.id.vi);
                lcn = itemView.findViewById(R.id.gp);
                ty = itemView.findViewById(R.id.ta);
                t5 = itemView.findViewById(R.id.di);
                llist = itemView.findViewById(R.id.llist);
            }
        }
    }
    private void showFarmerNameDialog() {

        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.custom_search_farmer_dialog);
        //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
        dialog.setTitle("Title...");
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
        final RecyclerView recyclerView = dialog.findViewById(R.id.itm);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        dialog.getWindow().setLayout(width, height);

        final AutoCompleteTextView elc = (AutoCompleteTextView) dialog.findViewById(R.id.elc);
        elc.setHint("Search FarmerName");
        farmerNameList = new ArrayList<>();
        farmerNameList.clear();
        farmerNameList = db.getFarmerName();
        HashSet hs = new HashSet();

        hs.addAll(farmerNameList); // demoArrayList= name of arrayList from which u want to remove duplicates

        farmerNameList.clear();
        farmerNameList.addAll(hs);

        final ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(mContext,
                R.layout.support_simple_spinner_dropdown_item, farmerNameList);

        elc.setAdapter(adapterlist2n);

        formerDaoList = new ArrayList<>();
        elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                formerDaoList.clear();
                String farmerCode = elc.getText().toString();
                formerDaoList = db.getAllFarmerDataDetails(farmerCode, "FARMER_NAME");

                MyRecyclerViewAdapterfarmer adapterf = new MyRecyclerViewAdapterfarmer(mContext, formerDaoList);
                recyclerView.setAdapter(adapterf);

                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
            }
        });

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);

        Button anf = (Button)dialog.findViewById(R.id.anf);

        anf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FDR.class);
                startActivity(i);
                dialog.dismiss();
            }
        });
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
    private void showDateDialog() {

        final Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, android.R.style.Theme_Holo_Dialog,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String month = null,day = null;
                        int m,d;

                        m = monthOfYear +1;
                        if(m < 10){

                            month = "0" + m;
                        }
                        else
                        {
                            month = String.valueOf(m);
                        }
                        if(dayOfMonth < 10){

                            day  = "0" + dayOfMonth ;
                        }
                        else
                        {
                            day= String.valueOf(dayOfMonth);
                        }

                        textViewPickUpdate.setText(day + "-" + month + "-" + year);


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();

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
                    byrname.setText(""+buyname);

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
    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        fetchsn();
        fetchqp();

    }
    public void fetchsn() {
        try{
            SQLiteDatabase dbs = db.getWritableDatabase();
            Cursor cursor = dbs.rawQuery("select v1,v2,v3,v4 from snowithqty where v3 = '"+ts+"' " +
                    "and v6 = '"+itemCode+"' and v7 != '2' ",null);
            Log.e("COUNT", "" + cursor.getCount());

            int x=0,y=0;
            //textViewQualityPara.setText("" + cursor.getCount());
            //textViewSINo.setText("" + cursor.getCount()+"(Serial Bags)");
            int res = 0;
            StringBuilder s = new StringBuilder(10000);
            // looping through all rows and adding to list
            if(cursor.getCount() > 0){
                textViewDecrease.setVisibility(View.GONE);
                textViewincrease.setVisibility(View.GONE);
                if (cursor.moveToFirst()) {
                    do {
                        try{
                            lbvals = cursor.getString(cursor.getColumnIndexOrThrow("v1"));
                            lbvals = lbvals.substring(0,2);
                            res += Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("v2")));
                        }catch (Exception er){
                            Log.e("error",Log.getStackTraceString(er));
                        }
                    } while (cursor.moveToNext());

                }
                if(lbvals.equalsIgnoreCase("LB")){
                    linearSino.setEnabled(false);
                    textViewSINo.setText("");
                    textViewQty2.setEnabled(true);
                    rb_bn.setChecked(true);
                    rb_by.setEnabled(false);
                }else{
                    textViewSINo.setText("" + cursor.getCount());
                    linearSino.setEnabled(true);
                    btn_view.setVisibility(View.VISIBLE);
                    btn_add.setVisibility(View.GONE);
                    textViewQty2.setText(""+res);
                    textViewItemName.setEnabled(false);
                    srccrop.setEnabled(false);
                    textViewQty2.setEnabled(false);
                    rb_by.setChecked(true);
                    rb_bn.setEnabled(false);
                }
            }else{
                btn_view.setVisibility(View.GONE);
                //linearSino.setEnabled(false);
                try{
                    if(Double.parseDouble(textViewQty2.getText().toString().trim()) > 0){
                        rb_bn.setChecked(true);
                        rb_by.setEnabled(false);
                        linearSino.setEnabled(false);
                    }else{
                        rb_bn.setChecked(true);
                    }
                }catch (Exception er){
                    rb_bn.setChecked(true);
                    linearSino.setEnabled(false);
                    Log.e("error",Log.getStackTraceString(er));
                }
            }
        }catch (Exception er){
            Log.e("error",Log.getStackTraceString(er));
        }
    }

    public void fetchqp() {
       try{
           SQLiteDatabase dbs = db.getWritableDatabase();

           Cursor cursor = dbs.query("qparpe", new String[]{"v1","v2","v3"
                   }, "v1" + "=? COLLATE NOCASE",
                   new String[]{ts}, null, null, null, null);
           Log.e("COUNT", "" + cursor.getCount());

           //textViewQualityPara.setText("" + cursor.getCount());
           //textViewSINo.setText("" + cursor.getCount()+"(Serial Bags)");

           StringBuilder s = new StringBuilder(10000);
           // looping through all rows and adding to list
           if (cursor.moveToFirst()) {
               do {
                   String[] pn = cursor.getString(1).split("-");
                   s.append(pn[1]+"-"+cursor.getString(2)+" \n");
                   textViewQualityPara.setText(s);
                   //
               } while (cursor.moveToNext());
           }
       }catch (Exception er){
           Log.e("error",Log.getStackTraceString(er));
       }
    }

    public void savepe()
    {
        if(textViewSINo.getText().toString().equalsIgnoreCase("")){
            lbvals = "LB";
        }
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
            user.put("userId", pawhsApplication.getPreferenceFromString(RecActualPurchaseActivity.this, "IN_USER_CODE"));
//            user.put("localeId", localeId);
            user.put("localeId", "actual");
            userd.put("context",user);
            JSONObject user2 = new JSONObject();

            user2.put("in_Actual_rowid", Integer.parseInt(peoid));
            user2.put("in_LotNo",lnom);
            //String[] by = spinnerb.getSelectedItem().toString().split("---");
            user2.put("in_Farmer_Code",farmerCode);
            user2.put("in_Farmer_Name",farmerName);

            user2.put("in_Member_Type",memberType);
            user2.put("in_Item_Code",itemCode);

            user2.put("in_Item_Name",itemName);
            user2.put("in_Actual_Qty", Double.parseDouble(textViewQty2.getText().toString()));
            user2.put("in_Actual_Price", Double.parseDouble(edt_price.getText().toString()));
            user2.put("in_Actual_Value", Double.parseDouble(textViewValue.getText().toString()));
            user2.put("in_advance_amt",0);

            if(textViewNoofBag.getText().toString().equalsIgnoreCase(""))
            {
                user2.put("in_no_of_bags",0);
            }
            else
            {
                user2.put("in_no_of_bags",Integer.parseInt(textViewNoofBag.getText().toString()));
            }
           // user2.put("in_no_of_bags", Integer.parseInt(textViewNoofBag.getText().toString()));
            user2.put("in_Status","Actual");

            if(peoid.equalsIgnoreCase("0"))
            {
                user2.put("in_mode_flag","I");
            }
            else
            {
                user2.put("in_mode_flag","U");
            }

            if(edt_remarks.getText().toString().equalsIgnoreCase(""))
            {
                user2.put("in_actual_remarks","NA");
            }
            else
            {
                user2.put("in_actual_remarks",edt_remarks.getText().toString());
            }


            user2.put("in_approved_remarks","");
            user2.put("in_pickup_remarks","");

            user2.put("in_actual_attach",encodedImage);
            if(spinner.getSelectedItem().toString().equalsIgnoreCase(""))
            {
                user2.put("in_vehicle_type","NA");
            }
            else
            {
                user2.put("in_vehicle_type",spinner.getSelectedItem().toString());
            }
            if(vno.getText().toString().equalsIgnoreCase(""))
            {
                user2.put("in_vehicle_no","NA");
            }
            else{
                user2.put("in_vehicle_no",vno.getText().toString());
            }
           if(spinner2.getSelectedItem().toString().equalsIgnoreCase(""))
           {
               user2.put("in_destination","NA");
           }
           else
           {
               user2.put("in_destination",spinner2.getSelectedItem().toString());
           }

           if(qcp.getText().toString().equalsIgnoreCase(""))
           {
               user2.put("in_qcperson_name","NA");           }
           else
           {
               user2.put("in_qcperson_name",qcp.getText().toString());
           }
            if(lrpname.getText().toString().equalsIgnoreCase(""))
            {
                user2.put("in_LRP_Name","NA");
            }
            else
            {
                user2.put("in_LRP_Name",lrpname.getText().toString());
            }
            if(lrpno.getText().toString().equalsIgnoreCase(""))
            {
                user2.put("In_LRP_Mobile_no","NA");
            }
            else
            {
                user2.put("In_LRP_Mobile_no",lrpno.getText().toString());
            }




            user2.put("In_Payment_type","");
            user2.put("In_Bank_acc_no","");
            user2.put("In_cheque_no","");
            user2.put("In_Buyer_code",buycode);
            user2.put("In_Buyer_name",buyname);
            user2.put("In_season",spinnerss.getSelectedItem().toString());
            String[] dt = textViewPickUpdate.getText().toString().split("-");
            user2.put("In_Acc_Date",dt[2]+"-"+dt[1]+"-"+dt[0]);


            user.put("Header",user2);



            JSONArray snodetails = new JSONArray();
            final SQLiteDatabase dbs = db.getWritableDatabase();
            //if(!textViewSINo.getText().toString().equalsIgnoreCase("") && Double.parseDouble(textViewSINo.getText().toString().trim()) <= 0){
            if(lbvals.equalsIgnoreCase("LB")){
                if(!peoid.equalsIgnoreCase("0")) {
                    JSONObject snolist = new JSONObject();
                    snolist.put("in_slno_row_id", 0);
                    snolist.put("in_slno", "LB" + itemCode);
                    snolist.put("in_temp1", "");
                    snolist.put("in_temp2", "");
                    snolist.put("in_mode_flag", "U");
                    snolist.put("in_qty", textViewQty2.getText().toString());
                    snodetails.put(snolist);
                }
            }
            else{
                              /* Cursor cursor2 = dbs.query("snope", new String[]{"id","v1","v2","v3"
                    }, "v3" + "=? COLLATE NOCASE",
                    new String[]{ts}, null, null, null, null);*/
                Cursor cursor2 = dbs.rawQuery("SELECT * FROM snowithqty where v3 = ? and v6 = ? ",
                        new String[]{ts,itemCode});
                if (cursor2.moveToFirst()) {
                    do {
                        JSONObject snolist = new JSONObject();

                        if(cursor2.getString(cursor2.getColumnIndexOrThrow("v5")).equalsIgnoreCase("1")) {
                            if( cursor2.getString(cursor2.getColumnIndexOrThrow("v7")).equalsIgnoreCase("0")){
                                snolist.put("in_mode_flag", "I");
                            }else if (cursor2.getString(cursor2.getColumnIndexOrThrow("v7")).equalsIgnoreCase("2")){
                                snolist.put("in_mode_flag", "D");
                            }
                            else{
                                snolist.put("in_mode_flag", "U");
                            }
                            snolist.put("in_slno_row_id", 0);
                            snolist.put("in_slno", cursor2.getString(cursor2.getColumnIndexOrThrow("v1")));
                            snolist.put("in_temp1", "");
                            snolist.put("in_temp2", "");
                            snolist.put("in_qty", cursor2.getString(cursor2.getColumnIndexOrThrow("v2")));
                        }
                        else
                        {
                            //int x = Integer.parseInt(cursor2.getString(2));
                            int x = Integer.parseInt(cursor2.getString(cursor2.getColumnIndexOrThrow("v5")));
                            int y;
                            String[] v = cursor2.getString(1).split("-");
                            String[] v2 = v[0].split(" ");
                            y = Integer.parseInt(v2[1]);
                            for (int i = 0;i<x;i++)
                            {
                                if( cursor2.getString(cursor2.getColumnIndexOrThrow("v7")).equalsIgnoreCase("0")){
                                    snolist.put("in_mode_flag", "I");
                                }else if( cursor2.getString(cursor2.getColumnIndexOrThrow("v7")).equalsIgnoreCase("2")){
                                    snolist.put("in_mode_flag", "D");
                                }
                                else{
                                    snolist.put("in_mode_flag", "U");
                                }
                                snolist.put("in_slno_row_id", 0);
                                snolist.put("in_slno", v2[0]+""+y);
                                snolist.put("in_temp1", "");
                                snolist.put("in_temp2", "");
                                //snolist.put("in_mode_flag", "I");
                                snolist.put("in_qty", cursor2.getString(cursor2.getColumnIndexOrThrow("v2")));

                                y++;

                            }
                        }

                        //snolist.put("QlyDetail",snolist);
                        snodetails.put(snolist);
                    }

                    while (cursor2.moveToNext());
                }
            }
            user.put("SlnoDetail",snodetails);
            Log.e("sljson",""+snodetails.toString());

            JSONArray qpar = new JSONArray();
            Cursor cursorq = dbs.query("qparpe", new String[]{"id","v1","v2","v3"
                    }, "v1" + "=? COLLATE NOCASE",
                    new String[]{ts}, null, null, null, null);
            if (cursorq.moveToFirst()) {
                do {
                    JSONObject qparlist = new JSONObject();

                    qparlist.put("in_qty_dtl_rowid",0);

                    String[] q = cursorq.getString(2).split("-");
                    qparlist.put("in_qty_code",q[0]);
                    if(cursorq.getString(3).equalsIgnoreCase("Yes"))
                    {
                        qparlist.put("in_actual_value",1);
                    }
                    else if(cursorq.getString(3).equalsIgnoreCase("No"))
                    {
                        qparlist.put("in_actual_value",2);
                    }
                    else
                    {
                        qparlist.put("in_actual_value", Double.parseDouble(cursorq.getString(3)));
                    }

                    qparlist.put("in_wr_qty_value",0);

                    qparlist.put("in_mode_flag","I");


                    qpar.put(qparlist);

                }

                while (cursorq.moveToNext());
            }
            user.put("QtyDetail",qpar);

            JSONArray od = new JSONArray();
            user.put("OtherDetail",od);



            Log.e("OUTPUT",""+jsonParam.toString());
        }
        catch(Exception e)
        {
            Log.e("OUTPUT",""+e.getMessage());
        }

        //  http://169.38.77.190:101/api/NewPawhsActulProcurment/new_pawhs_ActualProc_save
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,ApiUtils.TEST_URL_API+"NewPawhsActulProcurment/new_pawhs_ActualProc_save",jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);

                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("header");


                            if(obj2.getString("in_LotNo").equalsIgnoreCase("null"))
                            {
                                showErrorDialog("Unable To Save");
                            }
                            else
                            {
                                final SQLiteDatabase dbs = db.getWritableDatabase();
                                Pojokyc.purno="";
                                dbs.execSQL("update snowithqty set v7 = '1' where v7 = '0' ");
// code for inser/update snowith qty for loosebags  -start
//                                if(textViewSINo.getText().toString().equalsIgnoreCase("") || Double.parseDouble(textViewSINo.getText().toString()) <= 0){
                                if(lbvals.equalsIgnoreCase("LB")){
                                    Cursor cr = dbs.rawQuery("select * from snowithqty where v1 = '"+"LB"+itemCode+"' ",null);
                                    try{
                                        if(cr.getCount() > 0){
                                            Double exstqtty = 0.0,exqty2 = 0.00;
                                            if(cr.moveToFirst()){
                                                if(!cr.getString(cr.getColumnIndexOrThrow("v2")).equalsIgnoreCase("") && cr.getString(cr.getColumnIndexOrThrow("v2")) != null){
                                                    exstqtty = Double.parseDouble(cr.getString(cr.getColumnIndexOrThrow("v2")));
                                                    Log.e("qty1",""+exstqtty);
                                                }
                                                exqty2 =  Double.parseDouble(textViewQty2.getText().toString()) - Double.parseDouble(oldlbqty);
                                                Log.e("qty2",""+exqty2);
                                        /*if(exqty2 < 0){

                                        }else if(exqty2 >= 0){

                                        }*/
                                                exstqtty += exqty2;
                                                Log.e("qty3",""+exstqtty);
                                                dbs.execSQL("update snowithqty set v2 = '"+exstqtty+"' where v1 = '"+"LB"+itemCode+"' ");
                                            }
                                        }
                                        else{

                                            db.insertsnowithqty("LB"+itemCode,textViewQty2.getText().toString(),ts,"Y","1",itemCode,"1");
                                        }
                                    }catch (Exception er){
                                        Log.e("error",Log.getStackTraceString(er));
                                    }finally {
                                        cr.close();
                                    }
// code for inser/update snowith qty for loosebags  - end
                                }
                                if(updatestatus.equalsIgnoreCase("0"))
                                {
                                    db.insertpurchaseentry(farmerCode,farmerName,memberType,itemCode,itemName,textViewQty2.getText().toString(),edt_price.getText().toString(),textViewValue.getText().toString(),textViewSINo.getText().toString(),edt_remarks.getText().toString(),ui,spinner.getSelectedItem().toString(),vno.getText().toString(),spinner2.getSelectedItem().toString(),qcp.getText().toString(),lrpname.getText().toString(),lrpno.getText().toString(),textViewPickUpdate.getText().toString(),buycode,spinnerss.getSelectedItem().toString(),ts,"1",obj2.getString("in_LotNo"),obj2.getString("in_Actual_rowid"),"U");

                                    pdialog.dismiss();
                                    updatestatus = "0";
                                    textViewItemName.setEnabled(true);
                                    srccrop.setEnabled(true);
                                    textViewLotNo.setText("");
                                    txt_uom.setText("");
                                    txt_var.setText("");
                                    textViewFarmerCode.setText("");
                                    textViewFarmerName.setText("");
                                    textViewMember.setText("");
                                    textViewItemName.setText("");
                                    edt_price.setText("");
                                    textViewValue.setText("");
                                    textViewQualityPara.setText("");
                                    textViewNoofBag.setText("");
                                    textViewSINo.setText("");
                                    textViewOtherCost.setText("");
                                    textViewTotalCost.setText("");
                                    textViewPickUpdate.setText("");
                                    edt_remarks.setText("");
                                    edt_advance.setText("");
                                    lrpno.setText("");
                                    qcp.setText("");
                                    vno.setText("");
                                    encodedImage="0";
                                    lrpname.setText("");
                                    spinner.setSelection(0);
                                    spinner2.setSelection(0);
                                    capturebill.setImageResource(0);
                                    spinnerpt.setSelection(0);
                                    bno.setText("");
                                    cqno.setText("");
                                    byrname.setText("");

                                    capturebill.setBackgroundResource(R.drawable.capture);
                                    numtest = 0;
                                    textViewQty.setText("" + numtest);
                                    textViewQty2.setText("" + numtest);
                                    showErrorDialog2("Saved Successfully !!! Purchase Entry No is"+obj2.getString("in_LotNo"));
                                    Long tsLong = System.currentTimeMillis()/1000;
                                    ts = tsLong.toString();
                                }
                                else{
                                    db.updatepurchaseentry(Integer.valueOf(tableid),farmerCode,farmerName,memberType,itemCode,itemName,textViewQty2.getText().toString(),edt_price.getText().toString(),textViewValue.getText().toString(),textViewNoofBag.getText().toString(),edt_remarks.getText().toString(),ui,spinner.getSelectedItem().toString(),vno.getText().toString(),spinner2.getSelectedItem().toString(),qcp.getText().toString(),lrpname.getText().toString(),lrpno.getText().toString(),textViewPickUpdate.getText().toString(),buycode,spinnerss.getSelectedItem().toString(),ts,"1",obj2.getString("in_LotNo"),obj2.getString("in_Actual_rowid"),"U");

                                    pdialog.dismiss();
                                    updatestatus = "0";
                                    textViewItemName.setEnabled(true);
                                    srccrop.setEnabled(true);
                                    textViewLotNo.setText("");
                                    txt_uom.setText("");
                                    txt_var.setText("");
                                    textViewFarmerCode.setText("");
                                    textViewFarmerName.setText("");
                                    textViewMember.setText("");
                                    textViewItemName.setText("");
                                    edt_price.setText("");
                                    textViewValue.setText("");
                                    textViewQualityPara.setText("");
                                    textViewNoofBag.setText("");
                                    textViewSINo.setText("");
                                    textViewOtherCost.setText("");
                                    textViewTotalCost.setText("");
                                    textViewPickUpdate.setText("");
                                    edt_remarks.setText("");
                                    edt_advance.setText("");
                                    lrpno.setText("");
                                    qcp.setText("");
                                    vno.setText("");
                                    encodedImage="0";
                                    lrpname.setText("");
                                    spinner.setSelection(0);
                                    spinner2.setSelection(0);
                                    capturebill.setImageResource(0);
                                    spinnerpt.setSelection(0);
                                    bno.setText("");
                                    cqno.setText("");
                                    byrname.setText("");

                                    capturebill.setBackgroundResource(R.drawable.capture);
                                    numtest = 0;
                                    textViewQty.setText("" + numtest);
                                    textViewQty2.setText("" + numtest);
                                    showErrorDialog2("Updated Successfully !!!");
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
    public  void showfardetails(String fcode)
    {
        DBHelper dbHelper = new DBHelper(RecActualPurchaseActivity.this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from farlist where fc = '"+fcode+"'",null);
        if(cursor.getCount()>0)
        {
            if(cursor.moveToFirst())
            {
                Log.e("Farvalue",""+cursor.getString(6)+"/"+cursor.getString(7)+"/"+cursor.getString(8)+"/"+cursor.getString(9)+"/"+cursor.getString(11));
                txt_village.setText(""+cursor.getString(6));
                txt_panchayat.setText(""+cursor.getString(7));
                txt_district.setText(""+cursor.getString(9));
                txt_mobileno.setText(""+cursor.getString(11));
            }
        }
    }
    public void deletedata(){
        if(updatestatus.equalsIgnoreCase("0")){
            SQLiteDataBaseHandler dbHelper = new SQLiteDataBaseHandler(RecActualPurchaseActivity.this);
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            sqLiteDatabase.execSQL("delete from snowithqty where v3 = '"+ts+"' and v6 = '"+itemCode+"' ");
            sqLiteDatabase.execSQL("delete from qparpe where v1 = '"+ts+"' ");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        buttonCancel.callOnClick();
    }

}