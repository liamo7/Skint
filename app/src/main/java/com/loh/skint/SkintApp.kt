package com.loh.skint

import android.app.Application
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class SkintApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }
}