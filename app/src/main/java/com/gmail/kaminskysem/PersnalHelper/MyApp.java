package com.gmail.kaminskysem.PersnalHelper;

import android.app.Application;
import android.content.Context;

import com.gmail.kaminskysem.PersnalHelper.DI.AppModule;
import com.gmail.kaminskysem.PersnalHelper.DI.ApplicationsComponent;
import com.gmail.kaminskysem.PersnalHelper.DI.DBModule;
import com.gmail.kaminskysem.PersnalHelper.DI.DaggerApplicationsComponent;
import com.gmail.kaminskysem.PersnalHelper.Notifications.MyFirebaseMessagingService;

public class MyApp extends Application {


    private ApplicationsComponent applicationsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationsComponent = DaggerApplicationsComponent.builder()
                .appModule(new AppModule(this))
                .dBModule(new DBModule())
                        .build();
        MyFirebaseMessagingService.registerForPushNotifications ();

    }
    public  static ApplicationsComponent getApplicationsComponent (Context context){
        return  ((MyApp)context.getApplicationContext()).getApplicationsComponent();
    }

    public ApplicationsComponent getApplicationsComponent() {
        return applicationsComponent;
    }

}
