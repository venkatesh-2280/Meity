package cdfi.fintantra.meity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cdfi.fintantra.meity.Pawhs.Estqtydao;
import cdfi.fintantra.meity.Pawhs.PAWHSApplication;

import cdfi.fintantra.meity.Pawhs.SQLiteDataBaseHandler;
import cdfi.fintantra.meity.Pawhs.SaleEntry;
import cdfi.fintantra.meity.Pawhs.api.ApiService;
import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import cdfi.fintantra.meity.Pawhs.bulk.BulkActualProcurementDao;
import cdfi.fintantra.meity.Pawhs.bulk.BulkContextDao;
import cdfi.fintantra.meity.Pawhs.bulk.BulkDocumentDao;
import cdfi.fintantra.meity.Pawhs.bulk.BulkEstProdDao;
import cdfi.fintantra.meity.Pawhs.bulk.BulkHeaderDao;
import cdfi.fintantra.meity.Pawhs.bulk.BulkNewWareHouseReceiptDao;
import cdfi.fintantra.meity.Pawhs.bulk.BulkTransactionDao;
import cdfi.fintantra.meity.Pawhs.formermodel.FormerContextDao;
import cdfi.fintantra.meity.Pawhs.formermodel.FormerDao;
import cdfi.fintantra.meity.Pawhs.formermodel.FormerListDao;
import cdfi.fintantra.meity.Pawhs.fragments.PaCheckerFragment;
import cdfi.fintantra.meity.Pawhs.fragments.PaMakerFragment;
import cdfi.fintantra.meity.Pawhs.fragments.WareHouseFragment;
import cdfi.fintantra.meity.Pawhs.model.ActProContextDao;
import cdfi.fintantra.meity.Pawhs.model.ActProListDao;
import cdfi.fintantra.meity.Pawhs.model.ActualProcurementContextDao;
import cdfi.fintantra.meity.Pawhs.model.ActualProcurementDocumentDao;
import cdfi.fintantra.meity.Pawhs.model.ActualProcurementHeaderDao;
import cdfi.fintantra.meity.Pawhs.model.ActualProcurementListDao;
import cdfi.fintantra.meity.Pawhs.model.ActualProcurementSaveDao;
import cdfi.fintantra.meity.Pawhs.model.OtherDetailDao;
import cdfi.fintantra.meity.Pawhs.model.PawhsActualProcSaveDao;
import cdfi.fintantra.meity.Pawhs.model.PmContextDao;
import cdfi.fintantra.meity.Pawhs.model.PmListDao;
import cdfi.fintantra.meity.Pawhs.model.PostOtherDetailDao;


