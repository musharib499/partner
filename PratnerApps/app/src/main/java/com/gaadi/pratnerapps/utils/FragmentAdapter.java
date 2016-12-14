package com.gaadi.pratnerapps.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by vinodtakhar on 6/6/16.
 */
public interface FragmentAdapter{
    void replaceFragment(Fragment fragment, boolean showAppBar);
    void addToBackStack(Fragment fragment, boolean showAppBar);
    void popFragment(boolean showAppBar);
    void showHome();
    void setTitleMessage(String message,boolean showAppBar);
    void setAppBarImage(String url,boolean showSubTitle);
    void setAppBarImages(ArrayList<String> images);
    void setAppBarImages(ArrayList<String> images,boolean onClick);
    View setFabMenu(Context context);

}
