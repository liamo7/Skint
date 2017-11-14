package com.loh.skint.domain.usecase.record

import com.loh.skint.domain.model.Record
import com.loh.skint.domain.repository.RecordRepository
import com.loh.skint.domain.usecase.SingleUseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.threeten.bp.LocalDate
import java.util.*
import javax.inject.Inject

class GetRecentRecords @Inject constructor(compositeDisposable: CompositeDisposable,
                                           private val repository: RecordRepository)
    : SingleUseCase<MutableList<Record>, UUID>(compositeDisposable) {

    override fun build(params: UUID): Single<MutableList<Record>> {
        return repository.getRecentRecords(params, LocalDate.now().minusWeeks(1))
    }
}