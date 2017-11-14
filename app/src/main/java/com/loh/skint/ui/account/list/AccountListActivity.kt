package com.loh.skint.ui.account.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.loh.skint.R
import com.loh.skint.domain.model.Account
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.base.activity.BaseActivity
import com.loh.skint.util.accountCreateIntent
import com.loh.skint.util.accountOverview
import com.loh.skint.util.hide
import com.loh.skint.util.show
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
        setupList()
        presenter.attach(this)
        fab_create_account.setOnClickListener { presenter.onFabClicked() }
        presenter.retrieveAccounts()
    }

    override fun getLayoutRes(): Int = R.layout.activity_account_list

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    private fun setupList() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = listAdapter
        listAdapter.setListener { account -> presenter.onAccountItemClicked(account) }
    }

    override fun showAccounts(accounts: List<Account>) {
        recycler_view.show()
        listAdapter.setAccounts(accounts)
    }

    override fun hideAccountsList() = recycler_view.hide()

    override fun showEmptyState() = empty_container.show()

    override fun hideEmptyState() = empty_container.hide()

    override fun navigateToAccount(uuid: UUID) = startActivity(accountOverview(uuid))

    override fun navigateToAccountCreation() = startActivity(accountCreateIntent())
}
