package cdfi.fintantra.meity.Pawhs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cdfi.fintantra.meity.Pawhs.formermodel.FormerDao;
import cdfi.fintantra.meity.Pawhs.model.ActProListDao;
import cdfi.fintantra.meity.Pawhs.model.OtherDetailDao;
import cdfi.fintantra.meity.Pawhs.model.PawhsActualProcSaveDao;
import cdfi.fintantra.meity.Pawhs.model.PmListDao;
import cdfi.fintantra.meity.Pawhs.model.PostOtherDetailDao;

import cdfi.fintantra.meity.Pawhs.model.PostQtyDetailDao;
import cdfi.fintantra.meity.Pawhs.model.PostSiNoDetailDao;
import cdfi.fintantra.meity.Pawhs.model.QtyDetailDao;
import cdfi.fintantra.meity.Pawhs.model.SiNoDetailDao;
import cdfi.fintantra.meity.Pawhs.model.SubmitRecEstPurchaseDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.ActualListDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.EstimateListDao;
import cdfi.fintantra.meity.Pawhs.model.singleactualprocmodel.SingleActualHeaderDao;
import cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel.SpmDetailDao;
import cdfi.fintantra.meity.Pawhs.model.warehouse.WareHouseListDao;
import cdfi.fintantra.meity.Pawhs.utils.MyConstants;


public class SQLiteDataBaseHandler extends SQLiteOpenHelper {
    public static int DATABASE_VERSION = 2;
    public static String DATABASE_NAME = "PawhsDB";

    //PRO_MASTER_ALL_PRODUCT
    public static final String TABLE_NAME_PRO_MASTER_ALL_PRODUCT="ProductMasterAllProduct";
    public static final String PMAP_KEY_ID="PmapKeyId";
    public static final String PMAP_OUT_PRODUCT_ROW_ID="PmapOutProductRowId";
    public static final String PMAP_OUT_PAWHS_CODE="PmapOutPawhsCode";
    public static final String PMAP_OUT_AGG_CODE="PmapOutAggCode";
    public static final String PMAP_OUT_PRODUCT_CODE="PmapOutProductCode";
    public static final String PMAP_OUT_PRODUCT_NAME="PmapOutProductName";
    public static final String PMAP_OUT_PRODUCT_CATEGORY="PmapOutProductCategory";
    public static final String PMAP_OUT_PRODUCT_SUBCATEGORY="PmapOutProductSubCategory";
    public static final String PMAP_OUT_HSN_CODE="PmapOutHsnCode";
    public static final String PMAP_OUT_HSN_DESC="PmapOutHsnDesc";
    public static final String PMAP_OUT_STATUS_CODE="PmapOutStatusCode";
    public static final String PMAP_OUT_STATUS_DESC="PmapOutStatusDesc";
    public static final String PMAP_OUT_ROW_TIMESTAMP="PmapOutRowTimeStamp";
    public static final String out_uomtype_code="out_uomtype_code";
    public static final String out_crop_variety="out_crop_variety";
    public static final String Out_value_withtax="Out_value_withtax";


    //FARMER
    public static final String TABLE_NAME_FARMER="FARMER";
    public static final String FARMER_KEY_ID="FarmerKeyId";
    public static final String FARMER_ROW_ID="FarmerRowId";
    public static final String FARMER_CODE="FarmerCode";
    public static final String FARMER_FARMER="FarmerFarmer";
    public static final String FARMER_FHW_NAME="FarmerFhwName";
    public static final String FARMER_NAME="FarmerName";
    public static final String FARMER_DIST="FarmerDist";
    public static final String FARMER_DIST_DESC="FarmerDistDesc";
    public static final String FARMER_TALUK="FarmerTaluk";
    public static final String FARMER_TALUK_DESC="FarmerTalukDesc";
    public static final String FARMER_PANCHAYAT="FarmerPanchayat";
    public static final String FARMER_PANCHAYAT_DESC="FarmerPanchayatDesc";
    public static final String FARMER_VILLAGE="FarmerVillage";
    public static final String FARMER_VILLAGE_DESC="FarmerVillageDesc";

    //SUBMITRECORDESTIMATEPURCHASE

    public static final String TABLE_NAME_SUBMIT_REC_EST_PURCHASE="SubmitRecEstPurchase";
    public static final String SUBMIT_REC_EST_PURCHASE_KEY_ID="SubmitRecEstPurchaseKeyId";
    public static final String SUBMIT_REC_EST_PURCHASE_FARMER_CODE="SubmitRecEstPurchaseFarmerCode";
    public static final String SUBMIT_REC_EST_PURCHASE_FARMER_NAME="SubmitRecEstPurchaseFarmerName";
    public static final String SUBMIT_REC_EST_PURCHASE_FARMER_MEMBER="SubmitRecEstPurchaseMember";
    public static final String SUBMIT_REC_EST_PURCHASE_ITEM_CODE="SubmitRecEstPurchaseItemCode";
    public static final String SUBMIT_REC_EST_PURCHASE_ITEM_NAME="SubmitRecEstPurchaseItemName";
    public static final String SUBMIT_REC_EST_PURCHASE_QUANTITY="SubmitRecEstPurchaseQty";
    public static final String SUBMIT_REC_EST_PURCHASE_PRICE="SubmitRecEstPurchasePrice";
    public static final String SUBMIT_REC_EST_PURCHASE_VALUE="SubmitRecEstPurchaseValue";
    public static final String SUBMIT_REC_EST_PURCHASE_DATE="SubmitRecEstPurchasePickUpDate";
    public static final String SUBMIT_REC_EST_PURCHASE_REMARKS="SubmitRecEstPurchaseRemarks";
    public static final String SUBMIT_REC_EST_PURCHASE_LOTNO="SubmitRecEstPurchaseLotNo";
    public static final String SUBMIT_REC_EST_PURCHASE_EST_ROWID="SubmitRecEstPurchaseEstRowId";
    public static final String SUBMIT_REC_EST_PURCHASE_ROW_TIME_STAMP="SubmitRecEstPurchaseRowTimeStamp";
    public static final String SUBMIT_REC_EST_PURCHASE_MODE_FLAG="SubmitRecEstPurchaseModeFlag";
    public static final String SUBMIT_REC_EST_PURCHASE_IS_CLOUD="SubmitRecEstPurchaseModeFlagIsCloud";
    public static final String SUBMIT_REC_EST_PURCHASE_ATTC="SubmitRecEstPurchaseattc";
    public static final String SUBMIT_REC_EST_PURCHASE_VS="SubmitRecEstPurchasevs";
    public static final String SUBMIT_REC_EST_PURCHASE_QP="SubmitRecEstPurchaseqp";
    public static final String SUBMIT_REC_EST_PURCHASE_RV="SubmitRecEstPurchaserv";
    public static final String SUBMIT_REC_EST_PURCHASE_LRP="SubmitRecEstPurchaselrp";
    public static final String SUBMIT_REC_EST_PURCHASE_LRPMN="SubmitRecEstPurchaselrpmn";

    //SUBMITESTIMATEPURCHASELIST

    public static final String TABLE_NAME_ESTIMATE_LIST="EstimateList";
    public static final String ESTIMATELIST_KEY_ID="EstimateListKeyId";
    public static final String ESTIMATELIST_ROW_ID="EstimateListRowId";
    public static final String ESTIMATELIST_LOTNO="EstimateListLotNo";
    public static final String ESTIMATELIST_AGG_CODE="EstimateListAggCode";
    public static final String ESTIMATELIST_FARMER_CODE="EstimateListFarmerCode";
    public static final String ESTIMATELIST_FARMER_NAME="EstimateListFarmerName";
    public static final String ESTIMATELIST_MEMBER="EstimateListMember";
    public static final String ESTIMATELIST_ITEM_CODE="EstimateListItemCode";
    public static final String ESTIMATELIST_ITEM_NAME="EstimateListItemName";
    public static final String ESTIMATELIST_QTY="EstimateListQty";
    public static final String ESTIMATELIST_PRICE="EstimateListPrice";
    public static final String ESTIMATELIST_VALUE="EstimateListValue";
    public static final String ESTIMATELIST_REMARK="EstimateListRemark";
    public static final String ESTIMATELIST_STATUS="EstimateListStatus";
    public static final String ESTIMATELIST_PICKUPDATE="EstimateListPickupDate";
    public static final String ESTIMATELIST_ALREADY_TAKEN="AlreadyTaken";

    //QULAITYPARAlIST
    public static final String TABLE_NAME_SPMDETAIL_LIST="SpmDetailList";
    public static final String SPMDETAIL_KEY_ID="SpmDetailListKeyId";
    public static final String SPMDETAIL_DTL_ROW_ID="SpmDetailListDtlRowId";
    public static final String SPMDETAIL_PAWHS_CODE="SpmDetailListPawhsCode";
    public static final String SPMDETAIL_ROW_SLNO="SpmDetailListRowSlNo";
    public static final String SPMDETAIL_MAIZE_CODE="SpmDetailListMaizeCode";
    public static final String SPMDETAIL_MAIZE_NAME="SpmDetailListMaizeName";
    public static final String SPMDETAIL_MAIZE_RANGE="SpmDetailListMaizeRange";
    public static final String SPMDETAIL_MAIZE_INTERVAL="SpmDetailListMaizeInterval";
    public static final String SPMDETAIL_MAIZE_UNIT="SpmDetailListMaizeUnit";
    public static final String SPMDETAIL_MAIZE_STATUS_CODE="SpmDetailListMaizeStatusCode";
    public static final String SPMDETAIL_MODE_FLAG="SpmDetailListModeFlag";

    //ActualQtyDetail
    public static final String TABLE_NAME_QTY_DETAIL="QtyDetailList";
    public static final String QTY_DETAIL_KEY_ID="QtyDetailListKeyId";
    public static final String QTY_DETAIL_ROW_ID="QtyDetailListRowId";
    public static final String QTY_DETAIL_QTY_CODE="QtyDetailListQtyCode";
    public static final String QTY_DETAIL_QTY_NAME="QtyDetailListQtyName";
    public static final String QTY_DETAIL_ACTUAL_VALUE="QtyDetailListActualValue";
    public static final String QTY_DETAIL_WR_QTY_VALUE="QtyDetailListWrQtyValue";
    public static final String QTY_DETAIL_MODE_FLAG="QtyDetailListModeFlag";
    public static final String QTY_DETAIL_THRESHOLD="QtyDetailListThreshold";
    public static final String QTY_DETAIL_UOM="QtyDetailListUom";
    public static final String QTY_STATUS="QtyDetailStatus";
    public static final String QTY_LOTNO="QtyDetailStatusLotno";

    //ActualSIDETAIL
    public static final String TABLE_NAME_SINO_DETAIL="SiNoDetailList";
    public static final String SINO_DETAIL_KEY_ID="SiNoDetailListKeyId";
    public static final String SINO_DETAIL_ROW_ID="SiNoDetailListRowId";
    public static final String SINO_DETAIL_SL_NO="SiNoDetailListSlNo";
    public static final String SINO_DETAIL_IN_TEMP1="SiNoDetailListInTemp1";
    public static final String SINO_DETAIL_IN_TEMP2="SiNoDetailListInTemp2";
    public static final String SINO_DETAIL_MODE_FLAG="SiNoDetailListModeFlag";
    public static final String SINO_DETAIL_STATUS="SiNoDetailListStatus";
    public static final String SINO_DETAIL_LOTNO="SiNoDetailListLotno";


    //ActualOTHERDETAIL

    public static final String TABLE_NAME_OTHER_DETAIL="OtherDetailList";
    public static final String OTHER_DETAIL_KEY_ID="OtherDetailListKeyId";
    public static final String OTHER_DETAIL_ROW_ID="OtherDetailListRowId";
    public static final String OTHER_DETAIL_PACKING_COST="OtherPackingCost";
    public static final String OTHER_DETAIL_TRANSPORTATION_COST="OtherTransportationCost";
    public static final String OTHER_DETAIL_UNPACKING_COST="OtherUnpackingCost";
    public static final String OTHER_DETAIL_LOCAL_PACKING_COST="OtherLocalPackingCost";
    public static final String OTHER_DETAIL_LOCAL_TRANSPORTING_COST="OtherLocalTransportingCost";
    public static final String OTHER_DETAIL_TEMP_COST="OtherLocalTempCost";
    public static final String OTHER_DETAIL_TEMP_COST1="OtherDetailTempCost1";
    public static final String OTHER_DETAIL_TEMP_COST2="OtherDetailTempCost2";
    public static final String OTHER_DETAIL_MODE_FLAG="OtherDetailListModeFlag";
    public static final String OTHER_DETAIL_STATUS="OtherDetailListStatus";
    public static final String OTHER_DETAIL_LOTNO="OtherDetailListLotno";


    //PAWHSACTUALPROCSAVE

    public static final String TABLE_NAME_ACTUALPROC_SAVE="ActualProcSave";
    public static final String ACTUALPROC_SAVE_KEY_ID="ActualProcSaveKeyId";
    public static final String ACTUALPROC_SAVE_ROW_ID="ActualProcSaveRowId";
    public static final String ACTUALPROC_SAVE_AGG_CODE="ActualProcSaveAggCode";
    public static final String ACTUALPROC_SAVE_LOTNO="ActualProcSaveLotno";
    public static final String ACTUALPROC_SAVE_FARMER_CODE="ActualProcSaveFarmerCode";
    public static final String ACTUALPROC_SAVE_FARMER_NAME="ActualProcSaveFarmerName";
    public static final String ACTUALPROC_SAVE_MEMBER="ActualProcSaveMember";
    public static final String ACTUALPROC_SAVE_ITEM_CODE="ActualProcSaveItemCode";
    public static final String ACTUALPROC_SAVE_ITEM_NAME="ActualProcSaveItemName";
    public static final String ACTUALPROC_SAVE_ACT_QTY="ActualProcSaveActQty";
    public static final String ACTUALPROC_SAVE_ACT_VALUE="ActualProcSaveActValue";
    public static final String ACTUALPROC_SAVE_ACT_PRICE="ActualProcSaveActPrice";
    public static final String ACTUALPROC_SAVE_ACT_DATE="ActualProcSaveActDate";
    public static final String ACTUALPROC_SAVE_ADVANCE_AMT="ActualProcSaveAdvanceAmt";
    public static final String ACTUALPROC_SAVE_APPROVE_DATE="ActualProcSaveApproveDate";
    public static final String ACTUALPROC_SAVE_REJECT_DATE="ActualProcSaveRejectDate";
    public static final String ACTUALPROC_SAVE_PICKUP_DATE="ActualProcSavePickUpDate";
    public static final String ACTUALPROC_SAVE_WR_DATE="ActualProcSaveWrDate";
    public static final String ACTUALPROC_SAVE_NO_OF_BAGS="ActualProcSaveNoofBags";
    public static final String ACTUALPROC_SAVE_STATUS="ActualProcSaveStatus";
    public static final String ACTUALPROC_ACTUAL_REMARKS="ActualProcSaveActRemarks";
    public static final String ACTUALPROC_APPROVE_REMARKS="ActualProcSaveApproveRemarks";
    public static final String ACTUALPROC_REJECT_REMARKS="ActualProcSaveRejectRemarks";
    public static final String ACTUALPROC_PICKUP_REMARKS="ActualProcSavePickupRemarks";
    public static final String ACTUALPROC_WR_REMARKS="ActualProcSaveWrRemarks";
    public static final String ACTUALPROC_STATUS_VALUE="ActualProcSaveStatusValue";
    public static final String ACTUALPROC_MODE_FLAG="ActualProcSaveModeFlag";
    public static final String ACTUALPROC_IS_CLOUD="ActualProcSaveIsCloud";
    public static final String ACTUALPROC_ACCEPT_QTY="ActualProcSaveAcceptQty";
    public static final String ACTUALPROC_ATTC="ActualProcSaveattc";
    public static final String ACTUALPROC_QCP="ActualProcSaveqcp";
    public static final String ACTUALPROC_VNO="ActualProcSavevno";
    public static final String ACTUALPROC_VTY="ActualProcSavevty";
    public static final String ACTUALPROC_DSN="ActualProcSavedsn";
    public static final String ACTUALPROC_LRP="ActualProcSavelrp";
    public static final String ACTUALPROC_LRPMN="ActualProcSavelrpmn";
    public static final String ACTUALPROC_PT="ActualProcSavept";
    public static final String ACTUALPROC_BNO="ActualProcSavebno";
    public static final String ACTUALPROC_CQ="ActualProcSavecq";

    //APPROVELIST
    public static final String TABLE_NAME_APPROVE_LIST="ApproveList";
    public static final String APPROVE_LIST_KEY_ID="ApproveListKeyId";
    public static final String APPROVE_LIST_ROW_ID="ApproveListRowId";
    public static final String APPROVE_LIST_LOT_NO="ApproveListLotNo";
    public static final String APPROVE_LIST_AGG_CODE="ApproveListAggCode";
    public static final String APPROVE_LIST_FARMER_CODE="ApproveListFarmerCode";
    public static final String APPROVE_LIST_FARMER_NAME="ApproveListFarmerName";
    public static final String APPROVE_LIST_MEMBER="ApproveListMember";
    public static final String APPROVE_LIST_ITEM_CODE="ApproveListItemCode";
    public static final String APPROVE_LIST_ITEM_NAME="ApproveListItemName";
    public static final String APPROVE_LIST_ACTUAL_QTY="ApproveListActQty";
    public static final String APPROVE_LIST_ACTUAL_PRICE="ApproveListActPrice";
    public static final String APPROVE_LIST_ACTUAL_VALUE="ApproveListActValue";
    public static final String APPROVE_LIST_ACTUAL_DATE="ApproveListActualDate";
    public static final String APPROVE_LIST_ADVANCE_AMOUNT="ApproveListAdvanceAmt";
    public static final String APPROVE_LIST_APPROVE_DATE="ApproveListApproveDate";
    public static final String APPROVE_LIST_PICKUP_DATE="ApproveListPickupDate";
    public static final String APPROVE_LIST_WR_DATE="ApproveListWRDate";
    public static final String APPROVE_LIST_NO_OF_BAGS="ApproveListNoofBags";
    public static final String APPROVE_LIST_STATUS="ApproveListStatus";
    public static final String APPROVE_LIST_ACTUAL_REMARK="ApproveListActualRemark";
    public static final String APPROVE_LIST_APPROVE_REMARK="ApproveListApproveRemark";
    public static final String APPROVE_LIST_PICKUP_REMARK="ApproveListPickupRemark";
    public static final String APPROVE_LIST_WR_REMARK="ApproveListWrRemark";
    public static final String APPROVE_LIST_ATTC="ApproveListattc";
    public static final String APPROVE_LIST_QCP="ApproveListqcp";
    public static final String APPROVE_LIST_VNO="ApproveListvno";
    public static final String APPROVE_LIST_VTY="ApproveListvty";
    public static final String APPROVE_LIST_DSN="ApproveListdsn";
    public static final String APPROVE_LIST_LRP="ApproveListlrp";
    public static final String APPROVE_LIST_LRPMN="ApproveListlrpmn";
    public static final String APPROVE_LIST_PT="ApproveListpt";
    public static final String APPROVE_LIST_BNO="ApproveListbno";
    public static final String APPROVE_LIST_CQ="ApproveListcq";


    //WAREHOUSELIST

    public static final String TABLE_NAME_WAREHOUSE_LIST="WrList";
    public static final String WAREHOUSE_LIST_KEY_ID="WrListKeyId";
    public static final String WAREHOUSE_LIST_ROW_ID="WrListRowId";
    public static final String WAREHOUSE_LIST_LOT_NO="WrListLotNo";
    public static final String WAREHOUSE_LIST_AGG_CODE="WrListAggCode";
    public static final String WAREHOUSE_LIST_FARMER_CODE="WrListFarmerCode";
    public static final String WAREHOUSE_LIST_FARMER_NAME="WrListFarmerName";
    public static final String WAREHOUSE_LIST_MEMBER="WrListMember";
    public static final String WAREHOUSE_LIST_ITEM_CODE="WrListItemCode";
    public static final String WAREHOUSE_LIST_ITEM_NAME="WrListItemName";
    public static final String WAREHOUSE_LIST_ACTUAL_QTY="WrListActQty";
    public static final String WAREHOUSE_LIST_ACTUAL_PRICE="WrListActPrice";
    public static final String WAREHOUSE_LIST_ACTUAL_VALUE="WrListActValue";
    public static final String WAREHOUSE_LIST_ACTUAL_DATE="WrListActualDate";
    public static final String WAREHOUSE_LIST_ADVANCE_AMOUNT="WrListAdvanceAmt";
    public static final String WAREHOUSE_LIST_APPROVE_DATE="WrListApproveDate";
    public static final String WAREHOUSE_LIST_PICKUP_DATE="WrListPickupDate";
    public static final String WAREHOUSE_LIST_WR_DATE="WrListWRDate";
    public static final String WAREHOUSE_LIST_NO_OF_BAGS="WrListNoofBags";
    public static final String WAREHOUSE_LIST_STATUS="WrListStatus";
    public static final String WAREHOUSE_LIST_ACTUAL_REMARK="WrListActualRemark";
    public static final String WAREHOUSE_LIST_APPROVE_REMARK="WrListApproveRemark";
    public static final String WAREHOUSE_LIST_PICKUP_REMARK="WrListPickupRemark";
    public static final String WAREHOUSE_LIST_WR_REMARK="WrListWrRemark";

    //ApproveSingleHeaderDao
    public static final String TABLE_NAME_APP_SINGLE_HEADER="AppSingleHeader";
    public static final String APP_SINGLE_HEADER_KEY_ID="AppSingleHeaderKeyId";
    public static final String APP_SINGLE_HEADER_ROW_ID="AppSingleHeaderRowId";
    public static final String APP_SINGLE_HEADER_AGG_CODE="AppSingleHeaderAggCode";
    public static final String APP_SINGLE_HEADER_LOT_NO="AppSingleHeaderLotNo";
    public static final String APP_SINGLE_HEADER_FARMER_CODE="AppSingleHeaderFarmerCode";
    public static final String APP_SINGLE_HEADER_FARMER_NAME="AppSingleHeaderFarmerName";
    public static final String APP_SINGLE_HEADER_MEMBER="AppSingleHeaderMember";
    public static final String APP_SINGLE_HEADER_ITEM_CODE="AppSingleHeaderItemCode";
    public static final String APP_SINGLE_HEADER_ITEM_NAME="AppSingleHeaderItemName";
    public static final String APP_SINGLE_HEADER_ACT_QTY="AppSingleHeaderActQty";
    public static final String APP_SINGLE_HEADER_ACTUAL_PRICE="AppSingleHeaderActPrice";
    public static final String APP_SINGLE_HEADER_ACTUAL_VALUE="AppSingleHeaderActValue";
    public static final String APP_SINGLE_HEADER_ADVANCE_AMT="AppSingleHeaderAdvanceAmt";
    public static final String APP_SINGLE_HEADER_NO_OF_BAGS="AppSingleHeaderNoofBags";
    public static final String APP_SINGLE_HEADER_IN_STATUS="AppSingleHeaderStatus";
    public static final String APP_SINGLE_HEADER_ACTUAL_REMARKS="AppSingleHeaderActualRemark";
    public static final String APP_SINGLE_HEADER_APPROVE_REMARK="AppSingleHeaderApproveRemark";
    public static final String APP_SINGLE_HEADER_PICKUP_REMARK="AppSingleHeaderPickupRemark";
    public static final String APP_SINGLE_HEADER_WR_REMARK="AppSingleHeaderWrRemark";
    public static final String APP_SINGLE_HEADER_MODE_FLAG="AppSingleHeaderModeFlag";
    public static final String APP_SINGLE_HEADER_ROW_TIMESTAMP="AppSingleHeaderTimeStamp";
    public static final String APP_SINGLE_HEADER_ATTC="AppSingleHeaderattc";
    public static final String APP_SINGLE_HEADER_QCP="AppSingleHeaderqcp";
    public static final String APP_SINGLE_HEADER_VNO="AppSingleHeadervno";
    public static final String APP_SINGLE_HEADER_VTY="AppSingleHeadervty";
    public static final String APP_SINGLE_HEADER_DSN="AppSingleHeaderdsn";
    public static final String APP_SINGLE_HEADER_LRP="AppSingleHeaderlrp";
    public static final String APP_SINGLE_HEADER_LRPMN="AppSingleHeaderlrpmn";
    public static final String APP_SINGLE_HEADER_PT="AppSingleHeaderpt";
    public static final String APP_SINGLE_HEADER_BNO="AppSingleHeaderbno";
    public static final String APP_SINGLE_HEADER_CQ="AppSingleHeadercq";

