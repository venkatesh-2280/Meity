package cdfi.fintantra.meity.Pawhs.reactapppurchase;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.List;


import cdfi.fintantra.meity.Pawhs.PAWHSApplication;
import cdfi.fintantra.meity.Pawhs.SQLiteDataBaseHandler;
import cdfi.fintantra.meity.Pawhs.api.ApiService;
import cdfi.fintantra.meity.Pawhs.model.OtherDetailDao;
import cdfi.fintantra.meity.Pawhs.model.PostSiNoDetailDao;
import cdfi.fintantra.meity.Pawhs.model.QtyDetailDao;
import cdfi.fintantra.meity.Pawhs.model.SiNoDetailDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.LotnoDao;
import cdfi.fintantra.meity.Pawhs.model.singleactualprocmodel.SingleActualHeaderDao;

public class ApproveActualPurchaseActivity extends AppCompatActivity  {

    private PAWHSApplication pawhsApplication;
    private ApiService mAPIService;
    private String userName;
    private Context mContext;
    private TextView textViewTitle, lrp, lrpno;
    int apid;

    private String orgnId, locnId, userId, localeId, orgnCode;
    private SQLiteDataBaseHandler db;

    private RelativeLayout progressLayout;

    private List<LotnoDao> lotnoDaoList;

    private TextView textViewLotNo, textViewFarmerCode, textViewFarmerName, textViewMember, textViewItemName, textViewQty, textViewValue, textViewPickUpdate;

    private TextView textViewQualityPara, textViewSINo, textViewNoofBag, textViewOtherCost, textViewTotalCost, txt_uom, txt_var;

    private EditText edt_remarks;
    private TextView textViewPrice, textViewAdvance, txt_vars, vno, vtype, dsn, qcp;

    private Button buttonApprove, buttonReject;

    private String lotNumber, aggCode, pickupdate;
    private int rowId;
    private SingleActualHeaderDao singleActualHeaderDao;

    private List<QtyDetailDao> qtyDetailDaoList;
    private List<OtherDetailDao> otherDetailDaoList;
    private List<SiNoDetailDao> siNoDetailDaoList;

    private List<PostSiNoDetailDao> postSiNoDetailDaoList;
//    private List<PostQtyDetailDao> postQtyDetailDaoList;
//    private List<PostOtherDetailDao> postOtherDetailDaoList;

    private boolean isNetwork;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.approve_actual_purchase);
