package com.loh.skint.ui.record.list

import android.os.Bundle
import android.view.View
import com.loh.skint.R
import com.loh.skint.injection.component.FragmentComponent
import com.loh.skint.ui.base.fragment.BaseFragment
import com.loh.skint.ui.view.ActionListener
import com.loh.skint.ui.view.RecordDatebar
import com.loh.skint.util.scrollLeft
import com.loh.skint.util.scrollRight
import kotlinx.android.synthetic.main.activity_record_list.*
import javax.inject.Inject

class RecordListFragment : BaseFragment(), ActionListener {

    @Inject lateinit var presenter: Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val datebar = view.findViewById<RecordDatebar>(R.id.datebar)
        datebar.setActionListener(this)
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