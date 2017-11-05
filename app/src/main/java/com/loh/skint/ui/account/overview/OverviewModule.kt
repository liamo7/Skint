package com.loh.skint.ui.account.overview

import com.loh.skint.injection.scope.ActivityScoped
import dagger.Module
import dagger.Provides

@Module
class OverviewModule {

    @Provides @ActivityScoped
    fun providePresenter(presenter: OverviewPresenter): Presenter = presenter
}