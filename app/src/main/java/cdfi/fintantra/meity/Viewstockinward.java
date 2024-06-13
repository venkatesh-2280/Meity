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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Viewstockinward extends AppCompatActivity {
    MyRecyclerViewAdapter adapter;
    TextView title,title2,name;
    double zto = 0,zto2=0;
    TextView fponame;
    String namt;
    int inwardflag = 0;
    LinearLayout ltr;
    TextView txtotherdiscount;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    DBHelper db;
    RelativeLayout rr,rr2;
    List<pojoProductfinal> productlist;
    TextView trans,others,amount,amount1,otherssi,amountsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstockinward);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        getSupportActionBar().hide();
        db =new  DBHelper(this);
        productlist = new ArrayList<>();
        title = (TextView)findViewById(R.id.title);
        title2 = (TextView)findViewById(R.id.title2);
        name = (TextView)findViewById(R.id.name);
        trans = (TextView)findViewById(R.id.trns);
        others = (TextView)findViewById(R.id.others);
        amount = (TextView)findViewById(R.id.amount);
        amount1 = (TextView)findViewById(R.id.amount1);
        otherssi = (TextView)findViewById(R.id.otherssi);
        amountsi = (TextView)findViewById(R.id.amountsi);
        txtotherdiscount = (TextView)findViewById(R.id.txtotherdiscount);

        rr = (RelativeLayout)findViewById(R.id.rr);
        rr2 = (RelativeLayout)findViewById(R.id.rr2);
        ltr = (LinearLayout) findViewById(R.id.ltr);
        Intent i = getIntent();
        name.setText("Welcome "+sharedpreferences.getString("un",""));

        if(i.getStringExtra("FRM").toString().equalsIgnoreCase("0"))
        {

            DecimalFormat amountFormate  = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);

            String t = i.getStringExtra("T");
            String o = i.getStringExtra("O");
            String ss = i.getStringExtra("ss");
            namt = i.getStringExtra("ta");

            final SQLiteDatabase dbs = db.getWritableDatabase();
            title.setText(sharedpreferences.getString("rn",""));
            title2.setText("Inward Stock");
            inwardflag = 1;
           // rr.setVisibility(View.GONE);
           // rr2.setVisibility(View.VISIBLE);
            //ltr.setVisibility(View.GONE);
            txtotherdiscount.setText("Other Discount :");
            androidx.recyclerview.widget.RecyclerView recyclerView = findViewById(R.id.itm);
            recyclerView.setLayoutManager(new LinearLayoutManager(Viewstockinward.this));
            adapter = new MyRecyclerViewAdapter(Viewstockinward.this, productlist);

            String selectQueryt = "SELECT  * FROM productlist where flag = 0";

            Cursor cursort = dbs.rawQuery(selectQueryt, null);

            // looping through all rows and adding to list
            if (cursort.moveToFirst()) {
                do {

                    pojoProductfinal pojoProductfinal = new pojoProductfinal();

                    if(t.equalsIgnoreCase(""))
                    {
                        trans.setText("0");
                    }
                    else
                    {
                        trans.setText("-"+t);

                    }
                    if(o.equalsIgnoreCase(""))
                    {
                        otherssi.setText("0");
                        others.setText("0");
                    }

                    else
                    {
                        otherssi.setText(o);
                        others.setText(o);
                    }

                    String id = cursort.getString(0);
                    String r = cursort.getString(6);
                    pojoProductfinal.setGa(cursort.getString(6));
                    pojoProductfinal.setQty(cursort.getString(2));
                    pojoProductfinal.setRate(cursort.getString(3));
                    pojoProductfinal.setItem(cursort.getString(1));
                    pojoProductfinal.setDis(cursort.getString(5));
                    pojoProductfinal.setAmt(cursort.getString(4));
                    if(ss.equalsIgnoreCase(""))
                    {
                        pojoProductfinal.setTax("");
                    }
                    else if(ss.equalsIgnoreCase("0"))
                    {
                        Cursor cursort2 = dbs.query("product", new String[]{"In_cgst",
                                        "In_sgst","In_igst","In_hsn_desc"}, "In_product_name" + "=?",
                                new String[]{cursort.getString(1)}, null, null, null, null);

                        if (cursort2.moveToFirst()) {
                            do {

                                //


                                double c = Float.parseFloat(cursort2.getString(0));
                                double s =Float.parseFloat(cursort2.getString(1));
                                double rl = Double.parseDouble(r);

                                double cg = (c/100) * rl;
                                double sg = (s/100) * rl;
                                double f = cg+sg;
                                //Toast.makeText(this, ""+c+s), Toast.LENGTH_SHORT).show();
                                pojoProductfinal.setTax(""+(""+String.format("%.2f", f)));



                            }
                            while (cursort2.moveToNext());
                        }

                        productlist.add(pojoProductfinal);
                        recyclerView.setAdapter(adapter);



                    }
                    else if(ss.equalsIgnoreCase("1"))
                    {
                        Cursor cursort2 = dbs.query("product", new String[]{"In_cgst",
                                        "In_sgst","In_igst","In_hsn_desc"}, "In_product_name" + "=?",
                                new String[]{cursort.getString(1)}, null, null, null, null);

                        if (cursort2.moveToFirst()) {
                            do {

                                if(cursort2.getString(2).equalsIgnoreCase("0.0000"))
                                {
                                    double c = Float.parseFloat(cursort2.getString(0));
                                    double s = Float.parseFloat(cursort2.getString(1));
                                    double rl = Double.parseDouble(r);

                                    double cg = (c/100) * rl;
                                    double sg = (s/100) * rl;
                                    double f = cg+sg;
                                    pojoProductfinal.setTax(""+amountFormate.format(f));
                                }
                                else
                                {
                                    double c = Float.parseFloat(cursort2.getString(0));
                                    double s = Float.parseFloat(cursort2.getString(1));
                                    double ig = Float.parseFloat(cursort2.getString(2));
                                    double rl = Double.parseDouble(r);

                                    double cg = (c/100) * rl;
                                    double sg = (s/100) * rl;
                                    double ig2 = (ig/100) * rl;
                                    double f = ig2;
                                    pojoProductfinal.setTax(""+(""+String.format("%.2f", f)));
                                }







                            }
                            while (cursort2.moveToNext());
                        }




                        if(ss.equalsIgnoreCase(""))
                        {

                        }
                        else
                        {
                            productlist.add(pojoProductfinal);
                            recyclerView.setAdapter(adapter);
                        }
                        // Toast.makeText(this, ""+cursort.getCount(), Toast.LENGTH_SHORT).show();



                    }

//                Log.e("Check",""+cursor.getString(1));


                    // array2.add(cursor.getString(11));
                    // Log.e("VAL",""+cursor.getString(11));

                } while (cursort.moveToNext());

            }


        }
        else
        {
            rr.setVisibility(View.VISIBLE);

            if(i.getStringExtra("BT").equalsIgnoreCase("0"))
            {
                title.setText(sharedpreferences.getString("rn",""));
                title2.setText("Book Invoice");

                DecimalFormat amountFormate  = new DecimalFormat("############.##");
                amountFormate.setMinimumFractionDigits(2);
                amountFormate.setMaximumFractionDigits(2);

                String t = i.getStringExtra("T");
                String o = i.getStringExtra("O");
                String ss = i.getStringExtra("ss");
                namt = i.getStringExtra("ta");
                final SQLiteDatabase dbs = db.getWritableDatabase();

                androidx.recyclerview.widget.RecyclerView recyclerView = findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(Viewstockinward.this));
                adapter = new MyRecyclerViewAdapter(Viewstockinward.this, productlist);

                String selectQueryt = "SELECT  * FROM productlist2 where flag = 0";

                Cursor cursort = dbs.rawQuery(selectQueryt, null);

                // looping through all rows and adding to list
                if (cursort.moveToFirst()) {
                    do {

                        pojoProductfinal pojoProductfinal = new pojoProductfinal();

                        if(t.equalsIgnoreCase(""))
                        {
                            trans.setText("0");
                        }
                        else
                        {
                            trans.setText(t);

                        }
                        if(o.equalsIgnoreCase(""))
                        {
                            others.setText("0");
                        }

                        else
                        {
                            others.setText(o);
                        }

                        String id = cursort.getString(0);
                        String r = cursort.getString(6);

                        pojoProductfinal.setGa(cursort.getString(6));
                        pojoProductfinal.setQty(cursort.getString(2));
                        pojoProductfinal.setRate(cursort.getString(3));
                        pojoProductfinal.setItem(cursort.getString(1));
                        pojoProductfinal.setAmt(cursort.getString(4));

                        pojoProductfinal.setDis(cursort.getString(5));





                        if(ss.equalsIgnoreCase(""))
                        {
                            pojoProductfinal.setTax("");
                        }
                        else if(ss.equalsIgnoreCase("0"))
                        {
                            Cursor cursort2 = dbs.query("product", new String[]{"In_cgst",
                                            "In_sgst","In_igst","In_hsn_desc"}, "In_product_name" + "=?",
                                    new String[]{cursort.getString(1)}, null, null, null, null);

                            if (cursort2.moveToFirst()) {
                                do {



                                    double c = Float.parseFloat(cursort2.getString(0));
                                    double s = Float.parseFloat(cursort2.getString(1));
                                    double rl = Double.parseDouble(r);

                                    double cg = (c/100)*rl;
                                    double sg = (s/100)*rl;
                                    double f = cg+sg;
                                    pojoProductfinal.setTax(""+(""+String.format("%.2f", f)));



                                }
                                while (cursort2.moveToNext());
                            }

                            productlist.add(pojoProductfinal);
                            recyclerView.setAdapter(adapter);



                        }
                        else if(ss.equalsIgnoreCase("1"))
                        {
                            Cursor cursort2 = dbs.query("product", new String[]{"In_cgst",
                                            "In_sgst","In_igst","In_hsn_desc"}, "In_product_name" + "=?",
                                    new String[]{cursort.getString(1)}, null, null, null, null);

                            if (cursort2.moveToFirst()) {
                                do {

                                    if(cursort2.getString(2).equalsIgnoreCase("0.0000"))
                                    {
                                        double c = Float.parseFloat(cursort2.getString(0));
                                        double s = Float.parseFloat(cursort2.getString(1));
                                        double rl = Double.parseDouble(r);

                                        double cg = (c/100)*rl;
                                        double sg = (s/100)*rl;
                                        double f = cg+sg;
                                        pojoProductfinal.setTax(""+(""+String.format("%.2f", f)));
                                    }
                                    else
                                    {
                                        double c = Float.parseFloat(cursort2.getString(0));
                                        double s = Float.parseFloat(cursort2.getString(1));
                                        double ig = Float.parseFloat(cursort2.getString(2));
                                        double rl = Double.parseDouble(r);

                                        double cg = (c/100)*rl;
                                        double sg = (s/100)*rl;
                                        double ig2 = (ig/100)*rl;
                                        double f = ig2;
                                        pojoProductfinal.setTax(""+(""+String.format("%.2f", f)));
                                    }







                                }
                                while (cursort2.moveToNext());
                            }

                            if(ss.equalsIgnoreCase(""))
                            {

                            }
                            else
                            {
                                productlist.add(pojoProductfinal);
                                recyclerView.setAdapter(adapter);
                            }



                        }

//                Log.e("Check",""+cursor.getString(1));


                        // array2.add(cursor.getString(11));
                        // Log.e("VAL",""+cursor.getString(11));

                    } while (cursort.moveToNext());

                }

            }
            else if(i.getStringExtra("BT").equalsIgnoreCase("1"))
            {
                title.setText(sharedpreferences.getString("rn", ""));
                title2.setText("Book Invoice");

                DecimalFormat amountFormate = new DecimalFormat("############.##");
                amountFormate.setMinimumFractionDigits(2);
                amountFormate.setMaximumFractionDigits(2);

                String t = i.getStringExtra("T");
                String o = i.getStringExtra("O");
                String ss = i.getStringExtra("ss");
                namt = i.getStringExtra("ta");
                final SQLiteDatabase dbs = db.getWritableDatabase();

                androidx.recyclerview.widget.RecyclerView recyclerView = findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(Viewstockinward.this));
                adapter = new MyRecyclerViewAdapter(Viewstockinward.this, productlist);

                String nid = sharedpreferences.getString("ids2","");
                String nid2 = nid.substring(1, nid.length() - 1);
                String[] id = nid2.split(",");
                productlist.clear();


                for(int ik = 0 ; ik< id.length;ik++) {

                    String selectQueryt = "SELECT  * FROM productlist2 where id =" + id[ik];
                    Cursor cursort = dbs.rawQuery(selectQueryt, null);

                    // looping through all rows and adding to list
                    if (cursort.moveToFirst()) {
                        do {

                            pojoProductfinal pojoProductfinal = new pojoProductfinal();

                            if (t.equalsIgnoreCase("")) {
                                trans.setText("0");
                            } else {
                                trans.setText(t);

                            }
                            if (o.equalsIgnoreCase("")) {
                                others.setText("0");
                            } else {
                                others.setText(o);
                            }

                            //String id = cursort.getString(0);
                            String r = cursort.getString(6);

                            pojoProductfinal.setGa(cursort.getString(6));
                            pojoProductfinal.setQty(cursort.getString(2));
                            pojoProductfinal.setRate(cursort.getString(3));
                            pojoProductfinal.setItem(cursort.getString(1));
                            pojoProductfinal.setAmt(cursort.getString(4));

                            pojoProductfinal.setDis(cursort.getString(5));


                            if (ss.equalsIgnoreCase("")) {
                                pojoProductfinal.setTax("");
                            } else if (ss.equalsIgnoreCase("0")) {
                                Cursor cursort2 = dbs.query("product", new String[]{"In_cgst",
                                                "In_sgst", "In_igst", "In_hsn_desc"}, "In_product_name" + "=?",
                                        new String[]{cursort.getString(1)}, null, null, null, null);

                                if (cursort2.moveToFirst()) {
                                    do {


                                        double c = Float.parseFloat(cursort2.getString(0));
                                        double s = Float.parseFloat(cursort2.getString(1));
                                        double rl = Double.parseDouble(r);

                                        double cg = (c / 100) * rl;
                                        double sg = (s / 100) * rl;
                                        double f = cg + sg;
                                        pojoProductfinal.setTax("" + (""+String.format("%.2f", f)));


                                    }
                                    while (cursort2.moveToNext());
                                }

                                productlist.add(pojoProductfinal);
                                recyclerView.setAdapter(adapter);


                            } else if (ss.equalsIgnoreCase("1")) {
                                Cursor cursort2 = dbs.query("product", new String[]{"In_cgst",
                                                "In_sgst", "In_igst", "In_hsn_desc"}, "In_product_name" + "=?",
                                        new String[]{cursort.getString(1)}, null, null, null, null);

                                if (cursort2.moveToFirst()) {
                                    do {

                                        if (cursort2.getString(2).equalsIgnoreCase("0.0000")) {
                                            double c = Float.parseFloat(cursort2.getString(0));
                                            double s = Float.parseFloat(cursort2.getString(1));
                                            double rl = Double.parseDouble(r);

                                            double cg = (c / 100) * rl;
                                            double sg = (s / 100) * rl;
                                            double f = cg + sg;
                                            pojoProductfinal.setTax("" + (""+String.format("%.2f", f)));
                                        } else {
                                            double c = Float.parseFloat(cursort2.getString(0));
                                            double s = Float.parseFloat(cursort2.getString(1));
                                            double ig = Float.parseFloat(cursort2.getString(2));
                                            double rl = Double.parseDouble(r);

                                            double cg = (c / 100) * rl;
                                            double sg = (s / 100) * rl;
                                            double ig2 = (ig / 100) * rl;
                                            double f = ig2;
                                            pojoProductfinal.setTax("" + (""+String.format("%.2f", f)));
                                        }


                                    }
                                    while (cursort2.moveToNext());
                                }

                                if (ss.equalsIgnoreCase("")) {

                                } else {
                                    productlist.add(pojoProductfinal);
                                    recyclerView.setAdapter(adapter);
                                }


                            }

//                Log.e("Check",""+cursor.getString(1));


                            // array2.add(cursor.getString(11));
                            // Log.e("VAL",""+cursor.getString(11));

                        } while (cursort.moveToNext());

                    }
                }

            }
            else
            {
                title.setText(sharedpreferences.getString("rn", ""));
                title2.setText("Payment");

                DecimalFormat amountFormate = new DecimalFormat("############.##");
                amountFormate.setMinimumFractionDigits(2);
                amountFormate.setMaximumFractionDigits(2);

                String t = i.getStringExtra("T");
                String o = i.getStringExtra("O");
                String ss = i.getStringExtra("ss");
                namt = i.getStringExtra("ta");
                final SQLiteDatabase dbs = db.getWritableDatabase();

                androidx.recyclerview.widget.RecyclerView recyclerView = findViewById(R.id.itm);
                recyclerView.setLayoutManager(new LinearLayoutManager(Viewstockinward.this));
                adapter = new MyRecyclerViewAdapter(Viewstockinward.this, productlist);

                String nid = i.getStringExtra("II");
                String nid2 = nid.substring(1, nid.length() - 1);
                String[] id = nid2.split(",");
                productlist.clear();


                for(int ik = 0 ; ik< id.length;ik++) {

                    String selectQueryt = "SELECT  * FROM productlist2 where id =" + id[ik];
                    Cursor cursort = dbs.rawQuery(selectQueryt, null);

                    // looping through all rows and adding to list
                    if (cursort.moveToFirst()) {
                        do {

                            pojoProductfinal pojoProductfinal = new pojoProductfinal();

                            if (t.equalsIgnoreCase("")) {
                                trans.setText("0");
                            } else {
                                trans.setText(t);

                            }
                            if (o.equalsIgnoreCase("")) {
                                others.setText("0");
                            } else {
                                others.setText(o);
                            }

                            //String id = cursort.getString(0);
                            String r = cursort.getString(6);

                            pojoProductfinal.setGa(cursort.getString(6));
                            pojoProductfinal.setQty(cursort.getString(2));
                            pojoProductfinal.setRate(cursort.getString(3));
                            pojoProductfinal.setItem(cursort.getString(1));
                            pojoProductfinal.setAmt(cursort.getString(4));

                            pojoProductfinal.setDis(cursort.getString(5));


                            if (ss.equalsIgnoreCase("")) {
                                pojoProductfinal.setTax("");
                            } else if (ss.equalsIgnoreCase("0")) {
                                Cursor cursort2 = dbs.query("product", new String[]{"In_cgst",
                                                "In_sgst", "In_igst", "In_hsn_desc"}, "In_product_name" + "=?",
                                        new String[]{cursort.getString(1)}, null, null, null, null);

                                if (cursort2.moveToFirst()) {
                                    do {


                                        double c = Float.parseFloat(cursort2.getString(0));
                                        double s = Float.parseFloat(cursort2.getString(1));
                                        double rl = Double.parseDouble(r);

                                        double cg = (c / 100) * rl;
                                        double sg = (s / 100) * rl;
                                        double f = cg + sg;
                                        pojoProductfinal.setTax("" + (""+String.format("%.2f", f)));


                                    }
                                    while (cursort2.moveToNext());
                                }

                                productlist.add(pojoProductfinal);
                                recyclerView.setAdapter(adapter);


                            } else if (ss.equalsIgnoreCase("1")) {
                                Cursor cursort2 = dbs.query("product", new String[]{"In_cgst",
                                                "In_sgst", "In_igst", "In_hsn_desc"}, "In_product_name" + "=?",
                                        new String[]{cursort.getString(1)}, null, null, null, null);

                                if (cursort2.moveToFirst()) {
                                    do {

                                        if (cursort2.getString(2).equalsIgnoreCase("0.0000")) {
                                            double c = Float.parseFloat(cursort2.getString(0));
                                            double s = Float.parseFloat(cursort2.getString(1));
                                            double rl = Double.parseDouble(r);

                                            double cg = (c / 100) * rl;
                                            double sg = (s / 100) * rl;
                                            double f = cg + sg;
                                            pojoProductfinal.setTax("" + (""+String.format("%.2f", f)));
                                        } else {
                                            double c = Float.parseFloat(cursort2.getString(0));
                                            double s = Float.parseFloat(cursort2.getString(1));
                                            double ig =Float.parseFloat(cursort2.getString(2));
                                            double rl = Double.parseDouble(r);

                                            double cg = (c / 100) * rl;
                                            double sg = (s / 100) * rl;
                                            double ig2 = (ig / 100) * rl;
                                            double f = ig2;
                                            pojoProductfinal.setTax("" + (""+String.format("%.2f", f)));
                                        }


                                    }
                                    while (cursort2.moveToNext());
                                }

                                if (ss.equalsIgnoreCase("")) {

                                } else {
                                    productlist.add(pojoProductfinal);
                                    recyclerView.setAdapter(adapter);
                                }


                            }

//                Log.e("Check",""+cursor.getString(1));


                            // array2.add(cursor.getString(11));
                            // Log.e("VAL",""+cursor.getString(11));

                        } while (cursort.moveToNext());

                    }
                }
            }


        }



    }
    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

        private List<pojoProductfinal> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<pojoProductfinal> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewstockadjustment, parent, false);
            return new MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
            pojoProductfinal pojoProductfinal = mData.get(position);

            DecimalFormat amountFormate  = new DecimalFormat("############.##");
            amountFormate.setMinimumFractionDigits(2);
            amountFormate.setMaximumFractionDigits(2);
            holder.tqty.setText(pojoProductfinal.getQty());
            holder.trate.setText(pojoProductfinal.getRate());
            holder.tga.setText(pojoProductfinal.getGa());
            holder.ttax.setText(pojoProductfinal.getTax());
            holder.myTextView.setText(pojoProductfinal.getItem());
            holder.cost.setText(pojoProductfinal.getAmt());
            holder.dis.setText(pojoProductfinal.getDis());
            double x = Double.parseDouble(pojoProductfinal.getTax());
            double y = Double.parseDouble(pojoProductfinal.getGa());

            double xx  = Double.parseDouble(pojoProductfinal.getGa());
            double yy  = Double.parseDouble(pojoProductfinal.getAmt());

            holder.tocost.setText(""+(""+String.format("%.2f", xx-yy)));

            double z = x + y;

            zto += x + y;

            zto2 +=zto;

            holder.ttotal.setText(""+(""+String.format("%.2f", z)));

            double tr = Double.parseDouble(trans.getText().toString());
            double ot = Double.parseDouble(others.getText().toString());
            double ttam = Double.parseDouble(namt);


            double tot = ot + ttam+tr;
            if(inwardflag == 1)
            {
                holder.ttocost.setText("Other Cost(Base):");
            }
            //Toast.makeText(Viewstockinward.this, ""+tot, Toast.LENGTH_SHORT).show();
            amount.setText(""+(""+String.format("%.2f", tot)));
            amountsi.setText(""+(""+String.format("%.2f", tot)));
            amount1.setText(""+Float.parseFloat(namt));

        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder  {
            TextView myTextView,tqty,trate,ttax,ttotal,tga,dis,cost,ttocost,tocost;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.titem);
                tqty = itemView.findViewById(R.id.tqty);
                trate = itemView.findViewById(R.id.trate);
                ttax = itemView.findViewById(R.id.ttax);
                ttotal = itemView.findViewById(R.id.ttotal);
                tga = itemView.findViewById(R.id.tga);
                dis = itemView.findViewById(R.id.dis);
                cost = itemView.findViewById(R.id.cost);
                tocost = itemView.findViewById(R.id.tocost);
                ttocost = itemView.findViewById(R.id.ttocost);


            }


        }

        // convenience method for getting data at click position


        // allows clicks events to be caught



    }
}