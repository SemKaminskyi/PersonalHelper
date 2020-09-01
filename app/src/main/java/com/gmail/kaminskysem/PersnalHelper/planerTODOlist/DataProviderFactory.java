package com.gmail.kaminskysem.PersnalHelper.planerTODOlist;

import android.content.Context;

import androidx.room.Room;

import com.gmail.kaminskysem.PersnalHelper.Data.AppDatabase;
import com.gmail.kaminskysem.PersnalHelper.Data.InMemoryUserPlanerProvider;

public class DataProviderFactory {

    private static AppDatabase roomDB;
    public static final InMemoryUserPlanerProvider inMemoryProvider = InMemoryUserPlanerProvider.getInstance();

    public static UserPlanerDao getDataProvider (Context context){
        if (roomDB == null){
            roomDB = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "tasks").build();
        }
        return roomDB.userPlanerDao();
    }
}
