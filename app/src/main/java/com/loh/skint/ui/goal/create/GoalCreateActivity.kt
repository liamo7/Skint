package com.loh.skint.ui.goal.create

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.loh.skint.R
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.base.activity.BaseActivity
import com.loh.skint.ui.base.adapter.IconAdapter
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

    private val goalIcons = listOf(
            R.drawable.ic_accomodation,
            R.drawable.ic_bills,
            R.drawable.ic_budget,
            R.drawable.ic_bullseye,
            R.drawable.ic_car,
            R.drawable.ic_cinema,
            R.drawable.ic_clothing,
            R.drawable.ic_coin_hand,
            R.drawable.ic_credit_card,
            R.drawable.ic_dashboard,
            R.drawable.ic_dollar,
            R.drawable.ic_drinks,
            R.drawable.ic_education,
            R.drawable.ic_entertainment,
            R.drawable.ic_food_drink,
            R.drawable.ic_gift,
            R.drawable.ic_groceries,
            R.drawable.ic_health,
            R.drawable.ic_hobbies,
            R.drawable.ic_home,
            R.drawable.ic_kids,
            R.drawable.ic_loan,
            R.drawable.ic_location,
            R.drawable.ic_money_bag,
            R.drawable.ic_mortgage,
            R.drawable.ic_other,
            R.drawable.ic_personal,
            R.drawable.ic_pets,
            R.drawable.ic_phone,
            R.drawable.ic_piggy_bank,
            R.drawable.ic_rent,
            R.drawable.ic_report,
            R.drawable.ic_safebox,
            R.drawable.ic_salary,
            R.drawable.ic_savings,
            R.drawable.ic_shopping,
            R.drawable.ic_transport,
            R.drawable.ic_travel,
            R.drawable.ic_utilities,
            R.drawable.ic_wallet)

    private val iconAdapter: IconAdapter = IconAdapter(goalIcons, {
        presenter.onIconSelected(it)
        iconSelectorDialog.hide()
    })

    private val iconSelectorDialog: MaterialDialog by lazy {
        MaterialDialog.Builder(this)
                .title(R.string.icon_select_title)
                .adapter(iconAdapter, GridLayoutManager(this, 4))
                .negativeText(R.string.cancel)
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