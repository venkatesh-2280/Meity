package cdfi.fintantra.meity.Pawhs.recestpurchase;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class RecEstPurchaseActivity extends AppCompatActivity  {

//    private PAWHSApplication pawhsApplication;
//    private ApiService mAPIService;
//    private String userName;
//    private Context mContext;
//    private TextView textViewTitle;
//    private TextView textViewSearchFarmerCode;
//    ImageView capturebill;
//    RadioButton rbutton1,rbutton2;
//    String qpdv="";
//    String vst2 = "";
//    String radiovalue="";
//    private List<Estqtydao> postQtyDetailDaoList;
//    ArrayList varitys = new ArrayList();
//    ArrayList varitysc = new ArrayList();
//    ArrayList sp4 = new ArrayList();
//    ArrayList sp1 = new ArrayList();
//    ArrayList sp1c = new ArrayList();
//    ArrayList sp2 = new ArrayList();
//    ArrayList sp2c = new ArrayList();
//    ArrayList sp3 = new ArrayList();
//    ArrayList sp3c = new ArrayList();
//    ArrayList sp4c = new ArrayList();
//
//    private LinearLayout linearFarmerCode,linearFarmerName,linearItem;
//
//    private EditText editTextPrice, editTextRemarks, editTextLotNO,lrpno;
//    private TextView textViewValue;
//    private TextView textViewDate;
//    private TextView textViewIncrease, textViewDecrese;
//    private TextView textViewFarmerName, textViewMemberType, textViewFarmerItem,txt_uom,txt_var;
//    private Button buttonSave, buttonView, buttonCancel;
//
//    private RelativeLayout progressLayout;
//
//    private String orgnId, locnId, userId, localeId, orgnCode;
//
//    private List<FormerDao> formerDaoList;
//    private SQLiteDataBaseHandler db;
//    String ui;
//    String imageFilePath,imageFilePath2;
//    String txtqp,txtqp2,txtqp3,txtqp4;
//    Bitmap thePic,thePic2;
//
//    String code;
//    Uri picUri;
//    TextView txt_qp;
//    EditText lrpname;
//    String encodedImage = "0",encodedImage2 = "0";
//    ByteArrayOutputStream byteArrayOutputStream,byteArrayOutputStream2;
//    final int CAMERA_CAPTURE = 16;
//    private static final int CAMERA_REQUEST = 1888;
//    private String farmerCode, farmerName, memberType;
//    private String productCode, productName, aggCode;
//
//    private List<String> farmerCodeList;
//    private List<String> farmerNameList;
//    private List<String> productItemList,cat,var;
//    private List<PmListDao> pmListDaoList;
//    private Dialog dialog;
//    AutoCompleteTextView textViewQty,textViewQty2;
//    double numtest = 0;
//
//    double num1 = 0;
//    double num2 = 0;
//    Spinner spinner,spinner2,spinner3;
//    boolean isNetwork;
//
//    int lastInsertedRowId;
//    String[] vstatus = { "Tap For Select", "Milled &amp; Loose","Cob","Milled &amp; Packing"};
//
//    private List<SubmitRecEstPurchaseDao> submitRecEstPurchaseDaoList;
//    private String edtEstValue="";
//    private SubmitRecEstPurchaseDao submitRecEstPurchaseDaoEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.rec_est_purchase_screen);
//        mContext = this;
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
//        //  db.deleteSubmitRecEstPurchase(mContext);
//
//        initView();
//
//    }
//
//    private void initView() {
//
//        mAPIService = ApiUtils.getAPIService();
//        textViewTitle = (TextView) findViewById(R.id.txt_title);
//        textViewTitle.setText("Welcome " + userName + "\n" + "Lot Identification");
//
//
//        editTextPrice = (EditText) findViewById(R.id.edt_est_price);
//        editTextPrice.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(6, 2)});
//
//        textViewValue = (TextView) findViewById(R.id.edt_est_value);
//        editTextRemarks = (EditText) findViewById(R.id.edt_remarks);
//        editTextLotNO = (EditText) findViewById(R.id.edt_lotno);
//        editTextLotNO.setEnabled(false);
//
//        textViewDate = (TextView) findViewById(R.id.txtview_date);
//        textViewSearchFarmerCode = (TextView) findViewById(R.id.txt_search_formercode);
//        textViewFarmerName = (TextView) findViewById(R.id.txt_farmerName);
//        textViewMemberType = (TextView) findViewById(R.id.txt_farmerMemberType);
//        textViewFarmerItem = (TextView) findViewById(R.id.txt_farmerItem);
//
//        linearFarmerCode=(LinearLayout)findViewById(R.id.linear_farmercode);
//        linearFarmerName=(LinearLayout)findViewById(R.id.linear_farmername);
//        linearItem=(LinearLayout)findViewById(R.id.linear_item);
//        lrpname = (EditText)findViewById(R.id.lrpname);
//        lrpno = (EditText)findViewById(R.id.lrpno);
//
//        textViewIncrease = (TextView) findViewById(R.id.txt_increase);
//        textViewDecrese = (TextView) findViewById(R.id.txt_decrease);
//        textViewQty = (AutoCompleteTextView) findViewById(R.id.txt_est_qty);
//        textViewQty2 = (AutoCompleteTextView) findViewById(R.id.txt_est_qty2);
//        txt_qp = (TextView)findViewById(R.id.txt_qp);
//        rbutton1 = (RadioButton) findViewById(R.id.radioButton);
//        rbutton2 = (RadioButton) findViewById(R.id.radioButton2);
//
//        postQtyDetailDaoList = new ArrayList<>();
//        txt_qp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final Dialog dialog = new Dialog(RecEstPurchaseActivity.this);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setCancelable(false);
//                dialog.setContentView(R.layout.qpar);
//                int width = WindowManager.LayoutParams.MATCH_PARENT;
//                int height = WindowManager.LayoutParams.MATCH_PARENT;
//
//                dialog.getWindow().setLayout(width, height);
////                final RadioButton rb1 = (RadioButton) dialog.findViewById(R.id.rb1);
////                final RadioButton rb2 = (RadioButton) dialog.findViewById(R.id.rb2);
////                final RadioButton rb3 = (RadioButton) dialog.findViewById(R.id.rb3);
////                final RadioButton rb4 = (RadioButton) dialog.findViewById(R.id.rb4);
////                final RadioButton rb5 = (RadioButton) dialog.findViewById(R.id.rb5);
////                final RadioButton rb6 = (RadioButton) dialog.findViewById(R.id.rb6);
////                final RadioButton rb7 = (RadioButton) dialog.findViewById(R.id.rb7);
////                final RadioButton rb8 = (RadioButton) dialog.findViewById(R.id.rb8);
////                final RadioButton rb9 = (RadioButton) dialog.findViewById(R.id.rb9);
////                final RadioButton rb10 = (RadioButton) dialog.findViewById(R.id.rb10);
////                final RadioButton rb11 = (RadioButton) dialog.findViewById(R.id.rb11);
//
//                Button close = (Button) dialog.findViewById(R.id.close);
//                final Spinner sn1 = (Spinner) dialog.findViewById(R.id.spinner);
//                final Spinner sn2 = (Spinner) dialog.findViewById(R.id.spinner2);
//                final Spinner sn3 = (Spinner) dialog.findViewById(R.id.spinner3);
//                final Spinner sn4 = (Spinner) dialog.findViewById(R.id.spinner4);
//                final SQLiteDatabase dbs = db.getWritableDatabase();
//                sp1.clear();
//                sp1c.clear();
//                sp2.clear();
//                sp2c.clear();
//                sp3.clear();
//                sp3c.clear();
//                sp4.clear();
//                sp4c.clear();
//                String selectQuery = "SELECT  * FROM qparest";
//
//                Cursor cursor = dbs.rawQuery(selectQuery, null);
//
//                if(cursor.moveToFirst())
//                {
//
//
//                        sp1c.add("0");
//                        sp1.add("Tap For Select");
//                    sp2c.add("0");
//                    sp2.add("Tap For Select");
//                    sp3c.add("0");
//                    sp3.add("Tap For Select");
//                    sp4c.add("0");
//                    sp4.add("Tap For Select");
//                        do {
//                            if(cursor.getString(3).equalsIgnoreCase("Moisture")) {
//                                String[] val = cursor.getString(4).split("/");
//
//                                for (int i = 0; i < val.length; i++) {
//
//                                    sp1.add(val[i]);
//                                }
//                            }
//                            if(cursor.getString(3).equalsIgnoreCase("Live Insect")) {
//                                String[] val = cursor.getString(4).split("/");
//
//                                for (int i = 0; i < val.length; i++) {
//
//                                    sp2.add(val[i]);
//                                }
//                            }
//                            if(cursor.getString(3).equalsIgnoreCase("Fungus")) {
//                                String[] val = cursor.getString(4).split("/");
//
//                                for (int i = 0; i < val.length; i++) {
//
//                                    sp3.add(val[i]);
//                                }
//                            }
//                            if(cursor.getString(3).equalsIgnoreCase("Damage")) {
//                                String[] val = cursor.getString(4).split("/");
//
//                                for (int i = 0; i < val.length; i++) {
//
//                                    sp4.add(val[i]);
//                                }
//                            }
//
//
//                            // total number of textviews to add
//
//
//                        } while (cursor.moveToNext());
//
//
//                    final ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(mContext,
//                            R.layout.spinnertext, sp1);
//
//                    sn1.setAdapter(adapterlist2n);
//                    final ArrayAdapter<String> adapterlist3n = new ArrayAdapter<String>(mContext,
//                            R.layout.spinnertext, sp2);
//
//                    sn2.setAdapter(adapterlist3n);
//                    final ArrayAdapter<String> adapterlist4n = new ArrayAdapter<String>(mContext,
//                            R.layout.spinnertext, sp3);
//
//                    sn3.setAdapter(adapterlist4n);
//                    final ArrayAdapter<String> adapterlist5n = new ArrayAdapter<String>(mContext,
//                            R.layout.spinnertext, sp4);
//
//                    sn4.setAdapter(adapterlist5n);
//                    if(qpdv.equalsIgnoreCase(""))
//                    {
//
//                    }
//                    else
//                    {
//                        String[] v = qpdv.split("@");
//                        for(int i = 0;i<v.length;i++)
//                        {
//                            String[] v2  = v[i].split("-");
//                            if(i == 0) {
//                                if (v2[1].equalsIgnoreCase("0")) {
//
//                                } else {
//                                    //  Log.e("OKK",""+v2[1].trim());
//                                    sn1.setSelection(((ArrayAdapter<String>) sn1.getAdapter()).getPosition(v2[1]));
//                                }
//                            }
//                            if(i == 1) {
//                                if (v2[1].equalsIgnoreCase("0")) {
//
//                                } else {
//                                    sn2.setSelection(((ArrayAdapter<String>) sn2.getAdapter()).getPosition(v2[1]));
//                                }
//                            }
//                            if(i == 2) {
//                                if (v2[1].equalsIgnoreCase("0")) {
//
//                                } else {
//                                    sn3.setSelection(((ArrayAdapter<String>) sn3.getAdapter()).getPosition(v2[1]));
//                                }
//                            }
//                            if(i == 3) {
//                                if (v2[1].equalsIgnoreCase("0")) {
//
//                                } else {
//                                    sn4.setSelection(((ArrayAdapter<String>) sn4.getAdapter()).getPosition(v2[1]));
//                                }
//                            }
//                        }
//                    }
//                } //
//
//
//
//                close.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        String v="0",v2="0",v3="0",v4="0";
//                        if(sn1.getSelectedItem().toString().equalsIgnoreCase("Tap For Select"))
//                        {
//
//                        }
//                        else
//                        {
//                            v=sn1.getSelectedItem().toString();
//                        }
//                        if(sn2.getSelectedItem().toString().equalsIgnoreCase("Tap For Select"))
//                        {
//
//                        }
//                        else
//                        {
//                            v2=sn2.getSelectedItem().toString();
//                        }
//                        if(sn3.getSelectedItem().toString().equalsIgnoreCase("Tap For Select"))
//                        {
//
//                        }
//                        else
//                        {
//                            v3=sn3.getSelectedItem().toString();
//                        }
//                        if(sn4.getSelectedItem().toString().equalsIgnoreCase("Tap For Select"))
//                        {
//
//                        }
//                        else
//                        {
//                            v4=sn4.getSelectedItem().toString();
//                        }
//                        if(v.equalsIgnoreCase("0")&&v2.equalsIgnoreCase("0")&&v3.equalsIgnoreCase("0")&&v4.equalsIgnoreCase("0"))
//                        {
//                            qpdv="";
//
//                        }
//                        else
//                        {
//                            qpdv="Moisture-"+v+"@Live Insect-"+v2+"@Fungus-"+v3+"@Damage-"+v4;
//                            txt_qp.setText("Moisture-"+v+" \n"+"Live Insect-"+v2+" \n"+"Fungus-"+v3+" \n"+"Damage-"+v4);
//
//
//                        }
//
//                        Log.e("OKK",""+qpdv);
//
//
//
//                        dialog.dismiss();
//
//
//
//                    }
//                });
//
//
//                dialog.show();
//            }
//        });
//        spinner3 = (Spinner)findViewById(R.id.spinner3);
//        final SQLiteDatabase dbs = db.getWritableDatabase();
//
//        String selectQuery = "SELECT  * FROM vstatus";
//
//         Cursor cursor = dbs.rawQuery(selectQuery, null);
//
//        if(cursor.moveToFirst())
//        {
//            varitysc.add("0");
//            varitys.add("Tap For Select");
//            do
//            {
//
//                varitysc.add(cursor.getString(2));
//                varitys.add(cursor.getString(3));
//
//
//
//
//
//
//                // total number of textviews to add
//
//
//            }while(cursor.moveToNext());
//            final ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(mContext,
//                    R.layout.spinnertext, varitys);
//
//            spinner3.setAdapter(adapterlist2n);
//        } //
//
//        capturebill = (ImageView)findViewById(R.id.capturebill);
//        capturebill.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                try {
//
//                    picUri = FileProvider.getUriForFile(RecEstPurchaseActivity.this, getApplicationContext().getPackageName() + ".provider", createImageFile());
//                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
//                    takePictureIntent.putExtra("return-data", true);
//                    startActivityForResult(takePictureIntent, CAMERA_CAPTURE);// convert path to Uri
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        textViewQty2.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//
//                try {
//                    if(s.length() == 0)
//                    {
//                        numtest = 0;
//                    }
//                    else
//                    {
//                        numtest = Double.parseDouble(s.toString());
//                    }
//                }
//                catch(Exception e)
//                {
//
//                }
//
//
//
//
//
//                //  numtest = Double.parseDouble(s.toString());
//
//                // TODO Auto-generated method stub
//            }
//        });
//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
//                this, android.R.layout.simple_list_item_1, getResources()
//                .getStringArray(R.array.qty));
//        String selection;
//        textViewQty.setAdapter(arrayAdapter);
//        textViewQty.setCursorVisible(false);
//        textViewQty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                textViewQty.showDropDown();
//                String sqty = (String) parent.getItemAtPosition(position);
//                numtest = Double.parseDouble(sqty);
//                textViewQty2.setText(sqty);
//
//            }
//        });
//
//        textViewQty.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View arg0) {
//                textViewQty.showDropDown();
//            }
//        });
//        textViewQty.setOnTouchListener(new View.OnTouchListener() {
//
//            @SuppressLint("ClickableViewAccessibility")
//            @Override
//            public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
//                // TODO Auto-generated method stub
//                textViewQty.showDropDown();
//
//                return false;
//            }
//        });
//        txt_uom = (TextView) findViewById(R.id.txt_uom);
//        txt_var = (TextView) findViewById(R.id.txt_var);
//        buttonSave = (Button) findViewById(R.id.but_save);
//        buttonView = (Button) findViewById(R.id.but_view);
//        buttonCancel = (Button) findViewById(R.id.but_cancel);
//
//        progressLayout = (RelativeLayout) findViewById(R.id.progressLayout);
//
//        formerDaoList = new ArrayList<>();
//        pmListDaoList = new ArrayList<>();
//
//        submitRecEstPurchaseDaoList = new ArrayList<>();
//
//
//
//
//        buttonSave.setOnClickListener(this);
//        buttonView.setOnClickListener(this);
//        buttonCancel.setOnClickListener(this);
//        textViewDate.setOnClickListener(this);
//        linearFarmerCode.setOnClickListener(this);
//        linearFarmerName.setOnClickListener(this);
//        linearItem.setOnClickListener(this);
//
//        textViewIncrease.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                numtest = numtest + 0.5;
//                textViewQty2.setText("" + numtest);
//                if (!editTextPrice.getText().toString().isEmpty()) {
//                    if(!editTextPrice.getText().toString().equalsIgnoreCase(".")) {
//                        num1 = Double.parseDouble(textViewQty2.getText().toString());
//                        num2 = Double.parseDouble(editTextPrice.getText().toString());
//                        double multipleValue = num1 * num2;
//                        double value = Double.parseDouble(new DecimalFormat("##.##").format(multipleValue));
//                        //  textViewValue.setText("" + multipleValue);
//                        // textViewValue.setText("" + value);
//                        textViewValue.setText("" + new DecimalFormat("##.##").format(value));
//                    }
//                } else {
//                    textViewValue.setText("");
//                }
//            }
//        });
//
//        textViewDecrese.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (numtest < 0) {
//                    numtest = 0;
//                    textViewQty2.setText(numtest + "");
//                }
//                if (numtest > 0) {
//                    numtest = numtest - 0.5;
//                    textViewQty2.setText(numtest + "");
//                    if (!editTextPrice.getText().toString().isEmpty()) {
//                        if(!editTextPrice.getText().toString().equalsIgnoreCase(".")) {
//                            num1 = Double.parseDouble(textViewQty2.getText().toString());
//                            num2 = Double.parseDouble(editTextPrice.getText().toString());
//                            double multipleValue = num1 * num2;
//                            double value = Double.parseDouble(new DecimalFormat("##.##").format(multipleValue));
//                            //  textViewValue.setText("" + multipleValue);
//                            // textViewValue.setText("" + value);
//                            textViewValue.setText("" + new DecimalFormat("##.##").format(value));
//                        }
//                    } else {
//                        textViewValue.setText("");
//                    }
//                }
//            }
//        });
//
//        editTextPrice.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//                if(textViewQty2.getText().toString().isEmpty()) {
//
//                }
//                else
//                {
//                    try {
//                        if (!editTextPrice.getText().toString().isEmpty()) {
//                            if(!editTextPrice.getText().toString().equalsIgnoreCase(".")){
//                                num1 = Double.parseDouble(textViewQty2.getText().toString());
//                                num2 = Double.parseDouble(editTextPrice.getText().toString());
//                                double multipleValue = num1 * num2;
//                                double value = Double.parseDouble(new DecimalFormat("##.##").format(multipleValue));
//                                //  textViewValue.setText("" + multipleValue);
//                                // textViewValue.setText("" + value);
//                                textViewValue.setText("" + new DecimalFormat("##.##").format(value));
//                            }
//                   /* num1 = Double.parseDouble(textViewQty.getText().toString());
//                    num2 = Double.parseDouble(editTextPrice.getText().toString());
//                    double multipleValue = num1 * num2;
//                    double value = Double.parseDouble(new DecimalFormat("##.##").format(multipleValue));
//                    //  textViewValue.setText("" + multipleValue);
//                    textViewValue.setText("" + value);*/
//
//                        } else {
//                            textViewValue.setText("");
//                        }
//                    }
//                    catch (Exception e)
//                    {
//
//                    }
//
//                }
//
//
//            }
//        });
//        textViewQty2.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//                if(textViewQty2.getText().toString().isEmpty()) {
//
//                }
//                else
//                {
//                    try {
//                        if (!editTextPrice.getText().toString().isEmpty()) {
//                            if(!editTextPrice.getText().toString().equalsIgnoreCase(".")){
//                                num1 = Double.parseDouble(textViewQty2.getText().toString());
//                                num2 = Double.parseDouble(editTextPrice.getText().toString());
//                                double multipleValue = num1 * num2;
//                                double value = Double.parseDouble(new DecimalFormat("##.##").format(multipleValue));
//                                //  textViewValue.setText("" + multipleValue);
//                                // textViewValue.setText("" + value);
//                                textViewValue.setText("" + new DecimalFormat("##.##").format(value));
//                            }
//                   /* num1 = Double.parseDouble(textViewQty.getText().toString());
//                    num2 = Double.parseDouble(editTextPrice.getText().toString());
//                    double multipleValue = num1 * num2;
//                    double value = Double.parseDouble(new DecimalFormat("##.##").format(multipleValue));
//                    //  textViewValue.setText("" + multipleValue);
//                    textViewValue.setText("" + value);*/
//
//                        } else {
//                            textViewValue.setText("");
//                        }
//                    }
//                    catch (Exception e)
//                    {
//
//                    }
//
//                }
//
//
//            }
//        });
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//        switch (id) {
//            case R.id.but_save:
//
//                callSaveMethod();
//
//                break;
//            case R.id.but_cancel:
//                finish();
//                break;
//            case R.id.but_view:
//                Intent intent=new Intent(mContext,RecEstPurChaseViewActivity.class);
//                startActivityForResult(intent,EST_EDT_VALUE);
//                break;
//            case R.id.txtview_date:
//                showDateDialog();
//                break;
//            case R.id.linear_item:
//                showItemSearchDialog();
//                break;
//            case R.id.linear_farmercode:
//                showFarmerCodeDialog();
//                break;
//            case R.id.linear_farmername:
//                showFarmerNameDialog();
//                break;
//            default:
//                break;
//        }
//
//    }
//
//
//    private void callSaveMethod() {
//        isNetwork = Utility.checkConnectivity(getApplicationContext());
//
//        String qty = textViewQty2.getText().toString();
//        String farmercode = textViewSearchFarmerCode.getText().toString();
//        String itemCode = textViewFarmerItem.getText().toString();
//        String price = editTextPrice.getText().toString();
//        String value = textViewValue.getText().toString();
//        String remarks = editTextRemarks.getText().toString();
//        final String lrpn = lrpname.getText().toString();
//        final String lrpmn = lrpno.getText().toString();
//        final String vstatus = spinner3.getSelectedItem().toString();
//
//        if(rbutton1.isChecked())
//        {
//            radiovalue="Acceptable";
//        }
//        else if(rbutton2.isChecked())
//        {
//            radiovalue="Not-Acceptable";
//        }
//
//
//        if (farmercode.isEmpty()) {
//            showErrorDialog("Please Select FarmerCode ");
//        } else if (itemCode.isEmpty()) {
//            showErrorDialog("Please Select Item ");
//        } else if (qty.equalsIgnoreCase("0")||qty.equalsIgnoreCase("0.0")) {
//            showErrorDialog("Estimated Qty Mustbe Greater Than 0 ");
//        } else if (textViewDate.getText().toString().isEmpty()) {
//            showErrorDialog("Please Select PickUp Date ");
//        } else if (price.isEmpty()) {
//            showErrorDialog("Please Enter Price");
//        } else if (value.isEmpty()) {
//            showErrorDialog("Please Enter Value ");
//        } else if (!(Double.parseDouble(price) > 0)) {
//            //  Toast.makeText(mContext, "Price Should not be 0 ", Toast.LENGTH_SHORT).show();
//            showErrorDialog("Price Should not be 0 ");
//        }
//        else if (vstatus.equalsIgnoreCase("Tap For Select")) {
//            showErrorDialog("Please Select Variety Status");
//        }
//        else if (encodedImage.equalsIgnoreCase("0")) {
//            showErrorDialog("Please Capture  Image");
//        }
//        else if (lrpn.isEmpty()) {
//            showErrorDialog("Please Enter LRP Name");
//        }
//        else if (lrpmn.isEmpty() && lrpmn.length()<10) {
//            showErrorDialog("Invalid LRP Mobile No");
//        }
//        else if(radiovalue.equalsIgnoreCase(""))
//        {
//            showErrorDialog("Please Select Lot Acceptable or Not");
//        }
//        else if(qpdv.equalsIgnoreCase(""))
//        {
//            showErrorDialog("Please Select Quality Parameter");
//        }
//        else {
//
//            final SQLiteDatabase dbs = db.getWritableDatabase();
//            Cursor cursor2 = dbs.query("vstatus", new String[]{"v1", "v2", "v3"
//                    }, "v3" + "=?",
//                    new String[]{vstatus}, null, null, null, null);
//
//
//            if(cursor2.moveToFirst())
//            {
//                do {
//                    vst2= cursor2.getString(1);
//
//                }while(cursor2.moveToNext());
//            }
//
//
//            String qp = qpdv;
//            String[] qps = qp.split("@");
//            postQtyDetailDaoList.clear();
//
//            for (int q = 0; q<qps.length;q++) {
//
//                String[] v = qps[q].split("-");
//                Log.e("OKK", "" + v[0]);
//
//
//                if (v[1].equalsIgnoreCase("0")) {
//
//                } else {
//                    Cursor cursorq = dbs.query("qparest", new String[]{"v1", "v2", "v3", "v4"
//                            }, "v3" + " LIKE ?",
//                            new String[]{"%" + v[0] + "%"}, null, null, null, null);
//
//
//                    if (cursorq.moveToFirst()) {
//                        do {
//                            postQtyDetailDaoList.add(new Estqtydao(0, cursorq.getString(1), 0.00, 0.00, v[1], "I"));
//                            // postQtyDetailDaoList.add(new Estqtydao(0,cursorq.getString(1),0.00,0.00,v[1],"I"));
//
//                        } while (cursorq.moveToNext());
//                    }
//                }
//
//            }
//
//
//
//
//            String[] d = textViewDate.getText().toString().split("-");
//
//            String estDate = d[2]+"-"+d[1]+"-"+d[0];
//            submitRecEstPurchaseDaoList.clear();
//            if(!edtEstValue.equalsIgnoreCase("editestimate")){
//                if (isNetwork == true) {
//
//                    submitRecEstPurchaseDaoList = db.getAllSubmitRecEstPurchaseDaoForValidate();
//
//                    if (submitRecEstPurchaseDaoList != null && submitRecEstPurchaseDaoList.size() > 0) {
//                        if (!contains(submitRecEstPurchaseDaoList, estDate, qty)) {
//
//                            progressLayout.setVisibility(View.VISIBLE);
//                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//
//                            NepsHeaderDao nepsHeaderDao = new NepsHeaderDao();
//                            nepsHeaderDao.setIn_Estm_rowid(0);
//                            nepsHeaderDao.setIn_LotNo("");
//                            nepsHeaderDao.setIn_Farmer_Code(farmerCode);
//                            nepsHeaderDao.setIn_Farmer_Name(farmerName);
//                            nepsHeaderDao.setIn_Member_Type(memberType);
//                            nepsHeaderDao.setIn_Item_Code(productCode);
//                            nepsHeaderDao.setIn_Item_Name(productName);
//                            nepsHeaderDao.setIn_Estimated_Qty(Double.parseDouble(qty));
//                            nepsHeaderDao.setIn_Estimated_Price(Double.parseDouble(price));
//                            nepsHeaderDao.setIn_Estimated_Value(Double.parseDouble(value));
//                            nepsHeaderDao.setIn_Estimated_PickDate(estDate);
//                            nepsHeaderDao.setIn_Remarks(remarks);
//                            nepsHeaderDao.setIn_rowtimestamp(estDate);
//                            nepsHeaderDao.setIn_mode_flag("I");
//                            nepsHeaderDao.setIn_Estimated_attach(encodedImage);
//                            nepsHeaderDao.setIn_variety_status(vst2);
//                            nepsHeaderDao.setIn_Estimated_Status(radiovalue);
//                            nepsHeaderDao.setIn_LRP_Name(lrpn);
//                            nepsHeaderDao.setIn_LRP_Mobile_no(lrpmn);
//                            nepsHeaderDao.setIn_status("Maker");
//                            NepsContextDao nepsContextDao = new NepsContextDao();
//                            nepsContextDao.setOrgnId(orgnCode);
//                            //  nepsContextDao.setLocnId(locnId);
//                            nepsContextDao.setLocnId(ApiUtils.instance);
//                            nepsContextDao.setUserId(userId);
//                            nepsContextDao.setLocaleId(localeId);
//                            nepsContextDao.setNepsHeaderDao(nepsHeaderDao);
//                            nepsContextDao.setPostQtyDetailDaoList(postQtyDetailDaoList);
//                            NepsDocumentDao nepsDocumentDao = new NepsDocumentDao();
//                            nepsDocumentDao.setNepsContextDao(nepsContextDao);
//
//                            NewEstimateProcSaveDao newEstimateProcSaveDao = new NewEstimateProcSaveDao();
//                            newEstimateProcSaveDao.setNepsDocumentDao(nepsDocumentDao);
//
//                            mAPIService.postNewEstimateProcSaveDetails(newEstimateProcSaveDao)
//                                    .subscribeOn(Schedulers.io())
//                                    .observeOn(AndroidSchedulers.mainThread())
//                                    .subscribe(new Subscriber<NewEstimateProcSaveDao>() {
//                                        @Override
//                                        public void onCompleted() {
//
//                                        }
//
//                                        @Override
//                                        public void onError(Throwable e) {
//
//                                        }
//
//                                        @Override
//                                        public void onNext(NewEstimateProcSaveDao newEstimateProcSaveDao) {
//                                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//                                            progressLayout.setVisibility(View.GONE);
//
//
//                                            if (newEstimateProcSaveDao.getNepsContextDao().getNepsheaderDao().getIn_LotNo() != null) {
//                                                // db.updateLotNO(lastInsertedRowId, newEstimateProcSaveDao.getNepsContextDao().getNepsheaderDao().getIn_LotNo(), "Yes");
//                                                NepsHeaderDao nepsHeaderDao1 = newEstimateProcSaveDao.getNepsContextDao().getNepsheaderDao();
//                                                String dateValue = Utility.splitTValue(nepsHeaderDao1.getIn_Estimated_PickDate());
//                                                SubmitRecEstPurchaseDao submitRecEstPurchaseDao = new SubmitRecEstPurchaseDao(1, nepsHeaderDao1.getIn_Farmer_Code(), nepsHeaderDao1.getIn_Farmer_Name(), nepsHeaderDao1.getIn_Member_Type(),
//                                                        nepsHeaderDao1.getIn_Item_Code(), nepsHeaderDao1.getIn_Item_Name(), String.valueOf(nepsHeaderDao1.getIn_Estimated_Qty()),
//                                                        String.valueOf(nepsHeaderDao1.getIn_Estimated_Price()), String.valueOf(nepsHeaderDao1.getIn_Estimated_Value()), dateValue, nepsHeaderDao1.getIn_Remarks(), nepsHeaderDao1.getIn_LotNo(), "0", dateValue, "I", "Yes",nepsHeaderDao1.getIn_Estimated_attach(),vst2,qpdv,radiovalue,lrpn,lrpmn);
//                                                db.addAllSubmitRecEstPurChaseOffline(submitRecEstPurchaseDao);
//                                                showLotNoDialog(newEstimateProcSaveDao.getNepsContextDao().getNepsheaderDao().getIn_LotNo());
//
//                                                textViewSearchFarmerCode.setText("");
//                                                textViewFarmerName.setText("");
//                                                textViewMemberType.setText("");
//                                                textViewDate.setText("");
//                                                textViewFarmerItem.setText("");
//                                                editTextPrice.setText("");
//                                                textViewValue.setText("");
//                                                editTextRemarks.setText("");
//                                                txt_uom.setText("");
//                                                txt_var.setText("");
//                                                lrpno.setText("");
//                                                editTextLotNO.setText("");
//                                                numtest = 0;
//                                                encodedImage="0";
//                                                qpdv="";
//                                                txt_qp.setText("Tap For Select");
//                                                lrpname.setText("");
//                                                rbutton1.setChecked(false);
//                                                rbutton2.setChecked(false);
//                                                spinner3.setSelection(0);
//                                                capturebill.setImageResource(0);
//                                                capturebill.setBackgroundResource(R.drawable.capture);
//                                                textViewQty.setText("" + numtest);
//                                                textViewQty2.setText("" + numtest);
//
//                                            } else {
//                                                showErrorDialog("Duplicate Entry.!");
//                                                textViewSearchFarmerCode.setText("");
//                                                textViewFarmerName.setText("");
//                                                textViewMemberType.setText("");
//                                                textViewDate.setText("");
//                                                textViewFarmerItem.setText("");
//                                                editTextPrice.setText("");
//                                                textViewValue.setText("");
//                                                editTextRemarks.setText("");
//                                                txt_uom.setText("");
//                                                lrpno.setText("");
//                                                txt_var.setText("");
//                                                encodedImage="0";
//                                                qpdv="";
//                                                txt_qp.setText("Tap For Select");
//                                                lrpname.setText("");
//                                                rbutton1.setChecked(false);
//                                                rbutton2.setChecked(false);
//                                                spinner3.setSelection(0);
//                                                capturebill.setImageResource(0);
//                                                capturebill.setBackgroundResource(R.drawable.capture);
//                                                editTextLotNO.setText("");
//                                                numtest = 0;
//                                                textViewQty.setText("" + numtest);
//                                                textViewQty2.setText("" + numtest);
//                                            }
//
//
//                                        }
//                                    });
//
//                        } else {
//                            textViewSearchFarmerCode.setText("");
//                            txt_uom.setText("");
//                            txt_var.setText("");
//                            qpdv="";
//                            txt_qp.setText("Tap For Select");
//                            lrpname.setText("");
//                            rbutton1.setChecked(false);
//                            rbutton2.setChecked(false);
//                            spinner3.setSelection(0);
//                            editTextLotNO.setText("");
//                            lrpno.setText("");
//                            textViewFarmerName.setText("");
//                            textViewMemberType.setText("");
//                            textViewDate.setText("");
//                            textViewFarmerItem.setText("");
//                            editTextPrice.setText("");
//                            textViewValue.setText("");
//                             encodedImage="0";
//                                                capturebill.setImageResource(0);
//                                                capturebill.setBackgroundResource(R.drawable.capture);
//                            editTextRemarks.setText("");
//                            numtest = 0;
//                            textViewQty.setText("" + numtest);
//                            textViewQty2.setText("" + numtest);
//                            showErrorDialog("Duplicate Entry.!");
//                        }
//                    } else {
//                        progressLayout.setVisibility(View.VISIBLE);
//                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//
//                        NepsHeaderDao nepsHeaderDao = new NepsHeaderDao();
//                        nepsHeaderDao.setIn_Estm_rowid(0);
//                        nepsHeaderDao.setIn_LotNo("");
//                        nepsHeaderDao.setIn_Farmer_Code(farmerCode);
//                        nepsHeaderDao.setIn_Farmer_Name(farmerName);
//                        nepsHeaderDao.setIn_Member_Type(memberType);
//                        nepsHeaderDao.setIn_Item_Code(productCode);
//                        nepsHeaderDao.setIn_Item_Name(productName);
//                        nepsHeaderDao.setIn_Estimated_Qty(Double.parseDouble(qty));
//                        nepsHeaderDao.setIn_Estimated_Price(Double.parseDouble(price));
//                        nepsHeaderDao.setIn_Estimated_Value(Double.parseDouble(value));
//                        nepsHeaderDao.setIn_Estimated_PickDate(estDate);
//                        nepsHeaderDao.setIn_Remarks(remarks);
//                        nepsHeaderDao.setIn_rowtimestamp(estDate);
//                        nepsHeaderDao.setIn_mode_flag("I");
//                        nepsHeaderDao.setIn_Estimated_attach(encodedImage);
//                        nepsHeaderDao.setIn_variety_status(vst2);
//                        nepsHeaderDao.setIn_Estimated_Status(radiovalue);
//                        nepsHeaderDao.setIn_LRP_Name(lrpn);
//                        nepsHeaderDao.setIn_LRP_Mobile_no(lrpmn);
//                        nepsHeaderDao.setIn_status("Maker");
//                        NepsContextDao nepsContextDao = new NepsContextDao();
//                        nepsContextDao.setOrgnId(orgnCode);
//                        //   nepsContextDao.setLocnId(locnId);
//                        nepsContextDao.setLocnId(ApiUtils.instance);
//                        nepsContextDao.setUserId(userId);
//                        nepsContextDao.setLocaleId(localeId);
//                        nepsContextDao.setNepsHeaderDao(nepsHeaderDao);
//                        nepsContextDao.setPostQtyDetailDaoList(postQtyDetailDaoList);
//                        NepsDocumentDao nepsDocumentDao = new NepsDocumentDao();
//                        nepsDocumentDao.setNepsContextDao(nepsContextDao);
//
//                        NewEstimateProcSaveDao newEstimateProcSaveDao = new NewEstimateProcSaveDao();
//                        newEstimateProcSaveDao.setNepsDocumentDao(nepsDocumentDao);
//
//                        mAPIService.postNewEstimateProcSaveDetails(newEstimateProcSaveDao)
//                                .subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(new Subscriber<NewEstimateProcSaveDao>() {
//                                    @Override
//                                    public void onCompleted() {
//
//                                    }
//
//                                    @Override
//                                    public void onError(Throwable e) {
//
//                                    }
//
//                                    @Override
//                                    public void onNext(NewEstimateProcSaveDao newEstimateProcSaveDao) {
//                                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//                                        progressLayout.setVisibility(View.GONE);
//
//
//                                        if (newEstimateProcSaveDao.getNepsContextDao().getNepsheaderDao().getIn_LotNo() != null) {
//                                            // db.updateLotNO(lastInsertedRowId, newEstimateProcSaveDao.getNepsContextDao().getNepsheaderDao().getIn_LotNo(), "Yes");
//                                            NepsHeaderDao nepsHeaderDao1 = newEstimateProcSaveDao.getNepsContextDao().getNepsheaderDao();
//                                            String dateValue = Utility.splitTValue(nepsHeaderDao1.getIn_Estimated_PickDate());
//                                            SubmitRecEstPurchaseDao submitRecEstPurchaseDao = new SubmitRecEstPurchaseDao(1, nepsHeaderDao1.getIn_Farmer_Code(), nepsHeaderDao1.getIn_Farmer_Name(), nepsHeaderDao1.getIn_Member_Type(),
//                                                    nepsHeaderDao1.getIn_Item_Code(), nepsHeaderDao1.getIn_Item_Name(), String.valueOf(nepsHeaderDao1.getIn_Estimated_Qty()),
//                                                    String.valueOf(nepsHeaderDao1.getIn_Estimated_Price()), String.valueOf(nepsHeaderDao1.getIn_Estimated_Value()), dateValue, nepsHeaderDao1.getIn_Remarks(), nepsHeaderDao1.getIn_LotNo(), "0", dateValue, "I", "Yes",nepsHeaderDao1.getIn_Estimated_attach(),vst2,qpdv,radiovalue,lrpn,lrpmn);
//                                            db.addAllSubmitRecEstPurChaseOffline(submitRecEstPurchaseDao);
//                                            showLotNoDialog(newEstimateProcSaveDao.getNepsContextDao().getNepsheaderDao().getIn_LotNo());
//
//                                            textViewSearchFarmerCode.setText("");
//                                            txt_uom.setText("");
//                                            txt_var.setText("");
//                                            qpdv="";
//                                            lrpno.setText("");
//                                            txt_qp.setText("Tap For Select");
//                                            lrpname.setText("");
//                                            rbutton1.setChecked(false);
//                                            rbutton2.setChecked(false);
//                                            spinner3.setSelection(0);
//                                            editTextLotNO.setText("");
//                                            textViewFarmerName.setText("");
//                                            textViewMemberType.setText("");
//                                            textViewDate.setText("");
//                                            textViewFarmerItem.setText("");
//                                            editTextPrice.setText("");
//                                            textViewValue.setText("");
//                                            editTextRemarks.setText("");
//                                            numtest = 0;
//                                             encodedImage="0";
//                                                capturebill.setImageResource(0);
//                                                capturebill.setBackgroundResource(R.drawable.capture);
//                                            textViewQty.setText("" + numtest);
//                                            textViewQty2.setText("" + numtest);
//
//                                        } else {
//                                            showErrorDialog("Duplicate Entry.!");
//                                            textViewSearchFarmerCode.setText("");
//                                            txt_uom.setText("");
//                                            txt_var.setText("");
//                                            editTextLotNO.setText("");
//                                            qpdv="";
//                                            lrpno.setText("");
//                                            txt_qp.setText("Tap For Select");
//                                            lrpname.setText("");
//                                            rbutton1.setChecked(false);
//                                            rbutton2.setChecked(false);
//                                            spinner3.setSelection(0);
//                                            textViewFarmerName.setText("");
//                                            textViewMemberType.setText("");
//                                            textViewDate.setText("");
//                                            textViewFarmerItem.setText("");
//                                            editTextPrice.setText("");
//                                            textViewValue.setText("");
//                                            editTextRemarks.setText("");
//                                            numtest = 0;
//                                             encodedImage="0";
//                                                capturebill.setImageResource(0);
//                                                capturebill.setBackgroundResource(R.drawable.capture);
//                                            textViewQty.setText("" + numtest);
//                                            textViewQty2.setText("" + numtest);
//                                        }
//
//
//                                    }
//                                });
//                    }
//
//
//                } else {
//
//                    submitRecEstPurchaseDaoList = db.getAllSubmitRecEstPurchaseDaoForValidate();
//
//                    if (submitRecEstPurchaseDaoList != null && submitRecEstPurchaseDaoList.size() > 0) {
//                        if (!contains(submitRecEstPurchaseDaoList, estDate, qty)) {
//                            SubmitRecEstPurchaseDao submitRecEstPurchaseDao = new SubmitRecEstPurchaseDao(1, farmerCode, farmerName, memberType, productCode, productName,
//                                    qty, price, value, estDate, remarks, "", "0", estDate, "I", "No",encodedImage,vst2,qpdv,radiovalue,lrpn,lrpmn);
//                            db.addAllSubmitRecEstPurChaseOffline(submitRecEstPurchaseDao);
//                            textViewSearchFarmerCode.setText("");
//                            txt_uom.setText("");
//                            txt_var.setText("");
//                            editTextLotNO.setText("");
//                            textViewFarmerName.setText("");
//                            textViewMemberType.setText("");
//                            textViewDate.setText("");
//                            textViewFarmerItem.setText("");
//                             encodedImage="0";
//                            qpdv="";
//                            lrpno.setText("");
//                            txt_qp.setText("Tap For Select");
//                            lrpname.setText("");
//                            rbutton1.setChecked(false);
//                            rbutton2.setChecked(false);
//                            spinner3.setSelection(0);
//                                                capturebill.setImageResource(0);
//                                                capturebill.setBackgroundResource(R.drawable.capture);
//                            editTextPrice.setText("");
//                            textViewValue.setText("");
//                            editTextRemarks.setText("");
//                            numtest = 0;
//                            textViewQty.setText("" + numtest);
//                            textViewQty2.setText("" + numtest);
//                            //  Toast.makeText(mContext, "SuccessFully Saved", Toast.LENGTH_SHORT).show();
//                            showErrorDialog("SuccessFully Saved");
//                        } else {
//                            textViewSearchFarmerCode.setText("");
//                            textViewFarmerName.setText("");
//                            txt_uom.setText("");
//                            txt_var.setText("");
//                            editTextLotNO.setText("");
//                            textViewMemberType.setText("");
//                            textViewDate.setText("");
//                            textViewFarmerItem.setText("");
//                            editTextPrice.setText("");
//                            textViewValue.setText("");
//                            editTextRemarks.setText("");
//                             encodedImage="0";
//                            qpdv="";
//                            lrpno.setText("");
//                            txt_qp.setText("Tap For Select");
//                            lrpname.setText("");
//                            rbutton1.setChecked(false);
//                            rbutton2.setChecked(false);
//                            spinner3.setSelection(0);
//                                                capturebill.setImageResource(0);
//                                                capturebill.setBackgroundResource(R.drawable.capture);
//                            numtest = 0;
//                            textViewQty.setText("" + numtest);
//                            textViewQty2.setText("" + numtest);
//                            showErrorDialog("Duplicate Entry.!");
//                        }
//                    } else {
//                        SubmitRecEstPurchaseDao submitRecEstPurchaseDao = new SubmitRecEstPurchaseDao(1, farmerCode, farmerName, memberType, productCode, productName,
//                                qty, price, value, estDate, remarks, "", "0", estDate, "I", "No",encodedImage,vst2,qpdv,radiovalue,lrpn,lrpmn);
//                        db.addAllSubmitRecEstPurChaseOffline(submitRecEstPurchaseDao);
//                        textViewSearchFarmerCode.setText("");
//                        textViewFarmerName.setText("");
//                        txt_uom.setText("");
//                        txt_var.setText("");
//                        editTextLotNO.setText("");
//                        textViewMemberType.setText("");
//                        textViewDate.setText("");
//                        textViewFarmerItem.setText("");
//                        editTextPrice.setText("");
//                        textViewValue.setText("");
//                        editTextRemarks.setText("");
//                         encodedImage="0";
//                        qpdv="";
//                        lrpno.setText("");
//                        txt_qp.setText("Tap For Select");
//                        lrpname.setText("");
//                        rbutton1.setChecked(false);
//                        rbutton2.setChecked(false);
//                        spinner3.setSelection(0);
//                                                capturebill.setImageResource(0);
//                                                capturebill.setBackgroundResource(R.drawable.capture);
//                        numtest = 0;
//                        textViewQty.setText("" + numtest);
//                        textViewQty2.setText("" + numtest);
//                        Toast.makeText(mContext, "SuccessFully Saved", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                }
//
//            } else {
//
//                if (isNetwork == true) {
//
//                    progressLayout.setVisibility(View.VISIBLE);
//                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//
//
//                    final NepsHeaderDao nepsHeaderDao = new NepsHeaderDao();
//                    nepsHeaderDao.setIn_Estm_rowid(0);
//                    nepsHeaderDao.setIn_LotNo(submitRecEstPurchaseDaoEdit.getLotNo());
//                    nepsHeaderDao.setIn_Farmer_Code(farmerCode);
//                    nepsHeaderDao.setIn_Farmer_Name(farmerName);
//                    nepsHeaderDao.setIn_Member_Type(memberType);
//                    nepsHeaderDao.setIn_Item_Code(productCode);
//                    nepsHeaderDao.setIn_Item_Name(productName);
//                    nepsHeaderDao.setIn_Estimated_Qty(Double.parseDouble(qty));
//                    nepsHeaderDao.setIn_Estimated_Price(Double.parseDouble(price));
//                    nepsHeaderDao.setIn_Estimated_Value(Double.parseDouble(value));
//                    nepsHeaderDao.setIn_Estimated_PickDate(estDate);
//                    nepsHeaderDao.setIn_Remarks(remarks);
//                    nepsHeaderDao.setIn_rowtimestamp(estDate);
//                    nepsHeaderDao.setIn_mode_flag("U");
//                    nepsHeaderDao.setIn_Estimated_attach(encodedImage);
//                    nepsHeaderDao.setIn_variety_status(vst2);
//                    nepsHeaderDao.setIn_Estimated_Status(radiovalue);
//                    nepsHeaderDao.setIn_LRP_Name(lrpn);
//                    nepsHeaderDao.setIn_LRP_Mobile_no(lrpmn);
//                    nepsHeaderDao.setIn_status("Maker");
//                    NepsContextDao nepsContextDao = new NepsContextDao();
//                    nepsContextDao.setOrgnId(orgnCode);
//                    //   nepsContextDao.setLocnId(locnId);
//                    nepsContextDao.setLocnId(ApiUtils.instance);
//                    nepsContextDao.setUserId(userId);
//                    nepsContextDao.setLocaleId(localeId);
//                    nepsContextDao.setNepsHeaderDao(nepsHeaderDao);
//                    nepsContextDao.setPostQtyDetailDaoList(postQtyDetailDaoList);
//
//                    NepsDocumentDao nepsDocumentDao = new NepsDocumentDao();
//                    nepsDocumentDao.setNepsContextDao(nepsContextDao);
//
//                    NewEstimateProcSaveDao newEstimateProcSaveDao = new NewEstimateProcSaveDao();
//                    newEstimateProcSaveDao.setNepsDocumentDao(nepsDocumentDao);
//
//                    mAPIService.postNewEstimateProcSaveDetails(newEstimateProcSaveDao)
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Subscriber<NewEstimateProcSaveDao>() {
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
//                                public void onNext(NewEstimateProcSaveDao newEstimateProcSaveDao) {
//
//                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//                                    progressLayout.setVisibility(View.GONE);
//
//
//                                    if (newEstimateProcSaveDao.getNepsContextDao().getNepsheaderDao().getIn_LotNo() != null) {
//
//                                        NepsHeaderDao nepsHeaderDao1 = newEstimateProcSaveDao.getNepsContextDao().getNepsheaderDao();
//                                        String dateValue = Utility.splitTValue(nepsHeaderDao1.getIn_Estimated_PickDate());
//                                        SubmitRecEstPurchaseDao submitRecEstPurchaseDao = new SubmitRecEstPurchaseDao(submitRecEstPurchaseDaoEdit.getId(), nepsHeaderDao1.getIn_Farmer_Code(), nepsHeaderDao1.getIn_Farmer_Name(), nepsHeaderDao1.getIn_Member_Type(),
//                                                nepsHeaderDao1.getIn_Item_Code(), nepsHeaderDao1.getIn_Item_Name(), String.valueOf(nepsHeaderDao1.getIn_Estimated_Qty()),
//                                                String.valueOf(nepsHeaderDao1.getIn_Estimated_Price()), String.valueOf(nepsHeaderDao1.getIn_Estimated_Value()), dateValue, nepsHeaderDao1.getIn_Remarks(), nepsHeaderDao1.getIn_LotNo(), "0", dateValue, "U", "Yes",nepsHeaderDao1.getIn_Estimated_attach(),nepsHeaderDao1.getIn_variety_status(),qpdv,nepsHeaderDao1.getIn_Estimated_Status(),nepsHeaderDao1.getIn_LRP_Name(),nepsHeaderDao1.getIn_LRP_Mobile_no());
//                                        db.updateAllSubmitRecEstPurChaseOffline(submitRecEstPurchaseDao);
//
//                                        textViewSearchFarmerCode.setText("");
//                                        textViewFarmerName.setText("");
//                                        textViewMemberType.setText("");
//                                        textViewDate.setText("");
//                                        textViewFarmerItem.setText("");
//                                        editTextPrice.setText("");
//                                        textViewValue.setText("");
//                                        editTextRemarks.setText("");
//                                        txt_uom.setText("");
//                                        txt_var.setText("");
//                                         encodedImage="0";
//                                        qpdv="";
//                                        lrpno.setText("");
//                                        txt_qp.setText("Tap For Select");
//                                        lrpname.setText("");
//                                        rbutton1.setChecked(false);
//                                        rbutton2.setChecked(false);
//                                        spinner3.setSelection(0);
//                                                capturebill.setImageResource(0);
//                                                capturebill.setBackgroundResource(R.drawable.capture);
//                                        editTextLotNO.setText("");
//                                        numtest = 0;
//                                        textViewQty.setText("" + numtest);
//                                        textViewQty2.setText("" + numtest);
//                                        showErrorDialog("SuccessFully Updated");
//
//                                    }
//
//                                }
//                            });
//
//
//                }else {
//
//                    textViewSearchFarmerCode.setText("");
//                    textViewFarmerName.setText("");
//                    textViewMemberType.setText("");
//                    textViewDate.setText("");
//                    textViewFarmerItem.setText("");
//                    editTextPrice.setText("");
//                    textViewValue.setText("");
//                    editTextRemarks.setText("");
//                    txt_uom.setText("");
//                     encodedImage="0";
//                    lrpno.setText("");
//                    qpdv="";
//                    txt_qp.setText("Tap For Select");
//                    lrpname.setText("");
//                    rbutton1.setChecked(false);
//                    rbutton2.setChecked(false);
//                    spinner3.setSelection(0);
//                                                capturebill.setImageResource(0);
//                                                capturebill.setBackgroundResource(R.drawable.capture);
//                    txt_var.setText("");
//                    editTextLotNO.setText("");
//                    numtest = 0;
//                    textViewQty.setText("" + numtest);
//                    textViewQty2.setText("" + numtest);
//
//                    SubmitRecEstPurchaseDao submitRecEstPurchaseDao = new SubmitRecEstPurchaseDao(submitRecEstPurchaseDaoEdit.getId(), farmerCode, farmerName, memberType, productCode, productName,
//                            qty, price, value, estDate, remarks, submitRecEstPurchaseDaoEdit.getLotNo(), submitRecEstPurchaseDaoEdit.getEstiRowId(), estDate, "U", "No",encodedImage,vst2,qpdv,radiovalue,lrpn,lrpmn);
//                    db.updateAllSubmitRecEstPurChaseOffline(submitRecEstPurchaseDao);
//                    showErrorDialog("SuccessFully Updated");
//
//                }
//
//            }
//
//
//        }
//
//    }
//
//    private boolean contains(List<SubmitRecEstPurchaseDao> list, String estDate, String qty) {
//        for (SubmitRecEstPurchaseDao item : list) {
//            if (farmerCode.equalsIgnoreCase(item.getFarmerCode()) &&
//                    farmerName.equalsIgnoreCase(item.getFarmerName()) &&
//                    memberType.equalsIgnoreCase(item.getFarmerMember()) &&
//                    productCode.equalsIgnoreCase(item.getItemCode()) &&
//                    productName.equalsIgnoreCase(item.getItemName()) &&
//                    qty.equalsIgnoreCase(item.getQty()) &&
//                    estDate.equalsIgnoreCase(item.getPickupdate())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean contains(List<SubmitRecEstPurchaseDao> list, NepsHeaderDao nepsHeaderDao, String date) {
//        for (SubmitRecEstPurchaseDao item : list) {
//            if (nepsHeaderDao.getIn_Farmer_Code().equalsIgnoreCase(item.getFarmerCode()) &&
//                    nepsHeaderDao.getIn_Farmer_Name().equalsIgnoreCase(item.getFarmerName()) &&
//                    nepsHeaderDao.getIn_Member_Type().equalsIgnoreCase(item.getFarmerMember()) &&
//                    nepsHeaderDao.getIn_Item_Code().equalsIgnoreCase(item.getItemCode()) &&
//                    nepsHeaderDao.getIn_Item_Name().equalsIgnoreCase(item.getItemName()) &&
//                    String.valueOf(nepsHeaderDao.getIn_Estimated_Qty()).equalsIgnoreCase(item.getQty()) &&
//                    date.equalsIgnoreCase(item.getPickupdate())) {
//                return true;
//            }
//        }
//        return false;
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
//    private void showLotNoDialog(String in_lotNo) {
//        new AlertDialog.Builder(this)
//                .setTitle("Success!")
//                .setMessage(in_lotNo + " Generated!")
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
//    private void showItemSearchDialog() {
//
//        dialog = new Dialog(mContext);
//        dialog.setContentView(R.layout.custom_search_item_dialog);
//        //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
//        dialog.setTitle("Title...");
//        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
//        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
//        final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
//        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        dialog.getWindow().setLayout(width, height);
//
//        final AutoCompleteTextView elc = (AutoCompleteTextView) dialog.findViewById(R.id.elc);
//        final AutoCompleteTextView elc2 = (AutoCompleteTextView) dialog.findViewById(R.id.elc2);
//        final AutoCompleteTextView elc3 = (AutoCompleteTextView) dialog.findViewById(R.id.elc3);
//        productItemList = new ArrayList<>();
//        cat = new ArrayList<>();
//        var = new ArrayList<>();
//        productItemList.clear();
//        cat.clear();
//        var.clear();
//        productItemList = db.getProductNameAndCode();
//        cat = db.getProductNameAndCode2();
//        var = db.getProductNameAndCode3();
//        HashSet hs = new HashSet();
//
//        hs.addAll(productItemList); // demoArrayList= name of arrayList from which u want to remove duplicates
//
//        productItemList.clear();
//        productItemList.addAll(hs);
//
//        HashSet hs2 = new HashSet();
//
//        hs2.addAll(cat); // demoArrayList= name of arrayList from which u want to remove duplicates
//
//        cat.clear();
//        cat.addAll(hs2);
//
//        HashSet hs3 = new HashSet();
//
//        hs3.addAll(var); // demoArrayList= name of arrayList from which u want to remove duplicates
//
//        var.clear();
//        var.addAll(hs3);
//
//        final ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(mContext,
//                R.layout.support_simple_spinner_dropdown_item, productItemList);
//
//        elc.setAdapter(adapterlist2n);
//        final ArrayAdapter<String> adapterlist2nn = new ArrayAdapter<String>(mContext,
//                R.layout.support_simple_spinner_dropdown_item, cat);
//
//        elc2.setAdapter(adapterlist2nn);
//        final ArrayAdapter<String> adapterlist2nnn = new ArrayAdapter<String>(mContext,
//                R.layout.support_simple_spinner_dropdown_item, var);
//
//        elc3.setAdapter(adapterlist2nnn);
//
//
//        elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                pmListDaoList.clear();
//                String productName = elc.getText().toString();
//                pmListDaoList = db.getProductMasterAllProductDetails(productName);
//
//                MyRecyclerViewAdapterItem adapterf = new MyRecyclerViewAdapterItem(mContext, pmListDaoList);
//                recyclerView.setAdapter(adapterf);
//
//                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
//            }
//        });
//        elc2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                pmListDaoList.clear();
//                String productName = elc2.getText().toString();
//                pmListDaoList = db.getProductMasterAllProductDetails2(productName);
//
//                MyRecyclerViewAdapterItem adapterf = new MyRecyclerViewAdapterItem(mContext, pmListDaoList);
//                recyclerView.setAdapter(adapterf);
//
//                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
//            }
//        });
//        elc3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                pmListDaoList.clear();
//                String productName = elc3.getText().toString();
//                pmListDaoList = db.getProductMasterAllProductDetails3(productName);
//
//                MyRecyclerViewAdapterItem adapterf = new MyRecyclerViewAdapterItem(mContext, pmListDaoList);
//                recyclerView.setAdapter(adapterf);
//
//                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
//            }
//        });
//
//        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//
//    }
//
//    private void showFarmerNameDialog() {
//
//        dialog = new Dialog(mContext);
//        dialog.setContentView(R.layout.custom_search_farmer_dialog);
//        //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
//        dialog.setTitle("Title...");
//        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
//        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
//        final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
//        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        dialog.getWindow().setLayout(width, height);
//
//        final AutoCompleteTextView elc = (AutoCompleteTextView) dialog.findViewById(R.id.elc);
//        elc.setHint("Search FarmerName");
//        farmerNameList = new ArrayList<>();
//        farmerNameList.clear();
//        farmerNameList = db.getFarmerName();
//        HashSet hs = new HashSet();
//
//        hs.addAll(farmerNameList); // demoArrayList= name of arrayList from which u want to remove duplicates
//
//        farmerNameList.clear();
//        farmerNameList.addAll(hs);
//
//        final ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(mContext,
//                R.layout.support_simple_spinner_dropdown_item, farmerNameList);
//
//        elc.setAdapter(adapterlist2n);
//
//
//        elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                formerDaoList.clear();
//                String farmerCode = elc.getText().toString();
//                formerDaoList = db.getAllFarmerDataDetails(farmerCode, "FARMER_NAME");
//
//                MyRecyclerViewAdapterfarmer adapterf = new MyRecyclerViewAdapterfarmer(mContext, formerDaoList);
//                recyclerView.setAdapter(adapterf);
//
//                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
//            }
//        });
//
//        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
//
//        Button anf = (Button)dialog.findViewById(R.id.anf);
//
//        anf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), FDR.class);
//                startActivity(i);
//                dialog.dismiss();
//            }
//        });
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//
//    }
//
//    private void showFarmerCodeDialog() {
//
//        dialog = new Dialog(mContext);
//        dialog.setContentView(R.layout.custom_search_farmer_dialog);
//        //  dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
//        dialog.setTitle("Title...");
//        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
//        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
//        final androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.itm);
//        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        dialog.getWindow().setLayout(width, height);
//
//        final AutoCompleteTextView elc = (AutoCompleteTextView) dialog.findViewById(R.id.elc);
//        elc.setHint("Search FarmerCode");
//        farmerCodeList = new ArrayList<>();
//        farmerCodeList.clear();
//        farmerCodeList = db.getFarmerCode();
//        HashSet hs = new HashSet();
//
//        hs.addAll(farmerCodeList); // demoArrayList= name of arrayList from which u want to remove duplicates
//
//        farmerCodeList.clear();
//        farmerCodeList.addAll(hs);
//
//        final ArrayAdapter<String> adapterlist2n = new ArrayAdapter<String>(mContext,
//                R.layout.support_simple_spinner_dropdown_item, farmerCodeList);
//
//        elc.setAdapter(adapterlist2n);
//
//
//        elc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                formerDaoList.clear();
//                String farmerCode = elc.getText().toString();
//                formerDaoList = db.getAllFarmerDataDetails(farmerCode, "FARMER_CODE");
//
//                MyRecyclerViewAdapterfarmer adapterf = new MyRecyclerViewAdapterfarmer(mContext, formerDaoList);
//                recyclerView.setAdapter(adapterf);
//
//                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(elc.getWindowToken(), 0);
//            }
//        });
//
//        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.cls);
//        Button anf = (Button)dialog.findViewById(R.id.anf);
//
//        anf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), FDR.class);
//                startActivity(i);
//                dialog.dismiss();
//            }
//        });
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }
//
//
//    private void showDateDialog() {
//
//        final Calendar calendar = Calendar.getInstance();
//        int mYear = calendar.get(Calendar.YEAR);
//        int mMonth = calendar.get(Calendar.MONTH);
//        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
//        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, android.R.style.Theme_Holo_Dialog,
//                new DatePickerDialog.OnDateSetListener() {
//
//                    @Override
//                    public void onDateSet(DatePicker view, int year,
//                                          int monthOfYear, int dayOfMonth) {
//                        String month = null,day = null;
//                        int m,d;
//
//                        m = monthOfYear +1;
//                        if(m < 10){
//
//                            month = "0" + m;
//                        }
//                        else
//                        {
//                            month = String.valueOf(m);
//                        }
//                        if(dayOfMonth < 10){
//
//                            day  = "0" + dayOfMonth ;
//                        }
//                        else
//                        {
//                            day= String.valueOf(dayOfMonth);
//                        }
//
//                        textViewDate.setText(day + "-" + month + "-" + year);
//
//
//                    }
//                }, mYear, mMonth, mDay);
//        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
//        datePickerDialog.show();
//
//    }
//
//
//    public class MyRecyclerViewAdapterfarmer extends RecyclerView.Adapter<MyRecyclerViewAdapterfarmer.ViewHolder> {
//
//        private List<FormerDao> mData;
//        private LayoutInflater mInflater;
//
//
//        // data is passed into the constructor
//        public MyRecyclerViewAdapterfarmer(Context context, List<FormerDao> data) {
//            this.mInflater = LayoutInflater.from(context);
//            this.mData = data;
//        }
//
//        @NonNull
//        @Override
//        public MyRecyclerViewAdapterfarmer.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = mInflater.inflate(R.layout.custom_recycleview_farmer, parent, false);
//            return new MyRecyclerViewAdapterfarmer.ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull MyRecyclerViewAdapterfarmer.ViewHolder holder, int position) {
//            final FormerDao pojofar = mData.get(position);
//            holder.name.setText(pojofar.getFarmer_name());
//            holder.fhw.setText(pojofar.getFhw_name());
//            holder.ph.setText(pojofar.getFarmer_village_desc());
//            holder.lcn.setText(pojofar.getFarmer_panchayat_desc());
//            holder.ty.setText(pojofar.getFarmer_taluk_desc());
//            holder.t5.setText(pojofar.getFarmer_dist_desc());
//
//            holder.llist.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    farmerCode = pojofar.getFarmer_code();
//                    farmerName = pojofar.getFarmer_name();
//                    memberType = pojofar.getFarmer();
//
//                    textViewFarmerName.setText(farmerName);
//                    textViewMemberType.setText(memberType);
//                    textViewSearchFarmerCode.setText(farmerCode);
//
//
//                    dialog.dismiss();
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return mData.size();
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            TextView name, fhw, ph, lcn, ty, t5;
//            LinearLayout llist;
//
//            public ViewHolder(@NonNull View itemView) {
//                super(itemView);
//                name = itemView.findViewById(R.id.fn);
//                fhw = itemView.findViewById(R.id.fhn);
//                ph = itemView.findViewById(R.id.vi);
//                lcn = itemView.findViewById(R.id.gp);
//                ty = itemView.findViewById(R.id.ta);
//                t5 = itemView.findViewById(R.id.di);
//                llist = itemView.findViewById(R.id.llist);
//            }
//        }
//    }
//
//    public class MyRecyclerViewAdapterItem extends RecyclerView.Adapter<MyRecyclerViewAdapterItem.ViewHolder> {
//
//        private List<PmListDao> mData;
//        private LayoutInflater mInflater;
//
//
//        // data is passed into the constructor
//        public MyRecyclerViewAdapterItem(Context context, List<PmListDao> data) {
//            this.mInflater = LayoutInflater.from(context);
//            this.mData = data;
//        }
//
//        @NonNull
//        @Override
//        public MyRecyclerViewAdapterItem.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = mInflater.inflate(R.layout.custom_recyclerview_product, parent, false);
//            return new MyRecyclerViewAdapterItem.ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull MyRecyclerViewAdapterItem.ViewHolder holder, int position) {
//            final PmListDao pojofar = mData.get(position);
//            holder.textViewProductName.setText(pojofar.getOut_product_name());
//            holder.textViewProductCode.setText(pojofar.getOut_product_code());
//            holder.txt3.setText(pojofar.getOut_uomtype_code());
//            holder.txt4.setText(pojofar.getOut_product_category());
//            holder.txt5.setText(pojofar.getOut_crop_variety());
//            //     holder.textViewProductAgg.setText(pojofar.getOut_agg_code());
//
//            holder.llist.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    productCode = pojofar.getOut_product_code();
//                    productName = pojofar.getOut_product_name();
//                    aggCode = pojofar.getOut_agg_code();
//                    txt_uom.setText(pojofar.getOut_uomtype_code());
//                    txt_var.setText(pojofar.getOut_crop_variety());
//                    textViewFarmerItem.setText(productName);
//
//
//                    dialog.dismiss();
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return mData.size();
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            TextView textViewProductName, textViewProductCode,txt3,txt4,txt5;
//            LinearLayout llist;
//
//            public ViewHolder(@NonNull View itemView) {
//                super(itemView);
//                textViewProductName = itemView.findViewById(R.id.txt_productName);
//                textViewProductCode = itemView.findViewById(R.id.txt_productcode);
//                txt3 = itemView.findViewById(R.id.txtuom);
//                txt4 = itemView.findViewById(R.id.txtcat);
//                txt5 = itemView.findViewById(R.id.txtvar);
//                //  textViewProductAgg = itemView.findViewById(R.id.txt_agg);
//                llist = itemView.findViewById(R.id.llist);
//            }
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode==EST_EDT_VALUE){
//            if(data!=null){
//                submitRecEstPurchaseDaoEdit=data.getParcelableExtra("EDIT_ESTIMATE_VALUE");
//                edtEstValue=data.getStringExtra("EDIT_EST_VALUE");
//                if(submitRecEstPurchaseDaoEdit!=null){
//
//                    textViewSearchFarmerCode.setText(submitRecEstPurchaseDaoEdit.getFarmerCode());
//                    textViewFarmerName.setText(submitRecEstPurchaseDaoEdit.getFarmerName());
//                    textViewMemberType.setText(submitRecEstPurchaseDaoEdit.getFarmerMember());
//                    textViewFarmerItem.setText(submitRecEstPurchaseDaoEdit.getItemName());
//                    textViewQty2.setText(submitRecEstPurchaseDaoEdit.getQty());
//                    editTextLotNO.setText(submitRecEstPurchaseDaoEdit.getLotNo());
//                    lrpno.setText(submitRecEstPurchaseDaoEdit.getLotmn());
//                    //textViewQty2.setText("0");
//                    final SQLiteDatabase dbs = db.getWritableDatabase();
//
//                    try {
//
//
//                        Cursor c = dbs.rawQuery("SELECT * FROM SubmitRecEstPurchase WHERE SubmitRecEstPurchaseKeyId = '" + submitRecEstPurchaseDaoEdit.getIn_Estimated_attach() + "'", null);
//
//
//                        if (c.moveToNext()) {
//                            Log.e("OKK",""+c.getString(17));
//                            Cursor cursor2 = dbs.query("vstatus", new String[]{"v1", "v2", "v3"
//                                    }, "v2" + "=?",
//                                    new String[]{c.getString(17)}, null, null, null, null);
//
//
//                            if(cursor2.moveToFirst())
//                            {
//                                do {
//                                    spinner3.setSelection(((ArrayAdapter<String>)spinner3.getAdapter()).getPosition(cursor2.getString(2)));
//
//                                }while(cursor2.moveToNext());
//                            }
//                            if(c.getString(19).equalsIgnoreCase("Acceptable"))
//                            {
//                                rbutton1.setChecked(true);
//                            }
//                            else
//                            {
//                                rbutton2.setChecked(true);
//                            }
//                            qpdv = c.getString(18);
//                            txt_qp.setText(qpdv);
//                            lrpname.setText(c.getString(20));
//                            encodedImage = c.getString(16);
//                            byte[] imageAsBytes = Base64.decode(c.getString(16).getBytes(), Base64.DEFAULT);
//
//
//                            capturebill.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
//                        }
//                    }
//                    catch (Exception e)
//                    {
//                        encodedImage="0";
//                    }
//
//
//                    Cursor cursoruom = dbs.query("ProductMasterAllProduct", new String[]{"out_uomtype_code","out_crop_variety"
//                            }, "PmapOutProductCode" + "=?",
//                            new String[]{submitRecEstPurchaseDaoEdit.getItemCode()}, null, null, null, null);
//
//                    if(cursoruom.moveToFirst())
//                    {
//                        txt_uom.setText(cursoruom.getString(0));
//                        txt_var.setText(cursoruom.getString(1));
//                    }
//
//                    editTextPrice.setText(submitRecEstPurchaseDaoEdit.getPrice());
//                    textViewValue.setText(submitRecEstPurchaseDaoEdit.getValue());
//                    String[] d = submitRecEstPurchaseDaoEdit.getPickupdate().split("-");
//
//                    textViewDate.setText(d[2]+"-"+d[1]+"-"+d[0]);
//                    editTextRemarks.setText(submitRecEstPurchaseDaoEdit.getRemarks());
//                    numtest = Double.parseDouble(submitRecEstPurchaseDaoEdit.getQty());
//
//                    farmerCode=submitRecEstPurchaseDaoEdit.getFarmerCode();
//                    farmerName=submitRecEstPurchaseDaoEdit.getFarmerName();
//                    memberType=submitRecEstPurchaseDaoEdit.getFarmerMember();
//                    productCode=submitRecEstPurchaseDaoEdit.getItemCode();
//                    productName=submitRecEstPurchaseDaoEdit.getItemName();
//
//                    if(edtEstValue!=null){
//                        if(edtEstValue.equalsIgnoreCase("editestimate")){
//                            linearFarmerCode.setEnabled(false);
//                            linearFarmerName.setEnabled(false);
//                        }else {
//                            linearFarmerCode.setEnabled(true);
//                            linearFarmerName.setEnabled(true);
//                        }
//
//
//                    }
//
//
//
//                    Log.v(MyConstants.TAG,submitRecEstPurchaseDaoEdit.getFarmerName());
//                }
//            }
//
//
//
//
//
//        }
//       if(resultCode == RESULT_OK)
//       {
//           if (requestCode == CAMERA_CAPTURE) {
//               Uri uri = picUri;
//               Log.d("picUri", picUri.toString());
//               // performCrop();
//               startCropImageActivity(uri);
//           } else if (requestCode == 2) {
//
//               Bundle extras = data.getExtras();
////get the cropped bitmap
//               thePic = (Bitmap) extras.get("data");
//               Log.e("PIC", "" + thePic);
//               capturebill.setImageResource(0);
//               capturebill.setImageBitmap(thePic);
//               //vcap.setImageBitmap(thePic);
//
//
//               byteArrayOutputStream = new ByteArrayOutputStream();
//               thePic.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
//               ui = thePic.toString();
//               Log.e("JJJJJJ", "" + ui);
//               try {
//                   encodedImage = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT), "UTF-8");
//               } catch (UnsupportedEncodingException e) {
//                   e.printStackTrace();
//               }
//
//
//           } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//               CropImage.ActivityResult result = CropImage.getActivityResult(data);
//               if (resultCode == RESULT_OK) {
//
//                   try {
//
//
//                       Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), result.getUri());
//                       capturebill.setImageResource(0);
//                       capturebill.setImageBitmap(bitmap);
//                       // vcap.setImageBitmap(bitmap);
//                       byteArrayOutputStream = new ByteArrayOutputStream();
//                       ui = result.getUri().toString();
//
//                       bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//                       Log.e("NJNJN", "" + byteArrayOutputStream.toByteArray());
//
//
//                       encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
//                       Log.e("PIC", "" + encodedImage);
//                       FileOutputStream fos = null;
//
//
//
////
//                   } catch (IOException e) {
//                       e.printStackTrace();
//                   }
//                   //  Log.e("PIC", "" + thePic);
//
////
//                   //((ImageView) findViewById(R.id.img)).setImageURI(result.getUri());
//                   // Toast.makeText(this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG).show();
//               } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                   Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
//               }
//           }
//       }
//    }
//    private File createImageFile() throws IOException {
//
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
//        String imageFileName = "IMG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
//        imageFilePath = image.getAbsolutePath();
//
//        return image;
//    }
//    private void startCropImageActivity(Uri imageUri) {
//        CropImage.activity(imageUri)
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .setMultiTouchEnabled(true)
//                .start(RecEstPurchaseActivity.this);
//    }
//    private String encodeImage(Bitmap bm)
//    {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
//        byte[] b = baos.toByteArray();
//        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
//
//        return encImage;
    }


}
