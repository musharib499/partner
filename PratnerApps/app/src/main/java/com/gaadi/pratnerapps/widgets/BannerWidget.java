package com.gaadi.pratnerapps.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.HomeApiModel;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;

/**
 * Created by vinodtakhar on 26/5/16.
 */
public class BannerWidget extends BaseCarouselWidget<String>{
    private HomeApiModel.BannerWidgetModel bannerWidgetModel;

    public BannerWidget(Context context, HomeApiModel.BannerWidgetModel bannerWidgetModel) {
        super(context);
        this.bannerWidgetModel = bannerWidgetModel;

        setShowBottomCard(false);
        setTitleLayoutVisibility(View.GONE);
        setIndicatorVisibility(View.GONE);

        init();
    }

    @Override
    public int getSize() {
        return bannerWidgetModel.getItems().size();
    }

    @Override
    public boolean isAutoScroll() {
        return bannerWidgetModel.isAutoSlide();
    }

    @Override
    public View getViewAt(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner_image,null);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        Utils.loadImage(context, getItemAt(position), imageView,progressBar);

        return view;
    }

    @Override
    public String getItemAt(int position) {
        return bannerWidgetModel.getItems().get(position);
    }
}
