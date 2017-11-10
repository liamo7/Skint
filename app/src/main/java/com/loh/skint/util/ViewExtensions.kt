package com.loh.skint.util

import android.support.annotation.LayoutRes
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import timber.log.Timber

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ViewPager.scrollLeft() {
    val index = if (currentItem == 0) adapter.count else currentItem--
    Timber.d("Scroll Left: $index")
    setCurrentItem(index, true)
}

fun ViewPager.scrollRight() {
    val index = if (currentItem == adapter.count) currentItem-- else 0
    Timber.d("Scroll Right: $index")
    setCurrentItem(index, true)
}

inline fun View.show() {
    visibility = VISIBLE
}

inline fun View.hide() {
    visibility = VISIBLE
}