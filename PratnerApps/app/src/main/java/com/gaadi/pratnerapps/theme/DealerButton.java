package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Button;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by vinodtakhar on 10/6/16.
 */
public class DealerButton extends Button{
    public DealerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerButton);

        String background = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerButton_background_color));
        String foreground = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerButton_text_color));

        if(!TextUtils.isEmpty(background))
            setBackgroundColor(Color.parseColor(background));

        if(!TextUtils.isEmpty(foreground)) {
            setTextColor(Color.parseColor(foreground));

            if(getCompoundDrawables()[0]!=null)
                tintDrawable(getCompoundDrawables()[0],Color.parseColor(foreground));

            if(getCompoundDrawables()[1]!=null)
                tintDrawable(getCompoundDrawables()[1],Color.parseColor(foreground));

            if(getCompoundDrawables()[2]!=null)
                tintDrawable(getCompoundDrawables()[2],Color.parseColor(foreground));

            if(getCompoundDrawables()[3]!=null)
                tintDrawable(getCompoundDrawables()[3],Color.parseColor(foreground));
        }
    }

    private void tintDrawable(Drawable drawable,int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final Drawable wrapped = DrawableCompat.wrap(drawable);
            drawable.mutate();
            DrawableCompat.setTint(wrapped, color);
        } else {
            drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
    }
}
