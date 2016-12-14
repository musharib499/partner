package com.gaadi.pratnerapps.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.holder.BuyCarFilterColorViewHolder;
import com.gaadi.pratnerapps.models.BuyCarFilterModel;
import com.gaadi.pratnerapps.models.ParameterApiModel;

import java.util.ArrayList;

/**
 * @author Mushareb Ali
 * @version 1.0
 * @since 19/8/16
 */


public class BuyCarColorAdapter extends RecyclerView.Adapter<BuyCarFilterColorViewHolder> {
    private ArrayList<ParameterApiModel.KeyValueModel> bodyList;
    private Context mContext;
    private BuyCarFilterModel buyCarFilterModel;
    public BuyCarColorAdapter(Context context, ArrayList<ParameterApiModel.KeyValueModel> keyValueModels, BuyCarFilterModel buyCarFilter) {
        bodyList=keyValueModels;
        mContext=context;
        buyCarFilterModel=buyCarFilter;


    }

    @Override
    public BuyCarFilterColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select_color_type, parent, false);

        return new BuyCarFilterColorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BuyCarFilterColorViewHolder holder, final int position) {
        holder.view_color.setBackgroundColor(Color.parseColor("#"+bodyList.get(position).getValue()));
        if (buyCarFilterModel.getColors().contains(bodyList.get(position).getKey()))
            holder.view_check.setVisibility(View.VISIBLE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buyCarFilterModel.getColors().contains(bodyList.get(position).getKey())) {
                    buyCarFilterModel.getColors().remove(bodyList.get(position).getKey());
                    holder.view_check.setVisibility(View.GONE);
                } else {
                    buyCarFilterModel.getColors().add(bodyList.get(position).getKey());
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
