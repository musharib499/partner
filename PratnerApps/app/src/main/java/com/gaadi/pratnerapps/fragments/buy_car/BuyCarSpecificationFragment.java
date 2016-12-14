package com.gaadi.pratnerapps.fragments.buy_car;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.BuyCarDetails;
import com.gaadi.pratnerapps.utils.Constants;


/**
 * Created by kanishroshan on 30/3/16.
 */
public class BuyCarSpecificationFragment extends Fragment {

    private BuyCarDetails carDetails;

    public static BuyCarSpecificationFragment newInstance(BuyCarDetails carDetails) {
        BuyCarSpecificationFragment fragment = new BuyCarSpecificationFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.VEHICLE_ID, carDetails);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            carDetails = (BuyCarDetails) getArguments().getSerializable(Constants.VEHICLE_ID);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specifications, container, false);
        return view;
    }
}
