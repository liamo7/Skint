package com.loh.skint.ui.widget

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.loh.skint.R
import com.loh.skint.util.hide
import com.loh.skint.util.inflate
import com.loh.skint.util.show

class SkintListWidget @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
        CardView(context, attrs, defStyle) {

    private var ITEM_THRESHOLD = 4
    private val listContainer: LinearLayout
    private val emptyMessage: TextView
    private val moreActionView: TextView
    private val titleView: TextView
    private var itemResId: Int = -1

    var moreAction: () -> Unit = {}
    var itemClickListener: (Any) -> Unit = {}

    init {
        inflate(context, R.layout.skint_widget, this)
        titleView = findViewById(R.id.widget_title)
        listContainer = findViewById(R.id.widget_list)
        emptyMessage = findViewById(R.id.widget_empty)
        moreActionView = findViewById(R.id.widget_more_action)
        moreActionView.setOnClickListener { moreAction() }

        attrs?.let {
            val a = context.obtainStyledAttributes(attrs, R.styleable.SkintListWidget)
            setTitle(a.getString(R.styleable.SkintListWidget_widget_title))
            setEmptyMessage(a.getString(R.styleable.SkintListWidget_widget_empty_message))
            itemResId = a.getResourceId(R.styleable.SkintListWidget_widget_itemRes, -1)
            a.recycle()
        }
    }

    fun setItems(items: List<*>, bindFn: (view: View, item: Any?) -> Unit) {
        listContainer.removeAllViews()

        if (items.isEmpty() || itemResId == -1) {
            emptyMessage.show()
            listContainer.hide()
            return
        }
        // show list container, hide empty message
        emptyMessage.hide()
        listContainer.show()

        var i = 0
        while ((i < items.size) and (i < ITEM_THRESHOLD)) {
            val view = inflate(itemResId)
            bindFn(view, items[i])
            listContainer.addView(view)
            listContainer.addView(inflate(R.layout.divider))
            i++
        }
    }

    private fun setTitle(title: String) {
        titleView.text = title
    }

    private fun setEmptyMessage(message: String) {
        emptyMessage.text = message
    }
}
