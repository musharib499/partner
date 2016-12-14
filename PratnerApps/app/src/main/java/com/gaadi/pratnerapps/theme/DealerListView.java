package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ListView;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by root on 17/6/16.
 */
public class DealerListView extends ListView {
    public DealerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication) context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerNavigationView);

        String backgroundColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerListView_background_color));
        String textColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerListView_text_color));
        String selectColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerListView_tint_color));
       // String selectedBackgroundColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerListView_selected_background));

        if (!TextUtils.isEmpty(backgroundColor)) {
            setBackgroundColor(Color.parseColor(backgroundColor));

            /*ColorStateList colorStateList = new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_checked}, // disabled
                            new int[]{-android.R.attr.state_checked}, // unchecked
                    },
                    new int[]{
                            Color.parseColor(tintColor),
                            Color.parseColor(backgroundColor),
                    }
            );*/


        }
        if (!TextUtils.isEmpty(selectColor))
            setSelector(new ColorDrawable(Color.parseColor(selectColor)));

//
//        if(backgroundColor!=null){
//            setIte(new ColorDrawable(Color.parseColor(backgroundColor)));
//        }
//
//        if (textColor != null && tintColor != null) {
//            ColorStateList colorStateList = new ColorStateList(
//                    new int[][]{
//                            new int[]{android.R.attr.state_checked}, // disabled
//                            new int[]{-android.R.attr.state_checked}, // unchecked
//                    },
//                    new int[]{
//                            Color.parseColor(tintColor),
//                            Color.parseColor(textColor),
//                    }
//            );
//
//            set(colorStateList);//ColorStateList.valueOf(Color.parseColor(textColor)));
//
//            setItemIconTintList(colorStateList);//ColorStateList.valueOf(Color.parseColor(tintColor)));
//        }
    }
}
