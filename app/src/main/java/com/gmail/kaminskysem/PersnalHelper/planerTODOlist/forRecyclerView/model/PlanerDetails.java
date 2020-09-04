package com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "tasks")
public class PlanerDetails {
    @PrimaryKey ( autoGenerate = true)
    @ColumnInfo (name = "task_id")
    private  long taskID;

@ColumnInfo (name = "task")
    private String task;

@ColumnInfo (name = "check_task")
    private Boolean checkTask =false;

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public PlanerDetails(long taskID, String task, boolean checkTask ) {
        this.task = task;
        this.checkTask =checkTask;
    }
    public Long getTaskID() {
        return taskID;
    }


    public Boolean getCheckTask() {
        return checkTask;
    }

    public void setCheckTask(Boolean checkTask) {
        this.checkTask = checkTask;
    }


    public String getTask() {
        return task;
    }

    public void setStringTask(String task) {
        this.task = task;
    }

}

