package com.gaadi.pratnerapps.menus;

import android.content.Context;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.MenuApiModel;

/**
 * Created by priyarawat on 14/7/16.
 */
public class MenuCardWidget extends MenuWidget {
    public MenuCardWidget(Context context, MenuApiModel menuApiModel) {
        super(context, menuApiModel);
    }

    @Override
    public int getLayoutId() {
        return R.layout.menu_card_widget;
    }
}
