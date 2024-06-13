package cdfi.fintantra.meity.Pawhs.recactpurchase;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class QulaityParameterActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      // setContentView(R.layout.quality_parameter);
//        mContext = this;
//
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            itemCode = bundle.getString("ITEM_CODE");
//            postQtyDetailDaoList=bundle.getParcelableArrayList("QUALITY_DETAIL_LIST");
//        }
//
//
//        pawhsApplication = PAWHSApplication.getGetInstance();
//        userName = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.IN_USER_NAME);
//
//        orgnCode = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_CODE);
//        orgnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_ID);
//        locnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCN_ID);
//        userId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.USER_ID);
//        localeId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCALE_ID);
//
//        db = new SQLiteDataBaseHandler(mContext);
//
//
//        initView();
//
//
//    }
//
//    private void initView() {
//        isNetwork = Utility.checkConnectivity(getApplicationContext());
//        mAPIService = ApiUtils.getAPIService();
//        textViewTitle = (TextView) findViewById(R.id.txt_title);
//        textViewTitle.setText("Welcome " + userName + "\n" + "PA Record Actual Purchase");
//
//        progressLayout = (RelativeLayout) findViewById(R.id.progressLayout);
//
//        textViewQualityPara = (TextView) findViewById(R.id.txt_quality_para);
//        textViewUom = (TextView) findViewById(R.id.txt_uom);
//        textViewThreshold = (TextView) findViewById(R.id.txt_threshold);
//        editTextActualValue = (EditText) findViewById(R.id.edt_actualvalue);
//        //editTextActualValue.setFilters(new InputFilter[]{ new InputFilterMinMax(minRange, maxRange)});
//        spinneractual=(Spinner)findViewById(R.id.spin_actual);
//
//        buttonAdd = (Button) findViewById(R.id.but_add);
//        buttonClose = (Button) findViewById(R.id.but_close);
//        buttonCancel = (Button) findViewById(R.id.but_cancel);
//
//        recyclerViewQuality = (RecyclerView) findViewById(R.id.recycle_qualitypara);
//        recyclerViewQuality.setLayoutManager(new LinearLayoutManager(mContext));
//
//        pmListDaoList = new ArrayList<>();
//        // postQtyDetailDaoList = new ArrayList<>();
//        pmListDaoList = db.getCodeRelatedProductMasterAllProductDetails(itemCode);
//
//        rangeNameDaoList=new ArrayList<>();
//        rangeNameDaoList.add(new RangeNameDao("Select",0));
//
//        rangeNameSpinnerAdapter=new RangeNameSpinnerAdapter(mContext,rangeNameDaoList);
//        spinneractual.setAdapter(rangeNameSpinnerAdapter);
//
//        if (pmListDaoList != null && pmListDaoList.size() > 0) {
//            productCode = pmListDaoList.get(0).getOut_pawhs_code();
//            productRowId = Integer.parseInt(pmListDaoList.get(0).getOut_product_rowid());
//        }
//
//        spinneractual.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                spinActValue = rangeNameDaoList.get(i).getId();
//                //   Toast.makeText(mContext, ""+spinActValue, Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        if(postQtyDetailDaoList!=null && postQtyDetailDaoList.size()>0){
//            setAdapter();
//        }
//
//
//        spmDetailDaoList = new ArrayList<>();
//
//        if (isNetwork == true) {
//            callQualityParameterJsonDetails();
//        }else {
//            spmDetailDaoList=db.getSpmDetailsListRelatedPawhsCode(productCode);
//            Log.v(MyConstants.TAG, String.valueOf(spmDetailDaoList.size()));
//
//        }
//
//
//
//        buttonAdd.setOnClickListener(this);
//        buttonCancel.setOnClickListener(this);
//        buttonClose.setOnClickListener(this);
//        textViewQualityPara.setOnClickListener(this);
//    }
//
//    private void callQualityParameterJsonDetails() {
//
//        progressLayout.setVisibility(View.VISIBLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//        SpmContextDao spmContextDao = new SpmContextDao();
//        spmContextDao.setOrgnId(orgnCode);
//        //   spmContextDao.setLocnId(locnId);
//        spmContextDao.setLocnId(ApiUtils.instance);
//        spmContextDao.setUserId(userId);
//        spmContextDao.setLocaleId(localeId);
//        SpmHeaderDao spmHeaderDao = new SpmHeaderDao();
//        spmHeaderDao.setIOU_product_rowid(productRowId);
//        spmHeaderDao.setIOU_agg_code(orgnCode);
//        spmHeaderDao.setIOU_product_code(productCode);
//        spmContextDao.setSpmHeaderDao(spmHeaderDao);
//
//        mAPIService.getQualityParameterDetails(spmContextDao)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<SingleProductMasterDao>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(SingleProductMasterDao singleProductMasterDao) {
//                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//                        progressLayout.setVisibility(View.GONE);
//
//                        spmDetailDaoList = singleProductMasterDao.getSpmContextDao().getSpmDetailDaoList();
//                    }
//                });
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//        switch (id) {
//            case R.id.but_add:
//
//                if(editTextActualValue.isShown()){
//                    String qtyName = textViewQualityPara.getText().toString();
//                    String uomvalue = textViewUom.getText().toString();
//                    String thresholdValue = textViewThreshold.getText().toString();
//                    String actValue = editTextActualValue.getText().toString();
//                    double actualValue = 0;
//                    if (!actValue.isEmpty()) {
//                        actualValue = Double.parseDouble(actValue);
//                    }
//
//                    if (qtyName.isEmpty()) {
//                        showErrorDialog("Please Select Quality Parameter");
//                    } else if (actValue.isEmpty()) {
//                        showErrorDialog("Please Enter Actual Value");
//                    } else if (actualValue < minRange) {
//                        showErrorDialog("Should be between Threshold Value");
//                    } else if (actualValue > maxRange) {
//                        showErrorDialog("Should be between Threshold Value");
//                    } else if (edit_item.equalsIgnoreCase("Edit_Quality_Item")) {
//                        edit_item = "";
//                        postQtyDetailDaoList.remove(pos);
//                        postQtyDetailDaoList.add(new PostQtyDetailDao(0, qualityCode, actualValue, 0, "I", thresholdValue, uomvalue, qtyName));
//                        textViewQualityPara.setText("");
//                        textViewThreshold.setText("");
//                        textViewUom.setText("");
//                        editTextActualValue.setText("");
//                        setAdapter();
//
//                    } else if (postQtyDetailDaoList != null && postQtyDetailDaoList.size() > 0) {
//                        if (!contains(postQtyDetailDaoList, qtyName)) {
//                            postQtyDetailDaoList.add(new PostQtyDetailDao(0, qualityCode, actualValue, 0, "I", thresholdValue, uomvalue, qtyName));
//                            textViewQualityPara.setText("");
//                            textViewThreshold.setText("");
//                            textViewUom.setText("");
//                            editTextActualValue.setText("");
//                            setAdapter();
//                        } else {
//                            showErrorDialog("Already Inserted");
//                        }
//
//                    } else {
//
//                        postQtyDetailDaoList.add(new PostQtyDetailDao(0, qualityCode, actualValue, 0, "I", thresholdValue, uomvalue, qtyName));
//                        textViewQualityPara.setText("");
//                        textViewThreshold.setText("");
//                        textViewUom.setText("");
//                        editTextActualValue.setText("");
//                        setAdapter();
//
//
//                    }
//                }else {
//
//                    String qtyName = textViewQualityPara.getText().toString();
//                    String uomvalue = textViewUom.getText().toString();
//                    String thresholdValue = textViewThreshold.getText().toString();
//                    //   int actValue = Integer.parseInt(editTextActualValue.getText().toString());
//
//                    if (qtyName.isEmpty()) {
//                        showErrorDialog("Please Select Quality Parameter");
//                    } else if (spinActValue==0) {
//                        showErrorDialog("Please Select Actual Value");
//                    } else if (edit_item.equalsIgnoreCase("Edit_Quality_Item")) {
//                        edit_item = "";
//                        postQtyDetailDaoList.remove(pos);
//                        postQtyDetailDaoList.add(new PostQtyDetailDao(0, qualityCode, spinActValue, 0, "I", thresholdValue, uomvalue, qtyName));
//                        textViewQualityPara.setText("");
//                        textViewThreshold.setText("");
//                        textViewUom.setText("");
//                        editTextActualValue.setText("");
//                        spinneractual.setSelection(0);
//                        setAdapter();
//
//                    } else if (postQtyDetailDaoList != null && postQtyDetailDaoList.size() > 0) {
//                        if (!contains(postQtyDetailDaoList, qtyName)) {
//                            postQtyDetailDaoList.add(new PostQtyDetailDao(0, qualityCode, spinActValue, 0, "I", thresholdValue, uomvalue, qtyName));
//                            textViewQualityPara.setText("");
//                            textViewThreshold.setText("");
//                            textViewUom.setText("");
//                            spinneractual.setSelection(0);
//                            setAdapter();
//                        } else {
//                            showErrorDialog("Already Inserted");
//                        }
//
//                    } else {
//
//                        postQtyDetailDaoList.add(new PostQtyDetailDao(0, qualityCode, spinActValue, 0, "I", thresholdValue, uomvalue, qtyName));
//                        textViewQualityPara.setText("");
//                        textViewThreshold.setText("");
//                        textViewUom.setText("");
//                        spinneractual.setSelection(0);
//                        setAdapter();
//
//
//                    }
//
//                }
//
//
//
//                /*else{
//
//                    if(edit_item.equalsIgnoreCase("Edit_Quality_Item")){
//                        edit_item="";
//                        bulkQtyDetailDaoList.remove(pos);
//                        bulkQtyDetailDaoList.add(new BulkQtyDetailDao(0,qualityCode,actualValue,0,"I",thresholdValue,uomvalue,qtyName));
//                        textViewQualityPara.setText("");
//                        textViewThreshold.setText("");
//                        textViewUom.setText("");
//                        editTextActualValue.setText("");
//                        setAdapter();
//
//
//                    }else {
//
//                        if(bulkQtyDetailDaoList!=null && bulkQtyDetailDaoList.size()>0){
//                            for (int i=0;i<bulkQtyDetailDaoList.size();i++){
//                                if(!qtyName.equalsIgnoreCase(bulkQtyDetailDaoList.get(i).getQtyName())){
//                                    bulkQtyDetailDaoList.add(new BulkQtyDetailDao(0,qualityCode,actualValue,0,"I",thresholdValue,uomvalue,qtyName));
//                                    textViewQualityPara.setText("");
//                                    textViewThreshold.setText("");
//                                    textViewUom.setText("");
//                                    editTextActualValue.setText("");
//                                    setAdapter();
//                                }else {
//                                    showErrorDialog("Already Inserted");
//                                }
//                            }
//                        }else {
//
//                            bulkQtyDetailDaoList.add(new BulkQtyDetailDao(0,qualityCode,actualValue,0,"I",thresholdValue,uomvalue,qtyName));
//                            textViewQualityPara.setText("");
//                            textViewThreshold.setText("");
//                            textViewUom.setText("");
//                            editTextActualValue.setText("");
//                            setAdapter();
//
//                        }
//
//                    }
//
//
//
//
//
//
//
//                }*/
//
//
//                break;
//            case R.id.txt_quality_para:
//
//                showQualityDialog();
//
//
//                break;
//            case R.id.but_close:
//                List<PostQtyDetailDao> postQtyDetailDaos = new ArrayList<>();
//                for (int i = 0; i < postQtyDetailDaoList.size(); i++) {
//                    Log.e("CHK",""+postQtyDetailDaoList.get(i).getIn_actual_value());
//                    postQtyDetailDaos.add(new PostQtyDetailDao(0, postQtyDetailDaoList.get(i).getIn_qty_code(), postQtyDetailDaoList.get(i).getIn_qty_name(),postQtyDetailDaoList.get(i).getIn_actual_value(), 0, "I"));
//                }
//                Intent intent = new Intent();
//                intent.putParcelableArrayListExtra("QUALITY_DETAIL_LIST", (ArrayList<? extends Parcelable>) postQtyDetailDaos);
//                intent.putParcelableArrayListExtra("QUALITY_DETAIL_QTY_NAME_LIST", (ArrayList<? extends Parcelable>) postQtyDetailDaoList);
//                setResult(QUALITY_PARA_VALUE, intent);
//                finish();
//                break;
//            case R.id.but_cancel:
//                finish();
//                break;
//            default:
//                break;
//        }
//    }
//
//    private boolean contains(List<PostQtyDetailDao> list, String name) {
//        for (PostQtyDetailDao item : list) {
//            if (item.getIn_qty_name().equals(name)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void setAdapter() {
//        qualityListParameterAdapter = new QualityListParameterAdapter(mContext, postQtyDetailDaoList, this);
//        recyclerViewQuality.setAdapter(qualityListParameterAdapter);
//        qualityListParameterAdapter.notifyDataSetChanged();
//    }
//
//    private void showQualityDialog() {
//
//        dialog = new Dialog(mContext);
//        dialog.setContentView(R.layout.custom_quality_para_dialog);
//        //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
//        dialog.setTitle("Title...");
//        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
//        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
//        final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.recycle_quality);
//        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        dialog.getWindow().setLayout(width, height);
//
//        final SearchView elc = (SearchView) dialog.findViewById(R.id.searchQuality);
//
//
//
//        final QulaityParameterActivity.MyRecyclerViewAdapterQulaity adapterf = new QulaityParameterActivity.MyRecyclerViewAdapterQulaity(mContext, spmDetailDaoList);
//        recyclerView.setAdapter(adapterf);
//
//        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
//
//        elc.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (adapterf != null) {
//                    adapterf.filter(newText);
//                }
//
//                return false;
//            }
//        });
//
//        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isNetwork == true) {
//                    callQualityParameterJsonDetails();
//                }else {
//                    spmDetailDaoList=db.getSpmDetailsListRelatedPawhsCode(productCode);
//
//                }
//                //  callQualityParameterJsonDetails();
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//
//    }
//
//    @Override
//    public void editItem(int position, String edititem) {
//        edit_item = edititem;
//        pos = position;
//        textViewQualityPara.setText(postQtyDetailDaoList.get(position).getIn_qty_name());
//        textViewUom.setText(postQtyDetailDaoList.get(position).getUom());
//        textViewThreshold.setText(postQtyDetailDaoList.get(position).getThresHoldValue());
//        // editTextActualValue.setText("" + postQtyDetailDaoList.get(position).getIn_actual_value());
//        qualityCode = postQtyDetailDaoList.get(position).getIn_qty_code();
//
//        String test1 = textViewThreshold.getText().toString();
//        if(test1.contains("to")){
//            editTextActualValue.setVisibility(View.VISIBLE);
//            spinneractual.setVisibility(View.GONE);
//            String[] items = Utility.splitRange(test1);
//            String min = items[0];
//            min = min.replace(" ", "");
//            String max = items[1];
//            max = max.replace(" ", "");
//            minRange = Integer.parseInt(min);
//            maxRange = Integer.parseInt(max);
//            editTextActualValue.setText("" + postQtyDetailDaoList.get(position).getIn_actual_value());
//        }else {
//            editTextActualValue.setVisibility(View.GONE);
//            spinneractual.setVisibility(View.VISIBLE);
//            /*rangeName = Utility.splitRangeName(test1);
//            for (int i=0;i<rangeName.length;i++) {
//                rangeNameDaoList.add(new RangeNameDao(rangeName[i],i+1));
//            }*/
//            spinneractual.setSelection((int) postQtyDetailDaoList.get(pos).getIn_actual_value());
//        }
//
//       /* if(postQtyDetailDaoList.get(pos).getIn_qty_name().equalsIgnoreCase("LIVE")){
//            spinneractual.setVisibility(View.VISIBLE);
//            editTextActualValue.setVisibility(View.GONE);
//            spinneractual.setSelection(postQtyDetailDaoList.get(pos).getIn_actual_value());
//        }else {
//            spinneractual.setVisibility(View.GONE);
//            editTextActualValue.setVisibility(View.VISIBLE);
//            editTextActualValue.setText("" + postQtyDetailDaoList.get(position).getIn_actual_value());
//        }*/
//
//    }
//
//    @Override
//    public void deleteItem(int position) {
//        postQtyDetailDaoList.remove(position);
//        qualityListParameterAdapter.notifyDataSetChanged();
//    }
//
//    public class MyRecyclerViewAdapterQulaity extends RecyclerView.Adapter<QulaityParameterActivity.MyRecyclerViewAdapterQulaity.ViewHolder> {
//
//        private List<SpmDetailDao> mData;
//        private LayoutInflater mInflater;
//        private List<SpmDetailDao> arraylist;
//
//
//        // data is passed into the constructor
//        public MyRecyclerViewAdapterQulaity(Context context, List<SpmDetailDao> data) {
//            this.mInflater = LayoutInflater.from(context);
//            this.mData = data;
//            this.arraylist = new ArrayList<SpmDetailDao>();
//            this.arraylist.addAll(QulaityParameterActivity.spmDetailDaoList);
//        }
//
//        @NonNull
//        @Override
//        public QulaityParameterActivity.MyRecyclerViewAdapterQulaity.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = mInflater.inflate(R.layout.custom_recyclerview_quality, parent, false);
//            return new QulaityParameterActivity.MyRecyclerViewAdapterQulaity.ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull QulaityParameterActivity.MyRecyclerViewAdapterQulaity.ViewHolder holder, int position) {
//            final SpmDetailDao pojofar = mData.get(position);
//            holder.textViewQtyName.setText(pojofar.getIn_maize_name());
//            holder.textViewRange.setText(pojofar.getIn_range());
//            holder.textViewUnit.setText(pojofar.getIn_maize_unit());
//
//            holder.llist.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    quality = pojofar.getIn_maize_name();
//                    qualityCode = pojofar.getIn_maize_code();
//                    range = pojofar.getIn_range();
//                    unit = pojofar.getIn_maize_unit();
//
//                    textViewQualityPara.setText(quality);
//                    textViewUom.setText(unit);
//                    textViewThreshold.setText(range);
//
//                    String test1 = textViewThreshold.getText().toString();
//                    if(test1.contains("to")){
//                        editTextActualValue.setVisibility(View.VISIBLE);
//                        spinneractual.setVisibility(View.GONE);
//                        String[] items = Utility.splitRange(test1);
//                        String min = items[0];
//                        min = min.replace(" ", "");
//                        String max = items[1];
//                        max = max.replace(" ", "");
//                        minRange = Integer.parseInt(min);
//                        maxRange = Integer.parseInt(max);
//                    }else {
//                        editTextActualValue.setVisibility(View.GONE);
//                        spinneractual.setVisibility(View.VISIBLE);
//                        rangeName = Utility.splitRangeName(test1);
//                        for (int i=0;i<rangeName.length;i++) {
//                            rangeNameDaoList.add(new RangeNameDao(rangeName[i],i+1));
//                        }
//                    }
//
//
//                    //callQualityParameterJsonDetails();
//                    if (isNetwork == true) {
//                        callQualityParameterJsonDetails();
//                    }else {
//                        spmDetailDaoList=db.getSpmDetailsListRelatedPawhsCode(productCode);
//
//                    }
//
//                    dialog.dismiss();
//                }
//            });
//        }
//
//        public void filter(String charText) {
//            charText = charText.toLowerCase(Locale.getDefault());
//            QulaityParameterActivity.spmDetailDaoList.clear();
//            if (charText.length() == 0) {
//                QulaityParameterActivity.spmDetailDaoList.addAll(arraylist);
//            } else {
//                for (SpmDetailDao wp : arraylist) {
//                    if (wp.getIn_maize_name().toLowerCase(Locale.getDefault()).contains(charText)) {
//                        QulaityParameterActivity.spmDetailDaoList.add(wp);
//                    }
//                }
//            }
//            notifyDataSetChanged();
//        }
//
//        @Override
//        public int getItemCount() {
//            return mData.size();
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            TextView textViewQtyName, textViewRange, textViewUnit;
//            LinearLayout llist;
//
//            public ViewHolder(@NonNull View itemView) {
//                super(itemView);
//                textViewQtyName = itemView.findViewById(R.id.quality_name);
//                textViewRange = itemView.findViewById(R.id.range);
//                textViewUnit = itemView.findViewById(R.id.unit);
//
//                llist = itemView.findViewById(R.id.llist);
//            }
//        }
//    }
//
//    private void showErrorDialog(String s) {
//        new AlertDialog.Builder(this)
//                .setTitle("Info!")
//                .setMessage(s)
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        dialog.dismiss();
//                    }
//                })
//                .show();
    }
}
