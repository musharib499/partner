package com.gaadi.pratnerapps.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
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
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaadi.pratnerapps.BuildConfig;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.BaseResponseModel;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;

import java.io.File;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashishkumar on 19/10/16.
 *
 */

public class AddTestimonialFragment extends PartnerBaseFragment implements View.OnClickListener {
    private EditText etName,etTestimonial;
    private ImageView imageUpload;
    private Button btnSubmit;;
    private static final int REQUEST_CODE = 101;
    private boolean hasfilePath = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addtestimonial,container,false);
        etName = (EditText)view.findViewById(R.id.etName);
        etTestimonial = (EditText)view.findViewById(R.id.etTestimonial);

        imageUpload = (ImageView) view.findViewById(R.id.ivUploadImage);
        imageUpload.setOnClickListener(this);

        btnSubmit = (Button)view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        getFragmentAdapter().setTitleMessage(getString(R.string.add_testimonial),false);
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.ivUploadImage:
                launchActivity();
                break;
            case R.id.btnSubmit:
                if(!etTestimonial.getText().toString().equals("")){
                    submitTestimonial();
                }else {
                    etTestimonial.setError("enter testimonial text");
                }

        }

    }
    public void launchActivity(){
        requestPermission(100, Manifest.permission.CAMERA);
    }

    @Override
    public void onPermissionGranted(int requstCode, String grantedPermission) {
        if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            requestPermission(requstCode,Manifest.permission.CAMERA);
        }else if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermission(requstCode,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }else {
            Uri photoUri = FileProvider.getUriForFile(getContext(),
                    BuildConfig.APPLICATION_ID + ".file.provider",
                    getFile());
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent,REQUEST_CODE);
        }
    }

    private File getFile() {
        File album = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                                     ,getResources().getString(R.string.app_name));
        if(!album.exists()){
            Log.e("Tag","Album directory not created");
        }
        return new File(album,"image.jpg");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE :
                if(resultCode == Activity.RESULT_OK){
                    hasfilePath = true;
                    Bitmap bitmap = scaledBitmap();
                    imageUpload.setImageBitmap(bitmap);
                }else {
                    showToast("Failed to capture image");
                }
                break;
        }

    }
    public Bitmap scaledBitmap(){
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
        return Bitmap.createScaledBitmap(myBitmap,outWidth,outHeight,false);
    }

    public void submitTestimonial(){
        HashMap<String,String> params = new HashMap<>();
        params.put("txtcustName",etName.getText().toString());
        params.put("comment",etTestimonial.getText().toString());

        if(hasfilePath){
            params.put("filePath",getFile().getAbsolutePath());
        }

        showProgress("Please wait...");

        RestApiCalls.postTestimonial(getActivity(),params,params.get("filePath")).enqueue(new Callback<BaseResponseModel>() {
            @Override
            public void onResponse(Call<BaseResponseModel> call, Response<BaseResponseModel> response) {

                hideProgress();

                if(response.isSuccessful() && response.body()!= null && response.body().isSuccess()){
                    showSubmitDialog(getResources().getString(R.string.testimonial_title_msg),
                            getResources().getString(R.string.testimonial_book_msg));

                }else if(response.body()!= null){
                    showToast(response.raw().message());
                } else {
                    showToast(getString(R.string.server_error_msg));
                }
            }

            @Override
            public void onFailure(Call<BaseResponseModel> call, Throwable t) {
                hideProgress();
                showToast(t.getMessage());
            }
        });

    }
}
