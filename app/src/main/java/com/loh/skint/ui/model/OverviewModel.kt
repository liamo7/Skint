package com.loh.skint.ui.model

import com.loh.skint.domain.model.Account
import com.loh.skint.domain.model.Goal
import com.loh.skint.domain.model.Record

data class OverviewModel(
        val account: Account,
        val recentRecords: List<Record>,
        val upcomingGoals: List<Goal>
)