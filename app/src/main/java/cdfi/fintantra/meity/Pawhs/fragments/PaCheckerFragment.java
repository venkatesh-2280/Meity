package cdfi.fintantra.meity.Pawhs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import cdfi.fintantra.meity.HomePageActivity;


public class PaCheckerFragment extends Fragment {

    private View view;
    private LinearLayout linearAppActPurchase,linearestimate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //view = inflater.inflate(R.layout.pachecker_screen, container, false);
        initView();
        ((HomePageActivity) getActivity()).getSupportActionBar().setTitle("FPO Manager");
        return view;
    }

    private void initView() {

        ((HomePageActivity) getActivity()).getSupportActionBar().setTitle("FPO Manager");

      //  linearAppActPurchase=(LinearLayout)view.findViewById(R.id.linear_approve);
       // linearestimate=(LinearLayout)view.findViewById(R.id.linear_estimated);

        linearAppActPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent intent=new Intent(getActivity(), RecAppListVIewActivity.class);
              //  intent.putExtra(ApiUtils.CHECK_STATUS,"approve");
              //  startActivity(intent);

            }
        });
        linearestimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent=new Intent(getActivity(), Estimatedpurlist.class);
              //  startActivity(intent);

            }
        });
    }
}
