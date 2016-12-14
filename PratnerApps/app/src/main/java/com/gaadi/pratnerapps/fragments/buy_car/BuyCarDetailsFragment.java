package com.gaadi.pratnerapps.fragments.buy_car;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.activities.HomeActivity;
import com.gaadi.pratnerapps.activities.RequestOtpActivity;
import com.gaadi.pratnerapps.fragments.PartnerBaseFragment;
import com.gaadi.pratnerapps.fragments.VehicleBasicDetailsFragment;
import com.gaadi.pratnerapps.models.BuyCarDetails;
import com.gaadi.pratnerapps.utils.AppPreferences;
import com.gaadi.pratnerapps.utils.Constants;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;

public class BuyCarDetailsFragment extends PartnerBaseFragment {
    private BuyCarDetails searchCarDetails;

    public static BuyCarDetailsFragment newInstance(BuyCarDetails carDetails) {
        BuyCarDetailsFragment fragment = new BuyCarDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.VEHICLE_ID, carDetails);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchCarDetails = (BuyCarDetails) getArguments().getSerializable(Constants.VEHICLE_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_detail_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((HomeActivity) getActivity()).floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchCarDetails.getMobile() != null)
                    Utils.callPhone(getActivity(), searchCarDetails.getMobile());
            }
        });
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.layoutRequestCall);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppPreferences.getBooleanSharedPreference(getContext(),AppPreferences.DEALER_VERIFIED,false)) {
                    getFragmentAdapter().addToBackStack(BuyCarRequestCallFragment.newInstance(searchCarDetails).setFragmentAdapter(getFragmentAdapter()), false);
                }else {
                    showToast("Kindly sign-in to request call.");
                    startActivity(new Intent(getActivity(), RequestOtpActivity.class));
                }
            }
        });

        if (searchCarDetails.getImages() != null && searchCarDetails.getImages().size() > 0)
            getFragmentAdapter().setAppBarImages(searchCarDetails.getImages());
        else {
            ArrayList<String> emptyImages = new ArrayList<>();
            emptyImages.add("defaultimage");
            getFragmentAdapter().setAppBarImages(emptyImages);
        }
        getFragmentAdapter().setTitleMessage(searchCarDetails.getMake() + " " + searchCarDetails.getModel(), true);
        // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flLocation, VehicleBasicDetailsFragment.newInstance(searchCarDetails)).commit();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flVehicleOverview, BuyCarOverviewFragment.newInstance(searchCarDetails)).commit();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flLocation, VehicleBasicDetailsFragment.newInstance(searchCarDetails)).commit();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flCondition, BuyCarConditionFragment.newInstance(searchCarDetails.getConditions())).commit();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flFeatures, BuyCarFeaturesFragment.newInstance(searchCarDetails.getFeatures())).commit();


    }

    @Override
    public void onPause() {
        super.onPause();
        ((HomeActivity) getActivity()).floatingActionButton.setVisibility(View.GONE);
        //((HomeActivity)getActivity()).collapsingToolbarLayout.setTitleEnabled(false);
        // ((HomeActivity)getContext()).setTitleMessage(searchCarDetails.getMake() +" "+searchCarDetails.getModel());
    }

  /*  public void addFrament(View view) {

        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flDescriptionCars, VehicleDescriptionFragment.newInstance(searchCarDetails.getDescription())).commit();

    }*/

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).floatingActionButton.setVisibility(View.VISIBLE);
    }
}
