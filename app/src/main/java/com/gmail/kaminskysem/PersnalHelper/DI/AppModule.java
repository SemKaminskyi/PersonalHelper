package com.gmail.kaminskysem.PersnalHelper.DI;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context context;

    public AppModule (@NonNull Context context){
        this.context = context;
    }

    @Singleton
    @Provides
    @NonNull
    public Context provideContext (){
        return context;
    }
}
