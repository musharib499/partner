package com.gaadi.pratnerapps.fragments.insurance_finance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.fragments.PartnerBaseFragment;
import com.gaadi.pratnerapps.models.HomeApiModel;
import com.gaadi.pratnerapps.utils.Utils;
import com.gaadi.pratnerapps.widgets.ImageWidget;

import java.util.ArrayList;

/**
 * Created by priyarawat on 3/8/16.
 */
public abstract class InsuranceFinanceBaseFragment extends PartnerBaseFragment {

    public abstract String  getHeroImage();

    public abstract ArrayList<String> getPartners();

  //  protected ImageView ivHeroImage;
    protected LinearLayout widgetContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.insurance_finance_root_layout, container, false);

      //  ivHeroImage = (ImageView) view.findViewById(R.id.iv_heroImage);
        widgetContainer = (LinearLayout) view.findViewById(R.id.widgetContainer);

        return view;
    }

    public void replaceContentFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.root_container,fragment).commit();
    }

    public void replaceContentFragmentWithBackStack(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.root_container,fragment).addToBackStack(null).commit();
    }

    public void setHeroAndWidget(){
        if (getHeroImage() != null) {
            ArrayList<String> images = new ArrayList<>();
            images.add(getHeroImage());
            getFragmentAdapter().setAppBarImages(images,false);
        }
        else {
            ArrayList<String> emptyImages = new ArrayList<>();
            emptyImages.add("defaultimage");
            getFragmentAdapter().setAppBarImages(emptyImages,false);
        }
        //getFragmentAdapter().setTitleMessage("Finance", true);

     //   Utils.loadImage(getContext(),getHeroImage(), ivHeroImage);
        HomeApiModel.ImagesWidgetModel imagesWidgetModel = new HomeApiModel.ImagesWidgetModel();
        imagesWidgetModel.setItems(getPartners());
        ImageWidget imageWidget = new ImageWidget(getContext(),imagesWidgetModel);
        imageWidget.setTitle("Our Partners");
        if(getPartners()!=null && getPartners().size()>0)
            widgetContainer.addView(imageWidget.getView());
   }
}
