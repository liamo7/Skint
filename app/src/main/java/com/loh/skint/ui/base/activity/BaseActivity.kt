package com.loh.skint.ui.base.activity

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v7.widget.Toolbar
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
    }

    abstract fun getLayoutRes(): Int

    fun setBackToolbar(toolbar: Toolbar, @DrawableRes iconRes: Int) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }
}