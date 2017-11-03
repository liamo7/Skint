package com.loh.skint.ui.account.list

import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import com.loh.skint.ui.model.Account
import java.util.*
import javax.inject.Inject

@ActivityScoped
class AccountListPresenter @Inject constructor() : BasePresenter<View>(), Presenter {

    override fun retrieveAccounts() {
        val accounts = arrayListOf(
                Account(UUID.randomUUID(), "Current Account", "300.00"),
                Account(UUID.randomUUID(), "Savings Account", "5100.00"),
                Account(UUID.randomUUID(), "Wallet", "100.00")
        )

        val rand = Random().nextInt(2)

        if (rand == 0) {
            getView().renderAccounts(accounts)
        } else {
            getView().renderEmptyState()
        }
    }

    override fun cleanUp() {
    }
}