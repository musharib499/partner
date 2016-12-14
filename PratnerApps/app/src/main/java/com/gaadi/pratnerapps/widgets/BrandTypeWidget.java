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
 * @author vinodtakhar
 * @version 1.0
 * @since 13/9/16
 */
public class BrandTypeWidget extends BaseCarouselWidget<HomeApiModel.BrandBodyWidgetModel.Item>{
    private HomeApiModel.BrandBodyWidgetModel brandBodyWidgetModel;
    private BrandBodyClickListener brandBodyClickListener;

    public static interface BrandBodyClickListener{
        void onClick(String key);
    }

    public BrandTypeWidget(Context context, HomeApiModel.BrandBodyWidgetModel imagesWidgetModel) {
        super(context);
        this.brandBodyWidgetModel = imagesWidgetModel;

        setShowBottomCard(false);
        getViewPager().setPageMargin(8);
        setIndicatorVisibility(View.GONE);
        setRightButtonVisibility(View.GONE);

        if(imagesWidgetModel.getTitle()!=null)
            setTitle(imagesWidgetModel.getTitle());

        init();
    }

    public BrandBodyClickListener getBrandBodyClickListener() {
        return brandBodyClickListener;
    }

    public void setBrandBodyClickListener(BrandBodyClickListener brandBodyClickListener) {
        this.brandBodyClickListener = brandBodyClickListener;
    }

    @Override
    public float getPageWidth() {
        return 0.33f;
    }

    @Override
    public int getSize() {
        return brandBodyWidgetModel.getItems().size();
    }

    @Override
    public boolean isAutoScroll() {
        return brandBodyWidgetModel.isAutoSlide();
    }

    @Override
    public View getViewAt(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_widget_brand_body,null);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        final HomeApiModel.BrandBodyWidgetModel.Item item = getItemAt(position);

        Utils.loadImage(context, item.getImage_url(), imageView,progressBar);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(brandBodyClickListener!=null)
                    brandBodyClickListener.onClick(item.getKey());
            }
        });
        

        return view;
    }

    @Override
    public HomeApiModel.BrandBodyWidgetModel.Item getItemAt(int position) {
        return brandBodyWidgetModel.getItems().get(position);
    }
}
