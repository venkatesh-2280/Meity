package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

public class VSlist extends AppCompatActivity {
    MyRecyclerViewAdapter adapter;
    TextView title,name;
    String f;
    double zto = 0;
    DBHelper db;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    List<pojoSyncSI> productlist;
    TextView trans,others,amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_slist);
        getSupportActionBar().hide();
        db =new  DBHelper(this);
        productlist = new ArrayList<>();
        title = (TextView)findViewById(R.id.title);
        name = (TextView)findViewById(R.id.name);
        trans = (TextView)findViewById(R.id.trns);
        others = (TextView)findViewById(R.id.others);
        amount = (TextView)findViewById(R.id.amount);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

            final SQLiteDatabase dbs = db.getWritableDatabase();
        title.setText(sharedpreferences.getString("rn",""));
        name.setText("Welcome "+sharedpreferences.getString("un",""));
            androidx.recyclerview.widget.RecyclerView recyclerView = findViewById(R.id.itm);
            recyclerView.setLayoutManager(new LinearLayoutManager(VSlist.this));
            adapter = new MyRecyclerViewAdapter(VSlist.this, productlist);

           Intent i = getIntent();
           if(i.getStringExtra("F").equalsIgnoreCase("1"))
           {
               f="1";
               String selectQueryt = "SELECT  * FROM inwardlist where flag = 0";

               Cursor cursort = dbs.rawQuery(selectQueryt, null);

               // looping through all rows and adding to list
               if (cursort.moveToFirst()) {
                   do {

                       pojoSyncSI pojoSyncSI = new pojoSyncSI();




                       pojoSyncSI.setId(cursort.getString(0));
                       pojoSyncSI.setInn(cursort.getString(1));
                       pojoSyncSI.setDt(cursort.getString(2));
                       pojoSyncSI.setSn(cursort.getString(3));
                       pojoSyncSI.setSl(cursort.getString(4));
                       pojoSyncSI.setTa(cursort.getString(5));
                       pojoSyncSI.setTr(cursort.getString(6));
                       pojoSyncSI.setOt(cursort.getString(7));
                       pojoSyncSI.setAmt(cursort.getString(8));


                       productlist.add(pojoSyncSI);
                       // array2.add(cursor.getString(11));
                       // Log.e("VAL",""+cursor.getString(11));
                       recyclerView.setAdapter(adapter);




//                Log.e("Check",""+cursor.getString(1));


                       // array2.add(cursor.getString(11));
                       // Log.e("VAL",""+cursor.getString(11));

                   } while (cursort.moveToNext());

               }

           }
           else
           {
               f="2";
               String selectQueryt = "SELECT  * FROM invoicelist where flag = 0";

               Cursor cursort = dbs.rawQuery(selectQueryt, null);

               // looping through all rows and adding to list
               if (cursort.moveToFirst()) {
                   do {

                       pojoSyncSI pojoSyncSI = new pojoSyncSI();




                       pojoSyncSI.setId(cursort.getString(0));
                       pojoSyncSI.setInn(cursort.getString(1));
                       pojoSyncSI.setDt(cursort.getString(2));
                       pojoSyncSI.setSn(cursort.getString(3));
                       pojoSyncSI.setSl(cursort.getString(4));
                       pojoSyncSI.setTa(cursort.getString(5));
                       pojoSyncSI.setTr(cursort.getString(6));
                       pojoSyncSI.setOt(cursort.getString(7));
                       pojoSyncSI.setAmt(cursort.getString(8));


                       productlist.add(pojoSyncSI);
                       // array2.add(cursor.getString(11));
                       // Log.e("VAL",""+cursor.getString(11));
                       recyclerView.setAdapter(adapter);




//                Log.e("Check",""+cursor.getString(1));


                       // array2.add(cursor.getString(11));
                       // Log.e("VAL",""+cursor.getString(11));

                   } while (cursort.moveToNext());

               }
           }

        }

    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

        private List<pojoSyncSI> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<pojoSyncSI> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.vslist, parent, false);
            return new MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
            pojoSyncSI pojoSyncSI = mData.get(position);
            holder.sn.setText(pojoSyncSI.getSn());
            holder.sl.setText(pojoSyncSI.getSl());
            holder.tr.setText(pojoSyncSI.getTr());
            holder.ta.setText(pojoSyncSI.getTa());
            holder.ot.setText(pojoSyncSI.getOt());
            holder.dt.setText(pojoSyncSI.getDt());
            holder.inn.setText(pojoSyncSI.getInn());
            holder.amt.setText(pojoSyncSI.getAmt());
            if(f.equalsIgnoreCase("2"))
            {
                holder.t1.setText("Invoice No:");
                holder.t2.setText("Invoice Amount:");
            }


        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder  {
            TextView sn,sl,tr,ta,ot,dt,amt,inn,t1,t2;

            ViewHolder(View itemView) {
                super(itemView);
                sn = itemView.findViewById(R.id.sname);
                sl = itemView.findViewById(R.id.lcn);
                tr = itemView.findViewById(R.id.trs);
                ta = itemView.findViewById(R.id.ttax);
                ot = itemView.findViewById(R.id.oth);
                dt = itemView.findViewById(R.id.dt);
                amt = itemView.findViewById(R.id.inam);
                inn = itemView.findViewById(R.id.inno);
                t1 = itemView.findViewById(R.id.t1);
                t2 = itemView.findViewById(R.id.t2);

            }


        }

        // convenience method for getting data at click position


        // allows clicks events to be caught



    }



    }



