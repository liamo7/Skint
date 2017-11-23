package com.loh.skint.ui.goal.detail

import com.loh.skint.domain.model.Goal
import com.loh.skint.domain.usecase.goal.GetGoal
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import com.loh.skint.util.LONG_DATE_FORMAT
import com.loh.skint.util.isValidDecimal
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class GoalDetailPresenter @Inject constructor(private val getGoal: GetGoal) : BasePresenter<View>(), Presenter {

    override fun loadGoal() {
        val uuid = getView().getGoalUUID()
        getGoal.execute(GetGoalObserver(), GetGoal.Params(uuid))
    }

    fun renderGoal(goal: Goal) {
        val prettyDate = goal.targetDate?.format(LONG_DATE_FORMAT) ?: "N/A"

        // TODO: Actually look at last record
        val lastRecordAmount = goal.records.getOrNull(0)?.amount?.toPlainString() ?: "N/A"

        getView().displayName(goal.name)
        getView().displayIcon(goal.iconResId)
        getView().displayNote(goal.note)
        getView().displayProgress(goal.progress(), goal.prettySavedAmount())
        getView().displayTargetAmount(goal.prettyTargetAmount())
        getView().displayTargetDate(prettyDate)
        getView().displayLastAddedAmount(lastRecordAmount)
    }

    override fun deleteGoal() {
        TODO("not implemented")
    }

    override fun addAmount(amount: String) {
        // validate amount
        if (!amount.isValidDecimal()) {
            Timber.d("Invalid goal amount.")
            return
        }

        // execute
    }

    override fun cleanUp() {
        getGoal.dispose()
    }

    inner class GetGoalObserver : DisposableSingleObserver<Goal>() {
        override fun onSuccess(goal: Goal) {
            renderGoal(goal)
        }

        override fun onError(e: Throwable) {
            Timber.e(e.message)
        }
    }
}