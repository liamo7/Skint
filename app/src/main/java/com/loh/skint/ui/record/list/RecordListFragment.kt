package com.loh.skint.ui.record.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.loh.skint.R
import com.loh.skint.injection.component.FragmentComponent
import com.loh.skint.ui.account.BaseAccountDrawerActivity
import com.loh.skint.ui.base.fragment.BaseFragment
import com.loh.skint.ui.model.Record
import com.loh.skint.ui.view.ActionListener
import com.loh.skint.util.*
import kotlinx.android.synthetic.main.activity_record_list.*
import kotlinx.android.synthetic.main.fragment_record_list.*
import org.threeten.bp.LocalDate
import javax.inject.Inject

class RecordListFragment : BaseFragment(), ActionListener, com.loh.skint.ui.record.list.View {

    @Inject lateinit var presenter: Presenter
    @Inject lateinit var listAdapter: RecordListAdapter

    companion object {
        private val ARG_POSITION = "ARG_POSITION"
        private val ARG_DATE_RANGE = "ARG_DATE_RANGE"

        fun newInstance(position: Int, dateRange: DateRange): RecordListFragment {
            val fragment = RecordListFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            bundle.putSerializable(ARG_DATE_RANGE, dateRange)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attach(this)
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.adapter = listAdapter
        datebar.setActionListener(this)
        presenter.retrieveRecords()
    }

    override fun onDestroyView() {
        presenter.detach()
        super.onDestroyView()
    }

    override fun getLayoutRes(): Int = R.layout.fragment_record_list

    override fun inject(component: FragmentComponent) = component.inject(this)

    override fun onNextActionClicked() {
        if (activity is RecordListActivity) activity.viewpager.scrollRight()
    }

    override fun onPreviousActionClicked() {
        if (activity is RecordListActivity) activity.viewpager.scrollLeft()
    }

    override fun getAccountId(): Int? {
        if (activity is BaseAccountDrawerActivity) {
            return (activity as BaseAccountDrawerActivity).getAccountId()
        }
        return null
    }

    override fun displayEmptyState() = empty_container.show()

    override fun hideEmptyState() = empty_container.hide()

    override fun displayRecords(records: List<Record>) {
        recycler_view.show()
        listAdapter.records = records
    }

    override fun hideRecords() = recycler_view.hide()

    override fun renderDatebar(date: LocalDate, dateRange: DateRange) {
        datebar.setDate(date, dateRange)
    }

    override fun getDateRange(): DateRange {
        return arguments.getSerializable(ARG_DATE_RANGE) as DateRange
    }

    override fun getDate(): LocalDate {
        return calculateDateFromViewPager(arguments.getInt(ARG_POSITION), getDateRange())
    }
}
