package com.gaadi.pratnerapps.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.EMIDetailsAdapter;
import com.gaadi.pratnerapps.models.EmiModel;
import com.gaadi.pratnerapps.utils.DividerItemDecoration;

import java.util.ArrayList;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by ashishkumar on 3/8/16.
 */
public class EMICalculatorFragment extends PartnerBaseFragment implements View.OnClickListener {
    public EditText etLoanAmount,etRateOfIntrest;
    private Button btnCalculateEMI;
    private RecyclerView recyclerEMIList;
    private LinearLayout emiDetailsLayout;
    private ArrayList<EmiModel> list;
    private EMIDetailsAdapter emiAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emicalculator,container,false);
        initializeView(view);
        getFragmentAdapter().setTitleMessage("EMI Calculator", false);
        return view;
    }

    private void initList(){
        list = new ArrayList<>();
        for(int i=1;i<8;i++){
            EmiModel emiModel = new EmiModel();
            emiModel.setMonth(12*i);
            emiModel.setPrice(0);
            list.add(emiModel);
        }
    }

    private void calculateEmi(double p,double r){
        for(EmiModel emiModel:list){
            emiModel.setPrice(calcEmi(p,r,emiModel.getMonth()));
        }
    }

    public double calcEmi(double p, double r, double n) {
        double R = (r / 12) / 100;
        double e = (p * R * (Math.pow((1 + R), n)) / ((Math.pow((1 + R), n)) - 1));
        return e;
    }

    private void initializeView(View view){
        etLoanAmount = (EditText) view.findViewById(R.id.etLoanAmount);
        etRateOfIntrest = (EditText)view.findViewById(R.id.etRateOfIntrest);
        btnCalculateEMI = (Button)view.findViewById(R.id.btnCalculateEMI);
        recyclerEMIList = (RecyclerView)view.findViewById(R.id.recycle_list);
        recyclerEMIList.setNestedScrollingEnabled(false);
        btnCalculateEMI.setOnClickListener(this);
        emiDetailsLayout = (LinearLayout)view.findViewById(R.id.emiDetails_layout);
        emiDetailsLayout.setVisibility(View.GONE);

        recyclerEMIList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerEMIList.setItemAnimator(new DefaultItemAnimator());
        recyclerEMIList.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.border_bottom));

        initList();

        emiAdapter = new EMIDetailsAdapter(getActivity(), list);
        recyclerEMIList.setAdapter(emiAdapter);
    }

    @Override
    public void onClick(View v) {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),0);

        if(validate()){
            if(emiDetailsLayout.getVisibility() == View.GONE){
                emiDetailsLayout.setVisibility(View.VISIBLE);
            }
            calculateEmi(Double.parseDouble(etLoanAmount.getText().toString()), Double.parseDouble(etRateOfIntrest.getText().toString()));
            emiAdapter.notifyDataSetChanged();
        }
    }

    private Boolean validate(){
        if(etLoanAmount.getText().toString().trim().equals("")){
            etLoanAmount.setError("Enter Loan Amount");
            return false;
        }else if(etRateOfIntrest.getText().toString().trim().equals("")){
            etRateOfIntrest.setError("Enter rate  of intrest");
            return false;
        }
        return true;
    }

}
