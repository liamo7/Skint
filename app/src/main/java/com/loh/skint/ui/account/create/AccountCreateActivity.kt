package com.loh.skint.ui.account.create

import android.os.Bundle
import android.view.MenuItem
import com.loh.skint.R
import com.loh.skint.ui.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_account_create.*

class AccountCreateActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_close)
        }
    }

    override fun getLayoutRes(): Int = R.layout.activity_account_create

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}