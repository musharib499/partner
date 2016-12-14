package com.gaadi.pratnerapps.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import com.gaadi.pratnerapps.BuildConfig;
import com.gaadi.pratnerapps.syncadapter.GenericAccountService;


/**
 * Created by vinodtakhar on 30/5/16.
 */
public class SyncUtils {

    public static final String CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";
    private static final String TAG = "SyncUtils.java";
    public static String ACCOUNT_TYPE = BuildConfig.ACCOUNT_TYPE;
    private static long DAYS_TO_SYNC = (1 * AlarmManager.INTERVAL_DAY)/1000; // run sync per day

    /**
     * Create an entry for this application in the system account list, if it isn't already there.
     *
     * @param context Context
     */
    @TargetApi(Build.VERSION_CODES.FROYO)
    public static boolean createSyncAccountIfNotExistOrDisabled(Context context,Bundle bundle) {

        boolean newAccount = false;

        // Create account, if it's missing. (Either first run, or user has deleted account.)
        Logger.e(TAG + " account Type: "+ACCOUNT_TYPE);

        Account account = GenericAccountService.GetAccount(ACCOUNT_TYPE);

        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        if(!hasAccountAdded(accountManager)) {

            Logger.e("Account does not exist. Adding now");

            if (accountManager.addAccountExplicitly(account, null, null)) {

                ContentResolver.setIsSyncable(account, CONTENT_AUTHORITY, 1);
                ContentResolver.setSyncAutomatically(account, CONTENT_AUTHORITY, true);
                ContentResolver.addPeriodicSync(
                        account, CONTENT_AUTHORITY, new Bundle(), DAYS_TO_SYNC);

                newAccount = true;

//                startSync(bundle);
            } else {
                Logger.e(TAG, "failed to add account");
            }
        }else {
            Logger.e("Account already added");

            if(ContentResolver.getIsSyncable(account, CONTENT_AUTHORITY)!=1 || !ContentResolver.getSyncAutomatically(account, CONTENT_AUTHORITY)){
                Logger.e("Account was removed to be sync, add it again");

                ContentResolver.setIsSyncable(account, CONTENT_AUTHORITY, 1);
                ContentResolver.setSyncAutomatically(account, CONTENT_AUTHORITY, true);
                ContentResolver.addPeriodicSync(
                        account, CONTENT_AUTHORITY, new Bundle(), DAYS_TO_SYNC);
            }
        }


        return newAccount;
    }

    private static boolean hasAccountAdded(AccountManager accountManager){

        Account accounts[]=accountManager.getAccountsByType(ACCOUNT_TYPE);

        return accounts!=null && accounts.length>0;
    }

    public static void startSync(Bundle bundle) {

        ContentResolver.requestSync(GenericAccountService.GetAccount(SyncUtils.ACCOUNT_TYPE),
                SyncUtils.CONTENT_AUTHORITY, bundle);
    }
}
