package cdfi.fintantra.meity.Pawhs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cdfi.fintantra.meity.R;
import cdfi.fintantra.meity.Pawhs.model.RangeNameDao;


public class RangeNameSpinnerAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflter;
    List<RangeNameDao> arrayList = new ArrayList<>();

    public RangeNameSpinnerAdapter(Context context, List<RangeNameDao> arrayList) {
        this.context = context;
        this.arrayList =arrayList;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        TextView spinnerText = (TextView) view.findViewById(R.id.spinnerText);
        spinnerText.setText(arrayList.get(i).getRangeName());
        return view;
    }}
