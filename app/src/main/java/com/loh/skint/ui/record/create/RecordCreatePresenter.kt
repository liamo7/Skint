package com.loh.skint.ui.record.create

import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import javax.inject.Inject

@ActivityScoped
class RecordCreatePresenter @Inject constructor() : BasePresenter<View>(), Presenter {

    override fun cleanUp() {}
}