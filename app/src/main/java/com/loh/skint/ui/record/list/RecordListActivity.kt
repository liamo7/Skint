package com.loh.skint.ui.record.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.afollestad.materialdialogs.MaterialDialog
import com.loh.skint.R
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.account.BaseAccountDrawerActivity
import com.loh.skint.util.DateRange
import com.loh.skint.util.INTENT_DATE
import com.loh.skint.util.calculateViewpagerPositionFromDateRange
import kotlinx.android.synthetic.main.activity_record_list.*
import org.threeten.bp.LocalDate
import javax.inject.Inject

class RecordListActivity : BaseAccountDrawerActivity() {

    @Inject lateinit var pagerAdapter: RecordListPagerAdapter

    private lateinit var date: LocalDate
    private val dateRangeDialog: MaterialDialog by lazy {
        MaterialDialog.Builder(this)
                .title(R.string.title_date_range_select)
                .items(R.array.date_ranges)
                .itemsCallbackSingleChoice(pagerAdapter.getDateRange().id, { _, _, which, _ ->
                    // update our newly selected date range
                    pagerAdapter.setDateRange(DateRange.values()[which])
                    // update the pager position to new position of date based on new date range
                    viewpager.currentItem = date.calculateViewpagerPositionFromDateRange(pagerAdapter.getDateRange())
                    true
                })
                .positiveText(R.string.choose)
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null && savedInstanceState[ARG_STATE] != null) {
            pagerAdapter.setDateRange(savedInstanceState.getSerializable(ARG_STATE) as DateRange)
        }

        // determines whether we should navigate to a specific date in time for list
        date = intent.getSerializableExtra(INTENT_DATE) as LocalDate? ?: LocalDate.now()
        viewpager.adapter = pagerAdapter
        viewpager.currentItem = date.calculateViewpagerPositionFromDateRange(pagerAdapter.getDateRange())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(ARG_STATE, pagerAdapter.getDateRange())
    }

    override fun getLayoutRes(): Int = R.layout.activity_record_list

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun getMenuItemRes(): Int = R.id.nav_records

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_record_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_date_range) openDateRangeDialog()
        return super.onOptionsItemSelected(item)
    }

    private fun openDateRangeDialog() {
        dateRangeDialog.show()
    }
}
