package com.gmail.kaminskysem.PersnalHelper.Notifications;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.Timer.TimerActivity;

public class TimerNotificationsManager {

    public static final String LOG_TAG = TimerNotificationsManager.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    private static NotificationManagerCompat notificationManager;
    private static NotificationCompat.Builder notificationManagerBuilder;
    private static Notification notification;
    private static String idTimerServiceNotification ="1";
    private static String timerServiseTitle;


    @SuppressLint("ServiceCast")
    @TargetApi(Build.VERSION_CODES.O)
    public static void setupNotificationsChannels(Context context) {

        notificationManager = NotificationManagerCompat.from(context);
        String timerServiceNotificationId = "1";
        String timerNotifications = "Timer notifications";
        NotificationChannel notificationChannel = new NotificationChannel(timerServiceNotificationId
                , timerNotifications
                , NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(notificationChannel);

        Log.d(LOG_TAG, "add NotificationsChannels" + notificationChannel.toString());
    }

    public static void showTimerNotifications(Context context, String text) {
        NotificationManagerCompat notificationsManagerCompat = NotificationManagerCompat.from(context);

        timerServiseTitle = "Timer service";

        PendingIntent mainContent = PendingIntent.getActivity(context,
                Integer.parseInt(idTimerServiceNotification),
                new Intent(context, TimerActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        notification = new NotificationCompat.Builder(context, idTimerServiceNotification)
                .setContentTitle(timerServiseTitle)
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_clock1_foreground)
                .setContentIntent(mainContent)
                .build();
        notificationsManagerCompat.notify(Integer.parseInt(idTimerServiceNotification), notification);

        Log.d(LOG_TAG, "showTimerNotifications " + text);

    }

    public static Notification getNotification() {
        return notification;
    }


    public static String getIdTimerServiceNotification() {
        return idTimerServiceNotification;
    }
}
