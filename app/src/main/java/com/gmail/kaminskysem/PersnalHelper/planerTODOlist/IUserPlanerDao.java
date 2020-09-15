package com.gmail.kaminskysem.PersnalHelper.planerTODOlist;

import androidx.annotation.WorkerThread;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetails;

import java.util.List;

@Dao
public interface IUserPlanerDao {
    @WorkerThread
    @Query("SELECT * FROM  tasks WHERE task_id = :id")
    PlanerDetails getTaskById(long id);

    @WorkerThread
    @Query("SELECT * FROM  tasks ORDER BY task_id DESC")
    List<PlanerDetails> getTaskList();

    @WorkerThread
    @Update
    void updateTaskWithId(PlanerDetails task);

    @WorkerThread
    @Insert
    void addNewTask( PlanerDetails planerDetails) ;

    @WorkerThread
    @Delete
    void delete(PlanerDetails task);


}
