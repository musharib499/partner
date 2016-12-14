package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by vinodtakhar on 30/5/16.
 */
public class DealerRelativeLayout extends RelativeLayout{
    public DealerRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerRelativeLayout);

        String background = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerRelativeLayout_background_color));

        if(!TextUtils.isEmpty(background))
            setBackgroundColor(Color.parseColor(background));
    }
}
