package com.loh.skint.ui.record.list

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.util.DateRange
import javax.inject.Inject

@ActivityScoped
class RecordListPagerAdapter @Inject constructor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var dateRange: DateRange = DateRange.DAY

    fun setDateRange(dateRange: DateRange) {
        this.dateRange = dateRange
        notifyDataSetChanged()
    }

    fun getDateRange() = this.dateRange

    override fun getItem(position: Int): Fragment = RecordListFragment.newInstance(position, dateRange)

    override fun getCount(): Int = dateRange.timespan
}