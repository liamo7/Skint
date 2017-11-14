package com.loh.skint.domain.usecase.account

import com.loh.skint.domain.usecase.SingleUseCase
import com.loh.skint.domain.usecase.record.GetRecentRecords
import com.loh.skint.ui.model.OverviewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import java.util.*
import javax.inject.Inject

class GetOverview @Inject constructor(compositeDisposable: CompositeDisposable,
                                      private val getAccount: GetAccount,
                                      private val getRecentRecords: GetRecentRecords)
    : SingleUseCase<OverviewModel, UUID>(compositeDisposable) {

    override fun build(params: UUID): Single<OverviewModel> {
        val account = getAccount.getInvoke(params)
        val recentRecords = getRecentRecords.getInvoke(params)
        return Single.zip(account, recentRecords, BiFunction { t1, t2 -> OverviewModel(t1, t2) })
    }
}