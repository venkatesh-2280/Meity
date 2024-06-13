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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DuplicateFarmerList extends AppCompatActivity {

    List<Pojofar> pojofarlist;
    MyRecyclerViewAdapterf adapterf;
    SQLiteDatabase dbs;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duplicate_farmer_list);
        getSupportActionBar().hide();
        db = new DBHelper(DuplicateFarmerList.this);
        dbs = db.getWritableDatabase();
        pojofarlist = new ArrayList<>();
        final androidx.recyclerview.widget.RecyclerView recyclerView = findViewById(R.id.itm);
        recyclerView.setLayoutManager(new LinearLayoutManager(DuplicateFarmerList.this));
        adapterf = new MyRecyclerViewAdapterf(DuplicateFarmerList.this, pojofarlist);

        pojofarlist.clear();
        // elc.setText(""+(String) parent.getItemAtPosition(position));

        Cursor cursor = dbs.query("farlist", new String[]{"fid", "fc", "fn", "vi", "gp", "ta", "di", "id","sn"
                }, "v5" + "=? COLLATE NOCASE",
                new String[]{"0"}, null, null, null, null);
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {

                    Pojofar pojofar = new Pojofar();

                    pojofar.setId(cursor.getString(0));
                    pojofar.setFc(cursor.getString(1));
                    pojofar.setFn(cursor.getString(2));
                    pojofar.setVi(cursor.getString(3));
                    pojofar.setGp(cursor.getString(4));
                    pojofar.setTa(cursor.getString(5));
                    pojofar.setDi(cursor.getString(6));
                    pojofar.setAid(cursor.getString(7));
                    pojofar.setFat(cursor.getString(8));


                    pojofarlist.add(pojofar);
                    // array2.add(cursor.getString(11));
                    // Log.e("VAL",""+cursor.getString(11));
                    recyclerView.setAdapter(adapterf);
                    // Log.e("VAL",""+cursor.getString(11));

                } while (cursor.moveToNext());
            }


        }
    }
    public class MyRecyclerViewAdapterf extends RecyclerView.Adapter<MyRecyclerViewAdapterf.ViewHolder> {

        private List<Pojofar> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapterf(Context context, List<Pojofar> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapterf.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.suplist4, parent, false);
            return new MyRecyclerViewAdapterf.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(final MyRecyclerViewAdapterf.ViewHolder holder, final int position) {
            final Pojofar pojofar = mData.get(position);
            holder.name.setText(pojofar.getFn());
            holder.ph.setText(pojofar.getVi());
            holder.lcn.setText(pojofar.getGp());
            holder.ty.setText(pojofar.getTa());
            holder.t5.setText(pojofar.getDi());
            holder.fc.setText(pojofar.getFc());
            holder.fat.setText(pojofar.getFat());
            // holder.llist.setBackgroundResource(R.drawable.rbutton);
            holder.llist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.llist.setBackgroundResource(R.drawable.rbutton2);

//                    bcode = pojobank.getBc();
//                    ebank.setText(pojobank.getBn());
//                    ebranch.setText(pojobank.getBrn());
//                    eifsc.setText(pojobank.getIfsc());
//                    dialog.dismiss();

                    //loadFarmer(pojofar.getFc(),pojofar.getId(),pojofar.getFn());



                }
            });
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder  {
            TextView myTextView,trate,tamt,tnamt,tdis,tqty,name,ph,lcn,ty,t5,fc,fat;
            ImageView del,ed;
            LinearLayout llist;

            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.fn);
                ph = itemView.findViewById(R.id.vi);
                lcn = itemView.findViewById(R.id.gp);
                ty = itemView.findViewById(R.id.ta);
                t5 = itemView.findViewById(R.id.di);
                fc = itemView.findViewById(R.id.fc);
                fat = itemView.findViewById(R.id.fat);
                llist = itemView.findViewById(R.id.llist);





            }


        }

        // convenience method for getting data at click position




        // parent activity will implement this method to respond to click events

    }
}