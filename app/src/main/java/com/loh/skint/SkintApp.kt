package com.loh.skint

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.loh.skint.data.entity.AccountEntity
import com.loh.skint.data.entity.RecordEntity
import com.loh.skint.data.entity.TransferType
import com.loh.skint.domain.model.AVAILABLE_CURRENCIES
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

        deleteDatabase("skint.db")
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }

        val a = AccountEntity().apply {
            uuid = UUID.randomUUID()
            name = "Current Account"
            balance = BigDecimal("300.00")
            currency = AVAILABLE_CURRENCIES[0]
            dateCreated = LocalDate.now().minusDays(10)
            iconResName = "ic_wallet"
        }

        val records = listOf(
                RecordEntity().apply {
                    uuid = UUID.randomUUID()
                    amount = BigDecimal("30.00")
                    transferType = TransferType.INCOME
                    dateOf = LocalDate.now().minusDays(7)
                    account = a
                },
                RecordEntity().apply {
                    uuid = UUID.randomUUID()
                    amount = BigDecimal("60.00")
                    transferType = TransferType.INCOME
                    dateOf = LocalDate.now().minusDays(10)
                    account = a
                },
                RecordEntity().apply {
                    uuid = UUID.randomUUID()
                    amount = BigDecimal("70.00")
                    transferType = TransferType.EXPENSE
                    dateOf = LocalDate.now().minusDays(1)
                    account = a
                },
                RecordEntity().apply {
                    uuid = UUID.randomUUID()
                    amount = BigDecimal("20.00")
                    transferType = TransferType.INCOME
                    dateOf = LocalDate.now().minusDays(5)
                    account = a
                },
                RecordEntity().apply {
                    uuid = UUID.randomUUID()
                    amount = BigDecimal("40.00")
                    transferType = TransferType.EXPENSE
                    dateOf = LocalDate.now().minusDays(3)
                    account = a
                },
                RecordEntity().apply {
                    uuid = UUID.randomUUID()
                    amount = BigDecimal("10.00")
                    transferType = TransferType.INCOME
                    dateOf = LocalDate.now().minusDays(8)
                    account = a
                },
                RecordEntity().apply {
                    uuid = UUID.randomUUID()
                    amount = BigDecimal("60.00")
                    transferType = TransferType.EXPENSE
                    dateOf = LocalDate.now().minusDays(1)
                    account = a
                })
        a.records = records

        repository.add(a).subscribe()
    }
}