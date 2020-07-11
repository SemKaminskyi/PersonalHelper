package com.gmail.kaminskysem.PersnalHelper.Data;

import com.gmail.kaminskysem.PersnalHelper.planer.forRecyclerView.model.PlanerDetails;

import java.util.List;

public interface IUserPlanerProvider {
    PlanerDetails getTaskById(long id);

    List<PlanerDetails> getPlanerList();

    void updateTaskWithID(long taskID, PlanerDetails task);

    long addNewTask(PlanerDetails task);
}
