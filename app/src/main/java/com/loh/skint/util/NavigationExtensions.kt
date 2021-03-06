package com.loh.skint.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.loh.skint.ui.account.create.AccountCreateActivity
import com.loh.skint.ui.account.list.AccountListActivity
import com.loh.skint.ui.account.overview.OverviewActivity
import com.loh.skint.ui.goal.create.GoalCreateActivity
import com.loh.skint.ui.goal.detail.GoalDetailActivity
import com.loh.skint.ui.goal.list.GoalListActivity
import com.loh.skint.ui.record.create.RecordCreateActivity
import com.loh.skint.ui.record.detail.RecordDetailActivity
import com.loh.skint.ui.record.list.RecordListActivity
import org.threeten.bp.LocalDate
import java.util.*

const val INTENT_ACCOUNT_ID = "ACCOUNT_ID"
const val INTENT_DATE = "DATE"
const val INTENT_GOAL_UUID = "GOAL_UUID"
const val INTENT_RECORD_ID = "RECORD_ID"

fun Context.accountOverview(id: UUID): Intent {
    return Intent(this, OverviewActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, id)
    }
}

fun Context.recordsList(id: UUID, date: LocalDate? = null): Intent {
    return Intent(this, RecordListActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, id)
        putExtra(INTENT_DATE, date)
    }
}

fun Context.goalsList(id: UUID): Intent {
    return Intent(this, GoalListActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, id)
    }
}

fun Context.accountListIntent(): Intent {
    return Intent(this, AccountListActivity::class.java)
}

fun Context.accountCreateIntent(): Intent {
    return Intent(this, AccountCreateActivity::class.java)
}

fun Context.recordCreateIntent(accountId: UUID): Intent {
    return Intent(this, RecordCreateActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, accountId)
    }
}

fun Context.goalCreateIntent(accountUUID: UUID): Intent {
    return Intent(this, GoalCreateActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, accountUUID)
    }
}

fun Context.goalDetailIntent(goalUUID: UUID, accountUUID: UUID): Intent {
    return Intent(this, GoalDetailActivity::class.java).apply {
        putExtra(INTENT_GOAL_UUID, goalUUID)
        putExtra(INTENT_ACCOUNT_ID, accountUUID)
    }
}

fun Context.recordDetailIntent(recordUUID: UUID, accountUUID: UUID): Intent {
    return Intent(this, RecordDetailActivity::class.java).apply {
        putExtra(INTENT_RECORD_ID, recordUUID)
        putExtra(INTENT_ACCOUNT_ID, accountUUID)
    }
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
        return component.className == OverviewActivity::class.java.name
    }

    return false
}