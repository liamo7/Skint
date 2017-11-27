package com.loh.skint.ui.account.create

import android.support.annotation.StringRes
import com.loh.skint.domain.model.Account
import com.loh.skint.ui.base.MvpView
import com.loh.skint.ui.base.presenter.MvpPresenter

interface View : MvpView {
    fun navigateToIconSelector()
    fun navigateToAccount(account: Account)
    fun showCurrencySelector()
    fun setCurrency(currency: String)
    fun setIcon(iconResId: Int)
    fun getName(): String
    fun getInitialBalance(): String
    fun showMessage(@StringRes stringRes: Int)
}

interface Presenter : MvpPresenter<View> {
    fun onIconClicked()
    fun onCurrencyClicked()
    fun onCurrencySelected(index: Int)
    fun onIconSelected(iconResId: Int)
    fun saveAccount()
    fun onSaveState(): AccountCreatePresenter.State
    fun onRestoreState(state: AccountCreatePresenter.State)
}