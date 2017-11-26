package com.loh.skint.domain.usecase.account

import com.loh.skint.domain.usecase.SingleUseCase
import com.loh.skint.domain.usecase.goal.GetUpcomingGoals
import com.loh.skint.domain.usecase.record.GetRecentRecords
import com.loh.skint.ui.model.OverviewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import java.util.*
import javax.inject.Inject

class GetOverview @Inject constructor(compositeDisposable: CompositeDisposable,
                                      private val getAccount: GetAccount,
                                      private val getRecentRecords: GetRecentRecords,
                                      private val getUpcomingGoals: GetUpcomingGoals)
    : SingleUseCase<OverviewModel, UUID>(compositeDisposable) {

    override fun build(params: UUID): Single<OverviewModel> {
        val account = getAccount.getInvoke(params)
        val recentRecords = getRecentRecords.getInvoke(params)
        val upcomingGoals = getUpcomingGoals.getInvoke(GetUpcomingGoals.Params(params))
        return Single.zip(account, recentRecords, upcomingGoals, Function3(::OverviewModel))
    }
}