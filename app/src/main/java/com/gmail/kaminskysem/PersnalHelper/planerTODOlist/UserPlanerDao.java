package com.gmail.kaminskysem.PersnalHelper.planerTODOlist;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetails;

import java.util.List;

public interface UserPlanerDao {

    @Query("SELECT * FROM  tasks WHERE task_id = :id")
    PlanerDetails getTaskById (long id);

     @Query("SELECT * FROM  tasks")
     List<PlanerDetails> getTaskList();

    @Update
    void updateTaskWithId (PlanerDetails task);

    @Insert
    long addNewTask(PlanerDetails task);

}
