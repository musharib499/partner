package com.gaadi.pratnerapps.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.fragments.buy_car.BuyCarBodyTypeFragment;
import com.gaadi.pratnerapps.models.HomeApiModel;
import com.gaadi.pratnerapps.models.ParameterApiModel;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;

/**
 * @author vinodtakhar
 * @version 1.0
 * @since 13/9/16
 */
public class BodyTypeWidget extends BaseCarouselWidget<HomeApiModel.BrandBodyWidgetModel.Item>{
    private HomeApiModel.BrandBodyWidgetModel brandBodyWidgetModel;
    private BrandBodyClickListener brandBodyClickListener;
    private ArrayList<ParameterApiModel.KeyValueModel> bodyTypes;

    public static interface BrandBodyClickListener{
        void onClick(String key);
    }

    public BodyTypeWidget(Context context, HomeApiModel.BrandBodyWidgetModel imagesWidgetModel) {
        super(context);
        this.brandBodyWidgetModel = imagesWidgetModel;

        setShowBottomCard(false);
        getViewPager().setPageMargin(8);
        setIndicatorVisibility(View.GONE);
        setRightButtonVisibility(View.GONE);

        if(imagesWidgetModel.getTitle()!=null)
            setTitle(imagesWidgetModel.getTitle());

        bodyTypes = ObjectTableUtil.getCarBodyTypes(context);

        init();
    }

    private String getValue(String key){
        for(int i=0; i<bodyTypes.size(); i++){
            if(bodyTypes.get(i).getKey().equals(key)){
                return bodyTypes.get(i).getValue();
            }
        }

        return "";
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_widget_body,null);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);

        TextView tvTitle = (TextView)view.findViewById(R.id.tvTitle);

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        final HomeApiModel.BrandBodyWidgetModel.Item item = getItemAt(position);

//        Utils.loadImage(context, item.getImage_url(), imageView,progressBar);
        progressBar.setVisibility(View.GONE);
        imageView.setImageResource(BuyCarBodyTypeFragment.getIcon(item.getKey()));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(brandBodyClickListener!=null)
                    brandBodyClickListener.onClick(item.getKey());
            }
        });

        tvTitle.setText(getValue(item.getKey()));

        return view;
    }

    @Override
    public HomeApiModel.BrandBodyWidgetModel.Item getItemAt(int position) {
        return brandBodyWidgetModel.getItems().get(position);
    }
}
