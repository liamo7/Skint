package com.loh.skint.ui.record.list

import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import javax.inject.Inject

@ActivityScoped
class RecordListPresenter @Inject constructor() : BasePresenter<View>(), Presenter {

    override fun cleanUp() {}
}