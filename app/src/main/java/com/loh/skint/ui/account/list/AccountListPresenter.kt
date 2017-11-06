package com.loh.skint.ui.account.list

import com.loh.skint.domain.usecase.account.GetAccounts
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import com.loh.skint.ui.model.Account
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class AccountListPresenter @Inject constructor(private val getAccounts: GetAccounts) : BasePresenter<View>(), Presenter {

    override fun onFabClicked() {
        getView().navigateToAccountCreation()
    }

    override fun retrieveAccounts() {
        getAccounts.execute({ onSuccess(it) }, { onError(it) }, null)
    }

    private fun onSuccess(accounts: List<Account>) {
        if (accounts.isEmpty())
            getView().renderEmptyState()
        else
            getView().renderAccounts(accounts)
    }

    private fun onError(throwable: Throwable) {
        Timber.e(throwable.message)
        getView().renderEmptyState()
    }

    override fun onAccountClicked(account: Account) {
        getView().navigateToAccount(account.dbId)
    }

    override fun cleanUp() {
        getAccounts.dispose()
    }
}
