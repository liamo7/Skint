package com.loh.skint.ui.account.create

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.loh.skint.R
import com.loh.skint.domain.model.AVAILABLE_CURRENCIES
import com.loh.skint.domain.model.Account
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.account.icon.AccountIconListActivity.Companion.ARG_SELECTED_ICON
import com.loh.skint.ui.account.icon.AccountIconListActivity.Companion.INTENT_REQUEST_CODE
import com.loh.skint.ui.base.activity.BaseActivity
import com.loh.skint.util.accountIconSelectorIntent
import com.loh.skint.util.accountOverview
import com.loh.skint.util.disable
import kotlinx.android.synthetic.main.activity_account_create.*
import javax.inject.Inject

class AccountCreateActivity : BaseActivity(), View {

    @Inject lateinit var presenter: Presenter

    private val currencyDialog: MaterialDialog by lazy {
        MaterialDialog.Builder(this)
                .title(R.string.title_currencies)
                .items(AVAILABLE_CURRENCIES)
                .itemsCallbackSingleChoice(-1, { _, _, which, _ ->
                    presenter.onCurrencySelected(which)
                    true
                })
                .positiveText(R.string.choose)
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
        setBackToolbar(toolbar, R.drawable.ic_close)

        // disable key listener
        account_create_currency_input.disable()
        account_create_currency_input.setOnClickListener { presenter.onCurrencyClicked() }
        account_create_currency_action.setOnClickListener { presenter.onCurrencyClicked() }
        account_create_icon.setOnClickListener { presenter.onIconClicked() }

        fab_save_account.setOnClickListener { presenter.saveAccount() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == INTENT_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.extras?.getInt(ARG_SELECTED_ICON)?.let {
                presenter.onIconSelected(it)
            }
        }
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun getLayoutRes(): Int = R.layout.activity_account_create

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun navigateToIconSelector() {
        startActivityForResult(accountIconSelectorIntent(), INTENT_REQUEST_CODE)
    }

    override fun navigateToAccount(account: Account) {
        startActivity(accountOverview(account.uuid))
        finish()
    }

    override fun showCurrencySelector() {
        if (!currencyDialog.isShowing) currencyDialog.show()
    }

    override fun setCurrency(currency: String) {
        account_create_currency_input.setText(currency)
    }

    override fun setIcon(iconResId: Int) {
        account_create_icon.setImageResource(iconResId)
    }

    override fun getName(): String = account_create_name.text.toString()

    override fun getInitialBalance(): String = account_create_balance_input.text.toString()

    override fun showMessage(stringRes: Int) = Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
}