package com.gaadi.pratnerapps.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;


/**
 * Created by mushareb on 4/4/16.
 */
public class BuyCarFilterMakeHolder extends RecyclerView.ViewHolder {
    public TextView tv_make, tv_model;
    View view;


    public BuyCarFilterMakeHolder(View itemView) {
        super(itemView);
        tv_make = (TextView) itemView.findViewById(R.id.tv_make);
        tv_model = (TextView) itemView.findViewById(R.id.tv_model);


    }
}

