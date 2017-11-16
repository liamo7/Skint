package com.loh.skint.injection.module

import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.account.AccountDrawerPresenter
import com.loh.skint.ui.account.create.AccountCreatePresenter
import com.loh.skint.ui.account.list.AccountListPresenter
import com.loh.skint.ui.account.overview.OverviewPresenter
import com.loh.skint.ui.account.overview.Presenter
import com.loh.skint.ui.category.list.CategoryListPresenter
import com.loh.skint.ui.record.create.RecordCreatePresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule constructor(private val activity: AppCompatActivity) {

    @Provides @ActivityScoped
    fun provideFragmentManager(): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides @ActivityScoped
    fun provideAccountOverviewPresenter(presenter: OverviewPresenter): Presenter {
        return presenter
    }

    @Provides @ActivityScoped
    fun provideAccountListPresenter(presenter: AccountListPresenter): com.loh.skint.ui.account.list.Presenter {
        return presenter
    }

    @Provides @ActivityScoped
    fun provideNavDrawerPresenter(presenter: AccountDrawerPresenter): com.loh.skint.ui.account.Presenter {
        return presenter
    }

    @Provides @ActivityScoped
    fun provideRecordCreatePresenter(presenter: RecordCreatePresenter): com.loh.skint.ui.record.create.Presenter {
        return presenter
    }

    @Provides @ActivityScoped
    fun provideCategoryListPresenter(presenter: CategoryListPresenter): com.loh.skint.ui.category.list.Presenter {
        return presenter
    }

    @Provides @ActivityScoped
    fun provideAccountCreatePresenter(presenter: AccountCreatePresenter): com.loh.skint.ui.account.create.Presenter {
        return presenter
    }
}