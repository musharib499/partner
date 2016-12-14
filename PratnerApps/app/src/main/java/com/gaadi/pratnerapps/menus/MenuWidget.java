package com.gaadi.pratnerapps.menus;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.MenuApiModel;
import com.gaadi.pratnerapps.utils.SpacesItemDecoration;

/**
 * Created by vinodtakhar on 5/7/16.
 */
public class MenuWidget {
    private final Context context;
    private final LayoutInflater layoutInflater;
    private View view;
    private RecyclerView recyclerView;
    private MenusBaseAdapter menusAdapter;
    private MenuApiModel menuApiModel;
    IChangeIcon iChangeIcon;
    public interface IChangeIcon
    {
        void changeIcon();
    }

    public MenusBaseAdapter getMenusAdapter() {
        return menusAdapter;
    }

    public MenuWidget(Context context, MenuApiModel menuApiModel){
        this.menuApiModel = menuApiModel;

        this.context = context;

        layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(getLayoutId(),null);

        recyclerView = (RecyclerView) view.findViewById(R.id.menu_recycle_list);
      /*String  cardColor = ((PartnerApplication)context.getApplicationContext()).getThemeProperty("card_color");
        if (!TextUtils.isEmpty(cardColor))
        recyclerView.setBackgroundColor(Color.parseColor(cardColor));*/


        int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.spacing);
        int no_of_columns = menuApiModel.getConfig().getOptions() != null ? menuApiModel.getConfig().getOptions().getMat_cols() : 2;
        int totalMenuItems = menuApiModel.getItems().size();
        no_of_columns = totalMenuItems < no_of_columns ? totalMenuItems : no_of_columns;
        if(menuApiModel.getConfig().getMenuStyle().equals(MenuApiModel.MenuConfig.STYLE_MATRIX)){
            setLayoutManager(LinearLayout.VERTICAL,no_of_columns);
           menusAdapter = new MatrixMenusAdapter(context,menuApiModel.getItems(), no_of_columns, spacingInPixels);
            recyclerView.addItemDecoration(new SpacesItemDecoration(no_of_columns, spacingInPixels,false));
        }else if(menuApiModel.getConfig().getMenuStyle().equals(MenuApiModel.MenuConfig.STYLE_BOTTOM)){
            setLayoutManager(LinearLayout.HORIZONTAL,1);
            menusAdapter = new BottomMenusAdapter(context,menuApiModel.getItems());

        }else if(menuApiModel.getConfig().getMenuStyle().equals(MenuApiModel.MenuConfig.STYLE_LIST)){
            setLayoutManager(LinearLayout.VERTICAL,no_of_columns);
            //recyclerView.addItemDecoration(new SpacesItemDecoration(no_of_columns, spacingInPixels,false));
            menusAdapter = new ListMenusAdapter(context,menuApiModel.getItems(), no_of_columns, spacingInPixels);
        }else if(menuApiModel.getConfig().getMenuStyle().equals(MenuApiModel.MenuConfig.STYLE_HUMBERGER)){
            setLayoutManager(LinearLayout.VERTICAL,1);
            menusAdapter = new HorizontalMenuAdapter(context,menuApiModel.getItems());
        }else if(menuApiModel.getConfig().getMenuStyle().equals(MenuApiModel.MenuConfig.STYLE_FAB_MENU)){
            setLayoutManager(LinearLayout.VERTICAL,1);
            menusAdapter = new FabMenuAdapter(context,menuApiModel.getItems());
        }

        recyclerView.setAdapter(menusAdapter);
    }

    public int getLayoutId(){
        return R.layout.menu_recyclerview;
    }

    private void setLayoutManager(int orientation,int spanCount){
        RecyclerView.LayoutManager layoutManager = null;

        if(spanCount==1) {
            layoutManager = new LinearLayoutManager(context);
            ((LinearLayoutManager)layoutManager).setOrientation(orientation);
        }
        else {
            layoutManager = new GridLayoutManager(context, spanCount);
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public View getView(){
        return view;
    }

    public void callChangeIcon(MenuWidget menuWidget) {
        if(menuApiModel.getConfig().getMenuStyle().equals(MenuApiModel.MenuConfig.STYLE_BOTTOM)
                || menuApiModel.getConfig().getMenuStyle().equals(MenuApiModel.MenuConfig.STYLE_MATRIX)
                || menuApiModel.getConfig().getMenuStyle().equals(MenuApiModel.MenuConfig.STYLE_LIST)) {
            iChangeIcon = (IChangeIcon) menusAdapter;
            iChangeIcon.changeIcon();
        }

    }
}
