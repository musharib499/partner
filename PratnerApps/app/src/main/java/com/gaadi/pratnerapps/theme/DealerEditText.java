package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.utils.Utils;

/**
 * Created by Mushareb Ali on 10/6/16.
 */
public class DealerEditText extends EditText {
    public DealerEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context, attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication) context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerTextView);

        String background = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerEditView_background_color));
        String foreground = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerEditView_text_color));
        String tintColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerEditView_tint_color));
        String hintTextColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerEditView_hint_color));

        if (!TextUtils.isEmpty(background))
            setBackgroundColor(Color.parseColor(background));

        if (!TextUtils.isEmpty(foreground))
            setTextColor(Color.parseColor(foreground));

        if (!TextUtils.isEmpty(hintTextColor))
            setHintTextColor(Color.parseColor(hintTextColor));

        if (!TextUtils.isEmpty(tintColor) ) {
            Utils.tintWidget(getCompoundDrawables(),Color.parseColor(tintColor));
        }
    }
}
