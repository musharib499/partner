package com.gaadi.pratnerapps.fragments.buy_car;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.BuyCarDetails;
import com.gaadi.pratnerapps.utils.Constants;
import com.gaadi.pratnerapps.utils.Utils;

import org.apache.commons.lang3.text.WordUtils;


/**
 * Created by Musharib Ali on 1/4/16
 */
public class BuyCarOverviewFragment extends Fragment {

    private Context mContext;
    private FragmentActivity mActivity;
    private BuyCarDetails searchCarDetails;
    private TextView tvFuel, tvPostBy, tvInsurance, tvRegCity, tvKiloMeter, tvTransmission, tvOwner, tvColor;
    private TextView tvYear;
    private TextView tvMake;
    private TextView tvModel;
    private TextView tvVariant;
    private TextView tvRegNo;
    private TextView tvRegPlace;
    private TextView tvPrice;
    private TextView tvCarversion;
    private TextView tvDescription;
    private TextView tvCreatedDate;
    private TextView tvCarCertification;
    String price, kms;


    public static BuyCarOverviewFragment newInstance(BuyCarDetails carDetails) {
        BuyCarOverviewFragment fragment = new BuyCarOverviewFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.VEHICLE_ID, carDetails);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchCarDetails = new BuyCarDetails();
            searchCarDetails = (BuyCarDetails) getArguments().getSerializable(Constants.VEHICLE_ID);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.buyercar_overview, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvYear = (TextView) view.findViewById(R.id.tvMakeYear);
        tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        tvFuel = (TextView) view.findViewById(R.id.tvFuelType);
        tvMake = (TextView) view.findViewById(R.id.tvMake);
        tvModel = (TextView) view.findViewById(R.id.tvModel);
        tvVariant = (TextView) view.findViewById(R.id.tvVariant);
        tvRegNo = (TextView) view.findViewById(R.id.tvRegno);
        tvRegPlace = (TextView) view.findViewById(R.id.tvRegplace);
        tvInsurance = (TextView) view.findViewById(R.id.tvInsurance);
        tvRegCity = (TextView) view.findViewById(R.id.tvRegCity);
        tvKiloMeter = (TextView) view.findViewById(R.id.tvKilometer);
        tvCarversion = (TextView) view.findViewById(R.id.tvCarversion);
        tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        tvCreatedDate = (TextView) view.findViewById(R.id.tvCreatedDate);
        tvCarCertification = (TextView) view.findViewById(R.id.tvCarCertification);
        tvOwner = (TextView) view.findViewById(R.id.tvOwners);
        tvRegPlace.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        tvRegCity.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        setData();
    }


    public void setData() {
        if (!TextUtils.isEmpty(searchCarDetails.getYear()))
            tvYear.setText(searchCarDetails.getYear());

        if (!TextUtils.isEmpty(searchCarDetails.getFuelType()))
            tvFuel.setText(searchCarDetails.getFuelType());

        if (!TextUtils.isEmpty(searchCarDetails.getVariant()))
            tvVariant.setText(searchCarDetails.getVariant());

        if (!TextUtils.isEmpty(searchCarDetails.getInsurance()))
            tvInsurance.setText(searchCarDetails.getInsurance());

        if (!TextUtils.isEmpty(searchCarDetails.getRegplace()))
            tvRegCity.setText(WordUtils.capitalize(searchCarDetails.getCity()));

        if (!TextUtils.isEmpty(searchCarDetails.getRegno()))
            tvRegNo.setText(searchCarDetails.getRegno());

        if (!TextUtils.isEmpty(searchCarDetails.getCarversion()))
            tvCarversion.setText(searchCarDetails.getCarversion());

        if (!TextUtils.isEmpty(searchCarDetails.getDescription()))
            tvDescription.setText(searchCarDetails.getDescription());

        if (!TextUtils.isEmpty(searchCarDetails.getKilometerDriven())) {
            kms = searchCarDetails.getKilometerDriven().trim();
            tvKiloMeter.setText(Utils.convertCommaIntoNumber(kms, "##,##,###") + " Kms");
        }

        if (!TextUtils.isEmpty(searchCarDetails.getCreatedDate()))
            tvCreatedDate.setText(searchCarDetails.getCreatedDate());

        if (!TextUtils.isEmpty(searchCarDetails.getCarCertification()))
            tvCarCertification.setText(searchCarDetails.getCarCertification());

        if (!TextUtils.isEmpty(searchCarDetails.getOwner()))
            tvOwner.setText(searchCarDetails.getOwner());

        if (!TextUtils.isEmpty(searchCarDetails.getRegplace()))
            tvRegPlace.setText(WordUtils.capitalize(searchCarDetails.getRegplace()));

        if (!TextUtils.isEmpty(searchCarDetails.getMake()))
            tvMake.setText(searchCarDetails.getMake());

        if (!TextUtils.isEmpty(searchCarDetails.getModel()))
            tvModel.setText(searchCarDetails.getModel());

        if (!TextUtils.isEmpty(searchCarDetails.getPrice())) {
            price = searchCarDetails.getPrice().trim();
            tvPrice.setText(Constants.RUPES + " " + Utils.convertCommaIntoNumber(price, "##,##,###"));
        }
    }
}
