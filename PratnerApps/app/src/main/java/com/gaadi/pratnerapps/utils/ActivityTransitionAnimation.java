package com.gaadi.pratnerapps.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.gaadi.pratnerapps.R;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class ActivityTransitionAnimation {

    public static void scaleupAnimation(View view, Context context, Class<?> secondActivity) {
        ActivityOptions opts = ActivityOptions.makeScaleUpAnimation(view, 0, 0,
                view.getWidth(), view.getHeight());

        context.startActivity(new Intent(context, secondActivity),
                opts.toBundle());
    }

    public static void scaleupAnimationWithOutIntent(View view, Context context, Class<?> secondActivity) {
        ActivityOptions opts = ActivityOptions.makeScaleUpAnimation(view, 0, 0,
                view.getWidth(), view.getHeight());

        context.startActivity(new Intent(context, secondActivity),
                opts.toBundle());
    }

    public static void translateAnimationBottomUp(Activity context, Class<?> secondActivity) {
        if (secondActivity != null) {
            context.startActivity(new Intent(context, secondActivity));
        }
        context.overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    public static void translateAnimationUpBottom(Activity context, Class<?> secondActivity) {
        if (secondActivity != null) {
            context.startActivity(new Intent(context, secondActivity));
        }
        context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public static void startActivityWithSharedView(Activity activity, View view, String transitionName, Class<?> secondActivity, Bundle extras) {
        if (extras == null)
            extras = new Bundle();
        if (Build.VERSION.SDK_INT > 21) {
            Intent intent = new Intent(activity, secondActivity);

            intent.putExtras(extras);
            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity, view, transitionName).toBundle());
        } else {
            activity.startActivity(new Intent(activity, secondActivity).putExtras(extras));
        }
    }
}
