package com.gmail.kaminskysem.PersnalHelper.planer.model;

import android.widget.EditText;

public final class CardDetails {
    private String task;

    public CardDetails(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
