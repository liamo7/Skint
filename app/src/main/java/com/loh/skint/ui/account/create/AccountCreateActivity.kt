package com.loh.skint.ui.account.create

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.loh.skint.R
import com.loh.skint.domain.model.AVAILABLE_CURRENCIES
import com.loh.skint.domain.model.Account
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.base.activity.BaseActivity
import com.loh.skint.ui.base.adapter.IconAdapter
import com.loh.skint.util.accountOverview
import com.loh.skint.util.disable
import com.loh.skint.util.tint
import kotlinx.android.synthetic.main.activity_account_create.*
import javax.inject.Inject

class AccountCreateActivity : BaseActivity(), View {

    @Inject lateinit var presenter: Presenter

    private val accountIcons = listOf(
            R.drawable.ic_coins,
            R.drawable.ic_credit_card,
            R.drawable.ic_piggy_bank,
            R.drawable.ic_money_bag,
            R.drawable.ic_safebox,
            R.drawable.ic_wallet,
            R.drawable.ic_other
    )

    private val iconAdapter = IconAdapter(accountIcons, {
        presenter.onIconSelected(it)
        iconSelectorDialog.hide()
    })

    private val iconSelectorDialog: MaterialDialog by lazy {
        MaterialDialog.Builder(this)
                .title(R.string.icon_select_title)
                .adapter(iconAdapter, GridLayoutManager(this, 4))
                .negativeText(R.string.cancel)
                .build()
    }

    private val currencyDialog: MaterialDialog by lazy {
        MaterialDialog.Builder(this)
                .title(R.string.title_currencies)
                .items(AVAILABLE_CURRENCIES)
                .itemsCallbackSingleChoice(-1, { _, _, which, _ ->
                    presenter.onCurrencySelected(which)
                    true
                })
                .positiveText(R.string.choose)
                .negativeText(R.string.cancel)
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
        setBackToolbar(toolbar, R.drawable.ic_close)

        if (savedInstanceState != null && savedInstanceState[ARG_STATE] != null) {
            presenter.onRestoreState(savedInstanceState.getSerializable(ARG_STATE) as AccountCreatePresenter.State)
        }

        // disable key listener
        account_create_currency_input.disable()
        account_create_currency_input.setOnClickListener { presenter.onCurrencyClicked() }
        account_create_currency_action.setOnClickListener { presenter.onCurrencyClicked() }
        account_create_icon.setOnClickListener { presenter.onIconClicked() }

        fab_save_account.setOnClickListener { presenter.saveAccount() }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable(ARG_STATE, presenter.onSaveState())
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun getLayoutRes(): Int = R.layout.activity_account_create

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun navigateToIconSelector() {
        iconSelectorDialog.show()
    }

    override fun navigateToAccount(account: Account) {
        startActivity(accountOverview(account.uuid))
        finish()
    }

    override fun showCurrencySelector() {
        currencyDialog.show()
    }

    override fun setCurrency(currency: String) {
        account_create_currency_input.setText(currency)
    }

    override fun setIcon(iconResId: Int) {
        account_create_icon.setImageResource(iconResId)
        account_create_icon.tint(R.color.white)
    }

    override fun getName(): String = account_create_name.text.toString()

    override fun getInitialBalance(): String = account_create_balance_input.text.toString()

    override fun showMessage(stringRes: Int) = Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
}