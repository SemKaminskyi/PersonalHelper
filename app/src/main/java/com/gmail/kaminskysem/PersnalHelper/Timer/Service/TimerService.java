package com.gmail.kaminskysem.PersnalHelper.Timer.Service;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.Timer.TimerTImerFragment;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                String timeWork = intent.getStringExtra("TimerWork");
                if (!TextUtils.isEmpty(timeWork)) {
                    CountDownTimer countDownTimer = new CountDownTimer(Integer.parseInt(timeWork) * 1000, 1000) {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onTick(long millisUntilFinished) {
                            String textWork = "seconds of  Work" + (int) (millisUntilFinished / 1000);
                        }

                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onFinish() {
                            String finished = "Finished";
                        }
                    }.start();
                    Log.d(LOG_TAG, "service is Work");

                }
            }
        });
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind" );
        return null;
    }
}

