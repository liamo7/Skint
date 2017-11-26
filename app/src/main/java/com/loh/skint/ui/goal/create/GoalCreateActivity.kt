package com.loh.skint.ui.goal.create

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.loh.skint.R
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.base.activity.BaseActivity
import com.loh.skint.ui.goal.list.GoalIconAdapter
import com.loh.skint.util.INTENT_ACCOUNT_ID
import com.loh.skint.util.disable
import com.loh.skint.util.tint
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.activity_goal_create.*
import org.threeten.bp.LocalDate
import java.util.*
import javax.inject.Inject

class GoalCreateActivity : BaseActivity(), View, DatePickerDialog.OnDateSetListener {

    companion object {
        @JvmField val ARG_STATE = "STATE"
    }

    @Inject lateinit var presenter: Presenter

    private val iconAdapter: GoalIconAdapter = GoalIconAdapter({
        presenter.onIconSelected(it)
        iconSelectorDialog.hide()
    })

    private val iconSelectorDialog: MaterialDialog by lazy {
        MaterialDialog.Builder(this)
                .title(R.string.icon_select_title)
                .adapter(iconAdapter, GridLayoutManager(this, 4))
                .negativeText(R.string.cancel)
                .onNegative({ _, _ -> iconSelectorDialog.hide() })
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
        setBackToolbar(toolbar, R.drawable.ic_close)

        if (savedInstanceState != null && savedInstanceState[ARG_STATE] != null) {
            presenter.onRestoreState(savedInstanceState.getSerializable(ARG_STATE) as GoalCreatePresenter.State)
        }

        goal_create_target_date_input.disable()
        goal_create_icon.setOnClickListener { presenter.openIconSelector() }

        goal_create_target_date_input.setOnClickListener { presenter.onTargetDateClicked() }
        goal_create_target_date_action.setOnClickListener { presenter.onTargetDateClicked() }

        fab_save_goal.setOnClickListener { presenter.saveGoal() }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable(ARG_STATE, presenter.onSaveState())
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun getLayoutRes(): Int = R.layout.activity_goal_create

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun showIconSelector() {
        iconSelectorDialog.show()
    }

    override fun setIconSelected(iconRes: Int) {
        goal_create_icon.setImageResource(iconRes)
        goal_create_icon.tint(R.color.white)
    }

    override fun setTargetDate(date: String) = goal_create_target_date_input.setText(date)

    override fun showTargetDateSelector(date: LocalDate) {
        val dpd = DatePickerDialog.newInstance(this, date.year, date.monthValue - 1, date.dayOfMonth)
        dpd.show(fragmentManager, "dpd")
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        presenter.onTargetDateSelected(year, monthOfYear, dayOfMonth)
    }

    override fun getName(): String = goal_create_name.text.toString()

    override fun getSavedAmount(): String = goal_create_saved_amount_input.text.toString()

    override fun getTargetAmount(): String = goal_create_target_amount_input.text.toString()

    override fun getNote(): String = goal_create_note_input.text.toString()

    override fun getAccountUUID(): UUID = intent.getSerializableExtra(INTENT_ACCOUNT_ID) as UUID

    override fun showMessage(messageRes: Int) = Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()

    override fun navigateToGoalsList() = finish()
}