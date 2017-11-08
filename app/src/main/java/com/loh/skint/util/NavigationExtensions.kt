package com.loh.skint.util

import android.content.Context
import android.content.Intent
import com.loh.skint.ui.account.create.AccountCreateActivity
import com.loh.skint.ui.account.list.AccountListActivity
import com.loh.skint.ui.account.overview.OverviewActivity

const val INTENT_ACCOUNT_ID = "ACCOUNT_ID"

fun Context.accountOverview(id: Int?): Intent {
    return Intent(this, OverviewActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, id)
    }
}

fun Context.recordsList(id: Int?): Intent {
    return Intent(this, OverviewActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, id)
    }
}

fun Context.goalsList(id: Int?): Intent {
    return Intent(this, OverviewActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, id)
    }
}

fun Context.budgetsList(id: Int?): Intent {
    return Intent(this, OverviewActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, id)
    }
}

fun Context.chartsList(id: Int?): Intent {
    return Intent(this, OverviewActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, id)
    }
}

fun Context.reportsList(id: Int?): Intent {
    return Intent(this, OverviewActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, id)
    }
}

fun Context.settings(): Intent {
    return Intent(this, OverviewActivity::class.java)
}

fun Context.accountListIntent(): Intent {
    return Intent(this, AccountListActivity::class.java)
}

fun Context.accountCreateIntent(): Intent {
    return Intent(this, AccountCreateActivity::class.java)
}

fun Intent.clearHistory(): Intent {
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    return this
}

fun Intent.bringToFront(): Intent {
    addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
    return this
}