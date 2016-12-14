package com.gaadi.pratnerapps.fragments.ucv;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.fragments.PartnerBaseFragment;
import com.gaadi.pratnerapps.models.UCVResponseModel;
import com.gaadi.pratnerapps.models.UsedCarValuactionModel;
import com.gaadi.pratnerapps.utils.Constants;
import com.gaadi.pratnerapps.utils.Logger;
import com.gaadi.pratnerapps.utils.Utils;

/*Created by mushareb ali*/
public class UsedCarValuationFeedBackFragment extends PartnerBaseFragment{


    private  UsedCarValuactionModel usedCarValuactionModel;
    private UCVResponseModel.UsedCarData usedCarData;
    private  TextView  tv_goodValue,tv_carmodel,tv_carowner , tv_know_more , tv_price_range;
    private Button btnSubmit;
    private String excellent,good,fair;

    public static UsedCarValuationFeedBackFragment newInstance(UsedCarValuactionModel usedCarValuactionModel,UCVResponseModel.UsedCarData ucvResponseModel) {
        UsedCarValuationFeedBackFragment fragment = new UsedCarValuationFeedBackFragment();

        Bundle args = new Bundle();
        args.putSerializable(Constants.UCV_ID, usedCarValuactionModel);
        args.putSerializable(Constants.UCVR_ID, ucvResponseModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            usedCarValuactionModel=new UsedCarValuactionModel();
           // ucvResponseModel=new UCVResponseModel();
            usedCarValuactionModel= (UsedCarValuactionModel) getArguments().getSerializable(Constants.UCV_ID);
            usedCarData= (UCVResponseModel.UsedCarData) getArguments().getSerializable(Constants.UCVR_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.ucv_feedback,null);
        getFragmentAdapter().setTitleMessage(getString(R.string.used_car),false);
        setHasOptionsMenu(true);
        initializationView(view);
        return view;
    }

    private void initializationView(final View view) {

        tv_goodValue = (TextView) view.findViewById(R.id.goodValue);
        tv_carmodel = (TextView) view.findViewById(R.id.carmodel);
        tv_know_more = (TextView) view.findViewById(R.id.know_more);
        tv_carowner = (TextView) view.findViewById(R.id.carowner);
        tv_price_range = (TextView) view.findViewById(R.id.car_valuation_price_range);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
              btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentAdapter().popFragment(false);

            }
        });

        tv_know_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogsInsuranceMessage(getResources().getString(R.string.know_more_title), getResources().getString(R.string.know_more_descripsion));
            }
        });

        if (usedCarData != null) {
            if(usedCarValuactionModel.ismRadioGroup()) {
                excellent = usedCarData.getI2DExcellent().replace(",", "").trim();
                good = usedCarData.getI2DGood().replace(",", "").trim();
                fair = usedCarData.getI2DFair().replace(",", "").trim();

            }else {
                excellent = usedCarData.getD2IExcellent().replace(",", "").trim();
                good = usedCarData.getD2IGood().replace(",", "").trim();
                fair = usedCarData.getD2IFair().replace(",", "").trim();
            }
            Logger.d("UCVF",""+usedCarData.toString());
            Logger.d("UCVFPrice",""+fair);
           // Utils.insertCommaIntoNumber(tv_goodValue, good, "##,##,###");
            tv_goodValue.setText(Constants.RUPES + Utils.convertCommaIntoNumber(good,"##,##,###"));
            tv_price_range.setText(String.format(getString(R.string.used_car_valuation_price_range), Utils.convertCommaIntoNumber(fair, "##,##,###"), Utils.convertCommaIntoNumber(excellent, "##,##,###")));


        }
        setData();
    }


    public void dialogsInsuranceMessage(String st_title,String st_message) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before

        //setting custom layout to dialog
        dialog.setContentView(R.layout.ucv_dailog);
        dialog.setTitle("Custom Dialog");

        //adding text dynamically
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        tv_title.setText(st_title);
        TextView tv_message = (TextView) dialog.findViewById(R.id.tv_message);
        tv_message.setText(st_message);


        //adding button click event
        LinearLayout dismissLayout = (LinearLayout) dialog.findViewById(R.id.lay_dismiss);
        dismissLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void setData()
    {

        if(usedCarValuactionModel!=null){
            tv_carmodel.setText(new StringBuilder().append(usedCarValuactionModel.getMake())
                    .append(" ").append(usedCarValuactionModel.getVersion()).append(" ").append(usedCarValuactionModel.getRegyear()).append(" Model"));

            tv_carowner.setText(new StringBuilder().append(usedCarValuactionModel.getCarkm()).append(" KMs | ")
                    .append(usedCarValuactionModel.getOwner()).append(" Owner | ")
                    .append(usedCarValuactionModel.getBuyer()).append(" | ")
                    .append(usedCarValuactionModel.getCity()));

        }
        
    }


}
