package com.loh.skint.ui.base.presenter

import com.loh.skint.ui.base.MvpView

interface MvpPresenter<V : MvpView> {

    fun attach(view: V)

    fun detach()

    fun isViewAttached(): Boolean

    fun cleanUp()
}