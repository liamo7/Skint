package com.loh.skint.util

import android.support.annotation.LayoutRes
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup

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