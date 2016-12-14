package com.gaadi.pratnerapps.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.activities.RequestOtpActivity;
import com.gaadi.pratnerapps.fragments.buy_car.BuyCarDetailsFragment;
import com.gaadi.pratnerapps.fragments.buy_car.BuyCarListFragment;
import com.gaadi.pratnerapps.fragments.service.SelectServiceFragment;
import com.gaadi.pratnerapps.menus.MenuCardWidget;
import com.gaadi.pratnerapps.menus.MenuWidget;
import com.gaadi.pratnerapps.models.BuyCarDetails;
import com.gaadi.pratnerapps.models.HomeApiModel;
import com.gaadi.pratnerapps.models.MenuApiModel;
import com.gaadi.pratnerapps.utils.AppPreferences;
import com.gaadi.pratnerapps.utils.FragmentAdapter;
import com.gaadi.pratnerapps.utils.MenuWidgetAvailableListener;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.gaadi.pratnerapps.widgets.BannerLogo;
import com.gaadi.pratnerapps.widgets.BannerWidget;
import com.gaadi.pratnerapps.widgets.BodyTypeWidget;
import com.gaadi.pratnerapps.widgets.BrandTypeWidget;
import com.gaadi.pratnerapps.widgets.RecentListingWidget;
import com.gaadi.pratnerapps.widgets.ServicesWidget;
import com.gaadi.pratnerapps.widgets.ShowroomWidget;
import com.gaadi.pratnerapps.widgets.TestimonialWidget;

/**
 * Created by vinodtakhar on 1/6/16.
 */
