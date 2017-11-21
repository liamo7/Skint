package com.loh.skint.ui.goal.create

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.base.presenter.MvpPresenter
import org.threeten.bp.LocalDate

interface View : AccountView {
    fun showIconSelector()
    fun setIconSelected(@DrawableRes iconRes: Int)
    fun setTargetDate(date: String)
    fun showTargetDateSelector(date: LocalDate)

    fun getName(): String
    fun getSavedAmount(): String
    fun getTargetAmount(): String
    fun getNote(): String
    fun showMessage(@StringRes messageRes: Int)
    fun navigateToGoalsList()
}

interface Presenter : MvpPresenter<View> {
    fun openIconSelector()
    fun onTargetDateClicked()
    fun onIconSelected(id: Int)
    fun onTargetDateSelected(year: Int, monthOfYear: Int, dayOfMonth: Int)

    fun saveGoal()

    fun onSaveState(): GoalCreatePresenter.State
    fun onRestoreState(state: GoalCreatePresenter.State)
}