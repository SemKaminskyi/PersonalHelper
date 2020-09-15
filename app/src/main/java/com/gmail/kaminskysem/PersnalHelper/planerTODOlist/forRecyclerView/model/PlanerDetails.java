package com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class PlanerDetails {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    private long taskID;

    @ColumnInfo(name = "task")
    private String taskString;

    @ColumnInfo(name = "check_task")
    private Boolean checkTask = false;

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public PlanerDetails() {
        this.taskString = "";
        this.checkTask = false;
    }

    public PlanerDetails(long taskID, String taskString, boolean checkTask) {
        this.taskString = taskString;
        this.checkTask = checkTask;
        this.taskID =taskID;
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


    public String getTaskString() {
        return taskString;
    }

    public void setTaskString(String taskString) {
        this.taskString = taskString;
    }

}

