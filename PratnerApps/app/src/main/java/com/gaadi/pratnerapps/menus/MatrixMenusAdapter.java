package com.gaadi.pratnerapps.menus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.MenuApiModel;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;

/**
 * Created by priyarawat on 14/7/16.
 */
public class MatrixMenusAdapter extends MenusBaseAdapter implements MenuWidget.IChangeIcon{
    int menuItemsSize;
    int no_of_columns;
    int screenWidth;
    int spacingInPixels;
    Context context;
    int rowWidth;
    public MatrixMenusAdapter(Context context, ArrayList<MenuApiModel.MenuModel> arrayList, int no_of_columns, int spacingInPixels) {
        super(context, arrayList);
        menuItemsSize = arrayList != null ? arrayList.size() : 0;
        this.no_of_columns = no_of_columns;
        screenWidth = calculateScreenWidth();
        this.spacingInPixels = no_of_columns * spacingInPixels;
        this.context = context;
        rowWidth = screenWidth/no_of_columns - spacingInPixels;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(rowWidth, no_of_columns >=3 ? rowWidth : (int) context.getResources().getDimension(R.dimen.dimen_60_dp)));
        return new ViewHolder(view);}

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(!(menuItemsSize % no_of_columns == 0) && position == menuItemsSize - menuItemsSize%no_of_columns - 1) {
            holder.tvTitle.setText("More");

            if(isClicked)
                holder.tvIcon.setImageResource(R.drawable.ic_close);
            else
                holder.tvIcon.setImageResource(R.drawable.ic_menu_more);

            Utils.setTint(holder.tvIcon,context);
        }
        else
            super.onBindViewHolder(holder, position);
    }
    public int getLayoutId()
    {
        return R.layout.grid_menu_row_layout;
    }

    @Override
    public int getItemCount() {
        if(menuItemsSize % no_of_columns == 0)
            return menuItemsSize;
        else
        return menuItemsSize - (menuItemsSize % no_of_columns);

    }

    @Override
    public boolean hasMoreOptions() {
        return menuItemsSize > no_of_columns && menuItemsSize % no_of_columns != 0;
    }

    @Override
    public int getIndexForMoreList() {
        if(menuItemsSize>0) {
            if (menuItemsSize < no_of_columns) {
                return menuItemsSize - 1;
            } else {
                return menuItemsSize - (menuItemsSize % no_of_columns) - 1;
            }
        }else{
            return 0;
        }
    }

    @Override
    public boolean isListOrMatrixMenu() {
        return true;
    }

    @Override
    public void changeIcon() {
        isClicked = false;
        notifyDataSetChanged();
    }
}
