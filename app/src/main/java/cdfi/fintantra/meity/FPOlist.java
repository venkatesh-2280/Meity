package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FPOlist extends AppCompatActivity {
    private DBHelper db;
    MyRecyclerViewAdapter adapter;
    List<Pojofpo> pojofpoList;
    private SearchView searchViewFarmerName;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_p_olist);
        db = new DBHelper(this);
        getSupportActionBar().hide();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SQLiteDatabase dbs = db.getWritableDatabase();
        String selectQuery = "SELECT  * FROM orgn";
        // looping through all rows and adding to list
        Cursor cursor = dbs.rawQuery(selectQuery, null);
        Log.e("NULL",""+cursor.getCount());
        List<String> org2 = new ArrayList<String>();
        org2.add("Tap to Select FPO");
        final List<String> org1 = new ArrayList<String>();
        org1.add("0");
        searchViewFarmerName=(SearchView) findViewById(R.id.searchFarmerName);
        pojofpoList = new ArrayList<>();
        androidx.recyclerview.widget.RecyclerView recyclerView = findViewById(R.id.fpolist);
        recyclerView.setLayoutManager(new LinearLayoutManager(FPOlist.this));
        adapter = new MyRecyclerViewAdapter(FPOlist.this, pojofpoList);
        recyclerView.setAdapter(adapter);
        pojofpoList.clear();
        if (cursor.moveToFirst()) {
            do {
               // org1.add(cursor.getString(2));
               // org2.add(cursor.getString(3));
                Pojofpo pojofpo = new Pojofpo();

                pojofpo.setName(cursor.getString(3));
                pojofpo.setCode(cursor.getString(2));



                pojofpoList.add(pojofpo);
                // array2.add(cursor.getString(11));
                // Log.e("VAL",""+cursor.getString(11));
                adapter = new MyRecyclerViewAdapter(FPOlist.this, pojofpoList);
                recyclerView.setAdapter(adapter);

                // Log.e("Check",""+cursor.getString(1));


                // array2.add(cursor.getString(11));
                // Log.e("VAL",""+cursor.getString(11));

            } while (cursor.moveToNext());

        }
        searchViewFarmerName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(adapter!=null){
                    adapter.filter(newText);
                }

                return false;
            }
        });
    }
    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

        private List<Pojofpo> mData;
        private LayoutInflater mInflater;
        private List<Pojofpo> arraylist;

        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<Pojofpo> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
            this.arraylist = new ArrayList<Pojofpo>();
            this.arraylist.addAll(data);
        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.suplistfpo, parent, false);
            return new MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(final MyRecyclerViewAdapter.ViewHolder holder, final int position) {
            final Pojofpo pojofpo = mData.get(position);
            holder.name.setText(pojofpo.getName());


            //holder.llist.setBackgroundResource(R.drawable.rbutton);
            holder.llist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    Log.e("FPO",""+pojofpo.getCode());

                    editor.putString("orgn",pojofpo.getCode());
                    editor.putString("orgnname",pojofpo.getName());

                    editor.commit();

                    final ProgressDialog pd = new ProgressDialog(FPOlist.this);
                    pd.setMessage("Loading");
                    pd.show();
                    Intent i = new Intent(getApplicationContext(), MainActivity3.class);


                    editor.putString("frm", "1");
                    editor.putString("fcode", "");
                    editor.putString("fid", "");

                    editor.commit();
                    startActivity(i);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            pd.dismiss();

                        }

                    }, 2000);


                }
            });
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }

        public  void filter(String charText) {
            Log.e("Hello","hi");
            charText = charText.toLowerCase(Locale.getDefault());
            mData.clear();
            if (charText.length() == 0) {
                mData.addAll(arraylist);
            } else {
                for (Pojofpo wp : arraylist) {
                    if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                        mData.add(wp);
                    }
                }
            }
            notifyDataSetChanged();
        }
        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder  {
            TextView myTextView,trate,tamt,tnamt,tdis,tqty,name,ph,lcn,ty,ham;
            ImageView del,ed;
            RelativeLayout llist;

            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name);


                llist = itemView.findViewById(R.id.llist);






            }


        }

        // convenience method for getting data at click position




        // parent activity will implement this method to respond to click events

    }
}