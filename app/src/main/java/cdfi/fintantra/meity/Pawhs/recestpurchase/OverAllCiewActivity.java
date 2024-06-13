package cdfi.fintantra.meity.Pawhs.recestpurchase;

import android.content.Context;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;


import cdfi.fintantra.meity.Pawhs.PAWHSApplication;
import cdfi.fintantra.meity.Pawhs.SQLiteDataBaseHandler;
import cdfi.fintantra.meity.Pawhs.api.ApiService;
import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.LotnoDao;

public class OverAllCiewActivity extends AppCompatActivity {
    private PAWHSApplication pawhsApplication;
    private ApiService mAPIService;
    private String userName;
    private Context mContext;
    private TextView textViewTitle;

    private RelativeLayout progressLayout;
    private SQLiteDataBaseHandler db;

    private RecyclerView recyclerViewOverAll;
    private SearchView searchViewFarmerName;
    private boolean isNetwork;

    private String orgnId, locnId, userId, localeId,orgnCode;

    public static List<LotnoDao> lotnoDaoList;
  //  private OverAllViewAdapter overAllViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.overall_view_screen);
        mContext = this;
        pawhsApplication = PAWHSApplication.getGetInstance();
        userName = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.IN_USER_NAME);

        orgnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_ID);
        locnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCN_ID);
        userId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.USER_ID);
        localeId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCALE_ID);
        orgnCode = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_CODE);


        db = new SQLiteDataBaseHandler(mContext);

        //initView();
    }

//    private void initView() {
//        isNetwork = Utility.checkConnectivity(getApplicationContext());
//        mAPIService = ApiUtils.getAPIService();
//        textViewTitle = (TextView) findViewById(R.id.txt_title);
//        textViewTitle.setText("Welcome " + userName + "\n" + "PA Approve WareHouse Purchase");
//
//        progressLayout = (RelativeLayout) findViewById(R.id.progressLayout);
//        recyclerViewOverAll=(RecyclerView)findViewById(R.id.recycle_overallView);
//        recyclerViewOverAll.setLayoutManager(new LinearLayoutManager(mContext));
//        searchViewFarmerName=(SearchView) findViewById(R.id.searchFarmerName);
//        ImageView searchIcon = searchViewFarmerName.findViewById(androidx.appcompat.R.id.search_close_btn);
//        searchIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_baseline_close_24));
//
//
//        lotnoDaoList = new ArrayList<>();
//
//        if(isNetwork==true){
//            getOverAllViewList();
//        }/*else {
//            wareHouseListDaoList=db.getOfflineWareHouseReceiptListDetails();
//            setAdapter();
//        }*/
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
//                if(overAllViewAdapter!=null){
//                    overAllViewAdapter.filter(newText);
//                }
//
//                return false;
//            }
//        });
//    }
//
//    private void getOverAllViewList() {
//        progressLayout.setVisibility(View.VISIBLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//        LotNoContextDao lotNoContextDao = new LotNoContextDao();
//        lotNoContextDao.setOrgnId(orgnCode);
//       // lotNoContextDao.setLocnId(locnId);
//        lotNoContextDao.setLocnId(ApiUtils.instance);
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
//                        setAdapter();
//
//                    }
//                });
//    }
//
//    private void setAdapter() {
//        overAllViewAdapter=new OverAllViewAdapter(mContext,lotnoDaoList);
//        recyclerViewOverAll.setAdapter(overAllViewAdapter);
//    }
}
