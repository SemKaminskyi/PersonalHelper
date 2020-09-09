package com.gmail.kaminskysem.PersnalHelper.planerTODOlist;

import androidx.annotation.WorkerThread;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetails;

import java.util.List;

    @Dao
public interface IUserPlanerDao {
    @WorkerThread
    @Query("SELECT * FROM  tasks WHERE task_id = :id")
    PlanerDetails getTaskById (long id);

    @WorkerThread
     @Query("SELECT * FROM  tasks")
     List<PlanerDetails> getTaskList();

    @WorkerThread
    @Update
    void updateTaskWithId (PlanerDetails task);

    @WorkerThread
    @Insert
    long addNewTask(PlanerDetails task);

}
