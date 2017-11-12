package com.loh.skint.ui.record.list

import com.loh.skint.domain.usecase.record.GetRecords
import com.loh.skint.injection.scope.FragmentScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import com.loh.skint.ui.model.Record
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject

@FragmentScoped
class RecordListPresenter @Inject constructor(private val getRecords: GetRecords) : BasePresenter<View>(), Presenter {

    override fun retrieveRecords() {
        val accountId = getView().getAccountId()

        val date = getView().getDate()
        val range = getView().getDateRange()

        getView().renderDatebar(date, range)

        if (accountId == null) {
            handleEmptyState()
            return
        }

        val params = GetRecords.Params(accountId, range, date)
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