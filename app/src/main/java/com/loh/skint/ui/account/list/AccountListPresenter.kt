package com.loh.skint.ui.account.list

import com.loh.skint.domain.usecase.account.GetAccounts
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import com.loh.skint.ui.model.Account
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class AccountListPresenter @Inject constructor(private val getAccounts: GetAccounts) : BasePresenter<View>(), Presenter {

    override fun retrieveAccounts() {
        getAccounts.execute(Observer(), null)
    }

    override fun onAccountItemClicked(account: Account) {
        getView().navigateToAccount(account.dbId)
    }

    override fun onFabClicked() {
        getView().navigateToAccountCreation()
    }

    override fun cleanUp() {
        getAccounts.dispose()
    }

    inner class Observer : DisposableSingleObserver<List<Account>>() {

        override fun onError(e: Throwable) {
            Timber.e(e.message)

            getView().hideAccountsList()
            getView().showEmptyState()
        }

        override fun onSuccess(accounts: List<Account>) {
            if (accounts.isEmpty()) {
                getView().hideAccountsList()
                getView().showEmptyState()
            } else {
                getView().showAccounts(accounts)
                getView().hideEmptyState()
            }
        }
    }
}
