package com.loh.skint.ui.record.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.loh.skint.R
import com.loh.skint.injection.scope.FragmentScoped
import com.loh.skint.ui.model.Record
import com.loh.skint.util.inflate
import kotlinx.android.synthetic.main.item_recent_record.view.*
import javax.inject.Inject

@FragmentScoped
class RecordListAdapter @Inject constructor() : RecyclerView.Adapter<ViewHolder>() {

    var records = listOf<Record>()
        set(value) {
            field = value
            notifyItemRangeChanged(0, field.size)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_recent_record))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = records[position]
        holder.itemView.item_recent_record_amount.text = record.amount.toPlainString()
    }

    override fun getItemCount() = records.size
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)