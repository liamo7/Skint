package com.loh.skint.ui.account.overview

import android.os.Bundle
import com.loh.skint.R
import com.loh.skint.domain.model.Record
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.account.BaseAccountDrawerActivity
import com.loh.skint.ui.model.OverviewModel
import com.loh.skint.util.recordCreateIntent
import kotlinx.android.synthetic.main.activity_account_overview.*
import java.util.*
import javax.inject.Inject

class OverviewActivity : BaseAccountDrawerActivity(), View {

    @Inject lateinit var presenter: Presenter

    private lateinit var collapseView: OverviewCollapse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
        fab_add_record.setOnClickListener { presenter.onFabClicked() }
    }

    override fun onResume() {
        super.onResume()
        presenter.loadAccount()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun getLayoutRes(): Int = R.layout.activity_account_overview

    override fun getMenuItemRes(): Int = R.id.nav_overview

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun handleInvalidAccount() = finish()

    override fun renderOverviewCollapse(overviewModel: OverviewModel) {
        collapseView = findViewById(R.id.overview_view)
        collapseView.setOverview(overviewModel)
    }

    override fun renderRecentRecords(recentRecords: List<Record>) {
        recent_records.setRecords(recentRecords)
    }

    override fun navigateToRecordCreation(uuid: UUID) {
        startActivity(recordCreateIntent(uuid))
    }
}
