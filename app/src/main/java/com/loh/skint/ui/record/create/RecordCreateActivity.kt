package com.loh.skint.ui.record.create

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.loh.skint.R
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.base.activity.BaseActivity
import com.loh.skint.util.INTENT_ACCOUNT_ID
import com.loh.skint.util.disable
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.activity_record_create.*
import org.threeten.bp.LocalDate
import java.util.*
import javax.inject.Inject

class RecordCreateActivity : BaseActivity(), View, DatePickerDialog.OnDateSetListener {

    companion object {
        @JvmStatic val ARG_STATE = "STATE"
    }

    @Inject lateinit var presenter: Presenter

    private val transferTypeDialog: MaterialDialog by lazy {
        MaterialDialog.Builder(this)
                .title(R.string.title_transfer_type)
                .items(R.array.transfer_types)
                .itemsCallbackSingleChoice(-1, { _, _, which, _ ->
                    presenter.onTransferTypeSelected(which)
                    true
                })
                .positiveText(R.string.choose)
                .build()
    }

    private val categoryAdapter = CategoryAdapter({
        presenter.onCategorySelected(it)
        categoryDialog.hide()
    })

    private val categoryDialog: MaterialDialog by lazy {
        MaterialDialog.Builder(this)
                .title(R.string.category_select)
                .adapter(categoryAdapter, GridLayoutManager(this, 4))
                .negativeText(R.string.cancel)
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackToolbar(toolbar, R.drawable.ic_arrow_back)
        presenter.attach(this)

        if (savedInstanceState != null && savedInstanceState[ARG_STATE] != null) {
            presenter.onRestoreState(savedInstanceState.getSerializable(ARG_STATE) as RecordCreatePresenter.State)
        }

        // disable entry
        record_create_transfer_type_input.disable()
        record_create_date_input.disable()

        // setup click listeners for actions
        record_create_transfer_type_input.setOnClickListener { presenter.onTransferTypeClicked() }
        record_create_transfer_type_action.setOnClickListener { presenter.onTransferTypeClicked() }

        record_create_date_input.setOnClickListener { presenter.onDateClicked() }
        record_create_date_action.setOnClickListener { presenter.onDateClicked() }

        record_create_icon.setOnClickListener { presenter.onCategoryIconClicked() }

        fab_save_record.setOnClickListener { presenter.saveRecord() }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable(ARG_STATE, presenter.onSaveState())
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun getLayoutRes(): Int = R.layout.activity_record_create

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun getAccountUUID(): UUID = intent.getSerializableExtra(INTENT_ACCOUNT_ID) as UUID

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        presenter.onDateSelected(year, monthOfYear, dayOfMonth)
    }

    override fun showCategorySelector() {
        categoryDialog.show()
    }

    override fun showTransferTypeSelector() {
        transferTypeDialog.show()
    }

    override fun showDateSelector(date: LocalDate) {
        // month value for local date starts at 1
        val dpd = DatePickerDialog.newInstance(this, date.year, date.monthValue - 1, date.dayOfMonth)
        dpd.show(fragmentManager, "dpd")
    }

    override fun setCategoryIcon(@DrawableRes iconRes: Int) {
        record_create_icon.setImageResource(iconRes)
    }

    override fun setTransferType(transferType: String) {
        record_create_transfer_type_input.setText(transferType)
    }

    override fun setDate(date: String) {
        record_create_date_input.setText(date)
    }

    override fun getAmount(): String {
        return record_create_amount.text.toString()
    }

    override fun getNote(): String {
        return record_create_note_input.text.toString()
    }

    override fun showMessage(@StringRes stringRes: Int) {
        Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
    }

    override fun navigateBackToRecordList() {
        finish()
    }
}