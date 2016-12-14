package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.rengwuxian.materialedittext.MaterialTextView;

/**
 * Created by vinodtakhar on 9/6/16.
 */
public class DealerMaterialTextView extends MaterialTextView {
    public DealerMaterialTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerMaterialTextView);

        String hintColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerMaterialTextView_hint_color));
        String textColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerMaterialTextView_text_color));
        String underlineColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerMaterialTextView_underline_color));

        if(!TextUtils.isEmpty(hintColor))
            setHintTextColor(Color.parseColor(hintColor));

        if(!TextUtils.isEmpty(textColor))
            setTextColor(Color.parseColor(textColor));

        if(!TextUtils.isEmpty(underlineColor))
            setUnderlineColor(Color.parseColor(underlineColor));
    }
}
