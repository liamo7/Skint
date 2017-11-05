package com.loh.skint.util

import android.content.Context
import android.content.Intent
import com.loh.skint.ui.account.create.AccountCreateActivity
import com.loh.skint.ui.account.list.AccountListActivity
import com.loh.skint.ui.account.overview.OverviewActivity
import java.util.*

const val INTENT_ACCOUNT_ID = "ACCOUNT_ID"

fun Context.accountOverview(uuid: UUID): Intent {
    return Intent(this, OverviewActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, uuid)
    }
}

fun Context.accountListIntent(): Intent {
    return Intent(this, AccountListActivity::class.java)
}

fun Context.accountCreateIntent(): Intent {
    return Intent(this, AccountCreateActivity::class.java)
}