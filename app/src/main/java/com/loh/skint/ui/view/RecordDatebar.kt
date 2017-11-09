package com.loh.skint.ui.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import com.loh.skint.R
import kotlinx.android.synthetic.main.record_datebar.view.*

class RecordDatebar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
        ConstraintLayout(context, attrs, defStyle) {

    interface ActionListener {
        fun onNextActionClicked()
        fun onPreviousActionClicked()
    }

    private var listener: ActionListener? = null

    init {
        View.inflate(context, R.layout.record_datebar, this)
    }

    fun setActionListener(listener: ActionListener) {
        this.listener = listener
        bindActions()
    }

    private fun bindActions() {
        // nested 'it' in lambda of click listeners require named 'outer' lambda parameter
        listener?.let { l ->
            {
                datebar_previous.setOnClickListener { l.onPreviousActionClicked() }
                datebar_next.setOnClickListener { l.onNextActionClicked() }
            }
        }
    }
}