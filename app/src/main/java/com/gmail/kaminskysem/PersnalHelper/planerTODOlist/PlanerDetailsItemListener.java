package com.gmail.kaminskysem.PersnalHelper.planerTODOlist;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetails;

public interface PlanerDetailsItemListener {
    void onTaskItemClick(int itemId, PlanerDetails planerDetails);
}
