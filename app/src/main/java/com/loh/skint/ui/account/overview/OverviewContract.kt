package com.loh.skint.ui.account.overview

import com.loh.skint.domain.model.Goal
import com.loh.skint.domain.model.Record
import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.base.presenter.MvpPresenter
import com.loh.skint.ui.model.OverviewModel
import java.util.*

interface View : AccountView {
    fun handleInvalidAccount()
    fun renderOverviewCollapse(overviewModel: OverviewModel)
    fun renderRecentRecords(recentRecords: List<Record>)
    fun renderUpcomingGoals(upcomingGoals: List<Goal>)
    fun navigateToRecordCreation(uuid: UUID)
}

interface Presenter : MvpPresenter<View> {
    fun loadAccount()
    fun onFabClicked()
}