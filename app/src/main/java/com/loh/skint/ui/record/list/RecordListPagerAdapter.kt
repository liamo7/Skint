package com.loh.skint.ui.record.list

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.loh.skint.injection.scope.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class RecordListPagerAdapter @Inject constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = RecordListFragment()

    override fun getCount(): Int = 100
}