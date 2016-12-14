package com.gaadi.pratnerapps.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;

/**
 * Created by root on 1/8/16.
 */
public class ViewPagerAdapter extends PagerAdapter{
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<String> mImagesArray;
    private ViewPagerItemClickListener viewPagerItemClickListener;

    public void setViewPagerItemClickListener(ViewPagerItemClickListener viewPagerItemClickListener) {
        this.viewPagerItemClickListener = viewPagerItemClickListener;
    }

    public interface ViewPagerItemClickListener{
        void onItemClick(int position);
    }

    public ViewPagerAdapter(Context context,ArrayList<String> imagesArray) {
        mContext = context;
        mImagesArray=imagesArray;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mImagesArray.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    public int getItemView(){
        return R.layout.layout_pager_item;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(getItemView(), container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        Utils.loadImageView(mContext,mImagesArray.get(position),imageView);

        container.addView(itemView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPagerItemClickListener!=null) {
                    viewPagerItemClickListener.onItemClick(position);
                }
            }
        });
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
