package com.loh.skint.ui.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import com.loh.skint.R
import com.loh.skint.util.*
import kotlinx.android.synthetic.main.record_datebar.view.*
import org.threeten.bp.LocalDate

class RecordDatebar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
        ConstraintLayout(context, attrs, defStyle) {

    private lateinit var listener: ActionListener

    init {
        View.inflate(context, R.layout.record_datebar, this)
    }

    fun setActionListener(listener: ActionListener) {
        this.listener = listener
        datebar_next.setOnClickListener({ this.listener.onNextActionClicked() })
        datebar_previous.setOnClickListener({ this.listener.onPreviousActionClicked() })
    }

    fun setDate(date: LocalDate, dateRange: DateRange) {
        datebar_value.text = formatDate(date, dateRange)
    }

    private fun formatDate(date: LocalDate, dateRange: DateRange): String {
        return when (dateRange) {
            is DateRange.DAY -> DAY_FORMAT.format(date)
            is DateRange.WEEK -> WEEK_FORMAT.format(date) + " -\n" + WEEK_FORMAT.format(date.plusDays(6))
            is DateRange.MONTH -> MONTH_FORMAT.format(date)
            is DateRange.YEAR -> YEAR_FORMAT.format(date)
        }
    }
}

interface ActionListener {
    fun onNextActionClicked()
    fun onPreviousActionClicked()
}