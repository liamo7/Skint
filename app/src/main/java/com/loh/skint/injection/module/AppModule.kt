package com.loh.skint.injection.module

import android.app.Application
import android.content.Context
import com.loh.skint.injection.qualifier.App
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
internal class AppModule {

    @Provides @App
    fun providesAppContext(app: Application): Context = app

    @Provides
    fun providesCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}