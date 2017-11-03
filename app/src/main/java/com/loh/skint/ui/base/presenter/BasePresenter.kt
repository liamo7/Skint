package com.loh.skint.ui.base.presenter

import com.loh.skint.ui.base.MvpView

abstract class BasePresenter<V : MvpView> : MvpPresenter<V> {

    private var view: V? = null

    override fun attach(view: V) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun isViewAttached(): Boolean = this.view != null

    fun getView(): V = this.view ?: throw ViewNotAttachedException()

    class ViewNotAttachedException : RuntimeException("View not attached to presenter")
}