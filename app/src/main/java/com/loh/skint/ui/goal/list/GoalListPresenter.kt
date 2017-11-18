package com.loh.skint.ui.goal.list

import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import javax.inject.Inject

@ActivityScoped
class GoalListPresenter @Inject constructor() : BasePresenter<View>(), Presenter {

    override fun retrieveGoals() {

    }

    override fun cleanUp() {}

    inner class Observer
}