package com.loh.skint.injection.module

import android.app.Application
import android.content.Context
import com.loh.skint.injection.qualifier.App
import dagger.Binds
import dagger.Module

@Module
internal abstract class AppModule {
    @Binds @App
    abstract fun bindsAppContext(app: Application): Context
}