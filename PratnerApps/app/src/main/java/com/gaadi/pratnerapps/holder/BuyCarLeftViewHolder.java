package com.gaadi.pratnerapps.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;


/**
 * Created by Mushareb Ali on 7/4/16.
 */
public class BuyCarLeftViewHolder extends RecyclerView.ViewHolder {

    public final TextView tv_type;
    public RelativeLayout parant_layout;


    public BuyCarLeftViewHolder(View view) {
        super(view);

        parant_layout = (RelativeLayout) view.findViewById(R.id.parent_layout);
        tv_type = (TextView) view.findViewById(R.id.tv_type);

    }

}