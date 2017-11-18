package com.loh.skint

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.loh.skint.domain.repository.AccountRepository
import com.loh.skint.injection.component.AppComponent
import com.loh.skint.injection.component.DaggerAppComponent
import com.loh.skint.injection.module.AppModule
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber
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

//        val a = AccountEntity().apply {
//            uuid = UUID.randomUUID()
//            name = "Current Account"
//            balance = BigDecimal("300.00")
//            currency = AVAILABLE_CURRENCIES[0]
//            dateCreated = LocalDate.now().minusDays(10)
//            iconId = Account.ICONS[0].id
//        }
//
//        val records = mutableListOf(
//                RecordEntity().apply {
//                    uuid = UUID.randomUUID()
//                    amount = BigDecimal("30.00")
//                    transferType = INCOME
//                    dateOf = LocalDate.now().minusDays(7)
//                    category = Category.getCategories()[12]
//                    note = ""
//                    currency = a.currency
//                    account = a
//                    accountUUID = a.uuid
//                },
//                RecordEntity().apply {
//                    uuid = UUID.randomUUID()
//                    amount = BigDecimal("60.00")
//                    transferType = INCOME
//                    dateOf = LocalDate.now().minusDays(10)
//                    category = Category.getCategories()[10]
//                    note = ""
//                    currency = a.currency
//                    account = a
//                    accountUUID = a.uuid
//                },
//                RecordEntity().apply {
//                    uuid = UUID.randomUUID()
//                    amount = BigDecimal("70.00")
//                    transferType = EXPENSE
//                    dateOf = LocalDate.now().minusDays(1)
//                    category = Category.getCategories()[21]
//                    note = ""
//                    currency = a.currency
//                    account = a
//                    accountUUID = a.uuid
//                },
//                RecordEntity().apply {
//                    uuid = UUID.randomUUID()
//                    amount = BigDecimal("20.00")
//                    transferType = INCOME
//                    dateOf = LocalDate.now().minusDays(5)
//                    category = Category.getCategories()[17]
//                    note = ""
//                    currency = a.currency
//                    account = a
//                    accountUUID = a.uuid
//                },
//                RecordEntity().apply {
//                    uuid = UUID.randomUUID()
//                    amount = BigDecimal("40.00")
//                    transferType = EXPENSE
//                    dateOf = LocalDate.now().minusDays(3)
//                    category = Category.getCategories()[9]
//                    note = ""
//                    currency = a.currency
//                    account = a
//                    accountUUID = a.uuid
//                },
//                RecordEntity().apply {
//                    uuid = UUID.randomUUID()
//                    amount = BigDecimal("10.00")
//                    transferType = INCOME
//                    dateOf = LocalDate.now().minusDays(8)
//                    category = Category.getCategories()[14]
//                    note = ""
//                    currency = a.currency
//                    account = a
//                    accountUUID = a.uuid
//                },
//                RecordEntity().apply {
//                    uuid = UUID.randomUUID()
//                    amount = BigDecimal("60.00")
//                    transferType = EXPENSE
//                    dateOf = LocalDate.now().minusDays(1)
//                    category = Category.getCategories()[12]
//                    note = ""
//                    currency = a.currency
//                    account = a
//                    accountUUID = a.uuid
//                })
//
//        a.records.addAll(records)
//
//        Timber.d("Skint: ${a.records.size}")
//
//        repository.add(a).subscribe()
//        val acc = repository.getAll().blockingGet().first()
//        Timber.d("${acc.records.size}")
    }
}