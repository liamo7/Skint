package com.loh.skint.ui.base.activity

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.loh.skint.SkintApp
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.injection.module.ActivityModule

abstract class BaseActivity : AppCompatActivity() {

    val activityComponent: ActivityComponent by lazy {
        (application as SkintApp).appComponent.plus(ActivityModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        inject(activityComponent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    abstract fun getLayoutRes(): Int

    abstract fun inject(component: ActivityComponent)

    fun setBackToolbar(toolbar: Toolbar, @DrawableRes iconRes: Int) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }
}