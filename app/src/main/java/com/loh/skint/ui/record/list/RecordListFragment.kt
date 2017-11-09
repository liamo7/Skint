package com.loh.skint.ui.record.list

import android.os.Bundle
import com.loh.skint.R
import com.loh.skint.injection.component.FragmentComponent
import com.loh.skint.ui.base.fragment.BaseFragment
import timber.log.Timber
import javax.inject.Inject

class RecordListFragment : BaseFragment() {

    @Inject lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("Presenter: " + presenter.toString())
    }

    override fun getLayoutRes(): Int = R.layout.fragment_record_list

    override fun inject(component: FragmentComponent) = component.inject(this)
}