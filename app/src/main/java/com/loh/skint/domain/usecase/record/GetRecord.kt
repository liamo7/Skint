package com.loh.skint.domain.usecase.record

import com.loh.skint.domain.model.Record
import com.loh.skint.domain.repository.RecordRepository
import com.loh.skint.domain.usecase.SingleUseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class GetRecord @Inject constructor(compositeDisposable: CompositeDisposable,
                                    private val repository: RecordRepository) :
        SingleUseCase<Record, GetRecord.Params>(compositeDisposable) {

    override fun build(params: Params): Single<Record> {
        return repository.get(params.uuid)
    }

    data class Params(val uuid: UUID)
}