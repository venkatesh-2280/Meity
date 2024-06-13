package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Otherinputsicd extends AppCompatActivity {
    TextView item, rate, quantity, amount;
    RadioButton rb1, rb2, rb3, rb4;
    RadioGroup radioGroup1, radioGroup2;
    AutoCompleteTextView nolp, mdate,edate,mrp,cvolume,type;
    Button view,back,add;
    String ts,isbase,isadd,othercostcode;
    SQLiteDatabase dbs;
    ArrayList<String> array;
    ArrayList<String> array2;
    ArrayList<String> array3;
    ArrayList<pojootherip> otherip;
    DBHelper db;
    Dialog dialog;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date,date2;
    MyRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherinputsicd);
        getSupportActionBar().hide();
        myCalendar = Calendar.getInstance();
        db  = new DBHelper(this);
        array = new ArrayList<>();
        array2 = new ArrayList<>();
        array3 = new ArrayList<>();
        otherip = new ArrayList<pojootherip>();
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

        nolp = (AutoCompleteTextView) findViewById(R.id.nolp);
        cvolume = (AutoCompleteTextView) findViewById(R.id.cvolume);
        type = (AutoCompleteTextView) findViewById(R.id.type);
        mdate = (AutoCompleteTextView) findViewById(R.id.mdate);
        edate = (AutoCompleteTextView) findViewById(R.id.edate);
        mrp = (AutoCompleteTextView) findViewById(R.id.mrp);

        mrp.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabel();
            }

        };

        date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabel2();
            }

        };

        mdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Otherinputsicd.this,android.R.style.Theme_Holo_Dialog , date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });

        edate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Otherinputsicd.this,android.R.style.Theme_Holo_Dialog , date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                
                datePickerDialog.show();

            }
        });
        add = (Button)findViewById(R.id.add);
        view = (Button)findViewById(R.id.view);
        back = (Button)findViewById(R.id.back);

        item.setText(Pojokyc.item);
        quantity.setText(Pojokyc.quantity);
        rate.setText(Pojokyc.rate);
        amount.setText(Pojokyc.amount);
        final SimpleDateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");
        mdate.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                //Toast.makeText(getApplicationContext(),"after text change",Toast.LENGTH_LONG).show();
                if(edate.getText().toString().equalsIgnoreCase(""))
                {

                }
                else
                {
                    try {


                        if (dfDate.parse(mdate.getText().toString()).before(dfDate.parse(edate.getText().toString()))) {
                            // If start date is before end date.
                        } else if (dfDate.parse(mdate.getText().toString()).equals(dfDate.parse(edate.getText().toString()))) {
                            // If two dates are equal.
                            mdate.setText("");
                            edate.setText("");
                            final AlertDialog alertDialog = new AlertDialog.Builder(Otherinputsicd.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Alert!")
//set message
                                    .setMessage("Invalid Date")
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
                        } else {
                            // If start date is after the end date.
                            mdate.setText("");
                            edate.setText("");
                            final AlertDialog alertDialog = new AlertDialog.Builder(Otherinputsicd.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Alert!")
//set message
                                    .setMessage("Invalid Date")
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
                    catch (Exception e)
                    {

                    }
                }
            }
        });

        edate.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                //Toast.makeText(getApplicationContext(),"after text change",Toast.LENGTH_LONG).show();
                if(mdate.getText().toString().equalsIgnoreCase(""))
                {

                }
                else
                {
                    try {


                        if (dfDate.parse(mdate.getText().toString()).before(dfDate.parse(edate.getText().toString()))) {
                            // If start date is before end date.
                        } else if (dfDate.parse(mdate.getText().toString()).equals(dfDate.parse(edate.getText().toString()))) {
                            // If two dates are equal.
                            mdate.setText("");
                            edate.setText("");
                            final AlertDialog alertDialog = new AlertDialog.Builder(Otherinputsicd.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Alert!")
//set message
                                    .setMessage("Invalid Date")
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
                        } else {
                            // If start date is after the end date.
                            mdate.setText("");
                            edate.setText("");
                            final AlertDialog alertDialog = new AlertDialog.Builder(Otherinputsicd.this)
//set icon
                                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                                    .setTitle("Alert!")
//set message
                                    .setMessage("Invalid Date")
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
                    catch (Exception e)
                    {

                    }
                }
            }
        });

        dbs = db.getWritableDatabase();
        Intent i = getIntent();
        ts = i.getStringExtra("TS");
        Log.e("TS","= "+ts);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nolp.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Otherinputsicd.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty No of Loose Packs")
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
                else  if(cvolume.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Otherinputsicd.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty Carton Volume")
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
                else  if(type.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Otherinputsicd.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty Type")
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
                else  if(mdate.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Otherinputsicd.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty Manufacture Date")
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
                else  if(edate.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Otherinputsicd.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty Expiry Date")
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
                else  if(mrp.getText().toString().equalsIgnoreCase(""))
                {
                    final AlertDialog alertDialog = new AlertDialog.Builder(Otherinputsicd.this)
//set icon
                            .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                            .setTitle("Alert!")
//set message
                            .setMessage("Empty MRP")
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
                    db.inserticdip(ts,nolp.getText().toString(),cvolume.getText().toString(),type.getText().toString(),mdate.getText().toString(),edate.getText().toString(),mrp.getText().toString(),"");
//                    nolp.setText("");
//                    cvolume.setText("");
//                    type.setText("");
//                    mdate.setText("");
//                    edate.setText("");
//                    mrp.setText("");


                    final AlertDialog alertDialog = new AlertDialog.Builder(Otherinputsicd.this)
//set icon
                            .setIcon(android.R.drawable.ic_menu_save)
//set title
                            .setTitle("Success!")
//set message
                            .setMessage("Other Inputs Added")
//set positive button
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //set what would happen when positive button is clicked
                                             finish();

                                        }
                                    }
//set negative button

                            )
                            .show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(Otherinputsicd.this);
                dialog.setContentView(R.layout.othercosticdview);
                dialog.getWindow().getAttributes().windowAnimations = R.anim.fadein;
                dialog.setTitle("Title...");
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                // set the custom dialog components - text, image and button





                androidx.recyclerview.widget.RecyclerView recyclerView = dialog.findViewById(R.id.rcview);
                recyclerView.setLayoutManager(new LinearLayoutManager(Otherinputsicd.this));
                adapter = new MyRecyclerViewAdapter(Otherinputsicd.this, otherip);
                String selectQuery = "SELECT  * FROM icdoc";
                otherip.clear();
                Cursor cursor = dbs.query("icdip", new String[]{"id","v1","v2","v3","v4","v5","v6","v7"
                        }, "v1" + "=? COLLATE NOCASE",
                        new String[]{ts}, null, null, null, null);
                Log.e("COUNT", "" + cursor.getCount());
                array3.clear();

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        pojootherip pojootherip = new pojootherip();

                        //array3.add(cursor.getString(3));

                        pojootherip.setT1(cursor.getString(0));
                        pojootherip.setT2(cursor.getString(2));
                        pojootherip.setT3(cursor.  getString(3));
                        pojootherip.setT4(cursor.getString(4));
                        pojootherip.setT5(cursor.getString(5));
                        pojootherip.setT6(cursor.getString(6));
                        pojootherip.setT7(cursor.getString(7));


                        otherip.add(pojootherip);
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

    @Override
    public void onResume(){
        super.onResume();
        Cursor cursor = dbs.query("icdip", new String[]{"id","v1","v2","v3","v4","v5","v6","v7"
                }, "v1" + "=? COLLATE NOCASE",
                new String[]{ts}, null, null, null, null);
        Log.e("COUNT", "" + cursor.getCount());
        array3.clear();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                pojootherip pojootherip = new pojootherip();

                //array3.add(cursor.getString(3));

                pojootherip.setT1(cursor.getString(0));
                pojootherip.setT2(cursor.getString(2));
                pojootherip.setT3(cursor.  getString(3));
                pojootherip.setT4(cursor.getString(4));
                pojootherip.setT5(cursor.getString(5));
                pojootherip.setT6(cursor.getString(6));
                pojootherip.setT7(cursor.getString(7));

                nolp.setText(pojootherip.getT2());
               cvolume.setText(pojootherip.getT3());
               type.setText(pojootherip.getT4());
             mdate.setText(pojootherip.getT5());
                edate.setText(pojootherip.getT6());
               mrp.setText(pojootherip.getT7());

            } while (cursor.moveToNext());

        }
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mdate.setText(sdf.format(myCalendar.getTime()));

    }
    private void updateLabel2() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edate.setText(sdf.format(myCalendar.getTime()));

    }

    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

        private List<pojootherip> mData;
        private LayoutInflater mInflater;


        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, ArrayList<pojootherip> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.otherinputicd, parent, false);
            return new MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, final int position) {
            final pojootherip pojootherip = mData.get(position);
            holder.t1.setText(pojootherip.getT2());
            holder.t2.setText(pojootherip.getT3());
            holder.t3.setText(pojootherip.getT4());
            holder.t4.setText(pojootherip.getT5());
            holder.t5.setText(pojootherip.getT6());
            holder.t6.setText(pojootherip.getT7());


            holder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    db.deleteip(Integer.valueOf(pojootherip.getT1()));
                    otherip.remove(position);
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
            TextView t1,t2,t3,t4,t5,t6;
            ImageView del;

            ViewHolder(View itemView) {
                super(itemView);
                t1 = itemView.findViewById(R.id.t1);
                t2 = itemView.findViewById(R.id.t2);
                t3 = itemView.findViewById(R.id.t3);
                t4 = itemView.findViewById(R.id.t4);
                t5 = itemView.findViewById(R.id.t5);
                t6 = itemView.findViewById(R.id.t6);


                del = (ImageView)itemView.findViewById(R.id.delete);




            }


        }

        // convenience method for getting data at click position




        // parent activity will implement this method to respond to click events

    }
}