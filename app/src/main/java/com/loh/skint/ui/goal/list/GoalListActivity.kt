package com.loh.skint.ui.goal.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.loh.skint.R
import com.loh.skint.domain.model.Goal
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.account.BaseAccountDrawerActivity
import com.loh.skint.util.goalCreateIntent
import com.loh.skint.util.goalDetailIntent
import com.loh.skint.util.hide
import com.loh.skint.util.show
import kotlinx.android.synthetic.main.activity_goals_list.*
import java.util.*
import javax.inject.Inject

class GoalListActivity : BaseAccountDrawerActivity(), View {

    @Inject lateinit var presenter: Presenter
    @Inject lateinit var listAdapter: GoalListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = listAdapter
        listAdapter.clickListener = { goal -> presenter.onGoalClicked(goal) }
        fab_add_goal.setOnClickListener { presenter.onFabClicked() }
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

    override fun showGoals(goals: MutableList<Goal>) {
        recycler_view.show()
        listAdapter.goals = goals
    }

    override fun navigateToGoal(uuid: UUID) {
        startActivity(goalDetailIntent(uuid))
    }

    override fun navigateToGoalCreation() {
        startActivity(goalCreateIntent(getAccountUUID()))
    }
}