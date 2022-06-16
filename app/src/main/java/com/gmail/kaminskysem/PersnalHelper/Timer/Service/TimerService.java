package com.gmail.kaminskysem.PersnalHelper.Timer.Service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.gmail.kaminskysem.PersnalHelper.Notifications.TimerNotificationsManager;
import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.Timer.TimerTImerFragment;

import timber.log.Timber;

public class TimerService extends Service {
    public static final String ACTION_START_TIMER = "start_timer";
    public static final String ACTION_STOP_TIMER = "stop_timer";

    private static CountDownTimer countDownTimerWork;
    private static CountDownTimer countDownTimeRest;


    private int timeWorkInt;
    private String timeWorkString = "";

    private int timeRestInt;
    private String timeRestString = "";

    public MyBinder binder = new MyBinder();
    private String timerWork;
    private String timerRest;


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


        Timber.d("onCreateTimerService");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, " Timer service starting", Toast.LENGTH_SHORT).show();

        timerWork = intent.getStringExtra(TimerTImerFragment.TIMER_WORK);
        timerRest = intent.getStringExtra(TimerTImerFragment.TIMER_REST);

        Timber.d("time  Work: %s", timerWork);
        Timber.d("time  rest: %s", timerRest);
        Timber.d("time  action: %s", intent.getAction());
        String action = intent.getAction();

        mediaPlayer = MediaPlayer.create(this, R.raw.timer_din);


        if (ACTION_START_TIMER.equals(action)) {
            // cancel previous timers
            cancelTimers();
            // might need to add null checks here
            assert timerWork != null;
            int convertToMinutes = 60000;
            long workMillis = Long.parseLong(timerWork) * convertToMinutes;
            assert timerRest != null;
            long restMillis = (long) Integer.parseInt(timerRest) * convertToMinutes;
            runWork(workMillis, restMillis);

            // start notifications
            TimerNotificationsManager.showTimerNotifications(this, timerWork);
            startForeground(
                    Integer.parseInt(TimerNotificationsManager.getIdTimerServiceNotification())
                    , TimerNotificationsManager.getNotification());
            Timber.d("Start foreground: ");
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

        Timber.d("timerBinder onBind");
        return binder;
    }

    private String formatTime(Integer seconds) {
        @SuppressLint("DefaultLocale")
        String b = String.format("%d:%02d:%02d", seconds / 3600,
                (seconds % 3600) / 60, (seconds % 60));
        return b;
    }

    private void runWork(long workMillis, long restMillis) {
        //add broadcast to draw circle
        Intent workInt = new Intent(TimerTImerFragment.BROADCAST_ACTION);
        workInt.putExtra(TimerTImerFragment.TIME_FOR_CIRCLE_WORK, timerWork);
        workInt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendBroadcast(workInt);

        Timber.d("TIME_FOR_CIRCLE_WORK to Broadcast %s",
                workInt.getStringExtra(TimerTImerFragment.TIME_FOR_CIRCLE_WORK));

        // cancel any previous timers
        cancelTimers();
        countDownTimerWork = new CountDownTimer(workMillis, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {


                timeWorkInt = (int) (millisUntilFinished / 1000);


                timeWorkString = "time of  Work: " + formatTime(timeWorkInt);
//                Log.d(LOG_TAG, " on Tick work" + timeWorkInt);

//                Log.d(LOG_TAG, " on work String " + timeWorkString);
                TimerNotificationsManager.showTimerNotifications(getBaseContext(), getTimeWorkString());
//                Log.d(LOG_TAG, " on Tick work String to notifications " + timeWorkString);

                Intent intent = new Intent(TimerTImerFragment.BROADCAST_ACTION);
                intent.putExtra(TimerTImerFragment.TIMER_WORK, timeWorkString);
                sendBroadcast(intent);
                Timber.d("intent to Broadcast : %s", timeWorkString);

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {

                Timber.d(" finishedWork on timer: %s", this);
                mediaPlayer.start();
                runRest(workMillis, restMillis);

//                Intent workInt = new Intent(TimerTImerFragment.BROADCAST_ACTION);
//                workInt.putExtra(TimerTImerFragment.TIME_FOR_CIRCLE_WORK,"-1");
//                workInt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                sendBroadcast(workInt);
//                Log.d(LOG_TAG, " TIME_FOR_CIRCLE_REST to Broadcast -1+ " +workInt.getStringExtra(TimerTImerFragment.TIME_FOR_CIRCLE_WORK)  );


            }
        }.start();
    }

    private void runRest(long workMillis, long restMillis) {
        //add broadcast to draw circle
        Intent restInt = new Intent(TimerTImerFragment.BROADCAST_ACTION);
        restInt.putExtra(TimerTImerFragment.TIME_FOR_CIRCLE_REST, timerRest);
        restInt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendBroadcast(restInt);
        Timber.d(" TIME_FOR_CIRCLE_REST to Broadcast %s",
                restInt.getStringExtra(TimerTImerFragment.TIME_FOR_CIRCLE_REST));

        countDownTimeRest = new CountDownTimer(restMillis, 1000) {

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {

                timeRestInt = (int) (millisUntilFinished / 1000);

                timeRestString = "time of Rest: " + formatTime(timeRestInt);

                Timber.d(" on TickRest%s", timeRestInt);
                Timber.d(" on Tick rest String to notifications %s", timeRestString);
                TimerNotificationsManager.showTimerNotifications(getBaseContext(), timeRestString);

                Intent intent = new Intent(TimerTImerFragment.BROADCAST_ACTION);
                intent.putExtra(TimerTImerFragment.TIMER_WORK, timeRestString);
                sendBroadcast(intent);
                Timber.d(" intent to Broadcast %s", timeRestString);
            }


            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                Timber.d(" finished Rest on timer: %s", this);
                mediaPlayer.start();
                runWork(workMillis, restMillis);

//                Intent restInt = new Intent(TimerTImerFragment.BROADCAST_ACTION);
//                restInt.putExtra(TimerTImerFragment.TIME_FOR_CIRCLE_REST,"-1");
//                restInt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                sendBroadcast(restInt);
//                Log.d(LOG_TAG, " TIME_FOR_CIRCLE_REST to Broadcast -1+ " +restInt.getStringExtra(TimerTImerFragment.TIME_FOR_CIRCLE_REST)  );
            }

        }.start();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Timber.d("MyService onRebind");
    }

    public void cancelByUser() {
        cancelTimers();
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        // add to Timer text view diff text
        Intent intent = new Intent(TimerTImerFragment.BROADCAST_ACTION);
        String timerStopped = "Timer Stopped";
        intent.putExtra(TimerTImerFragment.TIMER_WORK, timerStopped);
        sendBroadcast(intent);

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

    public int getTimeWorkInt() {
        return timeWorkInt;
    }

    public int getTimeRestInt() {
        return timeRestInt;
    }

    public String getTimeRestString() {
        return timeRestString;
    }

    private MediaPlayer mediaPlayer;


    public String getTimeWorkString() {
        return timeWorkString;
    }


}