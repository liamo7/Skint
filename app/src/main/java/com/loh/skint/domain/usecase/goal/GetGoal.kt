package com.loh.skint.domain.usecase.goal

import com.loh.skint.domain.model.Goal
import com.loh.skint.domain.repository.GoalRepository
import com.loh.skint.domain.usecase.SingleUseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class GetGoal @Inject constructor(compositeDisposable: CompositeDisposable,
                                  private val repository: GoalRepository) :
        SingleUseCase<Goal, GetGoal.Params>(compositeDisposable) {

    override fun build(params: GetGoal.Params): Single<Goal> {
        return repository.get(params.goalUUID)
    }

    inner class Params(val goalUUID: UUID)
}