package com.loh.skint.ui.account.list

import com.loh.skint.ui.base.MvpView
import com.loh.skint.ui.base.presenter.MvpPresenter
import com.loh.skint.ui.model.Account

interface View : MvpView {
    fun renderAccounts(accounts: List<Account>)
    fun renderEmptyState()
}

interface Presenter : MvpPresenter<View> {
    fun retrieveAccounts()
}