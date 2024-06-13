package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ChangeMpin extends AppCompatActivity implements TextWatcher {
    EditText mpin1,mpin2,mpin3,mpin4,nmpin1,nmpin2,nmpin3,nmpin4,cnpin1,cnpin2,cnpin3,cnpin4;
    ImageView peye1;
    Button save;
    int la = 0;
    DBHelper dbHelper;
    SQLiteDatabase dbs;
    String results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_mpin);
        getSupportActionBar().hide();
        dbHelper = new DBHelper(this);
        dbs = dbHelper.getWritableDatabase();
        mpin1 = findViewById(R.id.mpin1);
        mpin2 = findViewById(R.id.mpin2);
        mpin3 = findViewById(R.id.mpin3);
        mpin4 = findViewById(R.id.mpin4);
        nmpin1 = findViewById(R.id.nmpin1);
        nmpin2 = findViewById(R.id.nmpin2);
        nmpin3 = findViewById(R.id.nmpin3);
        nmpin4 = findViewById(R.id.nmpin4);
        cnpin1 = findViewById(R.id.cnpin1);
        cnpin2 = findViewById(R.id.cnpin2);
        cnpin3 = findViewById(R.id.cnpin3);
        cnpin4 = findViewById(R.id.cnpin4);
        peye1 = findViewById(R.id.peye1);
        save = findViewById(R.id.bsave);

        mpin1.addTextChangedListener(this);
        mpin2.addTextChangedListener(this);
        mpin3.addTextChangedListener(this);
        mpin4.addTextChangedListener(this);
        nmpin1.addTextChangedListener(this);
        nmpin2.addTextChangedListener(this);
        nmpin3.addTextChangedListener(this);
        nmpin4.addTextChangedListener(this);
        cnpin1.addTextChangedListener(this);
        cnpin2.addTextChangedListener(this);
        cnpin3.addTextChangedListener(this);
        cnpin4.addTextChangedListener(this);

        peye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                la++;
                if(la % 2 == 0){
                    cnpin1.setTransformationMethod(new PasswordTransformationMethod());
                    cnpin2.setTransformationMethod(new PasswordTransformationMethod());
                    cnpin3.setTransformationMethod(new PasswordTransformationMethod());
                    cnpin4.setTransformationMethod(new PasswordTransformationMethod());
                    peye1.setBackgroundResource(R.drawable.eye);
                    cnpin1.requestFocus();
                    if(!cnpin1.getText().toString().equalsIgnoreCase("")){
                        cnpin2.requestFocus();
                    }
                    if(!cnpin2.getText().toString().equalsIgnoreCase("")){
                        cnpin3.requestFocus();
                    }
                    if(!cnpin3.getText().toString().equalsIgnoreCase("")){
                        cnpin4.requestFocus();
                    }
                }else{
                    cnpin1.setTransformationMethod(null);
                    cnpin2.setTransformationMethod(null);
                    cnpin3.setTransformationMethod(null);
                    cnpin4.setTransformationMethod(null);
                    cnpin1.requestFocus();
                    if(!cnpin1.getText().toString().equalsIgnoreCase("")){
                        cnpin2.requestFocus();
                    }
                    if(!cnpin2.getText().toString().equalsIgnoreCase("")){
                        cnpin3.requestFocus();
                    }
                    if(!cnpin3.getText().toString().equalsIgnoreCase("")){
                        cnpin4.requestFocus();
                    }
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String opin1 = mpin1.getText().toString();
                String opin2 = mpin2.getText().toString();
                String opin3 = mpin3.getText().toString();
                String opin4 = mpin4.getText().toString();
                String npin1 = nmpin1.getText().toString();
                String npin2 = nmpin2.getText().toString();
                String npin3 = nmpin3.getText().toString();
                String npin4 = nmpin4.getText().toString();
                String conpin1 = cnpin1.getText().toString();
                String conpin2 = cnpin2.getText().toString();
                String conpin3 = cnpin3.getText().toString();
                String conpin4 = cnpin4.getText().toString();
                String resopin = opin1+opin2+opin3+opin4;
                String resnpin = npin1+npin2+npin3+npin4;
                String resconpin = conpin1+conpin2+conpin3+conpin4;
                Log.e("res1234", resopin);
                Log.e("res1234", resnpin);
                Log.e("res1234", resconpin);
                if(opin1.isEmpty() || opin2.isEmpty() || opin3.isEmpty() || opin4.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter all the fields",Toast.LENGTH_SHORT).show();
                }else if(npin1.isEmpty() || npin2.isEmpty() || npin3.isEmpty() || npin4.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter all the fields",Toast.LENGTH_SHORT).show();
                }else if(conpin1.isEmpty() || conpin2.isEmpty() || conpin3.isEmpty() || conpin4.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter all the fields",Toast.LENGTH_SHORT).show();
                }else{
                    Cursor cr1 = dbs.rawQuery("select * from mpin where usermpin = '"+resopin+"'",null);
                    // Log.e("res12345", ""+selectQuerys);
                    if(cr1.getCount()>0){
                        if(cr1.moveToFirst()){
                            do{
                                results = cr1.getString(cr1.getColumnIndexOrThrow("usermpin"));
                            }while (cr1.moveToNext());
                        }
                    }
                    if(!resopin.equals(results)){
                        Toast.makeText(getApplicationContext(),"PIN Not Matched",Toast.LENGTH_SHORT).show();
                    }else if(resnpin.equals(resopin)){
                        Toast.makeText(getApplicationContext(),"Old MPIN and NEW MPIN Cannot Be Same",Toast.LENGTH_SHORT).show();
                    }else if(!resnpin.equals(resconpin)){
                        Toast.makeText(getApplicationContext(),"PIN Not Matched",Toast.LENGTH_SHORT).show();
                    }else{
                        dbs.execSQL("update mpin set usermpin='"+resnpin+"'");
                        Toast.makeText(getApplicationContext(),"PIN Updated",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),SingleLogin.class);
                        startActivity(intent);
                    }

                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
/*        cnpin1.requestFocus();
        if(!mpin1.getText().toString().equalsIgnoreCase("")){
            mpin2.requestFocus();
        }
        if(!mpin2.getText().toString().equalsIgnoreCase("")){
            mpin3.requestFocus();
        }
        if(!mpin3.getText().toString().equalsIgnoreCase("")){
            mpin4.requestFocus();
        }
        if(!mpin4.getText().toString().equalsIgnoreCase("")){
            nmpin1.requestFocus();
        }
        if(!nmpin1.getText().toString().equalsIgnoreCase("")){
            nmpin2.requestFocus();
        }
        if(!nmpin2.getText().toString().equalsIgnoreCase("")){
            nmpin3.requestFocus();
        }
        if(!nmpin3.getText().toString().equalsIgnoreCase("")){
            nmpin4.requestFocus();
        }
        if(!nmpin4.getText().toString().equalsIgnoreCase("")){
            cnpin1.requestFocus();
        }
        if(!cnpin1.getText().toString().equalsIgnoreCase("")){
            cnpin2.requestFocus();
        }
        if(!cnpin2.getText().toString().equalsIgnoreCase("")){
            cnpin3.requestFocus();
        }
        if(!cnpin3.getText().toString().equalsIgnoreCase("")){
            cnpin4.requestFocus();
        }*/
        if(editable == mpin1.getEditableText()){
            if(editable.length() == 1){
                mpin2.requestFocus();
            }
        }else if(editable == mpin2.getEditableText()){
            if(editable.length() == 0){
                mpin1.requestFocus();
            }else if(editable.length() == 1){
                mpin3.requestFocus();
            }
        }else if(editable == mpin3.getEditableText()){
            if(editable.length() == 0){
                mpin2.requestFocus();
            }else if(editable.length() == 1){
                mpin4.requestFocus();
            }
        }else if(editable == mpin4.getEditableText()){
            if(editable.length() == 0){
                mpin3.requestFocus();
            }
        }else if(editable == nmpin1.getEditableText()){
            if(editable.length() == 1){
                nmpin2.requestFocus();
            }
        }else if(editable == nmpin2.getEditableText()){
            if(editable.length() == 0){
                nmpin1.requestFocus();
            }else if(editable.length() == 1){
                nmpin3.requestFocus();
            }
        }else if(editable == nmpin3.getEditableText()){
            if(editable.length() == 0){
                nmpin2.requestFocus();
            }else if(editable.length() == 1){
                nmpin4.requestFocus();
            }
        }else if(editable == nmpin4.getEditableText()){
            if(editable.length() == 0){
                nmpin3.requestFocus();
            }
        }else if(editable == cnpin1.getEditableText()){
            if(editable.length() == 1){
                cnpin2.requestFocus();
            }
        }else if(editable == cnpin2.getEditableText()){
            if(editable.length() == 0){
                cnpin1.requestFocus();
            }else if(editable.length() == 1){
                cnpin3.requestFocus();
            }
        }else if(editable == cnpin3.getEditableText()){
            if(editable.length() == 0){
                cnpin2.requestFocus();
            }else if(editable.length() == 1){
                cnpin4.requestFocus();
            }
        }else if(editable == cnpin4.getEditableText()){
            if(editable.length() == 0){
                cnpin3.requestFocus();
            }
        }
    }
}