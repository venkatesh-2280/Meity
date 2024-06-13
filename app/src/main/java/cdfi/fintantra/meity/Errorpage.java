package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Errorpage extends AppCompatActivity {

    Button btnreport;
    String errormsg = "";
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_errorpage);
        context = this;
        btnreport = (Button) findViewById(R.id.btnreport);
        Intent i = getIntent();
        errormsg = i.getStringExtra("error");
        Log.e("ARRR",""+i.getStringExtra("error"));
        // Write a message to the database
        FirebaseApp.initializeApp(Errorpage.this);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://kanchiwrapperup-default-rtdb.firebaseio.com/");

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        DatabaseReference mRef =  database.getReference().child(date).child(ts);
        Log.e("Valuecheck",""+mRef.getRef());
        mRef.child("Error").setValue(i.getStringExtra("error"));

        btnreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                intentShare.putExtra(Intent.EXTRA_SUBJECT, "My Subject Here ... ");
                intentShare.putExtra(Intent.EXTRA_TEXT, errormsg);
                context.startActivity(Intent.createChooser(intentShare, "Shared the text ..."));
            }
        });
    }
}