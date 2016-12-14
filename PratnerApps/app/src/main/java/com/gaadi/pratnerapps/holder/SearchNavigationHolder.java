package com.gaadi.pratnerapps.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.gaadi.pratnerapps.R;


/**
 * Created by mushareb on 4/4/16.
 */
public class SearchNavigationHolder extends RecyclerView.ViewHolder {
    public CheckBox cb_item;


    public SearchNavigationHolder(View itemView) {
        super(itemView);
        cb_item = (CheckBox) itemView.findViewById(R.id.cb_item);


    }
}

