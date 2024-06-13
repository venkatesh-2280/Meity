package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Othercosticd extends AppCompatActivity {
    TextView item, rate, quantity, amount;
    RadioButton rb1, rb2, rb3, rb4;
    RadioGroup radioGroup1, radioGroup2;
    AutoCompleteTextView othercost, eamount;
    Button view,back,add;
    String ts,isbase,isadd,othercostcode;
    SQLiteDatabase dbs;
    ArrayList<String> array;
    ArrayList<String> array2;
    ArrayList<String> array3;
    ArrayList<pojoocost> othercostlist;
    DBHelper db;
    Dialog dialog;
    MyRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_othercosticd);
        getSupportActionBar().hide();

        db  = new DBHelper(this);
        array = new ArrayList<>();
        array2 = new ArrayList<>();
        array3 = new ArrayList<>();
        othercostlist = new ArrayList<pojoocost>();
        item = (TextView) findViewById(R.id.item);
        quantity = (TextView) findViewById(R.id.quantity);
        rate = (TextView) findViewById(R.id.rate);

        amount = (TextView) findViewById(R.id.amount);

        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);

        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);

        othercost = (AutoCompleteTextView) findViewById(R.id.othercost);
        eamount = (AutoCompleteTextView) findViewById(R.id.eamount);
      //  eamount.setFilters(new InputFilter[] {new DecimalDigitsInputFilter2(8,2)});
        eamount.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        add = (Button)findViewById(R.id.add);
        view = (Button)findViewById(R.id.view);
        back = (Button)findViewById(R.id.back);

        item.setText(Pojokyc.item);
        quantity.setText(Pojokyc.quantity);
        rate.setText(Pojokyc.rate);
        amount.setText(Pojokyc.amount);

         dbs = db.getWritableDatabase();
        Intent i = getIntent();
        ts = i.getStringExtra("TS");
        Log.e("TS","= "+ts);
        String selectQuery = "SELECT  * FROM oci";
        Cursor cursor = dbs.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                if(array.contains(cursor.getString(2)))
                {

                }
                else
                {
                    array.add(cursor.getString(3));
                    array2.add(cursor.getString(2));
                }

            } while (cursor.moveToNext());
        }

        Log.e("TTT",""+cursor.getCount());

        ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(Othercosticd.this,
                R.layout.spinnertext, array);
        othercost.setAdapter(adapterlist);
        othercost.setThreshold(0);
        othercost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                othercost.showDropDown();
                othercost.requestFocus();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        othercost.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                othercostcode = array.get(position);

            }
        });

        eamount.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() == 1 && s.toString().startsWith("0")) {
                    s.clear();
                }
                else if(s.toString().length() == 1 && s.toString().startsWith("."))
                {
                    s.clear();
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {



            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(othercost.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Othercosticd.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty Other Cost")
//set positive button
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //set what would happen when positive button is clicked
                                            // finish();

                                        }
                                    }
//set negative button

                            )
                            .show();
                }
                else if(eamount.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Othercosticd.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty Amount")
//set positive button
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //set what would happen when positive button is clicked
                                            // finish();

                                        }
                                    }
//set negative button

                            )
                            .show();
                }
                else {

                    if(array3.contains(othercost.getText().toString()))
                    {
                        final AlertDialog alertDialog = new AlertDialog.Builder(Othercosticd.this)
//set icon
                                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                .setTitle("Alert!")
//set message
                                .setMessage(othercost.getText().toString()+" Already Added")
//set positive button
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //set what would happen when positive button is clicked
                                                // finish();

                                            }
                                        }
