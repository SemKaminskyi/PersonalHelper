package com.gmail.kaminskysem.PersnalHelper.Timer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TimerReceiver extends BroadcastReceiver {
    public static final String LOG_TAG = TimerReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        String timeFromBroadcastWork = intent.getAction();
        Log.d(LOG_TAG, " broadcast "+timeFromBroadcastWork);
    }
}
