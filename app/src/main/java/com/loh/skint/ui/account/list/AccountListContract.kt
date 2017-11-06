package com.loh.skint.ui.account.list

import com.loh.skint.ui.base.MvpView
import com.loh.skint.ui.base.presenter.MvpPresenter
import com.loh.skint.ui.model.Account

interface View : MvpView {
    fun renderAccounts(accounts: List<Account>)
    fun renderEmptyState()
    fun navigateToAccount(id: Int)
    fun navigateToAccountCreation()
}

interface Presenter : MvpPresenter<View> {
    fun retrieveAccounts()
    fun onAccountClicked(account: Account)
    fun onFabClicked()
}