package com.gaadi.pratnerapps.fragments.buy_car;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.fragments.PartnerBaseFragment;
import com.gaadi.pratnerapps.widgets.BannerViewPager;

import java.util.ArrayList;

/**
 * Created by root on 5/8/16.
 */
public class BuyCarImageGalleryFragment extends PartnerBaseFragment {

    public static  String EXTRA_IMAGES ="extra_images";
    public static  String EXTRA_POSITION ="extra_position";
    private ArrayList<String> imageArray=new ArrayList<>();;
    public BuyCarImageGalleryFragment() {
    }

    public static BuyCarImageGalleryFragment newInstance(ArrayList<String> arrayList, int position)
    {
        Bundle bundle=new Bundle();
        bundle.putStringArrayList(EXTRA_IMAGES,arrayList);
        bundle.putInt(EXTRA_POSITION,position);

        BuyCarImageGalleryFragment buyCarImageGalleryFragment = new BuyCarImageGalleryFragment();
        buyCarImageGalleryFragment.setArguments(bundle);

        return buyCarImageGalleryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null)
        {
            imageArray=getArguments().getStringArrayList(EXTRA_IMAGES);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.layout_buy_car_image_gallery,container,false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FrameLayout frameLayout= (FrameLayout) view.findViewById(R.id.flImageGallery);
        if (imageArray !=null && !imageArray.isEmpty())
            frameLayout.addView(new BannerViewPager(getActivity(),imageArray,getArguments().getInt(EXTRA_POSITION),R.layout.layout_pager_full_item).getView());

    }

}
