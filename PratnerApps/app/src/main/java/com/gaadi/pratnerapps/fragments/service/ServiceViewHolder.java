package com.gaadi.pratnerapps.fragments.service;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;

/**
 * Created by ashishkumar on 5/9/16.
 *
 */
public class ServiceViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivCheck, ivService;
    public TextView tvService;
    public String key;
    public String value;

    public ServiceViewHolder(View itemView) {
        super(itemView);
        ivCheck = (ImageView) itemView.findViewById(R.id.ivCheck);
        ivService = (ImageView)itemView.findViewById(R.id.ivService);
        tvService = (TextView)itemView.findViewById(R.id.tvService);
    }
}
