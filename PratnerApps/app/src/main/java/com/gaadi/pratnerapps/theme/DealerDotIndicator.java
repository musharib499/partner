package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.matthewtamlin.sliding_intro_screen_library.DotIndicator;

/**
 * Created by vinodtakhar on 27/6/16.
 */
public class DealerDotIndicator extends DotIndicator{
    public DealerDotIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerDotIndicator);

        String tintColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerDotIndicator_tint_color));

        if (!TextUtils.isEmpty(tintColor)) {
            setSelectedDotColor(Color.parseColor(tintColor));
            setUnselectedDotColor(Color.parseColor(tintColor));
        }

    }
}
