package com.gaadi.pratnerapps.menus;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.MenuApiModel;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;

/**
 * Created by priyarawat on 4/7/16.
 */
public class BottomMenusAdapter extends MenusBaseAdapter implements MenuWidget.IChangeIcon {
    static final int NUM_OF_ITEMS = 4;
    Context context;
    int screenWidth;
    int menuItemsSize;
    String accentColor;
    String selectedTextColor;
    String normalTextColor;
    public BottomMenusAdapter(Context context, ArrayList<MenuApiModel.MenuModel> menuItems) {
        super(context,menuItems);
        this.context = context;
        screenWidth = calculateScreenWidth();
        menuItemsSize = menuItems != null ? menuItems.size() : 0;
        accentColor = ((PartnerApplication)context.getApplicationContext()).getThemeProperty("accent_color");
        selectedTextColor = ((PartnerApplication)context.getApplicationContext()).getThemeProperty("title_color_icon_color");
        normalTextColor = ((PartnerApplication)context.getApplicationContext()).getThemeProperty("icon_color");

        selectedPosition = 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_menu_row_layout, parent, false);
        if(menuItemsSize > 0 && menuItemsSize <= NUM_OF_ITEMS)
            view.setLayoutParams(new RecyclerView.LayoutParams(screenWidth/menuItemsSize, (int)context.getResources().getDimension(R.dimen.dimen_60_dp)));
        else
        view.setLayoutParams(new RecyclerView.LayoutParams(screenWidth/NUM_OF_ITEMS, (int)context.getResources().getDimension(R.dimen.dimen_60_dp)));
        return new ViewHolder(view);
    }

    @Override
    public boolean hasMoreOptions(){
        return menuItemsSize > BottomMenusAdapter.NUM_OF_ITEMS;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(hasMoreOptions() && position == BottomMenusAdapter.NUM_OF_ITEMS-1)
        {
            holder.tvTitle.setText("More");
            if(isClicked) {
                holder.tvIcon.setImageResource(R.drawable.ic_close);
                Utils.setTint(holder.tvIcon,context);
            }
            else {
                holder.tvIcon.setImageResource(R.drawable.ic_menu_more);
                Utils.setTint(holder.tvIcon,context);
            }
        }
        else {
            super.onBindViewHolder(holder, position);
        }

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

    @Override
    public int getItemCount() {
        if(menuItemsSize > BottomMenusAdapter.NUM_OF_ITEMS)
        {
            return BottomMenusAdapter.NUM_OF_ITEMS;
        }
        else
            return menuItemsSize;
    }

    @Override
    public boolean isListOrMatrixMenu() {
        return false;
    }

    @Override
    public void changeIcon() {
        isClicked = false;
        notifyDataSetChanged();
    }
}
