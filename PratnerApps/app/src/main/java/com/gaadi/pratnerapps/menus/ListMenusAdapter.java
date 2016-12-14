package com.gaadi.pratnerapps.menus;

import android.content.Context;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.MenuApiModel;

import java.util.ArrayList;

/**
 * Created by priyarawat on 15/7/16.
 */
public class ListMenusAdapter extends MatrixMenusAdapter {
    public ListMenusAdapter(Context context, ArrayList<MenuApiModel.MenuModel> arrayList, int no_of_columns, int spacingInPixels) {
        super(context, arrayList, no_of_columns, spacingInPixels);
    }

    @Override
    public int getLayoutId() {
        return R.layout.list_menus_row_layout;
    }
}
