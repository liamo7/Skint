package com.loh.skint.ui.goal.list

import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.base.presenter.MvpPresenter

interface View : AccountView {
    fun hideEmptyState()
    fun showEmptyState()

    fun hideGoals()
    fun showGoals()
}

interface Presenter : MvpPresenter<View> {
    fun retrieveGoals()
}