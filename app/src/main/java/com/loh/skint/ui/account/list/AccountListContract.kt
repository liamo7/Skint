package com.loh.skint.ui.account.list

import com.loh.skint.domain.model.Account
import com.loh.skint.ui.base.MvpView
import com.loh.skint.ui.base.presenter.MvpPresenter
import java.util.*

interface View : MvpView {
    fun showAccounts(accounts: List<Account>)
    fun hideAccountsList()

    fun showEmptyState()
    fun hideEmptyState()

    fun navigateToAccount(uuid: UUID)
    fun navigateToAccountCreation()
}

interface Presenter : MvpPresenter<View> {
    fun retrieveAccounts()
    fun onAccountItemClicked(account: Account)
    fun onFabClicked()
}