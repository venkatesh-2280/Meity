package cdfi.fintantra.meity.Pawhs;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cdfi.fintantra.meity.R;
import cdfi.fintantra.meity.Pawhs.model.ActProListDao;
import cdfi.fintantra.meity.Pawhs.recactpurchase.ActEditListener;
import cdfi.fintantra.meity.Pawhs.recactpurchase.RecAutualPurchaseViewActivity;


public class RecActualViewListAdapter extends RecyclerView.Adapter<RecActualViewListAdapter.ViewHolder> {
    private Context mContext;
    private List<ActProListDao> actProListDaoList;
    private List<ActProListDao> arraylist;
    private LayoutInflater inflater;
    private ActEditListener actEditListener;

    public RecActualViewListAdapter(Context mContext, List<ActProListDao> actProListDaoList, ActEditListener actEditListener) {
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
        this.actProListDaoList = actProListDaoList;
        this.arraylist = new ArrayList<ActProListDao>();
        this.arraylist.addAll(RecAutualPurchaseViewActivity.actProListDaoList);
        this.actEditListener=actEditListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.rec_actual_purchase_view_list, parent,false);
        return new RecActualViewListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ActProListDao actProListDao = actProListDaoList.get(position);

        holder.textViewLotNo.setText(actProListDao.getOut_lotno());
        holder.textViewFarmerName.setText(actProListDao.getOut_farmer_name());
        holder.textViewFarmerCode.setText(actProListDao.getOut_farmer_code());
        holder.textViewMember.setText(actProListDao.getOut_member_type());
        holder.textViewItem.setText(actProListDao.getOut_item_name());
        holder.textViewStatus.setText(actProListDao.getOut_status());
        SQLiteDataBaseHandler db = new SQLiteDataBaseHandler(mContext);
        final SQLiteDatabase dbs = db.getWritableDatabase();
        Cursor cursoruom = dbs.query("ProductMasterAllProduct", new String[]{"out_uomtype_code"
                }, "PmapOutProductCode" + "=?",
                new String[]{actProListDao.getOut_item_code()}, null, null, null, null);

        if(cursoruom.moveToFirst())
        {
            holder.txtuom.setText(cursoruom.getString(0));
        }
        holder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(actProListDao.getOut_status().equalsIgnoreCase("Actual")||actProListDao.getOut_status().equalsIgnoreCase("Approved")){
                    actEditListener.editeItem(actProListDao);
                }else {
                    showStatusDialog("Actual and Approve Only Edit");
                }



            }
        });

    }

    private void showStatusDialog(String s) {
        new AlertDialog.Builder(mContext)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Info!")
                .setMessage(s)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public int getItemCount() {
        return actProListDaoList.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        RecAutualPurchaseViewActivity.actProListDaoList.clear();
        if (charText.length() == 0) {
            RecAutualPurchaseViewActivity.actProListDaoList.addAll(arraylist);
        } else {
            for (ActProListDao wp : arraylist) {
                if (wp.getOut_farmer_name().toLowerCase(Locale.getDefault()).contains(charText)) {
                    RecAutualPurchaseViewActivity.actProListDaoList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewLotNo,textViewFarmerName,textViewFarmerCode;
        private TextView textViewMember,textViewItem,textViewStatus,txtuom;
        private ImageView imageViewEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLotNo=(TextView)itemView.findViewById(R.id.txt_lotNo);
            textViewFarmerName=(TextView)itemView.findViewById(R.id.txt_farmerName);
            textViewFarmerCode=(TextView)itemView.findViewById(R.id.txt_farmerCode);
            textViewMember=(TextView)itemView.findViewById(R.id.txt_memberType);
            textViewItem=(TextView)itemView.findViewById(R.id.txt_Item);
            textViewStatus=(TextView)itemView.findViewById(R.id.txt_status);
            imageViewEdit = itemView.findViewById(R.id.item_edit);
            txtuom = itemView.findViewById(R.id.txtuom);
        }
    }
}
