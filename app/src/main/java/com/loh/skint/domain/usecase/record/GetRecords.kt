package com.loh.skint.domain.usecase.record

import com.loh.skint.domain.mapper.RecordMapper
import com.loh.skint.domain.repository.RecordRepository
import com.loh.skint.domain.usecase.SingleUseCase
import com.loh.skint.ui.model.Record
import com.loh.skint.util.DateRange
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.threeten.bp.LocalDate
import javax.inject.Inject

class GetRecords @Inject constructor(private val repository: RecordRepository, private val mapper: RecordMapper, compositeDisposable: CompositeDisposable) :
        SingleUseCase<List<Record>, GetRecords.Params>(compositeDisposable) {

    override fun build(params: Params): Single<List<Record>> {
        return repository.getRange(params.accountId, params.date, params.dateRange)
                .map { mapper.mapDomainToUi(it) }
    }

    data class Params(val accountId: Int, val dateRange: DateRange, val date: LocalDate)
}