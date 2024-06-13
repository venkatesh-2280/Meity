package cdfi.fintantra.meity.Pawhs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cdfi.fintantra.meity.R;
import cdfi.fintantra.meity.Pawhs.model.PostSiNoDetailDao;
import cdfi.fintantra.meity.Pawhs.recactpurchase.DeleteSerialItemListener;


public class SerialNoListAdapter extends RecyclerView.Adapter<SerialNoListAdapter.ViewHolder> {

    private Context mContext;
    private List<PostSiNoDetailDao> bulkSInoDetailsDaoList;
    private LayoutInflater inflater;
    private DeleteSerialItemListener deleteSerialItemListener;

    public SerialNoListAdapter(Context mContext, List<PostSiNoDetailDao> bulkSInoDetailsDaoList, DeleteSerialItemListener deleteSerialItemListener) {
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
        this.bulkSInoDetailsDaoList = bulkSInoDetailsDaoList;
        this.deleteSerialItemListener=deleteSerialItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.serial_list_screen, parent,false);
        return new SerialNoListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        PostSiNoDetailDao bulkSInoDetailsDao=bulkSInoDetailsDaoList.get(position);
        holder.textViewSerialNo.setText(bulkSInoDetailsDao.getIn_slno());

        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSerialItemListener.deleteItem(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return bulkSInoDetailsDaoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewSerialNo;
        private ImageView imageViewDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSerialNo=(TextView)itemView.findViewById(R.id.txt_serialNo);
            imageViewDelete=(ImageView)itemView.findViewById(R.id.img_delete);
        }
    }
}
