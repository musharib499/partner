package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.utils.Utils;

/**
 * Created by ashishkumar on 1/8/16.
 */
public class DealerAutoCompleteTextView extends AutoCompleteTextView {
    public DealerAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }
    public void setTheme(Context context,AttributeSet attrs){
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerAutoCompleteTextView);

        String hintColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerAutoCompleteTextView_hint_color));
        String textColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerAutoCompleteTextView_text_color));
        String tintColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerAutoCompleteTextView_tint_color));
        String bgColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerAutoCompleteTextView_background_color));
        if(!TextUtils.isEmpty(hintColor)){
            setHintTextColor(Color.parseColor(hintColor));

        }
        if (!TextUtils.isEmpty(textColor)){
            setTextColor(Color.parseColor(textColor));
        }
        if (!TextUtils.isEmpty(bgColor)){
            setBackgroundColor(Color.parseColor(bgColor));
        }

        if (!TextUtils.isEmpty(tintColor) ) {
            Utils.tintWidget(getCompoundDrawables(),Color.parseColor(tintColor));
        }




    }

}
