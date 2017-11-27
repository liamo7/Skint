package com.loh.skint.ui.base.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.loh.skint.R
import com.loh.skint.util.inflate
import com.loh.skint.util.tint
import kotlinx.android.synthetic.main.item_icon.view.*

class IconAdapter constructor(private val icons: List<Int>,
                              private val callback: (Int) -> Unit = {})
    : RecyclerView.Adapter<IconAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(parent.inflate(R.layout.item_icon))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            setOnClickListener { callback(icons[position]) }
            item_icon.setImageResource(icons[position])
            item_icon.tint(R.color.black)
        }
    }

    override fun getItemCount(): Int = icons.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}