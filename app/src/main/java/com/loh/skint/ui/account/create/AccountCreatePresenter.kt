package com.loh.skint.ui.account.create

import com.loh.skint.R
import com.loh.skint.domain.model.AVAILABLE_CURRENCIES
import com.loh.skint.domain.model.Account
import com.loh.skint.domain.model.AccountIcon
import com.loh.skint.domain.usecase.account.AddAccount
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import io.reactivex.observers.DisposableSingleObserver
import org.threeten.bp.LocalDate
import timber.log.Timber
import java.io.Serializable
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

@ActivityScoped
class AccountCreatePresenter @Inject constructor(private val addAccount: AddAccount) : BasePresenter<View>(), Presenter {

    private var selectedIcon: AccountIcon? = null
    private var selectedCurrencyIndex: Int? = null

    override fun onIconClicked() {
        getView().navigateToIconSelector()
    }

    override fun onCurrencyClicked() {
        getView().showCurrencySelector()
    }

    override fun onCurrencySelected(index: Int) {
        val currency = AVAILABLE_CURRENCIES[index]
        getView().setCurrency(currency.name)
        selectedCurrencyIndex = index
    }

    override fun onIconSelected(iconId: Int) {
        val icon = Account.findIconById(iconId)
        selectedIcon = icon
        getView().setIcon(icon.iconResId)
    }

    override fun saveAccount() {
        // gather input
        val name = getView().getName()
        val initialBalance = getView().getInitialBalance()

        // validate input
        if (name.isBlank()) {
            getView().showMessage(R.string.name_invalid_error)
            return
        }

        val balanceRegex = "^[0-9]+(\\.[0-9]{1,2})?$"
        if (initialBalance.isBlank() || !initialBalance.matches(Regex(balanceRegex))) {
            getView().showMessage(R.string.balance_invalid_error)
            return
        }

        if (selectedCurrencyIndex == null) {
            getView().showMessage(R.string.category_select_error)
            return
        }

        if (selectedIcon == null) {
            getView().showMessage(R.string.icon_select_error)
            return
        }

        val currency = AVAILABLE_CURRENCIES[selectedCurrencyIndex!!]

        val account = Account(
                UUID.randomUUID(),
                name,
                BigDecimal(initialBalance),
                currency,
                LocalDate.now(),
                selectedIcon!!
        )

        // pass to usecase
        addAccount.execute(Observer(), account)

    }

    override fun onSaveState(): State {
        return State(selectedIcon,
                getView().getName(),
                selectedCurrencyIndex,
                getView().getInitialBalance())
    }

    override fun onRestoreState(state: State) {
        state.currencyIndex?.let { onCurrencySelected(it) }
        state.icon?.let { onIconSelected(it.id) }
    }

    override fun cleanUp() {
        addAccount.dispose()
    }

    data class State(val icon: AccountIcon?,
                     val accountName: String,
                     val currencyIndex: Int?,
                     val initialBalance: String) : Serializable

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