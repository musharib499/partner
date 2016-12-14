package com.gaadi.pratnerapps.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gaadi.pratnerapps.utils.AppPreferences;
import com.gaadi.pratnerapps.utils.ProgressCommonDialog;
import com.gaadi.pratnerapps.utils.Utils;


/**
 * Created by vinodtakhar on 25/5/16.
 *
 */
public class PartnerBaseActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE = 100;
    private String permissionBeingAsked;
    private int clientRequestCode;

    private ProgressDialog progressDialog;
    protected void showProgress(String msg) {
        progressDialog = ProgressCommonDialog.showProgressDialog(this, "", msg);
    }
    protected void showProgress(Context context,String msg){
        progressDialog = ProgressCommonDialog.showProgressDialog(context,"",msg);
    }
    protected void hideProgress(){
        if(progressDialog!=null && progressDialog.isShowing()){
            try {
                progressDialog.dismiss();
            }catch (Exception e){e.printStackTrace();}
        }
    }

    protected void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.logAnalytics(this);
    }

    protected void requestPermission(int requestCode, String permission) {

        permissionBeingAsked = permission;
        clientRequestCode = requestCode;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {

                if (shouldShowRequestPermissionRationale(permission) || !AppPreferences.getBooleanSharedPreference(this, permission, false)) {
                    AppPreferences.setBooleanSharedPreference(this, permission, true); /*set  that we have already asked the permission to handle rational*/

                    requestPermissions(
                            new String[]{permission},
                            PERMISSIONS_REQUEST_CODE);
                } else
                    Utils.showPermissionRequiredDialog(this, "Permission Required", "Please grant required permissions in Application Settings under Permissions", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utils.openAppSettings(PartnerBaseActivity.this);
                        }
                    });
            } else {
                onPermissionGranted(clientRequestCode,permissionBeingAsked);
            }
        } else {
            onPermissionGranted(clientRequestCode,permissionBeingAsked);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) //remove this line to make recursive
                    onPermissionGranted(clientRequestCode,permissionBeingAsked);
                else
                    onPermissionDenied(clientRequestCode,permissionBeingAsked);
                break;
        }
    }

    public void onPermissionGranted(int requstCode,String grantedPermission) {
    }

    public void onPermissionDenied(int requestCode,String deniedPermission) {
    }
    @Override
    protected void onStart() {
        super.onStart();
        Utils.onStartAnalytic(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Utils.onStopAnalytic(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    protected boolean isRunning(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return !isDestroyed() && !isFinishing();
        }else
            return !isFinishing();
    }
}
