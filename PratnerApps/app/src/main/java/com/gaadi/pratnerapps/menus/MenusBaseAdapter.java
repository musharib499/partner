package com.gaadi.pratnerapps.menus;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.MenuApiModel;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by priyarawat on 4/7/16.
 */
public abstract class MenusBaseAdapter extends RecyclerView.Adapter<MenusBaseAdapter.ViewHolder> {
    private static final String TAG = "MenusBaseAdapter";

    public static final String MENU_HOME = "home";
    public static final String MENU_PROFILE = "my_profile";
    public static final String MENU_BUY_CAR = "buy";
    public static final String MENU_SELL_CAR = "sell";
    public static final String MENU_FINANCE = "finance";
    public static final String MENU_INSURANCE = "insurance";
    public static final String MENU_NOTIFICATION = "notifications";
    public static final String MENU_TESTIMONIAL = "testimonials";
    public static final String MENU_SHARE = "refer_and_earn";
    public static final String MENU_ABOUT_US = "about_us";
    public static final String MENU_SHOWROOM = "showroom";
    public static final String MENU_CONTACT_US = "contact_us";
    public static final String MENU_LOG_OUT = "logout";
    public static final String MENU_CANCEL = "cancel";
    public static final String MENU_UCV = "uc_evaluation";
    public static final String MENU_SERVICE = "services";

    private Context context;
    private ArrayList<MenuApiModel.MenuModel> arrayList;
    protected int selectedPosition = -1;
    protected boolean isClicked = false;
    private INavigationListener iNavigationListener;


    public void setINavigationListener(INavigationListener iNavigationListener) {
        this.iNavigationListener = iNavigationListener;

        if(hasMoreOptions()){
            int fromIndex = getIndexForMoreList();
            Log.e(TAG,"ArrayListSize:"+arrayList.size());
            Log.e(TAG,"fromIndex:"+fromIndex);
            iNavigationListener.setMoreOptions(arrayList.subList(fromIndex, arrayList.size()), isListOrMatrixMenu());
        }else{
            iNavigationListener.setMoreOptions(new ArrayList<MenuApiModel.MenuModel>(), isListOrMatrixMenu());
        }
    }

    public INavigationListener getiNavigationListener() {
        return iNavigationListener;
    }

    public boolean hasMoreOptions() {
        return false;
    }

    public interface INavigationListener{
         void onMenuItemSelected(String key);
         void setMoreOptions(List<MenuApiModel.MenuModel> arrayList,boolean isListOrMatrix);
         void closeMoreOptions();
         void showMoreOptions();
         FloatingActionButton getFabMenu();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected  TextView tvTitle;
        protected ImageView tvIcon;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.menu_textview);
            tvIcon = (ImageView) itemView.findViewById(R.id.menu_ic_imageview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    notifyItemChanged(selectedPosition);
                    selectedPosition = getLayoutPosition();
                    notifyItemChanged(selectedPosition);
                    if(tvTitle.getText().toString().equals("More")) {
                         if(!(tvIcon.getDrawable().getConstantState() == ContextCompat.getDrawable(context, R.drawable.ic_close).getConstantState()) && !isClicked)
                         {
                            tvIcon.setImageResource(R.drawable.ic_close);
                             isClicked = true;

                             if(iNavigationListener!=null)
                                 iNavigationListener.showMoreOptions();
                         }
                         else {
                             tvIcon.setImageResource(R.drawable.ic_menu_more);
                             isClicked = false;

                             if(iNavigationListener!=null)
                                 iNavigationListener.closeMoreOptions();
                         }

                         notifyItemChanged(selectedPosition);
                    }
                    else {
                        isClicked = false;

                        if(iNavigationListener!=null)
                            iNavigationListener.onMenuItemSelected(arrayList.get(getAdapterPosition()).getKey());
                    }
               }
            });
        }

    }
    public int getIndexForMoreList()
    {
        if(arrayList.size()>0) {
            return arrayList.size() > BottomMenusAdapter.NUM_OF_ITEMS ? BottomMenusAdapter.NUM_OF_ITEMS - 1 : arrayList.size() - 1;
        }
        else{
            return 0;
        }
    }

    public boolean isListOrMatrixMenu(){
        return false;
    }

    public MenusBaseAdapter(Context context, ArrayList<MenuApiModel.MenuModel> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

            holder.tvTitle.setText(arrayList.get(position).getValue());
            holder.tvIcon.setImageResource(getIcon(arrayList.get(position).getKey()));
            Utils.setTint(holder.tvIcon,context);

    }

    public static int getIcon(String key) {
        switch (key)
        {
            case MENU_ABOUT_US:
                return R.drawable.ic_about_us;
            case MENU_BUY_CAR:
                return R.drawable.ic_buy_car;
            case MENU_CONTACT_US:
                return R.drawable.ic_contact_us;
            case MENU_FINANCE:
                return R.drawable.ic_finance;
            case MENU_HOME:
                return R.drawable.ic_home;
            case MENU_NOTIFICATION:
                return R.drawable.ic_notification;
            case MENU_PROFILE:
                return R.drawable.ic_my_profile;
            case MENU_SELL_CAR:
                return R.drawable.ic_sell_car;
            case MENU_SHARE:
                return R.drawable.ic_share;
            case MENU_SHOWROOM:
                return R.drawable.ic_showroom;
            case MENU_TESTIMONIAL:
                return R.drawable.ic_chat;
            case MENU_INSURANCE:
                return R.drawable.car_insurance;
            case MENU_CANCEL:
                return R.drawable.ic_close;
            case MENU_LOG_OUT:
                return R.drawable.log_out;
            case MENU_SERVICE:
                return R.drawable.service_icon;
            default:
                return R.drawable.ucv_car;
        }
    }

    public MenuApiModel.MenuModel getItem(int position){
        if(position>=arrayList.size())return null;
        else return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        if(this instanceof BottomMenusAdapter)
        {
            if(arrayList != null && arrayList.size() > BottomMenusAdapter.NUM_OF_ITEMS)
            {
                return BottomMenusAdapter.NUM_OF_ITEMS;
            }
            else if (arrayList != null)
                return arrayList.size();
            else
                return 0;
        }
        else
        {
           return arrayList != null ? arrayList.size() : 0;
        }
    }

    protected int calculateScreenWidth() {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        return width;
    }
}
