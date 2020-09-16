package com.gmail.kaminskysem.PersnalHelper.DI;

import android.content.Context;

import androidx.room.Room;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.Data.AppDatabase;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.IUserPlanerDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DBModule {

   @Provides
   public IUserPlanerDao getDataProvider (AppDatabase database){
       return database.userPlanerDao();
   }
@Singleton
@Provides
    public  AppDatabase getAppDataBase (Context context){

        return Room
                .databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Tasks")
                .build();
    }
}