    //ApproveQtyDetail

    public static final String TABLE_NAME_APP_QTY_DETAIL="AppQtyDetail";
    public static final String APP_QTY_DETAIL_KEY_ID="AppQtyDetailKeyId";
    public static final String APP_QTY_DETAIL_ROW_ID="AppQtyDetailRowId";
    public static final String APP_QTY_DETAIL_QTY_CODE="AppQtyDetailQtyCode";
    public static final String APP_QTY_DETAIL_QTY_NAME="AppQtyDetailQtyName";
    public static final String APP_QTY_DETAIL_ACTUAL_VALUE="AppQtyDetailActValue";
    public static final String APP_QTY_DETAIL_WR_QTY_VALUE="AppQtyDetailWrQtyValue";
    public static final String APP_QTY_DETAIL_MODE_FLAG="AppQtyDetailModeFlag";
    public static final String APP_QTY_DETAIL_AGG_CODE="AppQtyDetailAggCode";
    public static final String APP_QTY_DETAIL_LOTNO="AppQtyDetailLotNo";
    public static final String APP_QTY_DETAIL_ITEM_CODE="AppQtyDetailItemCode";
    public static final String APP_QTY_DETAIL_STATUS="AppQtyDetailStatus";

    //ApproveSinoDetails

    public static final String TABLE_NAME_APP_SINO_DETAIL="AppSiNoDetail";
    public static final String APP_SINO_DETAIL_KEY_ID="AppSiNoDetailKeyId";
    public static final String APP_SINO_DETAIL_ROW_ID="AppSiNoDetailRowId";
    public static final String APP_SINO_DETAIL_SL_NO="AppSiNoDetailSlNo";
    public static final String APP_SINO_DETAIL_TEMP1="AppSiNoDetailTempl";
    public static final String APP_SINO_DETAIL_TEMP2="AppSiNoDetailTemp2";
    public static final String APP_SINO_DETAIL_MODE_FLAG="AppSiNoDetailModeFlag";
    public static final String APP_SINO_DETAIL_AGG_CODE="AppSiNoDetailAggCode";
    public static final String APP_SINO_DETAIL_LOT_NO="AppSiNoDetailLotno";
    public static final String APP_SINO_DETAIL_STATUS="AppSiNoDetailStatus";

    //ApproveOtherDetails

    public static final String TABLE_NAME_APP_OTHER_DETAIL="AppOtherDetail";
    public static final String APP_OTHER_DETAIL_KEY_ID="AppOtherDetailKeyId";
    public static final String APP_OTHER_DETAIL_ROW_ID="AppOtherDetailRowId";
    public static final String APP_OTHER_DETAIL_PACKING_COST="AppOtherPackingCost";
    public static final String APP_OTHER_DETAIL_TRANSPORTING_COST="AppOtherTransportingCost";
    public static final String APP_OTHER_DETAIL_UNPACKING_COST="AppOtherUnpackingCost";
    public static final String APP_OTHER_DETAIL_LOCAL_PACKING_COST="AppOtherLocalPackingCost";
    public static final String APP_OTHER_DETAIL_LOCAL_TRANSPORTING_COST="AppOtherLocalTransportingCost";
    public static final String APP_OTHER_DETAIL_TEMP_COST="AppOtherDetailTempCost";
    public static final String APP_OTHER_DETAIL_TEMP_COST1="AppOtherDetailTempCost1";
    public static final String APP_OTHER_DETAIL_TEMP_COST2="AppOtherDetailTempCost2";
    public static final String APP_OTHER_DETAIL_MODE_FLAG="AppOtherDetailModeFlag";
    public static final String APP_OTHER_DETAIL_AGG_CODE="AppOtherDetailAggCode";
    public static final String APP_OTHER_DETAIL_LOTNO="AppOtherDetailLotNo";
    public static final String APP_OTHER_DETAIL_STATUS="AppOtherDetailStatus";




    //WRSingleHeaderDao
    public static final String TABLE_NAME_WR_SINGLE_HEADER="WRSingleHeader";
    public static final String WR_SINGLE_HEADER_KEY_ID="WRSingleHeaderKeyId";
    public static final String WR_SINGLE_HEADER_ROW_ID="WRSingleHeaderRowId";
    public static final String WR_SINGLE_HEADER_AGG_CODE="WRSingleHeaderAggCode";
    public static final String WR_SINGLE_HEADER_LOT_NO="WRSingleHeaderLotNo";
    public static final String WR_SINGLE_HEADER_FARMER_CODE="WRSingleHeaderFarmerCode";
    public static final String WR_SINGLE_HEADER_FARMER_NAME="WRSingleHeaderFarmerName";
    public static final String WR_SINGLE_HEADER_MEMBER="WRSingleHeaderMember";
    public static final String WR_SINGLE_HEADER_ITEM_CODE="WRSingleHeaderItemCode";
    public static final String WR_SINGLE_HEADER_ITEM_NAME="WRSingleHeaderItemName";
    public static final String WR_SINGLE_HEADER_ACT_QTY="WRSingleHeaderActQty";
    public static final String WR_SINGLE_HEADER_ACTUAL_PRICE="WRSingleHeaderActPrice";
    public static final String WR_SINGLE_HEADER_ACTUAL_VALUE="WRSingleHeaderActValue";
    public static final String WR_SINGLE_HEADER_ADVANCE_AMT="WRSingleHeaderAdvanceAmt";
    public static final String WR_SINGLE_HEADER_NO_OF_BAGS="WRSingleHeaderNoofBags";
    public static final String WR_SINGLE_HEADER_IN_STATUS="WRSingleHeaderStatus";
    public static final String WR_SINGLE_HEADER_ACTUAL_REMARKS="WRSingleHeaderActualRemark";
    public static final String WR_SINGLE_HEADER_APPROVE_REMARK="WRSingleHeaderApproveRemark";
    public static final String WR_SINGLE_HEADER_PICKUP_REMARK="WRSingleHeaderPickupRemark";
    public static final String WR_SINGLE_HEADER_WR_REMARK="WRSingleHeaderWrRemark";
    public static final String WR_SINGLE_HEADER_MODE_FLAG="WRSingleHeaderModeFlag";
    public static final String WR_SINGLE_HEADER_ROW_TIMESTAMP="WRSingleHeaderTimeStamp";

    //WRQtyDetail

    public static final String TABLE_NAME_WR_QTY_DETAIL="WRQtyDetail";
    public static final String WR_QTY_DETAIL_KEY_ID="WRQtyDetailKeyId";
    public static final String WR_QTY_DETAIL_ROW_ID="WRQtyDetailRowId";
    public static final String WR_QTY_DETAIL_QTY_CODE="WRQtyDetailQtyCode";
    public static final String WR_QTY_DETAIL_QTY_NAME="WRQtyDetailQtyName";
    public static final String WR_QTY_DETAIL_ACTUAL_VALUE="WRQtyDetailActValue";
    public static final String WR_QTY_DETAIL_WR_QTY_VALUE="WRQtyDetailWrQtyValue";
    public static final String WR_QTY_DETAIL_MODE_FLAG="WRQtyDetailModeFlag";
    public static final String WR_QTY_DETAIL_AGG_CODE="WRQtyDetailAggCode";
    public static final String WR_QTY_DETAIL_LOTNO="WRQtyDetailLotNo";
    public static final String WR_QTY_DETAIL_ITEM_CODE="WRQtyDetailItemCode";
    public static final String WR_QTY_DETAIL_STATUS="WRQtyDetailStatus";

    //WRSinoDetails

    public static final String TABLE_NAME_WR_SINO_DETAIL="WRSiNoDetail";
    public static final String WR_SINO_DETAIL_KEY_ID="WRSiNoDetailKeyId";
    public static final String WR_SINO_DETAIL_ROW_ID="WRSiNoDetailRowId";
    public static final String WR_SINO_DETAIL_SL_NO="WRSiNoDetailSlNo";
    public static final String WR_SINO_DETAIL_TEMP1="WRSiNoDetailTempl";
    public static final String WR_SINO_DETAIL_TEMP2="WRSiNoDetailTemp2";
    public static final String WR_SINO_DETAIL_MODE_FLAG="WRSiNoDetailModeFlag";
    public static final String WR_SINO_DETAIL_AGG_CODE="WRSiNoDetailAggCode";
    public static final String WR_SINO_DETAIL_LOT_NO="WRSiNoDetailLotno";
    public static final String WR_SINO_DETAIL_STATUS="WRSiNoDetailStatus";

    //WareHouseOtherDetails
    public static final String TABLE_NAME_WR_OTHER_DETAIL="WROtherDetail";
    public static final String WR_OTHER_DETAIL_KEY_ID="WROtherDetailKeyId";
    public static final String WR_OTHER_DETAIL_ROW_ID="WROtherDetailRowId";
    public static final String WR_OTHER_DETAIL_PACKING_COST="WROtherPackingCost";
    public static final String WR_OTHER_DETAIL_TRANSPORTING_COST="WROtherTransportingCost";
    public static final String WR_OTHER_DETAIL_UNPACKING_COST="WROtherUnpackingCost";
    public static final String WR_OTHER_DETAIL_LOCAL_PACKING_COST="WROtherLocalPackingCost";
    public static final String WR_OTHER_DETAIL_LOCAL_TRANSPORTING_COST="WROtherLocalTransportingCost";
    public static final String WR_OTHER_DETAIL_TEMP_COST="WROtherDetailTempCost";
    public static final String WR_OTHER_DETAIL_TEMP_COST1="WROtherDetailTempCost1";
    public static final String WR_OTHER_DETAIL_TEMP_COST2="WROtherDetailTempCost2";
    public static final String WR_OTHER_DETAIL_MODE_FLAG="WROtherDetailModeFlag";
    public static final String WR_OTHER_DETAIL_AGG_CODE="WROtherDetailAggCode";
    public static final String WR_OTHER_DETAIL_LOTNO="WROtherDetailLotNo";
    public static final String WR_OTHER_DETAIL_STATUS="WROtherDetailStatus";

    //WareHouseReceiptListDetails
    public static final String TABLE_NAME_WR_RECEIPT_LIST="WrReceiptList";
    public static final String WR_RECEIPT_LIST_KEY_ID="WrReceiptListKeyId";
    public static final String WR_RECEIPT_LIST_ROW_ID="WrReceiptListRowId";
    public static final String WR_RECEIPT_LIST_LOTNO="WrReceiptListLotno";
    public static final String WR_RECEIPT_LIST_WHS_CODE="WrReceiptListWhsCode";
    public static final String WR_RECEIPT_LIST_FARMER_CODE="WrReceiptListFarmerCode";
    public static final String WR_RECEIPT_LIST_FARMER_NAME="WrReceiptListFarmerName";
    public static final String WR_RECEIPT_LIST_MEMBER="WrReceiptListMember";
    public static final String WR_RECEIPT_LIST_ITEM_CODE="WrReceiptListItemCode";
    public static final String WR_RECEIPT_LIST_ITEM_NAME="WrReceiptListItemName";
    public static final String WR_RECEIPT_LIST_ACCEPT_QTY="WrReceiptListAcceptQty";
    public static final String WR_RECEIPT_LIST_PAYMENT_ADVCNO="WrReceiptListPaymentAdvcNo";
    public static final String WR_RECEIPT_LIST_ACCEPTED_DATE="WrReceiptListAcceptDate";
    public static final String WR_RECEIPT_LIST_STATUS="WrReceiptListStatus";
    public static final String WR_RECEIPT_LIST_REMARK="WrReceiptListRemark";
    public static final String WR_RECEIPT_LIST_TIMESTAMP="WrReceiptListTimeStamp";

    //ActualListDetails
    public static final String TABLE_NAME_ACTUAL_PRO_LIST="ActualProList";
    public static final String ACTUAL_PRO_LIST_KEY_ID="ActualProListKeyId";
    public static final String ACTUAL_PRO_LIST_ROW_ID="ActualProListRowId";
    public static final String ACTUAL_PRO_LIST_AGG_CODE="ActualProListAggCode";
    public static final String ACTUAL_PRO_LIST_LOTNO="ActualProListLotno";
    public static final String ACTUAL_PRO_LIST_FARMER_CODE="ActualProListFamerCode";
    public static final String ACTUAL_PRO_LIST_FARMER_NAME="ActualProListFarmerName";
    public static final String ACTUAL_PRO_LIST_MEMBER="ActualProListMember";
    public static final String ACTUAL_PRO_LIST_ITEM_CODE="ActualProListItemCode";
    public static final String ACTUAL_PRO_LIST_ITEM_NAME="ActualProListItemName";
    public static final String ACTUAL_PRO_LIST_STATUS="ActualProListStatus";
    public static final String ACTUAL_PRO_LIST_TIMESTAMP="ActualProListTimeStamp";