import cdfi.fintantra.meity.Pawhs.model.PostQtyDetailDao;
import cdfi.fintantra.meity.Pawhs.model.PostSiNoDetailDao;
import cdfi.fintantra.meity.Pawhs.model.ProductMasterDao;
import cdfi.fintantra.meity.Pawhs.model.QtyDetailDao;
import cdfi.fintantra.meity.Pawhs.model.SiNoDetailDao;
import cdfi.fintantra.meity.Pawhs.model.SubmitRecEstPurchaseDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.ActualListDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.EstActAppContextDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.EstimateActualApprovedListDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.EstimateListDao;
import cdfi.fintantra.meity.Pawhs.model.singleactualprocmodel.SingleActProcContextDao;
import cdfi.fintantra.meity.Pawhs.model.singleactualprocmodel.SingleActualHeaderDao;
import cdfi.fintantra.meity.Pawhs.model.singleactualprocmodel.SingleActualProcurementDao;
import cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel.SingleProductMasterDao;
import cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel.SpmContextDao;
import cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel.SpmDetailDao;
import cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel.SpmHeaderDao;
import cdfi.fintantra.meity.Pawhs.model.warehouse.WareHouseListDao;
import cdfi.fintantra.meity.Pawhs.model.warehouse.WareHouseReceiptListDao;
import cdfi.fintantra.meity.Pawhs.model.warehouse.WareReceiptContextDao;
import cdfi.fintantra.meity.Pawhs.reactapppurchase.RecAppListVIewActivity;
import cdfi.fintantra.meity.Pawhs.recactpurchase.RecActualPurchaseActivity;
import cdfi.fintantra.meity.Pawhs.utils.MyConstants;
import cdfi.fintantra.meity.Pawhs.utils.Utility;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Context mContext;
    JSONObject userd;
    int cd = 0;
    int offc,offc2=0;
    ProgressDialog pdialog;
    JSONArray castb;
    String seid;
    ByteArrayOutputStream byteArrayOutputStream,byteArrayOutputStream2;
    String fmid,n1,n10,n11,n12,n13,adsfid;
    String farid, gf, encodedImage;
    private static final int PERMISSION_REQUEST_CODE = 200;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    private TextView textView_role, textview_name;
    private PaMakerFragment paMakerFragment;
    private PaCheckerFragment paCheckerFragment;
    private WareHouseFragment wareHouseFragment;
    JSONObject jsonParam;
    private List<Estqtydao> postQtyDetailDaoList;
    private PAWHSApplication pawhsApplication;
    private String userName, roleCode;

    private String orgnCode, locnId, userId, localeId, orgnId;

    private RelativeLayout progressLayout;
    private ApiService mAPIService;
    private SQLiteDataBaseHandler db;

    private boolean isNetwork;
    private List<SubmitRecEstPurchaseDao> submitOfflineRecEstPurchaseDaoList;
    private List<FormerDao> formerDaoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mContext = this;
        pawhsApplication = PAWHSApplication.getGetInstance();
        db = new SQLiteDataBaseHandler(this);
        // save();
        // save2();

        userName = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.IN_USER_NAME);
        roleCode = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.IN_ROLE_CODE);

        orgnCode = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_CODE);
        locnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCN_ID);
        userId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.USER_ID);
        localeId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCALE_ID);
        orgnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_ID);


        initView();
    }

    private void initView() {





        mAPIService = ApiUtils.getAPIService();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        progressLayout = (RelativeLayout) findViewById(R.id.progressLayout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        textview_name = (TextView) header.findViewById(R.id.textview_name);
        textView_role = (TextView) header.findViewById(R.id.textview_role);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        navigationView.setItemIconTintList(null);

        postQtyDetailDaoList = new ArrayList<>();
        paMakerFragment = new PaMakerFragment();
        paCheckerFragment = new PaCheckerFragment();
        wareHouseFragment = new WareHouseFragment();
        if (checkPermission()) {
            //main logic or main code

            // . write your main code to execute, It will execute if the permission is already given.

        } else {
            requestPermission();
        }

//        if (roleCode.equalsIgnoreCase("ROLE_PAWHS_LRP")) {
            navigationView.inflateMenu(R.menu.pamaker_home_page_drawer);
            navigationView.setNavigationItemSelectedListener(this);
            textview_name.setText("Produce Aggregator (LRP)");
            textView_role.setText(/*" << " + */userName /*+ " >> "*/);
            loadFragment(paMakerFragment);
//        } else if (roleCode.equalsIgnoreCase("ROLE_PAWHS_FPO_MANAGE")) {
//            navigationView.inflateMenu(R.menu.pachecker_home_page_drawer);
//            navigationView.setNavigationItemSelectedListener(this);
//            textview_name.setText("FPO Manager");
//            textView_role.setText(/*" << " +*/ userName /*+ " >> "*/);
//            loadFragment(paCheckerFragment);
//        } else {
//            navigationView.inflateMenu(R.menu.pawarehouse_home_page_drawer);
//            navigationView.setNavigationItemSelectedListener(this);
//            textview_name.setText("Warehouse Manager");
//            textView_role.setText(/*" << " +*/ userName /*+ " >> "*/);
//            loadFragment(wareHouseFragment);
//        }
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

                    // main logic
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(HomePageActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (f instanceof PaMakerFragment || f instanceof PaCheckerFragment || f instanceof WareHouseFragment) {
            showLogoutDialog();
            //  finish();
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.master) {

            isNetwork = Utility.checkConnectivity(getApplicationContext());
            if (isNetwork) {
                try {
                        deleteCache(this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    callProductMasterAllProductJsonDetails();
                    cd = 0;
//                if (roleCode.equalsIgnoreCase("ROLE_PAWHS_LRP")) {
//
//                    try {
//                        deleteCache(this);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    callProductMasterAllProductJsonDetails();
//                    cd = 0;
//                } else if (roleCode.equalsIgnoreCase("ROLE_PAWHS_FPO_MANAGE")) {
//                    db.deleteApproveList(getApplicationContext());
//                    callLotnoListDetails2();
//                    callLotnoListDetailsOfApproveWare("actual");
//                    cd = 0;
//                } else {
//                    db.deleteWareHouseList(getApplicationContext());
//                    callLotnoListDetailsOfApproveWare("WareHouse");
//                    cd = 0;
//                }
            } else {
                showConnectionDialog();
            }


        } else if (id == R.id.transaction) {

            isNetwork = Utility.checkConnectivity(getApplicationContext());
            if (isNetwork) {
//                if (roleCode.equalsIgnoreCase("ROLE_PAWHS_LRP")) {


                    final SQLiteDatabase dbs = db.getWritableDatabase();
                    String selectQuery5 = "SELECT  * FROM farmerh where flag = 0";
                    Cursor cc = dbs.rawQuery(selectQuery5, null);

                    if (cc.getCount() == 0) {
                        // callBulkTransactionDetails("1");
                        String selectQuery = "SELECT  * FROM saleentry where v17 = 0";



                        Cursor cursor = dbs.rawQuery(selectQuery, null);

                        if(cursor.getCount()>0)
                        {progressLayout.setVisibility(View.VISIBLE);
//                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                            offlinesavesaleentry();
                        }
                        else
                        {
                            String selectQuery2 = "SELECT  * FROM purchaseentry where v22 = 0";



                            Cursor cursor2 = dbs.rawQuery(selectQuery2, null);

                            if(cursor2.getCount()>0)
                            {
                                progressLayout.setVisibility(View.VISIBLE);
//                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                offlinesavepurchaseentry();
                            }
                            else
                            {
                                showErrorDialog("No Transaction Available");
                            }



                        }
                    } else {


                        progressLayout.setVisibility(View.VISIBLE);
//                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        offlinesave();
                        offc= cc.getCount();

                    }

//                } else if (roleCode.equalsIgnoreCase("ROLE_PAWHS_FPO_MANAGE")) {
//                    callActualProcSaveMethod("Approved");
//                } else {
//                    callActualProcSaveMethod("WR Generation");
//                }

            } else {
                showConnectionDialog();
            }


        }  else if (id == R.id.act_purchase) {
            Pojokyc.purno="";
            Intent intent = new Intent(mContext, RecActualPurchaseActivity.class);
            intent.putExtra("Frm", "Actual");
            intent.putExtra("PEID", "");
            startActivity(intent);
        }
        else if (id == R.id.act_purchase2) {
            Intent intent = new Intent(mContext, SaleEntry.class);
            Pojokyc.saleno="";
            intent.putExtra("SEID", "");
            startActivity(intent);
        }
        else if (id == R.id.app_act_purchase) {
            Intent intent = new Intent(mContext, RecAppListVIewActivity.class);
            intent.putExtra(ApiUtils.CHECK_STATUS, "approve");
            startActivity(intent);

        } else if (id == R.id.nav_warehouse) {
            Intent intent = new Intent(mContext, RecAppListVIewActivity.class);
            intent.putExtra(ApiUtils.CHECK_STATUS, "warehouse");
            startActivity(intent);

        } else if (id == R.id.app_est_pur) {
            //Intent intent = new Intent(mContext, Estimatedpurlist.class);
            //startActivity(intent);
        } else if (id == R.id.settings) {

        } else if (id == R.id.logout) {
            // loadFragment(new LeaveSummaryFragment());
            showLogoutDialog();
        }


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void callBulkTransactionDetails(String s) {

        submitOfflineRecEstPurchaseDaoList = new ArrayList<>();
        submitOfflineRecEstPurchaseDaoList = db.getAllOfflineSubmitRecEstPurchaseDao();
        Log.v(MyConstants.TAG, submitOfflineRecEstPurchaseDaoList.toString());

        List<BulkEstProdDao> bulkEstProdDaoList = new ArrayList<>();
        List<BulkActualProcurementDao> bulkActualProcurementDaoList = new ArrayList<>();
        List<BulkNewWareHouseReceiptDao> bulkNewWareHouseReceiptDaoList = new ArrayList<>();
        BulkEstProdDao bulkEstProdDao;

        List<PawhsActualProcSaveDao> pawhsActualProcSaveTempDaoList = db.getOfflinePawhsActualProcSaveDaoDetails("Actual");
        List<PawhsActualProcSaveDao> pawhsActualProcSaveTempDaoList2 = db.getOfflinePawhsActualProcSaveDaoDetails("Approved");
        String encodedImage = "0";
        for (int i = 0; i < submitOfflineRecEstPurchaseDaoList.size(); i++) {
            final SQLiteDatabase dbs = db.getWritableDatabase();

            try {


                Cursor c = dbs.rawQuery("SELECT * FROM SubmitRecEstPurchase WHERE SubmitRecEstPurchaseKeyId = '" + submitOfflineRecEstPurchaseDaoList.get(i).getId() + "'", null);


                if (c.moveToNext()) {
                    String qp = c.getString(18);
                    String[] qps = qp.split("@");

                    for (int q = 0; q<qps.length;q++)
                    {
                        String[] v  = qps[q].split("-");
                        if(v[1].equalsIgnoreCase("0"))
                        {

                        }
                        else
                        {
                            Cursor cursorq = dbs.query("qparest", new String[]{"v1", "v2", "v3","v4"
                                    }, "v3" + " LIKE ?",
                                    new String[]{"%"+ v[0]+ "%"}, null, null, null, null);


                            if(cursorq.moveToFirst())
                            {
                                do {
                                    postQtyDetailDaoList.add(new Estqtydao(0,cursorq.getString(1),0.00,0.00,v[0],"I"));

                                }while(cursorq.moveToNext());
                            }
                        }
                    }

                    encodedImage = c.getString(16);

                }
            } catch (Exception e) {
                encodedImage = "0";
            }
//            bulkEstProdDao = new BulkEstProdDao(Integer.parseInt(submitOfflineRecEstPurchaseDaoList.get(i).getEstiRowId()), submitOfflineRecEstPurchaseDaoList.get(i).getLotNo(), submitOfflineRecEstPurchaseDaoList.get(i).getFarmerCode()
//                    , submitOfflineRecEstPurchaseDaoList.get(i).getFarmerName(), submitOfflineRecEstPurchaseDaoList.get(i).getFarmerMember(), submitOfflineRecEstPurchaseDaoList.get(i).getItemCode(),
//                    submitOfflineRecEstPurchaseDaoList.get(i).getItemName(), Double.parseDouble(submitOfflineRecEstPurchaseDaoList.get(i).getQty()), Double.parseDouble(submitOfflineRecEstPurchaseDaoList.get(i).getPrice()),
//                    Double.parseDouble(submitOfflineRecEstPurchaseDaoList.get(i).getValue()), submitOfflineRecEstPurchaseDaoList.get(i).getPickupdate(), submitOfflineRecEstPurchaseDaoList.get(i).getRemarks(),
//                    submitOfflineRecEstPurchaseDaoList.get(i).getRowTimeStamp(), submitOfflineRecEstPurchaseDaoList.get(i).getMode_Flag(), encodedImage,submitOfflineRecEstPurchaseDaoList.get(i).getVs(),submitOfflineRecEstPurchaseDaoList.get(i).getRv(),submitOfflineRecEstPurchaseDaoList.get(i).getLrp(),postQtyDetailDaoList,submitOfflineRecEstPurchaseDaoList.get(i).getLotmn());
//            bulkEstProdDaoList.add(bulkEstProdDao);
            Log.v(MyConstants.TAG, "OfflineEstList  :" + submitOfflineRecEstPurchaseDaoList.get(i).getIn_Estimated_attach());
        }


        if (bulkEstProdDaoList != null && bulkEstProdDaoList.size() > 0) {

            progressLayout.setVisibility(View.VISIBLE);
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


            BulkHeaderDao bulkHeaderDao = new BulkHeaderDao();
            bulkHeaderDao.setAggrcode(orgnCode);
            bulkHeaderDao.setUsername(userName);
            BulkContextDao bulkContextDao = new BulkContextDao();
            bulkContextDao.setOrgnId(orgnCode);
            // bulkContextDao.setLocnId(locnId);
            bulkContextDao.setLocnId(ApiUtils.instance);
            bulkContextDao.setUserId(userId);
            bulkContextDao.setLocaleId(localeId);
            bulkContextDao.setBulkHeaderDao(bulkHeaderDao);
            bulkContextDao.setBulkEstProdDaoList(bulkEstProdDaoList);
            bulkContextDao.setBulkActualProcurementDaoList(bulkActualProcurementDaoList);
            bulkContextDao.setBulkNewWareHouseReceiptDaoList(bulkNewWareHouseReceiptDaoList);

            BulkDocumentDao bulkDocumentDao = new BulkDocumentDao();
            bulkDocumentDao.setBulkContextDao(bulkContextDao);

            BulkTransactionDao bulkTransactionDao = new BulkTransactionDao();

            bulkTransactionDao.setBulkDocumentDao(bulkDocumentDao);

            mAPIService.saveBulkTransactionDetails(bulkTransactionDao)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<String[]>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(String[] strings) {
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            progressLayout.setVisibility(View.GONE);


                            for (int i = 0; i < submitOfflineRecEstPurchaseDaoList.size(); i++) {
                                for (int j = 0; j < strings.length; j++) {
                                    db.updateLotNO(submitOfflineRecEstPurchaseDaoList.get(i).getId(), strings[i], "Yes");
                                }
                            }

                            showTransactionDialog();
                        }
                    });

        } else if (pawhsActualProcSaveTempDaoList != null && pawhsActualProcSaveTempDaoList.size() > 0) {
            callActualProcSaveMethodActual("Actual");
        } else if (pawhsActualProcSaveTempDaoList2 != null && pawhsActualProcSaveTempDaoList2.size() > 0) {
            callActualProcSaveMethodActual("Approved");
        } else {

            if(s.equalsIgnoreCase("2"))
            {
                showErrorDialog("Transaction Completed");
            }
            else
            {
                showErrorDialog("No Records Available");
            }



        }
        /*if (roleCode.equalsIgnoreCase("ROLE_PAWHS_LRP")) {
            callActualProcSaveMethod();
        }*/


    }

    private void callActualProcSaveMethodActual(final String statusValue) {

        List<PostOtherDetailDao> postOtherDetailDaosOfflineList = new ArrayList<>();
        List<PostSiNoDetailDao> postSiNoDetailDaosOfflineList = new ArrayList<>();
        List<PostQtyDetailDao> postQtyDetailDaosOfflineList = new ArrayList<>();


        if (statusValue.equalsIgnoreCase("Actual")) {
            final List<PawhsActualProcSaveDao> pawhsActualProcSaveDaoList = db.getOfflinePawhsActualProcSaveDaoDetails(statusValue);

            List<OtherDetailDao> otherDetailDaoList = null;
            List<SiNoDetailDao> siNoDetailDaoList = null;
            List<QtyDetailDao> qtyDetailDaoList = null;
            for (int i = 0; i < pawhsActualProcSaveDaoList.size(); i++) {


                final List<SingleActualHeaderDao> singleActualHeaderDaoList = db.getOfflineAppSingleHeaderListDetailsRelatedLotNO(pawhsActualProcSaveDaoList.get(i).getLotNo());

                Log.e("LOTNO", "" + pawhsActualProcSaveDaoList.get(i).getLotNo());
                otherDetailDaoList = db.getOfflineApproveOtherDetailsful(pawhsActualProcSaveDaoList.get(i).getLotNo());
                siNoDetailDaoList = db.getOfflineApproveSiNoDetailsListful(pawhsActualProcSaveDaoList.get(i).getLotNo());
                qtyDetailDaoList = db.getOfflineApproveQtyDetailsListful(pawhsActualProcSaveDaoList.get(i).getLotNo());
                for (int j = 0; j < otherDetailDaoList.size(); j++) {
                    if (pawhsActualProcSaveDaoList.get(i).getLotNo().equalsIgnoreCase(otherDetailDaoList.get(j).getIn_lotno())) {
                        Log.v(MyConstants.TAG, pawhsActualProcSaveDaoList.get(i).getLotNo() + "//" + otherDetailDaoList.get(j).getIn_lotno());


                        postOtherDetailDaosOfflineList.add(new PostOtherDetailDao(otherDetailDaoList.get(j).getId(), otherDetailDaoList.get(j).getIn_Other_row_id(),
                                otherDetailDaoList.get(j).getIn_packaging_cost(), otherDetailDaoList.get(j).getIn_transporting_cost(), otherDetailDaoList.get(j).getIn_unpacking_cost(),
                                otherDetailDaoList.get(j).getIn_local_packaging_cost(), otherDetailDaoList.get(j).getIn_local_transporting_cost(), otherDetailDaoList.get(j).getIn_temp_cost(), otherDetailDaoList.get(j).getIn_temp_cost1(),
                                otherDetailDaoList.get(j).getIn_temp_cost2(), otherDetailDaoList.get(j).getIn_mode_flag(), otherDetailDaoList.get(j).getStatusValue(), otherDetailDaoList.get(j).getIn_lotno()));
                    }
                }

                for (int k = 0; k < siNoDetailDaoList.size(); k++) {
                    if (pawhsActualProcSaveDaoList.get(i).getLotNo().equalsIgnoreCase(siNoDetailDaoList.get(k).getIn_lotno())) {

                        Log.v(MyConstants.TAG, pawhsActualProcSaveDaoList.get(i).getLotNo() + "//" + siNoDetailDaoList.get(k).getIn_lotno());

                        postSiNoDetailDaosOfflineList.add(new PostSiNoDetailDao(siNoDetailDaoList.get(k).getId(), siNoDetailDaoList.get(k).getIn_slno_row_id(),
                                siNoDetailDaoList.get(k).getIn_slno(), siNoDetailDaoList.get(k).getIn_temp1(), siNoDetailDaoList.get(k).getIn_temp2(),
                                siNoDetailDaoList.get(k).getIn_mode_flag(), siNoDetailDaoList.get(k).getStatusValue(), siNoDetailDaoList.get(k).getIn_lotno()));
                    }
                }

                for (int l = 0; l < qtyDetailDaoList.size(); l++) {

                    if (pawhsActualProcSaveDaoList.get(i).getLotNo().equalsIgnoreCase(qtyDetailDaoList.get(l).getIn_lotno())) {
                        Log.v(MyConstants.TAG, pawhsActualProcSaveDaoList.get(i).getLotNo() + "//" + qtyDetailDaoList.get(l).getIn_lotno());

                        postQtyDetailDaosOfflineList.add(new PostQtyDetailDao(qtyDetailDaoList.get(l).getId(), qtyDetailDaoList.get(l).getIn_qty_row_id(),
                                qtyDetailDaoList.get(l).getIn_qty_code(), qtyDetailDaoList.get(l).getIn_qty_name(), qtyDetailDaoList.get(l).getIn_actual_value(),
                                qtyDetailDaoList.get(l).getIn_wr_qty_value(), qtyDetailDaoList.get(l).getIn_mode_flag(), qtyDetailDaoList.get(l).getThresHoldValue(),
                                qtyDetailDaoList.get(l).getUom(), qtyDetailDaoList.get(l).getStatusValue(), qtyDetailDaoList.get(l).getIn_lotno()));
                    }
                }

                String vst2="",dsn2="";
                final SQLiteDatabase dbs = db.getWritableDatabase();
                Cursor cursor2 = dbs.query("vtype", new String[]{"v1", "v2", "v3"
                        }, "v3" + "=?",
                        new String[]{singleActualHeaderDaoList.get(i).getIn_vehicle_type()}, null, null, null, null);


                if(cursor2.moveToFirst())
                {
                    do {
                        vst2= cursor2.getString(1);

                    }while(cursor2.moveToNext());
                }

                Cursor cursor2d = dbs.query("dstn", new String[]{"v1", "v2", "v3"
                        }, "v3" + "=?",
                        new String[]{singleActualHeaderDaoList.get(i).getIn_destination()}, null, null, null, null);


                if(cursor2d.moveToFirst())
                {
                    do {
                        dsn2= cursor2d.getString(1);

                    }while(cursor2d.moveToNext());
                }
                ActualProcurementHeaderDao actualProcurementHeaderDao = new ActualProcurementHeaderDao();
                actualProcurementHeaderDao.setIn_Actual_rowid(pawhsActualProcSaveDaoList.get(i).getAct_row_id());
                actualProcurementHeaderDao.setIn_LotNo(pawhsActualProcSaveDaoList.get(i).getLotNo());
                actualProcurementHeaderDao.setIn_Farmer_Code(pawhsActualProcSaveDaoList.get(i).getFarmerCode());
                actualProcurementHeaderDao.setIn_Farmer_Name(pawhsActualProcSaveDaoList.get(i).getFarmerName());
                actualProcurementHeaderDao.setIn_Member_Type(pawhsActualProcSaveDaoList.get(i).getMemberType());
                actualProcurementHeaderDao.setIn_Item_Code(pawhsActualProcSaveDaoList.get(i).getItemCode());
                actualProcurementHeaderDao.setIn_Item_Name(pawhsActualProcSaveDaoList.get(i).getItemName());
                actualProcurementHeaderDao.setIn_Actual_Qty(Double.parseDouble(pawhsActualProcSaveDaoList.get(i).getActualqty()));
                actualProcurementHeaderDao.setIn_Actual_Price(Double.parseDouble(pawhsActualProcSaveDaoList.get(i).getActualPrice()));
                actualProcurementHeaderDao.setIn_Actual_Value(Double.parseDouble(pawhsActualProcSaveDaoList.get(i).getActualValue()));
                if (pawhsActualProcSaveDaoList.get(i).getAdvanceAmount() != null && !pawhsActualProcSaveDaoList.get(i).getAdvanceAmount().isEmpty()) {
                    actualProcurementHeaderDao.setIn_advance_amt(Double.parseDouble(pawhsActualProcSaveDaoList.get(i).getAdvanceAmount()));
                } else {
                    actualProcurementHeaderDao.setIn_advance_amt(Double.parseDouble("0"));
                }

                actualProcurementHeaderDao.setIn_no_of_bags(Integer.parseInt(pawhsActualProcSaveDaoList.get(i).getNoofBags()));
                actualProcurementHeaderDao.setIn_Status(pawhsActualProcSaveDaoList.get(i).getStatus());
                // actualProcurementHeaderDao.setIn_mode_flag(pawhsActualProcSaveDaoList.get(i).getModeFlag());
                actualProcurementHeaderDao.setIn_mode_flag(singleActualHeaderDaoList.get(0).getIn_mode_flag());
                actualProcurementHeaderDao.setIn_actual_remarks(pawhsActualProcSaveDaoList.get(i).getActualRemarks());
                actualProcurementHeaderDao.setIn_approved_remarks(pawhsActualProcSaveDaoList.get(i).getApproveRemarks());
                actualProcurementHeaderDao.setIn_pickup_remarks(pawhsActualProcSaveDaoList.get(i).getPickUpRemarks());

                // final SQLiteDatabase dbs = db.getWritableDatabase();
                String encodedImage = "0";
                try {


                    Cursor c = dbs.rawQuery("SELECT * FROM AppSingleHeader WHERE AppSingleHeaderKeyId = '" + singleActualHeaderDaoList.get(i).getId() + "'", null);


                    if (c.moveToNext()) {
                        encodedImage = c.getString(21);

                    }
                } catch (Exception e) {
                    encodedImage = "0";
                }
                actualProcurementHeaderDao.setIn_actual_attach(encodedImage);
                actualProcurementHeaderDao.setIn_qcperson_name(singleActualHeaderDaoList.get(i).getIn_qcperson_name());
                actualProcurementHeaderDao.setIn_vehicle_no(singleActualHeaderDaoList.get(i).getIn_vehicle_no());
                actualProcurementHeaderDao.setIn_vehicle_type(vst2);
                actualProcurementHeaderDao.setIn_destination(dsn2);
                actualProcurementHeaderDao.setIn_LRP_Name(singleActualHeaderDaoList.get(i).getIn_LRP_Name());

                ActualProcurementContextDao actualProcurementContextDao = new ActualProcurementContextDao();
                actualProcurementContextDao.setOrgnId(orgnCode);
                // actualProcurementContextDao.setLocnId(locnId);
                actualProcurementContextDao.setLocnId(ApiUtils.instance);
                actualProcurementContextDao.setUserId(userId);
                actualProcurementContextDao.setLocaleId(localeId);
                actualProcurementContextDao.setActualProcurementHeaderDao(actualProcurementHeaderDao);
                actualProcurementContextDao.setPostQtyDetailDaoList(postQtyDetailDaosOfflineList);
                actualProcurementContextDao.setPostSiNoDetailDaoList(postSiNoDetailDaosOfflineList);
                actualProcurementContextDao.setPostOtherDetailDaoList(postOtherDetailDaosOfflineList);

                ActualProcurementDocumentDao actualProcurementDocumentDao = new ActualProcurementDocumentDao();
                actualProcurementDocumentDao.setActualProcurementContextDao(actualProcurementContextDao);

                ActualProcurementSaveDao actualProcurementSaveDao = new ActualProcurementSaveDao();
                actualProcurementSaveDao.setActualProcurementDocumentDao(actualProcurementDocumentDao);

                Log.v(MyConstants.TAG, actualProcurementSaveDao.toString());

                progressLayout.setVisibility(View.VISIBLE);
//                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                mAPIService.postActualProcurementDetails(actualProcurementSaveDao)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ActualProcurementSaveDao>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ActualProcurementSaveDao actualProcurementSaveDao) {
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                progressLayout.setVisibility(View.GONE);
                                if (statusValue.equalsIgnoreCase("Approved")) {
                                    db.deletePawhsActualProcSaveDaoDetails(getApplicationContext(), statusValue);
                                    db.deleteOtherDetails(getApplicationContext(), "APPROVAL");
                                    db.deleteQtyDetails(getApplicationContext(), "APPROVAL");
                                    db.deleteSiNoDetails(getApplicationContext(), "APPROVAL");
                                } else if (statusValue.equalsIgnoreCase("WR Generation")) {
                                    db.deletePawhsActualProcSaveDaoDetails(getApplicationContext(), statusValue);
                                    db.deleteOtherDetails(getApplicationContext(), "WRGENERATION");
                                    db.deleteQtyDetails(getApplicationContext(), "WRGENERATION");
                                    db.deleteSiNoDetails(getApplicationContext(), "WRGENERATION");
                               /* db.deleteWRQtyDetails(getApplicationContext(),"WRGENERATION");
                                db.deleteWROtherDetails(getApplicationContext(),"WRGENERATION");
                                db.deleteWRSiNoDetails(getApplicationContext(),"WRGENERATION");*//* db.deleteWRQtyDetails(getApplicationContext(),"WRGENERATION");
                                db.deleteWROtherDetails(getApplicationContext(),"WRGENERATION");
                                db.deleteWRSiNoDetails(getApplicationContext(),"WRGENERATION");*/
                                } else if (statusValue.equalsIgnoreCase("Actual")) {
                                    db.deletePawhsActualProcSaveDaoDetails(getApplicationContext(), statusValue);
                                    db.deleteOtherDetails(getApplicationContext(), "ACTUAL");
                                    db.deleteQtyDetails(getApplicationContext(), "ACTUAL");
                                    db.deleteSiNoDetails(getApplicationContext(), "ACTUAL");
                                    db.deleteApproveOtherDetails(getApplicationContext(), "ACTUAL");
                                    db.deleteApproveSiNoDetails(getApplicationContext(), "ACTUAL");
                                    db.deleteApproveQtyDetails(getApplicationContext(), "ACTUAL");
                                    db.deleteApproveSingleHeaderDetails(getApplicationContext(), singleActualHeaderDaoList.get(0).getIoU_lotno());
                                }

                                showTransactionDialog();

                            }
                        });

            }

        } else if (statusValue.equalsIgnoreCase("Approved")) {
            final List<PawhsActualProcSaveDao> pawhsActualProcSaveDaoList = db.getOfflinePawhsActualProcSaveDaoDetails(statusValue);
            List<OtherDetailDao> otherDetailDaoList = null;
            List<SiNoDetailDao> siNoDetailDaoList = null;
            List<QtyDetailDao> qtyDetailDaoList = null;

            for (int i = 0; i < pawhsActualProcSaveDaoList.size(); i++) {


                otherDetailDaoList = db.getOfflineApproveOtherDetailsful(pawhsActualProcSaveDaoList.get(i).getLotNo());
                siNoDetailDaoList = db.getOfflineApproveSiNoDetailsListful(pawhsActualProcSaveDaoList.get(i).getLotNo());
                qtyDetailDaoList = db.getOfflineApproveQtyDetailsListful(pawhsActualProcSaveDaoList.get(i).getLotNo());
                final List<SingleActualHeaderDao> singleActualHeaderDaoList = db.getOfflineAppSingleHeaderListDetailsRelatedLotNO(pawhsActualProcSaveDaoList.get(i).getLotNo());
                for (int j = 0; j < otherDetailDaoList.size(); j++) {
                    if (pawhsActualProcSaveDaoList.get(i).getLotNo().equalsIgnoreCase(otherDetailDaoList.get(j).getIn_lotno())) {
                        postOtherDetailDaosOfflineList.add(new PostOtherDetailDao(otherDetailDaoList.get(j).getId(), otherDetailDaoList.get(j).getIn_Other_row_id(),
                                otherDetailDaoList.get(j).getIn_packaging_cost(), otherDetailDaoList.get(j).getIn_transporting_cost(), otherDetailDaoList.get(j).getIn_unpacking_cost(),
                                otherDetailDaoList.get(j).getIn_local_packaging_cost(), otherDetailDaoList.get(j).getIn_local_transporting_cost(), otherDetailDaoList.get(j).getIn_temp_cost(), otherDetailDaoList.get(j).getIn_temp_cost1(),
                                otherDetailDaoList.get(j).getIn_temp_cost2(), otherDetailDaoList.get(j).getIn_mode_flag(), otherDetailDaoList.get(j).getStatusValue(), otherDetailDaoList.get(j).getIn_lotno()));
                    }
                }

                for (int k = 0; k < siNoDetailDaoList.size(); k++) {
                    if (pawhsActualProcSaveDaoList.get(i).getLotNo().equalsIgnoreCase(siNoDetailDaoList.get(k).getIn_lotno())) {
                        postSiNoDetailDaosOfflineList.add(new PostSiNoDetailDao(siNoDetailDaoList.get(k).getId(), siNoDetailDaoList.get(k).getIn_slno_row_id(),
                                siNoDetailDaoList.get(k).getIn_slno(), siNoDetailDaoList.get(k).getIn_temp1(), siNoDetailDaoList.get(k).getIn_temp2(),
                                siNoDetailDaoList.get(k).getIn_mode_flag(), siNoDetailDaoList.get(k).getStatusValue(), siNoDetailDaoList.get(k).getIn_lotno()));
                    }
                }

                for (int l = 0; l < qtyDetailDaoList.size(); l++) {
                    if (pawhsActualProcSaveDaoList.get(i).getLotNo().equalsIgnoreCase(qtyDetailDaoList.get(l).getIn_lotno())) {
                        postQtyDetailDaosOfflineList.add(new PostQtyDetailDao(qtyDetailDaoList.get(l).getId(), qtyDetailDaoList.get(l).getIn_qty_row_id(),
                                qtyDetailDaoList.get(l).getIn_qty_code(), qtyDetailDaoList.get(l).getIn_qty_name(), qtyDetailDaoList.get(l).getIn_actual_value(),
                                qtyDetailDaoList.get(l).getIn_wr_qty_value(), qtyDetailDaoList.get(l).getIn_mode_flag(), qtyDetailDaoList.get(l).getThresHoldValue(),
                                qtyDetailDaoList.get(l).getUom(), qtyDetailDaoList.get(l).getStatusValue(), qtyDetailDaoList.get(l).getIn_lotno()));
                    }
                }
                otherDetailDaoList.clear();
                qtyDetailDaoList.clear();
                siNoDetailDaoList.clear();
                ActualProcurementHeaderDao actualProcurementHeaderDao = new ActualProcurementHeaderDao();
                actualProcurementHeaderDao.setIn_Actual_rowid(pawhsActualProcSaveDaoList.get(i).getAct_row_id());
                actualProcurementHeaderDao.setIn_LotNo(pawhsActualProcSaveDaoList.get(i).getLotNo());
                actualProcurementHeaderDao.setIn_Farmer_Code(pawhsActualProcSaveDaoList.get(i).getFarmerCode());
                actualProcurementHeaderDao.setIn_Farmer_Name(pawhsActualProcSaveDaoList.get(i).getFarmerName());
                actualProcurementHeaderDao.setIn_Member_Type(pawhsActualProcSaveDaoList.get(i).getMemberType());
                actualProcurementHeaderDao.setIn_Item_Code(pawhsActualProcSaveDaoList.get(i).getItemCode());
                actualProcurementHeaderDao.setIn_Item_Name(pawhsActualProcSaveDaoList.get(i).getItemName());
                actualProcurementHeaderDao.setIn_Actual_Qty(Double.parseDouble(pawhsActualProcSaveDaoList.get(i).getActualqty()));
                actualProcurementHeaderDao.setIn_Actual_Price(Double.parseDouble(pawhsActualProcSaveDaoList.get(i).getActualPrice()));
                actualProcurementHeaderDao.setIn_Actual_Value(Double.parseDouble(pawhsActualProcSaveDaoList.get(i).getActualValue()));
                if (pawhsActualProcSaveDaoList.get(i).getAdvanceAmount() != null && !pawhsActualProcSaveDaoList.get(i).getAdvanceAmount().isEmpty()) {
                    actualProcurementHeaderDao.setIn_advance_amt(Double.parseDouble(pawhsActualProcSaveDaoList.get(i).getAdvanceAmount()));
                } else {
                    actualProcurementHeaderDao.setIn_advance_amt(Double.parseDouble("0"));
                }

                actualProcurementHeaderDao.setIn_no_of_bags(Integer.parseInt(pawhsActualProcSaveDaoList.get(i).getNoofBags()));
                actualProcurementHeaderDao.setIn_Status(pawhsActualProcSaveDaoList.get(i).getStatus());
                // actualProcurementHeaderDao.setIn_mode_flag(pawhsActualProcSaveDaoList.get(i).getModeFlag());
                actualProcurementHeaderDao.setIn_mode_flag(singleActualHeaderDaoList.get(0).getIn_mode_flag());
                actualProcurementHeaderDao.setIn_actual_remarks(pawhsActualProcSaveDaoList.get(i).getActualRemarks());
                actualProcurementHeaderDao.setIn_approved_remarks(pawhsActualProcSaveDaoList.get(i).getApproveRemarks());
                actualProcurementHeaderDao.setIn_pickup_remarks(pawhsActualProcSaveDaoList.get(i).getPickUpRemarks());

                ActualProcurementContextDao actualProcurementContextDao = new ActualProcurementContextDao();
                actualProcurementContextDao.setOrgnId(orgnCode);
                // actualProcurementContextDao.setLocnId(locnId);
                actualProcurementContextDao.setLocnId(ApiUtils.instance);
                actualProcurementContextDao.setUserId(userId);
                actualProcurementContextDao.setLocaleId(localeId);
                actualProcurementContextDao.setActualProcurementHeaderDao(actualProcurementHeaderDao);
                actualProcurementContextDao.setPostQtyDetailDaoList(postQtyDetailDaosOfflineList);
                actualProcurementContextDao.setPostSiNoDetailDaoList(postSiNoDetailDaosOfflineList);
                actualProcurementContextDao.setPostOtherDetailDaoList(postOtherDetailDaosOfflineList);

                ActualProcurementDocumentDao actualProcurementDocumentDao = new ActualProcurementDocumentDao();
                actualProcurementDocumentDao.setActualProcurementContextDao(actualProcurementContextDao);

                ActualProcurementSaveDao actualProcurementSaveDao = new ActualProcurementSaveDao();
                actualProcurementSaveDao.setActualProcurementDocumentDao(actualProcurementDocumentDao);

                Log.v(MyConstants.TAG, actualProcurementSaveDao.toString());

                progressLayout.setVisibility(View.VISIBLE);
//                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                mAPIService.postActualProcurementDetails(actualProcurementSaveDao)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ActualProcurementSaveDao>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ActualProcurementSaveDao actualProcurementSaveDao) {
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                progressLayout.setVisibility(View.GONE);
                                if (statusValue.equalsIgnoreCase("Approved")) {
                                    db.deletePawhsActualProcSaveDaoDetails(getApplicationContext(), statusValue);
                                    db.deleteOtherDetails(getApplicationContext(), "APPROVAL");
                                    db.deleteQtyDetails(getApplicationContext(), "APPROVAL");
                                    db.deleteSiNoDetails(getApplicationContext(), "APPROVAL");
                                } else if (statusValue.equalsIgnoreCase("WR Generation")) {
                                    db.deletePawhsActualProcSaveDaoDetails(getApplicationContext(), statusValue);
                                    db.deleteOtherDetails(getApplicationContext(), "WRGENERATION");
                                    db.deleteQtyDetails(getApplicationContext(), "WRGENERATION");
                                    db.deleteSiNoDetails(getApplicationContext(), "WRGENERATION");
                               /* db.deleteWRQtyDetails(getApplicationContext(),"WRGENERATION");
                                db.deleteWROtherDetails(getApplicationContext(),"WRGENERATION");
                                db.deleteWRSiNoDetails(getApplicationContext(),"WRGENERATION");*//* db.deleteWRQtyDetails(getApplicationContext(),"WRGENERATION");
                                db.deleteWROtherDetails(getApplicationContext(),"WRGENERATION");
                                db.deleteWRSiNoDetails(getApplicationContext(),"WRGENERATION");*/
                                } else if (statusValue.equalsIgnoreCase("Approved")) {
                                    db.deletePawhsActualProcSaveDaoDetails(getApplicationContext(), statusValue);
                                    db.deleteOtherDetails(getApplicationContext(), "Approved");
                                    db.deleteQtyDetails(getApplicationContext(), "Approved");
                                    db.deleteSiNoDetails(getApplicationContext(), "Approved");
                                    db.deleteApproveOtherDetails(getApplicationContext(), "Approved");
                                    db.deleteApproveSiNoDetails(getApplicationContext(), "Approved");
                                    db.deleteApproveQtyDetails(getApplicationContext(), "Approved");
                                    db.deleteApproveSingleHeaderDetails(getApplicationContext(), singleActualHeaderDaoList.get(0).getIoU_lotno());
                                }

                                showTransactionDialog();

                            }
                        });

            }

        }
    }

    private void callActualProcSaveMethod(final String statusValue) {


        List<PostOtherDetailDao> postOtherDetailDaosOfflineList = new ArrayList<>();
        List<PostSiNoDetailDao> postSiNoDetailDaosOfflineList = new ArrayList<>();
        List<PostQtyDetailDao> postQtyDetailDaosOfflineList = new ArrayList<>();

        List<PawhsActualProcSaveDao> pawhsActualProcSaveDaoList = db.getOfflinePawhsActualProcSaveDaoDetails(statusValue);
        if (pawhsActualProcSaveDaoList != null && pawhsActualProcSaveDaoList.size() > 0) {
            List<PostOtherDetailDao> postOtherDetailDaosList = db.getOfflineOtherDetails();
            List<PostSiNoDetailDao> postSiNoDetailDaosList = db.getOfflineSiNoDetails();
            List<PostQtyDetailDao> postQtyDetailDaosList = db.getOfflineQtyDetails();


            for (int i = 0; i < pawhsActualProcSaveDaoList.size(); i++) {
                for (int j = 0; j < postOtherDetailDaosList.size(); j++) {
                    if (pawhsActualProcSaveDaoList.get(i).getLotNo().equalsIgnoreCase(postOtherDetailDaosList.get(j).getLotno())) {
                        postOtherDetailDaosOfflineList.add(postOtherDetailDaosList.get(j));
                    }
                }

                for (int k = 0; k < postSiNoDetailDaosList.size(); k++) {
                    if (pawhsActualProcSaveDaoList.get(i).getLotNo().equalsIgnoreCase(postSiNoDetailDaosList.get(k).getLotno())) {
                        postSiNoDetailDaosOfflineList.add(postSiNoDetailDaosList.get(k));
                    }
                }

                for (int l = 0; l < postQtyDetailDaosList.size(); l++) {
                    if (pawhsActualProcSaveDaoList.get(i).getLotNo().equalsIgnoreCase(postQtyDetailDaosList.get(l).getLotno())) {
                        postQtyDetailDaosOfflineList.add(postQtyDetailDaosList.get(l));
                    }
                }

                if (statusValue.equalsIgnoreCase("WR Generation")) {
                    final String lotnumber = pawhsActualProcSaveDaoList.get(i).getLotNo();
                    String pickupdate = pawhsActualProcSaveDaoList.get(i).getPickupDate();
                    final String remarks = pawhsActualProcSaveDaoList.get(i).getWrRemarks();
                    final String acptQty = pawhsActualProcSaveDaoList.get(i).getAcceptQty();
                    Log.e("Test", "Status" + lotnumber);
                    Log.e("Test", "Status" + pickupdate);
                    Log.e("Test", "Status" + remarks);
                    Log.e("Test", "Status" + acptQty);
                    JSONObject jsonParam = null;
                    try {


                        jsonParam = new JSONObject();
                        JSONObject userd = new JSONObject();
                        jsonParam.put("document", userd);
                        JSONObject user = new JSONObject();
                        user.put("orgnId", orgnCode);
                        user.put("locnId", ApiUtils.instance);
                        user.put("userId", "admin");
                        user.put("localeId", "en_US");
                        userd.put("context", user);
                        JSONObject user2 = new JSONObject();

                        user2.put("in_whs_rowid", 0);
                        user2.put("in_whs_code", "");
                        user2.put("in_LotNo", lotnumber);
                        user2.put("in_Accepted_Qty", Double.parseDouble(acptQty));
                        String[] d = pickupdate.split("-");
                        user2.put("in_Accepted_date", d[2] + "-" + d[1] + "-" + d[0]);
                        user2.put("in_status", "A");
                        user2.put("in_Remarks", remarks);
                        user2.put("in_rowtimestamp", "");
                        user2.put("in_mode_flag", "I");


                        user.put("Header", user2);
                        Log.e("OUTPUT", "" + jsonParam.toString());
                    } catch (Exception e) {
                        Log.e("OUTPUT", "" + e.getMessage());
                    }


//
                    //169.38.77.190:101

                    JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, ApiUtils.TEST_URL_API + "/PAWHS_NewWareHouseReceipt/pawhs_NewWareHouseReceipt_save", jsonParam,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.e("CCCC", "" + response);


                                }


                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("CCCC", "" + error);

                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();

                            return params;
                        }
                    };
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            2500000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
                }

                ActualProcurementHeaderDao actualProcurementHeaderDao = new ActualProcurementHeaderDao();
                actualProcurementHeaderDao.setIn_Actual_rowid(pawhsActualProcSaveDaoList.get(i).getAct_row_id());
                actualProcurementHeaderDao.setIn_LotNo(pawhsActualProcSaveDaoList.get(i).getLotNo());
                actualProcurementHeaderDao.setIn_Farmer_Code(pawhsActualProcSaveDaoList.get(i).getFarmerCode());
                actualProcurementHeaderDao.setIn_Farmer_Name(pawhsActualProcSaveDaoList.get(i).getFarmerName());
                actualProcurementHeaderDao.setIn_Member_Type(pawhsActualProcSaveDaoList.get(i).getMemberType());
                actualProcurementHeaderDao.setIn_Item_Code(pawhsActualProcSaveDaoList.get(i).getItemCode());
                actualProcurementHeaderDao.setIn_Item_Name(pawhsActualProcSaveDaoList.get(i).getItemName());
                actualProcurementHeaderDao.setIn_Actual_Qty(Double.parseDouble(pawhsActualProcSaveDaoList.get(i).getActualqty()));
                actualProcurementHeaderDao.setIn_Actual_Price(Double.parseDouble(pawhsActualProcSaveDaoList.get(i).getActualPrice()));
                actualProcurementHeaderDao.setIn_Actual_Value(Double.parseDouble(pawhsActualProcSaveDaoList.get(i).getActualValue()));
                if (pawhsActualProcSaveDaoList.get(i).getAdvanceAmount() != null && !pawhsActualProcSaveDaoList.get(i).getAdvanceAmount().isEmpty()) {
                    actualProcurementHeaderDao.setIn_advance_amt(Double.parseDouble(pawhsActualProcSaveDaoList.get(i).getAdvanceAmount()));
                } else {
                    actualProcurementHeaderDao.setIn_advance_amt(Double.parseDouble("0"));
                }

                actualProcurementHeaderDao.setIn_no_of_bags(Integer.parseInt(pawhsActualProcSaveDaoList.get(i).getNoofBags()));
                actualProcurementHeaderDao.setIn_Status(pawhsActualProcSaveDaoList.get(i).getStatus());
                actualProcurementHeaderDao.setIn_mode_flag(pawhsActualProcSaveDaoList.get(i).getModeFlag());
                actualProcurementHeaderDao.setIn_actual_remarks(pawhsActualProcSaveDaoList.get(i).getActualRemarks());
                actualProcurementHeaderDao.setIn_approved_remarks(pawhsActualProcSaveDaoList.get(i).getApproveRemarks());
                actualProcurementHeaderDao.setIn_pickup_remarks(pawhsActualProcSaveDaoList.get(i).getPickUpRemarks());
                actualProcurementHeaderDao.setIn_Accepted_Qty(pawhsActualProcSaveDaoList.get(i).getAcceptQty());

                ActualProcurementContextDao actualProcurementContextDao = new ActualProcurementContextDao();
                actualProcurementContextDao.setOrgnId(orgnCode);
                // actualProcurementContextDao.setLocnId(locnId);
                actualProcurementContextDao.setLocnId(ApiUtils.instance);
                actualProcurementContextDao.setUserId(userId);
                actualProcurementContextDao.setLocaleId(localeId);
                actualProcurementContextDao.setActualProcurementHeaderDao(actualProcurementHeaderDao);
                actualProcurementContextDao.setPostQtyDetailDaoList(postQtyDetailDaosOfflineList);
                actualProcurementContextDao.setPostSiNoDetailDaoList(postSiNoDetailDaosOfflineList);
                actualProcurementContextDao.setPostOtherDetailDaoList(postOtherDetailDaosOfflineList);

                ActualProcurementDocumentDao actualProcurementDocumentDao = new ActualProcurementDocumentDao();
                actualProcurementDocumentDao.setActualProcurementContextDao(actualProcurementContextDao);

                ActualProcurementSaveDao actualProcurementSaveDao = new ActualProcurementSaveDao();
                actualProcurementSaveDao.setActualProcurementDocumentDao(actualProcurementDocumentDao);

                Log.v(MyConstants.TAG, actualProcurementSaveDao.toString());

                progressLayout.setVisibility(View.VISIBLE);
