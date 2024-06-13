package cdfi.fintantra.meity.Pawhs.recactpurchase;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class OtherCostActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.other_cost_screen);
//        mContext = this;
//        pawhsApplication = PAWHSApplication.getGetInstance();
//        userName = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.IN_USER_NAME);
//
//        orgnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_ID);
//        locnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCN_ID);
//        userId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.USER_ID);
//        localeId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCALE_ID);
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            postFilterOtherDetailDaoList=bundle.getParcelableArrayList("OTHER_DETAIL_LIST");
//        }
//
//        initView();
//
//    }
//
//    private void initView() {
//        mAPIService = ApiUtils.getAPIService();
//        textViewTitle = (TextView) findViewById(R.id.txt_title);
//        textViewTitle.setText("Welcome " + userName + "\n" + "PA Record Actual Purchase");
//
//        editTextAmount = (EditText) findViewById(R.id.edt_amount);
//        spinnerOtherCost = (Spinner) findViewById(R.id.spin_other_cost);
//        buttonAdd = (Button) findViewById(R.id.but_add);
//        buttonClose = (Button) findViewById(R.id.but_close);
//        buttonCancel = (Button) findViewById(R.id.but_cancel);
//
//        recyclerViewOtherCost = (RecyclerView) findViewById(R.id.recycle_othercost);
//        recyclerViewOtherCost.setLayoutManager(new LinearLayoutManager(mContext));
//        editTextAmount.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.toString().length() == 1 && s.toString().startsWith("0")) {
//                    s.clear();
//                }
//            }
//        });
//        lsCostDaoList = new ArrayList<>();
//        lsCostDaoList.add(new LsCostDao("0", "Select Cost"));
//        lsCostDaoList.add(new LsCostDao("1", "Packing Cost"));
//        lsCostDaoList.add(new LsCostDao("2", "Transportation Cost"));
//        lsCostDaoList.add(new LsCostDao("3", "UnPacking Cost"));
//        lsCostDaoList.add(new LsCostDao("4", "Local Packing Cost"));
//        lsCostDaoList.add(new LsCostDao("5", "Local Transportation Cost"));
//
//        otherCostSpinnerAdapter = new OtherCostSpinnerAdapter(mContext, lsCostDaoList);
//        spinnerOtherCost.setAdapter(otherCostSpinnerAdapter);
//
//        postOtherDetailDao = new PostOtherDetailDao();
//        postOtherDetailDaoList = new ArrayList<>();
//        tempOtherDetailDaoList = new ArrayList<>();
//
//        buttonAdd.setOnClickListener(this);
//        buttonCancel.setOnClickListener(this);
//        buttonClose.setOnClickListener(this);
//
//        if(postFilterOtherDetailDaoList!=null && postFilterOtherDetailDaoList.size()>0){
//
//            for(int i=0;i<postFilterOtherDetailDaoList.size();i++){
//                if(postFilterOtherDetailDaoList.get(i).getIn_packaging_cost()>0) {
//                    tempOtherDetailDaoList.add(new TempOtherDetailDao("Packing Cost", String.valueOf(postFilterOtherDetailDaoList.get(i).getIn_packaging_cost())));
//                }
//
//                if(postFilterOtherDetailDaoList.get(i).getIn_transporting_cost()>0) {
//                    tempOtherDetailDaoList.add(new TempOtherDetailDao("Transportation Cost", String.valueOf(postFilterOtherDetailDaoList.get(i).getIn_transporting_cost())));
//                }
//                if(postFilterOtherDetailDaoList.get(i).getIn_unpacking_cost()>0) {
//                    tempOtherDetailDaoList.add(new TempOtherDetailDao("UnPacking Cost", String.valueOf(postFilterOtherDetailDaoList.get(i).getIn_unpacking_cost())));
//                }
//                if(postFilterOtherDetailDaoList.get(i).getIn_local_packaging_cost()>0) {
//                    tempOtherDetailDaoList.add(new TempOtherDetailDao("Local Packing Cost", String.valueOf(postFilterOtherDetailDaoList.get(i).getIn_local_packaging_cost())));
//                }
//                if(postFilterOtherDetailDaoList.get(i).getIn_local_transporting_cost()>0) {
//                    tempOtherDetailDaoList.add(new TempOtherDetailDao("Local Transportation Cost", String.valueOf(postFilterOtherDetailDaoList.get(i).getIn_local_transporting_cost())));
//                }
//
//            }
//
//
//        }
//
//        if(tempOtherDetailDaoList!=null && tempOtherDetailDaoList.size()>0){
//            setAdapter();
//        }
//
//        spinnerOtherCost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                //Toast.makeText(mContext, lsCostDaoList.get(i).getCostId(), Toast.LENGTH_SHORT).show();
//                costId = lsCostDaoList.get(i).getCostId();
//                costName = lsCostDaoList.get(i).getCostName();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }
//
//    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//        switch (id) {
//            case R.id.but_add:
//                addCostAmount();
//
//                break;
//            case R.id.but_close:
//
//                //Log.e("HELLO",""+tempOtherDetailDaoList.size());
//                if (tempOtherDetailDaoList != null && tempOtherDetailDaoList.size() > 0) {
//                    for (int i = 0; i < tempOtherDetailDaoList.size(); i++) {
//                        postOtherDetailDao.setIn_otherdtl_row_id(0);
//                        postOtherDetailDao.setIn_temp_cost(0);
//                        postOtherDetailDao.setIn_temp_cost1(0);
//                        postOtherDetailDao.setIn_temp_cost2(0);
//                        postOtherDetailDao.setIn_mode_flag("I");
//                        if (tempOtherDetailDaoList.get(i).getName().equalsIgnoreCase("Packing Cost")) {
//                            postOtherDetailDao.setIn_packaging_cost(Integer.parseInt(tempOtherDetailDaoList.get(i).getAmount()));
//                        } else if (tempOtherDetailDaoList.get(i).getName().equalsIgnoreCase("Transportation Cost")) {
//                            postOtherDetailDao.setIn_transporting_cost(Integer.parseInt(tempOtherDetailDaoList.get(i).getAmount()));
//                        } else if (tempOtherDetailDaoList.get(i).getName().equalsIgnoreCase("UnPacking Cost")) {
//                            postOtherDetailDao.setIn_unpacking_cost(Integer.parseInt(tempOtherDetailDaoList.get(i).getAmount()));
//                        } else if (tempOtherDetailDaoList.get(i).getName().equalsIgnoreCase("Local Packing Cost")) {
//                            postOtherDetailDao.setIn_local_packaging_cost(Integer.parseInt(tempOtherDetailDaoList.get(i).getAmount()));
//                        } else if (tempOtherDetailDaoList.get(i).getName().equalsIgnoreCase("Local Transportation Cost")) {
//                            postOtherDetailDao.setIn_local_transporting_cost(Integer.parseInt(tempOtherDetailDaoList.get(i).getAmount()));
//                        }
//
//                    }
//                }
//
//
//
//                postOtherDetailDaoList.add(postOtherDetailDao);
//
//                Intent intent = new Intent();
//                intent.putParcelableArrayListExtra("OTHER_COST_LIST", (ArrayList<? extends Parcelable>) postOtherDetailDaoList);
//                intent.putParcelableArrayListExtra("OTHER_TEMP_COST_LIST", (ArrayList<? extends Parcelable>) tempOtherDetailDaoList);
//                setResult(OTHER_COST_VALUE, intent);
//                finish();
//                break;
//            case R.id.but_cancel:
//                finish();
//                break;
//            default:
//                break;
//
//        }
//    }
//
//    private void addCostAmount() {
//        String amount = editTextAmount.getText().toString();
//        String costname = costName;
//
//        if (amount.isEmpty()) {
//            Toast.makeText(mContext, "Please Enter Amount", Toast.LENGTH_SHORT).show();
//        } else if (costId.equalsIgnoreCase("0")) {
//            Toast.makeText(mContext, "Please Select Cost", Toast.LENGTH_SHORT).show();
//        } else if (tempOtherDetailDaoList != null && tempOtherDetailDaoList.size() > 0) {
//            if (!contains(tempOtherDetailDaoList, costname)) {
//                tempOtherDetailDaoList.add(new TempOtherDetailDao(costname, amount));
//                editTextAmount.setText("");
//                spinnerOtherCost.setSelection(0);
//                setAdapter();
//            } else {
//                showErrorDialog("Already Inserted");
//            }
//
//        } else {
//
//            tempOtherDetailDaoList.add(new TempOtherDetailDao(costname, amount));
//            editTextAmount.setText("");
//            spinnerOtherCost.setSelection(0);
//            setAdapter();
//
//        }
//
//
//    }
//
//    private boolean contains(List<TempOtherDetailDao> list, String name) {
//        for (TempOtherDetailDao item : list) {
//            if (item.getName().equals(name)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void setAdapter() {
//
//        otherCostAdapter = new OtherCostAdapter(mContext, tempOtherDetailDaoList, this);
//        recyclerViewOtherCost.setAdapter(otherCostAdapter);
//        otherCostAdapter.notifyDataSetChanged();
//
//    }
//
//    @Override
//    public void deleteItem(int position) {
//
//        tempOtherDetailDaoList.remove(position);
//        otherCostAdapter.notifyDataSetChanged();
//
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
