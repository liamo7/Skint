package com.loh.skint.domain.usecase.account

import com.loh.skint.domain.mapper.RecordMapper
import com.loh.skint.domain.repository.RecordRepository
import com.loh.skint.domain.usecase.SingleUseCase
import com.loh.skint.ui.model.Record
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.threeten.bp.LocalDate
import javax.inject.Inject

class GetRecentRecords @Inject constructor(compositeDisposable: CompositeDisposable,
                                           private val repository: RecordRepository,
                                           private val mapper: RecordMapper)
    : SingleUseCase<List<Record>, Int>(compositeDisposable) {

    override fun build(params: Int): Single<List<Record>> {
        return repository.getRecentRecords(params, LocalDate.now().minusDays(7))
                .map { mapper.mapDomainToUi(it) }
    }

}