package com.loh.skint.ui.category.list

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.loh.skint.R
import com.loh.skint.domain.model.Category.Companion.getCategories
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.util.inflate
import kotlinx.android.synthetic.main.item_category.view.*
import javax.inject.Inject

@ActivityScoped
class CategoryListAdapter @Inject constructor() : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    interface OnCategoryClickListener {
        fun onCategoryClicked(categoryId: Int)
    }

    private var listener: OnCategoryClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_category))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = getCategories()[position]

        holder.itemView.apply {
            listener?.let { setOnClickListener { listener?.onCategoryClicked(category.id) } }
            item_category_icon.setImageResource(category.iconRes)
            item_category_icon.setColorFilter(ContextCompat.getColor(context, category.colorRes), android.graphics.PorterDuff.Mode.SRC_IN)
            item_category_name.setText(category.nameRes)
        }
    }

    override fun getItemCount(): Int = getCategories().size

    fun setListener(listener: CategoryListAdapter.OnCategoryClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}