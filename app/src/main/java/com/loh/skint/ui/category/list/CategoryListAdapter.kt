package com.loh.skint.ui.category.list

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.loh.skint.R
import com.loh.skint.domain.model.CATEGORIES
import com.loh.skint.util.inflate
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryListAdapter : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_category))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = CATEGORIES[position]

        holder.itemView.apply {
            item_category_icon.setImageResource(category.iconRes)
            item_category_icon.setColorFilter(ContextCompat.getColor(context, category.colorRes), android.graphics.PorterDuff.Mode.SRC_IN)
            item_category_name.setText(category.nameRes)
        }
    }

    override fun getItemCount(): Int = CATEGORIES.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}