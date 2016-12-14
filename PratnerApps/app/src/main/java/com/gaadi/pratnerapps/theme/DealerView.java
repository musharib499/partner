package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by vinodtakhar on 9/6/16.
 */
public class DealerView extends View {
    private static final String TAG = DealerView.class.getName();

    public DealerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerView);

        String backgroundColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerView_background_color));


        if(!TextUtils.isEmpty(backgroundColor))
            setBackgroundColor(Color.parseColor(backgroundColor));
    }
}
