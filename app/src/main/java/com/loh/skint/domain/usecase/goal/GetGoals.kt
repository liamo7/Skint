package com.loh.skint.domain.usecase.goal

import com.loh.skint.domain.model.Goal
import com.loh.skint.domain.repository.RecordRepository
import com.loh.skint.domain.usecase.SingleUseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class GetGoals @Inject constructor(compositeDisposable: CompositeDisposable,
                                   private val repository: RecordRepository) :
        SingleUseCase<MutableList<Goal>, GetGoals.Params>(compositeDisposable) {

    override fun build(params: Params): Single<MutableList<Goal>> {
        TODO()
    }

    data class Params(val accountUUID: UUID)
}