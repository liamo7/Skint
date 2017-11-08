package com.loh.skint.injection.module

import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.account.create.AccountCreateActivity
import com.loh.skint.ui.account.create.AccountCreateModule
import com.loh.skint.ui.account.list.AccountListActivity
import com.loh.skint.ui.account.list.AccountListModule
import com.loh.skint.ui.account.overview.OverviewActivity
import com.loh.skint.ui.account.overview.OverviewModule
import com.loh.skint.ui.record.list.RecordListActivity
import com.loh.skint.ui.record.list.RecordListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(AccountListModule::class))
    internal abstract fun accountListActivity(): AccountListActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(OverviewModule::class))
    internal abstract fun overviewActivity(): OverviewActivity

    @ContributesAndroidInjector(modules = arrayOf(AccountCreateModule::class))
    internal abstract fun accountCreateActivity(): AccountCreateActivity

    @ContributesAndroidInjector(modules = arrayOf(RecordListModule::class))
    internal abstract fun recordListActivity(): RecordListActivity
}