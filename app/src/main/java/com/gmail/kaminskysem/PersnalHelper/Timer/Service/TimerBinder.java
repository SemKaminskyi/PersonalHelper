package com.gmail.kaminskysem.PersnalHelper.Timer.Service;

import android.os.Binder;

public class TimerBinder extends Binder {

    public static Class<TimerService> getService() {
        return TimerService.class;
    }
}
