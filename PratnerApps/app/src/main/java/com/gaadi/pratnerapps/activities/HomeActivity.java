package com.gaadi.pratnerapps.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaadi.pratnerapps.PartnerApplication;
import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.adapter.ViewPagerAdapter;
import com.gaadi.pratnerapps.fragments.ContactFragment;
import com.gaadi.pratnerapps.fragments.DealerProfileFragment;
import com.gaadi.pratnerapps.fragments.HomeFragment;
import com.gaadi.pratnerapps.fragments.NotificationFragment;
import com.gaadi.pratnerapps.fragments.ReferFriendFragment;
import com.gaadi.pratnerapps.fragments.SellCarsFragment;
import com.gaadi.pratnerapps.fragments.ShowroomFragment;
import com.gaadi.pratnerapps.fragments.TestimonialFragment;
import com.gaadi.pratnerapps.fragments.buy_car.BuyCarImageGalleryFragment;
import com.gaadi.pratnerapps.fragments.buy_car.BuyCarListFragment;
import com.gaadi.pratnerapps.fragments.insurance_finance.FinanceFragment;
import com.gaadi.pratnerapps.fragments.insurance_finance.InsuranceFragment;
import com.gaadi.pratnerapps.fragments.service.SelectServiceFragment;
import com.gaadi.pratnerapps.fragments.service.ServiceDetailsFragment;
import com.gaadi.pratnerapps.fragments.ucv.UsedCarValuationFragment;
import com.gaadi.pratnerapps.holder.MenuItemViewHolder;
import com.gaadi.pratnerapps.menus.MenuWidget;
import com.gaadi.pratnerapps.menus.MenusBaseAdapter;
import com.gaadi.pratnerapps.models.BaseResponseModel;
import com.gaadi.pratnerapps.models.HomeApiModel;
import com.gaadi.pratnerapps.models.MenuApiModel;
import com.gaadi.pratnerapps.retrofit.RestApiCalls;
import com.gaadi.pratnerapps.utils.AppPreferences;
import com.gaadi.pratnerapps.utils.CommonUtils;
import com.gaadi.pratnerapps.utils.FragmentAdapter;
import com.gaadi.pratnerapps.utils.Logger;
import com.gaadi.pratnerapps.utils.MenuWidgetAvailableListener;
import com.gaadi.pratnerapps.utils.ObjectTableUtil;
import com.gaadi.pratnerapps.utils.Utils;
import com.gaadi.pratnerapps.widgets.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseCollapsingWithNavigationViewActivity implements FragmentAdapter, MenusBaseAdapter.INavigationListener,MenuWidgetAvailableListener {

    public static final String EXTRA_NOTIFICATION = "extra_notification";
    private final String TAG = "HomeActivity";
    public View appBarFrameLayout;
    private ImageView ivCar;
    private FragmentManager fragmentManager;
    String tintColor;
    private String currentMenuStyle = MenuApiModel.MenuConfig.STYLE_HUMBERGER;
    private List<MenuApiModel.MenuModel> moreMenuList;
    protected MenuWidget menuWidget;
    protected boolean hasMatrixOrList;
    private View imagesView;
    protected FrameLayout frameLayoutImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appBarFrameLayout = getLayoutInflater().inflate(R.layout.layout_image_view, appBarCollapsingContainer);
        ivCar = (ImageView) appBarFrameLayout.findViewById(R.id.imgAppBarView);
        frameLayoutImages = (FrameLayout) appBarFrameLayout.findViewById(R.id.frameLayoutImages);

        PartnerApplication partnerApplication = (PartnerApplication) this.getApplicationContext();
        tintColor = partnerApplication.getThemeProperty("title_color_icon_color");
        fragmentManager = getSupportFragmentManager();
        showDefaultAppBarImage();

        loadMenus();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Logger.e(TAG, "BackStackCount:" + fragmentManager.getBackStackEntryCount());

                if (fragmentManager.getBackStackEntryCount() == 0 ) {
                    if(currentMenuStyle.equals(MenuApiModel.MenuConfig.STYLE_HUMBERGER))
                        mDrawerToggle.setDrawerIndicatorEnabled(true);
                    else
                    {
                        mDrawerToggle.setDrawerIndicatorEnabled(false);
                        Drawable drawable = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                            drawable = getResources().getDrawable(R.drawable.ic_launcher, null);
                        } else
                            drawable = ContextCompat.getDrawable(HomeActivity.this, R.drawable.ic_launcher);

                       /* if (tintColor != null)
                            drawable.mutate().setColorFilter(Color.parseColor(tintColor), PorterDuff.Mode.SRC_IN);*/

                        mDrawerToggle.setHomeAsUpIndicator(drawable);
                        if(currentMenuStyle.equals(MenuApiModel.MenuConfig.STYLE_BOTTOM))
                        bottomMenusLayout.setVisibility(View.VISIBLE);
                    }
                } else {
                    bottomMenusLayout.setVisibility(View.GONE);
                    mDrawerToggle.setDrawerIndicatorEnabled(false);
                    Drawable drawable = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        drawable = getResources().getDrawable(R.drawable.ic_arrow_back, null);
                    } else
                        drawable = ContextCompat.getDrawable(HomeActivity.this, R.drawable.ic_arrow_back);

                    if (tintColor != null)
                        drawable.mutate().setColorFilter(Color.parseColor(tintColor), PorterDuff.Mode.SRC_IN);

                    mDrawerToggle.setHomeAsUpIndicator(drawable);
                }
            }
        });

        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setMenuWidgetAvailableListener(this);
        replaceFragment(homeFragment.setFragmentAdapter(this), true);
    }

    private void loadMenus() {
        MenuApiModel menuApiModel = ObjectTableUtil.getNavigation(this);
        currentMenuStyle = menuApiModel.getConfig().getMenuStyle();
        switch (menuApiModel.getConfig().getMenuStyle()) {
            case MenuApiModel.MenuConfig.STYLE_HUMBERGER:
                menuWidget = new MenuWidget(HomeActivity.this, menuApiModel);
                navigationView.addView(menuWidget.getView());
                setDrawerState(true);
                break;
            case MenuApiModel.MenuConfig.STYLE_BOTTOM:
                menuWidget = new MenuWidget(HomeActivity.this, menuApiModel);
                bottomMenusLayout.addView(menuWidget.getView());
                setDrawerState(false);
                break;
            default:
                setDrawerState(false);
               // bottomMenusLayout.setVisibility(View.GONE);
                break;
        }

        if(menuWidget!=null)
            menuWidget.getMenusAdapter().setINavigationListener(this);
    }

    public void setDrawerState(boolean isEnabled) {
        if (isEnabled) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            //mDrawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerToggle.syncState();
            bottomMenusLayout.setVisibility(View.GONE);

        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            // mDrawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            mDrawerToggle.syncState();
            Drawable drawable = null;
            if (currentMenuStyle.equals(MenuApiModel.MenuConfig.STYLE_BOTTOM)) {
                bottomMenusLayout.setVisibility(View.VISIBLE);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    drawable = getResources().getDrawable(R.drawable.ic_launcher, null);
                } else
                    drawable = ContextCompat.getDrawable(HomeActivity.this, R.drawable.ic_launcher);
            }
            else
            {
                bottomMenusLayout.setVisibility(View.GONE);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    drawable = getResources().getDrawable(R.drawable.ic_launcher, null);
                } else
                    drawable = ContextCompat.getDrawable(HomeActivity.this, R.drawable.ic_launcher);
            }
           /* if (tintColor != null)
                drawable.mutate().setColorFilter(Color.parseColor(tintColor), PorterDuff.Mode.SRC_IN);*/

            mDrawerToggle.setHomeAsUpIndicator(drawable);

        }
    }

    @Override
    protected void showDefaultAppBarImage() {
        HomeApiModel homeApiModel = ObjectTableUtil.getHomeConfig(this);

        if (homeApiModel.getHero() != null && homeApiModel.getHero().getItems() != null && homeApiModel.getHero().getItems().size() > 0)
            setAppBarImage(homeApiModel.getHero().getItems().get(0).getImage_url(), true);

    }

    private void setTitleMessageWithExpandStatus(String title, boolean isExpand) {
        setExpandCollapse(isExpand);
        setTitleMessage(title);

    }

    private void setExpandSubTitle(boolean showSubTitle) {
        HomeApiModel homeApiModel = ObjectTableUtil.getHomeConfig(this);

        LinearLayout linearLayout = (LinearLayout) appBarFrameLayout.findViewById(R.id.layoutTitle);
        TextView textTitle = (TextView) appBarFrameLayout.findViewById(R.id.tvTitle);
        TextView textSubTitle = (TextView) appBarFrameLayout.findViewById(R.id.tvSubTitle);

        if (showSubTitle) {
            collapsingToolbarLayout.setTitleEnabled(false);
            if (linearLayout.getVisibility() == View.GONE)
                linearLayout.setVisibility(View.VISIBLE);
            if ("S.R.C. MOTORS".equalsIgnoreCase(getString(R.string.app_name)))
                textTitle.setText(""/*getString(R.string.welcome_message) + " Your Dream Car Zone" */);
            else
            textTitle.setText(getString(R.string.welcome_message) + " " + getString(R.string.app_name).toUpperCase());

            if (homeApiModel.getHero() != null && homeApiModel.getHero().getItems() != null && homeApiModel.getHero().getItems().size() > 0)
                textSubTitle.setText("" + homeApiModel.getHero().getItems().get(0).getImage_text());
        } else {
            collapsingToolbarLayout.setTitleEnabled(true);
            if (linearLayout.getVisibility() == View.VISIBLE)
                linearLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:

                if (fragmentManager.getBackStackEntryCount() == 0) {
                    if (currentMenuStyle.equals(MenuApiModel.MenuConfig.STYLE_HUMBERGER)) {
                      Utils.hideKeypad(this);

                        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                            mDrawerLayout.closeDrawer(Gravity.LEFT);
                        } else {
                            mDrawerLayout.openDrawer(Gravity.LEFT);
                        }
                    }
                } else {

                    onBackPressed();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void initializeMoreOptions() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_list_menu);
        CommonUtils.recyclerView(recyclerView, this, true).setAdapter(new MenuMoreOptionsAdapter());
    }

    @Override
    public void onMenuItemSelected(String key) {
/*
        if(menuItem.isChecked())
            menuItem.setChecked(false);
        else
            menuItem.setChecked(true);*/

        mDrawerLayout.closeDrawers();

        if (menuFrameLayout.getVisibility() == View.VISIBLE) {
            menuFrameLayout.setVisibility(View.GONE);
        }

        switch (key) {
            case MenusBaseAdapter.MENU_HOME:
                hasMatrixOrListForMenus(new HomeFragment().setFragmentAdapter(this), hasMatrixOrList);
                break;
            case MenusBaseAdapter.MENU_SELL_CAR:
                hasMatrixOrListForMenus(SellCarsFragment.newInstance().setFragmentAdapter(this), hasMatrixOrList);
                break;
//            case R.id.menu_about_us:
//                replaceFragment(new AboutUsFragment(), false);
//                return true;
            case MenusBaseAdapter.MENU_TESTIMONIAL:
                hasMatrixOrListForMenus(new TestimonialFragment().setFragmentAdapter(this), hasMatrixOrList);
                break;
            case MenusBaseAdapter.MENU_CONTACT_US:
                hasMatrixOrListForMenus(ContactFragment.newInstance().setFragmentAdapter(this), hasMatrixOrList);
                break;
            case MenusBaseAdapter.MENU_PROFILE:

                if (AppPreferences.getBooleanSharedPreference(this, AppPreferences.DEALER_VERIFIED, hasMatrixOrList)) {
                    hasMatrixOrListForMenus(new DealerProfileFragment().setFragmentAdapter(this), hasMatrixOrList);
                } else {
                    startActivity(new Intent(this, RequestOtpActivity.class));

                }
                break;
            case MenusBaseAdapter.MENU_SHOWROOM:
                hasMatrixOrListForMenus(ShowroomFragment.newInstance().setFragmentAdapter(this), hasMatrixOrList);
                break;
            case MenusBaseAdapter.MENU_BUY_CAR:
                hasMatrixOrListForMenus(BuyCarListFragment.newInstance().setFragmentAdapter(this), hasMatrixOrList);
                break;
            case MenusBaseAdapter.MENU_SHARE:
                hasMatrixOrListForMenus(new ReferFriendFragment().setFragmentAdapter(this), hasMatrixOrList);
                break;
            case MenusBaseAdapter.MENU_NOTIFICATION:
                hasMatrixOrListForMenus(new NotificationFragment().setFragmentAdapter(this), hasMatrixOrList);
                break;
            case MenusBaseAdapter.MENU_CANCEL:
                menuFrameLayout.setVisibility(View.GONE);
                break;
            case MenusBaseAdapter.MENU_LOG_OUT:
               logOut(this);
                break;
            case MenusBaseAdapter.MENU_FINANCE:
                hasMatrixOrListForMenus(new FinanceFragment().setFragmentAdapter(this), hasMatrixOrList);
                break;
            case MenusBaseAdapter.MENU_INSURANCE:
                hasMatrixOrListForMenus(new InsuranceFragment().setFragmentAdapter(this), hasMatrixOrList);
                break;
            case MenusBaseAdapter.MENU_UCV:
                hasMatrixOrListForMenus(new UsedCarValuationFragment().setFragmentAdapter(this),hasMatrixOrList);
                break;
            case "Book A Service":
            case MenusBaseAdapter.MENU_SERVICE:
                hasMatrixOrListForMenus(new SelectServiceFragment(),hasMatrixOrList);
                break;

        }
    }


    public  void logOut(Context context)
    {
        showProgress(context,"Please wait...");

        RestApiCalls.logout(HomeActivity.this).enqueue(new Callback<BaseResponseModel>() {
            @Override
            public void onResponse(Call<BaseResponseModel> call, Response<BaseResponseModel> response) {
                hideProgress();

                if(response.isSuccessful() && response.body()!=null){
                    if(response.body().isSuccess()){
                        AppPreferences.DeleteSharedPreference(getApplicationContext());
                        ObjectTableUtil.setUserModel(HomeActivity.this, null);
                        startActivity(new Intent(HomeActivity.this, SplashActivity.class));
                        HomeActivity.this.finish();
                    }else{
                        showToast(response.body().getMessage());
                    }
                }else{
                    showToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<BaseResponseModel> call, Throwable t) {
                hideProgress();
                showToast(t.getMessage());
            }
        });

    }

    @Override
    public void setMoreOptions(List<MenuApiModel.MenuModel> arrayList,boolean isListOrMatrix) {
        moreMenuList = arrayList;
        hasMatrixOrList = isListOrMatrix;

        if (isListOrMatrix) {
            MenuApiModel.MenuModel menuModel=new MenuApiModel.MenuModel();
            menuModel.setKey("cancel");
            menuModel.setValue("Close");
            moreMenuList.add(menuModel);
        }

        Log.e(TAG,"more options count:"+moreMenuList.size());

        initializeMoreOptions();
    }

    @Override
    public void closeMoreOptions() {
        Animation  bottomDown = AnimationUtils.loadAnimation(this,R.anim.bottom_down);
        menuFrameLayout.setAnimation(bottomDown);
        menuFrameLayout.setVisibility(View.GONE);
    }

    @Override
    public void showMoreOptions() {
        Animation bottomUp = AnimationUtils.loadAnimation(this,R.anim.bottom_up);
        menuFrameLayout.setAnimation(bottomUp);
        menuFrameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public FloatingActionButton getFabMenu() {
        return fabHome;
    }

    @Override
    public void replaceFragment(Fragment fragment, boolean showAppBar) {
        Fragment oldFragment = fragmentManager.findFragmentByTag(fragment.getClass().getName());

        if (oldFragment != null) {
            fragmentManager.beginTransaction().remove(oldFragment).commit();
        }

        fragmentManager
                .beginTransaction()
                .replace(mainContainer.getId(), fragment, fragment.getClass().getName())
                .commit();

        setExpandCollapse(showAppBar);
    }

    @Override
    public void addToBackStack(Fragment fragment, boolean showAppBar) {

        Fragment oldFragment = fragmentManager.findFragmentByTag(fragment.getClass().getName());

        if (oldFragment != null) {
            fragmentManager.beginTransaction().remove(oldFragment).commit();
        }

        fragmentManager
                .beginTransaction()
                .replace(mainContainer.getId(), fragment, fragment.getClass().getName())
                .addToBackStack(null)
                .commit();

        setExpandCollapse(showAppBar);
    }


    public void hasMatrixOrListForMenus(Fragment fragment, boolean hasMatrixOrList) {

        Fragment oldFragment = fragmentManager.findFragmentByTag(fragment.getClass().getName());

        if (oldFragment != null) {
            fragmentManager.beginTransaction().remove(oldFragment).commit();
        }

        if(hasMatrixOrList)
        fragmentManager
                .beginTransaction()
                .replace(mainContainer.getId(), fragment, fragment.getClass().getName())
                .addToBackStack(null)
                .commit();
        else
            fragmentManager
                    .beginTransaction()
                    .replace(mainContainer.getId(), fragment, fragment.getClass().getName())
                    .commit();

    }

    @Override
    public void popFragment(boolean showAppBar) {
        fragmentManager.popBackStack();
        setExpandCollapse(showAppBar);
    }


    @Override
    public void showHome() {
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setMenuWidgetAvailableListener(this);
        replaceFragment(homeFragment.setFragmentAdapter(this), true);
    }

    @Override
    public void setTitleMessage(String message, boolean showAppBar) {
        setTitleMessageWithExpandStatus(message, showAppBar);
    }


    @Override
    public void setAppBarImage(String url, boolean showSubTitle) {

        if (ivCar != null)
            Utils.loadImage(this, url, ivCar);

        if(imagesView!=null){
            frameLayoutImages.removeView(imagesView);
            imagesView=null;
        }

        setExpandSubTitle(showSubTitle);
    }

    @Override
    public void setAppBarImages(final ArrayList<String> images) {
        BannerViewPager bannerViewPager = new BannerViewPager(this,images,R.layout.layout_pager_item);
        bannerViewPager.setViewPagerItemClickListener(new ViewPagerAdapter.ViewPagerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                addToBackStack(BuyCarImageGalleryFragment.newInstance(images,position),false);
            }
        });
        frameLayoutImages.addView(imagesView = bannerViewPager.getView());
    }

    @Override
    public void setAppBarImages(final ArrayList<String> images, final boolean onClick) {
        BannerViewPager bannerViewPager = new BannerViewPager(this,images,R.layout.layout_pager_item);
        bannerViewPager.setViewPagerItemClickListener(new ViewPagerAdapter.ViewPagerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (onClick)
                addToBackStack(BuyCarImageGalleryFragment.newInstance(images,position),false);

            }
        });
        frameLayoutImages.addView(imagesView = bannerViewPager.getView());
    }

    @Override
    public View setFabMenu(Context context) {
        return fabMenu;
    }

    /*private int getIcon(String key) {
        switch (key)
        {
            case MenusBaseAdapter.MENU_ABOUT_US:
                return R.drawable.ic_about_us;
            case MenusBaseAdapter.MENU_BUY_CAR:
                return R.drawable.ic_buy_car;
            case MenusBaseAdapter.MENU_CONTACT_US:
                return R.drawable.ic_contact_us;
            case MenusBaseAdapter.MENU_FINANCE:
                return R.drawable.ic_finance;
            case MenusBaseAdapter.MENU_HOME:
                return R.drawable.ic_home;
            case MenusBaseAdapter.MENU_NOTIFICATION:
                return R.drawable.ic_notification;
            case MenusBaseAdapter.MENU_PROFILE:
                return R.drawable.ic_my_profile;
            case MenusBaseAdapter.MENU_SELL_CAR:
                return R.drawable.ic_sell_car;
            case MenusBaseAdapter.MENU_SHARE:
                return R.drawable.ic_share;
            case MenusBaseAdapter.MENU_SHOWROOM:
                return R.drawable.ic_showroom;
            case MenusBaseAdapter.MENU_TESTIMONIAL:
                return R.drawable.ic_chat;
            case MenusBaseAdapter.MENU_INSURANCE:
                return R.drawable.ic_insurance;
            case MenusBaseAdapter.MENU_CANCEL:
                return R.drawable.ic_close;
            default:
                return R.drawable.ic_home;
        }
    }*/

    @Override
    public void onBackPressed() {

        if (menuFrameLayout.getVisibility()==View.VISIBLE) {
            menuFrameLayout.setVisibility(View.GONE);

            if(menuWidget!=null)
                menuWidget.callChangeIcon(menuWidget);
        }else{
            if (isNavDrawerOpen()) {
                closeNavDrawer();
            } else {
                if(fragmentManager.getBackStackEntryCount() == 0) {

                   // if(!getApplicationContext().getClass().getName().equals("HomeFragment")) {
                        showDialog();
                  //  }
                }
                else {
                    showDefaultAppBarImage();
                    appBarLayout.setExpanded(false);



                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public void onMenuWidgetAvailable(MenuWidget menuWidget) {
        this.menuWidget = menuWidget;
        menuWidget.getMenusAdapter().setINavigationListener(this);
    }



    public class MenuMoreOptionsAdapter extends RecyclerView.Adapter<MenuItemViewHolder> {
        @Override
        public MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MenuItemViewHolder(getLayoutInflater().inflate(R.layout.layout_menu_itme,parent,false));
        }

        @Override
        public void onBindViewHolder(MenuItemViewHolder holder,final int position) {
            holder.tvItem.setText(moreMenuList.get(position).getValue());
            holder.imItem.setImageResource(MenusBaseAdapter.getIcon(moreMenuList.get(position).getKey()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(MenuMoreOptionsAdapter.this, "", Toast.LENGTH_SHORT).show();
                    closeMoreOptions();

                    menuWidget.callChangeIcon(menuWidget);
                    onMenuItemSelected(moreMenuList.get(position).getKey());
                }
            });
            Utils.setTint(holder.imItem,HomeActivity.this);
        }

        @Override
        public int getItemCount() {
            return moreMenuList==null?0:moreMenuList.size();
        }
    }
}

