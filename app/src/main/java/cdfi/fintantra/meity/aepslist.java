package cdfi.fintantra.meity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.service.aepsnew.Host;

import java.util.Calendar;

public class aepslist extends AppCompatActivity {
    Button settlement, history;
    String ts;
    EditText amount;
    ImageView img1, img2;
    String aeps_merchantId = "";
    DBHelper db;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aepslist);
        getSupportActionBar().hide();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        settlement = findViewById(R.id.settlement);
        history = findViewById(R.id.history);
        amount = findViewById(R.id.amount);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);

        db = new DBHelper(this);
        final SQLiteDatabase dbs = db.getWritableDatabase();
        Cursor cmid_aeps = dbs.rawQuery("select * from aepsmercid where out_aeps_fpocode = '" + sharedpreferences.getString("oc1", "") + "'", null);

        if (cmid_aeps.getCount() > 0) {
            if (cmid_aeps.moveToFirst()) {
                aeps_merchantId = cmid_aeps.getString(4);
            }
        }

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(aepslist.this, Host.class);
                    //intent.putExtra("username","R005155");

                    intent.putExtra("username", aeps_merchantId);
                    Log.e("aeps_merchantId", aeps_merchantId);
                    intent.putExtra("amount", amount.getText().toString());
                    intent.putExtra("txntype", "2");
                    intent.putExtra("clienttxnid", "MEITY" + ts);
                    startActivityForResult(intent, 1000);

            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(aepslist.this, Host.class);
                //intent.putExtra("username","R005155");

                intent.putExtra("username", aeps_merchantId);
                intent.putExtra("amount", "");
                intent.putExtra("txntype", "3");
                intent.putExtra("clienttxnid", "MEITY" + ts);
                startActivityForResult(intent, 1000);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            boolean status = data.getBooleanExtra("status", false);
            int response = data.getIntExtra("response", 0);
            String message = data.getStringExtra("message");
            String json = data.getStringExtra("data");
            Log.e("json1", String.valueOf(status));
            Log.e("json1", String.valueOf(response));
            Log.e("json1", String.valueOf(message));
            Log.e("json1", String.valueOf(json));
            Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();

            final AlertDialog alertDialog = new AlertDialog.Builder(aepslist.this)
//set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                    .setTitle("Info!")
//set message
                    .setMessage(message)
//set positive button
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    //set what would happen when positive button is clicked
                                 //   finish();

                                }
                            }
//set negative button

                    )
                    .setCancelable(true)
                    .show();

        }catch (Exception e){

        }
    }

}