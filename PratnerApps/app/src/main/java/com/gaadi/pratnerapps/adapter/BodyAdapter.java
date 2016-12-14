package com.gaadi.pratnerapps.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.fragments.buy_car.BuyCarBodyTypeFragment;
import com.gaadi.pratnerapps.holder.BuyCarBodyTypeViewHolder;
import com.gaadi.pratnerapps.models.BuyCarFilterModel;
import com.gaadi.pratnerapps.models.ParameterApiModel;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;

/**
 * @author Mushareb Ali
 * @version 1.0
 * @since 19/8/16
 */


public class BodyAdapter extends RecyclerView.Adapter<BuyCarBodyTypeViewHolder> {
    private ArrayList<ParameterApiModel.KeyValueModel> bodyList;
    private Context mContext;
    private BuyCarFilterModel buyCarFilterModel;
    public BodyAdapter(Context context, ArrayList<ParameterApiModel.KeyValueModel> keyValueModels, BuyCarFilterModel buyCarFilter) {
        bodyList=keyValueModels;
        mContext=context;
        buyCarFilterModel=buyCarFilter;


    }

    @Override
    public BuyCarBodyTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_body_type, parent, false);

        return new BuyCarBodyTypeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BuyCarBodyTypeViewHolder holder, final int position) {
        holder.tv_view.setText(bodyList.get(position).getValue());

        holder.im_view.setImageResource(BuyCarBodyTypeFragment.getIcon(bodyList.get(position).getKey()));
        holder.view_check.setImageResource(BuyCarBodyTypeFragment.getIcon(bodyList.get(position).getKey()));
        Utils.setTint(holder.view_check,mContext,"accent_color");

        if (buyCarFilterModel.getBodyTypes().contains(bodyList.get(position).getKey())) {
            holder.view_check.setVisibility(View.VISIBLE);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buyCarFilterModel.getBodyTypes().contains(bodyList.get(position).getKey())) {
                    buyCarFilterModel.getBodyTypes().remove(bodyList.get(position).getKey());
                    holder.view_check.setVisibility(View.GONE);
                } else {
                    buyCarFilterModel.getBodyTypes().add(bodyList.get(position).getKey());
                    holder.view_check.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bodyList.size();
    }
}
