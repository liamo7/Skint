package com.loh.skint.ui.record.create

import com.loh.skint.data.entity.TransferType
import com.loh.skint.domain.model.Category
import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.base.presenter.MvpPresenter

interface View : AccountView {
    fun setSelectedCategory(selectedCategory: Category)
    fun navigateToCategorySelector()
    fun showTransferTypeSelector()
    fun showDateSelector()
    fun showLocationSelector()
    fun setSelectedTransferType(transferType: TransferType)
    fun retrieveInput()
}

interface Presenter : MvpPresenter<View> {
    fun onCategorySelected(id: Int)
    fun onTransferTypeClicked()
    fun onDateClicked()
    fun onLocationClicked()
    fun onIconClicked()
    fun onTransferTypeSelected(which: Int)
    fun addRecord()
}