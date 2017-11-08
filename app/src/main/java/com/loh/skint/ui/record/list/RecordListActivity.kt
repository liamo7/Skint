package com.loh.skint.ui.record.list

import com.loh.skint.R
import com.loh.skint.ui.base.activity.BaseAccountDrawerActivity

class RecordListActivity : BaseAccountDrawerActivity() {

    override fun getLayoutRes(): Int = R.layout.activity_record_list

    override fun getMenuItemRes(): Int = R.id.nav_records
}