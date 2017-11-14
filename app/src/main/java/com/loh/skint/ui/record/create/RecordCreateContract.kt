package com.loh.skint.ui.record.create

import android.support.annotation.DrawableRes
import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.base.presenter.MvpPresenter
import org.threeten.bp.LocalDate

interface View : AccountView {
    fun showCategorySelector()
    fun showTransferTypeSelector()
    fun showDateSelector(date: LocalDate)
    fun showLocationSelector()
    fun setCategoryIcon(@DrawableRes iconRes: Int)
    fun setTransferType(transferType: String)
    fun setDate(date: String)
    fun getAmount() : String
    fun getNote() : String
}

interface Presenter : MvpPresenter<View> {
    fun onTransferTypeClicked()
    fun onDateClicked()
    fun onLocationClicked()
    fun onCategoryIconClicked()
    fun onCategorySelected(categoryId: Int)
    fun onDateSelected(year: Int, monthOfYear: Int, dayOfMonth: Int)
    fun onTransferTypeSelected(itemIndex: Int)
    fun saveRecord()

    fun onSaveState(): RecordCreatePresenter.State
    fun onRestoreState(state: RecordCreatePresenter.State)
}
