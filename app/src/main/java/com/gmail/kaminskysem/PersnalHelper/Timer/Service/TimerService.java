package com.gmail.kaminskysem.PersnalHelper.Timer.Service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Objects;

public class TimerService extends Service {
    private final static String LOG_TAG = TimerService.class.getSimpleName();

    public TimerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreateTimerService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, " Timer service starting", Toast.LENGTH_LONG).show();
        Log.d(LOG_TAG, "time  Work: " + intent.getStringExtra("TimerWork"));
        Log.d(LOG_TAG, "time  rest: " + intent.getStringExtra("TimerRest"));
        Log.d(LOG_TAG, "time  action: " + intent.getAction());
        RunWork(intent);

        return START_STICKY;
    }



    private void cancelByUser(Intent intent, CountDownTimer countDownTimeRest) {
        if (Objects.equals(intent.getAction(), false)) {
            countDownTimeRest.cancel();

            Log.d(LOG_TAG, "countDownTimeRest stoped");
        }
        stopService(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }


    private void RunWork(Intent intent) {
        String timeWork = intent.getStringExtra("TimerWork");
        if (!TextUtils.isEmpty(timeWork)) {
            CountDownTimer countDownTimerWork = new CountDownTimer(Integer.parseInt(timeWork) * 1000, 1000) {
                @SuppressLint("SetTextI18n")
                @Override
                public void onTick(long millisUntilFinished) {
                    String textWork = "seconds of  Work" + (int) (millisUntilFinished / 1000);
                    Log.d(LOG_TAG, " on Tick" + textWork);
                    if (textWork.equals(false)) {
                        stopService(intent);
                    }
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onFinish() {
                    String finished = "Finished";
                    RunRest(intent);
                }
            }.start();
            cancelByUser(intent, countDownTimerWork);
        }
    }
    private void RunRest(Intent intent) {
        String timeRest = intent.getStringExtra("TimerRest");
        if (!TextUtils.isEmpty(timeRest)) {
            CountDownTimer countDownTimeRest = new CountDownTimer(Integer.parseInt(timeRest) * 1000, 1000) {
                @SuppressLint("SetTextI18n")
                @Override
                public void onTick(long millisUntilFinished) {
                    String textRest = "seconds of  rest" + (int) (millisUntilFinished / 1000);
                    Log.d(LOG_TAG, " on TickRest" + textRest);

                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onFinish() {
                    String finished = "Finished rest";
                    RunWork(intent);
                }

            }.start();
            cancelByUser(intent, countDownTimeRest);

        }
    }
    public boolean stopService(Intent intent){
        if (intent.getAction().equals(false)){
            stopService(intent);
        }
        return false;
    }


}

