package cdfi.fintantra.meity.Pawhs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cdfi.fintantra.meity.Pawhs.api.ApiService;
import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import cdfi.fintantra.meity.Pawhs.model.PmListDao;
import cdfi.fintantra.meity.Pawhs.model.PostQtyDetailDao;
import cdfi.fintantra.meity.Pawhs.model.RangeNameDao;
import cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel.SingleProductMasterDao;
import cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel.SpmContextDao;
import cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel.SpmDetailDao;
import cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel.SpmHeaderDao;
import cdfi.fintantra.meity.Pawhs.recactpurchase.QualityEditDeleteListener;
import cdfi.fintantra.meity.Pawhs.utils.MyConstants;
import cdfi.fintantra.meity.Pawhs.utils.Utility;
import cdfi.fintantra.meity.Pojoqpar;
import cdfi.fintantra.meity.R;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class QparSalenew extends AppCompatActivity implements View.OnClickListener, QualityEditDeleteListener {
    private PAWHSApplication pawhsApplication;
    private ApiService mAPIService;
    private String userName;
    private Context mContext;
    private TextView textViewTitle;

    private RelativeLayout progressLayout;

    private RecyclerView recyclerViewQuality;

    private Button buttonAdd, buttonClose, buttonCancel;

    private TextView textViewQualityPara, textViewUom, textViewThreshold;
    List<Pojoqpar> qlist;
    private EditText editTextActualValue;

    private String orgnId, locnId, userId, localeId, orgnCode;

    private String itemCode;
    private List<PmListDao> pmListDaoList;
    private String productCode;
    private int productRowId;

    private static List<SpmDetailDao> spmDetailDaoList;
    private SQLiteDataBaseHandler db;

    String quality, qualityCode="", range, unit;

    private int minRange = 0;
    private int maxRange = 0;
    int qstatus;

    private Dialog dialog;
    ArrayList qpname = new ArrayList();

    private QualityListParameterAdapter qualityListParameterAdapter;
    private List<PostQtyDetailDao> postQtyDetailDaoList=new ArrayList<>();

    private String edit_item = "";
    private int pos;

    public Spinner spinneractual;
    private String[] rangeName;
    SQLiteDatabase dbs;
    String sid;
    String ts,pc;
    private RangeNameSpinnerAdapter rangeNameSpinnerAdapter;
    private List<RangeNameDao> rangeNameDaoList;
    private int spinActValue;
    private String rangeValue;
    MyRecyclerViewAdapterfarmer adapterf;
    boolean isNetwork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qpar_salenew);
        getSupportActionBar().hide();
        mContext = this;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            itemCode = bundle.getString("ITEM_CODE");
            postQtyDetailDaoList=bundle.getParcelableArrayList("QUALITY_DETAIL_LIST");
        }
        Intent i = getIntent();
        ts = i.getStringExtra("TS");
        productCode = i.getStringExtra("PC");

        pawhsApplication = PAWHSApplication.getGetInstance();
        userName = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.IN_USER_NAME);

        orgnCode = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_CODE);
        orgnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_ID);
        locnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCN_ID);
        userId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.USER_ID);
        localeId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCALE_ID);

        db = new SQLiteDataBaseHandler(mContext);
        dbs = db.getWritableDatabase();

        initView();


    }

    private void initView() {
        isNetwork = Utility.checkConnectivity(getApplicationContext());
        mAPIService = ApiUtils.getAPIService();
        textViewTitle = (TextView) findViewById(R.id.txt_title);
        textViewTitle.setText("Welcome " + userName + "\n" + "Sale Entry");

        progressLayout = (RelativeLayout) findViewById(R.id.progressLayout);

        textViewQualityPara = (TextView) findViewById(R.id.txt_quality_para);
        textViewUom = (TextView) findViewById(R.id.txt_uom);
        textViewThreshold = (TextView) findViewById(R.id.txt_threshold);
        editTextActualValue = (EditText) findViewById(R.id.edt_actualvalue);
        //editTextActualValue.setFilters(new InputFilter[]{ new InputFilterMinMax(minRange, maxRange)});
        spinneractual=(Spinner)findViewById(R.id.spin_actual);

        buttonAdd = (Button) findViewById(R.id.but_add);
        buttonClose = (Button) findViewById(R.id.but_close);
        buttonCancel = (Button) findViewById(R.id.but_cancel);
        qlist = new ArrayList<>();
        recyclerViewQuality = (RecyclerView) findViewById(R.id.recycle_qualitypara);
        recyclerViewQuality.setLayoutManager(new LinearLayoutManager(mContext));

        pmListDaoList = new ArrayList<>();
        // postQtyDetailDaoList = new ArrayList<>();
        pmListDaoList = db.getCodeRelatedProductMasterAllProductDetails(itemCode);

        rangeNameDaoList=new ArrayList<>();
        rangeNameDaoList.add(new RangeNameDao("Select",0));

        rangeNameSpinnerAdapter=new RangeNameSpinnerAdapter(mContext,rangeNameDaoList);
        spinneractual.setAdapter(rangeNameSpinnerAdapter);

        textViewUom.addTextChangedListener(new TextWatcher() {
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

                if(s.toString().equalsIgnoreCase("Number"))
                {
                    //Toast.makeText(getApplicationContext(), "YES", Toast.LENGTH_SHORT).show();
                    qstatus = 1;
                }
                else
                {
                    //Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
                    qstatus = 0;
                }

                // TODO Auto-generated method stub
            }
        });
