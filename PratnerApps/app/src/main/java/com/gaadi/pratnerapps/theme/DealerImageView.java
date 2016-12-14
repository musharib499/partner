package com.gaadi.pratnerapps.theme;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by vinodtakhar on 30/5/16.
 */
public class DealerImageView extends ImageView{
    private String tintColor;

    public DealerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTheme(context,attrs);
    }

    public void setTheme(Context context, AttributeSet attrs) {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DealerImageView);

        tintColor = partnerApplication.getThemeProperty(typedArray.getString(R.styleable.DealerImageView_tint_color));

        setTint();
    }

    private void setTint(){
        if(!TextUtils.isEmpty(tintColor) && this.getDrawable()!=null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                final Drawable wrapped = DrawableCompat.wrap(this.getDrawable());
                this.getDrawable().mutate();
                DrawableCompat.setTint(wrapped, Color.parseColor(tintColor));
            } else {
                this.getDrawable().mutate().setColorFilter(Color.parseColor(tintColor), PorterDuff.Mode.SRC_IN);
            }
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);

        setTint();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);

        setTint();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);

        setTint();
    }
}
