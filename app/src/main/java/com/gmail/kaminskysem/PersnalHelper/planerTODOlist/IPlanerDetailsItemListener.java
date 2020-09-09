package com.gmail.kaminskysem.PersnalHelper.planerTODOlist;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetails;

public interface IPlanerDetailsItemListener {
    void onTaskItemClick(int itemId, PlanerDetails planerDetails);
}
