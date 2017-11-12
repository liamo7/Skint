package com.loh.skint.ui.record.list

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.util.DateRange
import javax.inject.Inject

@ActivityScoped
class RecordListPagerAdapter @Inject constructor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    var dateRange: DateRange = DateRange.DAY
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItem(position: Int): Fragment = RecordListFragment.newInstance(position, dateRange)

    override fun getCount(): Int = dateRange.timespan
}