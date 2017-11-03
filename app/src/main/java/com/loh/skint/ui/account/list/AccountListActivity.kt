package com.loh.skint.ui.account.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import com.loh.skint.R
import com.loh.skint.ui.base.activity.BaseActivity
import com.loh.skint.ui.model.Account
import kotlinx.android.synthetic.main.activity_account_list.*
import javax.inject.Inject

class AccountListActivity : BaseActivity(), View {

    @Inject lateinit var listAdapter: AccountListAdapter
    @Inject lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.title_account_list)
        setupList()
        presenter.attach(this)
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
}