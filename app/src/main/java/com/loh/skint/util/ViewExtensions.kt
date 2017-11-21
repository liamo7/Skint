package com.loh.skint.util

import android.graphics.PorterDuff
import android.support.annotation.ColorRes
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ViewPager.scrollLeft() {
    if (currentItem > 0) setCurrentItem(currentItem - 1, true)
}

fun ViewPager.scrollRight() {
    adapter?.let { a ->
        if (currentItem < a.count) setCurrentItem(currentItem + 1, true)
    }
}

fun View.show() {
    visibility = VISIBLE
}

fun View.hide() {
    visibility = GONE
}

fun TextView.colorize(@ColorRes colorResId: Int) = setTextColor(ContextCompat.getColor(context, colorResId))

fun ImageView.tint(@ColorRes colorResId: Int) =
        setColorFilter(ContextCompat.getColor(context, colorResId), PorterDuff.Mode.SRC_IN)

fun EditText.disable() {
    keyListener = null
}