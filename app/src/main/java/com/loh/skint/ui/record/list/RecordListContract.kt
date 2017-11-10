package com.loh.skint.ui.record.list

import com.loh.skint.ui.base.AccountView
import com.loh.skint.ui.base.presenter.MvpPresenter

interface View : AccountView
interface Presenter : MvpPresenter<View>