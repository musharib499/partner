package com.gaadi.pratnerapps.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.gaadi.pratnerapps.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * Created by Mushareb Ali on 8/4/16.
 */
public class CommonUtils {

    public static TextView OverFlowMenuText(Context context, String st, int testSize, Menu menu) {
        int testsize1 = 16;
        testsize1 = testSize;

        View view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_reset, null);
        TextView tv = (TextView) view.findViewById(R.id.tvSetting);
        tv.setText(st);
       /* String background=((PartnerApplication) context.getApplicationContext()).getThemeProperty("primary_color");
        if (background !=null) {
            tv.setBackgroundColor(Color.parseColor(background));
        }else {
            tv.setBackgroundColor();
        }
        tv.setTextColor(Color.parseColor(((PartnerApplication) context.getApplicationContext()).getThemeProperty("title_color_icon_color")));
      */
        tv.setPadding(0, 0, 20, 0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 10, 0);

        tv.setLayoutParams(params);
        tv.setTypeface(null, Typeface.NORMAL);
        tv.setTextSize(testsize1);
        menu.add(0, 0, 1, st).setActionView(tv).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return tv;
    }

    public static RecyclerView recyclerView(RecyclerView recyclerView, Context context, boolean orientation) {
        LinearLayoutManager recycler_layout = new LinearLayoutManager(context);
        if (orientation)
            recycler_layout.setOrientation(LinearLayout.VERTICAL);
        else
            recycler_layout.setOrientation(LinearLayout.HORIZONTAL);

        recyclerView.setLayoutManager(recycler_layout);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return recyclerView;
    }

    public static byte[] serialize(Object obj) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(out);
            os.writeObject(obj);
            return out.toByteArray();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public static void setCircularImageView(final Context context, String url, final ImageView imageView) {
        Glide.with(context).load(url).asBitmap()
                .centerCrop()
                .placeholder(R.drawable.ic_profile)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circular = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circular.setCircular(true);
                        imageView.setImageDrawable(circular);
                    }
                });
    }
    public static Object deserialize(byte[] mydata) {
        ByteArrayInputStream in = new ByteArrayInputStream(mydata);
        ObjectInputStream is;
        try {
            is = new ObjectInputStream(in);
            return is.readObject();
        } catch (StreamCorruptedException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public static void shakeView(Context context, View failedView) {
        Animation anim = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.shake);
        failedView.setAnimation(anim);
        failedView.startAnimation(anim);
    }
}
