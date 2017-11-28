package com.loh.skint.ui.record.detail

import com.loh.skint.R
import com.loh.skint.domain.model.Record
import com.loh.skint.domain.usecase.record.DeleteRecord
import com.loh.skint.domain.usecase.record.GetRecord
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import com.loh.skint.util.LONG_DATE_FORMAT
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class RecordDetailPresenter @Inject constructor(private val getRecord: GetRecord,
                                                private val deleteRecord: DeleteRecord)
    : BasePresenter<View>(), Presenter {

    override fun getRecord() {
        getRecord.execute(GetObserver(), GetRecord.Params(getView().getRecordUUID()))
    }

    override fun deleteRecord() {
        deleteRecord.execute(DeleteObserver(), DeleteRecord.Params(getView().getRecordUUID()))
    }

    override fun cleanUp() {
        getRecord.dispose()
        deleteRecord.dispose()
    }

    inner class GetObserver : DisposableSingleObserver<Record>() {
        override fun onSuccess(record: Record) {
            getView().setCategoryIcon(record.category.iconRes)
            getView().setAmount(record.prettyAmount(), record.colorizeAmount())
            getView().setTransferType(record.transferType.name.toLowerCase().capitalize())
            getView().setDate(record.date.format(LONG_DATE_FORMAT))
            getView().setNote(record.note)
        }

        override fun onError(e: Throwable) {
            getView().exit()
            Timber.e(e)
        }
    }

    inner class DeleteObserver : DisposableCompletableObserver() {
        override fun onComplete() {
            getView().showMessage(R.string.record_deleted)
            getView().exit()
        }

        override fun onError(e: Throwable) {
            Timber.e(e)
            getView().showMessage(R.string.oops_error)
        }

    }
}