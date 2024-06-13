package cdfi.fintantra.meity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "MyDBNameup.db";
    public static final String CONTACTS_TABLE_NAME = "product";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_NAME2 = "name";
    static String DB_PATH = "/data/data/cdfi.fintantra.meity/databases/";
    private HashMap hp;
    public Context context;
//   // public DBHelper(Context context) {
//        super(context, DATABASE_NAME , null, 2);
//    }
    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DATABASE_NAME;
        sqliteDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    /*public SQLiteDatabase openDataBase() throws SQLException{
        //Open the database
        String myPath = DB_PATH + DATABASE_NAME;
        sqliteDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        return sqliteDataBase;
    }
*/


    public void copyDataBase() throws IOException
    {
        String outFileName = DB_PATH + DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        InputStream myInput = context.getAssets().open(DATABASE_NAME);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0)
        {
            myOutput.write(buffer, 0, length);
        }
        myInput.close();
        myOutput.flush();
        myOutput.close();
    }
    static SQLiteDatabase sqliteDataBase;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     * Parameters of super() are    1. Context
     *                              2. Data Base Name.
     *                              3. Cursor Factory.
     *                              4. Data Base Version.
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null ,DATABASE_VERSION);
        this.context = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * By calling this method and empty database will be created into the default system path
     * of your application so we are gonna be able to overwrite that database with our database.
     /
     public void createDataBase() throws IOException{
     //check if the database exists
     boolean databaseExist = checkDataBase();

     if(databaseExist){
     // Do Nothing.
     }else{
     this.getWritableDatabase();
     copyDataBase();
     }// end if else dbExist
     } // end createDataBase().

     /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    public boolean checkDataBase(){
        File databaseFile = new File(DB_PATH + DATABASE_NAME);
        return databaseFile.exists();
    }
    /**
     * This Method is used to close the data base connection.
     */
    @Override
    public synchronized void close() {
        if(sqliteDataBase != null)
            sqliteDataBase.close();
        super.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
//        db.execSQL(
//                "create table product " +
//                        "(id integer primary key, In_product_code text,In_product_name text,In_productcategory_code text,In_productcategory_desc text,In_productsubcategory_code text,In_productsubcategory_desc text,In_uomtype_code text,In_uomtype_desc text,In_hsn_code text,In_hsn_desc text,In_base_price text,In_current_qty text,In_cgst text,In_sgst text,In_igst text,In_orgn_code text,In_ic_code text)"
//
//
//        );
//        db.execSQL(
//                "create table productlist " +
//                        "(id integer primary key, item text,quantity text,rate text,amount text,discount text,netamount text,pid text,flag text,ts text)"
//
//
//        );
//
//        db.execSQL(
//                "create table inwardlist " +
//                        "(id integer primary key, inwardno text,date text,supplier text,location text,tax text,transport text,others text,inwardamount text,ids text,itemlist text,itemtaxlist text,flag text,img text,lcode text,inweb text,billno text)"
//
//
//        );
//
//
//        db.execSQL(
//                "create table productlist2 " +
//                        "(id integer primary key, item text,quantity text,rate text,amount text,discount text,netamount text,pid text,flag text,ts text)"
//
//
//        );
//
//        db.execSQL(
//                "create table invoice " +
//                        "(id integer primary key, invoiceno text,amount text,balance text,paymode text,refno text,date text,cname text,flag text)"
//
//
//        );
//
//
//        db.execSQL(
//                "create table inward " +
//                        "(id integer primary key, invoiceno text,amount text,balance text,paymode text,refno text,date text,cname text,flag text)"
//
//
//        );
//
//
//        db.execSQL(
//                "create table paylist " +
//                        "(id integer primary key, invoiceno text,paymode text,amountpaid text,refno text,date text,bal text,flag text,uflag text,ckno text)"
//
//
//        );
//        db.execSQL(
//                "create table paylist2 " +
//                        "(id integer primary key, invoiceno text,paymode text,amountpaid text,refno text,date text,bal text,flag text,uflag text,ckno text)"
//
//
//        );
//        db.execSQL(
//                "create table invoicelist " +
//                        "(id integer primary key, invoiceno text,date text,supplier text,location text,tax text,transport text,others text,invoiceamount text,ids text,itemlist text,itemtaxlist text,flag text,lcode text,sid text,inweb text,phone text,mem text)"
//
//
//        );
//
//        db.execSQL(
//                "create table supplierlist " +
//                        "(id integer primary key, In_supplier_code text,In_supplier_name text,In_pan_no text,In_supplier_state_code text,In_supplier_state_desc text,In_supplier_location text,In_ic_code text)"
//
//
//        );
//
//        db.execSQL(
//                "create table customerlist " +
//                        "(id integer primary key, farmer_code text,farmer_name text,village_name text,farmer_address text,state_name text,farmer_village_code text,farmer_state_code text,mor text,phone text,ic_code text,surname text,fhwname text)"
//
//
//        );
//        db.execSQL(
//                "create table customerlistnm " +
//                        "(id integer primary key, farmer_code text,farmer_name text,village_name text,farmer_address text,state_name text,farmer_village_code text,farmer_state_code text,mor text,phone text,ic_code text,surname text,fhwname text)"
//
//
//        );
//
//        db.execSQL(
//                "create table astock " +
//                        "(id integer primary key, item text,adjusttype text,inwardno text,stock text,aqty text,rqty text,flag text,lrp text)"
//
//
//        );
//
//        db.execSQL(
//                "create table astock2 " +
//                        "(id integer primary key, item text,asno text,rqty text,ids text,aqty text,date text,org text)"
//
//
//        );
//        db.execSQL(
//                "create table tab " +
//                        "(id integer primary key, tab text)"
//
//
//        );
//
//        db.execSQL(
//                "create table state " +
//                        "(id integer primary key, sname text,scode text)"
//
//
//        );
//        db.execSQL(
//                "create table tablist " +
//                        "(id integer primary key, tab text,field text)"
//
//
//        );
//        db.execSQL(
//                "create table farmerh " +
//                        "(id integer primary key, name text,sname text,fname text,mobileno text,gender text,dob text,atype text,pincode text,address text,village text,gp text,taluk text,dis text,state text,country text,flag text,isl text,vc text,gc text,tc text,dc text,faid text,hm text,poto text,fpoorgn_code text,fpomember_code text,v8 text,UNIQUE (name, sname, fname, dob))"
//
//
//        );
//
//        db.execSQL(
//                "create table kyc " +
//                        "(id integer primary key, type text,subtype text,dno text,vtd text,fid text,f1 text,f2 text,tms text,stmc text,rid text,poto text, UNIQUE (type, subtype, dno, vtd, fid, f1, f2, tms, stmc, rid))"
//
//
//        );
//        db.execSQL(
//                "create table bank " +
//                        "(id integer primary key, type text,ano text,bank text,ifsc text,branch text,dc text,dd text,accs text,fid text,flag text,bid text,typec text,bc text, UNIQUE (type, ano, bank, ifsc, branch))"
//
//
//        );
//
//
//        db.execSQL(
//                "create table personal " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text,v11 text,v12 text, UNIQUE(v1, v2, v3, v4, v5, v6, v7))"
//
//
//        );
//        db.execSQL(
//                "create table family " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text,v11 text, UNIQUE(v1, v2, v3, v4, v5, v6, v7))"
//
//
//        );
//        db.execSQL(
//                "create table training " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text,v11 text, UNIQUE(v1, v2, v3, v4, v5, v6, v7))"
//
//
//        );
//
//        db.execSQL(
//                "create table loan " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text,v11 text,v12 text,v13 text,v14 text,v15 text, UNIQUE(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11))"
//
//
//        );
//        db.execSQL(
//                "create table input " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text,v11 text,v12 text,v13 text,v14 text,v15 text,v16 text, UNIQUE(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12))"
//
//
//        );
//        db.execSQL(
//                "create table loanr " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text, UNIQUE(v1, v2, v3, v4))"
//
//
//        );
//
//        db.execSQL(
//                "create table loanq " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text, UNIQUE(v1, v2, v3, v4))"
//
//
//        );
//
//        db.execSQL(
//                "create table shareh " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text, UNIQUE(v1, v2, v3, v4))"
//
//
//        );
//        db.execSQL(
//                "create table business " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text, UNIQUE(v1, v2, v3, v4))"
//
//
//        );
//        db.execSQL(
//                "create table land " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text,v11 text,v12 text,v13 text,v14 text)"
//
//
//        );
//        db.execSQL(
//                "create table live " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text, UNIQUE(v1, v2, v3, v4, v5))"
//
//
//        );
//
//        db.execSQL(
//                "create table production " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text,v11 text,v12 text,v13 text,v14 text)"
//
//
//        );
//        db.execSQL(
//                "create table asset " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text, UNIQUE(v1, v2, v3, v4, v5, v6))"
//
//
//        );
//
//        db.execSQL(
//                "create table stock " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text, UNIQUE(v1, v2, v3, v4, v5, v6))"
//
//
//        );
//
//        db.execSQL(
//                "create table insurance " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text,v11 text,v12 text,v13 text,v14 text, UNIQUE(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10))"
//
//
//        );
//
//        db.execSQL(
//                "create table sale " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text,v11 text,v12 text,v13 text,v14 text, UNIQUE(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10))"
//
//
//        );
//
//        db.execSQL(
//                "create table bankm " +
//                        "(id integer primary key, bank_rowid text,bank_code text,bank_name text,branch_name text,ifsc_code text,status_desc text)"
//
//
//        );
//        db.execSQL(
//                "create table masterl " +
//                        "(id integer primary key, out_master_rowid text,out_parent_code text,out_master_code text,out_master_description text,out_depend_code text,out_depend_desc text,out_locallang_flag text,out_status_code text,out_status_desc text)"
//
//
//        );
//        db.execSQL(
//                "create table masterla " +
//                        "(id integer primary key, out_master_rowid text,out_parent_code text,out_master_code text,out_master_description text,out_depend_code text,out_depend_desc text,out_locallang_flag text,out_status_code text,out_status_desc text)"
//
//
//        );
//
//        db.execSQL(
//                "create table address " +
//                        "(id integer primary key, fc text,fn text,fid text,at text,atc text,pi text,addr text,vi text,vic text,gp text,gpc text,ta text,tac text,di text,dic text,flag text,slno text, UNIQUE(fc, fn, fid, at, atc, pi, addr, vi, vic, gp, gpc, ta, tac, di, dic, flag, slno))"
//
//
//        );
//
//
//        db.execSQL(
//                "create table farlist " +
//                        "(id integer primary key, fid text,fc text,fn text,sn,text,vi text,gp text,ta text,di text,v1 text,v2 text,v3 text,v4 text,v5 text,fpoorgn_code text,fpomember_code text,v8 text)"
//
//
//        );
//
//        db.execSQL(
//                "create table membermapping " +
//                        "(id integer primary key, farmername text,farmercode text,fpoorgncode text,flag text)"
//
//
//        );
//
//        db.execSQL(
//                "create table crop " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text,v11 text,v12 text, UNIQUE(v1, v2, v3, v4, v5, v11))"
//
//
//        );
//        db.execSQL(
//                "create table sowing " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text,v11 text,v12 text,v13 text,v14 text,v15 text,v16 text,v17 text,n1 text,n2 text,n3 text,n4 text,n5 text)"
//
//
//        );
//
//        db.execSQL(
//                "create table ads " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text,v9 text,v10 text,v11 text,v12 text,v13 text,v14 text,v15 text,v16 text,v17 text,v18 text,v19 text,v20 text,v21 text,v22 text,v23 text,v24 text,v25 text,v26 text,v27 text,v28 text,v29 text,v30 text,v31 text,v32 text,v33 text,v34 text,v35 text,v36 text,v37 text,v38 text,v39 text,v40 text,v41 text,v42 text,v43 text,v44 text,v45 text,v46 text,v47 text,v48 text,v49 text,v50 text,v51 text,v52 text,v53 text,v54 text,v55 text,v56 text,v57 text,v58 text,v59 text,v60 text,v61 text,v62 text,v63 text,v64 text,v65 text,v66 text,v67 text,v68 text,v69 text,v70 text,v71 text,v72 text,v73 text,v74 text,v75 text,v76 text,v77 text,v78 text,v79 text,v80 text,v81 text,v82 text,v83 text,v84 text,v85 text,v86 text,v87 text,v88 text,v89 text,v90 text,v91 text,v92 text,v93 text,v94 text,v95 text,v96 text,v97 text,v98 text,v99 text,v100 text,v101 text,v102 text,v103 text,v104 text,v105 text,v106 text,v107 text,v108 text,v109 text,v110 text,v111 text)"
//
//
//        );
//
//        db.execSQL(
//                "create table orgn " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text)"
//
//
//        );
//        db.execSQL(
//                "create table icdlist " +
//                        "(id integer primary key, v1 text,v2 text)"
//
//
//        );
//
//        db.execSQL(
//                "create table sno " +
//                        "(id integer primary key, v1 text,v2 text,v3 text)"
//
//
//        );
//
//        db.execSQL(
//                "create table lrplist " +
//                        "(id integer primary key, v1 text,v2 text)"
//
//
//        );
//
//        db.execSQL(
//                "create table inw " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text)"
//
//
//        );
//
//        db.execSQL(
//                "create table itransfer " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text)"
//
//
//        );
//
//        db.execSQL(
//                "create table iconfirm " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text)"
//
//
//        );
//
//        db.execSQL(
//                "create table icdoc " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text)"
//
//
//        );
//
//        db.execSQL(
//                "create table icdip " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text,v5 text,v6 text,v7 text,v8 text)"
//
//
//        );
//
//        db.execSQL(
//                "create table oci " +
//                        "(id integer primary key, v1 text,v2 text,v3 text,v4 text)"
//
//
//        );
//        db.execSQL(
//                "create table mpin " +
//                        "(id integer primary key, usercode text,username text,rolecode text,rolename text,orgncode text,usermpin text)"
//
//
//        );
//        db.execSQL(
//                "create table attachment " +
//                        "(id integer primary key, doctype text,docsubtype text,filename text,remarks text,farmercode text,flag text)"
//
//
//        );
//
//        db.execSQL(
//                "create table template " +
//                        "(id integer primary key, template_id text,template_consent text,effective_From text,effective_to text,lang_code text)"
//
//
//        );
//        db.execSQL(
//                "create table templatefarmer " +
//                        "(id integer primary key, in_farmerconsent_rowid text,in_template_id text,in_consent_owner_id text,in_consent_to text,in_lang_code text,template_consent text,in_otp_flag text,in_isverified text,in_attachment_flag text)"
//
//
//        );
//        db.execSQL(
//                "create table gpsactivity " +
//                        "(id integer primary key, qrvalue text,latlng text,cdate text,istime text,ictime text,pstime text,pctime text,flag text,userid text,rowid text)"
//
//
//        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

//        Log.e("HATA",""+"YES");
//        db.execSQL("DROP TABLE IF EXISTS product");
//        db.execSQL("DROP TABLE IF EXISTS productlist");
//        db.execSQL("DROP TABLE IF EXISTS inwardlist");
//        db.execSQL("DROP TABLE IF EXISTS productlist2");
//        db.execSQL("DROP TABLE IF EXISTS invoice");
//        db.execSQL("DROP TABLE IF EXISTS inward");
//        db.execSQL("DROP TABLE IF EXISTS paylist");
//        db.execSQL("DROP TABLE IF EXISTS paylist2");
//        db.execSQL("DROP TABLE IF EXISTS invoicelist");
//        db.execSQL("DROP TABLE IF EXISTS supplierlist");
//        db.execSQL("DROP TABLE IF EXISTS customerlist");
//        db.execSQL("DROP TABLE IF EXISTS customerlistnm");
//        db.execSQL("DROP TABLE IF EXISTS astock");
//        db.execSQL("DROP TABLE IF EXISTS astock2");
//        db.execSQL("DROP TABLE IF EXISTS tab");
//        db.execSQL("DROP TABLE IF EXISTS state");
//        db.execSQL("DROP TABLE IF EXISTS tablist");
//        db.execSQL("DROP TABLE IF EXISTS farmerh");
//        db.execSQL("DROP TABLE IF EXISTS kyc");
//        db.execSQL("DROP TABLE IF EXISTS bank");
//        db.execSQL("DROP TABLE IF EXISTS personal");
//        db.execSQL("DROP TABLE IF EXISTS family");
//        db.execSQL("DROP TABLE IF EXISTS training");
//        db.execSQL("DROP TABLE IF EXISTS loan");
//        db.execSQL("DROP TABLE IF EXISTS input");
//        db.execSQL("DROP TABLE IF EXISTS loanr");
//        db.execSQL("DROP TABLE IF EXISTS loanq");
//        db.execSQL("DROP TABLE IF EXISTS shareh");
//        db.execSQL("DROP TABLE IF EXISTS business");
//        db.execSQL("DROP TABLE IF EXISTS land");
//        db.execSQL("DROP TABLE IF EXISTS live");
//        db.execSQL("DROP TABLE IF EXISTS production");
//        db.execSQL("DROP TABLE IF EXISTS asset");
//        db.execSQL("DROP TABLE IF EXISTS stock");
//        db.execSQL("DROP TABLE IF EXISTS insurance");
//        db.execSQL("DROP TABLE IF EXISTS sale");
//        db.execSQL("DROP TABLE IF EXISTS bankm");
//        db.execSQL("DROP TABLE IF EXISTS masterl");
//        db.execSQL("DROP TABLE IF EXISTS masterla");
//        db.execSQL("DROP TABLE IF EXISTS address");
//        db.execSQL("DROP TABLE IF EXISTS farlist");
//        db.execSQL("DROP TABLE IF EXISTS membermapping");
//        db.execSQL("DROP TABLE IF EXISTS crop");
//        db.execSQL("DROP TABLE IF EXISTS sowing");
//        db.execSQL("DROP TABLE IF EXISTS ads");
//        db.execSQL("DROP TABLE IF EXISTS orgn");
//        db.execSQL("DROP TABLE IF EXISTS icdlist");
//        db.execSQL("DROP TABLE IF EXISTS sno");
//        db.execSQL("DROP TABLE IF EXISTS lrplist");
//        db.execSQL("DROP TABLE IF EXISTS inw");
//        db.execSQL("DROP TABLE IF EXISTS itransfer");
//        db.execSQL("DROP TABLE IF EXISTS iconfirm");
//        db.execSQL("DROP TABLE IF EXISTS icdoc");
//        db.execSQL("DROP TABLE IF EXISTS icdip");
//        db.execSQL("DROP TABLE IF EXISTS oci");
//        db.execSQL("DROP TABLE IF EXISTS mpin");
//        db.execSQL("DROP TABLE IF EXISTS attachment");
//        db.execSQL("DROP TABLE IF EXISTS template");
//        db.execSQL("DROP TABLE IF EXISTS templatefarmer");
//        db.execSQL("DROP TABLE IF EXISTS gpsactivity");
//        onCreate(db);
    }

    public boolean insertContact (String pc,String pn,String pcc,String pcn,String pscc,String pscn,String uc,String un,String hn,String hc,String bp,String cq,String cgst,String sgst,String igst,String oc,String ic,String valueadded) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("In_product_code", pc);
        contentValues.put("In_product_name", pn);
        contentValues.put("In_productcategory_code", pcc);
        contentValues.put("In_productcategory_desc", pcn);
        contentValues.put("In_productsubcategory_code", pscc);
        contentValues.put("In_productsubcategory_desc", pscn);
        contentValues.put("In_uomtype_code", uc);
        contentValues.put("In_uomtype_desc", un);
        contentValues.put("In_hsn_code", hn);
        contentValues.put("In_hsn_desc", hc);
        contentValues.put("In_base_price", bp);
        contentValues.put("In_current_qty", cq);
        contentValues.put("In_cgst", cgst);
        contentValues.put("In_sgst", sgst);
        contentValues.put("In_igst", igst);
        contentValues.put("In_orgn_code", oc);
        contentValues.put("In_ic_code", ic);
        contentValues.put("valueadded", valueadded);
        db.insert("product", null, contentValues);
        Log.e("Table Product","Inserted");
        return true;
    }
    public boolean insertsno (String n1,String n2,String n3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);



        db.insert("sno", null, contentValues);
        Log.e("Table SNO","Inserted");
        return true;
    }
    public boolean inserttemplate (String n1,String n2,String n3,String n4,String n5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("template_id", n1);
        contentValues.put("template_consent", n2);
        contentValues.put("effective_From", n3);
        contentValues.put("effective_to", n4);
        contentValues.put("lang_code", n5);



        db.insert("template", null, contentValues);
        Log.e("Table Template","Inserted");
        return true;
    }

    public boolean insertgps (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("qrvalue", n1);
        contentValues.put("latlng", n2);
        contentValues.put("cdate", n3);
        contentValues.put("istime", n4);
        contentValues.put("ictime", n5);
        contentValues.put("pstime", n6);
        contentValues.put("pctime", n7);
        contentValues.put("flag", n8);
        contentValues.put("userid", n9);
        contentValues.put("rowid", n10);



        db.insert("gpsactivity", null, contentValues);
        Log.e("Table GPS","Inserted");
        return true;
    }
    public boolean inserttemplatefarmer (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("in_farmerconsent_rowid", n1);
        contentValues.put("in_template_id", n2);
        contentValues.put("in_consent_owner_id", n3);
        contentValues.put("in_consent_to", n4);
        contentValues.put("in_lang_code", n5);
        contentValues.put("template_consent", n6);
        contentValues.put("in_otp_flag", n7);
        contentValues.put("in_isverified", n8);
        contentValues.put("in_attachment_flag", n9);



        db.insert("templatefarmer", null, contentValues);
        Log.e("Table TemplateFarmer","Inserted");
        return true;
    }
    public boolean insertproduct (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item", n1);
        contentValues.put("quantity", n2);
        contentValues.put("rate", n3);
        contentValues.put("amount", n4);
        contentValues.put("discount", n5);
        contentValues.put("netamount", n6);
        contentValues.put("pid", n7);
        contentValues.put("flag", n8);
        contentValues.put("ts", n9);

        db.insert("productlist", null, contentValues);
        Log.e("Table Productlist","Inserted");
        return true;
    }

    public boolean insertMercID (String n1,String n2,String n3,String n4,String n5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("out_paytm_masid", n1);
        contentValues.put("out_paytm_fpocode", n2);
        contentValues.put("out_paytm_type", n3);
        contentValues.put("out_paytm_merchantId", n4);
        contentValues.put("out_paytm_tid", n5);


        db.insert("mercid", null, contentValues);
        Log.e("Table Merc ID","Inserted");
        return true;
    }

    public boolean insertAepsMercID (String n1,String n2,String n3,String n4,String n5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("out_aeps_masid", n1);
        contentValues.put("out_aeps_fpocode", n2);
        contentValues.put("out_aeps_type", n3);
        contentValues.put("out_aeps_merchantid", n4);
        contentValues.put("out_aeps_tid", n5);

        db.insert("aepsmercid", null, contentValues);
        Log.e("Table AepsMerc ID","Inserted");
        return true;
    }

    public boolean inserttab (String n1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tab", n1);


        db.insert("tab", null, contentValues);
        Log.e("Table Tab","Inserted");
        return true;
    }

    public boolean inserttablist (String n1,String n2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tab", n1);
        contentValues.put("field", n2);


        db.insert("tablist", null, contentValues);
        Log.e("Table Tablist","Inserted");
        return true;
    }

    public boolean istate (String n1,String n2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sname", n1);
        contentValues.put("scode", n2);


        db.insert("state", null, contentValues);
        Log.e("Table State","Inserted");
        return true;
    }

    public boolean invoice (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("invoiceno", n1);
        contentValues.put("balance", n2);
        contentValues.put("amount", n3);
        contentValues.put("paymode", n4);
        contentValues.put("refno", n5);
        contentValues.put("date", n6);
        contentValues.put("cname", n7);
        contentValues.put("flag", n8);

        db.insert("invoice", null, contentValues);
        Log.e("Table Invoice","Inserted");
        return true;
    }


    public boolean inward (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("invoiceno", n1);
        contentValues.put("balance", n2);
        contentValues.put("amount", n3);
        contentValues.put("paymode", n4);
        contentValues.put("refno", n5);
        contentValues.put("date", n6);
        contentValues.put("cname", n7);
        contentValues.put("flag", n8);

        db.insert("inward", null, contentValues);
        Log.e("Table inward","Inserted");
        return true;
    }

    public boolean paylist (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("invoiceno", n1);
        contentValues.put("paymode", n2);
        contentValues.put("amountpaid", n3);
        contentValues.put("refno", n4);
        contentValues.put("date", n5);
        contentValues.put("bal", n6);
        contentValues.put("flag", n7);
        contentValues.put("uflag", n8);
        contentValues.put("ckno", n9);
        contentValues.put("fstatus", n10);
        contentValues.put("msg", n11);

        db.insert("paylist", null, contentValues);
        Log.e("Table Paylist","Inserted");
        return true;
    }
    public boolean paylist2 (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("invoiceno", n1);
        contentValues.put("paymode", n2);
        contentValues.put("amountpaid", n3);
        contentValues.put("refno", n4);
        contentValues.put("date", n5);
        contentValues.put("bal", n6);
        contentValues.put("flag", n7);
        contentValues.put("uflag", n8);
        contentValues.put("ckno", n9);

        db.insert("paylist2", null, contentValues);
        Log.e("Table Paylist2","Inserted");
        return true;
    }
    public boolean astock (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item", n1);
        contentValues.put("adjusttype", n2);
        contentValues.put("inwardno", n3);
        contentValues.put("stock", n4);
        contentValues.put("aqty", n5);
        contentValues.put("rqty", n6);
        contentValues.put("flag", n7);
        contentValues.put("lrp", n8);

        db.insert("astock", null, contentValues);
        Log.e("Table Astock","Inserted");
        return true;
    }
    public boolean updateastock (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item", n1);
        contentValues.put("adjusttype", n2);
        contentValues.put("inwardno", n3);
        contentValues.put("stock", n4);
        contentValues.put("aqty", n5);
        contentValues.put("rqty", n6);
        contentValues.put("flag", n7);
        contentValues.put("lrp", n8);

        db.update("astock", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Astock","Updated");
        return true;
    }

    public boolean astock2 (String n1,String n2,String n3,String n4,String n5,String n6,String n7) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item", n1);
        contentValues.put("asno", n2);
        contentValues.put("rqty", n3);
        contentValues.put("ids", n4);
        contentValues.put("aqty", n5);
        contentValues.put("date", n6);
        contentValues.put("org", n7);


        db.insert("astock2", null, contentValues);
        Log.e("Table Astock2","Inserted");
        return true;
    }

    public boolean updateinvoice (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("invoiceno", n1);
        contentValues.put("balance", n2);
        contentValues.put("amount", n3);
        contentValues.put("paymode", n4);
        contentValues.put("refno", n5);
        contentValues.put("date", n6);
        contentValues.put("cname", n7);
        contentValues.put("flag", n8);

        db.update("invoice", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Invoice","updated");
        return true;
    }

    public boolean updateinward (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("invoiceno", n1);
        contentValues.put("balance", n2);
        contentValues.put("amount", n3);
        contentValues.put("paymode", n4);
        contentValues.put("refno", n5);
        contentValues.put("date", n6);
        contentValues.put("cname", n7);
        contentValues.put("flag", n8);

        db.update("inward", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table inward","updated");
        return true;
    }
    public boolean insertproduct2 (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item", n1);
        contentValues.put("quantity", n2);
        contentValues.put("rate", n3);
        contentValues.put("amount", n4);
        contentValues.put("discount", n5);
        contentValues.put("netamount", n6);
        contentValues.put("pid", n7);
        contentValues.put("flag", n8);
        contentValues.put("ts", n9);

        db.insert("productlist2", null, contentValues);
        Log.e("Table Productlist","Inserted");
        return true;
    }

    public boolean insertsupplier (String n1,String n2,String n3,String n4,String n5,String n6,String n7) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("In_supplier_code", n1);
        contentValues.put("In_supplier_name", n2);
        contentValues.put("In_pan_no", n3);
        contentValues.put("In_supplier_state_code", n4);
        contentValues.put("In_supplier_state_desc", n5);
        contentValues.put("In_supplier_location",n6);
        contentValues.put("In_ic_code",n7);




        db.insert("supplierlist", null, contentValues);
        Log.e("Table Supplier","Inserted");
        return true;
    }

    public boolean insertcustomer (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("farmer_code", n1);
        contentValues.put("farmer_name", n2);
        contentValues.put("village_name", n3);
        contentValues.put("farmer_address", n4);
        contentValues.put("state_name", n5);
        contentValues.put("farmer_village_code", n6);
        contentValues.put("farmer_state_code", n7);
        contentValues.put("mor", n8);
        contentValues.put("phone",n9);
        contentValues.put("ic_code",n10);
        contentValues.put("surname",n11);
        contentValues.put("fhwname",n12);
        db.insert("customerlist", null, contentValues);
        Log.e("Table customer","Inserted");
        return true;
    }
    public boolean updatecustomer(String fc,String n2,String n3,String n4,String n6,String n9,String n11,String n12)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("farmer_name", n2);
        contentValues.put("village_name", n3);
        contentValues.put("farmer_address", n4);
        contentValues.put("farmer_village_code", n6);
        contentValues.put("phone",n9);
        contentValues.put("surname",n11);
        contentValues.put("fhwname",n12);
        database.update("customerlist", contentValues, "farmer_code=?", new String[] {fc});
       // database.close();
        return true;
    }
    public boolean insertcustomernm (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("farmer_code", n1);
        contentValues.put("farmer_name", n2);
        contentValues.put("village_name", n3);
        contentValues.put("farmer_address", n4);
        contentValues.put("state_name", n5);
        contentValues.put("farmer_village_code", n6);
        contentValues.put("farmer_state_code", n7);
        contentValues.put("mor", n8);
        contentValues.put("phone",n9);
        contentValues.put("ic_code",n10);
        contentValues.put("surname",n11);
        contentValues.put("fhwname",n12);




        db.insert("customerlistnm", null, contentValues);
        Log.e("Table customer","Inserted");
        return true;
    }
    public boolean insertinward (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14,String n15,String n16) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("inwardno", n1);
        contentValues.put("date", n2);
        contentValues.put("supplier", n3);
        contentValues.put("location", n4);
        contentValues.put("tax", n5);
        contentValues.put("transport", n6);
        contentValues.put("others", n7);
        contentValues.put("inwardamount", n8);
        contentValues.put("ids", n9);
        contentValues.put("itemlist", n10);
        contentValues.put("itemtaxlist", n11);
        contentValues.put("flag", n12);
        contentValues.put("img", n13);
        contentValues.put("lcode", n14);
        contentValues.put("inweb", n15);
        contentValues.put("billno", n16);

        db.insert("inwardlist", null, contentValues);
        Log.e("Table Inward","Inserted");
        return true;
    }
    public boolean insertinvoice (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14,String n15,String n16,String n17) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("invoiceno", n1);
        contentValues.put("date", n2);
        contentValues.put("supplier", n3);
        contentValues.put("location", n4);
        contentValues.put("tax", n5);
        contentValues.put("transport", n6);
        contentValues.put("others", n7);
        contentValues.put("invoiceamount", n8);
        contentValues.put("ids", n9);
        contentValues.put("itemlist", n10);
        contentValues.put("itemtaxlist", n11);
        contentValues.put("flag", n12);

        contentValues.put("lcode", n13);
        contentValues.put("sid", n14);
        contentValues.put("inweb", n15);
        contentValues.put("phone", n16);
        contentValues.put("mem", n17);
        db.insert("invoicelist", null, contentValues);
        Log.e("Table Inward","Inserted");
        return true;
    }


    public boolean updateproduct (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item", n1);
        contentValues.put("quantity", n2);
        contentValues.put("rate", n3);
        contentValues.put("amount", n4);
        contentValues.put("discount", n5);
        contentValues.put("netamount", n6);
        contentValues.put("pid", n7);
        contentValues.put("flag", n8);
        contentValues.put("ts", n9);

        db.update("productlist", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Productlist","Updated");
        return true;


    }
    public boolean updateproduct2 (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item", n1);
        contentValues.put("quantity", n2);
        contentValues.put("rate", n3);
        contentValues.put("amount", n4);
        contentValues.put("discount", n5);
        contentValues.put("netamount", n6);
        contentValues.put("pid", n7);
        contentValues.put("flag", n8);
        contentValues.put("ts", n9);

        db.update("productlist2", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Productlist","Updated");
        return true;


    }
    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id,String pc,String pn,String pcc,String pcn,String pscc,String pscn,String uc,String un,String hn,String hc,String bp,String cq,String cgst,String sgst,String igst,String oc,String ic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("In_product_code", pc);
        contentValues.put("In_product_name", pn);
        contentValues.put("In_productcategory_code", pcc);
        contentValues.put("In_productcategory_desc", pcn);
        contentValues.put("In_productsubcategory_code", pscc);
        contentValues.put("In_productsubcategory_desc", pscn);
        contentValues.put("In_uomtype_code", uc);
        contentValues.put("In_uomtype_desc", un);
        contentValues.put("In_hsn_code", hc);
        contentValues.put("In_hsn_desc", hn);
        contentValues.put("In_base_price", bp);
        contentValues.put("In_current_qty", cq);
        contentValues.put("In_cgst", cgst);
        contentValues.put("In_sgst", sgst);
        contentValues.put("In_igst", igst);
        contentValues.put("In_orgn_code", oc);
        contentValues.put("In_ic_code", ic);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public boolean upinvoice (Integer id,String pc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("inweb", pc);
        db.update("invoicelist", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public boolean upinward (Integer id,String pc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("inweb", pc);
        db.update("inwardlist", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteProductlist (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Productlist","Deleted");
        return db.delete("productlist",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }

    public Integer deleteProductlist2 (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Productlist2","Deleted");
        return db.delete("productlist2",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }

    public Integer deletestock (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table astock","Deleted");
        return db.delete("astock",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    public boolean insertaddress (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14,String n15,String n16,String n17) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fc", n1);
        contentValues.put("fn", n2);
        contentValues.put("fid", n3);
        contentValues.put("at", n4);
        contentValues.put("atc", n5);
        contentValues.put("pi", n6);
        contentValues.put("addr", n7);
        contentValues.put("vi", n8);
        contentValues.put("vic", n9);
        contentValues.put("gp", n10);
        contentValues.put("gpc", n11);
        contentValues.put("ta", n12);

        contentValues.put("tac", n13);
        contentValues.put("di", n14);
        contentValues.put("dic", n15);
        contentValues.put("flag", n16);
        contentValues.put("slno", n17);

        db.insertOrThrow("address", null, contentValues);
        Log.e("Table Address","Inserted");
        return true;
    }


    public boolean updateaddress (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14,String n15,String n16,String n17) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fc", n1);
        contentValues.put("fn", n2);
        contentValues.put("fid", n3);
        contentValues.put("at", n4);
        contentValues.put("atc", n5);
        contentValues.put("pi", n6);
        contentValues.put("addr", n7);
        contentValues.put("vi", n8);
        contentValues.put("vic", n9);
        contentValues.put("gp", n10);
        contentValues.put("gpc", n11);
        contentValues.put("ta", n12);

        contentValues.put("tac", n13);
        contentValues.put("di", n14);
        contentValues.put("dic", n15);
        contentValues.put("flag", n16);
        contentValues.put("slno", n17);

        db.update("address", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Address","Updated");
        return true;


    }

    public boolean insertads (String n1, String n2, String n3, String n4, String n5, String n6, String n7, String n8, String n9, String n10, String n11, String n12, String n13, String n14, String n15, String n16, String n17, String n18, String n19, String n20, String n21, String n22, String n23, String n24, String n25, String n26, String n27, String n28, String n29, String n30, String n31, String n32, String n33, String n34, String n35, String n36, String n37, String n38, String n39, String n40, String n41, String n42, String n43, String n44, String n45, String n46, String n47, String n48, String n49, String n50, String n51, String n52, String n53, String n54, String n55, String n56, String n57, String n58, String n59, String n60, String n61, String n62, String n63, String n64, String n65, String n66, String n67, String n68, String n69, String n70, String n71, String n72, String n73, String n74, String n75, String n76, String n77, String n78, String n79, String n80, String n81, String n82, String n83, String n84, String n85, String n86, String n87, String n88, String n89, String n90, String n91, String n92, String n93, String n94,String n95, String n96, String n97, String n98, String n99, String n100, String n101, String n102,String n103,String n104,String n105,String n106,String n107,String n108,String n109,String n110,String n111) {
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
        contentValues.put("v26", n26);
        contentValues.put("v27", n27);
        contentValues.put("v28", n28);
        contentValues.put("v29", n29);
        contentValues.put("v30", n30);
        contentValues.put("v31", n31);
        contentValues.put("v32", n32);
        contentValues.put("v33", n33);
        contentValues.put("v34", n34);
        contentValues.put("v35", n35);
        contentValues.put("v36", n36);
        contentValues.put("v37", n37);
        contentValues.put("v38", n38);
        contentValues.put("v39", n39);
        contentValues.put("v40", n40);
        contentValues.put("v41", n41);
        contentValues.put("v42", n42);
        contentValues.put("v43", n43);
        contentValues.put("v44", n44);
        contentValues.put("v45", n45);
        contentValues.put("v46", n46);
        contentValues.put("v47", n47);
        contentValues.put("v48", n48);
        contentValues.put("v49", n49);
        contentValues.put("v50", n50);
        contentValues.put("v51", n51);
        contentValues.put("v52", n52);
        contentValues.put("v53", n53);
        contentValues.put("v54", n54);
        contentValues.put("v55", n55);
        contentValues.put("v56", n56);
        contentValues.put("v57", n57);
        contentValues.put("v58", n58);
        contentValues.put("v59", n59);
        contentValues.put("v60", n60);
        contentValues.put("v61", n61);
        contentValues.put("v62", n62);
        contentValues.put("v63", n63);
        contentValues.put("v64", n64);
        contentValues.put("v65", n65);
        contentValues.put("v66", n66);
        contentValues.put("v67", n67);
        contentValues.put("v68", n68);
        contentValues.put("v69", n69);
        contentValues.put("v70", n70);
        contentValues.put("v71", n71);
        contentValues.put("v72", n72);
        contentValues.put("v73", n73);
        contentValues.put("v74", n74);
        contentValues.put("v75", n75);
        contentValues.put("v76", n76);
        contentValues.put("v77", n77);
        contentValues.put("v78", n78);
        contentValues.put("v79", n79);
        contentValues.put("v80", n80);
        contentValues.put("v81", n81);
        contentValues.put("v82", n82);
        contentValues.put("v83", n83);
        contentValues.put("v84", n84);
        contentValues.put("v85", n85);
        contentValues.put("v86", n86);
        contentValues.put("v87", n87);
        contentValues.put("v88", n88);
        contentValues.put("v89", n89);
        contentValues.put("v90", n90);
        contentValues.put("v91", n91);
        contentValues.put("v92", n92);
        contentValues.put("v93", n93);
        contentValues.put("v94", n94);
        contentValues.put("v95", n95);
        contentValues.put("v96", n96);
        contentValues.put("v97", n97);
        contentValues.put("v98", n98);
        contentValues.put("v99", n99);
        contentValues.put("v100", n100);
        contentValues.put("v101", n101);
        contentValues.put("v102", n102);
        contentValues.put("v103", n103);
        contentValues.put("v104", n104);
        contentValues.put("v105", n105);
        contentValues.put("v106", n106);
        contentValues.put("v107", n107);
        contentValues.put("v108", n108);
        contentValues.put("v109", n109);
        contentValues.put("v110", n110);
        contentValues.put("v111", n111);


        db.insertOrThrow("ads", null, contentValues);
        Log.e("Table ADS","Inserted");
        return true;
    }

    public boolean updateads (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14,String n15,String n16,String n17,String n18,String n19,String n20,String n21,String n22,String n23,String n24,String n25,String n26,String n27,String n28,String n29,String n30,String n31,String n32,String n33,String n34,String n35,String n36,String n37,String n38,String n39,String n40,String n41,String n42,String n43,String n44,String n45,String n46,String n47,String n48,String n49,String n50,String n51,String n52,String n53,String n54,String n55,String n56,String n57,String n58,String n59,String n60,String n61,String n62,String n63,String n64,String n65,String n66,String n67,String n68,String n69,String n70,String n71,String n72,String n73,String n74,String n75,String n76,String n77,String n78,String n79,String n80,String n81,String n82,String n83,String n84,String n85,String n86,String n87,String n88,String n89,String n90,String n91,String n92,String n93,String n94,String n95, String n96, String n97, String n98, String n99, String n100, String n101, String n102,String n103,String n104,String n105,String n106,String n107,String n108,String n109,String n110,String n111) {
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
        contentValues.put("v26", n26);
        contentValues.put("v27", n27);
        contentValues.put("v28", n28);
        contentValues.put("v29", n29);
        contentValues.put("v30", n30);
        contentValues.put("v31", n31);
        contentValues.put("v32", n32);
        contentValues.put("v33", n33);
        contentValues.put("v34", n34);
        contentValues.put("v35", n35);
        contentValues.put("v36", n36);
        contentValues.put("v37", n37);
        contentValues.put("v38", n38);
        contentValues.put("v39", n39);
        contentValues.put("v40", n40);
        contentValues.put("v41", n41);
        contentValues.put("v42", n42);
        contentValues.put("v43", n43);
        contentValues.put("v44", n44);
        contentValues.put("v45", n45);
        contentValues.put("v46", n46);
        contentValues.put("v47", n47);
        contentValues.put("v48", n48);
        contentValues.put("v49", n49);
        contentValues.put("v50", n50);
        contentValues.put("v51", n51);
        contentValues.put("v52", n52);
        contentValues.put("v53", n53);
        contentValues.put("v54", n54);
        contentValues.put("v55", n55);
        contentValues.put("v56", n56);
        contentValues.put("v57", n57);
        contentValues.put("v58", n58);
        contentValues.put("v59", n59);
        contentValues.put("v60", n60);
        contentValues.put("v61", n61);
        contentValues.put("v62", n62);
        contentValues.put("v63", n63);
        contentValues.put("v64", n64);
        contentValues.put("v65", n65);
        contentValues.put("v66", n66);
        contentValues.put("v67", n67);
        contentValues.put("v68", n68);
        contentValues.put("v69", n69);
        contentValues.put("v70", n70);
        contentValues.put("v71", n71);
        contentValues.put("v72", n72);
        contentValues.put("v73", n73);
        contentValues.put("v74", n74);
        contentValues.put("v75", n75);
        contentValues.put("v76", n76);
        contentValues.put("v77", n77);
        contentValues.put("v78", n78);
        contentValues.put("v79", n79);
        contentValues.put("v80", n80);
        contentValues.put("v81", n81);
        contentValues.put("v82", n82);
        contentValues.put("v83", n83);
        contentValues.put("v84", n84);
        contentValues.put("v85", n85);
        contentValues.put("v86", n86);
        contentValues.put("v87", n87);
        contentValues.put("v88", n88);
        contentValues.put("v89", n89);
        contentValues.put("v90", n90);
        contentValues.put("v91", n91);
        contentValues.put("v92", n92);
        contentValues.put("v93", n93);
        contentValues.put("v94", n94);
        contentValues.put("v95", n95);
        contentValues.put("v96", n96);
        contentValues.put("v97", n97);
        contentValues.put("v98", n98);
        contentValues.put("v99", n99);
        contentValues.put("v100", n100);
        contentValues.put("v101", n101);
        contentValues.put("v102", n102);
        contentValues.put("v103", n103);
        contentValues.put("v104", n104);
        contentValues.put("v105", n105);
        contentValues.put("v106", n106);
        contentValues.put("v107", n107);
        contentValues.put("v108", n108);
        contentValues.put("v109", n109);
        contentValues.put("v110", n110);
        contentValues.put("v111", n111);
        db.update("ads", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table ADS","Updated");
        return true;


    }
    public Integer deletefarmer (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table farmerh","Deleted");
        return db.delete("farmerh",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }
    public boolean inserfarlist (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14,String n15,String n16) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fid", n1);
        contentValues.put("fc", n2);
        contentValues.put("fn", n3);
        contentValues.put("sn", n4);
        contentValues.put("vi", n5);
        contentValues.put("gp", n6);
        contentValues.put("ta", n7);
        contentValues.put("di", n8);
        contentValues.put("v1", n9);
        contentValues.put("v2", n10);
        contentValues.put("v3", n11);
        contentValues.put("v4", n12);
        contentValues.put("v5", n13);
        contentValues.put("fpoorgn_code", n14);
        contentValues.put("fpomember_code", n15);
        contentValues.put("v8", n16);

        db.insert("farlist", null, contentValues);
        Log.e("Table Farlist","Inserted");
        return true;
    }

    public boolean membermapping (String n1,String n2, String n3, String n4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("farmername", n1);
        contentValues.put("farmercode", n2);
        contentValues.put("fpoorgncode", n3);
        contentValues.put("flag", n4);

        db.insert("membermapping", null, contentValues);
        Log.e("Table memmap","Inserted");
        return true;
    }

    public boolean updatefarlist(String fc, String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n12,String n13)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fid", n1);
        contentValues.put("fc", n2);
        contentValues.put("fn", n3);
        contentValues.put("sn", n4);
        contentValues.put("vi", n5);
        contentValues.put("gp", n6);
        contentValues.put("ta", n7);
        contentValues.put("di", n8);
        contentValues.put("v1", n9);
        contentValues.put("v2", n10);

        contentValues.put("v4", n12);
        contentValues.put("v5", n13);
        database.update("farlist", contentValues, "fc=?", new String[] {fc});
       // database.close();
        return true;
    }
    public boolean insertkyc (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,int n10,String n11) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", n1);
        contentValues.put("subtype", n2);
        contentValues.put("dno", n3);
        contentValues.put("vtd", n4);
        contentValues.put("fid", n5);
        contentValues.put("f1", n6);
        contentValues.put("f2", n7);
        contentValues.put("tms", n8);
        contentValues.put("stmc", n9);
        contentValues.put("rid", n10);
        contentValues.put("poto", n11);


        db.insertOrThrow("kyc", null, contentValues);
        Log.e("Table KYC","Inserted");
        return true;
    }
    public boolean updatekyc (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,int n10,String n11) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", n1);
        contentValues.put("subtype", n2);
        contentValues.put("dno", n3);
        contentValues.put("vtd", n4);
        contentValues.put("fid", n5);
        contentValues.put("f1", n6);
        contentValues.put("f2", n7);
        contentValues.put("tms", n8);
        contentValues.put("stmc", n9);
        contentValues.put("rid", n10);
        contentValues.put("poto", n11);
        db.update("kyc", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table KYC","Updated");
        return true;
    }
    public Integer deleteaddress (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table address","Deleted");
        return db.delete("address",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }

    public boolean insertbank (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,int n11,String n12,String n13) {
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
    public boolean updatebank (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String  n9,String n10,int n11,String n12,String n13) {
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
    public Integer deletebank (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table bank","Deleted");
        return db.delete("bank",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }

    public boolean insertcrop (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12) {
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

        db.insertOrThrow("crop", null, contentValues);
        Log.e("Table Crop","Inserted");
        return true;
    }


    public boolean updatecrop (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12) {
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

        db.update("crop", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Crop","Updated");
        return true;
    }

    public boolean insertsowing (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14,String n15,String n16,String n17,String e1,String e2,String e3,String e4,String e5) {
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
        contentValues.put("n1", e1);
        contentValues.put("n2", e2);
        contentValues.put("n3", e3);
        contentValues.put("n4", e4);
        contentValues.put("n5", e5);

        db.insert("sowing", null, contentValues);
        Log.e("Table Sowing","Inserted");
        return true;
    }
    public boolean updatesowing (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14,String n15,String n16,String n17,String e1,String e2,String e3,String e4,String e5) {
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
        contentValues.put("n1", e1);
        contentValues.put("n2", e2);
        contentValues.put("n3", e3);
        contentValues.put("n4", e4);
        contentValues.put("n5", e5);

        db.update("sowing", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Sowing","Updated");
        return true;
    }
    public boolean insertpersonal (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12) {
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




        db.insertOrThrow("personal", null, contentValues);
        Log.e("Table Personal","Inserted");
        return true;
    }
    public boolean updatepersonal (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12) {
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

        db.update("personal", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Personal","Updated");
        return true;
    }

    public boolean insertfamily (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11) {
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





        db.insertOrThrow("family", null, contentValues);
        Log.e("Table Family","Inserted");
        return true;
    }
    public boolean updatefamily (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11) {
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


        db.update("family", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Family","Updated");
        return true;
    }

    public boolean insertorgn (String n1,String n2,String n3,String n4,String n5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);
        contentValues.put("v4", n4);
        contentValues.put("v5", n5);





        db.insertOrThrow("orgn", null, contentValues);
        Log.e("Table Orgn","Inserted");
        return true;
    }
    public boolean inserticdoc (String n1,String n2,String n3,String n4,String n5,String n6) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);
        contentValues.put("v4", n4);
        contentValues.put("v5", n5);
        contentValues.put("v6", n6);





        db.insertOrThrow("icdoc", null, contentValues);
        Log.e("Table ICDOC","Inserted");
        return true;
    }

    public boolean insertitransfer (String n1,String n2,String n3,String n4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);
        contentValues.put("v4", n4);






        db.insertOrThrow("itransfer", null, contentValues);
        Log.e("Table Itransfer","Inserted");
        return true;
    }


    public boolean insertoci (String n1,String n2,String n3,String n4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);
        contentValues.put("v4", n4);







        db.insertOrThrow("oci", null, contentValues);
        Log.e("Table OCI","Inserted");
        return true;
    }
    public boolean inserticdlist (String n1,String n2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);






        db.insertOrThrow("icdlist", null, contentValues);
        Log.e("Table ICD LIST","Inserted");
        return true;
    }

    public boolean insertlrplist (String n1,String n2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);






        db.insertOrThrow("lrplist", null, contentValues);
        Log.e("Table LRP LIST","Inserted");
        return true;
    }
    public boolean inserttraining (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11) {
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





        db.insertOrThrow("training", null, contentValues);
        Log.e("Table Training","Inserted");
        return true;
    }
    public boolean updatetraining (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11) {
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


        db.update("training", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Training","Updated");
        return true;
    }
    public boolean insertlive (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9) {
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






        db.insertOrThrow("live", null, contentValues);
        Log.e("Table Live","Inserted");
        return true;
    }
    public boolean updatelive (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9) {
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



        db.update("live", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Live","Updated");
        return true;
    }
    public boolean insertproduction (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14) {
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






        db.insertOrThrow("production", null, contentValues);
        Log.e("Table Production","Inserted");
        return true;
    }
    public boolean updateproduction (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14) {
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


        db.update("production", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Production","Updated");
        return true;
    }
    public boolean insertasset (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10) {
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






        db.insertOrThrow("asset", null, contentValues);
        Log.e("Table Asset","Inserted");
        return true;
    }
    public boolean updateasset (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10) {
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



        db.update("asset", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Asset","Updated");
        return true;
    }

    public boolean insertstock (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10) {
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






        db.insertOrThrow("stock", null, contentValues);
        Log.e("Table Stock","Inserted");
        return true;
    }
    public boolean updatestock (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10) {
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



        db.update("stock", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table stock","Updated");
        return true;
    }
    public boolean insertland (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14) {
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





        db.insert("land", null, contentValues);
        Log.e("Table Land","Inserted");
        return true;
    }
    public boolean updateland (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14) {
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


        db.update("land", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Land","Updated");
        return true;
    }
    public boolean insertloan (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14,String n15) {
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





        db.insertOrThrow("loan", null, contentValues);
        Log.e("Table Loan","Inserted");
        return true;
    }
    public boolean updateloan (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14,String n15) {
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


        db.update("loan", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table loan","Updated");
        return true;
    }
    public boolean insertinput (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14,String n15,String n16) {
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





        db.insertOrThrow("input", null, contentValues);
        Log.e("Table Input","Inserted");
        return true;
    }
    public boolean updateinput (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14,String n15,String n16) {
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


        db.update("input", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Input","Updated");
        return true;
    }
    public boolean insertinsurance (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14) {
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






        db.insertOrThrow("insurance", null, contentValues);
        Log.e("Table Insurance","Inserted");
        return true;
    }
    public boolean updateinsurance (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14) {
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



        db.update("insurance", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Insurance","Updated");
        return true;
    }

    public boolean insertsale (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14) {
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






        db.insertOrThrow("sale", null, contentValues);
        Log.e("Table Sale","Inserted");
        return true;
    }
    public boolean updatesale (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String n14) {
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



        db.update("sale", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Sale","Updated");
        return true;
    }
    public boolean insertloanr (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8) {
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






        db.insertOrThrow("loanr", null, contentValues);
        Log.e("Table Loanr","Inserted");
        return true;
    }


    public boolean inserticdip (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8) {
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






        db.insertOrThrow("icdip", null, contentValues);
        Log.e("Table ICDIP","Inserted");
        return true;
    }
    public boolean updateloanr (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8) {
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



        db.update("loanr", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table loanr","Updated");
        return true;
    }
    public boolean insertloanq (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8) {
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






        db.insertOrThrow("loanq", null, contentValues);
        Log.e("Table Loanq","Inserted");
        return true;
    }
    public boolean updateloanq (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8) {
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



        db.update("loanq", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table loanq","Updated");
        return true;
    }
    public boolean insertshare (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8) {
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






        db.insertOrThrow("shareh", null, contentValues);
        Log.e("Table Share","Inserted");
        return true;
    }
    public boolean updateshare (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8) {
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



        db.update("shareh", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Share","Updated");
        return true;
    }
    public boolean insertbusiness (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8) {
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






        db.insertOrThrow("business", null, contentValues);
        Log.e("Table Business","Inserted");
        return true;
    }
    public boolean updatebusiness (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8) {
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



        db.update("business", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Business","Updated");
        return true;
    }
    public Integer deletepersonal (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Personal","Deleted");
        return db.delete("personal",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }

    public Integer deleteoc (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table OC","Deleted");
        return db.delete("icdoc",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }

    public Integer deleteip (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table IP","Deleted");
        return db.delete("icdip",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }

    public Integer deleteinput (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Input","Deleted");
        return db.delete("input",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }
    public Integer deleteland (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Land","Deleted");
        return db.delete("land",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }
    public Integer deleteproduction (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Production","Deleted");
        return db.delete("production",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }
    public Integer deletestockn (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table stock","Deleted");
        return db.delete("stock",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }
    public Integer deletesale (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Sale","Deleted");
        return db.delete("sale",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }
    public Integer deletetraining (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Training","Deleted");
        return db.delete("training",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }
    public Integer deleteshare (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Share","Deleted");
        return db.delete("shareh",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }

    public Integer deletebusiness (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Business","Deleted");
        return db.delete("business",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }
    public Integer deleteloanq (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Loanq","Deleted");
        return db.delete("loanq",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }
    public boolean insertfarmer (String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String  n14,String  n15,String n16,String n17,String n18,String  n19,String  n20,String n21,String n22,String n23,String n24,String n25,String n26, String n27) {
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

    public boolean updatefarmer (Integer id,String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9,String n10,String n11,String n12,String n13,String  n14,String  n15,String n16,String n17,String n18,String  n19,String  n20,String n21,String n22,String n23,String n24) {
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
        db.update("farmerh", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        Log.e("Table Farmer","Updated");
        return true;
    }

    public Integer deletefamily (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Family","Deleted");
        return db.delete("family",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }

    public Integer deletelive (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Livestock","Deleted");
        return db.delete("live",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }
    public Integer deletecrop (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Crop","Deleted");
        return db.delete("crop",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }
    public Integer deletesowing (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Sowing","Deleted");
        return db.delete("sowing",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }

    public Integer deleteasset (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Asset","Deleted");
        return db.delete("asset",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }

    public Integer deleteinsurance (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Insurance","Deleted");
        return db.delete("insurance",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }

    public Integer deleteloan (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Loan","Deleted");
        return db.delete("loan",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }
    public Integer deleteloanr (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table Loanr","Deleted");
        return db.delete("loanr",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }
    public boolean insertbankm (String n1,String n2,String n3,String n4,String n5,String n6) {
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
    public boolean insertinw (String n1,String n2,String n3,String n4,String n5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);
        contentValues.put("v4", n4);
        contentValues.put("v5", n5);






        db.insert("inw", null, contentValues);
        Log.e("Table INW","Inserted");
        return true;
    }

    public boolean inserticonfirm (String n1,String n2,String n3,String n4,String n5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("v1", n1);
        contentValues.put("v2", n2);
        contentValues.put("v3", n3);
        contentValues.put("v4", n4);
        contentValues.put("v5", n5);






        db.insert("iconfirm", null, contentValues);
        Log.e("Table Iconfirm","Inserted");
        return true;
    }

    public boolean insertmpin (String n1,String n2,String n3,String n4,String n5,String n6) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usercode", n1);
        contentValues.put("username", n2);
        contentValues.put("rolecode", n3);
        contentValues.put("rolename", n4);
        contentValues.put("orgncode", n5);
        contentValues.put("usermpin", n6);
        db.insert("mpin", null, contentValues);
        Log.e("Table Mpin","Inserted");
        return true;
    }

    public boolean insertattachment (String n1,String n2,String n3,String n4,String n5,String n6) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("doctype", n1);
        contentValues.put("docsubtype", n2);
        contentValues.put("filename", n3);
        contentValues.put("remarks", n4);
        contentValues.put("farmercode", n5);
        contentValues.put("flag", n6);
        db.insert("attachment", null, contentValues);
        Log.e("Table Attachment","Inserted");
        return true;
    }

    public boolean insertmasterl(String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9) {
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
    public boolean insertmasterla(String n1,String n2,String n3,String n4,String n5,String n6,String n7,String n8,String n9) {
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





        db.insert("masterla", null, contentValues);
        Log.e("Table masterla","Inserted");
        return true;
    }
    public Integer dkyc (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Table KYC","Deleted");
        return db.delete("kyc",
                "id = ? ",
                new String[] { Integer.toString(id) });


    }
}
