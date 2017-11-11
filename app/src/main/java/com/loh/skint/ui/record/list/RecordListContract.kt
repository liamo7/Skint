package com.loh.skint.ui.record.list

import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.base.presenter.MvpPresenter
import com.loh.skint.ui.model.Record

interface View : AccountView {
    fun displayEmptyState()
    fun hideEmptyState()
    fun displayRecords(records: List<Record>)
    fun hideRecords()
}

interface Presenter : MvpPresenter<View> {
    fun retrieveRecords()
    fun onRecordClicked()
    fun onAddRecord()
}