package com.loh.skint.ui.base.view

import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import com.loh.skint.R
import kotlinx.android.synthetic.main.toolbar_skint.view.*

class SkintToolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
        Toolbar(context, attrs, defStyle) {

    init {
        View.inflate(getContext(), R.layout.toolbar_skint, this)
    }

    override fun setTitle(title: CharSequence?) {
        toolbar_title.text = title
    }
}