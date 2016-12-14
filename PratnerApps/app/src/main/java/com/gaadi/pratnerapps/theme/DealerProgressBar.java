package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by vinodtakhar on 16/6/16.
 */
public class DealerProgressBar extends ProgressBar {
    public DealerProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerProgressBar);

        String tintColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerProgressBar_tint_color));


        if(!TextUtils.isEmpty(tintColor)){
            getIndeterminateDrawable().setColorFilter(Color.parseColor(tintColor), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }
}
