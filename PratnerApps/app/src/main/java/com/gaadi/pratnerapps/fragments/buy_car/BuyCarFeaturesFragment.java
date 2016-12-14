package com.gaadi.pratnerapps.fragments.buy_car;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.BaseRecyclerAdapter;
import com.gaadi.pratnerapps.holder.BuyCarFeaturesViewHolder;
import com.gaadi.pratnerapps.utils.CommonUtils;
import com.gaadi.pratnerapps.utils.Constants;

import java.util.ArrayList;

/**
 * Created by kanishroshan on 30/3/16.
 */

public class BuyCarFeaturesFragment extends Fragment implements BaseRecyclerAdapter.BindAdapterListener<BuyCarFeaturesViewHolder> {

    private ArrayList<String> arrayList = new ArrayList<>();

    public static BuyCarFeaturesFragment newInstance(ArrayList<String> carDetails) {
        BuyCarFeaturesFragment fragment = new BuyCarFeaturesFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(Constants.VEHICLE_ID, carDetails);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            arrayList = getArguments().getStringArrayList(Constants.VEHICLE_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fargment_buy_car_features, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_list);
        BaseRecyclerAdapter<String, BuyCarFeaturesViewHolder> adapter = new BaseRecyclerAdapter<>(getActivity(), arrayList, this, BuyCarFeaturesViewHolder.class, R.layout.item_buy_car_features);
        CommonUtils.recyclerView(recyclerView, getActivity(), true);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onBind(BuyCarFeaturesViewHolder holder, int position) {

        holder.tvFeature.setText(arrayList.get(position).toString());



    }


}
