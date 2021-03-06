package com.gmail.kaminskysem.PersnalHelper.planerTODOlist.Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.IUserPlanerDao;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetails;

@Database(entities = {PlanerDetails.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract IUserPlanerDao userPlanerDao();
}
