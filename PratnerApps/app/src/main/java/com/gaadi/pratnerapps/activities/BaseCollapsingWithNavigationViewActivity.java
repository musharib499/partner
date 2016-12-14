package com.gaadi.pratnerapps.activities;

import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.utils.Utils;


public abstract class BaseCollapsingWithNavigationViewActivity extends PartnerBaseActivity  {
    protected CollapsingToolbarLayout collapsingToolbarLayout;
    public FloatingActionButton floatingActionButton ,fabMenu ;
    protected FloatingActionButton fabHome;
    protected LinearLayout navigationView;
    protected CardView bottomMenusLayout;
    protected DrawerLayout mDrawerLayout;
    protected Toolbar toolbar;
    protected FrameLayout mainContainer,menuFrameLayout;
    protected FrameLayout appBarCollapsingContainer;
    protected ActionBarDrawerToggle mDrawerToggle;
    protected AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_collapsing_with_navigation_view);
        Utils.logAnalytics(this);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }*/
        navigationView = (LinearLayout) findViewById(R.id.navigation_view);
        bottomMenusLayout = (CardView) findViewById(R.id.bottomMenusLayout);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mainContainer = (FrameLayout) findViewById(R.id.main_container);
        menuFrameLayout = (FrameLayout) findViewById(R.id.menuFrameLayout);
        appBarCollapsingContainer = (FrameLayout) findViewById(R.id.appbar_collapsing_container);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.main_collapsing);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        fabHome = (FloatingActionButton) findViewById(R.id.fabHome);
        fabMenu = (FloatingActionButton) findViewById(R.id.fabMenu);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        LinearLayout navigationHeader = (LinearLayout) findViewById(R.id.headerLayout);
       /* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        // This is make sure navigation header is below status bar // This only required for devices API >= 21
            DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) navigationView.getLayoutParams();
            layoutParams.setMargins(0, getStatusBarHeight(getResources()), 0, 0);
            navigationView.setLayoutParams(layoutParams); }
*/
        navigationHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        mDrawerSync();

        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
      /*  String color=((PartnerApplication) this.getApplicationContext()).getThemeProperty("card_color");
        if (color !=null)
        setOverflowButtonColor(this, Color.parseColor(color));*/

//        MenuTintUtils.tintAllIcons((PartnerApplication)getApplicationContext(),navigationView.getMenu());
    }


  /*  private static void setOverflowButtonColor(final Activity activity, final int colorFilter) {
        final String overflowDescription = activity.getString(R.string.abc_action_menu_overflow_description);
        final ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        final ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final ArrayList<View> outViews = new ArrayList<View>();
                decorView.findViewsWithText(outViews, overflowDescription,
                        View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
                if (outViews.isEmpty()) {
                    return;
                }
                AppCompatImageView overflow = (AppCompatImageView) outViews.get(0);

                overflow.setColorFilter(colorFilter);
                removeOnGlobalLayoutListener(decorView,this);
            }
        });
    }
    private static void removeOnGlobalLayoutListener(View v, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            v.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        }
        else {
            v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        }
    }*/
    private static int getStatusBarHeight(android.content.res.Resources res) {
        return (int) (24 * res.getDisplayMetrics().density);
    }

    public void mDrawerSync() {
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        mDrawerToggle.syncState();

        setSupportActionBar(toolbar);
        //getSupportActionBar().setHomeAsUpIndicator(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    protected void setTitleMessage(String message)
    {
        if (message!=null) {

            if(toolbar != null) {
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle(message);
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                collapsingToolbarLayout.setTitleEnabled(false);

            }
        }
    }

    public void setExpandCollapse(boolean isExpand) {
        if (isExpand) {
            ViewCompat.setNestedScrollingEnabled(mainContainer, true);
            appBarLayout.setExpanded(true, true);

        } else {
            appBarLayout.setExpanded(false, true);
            ViewCompat.setNestedScrollingEnabled(mainContainer, false);


        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Utils.onStartAnalytic(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Utils.onStopAnalytic(this);
    }

    @Override
    public void onBackPressed() {
        if (menuFrameLayout.getVisibility()==View.VISIBLE) {
            menuFrameLayout.setVisibility(View.GONE);
        }else {
            if (isNavDrawerOpen()) {
                closeNavDrawer();
            } else {
               // showDialog();
                showDefaultAppBarImage();
                appBarLayout.setExpanded(false);
                super.onBackPressed();
            }
        }



    }

    protected abstract void showDefaultAppBarImage();

    public boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeNavDrawer() {
        if (mDrawerLayout != null) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT))
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            else if (mDrawerLayout.isDrawerOpen(GravityCompat.END))
                mDrawerLayout.closeDrawer(GravityCompat.END);
        }
    }

    public void showDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_home_dailogs);
        dialog.findViewById(R.id.btnNoExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.btnYesExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showDefaultAppBarImage();
                appBarLayout.setExpanded(false);
                finish();
            }
        });
         dialog.setCanceledOnTouchOutside(true);
         dialog.show();

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //ViewServer.get(this).removeWindow(this);
    }
}
