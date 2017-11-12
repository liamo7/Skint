package com.loh.skint.ui.record.list

import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.base.presenter.MvpPresenter
import com.loh.skint.ui.model.Record
import com.loh.skint.util.DateRange
import org.threeten.bp.LocalDate

interface View : AccountView {
    fun displayEmptyState()
    fun hideEmptyState()
    fun displayRecords(records: List<Record>)
    fun hideRecords()
    fun renderDatebar(date: LocalDate, dateRange: DateRange)
    fun getDateRange(): DateRange
    fun getDate(): LocalDate
    fun navigateToRecordCreation(accountId: Int)
}

interface Presenter : MvpPresenter<View> {
    fun retrieveRecords()
    fun onRecordClicked()
    fun onAddRecord()
}