package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.NavigationView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by vinodtakhar on 14/6/16.
 */
public class DealerNavigationView extends NavigationView {
    public DealerNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication) context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerNavigationView);

        String backgroundColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerNavigationView_background_color));
        String textColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerNavigationView_text_color));
        String tintColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerNavigationView_tint_color));
        String selectedBackgroundColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerNavigationView_selected_background));

        if (!TextUtils.isEmpty(backgroundColor)) {
            setBackgroundColor(Color.parseColor(backgroundColor));
        }


        if(!TextUtils.isEmpty(backgroundColor)){
            setItemBackground(new ColorDrawable(Color.parseColor(backgroundColor)));
        }

        if (!TextUtils.isEmpty(textColor) && !TextUtils.isEmpty(tintColor)) {
            ColorStateList colorStateList = new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_checked}, // disabled
                            new int[]{-android.R.attr.state_checked}, // unchecked
                    },
                    new int[]{
                            Color.parseColor(tintColor),
                            Color.parseColor(textColor),
                    }
            );

            setItemTextColor(colorStateList);//ColorStateList.valueOf(Color.parseColor(textColor)));

            setItemIconTintList(colorStateList);//ColorStateList.valueOf(Color.parseColor(tintColor)));
        }
    }
}
