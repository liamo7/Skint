package com.loh.skint.ui.goal.list

import android.os.Bundle
import com.loh.skint.R
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.account.BaseAccountDrawerActivity
import com.loh.skint.util.hide
import com.loh.skint.util.show
import kotlinx.android.synthetic.main.activity_goals_list.*
import javax.inject.Inject

class GoalListActivity : BaseAccountDrawerActivity(), View {

    @Inject lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.retrieveGoals()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun getMenuItemRes(): Int = R.id.nav_goals

    override fun getLayoutRes(): Int = R.layout.activity_goals_list

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun hideEmptyState() = empty_container.hide()

    override fun showEmptyState() = empty_container.show()

    override fun hideGoals() = recycler_view.hide()

    override fun showGoals() {
        recycler_view.show()
    }
}