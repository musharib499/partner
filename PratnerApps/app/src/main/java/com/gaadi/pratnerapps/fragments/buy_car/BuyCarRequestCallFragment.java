package com.gaadi.pratnerapps.fragments.buy_car;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.fragments.PartnerBaseFragment;
import com.gaadi.pratnerapps.models.BaseResponseModel;
import com.gaadi.pratnerapps.models.BuyCarDetails;
import com.gaadi.pratnerapps.models.UserModel;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;
import com.gaadi.pratnerapps.utils.Constants;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.gaadi.pratnerapps.utils.Utils;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashishkumar on 7/6/16.
 */
public class BuyCarRequestCallFragment extends PartnerBaseFragment implements View.OnClickListener {

    MaterialEditText etName,etNum,etEmail;
    TextView makeName,price,yearKmType,address;
    ScrollView scrollView;
    Button button;
    BuyCarDetails carDetails;
    String car_Id = "";
    String cPrice,kms;
    private ImageView carImageView;

    public static BuyCarRequestCallFragment newInstance(BuyCarDetails carDetails) {
        BuyCarRequestCallFragment fragment = new BuyCarRequestCallFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.VEHICLE_ID,carDetails);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            carDetails = (BuyCarDetails) getArguments().getSerializable(Constants.VEHICLE_ID);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.request_call, container, false);
        makeName = (TextView) view.findViewById(R.id.tvMake);
        carImageView = (ImageView) view.findViewById(R.id.imgCar);
        price = (TextView) view.findViewById(R.id.tvPrice);
        yearKmType = (TextView) view.findViewById(R.id.tvYearKilFuel);
        address = (TextView) view.findViewById(R.id.tvAddress);
        etName = (MaterialEditText) view.findViewById(R.id.etName);
        etNum = (MaterialEditText) view.findViewById(R.id.etContactNum);
        etEmail = (MaterialEditText) view.findViewById(R.id.etEmail);
        button = (Button) view.findViewById(R.id.btnSubmit);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        view.findViewById(R.id.layoutRequestCall).setVisibility(View.GONE);
        setData();
        button.setOnClickListener(this);

        UserModel userModel = ObjectTableUtil.getUserModel(getContext());

        etName.setText(userModel.getName());
        etNum.setText(userModel.getMobile());

        if(!TextUtils.isEmpty(userModel.getEmail()))
            etEmail.setText(userModel.getEmail());

        getFragmentAdapter().setTitleMessage(getString(R.string.request_call),false);

        return view;
    }

    private void setData() {
        if (carDetails.getImages().size()!=0)
        Utils.loadImage(getActivity(), carDetails.getImages().get(0), carImageView);

        makeName.setText(carDetails.getMake() + " " + carDetails.getModel());
        cPrice = carDetails.getPrice().trim();
        price.setText(Constants.RUPES + " " + Utils.convertCommaIntoNumber(cPrice,"##,##,###"));
        kms = carDetails.getKilometerDriven().trim();
        yearKmType.setText(getContext().getString(R.string.year_kms_type_value, " " + carDetails.getYear(), " " +
                Utils.convertCommaIntoNumber(kms,"##,##,###") + " kms ", " " + carDetails.getFuelType()));
        address.setText(carDetails.getCity());

    }


    private boolean validate() {
        if (etName.getText().toString().trim().equals("")) {
            etName.setError("Enter name");
            return false;
        } else if (etName.getText().toString().trim().length() < 3) {
            etName.setError("Name should contain atleast 3 characters");
            etName.requestFocus();
            return false;
        }else if (etNum.getText().toString().trim().equals("")) {
            etNum.setError("Enter mobile number");
            return false;
        }
        else if (etNum.getText().toString().trim().length() < 10)
        {
            etNum.setError("Enter valid mobile number");
            return false;
        }
        else if (!Pattern.compile("^[7-9]").matcher(etNum.getText().toString().trim()).find()) {
            etNum.setError("Enter valid mobile number");
            return false;
        }
        else if (TextUtils.isEmpty(etEmail.getText().toString()) || !Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches())
        {
            etEmail.setError("Enter valid email address.");
            return false;
        }
        return true;
    }
    @Override
    public void onClick(View v) {
        if (Utils.checkInternetConnection(getActivity())) {
            if (validate())
                submitRequestCall();
        }else
        {
            showToast(getString(R.string.no_connection));
        }
    }
    private void submitRequestCall(){
        String persionName = etName.getText().toString();
        String contactNum = etNum.getText().toString();

        HashMap<String,String> params = new HashMap<>();

        params.put(Constants.NAME, persionName);
        params.put(Constants.MOBILE, contactNum);
        params.put(Constants.EMAIL, etEmail.getText().toString());
        params.put(Constants.USED_CAR_ID, carDetails.getGaadiId().toString());

        requestProgress("","Please wait...");

        RestApiCalls.requestCall(getContext(),params).enqueue(new Callback<BaseResponseModel>() {
            @Override
            public void onResponse(Call<BaseResponseModel> call, Response<BaseResponseModel> response) {
                hideProgress();
                if(response.body()!=null && response.isSuccessful()){

                    if (response.body().isSuccess()) {
                        /*showToast("Thanks for submitting the details.");
                        getFragmentAdapter().popFragment(false);*/
                        showSubmitDialog(getResources().getString(R.string.buy_car_title_msg),
                                getResources().getString(R.string.buy_car_title_msg));
                    }else {
                        showToast("Please try again !");
                    }
                } else if(response.body()!=null){
                    showToast(response.body().getMessage());
                }else{
                    showToast("Something went wrong, Please try later.");
                }
            }

            @Override
            public void onFailure(Call<BaseResponseModel> call, Throwable t) {

               /* if(t instanceof UnknownHostException){
                    Toast.makeText(getActivity(),getString(R.string.network_connection_error),Toast.LENGTH_SHORT);
                }*/
                hideProgress();
                showToast(t.getMessage());
            }
        });

    }
}
