package com.gaadi.pratnerapps.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;


/**
 * Created by Mushareb Ali on 11/4/16.
 */
public class BuyCarBodyTypeViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_view;
    public ImageView im_view;
    public ImageView view_check;


    public BuyCarBodyTypeViewHolder(View itemView) {
        super(itemView);

        tv_view = (TextView) itemView.findViewById(R.id.tv_name);
        im_view = (ImageView) itemView.findViewById(R.id.im_view);
        view_check = (ImageView) itemView.findViewById(R.id.ivCheck);
    }
}
