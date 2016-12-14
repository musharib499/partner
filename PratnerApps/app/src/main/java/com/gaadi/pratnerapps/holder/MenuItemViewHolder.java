package com.gaadi.pratnerapps.holder;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;

/**
 * Created by root on 13/7/16.
 */
public class MenuItemViewHolder extends RecyclerView.ViewHolder {

    public TextView tvItem;
    public FloatingActionButton imItem;
    public LinearLayout layoutMenu;



    public MenuItemViewHolder(View itemView) {
        super(itemView);

        tvItem = (TextView) itemView.findViewById(R.id.tvItem);
        imItem = (FloatingActionButton) itemView.findViewById(R.id.imItem);

    }
}
