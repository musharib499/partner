package com.gaadi.pratnerapps.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.BaseRecyclerAdapter;
import com.gaadi.pratnerapps.fragments.buy_car.BuyCarDetailsFragment;
import com.gaadi.pratnerapps.holder.RecommendedVehicleViewHolder;
import com.gaadi.pratnerapps.models.BuyCarDetails;
import com.gaadi.pratnerapps.utils.ActivityTransitionAnimation;
import com.gaadi.pratnerapps.utils.CommonUtils;
import com.gaadi.pratnerapps.utils.Constants;
import com.gaadi.pratnerapps.utils.Utils;

/**
 * Created by lakshaygirdhar on 1/4/16.
 */
public class RecommendedVehicleFragment extends Fragment implements BaseRecyclerAdapter.BindAdapterListener<RecommendedVehicleViewHolder> {

    private Context mContext;
    private RecyclerView recyclerView;
    private BuyCarDetails searchCarDetails;


    public static RecommendedVehicleFragment newInstance(BuyCarDetails carDetails) {
        RecommendedVehicleFragment fragment = new RecommendedVehicleFragment();
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
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recommended_cars, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_recomended_cars);
      /*  for (int i = 0; i < 10; i++) {
            BuyCarDetails carsDetails = new BuyCarDetails("WAGON R( " + i + " )", "cannaughtPlace,Delhi", "" + i + "20000");
            carlist.add(carsDetails);
        }*/

        BaseRecyclerAdapter<String, RecommendedVehicleViewHolder> serchNavigation = new BaseRecyclerAdapter<>(getActivity(), searchCarDetails.getImages(), this, RecommendedVehicleViewHolder.class, R.layout.item_recycler_recommendedcars);
        CommonUtils.recyclerView(recyclerView, getActivity(), false).setAdapter(serchNavigation);

        return view;
    }


    @Override
    public void onBind(final RecommendedVehicleViewHolder holder, int position) {


        Utils.loadImage(getActivity(), searchCarDetails.getImages().get(position), holder.imgCar);
        holder.tvMake.setText(searchCarDetails.getMake());
        holder.tvAddress.setText(searchCarDetails.getCity());
        holder.tvPrice.setText(searchCarDetails.getPrice());
        holder.imgCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityTransitionAnimation.startActivityWithSharedView(getActivity(), holder.imgCar, "car", BuyCarDetailsFragment.class, null);
            }
        });
    }
}
