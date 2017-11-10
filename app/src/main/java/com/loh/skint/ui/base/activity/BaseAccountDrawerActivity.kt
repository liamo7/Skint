package com.loh.skint.ui.base.activity

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.design.widget.NavigationView
import android.view.Gravity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.loh.skint.R
import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.model.Account
import com.loh.skint.util.*

abstract class BaseAccountDrawerActivity : BaseDrawerActivity(), AccountView, NavigationView.OnNavigationItemSelectedListener {

    @IdRes
    abstract fun getMenuItemRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onResume() {
        super.onResume()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        navigationView.setCheckedItem(getMenuItemRes())
    }

    override fun renderNavHeader(account: Account) {
        val nameView = findViewById<TextView>(R.id.nav_header_account_name)
        val balanceView = findViewById<TextView>(R.id.nav_header_account_balance)
        val iconView = findViewById<ImageView>(R.id.nav_header_account_icon)

        nameView.text = account.name
        balanceView.text = account.prettyBalance
        iconView.setImageResource(account.iconResId)
    }

    override fun getAccountId(): Int? = intent.extras[INTENT_ACCOUNT_ID] as Int

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val accountId = getAccountId()
        val intent = when (item.itemId) {
            R.id.nav_overview -> accountOverview(accountId)
            R.id.nav_records -> recordsList(accountId)
            R.id.nav_budgets -> budgetsList(accountId)
            R.id.nav_goals -> goalsList(accountId)
            R.id.nav_charts -> chartsList(accountId)
            R.id.nav_reports -> reportsList(accountId)
            R.id.nav_settings -> settings()
            else -> null
        }
        drawerLayout.closeDrawer(Gravity.START)

        if (item.itemId == R.id.nav_sign_out) {
            startActivity(accountListIntent().bringToFront().clearHistory())
            return true
        }


        if (intent != null) {
            startActivity(intent.bringToFront())
            return true
        }

        return false
    }
}