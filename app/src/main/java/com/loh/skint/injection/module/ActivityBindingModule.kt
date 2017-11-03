package com.loh.skint.injection.module

import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.account.list.AccountListActivity
import com.loh.skint.ui.account.list.AccountListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(AccountListModule::class))
    internal abstract fun accountListActivity(): AccountListActivity
}