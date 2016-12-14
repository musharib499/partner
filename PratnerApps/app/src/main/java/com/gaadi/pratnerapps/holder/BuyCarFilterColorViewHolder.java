package com.gaadi.pratnerapps.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gaadi.pratnerapps.R;


/**
 * Created by Mushareb Ali on 8/4/16.
 */
public class BuyCarFilterColorViewHolder extends RecyclerView.ViewHolder {
    public View view_color, view_check;

    public BuyCarFilterColorViewHolder(View itemView) {
        super(itemView);
        view_check = itemView.findViewById(R.id.ivCheck);
        view_color = itemView.findViewById(R.id.view_color);


    }
}
