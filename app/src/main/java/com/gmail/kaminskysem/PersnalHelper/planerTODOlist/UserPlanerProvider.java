package com.gmail.kaminskysem.PersnalHelper.planerTODOlist;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetails;

import java.util.ArrayList;
import java.util.List;

public class UserPlanerProvider {
    List<PlanerDetails> getCardsDetails (){
        List<PlanerDetails>cardList = new ArrayList<>();
        cardList.add(new PlanerDetails(-1,"new task", false));

        return cardList;
    }
}