    public SQLiteDataBaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(MyConstants.TAG, "DB Created");
    }

    String create_product_master_allproduct="CREATE TABLE "+TABLE_NAME_PRO_MASTER_ALL_PRODUCT+"("+PMAP_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +PMAP_OUT_PRODUCT_ROW_ID+" TEXT, "+PMAP_OUT_PAWHS_CODE+" TEXT, "+ PMAP_OUT_AGG_CODE+" TEXT, "+PMAP_OUT_PRODUCT_CODE+" TEXT, "
            +PMAP_OUT_PRODUCT_NAME+" TEXT, "+PMAP_OUT_PRODUCT_CATEGORY+" TEXT, "+ PMAP_OUT_PRODUCT_SUBCATEGORY+" TEXT, "+PMAP_OUT_HSN_CODE+" TEXT, "
            +PMAP_OUT_HSN_DESC+" TEXT, "+PMAP_OUT_STATUS_CODE+" TEXT, "+PMAP_OUT_STATUS_DESC+" TEXT, "+PMAP_OUT_ROW_TIMESTAMP+" TEXT, "+out_uomtype_code+" TEXT, "+out_crop_variety+" TEXT, "+Out_value_withtax+" TEXT)";

    String create_farmer_list="CREATE TABLE "+TABLE_NAME_FARMER+"("+FARMER_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +FARMER_ROW_ID+" TEXT, "+FARMER_CODE+" TEXT, "+ FARMER_FARMER+" TEXT, "+FARMER_FHW_NAME+" TEXT, "
            +FARMER_NAME+" TEXT, "+FARMER_DIST+" TEXT, "+ FARMER_DIST_DESC+" TEXT, "+FARMER_TALUK+" TEXT, "
            +FARMER_TALUK_DESC+" TEXT, "+FARMER_PANCHAYAT+" TEXT, "+FARMER_PANCHAYAT_DESC+" TEXT, "+FARMER_VILLAGE+" TEXT, "+FARMER_VILLAGE_DESC+" TEXT)";

    String create_submit_rec_est_purchase="CREATE TABLE "+TABLE_NAME_SUBMIT_REC_EST_PURCHASE+"("+SUBMIT_REC_EST_PURCHASE_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +SUBMIT_REC_EST_PURCHASE_FARMER_CODE+" TEXT, "+SUBMIT_REC_EST_PURCHASE_FARMER_NAME+" TEXT, "+ SUBMIT_REC_EST_PURCHASE_FARMER_MEMBER+" TEXT, "+SUBMIT_REC_EST_PURCHASE_ITEM_CODE+" TEXT, "
            +SUBMIT_REC_EST_PURCHASE_ITEM_NAME+" TEXT, "+SUBMIT_REC_EST_PURCHASE_QUANTITY+" TEXT, "+ SUBMIT_REC_EST_PURCHASE_PRICE+" TEXT, "+SUBMIT_REC_EST_PURCHASE_VALUE+" TEXT, "
            +SUBMIT_REC_EST_PURCHASE_DATE+" TEXT, "+SUBMIT_REC_EST_PURCHASE_REMARKS+" TEXT, "+SUBMIT_REC_EST_PURCHASE_LOTNO+" TEXT, "
            +SUBMIT_REC_EST_PURCHASE_EST_ROWID+" TEXT, "+SUBMIT_REC_EST_PURCHASE_ROW_TIME_STAMP+" TEXT, "+SUBMIT_REC_EST_PURCHASE_MODE_FLAG+" TEXT, "+SUBMIT_REC_EST_PURCHASE_IS_CLOUD+" TEXT, "+SUBMIT_REC_EST_PURCHASE_ATTC+" TEXT, "+SUBMIT_REC_EST_PURCHASE_VS+" TEXT, "+SUBMIT_REC_EST_PURCHASE_QP+" TEXT, "+SUBMIT_REC_EST_PURCHASE_RV+" TEXT, "+SUBMIT_REC_EST_PURCHASE_LRP+" TEXT, "+SUBMIT_REC_EST_PURCHASE_LRPMN+" TEXT)";

    String create_estimate_list="CREATE TABLE "+TABLE_NAME_ESTIMATE_LIST+"("+ESTIMATELIST_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +ESTIMATELIST_ROW_ID+" TEXT, "+ESTIMATELIST_LOTNO+" TEXT, "+ ESTIMATELIST_AGG_CODE+" TEXT, "+ESTIMATELIST_FARMER_CODE+" TEXT, "
            +ESTIMATELIST_FARMER_NAME+" TEXT, "+ESTIMATELIST_MEMBER+" TEXT, "+ ESTIMATELIST_ITEM_CODE+" TEXT, "+ESTIMATELIST_ITEM_NAME+" TEXT, "
            +ESTIMATELIST_QTY+" TEXT, "+ESTIMATELIST_PRICE+" TEXT, "+ESTIMATELIST_VALUE+" TEXT, "+ESTIMATELIST_REMARK+" TEXT, "+ESTIMATELIST_STATUS+" TEXT, "+ESTIMATELIST_PICKUPDATE+" TEXT, "+ESTIMATELIST_ALREADY_TAKEN+" TEXT)";


    String create_spmdetail_list="CREATE TABLE "+TABLE_NAME_SPMDETAIL_LIST+"("+SPMDETAIL_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +SPMDETAIL_DTL_ROW_ID+" TEXT, "+SPMDETAIL_PAWHS_CODE+" TEXT, "+ SPMDETAIL_ROW_SLNO+" TEXT, "+SPMDETAIL_MAIZE_CODE+" TEXT, "
            +SPMDETAIL_MAIZE_NAME+" TEXT, "+SPMDETAIL_MAIZE_RANGE+" TEXT, "+ SPMDETAIL_MAIZE_INTERVAL+" TEXT, "+SPMDETAIL_MAIZE_UNIT+" TEXT, "
            +SPMDETAIL_MAIZE_STATUS_CODE+" TEXT, "+SPMDETAIL_MODE_FLAG+" TEXT)";

    String create_qtydetails_list="CREATE TABLE "+TABLE_NAME_QTY_DETAIL+"("+QTY_DETAIL_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +QTY_DETAIL_ROW_ID+" TEXT, "+QTY_DETAIL_QTY_CODE+" TEXT, "+ QTY_DETAIL_QTY_NAME+" TEXT, "+QTY_DETAIL_ACTUAL_VALUE+" TEXT, "
            +QTY_DETAIL_WR_QTY_VALUE+" TEXT, "+QTY_DETAIL_MODE_FLAG+" TEXT, "+ QTY_DETAIL_THRESHOLD+" TEXT, "+QTY_DETAIL_UOM+" TEXT, "
            +QTY_STATUS+" TEXT, "+QTY_LOTNO+" TEXT)";

    String create_sidetails_list="CREATE TABLE "+TABLE_NAME_SINO_DETAIL+"("+SINO_DETAIL_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +SINO_DETAIL_ROW_ID+" TEXT, "+SINO_DETAIL_SL_NO+" TEXT, "+ SINO_DETAIL_IN_TEMP1+" TEXT, "+SINO_DETAIL_IN_TEMP2+" TEXT, "
            +SINO_DETAIL_MODE_FLAG+" TEXT, "+SINO_DETAIL_STATUS+" TEXT, "+SINO_DETAIL_LOTNO+" TEXT)";

    String create_otherdetail_list="CREATE TABLE "+TABLE_NAME_OTHER_DETAIL+"("+OTHER_DETAIL_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +OTHER_DETAIL_ROW_ID+" TEXT, "+OTHER_DETAIL_PACKING_COST+" TEXT, "+ OTHER_DETAIL_TRANSPORTATION_COST+" TEXT, "+OTHER_DETAIL_UNPACKING_COST+" TEXT, "
            +OTHER_DETAIL_LOCAL_PACKING_COST+" TEXT, "+OTHER_DETAIL_LOCAL_TRANSPORTING_COST+" TEXT, "+OTHER_DETAIL_TEMP_COST+" TEXT, "+ OTHER_DETAIL_TEMP_COST1+" TEXT, "+OTHER_DETAIL_TEMP_COST2+" TEXT, "
            +OTHER_DETAIL_MODE_FLAG+" TEXT, "+OTHER_DETAIL_STATUS+" TEXT, "+OTHER_DETAIL_LOTNO+" TEXT)";

    String create_actual_proc_save="CREATE TABLE "+TABLE_NAME_ACTUALPROC_SAVE+"("+ACTUALPROC_SAVE_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +ACTUALPROC_SAVE_ROW_ID+" TEXT, "+ACTUALPROC_SAVE_AGG_CODE+" TEXT, "+ACTUALPROC_SAVE_LOTNO+" TEXT, "+ ACTUALPROC_SAVE_FARMER_CODE+" TEXT, "+ACTUALPROC_SAVE_FARMER_NAME+" TEXT, "
            +ACTUALPROC_SAVE_MEMBER+" TEXT, "+ACTUALPROC_SAVE_ITEM_CODE+" TEXT, "+ ACTUALPROC_SAVE_ITEM_NAME+" TEXT, "+ACTUALPROC_SAVE_ACT_QTY+" TEXT, "
            +ACTUALPROC_SAVE_ACT_VALUE+" TEXT, "+ACTUALPROC_SAVE_ACT_PRICE+" TEXT, "+ACTUALPROC_SAVE_ACT_DATE+" TEXT, "
            +ACTUALPROC_SAVE_ADVANCE_AMT+" TEXT, "+ACTUALPROC_SAVE_APPROVE_DATE+" TEXT, "+ACTUALPROC_SAVE_REJECT_DATE+" TEXT, "
            +ACTUALPROC_SAVE_PICKUP_DATE+" TEXT, "+ACTUALPROC_SAVE_WR_DATE+" TEXT, "+ACTUALPROC_SAVE_NO_OF_BAGS+" TEXT, "+ACTUALPROC_SAVE_STATUS+" TEXT, "
            +ACTUALPROC_ACTUAL_REMARKS+" TEXT, "+ACTUALPROC_APPROVE_REMARKS+" TEXT, "+ACTUALPROC_REJECT_REMARKS+" TEXT, "+ACTUALPROC_PICKUP_REMARKS+" TEXT, "
            +ACTUALPROC_WR_REMARKS+" TEXT, "+ACTUALPROC_STATUS_VALUE+" TEXT, "+ACTUALPROC_MODE_FLAG+" TEXT, "+ACTUALPROC_IS_CLOUD+" TEXT, "+ACTUALPROC_ACCEPT_QTY+" TEXT, "+ACTUALPROC_ATTC+" TEXT, "+ACTUALPROC_QCP+" TEXT, "+ACTUALPROC_VNO+" TEXT, "+ACTUALPROC_VTY+" TEXT, "+ACTUALPROC_DSN+" TEXT, "+ACTUALPROC_LRP+" TEXT, "+ACTUALPROC_LRPMN+" TEXT, "+ACTUALPROC_PT+" TEXT, "+ACTUALPROC_BNO+" TEXT, "+ACTUALPROC_CQ+" TEXT)";

    String create_approve_list="CREATE TABLE "+TABLE_NAME_APPROVE_LIST+"("+APPROVE_LIST_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +APPROVE_LIST_ROW_ID+" TEXT, "+APPROVE_LIST_LOT_NO+" TEXT, "+APPROVE_LIST_AGG_CODE+" TEXT, "+ APPROVE_LIST_FARMER_CODE+" TEXT, "+APPROVE_LIST_FARMER_NAME+" TEXT, "
            +APPROVE_LIST_MEMBER+" TEXT, "+APPROVE_LIST_ITEM_CODE+" TEXT, "+ APPROVE_LIST_ITEM_NAME+" TEXT, "+APPROVE_LIST_ACTUAL_QTY+" TEXT, "
            +APPROVE_LIST_ACTUAL_PRICE+" TEXT, "+APPROVE_LIST_ACTUAL_VALUE+" TEXT, "+APPROVE_LIST_ACTUAL_DATE+" TEXT, "
            +APPROVE_LIST_ADVANCE_AMOUNT+" TEXT, "+APPROVE_LIST_APPROVE_DATE+" TEXT, "+APPROVE_LIST_PICKUP_DATE+" TEXT, "
            +APPROVE_LIST_WR_DATE+" TEXT, "+APPROVE_LIST_NO_OF_BAGS+" TEXT, "+APPROVE_LIST_STATUS+" TEXT, "+APPROVE_LIST_ACTUAL_REMARK+" TEXT, "
            +APPROVE_LIST_APPROVE_REMARK+" TEXT, "+APPROVE_LIST_PICKUP_REMARK+" TEXT, "+APPROVE_LIST_WR_REMARK+" TEXT, "+APPROVE_LIST_ATTC+" TEXT, "+APPROVE_LIST_QCP+" TEXT, "+APPROVE_LIST_VNO+" TEXT, "+APPROVE_LIST_VTY+" TEXT, "+APPROVE_LIST_DSN+" TEXT, "+APPROVE_LIST_LRP+" TEXT, "+APPROVE_LIST_LRPMN+" TEXT, "+APPROVE_LIST_PT+" TEXT, "+APPROVE_LIST_BNO+" TEXT, "+APPROVE_LIST_CQ+" TEXT)";

    String create_warehouse_list="CREATE TABLE "+TABLE_NAME_WAREHOUSE_LIST+"("+WAREHOUSE_LIST_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +WAREHOUSE_LIST_ROW_ID+" TEXT, "+WAREHOUSE_LIST_LOT_NO+" TEXT, "+WAREHOUSE_LIST_AGG_CODE+" TEXT, "+ WAREHOUSE_LIST_FARMER_CODE+" TEXT, "+WAREHOUSE_LIST_FARMER_NAME+" TEXT, "
            +WAREHOUSE_LIST_MEMBER+" TEXT, "+WAREHOUSE_LIST_ITEM_CODE+" TEXT, "+ WAREHOUSE_LIST_ITEM_NAME+" TEXT, "+WAREHOUSE_LIST_ACTUAL_QTY+" TEXT, "
            +WAREHOUSE_LIST_ACTUAL_PRICE+" TEXT, "+WAREHOUSE_LIST_ACTUAL_VALUE+" TEXT, "+WAREHOUSE_LIST_ACTUAL_DATE+" TEXT, "
            +WAREHOUSE_LIST_ADVANCE_AMOUNT+" TEXT, "+WAREHOUSE_LIST_APPROVE_DATE+" TEXT, "+WAREHOUSE_LIST_PICKUP_DATE+" TEXT, "
            +WAREHOUSE_LIST_WR_DATE+" TEXT, "+WAREHOUSE_LIST_NO_OF_BAGS+" TEXT, "+WAREHOUSE_LIST_STATUS+" TEXT, "+WAREHOUSE_LIST_ACTUAL_REMARK+" TEXT, "
            +WAREHOUSE_LIST_APPROVE_REMARK+" TEXT, "+WAREHOUSE_LIST_PICKUP_REMARK+" TEXT, "+WAREHOUSE_LIST_WR_REMARK+" TEXT)";

    String create_approve_single_header="CREATE TABLE "+TABLE_NAME_APP_SINGLE_HEADER+"("+APP_SINGLE_HEADER_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +APP_SINGLE_HEADER_ROW_ID+" TEXT, "+APP_SINGLE_HEADER_AGG_CODE+" TEXT, "+ APP_SINGLE_HEADER_LOT_NO+" TEXT, "+APP_SINGLE_HEADER_FARMER_CODE+" TEXT, "
            +APP_SINGLE_HEADER_FARMER_NAME+" TEXT, "+APP_SINGLE_HEADER_MEMBER+" TEXT, "+ APP_SINGLE_HEADER_ITEM_CODE+" TEXT, "+APP_SINGLE_HEADER_ITEM_NAME+" TEXT, "
            +APP_SINGLE_HEADER_ACT_QTY+" TEXT, "+APP_SINGLE_HEADER_ACTUAL_PRICE+" TEXT, "+APP_SINGLE_HEADER_ACTUAL_VALUE+" TEXT, "
            +APP_SINGLE_HEADER_ADVANCE_AMT+" TEXT, "+APP_SINGLE_HEADER_NO_OF_BAGS+" TEXT, "+APP_SINGLE_HEADER_IN_STATUS+" TEXT, "+APP_SINGLE_HEADER_ACTUAL_REMARKS+" TEXT, "
            +APP_SINGLE_HEADER_APPROVE_REMARK+" TEXT, "+APP_SINGLE_HEADER_PICKUP_REMARK+" TEXT, "+APP_SINGLE_HEADER_WR_REMARK+" TEXT, "
            +APP_SINGLE_HEADER_MODE_FLAG+" TEXT, "+APP_SINGLE_HEADER_ROW_TIMESTAMP+" TEXT, "+APP_SINGLE_HEADER_ATTC+" TEXT, "+APP_SINGLE_HEADER_QCP+" TEXT, "+APP_SINGLE_HEADER_VNO+" TEXT, "+APP_SINGLE_HEADER_VTY+" TEXT, "+APP_SINGLE_HEADER_DSN+" TEXT, "+APP_SINGLE_HEADER_LRP+" TEXT, "+APP_SINGLE_HEADER_LRPMN+" TEXT, "+APP_SINGLE_HEADER_PT+" TEXT, "+APP_SINGLE_HEADER_BNO+" TEXT, "+APP_SINGLE_HEADER_CQ+" TEXT)";

    String create_warehouse_single_header="CREATE TABLE "+TABLE_NAME_WR_SINGLE_HEADER+"("+WR_SINGLE_HEADER_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +WR_SINGLE_HEADER_ROW_ID+" TEXT, "+WR_SINGLE_HEADER_AGG_CODE+" TEXT, "+ WR_SINGLE_HEADER_LOT_NO+" TEXT, "+WR_SINGLE_HEADER_FARMER_CODE+" TEXT, "
            +WR_SINGLE_HEADER_FARMER_NAME+" TEXT, "+WR_SINGLE_HEADER_MEMBER+" TEXT, "+ WR_SINGLE_HEADER_ITEM_CODE+" TEXT, "+WR_SINGLE_HEADER_ITEM_NAME+" TEXT, "
            +WR_SINGLE_HEADER_ACT_QTY+" TEXT, "+WR_SINGLE_HEADER_ACTUAL_PRICE+" TEXT, "+WR_SINGLE_HEADER_ACTUAL_VALUE+" TEXT, "
            +WR_SINGLE_HEADER_ADVANCE_AMT+" TEXT, "+WR_SINGLE_HEADER_NO_OF_BAGS+" TEXT, "+WR_SINGLE_HEADER_IN_STATUS+" TEXT, "+WR_SINGLE_HEADER_ACTUAL_REMARKS+" TEXT, "
            +WR_SINGLE_HEADER_APPROVE_REMARK+" TEXT, "+WR_SINGLE_HEADER_PICKUP_REMARK+" TEXT, "+WR_SINGLE_HEADER_WR_REMARK+" TEXT, "
            +WR_SINGLE_HEADER_MODE_FLAG+" TEXT, "+WR_SINGLE_HEADER_ROW_TIMESTAMP+" TEXT)";

    String create_approve_qty_detail="CREATE TABLE "+TABLE_NAME_APP_QTY_DETAIL+"("+APP_QTY_DETAIL_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +APP_QTY_DETAIL_ROW_ID+" TEXT, "+APP_QTY_DETAIL_QTY_CODE+" TEXT, "+ APP_QTY_DETAIL_QTY_NAME+" TEXT, "+APP_QTY_DETAIL_ACTUAL_VALUE+" TEXT, "
            +APP_QTY_DETAIL_WR_QTY_VALUE+" TEXT, "+APP_QTY_DETAIL_MODE_FLAG+" TEXT, "+ APP_QTY_DETAIL_AGG_CODE+" TEXT, "+APP_QTY_DETAIL_LOTNO+" TEXT, "
            +APP_QTY_DETAIL_ITEM_CODE+" TEXT, "+APP_QTY_DETAIL_STATUS+" TEXT)";

    String create_warehouse_qty_detail="CREATE TABLE "+TABLE_NAME_WR_QTY_DETAIL+"("+WR_QTY_DETAIL_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +WR_QTY_DETAIL_ROW_ID+" TEXT, "+WR_QTY_DETAIL_QTY_CODE+" TEXT, "+ WR_QTY_DETAIL_QTY_NAME+" TEXT, "+WR_QTY_DETAIL_ACTUAL_VALUE+" TEXT, "
            +WR_QTY_DETAIL_WR_QTY_VALUE+" TEXT, "+WR_QTY_DETAIL_MODE_FLAG+" TEXT, "+ WR_QTY_DETAIL_AGG_CODE+" TEXT, "+WR_QTY_DETAIL_LOTNO+" TEXT, "
            +WR_QTY_DETAIL_ITEM_CODE+" TEXT, "+WR_QTY_DETAIL_STATUS+" TEXT)";

    String create_approve_sidetails="CREATE TABLE "+TABLE_NAME_APP_SINO_DETAIL+"("+APP_SINO_DETAIL_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +APP_SINO_DETAIL_ROW_ID+" TEXT, "+APP_SINO_DETAIL_SL_NO+" TEXT, "+ APP_SINO_DETAIL_TEMP1+" TEXT, "+APP_SINO_DETAIL_TEMP2+" TEXT, "
            +APP_SINO_DETAIL_MODE_FLAG+" TEXT, "+APP_SINO_DETAIL_AGG_CODE+" TEXT, "+APP_SINO_DETAIL_LOT_NO+" TEXT, "+APP_SINO_DETAIL_STATUS+" TEXT)";

    String create_warehouse_sidetails="CREATE TABLE "+TABLE_NAME_WR_SINO_DETAIL+"("+WR_SINO_DETAIL_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +WR_SINO_DETAIL_ROW_ID+" TEXT, "+WR_SINO_DETAIL_SL_NO+" TEXT, "+ WR_SINO_DETAIL_TEMP1+" TEXT, "+WR_SINO_DETAIL_TEMP2+" TEXT, "
            +WR_SINO_DETAIL_MODE_FLAG+" TEXT, "+WR_SINO_DETAIL_AGG_CODE+" TEXT, "+WR_SINO_DETAIL_LOT_NO+" TEXT, "+WR_SINO_DETAIL_STATUS+" TEXT)";

    String create_approveotherdetail_list="CREATE TABLE "+TABLE_NAME_APP_OTHER_DETAIL+"("+APP_OTHER_DETAIL_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +APP_OTHER_DETAIL_ROW_ID+" TEXT, "+APP_OTHER_DETAIL_PACKING_COST+" TEXT, "+ APP_OTHER_DETAIL_TRANSPORTING_COST+" TEXT, "+APP_OTHER_DETAIL_UNPACKING_COST+" TEXT, "
            +APP_OTHER_DETAIL_LOCAL_PACKING_COST+" TEXT, "+APP_OTHER_DETAIL_LOCAL_TRANSPORTING_COST+" TEXT, "+APP_OTHER_DETAIL_TEMP_COST+" TEXT, "+ APP_OTHER_DETAIL_TEMP_COST1+" TEXT, "+APP_OTHER_DETAIL_TEMP_COST2+" TEXT, "
            +APP_OTHER_DETAIL_MODE_FLAG+" TEXT, "+APP_OTHER_DETAIL_AGG_CODE+" TEXT, "+APP_OTHER_DETAIL_LOTNO+" TEXT, "+APP_OTHER_DETAIL_STATUS+" TEXT)";


    String create_warehouseotherdetail_list="CREATE TABLE "+TABLE_NAME_WR_OTHER_DETAIL+"("+WR_OTHER_DETAIL_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +WR_OTHER_DETAIL_ROW_ID+" TEXT, "+WR_OTHER_DETAIL_PACKING_COST+" TEXT, "+ WR_OTHER_DETAIL_TRANSPORTING_COST+" TEXT, "+WR_OTHER_DETAIL_UNPACKING_COST+" TEXT, "
            +WR_OTHER_DETAIL_LOCAL_PACKING_COST+" TEXT, "+WR_OTHER_DETAIL_LOCAL_TRANSPORTING_COST+" TEXT, "+WR_OTHER_DETAIL_TEMP_COST+" TEXT, "+ WR_OTHER_DETAIL_TEMP_COST1+" TEXT, "+WR_OTHER_DETAIL_TEMP_COST2+" TEXT, "
            +WR_OTHER_DETAIL_MODE_FLAG+" TEXT, "+WR_OTHER_DETAIL_AGG_CODE+" TEXT, "+WR_OTHER_DETAIL_LOTNO+" TEXT, "+WR_OTHER_DETAIL_STATUS+" TEXT)";

    String create_warehouse_receipt_list="CREATE TABLE "+TABLE_NAME_WR_RECEIPT_LIST+"("+WR_RECEIPT_LIST_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +WR_RECEIPT_LIST_ROW_ID+" TEXT, "+WR_RECEIPT_LIST_LOTNO+" TEXT, "+WR_RECEIPT_LIST_WHS_CODE+" TEXT, "
            +WR_RECEIPT_LIST_FARMER_CODE+" TEXT, "+WR_RECEIPT_LIST_FARMER_NAME+" TEXT, "+ WR_RECEIPT_LIST_MEMBER+" TEXT, "+WR_RECEIPT_LIST_ITEM_CODE+" TEXT, "
            +WR_RECEIPT_LIST_ITEM_NAME+" TEXT, "+WR_RECEIPT_LIST_ACCEPT_QTY+" TEXT, "+WR_RECEIPT_LIST_PAYMENT_ADVCNO+" TEXT, "
            +WR_RECEIPT_LIST_ACCEPTED_DATE+" TEXT, "+WR_RECEIPT_LIST_STATUS+" TEXT, "+WR_RECEIPT_LIST_REMARK+" TEXT, "+WR_RECEIPT_LIST_TIMESTAMP+" TEXT)";

    String create_actual_pro_list="CREATE TABLE "+TABLE_NAME_ACTUAL_PRO_LIST+"("+ACTUAL_PRO_LIST_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +ACTUAL_PRO_LIST_ROW_ID+" TEXT, "+ACTUAL_PRO_LIST_AGG_CODE+" TEXT, "+ACTUAL_PRO_LIST_LOTNO+" TEXT, "
            +ACTUAL_PRO_LIST_FARMER_CODE+" TEXT, "+ACTUAL_PRO_LIST_FARMER_NAME+" TEXT, "+ ACTUAL_PRO_LIST_MEMBER+" TEXT, "+ACTUAL_PRO_LIST_ITEM_CODE+" TEXT, "
            +ACTUAL_PRO_LIST_ITEM_NAME+" TEXT, "+ACTUAL_PRO_LIST_STATUS+" TEXT, "+ACTUAL_PRO_LIST_TIMESTAMP+" TEXT)";



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_product_master_allproduct);
        db.execSQL(create_farmer_list);
        db.execSQL(create_submit_rec_est_purchase);
        db.execSQL(create_estimate_list);
        db.execSQL(create_spmdetail_list);
        db.execSQL(create_qtydetails_list);
        db.execSQL(create_sidetails_list);
        db.execSQL(create_otherdetail_list);
        db.execSQL(create_actual_proc_save);
        db.execSQL(create_approve_list);
        db.execSQL(create_warehouse_list);
        db.execSQL(create_approve_single_header);
        db.execSQL(create_warehouse_single_header);
        db.execSQL(create_approve_qty_detail);
        db.execSQL(create_warehouse_qty_detail);
        db.execSQL(create_approve_sidetails);
        db.execSQL(create_warehouse_sidetails);
        db.execSQL(create_approveotherdetail_list);
        db.execSQL(create_warehouseotherdetail_list);
        db.execSQL(create_warehouse_receipt_list);
        db.execSQL(create_actual_pro_list);
        db.execSQL(
                "create table masterl " +
                        "(id integer primary key, out_master_rowid text,out_parent_code text,out_master_code text,out_master_description text,out_depend_code text,out_depend_desc text,out_locallang_flag text,out_status_code text,out_status_desc text)"


        );

        db.execSQL(
                "create table bankm " +
                        "(id integer primary key, bank_rowid text,bank_code text,bank_name text,branch_name text,ifsc_code text,status_desc text)"


        );
        db.execSQL(
                "create table fpoad " +
                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text,v11 text,v12 text,v13 text)"


        );

        db.execSQL(
                "create table farmerh " +
                        "(id integer primary key, name text,sname text,fname text,mobileno text,gender text,dob text,atype text,pincode text,address text,village text,gp text,taluk text,dis text,state text,country text,flag text,isl text,vc text,gc text,tc text,dc text,faid text,hm text,poto text, fpoorgn_code text,fpomember_code text,v8 text,UNIQUE (name, sname, fname, dob))"


        );


        db.execSQL(
                "create table bank " +
                        "(id integer primary key, type text,ano text,bank text,ifsc text,branch text,dc text,dd text,accs text,fid text,flag text,bid text,typec text,bc text, UNIQUE (type, ano, bank, ifsc, branch))"


        );

        db.execSQL(
                "create table vstatus " +
                        "(id integer primary key, v1 text,v2 text,v3 text)"


        );
        db.execSQL(
                "create table vtype " +
                        "(id integer primary key, v1 text,v2 text,v3 text)"


        );
        db.execSQL(
                "create table dstn " +
                        "(id integer primary key, v1 text,v2 text,v3 text)"


        );
        db.execSQL(
                "create table qparest " +
                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text)"


        );
        db.execSQL(
                "create table byr " +
                        "(id integer primary key, v1 text,v2 text,v3 text)"


        );

        db.execSQL(
                "create table sno " +
                        "(id integer primary key, v1 text,v2 text,v3 text, v4 text, v5 text,v6 text)"


        );

        db.execSQL(
                "create table snowithqty " +
                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text, v6 text, v7 text)"


        );

        db.execSQL(
                "create table qpar " +
                        "(id integer primary key, v1 text,v2 text,v3 text)"


        );

        db.execSQL(
                "create table saleentry " +
                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text,v11 text,v12 text,v13 text,v14 text,v15 text,v16 text,v17 text,v18 text,v19 text,v20 text,v21 text)"


        );


        db.execSQL(
                "create table purchaseentry " +
                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text,v11 text,v12 text,v13 text,v14 text,v15 text,v16 text,v17 text,v18 text,v19 text,v20 text,v21 text,v22 test,v23 text,v24 text,v25 text)"


        );

        db.execSQL(
                "create table snope " +
                        "(id integer primary key, v1 text,v2 text,v3 text)"


        );

        db.execSQL(
                "create table qparpe " +
                        "(id integer primary key, v1 text,v2 text,v3 text)"


        );
        db.execSQL(
                "create table qparnew " +
                        "(id integer primary key, v1 text,v2 text,v3 text)"
        );


        Log.i(MyConstants.TAG, "document Table created");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS ";
        db.execSQL(query+TABLE_NAME_PRO_MASTER_ALL_PRODUCT);
        db.execSQL(query+TABLE_NAME_FARMER);
        db.execSQL(query+TABLE_NAME_SUBMIT_REC_EST_PURCHASE);
        db.execSQL(query+TABLE_NAME_ESTIMATE_LIST);
        db.execSQL(query+TABLE_NAME_SPMDETAIL_LIST);
        db.execSQL(query+TABLE_NAME_QTY_DETAIL);
        db.execSQL(query+TABLE_NAME_SINO_DETAIL);
        db.execSQL(query+TABLE_NAME_OTHER_DETAIL);
        db.execSQL(query+TABLE_NAME_ACTUALPROC_SAVE);
        db.execSQL(query+TABLE_NAME_APPROVE_LIST);
        db.execSQL(query+TABLE_NAME_WAREHOUSE_LIST);
        db.execSQL(query+TABLE_NAME_APP_SINGLE_HEADER);
        db.execSQL(query+TABLE_NAME_WR_SINGLE_HEADER);
        db.execSQL(query+TABLE_NAME_APP_QTY_DETAIL);
        db.execSQL(query+TABLE_NAME_WR_QTY_DETAIL);
        db.execSQL(query+TABLE_NAME_APP_SINO_DETAIL);
        db.execSQL(query+TABLE_NAME_WR_SINO_DETAIL);
        db.execSQL(query+TABLE_NAME_APP_OTHER_DETAIL);
        db.execSQL(query+TABLE_NAME_WR_OTHER_DETAIL);
        db.execSQL(query+TABLE_NAME_WR_RECEIPT_LIST);
        db.execSQL(query+TABLE_NAME_ACTUAL_PRO_LIST);
        db.execSQL("DROP TABLE IF EXISTS bankm");
        db.execSQL("DROP TABLE IF EXISTS masterl");
        db.execSQL("DROP TABLE IF EXISTS fpoad");
        db.execSQL("DROP TABLE IF EXISTS farmerlistnew");
        db.execSQL("DROP TABLE IF EXISTS bank");
        db.execSQL("DROP TABLE IF EXISTS vtype");
        db.execSQL("DROP TABLE IF EXISTS vstatus");
        db.execSQL("DROP TABLE IF EXISTS dstn");
        db.execSQL("DROP TABLE IF EXISTS qparest");
        db.execSQL("DROP TABLE IF EXISTS byr");
        db.execSQL("DROP TABLE IF EXISTS saleentry");
        db.execSQL("DROP TABLE IF EXISTS sno");
        db.execSQL("DROP TABLE IF EXISTS snowithqty");
        db.execSQL("DROP TABLE IF EXISTS qpar");
        db.execSQL("DROP TABLE IF EXISTS snope");
        db.execSQL("DROP TABLE IF EXISTS qparpe");
        db.execSQL("DROP TABLE IF EXISTS qparnew");
        onCreate(db);
    }

    public void deleteAllProductMasterAllProduct(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_PRO_MASTER_ALL_PRODUCT);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }
    public boolean insertbank (String n1, String n2, String n3, String n4, String n5, String n6, String n7, String n8, String n9, String n10, int n11, String n12, String n13) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", n1);
        contentValues.put("ano", n2);
        contentValues.put("bank", n3);
        contentValues.put("ifsc", n4);
        contentValues.put("branch", n5);
        contentValues.put("dc", n6);
        contentValues.put("dd", n7);
        contentValues.put("accs", n8);
        contentValues.put("fid", n9);
        contentValues.put("flag", n10);
        contentValues.put("bid", n11);
        contentValues.put("typec", n12);
        contentValues.put("bc", n13);


        db.insertOrThrow("bank", null, contentValues);
        Log.e("Table Bank","Inserted");
        return true;
    }
    public boolean updatebank (Integer id, String n1, String n2, String n3, String n4, String n5, String n6, String n7, String n8, String n9, String n10, int n11, String n12, String n13) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", n1);
        contentValues.put("ano", n2);
        contentValues.put("bank", n3);
        contentValues.put("ifsc", n4);
        contentValues.put("branch", n5);
        contentValues.put("dc", n6);
        contentValues.put("dd", n7);
        contentValues.put("accs", n8);
        contentValues.put("fid", n9);
        contentValues.put("flag", n10);
        contentValues.put("bid", n11);
        contentValues.put("typec", n12);
        contentValues.put("bc", n13);
        db.update("bank", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Bank","Updated");
        return true;
    }
    public boolean insertfarmer (String n1, String n2, String n3, String n4, String n5, String n6, String n7, String n8, String n9, String n10, String n11, String n12, String n13, String n14, String n15, String n16, String n17, String n18, String n19, String n20, String n21, String n22, String n23, String n24, String n25, String n26, String n27) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", n1);
        contentValues.put("sname", n2);
        contentValues.put("fname", n3);
        contentValues.put("mobileno", n4);
        contentValues.put("gender", n5);
        contentValues.put("dob", n6);
        contentValues.put("atype", n7);
        contentValues.put("pincode", n8);
        contentValues.put("address", n9);
        contentValues.put("village", n10);
        contentValues.put("gp", n11);
        contentValues.put("taluk", n12);
        contentValues.put("dis", n13);
        contentValues.put("state", n14);
        contentValues.put("country", n15);
        contentValues.put("flag", n16);
        contentValues.put("isl", n17);
        contentValues.put("vc", n18);
        contentValues.put("gc", n19);
        contentValues.put("tc", n20);
        contentValues.put("dc", n21);
        contentValues.put("faid",n22);
        contentValues.put("hm",n23);
        contentValues.put("poto",n24);
        contentValues.put("fpoorgn_code",n25);
        contentValues.put("fpomember_code",n26);
        contentValues.put("v8",n27);

        db.insertOrThrow("farmerh", null, contentValues);
        Log.e("Table Farmer","Inserted");
        return true;
    }

    public boolean insertaddress (String n1, String n2, String n3, String n4, String n5, String n6, String n7, String n8, String n9, String n10, String n11, String n12, String n13) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);
        contentValues.put("v4", n4);
        contentValues.put("v5", n5);
        contentValues.put("v6", n6);
        contentValues.put("v7", n7);
        contentValues.put("v8", n8);
        contentValues.put("v9", n9);
        contentValues.put("v10", n10);
        contentValues.put("v11", n11);
        contentValues.put("v12", n12);
        contentValues.put("v13", n13);


        db.insert("fpoad", null, contentValues);
        Log.e("Table FPOAD","Inserted");
        return true;
    }

    public boolean insertvtype (String n1, String n2, String n3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);



        db.insert("vtype", null, contentValues);
        Log.e("Table VTYPE","Inserted");
        return true;
    }

    public boolean insertvstatus (String n1, String n2, String n3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);



        db.insert("vstatus", null, contentValues);
        Log.e("Table VSTATUS","Inserted");
        return true;
    }

    public boolean insertdstn (String n1, String n2, String n3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);



        db.insert("dstn", null, contentValues);
        Log.e("Table DSTN","Inserted");
        return true;
    }

    public boolean insertbyr (String n1, String n2, String n3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);



        db.insert("byr", null, contentValues);
        Log.e("Table DSTN","Inserted");
        return true;
    }
    public boolean insertsno (String n1, String n2, String n3, String n4, String n5, String n6) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);
        contentValues.put("v4", n4);  //product code
        contentValues.put("v5", n5);  //is Server
        contentValues.put("v6", n6);  //is qty

        db.insert("sno", null, contentValues);
        Log.e("Table SNO","Inserted");
        return true;
    }

    public boolean insertsnowithqty (String n1, String n2, String n3, String n4, String n5, String n6, String n7) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);  //serial number
        contentValues.put("v2", n2);  //Qty
        contentValues.put("v3", n3);  //ts
        contentValues.put("v4",n4);   //loose bag flag
        contentValues.put("v5",n5);   //flag
        contentValues.put("v6",n6);   //product code
        contentValues.put("v7",n7);   //is server


        db.insert("snowithqty", null, contentValues);
        Log.e("Table SNOwithaty","Inserted");
        return true;
    }

    public boolean insertqpar (String n1, String n2, String n3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);



        db.insert("qpar", null, contentValues);
        Log.e("Table QPAR","Inserted");
        return true;
    }

    public boolean insertsnope (String n1, String n2, String n3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);



        db.insert("snope", null, contentValues);
        Log.e("Table SNO","Inserted");
        return true;
    }

    public boolean insertqparpe (String n1, String n2, String n3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);



        db.insert("qparpe", null, contentValues);
        Log.e("Table QPAR","Inserted");
        return true;
    }

    public boolean insertqparnew (String n1, String n2, String n3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);
        db.insert("qparnew", null, contentValues);
        Log.e("Table QPARNEW","Inserted");
        return true;
    }

    public boolean insertqparest (String n1, String n2, String n3, String n4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);
        contentValues.put("v4", n4);



        db.insert("qparest", null, contentValues);
        Log.e("Table QPAREST","Inserted");
        return true;
    }

    public boolean insertsaleentry (String n1, String n2, String n3, String n4, String n5, String n6, String n7, String n8, String n9, String n10, String n11, String n12, String n13, String n14, String n15, String n16, String n17, String n18, String n19, String n20,String n21) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);
        contentValues.put("v4", n4);
        contentValues.put("v5", n5);
        contentValues.put("v6", n6);
        contentValues.put("v7", n7);
        contentValues.put("v8", n8);
        contentValues.put("v9", n9);
        contentValues.put("v10", n10);
        contentValues.put("v11", n11);
        contentValues.put("v12", n12);
        contentValues.put("v13", n13);
        contentValues.put("v14", n14);
        contentValues.put("v15", n15);
        contentValues.put("v16", n16);
        contentValues.put("v17", n17);
        contentValues.put("v18", n18);
        contentValues.put("v19", n19);
        contentValues.put("v20", n20);
        contentValues.put("v21", n21);



        db.insert("saleentry", null, contentValues);
        Log.e("Table Sale Entry","Inserted");
        return true;
    }
    public boolean updatesaleentry (Integer id, String n1, String n2, String n3, String n4, String n5, String n6, String n7, String n8, String n9, String n10, String n11, String n12, String n13, String n14, String n15, String n16, String n17, String n18, String n19, String n20,String n21) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);
        contentValues.put("v4", n4);
        contentValues.put("v5", n5);
        contentValues.put("v6", n6);
        contentValues.put("v7", n7);
        contentValues.put("v8", n8);
        contentValues.put("v9", n9);
        contentValues.put("v10", n10);
        contentValues.put("v11", n11);
        contentValues.put("v12", n12);
        contentValues.put("v13", n13);
        contentValues.put("v14", n14);
        contentValues.put("v15", n15);
        contentValues.put("v16", n16);
        contentValues.put("v17", n17);
        contentValues.put("v18", n18);
        contentValues.put("v19", n19);
        contentValues.put("v20", n20);
        contentValues.put("v21", n21);


        db.update("saleentry", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Sale Entry","Updated");
        return true;
    }
    public boolean insertpurchaseentry (String n1, String n2, String n3, String n4, String n5, String n6, String n7, String n8, String n9, String n10, String n11, String n12, String n13, String n14, String n15, String n16, String n17, String n18, String n19, String n20, String n21, String n22, String n23, String n24, String n25) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);
        contentValues.put("v4", n4);
        contentValues.put("v5", n5);
        contentValues.put("v6", n6);
        contentValues.put("v7", n7);
        contentValues.put("v8", n8);
        contentValues.put("v9", n9);
        contentValues.put("v10", n10);
        contentValues.put("v11", n11);
        contentValues.put("v12", n12);
        contentValues.put("v13", n13);
        contentValues.put("v14", n14);
        contentValues.put("v15", n15);
        contentValues.put("v16", n16);
        contentValues.put("v17", n17);
        contentValues.put("v18", n18);
        contentValues.put("v19", n19);
        contentValues.put("v20", n20);
        contentValues.put("v21", n21);
        contentValues.put("v22", n22);
        contentValues.put("v23", n23);
        contentValues.put("v24", n24);
        contentValues.put("v25", n25);



        db.insert("purchaseentry", null, contentValues);
        Log.e("Table Purchase Entry","Inserted");
        return true;
    }

    public boolean updatepurchaseentry (Integer id, String n1, String n2, String n3, String n4, String n5, String n6, String n7, String n8, String n9, String n10, String n11, String n12, String n13, String n14, String n15, String n16, String n17, String n18, String n19, String n20, String n21, String n22, String n23, String n24, String n25) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);
        contentValues.put("v4", n4);
        contentValues.put("v5", n5);
        contentValues.put("v6", n6);
        contentValues.put("v7", n7);
        contentValues.put("v8", n8);
        contentValues.put("v9", n9);
        contentValues.put("v10", n10);
        contentValues.put("v11", n11);
        contentValues.put("v12", n12);
        contentValues.put("v13", n13);
        contentValues.put("v14", n14);
        contentValues.put("v15", n15);
        contentValues.put("v16", n16);
        contentValues.put("v17", n17);
        contentValues.put("v18", n18);
        contentValues.put("v19", n19);
        contentValues.put("v20", n20);
        contentValues.put("v21", n21);
        contentValues.put("v22", n22);
        contentValues.put("v23", n23);
        contentValues.put("v24", n24);
        contentValues.put("v25", n25);


        db.update("purchaseentry", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Purchase Entry","Updated");
        return true;
    }
    public boolean insertmasterl(String n1, String n2, String n3, String n4, String n5, String n6, String n7, String n8, String n9) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("out_master_rowid", n1);
        contentValues.put("out_parent_code", n2);
        contentValues.put("out_master_code", n3);
        contentValues.put("out_master_description", n4);
        contentValues.put("out_depend_code", n5);
        contentValues.put("out_depend_desc", n6);
        contentValues.put("out_locallang_flag", n7);
        contentValues.put("out_status_code", n8);
        contentValues.put("out_status_desc", n9);





        db.insert("masterl", null, contentValues);
        Log.e("Table masterl","Inserted");
        return true;
    }
    public boolean insertbankm (String n1, String n2, String n3, String n4, String n5, String n6) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("bank_rowid", n1);
        contentValues.put("bank_code", n2);
        contentValues.put("bank_name", n3);
        contentValues.put("branch_name", n4);
        contentValues.put("ifsc_code", n5);
        contentValues.put("status_desc", n6);





        db.insert("bankm", null, contentValues);
        Log.e("Table bankm","Inserted");
        return true;
    }
    public void addAllProductMasterAllProduct(PmListDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PMAP_OUT_PRODUCT_ROW_ID, data.getOut_product_rowid());
        values.put(PMAP_OUT_PAWHS_CODE, data.getOut_pawhs_code());
        values.put(PMAP_OUT_AGG_CODE, data.getOut_agg_code());
        values.put(PMAP_OUT_PRODUCT_CODE, data.getOut_product_code());
        values.put(PMAP_OUT_PRODUCT_NAME, data.getOut_product_name());
        values.put(PMAP_OUT_PRODUCT_CATEGORY, data.getOut_product_category());
        values.put(PMAP_OUT_PRODUCT_SUBCATEGORY, data.getOut_product_subcategory());
        values.put(PMAP_OUT_HSN_CODE, data.getOut_hsn_code());
        values.put(PMAP_OUT_HSN_DESC, data.getOut_hsn_desctiption());
        values.put(PMAP_OUT_STATUS_CODE, data.getOut_status_code());
        values.put(PMAP_OUT_STATUS_DESC, data.getOut_status_desc());
        values.put(PMAP_OUT_ROW_TIMESTAMP, data.getOut_row_timestamp());
        values.put(out_uomtype_code,data.getOut_uomtype_code());
        values.put(out_crop_variety,data.getOut_crop_variety());
        values.put(Out_value_withtax,data.getOut_value_withtax());

        // insert
        db.insert(TABLE_NAME_PRO_MASTER_ALL_PRODUCT,null, values);
        db.close();
