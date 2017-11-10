package com.loh.skint.ui.account.list

import com.loh.skint.ui.base.MvpView
import com.loh.skint.ui.base.presenter.MvpPresenter
import com.loh.skint.ui.model.Account

interface View : MvpView {
    fun showAccounts(accounts: List<Account>)
    fun hideAccountsList()

    fun showEmptyState()
    fun hideEmptyState()

    fun navigateToAccount(id: Int)
    fun navigateToAccountCreation()
}

interface Presenter : MvpPresenter<View> {
    fun retrieveAccounts()
    fun onAccountItemClicked(account: Account)
    fun onFabClicked()
}