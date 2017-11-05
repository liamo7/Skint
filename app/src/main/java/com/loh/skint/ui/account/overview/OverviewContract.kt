package com.loh.skint.ui.account.overview

import com.loh.skint.ui.base.MvpView
import com.loh.skint.ui.base.presenter.MvpPresenter
import com.loh.skint.ui.model.Account
import java.util.*

interface View : MvpView {
    fun getAccountId(): UUID?
    fun handleInvalidAccount()
    fun renderOverviewCollapse(account: Account)
}

interface Presenter : MvpPresenter<View> {
    fun loadAccount()
}