package com.gmail.kaminskysem.PersnalHelper.Data;

import android.content.Context;

public class DataProviderFactory {

    private static UserTasksDataBaseHelper dbProvider;
    private static final InMemoryUserPlanerProvider inMemoryProvider = InMemoryUserPlanerProvider.getInstance();

    public static IUserPlanerProvider getDataProvider (Context context){
        if (dbProvider ==null){
            dbProvider = new UserTasksDataBaseHelper(context);
        }
        return dbProvider;
    }
}
