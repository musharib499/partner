package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by ashishkumar on 29/7/16.
 */
public class DealerTextInputLayout extends TextInputLayout {
    public DealerTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }
    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication) context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerTextView);

        String background = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerTextView_background_color));
      /*  String text_hint_color = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerTextInputLayout_hint_color));
        String text_color_accent = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerTextInputLayout_text_color_accent));
        String text_color_activated = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerTextInputLayout_text_color_control_activated));
        String text_color_normal = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerTextInputLayout_text_color_control_normal));
*/        if (!TextUtils.isEmpty(background))
            setBackgroundColor(Color.parseColor(background));







      /*  if (text_hint_color != null)
            setH(Color.parseColor(text_hint_color));*/
    }
}