//        Log.i(Myconstants.TAG,"breakdown Inserted successfully"+ values.toString());
    }

    public List<String> getProductNameAndCode() {
        List<String> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_PRO_MASTER_ALL_PRODUCT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        FormerDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                if (players.contains(cursor.getString(5))) {

                } else {
                    players.add(cursor.getString(5));
                }
            } while (cursor.moveToNext());
        }
        return players;
    }
    public List<String> getProductNameAndCode2() {
        List<String> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_PRO_MASTER_ALL_PRODUCT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        FormerDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                if (players.contains(cursor.getString(6))) {

                } else {
                    players.add(cursor.getString(6));
                }
            } while (cursor.moveToNext());
        }
        return players;
    }
    public List<String> getProductNameAndCode3() {
        List<String> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_PRO_MASTER_ALL_PRODUCT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        FormerDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                if (players.contains(cursor.getString(14))) {

                } else {
                    players.add(cursor.getString(14));
                }
            } while (cursor.moveToNext());
        }
        return players;
    }
    public List<PmListDao> getProductMasterAllProductDetails(String selectproductName) {
        List<PmListDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_PRO_MASTER_ALL_PRODUCT +" where "+ PMAP_OUT_PRODUCT_NAME + " like '%"+selectproductName+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        PmListDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new PmListDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setOut_product_rowid(cursor.getString(1));
                data.setOut_pawhs_code(cursor.getString(2));
                data.setOut_agg_code(cursor.getString(3));
                data.setOut_product_code(cursor.getString(4));
                data.setOut_product_name(cursor.getString(5));
                data.setOut_product_category(cursor.getString(6));
                data.setOut_product_subcategory(cursor.getString(7));
                data.setOut_hsn_code(cursor.getString(8));
                data.setOut_hsn_desctiption(cursor.getString(9));
                data.setOut_status_code(cursor.getString(10));
                data.setOut_status_desc(cursor.getString(11));
                data.setOut_row_timestamp(cursor.getString(12));
                data.setOut_uomtype_code(cursor.getString(13));
                data.setOut_crop_variety(cursor.getString(14));
                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }
    public List<PmListDao> getProductMasterAllProductDetails2(String selectproductName) {
        List<PmListDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_PRO_MASTER_ALL_PRODUCT +" where "+ PMAP_OUT_PRODUCT_CATEGORY + " like '%"+selectproductName+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        PmListDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new PmListDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setOut_product_rowid(cursor.getString(1));
                data.setOut_pawhs_code(cursor.getString(2));
                data.setOut_agg_code(cursor.getString(3));
                data.setOut_product_code(cursor.getString(4));
                data.setOut_product_name(cursor.getString(5));
                data.setOut_product_category(cursor.getString(6));
                data.setOut_product_subcategory(cursor.getString(7));
                data.setOut_hsn_code(cursor.getString(8));
                data.setOut_hsn_desctiption(cursor.getString(9));
                data.setOut_status_code(cursor.getString(10));
                data.setOut_status_desc(cursor.getString(11));
                data.setOut_row_timestamp(cursor.getString(12));
                data.setOut_uomtype_code(cursor.getString(13));
                data.setOut_crop_variety(cursor.getString(14));
                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }
    public List<PmListDao> getProductMasterAllProductDetails3(String selectproductName) {
        List<PmListDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_PRO_MASTER_ALL_PRODUCT +" where "+ out_crop_variety + " like '%"+selectproductName+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        PmListDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new PmListDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setOut_product_rowid(cursor.getString(1));
                data.setOut_pawhs_code(cursor.getString(2));
                data.setOut_agg_code(cursor.getString(3));
                data.setOut_product_code(cursor.getString(4));
                data.setOut_product_name(cursor.getString(5));
                data.setOut_product_category(cursor.getString(6));
                data.setOut_product_subcategory(cursor.getString(7));
                data.setOut_hsn_code(cursor.getString(8));
                data.setOut_hsn_desctiption(cursor.getString(9));
                data.setOut_status_code(cursor.getString(10));
                data.setOut_status_desc(cursor.getString(11));
                data.setOut_row_timestamp(cursor.getString(12));
                data.setOut_uomtype_code(cursor.getString(13));
                data.setOut_crop_variety(cursor.getString(14));
                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }
    public List<PmListDao> getCodeRelatedProductMasterAllProductDetails(String itemcode) {
        List<PmListDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_PRO_MASTER_ALL_PRODUCT +" where "+ PMAP_OUT_PRODUCT_CODE + " like '%"+itemcode+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        PmListDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new PmListDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setOut_product_rowid(cursor.getString(1));
                data.setOut_pawhs_code(cursor.getString(2));
                data.setOut_agg_code(cursor.getString(3));
                data.setOut_product_code(cursor.getString(4));
                data.setOut_product_name(cursor.getString(5));
                data.setOut_product_category(cursor.getString(6));
                data.setOut_product_subcategory(cursor.getString(7));
                data.setOut_hsn_code(cursor.getString(8));
                data.setOut_hsn_desctiption(cursor.getString(9));
                data.setOut_status_code(cursor.getString(10));
                data.setOut_status_desc(cursor.getString(11));
                data.setOut_row_timestamp(cursor.getString(12));
                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }

    public List<PmListDao> getOfflineMasterAllProductDetails() {
        List<PmListDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_PRO_MASTER_ALL_PRODUCT;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        PmListDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new PmListDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setOut_product_rowid(cursor.getString(1));
                data.setOut_pawhs_code(cursor.getString(2));
                data.setOut_agg_code(cursor.getString(3));
                data.setOut_product_code(cursor.getString(4));
                data.setOut_product_name(cursor.getString(5));
                data.setOut_product_category(cursor.getString(6));
                data.setOut_product_subcategory(cursor.getString(7));
                data.setOut_hsn_code(cursor.getString(8));
                data.setOut_hsn_desctiption(cursor.getString(9));
                data.setOut_status_code(cursor.getString(10));
                data.setOut_status_desc(cursor.getString(11));
                data.setOut_row_timestamp(cursor.getString(12));
                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }




    //FarmerDetails

    public void addAllFarmerDetails(FormerDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FARMER_ROW_ID, data.getFarmer_rowid());
        values.put(FARMER_CODE, data.getFarmer_code());
        values.put(FARMER_FARMER, data.getFarmer());
        values.put(FARMER_FHW_NAME, data.getFhw_name());
        values.put(FARMER_NAME, data.getFarmer_name());
        values.put(FARMER_DIST, data.getFarmer_dist());
        values.put(FARMER_DIST_DESC, data.getFarmer_dist_desc());
        values.put(FARMER_TALUK, data.getFarmer_taluk());
        values.put(FARMER_TALUK_DESC, data.getFarmer_taluk_desc());
        values.put(FARMER_PANCHAYAT, data.getFarmer_panchayat());
        values.put(FARMER_PANCHAYAT_DESC, data.getFarmer_panchayat_desc());
        values.put(FARMER_VILLAGE, data.getFarmer_village());
        values.put(FARMER_VILLAGE_DESC, data.getFarmer_village_desc());
        // insert
        db.insert(TABLE_NAME_FARMER,null, values);
        db.close();
      //  Log.i(MyConstants.TAG,"Farmer Inserted successfully"+ values.toString());
    }
    public boolean updatefarmer(String fc,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FARMER_ROW_ID, n1);
        contentValues.put(FARMER_CODE, n2);
        contentValues.put(FARMER_FHW_NAME, n3);
        contentValues.put(FARMER_NAME, n4);
        contentValues.put(FARMER_DIST, n5);
        contentValues.put(FARMER_DIST_DESC, n6);
        contentValues.put(FARMER_TALUK, n7);
        contentValues.put(FARMER_TALUK_DESC, n8);
        contentValues.put(FARMER_PANCHAYAT, n9);
        contentValues.put(FARMER_PANCHAYAT_DESC, n10);
        contentValues.put(FARMER_VILLAGE,n11);
        contentValues.put(FARMER_VILLAGE_DESC, n12);
        database.update(TABLE_NAME_FARMER, contentValues, "FarmerCode=?", new String[] {fc});
        database.close();
        return true;
    }
    public void deleteFarmerList(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_FARMER);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public List<FormerDao> getAllFarmerDataDetails(String selectedFormerCode, String farmer_code) {
        List<FormerDao> players = new ArrayList<>();
        String query="";
        if(farmer_code.equalsIgnoreCase("FARMER_CODE")){
             query = "SELECT * FROM " + TABLE_NAME_FARMER +" where "+ FARMER_CODE + " like '%"+selectedFormerCode+"%'";
        }else {
             query = "SELECT * FROM " + TABLE_NAME_FARMER +" where "+ FARMER_NAME + " like '%"+selectedFormerCode+"%'";
        }

        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        FormerDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new FormerDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setFarmer_rowid(cursor.getString(1));
                data.setFarmer_code(cursor.getString(2));
                data.setFarmer(cursor.getString(3));
                data.setFhw_name(cursor.getString(4));
                data.setFarmer_name(cursor.getString(5));
                data.setFarmer_dist(cursor.getString(6));
                data.setFarmer_dist_desc(cursor.getString(7));
                data.setFarmer_taluk(cursor.getString(8));
                data.setFarmer_taluk_desc(cursor.getString(9));
                data.setFarmer_panchayat(cursor.getString(10));
                data.setFarmer_panchayat_desc(cursor.getString(11));
                data.setFarmer_village(cursor.getString(12));
                data.setFarmer_village_desc(cursor.getString(13));
                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }

    public List<String> getFarmerCode() {
        List<String> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_FARMER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        FormerDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                if (players.contains(cursor.getString(2))) {

                } else {
                    players.add(cursor.getString(2));
                }
            } while (cursor.moveToNext());
        }
        return players;
    }

    public List<String> getFarmerName() {
        List<String> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_FARMER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        FormerDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                if (players.contains(cursor.getString(5))) {

                } else {
                    players.add(cursor.getString(5));
                }
            } while (cursor.moveToNext());
        }
        return players;
    }

    //SubmitRecEstPurchase
    public int addAllSubmitRecEstPurChase(SubmitRecEstPurchaseDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SUBMIT_REC_EST_PURCHASE_FARMER_CODE, data.getFarmerCode());
        values.put(SUBMIT_REC_EST_PURCHASE_FARMER_NAME, data.getFarmerName());
        values.put(SUBMIT_REC_EST_PURCHASE_FARMER_MEMBER, data.getFarmerMember());
        values.put(SUBMIT_REC_EST_PURCHASE_ITEM_CODE, data.getItemCode());
        values.put(SUBMIT_REC_EST_PURCHASE_ITEM_NAME, data.getItemName());
        values.put(SUBMIT_REC_EST_PURCHASE_QUANTITY, data.getQty());
        values.put(SUBMIT_REC_EST_PURCHASE_PRICE, data.getPrice());
        values.put(SUBMIT_REC_EST_PURCHASE_VALUE, data.getValue());
        values.put(SUBMIT_REC_EST_PURCHASE_DATE, data.getPickupdate());
        values.put(SUBMIT_REC_EST_PURCHASE_REMARKS, data.getRemarks());
        values.put(SUBMIT_REC_EST_PURCHASE_LOTNO, data.getLotNo());
        values.put(SUBMIT_REC_EST_PURCHASE_EST_ROWID, data.getEstiRowId());
        values.put(SUBMIT_REC_EST_PURCHASE_ROW_TIME_STAMP, data.getRowTimeStamp());
        values.put(SUBMIT_REC_EST_PURCHASE_MODE_FLAG, data.getMode_Flag());
        values.put(SUBMIT_REC_EST_PURCHASE_IS_CLOUD,data.getIsCloud());
        values.put(SUBMIT_REC_EST_PURCHASE_ATTC,data.getIn_Estimated_attach());
        values.put(SUBMIT_REC_EST_PURCHASE_VS,data.getVs());
        values.put(SUBMIT_REC_EST_PURCHASE_QP,data.getQp());
        values.put(SUBMIT_REC_EST_PURCHASE_RV,data.getRv());
        values.put(SUBMIT_REC_EST_PURCHASE_LRP,data.getLrp());
        values.put(SUBMIT_REC_EST_PURCHASE_LRPMN,data.getLotmn());
        // insert
        db.insert(TABLE_NAME_SUBMIT_REC_EST_PURCHASE,null, values);

        String queryLastRowInserted = "select last_insert_rowid()";

        final Cursor cursor = db.rawQuery(queryLastRowInserted, null);
        int _idLastInsertedRow = 0;
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    _idLastInsertedRow = cursor.getInt(0);
                }
            } finally {
                cursor.close();
            }
        }
      //  db.close();

        Log.i(MyConstants.TAG,"Submit Record Est Inserted successfully"+ values.toString());
        return _idLastInsertedRow;
    }

    public void addAllSubmitRecEstPurChaseOffline(SubmitRecEstPurchaseDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SUBMIT_REC_EST_PURCHASE_FARMER_CODE, data.getFarmerCode());
        values.put(SUBMIT_REC_EST_PURCHASE_FARMER_NAME, data.getFarmerName());
        values.put(SUBMIT_REC_EST_PURCHASE_FARMER_MEMBER, data.getFarmerMember());
        values.put(SUBMIT_REC_EST_PURCHASE_ITEM_CODE, data.getItemCode());
        values.put(SUBMIT_REC_EST_PURCHASE_ITEM_NAME, data.getItemName());
        values.put(SUBMIT_REC_EST_PURCHASE_QUANTITY, data.getQty());
        values.put(SUBMIT_REC_EST_PURCHASE_PRICE, data.getPrice());
        values.put(SUBMIT_REC_EST_PURCHASE_VALUE, data.getValue());
        values.put(SUBMIT_REC_EST_PURCHASE_DATE, data.getPickupdate());
        values.put(SUBMIT_REC_EST_PURCHASE_REMARKS, data.getRemarks());
        values.put(SUBMIT_REC_EST_PURCHASE_LOTNO, data.getLotNo());
        values.put(SUBMIT_REC_EST_PURCHASE_EST_ROWID, data.getEstiRowId());
        values.put(SUBMIT_REC_EST_PURCHASE_ROW_TIME_STAMP, data.getRowTimeStamp());
        values.put(SUBMIT_REC_EST_PURCHASE_MODE_FLAG, data.getMode_Flag());
        values.put(SUBMIT_REC_EST_PURCHASE_IS_CLOUD,data.getIsCloud());
        values.put(SUBMIT_REC_EST_PURCHASE_ATTC,data.getIn_Estimated_attach());
        values.put(SUBMIT_REC_EST_PURCHASE_VS,data.getVs());
        values.put(SUBMIT_REC_EST_PURCHASE_QP,data.getQp());
        values.put(SUBMIT_REC_EST_PURCHASE_RV,data.getRv());
        values.put(SUBMIT_REC_EST_PURCHASE_LRP,data.getLrp());
        values.put(SUBMIT_REC_EST_PURCHASE_LRPMN,data.getLotmn());
        // insert
        db.insert(TABLE_NAME_SUBMIT_REC_EST_PURCHASE,null, values);
        db.close();

        Log.i(MyConstants.TAG,"Submit Record Est Offline Inserted successfully"+ values.toString());
    }

    public void updateAllSubmitRecEstPurChaseOffline(SubmitRecEstPurchaseDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SUBMIT_REC_EST_PURCHASE_FARMER_CODE, data.getFarmerCode());
        values.put(SUBMIT_REC_EST_PURCHASE_FARMER_NAME, data.getFarmerName());
        values.put(SUBMIT_REC_EST_PURCHASE_FARMER_MEMBER, data.getFarmerMember());
        values.put(SUBMIT_REC_EST_PURCHASE_ITEM_CODE, data.getItemCode());
        values.put(SUBMIT_REC_EST_PURCHASE_ITEM_NAME, data.getItemName());
        values.put(SUBMIT_REC_EST_PURCHASE_QUANTITY, data.getQty());
        values.put(SUBMIT_REC_EST_PURCHASE_PRICE, data.getPrice());
        values.put(SUBMIT_REC_EST_PURCHASE_VALUE, data.getValue());
        values.put(SUBMIT_REC_EST_PURCHASE_DATE, data.getPickupdate());
        values.put(SUBMIT_REC_EST_PURCHASE_REMARKS, data.getRemarks());
        values.put(SUBMIT_REC_EST_PURCHASE_LOTNO, data.getLotNo());
        values.put(SUBMIT_REC_EST_PURCHASE_EST_ROWID, data.getEstiRowId());
        values.put(SUBMIT_REC_EST_PURCHASE_ROW_TIME_STAMP, data.getRowTimeStamp());
        values.put(SUBMIT_REC_EST_PURCHASE_MODE_FLAG, data.getMode_Flag());
        values.put(SUBMIT_REC_EST_PURCHASE_IS_CLOUD,data.getIsCloud());
        values.put(SUBMIT_REC_EST_PURCHASE_ATTC,data.getIn_Estimated_attach());
        values.put(SUBMIT_REC_EST_PURCHASE_VS,data.getVs());
        values.put(SUBMIT_REC_EST_PURCHASE_QP,data.getQp());
        values.put(SUBMIT_REC_EST_PURCHASE_RV,data.getRv());
        values.put(SUBMIT_REC_EST_PURCHASE_LRP,data.getLrp());
        values.put(SUBMIT_REC_EST_PURCHASE_LRPMN,data.getLotmn());
        // insert
        db.update(TABLE_NAME_SUBMIT_REC_EST_PURCHASE,values, "SubmitRecEstPurchaseKeyId = ? ",new String[]{Integer.toString(data.getId())});
        db.close();

        Log.i(MyConstants.TAG,"Submit Record Est Offline Inserted successfully"+ values.toString());
    }

    public List<SubmitRecEstPurchaseDao> getAllSubmitRecEstPurchaseDao(String currentDate) {
        List<SubmitRecEstPurchaseDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_SUBMIT_REC_EST_PURCHASE +" where "+ SUBMIT_REC_EST_PURCHASE_DATE + " like '%"+currentDate+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        SubmitRecEstPurchaseDao data = null;

        players.clear();
        if (cursor.moveToLast()) {
            do {
                data= new SubmitRecEstPurchaseDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setFarmerCode(cursor.getString(1));
                data.setFarmerName(cursor.getString(2));
                data.setFarmerMember(cursor.getString(3));
                data.setItemCode(cursor.getString(4));
                data.setItemName(cursor.getString(5));
                data.setQty(cursor.getString(6));
                data.setPrice(cursor.getString(7));
                data.setValue(cursor.getString(8));
                data.setPickupdate(cursor.getString(9));
                data.setRemarks(cursor.getString(10));
                data.setLotNo(cursor.getString(11));
                data.setEstiRowId(cursor.getString(12));
                data.setRowTimeStamp(cursor.getString(13));
                data.setMode_Flag(cursor.getString(14));
                data.setIn_Estimated_attach(cursor.getString(0));
                data.setVs(cursor.getString(17));
                data.setQp(cursor.getString(18));
                data.setRv(cursor.getString(19));
                data.setLrp(cursor.getString(20));
                data.setLotmn(cursor.getString(21));
                players.add(data);
            } while (cursor.moveToPrevious());
        }
        return players;
    }

    public List<SubmitRecEstPurchaseDao> getAllSubmitRecEstPurchaseDaoForValidate() {
        List<SubmitRecEstPurchaseDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_SUBMIT_REC_EST_PURCHASE;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        SubmitRecEstPurchaseDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new SubmitRecEstPurchaseDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setFarmerCode(cursor.getString(1));
                data.setFarmerName(cursor.getString(2));
                data.setFarmerMember(cursor.getString(3));
                data.setItemCode(cursor.getString(4));
                data.setItemName(cursor.getString(5));
                data.setQty(cursor.getString(6));
                data.setPrice(cursor.getString(7));
                data.setValue(cursor.getString(8));
                data.setPickupdate(cursor.getString(9));
                data.setRemarks(cursor.getString(10));
                data.setLotNo(cursor.getString(11));
                data.setEstiRowId(cursor.getString(12));
                data.setRowTimeStamp(cursor.getString(13));
                data.setMode_Flag(cursor.getString(14));
                data.setIn_Estimated_attach(cursor.getString(0));
                data.setVs(cursor.getString(17));
                data.setQp(cursor.getString(18));
                data.setRv(cursor.getString(19));
                data.setLrp(cursor.getString(20));
                data.setLotmn(cursor.getString(21));
                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }

    public void deleteLotNoFromSubmitRecEstPurchaseDao(String lotno) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_SUBMIT_REC_EST_PURCHASE + " where "+ SUBMIT_REC_EST_PURCHASE_LOTNO+ "="+ "'"+lotno+"'");
            String query="DELETE FROM " + TABLE_NAME_SUBMIT_REC_EST_PURCHASE + " where "+ SUBMIT_REC_EST_PURCHASE_LOTNO+ "="+ "'"+lotno+"'";
            Log.e(MyConstants.TAG,query);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public List<SubmitRecEstPurchaseDao> getAllOfflineSubmitRecEstPurchaseDao() {
        List<SubmitRecEstPurchaseDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_SUBMIT_REC_EST_PURCHASE +" where "+ SUBMIT_REC_EST_PURCHASE_IS_CLOUD+ "="+"'No'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        SubmitRecEstPurchaseDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new SubmitRecEstPurchaseDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setFarmerCode(cursor.getString(1));
                data.setFarmerName(cursor.getString(2));
                data.setFarmerMember(cursor.getString(3));
                data.setItemCode(cursor.getString(4));
                data.setItemName(cursor.getString(5));
                data.setQty(cursor.getString(6));
                data.setPrice(cursor.getString(7));
                data.setValue(cursor.getString(8));
                data.setPickupdate(cursor.getString(9));
                data.setRemarks(cursor.getString(10));
                data.setLotNo(cursor.getString(11));
                data.setEstiRowId(cursor.getString(12));
                data.setRowTimeStamp(cursor.getString(13));
                data.setMode_Flag(cursor.getString(14));
                data.setIn_Estimated_attach(cursor.getString(0));
                data.setVs(cursor.getString(17));
                data.setQp(cursor.getString(18));
                data.setRv(cursor.getString(19));
                data.setLrp(cursor.getString(20));
                data.setLotmn(cursor.getString(21));
                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }

    public void deleteSubmitRecEstPurchase(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_SUBMIT_REC_EST_PURCHASE);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    //updateLotNO
    public void updateLotNO(int lastInsertedRowId, String lotNo, String yes){
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("UPDATE " + TABLE_NAME_SUBMIT_REC_EST_PURCHASE+" SET " + SUBMIT_REC_EST_PURCHASE_LOTNO + " = "+"'"+lotNo+"'"+"," + SUBMIT_REC_EST_PURCHASE_IS_CLOUD + " = "+"'"+yes+"'"+" WHERE " + SUBMIT_REC_EST_PURCHASE_KEY_ID + " = " + lastInsertedRowId);
            String updateQuery="UPDATE " + TABLE_NAME_SUBMIT_REC_EST_PURCHASE+" SET " + SUBMIT_REC_EST_PURCHASE_LOTNO + " = "+"'"+lotNo+"'"+"," + SUBMIT_REC_EST_PURCHASE_IS_CLOUD + " = "+"'"+yes+"'"+" WHERE " + SUBMIT_REC_EST_PURCHASE_KEY_ID + " = " + lastInsertedRowId;
            Log.i(MyConstants.TAG,updateQuery);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    //EstimateList
    public void addAllEstimateListDetails(EstimateListDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ESTIMATELIST_ROW_ID, data.getOut_Estm_rowid());
        values.put(ESTIMATELIST_LOTNO, data.getOut_LotNo());
        values.put(ESTIMATELIST_AGG_CODE, data.getOut_agg_code());
        values.put(ESTIMATELIST_FARMER_CODE, data.getOut_Farmer_Code());
        values.put(ESTIMATELIST_FARMER_NAME, data.getOut_Farmer_Name());
        values.put(ESTIMATELIST_MEMBER, data.getOut_Member_Type());
        values.put(ESTIMATELIST_ITEM_CODE, data.getOut_Item_Code());
        values.put(ESTIMATELIST_ITEM_NAME, data.getOut_Item_Name());
        values.put(ESTIMATELIST_QTY, data.getOut_Estimated_Qty());
        values.put(ESTIMATELIST_PRICE, data.getOut_Estimated_Price());
        values.put(ESTIMATELIST_VALUE, data.getOut_Estimated_Value());
        values.put(ESTIMATELIST_REMARK, data.getOut_Remarks());
        values.put(ESTIMATELIST_STATUS, data.getOut_Status());
        values.put(ESTIMATELIST_PICKUPDATE, data.getOut_Estimated_PickupDate());
        values.put(ESTIMATELIST_ALREADY_TAKEN, data.getAlreadyTaken());
        // insert
        db.insert(TABLE_NAME_ESTIMATE_LIST,null, values);
        db.close();
        Log.i(MyConstants.TAG,"Estimate Inserted successfully"+ values.toString());
    }

    public List<EstimateListDao> getAllEstimateList() {
        List<EstimateListDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_ESTIMATE_LIST +" where "+ ESTIMATELIST_ALREADY_TAKEN+ "="+"'No'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        EstimateListDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new EstimateListDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setOut_Estm_rowid(Integer.parseInt(cursor.getString(1)));
                data.setOut_LotNo(cursor.getString(2));
                data.setOut_agg_code(cursor.getString(3));
                data.setOut_Farmer_Code(cursor.getString(4));
                data.setOut_Farmer_Name(cursor.getString(5));
                data.setOut_Member_Type(cursor.getString(6));
                data.setOut_Item_Code(cursor.getString(7));
                data.setOut_Item_Name(cursor.getString(8));
                data.setOut_Estimated_Qty(cursor.getString(9));
                data.setOut_Estimated_Price(cursor.getString(10));
                data.setOut_Estimated_Value(cursor.getString(11));
                data.setOut_Remarks(cursor.getString(12));
                data.setOut_Status(cursor.getString(13));
                data.setOut_Estimated_PickupDate(cursor.getString(14));
                data.setAlreadyTaken(cursor.getString(15));

                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }

    public void updateLotNOAlreadyTaken(String lotNo, String yes){
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("UPDATE " + TABLE_NAME_ESTIMATE_LIST+" SET "  + ESTIMATELIST_ALREADY_TAKEN + " = "+"'"+yes+"'"+" WHERE " + ESTIMATELIST_LOTNO + " = " + "'"+lotNo+"'");
            String updateQuery="UPDATE " + TABLE_NAME_ESTIMATE_LIST+" SET "  + ESTIMATELIST_ALREADY_TAKEN + " = "+"'"+yes+"'"+" WHERE " + ESTIMATELIST_LOTNO + " = " + "'"+lotNo+"'";
            Log.i(MyConstants.TAG,updateQuery);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public List<EstimateListDao> getParticularLotnoDetails(String lotno, String frm) {
        List<EstimateListDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_ESTIMATE_LIST +" where "+ ESTIMATELIST_FARMER_NAME + " like '%"+lotno+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        EstimateListDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new EstimateListDao();
                if(frm.equalsIgnoreCase("Actual"))
                {
                    frm = "Estimate";
                }
                if(cursor.getString(13).equalsIgnoreCase(frm)) {
                    data.setId(Integer.parseInt(cursor.getString(0)));
                    data.setOut_Estm_rowid(Integer.parseInt(cursor.getString(1)));
                    data.setOut_LotNo(cursor.getString(2));
                    data.setOut_agg_code(cursor.getString(3));
                    data.setOut_Farmer_Code(cursor.getString(4));
                    data.setOut_Farmer_Name(cursor.getString(5));
                    data.setOut_Member_Type(cursor.getString(6));
                    data.setOut_Item_Code(cursor.getString(7));
                    data.setOut_Item_Name(cursor.getString(8));
                    data.setOut_Estimated_Qty(cursor.getString(9));
                    data.setOut_Estimated_Price(cursor.getString(10));
                    data.setOut_Estimated_Value(cursor.getString(11));
                    data.setOut_Remarks(cursor.getString(12));
                    data.setOut_Status(cursor.getString(13));
                    data.setOut_Estimated_PickupDate(cursor.getString(14));
                    data.setAlreadyTaken(cursor.getString(15));
                    players.add(data);
                }
            } while (cursor.moveToNext());
        }
        return players;
    }



    public void addAllSpmDetailsList(SpmDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SPMDETAIL_DTL_ROW_ID, data.getIn_product_dtl_rowid());
        values.put(SPMDETAIL_PAWHS_CODE, data.getIn_pawhs_code());
        values.put(SPMDETAIL_ROW_SLNO, data.getIn_row_slno());
        values.put(SPMDETAIL_MAIZE_CODE, data.getIn_maize_code());
        values.put(SPMDETAIL_MAIZE_NAME, data.getIn_maize_name());
        values.put(SPMDETAIL_MAIZE_RANGE, data.getIn_range());
        values.put(SPMDETAIL_MAIZE_INTERVAL, data.getIn_maize_interval());
        values.put(SPMDETAIL_MAIZE_UNIT, data.getIn_maize_unit());
        values.put(SPMDETAIL_MAIZE_STATUS_CODE, data.getIn_status_code());
        values.put(SPMDETAIL_MODE_FLAG, data.getIn_mode_flag());
        // insert
        db.insert(TABLE_NAME_SPMDETAIL_LIST,null, values);
        db.close();
        Log.i(MyConstants.TAG,"SpmDetaails Inserted successfully"+ values.toString());
    }

    public List<SpmDetailDao> getSpmDetailsListRelatedPawhsCode(String pawhsCode) {
        List<SpmDetailDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_SPMDETAIL_LIST +" where "+ SPMDETAIL_PAWHS_CODE + " like '%"+pawhsCode+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        SpmDetailDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new SpmDetailDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setIn_product_dtl_rowid(Integer.parseInt(cursor.getString(1)));
                data.setIn_pawhs_code(cursor.getString(2));
                data.setIn_row_slno(Integer.parseInt(cursor.getString(3)));
                data.setIn_maize_code(cursor.getString(4));
                data.setIn_maize_name(cursor.getString(5));
                data.setIn_range(cursor.getString(6));
                data.setIn_maize_interval(cursor.getString(7));
                data.setIn_maize_unit(cursor.getString(8));
                data.setIn_status_code(cursor.getString(9));
                data.setIn_mode_flag(cursor.getString(10));
                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }

    public void deleteSpmDetailsList(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_SPMDETAIL_LIST);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void deleteEstimateList(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_ESTIMATE_LIST);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }


    public void addAllQtyDetailsList(PostQtyDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QTY_DETAIL_ROW_ID, data.getIn_qty_dtl_rowid());
        values.put(QTY_DETAIL_QTY_CODE, data.getIn_qty_code());
        values.put(QTY_DETAIL_QTY_NAME, data.getIn_qty_name());
        values.put(QTY_DETAIL_ACTUAL_VALUE, data.getIn_actual_value());
        values.put(QTY_DETAIL_WR_QTY_VALUE, data.getIn_wr_qty_value());
        values.put(QTY_DETAIL_MODE_FLAG, data.getIn_mode_flag());
        values.put(QTY_DETAIL_THRESHOLD, data.getThresHoldValue());
        values.put(QTY_DETAIL_UOM, data.getUom());
        values.put(QTY_STATUS, data.getStatusValue());
        values.put(QTY_LOTNO, data.getLotno());
        // insert
        db.insert(TABLE_NAME_QTY_DETAIL,null, values);
        db.close();
        Log.i(MyConstants.TAG,"QtyDetails Inserted successfully"+ values.toString());
    }

    public void updateAllQtyDetailsList(PostQtyDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QTY_DETAIL_ROW_ID, data.getIn_qty_dtl_rowid());
        values.put(QTY_DETAIL_QTY_CODE, data.getIn_qty_code());
        values.put(QTY_DETAIL_QTY_NAME, data.getIn_qty_name());
        values.put(QTY_DETAIL_ACTUAL_VALUE, data.getIn_actual_value());
        values.put(QTY_DETAIL_WR_QTY_VALUE, data.getIn_wr_qty_value());
        values.put(QTY_DETAIL_MODE_FLAG, data.getIn_mode_flag());
        values.put(QTY_DETAIL_THRESHOLD, data.getThresHoldValue());
        values.put(QTY_DETAIL_UOM, data.getUom());
        values.put(QTY_STATUS, data.getStatusValue());
        values.put(QTY_LOTNO, data.getLotno());
        // update
        db.update(TABLE_NAME_QTY_DETAIL,values, "QtyDetailListKeyId = ? ",new String[]{Integer.toString(data.getId())});
        db.close();

        Log.i(MyConstants.TAG,"AllQtyDetailsList Offline Updated successfully"+ values.toString());
    }

    public void deleteQtyDetails(Context context, String statusValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_QTY_DETAIL +" where "+ QTY_STATUS + " like '%"+statusValue+"%'");
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public List<PostQtyDetailDao> getOfflineQtyDetails() {
        List<PostQtyDetailDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_QTY_DETAIL;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        PostQtyDetailDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new PostQtyDetailDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setIn_qty_dtl_rowid(Integer.parseInt(cursor.getString(1)));
                data.setIn_qty_code(cursor.getString(2));
                data.setIn_qty_name(cursor.getString(3));
                data.setIn_actual_value(Integer.parseInt(cursor.getString(4)));
                data.setIn_wr_qty_value(Integer.parseInt(cursor.getString(5)));
                data.setIn_mode_flag(cursor.getString(6));
                data.setThresHoldValue(cursor.getString(7));
                data.setUom(cursor.getString(8));
                data.setStatusValue(cursor.getString(9));
                data.setLotno(cursor.getString(10));
                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }

    public void addAllSiDetailsList(PostSiNoDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SINO_DETAIL_ROW_ID, data.getIn_slno_row_id());
        values.put(SINO_DETAIL_SL_NO, data.getIn_slno());
        values.put(SINO_DETAIL_IN_TEMP1, data.getIn_temp1());
        values.put(SINO_DETAIL_IN_TEMP2, data.getIn_temp2());
        values.put(SINO_DETAIL_MODE_FLAG, data.getIn_mode_flag());
        values.put(SINO_DETAIL_STATUS, data.getStatusValue());
        values.put(SINO_DETAIL_LOTNO, data.getLotno());
        // insert
        db.insert(TABLE_NAME_SINO_DETAIL,null, values);
        db.close();
        Log.i(MyConstants.TAG,"SiDetails Inserted successfully"+ values.toString());
    }

    public void updateAllSiDetailsList(PostSiNoDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SINO_DETAIL_ROW_ID, data.getIn_slno_row_id());
        values.put(SINO_DETAIL_SL_NO, data.getIn_slno());
        values.put(SINO_DETAIL_IN_TEMP1, data.getIn_temp1());
        values.put(SINO_DETAIL_IN_TEMP2, data.getIn_temp2());
        values.put(SINO_DETAIL_MODE_FLAG, data.getIn_mode_flag());
        values.put(SINO_DETAIL_STATUS, data.getStatusValue());
        values.put(SINO_DETAIL_LOTNO, data.getLotno());
        // update
        db.update(TABLE_NAME_SINO_DETAIL,values, "SiNoDetailListKeyId = ? ",new String[]{Integer.toString(data.getId())});
        db.close();

        Log.i(MyConstants.TAG,"AllSiDetailsList Offline Updated successfully"+ values.toString());
    }

    public void deleteSiNoDetails(Context context, String statusValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_SINO_DETAIL +" where "+ SINO_DETAIL_STATUS + " like '%"+statusValue+"%'");
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public List<PostSiNoDetailDao> getOfflineSiNoDetails() {
        List<PostSiNoDetailDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_SINO_DETAIL;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        PostSiNoDetailDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new PostSiNoDetailDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setIn_slno_row_id(Integer.parseInt(cursor.getString(1)));
                data.setIn_slno(cursor.getString(2));
                data.setIn_temp1(cursor.getString(3));
                data.setIn_temp2(cursor.getString(4));
                data.setIn_mode_flag(cursor.getString(5));
                data.setStatusValue(cursor.getString(6));
                data.setLotno(cursor.getString(7));
                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }


    public void addAllOtherDetailsList(PostOtherDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OTHER_DETAIL_ROW_ID, data.getIn_otherdtl_row_id());
        values.put(OTHER_DETAIL_PACKING_COST, data.getIn_packaging_cost());
        values.put(OTHER_DETAIL_TRANSPORTATION_COST, data.getIn_transporting_cost());
        values.put(OTHER_DETAIL_UNPACKING_COST, data.getIn_unpacking_cost());
        values.put(OTHER_DETAIL_LOCAL_PACKING_COST, data.getIn_local_packaging_cost());
        values.put(OTHER_DETAIL_LOCAL_TRANSPORTING_COST, data.getIn_local_transporting_cost());
        values.put(OTHER_DETAIL_TEMP_COST, data.getIn_temp_cost());
        values.put(OTHER_DETAIL_TEMP_COST1, data.getIn_temp_cost1());
        values.put(OTHER_DETAIL_TEMP_COST2, data.getIn_temp_cost2());
        values.put(OTHER_DETAIL_MODE_FLAG, data.getIn_mode_flag());
        values.put(OTHER_DETAIL_STATUS, data.getStatusValue());
        values.put(OTHER_DETAIL_LOTNO, data.getLotno());
        // insert
        db.insert(TABLE_NAME_OTHER_DETAIL,null, values);
        db.close();
        Log.i(MyConstants.TAG,"OtherDetaails Inserted successfully"+ values.toString());
    }

    public void updateAllOtherDetailsList(PostOtherDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OTHER_DETAIL_ROW_ID, data.getIn_otherdtl_row_id());
        values.put(OTHER_DETAIL_PACKING_COST, data.getIn_packaging_cost());
        values.put(OTHER_DETAIL_TRANSPORTATION_COST, data.getIn_transporting_cost());
        values.put(OTHER_DETAIL_UNPACKING_COST, data.getIn_unpacking_cost());
        values.put(OTHER_DETAIL_LOCAL_PACKING_COST, data.getIn_local_packaging_cost());
        values.put(OTHER_DETAIL_LOCAL_TRANSPORTING_COST, data.getIn_local_transporting_cost());
        values.put(OTHER_DETAIL_TEMP_COST, data.getIn_temp_cost());
        values.put(OTHER_DETAIL_TEMP_COST1, data.getIn_temp_cost1());
        values.put(OTHER_DETAIL_TEMP_COST2, data.getIn_temp_cost2());
        values.put(OTHER_DETAIL_MODE_FLAG, data.getIn_mode_flag());
        values.put(OTHER_DETAIL_STATUS, data.getStatusValue());
        values.put(OTHER_DETAIL_LOTNO, data.getLotno());
        // update
        db.update(TABLE_NAME_OTHER_DETAIL,values, "OtherDetailListKeyId = ? ",new String[]{Integer.toString(data.getId())});
        db.close();

        Log.i(MyConstants.TAG,"OtherDetails Offline Updated successfully"+ values.toString());
    }

    public void deleteOtherDetails(Context context, String statusValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_OTHER_DETAIL +" where "+ OTHER_DETAIL_STATUS + " like '%"+statusValue+"%'");
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public List<EstimateListDao> getParticularLotnoDetails2(String lotno, String frm) {
        List<EstimateListDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_ESTIMATE_LIST +" where "+ ESTIMATELIST_ITEM_NAME + " like '%"+lotno+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        EstimateListDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new EstimateListDao();
                if(frm.equalsIgnoreCase("Actual"))
                {
                    frm = "Estimate";
                }
                if(cursor.getString(13).equalsIgnoreCase(frm)) {
                    data.setId(Integer.parseInt(cursor.getString(0)));
                    data.setOut_Estm_rowid(Integer.parseInt(cursor.getString(1)));
                    data.setOut_LotNo(cursor.getString(2));
                    data.setOut_agg_code(cursor.getString(3));
                    data.setOut_Farmer_Code(cursor.getString(4));
                    data.setOut_Farmer_Name(cursor.getString(5));
                    data.setOut_Member_Type(cursor.getString(6));
                    data.setOut_Item_Code(cursor.getString(7));
                    data.setOut_Item_Name(cursor.getString(8));
                    data.setOut_Estimated_Qty(cursor.getString(9));
                    data.setOut_Estimated_Price(cursor.getString(10));
                    data.setOut_Estimated_Value(cursor.getString(11));
                    data.setOut_Remarks(cursor.getString(12));
                    data.setOut_Status(cursor.getString(13));
                    data.setOut_Estimated_PickupDate(cursor.getString(14));
                    data.setAlreadyTaken(cursor.getString(15));
                    players.add(data);
                }
            } while (cursor.moveToNext());
        }
        return players;
    }
    public List<EstimateListDao> getParticularLotnoDetails3(String lotno, String frm) {
        List<EstimateListDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_ESTIMATE_LIST +" where "+ ESTIMATELIST_PICKUPDATE + " like '%"+lotno+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        EstimateListDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new EstimateListDao();
                if(frm.equalsIgnoreCase("Actual"))
                {
                    frm = "Estimate";
                }
                if(cursor.getString(13).equalsIgnoreCase(frm)) {
                    data.setId(Integer.parseInt(cursor.getString(0)));
                    data.setOut_Estm_rowid(Integer.parseInt(cursor.getString(1)));
                    data.setOut_LotNo(cursor.getString(2));
                    data.setOut_agg_code(cursor.getString(3));
                    data.setOut_Farmer_Code(cursor.getString(4));
                    data.setOut_Farmer_Name(cursor.getString(5));
                    data.setOut_Member_Type(cursor.getString(6));
                    data.setOut_Item_Code(cursor.getString(7));
                    data.setOut_Item_Name(cursor.getString(8));
                    data.setOut_Estimated_Qty(cursor.getString(9));
                    data.setOut_Estimated_Price(cursor.getString(10));
                    data.setOut_Estimated_Value(cursor.getString(11));
                    data.setOut_Remarks(cursor.getString(12));
                    data.setOut_Status(cursor.getString(13));
                    data.setOut_Estimated_PickupDate(cursor.getString(14));
                    data.setAlreadyTaken(cursor.getString(15));
                    players.add(data);
                }
            } while (cursor.moveToNext());
        }
        return players;
    }
    public List<EstimateListDao> getParticularLotnoDetails4() {
        List<EstimateListDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_ESTIMATE_LIST;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        EstimateListDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new EstimateListDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setOut_Estm_rowid(Integer.parseInt(cursor.getString(1)));
                data.setOut_LotNo(cursor.getString(2));
                data.setOut_agg_code(cursor.getString(3));
                data.setOut_Farmer_Code(cursor.getString(4));
                data.setOut_Farmer_Name(cursor.getString(5));
                data.setOut_Member_Type(cursor.getString(6));
                data.setOut_Item_Code(cursor.getString(7));
                data.setOut_Item_Name(cursor.getString(8));
                data.setOut_Estimated_Qty(cursor.getString(9));
                data.setOut_Estimated_Price(cursor.getString(10));
                data.setOut_Estimated_Value(cursor.getString(11));
                data.setOut_Remarks(cursor.getString(12));
                data.setOut_Status(cursor.getString(13));
                data.setOut_Estimated_PickupDate(cursor.getString(14));
                data.setAlreadyTaken(cursor.getString(15));
                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }


    public List<PostOtherDetailDao> getOfflineOtherDetails() {
        List<PostOtherDetailDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_OTHER_DETAIL;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        PostOtherDetailDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new PostOtherDetailDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setIn_otherdtl_row_id(Integer.parseInt(cursor.getString(1)));
                data.setIn_packaging_cost(Integer.parseInt(cursor.getString(2)));
                data.setIn_transporting_cost(Integer.parseInt(cursor.getString(3)));
                data.setIn_unpacking_cost(Integer.parseInt(cursor.getString(4)));
                data.setIn_local_packaging_cost(Integer.parseInt(cursor.getString(5)));
                data.setIn_local_transporting_cost(Integer.parseInt(cursor.getString(6)));
                data.setIn_temp_cost(Integer.parseInt(cursor.getString(7)));
                data.setIn_temp_cost1(Integer.parseInt(cursor.getString(8)));
                data.setIn_temp_cost2(Integer.parseInt(cursor.getString(9)));
                data.setIn_mode_flag(cursor.getString(10));
                data.setStatusValue(cursor.getString(11));
                data.setLotno(cursor.getString(12));
                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }


    public void addAllActualProcSave(PawhsActualProcSaveDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACTUALPROC_SAVE_ROW_ID, data.getAct_row_id());
        values.put(ACTUALPROC_SAVE_AGG_CODE, data.getAggCode());
        values.put(ACTUALPROC_SAVE_LOTNO, data.getLotNo());
        values.put(ACTUALPROC_SAVE_FARMER_CODE, data.getFarmerCode());
        values.put(ACTUALPROC_SAVE_FARMER_NAME, data.getFarmerName());
        values.put(ACTUALPROC_SAVE_MEMBER, data.getMemberType());
        values.put(ACTUALPROC_SAVE_ITEM_CODE, data.getItemCode());
        values.put(ACTUALPROC_SAVE_ITEM_NAME, data.getItemName());
        values.put(ACTUALPROC_SAVE_ACT_QTY, data.getActualqty());
        values.put(ACTUALPROC_SAVE_ACT_VALUE, data.getActualPrice());
        values.put(ACTUALPROC_SAVE_ACT_PRICE, data.getActualValue());
        values.put(ACTUALPROC_SAVE_ACT_DATE, data.getActualDate());
        values.put(ACTUALPROC_SAVE_ADVANCE_AMT, data.getAdvanceAmount());
        values.put(ACTUALPROC_SAVE_APPROVE_DATE, data.getApproveDate());
        values.put(ACTUALPROC_SAVE_REJECT_DATE, data.getRejectDate());
        values.put(ACTUALPROC_SAVE_PICKUP_DATE, data.getPickupDate());
        values.put(ACTUALPROC_SAVE_WR_DATE, data.getWrDate());
        values.put(ACTUALPROC_SAVE_NO_OF_BAGS,data.getNoofBags());
        values.put(ACTUALPROC_SAVE_STATUS,data.getStatus());
        values.put(ACTUALPROC_ACTUAL_REMARKS,data.getActualRemarks());
        values.put(ACTUALPROC_APPROVE_REMARKS,data.getApproveRemarks());
        values.put(ACTUALPROC_REJECT_REMARKS,data.getRejectRemarks());
        values.put(ACTUALPROC_PICKUP_REMARKS,data.getPickUpRemarks());
        values.put(ACTUALPROC_WR_REMARKS,data.getWrRemarks());
        values.put(ACTUALPROC_STATUS_VALUE,data.getStatusValue());
        values.put(ACTUALPROC_MODE_FLAG,data.getModeFlag());
        values.put(ACTUALPROC_IS_CLOUD,data.getIsCloud());
        values.put(ACTUALPROC_ACCEPT_QTY,data.getAcceptQty());
        values.put(ACTUALPROC_ATTC,data.getIn_actual_attach());
        values.put(ACTUALPROC_QCP,data.getIn_qcperson_name());
        values.put(ACTUALPROC_VNO,data.getIn_vehicle_no());
        values.put(ACTUALPROC_VTY,data.getIn_vehicle_type());
        values.put(ACTUALPROC_DSN,data.getIn_destination());
        values.put(ACTUALPROC_LRP,data.getIn_LRP_Name());
        values.put(ACTUALPROC_LRPMN,data.getIn_LRP_Mobile_no());
        values.put(ACTUALPROC_PT,data.getIn_Payment_type());
        values.put(ACTUALPROC_BNO,data.getIn_Bank_acc_no());
        values.put(ACTUALPROC_CQ,data.getIn_cheque_no());


        // insert
        db.insert(TABLE_NAME_ACTUALPROC_SAVE,null, values);
        db.close();


        Log.i(MyConstants.TAG,"ActualProcSave Inserted successfully"+ values.toString());
    }

    public void updateAllAllActualProcSave(PawhsActualProcSaveDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACTUALPROC_SAVE_ROW_ID, data.getAct_row_id());
        values.put(ACTUALPROC_SAVE_AGG_CODE, data.getAggCode());
        values.put(ACTUALPROC_SAVE_LOTNO, data.getLotNo());
        values.put(ACTUALPROC_SAVE_FARMER_CODE, data.getFarmerCode());
        values.put(ACTUALPROC_SAVE_FARMER_NAME, data.getFarmerName());
        values.put(ACTUALPROC_SAVE_MEMBER, data.getMemberType());
        values.put(ACTUALPROC_SAVE_ITEM_CODE, data.getItemCode());
        values.put(ACTUALPROC_SAVE_ITEM_NAME, data.getItemName());
        values.put(ACTUALPROC_SAVE_ACT_QTY, data.getActualqty());
        values.put(ACTUALPROC_SAVE_ACT_VALUE, data.getActualPrice());
        values.put(ACTUALPROC_SAVE_ACT_PRICE, data.getActualValue());
        values.put(ACTUALPROC_SAVE_ACT_DATE, data.getActualDate());
        values.put(ACTUALPROC_SAVE_ADVANCE_AMT, data.getAdvanceAmount());
        values.put(ACTUALPROC_SAVE_APPROVE_DATE, data.getApproveDate());
        values.put(ACTUALPROC_SAVE_REJECT_DATE, data.getRejectDate());
        values.put(ACTUALPROC_SAVE_PICKUP_DATE, data.getPickupDate());
        values.put(ACTUALPROC_SAVE_WR_DATE, data.getWrDate());
        values.put(ACTUALPROC_SAVE_NO_OF_BAGS,data.getNoofBags());
        values.put(ACTUALPROC_SAVE_STATUS,data.getStatus());
        values.put(ACTUALPROC_ACTUAL_REMARKS,data.getActualRemarks());
        values.put(ACTUALPROC_APPROVE_REMARKS,data.getApproveRemarks());
        values.put(ACTUALPROC_REJECT_REMARKS,data.getRejectRemarks());
        values.put(ACTUALPROC_PICKUP_REMARKS,data.getPickUpRemarks());
        values.put(ACTUALPROC_WR_REMARKS,data.getWrRemarks());
        values.put(ACTUALPROC_STATUS_VALUE,data.getStatusValue());
        values.put(ACTUALPROC_MODE_FLAG,data.getModeFlag());
        values.put(ACTUALPROC_IS_CLOUD,data.getIsCloud());
        values.put(ACTUALPROC_ACCEPT_QTY,data.getAcceptQty());
        values.put(ACTUALPROC_ATTC,data.getIn_actual_attach());
        values.put(ACTUALPROC_QCP,data.getIn_qcperson_name());
        values.put(ACTUALPROC_VNO,data.getIn_vehicle_no());
        values.put(ACTUALPROC_VTY,data.getIn_vehicle_type());
        values.put(ACTUALPROC_DSN,data.getIn_destination());
        values.put(ACTUALPROC_LRP,data.getIn_LRP_Name());
        values.put(ACTUALPROC_LRPMN,data.getIn_LRP_Mobile_no());
        values.put(ACTUALPROC_PT,data.getIn_Payment_type());
        values.put(ACTUALPROC_BNO,data.getIn_Bank_acc_no());
        values.put(ACTUALPROC_CQ,data.getIn_cheque_no());
        // update
        db.update(TABLE_NAME_ACTUALPROC_SAVE,values, "ActualProcSaveKeyId = ? ",new String[]{Integer.toString(data.getId())});
        db.close();

        Log.i(MyConstants.TAG,"AllActualProcSave Offline Updated successfully"+ values.toString());
    }


    public void deletePawhsActualProcSaveDaoDetails(Context context, String statusValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_ACTUALPROC_SAVE +" where "+ ACTUALPROC_SAVE_STATUS + " like '%"+statusValue+"%'");
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public List<PawhsActualProcSaveDao> getOfflinePawhsActualProcSaveDaoDetails(String statusValue) {
        List<PawhsActualProcSaveDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_ACTUALPROC_SAVE +" where "+ ACTUALPROC_SAVE_STATUS + " like '%"+statusValue+"%'" + " AND "+ ACTUALPROC_IS_CLOUD+ "="+"'No'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        PawhsActualProcSaveDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new PawhsActualProcSaveDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setAct_row_id(Integer.parseInt(cursor.getString(1)));
                data.setAggCode(cursor.getString(2));
                data.setLotNo(cursor.getString(3));
                data.setFarmerCode(cursor.getString(4));
                data.setFarmerName(cursor.getString(5));
                data.setMemberType(cursor.getString(6));
                data.setItemCode(cursor.getString(7));
                data.setItemName(cursor.getString(8));
                data.setActualqty(cursor.getString(9));
                data.setActualPrice(cursor.getString(10));
                data.setActualValue(cursor.getString(11));
                data.setActualDate(cursor.getString(12));
                data.setAdvanceAmount(cursor.getString(13));
                data.setApproveDate(cursor.getString(14));
                data.setRejectDate(cursor.getString(15));
                data.setPickupDate(cursor.getString(16));
                data.setWrDate(cursor.getString(17));
                data.setNoofBags(cursor.getString(18));
                data.setStatus(cursor.getString(19));
                data.setActualRemarks(cursor.getString(20));
                data.setApproveRemarks(cursor.getString(21));
                data.setRejectRemarks(cursor.getString(22));
                data.setPickUpRemarks(cursor.getString(23));
                data.setWrRemarks(cursor.getString(24));
                data.setStatusValue(cursor.getString(25));
                data.setModeFlag(cursor.getString(26));
                data.setIsCloud(cursor.getString(27));
                data.setAcceptQty(cursor.getString(28));


                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }



    public void addAllApproveListDetails(ActualListDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(APPROVE_LIST_ROW_ID, data.getOut_act_rowid());
        values.put(APPROVE_LIST_LOT_NO, data.getOut_lotno());
        values.put(APPROVE_LIST_AGG_CODE, data.getOut_agg_code());
        values.put(APPROVE_LIST_FARMER_CODE, data.getOut_farmer_code());
        values.put(APPROVE_LIST_FARMER_NAME, data.getOut_farmer_name());
        values.put(APPROVE_LIST_MEMBER, data.getOut_member_type());
        values.put(APPROVE_LIST_ITEM_CODE, data.getOut_item_code());
        values.put(APPROVE_LIST_ITEM_NAME, data.getOut_item_name());
        values.put(APPROVE_LIST_ACTUAL_QTY, data.getOut_actual_qty());
        values.put(APPROVE_LIST_ACTUAL_PRICE, data.getOut_actual_price());
        values.put(APPROVE_LIST_ACTUAL_VALUE, data.getOut_actual_value());
        values.put(APPROVE_LIST_ACTUAL_DATE, data.getOut_actual_date());
        values.put(APPROVE_LIST_ADVANCE_AMOUNT, data.getOut_advance_amt());
        values.put(APPROVE_LIST_APPROVE_DATE, data.getOut_approve_date());
        values.put(APPROVE_LIST_PICKUP_DATE, data.getOut_pickup_date());
        values.put(APPROVE_LIST_WR_DATE, data.getOut_wr_date());
        values.put(APPROVE_LIST_NO_OF_BAGS, data.getOut_no_of_bags());
        values.put(APPROVE_LIST_STATUS,data.getOut_status());
        values.put(APPROVE_LIST_ACTUAL_REMARK,data.getOut_actual_remarks());
        values.put(APPROVE_LIST_APPROVE_REMARK,data.getOut_approved_remarks());
        values.put(APPROVE_LIST_PICKUP_REMARK,data.getOut_pickup_remarks());
        values.put(APPROVE_LIST_WR_REMARK,data.getOut_wr_remarks());
        values.put(APPROVE_LIST_ATTC,data.getIn_actual_attach());
        values.put(APPROVE_LIST_QCP,data.getIn_qcperson_name());
        values.put(APPROVE_LIST_VNO,data.getIn_vehicle_no());
        values.put(APPROVE_LIST_VTY,data.getIn_vehicle_type());
        values.put(APPROVE_LIST_DSN,data.getIn_destination());
        values.put(APPROVE_LIST_LRP,data.getIn_LRP_Name());
        values.put(APPROVE_LIST_LRPMN,data.getIn_LRP_Mobile_no());
        values.put(APPROVE_LIST_PT,data.getIn_Payment_type());
        values.put(APPROVE_LIST_BNO,data.getIn_Bank_acc_no());
        values.put(APPROVE_LIST_CQ,data.getIn_cheque_no());
        // insert
        db.insert(TABLE_NAME_APPROVE_LIST,null, values);
        db.close();


        Log.i(MyConstants.TAG,"Approve Inserted successfully"+ values.toString());
    }

    public void deleteApproveList(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_APPROVE_LIST);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public List<ActualListDao> getOfflineApproveListDetails() {
        List<ActualListDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_APPROVE_LIST;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ActualListDao data = null;

        players.clear();
        try {
            if (cursor.moveToFirst()) {
                do {
                    if(cursor.getString(18).equalsIgnoreCase("Approve")||cursor.getString(18).equalsIgnoreCase("Reject"))
                    {

                    }
                    else
                    {
                        Log.e("CHKSTA",""+cursor.getString(18)+"//"+ Integer.parseInt(cursor.getString(1)));
                        data = new ActualListDao();
                        data.setId(Integer.parseInt(cursor.getString(0)));
                        data.setOut_act_rowid(Integer.parseInt(cursor.getString(1)));
                        data.setOut_lotno(cursor.getString(2));
                        data.setOut_agg_code(cursor.getString(3));
                        data.setOut_farmer_code(cursor.getString(4));
                        data.setOut_farmer_name(cursor.getString(5));
                        data.setOut_member_type(cursor.getString(6));
                        data.setOut_item_code(cursor.getString(7));
                        data.setOut_item_name(cursor.getString(8));
                        data.setOut_actual_qty(Double.parseDouble(cursor.getString(9)));
                        data.setOut_actual_price(Double.parseDouble(cursor.getString(10)));
                        data.setOut_actual_value(Double.parseDouble(cursor.getString(11)));
                        data.setOut_actual_date(cursor.getString(12));
                        data.setOut_advance_amt(Double.parseDouble(cursor.getString(13)));
                        data.setOut_approve_date(cursor.getString(14));
                        data.setOut_pickup_date(cursor.getString(15));
                        data.setOut_wr_date(cursor.getString(16));
                        data.setOut_no_of_bags(cursor.getString(17));
                        data.setOut_status(cursor.getString(18));
                        data.setOut_actual_remarks(cursor.getString(19));
                        data.setOut_approved_remarks(cursor.getString(20));
                        data.setOut_pickup_remarks(cursor.getString(21));
                        data.setOut_wr_remarks(cursor.getString(22));
                        players.add(data);
                    }

                } while (cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        return players;
    }

    public List<ActualListDao> getOfflineApproveListDetailsRelatedLotno(String lotno) {
        List<ActualListDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_APPROVE_LIST +" where "+ APPROVE_LIST_LOT_NO + " like '%"+lotno+"%'";;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ActualListDao data = null;

        players.clear();
        try {
            if (cursor.moveToFirst()) {
                do {
                    data = new ActualListDao();
                    data.setId(Integer.parseInt(cursor.getString(0)));
                    data.setOut_act_rowid(Integer.parseInt(cursor.getString(1)));
                    data.setOut_lotno(cursor.getString(2));
                    data.setOut_agg_code(cursor.getString(3));
                    data.setOut_farmer_code(cursor.getString(4));
                    data.setOut_farmer_name(cursor.getString(5));
                    data.setOut_member_type(cursor.getString(6));
                    data.setOut_item_code(cursor.getString(7));
                    data.setOut_item_name(cursor.getString(8));
                    data.setOut_actual_qty(Double.parseDouble(cursor.getString(9)));
                    data.setOut_actual_price(Double.parseDouble(cursor.getString(10)));
                    data.setOut_actual_value(Double.parseDouble(cursor.getString(11)));
                    data.setOut_actual_date(cursor.getString(12));
                    data.setOut_advance_amt(Double.parseDouble(cursor.getString(13)));
                    data.setOut_approve_date(cursor.getString(14));
                    data.setOut_pickup_date(cursor.getString(15));
                    data.setOut_wr_date(cursor.getString(16));
                    data.setOut_no_of_bags(cursor.getString(17));
                    data.setOut_status(cursor.getString(18));
                    data.setOut_actual_remarks(cursor.getString(19));
                    data.setOut_approved_remarks(cursor.getString(20));
                    data.setOut_pickup_remarks(cursor.getString(21));
                    data.setOut_wr_remarks(cursor.getString(22));
                    data.setIn_qcperson_name(cursor.getString(24));
                    data.setIn_vehicle_no(cursor.getString(25));
                    players.add(data);
                } while (cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        return players;
    }

    public void addAllWareHouseListDetails(ActualListDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WAREHOUSE_LIST_ROW_ID, data.getOut_act_rowid());
        values.put(WAREHOUSE_LIST_LOT_NO, data.getOut_lotno());
        values.put(WAREHOUSE_LIST_AGG_CODE, data.getOut_agg_code());
        values.put(WAREHOUSE_LIST_FARMER_CODE, data.getOut_farmer_code());
        values.put(WAREHOUSE_LIST_FARMER_NAME, data.getOut_farmer_name());
        values.put(WAREHOUSE_LIST_MEMBER, data.getOut_member_type());
        values.put(WAREHOUSE_LIST_ITEM_CODE, data.getOut_item_code());
        values.put(WAREHOUSE_LIST_ITEM_NAME, data.getOut_item_name());
        values.put(WAREHOUSE_LIST_ACTUAL_QTY, data.getOut_actual_qty());
        values.put(WAREHOUSE_LIST_ACTUAL_PRICE, data.getOut_actual_price());
        values.put(WAREHOUSE_LIST_ACTUAL_VALUE, data.getOut_actual_value());
        values.put(WAREHOUSE_LIST_ACTUAL_DATE, data.getOut_actual_date());
        values.put(WAREHOUSE_LIST_ADVANCE_AMOUNT, data.getOut_advance_amt());
        values.put(WAREHOUSE_LIST_APPROVE_DATE, data.getOut_approve_date());
        values.put(WAREHOUSE_LIST_PICKUP_DATE, data.getOut_pickup_date());
        values.put(WAREHOUSE_LIST_WR_DATE, data.getOut_wr_date());
        values.put(WAREHOUSE_LIST_NO_OF_BAGS, data.getOut_no_of_bags());
        values.put(WAREHOUSE_LIST_STATUS,data.getOut_status());
        values.put(WAREHOUSE_LIST_ACTUAL_REMARK,data.getOut_actual_remarks());
        values.put(WAREHOUSE_LIST_APPROVE_REMARK,data.getOut_approved_remarks());
        values.put(WAREHOUSE_LIST_PICKUP_REMARK,data.getOut_pickup_remarks());
        values.put(WAREHOUSE_LIST_WR_REMARK,data.getOut_wr_remarks());
        // insert
        db.insert(TABLE_NAME_WAREHOUSE_LIST,null, values);
        db.close();


        Log.i(MyConstants.TAG,"WareHouse Inserted successfully"+ values.toString());
    }

    public void deleteWareHouseList(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_WAREHOUSE_LIST);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public List<ActualListDao> getOfflineWareHouseListDetails() {
        List<ActualListDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_WAREHOUSE_LIST ;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ActualListDao data = null;

        players.clear();
        try {
            if (cursor.moveToFirst()) {
                do {
                    data = new ActualListDao();
                    data.setId(Integer.parseInt(cursor.getString(0)));
                    data.setOut_act_rowid(Integer.parseInt(cursor.getString(1)));
                    data.setOut_lotno(cursor.getString(2));
                    data.setOut_agg_code(cursor.getString(3));
                    data.setOut_farmer_code(cursor.getString(4));
                    data.setOut_farmer_name(cursor.getString(5));
                    data.setOut_member_type(cursor.getString(6));
                    data.setOut_item_code(cursor.getString(7));
                    data.setOut_item_name(cursor.getString(8));
                    data.setOut_actual_qty(Double.parseDouble(cursor.getString(9)));
                    data.setOut_actual_price(Double.parseDouble(cursor.getString(10)));
                    data.setOut_actual_value(Double.parseDouble(cursor.getString(11)));
                    data.setOut_actual_date(cursor.getString(12));
                    data.setOut_advance_amt(Double.parseDouble(cursor.getString(13)));
                    data.setOut_approve_date(cursor.getString(14));
                    data.setOut_pickup_date(cursor.getString(15));
                    data.setOut_wr_date(cursor.getString(16));
                    data.setOut_no_of_bags(cursor.getString(17));
                    data.setOut_status(cursor.getString(18));
                    data.setOut_actual_remarks(cursor.getString(19));
                    data.setOut_approved_remarks(cursor.getString(20));
                    data.setOut_pickup_remarks(cursor.getString(21));
                    data.setOut_wr_remarks(cursor.getString(22));
                    players.add(data);
                } while (cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        return players;
    }

    public void deletepproveSingleHeaderDetails(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_APP_SINGLE_HEADER);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void updateAllApproveSingleHeaderDetails(SingleActualHeaderDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(APP_SINGLE_HEADER_ROW_ID, data.getIoU_act_rowid());
        values.put(APP_SINGLE_HEADER_AGG_CODE, data.getIoU_agg_code());
        values.put(APP_SINGLE_HEADER_LOT_NO, data.getIoU_lotno());
        values.put(APP_SINGLE_HEADER_FARMER_CODE, data.getIn_farmer_code());
        values.put(APP_SINGLE_HEADER_FARMER_NAME, data.getIn_farmer_name());
        values.put(APP_SINGLE_HEADER_MEMBER, data.getIn_member_type());
        values.put(APP_SINGLE_HEADER_ITEM_CODE, data.getIn_item_code());
        values.put(APP_SINGLE_HEADER_ITEM_NAME, data.getIn_item_name());
        values.put(APP_SINGLE_HEADER_ACT_QTY, data.getIn_actual_qty());
        values.put(APP_SINGLE_HEADER_ACTUAL_PRICE, data.getIn_actual_price());
        values.put(APP_SINGLE_HEADER_ACTUAL_VALUE, data.getIn_actual_value());
        values.put(APP_SINGLE_HEADER_ADVANCE_AMT, data.getIn_advance_amt());
        values.put(APP_SINGLE_HEADER_NO_OF_BAGS, data.getIn_no_of_bags());
        values.put(APP_SINGLE_HEADER_IN_STATUS, data.getIn_status());
        values.put(APP_SINGLE_HEADER_ACTUAL_REMARKS, data.getIn_actual_remarks());
        values.put(APP_SINGLE_HEADER_APPROVE_REMARK, data.getIn_approved_remarks());
        values.put(APP_SINGLE_HEADER_PICKUP_REMARK, data.getIn_pickup_remarks());
        values.put(APP_SINGLE_HEADER_WR_REMARK,data.getIn_wr_remarks());
        values.put(APP_SINGLE_HEADER_MODE_FLAG,data.getIn_mode_flag());
        values.put(APP_SINGLE_HEADER_ROW_TIMESTAMP,data.getIn_row_timestamp());
        values.put(APP_SINGLE_HEADER_ATTC,data.getIn_actual_attach());
        values.put(APP_SINGLE_HEADER_QCP,data.getIn_qcperson_name());
        values.put(APP_SINGLE_HEADER_VNO,data.getIn_vehicle_no());
        values.put(APP_SINGLE_HEADER_VTY,data.getIn_vehicle_type());
        values.put(APP_SINGLE_HEADER_DSN,data.getIn_destination());
        values.put(APP_SINGLE_HEADER_LRP,data.getIn_LRP_Name());
        values.put(APP_SINGLE_HEADER_LRPMN,data.getIn_LRP_Mobile_no());
        values.put(APP_SINGLE_HEADER_PT,data.getIn_Payment_type());
        values.put(APP_SINGLE_HEADER_BNO,data.getIn_Bank_acc_no());
        values.put(APP_SINGLE_HEADER_CQ,data.getIn_cheque_no());
        // update
        db.update(TABLE_NAME_APP_SINGLE_HEADER,values, "AppSingleHeaderKeyId = ? ",new String[]{Integer.toString(data.getId())});
        db.close();

        Log.i(MyConstants.TAG,"ApproveSingleHeaderDetails Offline Updated successfully"+ values.toString());
    }

    public void addAllApproveSingleHeaderDetails(SingleActualHeaderDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(APP_SINGLE_HEADER_ROW_ID, data.getIoU_act_rowid());
        values.put(APP_SINGLE_HEADER_AGG_CODE, data.getIoU_agg_code());
        values.put(APP_SINGLE_HEADER_LOT_NO, data.getIoU_lotno());
        values.put(APP_SINGLE_HEADER_FARMER_CODE, data.getIn_farmer_code());
        values.put(APP_SINGLE_HEADER_FARMER_NAME, data.getIn_farmer_name());
        values.put(APP_SINGLE_HEADER_MEMBER, data.getIn_member_type());
        values.put(APP_SINGLE_HEADER_ITEM_CODE, data.getIn_item_code());
        values.put(APP_SINGLE_HEADER_ITEM_NAME, data.getIn_item_name());
        values.put(APP_SINGLE_HEADER_ACT_QTY, data.getIn_actual_qty());
        values.put(APP_SINGLE_HEADER_ACTUAL_PRICE, data.getIn_actual_price());
        values.put(APP_SINGLE_HEADER_ACTUAL_VALUE, data.getIn_actual_value());
        values.put(APP_SINGLE_HEADER_ADVANCE_AMT, data.getIn_advance_amt());
        values.put(APP_SINGLE_HEADER_NO_OF_BAGS, data.getIn_no_of_bags());
        values.put(APP_SINGLE_HEADER_IN_STATUS, data.getIn_status());
        values.put(APP_SINGLE_HEADER_ACTUAL_REMARKS, data.getIn_actual_remarks());
        values.put(APP_SINGLE_HEADER_APPROVE_REMARK, data.getIn_approved_remarks());
        values.put(APP_SINGLE_HEADER_PICKUP_REMARK, data.getIn_pickup_remarks());
        values.put(APP_SINGLE_HEADER_WR_REMARK,data.getIn_wr_remarks());
        values.put(APP_SINGLE_HEADER_MODE_FLAG,data.getIn_mode_flag());
        values.put(APP_SINGLE_HEADER_ROW_TIMESTAMP,data.getIn_row_timestamp());
        values.put(APP_SINGLE_HEADER_ATTC,data.getIn_actual_attach());
        values.put(APP_SINGLE_HEADER_QCP,data.getIn_qcperson_name());
        values.put(APP_SINGLE_HEADER_VNO,data.getIn_vehicle_no());
        values.put(APP_SINGLE_HEADER_VTY,data.getIn_vehicle_type());
        values.put(APP_SINGLE_HEADER_DSN,data.getIn_destination());
        values.put(APP_SINGLE_HEADER_LRP,data.getIn_LRP_Name());
        values.put(APP_SINGLE_HEADER_LRPMN,data.getIn_LRP_Mobile_no());
        values.put(APP_SINGLE_HEADER_PT,data.getIn_Payment_type());
        values.put(APP_SINGLE_HEADER_BNO,data.getIn_Bank_acc_no());
        values.put(APP_SINGLE_HEADER_CQ,data.getIn_cheque_no());
        // insert
        db.insert(TABLE_NAME_APP_SINGLE_HEADER,null, values);
        db.close();


        Log.i(MyConstants.TAG,"ApproveSingleHeader Inserted successfully"+ values.toString());
    }

   /* public List<SingleActualHeaderDao> getOfflineAppSingleHeaderListDetails() {
        List<SingleActualHeaderDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_APP_SINGLE_HEADER ;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        SingleActualHeaderDao data = null;

        players.clear();
        try {
            if (cursor.moveToFirst()) {
                do {
                    data = new SingleActualHeaderDao();
                    data.setId(Integer.parseInt(cursor.getString(0)));
                    data.setIoU_act_rowid(Integer.parseInt(cursor.getString(1)));
                    data.setIoU_agg_code(cursor.getString(2));
                    data.setIoU_lotno(cursor.getString(3));
                    data.setIn_farmer_code(cursor.getString(4));
                    data.setIn_farmer_name(cursor.getString(5));
                    data.setIn_member_type(cursor.getString(6));
                    data.setIn_item_code(cursor.getString(7));
                    data.setIn_item_name(cursor.getString(8));
                    data.setIn_actual_qty(Integer.parseInt(cursor.getString(9)));
                    data.setIn_actual_price(Double.parseDouble(cursor.getString(10)));
                    data.setIn_actual_value(Double.parseDouble(cursor.getString(11)));
                    data.setIn_advance_amt(Double.parseDouble(cursor.getString(12)));
                    data.setIn_no_of_bags(Integer.parseInt(cursor.getString(13)));
                    data.setIn_status(cursor.getString(14));
                    data.setIn_actual_remarks(cursor.getString(15));
                    data.setIn_approved_remarks(cursor.getString(16));
                    data.setIn_pickup_remarks(cursor.getString(17));
                    data.setIn_wr_remarks(cursor.getString(18));
                    data.setIn_mode_flag(cursor.getString(19));
                    data.setIn_row_timestamp(cursor.getString(20));
                    players.add(data);
                } while (cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        return players;
    }*/

    public List<SingleActualHeaderDao> getOfflineAppSingleHeaderListDetailsRelatedLotNO(String lotno) {
        List<SingleActualHeaderDao> players = new ArrayList<>();
      //  String query = "SELECT * FROM " + TABLE_NAME_ESTIMATE_LIST +" where "+ ESTIMATELIST_LOTNO + " like '%"+lotno+"%'";
        String query = "SELECT * FROM " + TABLE_NAME_APP_SINGLE_HEADER +" where "+ APP_SINGLE_HEADER_LOT_NO + " like '%"+lotno+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        SingleActualHeaderDao data = null;

        players.clear();
        try {
            if (cursor.moveToFirst()) {
                do {
                    data = new SingleActualHeaderDao();
                    data.setId(Integer.parseInt(cursor.getString(0)));
                    data.setIoU_act_rowid(Integer.parseInt(cursor.getString(1)));
                    data.setIoU_agg_code(cursor.getString(2));
                    data.setIoU_lotno(cursor.getString(3));
                    data.setIn_farmer_code(cursor.getString(4));
                    data.setIn_farmer_name(cursor.getString(5));
                    data.setIn_member_type(cursor.getString(6));
                    data.setIn_item_code(cursor.getString(7));
                    data.setIn_item_name(cursor.getString(8));
                    data.setIn_actual_qty(Double.parseDouble(cursor.getString(9)));
                    data.setIn_actual_price(Double.parseDouble(cursor.getString(10)));
                    data.setIn_actual_value(Double.parseDouble(cursor.getString(11)));
                    data.setIn_advance_amt(Double.parseDouble(cursor.getString(12)));
                    data.setIn_no_of_bags(Integer.parseInt(cursor.getString(13)));
                    data.setIn_status(cursor.getString(14));
                    data.setIn_actual_remarks(cursor.getString(15));
                    data.setIn_approved_remarks(cursor.getString(16));
                    data.setIn_pickup_remarks(cursor.getString(17));
                    data.setIn_wr_remarks(cursor.getString(18));
                    data.setIn_mode_flag(cursor.getString(19));
                    data.setIn_row_timestamp(cursor.getString(20));
                    data.setIn_qcperson_name(cursor.getString(22));
                    data.setIn_vehicle_no(cursor.getString(23));
                    data.setIn_vehicle_type(cursor.getString(24));
                    data.setIn_destination(cursor.getString(25));
                    data.setIn_LRP_Name(cursor.getString(26));
                    data.setIn_LRP_Mobile_no(cursor.getString(27));
                    data.setIn_Payment_type(cursor.getString(28));
                    data.setIn_Bank_acc_no(cursor.getString(29));
                    data.setIn_cheque_no(cursor.getString(30));
                    players.add(data);
                } while (cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        return players;
    }

    public void deleteWarehouseSingleHeaderDetails(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_WR_SINGLE_HEADER);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void addAllWarehouseSingleHeaderDetails(SingleActualHeaderDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WR_SINGLE_HEADER_ROW_ID, data.getIoU_act_rowid());
        values.put(WR_SINGLE_HEADER_AGG_CODE, data.getIoU_agg_code());
        values.put(WR_SINGLE_HEADER_LOT_NO, data.getIoU_lotno());
        values.put(WR_SINGLE_HEADER_FARMER_CODE, data.getIn_farmer_code());
        values.put(WR_SINGLE_HEADER_FARMER_NAME, data.getIn_farmer_name());
        values.put(WR_SINGLE_HEADER_MEMBER, data.getIn_member_type());
        values.put(WR_SINGLE_HEADER_ITEM_CODE, data.getIn_item_code());
        values.put(WR_SINGLE_HEADER_ITEM_NAME, data.getIn_item_name());
        values.put(WR_SINGLE_HEADER_ACT_QTY, data.getIn_actual_qty());
        values.put(WR_SINGLE_HEADER_ACTUAL_PRICE, data.getIn_actual_price());
        values.put(WR_SINGLE_HEADER_ACTUAL_VALUE, data.getIn_actual_value());
        values.put(WR_SINGLE_HEADER_ADVANCE_AMT, data.getIn_advance_amt());
        values.put(WR_SINGLE_HEADER_NO_OF_BAGS, data.getIn_no_of_bags());
        values.put(WR_SINGLE_HEADER_IN_STATUS, data.getIn_status());
        values.put(WR_SINGLE_HEADER_ACTUAL_REMARKS, data.getIn_actual_remarks());
        values.put(WR_SINGLE_HEADER_APPROVE_REMARK, data.getIn_approved_remarks());
        values.put(WR_SINGLE_HEADER_PICKUP_REMARK, data.getIn_pickup_remarks());
        values.put(WR_SINGLE_HEADER_WR_REMARK,data.getIn_wr_remarks());
        values.put(WR_SINGLE_HEADER_MODE_FLAG,data.getIn_mode_flag());
        values.put(WR_SINGLE_HEADER_ROW_TIMESTAMP,data.getIn_row_timestamp());
        // insert
        db.insert(TABLE_NAME_WR_SINGLE_HEADER,null, values);
        db.close();


        Log.i(MyConstants.TAG,"WareHouseSingleHeader Inserted successfully"+ values.toString());
    }

    public List<SingleActualHeaderDao> getOfflineWareHouseSingleHeaderListDetailsRelatedLotNO(String lotno) {
        List<SingleActualHeaderDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_WR_SINGLE_HEADER +" where "+ WR_SINGLE_HEADER_LOT_NO + " like '%"+lotno+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        SingleActualHeaderDao data = null;

        players.clear();
        try {
            if (cursor.moveToFirst()) {
                do {
                    data = new SingleActualHeaderDao();
                    data.setId(Integer.parseInt(cursor.getString(0)));
                    data.setIoU_act_rowid(Integer.parseInt(cursor.getString(1)));
                    data.setIoU_agg_code(cursor.getString(2));
                    data.setIoU_lotno(cursor.getString(3));
                    data.setIn_farmer_code(cursor.getString(4));
                    data.setIn_farmer_name(cursor.getString(5));
                    data.setIn_member_type(cursor.getString(6));
                    data.setIn_item_code(cursor.getString(7));
                    data.setIn_item_name(cursor.getString(8));
                    data.setIn_actual_qty(Double.parseDouble(cursor.getString(9)));
                    data.setIn_actual_price(Double.parseDouble(cursor.getString(10)));
                    data.setIn_actual_value(Double.parseDouble(cursor.getString(11)));
                    data.setIn_advance_amt(Double.parseDouble(cursor.getString(12)));
                    data.setIn_no_of_bags(Integer.parseInt(cursor.getString(13)));
                    data.setIn_status(cursor.getString(14));
                    data.setIn_actual_remarks(cursor.getString(15));
                    data.setIn_approved_remarks(cursor.getString(16));
                    data.setIn_pickup_remarks(cursor.getString(17));
                    data.setIn_wr_remarks(cursor.getString(18));
                    data.setIn_mode_flag(cursor.getString(19));
                    data.setIn_row_timestamp(cursor.getString(20));
                    players.add(data);
                } while (cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        return players;
    }

    public void deleteApproveQtyDetails(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_APP_QTY_DETAIL);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void updateAllApproveQtyDetails(QtyDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(APP_QTY_DETAIL_ROW_ID, data.getIn_qty_row_id());
        values.put(APP_QTY_DETAIL_QTY_CODE, data.getIn_qty_code());
        values.put(APP_QTY_DETAIL_QTY_NAME, data.getIn_qty_name());
        values.put(APP_QTY_DETAIL_ACTUAL_VALUE, data.getIn_actual_value());
        values.put(APP_QTY_DETAIL_WR_QTY_VALUE, data.getIn_wr_qty_value());
        values.put(APP_QTY_DETAIL_MODE_FLAG, data.getIn_mode_flag());
        values.put(APP_QTY_DETAIL_AGG_CODE, data.getIn_agg_code());
        values.put(APP_QTY_DETAIL_LOTNO, data.getIn_lotno());
        values.put(APP_QTY_DETAIL_ITEM_CODE, data.getIn_item_code());
        values.put(APP_QTY_DETAIL_STATUS, data.getIn_item_code());
        // update
        db.update(TABLE_NAME_APP_QTY_DETAIL,values, "AppQtyDetailKeyId = ? ",new String[]{Integer.toString(data.getId())});
   //     db.update(TABLE_NAME_APP_QTY_DETAIL,values, "AppQtyDetailLotNo = ? ",new String[]{data.getIn_lotno()});
        db.close();

        Log.i(MyConstants.TAG,"AllApproveQtyDetailsList Offline Updated successfully"+ values.toString());
    }

    public void addAllApproveQtyDetails(QtyDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(APP_QTY_DETAIL_ROW_ID, data.getIn_qty_row_id());
        values.put(APP_QTY_DETAIL_QTY_CODE, data.getIn_qty_code());
        values.put(APP_QTY_DETAIL_QTY_NAME, data.getIn_qty_name());
        values.put(APP_QTY_DETAIL_ACTUAL_VALUE, data.getIn_actual_value());
        values.put(APP_QTY_DETAIL_WR_QTY_VALUE, data.getIn_wr_qty_value());
        values.put(APP_QTY_DETAIL_MODE_FLAG, data.getIn_mode_flag());
        values.put(APP_QTY_DETAIL_AGG_CODE, data.getIn_agg_code());
        values.put(APP_QTY_DETAIL_LOTNO, data.getIn_lotno());
        values.put(APP_QTY_DETAIL_ITEM_CODE, data.getIn_item_code());
        values.put(APP_QTY_DETAIL_STATUS, data.getStatusValue());
        // insert
        db.insert(TABLE_NAME_APP_QTY_DETAIL,null, values);
        db.close();


        Log.i(MyConstants.TAG,"ApproveQtyDetail Inserted successfully"+ values.toString());
    }

    public List<QtyDetailDao> getOfflineApproveQtyDetailsList(String lotno) {
        List<QtyDetailDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_APP_QTY_DETAIL +" where "+ APP_QTY_DETAIL_LOTNO + " like '%"+lotno+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        QtyDetailDao data = null;

        players.clear();
        try {
            if (cursor.moveToFirst()) {
                do {
                    data = new QtyDetailDao();
                    data.setId(Integer.parseInt(cursor.getString(0)));
                    data.setIn_qty_row_id(Integer.parseInt(cursor.getString(1)));
                    data.setIn_qty_code(cursor.getString(2));
                    data.setIn_qty_name(cursor.getString(3));
                    data.setIn_actual_value(Double.parseDouble(cursor.getString(4)));
                    data.setIn_wr_qty_value(Double.parseDouble(cursor.getString(5)));
                    data.setIn_mode_flag(cursor.getString(6));
                    data.setIn_agg_code(cursor.getString(7));
                    data.setIn_lotno(cursor.getString(8));
                    data.setIn_item_code(cursor.getString(9));
                    data.setStatusValue(cursor.getString(10));
                    players.add(data);
                } while (cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        return players;
    }

    public List<QtyDetailDao> getOfflineApproveQtyDetailsListful(String lotno) {
        List<QtyDetailDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_APP_QTY_DETAIL ;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        QtyDetailDao data = null;

        players.clear();
        try {
            if (cursor.moveToFirst()) {
                do {

                    if(cursor.getString(8).equalsIgnoreCase(lotno)) {
                        data = new QtyDetailDao();
                        data.setId(Integer.parseInt(cursor.getString(0)));
                        data.setIn_qty_row_id(Integer.parseInt(cursor.getString(1)));
                        data.setIn_qty_code(cursor.getString(2));
                        data.setIn_qty_name(cursor.getString(3));
                        data.setIn_actual_value(Integer.parseInt(cursor.getString(4)));
                        data.setIn_wr_qty_value(Integer.parseInt(cursor.getString(5)));
                        data.setIn_mode_flag(cursor.getString(6));
                        data.setIn_agg_code(cursor.getString(7));
                        data.setIn_lotno(cursor.getString(8));
                        data.setIn_item_code(cursor.getString(9));
                        data.setStatusValue(cursor.getString(10));
                        players.add(data);
                    }
                } while (cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        return players;
    }

    public void deleteWareHouseQtyDetails(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_WR_QTY_DETAIL);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void addAllWareHouseQtyDetails(QtyDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WR_QTY_DETAIL_ROW_ID, data.getIn_qty_row_id());
        values.put(WR_QTY_DETAIL_QTY_CODE, data.getIn_qty_code());
        values.put(WR_QTY_DETAIL_QTY_NAME, data.getIn_qty_name());
        values.put(WR_QTY_DETAIL_ACTUAL_VALUE, data.getIn_actual_value());
        values.put(WR_QTY_DETAIL_WR_QTY_VALUE, data.getIn_wr_qty_value());
        values.put(WR_QTY_DETAIL_MODE_FLAG, data.getIn_mode_flag());
        values.put(WR_QTY_DETAIL_AGG_CODE, data.getIn_agg_code());
        values.put(WR_QTY_DETAIL_LOTNO, data.getIn_lotno());
        values.put(WR_QTY_DETAIL_ITEM_CODE, data.getIn_item_code());
        values.put(WR_QTY_DETAIL_STATUS, data.getStatusValue());
        // insert
        db.insert(TABLE_NAME_WR_QTY_DETAIL,null, values);
        db.close();


        Log.i(MyConstants.TAG,"WareHouseQtyDetail Inserted successfully"+ values.toString());
    }

    public List<QtyDetailDao> getOfflineWareHouseQtyDetailsList(String lotno) {
        List<QtyDetailDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_WR_QTY_DETAIL +" where "+ WR_QTY_DETAIL_LOTNO + " like '%"+lotno+"%'" ;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        QtyDetailDao data = null;

        players.clear();
        try {
            if (cursor.moveToFirst()) {
                do {
                    data = new QtyDetailDao();
                    data.setId(Integer.parseInt(cursor.getString(0)));
                    data.setIn_qty_row_id(Integer.parseInt(cursor.getString(1)));
                    data.setIn_qty_code(cursor.getString(2));
                    data.setIn_qty_name(cursor.getString(3));
                    data.setIn_actual_value(Integer.parseInt(cursor.getString(4)));
                    data.setIn_wr_qty_value(Integer.parseInt(cursor.getString(5)));
                    data.setIn_mode_flag(cursor.getString(6));
                    data.setIn_agg_code(cursor.getString(7));
                    data.setIn_lotno(cursor.getString(8));
                    data.setIn_item_code(cursor.getString(9));
                    data.setStatusValue(cursor.getString(10));
                    players.add(data);
                } while (cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        return players;
    }

    public void deleteApproveSiNoDetails(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_APP_SINO_DETAIL);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void updateAllApproveSiNoDetails(SiNoDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(APP_SINO_DETAIL_ROW_ID, data.getIn_slno_row_id());
        values.put(APP_SINO_DETAIL_SL_NO, data.getIn_slno());
        values.put(APP_SINO_DETAIL_TEMP1, data.getIn_temp1());
        values.put(APP_SINO_DETAIL_TEMP2, data.getIn_temp2());
        values.put(APP_SINO_DETAIL_MODE_FLAG, data.getIn_mode_flag());
        values.put(APP_SINO_DETAIL_AGG_CODE, data.getIn_agg_code());
        values.put(APP_SINO_DETAIL_LOT_NO, data.getIn_lotno());
        values.put(APP_SINO_DETAIL_STATUS, data.getIn_lotno());
        // update
        db.update(TABLE_NAME_APP_SINO_DETAIL,values, "AppSiNoDetailKeyId = ? ",new String[]{Integer.toString(data.getId())});
       // db.update(TABLE_NAME_APP_SINO_DETAIL,values, "AppSiNoDetailLotno = ? ",new String[]{data.getIn_lotno()});
        db.close();

        Log.i(MyConstants.TAG,"AllApproveSiNoDetailsList Offline Updated successfully"+ values.toString());
    }

    public void addAllApproveSiNoDetails(SiNoDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(APP_SINO_DETAIL_ROW_ID, data.getIn_slno_row_id());
        values.put(APP_SINO_DETAIL_SL_NO, data.getIn_slno());
        values.put(APP_SINO_DETAIL_TEMP1, data.getIn_temp1());
        values.put(APP_SINO_DETAIL_TEMP2, data.getIn_temp2());
        values.put(APP_SINO_DETAIL_MODE_FLAG, data.getIn_mode_flag());
        values.put(APP_SINO_DETAIL_AGG_CODE, data.getIn_agg_code());
        values.put(APP_SINO_DETAIL_LOT_NO, data.getIn_lotno());
        values.put(APP_SINO_DETAIL_STATUS, data.getStatusValue());
        // insert
        db.insert(TABLE_NAME_APP_SINO_DETAIL,null, values);
        db.close();


        Log.i(MyConstants.TAG,"ApproveSiNoDetail Inserted successfully"+ values.toString());
    }

    public List<SiNoDetailDao> getOfflineApproveSiNoDetailsList(String lotno) {
        List<SiNoDetailDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_APP_SINO_DETAIL +" where "+ APP_SINO_DETAIL_LOT_NO + " like '%"+lotno+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        SiNoDetailDao data = null;

        players.clear();
        try {
            if (cursor.moveToFirst()) {
                do {
                    data = new SiNoDetailDao();
                    data.setId(Integer.parseInt(cursor.getString(0)));
                    data.setIn_slno_row_id(Integer.parseInt(cursor.getString(1)));
                    data.setIn_slno(cursor.getString(2));
                    data.setIn_temp1(cursor.getString(3));
                    data.setIn_temp2(cursor.getString(4));
                    data.setIn_mode_flag(cursor.getString(5));
                    data.setIn_agg_code(cursor.getString(6));
                    data.setIn_lotno(cursor.getString(7));
                    data.setStatusValue(cursor.getString(8));
                    players.add(data);
                } while (cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        return players;
    }

    public List<SiNoDetailDao> getOfflineApproveSiNoDetailsListful(String lotno) {
        List<SiNoDetailDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_APP_SINO_DETAIL ;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        SiNoDetailDao data = null;

        players.clear();
        try {
            if (cursor.moveToFirst()) {
                do {

                    if(cursor.getString(7).equalsIgnoreCase(lotno)) {
                        data = new SiNoDetailDao();
                        data.setId(Integer.parseInt(cursor.getString(0)));
                        data.setIn_slno_row_id(Integer.parseInt(cursor.getString(1)));
                        data.setIn_slno(cursor.getString(2));
                        data.setIn_temp1(cursor.getString(3));
                        data.setIn_temp2(cursor.getString(4));
                        data.setIn_mode_flag(cursor.getString(5));
                        data.setIn_agg_code(cursor.getString(6));
                        data.setIn_lotno(cursor.getString(7));
                        data.setStatusValue(cursor.getString(8));
                        players.add(data);
                    }
                } while (cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        return players;
    }

    public void deleteWareHouseSiNoDetails(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_WR_SINO_DETAIL);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void addAllWareHouseSiNoDetails(SiNoDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WR_SINO_DETAIL_ROW_ID, data.getIn_slno_row_id());
        values.put(WR_SINO_DETAIL_SL_NO, data.getIn_slno());
        values.put(WR_SINO_DETAIL_TEMP1, data.getIn_temp1());
        values.put(WR_SINO_DETAIL_TEMP2, data.getIn_temp2());
        values.put(WR_SINO_DETAIL_MODE_FLAG, data.getIn_mode_flag());
        values.put(WR_SINO_DETAIL_AGG_CODE, data.getIn_agg_code());
        values.put(WR_SINO_DETAIL_LOT_NO, data.getIn_lotno());
        values.put(WR_SINO_DETAIL_STATUS, data.getStatusValue());
        // insert
        db.insert(TABLE_NAME_WR_SINO_DETAIL,null, values);
        db.close();


        Log.i(MyConstants.TAG,"WareHouseSiNoDetail Inserted successfully"+ values.toString());
    }

    public List<SiNoDetailDao> getOfflineWareHouseSiNoDetailsList(String lotno) {
        List<SiNoDetailDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_WR_SINO_DETAIL +" where "+ WR_SINO_DETAIL_LOT_NO + " like '%"+lotno+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        SiNoDetailDao data = null;

        players.clear();
        try {
            if (cursor.moveToFirst()) {
                do {
                    data = new SiNoDetailDao();
                    data.setId(Integer.parseInt(cursor.getString(0)));
                    data.setIn_slno_row_id(Integer.parseInt(cursor.getString(1)));
                    data.setIn_slno(cursor.getString(2));
                    data.setIn_temp1(cursor.getString(3));
                    data.setIn_temp2(cursor.getString(4));
                    data.setIn_mode_flag(cursor.getString(5));
                    data.setIn_agg_code(cursor.getString(6));
                    data.setIn_lotno(cursor.getString(7));
                    data.setStatusValue(cursor.getString(8));
                    players.add(data);
                } while (cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        return players;
    }

    public void deleteApproveOtherDetailsList(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_APP_OTHER_DETAIL);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void updateAllApproveOtherDetailsDetails(OtherDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(APP_OTHER_DETAIL_ROW_ID, data.getIn_Other_row_id());
        values.put(APP_OTHER_DETAIL_PACKING_COST, data.getIn_packaging_cost());
        values.put(APP_OTHER_DETAIL_TRANSPORTING_COST, data.getIn_transporting_cost());
        values.put(APP_OTHER_DETAIL_UNPACKING_COST, data.getIn_unpacking_cost());
        values.put(APP_OTHER_DETAIL_LOCAL_PACKING_COST, data.getIn_local_packaging_cost());
        values.put(APP_OTHER_DETAIL_LOCAL_TRANSPORTING_COST, data.getIn_local_transporting_cost());
        values.put(APP_OTHER_DETAIL_TEMP_COST, data.getIn_temp_cost());
        values.put(APP_OTHER_DETAIL_TEMP_COST1, data.getIn_temp_cost1());
        values.put(APP_OTHER_DETAIL_TEMP_COST2, data.getIn_temp_cost2());
        values.put(APP_OTHER_DETAIL_MODE_FLAG, data.getIn_mode_flag());
        values.put(APP_OTHER_DETAIL_AGG_CODE, data.getIn_agg_code());
        values.put(APP_OTHER_DETAIL_LOTNO, data.getIn_lotno());
        values.put(APP_OTHER_DETAIL_STATUS, data.getStatusValue());
        // update
        db.update(TABLE_NAME_APP_OTHER_DETAIL,values, "AppOtherDetailKeyId = ? ",new String[]{Integer.toString(data.getId())});
      //  db.update(TABLE_NAME_APP_OTHER_DETAIL,values, "AppOtherDetailLotNo = ? ",new String[]{data.getIn_lotno()});
        db.close();

        Log.i(MyConstants.TAG,"AllApproveOtherDetailsList Offline Updated successfully"+ values.toString());
    }

    public void addAllApproveOtherDetailsList(OtherDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(APP_OTHER_DETAIL_ROW_ID, data.getIn_Other_row_id());
        values.put(APP_OTHER_DETAIL_PACKING_COST, data.getIn_packaging_cost());
        values.put(APP_OTHER_DETAIL_TRANSPORTING_COST, data.getIn_transporting_cost());
        values.put(APP_OTHER_DETAIL_UNPACKING_COST, data.getIn_unpacking_cost());
        values.put(APP_OTHER_DETAIL_LOCAL_PACKING_COST, data.getIn_local_packaging_cost());
        values.put(APP_OTHER_DETAIL_LOCAL_TRANSPORTING_COST, data.getIn_local_transporting_cost());
        values.put(APP_OTHER_DETAIL_TEMP_COST, data.getIn_temp_cost());
        values.put(APP_OTHER_DETAIL_TEMP_COST1, data.getIn_temp_cost1());
        values.put(APP_OTHER_DETAIL_TEMP_COST2, data.getIn_temp_cost2());
        values.put(APP_OTHER_DETAIL_MODE_FLAG, data.getIn_mode_flag());
        values.put(APP_OTHER_DETAIL_AGG_CODE, data.getIn_agg_code());
        values.put(APP_OTHER_DETAIL_LOTNO, data.getIn_lotno());
        values.put(APP_OTHER_DETAIL_STATUS, data.getStatusValue());
        // insert
        db.insert(TABLE_NAME_APP_OTHER_DETAIL,null, values);
        db.close();
        Log.i(MyConstants.TAG,"ApproveOtherDetaails Inserted successfully"+ values.toString());
    }

    public List<OtherDetailDao> getOfflineApproveOtherDetails(String lotno) {
        List<OtherDetailDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_APP_OTHER_DETAIL +" where "+ APP_OTHER_DETAIL_LOTNO + " like '%"+lotno+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        OtherDetailDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new OtherDetailDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setIn_Other_row_id(Integer.parseInt(cursor.getString(1)));
                data.setIn_packaging_cost(Integer.parseInt(cursor.getString(2)));
                data.setIn_transporting_cost(Integer.parseInt(cursor.getString(3)));
                data.setIn_unpacking_cost(Integer.parseInt(cursor.getString(4)));
                data.setIn_local_packaging_cost(Integer.parseInt(cursor.getString(5)));
                data.setIn_local_transporting_cost(Integer.parseInt(cursor.getString(6)));
                data.setIn_temp_cost(Integer.parseInt(cursor.getString(7)));
                data.setIn_temp_cost1(Integer.parseInt(cursor.getString(8)));
                data.setIn_temp_cost2(Integer.parseInt(cursor.getString(9)));
                data.setIn_mode_flag(cursor.getString(10));
                data.setIn_agg_code(cursor.getString(11));
                data.setIn_lotno(cursor.getString(12));
                data.setStatusValue(cursor.getString(13));
                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }

    public List<OtherDetailDao> getOfflineApproveOtherDetailsful(String lotno) {
        List<OtherDetailDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_APP_OTHER_DETAIL;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        OtherDetailDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {

                if(cursor.getString(12).equalsIgnoreCase(lotno)) {
                    data = new OtherDetailDao();
                    data.setId(Integer.parseInt(cursor.getString(0)));
                    data.setIn_Other_row_id(Integer.parseInt(cursor.getString(1)));
                    data.setIn_packaging_cost(Integer.parseInt(cursor.getString(2)));
                    data.setIn_transporting_cost(Integer.parseInt(cursor.getString(3)));
                    data.setIn_unpacking_cost(Integer.parseInt(cursor.getString(4)));
                    data.setIn_local_packaging_cost(Integer.parseInt(cursor.getString(5)));
                    data.setIn_local_transporting_cost(Integer.parseInt(cursor.getString(6)));
                    data.setIn_temp_cost(Integer.parseInt(cursor.getString(7)));
                    data.setIn_temp_cost1(Integer.parseInt(cursor.getString(8)));
                    data.setIn_temp_cost2(Integer.parseInt(cursor.getString(9)));
                    data.setIn_mode_flag(cursor.getString(10));
                    data.setIn_agg_code(cursor.getString(11));
                    data.setIn_lotno(cursor.getString(12));
                    data.setStatusValue(cursor.getString(13));
                    players.add(data);
                }
            } while (cursor.moveToNext());
        }
        return players;
    }

    public void deleteWareHouseOtherDetailsList(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_WR_OTHER_DETAIL);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void addAllWareHouseOtherDetailsList(OtherDetailDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WR_OTHER_DETAIL_ROW_ID, data.getIn_Other_row_id());
        values.put(WR_OTHER_DETAIL_PACKING_COST, data.getIn_packaging_cost());
        values.put(WR_OTHER_DETAIL_TRANSPORTING_COST, data.getIn_transporting_cost());
        values.put(WR_OTHER_DETAIL_UNPACKING_COST, data.getIn_unpacking_cost());
        values.put(WR_OTHER_DETAIL_LOCAL_PACKING_COST, data.getIn_local_packaging_cost());
        values.put(WR_OTHER_DETAIL_LOCAL_TRANSPORTING_COST, data.getIn_local_transporting_cost());
        values.put(WR_OTHER_DETAIL_TEMP_COST, data.getIn_temp_cost());
        values.put(WR_OTHER_DETAIL_TEMP_COST1, data.getIn_temp_cost1());
        values.put(WR_OTHER_DETAIL_TEMP_COST2, data.getIn_temp_cost2());
        values.put(WR_OTHER_DETAIL_MODE_FLAG, data.getIn_mode_flag());
        values.put(WR_OTHER_DETAIL_AGG_CODE, data.getIn_agg_code());
        values.put(WR_OTHER_DETAIL_LOTNO, data.getIn_lotno());
        values.put(WR_OTHER_DETAIL_STATUS, data.getStatusValue());
        // insert
        db.insert(TABLE_NAME_WR_OTHER_DETAIL,null, values);
        db.close();
        Log.i(MyConstants.TAG,"WareHouseOtherDetaails Inserted successfully"+ values.toString());
    }

    public List<OtherDetailDao> getOfflineWareHouseOtherDetails(String lotno) {
        List<OtherDetailDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_WR_OTHER_DETAIL +" where "+ WR_OTHER_DETAIL_LOTNO + " like '%"+lotno+"%'";
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        OtherDetailDao data = null;

        players.clear();
        if (cursor.moveToFirst()) {
            do {
                data= new OtherDetailDao();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setIn_Other_row_id(Integer.parseInt(cursor.getString(1)));
                data.setIn_packaging_cost(Integer.parseInt(cursor.getString(2)));
                data.setIn_transporting_cost(Integer.parseInt(cursor.getString(3)));
                data.setIn_unpacking_cost(Integer.parseInt(cursor.getString(4)));
                data.setIn_local_packaging_cost(Integer.parseInt(cursor.getString(5)));
                data.setIn_local_transporting_cost(Integer.parseInt(cursor.getString(6)));
                data.setIn_temp_cost(Integer.parseInt(cursor.getString(7)));
                data.setIn_temp_cost1(Integer.parseInt(cursor.getString(8)));
                data.setIn_temp_cost2(Integer.parseInt(cursor.getString(9)));
                data.setIn_mode_flag(cursor.getString(10));
                data.setIn_agg_code(cursor.getString(11));
                data.setIn_lotno(cursor.getString(12));
                data.setStatusValue(cursor.getString(13));
                players.add(data);
            } while (cursor.moveToNext());
        }
        return players;
    }

    public void addAllWarehouseReceiptListDetails(WareHouseListDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WR_RECEIPT_LIST_ROW_ID, data.getOut_whs_rowid());
        values.put(WR_RECEIPT_LIST_LOTNO, data.getOut_LotNo());
        values.put(WR_RECEIPT_LIST_WHS_CODE, data.getOut_whs_code());
        values.put(WR_RECEIPT_LIST_FARMER_CODE, data.getOut_farmer_code());
        values.put(WR_RECEIPT_LIST_FARMER_NAME, data.getOut_farmer_name());
        values.put(WR_RECEIPT_LIST_MEMBER, data.getOut_member_type());
        values.put(WR_RECEIPT_LIST_ITEM_CODE, data.getOut_item_code());
        values.put(WR_RECEIPT_LIST_ITEM_NAME, data.getOut_item_name());
        values.put(WR_RECEIPT_LIST_ACCEPT_QTY, data.getOut_accepted_qty());
        values.put(WR_RECEIPT_LIST_PAYMENT_ADVCNO, data.getOut_payment_advcno());
        values.put(WR_RECEIPT_LIST_ACCEPTED_DATE, data.getOut_Accepted_date());
        values.put(WR_RECEIPT_LIST_STATUS, data.getOut_status());
        values.put(WR_RECEIPT_LIST_REMARK, data.getOut_Remarks());
        values.put(WR_RECEIPT_LIST_TIMESTAMP, data.getOut_row_timestamp());
        // insert
        db.insert(TABLE_NAME_WR_RECEIPT_LIST,null, values);
        db.close();


        Log.i(MyConstants.TAG,"WareHouseRecipt Inserted successfully"+ values.toString());
    }

    public List<WareHouseListDao> getOfflineWareHouseReceiptListDetails() {
        List<WareHouseListDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_WR_RECEIPT_LIST ;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        WareHouseListDao data = null;

        players.clear();
        try {
            if (cursor.moveToFirst()) {
                do {
                    data = new WareHouseListDao();
                    data.setId(Integer.parseInt(cursor.getString(0)));
                    data.setOut_whs_rowid(Integer.parseInt(cursor.getString(1)));
                    data.setOut_LotNo(cursor.getString(2));
                    data.setOut_whs_code(cursor.getString(3));
                    data.setOut_farmer_code(cursor.getString(4));
                    data.setOut_farmer_name(cursor.getString(5));
                    data.setOut_member_type(cursor.getString(6));
                    data.setOut_item_code(cursor.getString(7));
                    data.setOut_item_name(cursor.getString(8));
                    data.setOut_accepted_qty(cursor.getString(9));
                    data.setOut_payment_advcno(cursor.getString(10));
                    data.setOut_Accepted_date(cursor.getString(11));
                    data.setOut_status(cursor.getString(12));
                    data.setOut_Remarks(cursor.getString(13));
                    data.setOut_row_timestamp(cursor.getString(14));
                    players.add(data);
                } while (cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        return players;
    }

    public void deleteAllWareHouseReceiptListDetails(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_WR_RECEIPT_LIST);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void addAllActualProListDetails(ActProListDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACTUAL_PRO_LIST_ROW_ID, data.getOut_act_rowid());
        values.put(ACTUAL_PRO_LIST_AGG_CODE, data.getOut_agg_code());
        values.put(ACTUAL_PRO_LIST_LOTNO, data.getOut_lotno());
        values.put(ACTUAL_PRO_LIST_FARMER_CODE, data.getOut_farmer_code());
        values.put(ACTUAL_PRO_LIST_FARMER_NAME, data.getOut_farmer_name());
        values.put(ACTUAL_PRO_LIST_MEMBER, data.getOut_member_type());
        values.put(ACTUAL_PRO_LIST_ITEM_CODE, data.getOut_item_code());
        values.put(ACTUAL_PRO_LIST_ITEM_NAME, data.getOut_item_name());
        values.put(ACTUAL_PRO_LIST_STATUS, data.getOut_status());
        values.put(ACTUAL_PRO_LIST_TIMESTAMP, data.getOut_row_timestamp());
        // insert
        db.insert(TABLE_NAME_ACTUAL_PRO_LIST,null, values);
        db.close();


        Log.i(MyConstants.TAG,"ActualProList Inserted successfully"+ values.toString());
    }

    public void updateAllActualProListDetails(ActProListDao data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACTUAL_PRO_LIST_ROW_ID, data.getOut_act_rowid());
        values.put(ACTUAL_PRO_LIST_AGG_CODE, data.getOut_agg_code());
        values.put(ACTUAL_PRO_LIST_LOTNO, data.getOut_lotno());
        values.put(ACTUAL_PRO_LIST_FARMER_CODE, data.getOut_farmer_code());
        values.put(ACTUAL_PRO_LIST_FARMER_NAME, data.getOut_farmer_name());
        values.put(ACTUAL_PRO_LIST_MEMBER, data.getOut_member_type());
        values.put(ACTUAL_PRO_LIST_ITEM_CODE, data.getOut_item_code());
        values.put(ACTUAL_PRO_LIST_ITEM_NAME, data.getOut_item_name());
        values.put(ACTUAL_PRO_LIST_STATUS, data.getOut_status());
        values.put(ACTUAL_PRO_LIST_TIMESTAMP, data.getOut_row_timestamp());
        // update
        db.update(TABLE_NAME_ACTUAL_PRO_LIST,values, "ActualProListKeyId = ? ",new String[]{Integer.toString(data.getId())});
        db.close();

        Log.i(MyConstants.TAG,"ActualProList Offline Updated successfully"+ values.toString());
    }

    public List<ActProListDao> getOfflineActualProListDetails() {
        List<ActProListDao> players = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_ACTUAL_PRO_LIST ;
        Log.e(MyConstants.TAG,query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ActProListDao data = null;

        players.clear();
        try {
            if (cursor.moveToFirst()) {
                do {
                    data = new ActProListDao();
                    data.setId(Integer.parseInt(cursor.getString(0)));
                    data.setOut_act_rowid(Integer.parseInt(cursor.getString(1)));
                    data.setOut_agg_code(cursor.getString(2));
                    data.setOut_lotno(cursor.getString(3));
                    data.setOut_farmer_code(cursor.getString(4));
                    data.setOut_farmer_name(cursor.getString(5));
                    data.setOut_member_type(cursor.getString(6));
                    data.setOut_item_code(cursor.getString(7));
                    data.setOut_item_name(cursor.getString(8));
                    data.setOut_status(cursor.getString(9));
                    data.setOut_row_timestamp(cursor.getString(10));
                    players.add(data);
                } while (cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        return players;
    }

    public void deleteAllActualProListDetails(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_ACTUAL_PRO_LIST);
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void deleteApproveQtyDetails(Context context, String statusValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_APP_QTY_DETAIL +" where "+ APP_QTY_DETAIL_STATUS + " like '%"+statusValue+"%'");
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void deleteApproveOtherDetails(Context context, String statusValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_APP_OTHER_DETAIL +" where "+ APP_OTHER_DETAIL_STATUS + " like '%"+statusValue+"%'");
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void deleteApproveSiNoDetails(Context context, String statusValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_APP_SINO_DETAIL +" where "+ APP_SINO_DETAIL_STATUS + " like '%"+statusValue+"%'");
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void deleteWRQtyDetails(Context context, String statusValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_WR_QTY_DETAIL +" where "+ WR_QTY_DETAIL_STATUS + " like '%"+statusValue+"%'");
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void deleteWROtherDetails(Context context, String statusValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_WR_OTHER_DETAIL +" where "+ WR_OTHER_DETAIL_STATUS + " like '%"+statusValue+"%'");
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void deleteWRSiNoDetails(Context context, String statusValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_WR_SINO_DETAIL +" where "+ WR_SINO_DETAIL_STATUS + " like '%"+statusValue+"%'");
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void deleteApproveQtyDetailsLotNo(Context context, String lotno) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_APP_QTY_DETAIL +" where "+ APP_QTY_DETAIL_LOTNO + " like '%"+lotno+"%'");
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void deleteApproveSIDetailsLotNo(Context context, String lotno) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_APP_SINO_DETAIL +" where "+ APP_SINO_DETAIL_LOT_NO + " like '%"+lotno+"%'");
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void deleteApproveOtherDetailsLotNo(Context context, String lotno) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_APP_OTHER_DETAIL +" where "+ APP_OTHER_DETAIL_LOTNO + " like '%"+lotno+"%'");
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void deleteApproveSingleHeaderDetails(Context context, String lotno) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME_APP_SINGLE_HEADER +" where "+ APP_SINGLE_HEADER_LOT_NO + " like '%"+lotno+"%'");
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

}
