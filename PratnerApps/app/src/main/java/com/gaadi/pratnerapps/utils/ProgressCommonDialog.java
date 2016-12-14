package com.gaadi.pratnerapps.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;

/**
 * Created by Mushareb Ali on 7/6/16.
 */
public class ProgressCommonDialog {


    public static ProgressDialog showProgressDialog(Context context, String title, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        //  progressDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog_layout);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent)));
        // progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        ProgressBar progressBar = (ProgressBar) progressDialog.findViewById(R.id.progressBar);

        //progressDialog.setTitle(title);
        //progressDialog.setMessage(message);

        PartnerApplication partnerApplication = (PartnerApplication)context.getApplicationContext();
        if(partnerApplication.getThemeProperty("primary_color")!=null)
            progressBar.getIndeterminateDrawable().setColorFilter(
                    Color.parseColor(partnerApplication.getThemeProperty("primary_color")), android.graphics.PorterDuff.Mode.SRC_IN);

       /* if(partnerApplication.getThemeProperty("primary_color")!=null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor(
                    partnerApplication.getThemeProperty("primary_color"))
            ));
        }*/

        //progressDialog.setIndeterminate(false);
        // progressDialog.show();
        return progressDialog;


    }


}
