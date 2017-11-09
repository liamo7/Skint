package com.loh.skint.injection.module

import android.support.v4.app.Fragment
import com.loh.skint.injection.scope.FragmentScoped
import com.loh.skint.ui.record.list.RecordListPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule constructor(private val fragment: Fragment) {

    @Provides @FragmentScoped
    fun provideRecordListPresenter(presenter: RecordListPresenter): com.loh.skint.ui.record.list.Presenter {
        return presenter
    }
}