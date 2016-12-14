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
 * @since 31/8/16
 */
public class ServicesWidget extends BaseCarouselWidget<String>{
    private HomeApiModel.ServicesWidgetModel servicesWidgetModel;
    private View.OnClickListener onClickListener;

    public void setOnItemClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public ServicesWidget(Context context, HomeApiModel.ServicesWidgetModel servicesWidgetModel) {
        super(context);
        this.servicesWidgetModel = servicesWidgetModel;

        setShowBottomCard(false);
        if(servicesWidgetModel.getTitle()!=null)
            setTitle(servicesWidgetModel.getTitle());
        setIndicatorVisibility(View.GONE);

        setRightButtonTitle("BOOK NOW");

        init();
    }

    @Override
    public int getSize() {
        return servicesWidgetModel.getItems().size();
    }

    @Override
    public boolean isAutoScroll() {
        return false;
    }

    @Override
    public View getViewAt(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_service_image,null);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener!=null){
                    onClickListener.onClick(v);
                }
            }
        });

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        Utils.loadImage(context, getItemAt(position), imageView,progressBar);

        return view;
    }

    @Override
    public String getItemAt(int position) {
        return servicesWidgetModel.getItems().get(position);
    }
}
