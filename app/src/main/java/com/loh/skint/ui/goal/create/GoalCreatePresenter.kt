package com.loh.skint.ui.goal.create

import com.loh.skint.R
import com.loh.skint.domain.model.Goal
import com.loh.skint.domain.usecase.goal.AddGoal
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import com.loh.skint.ui.goal.list.GoalIconAdapter
import com.loh.skint.util.LONG_DATE_FORMAT
import com.loh.skint.util.isValidDecimal
import io.reactivex.observers.DisposableCompletableObserver
import org.threeten.bp.LocalDate
import timber.log.Timber
import java.io.Serializable
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

@ActivityScoped
class GoalCreatePresenter @Inject constructor(private val addGoal: AddGoal) : BasePresenter<View>(), Presenter {

    private var selectedIconRes: Int? = null
    private var selectedTargetDate: LocalDate? = null

    override fun openIconSelector() = getView().showIconSelector()

    override fun onIconSelected(id: Int) {
        val iconRes = GoalIconAdapter.icons[id]
        selectIcon(iconRes)
    }

    override fun onTargetDateClicked() {
        getView().showTargetDateSelector(LocalDate.now())
    }

    private fun selectIcon(iconResId: Int) {
        getView().setIconSelected(iconResId)
        selectedIconRes = iconResId
    }

    override fun onTargetDateSelected(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val date = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
        selectTargetDate(date)
    }

    private fun selectTargetDate(date: LocalDate) {
        val prettyDate = date.format(LONG_DATE_FORMAT)
        getView().setTargetDate(prettyDate)
        selectedTargetDate = date
    }

    override fun saveGoal() {
        // gather input
        val accountUUID = getView().getAccountUUID()

        val name = getView().getName()
        val note = getView().getNote()
        val savedAmount = getView().getSavedAmount()
        val targetAmount = getView().getTargetAmount()
        val targetDate = selectedTargetDate
        val iconRes = selectedIconRes

        if (iconRes == null) {
            getView().showMessage(R.string.icon_select_error)
            return
        }

        if (!savedAmount.isValidDecimal() || !targetAmount.isValidDecimal()) {
            getView().showMessage(R.string.amount_invalid_error)
            return
        }

        val goal = Goal(
                UUID.randomUUID(),
                name,
                note,
                iconRes,
                LocalDate.now(),
                targetDate,
                BigDecimal(savedAmount),
                BigDecimal(targetAmount),
                accountUUID
        )

        val params = AddGoal.Params(accountUUID, goal)
        addGoal.execute(Observer(), params)
    }

    override fun onSaveState(): State {
        return State(selectedIconRes, selectedTargetDate)
    }

    override fun onRestoreState(state: State) {
        state.iconResId?.let { selectIcon(it) }
        state.targetDate?.let { selectTargetDate(it) }
    }

    private fun showGenericError() = getView().showMessage(R.string.oops_error)

    override fun cleanUp() {
        addGoal.dispose()
    }

    inner class Observer : DisposableCompletableObserver() {
        override fun onComplete() {
            getView().showMessage(R.string.goal_created_success)
            getView().navigateToGoalsList()
            Timber.d("Goal created successfully.")
        }

        override fun onError(e: Throwable) {
            showGenericError()
            Timber.e(e.message)
        }
    }

    data class State(val iconResId: Int?,
                     val targetDate: LocalDate?) : Serializable

}