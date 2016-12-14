package com.gaadi.pratnerapps.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.BuyCarDetails;
import com.gaadi.pratnerapps.models.BuyCarIInfo;
import com.gaadi.pratnerapps.models.HomeApiModel;
import com.gaadi.pratnerapps.utils.Constants;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;

/**
 * Created by vinodtakhar on 25/5/16.
 */
public class RecentListingWidget extends BaseCarouselWidget {
    private ArrayList<BuyCarDetails> items;
    private OnCarClickListener onCarClickListener;
    private String price,kms;
    private HomeApiModel.RecentListingWidgetModel recentListingWidgetModel;

    public RecentListingWidget(Context context, HomeApiModel.RecentListingWidgetModel recentListingWidgetModel) {
        super(context);
        this.items = ObjectTableUtil.getRecentListingModel(context).getItems();
        this.recentListingWidgetModel = recentListingWidgetModel;

        if(recentListingWidgetModel.getTitle()!=null)
            setTitle(recentListingWidgetModel.getTitle());
        setShowBottomCard(true);
        setIndicatorVisibility(View.GONE);

        init();

        getViewPager().setPageTransformer(true,new DepthPageTransformer());
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public boolean isAutoScroll() {
        return recentListingWidgetModel.isAutoSlide();
    }

    @Override
    public View getViewAt(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_recommendedcars,null);

        final BuyCarDetails searchCarDetails = getItemAt(position);

        ViewHolder viewHolder = new ViewHolder(view);

        if(searchCarDetails.getImages()!=null && searchCarDetails.getImages().size()>0)
            Utils.loadImage(context, searchCarDetails.getImages().get(0), viewHolder.imgCar);
        else
            viewHolder.imgCar.setImageResource(R.drawable.placeholder_img);

        viewHolder.tvAddress.setText(searchCarDetails.getCity());
        viewHolder.tvMake.setText(searchCarDetails.getMake() + " " + searchCarDetails.getModel());
        price = searchCarDetails.getPrice().trim();
        viewHolder.tvPrice.setText(Constants.RUPES + " " + Utils.convertCommaIntoNumber(price, "##,##,###"));
        kms = searchCarDetails.getKilometerDriven().trim();
        viewHolder.tvYearKilFuel.setText(" " + searchCarDetails.getYear() + Constants.BULLET + Utils.convertCommaIntoNumber(kms,"##,##,###") + " Kms" + Constants.BULLET + searchCarDetails.getFuelType());

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCarClickListener.onCarClick(searchCarDetails);
            }
        };

        viewHolder.view.setOnClickListener(onClickListener);
        viewHolder.imgCar.setOnClickListener(onClickListener);
        viewHolder.tvYearKilFuel.setOnClickListener(onClickListener);
        viewHolder.tvAddress.setOnClickListener(onClickListener);
        viewHolder.tvMake.setOnClickListener(onClickListener);
        viewHolder.tvPrice.setOnClickListener(onClickListener);

        view.setTag(viewHolder);

        return view;
    }

    @Override
    public BuyCarDetails getItemAt(int position) {
        return items.get(position);
    }

    public OnCarClickListener getOnCarClickListener() {
        return onCarClickListener;
    }

    public void setOnCarClickListener(OnCarClickListener onCarClickListener) {
        this.onCarClickListener = onCarClickListener;
    }

    public interface OnCarClickListener {
        void onCarClick(BuyCarDetails searchCarDetails);
    }

    private static class ViewHolder{
        ImageView imgCar;
        TextView tvMake,tvPrice,tvAddress,tvYearKilFuel;
        RelativeLayout layoutBottom;
        View view;

        ViewHolder(View view){
            this.view = view;
            imgCar = (ImageView)view.findViewById(R.id.imgCar);
            tvMake = (TextView)view.findViewById(R.id.tvMake);
            tvAddress = (TextView)view.findViewById(R.id.tvAddress);
            tvPrice = (TextView)view.findViewById(R.id.tvPrice);
            tvYearKilFuel = (TextView)view.findViewById(R.id.tvYearKilFuel);
            layoutBottom = (RelativeLayout) view.findViewById(R.id.layoutRequestCall);

            layoutBottom.setVisibility(View.GONE);
        }
    }
}
