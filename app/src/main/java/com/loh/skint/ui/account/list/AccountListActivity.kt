package com.loh.skint.ui.account.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import com.loh.skint.R
import com.loh.skint.ui.account.overview.OverviewActivity
import com.loh.skint.ui.base.activity.BaseActivity
import com.loh.skint.ui.model.Account
import com.loh.skint.util.INTENT_ACCOUNT_ID
import com.loh.skint.util.accountCreateIntent
import kotlinx.android.synthetic.main.activity_account_list.*
import java.util.*
import javax.inject.Inject

class AccountListActivity : BaseActivity(), View {
    @Inject lateinit var listAdapter: AccountListAdapter

    @Inject lateinit var presenter: Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.title_account_list)
        presenter.attach(this)
        setupList()
        fab_create_account.setOnClickListener { presenter.onFabClicked() }
        presenter.retrieveAccounts()
    }

    override fun getLayoutRes(): Int = R.layout.activity_account_list

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    private fun setupList() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = listAdapter

        listAdapter.setListener { account -> presenter.onAccountClicked(account) }
    }

    override fun renderAccounts(accounts: List<Account>) {
        recycler_view.visibility = VISIBLE
        empty_container.visibility = GONE
        listAdapter.setAccounts(accounts)
    }

    override fun renderEmptyState() {
        recycler_view.visibility = GONE
        empty_container.visibility = VISIBLE
    }

    override fun navigateToAccount(uuid: UUID) {
        startActivity(Intent(this, OverviewActivity::class.java).apply { putExtra(INTENT_ACCOUNT_ID, uuid) })
    }

    override fun navigateToAccountCreation() {
        startActivity(accountCreateIntent())
    }
}
