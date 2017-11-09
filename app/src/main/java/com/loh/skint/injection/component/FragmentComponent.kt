package com.loh.skint.injection.component

import com.loh.skint.injection.module.FragmentModule
import com.loh.skint.injection.scope.FragmentScoped
import com.loh.skint.ui.record.list.RecordListFragment
import dagger.Subcomponent

@FragmentScoped
@Subcomponent(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
    fun inject(fragment: RecordListFragment)
}