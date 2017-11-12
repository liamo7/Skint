package com.loh.skint.ui.record.create

import android.os.Bundle
import com.loh.skint.R
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.base.activity.BaseActivity
import com.loh.skint.util.INTENT_ACCOUNT_ID
import com.loh.skint.util.categoryListActivity
import kotlinx.android.synthetic.main.activity_record_create.*

class RecordCreateActivity : BaseActivity(), View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackToolbar(toolbar, R.drawable.ic_arrow_back)
        record_create_icon.setOnClickListener { startActivity(categoryListActivity()) }
    }

    override fun getLayoutRes(): Int = R.layout.activity_record_create

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun getAccountId(): Int? = intent.getIntExtra(INTENT_ACCOUNT_ID, -1)
}