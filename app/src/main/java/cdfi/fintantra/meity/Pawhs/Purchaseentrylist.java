package cdfi.fintantra.meity.Pawhs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cdfi.fintantra.meity.ExceptionHandler;
import cdfi.fintantra.meity.Pojokyc;
import cdfi.fintantra.meity.Pojope;
import cdfi.fintantra.meity.R;
import cdfi.fintantra.meity.Pawhs.api.ApiService;
import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.EstimateListDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.SingleLotnoDao;
import cdfi.fintantra.meity.Pawhs.recactpurchase.RecActualPurchaseActivity;
import cdfi.fintantra.meity.Pawhs.utils.Utility;

public class Purchaseentrylist extends AppCompatActivity {

    private SQLiteDataBaseHandler db;
    private PAWHSApplication pawhsApplication;
    private ApiService mAPIService;
    ProgressDialog progressDialog;
    boolean isNetwork;
    JSONObject userd;
    String qpdv="";
    ArrayList pelist = new ArrayList();
    String v="0",v2="0",v3="0",v4="0";
    private String orgnId, locnId, userId, localeId,orgnCode,userName;
    private RelativeLayout progressLayout;
    private List<EstimateListDao> estimateListDaoList;
    private List<SingleLotnoDao> singleLotnoDaoList;
    TextView textViewTitle;
    SQLiteDatabase dbs;
    MyRecyclerViewAdapterLotNo adapterf;
    private SearchView searchViewFarmerName;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_purchaseentrylist);
        db = new SQLiteDataBaseHandler(this);
        getSupportActionBar().hide();
        mAPIService = ApiUtils.getAPIService();
        pawhsApplication = PAWHSApplication.getGetInstance();
        textViewTitle = (TextView) findViewById(R.id.txt_title);
        dbs = db.getWritableDatabase();
        userName = pawhsApplication.getPreferenceFromString(this, ApiUtils.IN_USER_NAME);
        textViewTitle.setText("Welcome " + userName + "\n" + " Purchase Entry");
        searchViewFarmerName=(SearchView) findViewById(R.id.searchFarmerName);
        orgnId = pawhsApplication.getPreferenceFromString(this, ApiUtils.ORGN_ID);
        locnId = pawhsApplication.getPreferenceFromString(this, ApiUtils.LOCN_ID);
        userId = pawhsApplication.getPreferenceFromString(this, ApiUtils.USER_ID);
        localeId = pawhsApplication.getPreferenceFromString(this, ApiUtils.LOCALE_ID);
        orgnCode = pawhsApplication.getPreferenceFromString(this, ApiUtils.ORGN_CODE);
        progressLayout = (RelativeLayout) findViewById(R.id.progressLayout);
        estimateListDaoList =new ArrayList<>();
        singleLotnoDaoList=new ArrayList<>();
        recyclerView =findViewById(R.id.recycle_lotno);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        isNetwork = Utility.checkConnectivity(getApplicationContext());
//        if (isNetwork == true) {
//            getLotNoListDetails();
//        }else {
//            estimateListDaoList=db.getAllEstimateList();
//            final androidx.recyclerview.widget.RecyclerView recyclerView =findViewById(R.id.recycle_lotno);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            List<EstimateListDao> estimateListDaos=estimateListDaoList;
//            singleLotnoDaoList.clear();
//            for(int i=0;i<estimateListDaos.size();i++){
//                singleLotnoDaoList.add(new SingleLotnoDao(estimateListDaos.get(i).getOut_Estm_rowid(),estimateListDaos.get(i).getOut_LotNo(),
//                        estimateListDaos.get(i).getOut_agg_code(),estimateListDaos.get(i).getOut_Farmer_Code(),estimateListDaos.get(i).getOut_Farmer_Name(),
//                        estimateListDaos.get(i).getOut_Member_Type(),estimateListDaos.get(i).getOut_Item_Code(),estimateListDaos.get(i).getOut_Item_Name(),
//                        Double.parseDouble(estimateListDaos.get(i).getOut_Estimated_Qty()),Double.parseDouble(estimateListDaos.get(i).getOut_Estimated_Price()),Double.parseDouble(estimateListDaos.get(i).getOut_Estimated_Value()),
//                        estimateListDaos.get(i).getOut_Estimated_PickupDate(),estimateListDaos.get(i).getOut_Remarks(),estimateListDaos.get(i).getOut_Status()));
//            }
//
//            adapterf = new MyRecyclerViewAdapterLotNo(this, singleLotnoDaoList);
//            recyclerView.setAdapter(adapterf);
//        }
        loadpurchaselist();
         //
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

