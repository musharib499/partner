package com.gaadi.pratnerapps.menus;

import android.content.Context;

import com.gaadi.pratnerapps.models.MenuApiModel;

import java.util.ArrayList;

/**
 * Created by mushareb on 22/9/16.
 */

public class FabMenuAdapter extends HorizontalMenuAdapter {
    public FabMenuAdapter(Context context, ArrayList<MenuApiModel.MenuModel> menuItems) {
        super(context, menuItems);
        selectedPosition = -1;
    }

    @Override
    public boolean isListOrMatrixMenu() {
        return true;
    }
}
