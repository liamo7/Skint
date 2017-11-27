package com.loh.skint.ui.goal.list

import com.loh.skint.domain.model.Goal
import com.loh.skint.domain.usecase.goal.GetGoals
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class GoalListPresenter @Inject constructor(private val getGoals: GetGoals) : BasePresenter<View>(), Presenter {

    override fun retrieveGoals() {
        val uuid = getView().getAccountUUID()
        getGoals.execute(Observer(), GetGoals.Params(uuid))
    }

    override fun onGoalClicked(goal: Goal) {
        getView().navigateToGoal(goal.uuid)
    }

    override fun onFabClicked() {
        getView().navigateToGoalCreation()
    }

    override fun cleanUp() {
        getGoals.dispose()
    }

    private fun renderEmptyState() {
        getView().hideGoals()
        getView().showEmptyState()
    }

    inner class Observer : DisposableSingleObserver<MutableList<Goal>>() {
        override fun onError(e: Throwable) {
            Timber.e("${e.message}")
            renderEmptyState()
        }

        override fun onSuccess(goals: MutableList<Goal>) {
            if (goals.isEmpty()) {
                renderEmptyState()
            } else {
                getView().hideEmptyState()
                getView().showGoals(goals)
            }
        }
    }
}