//        progressLayout.setVisibility(View.VISIBLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                // Actions to do after 10 seconds
//                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//                progressLayout.setVisibility(View.GONE);
//            }
//        }, 40000);

    }

    //    private void getLotNoListDetails() {
//        /*progressLayout.setVisibility(View.VISIBLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//        LotNoContextDao lotNoContextDao = new LotNoContextDao();
//        lotNoContextDao.setOrgnId(orgnCode);
//        lotNoContextDao.setLocnId(locnId);
//        lotNoContextDao.setUserId(userId);
//        lotNoContextDao.setLocaleId(localeId);
//        lotNoContextDao.setFilterBy_Option("ALL");
//        lotNoContextDao.setFilterBy_Code(".");
//        lotNoContextDao.setFilterBy_FromValue(".");
//        lotNoContextDao.setFilterBy_ToValue(".");
//
//        mAPIService.getEstProcLotNOList(lotNoContextDao)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<NewEstimateProcurementListDao>() {
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
//                    public void onNext(NewEstimateProcurementListDao newEstimateProcurementListDao) {
//                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//                        progressLayout.setVisibility(View.GONE);
//                        lotnoDaoList=newEstimateProcurementListDao.getLotNoContextDao().getLotnoDaoList();
//
//                    }
//                });*/
//
////        progressLayout.setVisibility(View.VISIBLE);
////        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
////                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//        EstActAppContextDao estActAppContextDao = new EstActAppContextDao();
//        estActAppContextDao.setOrgnId(orgnCode);
//        //  estActAppContextDao.setLocnId(locnId);
//        estActAppContextDao.setLocnId(ApiUtils.instance);
//        estActAppContextDao.setUserId(userId);
//        estActAppContextDao.setLocaleId(localeId);
//        estActAppContextDao.setStatus("EstimateApproved");
//
//        mAPIService.getEstProcLotNOListDetails(estActAppContextDao)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<EstimateActualApprovedListDao>() {
//                    @Override
//                    public void onCompleted() {
//                        // progressLayout.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(EstimateActualApprovedListDao estimateActualApprovedListDao) {
//                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//                        estimateListDaoList =estimateActualApprovedListDao.getEstActAppContextDao().getEstimateListDaoList();
//                        final androidx.recyclerview.widget.RecyclerView recyclerView =findViewById(R.id.recycle_lotno);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(Purchaseentrylist.this));
//                        List<EstimateListDao> estimateListDaos=estimateListDaoList;
//                        singleLotnoDaoList.clear();
//                        for(int i=0;i<estimateListDaos.size();i++) {
//                            if (estimateListDaos.get(i).getOut_Status().equalsIgnoreCase("Estimate")) {
//                                singleLotnoDaoList.add(new SingleLotnoDao(estimateListDaos.get(i).getOut_Estm_rowid(), estimateListDaos.get(i).getOut_LotNo(),
//                                        estimateListDaos.get(i).getOut_agg_code(), estimateListDaos.get(i).getOut_Farmer_Code(), estimateListDaos.get(i).getOut_Farmer_Name(),
//                                        estimateListDaos.get(i).getOut_Member_Type(), estimateListDaos.get(i).getOut_Item_Code(), estimateListDaos.get(i).getOut_Item_Name(),
//                                        Double.parseDouble(estimateListDaos.get(i).getOut_Estimated_Qty()), Double.parseDouble(estimateListDaos.get(i).getOut_Estimated_Price()), Double.parseDouble(estimateListDaos.get(i).getOut_Estimated_Value()),
//                                        estimateListDaos.get(i).getOut_Estimated_PickupDate(), estimateListDaos.get(i).getOut_Remarks(), estimateListDaos.get(i).getOut_Status()));
//                                callsingleest(estimateListDaos.get(i).getOut_Estm_rowid(),estimateListDaos.get(i).getOut_LotNo());
//                            }
//                        }
//
//                        adapterf = new MyRecyclerViewAdapterLotNo(Purchaseentrylist.this, singleLotnoDaoList);
//                        recyclerView.setAdapter(adapterf);
//
//
//
//
//                    }
//                });
//    }
    public  class MyRecyclerViewAdapterLotNo extends RecyclerView.Adapter<MyRecyclerViewAdapterLotNo.ViewHolder>  {
        private List<Pojope> mData;
        private LayoutInflater mInflater;
        private List<Pojope> arraylist;

        // data is passed into the constructor
        public MyRecyclerViewAdapterLotNo(Context context, List<Pojope> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
            this.arraylist = new ArrayList<Pojope>();
            this.arraylist.addAll(data);
        }

        @NonNull
        @Override
        public MyRecyclerViewAdapterLotNo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.custom_recyclerview_lotno, parent, false);
            return new MyRecyclerViewAdapterLotNo.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyRecyclerViewAdapterLotNo.ViewHolder holder, int position) {
            final Pojope pojofar = mData.get(position);

            //Log.e("CHK",""+pojofar.getOut_Status());


            holder.txtlotNo.setText(pojofar.getLn());
            holder.txtFName.setText(pojofar.getFn());
            holder.txtFCode.setText(pojofar.getFc());
            holder.txtMember.setText(pojofar.getMn());
            holder.txtICode.setText(pojofar.getIc());
            holder.txtIName.setText(pojofar.getPn());
            holder.txtQty.setText(""+pojofar.getQty());
            holder.txtPrice.setText(""+pojofar.getPr());
            holder.txtnob.setText(pojofar.getLrp());
            final SQLiteDatabase dbs = db.getWritableDatabase();


            Cursor cursor = dbs.query("qparpe", new String[]{"v1","v2","v3"
                    }, "v1" + "=? COLLATE NOCASE",
                    new String[]{pojofar.getQc()}, null, null, null, null);
            Log.e("COUNT", "" + cursor.getCount());
            //textViewQualityPara.setText("" + cursor.getCount());
            //textViewSINo.setText("" + cursor.getCount()+"(Serial Bags)");

            StringBuilder s = new StringBuilder(10000);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    String[] pn = cursor.getString(1).split("-");
                    s.append(pn[1]+"-"+cursor.getString(2)+" \n");
                    holder.txtqc.setText(s);


                    //


                } while (cursor.moveToNext());

            }
            Cursor cursoruom = dbs.query("ProductMasterAllProduct", new String[]{"out_uomtype_code"
                    }, "PmapOutProductCode" + "=?",
                    new String[]{pojofar.getIc()}, null, null, null, null);

            if(cursoruom.moveToFirst())
            {
                holder.txtuom.setText(cursoruom.getString(0));
            }
            holder.llist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Pojokyc.purno = pojofar.getLn();

                    Intent intent=new Intent(getApplicationContext(), RecActualPurchaseActivity.class);
                    intent.putExtra("Frm", "Actual");
                    intent.putExtra("PEID", pojofar.getId());
                    startActivity(intent);
                    //finish();

                }
            });




        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
        public  void filter(String charText) {
            Log.e("Hello","hi");
            charText = charText.toLowerCase(Locale.getDefault());
            mData.clear();
            if (charText.length() == 0) {
                mData.addAll(arraylist);
            } else {
                for (Pojope pojope : arraylist) {
                    if (pojope.getFn().toLowerCase(Locale.getDefault()).contains(charText)) {
                        mData.add(pojope);
                    }
                }
            }
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView txtlotNo,txtFName,txtFCode, txtMember, txtICode, txtIName, txtQty,txtPrice,txtStatus,txtuom,txtqc,txtnob;
            LinearLayout llist;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtlotNo = itemView.findViewById(R.id.txt_ln);
                txtFName = itemView.findViewById(R.id.txt_fn);
                txtFCode = itemView.findViewById(R.id.txt_fc);
                txtMember = itemView.findViewById(R.id.txt_mt);
                txtICode = itemView.findViewById(R.id.txt_ic);
                txtIName = itemView.findViewById(R.id.txt_in);
                txtQty = itemView.findViewById(R.id.txt_qty);
                txtqc = itemView.findViewById(R.id.txt_qc);
                txtPrice = itemView.findViewById(R.id.txt_price);
                txtStatus = itemView.findViewById(R.id.txt_status);
                llist = itemView.findViewById(R.id.llist);
                txtuom = itemView.findViewById(R.id.txt_inn);
                txtnob = itemView.findViewById(R.id.txt_nob);
            }
        }

    }
