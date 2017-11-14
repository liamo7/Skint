package com.loh.skint.ui.category.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.loh.skint.R
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_category_list.*
import javax.inject.Inject

class CategoryListActivity : BaseActivity(), View, CategoryListAdapter.OnCategoryClickListener {

    @Inject lateinit var presenter: Presenter
    @Inject lateinit var listAdapter: CategoryListAdapter

    companion object {
        @JvmStatic val INTENT_REQUEST_CODE = 1001
        @JvmStatic val ARG_SELECTED_CATEGORY = "SELECTED_CATEGORY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
        listAdapter.setListener(this)
        recycler_view.layoutManager = GridLayoutManager(this, 3)
        recycler_view.adapter = listAdapter
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun getLayoutRes(): Int = R.layout.activity_category_list

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun onCategoryClicked(categoryId: Int) {
        presenter.onCategoryClicked(categoryId)
    }

    override fun returnSelectedCategory(categoryId: Int) {
        val result = Intent().apply { putExtra(ARG_SELECTED_CATEGORY, categoryId) }
        setResult(RESULT_OK, result)
        finish()
    }
}