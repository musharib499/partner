package com.gaadi.pratnerapps.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.HomeApiModel;
import com.gaadi.pratnerapps.utils.Utils;

/**
 * Created by vinodtakhar on 4/8/16.
 */
public class ImageWidget extends BaseCarouselWidget<String>{
    private HomeApiModel.ImagesWidgetModel imagesWidgetModel;

    public ImageWidget(Context context, HomeApiModel.ImagesWidgetModel imagesWidgetModel) {
        super(context);
        this.imagesWidgetModel = imagesWidgetModel;

        setShowBottomCard(false);
        getViewPager().setPageMargin(8);
        setIndicatorVisibility(View.VISIBLE);
        setRightButtonVisibility(View.GONE);

        init();
    }

    @Override
    public float getPageWidth() {
        return 0.33f;
    }

    @Override
    public int getSize() {
        return imagesWidgetModel.getItems().size();
    }

    @Override
    public boolean isAutoScroll() {
        return imagesWidgetModel.isAutoSlide();
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
        return imagesWidgetModel.getItems().get(position);
    }
}
