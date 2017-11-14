package com.loh.skint.ui.category.list

import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.base.presenter.BasePresenter
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class CategoryListPresenter @Inject constructor() : BasePresenter<View>(), Presenter {

    override fun onCategoryClicked(categoryId: Int) {
        Timber.d("Category ID: $categoryId")
        getView().returnSelectedCategory(categoryId)
    }

    override fun cleanUp() {}
}