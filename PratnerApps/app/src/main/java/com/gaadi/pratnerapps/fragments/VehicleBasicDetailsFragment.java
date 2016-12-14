package com.gaadi.pratnerapps.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.BuyCarDetails;
import com.gaadi.pratnerapps.utils.Constants;
import com.gaadi.pratnerapps.utils.Utils;


public class VehicleBasicDetailsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private TextView tvMake, tvPrice, tvAddress, tvReqCall;


    private String mParam1,price;
    private BuyCarDetails searchCarDetails;


    public static VehicleBasicDetailsFragment newInstance(BuyCarDetails carDetails) {
        VehicleBasicDetailsFragment fragment = new VehicleBasicDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.VEHICLE_ID, carDetails);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchCarDetails = new BuyCarDetails();
            searchCarDetails = (BuyCarDetails) getArguments().getSerializable(Constants.VEHICLE_ID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_vehicle_fragment, null);
        intilizationView(view);

        return view;
    }

    public void intilizationView(View view) {

        tvAddress = (TextView) view.findViewById(R.id.tvAddress);
        tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        setData();
    }

    public void setData() {
        if (searchCarDetails.getCity() != null)
        tvAddress.setText(searchCarDetails.getCity());

        if (searchCarDetails.getPrice() != null)
            price = searchCarDetails.getPrice().trim();
            tvPrice.setText(Constants.RUPES + " " + Utils.convertCommaIntoNumber(price,"##,##,###"));
        /*tvMake.setText(searchCarDetails.getE);*/
    }

}
