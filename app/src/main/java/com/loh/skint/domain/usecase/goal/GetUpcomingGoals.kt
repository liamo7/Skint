package com.loh.skint.domain.usecase.goal

import com.loh.skint.domain.model.Goal
import com.loh.skint.domain.repository.GoalRepository
import com.loh.skint.domain.usecase.SingleUseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.threeten.bp.LocalDate
import java.util.*
import javax.inject.Inject

class GetUpcomingGoals @Inject constructor(compositeDisposable: CompositeDisposable,
                                           private val repository: GoalRepository) :
        SingleUseCase<MutableList<Goal>, GetUpcomingGoals.Params>(compositeDisposable) {

    override fun build(params: Params): Single<MutableList<Goal>> {
        return repository.getUpcoming(params.accountUUID, params.dates, params.threshold)
    }

    data class Params(val accountUUID: UUID,
                      val dates: Pair<LocalDate, LocalDate> = LocalDate.now() to LocalDate.now().plusDays(7),
                      val threshold: Int = 90)
}