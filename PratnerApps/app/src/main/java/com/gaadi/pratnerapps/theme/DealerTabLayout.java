package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by vinodtakhar on 30/5/16.
 */
public class DealerTabLayout extends TabLayout{
    public DealerTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerTabLayout);

        String backgroundColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerTabLayout_background_color));
        String colorSelected = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerTabLayout_text_color_selected));
        String colorUnselected = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerTabLayout_text_color_unselected));
        String tintColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerTabLayout_tint_color));
        String underlineColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerTabLayout_underline_color));

        if(!TextUtils.isEmpty(backgroundColor))
            setBackgroundColor(Color.parseColor(backgroundColor));

        if(!TextUtils.isEmpty(colorSelected) && !TextUtils.isEmpty(colorUnselected))
            setTabTextColors(Color.parseColor(colorUnselected),Color.parseColor(colorSelected));

        if(!TextUtils.isEmpty(underlineColor))
            setSelectedTabIndicatorColor(Color.parseColor(underlineColor));
    }
}
