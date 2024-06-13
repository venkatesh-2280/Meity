package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;



public class QRView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_view);
        TextView t1 = (TextView)findViewById(R.id.t1);
        TextView t2 = (TextView)findViewById(R.id.t2);
        TextView t3 = (TextView)findViewById(R.id.t3);
        TextView t4 = (TextView)findViewById(R.id.t4);
        TextView t5 = (TextView)findViewById(R.id.t5);
        TextView t6 = (TextView)findViewById(R.id.t6);
        TextView t7 = (TextView)findViewById(R.id.t7);
        TextView t8 = (TextView)findViewById(R.id.t8);
        TextView t9 = (TextView)findViewById(R.id.t9);

        Intent i = getIntent();

        String v1 = i.getStringExtra("tx1").replaceAll(","," ");
        String v2 = i.getStringExtra("tx2").replaceAll(","," ");
        String v3 = i.getStringExtra("tx3");
        String v4 = i.getStringExtra("tx4");
        String v5 = i.getStringExtra("tx5").replaceAll(","," ");
        String v6 = i.getStringExtra("tx6").replaceAll(","," ");
        String v7 = i.getStringExtra("tx7").replaceAll(","," ");
        String v8 = i.getStringExtra("tx8").replaceAll(","," ");
        String v9 = i.getStringExtra("tx9").replaceAll(","," ");

        t1.setText(v1);
        t2.setText(v2);
        t3.setText(v3);
        t4.setText(v4);
        t5.setText(v5);
        t6.setText(v6);
        t7.setText(v7);
        t8.setText(v8);
        t9.setText(v9);
    }
}