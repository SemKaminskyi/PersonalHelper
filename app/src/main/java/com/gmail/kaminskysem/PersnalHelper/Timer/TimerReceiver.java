package com.gmail.kaminskysem.PersnalHelper.Timer;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class TimerReceiver extends BroadcastReceiver {
    public static final String LOG_TAG = TimerReceiver.class.getSimpleName();
    public static final String SIMPLE_ACTION ="com.gmail.kaminskysem.PersnalHelper.Timer.SIMPLE_ACTION";
    public static String timeFromBroadcastWork=TimerTImerFragment.TIMER_WORK;

    @Override
    public void onReceive(Context context, Intent intent) {
       timeFromBroadcastWork = intent.getStringExtra(TimerTImerFragment.TIMER_WORK);
        Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT);
        Log.d(LOG_TAG, " broadcast caught "+timeFromBroadcastWork);
    }
}

