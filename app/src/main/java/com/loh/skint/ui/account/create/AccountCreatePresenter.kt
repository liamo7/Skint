package com.loh.skint.ui.account.create

import com.loh.skint.R
import com.loh.skint.domain.model.AVAILABLE_CURRENCIES
import com.loh.skint.domain.model.Account
import com.loh.skint.domain.model.Currency
import com.loh.skint.domain.usecase.account.AddAccount
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import com.loh.skint.util.isValidDecimal
import io.reactivex.observers.DisposableSingleObserver
import org.threeten.bp.LocalDate
import timber.log.Timber
import java.io.Serializable
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

@ActivityScoped
class AccountCreatePresenter @Inject constructor(private val addAccount: AddAccount) : BasePresenter<View>(), Presenter {

    //private var selectedIcon: Int? = null
    //private var selectedCurrencyIndex: Int? = null

    private var selectedIcon: Int? = null
    private var selectedCurrency: Currency? = null

    override fun onIconClicked() {
        getView().navigateToIconSelector()
    }

    override fun onCurrencyClicked() {
        getView().showCurrencySelector()
    }

    override fun onCurrencySelected(index: Int) {
        val currency = AVAILABLE_CURRENCIES.getOrNull(index)
        currency?.let { onCurrencySelected(it) }
    }

    private fun onCurrencySelected(currency: Currency) {
        getView().setCurrency(currency.name)
        selectedCurrency = currency

    }

    override fun onIconSelected(iconResId: Int) {
        getView().setIcon(iconResId)
        selectedIcon = iconResId
    }

    override fun saveAccount() {
        // gather input
        val name = getView().getName()
        val initialBalance = getView().getInitialBalance()
        val icon = selectedIcon
        val currency = selectedCurrency

        // validate input
        if (name.isBlank()) {
            getView().showMessage(R.string.name_invalid_error)
            return
        }

        if (!initialBalance.isValidDecimal()) {
            getView().showMessage(R.string.balance_invalid_error)
            return
        }

        if (currency == null) {
            getView().showMessage(R.string.currency_invalid_error)
            return
        }

        if (icon == null) {
            getView().showMessage(R.string.icon_select_error)
            return
        }

        val account = Account(
                UUID.randomUUID(),
                name,
                BigDecimal(initialBalance),
                currency,
                LocalDate.now(),
                icon
        )

        addAccount.execute(Observer(), account)
    }

    override fun onSaveState(): State {
        return State(selectedIcon,
                selectedCurrency)
    }

    override fun onRestoreState(state: State) {
        state.currency?.let { onCurrencySelected(it) }
        state.iconResId?.let { onIconSelected(it) }
    }

    override fun cleanUp() {
        addAccount.dispose()
    }

    data class State(val iconResId: Int?,
                     val currency: Currency?) : Serializable

    inner class Observer : DisposableSingleObserver<Account>() {
        override fun onSuccess(account: Account) {
            getView().showMessage(R.string.account_create_success)
            getView().navigateToAccount(account)
        }

        override fun onError(e: Throwable) {
            Timber.e(e.message)
        }
    }
}