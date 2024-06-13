package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

public class VSA extends AppCompatActivity {
    VSA.MyRecyclerViewAdapter adapter;
    TextView title;
    String f;
    double zto = 0;
    DBHelper db;
    List<pojoVSA> productlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_s);
        db =new  DBHelper(this);
        getSupportActionBar().hide();
        productlist = new ArrayList<>();
  




        final SQLiteDatabase dbs = db.getWritableDatabase();

        androidx.recyclerview.widget.RecyclerView recyclerView = findViewById(R.id.itm);
        recyclerView.setLayoutManager(new LinearLayoutManager(VSA.this));
        adapter = new VSA.MyRecyclerViewAdapter(VSA.this, productlist);

        String selectQueryt = "SELECT  * FROM astock2";

        Cursor cursort = dbs.rawQuery(selectQueryt, null);

        // looping through all rows and adding to list
        if (cursort.moveToFirst()) {
            do {

                pojoVSA pojoVSA = new pojoVSA();




                pojoVSA.setId(cursort.getString(0));
                pojoVSA.setAn(cursort.getString(2));
                pojoVSA.setAd(cursort.getString(3));



                productlist.add(pojoVSA);
                // array2.add(cursor.getString(11));
                // Log.e("VAL",""+cursor.getString(11));
                recyclerView.setAdapter(adapter);




//                Log.e("Check",""+cursor.getString(1));


                // array2.add(cursor.getString(11));
                // Log.e("VAL",""+cursor.getString(11));

            } while (cursort.moveToNext());

        }
    }
    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<VSA.MyRecyclerViewAdapter.ViewHolder> {

        private List<pojoVSA> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<pojoVSA> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public VSA.MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.vsa, parent, false);
            return new VSA.MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(VSA.MyRecyclerViewAdapter.ViewHolder holder, int position) {
            pojoVSA pojoVSA = mData.get(position);
            holder.an.setText(pojoVSA.getAn());
            holder.ad.setText(pojoVSA.getAd());




        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder  {
            TextView sn,sl,tr,ta,ot,dt,amt,inn,an,ad;

            ViewHolder(View itemView) {
                super(itemView);
                an = itemView.findViewById(R.id.t1);
                ad = itemView.findViewById(R.id.t2);


            }


        }

        // convenience method for getting data at click position


        // allows clicks events to be caught



    }
}
