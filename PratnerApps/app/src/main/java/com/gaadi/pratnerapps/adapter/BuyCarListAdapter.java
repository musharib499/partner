package com.gaadi.pratnerapps.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.holder.BuyCarListViewHolder;
import com.gaadi.pratnerapps.models.BuyCarDetails;
import com.gaadi.pratnerapps.models.HomeApiModel;
import com.gaadi.pratnerapps.utils.Constants;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.List;

/**
 * Created by root on 26/7/16.
 */
public class BuyCarListAdapter extends RecyclerView.Adapter<BuyCarListViewHolder>{


    private List<BuyCarDetails> buyCarList;
    private Context mContext;
    private String price,year;
    private CarClickListener carClickListener;

    private String styleView = Constants.STYLE_LIST_TYPE;

    public static interface  CarClickListener{
        void onCarDetail(BuyCarDetails buyCarDetails);
        void onCallRequest(BuyCarDetails buyCarDetails);
        void onLoadItem(int position);
    }

    public void setCarClickListener(CarClickListener carClickListener) {
        this.carClickListener = carClickListener;
    }

    public BuyCarListAdapter(Context context, List<BuyCarDetails> buyCarDetailses) {
        mContext=context;
        buyCarList=buyCarDetailses;
        if(ObjectTableUtil.getHomeConfig(mContext).getRecent_listing()!=null) {
            this.styleView = ObjectTableUtil.getHomeConfig(context).getRecent_listing().getConfig().getStyle().getListing_type();
        }
    }


    @Override
    public BuyCarListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        if (styleView!=null && styleView.equals(Constants.STYLE_LIST_TYPE)) {
           itemView= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_search_result_recycle, parent, false);
        }else {
            itemView= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_recycler_hz_recommendedcars, parent, false);

        }

        return new BuyCarListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BuyCarListViewHolder holder, final int position) {

        final BuyCarDetails carsDetails = buyCarList.get(position);
        if (carsDetails.getImages().size() != 0) {
            Utils.loadImage(mContext, carsDetails.getImages().get(0), holder.imgCar);
        }else {
            holder.imgCar.setImageResource(R.drawable.placeholder_img);
        }
        if (!TextUtils.isEmpty(carsDetails.getPrice())){
            price = carsDetails.getPrice().trim();
            holder.tvPrice.setText(Constants.RUPES + " " + Utils.convertCommaIntoNumber(price,"##,##,###"));

        }
       /* holder.tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!carsDetails.getMobile().isEmpty())
                    Utils.callPhone(mContext, carsDetails.getMobile());
            }
        });*/
        holder.tvRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               carClickListener.onCallRequest(carsDetails);
                   }
        });
        StringBuilder stringBuilderMake = new StringBuilder();

        if (!TextUtils.isEmpty(carsDetails.getPrice()))
            stringBuilderMake.append(carsDetails.getMake() + " ");

        if (!TextUtils.isEmpty(carsDetails.getPrice()))
            stringBuilderMake.append(carsDetails.getModel());

        holder.tvMake.setText(stringBuilderMake);

        if (!TextUtils.isEmpty(carsDetails.getCity()))
            holder.tvAddress.setText(carsDetails.getCity());

        StringBuilder stringBuilderYearKill = new StringBuilder();
        if (!TextUtils.isEmpty(carsDetails.getPrice())) {
            stringBuilderYearKill.append(carsDetails.getYear() + " " + Constants.BULLET);
           /* stringBuilderMake.append(" " +  + " ");*/
        }
        if (!TextUtils.isEmpty(carsDetails.getPrice())) {
            year = carsDetails.getKilometerDriven().trim();
            stringBuilderYearKill.append(Utils.convertCommaIntoNumber(year,"##,##,###") + " kms " + Constants.BULLET);

        }

        if (!TextUtils.isEmpty(carsDetails.getFuelType()))
            stringBuilderYearKill.append(carsDetails.getFuelType());

        holder.tvYearKilFuel.setText(stringBuilderYearKill);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carClickListener.onCarDetail(carsDetails);
            }
        });
        carClickListener.onLoadItem(position+1);
    }


    @Override
    public int getItemCount() {
        return buyCarList.size();
    }
}
