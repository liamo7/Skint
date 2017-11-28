package com.loh.skint.ui.record.detail

import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.base.presenter.MvpPresenter
import java.util.*

interface View : AccountView {
    fun getRecordUUID(): UUID
    fun setCategoryIcon(iconRes: Int)
    fun setAmount(amount: String, color: Int)
    fun setTransferType(type: String)
    fun setDate(date: String)
    fun setNote(note: String)
    fun showMessage(messageRes: Int)
    fun exit()
}

interface Presenter : MvpPresenter<View> {
    fun getRecord()
    fun deleteRecord()
}