//        mContext = this;
//
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            Log.e("Test","LN"+bundle.getString("IOU_LOT_NO"));
//            rowId = bundle.getInt("IOU_ROW_ID");
//            lotNumber = bundle.getString("IOU_LOT_NO");
//            aggCode = bundle.getString("IOU_AGG_CODE");
//            pickupdate = bundle.getString("PICK_UP_DATE");
//           // apid = Integer.parseInt(bundle.getString("IDD"));
//
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
//
//    }
//
//    private void initView() {
//        isNetwork = Utility.checkConnectivity(getApplicationContext());
//        mAPIService = ApiUtils.getAPIService();
//        textViewTitle = (TextView) findViewById(R.id.txt_title);
//        textViewTitle.setText("Welcome " + userName + "\n" + "PA Approve Actual Purchase");
//
//        progressLayout = (RelativeLayout) findViewById(R.id.progressLayout);
//
//        textViewLotNo=(TextView)findViewById(R.id.txt_lot_no);
//        textViewFarmerCode=(TextView)findViewById(R.id.txt_act_formercode);
//        textViewFarmerName=(TextView)findViewById(R.id.txt_act_farmerName);
//        textViewMember=(TextView)findViewById(R.id.txt_act_MemberType);
//        textViewItemName=(TextView)findViewById(R.id.txt_act_Item);
//        textViewQty=(TextView)findViewById(R.id.txt_act_qty);
//        textViewValue=(TextView)findViewById(R.id.edt_act_value);
//        textViewPickUpdate=(TextView)findViewById(R.id.txtview_date);
//
//        textViewQualityPara=(TextView)findViewById(R.id.txt_act_quality);
//        textViewSINo=(TextView)findViewById(R.id.txt_act_sl);
//        textViewNoofBag=(TextView)findViewById(R.id.txt_act_noofbag);
//        textViewOtherCost=(TextView)findViewById(R.id.txt_act_othercost);
//        textViewTotalCost=(TextView)findViewById(R.id.txt_act_totalcost);
//
//        textViewPrice=(TextView)findViewById(R.id.txt_act_price);
//        textViewAdvance=(TextView)findViewById(R.id.txt_advance);
//        txt_vars=(TextView)findViewById(R.id.txt_vars);
//        vno=(TextView)findViewById(R.id.vno);
//        vtype=(TextView)findViewById(R.id.vtype);
//        dsn=(TextView)findViewById(R.id.dsn);
//        qcp=(TextView)findViewById(R.id.qcp);
//        lrp=(TextView)findViewById(R.id.lrp);
//        lrpno=(TextView)findViewById(R.id.lrpno);
//
//        edt_remarks=(EditText) findViewById(R.id.edt_remarks);
//        txt_uom = (TextView) findViewById(R.id.txt_uom);
//        txt_var = (TextView) findViewById(R.id.txt_var);
//        buttonApprove = (Button) findViewById(R.id.but_approve);
//        buttonReject = (Button) findViewById(R.id.but_reject);
//
//        singleActualHeaderDao=new SingleActualHeaderDao();
//        qtyDetailDaoList=new ArrayList<>();
//        otherDetailDaoList=new ArrayList<>();
//        siNoDetailDaoList=new ArrayList<>();
//
//        postSiNoDetailDaoList=new ArrayList<>();
//        postOtherDetailDaoList=new ArrayList<>();
//        postQtyDetailDaoList=new ArrayList<>();
//
//        if(isNetwork==true){
//            callSingleActualProcurementDetails();
//        }else {
//
//            List<SingleActualHeaderDao> singleActualHeaderDaoList=db.getOfflineAppSingleHeaderListDetailsRelatedLotNO(lotNumber);
//            for (int i=0;i<singleActualHeaderDaoList.size();i++){
//                singleActualHeaderDao=singleActualHeaderDaoList.get(i);
//            }
//            qtyDetailDaoList=db.getOfflineApproveQtyDetailsList(lotNumber);
//            otherDetailDaoList=db.getOfflineApproveOtherDetails(lotNumber);
//            siNoDetailDaoList=db.getOfflineApproveSiNoDetailsList(lotNumber);
//
//            setActualValues();
//
//        }
//
//
//
//
//        buttonApprove.setOnClickListener(this);
//        buttonReject.setOnClickListener(this);
//        textViewOtherCost.setOnClickListener(this);
//        textViewSINo.setOnClickListener(this);
//        textViewQualityPara.setOnClickListener(this);
//
//    }
//
//    private void callSingleActualProcurementDetails() {
//        progressLayout.setVisibility(View.VISIBLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//
//
//
//        SingleActProcContextDao singleActProcContextDao = new SingleActProcContextDao();
//        singleActProcContextDao.setOrgnId(orgnCode);
//      //  singleActProcContextDao.setLocnId(locnId);
//        singleActProcContextDao.setLocnId(ApiUtils.instance);
//        singleActProcContextDao.setUserId(userId);
//        singleActProcContextDao.setLocaleId(localeId);
//        singleActProcContextDao.setIOU_act_rowid(rowId);
//        singleActProcContextDao.setIOU_lotno(lotNumber);
//        singleActProcContextDao.setIOU_agg_code(aggCode);
//
//        mAPIService.postSingleActualProcurementDetails(singleActProcContextDao)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<SingleActualProcurementDao>() {
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
//                    public void onNext(SingleActualProcurementDao singleActualProcurementDao) {
//                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//                        progressLayout.setVisibility(View.GONE);
//
//                        singleActualHeaderDao=singleActualProcurementDao.getSingleActProcContextDao().getSingleActualHeaderDao();
//                        qtyDetailDaoList=singleActualProcurementDao.getSingleActProcContextDao().getQtyDetailDaoList();
//                        otherDetailDaoList=singleActualProcurementDao.getSingleActProcContextDao().getOtherDetailDaoList();
//                        siNoDetailDaoList=singleActualProcurementDao.getSingleActProcContextDao().getSiNoDetailDaoList();
//                        setActualValues();
//                    }
//                });
//
//
//    }
//
//    private void setActualValues() {
//
//        apid = singleActualHeaderDao.getIoU_act_rowid();
//        textViewLotNo.setText(singleActualHeaderDao.getIoU_lotno());
//        textViewFarmerName.setText(singleActualHeaderDao.getIn_farmer_name());
//        textViewFarmerCode.setText(singleActualHeaderDao.getIn_farmer_code());
//        textViewMember.setText(singleActualHeaderDao.getIn_member_type());
//        textViewItemName.setText(singleActualHeaderDao.getIn_item_name());
//        final SQLiteDatabase dbs = db.getWritableDatabase();
//        Cursor cursoruom = dbs.query("ProductMasterAllProduct", new String[]{"out_uomtype_code","out_crop_variety"
//                }, "PmapOutProductCode" + "=?",
//                new String[]{singleActualHeaderDao.getIn_item_code()}, null, null, null, null);
//
//        if(cursoruom.moveToFirst())
//        {
//            txt_uom.setText(cursoruom.getString(0));
//            txt_var.setText(cursoruom.getString(1));
//        }
//        textViewQty.setText(""+singleActualHeaderDao.getIn_actual_qty());
//        textViewPrice.setText(""+singleActualHeaderDao.getIn_actual_price());
//        textViewValue.setText(""+new DecimalFormat("##.##").format(singleActualHeaderDao.getIn_actual_value()));
//        textViewTotalCost.setText(""+new DecimalFormat("##.##").format(singleActualHeaderDao.getIn_actual_value()));
//        textViewNoofBag.setText(""+singleActualHeaderDao.getIn_no_of_bags());
//        qcp.setText(""+singleActualHeaderDao.getIn_qcperson_name());
//       // txt_vars.setText(""+singleActualHeaderDao.va());
//        vno.setText(""+singleActualHeaderDao.getIn_vehicle_no());
//        vtype.setText(""+singleActualHeaderDao.getIn_vehicle_type());
//        dsn.setText(""+singleActualHeaderDao.getIn_destination());
//        lrp.setText(""+singleActualHeaderDao.getIn_LRP_Name());
//        lrpno.setText(""+singleActualHeaderDao.getIn_LRP_Mobile_no());
//        textViewPickUpdate.setText(pickupdate);
//        if(singleActualHeaderDao.getIn_advance_amt()>0){
//            textViewAdvance.setText(""+singleActualHeaderDao.getIn_LRP_Name());
//        }else {
//            textViewAdvance.setText("");
//        }
//
//        edt_remarks.setText(singleActualHeaderDao.getIn_actual_remarks());
//
//        if(siNoDetailDaoList!=null && siNoDetailDaoList.size()>0){
//            List<String> siname=new ArrayList<>();
//
//            for(int i=0;i<siNoDetailDaoList.size();i++){
//                siname.add(siNoDetailDaoList.get(i).getIn_slno());
//                if(siNoDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("S")){
//                    //    bulkSInoDetailsDaoList.set(i,new BulkSInoDetailsDao(bulkSInoDetailsDaoList.get(i).getIn_slno_row_id(),bulkSInoDetailsDaoList.get(i).getIn_slno(),bulkSInoDetailsDaoList.get(i).getIn_temp1(),bulkSInoDetailsDaoList.get(i).getIn_temp2(),"U"));
//                    postSiNoDetailDaoList.add(new PostSiNoDetailDao(siNoDetailDaoList.get(i).getIn_slno_row_id(),siNoDetailDaoList.get(i).getIn_slno(),siNoDetailDaoList.get(i).getIn_temp1(),siNoDetailDaoList.get(i).getIn_temp2(),"U"));
//                }
//            }
//            String s= TextUtils.join(", ",siname);
//            String count= String.valueOf(siname.size());
//            textViewSINo.setText(s);
//        }
//        if(qtyDetailDaoList!=null && qtyDetailDaoList.size()>0){
//            List<String> qtyname=new ArrayList<>();
//
//            for(int i=0;i<qtyDetailDaoList.size();i++){
//                qtyname.add(qtyDetailDaoList.get(i).getIn_qty_name()+" \n");
//                if(qtyDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("S")){
//                    // bulkQtyDetailDaoList.set(i,new BulkQtyDetailDao(bulkQtyDetailDaoList.get(i).getIn_qty_row_id(),bulkQtyDetailDaoList.get(i).getIn_qty_code(),bulkQtyDetailDaoList.get(i).getIn_qty_name(),bulkQtyDetailDaoList.get(i).getIn_actual_value(),bulkQtyDetailDaoList.get(i).getIn_actual_value(),"U"));
//                    postQtyDetailDaoList.add(new PostQtyDetailDao(qtyDetailDaoList.get(i).getIn_qty_row_id(),qtyDetailDaoList.get(i).getIn_qty_code(),qtyDetailDaoList.get(i).getIn_qty_name(),qtyDetailDaoList.get(i).getIn_actual_value(),qtyDetailDaoList.get(i).getIn_actual_value(),"U"));
//                }
//            }
//            String s= TextUtils.join("",qtyname);
//            textViewQualityPara.setText(s);
//        }
//        if(otherDetailDaoList!=null && otherDetailDaoList.size()>0){
//            List<String> qtyname=new ArrayList<>();
//            List<Integer> valueList=new ArrayList<>();
//
//            for(int i=0;i<otherDetailDaoList.size();i++){
//                if(otherDetailDaoList.get(i).getIn_mode_flag().equalsIgnoreCase("S")){
//                    /*bulkOtherDetailDaoList.set(i,new BulkOtherDetailDao(bulkOtherDetailDaoList.get(i).getIn_otherdtl_row_id(),bulkOtherDetailDaoList.get(i).getIn_packaging_cost(),
//                            bulkOtherDetailDaoList.get(i).getIn_transporting_cost(),bulkOtherDetailDaoList.get(i).getIn_unpacking_cost(),
//                            bulkOtherDetailDaoList.get(i).getIn_local_packaging_cost(),bulkOtherDetailDaoList.get(i).getIn_local_transporting_cost(),
//                            bulkOtherDetailDaoList.get(i).getIn_temp_cost(),bulkOtherDetailDaoList.get(i).getIn_temp_cost1(),bulkOtherDetailDaoList.get(i).getIn_temp_cost2(),"U"));*/
//                    postOtherDetailDaoList.add(new PostOtherDetailDao(otherDetailDaoList.get(i).getIn_Other_row_id(),otherDetailDaoList.get(i).getIn_packaging_cost(),
//                            otherDetailDaoList.get(i).getIn_transporting_cost(),otherDetailDaoList.get(i).getIn_unpacking_cost(),
//                            otherDetailDaoList.get(i).getIn_local_packaging_cost(),otherDetailDaoList.get(i).getIn_local_transporting_cost(),
//                            otherDetailDaoList.get(i).getIn_temp_cost(),otherDetailDaoList.get(i).getIn_temp_cost1(),otherDetailDaoList.get(i).getIn_temp_cost2(),"U"));
//                }
//                if(otherDetailDaoList.get(i).getIn_packaging_cost()>0){
//                    qtyname.add("Packing Cost");
//                    valueList.add(otherDetailDaoList.get(i).getIn_packaging_cost());
//                }
//                if(otherDetailDaoList.get(i).getIn_transporting_cost()>0){
//                    qtyname.add("Transportation Cost");
//                    valueList.add(otherDetailDaoList.get(i).getIn_transporting_cost());
//                }
//                if(otherDetailDaoList.get(i).getIn_unpacking_cost()>0){
//                    qtyname.add("UnPacking Cost");
//                    valueList.add(otherDetailDaoList.get(i).getIn_unpacking_cost());
//                }
//                if(otherDetailDaoList.get(i).getIn_local_packaging_cost()>0){
//                    qtyname.add("Local Packing Cost");
//                    valueList.add(otherDetailDaoList.get(i).getIn_local_packaging_cost());
//                }
//                if(otherDetailDaoList.get(i).getIn_local_transporting_cost()>0){
//                    qtyname.add("Local Transportation Cost");
//                    valueList.add(otherDetailDaoList.get(i).getIn_local_transporting_cost());
//                }
//            }
//            String s= TextUtils.join(", ",qtyname);
//            int totalPrice = 0;
//            for(int i = 0 ; i < valueList.size(); i++) {
//                totalPrice += valueList.get(i);
//            }
//            textViewOtherCost.setText(""+totalPrice);
//            int total=grandTotal2(valueList);
//            String ptf = String.valueOf(singleActualHeaderDao.getIn_advance_amt());
//            double totalCost;
//            if(ptf.equalsIgnoreCase(""))
//            {
//                totalCost=singleActualHeaderDao.getIn_actual_value()-total;
//            }
//            else
//
//            {
//                totalCost=(singleActualHeaderDao.getIn_actual_value()-total)-singleActualHeaderDao.getIn_advance_amt();
//            }
//
//
//
//           // textViewTotalCost.setText(""+totalCost);
//         //   textViewTotalCost.setText(""+total);
//
//        }
//    }
//
//    private int grandTotal(List<Integer> items){
//
//        int totalPrice = 0;
//        for(int i = 0 ; i < items.size(); i++) {
//            totalPrice += items.get(i);
//        }
//
//        return totalPrice;
//    }
//
//    @Override
//    public void onClick(View view) {
//        int id=view.getId();
//        switch (id){
//            case R.id.but_approve:
//                callApproveAndRejectMethod("Approved");
//                break;
//            case R.id.but_reject:
//                callApproveAndRejectMethod("Reject");
//                break;
//            case R.id.txt_act_quality:
//                viewQualityDialog();
//
//                break;
//            case R.id.txt_act_sl:
//                viewSerialNoDialog();
//
//                break;
//            case R.id.txt_act_othercost:
//
//                viewOtherCostDialog();
//
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void viewSerialNoDialog() {
//
//        dialog = new Dialog(mContext);
//        dialog.setContentView(R.layout.view_si_dialog);
//        //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
//        dialog.setTitle("Title...");
//        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
//      /*  int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);*/
//        final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.recycle_viewserialno);
//        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//     //   dialog.getWindow().setLayout(width, height);
//        dialog.getWindow().setLayout(width, RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//        ViewSiListAdapter viewSiListAdapter=new ViewSiListAdapter(mContext,postSiNoDetailDaoList);
//        recyclerView.setAdapter(viewSiListAdapter);
//
//        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//
//    }
//
//    private void viewOtherCostDialog() {
//        dialog = new Dialog(mContext);
//        dialog.setContentView(R.layout.view_othercost_dialog);
//        //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
//        dialog.setTitle("Title...");
//       int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
//        /* int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);*/
//
//        final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.recycle_viewothercost);
//        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//    //    dialog.getWindow().setLayout(width, height);
//        dialog.getWindow().setLayout(width, RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//        List<TempOtherDetailDao> tempOtherDetailDaoList=new ArrayList<>();
//
//        if(postOtherDetailDaoList!=null && postOtherDetailDaoList.size()>0){
//
//            for(int i=0;i<postOtherDetailDaoList.size();i++){
//                if(postOtherDetailDaoList.get(i).getIn_packaging_cost()>0) {
//                    tempOtherDetailDaoList.add(new TempOtherDetailDao("Packing Cost", String.valueOf(postOtherDetailDaoList.get(i).getIn_packaging_cost())));
//                }
//
//                if(postOtherDetailDaoList.get(i).getIn_transporting_cost()>0) {
//                    tempOtherDetailDaoList.add(new TempOtherDetailDao("Transportation Cost", String.valueOf(postOtherDetailDaoList.get(i).getIn_transporting_cost())));
//                }
//                if(postOtherDetailDaoList.get(i).getIn_unpacking_cost()>0) {
//                    tempOtherDetailDaoList.add(new TempOtherDetailDao("UnPacking Cost", String.valueOf(postOtherDetailDaoList.get(i).getIn_unpacking_cost())));
//                }
//                if(postOtherDetailDaoList.get(i).getIn_local_packaging_cost()>0) {
//                    tempOtherDetailDaoList.add(new TempOtherDetailDao("Local Packing Cost", String.valueOf(postOtherDetailDaoList.get(i).getIn_local_packaging_cost())));
//                }
//                if(postOtherDetailDaoList.get(i).getIn_local_transporting_cost()>0) {
//                    tempOtherDetailDaoList.add(new TempOtherDetailDao("Local Transportation Cost", String.valueOf(postOtherDetailDaoList.get(i).getIn_local_transporting_cost())));
//                }
//
//            }
//
//
//        }
//
//        ViewOtherCostAdapter viewQualityListAdapter=new ViewOtherCostAdapter(mContext,tempOtherDetailDaoList);
//        recyclerView.setAdapter(viewQualityListAdapter);
//
//        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }
//
//    private void viewQualityDialog() {
//
//        dialog = new Dialog(mContext);
//        dialog.setContentView(R.layout.view_qualit_para_dialog);
//        //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
//        dialog.setTitle("Title...");
//        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
//        /*int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);*/
//        final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.recycle_viewqualitypara);
//        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        //dialog.getWindow().setLayout(width, height);
//        dialog.getWindow().setLayout(width, RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//        ViewQualityListAdapter viewQualityListAdapter=new ViewQualityListAdapter(mContext,postQtyDetailDaoList);
//        recyclerView.setAdapter(viewQualityListAdapter);
//
//        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }
//
//    private void callApproveAndRejectMethod(final String status) {
//        final String advanceAmount;
//        final String lotnumber=textViewLotNo.getText().toString();
//        final String farmercode=textViewFarmerCode.getText().toString();
//        final String farmername=textViewFarmerName.getText().toString();
//        final String membertype=textViewMember.getText().toString();
//        final String itemname=textViewItemName.getText().toString();
//        final String qty = textViewQty.getText().toString();
//        final String price = textViewPrice.getText().toString();
//        final String value = textViewValue.getText().toString();
//        final String remarks =edt_remarks.getText().toString();
//        if(textViewAdvance.getText().toString().isEmpty()){
//            advanceAmount="0";
//        }else {
//            advanceAmount=textViewAdvance.getText().toString();
//        }
//        String quality=textViewQualityPara.getText().toString();
//        String siNoBags=textViewSINo.getText().toString();
//        final String noofbags=textViewNoofBag.getText().toString();
//        String otherCost=textViewOtherCost.getText().toString();
//        String pickupdate=textViewPickUpdate.getText().toString();
//
//        if(lotnumber.isEmpty()){
//            showErrorDialog("Please Select LotNo ");
//        }else if (itemname.isEmpty()){
//            showErrorDialog("Please Select Item");
//        }else if (qty.equalsIgnoreCase("0")) {
//            showErrorDialog("Estimated Qty Mustbe Greater Than 0 ");
//        }else if(price.isEmpty()){
//            showErrorDialog("Please Enter Price");
//        }else if(quality.isEmpty()){
//            showErrorDialog("Please Select Quality Parameters  ");
//        }else if(siNoBags.isEmpty()){
//            showErrorDialog("Please Select SI.No.of Bags");
//        }
////        else if(otherCost.isEmpty()){
////            showErrorDialog("Please Select Other Cost ");
////        }
//
//        else if(!(Double.parseDouble(price) >0)){
//            //  Toast.makeText(mContext, "Price Should not be 0 ", Toast.LENGTH_SHORT).show();
//            showErrorDialog("Price Should not be 0 ");
//        } else if(status.equalsIgnoreCase("Reject")){
//            if(remarks.isEmpty()){
//              //  showLotNoDialog("Remarks?","Rejection Remarks Should be enter");
//                showErrorDialog("Rejection Remarks Should be enter ");
//            }else {
//
//                if(isNetwork==true){
//
//                    progressLayout.setVisibility(View.VISIBLE);
//                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//
//
//                    ActualProcurementHeaderDao actualProcurementHeaderDao=new ActualProcurementHeaderDao();
//                    actualProcurementHeaderDao.setIn_Actual_rowid(0);
//                    actualProcurementHeaderDao.setIn_LotNo(lotnumber);
//                    actualProcurementHeaderDao.setIn_Farmer_Code(farmercode);
//                    actualProcurementHeaderDao.setIn_Farmer_Name(farmername);
//                    actualProcurementHeaderDao.setIn_Member_Type(membertype);
//                    actualProcurementHeaderDao.setIn_Item_Code(singleActualHeaderDao.getIn_item_code());
//                    actualProcurementHeaderDao.setIn_Item_Name(itemname);
//                    actualProcurementHeaderDao.setIn_Actual_Qty(Double.parseDouble(qty));
//                    actualProcurementHeaderDao.setIn_Actual_Price(Double.parseDouble(price));
//                    actualProcurementHeaderDao.setIn_Actual_Value(Double.parseDouble(value));
//                    actualProcurementHeaderDao.setIn_advance_amt(Double.parseDouble(advanceAmount));
//                    actualProcurementHeaderDao.setIn_no_of_bags(Integer.parseInt(noofbags));
//                    actualProcurementHeaderDao.setIn_Status(status);
//                    actualProcurementHeaderDao.setIn_mode_flag("U");
//                    actualProcurementHeaderDao.setIn_actual_remarks(singleActualHeaderDao.getIn_actual_remarks());
//                    actualProcurementHeaderDao.setIn_approved_remarks(remarks);
//                    actualProcurementHeaderDao.setIn_pickup_remarks("");
//
//                    ActualProcurementContextDao actualProcurementContextDao=new ActualProcurementContextDao();
//                    actualProcurementContextDao.setOrgnId(orgnCode);
//                    //  actualProcurementContextDao.setLocnId(locnId);
//                    actualProcurementContextDao.setLocnId(ApiUtils.instance);
//                    actualProcurementContextDao.setUserId(userId);
//                    actualProcurementContextDao.setLocaleId(localeId);
//                    actualProcurementContextDao.setActualProcurementHeaderDao(actualProcurementHeaderDao);
//                    actualProcurementContextDao.setPostOtherDetailDaoList(postOtherDetailDaoList);
//                    actualProcurementContextDao.setPostQtyDetailDaoList(postQtyDetailDaoList);
//                    actualProcurementContextDao.setPostSiNoDetailDaoList(postSiNoDetailDaoList);
//
//                    ActualProcurementDocumentDao actualProcurementDocumentDao=new ActualProcurementDocumentDao();
//                    actualProcurementDocumentDao.setActualProcurementContextDao(actualProcurementContextDao);
//
//                    ActualProcurementSaveDao actualProcurementSaveDao=new ActualProcurementSaveDao();
//                    actualProcurementSaveDao.setActualProcurementDocumentDao(actualProcurementDocumentDao);
//
//                    mAPIService.postActualProcurementDetails(actualProcurementSaveDao)
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Subscriber<ActualProcurementSaveDao>() {
//                                @Override
//                                public void onCompleted() {
//
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//
//                                }
//
//                                @Override
//                                public void onNext(ActualProcurementSaveDao actualProcurementSaveDao) {
//                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//                                    progressLayout.setVisibility(View.GONE);
//
//                                    PawhsActualProcSaveDao pawhsActualProcSaveDao=new PawhsActualProcSaveDao(1,0,orgnCode,lotnumber,farmercode,farmername,membertype,
//                                            singleActualHeaderDao.getIn_item_code(),itemname, qty, price, value, "0", advanceAmount, "0", "0", "0", "0", noofbags, status, "0", remarks, "0", "0", "0","REJECT","U","Yes","","","","","","","","","","","");
//                                    db.addAllActualProcSave(pawhsActualProcSaveDao);
//
//                                    if(postQtyDetailDaoList.size()>0){
//                                        for(int i=0;i<postQtyDetailDaoList.size();i++){
//
//                                            PostQtyDetailDao postQtyDetailDao=new PostQtyDetailDao(1,postQtyDetailDaoList.get(i).getIn_qty_dtl_rowid(),postQtyDetailDaoList.get(i).getIn_qty_code(),
//                                                    postQtyDetailDaoList.get(i).getIn_qty_name(),postQtyDetailDaoList.get(i).getIn_actual_value(),postQtyDetailDaoList.get(i).getIn_wr_qty_value(),
//                                                    postQtyDetailDaoList.get(i).getIn_mode_flag(),postQtyDetailDaoList.get(i).getThresHoldValue(),postQtyDetailDaoList.get(i).getUom(),
//                                                    "REJECT",lotnumber);
//                                            db.addAllQtyDetailsList(postQtyDetailDao);
//
//                                        }
//                                    }
//
//                                    if(postSiNoDetailDaoList.size()>0){
//                                        for(int i=0;i<postSiNoDetailDaoList.size();i++){
//
//                                            PostSiNoDetailDao postSiNoDetailDao=new PostSiNoDetailDao(1,postSiNoDetailDaoList.get(i).getIn_slno_row_id(),postSiNoDetailDaoList.get(i).getIn_slno(),
//                                                    postSiNoDetailDaoList.get(i).getIn_temp1(),postSiNoDetailDaoList.get(i).getIn_temp2(),postQtyDetailDaoList.get(i).getIn_mode_flag(),
//                                                    "REJECT",lotnumber);
//                                            db.addAllSiDetailsList(postSiNoDetailDao);
//
//                                        }
//                                    }
//
//                                    if(postOtherDetailDaoList.size()>0){
//                                        for(int i=0;i<postOtherDetailDaoList.size();i++){
//
//                                            PostOtherDetailDao postOtherDetailDao=new PostOtherDetailDao(1,postOtherDetailDaoList.get(i).getIn_otherdtl_row_id(),postOtherDetailDaoList.get(i).getIn_packaging_cost(),
//                                                    postOtherDetailDaoList.get(i).getIn_transporting_cost(),postOtherDetailDaoList.get(i).getIn_unpacking_cost(),postOtherDetailDaoList.get(i).getIn_local_packaging_cost(),
//                                                    postOtherDetailDaoList.get(i).getIn_local_transporting_cost(),postOtherDetailDaoList.get(i).getIn_temp_cost(),postOtherDetailDaoList.get(i).getIn_temp_cost1(),postOtherDetailDaoList.get(i).getIn_temp_cost2(),
//                                                    postOtherDetailDaoList.get(i).getIn_mode_flag(),"REJECT",lotnumber);
//                                            db.addAllOtherDetailsList(postOtherDetailDao);
//
//                                        }
//                                    }
//
//                                    textViewLotNo.setText("");
//                                    txt_uom.setText("");
//                                    txt_var.setText("");
//                                    textViewFarmerCode.setText("");
//                                    textViewFarmerName.setText("");
//                                    textViewMember.setText("");
//                                    textViewItemName.setText("");
//                                    textViewQty.setText("");
//                                    textViewPrice.setText("");
//                                    textViewValue.setText("");
//                                    textViewQualityPara.setText("");
//                                    textViewNoofBag.setText("");
//                                    textViewSINo.setText("");
//                                    textViewOtherCost.setText("");
//                                    textViewTotalCost.setText("");
//                                    textViewPickUpdate.setText("");
//                                    edt_remarks.setText("");
//                                    textViewAdvance.setText("");
//
//                                    if(status.equalsIgnoreCase("Approved")){
//                                        showLotNoDialog("Success!","Approved");
//                                    }else {
//                                        showLotNoDialog("Reject!",lotnumber + " has Rejected");
//                                    }
//
//
//                                }
//                            });
//
//                }else {
//
//                    PawhsActualProcSaveDao pawhsActualProcSaveDao=new PawhsActualProcSaveDao(1,0,orgnCode,lotnumber,farmercode,farmername,membertype,
//                            singleActualHeaderDao.getIn_item_code(),itemname, qty, price, value, "0", advanceAmount, "0", "0", "0", "0", noofbags, status, "0", remarks, "0", "0", "0","REJECT","U","No","","","","","","","","","","","");
//                    db.addAllActualProcSave(pawhsActualProcSaveDao);
//                   SQLiteDatabase dbs = db.getWritableDatabase();
//                   Log.e("CHK",""+apid);
//                    dbs.execSQL("UPDATE ApproveList SET ApproveListStatus='Reject' WHERE ApproveListRowId="+apid);
//                    if(postQtyDetailDaoList.size()>0){
//                        for(int i=0;i<postQtyDetailDaoList.size();i++){
//
//                            PostQtyDetailDao postQtyDetailDao=new PostQtyDetailDao(1,postQtyDetailDaoList.get(i).getIn_qty_dtl_rowid(),postQtyDetailDaoList.get(i).getIn_qty_code(),
//                                    postQtyDetailDaoList.get(i).getIn_qty_name(),postQtyDetailDaoList.get(i).getIn_actual_value(),postQtyDetailDaoList.get(i).getIn_wr_qty_value(),
//                                    postQtyDetailDaoList.get(i).getIn_mode_flag(),postQtyDetailDaoList.get(i).getThresHoldValue(),postQtyDetailDaoList.get(i).getUom(),
//                                    "REJECT",lotnumber);
//                            db.addAllQtyDetailsList(postQtyDetailDao);
//
//                        }
//                    }
//
//                    if(postSiNoDetailDaoList.size()>0){
//                        for(int i=0;i<postSiNoDetailDaoList.size();i++){
//
//                            PostSiNoDetailDao postSiNoDetailDao=new PostSiNoDetailDao(1,postSiNoDetailDaoList.get(i).getIn_slno_row_id(),postSiNoDetailDaoList.get(i).getIn_slno(),
//                                    postSiNoDetailDaoList.get(i).getIn_temp1(),postSiNoDetailDaoList.get(i).getIn_temp2(),postQtyDetailDaoList.get(i).getIn_mode_flag(),
//                                    "REJECT",lotnumber);
//                            db.addAllSiDetailsList(postSiNoDetailDao);
//
//                        }
//                    }
//
//                    if(postOtherDetailDaoList.size()>0){
//                        for(int i=0;i<postOtherDetailDaoList.size();i++){
//
//                            PostOtherDetailDao postOtherDetailDao=new PostOtherDetailDao(1,postOtherDetailDaoList.get(i).getIn_otherdtl_row_id(),postOtherDetailDaoList.get(i).getIn_packaging_cost(),
//                                    postOtherDetailDaoList.get(i).getIn_transporting_cost(),postOtherDetailDaoList.get(i).getIn_unpacking_cost(),postOtherDetailDaoList.get(i).getIn_local_packaging_cost(),
//                                    postOtherDetailDaoList.get(i).getIn_local_transporting_cost(),postOtherDetailDaoList.get(i).getIn_temp_cost(),postOtherDetailDaoList.get(i).getIn_temp_cost1(),postOtherDetailDaoList.get(i).getIn_temp_cost2(),
//                                    postOtherDetailDaoList.get(i).getIn_mode_flag(),"REJECT",lotnumber);
//                            db.addAllOtherDetailsList(postOtherDetailDao);
//
//                        }
//                    }
//
//                    textViewLotNo.setText("");
//                    txt_uom.setText("");
//                    txt_var.setText("");
//                    textViewFarmerCode.setText("");
//                    textViewFarmerName.setText("");
//                    textViewMember.setText("");
//                    textViewItemName.setText("");
//                    textViewQty.setText("");
//                    textViewPrice.setText("");
//                    textViewValue.setText("");
//                    textViewQualityPara.setText("");
//                    textViewNoofBag.setText("");
//                    textViewSINo.setText("");
//                    textViewOtherCost.setText("");
//                    textViewTotalCost.setText("");
//                    textViewPickUpdate.setText("");
//                    edt_remarks.setText("");
//                    textViewAdvance.setText("");
//
//                    showStatusDialog("Reject!","Rejected Actual Purchase Stored Locally");
//
//                }
//
//
//
//            }
//        }else {
//            if(isNetwork==true){
//                progressLayout.setVisibility(View.VISIBLE);
//                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//
//
//                ActualProcurementHeaderDao actualProcurementHeaderDao=new ActualProcurementHeaderDao();
//                actualProcurementHeaderDao.setIn_Actual_rowid(0);
//                actualProcurementHeaderDao.setIn_LotNo(lotnumber);
//                actualProcurementHeaderDao.setIn_Farmer_Code(farmercode);
//                actualProcurementHeaderDao.setIn_Farmer_Name(farmername);
//                actualProcurementHeaderDao.setIn_Member_Type(membertype);
//                actualProcurementHeaderDao.setIn_Item_Code(singleActualHeaderDao.getIn_item_code());
//                actualProcurementHeaderDao.setIn_Item_Name(itemname);
//                actualProcurementHeaderDao.setIn_Actual_Qty(Double.parseDouble(qty));
//                actualProcurementHeaderDao.setIn_Actual_Price(Double.parseDouble(price));
//                actualProcurementHeaderDao.setIn_Actual_Value(Double.parseDouble(value));
//                actualProcurementHeaderDao.setIn_advance_amt(Double.parseDouble(advanceAmount));
//                actualProcurementHeaderDao.setIn_no_of_bags(Integer.parseInt(noofbags));
//                actualProcurementHeaderDao.setIn_Status(status);
//                actualProcurementHeaderDao.setIn_mode_flag("U");
//                actualProcurementHeaderDao.setIn_actual_remarks(singleActualHeaderDao.getIn_actual_remarks());
//                actualProcurementHeaderDao.setIn_approved_remarks(remarks);
//                actualProcurementHeaderDao.setIn_pickup_remarks("");
//
//                ActualProcurementContextDao actualProcurementContextDao=new ActualProcurementContextDao();
//                actualProcurementContextDao.setOrgnId(orgnCode);
//                //   actualProcurementContextDao.setLocnId(locnId);
//                actualProcurementContextDao.setLocnId(ApiUtils.instance);
//                actualProcurementContextDao.setUserId(userId);
//                actualProcurementContextDao.setLocaleId(localeId);
//                actualProcurementContextDao.setActualProcurementHeaderDao(actualProcurementHeaderDao);
//                actualProcurementContextDao.setPostOtherDetailDaoList(postOtherDetailDaoList);
//                actualProcurementContextDao.setPostQtyDetailDaoList(postQtyDetailDaoList);
//                actualProcurementContextDao.setPostSiNoDetailDaoList(postSiNoDetailDaoList);
//
//                ActualProcurementDocumentDao actualProcurementDocumentDao=new ActualProcurementDocumentDao();
//                actualProcurementDocumentDao.setActualProcurementContextDao(actualProcurementContextDao);
//
//                ActualProcurementSaveDao actualProcurementSaveDao=new ActualProcurementSaveDao();
//                actualProcurementSaveDao.setActualProcurementDocumentDao(actualProcurementDocumentDao);
//
//                mAPIService.postActualProcurementDetails(actualProcurementSaveDao)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<ActualProcurementSaveDao>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onNext(ActualProcurementSaveDao actualProcurementSaveDao) {
//                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//                                progressLayout.setVisibility(View.GONE);
//
//                                PawhsActualProcSaveDao pawhsActualProcSaveDao=new PawhsActualProcSaveDao(1,0,orgnCode,lotnumber,farmercode,farmername,membertype,
//                                        singleActualHeaderDao.getIn_item_code(),itemname, qty, price, value, "0", advanceAmount, "0", "0", "0", "0", noofbags, status, "0", remarks, "0", "0", "0","APPROVAL","U","Yes","","","","","","","","","","","");
//                                db.addAllActualProcSave(pawhsActualProcSaveDao);
//
//                                if(postQtyDetailDaoList.size()>0){
//                                    for(int i=0;i<postQtyDetailDaoList.size();i++){
//
//                                        PostQtyDetailDao postQtyDetailDao=new PostQtyDetailDao(1,postQtyDetailDaoList.get(i).getIn_qty_dtl_rowid(),postQtyDetailDaoList.get(i).getIn_qty_code(),
//                                                postQtyDetailDaoList.get(i).getIn_qty_name(),postQtyDetailDaoList.get(i).getIn_actual_value(),postQtyDetailDaoList.get(i).getIn_wr_qty_value(),
//                                                postQtyDetailDaoList.get(i).getIn_mode_flag(),postQtyDetailDaoList.get(i).getThresHoldValue(),postQtyDetailDaoList.get(i).getUom(),
//                                                "APPROVAL",lotnumber);
//                                        db.addAllQtyDetailsList(postQtyDetailDao);
//
//                                    }
//                                }
//
//                                if(postSiNoDetailDaoList.size()>0){
//                                    for(int i=0;i<postSiNoDetailDaoList.size();i++){
//
//                                        PostSiNoDetailDao postSiNoDetailDao=new PostSiNoDetailDao(1,postSiNoDetailDaoList.get(i).getIn_slno_row_id(),postSiNoDetailDaoList.get(i).getIn_slno(),
//                                                postSiNoDetailDaoList.get(i).getIn_temp1(),postSiNoDetailDaoList.get(i).getIn_temp2(),postQtyDetailDaoList.get(i).getIn_mode_flag(),
//                                                "APPROVAL",lotnumber);
//                                        db.addAllSiDetailsList(postSiNoDetailDao);
//
//                                    }
//                                }
//
//                                if(postOtherDetailDaoList.size()>0){
//                                    for(int i=0;i<postOtherDetailDaoList.size();i++){
//
//                                        PostOtherDetailDao postOtherDetailDao=new PostOtherDetailDao(1,postOtherDetailDaoList.get(i).getIn_otherdtl_row_id(),postOtherDetailDaoList.get(i).getIn_packaging_cost(),
//                                                postOtherDetailDaoList.get(i).getIn_transporting_cost(),postOtherDetailDaoList.get(i).getIn_unpacking_cost(),postOtherDetailDaoList.get(i).getIn_local_packaging_cost(),
//                                                postOtherDetailDaoList.get(i).getIn_local_transporting_cost(),postOtherDetailDaoList.get(i).getIn_temp_cost(),postOtherDetailDaoList.get(i).getIn_temp_cost1(),postOtherDetailDaoList.get(i).getIn_temp_cost2(),
//                                                postOtherDetailDaoList.get(i).getIn_mode_flag(),"APPROVAL",lotnumber);
//                                        db.addAllOtherDetailsList(postOtherDetailDao);
//
//                                    }
//                                }
//                                textViewLotNo.setText("");
//                                txt_uom.setText("");
//                                txt_var.setText("");
//                                textViewFarmerCode.setText("");
//                                textViewFarmerName.setText("");
//                                textViewMember.setText("");
//                                textViewItemName.setText("");
//                                textViewQty.setText("");
//                                textViewPrice.setText("");
//                                textViewValue.setText("");
//                                textViewQualityPara.setText("");
//                                textViewNoofBag.setText("");
//                                textViewSINo.setText("");
//                                textViewOtherCost.setText("");
//                                textViewTotalCost.setText("");
//                                textViewPickUpdate.setText("");
//                                edt_remarks.setText("");
//                                textViewAdvance.setText("");
//
//                                if(status.equalsIgnoreCase("Approved")){
//                                    showLotNoDialog("Success!","Approved");
//                                }else {
//                                    showLotNoDialog("Reject!",lotnumber + " has Rejected");
//                                }
//
//
//                            }
//                        });
//
//            }else {
//                Log.e("Test","0");
//                PawhsActualProcSaveDao pawhsActualProcSaveDao=new PawhsActualProcSaveDao(1,0,orgnCode,lotnumber,farmercode,farmername,membertype,
//                        singleActualHeaderDao.getIn_item_code(),itemname, qty, price, value, "0", advanceAmount, "0", "0", "0", "0", noofbags, status, "0", remarks, "0", "0", "0","APPROVAL","U","No","","","","","","","","","","","");
//                db.addAllActualProcSave(pawhsActualProcSaveDao);
//                Log.e("Test","1");
//                SQLiteDatabase dbs = db.getWritableDatabase();
//                dbs.execSQL("UPDATE ApproveList SET ApproveListStatus='Approve' WHERE ApproveListRowId="+apid);
//
//                if(postQtyDetailDaoList.size()>0){
//                    for(int i=0;i<postQtyDetailDaoList.size();i++){
//
//                        PostQtyDetailDao postQtyDetailDao=new PostQtyDetailDao(1,postQtyDetailDaoList.get(i).getIn_qty_dtl_rowid(),postQtyDetailDaoList.get(i).getIn_qty_code(),
//                                postQtyDetailDaoList.get(i).getIn_qty_name(),postQtyDetailDaoList.get(i).getIn_actual_value(),postQtyDetailDaoList.get(i).getIn_wr_qty_value(),
//                                postQtyDetailDaoList.get(i).getIn_mode_flag(),postQtyDetailDaoList.get(i).getThresHoldValue(),postQtyDetailDaoList.get(i).getUom(),
//                                "APPROVAL",lotnumber);
//                        db.addAllQtyDetailsList(postQtyDetailDao);
//
//                    }
//                }
//                Log.e("Test","2");
//                if(postSiNoDetailDaoList.size()>0){
//                    for(int i=0;i<postSiNoDetailDaoList.size();i++){
//
//                        PostSiNoDetailDao postSiNoDetailDao=new PostSiNoDetailDao(1,postSiNoDetailDaoList.get(i).getIn_slno_row_id(),postSiNoDetailDaoList.get(i).getIn_slno(),
//                                postSiNoDetailDaoList.get(i).getIn_temp1(),postSiNoDetailDaoList.get(i).getIn_temp2(),postQtyDetailDaoList.get(i).getIn_mode_flag(),
//                                "APPROVAL",lotnumber);
//                        db.addAllSiDetailsList(postSiNoDetailDao);
//
//                    }
//                }
//                Log.e("Test","3");
//                if(postOtherDetailDaoList.size()>0){
//                    for(int i=0;i<postOtherDetailDaoList.size();i++){
//
//                        PostOtherDetailDao postOtherDetailDao=new PostOtherDetailDao(1,postOtherDetailDaoList.get(i).getIn_otherdtl_row_id(),postOtherDetailDaoList.get(i).getIn_packaging_cost(),
//                                postOtherDetailDaoList.get(i).getIn_transporting_cost(),postOtherDetailDaoList.get(i).getIn_unpacking_cost(),postOtherDetailDaoList.get(i).getIn_local_packaging_cost(),
//                                postOtherDetailDaoList.get(i).getIn_local_transporting_cost(),postOtherDetailDaoList.get(i).getIn_temp_cost(),postOtherDetailDaoList.get(i).getIn_temp_cost1(),postOtherDetailDaoList.get(i).getIn_temp_cost2(),
//                                postOtherDetailDaoList.get(i).getIn_mode_flag(),"APPROVAL",lotnumber);
//                        db.addAllOtherDetailsList(postOtherDetailDao);
//
//                    }
//                }
//                Log.e("Test","4");
//                textViewLotNo.setText("");
//                txt_uom.setText("");
//                txt_var.setText("");
//                textViewFarmerCode.setText("");
//                textViewFarmerName.setText("");
//                textViewMember.setText("");
//                textViewItemName.setText("");
//                textViewQty.setText("");
//                textViewPrice.setText("");
//                textViewValue.setText("");
//                textViewQualityPara.setText("");
//                textViewNoofBag.setText("");
//                textViewSINo.setText("");
//                textViewOtherCost.setText("");
//                textViewTotalCost.setText("");
//                textViewPickUpdate.setText("");
//                edt_remarks.setText("");
//                textViewAdvance.setText("");
//                Log.e("Test","5");
//                showStatusDialog("Approve!","Approved Actual Purchase Stored Locally");
//
//            }
//
//        }
//
//
//
//    }
//
//    private void showStatusDialog(String title, String s) {
//        new AlertDialog.Builder(this)
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .setTitle(title)
//                .setMessage(s)
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        dialog.dismiss();
//                        Intent intent = new Intent(mContext, RecAppListVIewActivity.class);
//                        intent.putExtra(ApiUtils.CHECK_STATUS, "approve");
//                        startActivity(intent);
//                        finish();
//                    }
//                })
//                .show();
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
//    }
//
//    private void showLotNoDialog(String title, String message) {
//        new AlertDialog.Builder(this)
//                .setTitle(title)
//                .setMessage(message)
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        dialog.dismiss();
//                        Intent intent = new Intent(mContext, RecAppListVIewActivity.class);
//                        intent.putExtra(ApiUtils.CHECK_STATUS, "approve");
//                        startActivity(intent);
//                        finish();
//                    }
//                })
//                .show();
//    }
//    private int grandTotal2(List<Integer> items){
//
//        int totalPrice = 0;
//        for(int i = 0 ; i < items.size(); i++) {
//            totalPrice += items.get(i);
//        }
//
//        return totalPrice;
//    }
//
//}
    }
}