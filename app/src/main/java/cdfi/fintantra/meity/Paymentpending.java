package cdfi.fintantra.meity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.paytm.ecr.bluetooth.sdk.IConnectionStateListener;
import com.paytm.ecr.bluetooth.sdk.PaytmPayments;
import com.paytm.ecr.bluetooth.sdk.ResponseListener;
import com.paytm.ecr.bluetooth.sdk.constants.ConnectionError;
import com.paytm.ecr.bluetooth.sdk.constants.ConnectionState;
import com.paytm.ecr.bluetooth.sdk.model.Config;
import com.paytm.ecr.bluetooth.sdk.model.request.ConnectionCheckRequest;
import com.paytm.ecr.bluetooth.sdk.model.request.StatusCheckRequest;
import com.paytm.ecr.bluetooth.sdk.model.response.BaseResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cdfi.fintantra.meity.Pawhs.api.ApiUtils;

public class Paymentpending extends AppCompatActivity implements ResponseListener, IConnectionStateListener {
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    String orglvlcode = "", invoicenumber, invoicedate, invoiceamount, balanceamount, paidamount, paiddate, orderid;
    RecyclerView rpaypending;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    List<Paymentpendinglist> listval;
    DBHelper db;
    String merchantId;
    double bl = 0;
    ImageView sconnecton;
    JSONObject jsonParam, userd;
    PaytmPayments payments;
    SQLiteDatabase dbs;
    String saleresponse, paytmbluetooth;
    ProgressDialog pdialog;
    int salestatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentpending);
        getSupportActionBar().hide();
        db = new DBHelper(this);
        pdialog = new ProgressDialog(this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String usercode = sharedpreferences.getString("uc", "");
        orglvlcode = sharedpreferences.getString(usercode + "/" + "orgnlvlcode", "");
        listval = new ArrayList<>();
        rpaypending = findViewById(R.id.rpaypending);
        sconnecton = findViewById(R.id.sconnecton);
        payments = PaytmPayments.with(this);
        payments.init(new Config.Builder()
                .setStatusCheckOnSaleRequestEnabled(true).build());
        dbs = db.getWritableDatabase();
        Cursor cmid = dbs.rawQuery("select * from mercid where out_paytm_fpocode = '" + sharedpreferences.getString("oc1", "") + "'", null);

        if (cmid.getCount() > 0) {
            if (cmid.moveToFirst()) {
                merchantId = cmid.getString(4);
                // Toast.makeText(Paymentlist.this,""+merchantId,Toast.LENGTH_SHORT).show();
            }
        }

        ConnectionCheckRequest connectionCheckRequest = new ConnectionCheckRequest.Builder().build();
        String connectionCheckRequestId = String.valueOf(System.currentTimeMillis());
        com.paytm.ecr.bluetooth.sdk.model.Request<ConnectionCheckRequest> request1 = com.paytm.ecr.bluetooth.sdk.model.Request.of(connectionCheckRequest, connectionCheckRequestId);
        payments.doConnectionCheck(request1, Paymentpending.this);

        salestatus = 20;

        sconnecton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if (!payments.isConnected()) {
                    payments.openConnection(Paymentpending.this);
                } else {
                    payments.closeConnection();
                }*/

                ConnectionCheckRequest connectionCheckRequest = new ConnectionCheckRequest.Builder().build();
                String connectionCheckRequestId = String.valueOf(System.currentTimeMillis());
                com.paytm.ecr.bluetooth.sdk.model.Request<ConnectionCheckRequest> request1 = com.paytm.ecr.bluetooth.sdk.model.Request.of(connectionCheckRequest, connectionCheckRequestId);
                payments.doConnectionCheck(request1, Paymentpending.this);

                salestatus = 20;

            }
        });
        JSONObject jsonParam2 = null;
        try {

            jsonParam2 = new JSONObject();

            jsonParam2.put("orgnId", sharedpreferences.getString("oc1",""));
            jsonParam2.put("locnId", "up");
            jsonParam2.put("userId", usercode);
            jsonParam2.put("localeId", "en_US");
            jsonParam2.put("orgn_code", sharedpreferences.getString("oc1",""));
            jsonParam2.put("status_code", "0");
            jsonParam2.put("invoice_date", "");


            Log.e("OUTPUTK", "" + jsonParam2);
        } catch (Exception e) {
            Log.e("OUTPUTK", "" + e.getMessage());
        }
        JsonObjectRequest stringRequest2temp = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "api/ICDMOB_PAYTM/PaymentOnlinepaytmfetch", jsonParam2,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listval.clear();
                        try {

                            JSONObject paylist = response.getJSONObject("context");
                            Log.e("CCCCpayment", "" + response.toString());
                            JSONArray list = paylist.getJSONArray("list");
                            for (int i = 0; i < list.length(); i++) {
                                JSONObject list1 = list.getJSONObject(i);
                                Paymentpendinglist paymentpendinglist = new Paymentpendinglist();
                                paymentpendinglist.setFpocode(list1.getString("in_fpo_code"));
                                paymentpendinglist.setOrderid(list1.getString("in_Order_Id"));
                                paymentpendinglist.setInvno(list1.getString("in_invoice_no"));
                                paymentpendinglist.setInvdate(list1.getString("in_invoice_date"));
                                paymentpendinglist.setInvamt(list1.getString("in_invoice_amount"));
                                paymentpendinglist.setStatuscode(list1.getString("in_status_code"));
                                paymentpendinglist.setPaiddate(list1.getString("in_paid_date"));
                                paymentpendinglist.setPaidamount(list1.getString("in_paid_amount"));
                                paymentpendinglist.setBalamount(list1.getString("in_balance_amount"));
                                listval.add(paymentpendinglist);

                            }
                        } catch (Exception e) {
                            Log.e("CCCC", "Error" + e);
                        }
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Paymentpending.this, LinearLayoutManager.VERTICAL, false);
                        rpaypending.setLayoutManager(linearLayoutManager);
                        myRecyclerViewAdapter = new MyRecyclerViewAdapter(Paymentpending.this, listval);
                        rpaypending.setAdapter(myRecyclerViewAdapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC1", "" + error);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        stringRequest2temp.setRetryPolicy(new DefaultRetryPolicy(
                80000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Paymentpending.this).addToRequestQueue(stringRequest2temp);


    }

    @Override
    public void onResponse(com.paytm.ecr.bluetooth.sdk.model.Response<? extends BaseResponse> response) {
        try {
            if(salestatus == 20){

                Log.e("SALE API3", "" + response.toString());

                String[] res = response.toString().split(",");

                String[] status = res[0].split("=");

                String[] statuses = status[1].split("=");
                String val = statuses[0];

                if(val.equalsIgnoreCase("SUCCESS")){

                    paytmbluetooth = "1";
                    Toast.makeText(Paymentpending.this,val, Toast.LENGTH_SHORT).show();

                }else if(val.equalsIgnoreCase("ERROR")){
                    paytmbluetooth = "0";

                    String[] scode = res[2].split("=");

                    String[] emsg = scode[1].split("=");
                    saleresponse = response.toString();

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(Paymentpending.this, emsg[0], Toast.LENGTH_SHORT).show();

                        }
                    });


                }


            }else {
                Log.e("SALE API3", "" + response.toString());
                Log.e("SALE API3", "" + response.toString());
                // dialog3.dismiss();
                try {

                    String[] res = response.toString().split(",");
                    Log.e("TXTid", "" + res[3]);

                    String[] tx = res[3].split("=");
                    String[] scode = res[41].split("=");
                    // erefno.setText(tx[1].substring( 1, tx[1].length() - 1 ));

                    if (scode[1].substring(1, scode[1].length() - 1).equalsIgnoreCase("101")) {
                        // dbs.execSQL("update paylist set msg = 'Success' , fstatus = '1' where id = '" + paylistid + "'");
                        saleresponse = response.toString();
                        //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                        savep();
                    } else {
                        String[] emsg = res[42].split("=");
                        // dbs.execSQL("update paylist set msg = '" + emsg[1] + "' , fstatus = '1' where id = '" + paylistid + "'");
                        saleresponse = response.toString();
                        saveStatus("2");

                        final AlertDialog alertDialog = new AlertDialog.Builder(Paymentpending.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("INFO!")
//set message
                                .setMessage(emsg[1] + "!")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                //set what would happen when positive button is clicked
                                                finish();

                                            }
                                        }
//set negative button

                                )

                                .setCancelable(false)
                                .show();

                        // db.paylist(itext, "PAYTM", eamtpaid.getText().toString(), orderID, epdate.getText().toString(), "", "0","1","0","2",""+emsg[1].substring( 1, emsg[1].length() - 1 ));


                    }
                } catch (Exception e) {

                    //dialogpayment.dismiss();


                    ;
                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentpending.this)
