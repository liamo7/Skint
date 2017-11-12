package com.loh.skint.ui.record.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.loh.skint.R
import com.loh.skint.injection.scope.FragmentScoped
import com.loh.skint.ui.model.Record
import com.loh.skint.util.inflate
import kotlinx.android.synthetic.main.item_record.view.*
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

@FragmentScoped
class RecordListAdapter @Inject constructor() : RecyclerView.Adapter<ViewHolder>() {

    var records = listOf<Record>()
        set(value) {
            field = value
            notifyItemRangeChanged(0, field.size)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_record))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = records[position]
        holder.itemView.item_record_name.text = record.amount.toPlainString()
        holder.itemView.item_record_date.text = record.date.format(DateTimeFormatter.ofPattern("E dd MMM YYYY"))
//        holder.itemView.item_record_icon = record.amount.toPlainString()
//        holder.itemView.item_record_balance.text = record.amount.toPlainString()

    }

    override fun getItemCount() = records.size
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)