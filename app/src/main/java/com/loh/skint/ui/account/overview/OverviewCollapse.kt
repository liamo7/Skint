package com.loh.skint.ui.account.overview

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.TextView
import com.loh.skint.R
import com.loh.skint.ui.model.OverviewModel

class OverviewCollapse @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
        ConstraintLayout(context, attrs, defStyle) {

    private var balanceValue: TextView
    private var availableValue: TextView
    private var lastRecordValue: TextView

    init {
        inflate(context, R.layout.overview_collapse, this)
        balanceValue = findViewById(R.id.overview_balance_value)
        availableValue = findViewById(R.id.overview_available_value)
        lastRecordValue = findViewById(R.id.overview_last_record_value)
    }

    fun setOverview(overviewModel: OverviewModel) {
        val currency = overviewModel.account.currency.symbol

        balanceValue.text = overviewModel.account.prettyBalance()
        availableValue.text = overviewModel.account.prettyBalance()
        lastRecordValue.text = overviewModel.recentRecords.firstOrNull()?.prettyAmount(currency)
                ?: "N/A"
    }
}