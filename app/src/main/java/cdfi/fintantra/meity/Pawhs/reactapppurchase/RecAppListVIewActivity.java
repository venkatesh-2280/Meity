package cdfi.fintantra.meity.Pawhs.reactapppurchase;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecAppListVIewActivity extends AppCompatActivity  {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.rec_approve_view);
//        mContext = this;
//
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            checkStatus = bundle.getString(ApiUtils.CHECK_STATUS);
//        }
//        pawhsApplication = PAWHSApplication.getGetInstance();
//        userName = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.IN_USER_NAME);
//
//        orgnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_ID);
//        locnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCN_ID);
//        userId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.USER_ID);
//        localeId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCALE_ID);
//        orgnCode = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_CODE);
//
//        db = new SQLiteDataBaseHandler(mContext);
//
//        initView();
//    }
//
//    private void initView() {
//
//        isNetwork = Utility.checkConnectivity(getApplicationContext());
//        mAPIService = ApiUtils.getAPIService();
//        textViewTitle = (TextView) findViewById(R.id.txt_title);
//        if(checkStatus.equalsIgnoreCase("approve")){
//            textViewTitle.setText("Welcome " + userName + "\n" + "PA Record Approve Purchase");
//        }else {
//            textViewTitle.setText("Welcome " + userName + "\n" + "PA Approve WareHouse Purchase");
//        }
//
//
//        progressLayout = (RelativeLayout) findViewById(R.id.progressLayout);
//        recyclerViewRecAppPurchase=(RecyclerView)findViewById(R.id.recycle_recapppurchse);
//        recyclerViewRecAppPurchase.setLayoutManager(new LinearLayoutManager(mContext));
//        searchViewFarmerName=(SearchView) findViewById(R.id.searchFarmerName);
//        ImageView searchIcon = searchViewFarmerName.findViewById(androidx.appcompat.R.id.search_close_btn);
//        searchIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_baseline_close_24));
//
//        actualandApproveListDaoList =new ArrayList<>();
//
//        if(isNetwork==true){
//            getLotNoListDetails();
//        }else {
//            if(checkStatus.equalsIgnoreCase("approve")){
//                actualandApproveListDaoList=db.getOfflineApproveListDetails();
//                setAdapter();
//            }else {
//                actualandApproveListDaoList=db.getOfflineWareHouseListDetails();
//                setAdapter();
//            }
//
//        }
//
//
//        searchViewFarmerName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if(recAppViewListAdapter!=null){
//                    recAppViewListAdapter.filter(newText);
//                }
//
//                return false;
//            }
//        });
//
//
//    }
//
//    private void getLotNoListDetails() {
//
//
//        progressLayout.setVisibility(View.VISIBLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//        if(checkStatus.equalsIgnoreCase("approve")){
//            status="actual";
//        }else {
//            status="WareHouse";
//        }
//
//        EstActAppContextDao estActAppContextDao = new EstActAppContextDao();
//        estActAppContextDao.setOrgnId(orgnCode);
//        //estActAppContextDao.setLocnId(locnId);
//        estActAppContextDao.setLocnId(ApiUtils.instance);
//        estActAppContextDao.setUserId(userId);
//        estActAppContextDao.setLocaleId(localeId);
//        estActAppContextDao.setStatus(status);
//
//        mAPIService.getEstProcLotNOListDetails(estActAppContextDao)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<EstimateActualApprovedListDao>() {
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
//                    public void onNext(EstimateActualApprovedListDao estimateActualApprovedListDao) {
//                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//                        progressLayout.setVisibility(View.GONE);
//
//
//                        if(estimateActualApprovedListDao.getEstActAppContextDao().getActualListDaoList() !=null && estimateActualApprovedListDao.getEstActAppContextDao().getActualListDaoList().size()>0){
//                            actualandApproveListDaoList.clear();
//                            actualandApproveListDaoList =estimateActualApprovedListDao.getEstActAppContextDao().getActualListDaoList();
//                            setAdapter();
//                        }else if(estimateActualApprovedListDao.getEstActAppContextDao().getApprovedListDaoList() !=null && estimateActualApprovedListDao.getEstActAppContextDao().getApprovedListDaoList().size()>0){
//                            actualandApproveListDaoList.clear();
//                            actualandApproveListDaoList =estimateActualApprovedListDao.getEstActAppContextDao().getApprovedListDaoList();
//                            setAdapter();
//                        }
//
//                    }
//                });
//    }
//
//    private void setAdapter() {
//
//        recAppViewListAdapter=new RecAppViewListAdapter(mContext, actualandApproveListDaoList,this);
//        recyclerViewRecAppPurchase.setAdapter(recAppViewListAdapter);
//
//    }
//
//    @Override
//    public void clickItem(ActualListDao actualListDao) {
//        if(checkStatus.equalsIgnoreCase("approve")){
//            Intent intent=new Intent(mContext,ApproveActualPurchaseActivity.class);
//            intent.putExtra("IOU_LOT_NO",actualListDao.getOut_lotno());
//            intent.putExtra("IOU_AGG_CODE",actualListDao.getOut_agg_code());
//            intent.putExtra("IOU_ROW_ID",actualListDao.getOut_act_rowid());
//            intent.putExtra("PICK_UP_DATE",actualListDao.getOut_actual_date());
//
//            startActivity(intent);
//            finish();
//        }else {
//            Intent intent=new Intent(mContext, WareHouseActivity.class);
//            intent.putExtra("IOU_LOT_NO",actualListDao.getOut_lotno());
//            intent.putExtra("IOU_AGG_CODE",actualListDao.getOut_agg_code());
//            intent.putExtra("IOU_ROW_ID",actualListDao.getOut_act_rowid());
//            intent.putExtra("PICK_UP_DATE",actualListDao.getOut_actual_date());
//            startActivity(intent);
//        }
//
//
//
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(isNetwork==true){
//            getLotNoListDetails();
//        }else {
//            if(checkStatus.equalsIgnoreCase("approve")){
//                actualandApproveListDaoList=db.getOfflineApproveListDetails();
//                setAdapter();
//            }else {
//                actualandApproveListDaoList=db.getOfflineWareHouseListDetails();
//                setAdapter();
//            }
//
//        }

    }
}
