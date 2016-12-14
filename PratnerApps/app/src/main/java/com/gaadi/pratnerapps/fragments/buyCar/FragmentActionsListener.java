package com.gaadi.pratnerapps.fragments.buyCar;

import android.support.v4.app.Fragment;

/**
 * Created by lakshaygirdhar on 7/4/16.
 */
public interface FragmentActionsListener {

    void onActionDone(Object object, Fragment fragment);

    void onActionCompleted(Object object, Fragment fragment);

}
