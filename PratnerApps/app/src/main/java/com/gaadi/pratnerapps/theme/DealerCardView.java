package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by vinodtakhar on 30/5/16.
 */
public class DealerCardView extends CardView{
    public DealerCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerCardView);

        String background = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerCardView_background_color));

        if(!TextUtils.isEmpty(background))
            setCardBackgroundColor(Color.parseColor(background));
        //setRadius(30);
    }
}
