package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by root on 4/8/16.
 */
public class DealerRadioButton extends AppCompatRadioButton {

    public DealerRadioButton(Context context, AttributeSet attrs) {

        super(context, attrs);
        setTheme(context, attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication) context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerRadioButton);

        String background = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerRadioButton_background_color));
        String foreground = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerRadioButton_text_color));
        String selectButtonTint = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerRadioButton_select_tint_color));
        String unSelectButtonTint = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerRadioButton_un_select_tint_color));

     /*   if (!TextUtils.isEmpty(background))
            setBackgroundColor(Color.parseColor(background));*/

            if (!TextUtils.isEmpty(selectButtonTint)) {
                ColorStateList colorStateList = new ColorStateList(
                        new int[][]{
                                new int[]{android.R.attr.state_checked}
                                , new int[]{-android.R.attr.state_checked}
                        },
                        new int[]{
                                Color.parseColor(selectButtonTint)
                                , Color.parseColor(unSelectButtonTint),
                        }
                );

                if (Build.VERSION.SDK_INT >=21 )
                    setButtonTintList(colorStateList);
                else
                    setSupportButtonTintList(colorStateList);


            }
        if (!TextUtils.isEmpty(foreground)) {
            setTextColor(Color.parseColor(foreground));
        }

    }
}
