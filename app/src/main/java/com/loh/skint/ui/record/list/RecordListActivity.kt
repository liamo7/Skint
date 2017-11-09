package com.loh.skint.ui.record.list

import android.os.Bundle
import com.loh.skint.R
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.base.activity.BaseAccountDrawerActivity
import kotlinx.android.synthetic.main.activity_record_list.*
import javax.inject.Inject

class RecordListActivity : BaseAccountDrawerActivity() {

    @Inject lateinit var pagerAdapter: RecordListPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewpager.adapter = pagerAdapter
    }

    override fun getLayoutRes(): Int = R.layout.activity_record_list

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun getMenuItemRes(): Int = R.id.nav_records
}