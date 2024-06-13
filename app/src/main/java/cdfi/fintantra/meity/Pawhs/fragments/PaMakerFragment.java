package cdfi.fintantra.meity.Pawhs.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import cdfi.fintantra.meity.HomePageActivity;
import cdfi.fintantra.meity.Pojokyc;
import cdfi.fintantra.meity.R;
import cdfi.fintantra.meity.Pawhs.SaleEntry;
import cdfi.fintantra.meity.Pawhs.recactpurchase.RecActualPurchaseActivity;


public class PaMakerFragment extends Fragment {

    private View view;
    private LinearLayout linearResEstPurchase,linearRecActPurchase,linearRecActPurchase2,linnearse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.pamaker_screen, container, false);
        initView();

        ((HomePageActivity) getActivity()).getSupportActionBar().setTitle("Produce Aggregator ( LRP )");

        return view;
    }

    private void initView() {
        ((HomePageActivity) getActivity()).getSupportActionBar().setTitle("Produce Aggregator ( LRP )");


        linearResEstPurchase=(LinearLayout)view.findViewById(R.id.linar_rec_est_pur);
        linearRecActPurchase=(LinearLayout)view.findViewById(R.id.linar_rec_act_pur);
        linearRecActPurchase2=(LinearLayout)view.findViewById(R.id.linar_rec_act_pur2);
        linnearse=(LinearLayout)view.findViewById(R.id.linar_se);

        linearResEstPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*RecEstPurchaseFragment recEstPurchaseFragment=new RecEstPurchaseFragment();
                Utility.loadFragment(getFragmentManager(), R.id.content_frame, recEstPurchaseFragment);*/
                // startActivity(new Intent(getActivity(), Tofullfillment.class));
            }
        });

        linearRecActPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pojokyc.purno="";
                /*RecEstPurchaseFragment recEstPurchaseFragment=new RecEstPurchaseFragment();
                Utility.loadFragment(getFragmentManager(), R.id.content_frame, recEstPurchaseFragment);*/
                Intent intent = new Intent(getActivity(), RecActualPurchaseActivity.class);
                intent.putExtra("Frm", "Actual");
                intent.putExtra("PEID", "");
                startActivity(intent);
            }
        });
        linearRecActPurchase2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pojokyc.purno="";
                /*RecEstPurchaseFragment recEstPurchaseFragment=new RecEstPurchaseFragment();
                Utility.loadFragment(getFragmentManager(), R.id.content_frame, recEstPurchaseFragment);*/
                Intent intent = new Intent(getActivity(), RecActualPurchaseActivity.class);
                intent.putExtra("Frm", "Approved");
                intent.putExtra("PEID", "");
                startActivity(intent);
            }
        });


        linnearse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pojokyc.saleno = "";
                /*RecEstPurchaseFragment recEstPurchaseFragment=new RecEstPurchaseFragment();
                Utility.loadFragment(getFragmentManager(), R.id.content_frame, recEstPurchaseFragment);*/
                Intent intent = new Intent(getActivity(), SaleEntry.class);
                intent.putExtra("SEID", "");
                startActivity(intent);
            }
        });
    }
}
