package com.gaadi.pratnerapps.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;


/**
 * Created by Mushareb Ali on 21/4/16.
 */
public class BuyCarListViewHolder extends RecyclerView.ViewHolder {
    public TextView tvAddress;
    public RelativeLayout front_layout, back_layout;
    //public LinearLayout rootLayout;
    public ImageButton cancelButton, back_cancel_button;
    public FrameLayout car_image_layout;
    public TextView tvPrice, tvMake, tvModel, tvEmi, tvYearKilFuel, tv_km, tvCall, tvRequest;
    public Button seller_details, back_submit;
    public ImageView imgCar, imgLike;

    public BuyCarListViewHolder(View itemView) {
        super(itemView);
        // car_image_layout = (FrameLayout) itemView.findViewById(R.id.search_car_tupple);
        //rootLayout = (LinearLayout) itemView.findViewById(R.id.root_layout);
        imgCar = (ImageView) itemView.findViewById(R.id.imgCar);
        //imgLike = (ImageView) itemView.findViewById(R.id.imgLike);
        tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
        //tvCall = (TextView) itemView.findViewById(R.id.tvCallnow);
        tvRequest = (TextView) itemView.findViewById(R.id.tvRequest);
        tvMake = (TextView) itemView.findViewById(R.id.tvMake);
        tvAddress = (TextView) itemView.findViewById(R.id.tvAddress);
        // tvEmi = (TextView) itemView.findViewById(R.id.tv_search_car_emi);
        tvYearKilFuel = (TextView) itemView.findViewById(R.id.tvYearKilFuel);
       /* seller_details = (Button) itemView.findViewById(R.id.button_get_seller_details);
        cancelButton = (ImageButton) itemView.findViewById(R.id.button_cancel_result);
        front_layout = (RelativeLayout) itemView.findViewById(R.id.front);
        back_layout = (RelativeLayout) itemView.findViewById(R.id.back);
        rootLayout = (CardView) itemView.findViewById(R.id.root_layout);
        back_cancel_button = (ImageButton) itemView.findViewById(R.id.back_cancel_button);
        back_submit = (Button) itemView.findViewById(R.id.button_back_submit);*/
    }
}

