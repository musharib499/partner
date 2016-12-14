package com.gaadi.pratnerapps.fragments;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.gaadi.pratnerapps.R;

/**
 * Created by mushareb on 22/9/16.
 */

public abstract class FabMenuFragment extends PartnerBaseFragment {
    protected PopupWindow popupMenu;
    protected FrameLayout frameLayout;
    public void initPopupMenu( View view) {

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.layout_popup_menu, null);
         frameLayout= (FrameLayout) layout.findViewById(R.id.flMenu);
        RelativeLayout viewGroup = (RelativeLayout) layout.findViewById(R.id.popup);
        frameLayout.addView(view);
        popupMenu = new PopupWindow(getContext());
        popupMenu.setContentView(layout);

        popupMenu.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupMenu.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.dismiss();
            }
        });
        popupMenu.setBackgroundDrawable(new BitmapDrawable());
      //  popupMenu.setOutsideTouchable(true);
        popupMenu.setFocusable(true);
        popupMenu.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK){
                    popupMenu.dismiss();
                    return true;
                }
                return false;
            }
        });

    }

    public void showPopupMenu(View view){
        Animation  animation= AnimationUtils.loadAnimation(getContext(),R.anim.rigth_bottom_top);

        popupMenu.showAtLocation(view, Gravity.NO_GRAVITY, 50, 50);
        frameLayout.startAnimation(animation);




    }

    public boolean hasPopupAndShowing(){
        return popupMenu!=null && popupMenu.isShowing();
    }

    public void dismissPopupMenu(){
        if(hasPopupAndShowing())
            popupMenu.dismiss();
    }
}
