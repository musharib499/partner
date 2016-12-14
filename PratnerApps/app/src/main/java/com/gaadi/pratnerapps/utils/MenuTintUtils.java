package com.gaadi.pratnerapps.utils;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by vinodtakhar on 14/6/16.
 */
public class MenuTintUtils {

    private static final String TAG = MenuTintUtils.class.getName();

    public static void tintAllIcons(PartnerApplication partnerApplication, Menu menu) {

        if (partnerApplication.getThemeProperty("primary_color") == null ||
                partnerApplication.getThemeProperty("title_color_icon_color") == null) return;

        int backgroundColor = Color.parseColor(partnerApplication.getThemeProperty("title_color_icon_color"));

        int textColor = Color.parseColor(partnerApplication.getThemeProperty("title_color_icon_color"));

        for (int i = 0; i < menu.size(); ++i) {
            final MenuItem item = menu.getItem(i);
            tintMenuItemIcon(backgroundColor,textColor, item);
            tintShareIconIfPresent(backgroundColor,textColor, item);
        }
    }

    private static void tintMenuItemIcon(int backgroundColor,int textColor, MenuItem item) {

        SpannableString spanString = new SpannableString(item.getTitle().toString());
        spanString.setSpan(new ForegroundColorSpan(textColor), 0, spanString.length(), 0);
        item.setTitle(spanString);

        final Drawable drawable = item.getIcon();
        if (drawable != null) {
            Logger.e(TAG,"Setting menu tint:"+spanString.toString());
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
                final Drawable wrapped = DrawableCompat.wrap(drawable);
                drawable.mutate();
                DrawableCompat.setTint(wrapped, textColor);
                drawable.invalidateSelf();
            }else{
                drawable.mutate().setColorFilter(textColor, PorterDuff.Mode.SRC_IN);
                drawable.invalidateSelf();
            }
            item.setIcon(drawable);
        }
    }

    private static void tintShareIconIfPresent(int color,int textColor, MenuItem item) {
        if (item.getActionView() != null) {
            final View actionView = item.getActionView();
            final View expandActivitiesButton = actionView.findViewById(R.id.expand_activities_button);
            if (expandActivitiesButton != null) {
                final ImageView image = (ImageView) expandActivitiesButton.findViewById(R.id.image);
                if (image != null) {
                    final Drawable drawable = image.getDrawable();

                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
                        final Drawable wrapped = DrawableCompat.wrap(drawable);
                        drawable.mutate();
                        DrawableCompat.setTint(wrapped, color);
                    }else{
                        drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
                    }
                    image.setImageDrawable(drawable);
                }
            }
        }
    }
}
