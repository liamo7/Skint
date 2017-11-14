package com.loh.skint.ui.record.create

import com.loh.skint.data.entity.TransferType
import com.loh.skint.domain.model.CATEGORIES
import com.loh.skint.domain.model.Record
import com.loh.skint.domain.model.findCategoryById
import com.loh.skint.domain.usecase.record.AddRecord
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import io.reactivex.observers.DisposableCompletableObserver
import org.threeten.bp.LocalDate
import timber.log.Timber
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

@ActivityScoped
class RecordCreatePresenter @Inject constructor(private val addRecordUseCase: AddRecord) : BasePresenter<View>(), Presenter {

    override fun onCategorySelected(id: Int) {
        val selectedCategory = findCategoryById(id)
        getView().setSelectedCategory(selectedCategory)
    }

    override fun onTransferTypeClicked() {
        getView().showTransferTypeSelector()
    }

    override fun onDateClicked() {
        getView().showDateSelector()
    }

    override fun onLocationClicked() {
        getView().showLocationSelector()
    }

    override fun onIconClicked() {
        getView().navigateToCategorySelector()
    }

    override fun onTransferTypeSelected(which: Int) {
        val transferType = if (which == 0) TransferType.INCOME else TransferType.EXPENSE
        getView().setSelectedTransferType(transferType)
    }

    override fun addRecord() {
        val accountId = getView().getAccountUUID() ?: return
        val record = Record(
                UUID.randomUUID(),
                TransferType.INCOME,
                BigDecimal("100.00"),
                LocalDate.now(),
                CATEGORIES[3],
                accountId)

        val params = AddRecord.Params(accountId, record)
        addRecordUseCase.execute(Observer(), params)
    }

    override fun cleanUp() {
        addRecordUseCase.dispose()
    }

    inner class Observer : DisposableCompletableObserver() {
        override fun onComplete() {
            Timber.d("Completed Successfully")
        }

        override fun onError(e: Throwable) {
            Timber.e(e.message)
        }
    }
}