//set negative button

                                )
                                .show();
                    }
                    else
                    {

                    if (rb1.isChecked()) {
                        isbase = "Base Price";
                    } else {
                        isbase = "Non Base Price";
                    }

                    if (rb3.isChecked()) {
                        isadd = "Add";
                    } else {
                        isadd = "Less";
                    }

                    db.inserticdoc(ts, othercost.getText().toString(), othercostcode, isbase, isadd, eamount.getText().toString());
                        array3.add(othercost.getText().toString());
                    othercost.setText("");
                    rb1.setChecked(true);
                    rb3.setChecked(true);
                    eamount.setText("");

                    final AlertDialog alertDialog = new AlertDialog.Builder(Othercosticd.this)
//set icon
                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                            .setTitle("Success!")
//set message
                            .setMessage("Other Cost Added")
//set positive button
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //set what would happen when positive button is clicked
                                            // finish();

                                        }
                                    }
//set negative button

                            )
                            .show();
                }
                }
            }
        });
        Cursor cursoro = dbs.query("icdoc", new String[]{"id","v1","v2","v3","v4","v5","v6"
                }, "v1" + "=? COLLATE NOCASE",
                new String[]{ts}, null, null, null, null);

        array3.clear();

        // looping through all rows and adding to list
        if (cursoro.moveToFirst()) {
            do {

                array3.add(cursoro.getString(2));


            } while (cursoro.moveToNext());

        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(Othercosticd.this);
                dialog.setContentView(R.layout.othercosticdview);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                // set the custom dialog components - text, image and button





                androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.rcview);
                recyclerView.setLayoutManager(new LinearLayoutManager(Othercosticd.this));
                adapter = new MyRecyclerViewAdapter(Othercosticd.this, othercostlist);
                String selectQuery = "SELECT  * FROM icdoc";
                othercostlist.clear();
                Cursor cursor = dbs.query("icdoc", new String[]{"id","v1","v2","v3","v4","v5","v6"
                        }, "v1" + "=? COLLATE NOCASE",
                        new String[]{ts}, null, null, null, null);
                Log.e("COUNT", "" + cursor.getCount());
                array3.clear();

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        pojoocost pojoocost = new pojoocost();

                        array3.add(cursor.getString(2));

                        pojoocost.setT1(cursor.getString(0));
                        pojoocost.setT2(cursor.getString(2));
                        pojoocost.setT3(cursor.  getString(4));
                        pojoocost.setT4(cursor.getString(5));
                        pojoocost.setT5(cursor.getString(6));
                        //pojoocost.setT6(cursor.getString(6));


                        othercostlist.add(pojoocost);
                        // array2.add(cursor.getString(11));
                        // Log.e("VAL",""+cursor.getString(11));
                        recyclerView.setAdapter(adapter);
                    } while (cursor.moveToNext());

                }

                // set up the RecyclerView



                ImageView dialogButton = (ImageView) dialog.findViewById(R.id.close);

                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
//                        finish();
//                        startActivity(getIntent());
                    }
                });

                dialog.show();
            }
        });

    }
    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

        private List<pojoocost> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, ArrayList<pojoocost> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.othercosticd, parent, false);
            return new MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, final int position) {
            final pojoocost pojoocost2 = mData.get(position);
            holder.t1.setText(pojoocost2.getT2());
            holder.t2.setText(pojoocost2.getT3());
            holder.t3.setText(pojoocost2.getT4());
            holder.t4.setText(pojoocost2.getT5());


            holder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    array3.remove(pojoocost2.getT2());


                    db.deleteoc(Integer.valueOf(pojoocost2.getT1()));
                    othercostlist.remove(position);
                    adapter.notifyDataSetChanged();


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
            TextView t1,t2,t3,t4;
            ImageView del;

            ViewHolder(View itemView) {
                super(itemView);
                t1 = itemView.findViewById(R.id.t1);
                t2 = itemView.findViewById(R.id.t2);
                t3 = itemView.findViewById(R.id.t3);
                t4 = itemView.findViewById(R.id.t4);


                del = (ImageView)itemView.findViewById(R.id.delete);




            }


        }

        // convenience method for getting data at click position




        // parent activity will implement this method to respond to click events

    }
}