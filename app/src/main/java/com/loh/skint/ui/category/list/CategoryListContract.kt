package com.loh.skint.ui.category.list

import com.loh.skint.ui.base.MvpView
import com.loh.skint.ui.base.presenter.MvpPresenter

interface View : MvpView {
    fun returnSelectedCategory(categoryId: Int)
}

interface Presenter : MvpPresenter<View> {
    fun onCategoryClicked(categoryId: Int)
}