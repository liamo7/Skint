package com.loh.skint.ui.account

import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.base.presenter.MvpPresenter
import com.loh.skint.ui.model.Account

interface View : AccountView {
    fun renderNavHeader(account: Account)
}

interface Presenter : MvpPresenter<View> {
    fun loadAccount()
}