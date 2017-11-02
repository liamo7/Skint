package com.loh.skint.ui.account.list

import android.os.Bundle
import com.loh.skint.R
import com.loh.skint.ui.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_account_list.*

class AccountListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.title_account_list)
    }

    override fun getLayoutRes(): Int = R.layout.activity_account_list
}