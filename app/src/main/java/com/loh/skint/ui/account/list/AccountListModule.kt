package com.loh.skint.ui.account.list

import com.loh.skint.injection.scope.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class AccountListModule {
    @Binds @ActivityScoped
    abstract fun bindPresenter(presenter: AccountListPresenter): Presenter
}