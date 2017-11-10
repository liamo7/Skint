package com.loh.skint.ui.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import com.loh.skint.R
import kotlinx.android.synthetic.main.record_datebar.view.*

class RecordDatebar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
        ConstraintLayout(context, attrs, defStyle) {

    private lateinit var listener: ActionListener

    init {
        View.inflate(context, R.layout.record_datebar, this)
    }

    fun setActionListener(listener: ActionListener) {
        this.listener = listener
        datebar_next.setOnClickListener(OnClickListener { this.listener.onNextActionClicked() })
        datebar_previous.setOnClickListener(OnClickListener { this.listener.onPreviousActionClicked() })
    }
}

interface ActionListener {
    fun onNextActionClicked()
    fun onPreviousActionClicked()
}