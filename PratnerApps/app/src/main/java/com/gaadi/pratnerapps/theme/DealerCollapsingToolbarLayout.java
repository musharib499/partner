package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by priyarawat on 14/6/16.
 */
public class DealerCollapsingToolbarLayout extends CollapsingToolbarLayout{

    public DealerCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context, attrs);
    }

    private void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerCollapsingToolbarLayout);
        String backgroundColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerCollapsingToolbarLayout_background_color));
        String content_scrim_color = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerCollapsingToolbarLayout_content_scrim));

        if(!TextUtils.isEmpty(backgroundColor)) {
            setBackgroundColor(Color.parseColor(backgroundColor));
        }
        if (!TextUtils.isEmpty(content_scrim_color)){
            setContentScrimColor(Color.parseColor(content_scrim_color));
        }
    }
}
