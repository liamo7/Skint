package com.loh.skint.ui.account.overview

import com.loh.skint.domain.model.Account
import com.loh.skint.domain.model.Record
import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.base.presenter.MvpPresenter

interface View : AccountView {
    fun handleInvalidAccount()
    fun renderOverviewCollapse(account: Account)
    fun renderRecentRecords(recentRecords: List<Record>)
    fun navigateToRecordCreation()
}

interface Presenter : MvpPresenter<View> {
    fun loadAccount()
    fun onFabClicked()
}