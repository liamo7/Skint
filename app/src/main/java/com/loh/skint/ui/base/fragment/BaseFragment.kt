package com.loh.skint.ui.base.fragment

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loh.skint.injection.component.FragmentComponent
import com.loh.skint.injection.module.FragmentModule
import com.loh.skint.ui.base.activity.BaseActivity
import com.loh.skint.util.inflate

abstract class BaseFragment : Fragment() {

    lateinit var fragmentComponent: FragmentComponent

    override fun onAttach(context: Context) {
        if (context is BaseActivity) {
            fragmentComponent = context.activityComponent.plus(FragmentModule(this))
            inject(fragmentComponent)
        }
        super.onAttach(context)
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun inject(component: FragmentComponent)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(getLayoutRes())
    }
}