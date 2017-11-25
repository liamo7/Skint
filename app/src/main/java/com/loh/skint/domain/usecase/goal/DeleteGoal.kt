package com.loh.skint.domain.usecase.goal

import com.loh.skint.domain.repository.GoalRepository
import com.loh.skint.domain.usecase.CompletableUseCase
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class DeleteGoal @Inject constructor(compositeDisposable: CompositeDisposable,
                                     private val repository: GoalRepository)
    : CompletableUseCase<DeleteGoal.Params>(compositeDisposable) {

    override fun build(params: Params): Completable {
        return repository.delete(params.goalUUID)
    }

    data class Params(val goalUUID: UUID)
}