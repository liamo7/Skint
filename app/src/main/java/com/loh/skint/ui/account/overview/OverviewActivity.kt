package com.loh.skint.ui.account.overview

import android.os.Bundle
import com.loh.skint.R
import com.loh.skint.domain.model.Account
import com.loh.skint.domain.model.Record
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.account.BaseAccountDrawerActivity
import kotlinx.android.synthetic.main.activity_account_overview.*
import javax.inject.Inject

class OverviewActivity : BaseAccountDrawerActivity(), View {

    @Inject lateinit var presenter: Presenter

    private lateinit var collapseView: OverviewCollapse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
        presenter.loadAccount()

        fab_add_record.setOnClickListener { presenter.onFabClicked() }
    }

    override fun getLayoutRes(): Int = R.layout.activity_account_overview

    override fun getMenuItemRes(): Int = R.id.nav_overview

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun handleInvalidAccount() = finish()

    override fun renderOverviewCollapse(account: Account) {
        collapseView = findViewById(R.id.overview_view)
        collapseView.setAccount(account)
    }

    override fun renderRecentRecords(recentRecords: List<Record>) {
        recent_records.setRecords(recentRecords)
    }

    override fun navigateToRecordCreation() {}

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}
