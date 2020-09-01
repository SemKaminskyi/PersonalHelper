package com.gmail.kaminskysem.PersnalHelper.planerTODOlist;

import android.content.Context;

import androidx.room.Room;

import com.gmail.kaminskysem.PersnalHelper.Data.InMemoryPlanerProvider;

public class DataProviderFactory {

    private static AppDatabase roomDB;
    public static final InMemoryPlanerProvider inMemoryProvider = InMemoryPlanerProvider.getInstance();

    public static UserPlanerDao getDataProvider (Context context){
        if (roomDB == null){
            roomDB = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "tasks").build();
        }
        return roomDB.userPlanerDao();
    }
}
