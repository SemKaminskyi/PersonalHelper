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
import com.gmail.kaminskysem.PersnalHelper.Timer.Service.TimerService;
import com.gmail.kaminskysem.PersnalHelper.Timer.TimerActivity;
import com.gmail.kaminskysem.PersnalHelper.Timer.TimerTImerFragment;

public class TimerNotificationsManager {

    public static final String LOG_TAG = TimerNotificationsManager.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    private static NotificationManagerCompat notificationManager;
    private static Notification notification;
    private static int idTimerServiceNotification =1;
    private static final String OTHER_TIME ="0";

    private static String getChanelIDTasks (Context context){
        return context.getString(R.string.default_notification_channel_id);
    }



    @TargetApi(Build.VERSION_CODES.O)
    public static void setupNotificationsChannels(Context context) {
        notificationManager = NotificationManagerCompat.from(context);

        String timerServiceNotificationId = "1";
        String timerNotifications = "Timer notifications";
        NotificationChannel notificationChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(getChanelIDTasks(context)
                    , timerNotifications
                    , NotificationManager.IMPORTANCE_LOW);
        }
        assert notificationChannel != null;
        notificationManager.createNotificationChannel(notificationChannel);

        Log.d(LOG_TAG, "add NotificationsChannels" + notificationChannel.toString());
    }

    public static void showTimerNotifications(Context context, String text) {
        NotificationManagerCompat notificationsManagerCompat = NotificationManagerCompat.from(context);

        String timerServiceTitle = "Timer service";

        PendingIntent mainContent = PendingIntent.getActivity(context,
                idTimerServiceNotification,
                new Intent(context, TimerActivity.class).putExtra(TimerTImerFragment.TIMER_WORK,"0").putExtra(OTHER_TIME , "0"),
                PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent = new Intent(context, TimerService.class );
        intent.setAction(TimerService.ACTION_STOP_TIMER);
        PendingIntent closeService = PendingIntent.getService(context,
                   idTimerServiceNotification,
                    intent,
                    PendingIntent.FLAG_CANCEL_CURRENT);


        notification = new NotificationCompat.Builder(context, getChanelIDTasks(context))
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
