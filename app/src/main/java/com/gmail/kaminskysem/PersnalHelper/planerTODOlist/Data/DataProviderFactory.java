package com.gmail.kaminskysem.PersnalHelper.planerTODOlist.Data;

import android.content.Context;

import androidx.room.Room;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.IUserPlanerDao;

public class DataProviderFactory {

    private static AppDatabase roomDB;
    private static final InMemoryUserPlanerProvider inMemoryProvider = InMemoryUserPlanerProvider.getInstance();

    public static IUserPlanerDao getDataProvider (Context context){
        if (roomDB ==null){
            roomDB = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "Tasks").build();
        }
        return roomDB.userPlanerDao();
    }
}

