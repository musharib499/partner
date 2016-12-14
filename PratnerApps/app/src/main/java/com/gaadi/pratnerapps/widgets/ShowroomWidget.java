package com.gaadi.pratnerapps.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.HomeApiModel;
import com.gaadi.pratnerapps.models.ShowroomApiModel;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;

/**
 * Created by vinodtakhar on 25/5/16.
 */
public class ShowroomWidget extends BaseCarouselWidget<String>{
    private HomeApiModel.ShowroomWidgetModel showroomWidgetModel;
    private boolean autoScroll = false;

    public ShowroomWidget(Context context, HomeApiModel.ShowroomWidgetModel showroomWidgetModel) {
        super(context);
        this.showroomWidgetModel = showroomWidgetModel;

        setShowBottomCard(false);
        if(showroomWidgetModel.getTitle()!=null)
            setTitle(showroomWidgetModel.getTitle());

        init();
    }

    public ShowroomWidget(Context context,boolean autoScroll,ArrayList<String> images) {
        super(context);
        this.showroomWidgetModel = new HomeApiModel.ShowroomWidgetModel();
        this.showroomWidgetModel.setItems(images);
        this.autoScroll = autoScroll;

        setShowBottomCard(false);
        setTitleLayoutVisibility(View.GONE);

        init();
    }

    @Override
    public int getSize() {
        return showroomWidgetModel.getItems().size();
    }

    @Override
    public boolean isAutoScroll() {
        return showroomWidgetModel.isAutoSlide() || autoScroll;
    }

    @Override
    public View getViewAt(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_showroom_image,null);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        Utils.loadImage(context, getItemAt(position), imageView,progressBar);

        return view;
    }

    @Override
    public String getItemAt(int position) {
        return showroomWidgetModel.getItems().get(position);
    }
}
