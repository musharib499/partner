package com.gaadi.pratnerapps.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.ViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by mushareb ali on 1/8/16.
 */
public class BannerViewPager {

    private ViewPager viewPager;
    private ArrayList<String> images;
    private Context context;
    private View view;
    private ImageView ivLeft,ivRight;
    private ViewPagerAdapter   adapter;

    public BannerViewPager(Context context, ArrayList<String> arrayList,int itemView){
        this(context,arrayList,0,itemView);
    }

    public BannerViewPager(Context context, ArrayList<String> arrayList,int position,final int itemView) {
        this.context = context;
        this.images = arrayList;


        view = LayoutInflater.from(context).inflate(R.layout.layout_view_pager,null);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        ivRight = (ImageView) view.findViewById(R.id.ivRightId);
        ivLeft = (ImageView) view.findViewById(R.id.ivLeftId);
        // final DotIndicator dotIndicator=(DotIndicator) view.findViewById(R.id.dotIndicator);
        if (arrayList.size()>1)
        {
            ivLeft.setVisibility(View.VISIBLE);
            ivRight.setVisibility(View.VISIBLE);
        }

        adapter = new ViewPagerAdapter(context, arrayList){
            @Override
            public int getItemView() {
                return itemView;
            }
        };

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);

        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousPage();
            }
        });
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage();
            }
        });

        // dotIndicator.setNumberOfItems(arrayList.size());
      /*  viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
               // dotIndicator.setSelectedItem(position,true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });*/

    }

    public void setViewPagerItemClickListener(ViewPagerAdapter.ViewPagerItemClickListener viewPagerItemClickListener) {
        adapter.setViewPagerItemClickListener(viewPagerItemClickListener);
    }

    public View getView(){
        return view;
    }

    private void nextPage() {
        int currentPage = viewPager.getCurrentItem();
        int totalPages = viewPager.getAdapter().getCount();

        int nextPage = currentPage+1;
        if (nextPage >= totalPages) {
            // We can't go forward anymore.
            // Loop to the first page. If you don't want looping just
            // return here.
            nextPage = 0;
        }

        viewPager.setCurrentItem(nextPage, true);
    }

    private void previousPage() {
        int currentPage = viewPager.getCurrentItem();
        int totalPages = viewPager.getAdapter().getCount();

        int previousPage = currentPage-1;
        if (previousPage < 0) {
            // We can't go back anymore.
            // Loop to the last page. If you don't want looping just
            // return here.
            previousPage = totalPages - 1;
        }

        viewPager.setCurrentItem(previousPage, true);
    }
}
