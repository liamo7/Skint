package com.loh.skint.ui.widget

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.loh.skint.R
import com.loh.skint.domain.model.Record
import com.loh.skint.util.inflate

class RecentRecordsWidget @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
        CardView(context, attrs, defStyle) {

    private val listContainer: LinearLayout
    private val moreAction: TextView

    init {
        inflate(context, R.layout.widget_recent_records, this)
        listContainer = findViewById(R.id.widget_recent_records_list)
        moreAction = findViewById(R.id.widget_recent_records_view_more_action)
    }

    fun setRecords(records: List<Record>?) {
        // clear out records if already there
        listContainer.removeAllViews()

        // check list size
        // if size == 0, show empty message, hide list container, return
        if (records == null || records.isEmpty()) {
            listContainer.visibility = View.GONE
            return
        }

        // show list container, hide empty message
        listContainer.visibility = View.VISIBLE

        // inflate record views
        var i = 0
        while (i < records.size && i < 4) {
            val view = inflate(R.layout.item_recent_record)
            bindRecord(view, records[i])
            listContainer.addView(view)
            listContainer.addView(inflate(R.layout.divider))
            i++
        }
    }

    fun bindRecord(view: View, record: Record) {
        val amount = view.findViewById<TextView>(R.id.item_recent_record_amount)
        val categoryIcon = view.findViewById<ImageView>(R.id.item_recent_record_icon)
        val categoryName = view.findViewById<TextView>(R.id.item_recent_record_name)

        amount.text = "£ ${record.amount.toPlainString()}"
        categoryName.text = context.getString(record.category.nameRes)
        categoryIcon.setImageResource(record.category.iconRes)
        categoryIcon.setColorFilter(ContextCompat.getColor(context, record.category.colorRes))
    }
}