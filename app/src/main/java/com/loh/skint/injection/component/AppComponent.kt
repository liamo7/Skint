package com.loh.skint.injection.component

import android.app.Application
import com.loh.skint.SkintApp
import com.loh.skint.injection.module.ActivityBindingModule
import com.loh.skint.injection.module.AppModule
import com.loh.skint.injection.module.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        DataModule::class,
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class))
internal interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(app: SkintApp)

    override fun inject(instance: DaggerApplication?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}