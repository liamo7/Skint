package com.loh.skint.ui.onboard

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.loh.skint.R
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.base.activity.BaseActivity
import com.ramotion.paperonboarding.PaperOnboardingEngine
import com.ramotion.paperonboarding.PaperOnboardingPage

class OnboardActivity : BaseActivity() {

    private val pages: ArrayList<PaperOnboardingPage> by lazy {
        arrayListOf(
                PaperOnboardingPage("Accounts",
                        "Be in control of your finances in all of your accounts.",
                        Color.parseColor("#678FB4"), R.drawable.ic_onboard_account, R.drawable.ic_safebox),
                PaperOnboardingPage("Records",
                        "Keep a careful check on what and how you are spending your money.",
                        Color.parseColor("#65B0B4"), R.drawable.ic_onboard_record, R.drawable.ic_coin_hand),
                PaperOnboardingPage("Goals",
                        "Setting goals gives you long-term vision and short-term motivation to get what you most desire. ",
                        Color.parseColor("#9B90BC"), R.drawable.ic_onboard_goal, R.drawable.ic_bullseye)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = ContextCompat.getColor(this@OnboardActivity, R.color.black_trans80)
        }
        PaperOnboardingEngine(findViewById(R.id.onboardingRootView), pages, applicationContext)
                .setOnRightOutListener { finish() }
    }

    override fun getLayoutRes(): Int = R.layout.onboarding_main_layout

    override fun inject(component: ActivityComponent) {}
}