//    public void callsingleest(int rid,String lotno)
//    {
//
//        SQLiteDatabase dbs = db.getWritableDatabase();
//        dbs.execSQL("delete from SubmitRecEstPurchase");
//        try {
//
//
////            DecimalFormat amountFormate = new DecimalFormat("############.##");
////            amountFormate.setMinimumFractionDigits(2);
////            amountFormate.setMaximumFractionDigits(2);
////
////            Date cc = Calendar.getInstance().getTime();
////            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//
//            userd = new JSONObject();
//
//            userd = new JSONObject();
//            userd.put("orgnId", orgnCode);
//            userd.put("locnId", ApiUtils.instance);
//            userd.put("userId", userId);
//            userd.put("localeId", localeId);
//            userd.put("in_Estm_rowid", rid);
//            userd.put("in_LotNo", lotno);
//
//            Log.e("OUTPUT", "" + userd.toString());
//
//        } catch (Exception e) {
//            Log.e("OUTPUT", "" + e.getMessage());
//        }
//
//
////        pdialog.setCanceledOnTouchOutside(false);
////        pdialog.setTitle("Uploading Please Wait.......");
////        pdialog.show();
//
//        //169.38.77.190:101/api/Mobile_FDR/Farmermaster
//        //15.206.21.248:27/Farmermaster
//        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, ApiUtils.TEST_URL_API+"PAWHS_NewEstimated_Procurment/pawhs_NewEstimate_Proc_single", userd,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.e("CCCC2", "" + response);
//                        try {
//                            JSONObject obj = response.getJSONObject("context");
//                            JSONObject obj2 = obj.getJSONObject("header");
//
//                            Log.e("Farmer name",""+obj2.getString("in_Farmer_Name"));
//                            JSONArray cast = obj.getJSONArray("qualityDt");
//
//                            if(cast.length()>0) {
//                                for (int i = 0; i < cast.length(); i++) {
//
//
//                                    JSONObject actor = cast.getJSONObject(i);
//
//
//                                    String n1 = actor.getString("out_qty_row_id");
//                                    String n2 = actor.getString("out_agg_code");
//                                    String n3 = actor.getString("out_lotno");
//                                    String n4 = actor.getString("out_qlt_code");
//                                    String n5 = actor.getString("out_Qlt_name");
//                                    String n6 = actor.getString("out_Qlt_value");
//
//                                    Log.e("Table2", "" + n1);
//
//                                    if(actor.getString("out_Qlt_name").equalsIgnoreCase("Moisture"))
//                                    {
//                                        v=actor.getString("out_Qlt_value");
//                                    }
//                                    if(actor.getString("out_Qlt_name").equalsIgnoreCase("Live Insect"))
//                                    {
//                                        v2=actor.getString("out_Qlt_value");
//                                    }
//                                    if(actor.getString("out_Qlt_name").equalsIgnoreCase("Fungus"))
//                                    {
//                                        v3=actor.getString("out_Qlt_value");
//                                    }
//                                    if(actor.getString("out_Qlt_name").equalsIgnoreCase("Damage"))
//                                    {
//                                        v4=actor.getString("out_Qlt_value");
//                                    }
//
//
//
//
//                                }
//                                qpdv ="Moisture-"+v+"@Live Insect-"+v2+"@Fungus-"+v3+"@Damage-"+v4;
//
//                            }
//                            Log.e("QPDV",""+qpdv);
//
//                            SubmitRecEstPurchaseDao submitRecEstPurchaseDao = new SubmitRecEstPurchaseDao(1, obj2.getString("in_Farmer_Code"), obj2.getString("in_Farmer_Name"), obj2.getString("in_Member_Type"), obj2.getString("in_Item_Code"), obj2.getString("in_Item_Name"),
//                                    obj2.getString("in_Estimated_Qty"), obj2.getString("in_Estimated_Price"), obj2.getString("in_Estimated_Value"), obj2.getString("in_Estimated_PickDate"), obj2.getString("in_Remarks"), obj2.getString("in_LotNo"), obj2.getString("in_Estm_rowid"), obj2.getString("in_Estimated_PickDate"), "U", "NO","",obj2.getString("in_variety_status"),qpdv,obj2.getString("in_Estimated_Status"),obj2.getString("in_LRP_Name"),obj2.getString("in_LRP_Mobile_no"));
//                            db.addAllSubmitRecEstPurChase(submitRecEstPurchaseDao);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//
//
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("CCCC", "" + error);
//
//                        //on error storing the name to sqlite with status unsynced
//                        // Toast.makeText(Demo.this, "Farmer "+n+" SuccessFull Added to Sync List" , Toast.LENGTH_SHORT).show();
//
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//
//                return params;
//            }
//        };
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                1500000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
////        SubmitRecEstPurchaseDao submitRecEstPurchaseDao = new SubmitRecEstPurchaseDao(1, farmerCode, farmerName, memberType, productCode, productName,
////                qty, price, value, estDate, remarks, "", "0", estDate, "I", "No",encodedImage,vst2,qpdv,radiovalue,lrpn,lrpmn);
////        db.addAllSubmitRecEstPurChase(submitRecEstPurchaseDao);
//    }

    public void loadpurchaselist(){
        try{
            String selectQuery = "SELECT  * FROM purchaseentry";

            Cursor cursor = dbs.rawQuery(selectQuery, null);
            pelist.clear();

            if(cursor.moveToFirst())
            {

                do
                {
                    Pojope pojope = new Pojope();

                    pojope.setId(cursor.getString(0));
                    pojope.setFc(cursor.getString(1));
                    pojope.setFn(cursor.getString(2));
                    pojope.setMn(cursor.getString(3));
                    pojope.setPn(cursor.getString(5));
                    pojope.setIc(cursor.getString(4));
                    pojope.setQty(cursor.getString(6));
                    pojope.setLn(cursor.getString(23));
                    pojope.setLrp(cursor.getString(9));
                    pojope.setPr(cursor.getString(7));
                    pojope.setQc(cursor.getString(21));
                    pelist.add(pojope);

                    // total number of textviews to add

                    adapterf = new MyRecyclerViewAdapterLotNo(this, pelist);
                    recyclerView.setAdapter(adapterf);
                }while(cursor.moveToNext());

            }
        }catch (Exception er){
            Log.e("error",er.toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadpurchaselist();
    }
}