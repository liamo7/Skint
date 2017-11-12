package com.loh.skint.ui.category.list

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.loh.skint.R
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_category_list.*

class CategoryListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recycler_view.layoutManager = GridLayoutManager(this, 3)
        recycler_view.adapter = CategoryListAdapter()

    }

    override fun getLayoutRes(): Int = R.layout.activity_category_list

    override fun inject(component: ActivityComponent) = component.inject(this)
}