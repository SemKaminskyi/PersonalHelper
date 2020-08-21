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
import android.widget.Button;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.Timer.Service.TimerService;
import com.gmail.kaminskysem.PersnalHelper.Timer.TimerActivity;

public class TimerNotificationsManager {

    public static final String LOG_TAG = TimerNotificationsManager.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    private static NotificationManagerCompat notificationManager;
    private static Notification notification;
    private static int idTimerServiceNotification =1;



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

        String timerServiceTitle = "Timer service";

        PendingIntent mainContent = PendingIntent.getActivity(context,
                idTimerServiceNotification,
                new Intent(context, TimerActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent = new Intent(context, TimerService.class );
        intent.setAction(TimerService.ACTION_STOP_TIMER);
        PendingIntent closeService = PendingIntent.getService(context,
                   idTimerServiceNotification,
                    intent,
                    PendingIntent.FLAG_CANCEL_CURRENT);


        notification = new NotificationCompat.Builder(context, String.valueOf(idTimerServiceNotification))
                .setContentTitle(timerServiceTitle)
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_clock1_foreground)
                .setContentIntent(mainContent)
                .addAction(0,"Stop Timer", closeService)
                .build();
        notificationsManagerCompat.notify(idTimerServiceNotification, notification);

        Log.d(LOG_TAG, "showTimerNotifications " + text);

    }

    public static Notification getNotification() {
        return notification;
    }


    public static String getIdTimerServiceNotification() {
        return  String.valueOf(idTimerServiceNotification);
    }
}
