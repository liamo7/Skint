package com.loh.skint.ui.base

import com.loh.skint.ui.model.Account

interface AccountView : MvpView {
    fun renderNavHeader(account: Account)
    fun getAccountId(): Int?
}