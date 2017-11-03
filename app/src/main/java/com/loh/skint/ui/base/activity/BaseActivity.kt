package com.loh.skint.ui.base.activity

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
    }

    abstract fun getLayoutRes(): Int
}