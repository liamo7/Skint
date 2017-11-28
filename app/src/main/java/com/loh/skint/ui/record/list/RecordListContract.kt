package com.loh.skint.ui.record.list

import com.loh.skint.domain.model.Record
import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.base.presenter.MvpPresenter
import com.loh.skint.util.DateRange
import org.threeten.bp.LocalDate
import java.util.*

interface View : AccountView {
    fun displayEmptyState()
    fun hideEmptyState()
    fun displayRecords(records: List<Record>)
    fun hideRecords()
    fun renderDatebar(date: LocalDate, dateRange: DateRange)
    fun getDateRange(): DateRange
    fun getDate(): LocalDate
    fun navigateToRecordCreation(accountUUID: UUID)
    fun navigateToRecord(uuid: UUID, accountUUID: UUID)
}

interface Presenter : MvpPresenter<View> {
    fun retrieveRecords()
    fun onRecordClicked(record: Record)
    fun onAddRecord()
}