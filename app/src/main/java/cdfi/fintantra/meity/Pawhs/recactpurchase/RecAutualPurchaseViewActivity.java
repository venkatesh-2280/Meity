package cdfi.fintantra.meity.Pawhs.recactpurchase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;


import cdfi.fintantra.meity.ExceptionHandler;
import cdfi.fintantra.meity.Pawhs.PAWHSApplication;
import cdfi.fintantra.meity.R;
import cdfi.fintantra.meity.Pawhs.RecActualViewListAdapter;
import cdfi.fintantra.meity.Pawhs.SQLiteDataBaseHandler;
import cdfi.fintantra.meity.Pawhs.api.ApiService;
import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import cdfi.fintantra.meity.Pawhs.model.ActProContextDao;
import cdfi.fintantra.meity.Pawhs.model.ActProListDao;
import cdfi.fintantra.meity.Pawhs.model.ActualProcurementListDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.ActualListDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.EstActAppContextDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.EstimateActualApprovedListDao;
import cdfi.fintantra.meity.Pawhs.utils.Utility;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static cdfi.fintantra.meity.Pawhs.api.ApiUtils.EST_ACT_VALUE;


public class RecAutualPurchaseViewActivity extends AppCompatActivity implements ActEditListener{

    private PAWHSApplication pawhsApplication;
    private ApiService mAPIService;
    private String userName;
    private Context mContext;
    private TextView textViewTitle;

    private RelativeLayout progressLayout;
    private SQLiteDataBaseHandler db;

    private RecyclerView recyclerViewRecActPurchase;
    private SearchView searchViewFarmerName;
    private boolean isNetwork;

    private String orgnId, locnId, userId, localeId,orgnCode;

    public static List<ActProListDao> actProListDaoList;

