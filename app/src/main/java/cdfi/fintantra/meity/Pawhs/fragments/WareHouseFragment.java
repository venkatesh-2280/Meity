package cdfi.fintantra.meity.Pawhs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import cdfi.fintantra.meity.HomePageActivity;


public class WareHouseFragment extends Fragment {

    private View view;
    private LinearLayout linearWareHouse;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // view = inflater.inflate(R.layout.pa_warehouse, container, false);
        initView();
        ((HomePageActivity) getActivity()).getSupportActionBar().setTitle("Warehouse Manager");
        return view;
    }

    private void initView() {

        ((HomePageActivity) getActivity()).getSupportActionBar().setTitle("Warehouse Manager");

//        linearWareHouse=(LinearLayout)view.findViewById(R.id.linear_warehouse);
//
//        linearWareHouse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(getActivity(), RecAppListVIewActivity.class);
//                intent.putExtra(ApiUtils.CHECK_STATUS,"warehouse");
//                startActivity(intent);
//
//            }
//        });

    }
}
