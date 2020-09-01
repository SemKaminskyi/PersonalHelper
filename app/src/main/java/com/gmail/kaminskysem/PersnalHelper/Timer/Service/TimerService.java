package com.gmail.kaminskysem.PersnalHelper.Timer.Service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.gmail.kaminskysem.PersnalHelper.Notifications.TimerNotificationsManager;
import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.Timer.TimerTImerFragment;

public class TimerService extends Service {
    private final static String LOG_TAG = TimerService.class.getSimpleName();

    public static final String ACTION_START_TIMER = "start_timer";
    public static final String ACTION_STOP_TIMER = "stop_timer";

    private static CountDownTimer countDownTimerWork;
    private static CountDownTimer countDownTimeRest;

    private int timeWorkInt;
    private String timeWorkString = " ";

    private int timeRestInt;
    private String timeRestString = " ";

    public MyBinder binder = new MyBinder();
    private String timerStopped;

    public TimerService() {
    }
    public class MyBinder extends Binder {
        public TimerService getService() {
            return TimerService.this;
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //add notifications channels - maybe need move to TimerFragment or TimerActivity
        TimerNotificationsManager.setupNotificationsChannels(this);

        Log.d(LOG_TAG, "onCreateTimerService");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, " Timer service starting", Toast.LENGTH_SHORT).show();
        String timerWork = intent.getStringExtra("TimerWork");
        String timerRest = intent.getStringExtra("TimerRest");
        Log.d(LOG_TAG, "time  Work: " + timerWork);
        Log.d(LOG_TAG, "time  rest: " + timerRest);
        Log.d(LOG_TAG, "time  action: " + intent.getAction());
        String action = intent.getAction();

        mediaPlayer = MediaPlayer.create(this, R.raw.timer_din);

        if (ACTION_START_TIMER.equals(action)) {
            // cancel previous timers
            cancelTimers();
            // might need to add null checks here
            assert timerWork != null;
            long workMillis = Integer.parseInt(timerWork) * 1000;
            assert timerRest != null;
            long restMillis = Integer.parseInt(timerRest) * 1000;
            runWork(workMillis, restMillis);

            // start notifications
            TimerNotificationsManager.showTimerNotifications(this, timerWork);
            startForeground(
                    Integer.parseInt(TimerNotificationsManager.getIdTimerServiceNotification())
                    , TimerNotificationsManager.getNotification());
            Log.d(LOG_TAG, "Start foreground: ");
            return START_STICKY;
        }

        if (ACTION_STOP_TIMER.equals(action)) {
            cancelByUser();
            Toast.makeText(this, " Timer service stopped", Toast.LENGTH_SHORT).show();

            return START_STICKY;
        }

        // unknown intent - throw exception this service should get only start and stop actions
        throw new IllegalArgumentException("Unknown action was passed to the service: " + action);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.d(LOG_TAG, "timerBinder onBind");
        return binder;
    }


    private void runWork(long workMillis, long restMillis) {
        // cancel any previous timers
        cancelTimers();
        countDownTimerWork = new CountDownTimer(workMillis, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                timeWorkInt = (int) (millisUntilFinished / 1000);
                timeWorkString = "seconds of  Work: " + timeWorkInt;

//                Log.d(LOG_TAG, " on Tick work" + timeWorkInt);

//                Log.d(LOG_TAG, " on work String " + timeWorkString);
                TimerNotificationsManager.showTimerNotifications(getBaseContext(), getTimeWorkString());
//                Log.d(LOG_TAG, " on Tick work String to notifications " + timeWorkString);

                Intent intent = new Intent(TimerTImerFragment.BROADCAST_ACTION);
                intent.putExtra(TimerTImerFragment.TIMER_WORK,timeWorkString);
                sendBroadcast(intent);
                Log.d(LOG_TAG, " intent to Broadcast : " +timeWorkString );

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {

                Log.d(LOG_TAG, " finishedWork on timer: " + this);
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
                timeRestInt = (int) (millisUntilFinished / 1000);
                timeRestString = "seconds of Rest: " + timeRestInt;

                Log.d(LOG_TAG, " on TickRest" + timeRestInt);
                Log.d(LOG_TAG, " on Tick rest String to notifications " + timeRestString);
                TimerNotificationsManager.showTimerNotifications(getBaseContext(), timeRestString);

                Intent intent = new Intent(TimerTImerFragment.BROADCAST_ACTION);
                intent.putExtra(TimerTImerFragment.TIMER_WORK,timeRestString);
                sendBroadcast(intent);
                Log.d(LOG_TAG, " intent to Broadcast " +timeRestString );
            }


            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                Log.d(LOG_TAG, " finished Rest on timer: " + this);
                mediaPlayer.start();
                runWork(workMillis, restMillis);
            }

        }.start();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(LOG_TAG, "MyService onRebind");
    }

    public void cancelByUser() {
        cancelTimers();
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        // add to Timer text view diff text
        Intent intent = new Intent(TimerTImerFragment.BROADCAST_ACTION);
        timerStopped = "Timer Stopped";
        intent.putExtra(TimerTImerFragment.TIMER_WORK, timerStopped);
        sendBroadcast(intent);
        //TODO qewstions - I need use bouth method stop service ore only one?
        stopSelf();
        stopForeground(true);
    }

    private void cancelTimers() {
        if (countDownTimerWork != null) {
            countDownTimerWork.cancel();
        }
        if (countDownTimeRest != null) {
            countDownTimeRest.cancel();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // important - not call cancelByUser here - it will stop service and android will not restart it
        cancelTimers();
    }



    private MediaPlayer mediaPlayer;


    public String getTimeWorkString() {
        return timeWorkString;
    }

    public String getTimeRestString() {
        return timeRestString;
    }



}