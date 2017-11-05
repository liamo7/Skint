package com.loh.skint

import com.facebook.stetho.Stetho
import com.loh.skint.data.entity.AccountEntity
import com.loh.skint.domain.repository.AccountRepository
import com.loh.skint.injection.component.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class SkintApp : DaggerApplication() {

    @Inject lateinit var repository: AccountRepository

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)

        deleteDatabase("skint.db")
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }

        repository.add(AccountEntity().apply {
            uuid = UUID.randomUUID()
            name = "Current Account"
            balance = "302.95"
            dateCreated = Date()
            iconResName = "ic_wallet"
        }).subscribe()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerAppComponent.builder().application(this).build()
        component.inject(this)
        return component
    }
}