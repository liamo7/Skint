package com.loh.skint.ui.account.overview

import com.loh.skint.domain.usecase.GetOverview
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import com.loh.skint.ui.model.OverviewModel
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class OverviewPresenter @Inject constructor(private val getOverview: GetOverview) : BasePresenter<View>(), Presenter {

    override fun loadAccount() {
        val id = getView().getAccountId()

        if (id == null) {
            getView().handleInvalidAccount()
            return
        }

        getOverview.execute({ onSuccess(it) }, { onError(it) }, id)
    }

    private fun onSuccess(overviewModel: OverviewModel) {
        getView().renderOverviewCollapse(overviewModel.account)
        getView().renderRecentRecords(overviewModel.recentRecords)
    }

    private fun onError(throwable: Throwable) {
        Timber.e(throwable.message)
    }

    override fun cleanUp() {
        getOverview.dispose()
    }
}