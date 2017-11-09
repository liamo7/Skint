package com.loh.skint.injection.component

import com.loh.skint.SkintApp
import com.loh.skint.injection.module.ActivityModule
import com.loh.skint.injection.module.AppModule
import com.loh.skint.injection.module.DataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        DataModule::class))
interface AppComponent {
    fun inject(app: SkintApp)
    fun plus(module: ActivityModule): ActivityComponent
}