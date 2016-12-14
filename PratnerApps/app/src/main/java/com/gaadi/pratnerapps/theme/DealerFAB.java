package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by Mushareb Ali on 14/6/16.
 */
public class DealerFAB extends FloatingActionButton {
    public DealerFAB(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context, attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication) context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerFAB);

        String background = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerFAB_background_color));
        String tint = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerFAB_tint_color));

        if (!TextUtils.isEmpty(background))
            setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(background)));

        if (!TextUtils.isEmpty(tint)) {
            //getDrawable().mutate().setColorFilter(ContextCompat.getColor(context, Color.parseColor(tintColor)), PorterDuff.Mode.SRC_IN);
            //DrawableCompat.setTint(getDrawable(), Color.parseColor(tintColor));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                final Drawable wrapped = DrawableCompat.wrap(getDrawable());
                getDrawable().mutate();
                DrawableCompat.setTint(wrapped, Color.parseColor(tint));

            } else {
                getDrawable().mutate().setColorFilter(Color.parseColor(tint), PorterDuff.Mode.SRC_IN);
            }

            getDrawable().invalidateSelf();
        }



    }


}
