package com.gmail.kaminskysem.PersnalHelper.Timer;

import java.io.Serializable;
import java.util.Observable;

public class TimeToWidget extends Observable implements Serializable {
    private int time;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        this.setChanged();
        this.notifyObservers(time);
    }
}