//                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                mAPIService.postActualProcurementDetails(actualProcurementSaveDao)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ActualProcurementSaveDao>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ActualProcurementSaveDao actualProcurementSaveDao) {
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                progressLayout.setVisibility(View.GONE);
                                if (statusValue.equalsIgnoreCase("Approved")) {
                                    db.deletePawhsActualProcSaveDaoDetails(getApplicationContext(), statusValue);
                                    db.deleteOtherDetails(getApplicationContext(), "APPROVAL");
                                    db.deleteQtyDetails(getApplicationContext(), "APPROVAL");
                                    db.deleteSiNoDetails(getApplicationContext(), "APPROVAL");
                                } else if (statusValue.equalsIgnoreCase("WR Generation")) {
                                    db.deletePawhsActualProcSaveDaoDetails(getApplicationContext(), statusValue);
                                    db.deleteOtherDetails(getApplicationContext(), "WRGENERATION");
                                    db.deleteQtyDetails(getApplicationContext(), "WRGENERATION");
                                    db.deleteSiNoDetails(getApplicationContext(), "WRGENERATION");
                               /* db.deleteWRQtyDetails(getApplicationContext(),"WRGENERATION");
                                db.deleteWROtherDetails(getApplicationContext(),"WRGENERATION");
                                db.deleteWRSiNoDetails(getApplicationContext(),"WRGENERATION");*//* db.deleteWRQtyDetails(getApplicationContext(),"WRGENERATION");
                                db.deleteWROtherDetails(getApplicationContext(),"WRGENERATION");
                                db.deleteWRSiNoDetails(getApplicationContext(),"WRGENERATION");*/
                                } else if (statusValue.equalsIgnoreCase("Actual")) {
                                    db.deletePawhsActualProcSaveDaoDetails(getApplicationContext(), statusValue);
                                    db.deleteOtherDetails(getApplicationContext(), "ACTUAL");
                                    db.deleteQtyDetails(getApplicationContext(), "ACTUAL");
                                    db.deleteSiNoDetails(getApplicationContext(), "ACTUAL");
                                    db.deleteApproveOtherDetails(getApplicationContext(), "ACTUAL");
                                    db.deleteApproveSiNoDetails(getApplicationContext(), "ACTUAL");
                                    db.deleteApproveQtyDetails(getApplicationContext(), "ACTUAL");
                                }

                                showTransactionDialog();

                            }
                        });


            }
        } else {

            showErrorDialog("No Records Available");

        }


    }

    private void callProductMasterAllProductJsonDetails() {
        progressLayout.setVisibility(View.VISIBLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        db.deleteAllProductMasterAllProduct(getApplicationContext());
        db.deleteEstimateList(getApplicationContext());
        db.deleteSpmDetailsList(getApplicationContext());

        PmContextDao pmContextDao = new PmContextDao();
        pmContextDao.setOrgnId(orgnCode);
        // pmContextDao.setLocnId(locnId);
        pmContextDao.setLocnId(ApiUtils.instance);
        pmContextDao.setUserId(userId);
        pmContextDao.setLocaleId(localeId);
        pmContextDao.setFilterBy_Option("ALL");
        pmContextDao.setFilterBy_Code(".");
        pmContextDao.setFilterBy_FromValue(".");
        pmContextDao.setFilterBy_ToValue(".");

        mAPIService.getProductMasterAllProductDetails(pmContextDao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProductMasterDao>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ProductMasterDao productMasterDao) {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressLayout.setVisibility(View.GONE);
                        List<PmListDao> pmListDaoList = productMasterDao.getPmContextDao().getPmListDaoList();

                        for (int i = 0; i < pmListDaoList.size(); i++) {

                            PmListDao pmListDao = new PmListDao(1, pmListDaoList.get(i).getOut_product_rowid(),
                                    pmListDaoList.get(i).getOut_pawhs_code(), pmListDaoList.get(i).getOut_agg_code(), pmListDaoList.get(i).getOut_product_code(),
                                    pmListDaoList.get(i).getOut_product_name(), pmListDaoList.get(i).getOut_product_category(), pmListDaoList.get(i).getOut_product_subcategory(),
                                    pmListDaoList.get(i).getOut_hsn_code(), pmListDaoList.get(i).getOut_hsn_desctiption(), pmListDaoList.get(i).getOut_status_code(),
                                    pmListDaoList.get(i).getOut_status_desc(), pmListDaoList.get(i).getOut_row_timestamp(), pmListDaoList.get(i).getOut_uomtype_code(), pmListDaoList.get(i).getOut_crop_variety(),pmListDaoList.get(i).getOut_value_withtax());
                            db.addAllProductMasterAllProduct(pmListDao);

                        }
                        List<PmListDao> pmListDaosList = db.getOfflineMasterAllProductDetails();

                        for (int i = 0; i < pmListDaosList.size(); i++) {
                            String productRowId = pmListDaosList.get(i).getOut_product_rowid();
                            String productCode = pmListDaosList.get(i).getOut_product_code();

                            callQualityParameterJsonDetails(productRowId, productCode);

                        }
                        //  callLotnoListDetails();
                        callFormerListJsonDetails();
                        //showCompleteDialog();

                        Log.i(MyConstants.TAG, String.valueOf(pmListDaoList.size()));


                    }
                });
    }

    private void callLotnoListDetails() {
        /*progressLayout.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);*/

        EstActAppContextDao estActAppContextDao = new EstActAppContextDao();
        estActAppContextDao.setOrgnId(orgnCode);
        // estActAppContextDao.setLocnId(locnId);
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
                        /*getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressLayout.setVisibility(View.GONE);*/
                        List<EstimateListDao> estimateListDaoList = estimateActualApprovedListDao.getEstActAppContextDao().getEstimateListDaoList();

                        for (int i = 0; i < estimateListDaoList.size(); i++) {

                            EstimateListDao estimateListDao = new EstimateListDao(1, estimateListDaoList.get(i).getOut_Estm_rowid(),
                                    estimateListDaoList.get(i).getOut_LotNo(), estimateListDaoList.get(i).getOut_agg_code(), estimateListDaoList.get(i).getOut_Farmer_Code(),
                                    estimateListDaoList.get(i).getOut_Farmer_Name(), estimateListDaoList.get(i).getOut_Member_Type(), estimateListDaoList.get(i).getOut_Item_Code(),
                                    estimateListDaoList.get(i).getOut_Item_Name(), estimateListDaoList.get(i).getOut_Estimated_Qty(), estimateListDaoList.get(i).getOut_Estimated_Price(),
                                    estimateListDaoList.get(i).getOut_Estimated_Value(), estimateListDaoList.get(i).getOut_Remarks(), estimateListDaoList.get(i).getOut_Status(), estimateListDaoList.get(i).getOut_Estimated_PickupDate(), "No");
                            db.addAllEstimateListDetails(estimateListDao);

                        }

                        List<PmListDao> pmListDaosList = db.getOfflineMasterAllProductDetails();

                        for (int i = 0; i < pmListDaosList.size(); i++) {
                            String productRowId = pmListDaosList.get(i).getOut_product_rowid();
                            String productCode = pmListDaosList.get(i).getOut_product_code();

                            callQualityParameterJsonDetails(productRowId, productCode);

                        }

                        //callFormerListJsonDetails();
                        db.deleteApproveList(getApplicationContext());
                        getActualProcurementListDetails();


                    }
                });
    }
    private void callLotnoListDetails2() {
        SQLiteDatabase dbs = db.getWritableDatabase();
        dbs.execSQL("delete from EstimateList");
        // EstimateList
        /*progressLayout.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);*/

        EstActAppContextDao estActAppContextDao = new EstActAppContextDao();
        estActAppContextDao.setOrgnId(orgnCode);
        // estActAppContextDao.setLocnId(locnId);
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
                        /*getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressLayout.setVisibility(View.GONE);*/
                        List<EstimateListDao> estimateListDaoList = estimateActualApprovedListDao.getEstActAppContextDao().getEstimateListDaoList();

                        for (int i = 0; i < estimateListDaoList.size(); i++) {
                            if (estimateListDaoList.get(i).getOut_Status().equalsIgnoreCase("Estimate")) {

                                EstimateListDao estimateListDao = new EstimateListDao(1, estimateListDaoList.get(i).getOut_Estm_rowid(),
                                        estimateListDaoList.get(i).getOut_LotNo(), estimateListDaoList.get(i).getOut_agg_code(), estimateListDaoList.get(i).getOut_Farmer_Code(),
                                        estimateListDaoList.get(i).getOut_Farmer_Name(), estimateListDaoList.get(i).getOut_Member_Type(), estimateListDaoList.get(i).getOut_Item_Code(),
                                        estimateListDaoList.get(i).getOut_Item_Name(), estimateListDaoList.get(i).getOut_Estimated_Qty(), estimateListDaoList.get(i).getOut_Estimated_Price(),
                                        estimateListDaoList.get(i).getOut_Estimated_Value(), estimateListDaoList.get(i).getOut_Remarks(), estimateListDaoList.get(i).getOut_Status(), estimateListDaoList.get(i).getOut_Estimated_PickupDate(), "No");
                                db.addAllEstimateListDetails(estimateListDao);
                                if (estimateListDaoList.get(i).getOut_Status().equalsIgnoreCase("Estimate")) {

                                }
                            }

                        }

//                        List<PmListDao> pmListDaosList = db.getOfflineMasterAllProductDetails();
//
//                        for (int i = 0; i < pmListDaosList.size(); i++) {
//                            String productRowId = pmListDaosList.get(i).getOut_product_rowid();
//                            String productCode = pmListDaosList.get(i).getOut_product_code();
//
//                            callQualityParameterJsonDetails(productRowId, productCode);
//
//                        }
//
//                        //callFormerListJsonDetails();
//                        db.deleteApproveList(getApplicationContext());
//                        getActualProcurementListDetails();


                    }
                });
    }
    private void callLotnoListDetailsOfApproveWare(final String status) {
        if (status.equalsIgnoreCase("actual")) {
            db.deletepproveSingleHeaderDetails(getApplicationContext());
            db.deleteApproveQtyDetails(getApplicationContext());
            db.deleteApproveSiNoDetails(getApplicationContext());
            db.deleteApproveOtherDetailsList(getApplicationContext());
        } else {
            db.deleteWarehouseSingleHeaderDetails(getApplicationContext());
            db.deleteWareHouseQtyDetails(getApplicationContext());
            db.deleteWareHouseSiNoDetails(getApplicationContext());
            db.deleteWareHouseOtherDetailsList(getApplicationContext());

        }

        progressLayout.setVisibility(View.VISIBLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

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

                        if (status.equalsIgnoreCase("actual")) {
                            List<ActualListDao> actualListDaoList = estimateActualApprovedListDao.getEstActAppContextDao().getActualListDaoList();
                            for (int i = 0; i < actualListDaoList.size(); i++) {
                                ActualListDao actualListDao = new ActualListDao(1, actualListDaoList.get(i).getOut_act_rowid(), actualListDaoList.get(i).getOut_lotno(),
                                        actualListDaoList.get(i).getOut_agg_code(), actualListDaoList.get(i).getOut_farmer_code(), actualListDaoList.get(i).getOut_farmer_name(),
                                        actualListDaoList.get(i).getOut_member_type(), actualListDaoList.get(i).getOut_item_code(), actualListDaoList.get(i).getOut_item_name(),
                                        actualListDaoList.get(i).getOut_actual_qty(), actualListDaoList.get(i).getOut_actual_price(), actualListDaoList.get(i).getOut_actual_value(),
                                        actualListDaoList.get(i).getOut_actual_date(), actualListDaoList.get(i).getOut_advance_amt(), actualListDaoList.get(i).getOut_approve_date(),
                                        actualListDaoList.get(i).getOut_pickup_date(), actualListDaoList.get(i).getOut_wr_date(), actualListDaoList.get(i).getOut_no_of_bags(),
                                        actualListDaoList.get(i).getOut_status(), actualListDaoList.get(i).getOut_actual_remarks(), actualListDaoList.get(i).getOut_approved_remarks(),
                                        actualListDaoList.get(i).getOut_pickup_remarks(), actualListDaoList.get(i).getOut_wr_remarks(), actualListDaoList.get(i).getIn_actual_attach(), actualListDaoList.get(i).getIn_qcperson_name(), actualListDaoList.get(i).getIn_vehicle_no(),actualListDaoList.get(i).getIn_vehicle_type(),actualListDaoList.get(i).getIn_destination(),actualListDaoList.get(i).getIn_LRP_Name(),actualListDaoList.get(i).getIn_LRP_Mobile_no(),actualListDaoList.get(i).getIn_Payment_type(),actualListDaoList.get(i).getIn_Bank_acc_no(),actualListDaoList.get(i).getIn_cheque_no());
                                db.addAllApproveListDetails(actualListDao);

                            }

                            List<ActualListDao> actualListDaos = db.getOfflineApproveListDetails();

                            for (int i = 0; i < actualListDaos.size(); i++) {
                                String lotNo = actualListDaos.get(i).getOut_lotno();
                                String aggCode = actualListDaos.get(i).getOut_agg_code();
                                int rowId = actualListDaos.get(i).getOut_act_rowid();

                                callSingleActualProcurementDetails(lotNo, aggCode, rowId, status);

                            }


                        } else {

                            List<ActualListDao> wareHouseListDaoList = estimateActualApprovedListDao.getEstActAppContextDao().getApprovedListDaoList();
                            for (int i = 0; i < wareHouseListDaoList.size(); i++) {
                                ActualListDao actualListDao = new ActualListDao(1, wareHouseListDaoList.get(i).getOut_act_rowid(), wareHouseListDaoList.get(i).getOut_lotno(),
                                        wareHouseListDaoList.get(i).getOut_agg_code(), wareHouseListDaoList.get(i).getOut_farmer_code(), wareHouseListDaoList.get(i).getOut_farmer_name(),
                                        wareHouseListDaoList.get(i).getOut_member_type(), wareHouseListDaoList.get(i).getOut_item_code(), wareHouseListDaoList.get(i).getOut_item_name(),
                                        wareHouseListDaoList.get(i).getOut_actual_qty(), wareHouseListDaoList.get(i).getOut_actual_price(), wareHouseListDaoList.get(i).getOut_actual_value(),
                                        wareHouseListDaoList.get(i).getOut_actual_date(), wareHouseListDaoList.get(i).getOut_advance_amt(), wareHouseListDaoList.get(i).getOut_approve_date(),
                                        wareHouseListDaoList.get(i).getOut_pickup_date(), wareHouseListDaoList.get(i).getOut_wr_date(), wareHouseListDaoList.get(i).getOut_no_of_bags(),
                                        wareHouseListDaoList.get(i).getOut_status(), wareHouseListDaoList.get(i).getOut_actual_remarks(), wareHouseListDaoList.get(i).getOut_approved_remarks(),
                                        wareHouseListDaoList.get(i).getOut_pickup_remarks(), wareHouseListDaoList.get(i).getOut_wr_remarks(), "", "", "","","","","","","","");
                                db.addAllWareHouseListDetails(actualListDao);

                            }

                            List<ActualListDao> actualListDaos = db.getOfflineWareHouseListDetails();

                            for (int i = 0; i < actualListDaos.size(); i++) {
                                String lotNo = actualListDaos.get(i).getOut_lotno();
                                String aggCode = actualListDaos.get(i).getOut_agg_code();
                                int rowId = actualListDaos.get(i).getOut_act_rowid();

                                callSingleActualProcurementDetails(lotNo, aggCode, rowId, status);

                            }

                        }

                        getWareHouseReceiptListDetails();






                      /*  for (int i = 0; i < estimateListDaoList.size(); i++) {

                            EstimateListDao estimateListDao = new EstimateListDao(1,estimateListDaoList.get(i).getOut_Estm_rowid(),
                                    estimateListDaoList.get(i).getOut_LotNo(),estimateListDaoList.get(i).getOut_agg_code(),estimateListDaoList.get(i).getOut_Farmer_Code(),
                                    estimateListDaoList.get(i).getOut_Farmer_Name(),estimateListDaoList.get(i).getOut_Member_Type(),estimateListDaoList.get(i).getOut_Item_Code(),
                                    estimateListDaoList.get(i).getOut_Item_Name(),estimateListDaoList.get(i).getOut_Estimated_Qty(),estimateListDaoList.get(i).getOut_Estimated_Price(),
                                    estimateListDaoList.get(i).getOut_Estimated_Value(),estimateListDaoList.get(i).getOut_Remarks(),estimateListDaoList.get(i).getOut_Status(),estimateListDaoList.get(i).getOut_Estimated_PickupDate(),"No");
                            db.addAllEstimateListDetails(estimateListDao);

                        }*/


                    }
                });
    }

    private void getWareHouseReceiptListDetails() {
        db.deleteAllWareHouseReceiptListDetails(getApplicationContext());
        progressLayout.setVisibility(View.VISIBLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        WareReceiptContextDao wareReceiptContextDao = new WareReceiptContextDao();
        wareReceiptContextDao.setOrgnId(orgnCode);
        //  wareReceiptContextDao.setLocnId(locnId);
        wareReceiptContextDao.setLocnId(ApiUtils.instance);
        wareReceiptContextDao.setUserId(userId);
        wareReceiptContextDao.setLocaleId(localeId);
        wareReceiptContextDao.setFilterBy_Option("ALL");
        wareReceiptContextDao.setFilterBy_Code(".");
        wareReceiptContextDao.setFilterBy_FromValue(".");
        wareReceiptContextDao.setFilterBy_ToValue(".");

        mAPIService.getWareHouseReceiptListDetails(wareReceiptContextDao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WareHouseReceiptListDao>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WareHouseReceiptListDao wareHouseReceiptListDao) {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressLayout.setVisibility(View.GONE);

                        List<WareHouseListDao> wareHouseListDaoList = wareHouseReceiptListDao.getWareReceiptContextDao().getWareHouseListDaoList();

                        for (int i = 0; i < wareHouseListDaoList.size(); i++) {
                            WareHouseListDao wareHouseListDao = new WareHouseListDao(1, wareHouseListDaoList.get(i).getOut_whs_rowid(), wareHouseListDaoList.get(i).getOut_LotNo(),
                                    wareHouseListDaoList.get(i).getOut_whs_code(), wareHouseListDaoList.get(i).getOut_farmer_code(), wareHouseListDaoList.get(i).getOut_farmer_name(),
                                    wareHouseListDaoList.get(i).getOut_member_type(), wareHouseListDaoList.get(i).getOut_item_code(), wareHouseListDaoList.get(i).getOut_item_name(),
                                    wareHouseListDaoList.get(i).getOut_accepted_qty(), wareHouseListDaoList.get(i).getOut_payment_advcno(), wareHouseListDaoList.get(i).getOut_Accepted_date(),
                                    wareHouseListDaoList.get(i).getOut_status(), wareHouseListDaoList.get(i).getOut_Remarks(), wareHouseListDaoList.get(i).getOut_row_timestamp());
                            db.addAllWarehouseReceiptListDetails(wareHouseListDao);

                        }

                        showCompleteDialog();
                    }
                });


    }

    private void getActualProcurementListDetails() {

        db.deleteAllActualProListDetails(getApplicationContext());

     /*   progressLayout.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);*/

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

                        List<ActProListDao> actProListDaoList = actualProcurementListDao.getActProContextDao().getActProListDaoList();

                        for (int i = 0; i < actProListDaoList.size(); i++) {
                            ActProListDao actProListDao = new ActProListDao(1, actProListDaoList.get(i).getOut_act_rowid(), actProListDaoList.get(i).getOut_agg_code(),
                                    actProListDaoList.get(i).getOut_lotno(), actProListDaoList.get(i).getOut_farmer_code(), actProListDaoList.get(i).getOut_farmer_name(),
                                    actProListDaoList.get(i).getOut_member_type(), actProListDaoList.get(i).getOut_item_code(), actProListDaoList.get(i).getOut_item_name(),
                                    actProListDaoList.get(i).getOut_status(), actProListDaoList.get(i).getOut_row_timestamp());
                            db.addAllActualProListDetails(actProListDao);

                        }

                        callLotnoListDetailsOfApproveWare("actual");
                    }
                });
    }

    private void callSingleActualProcurementDetails(String lotNo, String aggCode, int rowId, final String status) {
        progressLayout.setVisibility(View.VISIBLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


        SingleActProcContextDao singleActProcContextDao = new SingleActProcContextDao();
        singleActProcContextDao.setOrgnId(orgnCode);
        //  singleActProcContextDao.setLocnId(locnId);
        singleActProcContextDao.setLocnId(ApiUtils.instance);
        singleActProcContextDao.setUserId(userId);
        singleActProcContextDao.setLocaleId(localeId);
        singleActProcContextDao.setIOU_act_rowid(rowId);
        singleActProcContextDao.setIOU_lotno(lotNo);
        singleActProcContextDao.setIOU_agg_code(aggCode);

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

                        /*singleActualHeaderDao=singleActualProcurementDao.getSingleActProcContextDao().getSingleActualHeaderDao();
                        qtyDetailDaoList=singleActualProcurementDao.getSingleActProcContextDao().getQtyDetailDaoList();
                        otherDetailDaoList=singleActualProcurementDao.getSingleActProcContextDao().getOtherDetailDaoList();
                        siNoDetailDaoList=singleActualProcurementDao.getSingleActProcContextDao().getSiNoDetailDaoList();
                        setActualValues();*/
//                        if (status.equalsIgnoreCase("actual")) {
//
//                            SingleActualHeaderDao singleActualHeaderDao = singleActualProcurementDao.getSingleActProcContextDao().getSingleActualHeaderDao();
//                            db.addAllApproveSingleHeaderDetails(new SingleActualHeaderDao(1, singleActualHeaderDao.getIoU_act_rowid(),
//                                    singleActualHeaderDao.getIoU_agg_code(), singleActualHeaderDao.getIoU_lotno(), singleActualHeaderDao.getIn_farmer_code(),
//                                    singleActualHeaderDao.getIn_farmer_name(), singleActualHeaderDao.getIn_member_type(), singleActualHeaderDao.getIn_item_code(),
//                                    singleActualHeaderDao.getIn_item_name(), singleActualHeaderDao.getIn_actual_qty(), singleActualHeaderDao.getIn_actual_price(),
//                                    singleActualHeaderDao.getIn_actual_value(), singleActualHeaderDao.getIn_advance_amt(), singleActualHeaderDao.getIn_no_of_bags(),
//                                    singleActualHeaderDao.getIn_status(), singleActualHeaderDao.getIn_actual_remarks(), singleActualHeaderDao.getIn_approved_remarks(),
//                                    singleActualHeaderDao.getIn_pickup_remarks(), singleActualHeaderDao.getIn_wr_remarks(), singleActualHeaderDao.getIn_mode_flag(),
//                                    singleActualHeaderDao.getIn_row_timestamp(), singleActualHeaderDao.getIn_actual_attach(), singleActualHeaderDao.getIn_qcperson_name(), singleActualHeaderDao.getIn_vehicle_no(),singleActualHeaderDao.getIn_vehicle_type(),singleActualHeaderDao.getIn_destination(),singleActualHeaderDao.getIn_LRP_Name(),singleActualHeaderDao.getIn_LRP_Mobile_no(),singleActualHeaderDao.getIn_Payment_type(),singleActualHeaderDao.getIn_Bank_acc_no(),singleActualHeaderDao.getIn_cheque_no()));
//
//                            List<QtyDetailDao> qtyDetailDaoList = singleActualProcurementDao.getSingleActProcContextDao().getQtyDetailDaoList();
//                            for (int i = 0; i < qtyDetailDaoList.size(); i++) {
//                                QtyDetailDao actualListDao = new QtyDetailDao(1, qtyDetailDaoList.get(i).getIn_qty_row_id(), qtyDetailDaoList.get(i).getIn_qty_code(),
//                                        qtyDetailDaoList.get(i).getIn_qty_name(), qtyDetailDaoList.get(i).getIn_actual_value(), qtyDetailDaoList.get(i).getIn_wr_qty_value(),
//                                        qtyDetailDaoList.get(i).getIn_mode_flag(), qtyDetailDaoList.get(i).getIn_agg_code(), qtyDetailDaoList.get(i).getIn_lotno(),
//                                        qtyDetailDaoList.get(i).getIn_item_code(), "ACTUAL");
//                                db.addAllApproveQtyDetails(actualListDao);
//
//                            }
//
//                            List<SiNoDetailDao> siNoDetailDaoList = singleActualProcurementDao.getSingleActProcContextDao().getSiNoDetailDaoList();
//                            for (int i = 0; i < siNoDetailDaoList.size(); i++) {
//                                SiNoDetailDao siNoDetailDao = new SiNoDetailDao(1, siNoDetailDaoList.get(i).getIn_slno_row_id(), siNoDetailDaoList.get(i).getIn_slno(),
//                                        siNoDetailDaoList.get(i).getIn_temp1(), siNoDetailDaoList.get(i).getIn_temp2(), siNoDetailDaoList.get(i).getIn_mode_flag(),
//                                        siNoDetailDaoList.get(i).getIn_agg_code(), siNoDetailDaoList.get(i).getIn_lotno(), "ACTUAL");
//                                db.addAllApproveSiNoDetails(siNoDetailDao);
//
//                            }
//
//                            List<OtherDetailDao> otherDetailDaoList = singleActualProcurementDao.getSingleActProcContextDao().getOtherDetailDaoList();
//                            for (int i = 0; i < otherDetailDaoList.size(); i++) {
//                                OtherDetailDao otherDetailDao = new OtherDetailDao(1, otherDetailDaoList.get(i).getIn_Other_row_id(), otherDetailDaoList.get(i).getIn_packaging_cost(),
//                                        otherDetailDaoList.get(i).getIn_transporting_cost(), otherDetailDaoList.get(i).getIn_unpacking_cost(), otherDetailDaoList.get(i).getIn_local_packaging_cost(),
//                                        otherDetailDaoList.get(i).getIn_local_transporting_cost(), otherDetailDaoList.get(i).getIn_temp_cost(), otherDetailDaoList.get(i).getIn_temp_cost1(), otherDetailDaoList.get(i).getIn_temp_cost2(),
//                                        siNoDetailDaoList.get(i).getIn_mode_flag(), otherDetailDaoList.get(i).getIn_agg_code(), otherDetailDaoList.get(i).getIn_lotno(), "ACTUAL");
//                                db.addAllApproveOtherDetailsList(otherDetailDao);
//
//                            }
//
//
//                        } else {
//
//                            SingleActualHeaderDao singleActualHeaderDao = singleActualProcurementDao.getSingleActProcContextDao().getSingleActualHeaderDao();
//                            db.addAllWarehouseSingleHeaderDetails(singleActualHeaderDao);
//
//                            List<QtyDetailDao> qtyDetailDaoList = singleActualProcurementDao.getSingleActProcContextDao().getQtyDetailDaoList();
//                            for (int i = 0; i < qtyDetailDaoList.size(); i++) {
//                                QtyDetailDao actualListDao = new QtyDetailDao(1, qtyDetailDaoList.get(i).getIn_qty_row_id(), qtyDetailDaoList.get(i).getIn_qty_code(),
//                                        qtyDetailDaoList.get(i).getIn_qty_name(), qtyDetailDaoList.get(i).getIn_actual_value(), qtyDetailDaoList.get(i).getIn_wr_qty_value(),
//                                        qtyDetailDaoList.get(i).getIn_mode_flag(), qtyDetailDaoList.get(i).getIn_agg_code(), qtyDetailDaoList.get(i).getIn_lotno(),
//                                        qtyDetailDaoList.get(i).getIn_item_code(), "WRGENERATION");
//                                db.addAllWareHouseQtyDetails(actualListDao);
//
//                            }
//
//                            List<SiNoDetailDao> siNoDetailDaoList = singleActualProcurementDao.getSingleActProcContextDao().getSiNoDetailDaoList();
//                            for (int i = 0; i < siNoDetailDaoList.size(); i++) {
//                                SiNoDetailDao siNoDetailDao = new SiNoDetailDao(1, siNoDetailDaoList.get(i).getIn_slno_row_id(), siNoDetailDaoList.get(i).getIn_slno(),
//                                        siNoDetailDaoList.get(i).getIn_temp1(), siNoDetailDaoList.get(i).getIn_temp2(), siNoDetailDaoList.get(i).getIn_mode_flag(),
//                                        siNoDetailDaoList.get(i).getIn_agg_code(), siNoDetailDaoList.get(i).getIn_lotno(), "WRGENERATION");
//                                db.addAllWareHouseSiNoDetails(siNoDetailDao);
//
//                            }
//
//                            List<OtherDetailDao> otherDetailDaoList = singleActualProcurementDao.getSingleActProcContextDao().getOtherDetailDaoList();
//                            for (int i = 0; i < otherDetailDaoList.size(); i++) {
//                                OtherDetailDao otherDetailDao = new OtherDetailDao(1, otherDetailDaoList.get(i).getIn_Other_row_id(), otherDetailDaoList.get(i).getIn_packaging_cost(),
//                                        otherDetailDaoList.get(i).getIn_transporting_cost(), otherDetailDaoList.get(i).getIn_unpacking_cost(), otherDetailDaoList.get(i).getIn_local_packaging_cost(),
//                                        otherDetailDaoList.get(i).getIn_local_transporting_cost(), otherDetailDaoList.get(i).getIn_temp_cost(), otherDetailDaoList.get(i).getIn_temp_cost1(), otherDetailDaoList.get(i).getIn_temp_cost2(),
//                                        siNoDetailDaoList.get(i).getIn_mode_flag(), otherDetailDaoList.get(i).getIn_agg_code(), otherDetailDaoList.get(i).getIn_lotno(), "WRGENERATION");
//                                db.addAllWareHouseOtherDetailsList(otherDetailDao);
//
//                            }
//
//                        }

                    }
                });


    }

    private void callQualityParameterJsonDetails(String productRowId, String productCode) {

       /* progressLayout.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);*/

        SpmContextDao spmContextDao = new SpmContextDao();
        spmContextDao.setOrgnId(orgnCode);
        // spmContextDao.setLocnId(locnId);
        spmContextDao.setLocnId(ApiUtils.instance);
        spmContextDao.setUserId(userId);
        spmContextDao.setLocaleId(localeId);
        SpmHeaderDao spmHeaderDao = new SpmHeaderDao();
        spmHeaderDao.setIOU_product_rowid(Integer.parseInt(productRowId));
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
                       /* getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressLayout.setVisibility(View.GONE);*/

                        List<SpmDetailDao> spmDetailDaoList = singleProductMasterDao.getSpmContextDao().getSpmDetailDaoList();

                        for (int i = 0; i < spmDetailDaoList.size(); i++) {

                            SpmDetailDao spmDetailDao = new SpmDetailDao(1, spmDetailDaoList.get(i).getIn_product_dtl_rowid(),
                                    spmDetailDaoList.get(i).getIn_pawhs_code(), spmDetailDaoList.get(i).getIn_row_slno(), spmDetailDaoList.get(i).getIn_maize_code(),
                                    spmDetailDaoList.get(i).getIn_maize_name(), spmDetailDaoList.get(i).getIn_range(), spmDetailDaoList.get(i).getIn_maize_interval(),
                                    spmDetailDaoList.get(i).getIn_maize_unit(), spmDetailDaoList.get(i).getIn_status_code(), spmDetailDaoList.get(i).getIn_mode_flag());
                            db.addAllSpmDetailsList(spmDetailDao);

                        }
                    }
                });

    }

    private void callFormerListJsonDetails() {

        progressLayout.setVisibility(View.VISIBLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        formerDaoList = new ArrayList<>();

        db.deleteFarmerList(getApplicationContext());
        FormerContextDao formerContextDao = new FormerContextDao();
        formerContextDao.setOrgnId(sharedpreferences.getString("Fcode", ""));
        // formerContextDao.setLocnId(locnId);
        formerContextDao.setLocnId(ApiUtils.instance);
        formerContextDao.setUserId(userId);
        formerContextDao.setLocaleId(localeId);
        formerContextDao.setFilterBy_Option("ALL");
        formerContextDao.setFilterBy_Code(sharedpreferences.getString("tcode", ""));
        formerContextDao.setFilterBy_FromValue(sharedpreferences.getString("gcode", ""));
        formerContextDao.setFilterBy_ToValue(sharedpreferences.getString("vcode", ""));
        formerContextDao.setInstance(Pojokyc.instance);

        mAPIService.getFormetListDetails(formerContextDao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FormerListDao>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FormerListDao formerListDao) {

                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressLayout.setVisibility(View.GONE);

                        // List<FormerDao> formerDaoList = formerListDao.getFormerContextDao().getFormerDaoList();

                       /* for (int i = 0; i < formerDaoList.size(); i++) {

                            FormerDao formerDao = new FormerDao(1,formerDaoList.get(i).getFarmer_rowid(),
                                    formerDaoList.get(i).getFarmer_code(),formerDaoList.get(i).getFarmer(),formerDaoList.get(i).getFhw_name(),
                                    formerDaoList.get(i).getFarmer_name(),formerDaoList.get(i).getFarmer_dist(),formerDaoList.get(i).getFarmer_dist_desc(),
                                    formerDaoList.get(i).getFarmer_taluk(),formerDaoList.get(i).getFarmer_taluk_desc(),formerDaoList.get(i).getFarmer_panchayat(),
                                    formerDaoList.get(i).getFarmer_panchayat_desc(),formerDaoList.get(i).getFarmer_village(),formerDaoList.get(i).getFarmer_village_desc());
                            db.addAllFarmerDetails(formerDao);

                        }
                        new SomeTask().execute();
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressLayout.setVisibility(View.GONE);
                        Log.i(MyConstants.TAG, String.valueOf(formerDaoList.size()));
                        showCompleteDialog();*/
                        formerDaoList = formerListDao.getFormerContextDao().getFormerDaoList();
                        new SomeTask().execute();

                    }
                });

    }

    class SomeTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // super.onPreExecute();
            progressLayout.setVisibility(View.VISIBLE);
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < formerDaoList.size(); i++) {

                FormerDao formerDao = new FormerDao(1, formerDaoList.get(i).getFarmer_rowid(),
                        formerDaoList.get(i).getFarmer_code(), formerDaoList.get(i).getFarmer(), formerDaoList.get(i).getFhw_name(),
                        formerDaoList.get(i).getFarmer_name(), formerDaoList.get(i).getFarmer_dist(), formerDaoList.get(i).getFarmer_dist_desc(),
                        formerDaoList.get(i).getFarmer_taluk(), formerDaoList.get(i).getFarmer_taluk_desc(), formerDaoList.get(i).getFarmer_panchayat(),
                        formerDaoList.get(i).getFarmer_panchayat_desc(), formerDaoList.get(i).getFarmer_village(), formerDaoList.get(i).getFarmer_village_desc());
                db.addAllFarmerDetails(formerDao);

            }

            save2();

            //callLotnoListDetails();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            // progressLayout.setVisibility(View.GONE);
            //showCompleteDialog();

            // callLotnoListDetailsOfApproveWare("WareHouse");

        }
    }


    private void showCompleteDialog() {


        if (cd == 0) {
            cd = 1;
            new AlertDialog.Builder(this)
                    .setTitle("Success!")
                    .setMessage("Master Sync Completed!")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    })
                    .show();
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

    private void showTransactionDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Success!")
                .setMessage("Transaction uploaded successfully!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void loadFragment(Fragment fragment) {

        Utility.loadFragment(getSupportFragmentManager(), R.id.content_frame, fragment);

    }

    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are You Sure Want to Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                       // pawhsApplication.clearPreferenceData(mContext, ApiUtils.IN_ROLE_CODE);
                       // pawhsApplication.clearPreferenceData(mContext, ApiUtils.IN_USER_NAME);
                        Intent i = new Intent(getApplicationContext(),Landpage.class);
                        startActivity(i);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showConnectionDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Please Check Your Network Connection")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .show();
    }

    public void offlinesave() {
        final SQLiteDatabase dbs = db.getWritableDatabase();
        String selectQuery5 = "SELECT  * FROM farmerh where flag = 0";
        final Cursor cc = dbs.rawQuery(selectQuery5, null);
        Log.e("NULL2", "" + cc.getCount());

        try {


            if (cc.moveToFirst()) {
                farid = cc.getString(0);
                Log.e("NULLK", "" + cc.getString(24));

                if (cc.getString(5).equalsIgnoreCase("male")) {
                    gf = "QCD_GENDER_MALE";
                }
                if (cc.getString(5).equalsIgnoreCase("female")) {
                    gf = "QCD_GENDER_FEMALE";
                }
                if (cc.getString(5).equalsIgnoreCase("transgender")) {
                    gf = "QCD_GENDER_TRANSGENDER";
                }


                jsonParam = new JSONObject();


                JSONObject user2 = new JSONObject();
                if (cc.getString(24).equalsIgnoreCase("0")) {
                    encodedImage = "0";
                } else {
                    try {

                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(cc.getString(24)));

                        byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        //Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());


                        encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                user2.put("in_farmer_rowid", 0);
                user2.put("in_farmer_code", "");
                user2.put("in_version_no", 1);
                user2.put("in_photo_farmer", encodedImage);

                fmid = cc.getString(0);

                n1 = cc.getString(1);

                n10 = cc.getString(10);
                n11 = cc.getString(11);
                n12 = cc.getString(12);
                n13 = cc.getString(13);

                adsfid = cc.getString(22);

                user2.put("in_farmer_name", cc.getString(1));
                user2.put("in_sur_name", cc.getString(2));
                user2.put("in_fhw_name", cc.getString(3));

                String[] dob = cc.getString(6).split("/");
                user2.put("in_farmer_dob",dob[2]+"/"+dob[1]+"/"+dob[0]);
                user2.put("in_farmer_addr1", cc.getString(9));
                user2.put("in_farmer_addr2", cc.getString(23));
                user2.put("in_farmer_country", "QCD_UN_IND");
                user2.put("in_farmer_state", "QCD_UNS_OR");

                user2.put("in_farmer_dist", cc.getString(21));
                user2.put("in_farmer_taluk", cc.getString(20));
                user2.put("in_farmer_panchayat", cc.getString(19));
                user2.put("in_farmer_village", cc.getString(18));
                user2.put("in_farmer_pincode", cc.getString(8));
                user2.put("in_marital_status", "");
                user2.put("in_gender_flag", gf);
                user2.put("in_reg_mobile_no", cc.getString(4));
                user2.put("in_status_code", "A");
                user2.put("in_mode_flag", "I");
                user2.put("Aadhar_no",cc.getString(7));
                user2.put("in_row_timestamp", "DUMFRMMOB/"+adsfid);
                user2.put("orgnId", "flexi");
                user2.put("locnId", "chennai");
                user2.put("userId", "PAWHS");
                user2.put("localeId", "en_US");
                user2.put("instance", "odisha");


                jsonParam.put("MFarmerHeaderDetails", user2);


                String fn = cc.getString(22);
                Log.e("NULL", "" + cc.getCount() + fn);

                JSONArray jsonArray2 = new JSONArray();
                Cursor cursorb = dbs.query("bank", new String[]{"ano", "typec", "bc", "branch", "ifsc", "dc", "dd"
                        }, "fid" + "=?",
                        new String[]{fn}, null, null, null, null);

                Log.e("COUNTb", "" + cursorb.getCount());
                if (cursorb.moveToFirst()) {

                    do {

                        JSONObject userb = new JSONObject();


                        userb.put("in_farmerbank_rowid", 0);
                        userb.put("in_bank_acc_no", cursorb.getString(0));
                        userb.put("in_bank_acc_type", cursorb.getString(1));

                        userb.put("in_bank_code", cursorb.getString(2));
                        userb.put("in_branch_code", cursorb.getString(3));
                        userb.put("in_ifsc_code", cursorb.getString(4));
                        userb.put("in_dflt_dr_acc", cursorb.getString(6));
                        userb.put("in_dflt_cr_acc", cursorb.getString(5));
                        userb.put("in_status_code", "A");
                        userb.put("in_mode_flag", "I");


                        jsonArray2.put(userb);

                    } while (cursorb.moveToNext());

                    user2.put("MfarmerBankDetails", jsonArray2);

                }
                Log.e("OUTPUT", "" + fn + jsonParam.toString());
            }
        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());

        }
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, ApiUtils.TEST_URL_API + "Mobile_FDR_Offlinesave/MFarmerProfileSave", jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        Log.e("CCCC", "" + response);

                        try {


                            if (response.getString("in_farmer_rowid").equalsIgnoreCase("0")) {


                                final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(HomePageActivity.this)
//set icon
                                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                        .setTitle("Error!")
//set message
                                        .setMessage("Transactions Unable To Complete Because!" + response.getString("error_msg") + "//farmer Name:" + cc.getString(1))
//set positive button
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        //set what would happen when positive button is clicked
                                                        // finish();
                                                        // ms();
                                                        try {
                                                            offc2++;
                                                            final SQLiteDatabase dbs = db.getWritableDatabase();


                                                            dbs.execSQL("DELETE FROM farmerh WHERE id = " + fmid);
                                                            // mydb.inserfarlist(response.getString("in_farmer_rowid").toString(), response.getString("in_farmer_code").toString(), n1, "0", n10, n11, n12, n13);

                                                            String selectQuery5 = "SELECT  * FROM farmerh where flag = 0";
                                                            Cursor cc = dbs.rawQuery(selectQuery5, null);

                                                            if (cc.getCount() != 0) {

                                                                offlinesave();


                                                            }
                                                        } catch (Exception e) {

                                                        }

                                                    }
                                                }
//set negative button

                                        )
                                        .show();
                            } else {




                                offc2++;
                                final SQLiteDatabase dbs = db.getWritableDatabase();
                                dbs.execSQL("UPDATE farmerh SET flag = 1 WHERE id = " + farid);

                                String selectQuery5 = "SELECT  * FROM farmerh where flag = 0";
                                Cursor cc = dbs.rawQuery(selectQuery5, null);

                                if (cc.getCount() != 0) {

                                    offlinesave();


                                }


//                        try {
//                            JSONObject obj = response.getJSONObject("context");
//                            JSONObject obj2 = obj.getJSONObject("Header");
//
//                            String inrid = obj2.getString("IOU_invoice_rowid");
//                            Log.e("CCCC","Hi"+inrid);
//


                                if (offc == offc2) {
                                    progressLayout.setVisibility(View.GONE);


                                    String selectQuery = "SELECT  * FROM saleentry where v17 = 0";



                                    Cursor cursor = dbs.rawQuery(selectQuery, null);

                                    if(cursor.getCount()>0)
                                    {progressLayout.setVisibility(View.VISIBLE);
//                                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                        offlinesavesaleentry();
                                    }
                                    else
                                    {
                                        String selectQuery2 = "SELECT  * FROM purchaseentry where v22 = 0";



                                        Cursor cursor2 = dbs.rawQuery(selectQuery2, null);

                                        if(cursor2.getCount()>0)
                                        {
                                            progressLayout.setVisibility(View.VISIBLE);
//                                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                                                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                            offlinesavepurchaseentry();
                                        }
                                        else
                                        {
                                            showErrorDialog("Transaction Completed");
                                        }



                                    }

                                }

//                        catch (Exception e)
//                        {
//
//                        }


                                // Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_SHORT).show();
                                //finish();


                            }
                        } catch (Exception e) {

                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);

                        // Toast.makeText(getApplicationContext(),"Unable to Insert",Toast.LENGTH_SHORT).show();

                        //on error storing the name to sqlite with status unsynced
                        // Toast.makeText(Demo.this, "Farmer "+n+" SuccessFull Added to Sync List" , Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                80000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public  void offlinesavesaleentry()
    {
        final SQLiteDatabase dbs = db.getWritableDatabase();
        String selectQuery = "SELECT  * FROM saleentry where v17 = 0";

        final Cursor cursor = dbs.rawQuery(selectQuery, null);
        Log.e("COUNT",""+cursor.getCount());



        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            try {
                jsonParam = new JSONObject();
                JSONObject userd = new JSONObject();
                jsonParam.put("document",userd);
                JSONObject user = new JSONObject();
                user.put("orgnId", orgnCode);
                user.put("locnId", locnId);
                user.put("userId", userId);
                user.put("localeId", localeId);
                userd.put("context",user);
                JSONObject user2 = new JSONObject();

                user2.put("in_Sale_rowid",0);
                user2.put("in_SaleNo","");
                //String[] by = spinnerb.getSelectedItem().toString().split("---");
                user2.put("In_Buyer_code",cursor.getString(1));
                user2.put("In_Buyer_name",cursor.getString(2));

                user2.put("in_Item_Code",cursor.getString(4));
                user2.put("in_Item_Name",cursor.getString(3));

                user2.put("in_Sale_Qty",Double.parseDouble(String.valueOf(cursor.getString(7))));
                user2.put("in_Sale_Price",0);
                user2.put("in_Sale_Value",0);
                user2.put("in_advance_amt",0);
                Cursor cursor2t = dbs.query("sno", new String[]{"id","v1","v2","v3"
                        }, "v3" + "=? COLLATE NOCASE",
                        new String[]{cursor.getString(16)}, null, null, null, null);
                user2.put("in_no_of_bags",cursor2t.getCount());
                user2.put("in_Status","Active");
                user2.put("in_mode_flag",cursor.getString(20));
                user2.put("in_sale_remarks",cursor.getString(10));

                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(cursor.getString(15)));

                    byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    //Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());


                    encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                    Log.e("ECS",""+encodedImage);


                } catch (IOException e) {
                    e.printStackTrace();
                }
                user2.put("in_sale_attach",encodedImage);
                user2.put("in_vehicle_type",cursor.getString(11));
                user2.put("in_vehicle_no",cursor.getString(12));

                user2.put("in_qcperson_name",cursor.getString(8));
                user2.put("in_LRP_Name",cursor.getString(13));
                user2.put("In_LRP_Mobile_no",cursor.getString(14));
                user2.put("In_Payment_type","");
                user2.put("In_Bank_acc_no","");
                user2.put("In_cheque_no","");
                user2.put("In_whs_code","");
                user2.put("In_whs_name","");
                String[] dt = cursor.getString(9).split("-");
                user2.put("In_Saledate",dt[2]+"-"+dt[1]+"-"+dt[0]);

                user.put("Header",user2);



                JSONArray snodetails = new JSONArray();

                Cursor cursor2 = dbs.query("sno", new String[]{"id","v1","v2","v3"
                        }, "v3" + "=? COLLATE NOCASE",
                        new String[]{cursor.getString(16)}, null, null, null, null);
                if (cursor2.moveToFirst()) {
                    do {
                        JSONObject snolist = new JSONObject();

                        snolist.put("in_slno_row_id",0);
                        snolist.put("in_slno",cursor2.getString(1));
                        snolist.put("in_temp1","");
                        snolist.put("in_temp2","");
                        snolist.put("in_mode_flag","I");
                        JSONArray qpar = new JSONArray();
                        String selectQuery3 = "SELECT  * FROM qpar WHERE v1 ="+cursor2.getString(0);
                        Cursor cursorq = dbs.rawQuery(selectQuery3, null);
                        if (cursorq.moveToFirst()) {
                            do {
                                JSONObject qparlist = new JSONObject();

                                qparlist.put("In_qly_dtl_rowid",0);
                                qparlist.put("In_slno",cursor2.getString(1));
                                String[] q = cursorq.getString(2).split("-");
                                qparlist.put("In_qly_code",q[0]);
                                if(cursorq.getString(3).equalsIgnoreCase("Yes"))
                                {
                                    qparlist.put("In_actual_value",1);
                                }
                                else if(cursorq.getString(3).equalsIgnoreCase("No"))
                                {
                                    qparlist.put("In_actual_value",2);
                                }
                                else
                                {
                                    qparlist.put("In_actual_value",Double.parseDouble(cursorq.getString(3)));
                                }

                                qparlist.put("In_wr_qly_value",0);
                                qparlist.put("in_estimate_qly_value",0);
                                qparlist.put("in_mode_flag","I");


                                qpar.put(qparlist);

                            }

                            while (cursorq.moveToNext());
                        }
                        snolist.put("QlyDetail",qpar);
                        snodetails.put(snolist);
                    }

                    while (cursor2.moveToNext());
                }
                user.put("SlnoDetail",snodetails);








                Log.e("OUTPUT",""+jsonParam.toString());
            }
            catch(Exception e)
            {
                Log.e("OUTPUT",""+e.getMessage());
            }

            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,ApiUtils.TEST_URL_API+"New_PAWHS_SaleEntry/New_Pawhs_SaleEntry_Save",jsonParam,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("CCCC", "" + response);

                            try {
                                JSONObject obj = response.getJSONObject("context");
                                JSONObject obj2 = obj.getJSONObject("header");

                                if(obj2.getString("in_SaleNo").equalsIgnoreCase("null"))
                                {
                                    showErrorDialog("Unable To Save");
                                }
                                else
                                {
                                    dbs.execSQL("UPDATE saleentry SET v17 = 1 WHERE id = "+cursor.getString(0));
                                    //dbs.execSQL("delete from saleentry where id = "+cursor.getString(0));
                                    String selectQuery = "SELECT  * FROM saleentry where v17 = 0";

                                    db.updatesaleentry(Integer.valueOf(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(15),cursor.getString(16),"1",obj2.getString("in_SaleNo"),obj2.getString("in_Sale_rowid"),"U",cursor.getString(21));

                                    Cursor cursor = dbs.rawQuery(selectQuery, null);

                                    if(cursor.getCount()>0)
                                    {
                                        offlinesavesaleentry();
                                    }
                                    else
                                    {
                                        progressLayout.setVisibility(View.GONE);

                                        String selectQuery2 = "SELECT  * FROM purchaseentry where v22 = 0";



                                        Cursor cursor2 = dbs.rawQuery(selectQuery2, null);

                                        if(cursor2.getCount()>0)
                                        {
                                            progressLayout.setVisibility(View.VISIBLE);
//                                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                                                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                            offlinesavepurchaseentry();
                                        }
                                        else
                                        {
                                            showErrorDialog("Transaction Completed");
                                        }

                                    }

                                    // showErrorDialog("Saved Successfully !!! Sale No is"+obj2.getString("in_SaleNo"));

                                }
                            }
                            catch (Exception e)
                            {

                            }


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



    }

    public  void offlinesavepurchaseentry()
    {

        final SQLiteDatabase dbs = db.getWritableDatabase();
        String selectQuery = "SELECT  * FROM purchaseentry where v22 = 0";

        final Cursor cursor = dbs.rawQuery(selectQuery, null);
        Log.e("COUNT",""+cursor.getCount());



        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            try {
                jsonParam = new JSONObject();
                JSONObject userd = new JSONObject();
                jsonParam.put("document",userd);
                JSONObject user = new JSONObject();
                user.put("orgnId", orgnCode);
                user.put("locnId", locnId);
                user.put("userId", userId);
                user.put("localeId", localeId);
                userd.put("context",user);
                JSONObject user2 = new JSONObject();

                user2.put("in_Actual_rowid",Integer.parseInt(cursor.getString(24)));
                user2.put("in_LotNo",cursor.getString(23));
                //String[] by = spinnerb.getSelectedItem().toString().split("---");
                user2.put("in_Farmer_Code",cursor.getString(1));
                user2.put("in_Farmer_Name",cursor.getString(2));

                user2.put("in_Member_Type",cursor.getString(3));
                user2.put("in_Item_Code",cursor.getString(4));

                user2.put("in_Item_Name",cursor.getString(5));
                user2.put("in_Actual_Qty",Double.parseDouble(cursor.getString(6)));
                user2.put("in_Actual_Price",Double.parseDouble(cursor.getString(7)));
                user2.put("in_Actual_Value",Double.parseDouble(cursor.getString(8)));
                user2.put("in_advance_amt",0);
                user2.put("in_no_of_bags",Integer.parseInt(cursor.getString(9)));
                user2.put("in_Status","Actual");
                user2.put("in_mode_flag",cursor.getString(25));
                user2.put("in_actual_remarks",cursor.getString(10));
                user2.put("in_approved_remarks","");
                user2.put("in_pickup_remarks","");
                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(cursor.getString(11)));

                    byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    //Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());


                    encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                    Log.e("ECS",""+encodedImage);


                } catch (IOException e) {
                    e.printStackTrace();
                }
                user2.put("in_actual_attach",encodedImage);
                user2.put("in_vehicle_type",cursor.getString(12));
                user2.put("in_vehicle_no",cursor.getString(13));
                user2.put("in_destination",cursor.getString(14));
                user2.put("in_qcperson_name",cursor.getString(15));
                user2.put("in_LRP_Name",cursor.getString(16));
                user2.put("In_LRP_Mobile_no",cursor.getString(17));
                user2.put("In_Payment_type","");
                user2.put("In_Bank_acc_no","");
                user2.put("In_cheque_no","");
                user2.put("In_Buyer_code",cursor.getString(19));
                user2.put("In_Buyer_name",cursor.getString(20));
                String[] dt = cursor.getString(18).split("-");
                user2.put("In_Acc_Date",dt[2]+"-"+dt[1]+"-"+dt[0]);


                user.put("Header",user2);



                JSONArray snodetails = new JSONArray();

                Cursor cursor2 = dbs.query("snope", new String[]{"id","v1","v2","v3"
                        }, "v3" + "=? COLLATE NOCASE",
                        new String[]{cursor.getString(21)}, null, null, null, null);
                if (cursor2.moveToFirst()) {
                    do {
                        JSONObject snolist = new JSONObject();

                        snolist.put("in_slno_row_id",0);
                        snolist.put("in_slno",cursor2.getString(1));
                        snolist.put("in_temp1","");
                        snolist.put("in_temp2","");
                        snolist.put("in_mode_flag","I");

                        //snolist.put("QlyDetail",snolist);
                        snodetails.put(snolist);
                    }

                    while (cursor2.moveToNext());
                }
                user.put("SlnoDetail",snodetails);

                JSONArray qpar = new JSONArray();
                Cursor cursorq = dbs.query("qparpe", new String[]{"id","v1","v2","v3"
                        }, "v1" + "=? COLLATE NOCASE",
                        new String[]{cursor.getString(21)}, null, null, null, null);
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
                            qparlist.put("in_actual_value",Double.parseDouble(cursorq.getString(3)));
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

            Log.e("URL",""+ApiUtils.TEST_URL_API+"NewPawhsActulProcurment/new_pawhs_ActualProc_save");

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

                                    //   dbs.execSQL("DELETE FROM purchaseentry WHERE id = "+cursor.getString(0));

                                    db.updatepurchaseentry(Integer.valueOf(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(15),cursor.getString(16),cursor.getString(17),cursor.getString(18),cursor.getString(19),cursor.getString(20),cursor.getString(21),"1",obj2.getString("in_LotNo"),obj2.getString("in_Actual_rowid"),"U");
                                    dbs.execSQL("UPDATE purchaseentry SET v22 = 1 WHERE id = "+cursor.getString(0));


                                    String selectQuery = "SELECT  * FROM purchaseentry where v22 = 0";

                                    final Cursor cursor = dbs.rawQuery(selectQuery, null);
                                    Log.e("COUNT",""+cursor.getCount());



                                    // looping through all rows and adding to list

                                    if(cursor.getCount()>0)
                                    {
                                        offlinesavepurchaseentry();
                                    }
                                    else
                                    {
                                        progressLayout.setVisibility(View.GONE);
                                        showErrorDialog("Transaction Completed");
                                    }




                                }
                            }
                            catch (Exception e)
                            {

                            }
                            progressLayout.setVisibility(View.GONE);

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



    }
    public void save() {
        pdialog = new ProgressDialog(HomePageActivity.this);
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Loading.....");
        pdialog.show();

        final SQLiteDatabase dbs = db.getWritableDatabase();

        dbs.execSQL("delete from vtype");
        dbs.execSQL("delete from vstatus");
        dbs.execSQL("delete from dstn");
        dbs.execSQL("delete from qparest");
        dbs.execSQL("delete from bankm");
        dbs.execSQL("delete from masterl");
        dbs.execSQL("delete from fpoad");

        try {


            DecimalFormat amountFormate = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", "Flexi");
            userd.put("locnId", "Chennai");
            userd.put("userId", "admin");
            userd.put("localeId", "en_US");
            userd.put("screen_Id", "FARMERANDPAWHS");
            userd.put("instance", ApiUtils.instance);

            Log.e("OUTPUT", "" + userd.toString());

        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

        //169.38.77.190:101/api/Mobile_FDR/Farmermaster
        //15.206.21.248:27/Farmermaster
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, ApiUtils.TEST_URL_API+"Mobile_FDR/Farmermaster", userd,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC2", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONArray cast = obj.getJSONArray("detail");
                            for (int i = 0; i < cast.length(); i++) {


                                JSONObject actor = cast.getJSONObject(i);


                                String n1 = actor.getString("out_master_rowid");
                                String n2 = actor.getString("out_parent_code");
                                String n3 = actor.getString("out_master_code");
                                String n4 = actor.getString("out_master_description");
                                String n5 = actor.getString("out_depend_code");
                                String n6 = actor.getString("out_depend_desc");
                                String n7 = actor.getString("out_locallang_flag");
                                String n8 = actor.getString("out_status_code");
                                String n9 = actor.getString("out_status_desc");

                                //   Log.e("Table2", "" + n3);

                                db.insertmasterl(n1, n2, n3, n4, n5, n6, n7, n8, n9);


                            }

                            JSONArray cast3 = obj.getJSONArray("varityDt");
                            for (int i = 0; i < cast3.length(); i++) {
                                Log.e("TableVS", "" + "Hi");

                                JSONObject actor = cast3.getJSONObject(i);


                                String n1 = actor.getString("out_master_rowid");
                                String n2 = actor.getString("out_master_code");
                                String n3 = actor.getString("out_master_desc");



                                Log.e("TableVS", "" + n3);

                                db.insertvstatus(n1, n2, n3);


                            }

                            JSONArray cast2 = obj.getJSONArray("qualityDt");
                            for (int i = 0; i < cast2.length(); i++) {


                                JSONObject actor = cast2.getJSONObject(i);


                                String n1 = actor.getString("out_qlt_rowid");
                                String n2 = actor.getString("out_qlt_code");
                                String n3 = actor.getString("out_qlt_name");
                                String n4 = actor.getString("out_QualityRange");


                                Log.e("TableQ", "" + n3);

                                db.insertqparest(n1, n2, n3, n4);


                            }



                            JSONArray cast4 = obj.getJSONArray("vehicleDt");
                            for (int i = 0; i < cast4.length(); i++) {


                                JSONObject actor = cast4.getJSONObject(i);


                                String n1 = actor.getString("out_master_rowid");
                                String n2 = actor.getString("out_master_code");
                                String n3 = actor.getString("out_master_desc");



                                Log.e("TableVT", "" + n3);

                                db.insertvtype(n1, n2, n3);


                            }

                            JSONArray cast5 = obj.getJSONArray("destinationDt");
                            for (int i = 0; i < cast5.length(); i++) {


                                JSONObject actor = cast5.getJSONObject(i);


                                String n1 = actor.getString("out_master_rowid");
                                String n2 = actor.getString("out_master_code");
                                String n3 = actor.getString("out_master_desc");



                                Log.e("TableDS", "" + n3);

                                db.insertdstn(n1, n2, n3);


                            }
                            pdialog.dismiss();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);

                        //on error storing the name to sqlite with status unsynced
                        // Toast.makeText(Demo.this, "Farmer "+n+" SuccessFull Added to Sync List" , Toast.LENGTH_SHORT).show();

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
    public void save2() {
        final SQLiteDataBaseHandler db = new SQLiteDataBaseHandler(HomePageActivity.this);
        SQLiteDatabase dbs = db.getWritableDatabase();
        dbs.execSQL("delete from bankm");


        try {


            DecimalFormat amountFormate = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            //userd = new JSONObject();

            userd = new JSONObject();
            userd.put("orgnId", "Flexi");
            userd.put("locnId", "Chennai");
            userd.put("userId", "admin");
            userd.put("localeId", "en_US");
            userd.put("instance", ApiUtils.instance);
            userd.put("FilterBy_Option", "ALL");
            userd.put("FilterBy_Code", ".");
            userd.put("FilterBy_FromValue", ".");
            userd.put("FilterBy_ToValue", ".");

            Log.e("OUTPUT", "" + userd.toString());

        } catch (Exception e) {
            Log.e("OUTPUT", "" + e.getMessage());
        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();

        //http://169.38.77.190:101/api/Mobile_FDR/Farmerbank
        //15.206.21.248:27/Farmerbank
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, ApiUtils.TEST_URL_API+"Mobile_FDR/Farmerbank", userd,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            castb = obj.getJSONArray("bankDtl");
//                            for (int i = 0; i < cast.length(); i++) {
//
//
//                                JSONObject actor = cast.getJSONObject(i);
//
//
//                                String n1 = actor.getString("bank_rowid");
//                                String n2 = actor.getString("bank_code");
//                                String n3 = actor.getString("bank_name");
//                                String n4 = actor.getString("branch_name");
//                                String n5 = actor.getString("ifsc_code");
//                                String n6 = actor.getString("status_desc");
//                                Log.e("Table", "" + n1);
//
//                                db.insertbankm(n1, n2, n3, n4, n5, n6);
//
//
//                            }

                            new SomeTaskbank().execute();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);

                        //on error storing the name to sqlite with status unsynced
                        // Toast.makeText(Demo.this, "Farmer "+n+" SuccessFull Added to Sync List" , Toast.LENGTH_SHORT).show();

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

    class SomeTaskbank extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // super.onPreExecute();
            progressLayout.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {


                for (int i = 0; i < castb.length(); i++) {


                    JSONObject actor = castb.getJSONObject(i);


                    String n1 = actor.getString("bank_rowid");
                    String n2 = actor.getString("bank_code");
                    String n3 = actor.getString("bank_name");
                    String n4 = actor.getString("branch_name");
                    String n5 = actor.getString("ifsc_code");
                    String n6 = actor.getString("status_desc");
                    Log.e("Table", "" + n1);

                   // db.insertbankm(n1, n2, n3, n4, n5, n6);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //callLotnoListDetails();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressLayout.setVisibility(View.GONE);
            showCompleteDialog();

            // callLotnoListDetailsOfApproveWare("WareHouse");

        }
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

}