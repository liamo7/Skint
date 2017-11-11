package com.loh.skint.ui.account

import com.loh.skint.domain.usecase.account.GetAccount
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import com.loh.skint.ui.model.Account
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class AccountDrawerPresenter @Inject constructor(private val getAccount: GetAccount) : BasePresenter<View>(), Presenter {

    override fun loadAccount() {
        val id = getView().getAccountId()
        if (id == null) {
            Timber.e("Invalid Account ID")
            return
        }

        getAccount.execute(Observer(), id)
    }

    override fun cleanUp() = getAccount.dispose()

    inner class Observer : DisposableSingleObserver<Account>() {
        override fun onError(e: Throwable) {
            Timber.e(e.message)
        }

        override fun onSuccess(account: Account) {
            getView().renderNavHeader(account)
        }

    }
}