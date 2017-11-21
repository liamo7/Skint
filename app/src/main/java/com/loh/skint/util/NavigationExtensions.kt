package com.loh.skint.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.loh.skint.ui.account.create.AccountCreateActivity
import com.loh.skint.ui.account.icon.AccountIconListActivity
import com.loh.skint.ui.account.list.AccountListActivity
import com.loh.skint.ui.account.overview.OverviewActivity
import com.loh.skint.ui.category.list.CategoryListActivity
import com.loh.skint.ui.goal.create.GoalCreateActivity
import com.loh.skint.ui.goal.detail.GoalDetailActivity
import com.loh.skint.ui.goal.list.GoalListActivity
import com.loh.skint.ui.record.create.RecordCreateActivity
import com.loh.skint.ui.record.list.RecordListActivity
import org.threeten.bp.LocalDate
import java.util.*

const val INTENT_ACCOUNT_ID = "ACCOUNT_ID"
const val INTENT_DATE = "DATE"
const val INTENT_GOAL_UUID = "GOAL_UUID"

fun Context.accountOverview(id: UUID?): Intent {
    return Intent(this, OverviewActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, id)
    }
}

fun Context.recordsList(id: UUID?, date: LocalDate? = null): Intent {
    return Intent(this, RecordListActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, id)
        putExtra(INTENT_DATE, date)
    }
}

fun Context.goalsList(id: UUID?): Intent {
    return Intent(this, GoalListActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, id)
    }
}

fun Context.budgetsList(id: UUID?): Intent {
    return Intent(this, OverviewActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, id)
    }
}

fun Context.chartsList(id: UUID?): Intent {
    return Intent(this, OverviewActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, id)
    }
}

fun Context.reportsList(id: UUID?): Intent {
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

fun Context.recordCreateIntent(accountId: UUID): Intent {
    return Intent(this, RecordCreateActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, accountId)
    }
}

fun Context.categoryListIntent(): Intent {
    return Intent(this, CategoryListActivity::class.java)
}

fun Context.accountIconSelectorIntent(): Intent {
    return Intent(this, AccountIconListActivity::class.java)
}

fun Context.goalCreateIntent(accountUUID: UUID): Intent {
    return Intent(this, GoalCreateActivity::class.java).apply {
        putExtra(INTENT_ACCOUNT_ID, accountUUID)
    }
}

fun Context.goalDetailIntent(uuid: UUID): Intent {
    return Intent(this, GoalDetailActivity::class.java).apply {
        putExtra(INTENT_GOAL_UUID, uuid)
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
//        component?.className?.equals(OverviewActivity::class.java.name)
        return component.className == OverviewActivity::class.java.name
    }

    return false
}