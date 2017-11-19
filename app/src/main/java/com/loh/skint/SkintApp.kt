package com.loh.skint

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.loh.skint.data.entity.AccountEntity
import com.loh.skint.data.entity.GoalEntity
import com.loh.skint.domain.model.AVAILABLE_CURRENCIES
import com.loh.skint.domain.model.Account
import com.loh.skint.domain.repository.AccountRepository
import com.loh.skint.injection.component.AppComponent
import com.loh.skint.injection.component.DaggerAppComponent
import com.loh.skint.injection.module.AppModule
import com.squareup.leakcanary.LeakCanary
import org.threeten.bp.LocalDate
import timber.log.Timber
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

class SkintApp : Application() {

    @Inject lateinit var repository: AccountRepository

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)
        AndroidThreeTen.init(this)
        appComponent.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }

        deleteDatabase("skint.db")
        buildTestData()
    }

    fun buildTestData() {
        val a = AccountEntity().apply {
            uuid = UUID.randomUUID()
            name = "Current Account"
            balance = BigDecimal("300.00")
            currency = AVAILABLE_CURRENCIES[0]
            dateCreated = LocalDate.now().minusDays(10)
            iconId = Account.ICONS[0].id
        }

        val goal = GoalEntity().apply {
            uuid = UUID.randomUUID()
            name = "New Car"
            note = "A brand new car"
            iconResId = R.drawable.ic_car
            startDate = LocalDate.now().minusDays(5)
            targetDate = LocalDate.now().plusDays(30)
            savedAmount = BigDecimal("210.00")
            targetAmount = BigDecimal("6230.00")
            currency = a.currency
            accountUUID = a.uuid
            account = a
        }

        a.goals.add(goal)
        repository.add(a).subscribe()
    }
}