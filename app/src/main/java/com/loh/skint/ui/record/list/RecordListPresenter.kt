package com.loh.skint.ui.record.list

import com.loh.skint.domain.usecase.record.GetRecords
import com.loh.skint.ui.base.presenter.BasePresenter
import com.loh.skint.ui.model.Record
import com.loh.skint.util.DateRange
import io.reactivex.observers.DisposableSingleObserver
import org.threeten.bp.LocalDate
import timber.log.Timber
import javax.inject.Inject

class RecordListPresenter @Inject constructor(val getRecords: GetRecords) : BasePresenter<View>(), Presenter {

    override fun retrieveRecords() {
        val accountId = getView().getAccountId()
        if (accountId == null) {
            handleEmptyState()
            return
        }

        val params = GetRecords.Params(accountId, DateRange.DAY, LocalDate.now())
        getRecords.execute(Observer(), params)
    }

    override fun onRecordClicked() {
        TODO("not implemented")
    }

    override fun onAddRecord() {
        TODO("not implemented")
    }

    private fun handleEmptyState() {
        getView().displayEmptyState()
        getView().hideRecords()

    }

    override fun cleanUp() = getRecords.dispose()

    inner class Observer : DisposableSingleObserver<List<Record>>() {
        override fun onSuccess(records: List<Record>) {
            Timber.d("Records: ${records.size}")
            if (records.isEmpty()) {
                handleEmptyState()
            } else {
                getView().displayRecords(records)
                getView().hideEmptyState()
            }
        }

        override fun onError(e: Throwable) {
            Timber.e(e.message)
            handleEmptyState()
        }
    }
}