    private RecActualViewListAdapter recActualViewListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.rec_actual_purchase_view);
        mContext = this;
        pawhsApplication = PAWHSApplication.getGetInstance();
        userName = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.IN_USER_NAME);

        orgnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_ID);
        locnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCN_ID);
        userId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.USER_ID);
        localeId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCALE_ID);
        orgnCode = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_CODE);

        db = new SQLiteDataBaseHandler(mContext);

        initView();
    }

    private void initView() {
        isNetwork = Utility.checkConnectivity(getApplicationContext());
        mAPIService = ApiUtils.getAPIService();
        textViewTitle = (TextView) findViewById(R.id.txt_title);
        textViewTitle.setText("Welcome " + userName + "\n" + "PA Record Actual Purchase");

        progressLayout = (RelativeLayout) findViewById(R.id.progressLayout);
        recyclerViewRecActPurchase=(RecyclerView)findViewById(R.id.recycle_recactpurchse);
        recyclerViewRecActPurchase.setLayoutManager(new LinearLayoutManager(mContext));
        searchViewFarmerName=(SearchView) findViewById(R.id.searchFarmerName);
        ImageView searchIcon = searchViewFarmerName.findViewById(androidx.appcompat.R.id.search_close_btn);
        searchIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_baseline_close_24));


        actProListDaoList=new ArrayList<>();
        if(isNetwork==true){
            getActualProcurementListDetails();
        }else {
            actProListDaoList=db.getOfflineActualProListDetails();
            setAdapter();
        }


        searchViewFarmerName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(recActualViewListAdapter!=null){
                    recActualViewListAdapter.filter(newText);
                }

                return false;
            }
        });
    }

    private void getActualProcurementListDetails() {

        progressLayout.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        ActProContextDao actProContextDao = new ActProContextDao();
        actProContextDao.setOrgnId(orgnCode);
      //  actProContextDao.setLocnId(locnId);
        actProContextDao.setLocnId(ApiUtils.instance);
        actProContextDao.setUserId(userId);
        actProContextDao.setLocaleId(localeId);
        actProContextDao.setFilterBy_Option("ALL");
        actProContextDao.setFilterBy_Code(".");
        actProContextDao.setFilterBy_FromValue(".");
        actProContextDao.setFilterBy_ToValue(".");

        mAPIService.getActProcListDetails(actProContextDao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ActualProcurementListDao>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ActualProcurementListDao actualProcurementListDao) {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressLayout.setVisibility(View.GONE);

                        actProListDaoList=actualProcurementListDao.getActProContextDao().getActProListDaoList();
                        callLotnoListDetailsOfApproveWare("actual");
                        setAdapter();
                    }
                });
    }

    private void setAdapter() {
        recActualViewListAdapter=new RecActualViewListAdapter(mContext,actProListDaoList,this);
        recyclerViewRecActPurchase.setAdapter(recActualViewListAdapter);
    }

    @Override
    public void editeItem(ActProListDao actProListDao) {
        Intent intent=new Intent();
        intent.putExtra("EDIT_ACTUAL_VALUE",actProListDao);
        intent.putExtra("EDIT_ACT_VALUE","editactual");
        setResult(EST_ACT_VALUE,intent);
        finish();
    }

    private void callLotnoListDetailsOfApproveWare(final String status) {
        /*if(status.equalsIgnoreCase("actual")){
            db.deletepproveSingleHeaderDetails(getApplicationContext());
            db.deleteApproveQtyDetails(getApplicationContext());
            db.deleteApproveSiNoDetails(getApplicationContext());
            db.deleteApproveOtherDetailsList(getApplicationContext());
        }*/
        db.deleteApproveList(getApplicationContext());

        progressLayout.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        EstActAppContextDao estActAppContextDao = new EstActAppContextDao();
        estActAppContextDao.setOrgnId(orgnCode);
        // estActAppContextDao.setLocnId(locnId);
        estActAppContextDao.setLocnId(ApiUtils.instance);
        estActAppContextDao.setUserId(userId);
        estActAppContextDao.setLocaleId(localeId);
        estActAppContextDao.setStatus(status);

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
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressLayout.setVisibility(View.GONE);

                        if(status.equalsIgnoreCase("actual")){
                            List<ActualListDao> actualListDaoList =estimateActualApprovedListDao.getEstActAppContextDao().getActualListDaoList();
                            for (int i = 0; i < actualListDaoList.size(); i++) {
                                ActualListDao actualListDao=new ActualListDao(1,actualListDaoList.get(i).getOut_act_rowid(),actualListDaoList.get(i).getOut_lotno(),
                                        actualListDaoList.get(i).getOut_agg_code(),actualListDaoList.get(i).getOut_farmer_code(),actualListDaoList.get(i).getOut_farmer_name(),
                                        actualListDaoList.get(i).getOut_member_type(),actualListDaoList.get(i).getOut_item_code(),actualListDaoList.get(i).getOut_item_name(),
                                        actualListDaoList.get(i).getOut_actual_qty(),actualListDaoList.get(i).getOut_actual_price(),actualListDaoList.get(i).getOut_actual_value(),
                                        actualListDaoList.get(i).getOut_actual_date(),actualListDaoList.get(i).getOut_advance_amt(),actualListDaoList.get(i).getOut_approve_date(),
                                        actualListDaoList.get(i).getOut_pickup_date(),actualListDaoList.get(i).getOut_wr_date(),actualListDaoList.get(i).getOut_no_of_bags(),
                                        actualListDaoList.get(i).getOut_status(),actualListDaoList.get(i).getOut_actual_remarks(),actualListDaoList.get(i).getOut_approved_remarks(),
                                        actualListDaoList.get(i).getOut_pickup_remarks(),actualListDaoList.get(i).getOut_wr_remarks(),actualListDaoList.get(i).getIn_actual_attach(),actualListDaoList.get(i).getIn_qcperson_name(),actualListDaoList.get(i).getIn_vehicle_no(),actualListDaoList.get(i).getIn_vehicle_type(),actualListDaoList.get(i).getIn_destination(),actualListDaoList.get(i).getIn_LRP_Name(),actualListDaoList.get(i).getIn_LRP_Mobile_no(),actualListDaoList.get(i).getIn_Payment_type(),actualListDaoList.get(i).getIn_Bank_acc_no(),actualListDaoList.get(i).getIn_cheque_no());
                                db.addAllApproveListDetails(actualListDao);

                            }

                          /*  List<ActualListDao> actualListDaos=db.getOfflineApproveListDetails();

                            for(int i=0;i<actualListDaos.size();i++){
                                String lotNo=actualListDaos.get(i).getOut_lotno();
                                String aggCode=actualListDaos.get(i).getOut_agg_code();
                                int rowId=actualListDaos.get(i).getOut_act_rowid();

                                callSingleActualProcurementDetails(lotNo,aggCode,rowId,status);

                            }*/


                        }





                    }
                });
    }
}
