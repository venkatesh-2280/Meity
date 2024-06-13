package cdfi.fintantra.meity.Pawhs.api;

import cdfi.fintantra.meity.Pawhs.LoginModel.LoginDao;

import cdfi.fintantra.meity.Pawhs.bulk.BulkTransactionDao;
import cdfi.fintantra.meity.Pawhs.formermodel.FormerContextDao;
import cdfi.fintantra.meity.Pawhs.formermodel.FormerListDao;
import cdfi.fintantra.meity.Pawhs.model.ActProContextDao;
import cdfi.fintantra.meity.Pawhs.model.ActualProcurementListDao;
import cdfi.fintantra.meity.Pawhs.model.ActualProcurementSaveDao;
import cdfi.fintantra.meity.Pawhs.model.NewEstimateProcSaveDao;
import cdfi.fintantra.meity.Pawhs.model.PmContextDao;
import cdfi.fintantra.meity.Pawhs.model.ProductMasterDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.EstActAppContextDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.EstimateActualApprovedListDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.LotNoContextDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.NewEstimateProcurementListDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.NewEstimateProcurementSingleDao;
import cdfi.fintantra.meity.Pawhs.model.lotmodel.SingleContextDao;
import cdfi.fintantra.meity.Pawhs.model.singleactualprocmodel.SingleActProcContextDao;
import cdfi.fintantra.meity.Pawhs.model.singleactualprocmodel.SingleActualProcurementDao;
import cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel.SingleProductMasterDao;
import cdfi.fintantra.meity.Pawhs.model.singleproductmastermodel.SpmContextDao;
import cdfi.fintantra.meity.Pawhs.model.warehouse.WareHouseReceiptListDao;
import cdfi.fintantra.meity.Pawhs.model.warehouse.WareReceiptContextDao;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {
//test
    @GET("user_validation")
    Observable<LoginDao> getLogin(
            @Query("org") String org, @Query("locn") String locn, @Query("user") String user, @Query("lang") String lang,
            @Query("user_code") String user_code, @Query("user_pwd") String user_pwd);

    @POST("PAWHSProductMaster/new_pawhs_ProductMaster_allproduct")
    Observable<ProductMasterDao> getProductMasterAllProductDetails(@Body PmContextDao pmContextDao);

    @POST("PAWHS_NewEstimated_Procurment/pawhs_NewEstimate_Proc_save")
    Observable<NewEstimateProcSaveDao> postNewEstimateProcSaveDetails(@Body NewEstimateProcSaveDao newEstimateProcSaveDao);

    @POST("PAWHS_NEW_Farmerlist/new_pawhs_Farmer_List")
    Observable<FormerListDao> getFormetListDetails(@Body FormerContextDao formerContextDao);

    @POST("PAWHS_NEW_BulkTransaction/new_pawhs_Bulk_save")
    Observable<String[]> saveBulkTransactionDetails(@Body BulkTransactionDao bulkTransactionDao);

    @POST("PAWHS_NewEstimated_Procurment/pawhs_NewEstimated_Procurment_List")
    Observable<NewEstimateProcurementListDao> getEstProcLotNOList(@Body LotNoContextDao lotNoContextDao);

    @POST("NewPawhsActulProcurment/Estimate_Actual_Approved_List")
    Observable<EstimateActualApprovedListDao> getEstProcLotNOListDetails(@Body EstActAppContextDao estActAppContextDao);

    @POST("PAWHS_NewEstimated_Procurment/pawhs_NewEstimate_Proc_single")
    Observable<NewEstimateProcurementSingleDao> getSingleLotNoDetails(@Body SingleContextDao singleContextDao);

    @POST("PAWHSProductMaster/new_pawhs_Single_ProductMaster")
    Observable<SingleProductMasterDao> getQualityParameterDetails(@Body SpmContextDao spmContextDao);

    @POST("NewPawhsActulProcurment/new_pawhs_ActualProc_save")
    Observable<ActualProcurementSaveDao> postActualProcurementDetails(@Body ActualProcurementSaveDao actualProcurementSaveDao);

    @POST("NewPawhsActulProcurment/new_pawhs_Single_ActualProc")
    Observable<SingleActualProcurementDao> postSingleActualProcurementDetails(@Body SingleActProcContextDao singleActProcContextDao);

    @POST("NewPawhsActulProcurment/new_pawhs_ActualProc_List")
    Observable<ActualProcurementListDao> getActProcListDetails(@Body ActProContextDao actProContextDao);

    @POST("PAWHS_NewWareHouseReceipt/pawhs_NewWareHouseReceipt_List")
    Observable<WareHouseReceiptListDao> getWareHouseReceiptListDetails(@Body WareReceiptContextDao wareReceiptContextDao);
}
