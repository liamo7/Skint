package com.loh.skint.ui.goal.list

import com.loh.skint.domain.model.Goal
import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.base.presenter.MvpPresenter
import java.util.*

interface View : AccountView {
    fun hideEmptyState()
    fun showEmptyState()

    fun hideGoals()
    fun showGoals(goals: MutableList<Goal>)
    fun navigateToGoal(uuid: UUID)
    fun navigateToGoalCreation()
}

interface Presenter : MvpPresenter<View> {
    fun retrieveGoals()
    fun onFabClicked()
    fun onGoalClicked(goal: Goal)
}