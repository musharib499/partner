package com.gaadi.pratnerapps.services;

import android.support.v4.app.NotificationCompat;

import com.gaadi.pratnerapps.provider.notificationtable.NotificationtableContentValues;
import com.gaadi.pratnerapps.utils.Logger;
import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationReceivedResult;

import java.math.BigInteger;

/**
 * @author vinodtakhar
 * @version 1.0
 * @since 30/8/16
 */
public class OneSignalService extends NotificationExtenderService {
    private static final String TAG = "OneSignalService";

    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {

        Logger.e(TAG,"New Push received!");

        NotificationtableContentValues notificationtableContentValues = new NotificationtableContentValues();
        notificationtableContentValues.putDate(System.currentTimeMillis());
        notificationtableContentValues.putDescription(notification.payload.body);
        notificationtableContentValues.putImageUrl(notification.payload.bigPicture);
        notificationtableContentValues.putReadStatus(1);
        notificationtableContentValues.putTitle(notification.payload.title);
        notificationtableContentValues.insert(this);

        return false;
    }
}