//set icon
                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                            .setTitle("INFO!")
//set message
                            .setMessage("Payment Not Completed")
//set positive button
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //set what would happen when positive button is clicked
                                            //finish();

                                        }
                                    }
//set negative button

                            )
                            .show();
                }
            }
        } catch (Exception e) {

        }

    }

    @Override
    public void onStatusUpdated(ConnectionState connectionState) {
        Toast.makeText(this, "Success :" + connectionState, Toast.LENGTH_SHORT).show();
        if (connectionState.toString().equalsIgnoreCase("CONNECTED")) {

            sconnecton.setBackgroundResource(R.drawable.eblue);

        } else {
            sconnecton.setBackgroundResource(R.drawable.grayblue);

        }
    }

    @Override
    public void onError(ConnectionError connectionError) {
        Toast.makeText(this, "Error :" + connectionError, Toast.LENGTH_SHORT).show();

    }

    private class MyRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

        private List<Paymentpendinglist> mData;
        private LayoutInflater mInflater;

        MyRecyclerViewAdapter(Context context, List<Paymentpendinglist> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.activity_paymentpendinglist, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Paymentpendinglist Listval = mData.get(position);
            holder.paypendingname.setText(Listval.getFpocode());
            holder.invno.setText(Listval.getInvno());
            holder.invdate.setText(Listval.getPaiddate());
            holder.invamt.setText(Listval.getPaidamount());
            holder.oid.setText(Listval.getOrderid());
            holder.statuscheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (paytmbluetooth.equalsIgnoreCase("1")) {

                        orderid = Listval.getOrderid();
                        paidamount = Listval.getOrderid();
                        paiddate = Listval.getOrderid();
                        invoiceamount = Listval.getOrderid();
                        invoicedate = Listval.getOrderid();
                        balanceamount = Listval.getOrderid();
                        Toast.makeText(Paymentpending.this, "Checking Payment Status", Toast.LENGTH_SHORT).show();
                        //salestatus = 6;
                        StatusCheckRequest.Builder builder = new StatusCheckRequest.Builder()
                                .setMerchantId((TextUtils.isEmpty(merchantId) ? null : merchantId))
                                .setOrderId(Listval.getOrderid());
                        // .setPrintInfo("printInfo://values?merchantTxnId=82938938983&caNumber=34567777&billNumber=xyz123")
                        //  .setGstInformation("gstInformation://values?gstIn=08TESTF0078P1ZP&gstBrkUp=CGST:10|SGST:10|IGST:10|CESS:10|GSTIncentive:10|GSTPCT:10&invoiceNo="+invoiceno.getText().toString()+"&invoiceDate=20220720142919")


                        StatusCheckRequest statusCheckRequest = builder.build();
                        String statusCheckRequestRequestId = String.valueOf(System.currentTimeMillis());
                        com.paytm.ecr.bluetooth.sdk.model.Request<StatusCheckRequest> request = com.paytm.ecr.bluetooth.sdk.model.Request.of(statusCheckRequest, statusCheckRequestRequestId);
                        payments.doStatusCheck(request, Paymentpending.this);
                    } else {
                        Toast.makeText(Paymentpending.this, "Device Not Connected", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView paypendingname, invno, invdate, invamt,oid;
        Button statuscheck;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            paypendingname = itemView.findViewById(R.id.paypendingname);
            invno = itemView.findViewById(R.id.invno);
            invdate = itemView.findViewById(R.id.invdate);
            invamt = itemView.findViewById(R.id.invamt);
            statuscheck = itemView.findViewById(R.id.statuscheck);
            oid = itemView.findViewById(R.id.oid);

        }
    }

    public void saveStatus(String status) {
        try {

            String[] res = saleresponse.toString().split(",");
            Log.e("TXTid", "" + res[3]);

            String[] tx = res[3].split("=");
            String[] scode = res[41].split("=");
            Log.e("VAL1", "" + scode[1]);
            String[] smsg = res[42].split("=");
            Log.e("VAL2", "" + smsg[1]);
            String[] abank = res[39].split("=");
            Log.e("VAL3", "" + abank[1]);
            String[] svcreated = res[36].split("=");
            Log.e("VAL4", "" + svcreated[1]);
            String[] boapplied = res[33].split("=");
            Log.e("VAL5", "" + boapplied[1]);
            String[] bmid = res[21].split("=");
            Log.e("VAL6", "" + bmid[1]);
            String[] btid = res[22].split("=");
            Log.e("VAL7", "" + btid[1]);
            String[] brscode = res[20].split("=");
            Log.e("VAL8", "" + brscode[1]);
            String[] cschme = res[19].split("=");
            Log.e("VAL9", "" + cschme[1]);
            String[] ctype = res[18].split("=");
            Log.e("VAL10", "" + ctype[1]);
            String[] pmethod = res[17].split("=");
            Log.e("VAL11", "" + pmethod[1]);
            String[] aid = res[16].split("=");
            Log.e("VAL12", "" + aid[1]);
            String[] tid = res[15].split("=");
            Log.e("VAL13", "" + tid[1]);
            String[] ttype = res[10].split("=");
            Log.e("VAL14", "" + ttype[1]);
            String[] issuebank = res[9].split("=");
            Log.e("VAL15", "" + issuebank[1]);
            String[] cno = res[8].split("=");
            Log.e("VAL16", "" + cno[1]);
            String[] rrn = res[7].split("=");
            Log.e("VAL17", "" + rrn[1]);
            String[] aucode = res[3].split("=");
            Log.e("VAL18", "" + aucode[1]);
            String[] txid = res[2].split("=");
            String[] inno = res[12].split("=");
            Log.e("VAL19", "" + txid[1]);


            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document", userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", sharedpreferences.getString("oc1", ""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc", ""));
            user.put("localeId", "en_US");
            userd.put("context", user);
            JSONObject user2 = new JSONObject();


            user2.put("IOU_pth_rowid", 0);
            user2.put("In_merchantId", merchantId);
            user2.put("In_orderId", orderid);

            String[] d = paiddate.toString().split("/");
            user2.put("In_txnId", tx[1].substring(1, tx[1].length() - 1));


            user2.put("In_authCode", "");
            user2.put("In_rrn", rrn[1].substring(1, rrn[1].length() - 1));
            user2.put("In_cardNo", cno[1].substring(1, cno[1].length() - 1));
            user2.put("In_issuingBank", issuebank[1].substring(1, issuebank[1].length() - 1));

            user2.put("In_amount", paidamount);


            user2.put("In_txnType", "PAYTM");
            user2.put("In_invoiceNumber", inno[1].substring(1, inno[1].length() - 1));
            user2.put("In_extendInfo", "");
            user2.put("In_printInfo", "");
            user2.put("In_tid", tid[1].substring(1, tid[1].length() - 1));
            user2.put("In_aid", aid[1].substring(1, aid[1].length() - 1));
            user2.put("In_payMethod", pmethod[1].substring(1, pmethod[1].length() - 1));
            user2.put("In_cardType", ctype[1].substring(1, ctype[1].length() - 1));
            user2.put("In_cardScheme", cschme[1].substring(1, cschme[1].length() - 1));
            user2.put("In_bankResponseCode", brscode[1].substring(1, brscode[1].length() - 1));
            user2.put("In_bankMid", bmid[1].substring(1, bmid[1].length() - 1));
            user2.put("In_bankTid", btid[1].substring(1, btid[1].length() - 1));
            user2.put("In_productManufacturer", "");
            user2.put("In_productCategory", "");
            user2.put("In_productSerialNoType", "");
            user2.put("In_productSerialNoValue", "");
            user2.put("In_productName", "");
            user2.put("In_emiTxnType", "");
            user2.put("In_emiTenure", "");
            user2.put("In_emiInterestRate", "");
            user2.put("In_emiMonthlyAmount", "");
            user2.put("In_emiTotalAmount", "");
            user2.put("In_bankOfferApplied", boapplied[1].substring(1, boapplied[1].length() - 1));
            user2.put("In_bankOfferType", "");
            user2.put("In_bankOfferAmount", "");
            user2.put("In_subventionCreated", svcreated[1].substring(1, svcreated[1].length() - 1));
            user2.put("In_subventionType", "");
            user2.put("In_subventionOfferAmount", "");
            user2.put("In_acquiringBank", "");
            user2.put("In_virtualPaymentAddress", "");
            user2.put("In_statusCode", scode[1].substring(1, scode[1].length() - 1));
            user2.put("In_statusMessage", smsg[1].substring(1, smsg[1].length() - 2));
            user2.put("In_mode_flag", "I");


            user.put("Header", user2);


            Log.e("OUTPUTSALE", "" + jsonParam.toString());
        } catch (Exception e) {
            Log.e("OUTPUTSALE", "" + Log.getStackTraceString(e));
            Log.e("OUTPUT", "" + jsonParam.toString());
        }


        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "api/ICDMOB_PAYTM/PaymentHistoryResponseSave", jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCCHISTORY", "" + response);
                        try {
                            JSONObject obj = response.getJSONObject("context");
                            JSONObject obj2 = obj.getJSONObject("Header");

                            String orderID = obj2.getString("IOU_pth_rowid");
                            //Toast.makeText(Paymentlist.this, ""+status, Toast.LENGTH_SHORT).show();
                            if (!orderID.equalsIgnoreCase("0")) {
                                try {
                                    ///dialogpayment.dismiss();
                                } catch (Exception e) {

                                }

                                if (status.equalsIgnoreCase("1")) {
                                    // db.updateinvoice(Integer.parseInt(pyid.toString()), innob.toString(), ebal.getText().toString(), balance.getText().toString(), "", "", "", cname.toString(), "0");

                                    // db.paylist(itext, paymodecode, eamtpaid.getText().toString(), erefno.getText().toString(), epdate.getText().toString(), ebal.getText().toString(), "0", "0","0","1","Success");

                                    final AlertDialog alertDialog = new AlertDialog.Builder(Paymentpending.this)
//set icon
                                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                            .setTitle("Success!")
//set message
                                            .setMessage("Payment Completed!")
//set positive button
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {

                                                            //set what would happen when positive button is clicked
                                                            finish();

                                                        }
                                                    }
//set negative button

                                            )
                                            .setCancelable(false)
                                            .show();
                                }
                            }

                        } catch (Exception e) {

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
                4500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Paymentpending.this).addToRequestQueue(stringRequest);


    }

    public void savep() {


        try {
            DecimalFormat amountFormate = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            Date cc = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            String formattedDate = df.format(cc);
            jsonParam = new JSONObject();
            JSONObject userd = new JSONObject();
            jsonParam.put("document", userd);
            JSONObject user = new JSONObject();
            user.put("orgnId", sharedpreferences.getString("oc", ""));
            user.put("locnId", Pojokyc.instance);
            user.put("userId", sharedpreferences.getString("uc", ""));
            user.put("localeId", "en_US");
            userd.put("context", user);
            JSONObject user2 = new JSONObject();
            String[] d1;

            d1 = paiddate.toString().split("/");


            user2.put("IOU_invoice_rowid", 0);
            user2.put("IOU_invoice_no", invoicenumber);
            user2.put("In_invoice_date", invoicedate);
            user2.put("In_invoice_amount", invoiceamount);

            try {
                bl = Double.parseDouble(balanceamount) - Double.parseDouble(paidamount);
                user2.put("In_balance_amount", "" + bl);
            } catch (Exception e) {
                user2.put("In_balance_amount", "" + bl);
            }


            user2.put("In_payment_mode", "QCD_AEPS_PAYTM");

            user2.put("In_ref_no", orderid);

            user2.put("In_payment_date", d1[2] + "/" + d1[1] + "/" + d1[0]);

            user2.put("In_payment_amount", paidamount);


            user2.put("In_payment_response", "Success");
            user2.put("In_status_code", "A");
            user2.put("In_row_timestamp", formattedDate);
            user2.put("In_mode_flag", "I");
            user2.put("In_check_no", "");

            user.put("Header", user2);

            JSONArray notebookUsers2 = new JSONArray();

            JSONObject user4 = new JSONObject();
            user4.put("In_paymentcollection_rowid", "0");
            user4.put("In_payment_type", "");
            user4.put("In_payment_no", invoicenumber);

            user4.put("In_balance_amount", bl);


            user4.put("In_payment_amount", paidamount);

            user4.put("In_payment_mode", "QCD_AEPS_PAYTM");

            user4.put("In_ref_no", orderid);


            user4.put("In_payment_date", d1[2] + "/" + d1[1] + "/" + d1[0]);
            user4.put("In_process_status", "A");

            user4.put("In_paid_amount", "" + paidamount);


            user4.put("In_mode_flag", "" + "I");


            notebookUsers2.put(user4);


            user.put("Detail", notebookUsers2);

            Log.e("OUTPUTINVOICE", "" + jsonParam.toString());
        } catch (Exception e) {
            Log.e("OUTPUTINVOICE", "" + Log.getStackTraceString(e));
            Log.e("OUTPUT", "" + jsonParam.toString());
        }


        pdialog.setCanceledOnTouchOutside(false);
        pdialog.setTitle("Uploading Please Wait.......");
        pdialog.show();


        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "api/ICDMOBInvoice/mobnewsavePaymentCollection", jsonParam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("CCCC", "" + response);


//                        try {
//                            JSONObject obj = response.getJSONObject("context");
//                            JSONObject obj2 = obj.getJSONObject("Header");
//
//                            String inrid = obj2.getString("IOU_invoice_rowid");
//                            Log.e("CCCC","Hi"+inrid);
//
                        pdialog.dismiss();
//                            SQLiteDatabase dbs = db.getWritableDatabase();
                        // Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
//                            dbs.execSQL("UPDATE invoicelist SET flag = 1 WHERE id = "+id2);
//                            dbs.execSQL("UPDATE invoicelist SET sid = "+inrid+" WHERE id = "+id2);
//                            Log.e("CCCC","Invoice");
                        // finish();
                        //SQLiteDatabase dbs = db.getWritableDatabase();
                        //dbs.execSQL("UPDATE paylist SET uflag = 1");
                        try {

                            userd = new JSONObject();
                            userd.put("senderid", "SMSTST");
                            userd.put("msg_desc", "Thanks for your purchase from " + sharedpreferences.getString("rn", "") + " for INR " + (Float.parseFloat(paidamount)) + " against Invoice " + invoicenumber + " dated " + paiddate + " We have received payment of INR " + (Float.parseFloat(paidamount)) + " and the Balance payable is " + (Float.parseFloat(String.valueOf(bl))));
                            userd.put("mobile_no", "8124873354");


                            Log.e("OUTPUT", "" + userd.toString());

                        } catch (Exception e) {
                            Log.e("OUTPUT", "" + e.getMessage());
                        }


//        pdialog.setCanceledOnTouchOutside(false);
//        pdialog.setTitle("Uploading Please Wait.......");
//        pdialog.show();


                        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Pojokyc.icdurl + "api/SendSMS/SendSMS", userd,
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
                                4500000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        VolleySingleton.getInstance(Paymentpending.this).addToRequestQueue(stringRequest);
//                        final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
////set icon
//                                .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                .setTitle("Success!")
////set message
//                                .setMessage("Payment Completed!")
////set positive button
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                //set what would happen when positive button is clicked
//
//                                                finish();
//
//                                            }
//                                        }
////set negative button
//
//                                )
//                                .show();


                        saveStatus("1");

//                        catch (Exception e)
//                        {
//
//                        }


                        // Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_SHORT).show();
                        //finish();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CCCC", "" + error);

                        // Toast.makeText(getApplicationContext(),"Unable to Insert",Toast.LENGTH_SHORT).show();
                        pdialog.dismiss();
//                        final AlertDialog alertDialog = new AlertDialog.Builder(Paymentlist.this)
////set icon
//                                .setIcon(android.R.drawable.ic_dialog_alert)
////set title
//                                .setTitle("Error!")
////set message
//                                .setMessage("Payment Error!")
////set positive button
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                //set what would happen when positive button is clicked
//
//                                            }
//                                        }
////set negative button
//
//                                )
//                                //.show();
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
}