package cdfi.fintantra.meity.Pawhs.recactpurchase;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import cdfi.fintantra.meity.ExceptionHandler;
import cdfi.fintantra.meity.Pawhs.PAWHSApplication;
import cdfi.fintantra.meity.R;
import cdfi.fintantra.meity.Pawhs.SerialNoListAdapter;
import cdfi.fintantra.meity.Pawhs.api.ApiService;
import cdfi.fintantra.meity.Pawhs.api.ApiUtils;
import cdfi.fintantra.meity.Pawhs.model.PostSiNoDetailDao;

import static cdfi.fintantra.meity.Pawhs.api.ApiUtils.SL_NO_VALUE;


public class SerialNoActivity extends AppCompatActivity implements View.OnClickListener,DeleteSerialItemListener {

    private PAWHSApplication pawhsApplication;
    private ApiService mAPIService;
    private String userName;
    private Context mContext;
    private TextView textViewTitle;
    ImageView qrcode;
    IntentIntegrator qrScan;
    private EditText editTextSerialNo;

    private Button buttonAdd,buttonClose,buttonCancel;

    private RecyclerView recyclerViewSerial;

    private String orgnId, locnId, userId, localeId;

    private List<PostSiNoDetailDao> postSiNoDetailDaoList=new ArrayList<>();

    private SerialNoListAdapter serialNoListAdapter;
    private String lotno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.serial_screen);
        mContext = this;
        pawhsApplication = PAWHSApplication.getGetInstance();
        userName = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.IN_USER_NAME);

        orgnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.ORGN_ID);
        locnId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCN_ID);
        userId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.USER_ID);
        localeId = pawhsApplication.getPreferenceFromString(mContext, ApiUtils.LOCALE_ID);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            postSiNoDetailDaoList=bundle.getParcelableArrayList("SI_DETAIL_LIST");
            lotno=bundle.getString("LOTNO");
        }


        initView();

    }

    private void initView() {
        mAPIService = ApiUtils.getAPIService();
        textViewTitle = (TextView) findViewById(R.id.txt_title);
        textViewTitle.setText("Welcome " + userName + "\n" + "PA Record Actual Purchase");

        editTextSerialNo = (EditText) findViewById(R.id.edt_serialno);
        buttonAdd = (Button) findViewById(R.id.but_add);
        buttonClose = (Button) findViewById(R.id.but_close);
        buttonCancel = (Button) findViewById(R.id.but_cancel);

        recyclerViewSerial=(RecyclerView)findViewById(R.id.recycle_serialno);
        recyclerViewSerial.setLayoutManager(new LinearLayoutManager(mContext));
        qrcode = (ImageView)findViewById(R.id.qrcode);
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1005);
                qrScan = new IntentIntegrator(SerialNoActivity.this);
                qrScan.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                qrScan.setPrompt("Scan Bar Code");
                qrScan.setBeepEnabled(true);
                qrScan.setCameraId(0);
                qrScan.setOrientationLocked(false);
                qrScan.setBarcodeImageEnabled(true);


                qrScan.initiateScan();
            }
        });
        //  postSiNoDetailDaoList=new ArrayList<>();

        buttonAdd.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
        buttonClose.setOnClickListener(this);

        if(postSiNoDetailDaoList!=null && postSiNoDetailDaoList.size()>0){
            setAdapter();
        }




    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.but_add:
                addSerialNo();

                break;
            case R.id.but_close:
                Intent intent=new Intent();
                intent.putParcelableArrayListExtra("SERIAL_LIST", (ArrayList<? extends Parcelable>) postSiNoDetailDaoList);
                setResult(SL_NO_VALUE,intent);
                finish();
                break;
            case R.id.but_cancel:
                finish();
                break;
            default:
                break;
        }
    }

    private void addSerialNo() {

        String serialNo= editTextSerialNo.getText().toString();

        if(serialNo.isEmpty()){
            Toast.makeText(mContext,"Enter Serial No", Toast.LENGTH_SHORT).show();
        }else if (postSiNoDetailDaoList != null && postSiNoDetailDaoList.size() > 0) {
            if (!contains(postSiNoDetailDaoList, serialNo)) {
                postSiNoDetailDaoList.add(new PostSiNoDetailDao(0,serialNo,"","","I","ACTUAL",lotno));
                editTextSerialNo.setText("");
                setAdapter();
            } else {
                showErrorDialog("Already Inserted");
            }

        } else {

            postSiNoDetailDaoList.add(new PostSiNoDetailDao(0,serialNo,"","","I","ACTUAL",lotno));
            editTextSerialNo.setText("");
            setAdapter();

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

    private boolean contains(List<PostSiNoDetailDao> list, String name) {
        for (PostSiNoDetailDao item : list) {
            if (item.getIn_slno().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private void setAdapter() {
        serialNoListAdapter=new SerialNoListAdapter(mContext,postSiNoDetailDaoList,this);
        recyclerViewSerial.setAdapter(serialNoListAdapter);
        serialNoListAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteItem(int position) {
        postSiNoDetailDaoList.remove(position);
        serialNoListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
////            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            Bundle extras = data.getExtras();
//            thePic = (Bitmap) extras.get("data");
//            cap.setImageBitmap(thePic);
//            vcap.setImageBitmap(thePic);
//        }

        if (resultCode == RESULT_OK) {

            if(requestCode == 49374)
            {


////                dialog = new Dialog(FDR.this);
////                Log.e("RCODE",""+data.getStringExtra("SCAN_RESULT"));
////                String scanvalue = data.getStringExtra("SCAN_RESULT");
////                loadFarmer(scanvalue,"","");
                IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                if (scanningResult != null) {
                    String scanContent = scanningResult.getContents();
                    String scanFormat = scanningResult.getFormatName();
                    // process received data
                    processScannedData(scanContent);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }

        }



    }
    protected void processScannedData(String scanData){
        Log.e("SCAN DATA",scanData);
        XmlPullParserFactory pullParserFactory;
        try {
            // init the parserfactory
            pullParserFactory = XmlPullParserFactory.newInstance();
            // get the parser
            XmlPullParser parser = pullParserFactory.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            String fstring2 = null;
            try {

                Log.e("Scan Data",""+scanData);
                editTextSerialNo.setText(scanData);
            }
            catch(Exception e)
            {

            }






            parser.setInput(new StringReader(scanData));




            // display the data on screen
            //  displayScannedData();
        } catch (XmlPullParserException e) {

            Log.e("AAdhar",""+e);
            e.printStackTrace();
        }
    }//
}
