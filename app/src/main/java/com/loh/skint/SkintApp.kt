package com.loh.skint

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.loh.skint.injection.component.AppComponent
import com.loh.skint.injection.component.DaggerAppComponent
import com.loh.skint.injection.module.AppModule
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class SkintApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)
        AndroidThreeTen.init(this)
        appComponent.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
//            deleteDatabase("skint.db")
        }
    }
}