package com.loh.skint.domain.usecase.record

import com.loh.skint.domain.repository.RecordRepository
import com.loh.skint.domain.usecase.CompletableUseCase
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class DeleteRecord @Inject constructor(compositeDisposable: CompositeDisposable,
                                       private val repository: RecordRepository)
    : CompletableUseCase<DeleteRecord.Params>(compositeDisposable) {

    override fun build(params: Params): Completable {
        return repository.delete(params.recordUUID)
    }

    data class Params(val recordUUID: UUID)
}