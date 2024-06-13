package cdfi.fintantra.meity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class AttachmentList extends AppCompatActivity {

    RecyclerView recyclerView;
    String flname = "",farmer_code = "", attch_gid = "";
    AutoCompleteTextView doctype,docsubtype,filename,remarks;
    DBHelper dbHelper;
    SQLiteDatabase dbs;
    Button addnew;
    ImageView backimg;
    List<pojoAttachment> attachmentList;
    AttachmentListAdapter attachadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attachment_list);
        dbHelper = new DBHelper(this);
        dbs = dbHelper.getWritableDatabase();
        farmer_code = Pojokyc.farmercode;
        initview();
    }
    public void initview(){
        recyclerView = (RecyclerView) findViewById(R.id.attlist);
        addnew = (Button) findViewById(R.id.addnew);
        backimg = (ImageView) findViewById(R.id.backimg);

        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attachmentpopupwindow("I");
            }
        });
        attlistinit();
    }
    public void attachmentpopupwindow(final String flag){
        final Dialog dialog = new Dialog(AttachmentList.this);
        dialog.setContentView(R.layout.attachment);
        ImageView attachment;
        String[] doctypes = {"Address Proof","Business Plan","ID Proof"};
        String[] docsubtypes = {"Driving License","Family Card","Voter ID"};
        Button bsave,bcancel;
        doctype = dialog.findViewById(R.id.doctype);
        docsubtype = dialog.findViewById(R.id.docsubtype);
        filename = dialog.findViewById(R.id.filename);
//        remarks = dialog.findViewById(R.id.remarks);
        bsave = dialog.findViewById(R.id.bsave);
        bcancel = dialog.findViewById(R.id.but_cancl);
        attachment = dialog.findViewById(R.id.attachment);

        ArrayAdapter<String> dadapter = new ArrayAdapter<String>(AttachmentList.this,R.layout.spinnertext,doctypes);
        doctype.setThreshold(1);
        doctype.setAdapter(dadapter);

        ArrayAdapter<String> dsadapter = new ArrayAdapter<String>(AttachmentList.this,R.layout.spinnertext,docsubtypes);
        docsubtype.setThreshold(1);
        docsubtype.setAdapter(dsadapter);

        bcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        doctype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doctype.showDropDown();
            }
        });
        docsubtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                docsubtype.showDropDown();
            }
        });
        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if(doctype.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(AttachmentList.this, "Please Select Doc Type", Toast.LENGTH_SHORT).show();
                }else if(docsubtype.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(AttachmentList.this, "Please Select DocSub Type", Toast.LENGTH_SHORT).show();
                }else{*/
                    /*Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, 1);*/
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 1);
             //   }
            }
        });
        bsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if(doctype.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(AttachmentList.this, "Please Select Doc Type", Toast.LENGTH_SHORT).show();
                }else if(docsubtype.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(AttachmentList.this, "Please Select DocSub Type", Toast.LENGTH_SHORT).show();
                }else*/ if(filename.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(AttachmentList.this, "Please Upload File", Toast.LENGTH_SHORT).show();
                }else{
                    if(flag.equalsIgnoreCase("I")){
                        dbHelper.insertattachment("", "", flname, "",Pojokyc.farmercode,"Y");
                        Toast.makeText(AttachmentList.this, "Attachment Added Successfully", Toast.LENGTH_SHORT).show();
                    }else if(flag.equalsIgnoreCase("U")){
                        dbs.execSQL("Update attachment set doctype = '', docsubtype = '', filename = '', remarks = '' where farmercode = '"+Pojokyc.farmercode+" ' and id = '"+attch_gid+"' ");
                    }
                    dialog.dismiss();
                }
            }
        });

        if(flag.equalsIgnoreCase("U")){
            Cursor cr = dbs.rawQuery("select * from attachment where farmercode = '"+farmer_code+"' and id = '"+attch_gid+" '",null);
            try {
                if(cr.getCount() > 0){
                    if(cr.moveToFirst()){
                        doctype.setText(cr.getString(cr.getColumnIndexOrThrow("doctype")));
                        docsubtype.setText(cr.getString(cr.getColumnIndexOrThrow("docsubtype")));
                        filename.setText(cr.getString(cr.getColumnIndexOrThrow("filename")));
                        remarks.setText(cr.getString(cr.getColumnIndexOrThrow("remarks")));
                    }
                }
            }catch (Exception er){
                Log.e("error",er.toString());
            }finally {
                cr.close();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == -1) {
                    Uri fileUri = data.getData();
                    Log.e("URIDA", "" + fileUri);
                    String fullPath = FileUtils.getPath(this, fileUri);
                    File file = new File(fullPath);

                    int file_size = Integer.parseInt(String.valueOf(file.length() / 1024));
                    if (file_size > 300) {
                        //  HCDialog.showDialog(AttachmentSaveActivity.this, "Please Select The File Below 300 KB", "0");
                    } else {
                        Log.e("FP", "FP=" + fullPath.substring(fullPath.lastIndexOf("/") + 1));
                        filename.setText("" + fullPath.substring(fullPath.lastIndexOf("/") + 1));
                        //size.setText("" + file_size + " KB");

                        File f = new File(Environment.getExternalStorageDirectory(), "PGA" + "/" + "01");
                        if (!f.exists()) {
                            f.mkdirs();
                        }
                        // String sourcePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TongueTwister/tt_temp.3gp";
                        File source = new File(fullPath);

                        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TongueTwister/tt_1A.3gp";
                        File destination = new File(f + "/" + fullPath.substring(fullPath.lastIndexOf("/") + 1));
                        flname = f + "/" + fullPath.substring(fullPath.lastIndexOf("/") + 1);
                        Log.e("flname1", flname);
                        try {
                            copyFile(source, destination);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

                break;
        }
    }

    public void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.getParentFile().exists())
            destFile.getParentFile().mkdirs();

        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    private String GetString(String filepath) throws IOException {
        InputStream inputStream = new FileInputStream(filepath);
        byte[] byteArray = IOUtils.toByteArray(inputStream);
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        Log.e("ENCODED", "" + encoded);
        return encoded;
    }
    private void attlistinit(){
        Cursor cr = dbs.rawQuery("select * from attachment where farmercode = '"+farmer_code+"' ",null);
        try{
            if(cr.getCount() > 0){
                attachmentList = new ArrayList<>();
                //attachmentList.clear();
                if(cr.moveToFirst()){
                    do{
                        pojoAttachment modelattach = new pojoAttachment();
                        modelattach.setAttachgid(cr.getString(cr.getColumnIndexOrThrow("id")));
                        modelattach.setDoctype(cr.getString(cr.getColumnIndexOrThrow("doctype")));
                        modelattach.setSubtype(cr.getString(cr.getColumnIndexOrThrow("docsubtype")));
                        modelattach.setFilensme(cr.getString(cr.getColumnIndexOrThrow("filename")));
                        modelattach.setRemarks(cr.getString(cr.getColumnIndexOrThrow("remarks")));
                        attachmentList.add(modelattach);
                    }while (cr.moveToNext());
                }
                attachadapter = new AttachmentListAdapter(this, attachmentList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(attachadapter);
            }
        }catch (Exception er){
            Log.e("error",er.toString());
        }finally {
            cr.close();
        }

    }
    public class AttachmentListAdapter extends RecyclerView.Adapter<AttachmentListAdapter.ViewHolder>{
        private List<pojoAttachment> mData;
        private LayoutInflater mInflater;

        // data is passed into the constructor
        AttachmentListAdapter(Context context, List <pojoAttachment> datalist){
            this.mData = datalist;
            this.mInflater = LayoutInflater.from(context);
        }

        // inflates the row layout from xml when needed
        @Override
        public AttachmentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.attachment_card_list, parent, false);
            return new AttachmentListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AttachmentListAdapter.ViewHolder holder, int position) {
            final pojoAttachment attmodel = mData.get(position);
            holder.edtdoctype.setText(attmodel.getDoctype());
            holder.edtdocsubtype.setText(attmodel.getSubtype());
            holder.edtfilename.setText(attmodel.getFilensme());
            holder.edtremarks.setText(attmodel.getRemarks());

            holder.edtattach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    attch_gid = attmodel.getAttachgid();
                    attachmentpopupwindow("U");
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder  {
            TextView edtdoctype,edtdocsubtype,edtfilename,edtremarks;
            ImageView edtattach,vwattach,delattach;


            ViewHolder(View itemView) {
                super(itemView);
                edtdoctype = itemView.findViewById(R.id.edtdoctype);
                edtdocsubtype = itemView.findViewById(R.id.edtdocsubtype);
                edtfilename = itemView.findViewById(R.id.edtfilename);
                edtremarks = itemView.findViewById(R.id.edtremarks);
                delattach = itemView.findViewById(R.id.delattach);
                vwattach = itemView.findViewById(R.id.vwattach);
                edtattach = itemView.findViewById(R.id.edtattach);
            }


        }

    }
}