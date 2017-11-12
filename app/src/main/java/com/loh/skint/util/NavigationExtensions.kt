package com.loh.skint.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.loh.skint.ui.account.create.AccountCreateActivity
import com.loh.skint.ui.account.list.AccountListActivity
import com.loh.skint.ui.account.overview.OverviewActivity
import com.loh.skint.ui.category.list.CategoryListActivity
import com.loh.skint.ui.record.create.RecordCreateActivity
import com.loh.skint.ui.record.list.RecordListActivity

const val INTENT_ACCOUNT_ID = "ACCOUNT_ID"

fun Context.accountOverview(id: Int?): Intent {
    return Intent(this, OverviewActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, id)
    }
}

fun Context.recordsList(id: Int?): Intent {
    return Intent(this, RecordListActivity::class.java).apply {
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

fun Context.recordCreateIntent(accountId: Int): Intent {
    return Intent(this, RecordCreateActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, accountId)
    }
}

fun Context.categoryListActivity(): Intent {
    return Intent(this, CategoryListActivity::class.java)
}

fun Intent.clearHistory(): Intent {
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    return this
}

fun Intent.bringToFront(): Intent {
    addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
    return this
}

fun Intent.eqClasses(arg1: ComponentName?): Boolean {
    return component?.className.equals(arg1?.className)
}

fun Intent.isOverview(): Boolean {
    if (component.className != null) {
//        component?.className?.equals(OverviewActivity::class.java.name)
        return component.className == OverviewActivity::class.java.name
    }

    return false
}