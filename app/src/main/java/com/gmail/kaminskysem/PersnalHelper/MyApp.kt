package com.gmail.kaminskysem.PersnalHelper

import android.app.Application
import android.content.Context
import com.gmail.kaminskysem.PersnalHelper.DI.AppModule
import com.gmail.kaminskysem.PersnalHelper.DI.ApplicationsComponent
import com.gmail.kaminskysem.PersnalHelper.DI.DBModule
import com.gmail.kaminskysem.PersnalHelper.DI.DaggerApplicationsComponent
import com.gmail.kaminskysem.PersnalHelper.Notifications.MyFirebaseMessagingService
import timber.log.Timber

class MyApp : Application() {

    lateinit var applicationsComponent: ApplicationsComponent
        private set

    override fun onCreate() {
        super.onCreate()
        
        applicationsComponent = DaggerApplicationsComponent.builder()
            .appModule(AppModule(this))
            .dBModule(DBModule())
            .build()
            
        MyFirebaseMessagingService.registerForPushNotifications()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree()) // <- this wrong is NOT WRONG
        }
    }

    companion object {
        @JvmStatic
        fun getApplicationsComponent(context: Context): ApplicationsComponent {
            return (context.applicationContext as MyApp).applicationsComponent
        }
    }
}
