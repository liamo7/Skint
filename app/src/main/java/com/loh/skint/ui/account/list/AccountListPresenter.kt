package com.loh.skint.ui.account.list

import com.loh.skint.domain.mapper.AccountMapper
import com.loh.skint.domain.repository.AccountRepository
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class AccountListPresenter @Inject constructor(val repository: AccountRepository) : BasePresenter<View>(), Presenter {

    override fun retrieveAccounts() {
        // TODO Move to interactor
        val mapper = AccountMapper()

        repository.getAll().subscribe({
            if (it.isEmpty()) {
                getView().renderEmptyState()
            } else {
                getView().renderAccounts(mapper.mapDomainToUi(it))
            }
        }, { Timber.d(it.message) })
    }

    override fun cleanUp() {
    }
}