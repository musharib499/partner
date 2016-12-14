package com.gaadi.pratnerapps.widgets;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.utils.Logger;
import com.matthewtamlin.sliding_intro_screen_library.DotIndicator;

/**
 * Created by vinodtakhar on 25/5/16.
 */
public abstract class BaseCarouselWidget<T> {
    private static final int INTERVAL_BETWEEN_SCROLL = 5 * 1000;
    private static final String TAG = BaseCarouselWidget.class.getName();
    Context context;
    private View view;
    private ViewPager viewPager;
    private LayoutInflater layoutInflater;
    private DotIndicator dotIndicator;
    private Runnable runnable;
    private TextView tvTitle,tvButtonRight;
    private OnItemClickListener onItemClickListener;
    private LinearLayout layoutTitle;
    private CardView bottomCard,contentCard;

    public BaseCarouselWidget(Context context){
        this.context = context;

        layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(R.layout.widget_base_carousel,null);

        viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        dotIndicator = (DotIndicator)view.findViewById(R.id.dotIndicator);
        tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        tvButtonRight = (TextView)view.findViewById(R.id.btnRight);
        layoutTitle = (LinearLayout)view.findViewById(R.id.layoutTitle);

        bottomCard = (CardView) view.findViewById(R.id.cardBottom);
        contentCard = (CardView) view.findViewById(R.id.contentCard);
    }

    protected void init(){
        dotIndicator.setNumberOfItems(getSize());

        viewPager.setAdapter(new CarouselPagerAdapter());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                dotIndicator.setSelectedItem(position,true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        startAutoScroll();

//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        stopAutoScroll();
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        startAutoScroll();
//                        break;
//                }
//                return false;
//            }
//        });
    }

    private void startAutoScroll(){
        if(isAutoScroll()){
            stopAutoScroll();

            runnable = new Runnable() {
                @Override
                public void run() {
                    if(getSize()>0) {
                        viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % getSize());
                        viewPager.postDelayed(runnable, INTERVAL_BETWEEN_SCROLL);
                    }
                }
            };

            viewPager.postDelayed(runnable,INTERVAL_BETWEEN_SCROLL);
        }
    }

    private void stopAutoScroll(){
        if(isAutoScroll()){
            viewPager.removeCallbacks(runnable);
        }
    }

    public abstract int getSize();
    public abstract boolean isAutoScroll();

    public ViewPager getViewPager() {
        return viewPager;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }

    public void setShowBottomCard(boolean showBottomCard){
        bottomCard.setVisibility(showBottomCard?View.VISIBLE:View.GONE);
        CardView.LayoutParams layoutParams = (CardView.LayoutParams) contentCard.getLayoutParams();
        layoutParams.setMargins(0,0,0,context.getResources().getDimensionPixelSize(R.dimen.margin_medium));
        contentCard.setLayoutParams(layoutParams);
    }

    public void setTitleLayoutVisibility(int visibility){
        layoutTitle.setVisibility(visibility);
    }

    public void setRightButtonTitle(String title){
        tvButtonRight.setText(title);
    }

    public void setRightButtonOnClickListener(View.OnClickListener onClickListener){
        tvButtonRight.setOnClickListener(onClickListener);
    }

    public void setRightButtonVisibility(int visibility){
        tvButtonRight.setVisibility(visibility);
    }

    public View getView() {
        return view;
    }

    public void setHeight(int height){
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height));
    }

    public void setIndicatorVisibility(int visibility){
        dotIndicator.setVisibility(visibility);
    }

    public void onDestroy(){
        stopAutoScroll();
    }

    public abstract View getViewAt(int position);
    public abstract T  getItemAt(int position);

    public float getPageWidth(){
        return 1.0f;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T item);
    }

    private class CarouselPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return getSize();
        }

        @Override
        public Object instantiateItem(ViewGroup container,final int position) {

            final View view = getViewAt(position);

            if(position==0 || viewPager.getHeight()==0){
                DisplayMetrics display = context.getResources().getDisplayMetrics();
                view.measure(display.widthPixels, display.heightPixels);
                ViewGroup.LayoutParams viewGroup = viewPager.getLayoutParams();
                viewGroup.height = view.getMeasuredHeight();
                viewPager.setLayoutParams(viewGroup);
            }

            container.addView(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null)
                        onItemClickListener.onItemClick(getItemAt(position));
                }
            });

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public float getPageWidth(int position) {
            return BaseCarouselWidget.this.getPageWidth();
        }
    }
}
