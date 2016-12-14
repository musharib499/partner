package com.gaadi.pratnerapps.menus;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.MenuApiModel;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;

/**
 * Created by priyarawat on 4/7/16.
 */
public class HorizontalMenuAdapter extends MenusBaseAdapter{
    Context context;
    String accentColor;
    String selectedTextColor;
    String normalTextColor;
    public HorizontalMenuAdapter(Context context, ArrayList<MenuApiModel.MenuModel> menuItems) {
        super(context,menuItems);
        this.context = context;
        accentColor = ((PartnerApplication)context.getApplicationContext()).getThemeProperty("accent_color");
        selectedTextColor = ((PartnerApplication)context.getApplicationContext()).getThemeProperty("title_color_icon_color");
        normalTextColor = ((PartnerApplication)context.getApplicationContext()).getThemeProperty("icon_color");

        selectedPosition = 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_menus_row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if(selectedPosition == position) {
            holder.itemView.setBackgroundColor(Color.parseColor(accentColor));
            Utils.setTint(holder.tvIcon, context, selectedTextColor);
            holder.tvTitle.setTextColor(Color.parseColor(selectedTextColor));
        }
        else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            Utils.setTint(holder.tvIcon, context, normalTextColor);
            holder.tvTitle.setTextColor(Color.parseColor(normalTextColor));
        }

    }
}
