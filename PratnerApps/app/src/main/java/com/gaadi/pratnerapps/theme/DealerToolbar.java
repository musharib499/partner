package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by vinodtakhar on 30/5/16.
 */
public class DealerToolbar extends Toolbar{
    public DealerToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerToolbar);
        String backgroundColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerToolbar_background_color));
        String textColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerToolbar_text_color));
        String tintColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerToolbar_tint_color));
        if(!TextUtils.isEmpty(backgroundColor)) {
            setBackgroundColor(Color.parseColor(backgroundColor));
        }

        if(!TextUtils.isEmpty(textColor))
            setTitleTextColor(Color.parseColor(textColor));

    }
}
