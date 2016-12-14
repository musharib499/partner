package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.utils.CustomMaterialAutoCompleteTxtVw;

/**
 * Created by vinodtakhar on 9/6/16.
 */
public class DealerCustomMaterialACTV extends CustomMaterialAutoCompleteTxtVw {
    public DealerCustomMaterialACTV(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerCustomMaterialACTV);

        String hintColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerCustomMaterialACTV_hint_color));
        String textColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerCustomMaterialACTV_text_color));
        String tintColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerCustomMaterialACTV_tint_color));
        String underlineColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerCustomMaterialACTV_underline_color));
        String bgColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerCustomMaterialACTV_background_color));

        if(!TextUtils.isEmpty(hintColor))
            setHintTextColor(Color.parseColor(hintColor));

        if(!TextUtils.isEmpty(textColor))
            setTextColor(Color.parseColor(textColor));

//        if(tintColor!=null)
//            setBackgroundColor(Color.parseColor(tintColor));
/*
        if(!TextUtils.isEmpty(bgColor))
            setBackgroundColor(Color.parseColor(bgColor));*/
    }
}
