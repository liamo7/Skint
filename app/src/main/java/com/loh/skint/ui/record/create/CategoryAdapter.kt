package com.loh.skint.ui.record.create

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.loh.skint.R
import com.loh.skint.domain.model.Category
import com.loh.skint.domain.model.Category.Companion.getCategories
import com.loh.skint.util.inflate
import com.loh.skint.util.tint
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter constructor(private val callback: (Category) -> Unit = {})
    : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(parent.inflate(R.layout.item_category))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = getCategories()[position]

        holder.itemView.apply {
            setOnClickListener { callback(category) }
            item_icon.setImageResource(category.iconRes)
            item_icon.tint(R.color.black)
            item_name.setText(category.nameRes)
        }
    }

    override fun getItemCount(): Int = getCategories().size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}