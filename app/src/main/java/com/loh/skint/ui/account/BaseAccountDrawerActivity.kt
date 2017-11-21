package com.loh.skint.ui.account

import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.design.widget.NavigationView
import android.view.Gravity
import android.view.MenuItem
import com.loh.skint.R
import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.base.activity.BaseDrawerActivity
import com.loh.skint.util.*
import java.util.*
import javax.inject.Inject

abstract class BaseAccountDrawerActivity : BaseDrawerActivity(), View, AccountView, NavigationView.OnNavigationItemSelectedListener {

    @Inject lateinit var drawerPresenter: Presenter

    @IdRes
    abstract fun getMenuItemRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drawerPresenter.attach(this)
        //drawerPresenter.loadAccount()
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onResume() {
        super.onResume()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        navigationView.setCheckedItem(getMenuItemRes())
    }

    override fun onDestroy() {
        super.onDestroy()
        drawerPresenter.detach()
    }

//    override fun renderNavHeader(account: Account) {
//        val nameView = findViewById<TextView>(R.id.nav_header_account_name)
//        val balanceView = findViewById<TextView>(R.id.nav_header_account_balance)
//        val iconView = findViewById<ImageView>(R.id.nav_header_account_icon)
//
//        nameView.text = account.name
//        balanceView.text = account.prettyBalance()
//        iconView.setImageResource(account.getIconResId(this))
//    }

    override fun getAccountUUID(): UUID = intent.extras[INTENT_ACCOUNT_ID] as UUID

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val accountId = getAccountUUID()
        val intent = when (item.itemId) {
            R.id.nav_overview -> accountOverview(accountId)
            R.id.nav_records -> recordsList(accountId)
            //R.id.nav_budgets -> budgetsList(accountId)
            R.id.nav_goals -> goalsList(accountId)
            R.id.nav_charts -> chartsList(accountId)
            //R.id.nav_reports -> reportsList(accountId)
            //R.id.nav_settings -> settings()
            else -> null
        }
        drawerLayout.closeDrawer(Gravity.START)

        // when user signs out, clear all task history
        if (item.itemId == R.id.nav_sign_out) {
            startActivity(accountListIntent().bringToFront().clearHistory())
            return true
        }

        // disallow opening new instance of active activity.
        // overview activity should'nt be finished
        if (intent != null && !intent.eqClasses(componentName)) {
            navigateToDrawerItem(intent)
            return true
        }

        return false
    }

    private fun navigateToDrawerItem(intent: Intent) {
        startActivity(intent.bringToFront())
        if (intent.isOverview()) finish()
    }
}