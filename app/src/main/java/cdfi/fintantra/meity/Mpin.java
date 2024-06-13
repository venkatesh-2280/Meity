package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Mpin extends AppCompatActivity implements TextWatcher {
    EditText mpin1,mpin2,mpin3,mpin4,cmpin1,cmpin2,cmpin3,cmpin4;
    Button save,cancel;
    ImageView peye1;
    int la = 0;
    DBHelper dbHelper;
    SQLiteDatabase dbs;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    String usercode,username,rolecode,rolename,orgncode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpin);
        getSupportActionBar().hide();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        dbHelper = new DBHelper(this);
        dbs = dbHelper.getWritableDatabase();
        mpin1 = findViewById(R.id.mpin1);
        mpin2 = findViewById(R.id.mpin2);
        mpin3 = findViewById(R.id.mpin3);
        mpin4 = findViewById(R.id.mpin4);
        cmpin1 = findViewById(R.id.cmpin1);
        cmpin2 = findViewById(R.id.cmpin2);
        cmpin3 = findViewById(R.id.cmpin3);
        cmpin4 = findViewById(R.id.cmpin4);
        save = findViewById(R.id.bsave);
        cancel = findViewById(R.id.bcancel);
        peye1 = findViewById(R.id.peye1);

        mpin1.addTextChangedListener(this);
        mpin2.addTextChangedListener(this);
        mpin3.addTextChangedListener(this);
        mpin4.addTextChangedListener(this);
        cmpin1.addTextChangedListener(this);
        cmpin2.addTextChangedListener(this);
        cmpin3.addTextChangedListener(this);
        cmpin4.addTextChangedListener(this);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("mpinflag", "1");
        editor.commit();
        peye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                la++;
                if(la % 2 == 0){
                    cmpin1.setTransformationMethod(new PasswordTransformationMethod());
                    cmpin2.setTransformationMethod(new PasswordTransformationMethod());
                    cmpin3.setTransformationMethod(new PasswordTransformationMethod());
                    cmpin4.setTransformationMethod(new PasswordTransformationMethod());
                    peye1.setBackgroundResource(R.drawable.eye);
                    cmpin1.requestFocus();
                    if(!cmpin1.getText().toString().equalsIgnoreCase("")){
                        cmpin2.requestFocus();
                    }
                    if(!cmpin2.getText().toString().equalsIgnoreCase("")){
                        cmpin3.requestFocus();
                    }
                    if(!cmpin3.getText().toString().equalsIgnoreCase("")){
                        cmpin4.requestFocus();
                    }
                }else{
                    cmpin1.setTransformationMethod(null);
                    cmpin2.setTransformationMethod(null);
                    cmpin3.setTransformationMethod(null);
                    cmpin4.setTransformationMethod(null);
                    cmpin1.requestFocus();
                    if(!cmpin1.getText().toString().equalsIgnoreCase("")){
                        cmpin2.requestFocus();
                    }
                    if(!cmpin2.getText().toString().equalsIgnoreCase("")){
                        cmpin3.requestFocus();
                    }
                    if(!cmpin3.getText().toString().equalsIgnoreCase("")){
                        cmpin4.requestFocus();
                    }
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pin1 = mpin1.getText().toString();
                String pin2 = mpin2.getText().toString();
                String pin3 = mpin3.getText().toString();
                String pin4 = mpin4.getText().toString();
                String cpin1 = cmpin1.getText().toString();
                String cpin2 = cmpin2.getText().toString();
                String cpin3 = cmpin3.getText().toString();
                String cpin4 = cmpin4.getText().toString();
                if(pin1.isEmpty() || pin2.isEmpty() || pin3.isEmpty() || pin4.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter all the fields",Toast.LENGTH_SHORT).show();
                }else if(cpin1.isEmpty() || cpin2.isEmpty() || cpin3.isEmpty() || cpin4.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter all the fields",Toast.LENGTH_SHORT).show();
                }else{
                    String mpins = pin1+pin2+pin3+pin4;
                    String cmpins = cpin1+cpin2+cpin3+cpin4;
                    if(!mpins.equals(cmpins)){
                        Toast.makeText(getApplicationContext(),"PIN not matching",Toast.LENGTH_SHORT).show();
                    }else{
                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        usercode = sharedpreferences.getString("uc", "");
                        username = sharedpreferences.getString("un", "");
                        rolecode = sharedpreferences.getString("rc", "");
                        rolename = sharedpreferences.getString("rn", "");
                        orgncode = sharedpreferences.getString("oc", "");
                        boolean ins = dbHelper.insertmpin(usercode, username, rolecode, rolename, orgncode,mpins);
                        if(ins){
                            Intent intent = new Intent(getApplicationContext(),Landpage.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Landpage.class);
                startActivity(intent);
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
    /*    mpin1.requestFocus();
        if(!mpin1.getText().toString().equalsIgnoreCase("")){
            mpin2.requestFocus();
        }
        if(!mpin2.getText().toString().equalsIgnoreCase("")){
            mpin3.requestFocus();
        }
        if(!mpin3.getText().toString().equalsIgnoreCase("")){
            mpin4.requestFocus();
        }
        if(!cmpin1.getText().toString().equalsIgnoreCase("")){
            cmpin2.requestFocus();
        }
        if(!cmpin2.getText().toString().equalsIgnoreCase("")){
            cmpin3.requestFocus();
        }
        if(!cmpin3.getText().toString().equalsIgnoreCase("")){
            cmpin4.requestFocus();
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
        }else if(editable == cmpin1.getEditableText()){
            if(editable.length() == 1){
                cmpin2.requestFocus();
            }
        }else if(editable == cmpin2.getEditableText()){
            if(editable.length() == 0){
                cmpin1.requestFocus();
            }else if(editable.length() == 1){
                cmpin3.requestFocus();
            }
        }else if(editable == cmpin3.getEditableText()){
            if(editable.length() == 0){
                cmpin2.requestFocus();
            }else if(editable.length() == 1){
                cmpin4.requestFocus();
            }
        }else if(editable == cmpin4.getEditableText()){
            if(editable.length() == 0){
                cmpin3.requestFocus();
            }
        }
    }
}