package com.gaadi.pratnerapps.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.gaadi.pratnerapps.R;

/**
 * Created by musharib on 29/7/16.
 */
public class BannerLogo {

    public BannerLogo() {
    }

    public static View  imageView(Context context)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_girnarsoft_logo,null);
        return  view;
    }

}
