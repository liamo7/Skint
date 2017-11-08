package com.loh.skint.ui.account.overview

import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.base.presenter.MvpPresenter
import com.loh.skint.ui.model.Account
import com.loh.skint.ui.model.Record

interface View : AccountView {
    fun handleInvalidAccount()
    fun renderOverviewCollapse(account: Account)
    fun renderRecentRecords(recentRecords: List<Record>)
}

interface Presenter : MvpPresenter<View> {
    fun loadAccount()
}