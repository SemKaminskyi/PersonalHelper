package com.gmail.kaminskysem.PersnalHelper.Timer.Service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.gmail.kaminskysem.PersnalHelper.R;

public class TimerService extends Service {
    private final static String LOG_TAG = TimerService.class.getSimpleName();

    public static final String ACTION_START_TIMER = "start_timer";
    public static final String ACTION_STOP_TIMER = "stop_timer";

    private CountDownTimer countDownTimerWork;
    private CountDownTimer countDownTimeRest;

    private MediaPlayer mediaPlayer;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder notificationManagerBuilder;
    private int idNotificationTimerService;
    private String textWork;
    private String timeRest;
    String currentTime;


    public TimerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManagerBuilder = getNotificationBuilder();

        notificationManagerBuilder.setContentTitle("Timer service")
                .setSmallIcon(R.drawable.ic_clock1_foreground);

        Log.d(LOG_TAG, "onCreateTimerService");
    }

    private NotificationCompat.Builder getNotificationBuilder() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return new NotificationCompat.Builder(this);
        } else {
            String timerServiceNotificationId = "timer service id";
            NotificationChannel notificationChannel = new NotificationChannel(timerServiceNotificationId
                    , "For working timer on background"
                    , NotificationManager.IMPORTANCE_DEFAULT);
            if (notificationManager.getNotificationChannel(timerServiceNotificationId) == null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
            return new NotificationCompat.Builder(this, timerServiceNotificationId);
        }


    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        idNotificationTimerService = 01;
        startForeground(idNotificationTimerService, getNotifications("Start Notifications"));
        Toast.makeText(this, " Timer service starting", Toast.LENGTH_LONG).show();
        String timerWork = intent.getStringExtra("TimerWork");
        String timerRest = intent.getStringExtra("TimerRest");
        Log.d(LOG_TAG, "time  Work: " + timerWork);
        Log.d(LOG_TAG, "time  rest: " + timerRest);
        Log.d(LOG_TAG, "time  action: " + intent.getAction());
        String action = intent.getAction();

        mediaPlayer = MediaPlayer.create(this, R.raw.timer_din);
        // проверка  и передача текста в нотификашку
        if(countDownTimerWork != null){
            currentTime = timerWork;
        }
        if(countDownTimeRest!=null){
            currentTime = timerRest;
        }
        notificationManager.notify(idNotificationTimerService, getNotifications(currentTime ));
        if (ACTION_START_TIMER.equals(action)) {
            // cancel previous timers
            cancelTimers();
            // might need to add null checks here
            long workMillis = Integer.parseInt(timerWork) * 1000;
            long restMillis = Integer.parseInt(timerRest) * 1000;
            runWork(workMillis, restMillis);
            return START_STICKY;
        }

        if (ACTION_STOP_TIMER.equals(action)) {
            cancelByUser();
            return START_STICKY;
        }

        // unknown intent - throw exception this service should get only start and stop actions
        throw new IllegalArgumentException("Unknown action was passed to the service: " + action);
    }

    private Notification getNotifications(String content) {
        return notificationManagerBuilder.setContentText(content).build();
    }


    private void cancelByUser() {
        cancelTimers();
        mediaPlayer.stop();
        stopSelf();
    }

    private void cancelTimers() {
        if (countDownTimerWork != null) {
            countDownTimerWork.cancel();
        }
        if (countDownTimeRest != null) {
            countDownTimeRest.cancel();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }


    private void runWork(long workMillis, long restMillis) {
        // cancel any previous timers
        cancelTimers();
        countDownTimerWork = new CountDownTimer(workMillis, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                textWork = "seconds of  Work" + (int) (millisUntilFinished / 1000);
                Log.d(LOG_TAG, " on Tick" + textWork);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                Log.d(LOG_TAG, " finihsedWork on timer: " + this);
                mediaPlayer.start();
                runRest(workMillis, restMillis);


            }
        }.start();
    }

    private void runRest(long workMillis, long restMillis) {
        countDownTimeRest = new CountDownTimer(restMillis, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                timeRest = "seconds of  rest" + (int) (millisUntilFinished / 1000);
                Log.d(LOG_TAG, " on TickRest" + timeRest);
            }


            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                Log.d(LOG_TAG, " finihsedRest on timer: " + this);
                mediaPlayer.start();
                runWork(workMillis, restMillis);
            }

        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // important - not call cancelByUser here - it will stop service and android will not restart it
        cancelTimers();
    }

}