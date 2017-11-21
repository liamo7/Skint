package com.loh.skint.ui.goal.detail

import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import javax.inject.Inject

@ActivityScoped
class GoalDetailPresenter @Inject constructor() : BasePresenter<View>(), Presenter {

    override fun cleanUp() {}
}