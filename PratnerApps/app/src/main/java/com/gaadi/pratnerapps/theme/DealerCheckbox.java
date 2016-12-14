package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by vinodtakhar on 10/6/16.
 */
public class DealerCheckbox extends AppCompatCheckBox {
    public DealerCheckbox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerCheckbox);

        String tintColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerCheckbox_tint_color));
        String un_select_tintColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerCheckbox_un_select_tint_color));
        String foreground = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerCheckbox_text_color));


        if (!TextUtils.isEmpty(tintColor)) {
            int[][] states = new int[][]{new int[]{android.R.attr.state_checked}, new int[]{-android.R.attr.state_checked}};
            int[] colors = new int[]{Color.parseColor(tintColor), Color.parseColor(un_select_tintColor)};
            setSupportButtonTintList(new ColorStateList(states, colors));

        }

        if (!TextUtils.isEmpty(foreground))
            setTextColor(Color.parseColor(foreground));

    }
}


