package com.gaadi.pratnerapps.fragments.insurance_finance;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.gaadi.pratnerapps.BuildConfig;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.annotations.Required;
import com.gaadi.pratnerapps.theme.DealerAutoCompleteTextView;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;

/**
 * @author priyarawat
 * @version 1.0
 * @since 9/8/16
 */
public class InsuranceDetailsFragment extends FinanceCarDetailsFragment implements View.OnClickListener{
    private static final String TAG = "InsuranceFragment";
    private static final int REQUEST_CODE_CAMERA = 101;
    public static final String EXTRA_COMAPNY_LIST = "extra_company_list";

    RadioGroup radioGroup;
    @Required(order = 9, messageResId = R.string.error_registration_no_required)
    DealerAutoCompleteTextView tvRegNum;
    @Required(order = 10, messageResId = R.string.policy_num_is_req)
    DealerAutoCompleteTextView etPolicyNumber;
    //@Required(order = 11, messageResId = R.string.policy_due_date_is_req)
    DealerAutoCompleteTextView tvPolicyDueDate;
   // @Required(order = 12, messageResId = R.string.company_is_req)
   DealerAutoCompleteTextView tvCompany;
    int year, month, day;
    ImageView ivUploadPolicy;
    private  DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int month, int day) {
           month = month+1;
            tvPolicyDueDate.setText(year+"-"+(month<10 ? "0"+month : month)+"-"+(day<10 ? "0"+day : day));
        }
    };
    private boolean hasFilePath = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        //findViewId
        radioGroup = (RadioGroup) view.findViewById(R.id.rGrp);
        final LinearLayout renewInsuranceLayout = (LinearLayout) view.findViewById(R.id.renewInsurancelayout);
        tvRegNum = (DealerAutoCompleteTextView) view.findViewById(R.id.tv_regNum);
        etPolicyNumber = (DealerAutoCompleteTextView) view.findViewById(R.id.etPolicyNumber);
        tvPolicyDueDate = (DealerAutoCompleteTextView) view.findViewById(R.id.tvDuePolicyDate);
        ivUploadPolicy = (ImageView) view.findViewById(R.id.ivPolicyImage);
        ivUploadPolicy.setOnClickListener(this);
        tvPolicyDueDate.setOnClickListener(this);
        tvCompany = (DealerAutoCompleteTextView) view.findViewById(R.id.tvCompany);

        tvCompany.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,getArguments().getStringArrayList(EXTRA_COMAPNY_LIST)));
        tvCompany.setThreshold(1);

        renewInsuranceLayout.setVisibility(View.GONE);

        radioGroup.check(R.id.rBtnNewInsurance);
        view.findViewById(R.id.btnContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValidator.validate();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId)
                {
                    case R.id.rBtnNewInsurance:
                        renewInsuranceLayout.setVisibility(View.GONE);
                        break;
                    case R.id.rBtnRenewInsurance:
                        renewInsuranceLayout.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);

        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.insurance_details_fragment;
    }

    @Override
    public void onValidationSucceeded() {
        getFragmentCallback().onContinue(getRequestCode(),loadHashmap());
    }

    @Override
    protected HashMap<String, String> loadHashmap() {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put(PARAM_MAKE, tv_make.getText().toString());
        paramsMap.put(PARAM_MODEL, tv_model.getText().toString());
        paramsMap.put(PARAM_VERSION, tv_variant.getText().toString());
        paramsMap.put("regno", tvRegNum.getText().toString());
        paramsMap.put(PARAM_MONTH, selectedMonth);
        paramsMap.put(PARAM_YEAR, tv_makeYear.getText().toString());

        if(hasFilePath)
            paramsMap.put("imagePath", getFile().getAbsolutePath());

        if(radioGroup.getCheckedRadioButtonId() == R.id.rBtnNewInsurance)
        {
            paramsMap.put("policyno", "");
            paramsMap.put("company", "");
            paramsMap.put("date", "");
        }
        else
        {
            paramsMap.put("policyno", etPolicyNumber.getText().toString());
            paramsMap.put("company", tvCompany.getText().toString());
            paramsMap.put("date", tvPolicyDueDate.getText().toString());
        }
        return  paramsMap;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
       switch (v.getId())
       {
           case R.id.tvDuePolicyDate:
               new DatePickerDialog(getActivity(), myDateListener, year, month, day).show();
               break;
           case R.id.ivPolicyImage:
               launchCameraActivity();
               break;
       }
    }

    @Override
    public void onPermissionGranted(int requstCode, String grantedPermission) {
        if(ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            requestPermission(requstCode,Manifest.permission.CAMERA);
        }else if(ContextCompat.checkSelfPermission(getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermission(requstCode,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }else{
            Uri photoURI = FileProvider.getUriForFile(getContext(),
                    BuildConfig.APPLICATION_ID + ".file.provider",
                    getFile());

            final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(intent, REQUEST_CODE_CAMERA);
        }
    }

    private void launchCameraActivity() {
        requestPermission(100,Manifest.permission.CAMERA);
    }

    private  File getFile(){
        File album = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES),getResources().getString(R.string.app_name));
        if(!album.exists()){
            if (!album.mkdirs()) {
                Log.e(TAG, "Album directory not created");
            }
        }
        return new File(album, "image.jpg");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case 111:
//            /*   if (resultCode == RESULT_OK) {
//                    ArrayList<String> list = null;
//                    if (data != null) {
//                        list = data.getStringArrayListExtra(Constants.RESULT_IMAGES);
//
//                       String imageUri = list.get(0);
//                        if (imageUri != null) {
//
//                            Glide.with(getActivity())
//                                    .load("file://" + imageUri)
//                                    .into(ivUploadPolicy);
////
//                        }
//                    }
//
//               }*/
////                    Bitmap photo = (Bitmap) data.getExtras().get("data");
//                    ivUploadPolicy.setImageURI(Uri.fromFile(getFile()));
//                break;
//        }
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if(resultCode== Activity.RESULT_OK){
                    hasFilePath = true;
                   Bitmap resizedBitmap = scaledBitmap();
                    ivUploadPolicy.setImageBitmap(resizedBitmap);
                }else{
                    showToast("Failed to capture image");
                }
                break;
        }
    }

    public   Bitmap scaledBitmap () {
        final int maxSize = 960;
        int outWidth;
        int outHeight;
        Bitmap myBitmap =  BitmapFactory.decodeFile(getFile().getAbsolutePath());
        int inWidth = myBitmap.getWidth();
        int inHeight = myBitmap.getHeight();
        if(inWidth > inHeight){
            outWidth = maxSize;
            outHeight = (inHeight * maxSize) / inWidth;
        } else {
            outHeight = maxSize;
            outWidth = (inWidth * maxSize) / inHeight;
        }

       return Bitmap.createScaledBitmap(myBitmap, outWidth, outHeight, false);
    }


}
