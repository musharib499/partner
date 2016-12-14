package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;

/**
 * Created by vinodtakhar on 9/6/16.
 */
public class DealerMaterialACTV extends MaterialAutoCompleteTextView {
    public DealerMaterialACTV(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerMaterialACTV);

        String hintColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerMaterialACTV_hint_color));
        String textColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerMaterialACTV_text_color));
        String underlineColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerMaterialACTV_underline_color));

        if(!TextUtils.isEmpty(hintColor))
            setHintTextColor(Color.parseColor(hintColor));

        if(!TextUtils.isEmpty(textColor))
            setTextColor(Color.parseColor(textColor));

        if(!TextUtils.isEmpty(underlineColor))
            setUnderlineColor(Color.parseColor(underlineColor));
    }
}
