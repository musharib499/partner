package com.gaadi.pratnerapps.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;

/**
 * Created by Mushareb Ali on 15/6/16.
 */
public class BuyCarFeaturesViewHolder extends RecyclerView.ViewHolder {

    public TextView tvFeature;
    public ImageView ivCheck;

    public BuyCarFeaturesViewHolder(View itemView) {
        super(itemView);

        tvFeature = (TextView) itemView.findViewById(R.id.tvFeature);
        ivCheck = (ImageView) itemView.findViewById(R.id.ivCheck);
    }
}
