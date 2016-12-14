package com.gaadi.pratnerapps.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.crashlytics.android.Crashlytics;
import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by vinodtakhar on 25/5/16.
 */
public class Utils {
    public static TextView tv;

    public static void showPermissionRequiredDialog(final Context activity, String title, String msg, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder;// = new AlertDialog.Builder(activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(activity, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(activity);
        }

        builder.setCancelable(true);
        builder.setMessage(msg);
        builder.setTitle(title);
        builder.setPositiveButton(R.string.go_to_app_settings, onClickListener);

        builder.show();
    }

   /* public static final SparseIntArray makeLogoMap = new SparseIntArray() {

        {
            put(12, R.drawable.isuzu);
            put(10, R.drawable.hyundai);
            put(9, R.drawable.honda);
            put(19, R.drawable.toyota);
            put(18, R.drawable.tata);
            put(11, R.drawable.mahindra);
            put(6, R.drawable.fiat);
            put(7, R.drawable.ford);
            put(4, R.drawable.chevrolet);
            put(2, R.drawable.audi);
            put(30, R.drawable.volkswagen);
            put(3, R.drawable.bmw);
            put(41, R.drawable.renault);
            put(13, R.drawable.mercedes);
            put(22, R.drawable.nissan);
            put(17, R.drawable.skoda);
            put(25, R.drawable.lamborghini);
            put(38, R.drawable.bugatti);
            put(44, R.drawable.polaris);
            put(42, R.drawable.ferrari);
            put(40, R.drawable.aston_martin);
            put(46, R.drawable.mini);
            put(43, R.drawable.peugeot);
            put(48, R.drawable.willys);
            put(51, R.drawable.lexus);
            put(53, R.drawable.leyland);
         //   put(68, R.drawable.mazda);
            // put(63, R.drawable.morris);
//put(64, R.drawable.studebaker);
            put(67, R.drawable.chrysler);
            put(8, R.drawable.hindustan_motors);
            put(15, R.drawable.opel);
            put(23, R.drawable.reva);
            put(24, R.drawable.mahindra_renault);
            put(26, R.drawable.maybach);
           // put(29, R.drawable.san_motors);
            put(5, R.drawable.daewoo);
            put(58, R.drawable.ssangyong);
            put(59, R.drawable.isuzu);
            put(39, R.drawable.maserati);
            put(45, R.drawable.force);
            put(14, R.drawable.mitsubishi);
            put(16, R.drawable.premier);
            put(20, R.drawable.porsche);
            put(27, R.drawable.icml);
            put(28, R.drawable.rolls_royce);
            put(31, R.drawable.volvo);
            put(36, R.drawable.land_rover);
            put(21, R.drawable.bentley);
            put(33, R.drawable.jaguar);
            put(55, R.drawable.datsun);
            put(57, R.drawable.hummer);
            put(60, R.drawable.kia);
            put(60, R.drawable.hindustan_motors);
            put(60, R.drawable.jeep);
            put(60, R.drawable.maybach);
        }
    };*/

    public static String convertion24to12hrs(String time){
        String convertedTime = "";
        try {
            //String _24HourTime = "22:15";
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");

            Date _24HourDt = _24HourSDF.parse(time);
            convertedTime = _12HourSDF.format(_24HourDt);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertedTime;
    }
    public static void  setTint(ImageView iv ,Context context)
    {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();


        String tintColor = partnerApplication.getThemeProperty("icon_color");

        if(tintColor!=null) {
            //getDrawable().mutate().setColorFilter(ContextCompat.getColor(context, Color.parseColor(tintColor)), PorterDuff.Mode.SRC_IN);
            //DrawableCompat.setTint(getDrawable(), Color.parseColor(tintColor));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                final Drawable wrapped = DrawableCompat.wrap(iv.getDrawable());
                iv.getDrawable().mutate();
                DrawableCompat.setTint(wrapped, Color.parseColor(tintColor));
            } else {
                iv.getDrawable().mutate().setColorFilter(Color.parseColor(tintColor), PorterDuff.Mode.SRC_IN);
            }
        }
    }
    public static void tintWidget(Drawable[] drawables,int color) {
        for(int i=0;i<drawables.length;i++) {
            if(drawables[i]==null){
                continue;
            }

            drawables[i].mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
    }
    public static void  setTint(ImageView iv ,Context context, String colorValue)
    {
        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();
        String tintColor = partnerApplication.getThemeProperty(colorValue);
        if(tintColor!=null) {
            //getDrawable().mutate().setColorFilter(ContextCompat.getColor(context, Color.parseColor(tintColor)), PorterDuff.Mode.SRC_IN);
            //DrawableCompat.setTint(getDrawable(), Color.parseColor(tintColor));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                final Drawable wrapped = DrawableCompat.wrap(iv.getDrawable());
                iv.getDrawable().mutate();
                DrawableCompat.setTint(wrapped, Color.parseColor(tintColor));
            } else {
                iv.getDrawable().mutate().setColorFilter(Color.parseColor(tintColor), PorterDuff.Mode.SRC_IN);
            }
        }
    }

    public static void logAnalytics(Context context)
    {
        Tracker t = ((PartnerApplication) context.getApplicationContext()).getTracker(
                PartnerApplication.TrackerName.APP_TRACKER);
        t.setScreenName(((Activity)context).getClass().getName());
        t.send(new HitBuilders.ScreenViewBuilder().build());
    }


    public static void onStartAnalytic(Context context)
    {
        GoogleAnalytics.getInstance(context).reportActivityStart((Activity) context);
    }
    public static void onStopAnalytic(Context context)
    {
        GoogleAnalytics.getInstance(context).reportActivityStop((Activity) context);
    }





    public static void openAppSettings(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }
    public static void setCircularImageView(final Context context, String url, final ImageView imageView) {
        Glide.with(context).load(url).asBitmap()
                .centerCrop()
                .placeholder(R.drawable.testimonial_profile_icon)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circular = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circular.setCircular(true);
                        imageView.setImageDrawable(circular);
                    }
                });
    }

    public static void loadImage(Context context,String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(R.drawable.placeholder_img)
                .centerCrop()
                .dontTransform()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }
    public static void loadImageView(Context context,String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(R.drawable.placeholder_img)
                .dontTransform()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }
    public static void loadImage(Context context,String path, ImageView imageView,int resourced_id) {
        Glide.with(context)
                .load(path)
                .placeholder(resourced_id)
                .centerCrop()
                .dontTransform()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    public static void loadImage(Context context, String path, final ImageView imageView, final ProgressBar progressBar) {
        Glide.with(context)
                .load(path)
                .placeholder(R.drawable.placeholder_img)
                .centerCrop()
                .dontTransform()
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        imageView.setImageDrawable(resource);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    public static String getRelativeDate(long date){
        return DateUtils.getRelativeTimeSpanString(date, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString();
    }

    public static void callPhone(Context context, String phone) {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            context.startActivity(intent);
        } catch (SecurityException e) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
            context.startActivity(intent);
        }
    }

    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void hideKeypad(Activity context)
    {
        View view = context.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    public static void showNoConnectionDialog(final Activity activity) {
        showNoConnectionDialog(activity,activity.getString(R.string.no_connection_title),activity.getString(R.string.no_connection),null);
    }
    public static void showNoConnectionDialog(final Activity activity,String title,String message,DialogInterface.OnClickListener onClickListener) {

        AlertDialog.Builder builder;// = new AlertDialog.Builder(activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(activity, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(activity);
        }

        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setTitle(title);

        builder.setPositiveButton("Ok",onClickListener);


        builder.show();
    }

    public static boolean isBlank(String value) {
        return (value==null || value.isEmpty() || value.equals("null"));
    }

    public static Integer[] getMakeYearList() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Integer arr[]  = new Integer[21];

        for (int i = currentYear ,k = 0; k<21  ; i--, k++)
        {
            arr[k] = i;
        }
        return arr;
    }

    private static boolean chkConvert(String s) {
        String tempArray[] = s.split("\\.");
        if (tempArray.length > 1) {
            return Integer.parseInt(tempArray[1]) > 0;
        } else
            return false;
    }

    private static String customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(value);

    }
    public static void insertCommaIntoNumber(EditText etText, CharSequence s, String format) {

        try {
            if (s.toString().length() > 0) {
                String convertedStr = s.toString();
                if (s.toString().contains(".")) {
                    if (chkConvert(s.toString()))
                        convertedStr = customFormat(format, Double.parseDouble(s.toString().replace(",", "")));
                } else {
                    convertedStr = customFormat(format, Double.parseDouble(s.toString().replace(",", "")));
                }
                if (!etText.getText().toString().equals(convertedStr) && convertedStr.length() > 0) {
                    etText.setText(convertedStr);
                    etText.setSelection(etText.getText().length());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String phoneNumberFormate(String mobile)
    {

        if (!TextUtils.isEmpty(mobile))
        return String.format("%s %s %s", mobile.substring(0,4) ,mobile.substring(4,7), mobile.substring(7,10));
        else return "";

    }
    public static void insertCommaIntoNumber(TextView etText, CharSequence s, String format) {

        try {
            if (s.toString().length() > 0) {
                String convertedStr = s.toString();
                if (s.toString().contains(".")) {
                    if (chkConvert(s.toString()))
                        convertedStr = customFormat(format, Double.parseDouble(s.toString().replace(",", "")));
                } else {
                    convertedStr = customFormat(format, Double.parseDouble(s.toString().replace(",", "")));
                }
                if (!etText.getText().toString().equals(convertedStr) && convertedStr.length() > 0) {
                    etText.setText(convertedStr);
                   // etText.setSelection(etText.getText().length());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String convertCommaIntoNumber(String s, String format) {
        String convertedStr = s;
        try {
            if (s.length() > 0) {

                if (s.contains(".")) {
                    if (chkConvert(s))
                        convertedStr = customFormat(format, Double.parseDouble(s.replace(",", "")));
                } else {
                    convertedStr = customFormat(format, Double.parseDouble(s.replace(",", "")));
                }

            }

        } catch (Exception e) {
            Crashlytics.logException(e.getCause());
            //e.printStackTrace();
        }

        return convertedStr;
    }

    public static String getDeviceId(Context context) {
        String deviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        if(TextUtils.isEmpty(deviceId)){
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = telephonyManager.getDeviceId();
        }

        return deviceId;
    }

    public static String getStringCommaSeparated(Iterator<String> list) {
        boolean firstRecord = true;

        StringBuilder stringBuilder = new StringBuilder();
        while(list.hasNext()){
            String val = list.next();
            if(!firstRecord){
                stringBuilder.append(",");
            }else{
                firstRecord=false;
            }

            stringBuilder.append(val);
        }

        return stringBuilder.toString();
    }
    public static String getStringSeparatedBullet(Iterator<String> list) {
        boolean firstRecord = true;

        StringBuilder stringBuilder = new StringBuilder();
        while(list.hasNext()){
            String val = list.next();
            if(!firstRecord){
                stringBuilder.append(" " + Constants.BULLET + " ");
            }else{
                firstRecord=false;
            }

            stringBuilder.append(val);
        }

        return stringBuilder.toString();
    }
}
