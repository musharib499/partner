package com.gaadi.pratnerapps.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;

/**
 * Created by Mushareb Ali on 13/4/16.
 */
public class RecommendedVehicleViewHolder extends RecyclerView.ViewHolder {
    public TextView tvMake, tvAddress, tvPrice, tv, tvYearKilFuel;
    public ImageView imgCar;

    public RecommendedVehicleViewHolder(View view) {
        super(view);
        tvMake = (TextView) view.findViewById(R.id.tvMake);
        tvAddress = (TextView) view.findViewById(R.id.tvAddress);
        tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        tvYearKilFuel = (TextView) view.findViewById(R.id.tvYearKilFuel);
        imgCar = (ImageView) view.findViewById(R.id.imgCar);

    }
}
