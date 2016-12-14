package com.gaadi.pratnerapps.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;


/**
 * Created by Mushareb Ali on 15/4/16.
 */
public class BuyCarFilterPriceRangeViewHolder extends RecyclerView.ViewHolder {
    public CheckBox checkBox;
    public TextView tvPrice;


    public BuyCarFilterPriceRangeViewHolder(View view) {
        super(view);


        checkBox = (CheckBox) view.findViewById(R.id.cbItem);
        tvPrice = (TextView) view.findViewById(R.id.tvPrice);

    }
}
