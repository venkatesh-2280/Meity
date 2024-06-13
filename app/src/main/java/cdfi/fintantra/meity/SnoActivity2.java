package cdfi.fintantra.meity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class SnoActivity2 extends AppCompatActivity implements View.OnClickListener{
    private String userName;
    private Context mContext;
    private TextView textViewTitle;
    ArrayList sn = new ArrayList();
    ArrayList qp1 = new ArrayList();
    ArrayList qp2 = new ArrayList();
    ImageView qrcode;
    String ts,pc;
    IntentIntegrator qrScan;
    private EditText editTextSerialNo;

    private Button buttonAdd,buttonClose,buttonCancel;
    List<Pojosno> slist;
    private RecyclerView recyclerViewSerial;
    SnoActivity2.MyRecyclerViewAdapterfarmer adapterf;
    private String orgnId, locnId, userId, localeId;




    private String lotno;
    SQLiteDatabase dbs;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sno);
        initView();
    }
    private void initView() {

        getSupportActionBar().hide();
        textViewTitle = (TextView) findViewById(R.id.txt_title);
        textViewTitle.setText("Welcome " + userName + "\n" + "Sale Entry");
        db = new DBHelper(SnoActivity2.this);
        dbs = db.getWritableDatabase();
        editTextSerialNo = (EditText) findViewById(R.id.edt_serialno);
        buttonAdd = (Button) findViewById(R.id.but_add);
        buttonClose = (Button) findViewById(R.id.but_close);
        buttonCancel = (Button) findViewById(R.id.but_cancel);

        slist = new ArrayList<>();
        Intent i = getIntent();
        ts = i.getStringExtra("TS");


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
                qrScan = new IntentIntegrator(SnoActivity2.this);
                qrScan.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                qrScan.setPrompt("Scan Bar Code");
                qrScan.setBeepEnabled(true);
                qrScan.setCameraId(0);
                qrScan.setOrientationLocked(false);




                qrScan.initiateScan();
            }
        });
        //  postSiNoDetailDaoList=new ArrayList<>();

        buttonAdd.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
        buttonClose.setOnClickListener(this);

        adapterf = new SnoActivity2.MyRecyclerViewAdapterfarmer(this, slist);
        fetchsn();





    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.but_add:
                addSerialNo();

                break;
            case R.id.but_close:

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
        Log.e("TST",""+ts);
        if(serialNo.isEmpty()){
            showErrorDialog("Empty Serial No");
        } else {

            if(sn.contains(serialNo))
            {
                showErrorDialog("Serial No Already Exists");
            }
            else
            {
                db.insertsno(serialNo,"",ts);
                editTextSerialNo.setText("");
                fetchsn();
                sn.add(serialNo);
            }



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

    public class MyRecyclerViewAdapterfarmer extends RecyclerView.Adapter<SnoActivity2.MyRecyclerViewAdapterfarmer.ViewHolder> {

        private List<Pojosno> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        public MyRecyclerViewAdapterfarmer(Context context, List<Pojosno> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        @NonNull
        @Override
        public SnoActivity2.MyRecyclerViewAdapterfarmer.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.serial_list_screen2, parent, false);
            return new SnoActivity2.MyRecyclerViewAdapterfarmer.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SnoActivity2.MyRecyclerViewAdapterfarmer.ViewHolder holder, int position) {
            final Pojosno pojofar = mData.get(position);
            holder.name.setText(pojofar.getSn());





            holder.img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbs.delete("sno", "id" + "=" + pojofar.getId(), null);

                    sn.remove(pojofar.getSn());
                    fetchsn();

                }
            });


        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name, fhw, ph, lcn, ty, t5;

            ImageView img_delete;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.txt_serialNo);

                img_delete = itemView.findViewById(R.id.img_delete);

            }
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        fetchsn();

    }
    public void fetchsn()
    {
        Cursor cursor = dbs.query("sno", new String[]{"id","v1","v2","v3"
                }, "v3" + "=? COLLATE NOCASE",
                new String[]{ts}, null, null, null, null);
        slist.clear();
        sn.clear();
        //  Cursor cursor = dbs.rawQuery(selectQuery, null);

        if(cursor.getCount()==0)
        {
            recyclerViewSerial.setAdapter(adapterf);
            adapterf.notifyDataSetChanged();
        }

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pojosno pojosno = new Pojosno();

                pojosno.setId(cursor.getString(0));
                pojosno.setSn(cursor.getString(1));
                sn.add(cursor.getString(1));



                slist.add(pojosno);
                // array2.add(cursor.getString(11));
                // Log.e("VAL",""+cursor.getString(11));
                recyclerViewSerial.setAdapter(adapterf);
                adapterf.notifyDataSetChanged();
            } while (cursor.moveToNext());

        }
    }

}