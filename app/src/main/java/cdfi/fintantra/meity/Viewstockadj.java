package cdfi.fintantra.meity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

public class Viewstockadj extends AppCompatActivity {
    Viewstockadj.MyRecyclerViewAdapter adapter;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstockadj);
        getSupportActionBar().hide();
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Item 1");
        animalNames.add("Item 2");
        animalNames.add("Item 3");
        animalNames.add("Item 4");
        animalNames.add("Item 5");
        animalNames.add("Item 6");
        title = (TextView)findViewById(R.id.title);
        Intent i = getIntent();


        androidx.recyclerview.widget.RecyclerView recyclerView = findViewById(R.id.itm);
        recyclerView.setLayoutManager(new LinearLayoutManager(Viewstockadj.this));
        adapter = new Viewstockadj.MyRecyclerViewAdapter(Viewstockadj.this, animalNames);
        recyclerView.setAdapter(adapter);

    }
    public static class MyRecyclerViewAdapter extends RecyclerView.Adapter<Viewstockadj.MyRecyclerViewAdapter.ViewHolder> {

        private List<String> mData;
        private LayoutInflater mInflater;
        private Viewstockadj.MyRecyclerViewAdapter.ItemClickListener mClickListener;

        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<String> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public Viewstockadj.MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.viewstkadj, parent, false);
            return new Viewstockadj.MyRecyclerViewAdapter.ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(Viewstockadj.MyRecyclerViewAdapter.ViewHolder holder, int position) {
            String animal = mData.get(position);
            holder.myTextView.setText(animal);
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView myTextView;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.titem);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        // convenience method for getting data at click position
        String getItem(int id) {
            return mData.get(id);
        }

        // allows clicks events to be caught
        void setClickListener(Viewstockadj.MyRecyclerViewAdapter.ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

        // parent activity will implement this method to respond to click events
        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }
    }
}

