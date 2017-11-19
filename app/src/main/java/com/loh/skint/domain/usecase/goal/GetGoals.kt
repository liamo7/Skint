package com.loh.skint.domain.usecase.goal

import com.loh.skint.domain.model.Goal
import com.loh.skint.domain.repository.GoalRepository
import com.loh.skint.domain.usecase.SingleUseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class GetGoals @Inject constructor(compositeDisposable: CompositeDisposable,
                                   private val repository: GoalRepository) :
        SingleUseCase<MutableList<Goal>, GetGoals.Params>(compositeDisposable) {

    override fun build(params: Params): Single<MutableList<Goal>> {
        return repository.getAll(params.accountUUID)
    }

    data class Params(val accountUUID: UUID)
}