public class HomeFragment extends FabMenuFragment{
    private LinearLayout containerLayout;
    private View widgetViews[] = new View[8];
    private MenuWidgetAvailableListener menuWidgetAvailableListener;
    private FloatingActionButton floatingPopupButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);
        setHasOptionsMenu(true);

        containerLayout = (LinearLayout) view.findViewById(R.id.container);

        getFragmentAdapter().setTitleMessage(getString(R.string.app_name),true);

        addWidgets();

        return view;
    }

    private void addWidgets(){
        MenuApiModel menuApiModel = ObjectTableUtil.getNavigation(getContext());

//        menuApiModel.getConfig().getStyle().setLayout(MenuApiModel.MenuConfig.STYLE_FAB_MENU);

        if(menuApiModel != null && (menuApiModel.getConfig().getMenuStyle().equals(MenuApiModel.MenuConfig.STYLE_MATRIX) || menuApiModel.getConfig().getMenuStyle().equals(MenuApiModel.MenuConfig.STYLE_LIST)))
        {
            MenuWidget menuWidget = new MenuCardWidget(getContext(), menuApiModel);

            View view = menuWidget.getView();
            CardView cardView = (CardView) view.findViewById(R.id.contentCard);
            PartnerApplication partnerApplication = (PartnerApplication) getContext().getApplicationContext();
            if (!menuApiModel.getConfig().getMenuStyle().equals(MenuApiModel.MenuConfig.STYLE_LIST)) {
                String background = partnerApplication.getThemeProperty("demarcation_line_color");

                if (background != null)
                    cardView.setCardBackgroundColor(Color.parseColor(background));

            }
            containerLayout.addView(view);

            if(menuWidgetAvailableListener!=null){
                menuWidgetAvailableListener.onMenuWidgetAvailable(menuWidget);
            }
        }else if(menuApiModel.getConfig().getMenuStyle().equals(MenuApiModel.MenuConfig.STYLE_FAB_MENU)){
            MenuWidget menuWidget = new MenuCardWidget(getContext(), menuApiModel);
            initPopupMenu(menuWidget.getView());

            if(menuWidgetAvailableListener!=null){
                menuWidgetAvailableListener.onMenuWidgetAvailable(menuWidget);
            }

            if (menuWidget.getMenusAdapter().getiNavigationListener()!=null) {
                floatingPopupButton = menuWidget.getMenusAdapter().getiNavigationListener().getFabMenu();

                floatingPopupButton.setVisibility(View.VISIBLE);

                floatingPopupButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPopupMenu(v);
                    }
                });
            }
        }

        HomeApiModel homeApiModel = ObjectTableUtil.getHomeConfig(getContext());

        if(homeApiModel.getBanner()!=null && homeApiModel.getBanner().hasItems())
            widgetViews[homeApiModel.getBanner().getOrder()-1]=getBannerWidget(homeApiModel.getBanner());

        if(homeApiModel.getShowroom()!=null && homeApiModel.getShowroom().hasItems())
            widgetViews[homeApiModel.getShowroom().getOrder()-1] = getShowroomWidget(homeApiModel.getShowroom());

        if(homeApiModel.getRecent_listing()!=null)
            widgetViews[homeApiModel.getRecent_listing().getOrder()-1]=getRecentListingWidget(homeApiModel.getRecent_listing());

        if(homeApiModel.getTestimonial()!=null)
            widgetViews[homeApiModel.getTestimonial().getOrder()-1] = getTestimonialWidget(homeApiModel.getTestimonial());

        if(homeApiModel.getBrands()!=null && homeApiModel.getBrands().hasItems())
            widgetViews[homeApiModel.getBrands().getOrder()-1]=getBrandTypeWidget(homeApiModel.getBrands());

        if(homeApiModel.getBody()!=null && homeApiModel.getBody().hasItems())
            widgetViews[homeApiModel.getBody().getOrder()-1]=getBodyTypeWidget(homeApiModel.getBody());

        if(homeApiModel.getServices()!=null && homeApiModel.getServices().hasItems())
            widgetViews[homeApiModel.getServices().getOrder()-1]=getServicesWidget(homeApiModel.getServices());

        for(int i=0;i<widgetViews.length;i++){
            if(widgetViews[i]!=null)
                containerLayout.addView(widgetViews[i]);
        }

        containerLayout.addView(BannerLogo.imageView(getContext()));
    }

    private View getServicesWidget(HomeApiModel.ServicesWidgetModel servicesWidgetModel) {
        ServicesWidget servicesWidget = new ServicesWidget(getContext(),servicesWidgetModel);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentAdapter().addToBackStack(new SelectServiceFragment().setFragmentAdapter(getFragmentAdapter()),false);
            }
        };

        servicesWidget.setRightButtonOnClickListener(onClickListener);
        servicesWidget.setOnItemClickListener(onClickListener);

        return  servicesWidget.getView();
    }

    private View getBrandTypeWidget(HomeApiModel.BrandBodyWidgetModel brandBodyWidgetModel) {
        BrandTypeWidget brandBodyTypeWidget = new BrandTypeWidget(getContext(),brandBodyWidgetModel);

        brandBodyTypeWidget.setBrandBodyClickListener(new BrandTypeWidget.BrandBodyClickListener() {
            @Override
            public void onClick(String key) {
                Bundle bundle = new Bundle();
                bundle.putInt(BuyCarListFragment.EXTRA_BRAND_TYPE,Integer.parseInt(key));
                getFragmentAdapter().addToBackStack(BuyCarListFragment.newInstance(bundle), false);
            }
        });

        return brandBodyTypeWidget.getView();
    }


    private View getBodyTypeWidget(HomeApiModel.BrandBodyWidgetModel brandBodyWidgetModel) {
        BodyTypeWidget bodyTypeWidget = new BodyTypeWidget(getContext(),brandBodyWidgetModel);

        bodyTypeWidget.setBrandBodyClickListener(new BodyTypeWidget.BrandBodyClickListener() {
            @Override
            public void onClick(String key) {
                Bundle bundle = new Bundle();
                bundle.putString(BuyCarListFragment.EXTRA_BODY_TYPE,key);
                getFragmentAdapter().addToBackStack(BuyCarListFragment.newInstance(bundle), false);
            }
        });

        return bodyTypeWidget.getView();
    }

    private View getBannerWidget(HomeApiModel.BannerWidgetModel banner) {
        return new BannerWidget(getContext(),banner).getView();
    }

    private View getShowroomWidget(HomeApiModel.ShowroomWidgetModel showroom){
        ShowroomWidget showroomGalleryWidget = new ShowroomWidget(getContext(),showroom);

        showroomGalleryWidget.setRightButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentAdapter().addToBackStack(new ShowroomFragment().setFragmentAdapter(getFragmentAdapter()),false);
            }
        });

        return showroomGalleryWidget.getView();
    }

    private View getTestimonialWidget(HomeApiModel.TestimonialWidgetModel testimonial){
        TestimonialWidget testimonialWidget = new TestimonialWidget(getContext(),testimonial);

        testimonialWidget.setRightButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentAdapter().addToBackStack(new TestimonialFragment().setFragmentAdapter(getFragmentAdapter()), false);
            }
        });

        return testimonialWidget.getView();
    }

    private View getRecentListingWidget(HomeApiModel.RecentListingWidgetModel recent_listing){
        RecentListingWidget recentListingWidget = new RecentListingWidget(getContext(),recent_listing);

        recentListingWidget.setRightButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentAdapter().addToBackStack(BuyCarListFragment.newInstance().setFragmentAdapter(getFragmentAdapter()), false);
            }
        });
        recentListingWidget.setOnCarClickListener(new RecentListingWidget.OnCarClickListener() {
            @Override
            public void onCarClick(BuyCarDetails searchCarDetails) {
                getFragmentAdapter().addToBackStack(BuyCarDetailsFragment.newInstance(searchCarDetails).setFragmentAdapter(getFragmentAdapter()), true);
            }
        });

        return recentListingWidget.getView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onResume() {
        super.onResume();

        if(floatingPopupButton!=null){
            floatingPopupButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if(floatingPopupButton!=null){
            floatingPopupButton.setVisibility(View.GONE);
            dismissPopupMenu();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.search){
            getFragmentAdapter().addToBackStack(BuyCarListFragment.newInstance().setFragmentAdapter(getFragmentAdapter()), false);
        }
        else if(item.getItemId() == R.id.profile)
        {
            if (AppPreferences.getBooleanSharedPreference(getActivity(),AppPreferences.DEALER_VERIFIED,false)) {
                getFragmentAdapter().addToBackStack(new DealerProfileFragment().setFragmentAdapter((FragmentAdapter) getActivity()), false);
            }else {
                startActivity(new Intent(getActivity(),RequestOtpActivity.class));

            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void setMenuWidgetAvailableListener(MenuWidgetAvailableListener menuWidgetAvailableListener) {
        this.menuWidgetAvailableListener = menuWidgetAvailableListener;
    }

}
