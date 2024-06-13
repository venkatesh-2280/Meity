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

import cdfi.fintantra.meity.Pawhs.model.PostQtyDetailDao;
import cdfi.fintantra.meity.R;
import cdfi.fintantra.meity.Pawhs.recactpurchase.QualityEditDeleteListener;


public class QualityListParameterAdapter extends RecyclerView.Adapter<QualityListParameterAdapter.ViewHolder> {
    private Context mContext;
    private List<PostQtyDetailDao> bulkQtyDetailDaoList;
    private QualityEditDeleteListener qualityEditDeleteListener;
    private LayoutInflater inflater;

    public QualityListParameterAdapter(Context mContext, List<PostQtyDetailDao> bulkQtyDetailDaoList, QualityEditDeleteListener qualityEditDeleteListener) {


    }

    public QualityListParameterAdapter(Context mContext, List<cdfi.fintantra.meity.Pawhs.model.PostQtyDetailDao> postQtyDetailDaoList, Qparpe qualityEditDeleteListener) {

        this.mContext = mContext;
        this.bulkQtyDetailDaoList = bulkQtyDetailDaoList;
        inflater= LayoutInflater.from(mContext);
        this.qualityEditDeleteListener = qualityEditDeleteListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.quality_parameter_list, parent,false);
        return new QualityListParameterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        PostQtyDetailDao bulkQtyDetailDao=bulkQtyDetailDaoList.get(position);
        holder.textViewQualityPara.setText(bulkQtyDetailDao.getIn_qty_name());
        holder.textViewUom.setText(bulkQtyDetailDao.getUom());
        holder.textViewThreshold.setText(bulkQtyDetailDao.getThresHoldValue());

        if(bulkQtyDetailDao.getIn_qty_name().equalsIgnoreCase("LIVE")){
            if(bulkQtyDetailDao.getIn_actual_value()==1){
                holder.textViewActualValue.setText("Nill");
            }else if(bulkQtyDetailDao.getIn_actual_value()==2){
                holder.textViewActualValue.setText("Few");
            }else if(bulkQtyDetailDao.getIn_actual_value()==3){
                holder.textViewActualValue.setText("Heavy");
            }

        }else {
            holder.textViewActualValue.setText(""+bulkQtyDetailDao.getIn_actual_value());
        }


        holder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qualityEditDeleteListener.editItem(position,"Edit_Quality_Item");
            }
        });

        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qualityEditDeleteListener.deleteItem(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bulkQtyDetailDaoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewQualityPara,textViewUom,textViewThreshold,textViewActualValue;

        private ImageView imageViewEdit,imageViewDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewQualityPara=(TextView)itemView.findViewById(R.id.txt_qual_para);
            textViewUom=(TextView)itemView.findViewById(R.id.txt_uom);
            textViewThreshold=(TextView)itemView.findViewById(R.id.txt_threshold);
            textViewActualValue=(TextView)itemView.findViewById(R.id.avs);
            imageViewEdit=(ImageView)itemView.findViewById(R.id.item_edit);
            imageViewDelete=(ImageView)itemView.findViewById(R.id.item_delete);
        }
    }
}
