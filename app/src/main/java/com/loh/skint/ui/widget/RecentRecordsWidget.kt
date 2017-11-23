package com.loh.skint.ui.widget

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.loh.skint.R
import com.loh.skint.domain.model.Record
import com.loh.skint.util.*


class RecentRecordsWidget @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
        CardView(context, attrs, defStyle) {

    interface OnClickListener {
        fun onMoreActionClicked()
        fun onRecordClicked(record: Record)
    }

    private val listContainer: LinearLayout
    private val emptyMessage: TextView
    private val moreAction: TextView

    private var listener: OnClickListener? = null
    private val ITEM_THRESHOLD = 4

    init {
        inflate(context, R.layout.widget_recent_records, this)
        listContainer = findViewById(R.id.widget_recent_records_list)
        emptyMessage = findViewById(R.id.widget_recent_records_empty)
        moreAction = findViewById(R.id.widget_recent_records_view_more_action)
    }

    fun setClickListener(listener: OnClickListener) {
        this.listener = listener

        // apply listener to 'more' action here as it needs to be available
        // even when no records are set
        moreAction.setOnClickListener { listener.onMoreActionClicked() }
    }

    fun setRecords(records: List<Record>) {
        // clear out records if already there
        listContainer.removeAllViews()

        if (records.isEmpty()) {
            emptyMessage.show()
            listContainer.hide()
            return
        }

        // show list container, hide empty message
        emptyMessage.hide()
        listContainer.show()

        var i = 0
        while ((i < records.size) and (i < ITEM_THRESHOLD)) {
            val view = inflate(R.layout.item_recent_record)
            bindRecord(view, records[i])
            listContainer.addView(view)
            listContainer.addView(inflate(R.layout.divider))
            i++
        }
    }

    private fun bindRecord(view: View, record: Record) {
        val amount = view.findViewById<TextView>(R.id.item_recent_record_amount)
        val categoryIcon = view.findViewById<ImageView>(R.id.item_recent_record_icon)
        val categoryName = view.findViewById<TextView>(R.id.item_recent_record_name)

        view.setOnClickListener { listener?.onRecordClicked(record) }

        amount.text = record.prettyAmount()
        amount.colorize(record.colorizeAmount())
        categoryName.text = context.getString(record.category.nameRes)
        categoryIcon.setImageResource(record.category.iconRes)
        categoryIcon.tint(record.category.colorRes)
    }
}