package com.loh.skint.ui.base.activity

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.MenuItem
import com.loh.skint.R


abstract class BaseDrawerActivity : BaseActivity() {

    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    private var isRestoredToTop: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupDrawer()
    }

    private fun setupDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)

        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START)
        }

        super.onBackPressed()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    // taken from https://stackoverflow.com/questions/20695522/puzzling-behavior-with-reorder-to-front
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (intent.flags or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT > 0) {
            isRestoredToTop = true
        }
    }

    // taken from https://stackoverflow.com/questions/20695522/puzzling-behavior-with-reorder-to-front
    override fun finish() {
        super.finish()
        if (!isTaskRoot && isRestoredToTop) {
            // reordered activity back press will go to home unexpectedly,
            // Workaround: move reordered activity current task to front when it's finished.
            val tasksManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
            tasksManager?.moveTaskToFront(taskId, ActivityManager.MOVE_TASK_NO_USER_ACTION)
        }
    }
}