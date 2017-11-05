package com.loh.skint.ui.account.overview

import com.loh.skint.domain.usecase.account.GetAccount
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import com.loh.skint.ui.model.Account
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class OverviewPresenter @Inject constructor(private val getAccount: GetAccount) : BasePresenter<View>(), Presenter {

    override fun loadAccount() {
        val id = getView().getAccountId()

        if (id == null) {
            getView().handleInvalidAccount()
            return
        }

        getAccount.execute({ onSuccess(it) }, { onError(it) }, id)
    }

    private fun onSuccess(account: Account) {
        Timber.d(account.toString())
        getView().renderOverviewCollapse(account)
    }

    private fun onError(throwable: Throwable) {
        Timber.e(throwable.message)
    }

    override fun cleanUp() {
        getAccount.dispose()
    }
}