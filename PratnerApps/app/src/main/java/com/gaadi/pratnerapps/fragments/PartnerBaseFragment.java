package com.gaadi.pratnerapps.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.utils.AppPreferences;
import com.gaadi.pratnerapps.utils.FragmentAdapter;
import com.gaadi.pratnerapps.utils.MenuTintUtils;
import com.gaadi.pratnerapps.utils.ProgressCommonDialog;
import com.gaadi.pratnerapps.utils.Utils;

/**
 * Created by vinodtakhar on 6/6/16.
 */
public class PartnerBaseFragment extends Fragment {
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    private String permissionBeingAsked;
    private int clientRequestCode;
    private FragmentAdapter fragmentAdapter;
    private String TAG = this.getClass().getName();
    private ProgressDialog progressDialog;

    public FragmentAdapter getFragmentAdapter() {
        return fragmentAdapter;
    }

    public PartnerBaseFragment setFragmentAdapter(FragmentAdapter fragmentAdapter) {
        this.fragmentAdapter = fragmentAdapter;
        return this;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentAdapter) {
            fragmentAdapter = (FragmentAdapter) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.logAnalytics(getActivity());
    }

    protected void requestPermission(int requestCode, String permission) {

        permissionBeingAsked = permission;
        clientRequestCode = requestCode;

        if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(permission) || !AppPreferences.getBooleanSharedPreference(getContext(), permission, false)) {
                AppPreferences.setBooleanSharedPreference(getContext(), permission, true); /*set  that we have already asked the permission to handle rational*/

                requestPermissions(new String[]{permission},
                        PERMISSIONS_REQUEST_CODE);
            } else
                Utils.showPermissionRequiredDialog(getContext(), "Permission Required", "Please grant required permissions in Application Settings under Permissions", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Utils.openAppSettings(getContext());
                    }
                });
        } else {
            onPermissionGranted(clientRequestCode,permissionBeingAsked);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
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


    @Override
    public void onStart() {
        super.onStart();
        Utils.onStartAnalytic(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        Utils.onStopAnalytic(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        Utils.hideKeypad(getActivity());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuTintUtils.tintAllIcons((PartnerApplication)getContext().getApplicationContext(),menu);
    }

    public void onPermissionGranted(int requstCode, String grantedPermission) {
    }

    public void onPermissionDenied(int requestCode,String deniedPermission) {
    }

    protected  void requestProgress(String title,String message){
        progressDialog = ProgressCommonDialog.showProgressDialog(getContext(),title,message);
    }
    protected void hideProgress(){
        if(progressDialog!=null && progressDialog.isShowing()){
            try{
                progressDialog.dismiss();
            }catch(Exception e){e.printStackTrace();}
        }
    }
    protected void showToast(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
    protected ProgressDialog showProgress(String msg){
        return progressDialog = ProgressCommonDialog.showProgressDialog(getActivity(),"",msg);
    }
    protected ProgressDialog showProgress(Context context,String msg){
        return progressDialog = ProgressCommonDialog.showProgressDialog(context,"",msg);
    }
    protected void showSubmitDialog(String title,String message){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog);
        TextView textView = (TextView) dialog.findViewById(R.id.tvId);
        textView.setText(title);
        TextView textView1 = (TextView) dialog.findViewById(R.id.tvdetails);
        textView1.setText(message);
        dialog.findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getFragmentAdapter().popFragment(false);
                getFragmentAdapter().popFragment(false);
                getFragmentAdapter().showHome();
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.show();
    }
}
