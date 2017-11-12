package com.loh.skint.injection.component

import com.loh.skint.injection.module.ActivityModule
import com.loh.skint.injection.module.FragmentModule
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.account.create.AccountCreateActivity
import com.loh.skint.ui.account.list.AccountListActivity
import com.loh.skint.ui.account.overview.OverviewActivity
import com.loh.skint.ui.category.list.CategoryListActivity
import com.loh.skint.ui.record.create.RecordCreateActivity
import com.loh.skint.ui.record.list.RecordListActivity
import dagger.Subcomponent

@ActivityScoped
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(activity: AccountCreateActivity)
    fun inject(activity: AccountListActivity)
    fun inject(activity: OverviewActivity)
    fun inject(activity: RecordListActivity)
    fun inject(activity: RecordCreateActivity)
    fun inject(activity: CategoryListActivity)

    fun plus(module: FragmentModule): FragmentComponent
}