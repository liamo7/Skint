package com.loh.skint.domain.usecase

import com.loh.skint.domain.usecase.account.GetAccount
import com.loh.skint.domain.usecase.account.GetRecentRecords
import com.loh.skint.ui.model.OverviewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class GetOverview @Inject constructor(compositeDisposable: CompositeDisposable,
                                      private val getAccount: GetAccount,
                                      private val getRecentRecords: GetRecentRecords)
    : SingleUseCase<OverviewModel, Int>(compositeDisposable) {

    override fun build(params: Int): Single<OverviewModel> {
        val account = getAccount.getInvoke(params)
        val recentRecords = getRecentRecords.getInvoke(params)
        return Single.zip(account, recentRecords, BiFunction { t1, t2 -> OverviewModel(t1, t2) })
    }
}