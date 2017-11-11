package com.loh.skint.ui.record.list

import android.os.Bundle
import android.view.View
import com.loh.skint.R
import com.loh.skint.injection.component.FragmentComponent
import com.loh.skint.ui.base.fragment.BaseFragment
import com.loh.skint.ui.view.ActionListener
import com.loh.skint.ui.view.RecordDatebar
import com.loh.skint.util.DateRange
import com.loh.skint.util.calculateDateFromViewPager
import com.loh.skint.util.scrollLeft
import com.loh.skint.util.scrollRight
import kotlinx.android.synthetic.main.activity_record_list.*
import javax.inject.Inject

class RecordListFragment : BaseFragment(), ActionListener {

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

    @Inject lateinit var presenter: Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val datebar = view.findViewById<RecordDatebar>(R.id.datebar)
        datebar.setActionListener(this)

        val dateRange = arguments.getSerializable(ARG_DATE_RANGE) as DateRange
        val date = calculateDateFromViewPager(arguments.getInt(ARG_POSITION), dateRange)

        datebar.setDate(date, dateRange)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_record_list

    override fun inject(component: FragmentComponent) = component.inject(this)

    override fun onNextActionClicked() {
        if (activity is RecordListActivity) activity.viewpager.scrollRight()
    }

    override fun onPreviousActionClicked() {
        if (activity is RecordListActivity) activity.viewpager.scrollLeft()
    }
}