//        editTextActualValue.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                if(s.length()>0)
//                {
//                    //Toast.makeText(getApplicationContext(), "YES", Toast.LENGTH_SHORT).show();
//                   try {
//
//                    String[] tr = textViewThreshold.getText().toString().split("to");
//                    double x = Double.parseDouble(tr[0].replaceAll(" ",""));
//                    double y = Double.parseDouble(tr[1].replaceAll(" ",""));
//
//                    double z = Double.parseDouble(s.toString());
//                      // Toast.makeText(getApplicationContext(), ""+x+"/"+y+"/"+z, Toast.LENGTH_SHORT).show();
//                    Log.e("CHK",""+x+"/"+y);
//
//                    if(z>x && z<y)
//                    {
//
//                    }
//                    else
//                    {
//                        showErrorDialog("Invalid Actual value");
//                        editTextActualValue.setText("");
//                    }
//                   }catch (Exception e)
//                   {
//
//                   }
//
//                }
//
//
//                // TODO Auto-generated method stub
//            }
//        });
//        if (pmListDaoList != null && pmListDaoList.size() > 0) {
//            productCode = pmListDaoList.get(0).getOut_pawhs_code();
//            productRowId = Integer.parseInt(pmListDaoList.get(0).getOut_product_rowid());
//        }

        spinneractual.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                spinActValue = rangeNameDaoList.get(i).getId();
                // Toast.makeText(mContext, ""+spinActValue, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if(postQtyDetailDaoList!=null && postQtyDetailDaoList.size()>0){
            setAdapter();
        }
        Intent i = getIntent();
        sid = i.getStringExtra("SID");
        productCode = i.getStringExtra("PC");
        adapterf = new MyRecyclerViewAdapterfarmer(mContext, qlist);
        fetchsn();
        spmDetailDaoList = new ArrayList<>();

        if (isNetwork == true) {
            // callQualityParameterJsonDetails();
            spmDetailDaoList=db.getSpmDetailsListRelatedPawhsCode(productCode);
            Log.v(MyConstants.TAG, String.valueOf(spmDetailDaoList.size()));

        }else {
            spmDetailDaoList=db.getSpmDetailsListRelatedPawhsCode(productCode);
            Log.v(MyConstants.TAG, String.valueOf(spmDetailDaoList.size()));

        }



        buttonAdd.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
        buttonClose.setOnClickListener(this);
        textViewQualityPara.setOnClickListener(this);
    }

    private void callQualityParameterJsonDetails() {

        progressLayout.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        SpmContextDao spmContextDao = new SpmContextDao();
        spmContextDao.setOrgnId(orgnCode);
        //   spmContextDao.setLocnId(locnId);
        spmContextDao.setLocnId(ApiUtils.instance);
        spmContextDao.setUserId(userId);
        spmContextDao.setLocaleId(localeId);
        SpmHeaderDao spmHeaderDao = new SpmHeaderDao();
        spmHeaderDao.setIOU_product_rowid(productRowId);
        spmHeaderDao.setIOU_agg_code(orgnCode);
        spmHeaderDao.setIOU_product_code(productCode);
        spmContextDao.setSpmHeaderDao(spmHeaderDao);

        mAPIService.getQualityParameterDetails(spmContextDao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SingleProductMasterDao>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SingleProductMasterDao singleProductMasterDao) {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressLayout.setVisibility(View.GONE);

                        spmDetailDaoList = singleProductMasterDao.getSpmContextDao().getSpmDetailDaoList();
                    }
                });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.but_add:



                if(qpname.contains(quality))
                {
                    showErrorDialog("Duplicate Entry");
                }
                else
                {



                    if(qstatus == 0) {
                        if(editTextActualValue.getText().toString().equalsIgnoreCase("")){
                            showErrorDialog("Empty Actual Value");

                        }else {

                            try {

                                String[] tr = textViewThreshold.getText().toString().split("to");
                                double x = Double.parseDouble(tr[0].replaceAll(" ",""));
                                double y = Double.parseDouble(tr[1].replaceAll(" ",""));

                                double z = Double.parseDouble(editTextActualValue.getText().toString());
                                // Toast.makeText(getApplicationContext(), ""+x+"/"+y+"/"+z, Toast.LENGTH_SHORT).show();
                                Log.e("CHK",""+x+"/"+y);

                                if(z>=x && z<=y)
                                {
                                    qpname.add(quality);
                                    db.insertqparnew(ts, qualityCode + "-" + quality, editTextActualValue.getText().toString());
                                    textViewQualityPara.setText("");
                                    textViewUom.setText("");
                                    textViewThreshold.setText("");
                                    editTextActualValue.setText("");
                                    fetchsn();
                                }
                                else
                                {
                                    showErrorDialog("Invalid Actual value");
                                    editTextActualValue.setText("");
                                }
                            }catch (Exception e)
                            {

                            }

                        }
                    }
                    else
                    {
                        if(spinActValue==0)
                        {
                            showErrorDialog("Empty Actual Value");
                        }
                        else
                        {
                            qpname.add(quality);
                            if(spinActValue == 1) {
                                db.insertqparnew(ts, qualityCode + "-" + quality, "Yes");
                                textViewQualityPara.setText("");
                                textViewUom.setText("");
                                textViewThreshold.setText("");
                                editTextActualValue.setText("");
                                fetchsn();
                            }
                            else
                            {
                                db.insertqparnew(ts, qualityCode + "-" + quality, "No");
                                textViewQualityPara.setText("");
                                textViewUom.setText("");
                                textViewThreshold.setText("");
                                editTextActualValue.setText("");
                                fetchsn();
                            }
                        }
                    }

                }
                // Toast.makeText(getApplicationContext(), "qc="+qualityCode, Toast.LENGTH_SHORT).show();







                /*else{

                    if(edit_item.equalsIgnoreCase("Edit_Quality_Item")){
                        edit_item="";
                        bulkQtyDetailDaoList.remove(pos);
                        bulkQtyDetailDaoList.add(new BulkQtyDetailDao(0,qualityCode,actualValue,0,"I",thresholdValue,uomvalue,qtyName));
                        textViewQualityPara.setText("");
                        textViewThreshold.setText("");
                        textViewUom.setText("");
                        editTextActualValue.setText("");
                        setAdapter();


                    }else {

                        if(bulkQtyDetailDaoList!=null && bulkQtyDetailDaoList.size()>0){
                            for (int i=0;i<bulkQtyDetailDaoList.size();i++){
                                if(!qtyName.equalsIgnoreCase(bulkQtyDetailDaoList.get(i).getQtyName())){
                                    bulkQtyDetailDaoList.add(new BulkQtyDetailDao(0,qualityCode,actualValue,0,"I",thresholdValue,uomvalue,qtyName));
                                    textViewQualityPara.setText("");
                                    textViewThreshold.setText("");
                                    textViewUom.setText("");
                                    editTextActualValue.setText("");
                                    setAdapter();
                                }else {
                                    showErrorDialog("Already Inserted");
                                }
                            }
                        }else {

                            bulkQtyDetailDaoList.add(new BulkQtyDetailDao(0,qualityCode,actualValue,0,"I",thresholdValue,uomvalue,qtyName));
                            textViewQualityPara.setText("");
                            textViewThreshold.setText("");
                            textViewUom.setText("");
                            editTextActualValue.setText("");
                            setAdapter();

                        }

                    }







                }*/


                break;
            case R.id.txt_quality_para:

                showQualityDialog();


                break;
            case R.id.but_close:
                finish();
                break;
            case R.id.but_cancel:
                finish();
                break;
            default:
                break;
        }
    }

    private boolean contains(List<PostQtyDetailDao> list, String name) {
        for (PostQtyDetailDao item : list) {
            if (item.getIn_qty_name().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private void setAdapter() {
        qualityListParameterAdapter = new QualityListParameterAdapter(mContext, postQtyDetailDaoList, this);
        recyclerViewQuality.setAdapter(qualityListParameterAdapter);
        qualityListParameterAdapter.notifyDataSetChanged();
    }

    private void showQualityDialog() {

        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.custom_quality_para_dialog);
        //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
        dialog.setTitle("Title...");
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
        final RecyclerView recyclerView = dialog.findViewById(R.id.recycle_quality);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        dialog.getWindow().setLayout(width, height);

        final SearchView elc = (SearchView) dialog.findViewById(R.id.searchQuality);



        final MyRecyclerViewAdapterQulaity adapterf = new MyRecyclerViewAdapterQulaity(mContext, spmDetailDaoList);
        recyclerView.setAdapter(adapterf);

        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);

        elc.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapterf != null) {
                    adapterf.filter(newText);
                }

                return false;
            }
        });

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetwork == true) {
                    // callQualityParameterJsonDetails();
                    spmDetailDaoList=db.getSpmDetailsListRelatedPawhsCode(productCode);
                }else {
                    spmDetailDaoList=db.getSpmDetailsListRelatedPawhsCode(productCode);

                }
                //  callQualityParameterJsonDetails();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public void editItem(int position, String edititem) {
        edit_item = edititem;
        pos = position;
        textViewQualityPara.setText(postQtyDetailDaoList.get(position).getIn_qty_name());
        textViewUom.setText(postQtyDetailDaoList.get(position).getUom());
        textViewThreshold.setText(postQtyDetailDaoList.get(position).getThresHoldValue());
        // editTextActualValue.setText("" + postQtyDetailDaoList.get(position).getIn_actual_value());
        qualityCode = postQtyDetailDaoList.get(position).getIn_qty_code();

        String test1 = textViewThreshold.getText().toString();
        if(test1.contains("to")){
            editTextActualValue.setVisibility(View.VISIBLE);
            spinneractual.setVisibility(View.GONE);
            String[] items = Utility.splitRange(test1);
            String min = items[0];
            min = min.replace(" ", "");
            String max = items[1];
            max = max.replace(" ", "");
            minRange = Integer.parseInt(min);
            maxRange = Integer.parseInt(max);
            editTextActualValue.setText("" + postQtyDetailDaoList.get(position).getIn_actual_value());
        }else {
            editTextActualValue.setVisibility(View.GONE);
            spinneractual.setVisibility(View.VISIBLE);
            /*rangeName = Utility.splitRangeName(test1);
            for (int i=0;i<rangeName.length;i++) {
                rangeNameDaoList.add(new RangeNameDao(rangeName[i],i+1));
            }*/
            spinneractual.setSelection((int) postQtyDetailDaoList.get(pos).getIn_actual_value());
        }

       /* if(postQtyDetailDaoList.get(pos).getIn_qty_name().equalsIgnoreCase("LIVE")){
            spinneractual.setVisibility(View.VISIBLE);
            editTextActualValue.setVisibility(View.GONE);
            spinneractual.setSelection(postQtyDetailDaoList.get(pos).getIn_actual_value());
        }else {
            spinneractual.setVisibility(View.GONE);
            editTextActualValue.setVisibility(View.VISIBLE);
            editTextActualValue.setText("" + postQtyDetailDaoList.get(position).getIn_actual_value());
        }*/

    }

    @Override
    public void deleteItem(int position) {
        postQtyDetailDaoList.remove(position);
        qualityListParameterAdapter.notifyDataSetChanged();
    }

    public class MyRecyclerViewAdapterQulaity extends RecyclerView.Adapter<MyRecyclerViewAdapterQulaity.ViewHolder> {

        private List<SpmDetailDao> mData;
        private LayoutInflater mInflater;
        private List<SpmDetailDao> arraylist;


        // data is passed into the constructor
        public MyRecyclerViewAdapterQulaity(Context context, List<SpmDetailDao> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
            this.arraylist = new ArrayList<SpmDetailDao>();
            this.arraylist.addAll(spmDetailDaoList);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.custom_recyclerview_quality, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final SpmDetailDao pojofar = mData.get(position);
            holder.textViewQtyName.setText(pojofar.getIn_maize_name());
            holder.textViewRange.setText(pojofar.getIn_range());
            holder.textViewUnit.setText(pojofar.getIn_maize_unit());

            holder.llist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    quality = pojofar.getIn_maize_name();
                    qualityCode = pojofar.getIn_maize_code();
                    range = pojofar.getIn_range();
                    unit = pojofar.getIn_maize_unit();

                    textViewQualityPara.setText(quality);
                    textViewUom.setText(unit);
                    textViewThreshold.setText(range);

                    String test1 = textViewThreshold.getText().toString();
                    if(test1.contains("to")){
                        editTextActualValue.setVisibility(View.VISIBLE);
                        spinneractual.setVisibility(View.GONE);
                        String[] items = Utility.splitRange(test1);
                        String min = items[0];
                        min = min.replace(" ", "");
                        String max = items[1];
                        max = max.replace(" ", "");
                        minRange = Integer.parseInt(min);
                        maxRange = Integer.parseInt(max);
                    }else {
                        editTextActualValue.setVisibility(View.GONE);
                        spinneractual.setVisibility(View.VISIBLE);
                        rangeName = Utility.splitRangeName(test1);
                        rangeNameDaoList.clear();
                        for (int i=0;i<rangeName.length;i++) {
                            rangeNameDaoList.add(new RangeNameDao(rangeName[i],i+1));
                        }
                    }


                    //callQualityParameterJsonDetails();
                    if (isNetwork == true) {
                        //callQualityParameterJsonDetails();
                        spmDetailDaoList=db.getSpmDetailsListRelatedPawhsCode(productCode);
                    }else {
                        spmDetailDaoList=db.getSpmDetailsListRelatedPawhsCode(productCode);

                    }

                    dialog.dismiss();
                }
            });
        }

        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            spmDetailDaoList.clear();
            if (charText.length() == 0) {
                spmDetailDaoList.addAll(arraylist);
            } else {
                for (SpmDetailDao wp : arraylist) {
                    if (wp.getIn_maize_name().toLowerCase(Locale.getDefault()).contains(charText)) {
                        spmDetailDaoList.add(wp);
                    }
                }
            }
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textViewQtyName, textViewRange, textViewUnit;
            LinearLayout llist;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewQtyName = itemView.findViewById(R.id.quality_name);
                textViewRange = itemView.findViewById(R.id.range);
                textViewUnit = itemView.findViewById(R.id.unit);

                llist = itemView.findViewById(R.id.llist);
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

    public  void fetchsn()
    {
        String selectQuery = "SELECT  * FROM qparnew where v1 ="+ts;
        qlist.clear();
        qpname.clear();
        Cursor cursor = dbs.rawQuery(selectQuery, null);
        Log.e("COUNT",""+cursor.getCount());

        if(cursor.getCount()==0)
        {
            recyclerViewQuality.setAdapter(adapterf);
            adapterf.notifyDataSetChanged();
        }

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pojoqpar pojosno = new Pojoqpar();

                pojosno.setId(cursor.getString(0));
                pojosno.setQp(cursor.getString(2));
                pojosno.setAv(cursor.  getString(3));

                String[] pn = cursor.getString(2).split("-");
                qpname.add(pn[1]);




                qlist.add(pojosno);
                // array2.add(cursor.getString(11));
                // Log.e("VAL",""+cursor.getString(11));
                recyclerViewQuality.setAdapter(adapterf);
                adapterf.notifyDataSetChanged();
            } while (cursor.moveToNext());

        }
    }
    public class MyRecyclerViewAdapterfarmer extends RecyclerView.Adapter<MyRecyclerViewAdapterfarmer.ViewHolder> {

        private List<Pojoqpar> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        public MyRecyclerViewAdapterfarmer(Context context, List<Pojoqpar> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.quality_parameter_list3, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Pojoqpar pojofar = mData.get(position);
            final String[] qpv = pojofar.getQp().split("-");
            holder.name.setText(qpv[1]);
            holder.avs.setText(pojofar.getAv());


            holder.item_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbs.delete("qparnew", "id" + "=" + pojofar.getId(), null);
                    qpname.remove(qpv[1]);
                    fetchsn();

                }
            });


        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name, fhw, ph, lcn, ty, t5,avs;
            LinearLayout llist;
            ImageView item_delete;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.txt_qual_para);
                avs = itemView.findViewById(R.id.avs);
                //llist = itemView.findViewById(R.id.llist);
                item_delete = itemView.findViewById(R.id.item_delete);

            }
        }
    }
}