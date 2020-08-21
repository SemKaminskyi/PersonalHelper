package com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model;

public class PlanerDetails {
    private final long taskID =-1L;

    private String task;
    private Boolean checkTask =false;

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

    public void setTask(String task) {
        this.task = task;
    }

}

