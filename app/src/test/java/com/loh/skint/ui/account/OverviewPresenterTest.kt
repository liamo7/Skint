package com.loh.skint.ui.account

import com.loh.skint.data.entity.TransferType
import com.loh.skint.domain.model.AVAILABLE_CURRENCIES
import com.loh.skint.domain.usecase.GetOverview
import com.loh.skint.ui.account.overview.OverviewPresenter
import com.loh.skint.ui.account.overview.Presenter
import com.loh.skint.ui.account.overview.View
import com.loh.skint.ui.model.Account
import com.loh.skint.ui.model.OverviewModel
import com.loh.skint.ui.model.Record
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableSingleObserver
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.threeten.bp.LocalDate
import org.threeten.bp.Month
import java.math.BigDecimal
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class OverviewPresenterTest {

    @Mock private lateinit var mockView: View
    @Mock private lateinit var mockUsecase: GetOverview

    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<OverviewModel>>
    private lateinit var presenter: Presenter

    private var records = listOf(Record(UUID.randomUUID(), 0, TransferType.INCOME, BigDecimal("20.00"), LocalDate.of(2017, Month.DECEMBER, 1), UUID.randomUUID()))
    private var account = Account(UUID.randomUUID(), 0, "Test 1", "30.00", AVAILABLE_CURRENCIES[0], LocalDate.of(2017, Month.JANUARY, 1), -1, records)

    private var overviewModel = OverviewModel(account, records)

    @Before
    fun setUp() {
        captor = argumentCaptor()
        presenter = OverviewPresenter(mockUsecase)
        presenter.attach(mockView)
    }

    @Test
    fun test_fab_click_navigates_to_record_creation() {
        presenter.onFabClicked()

        verify(mockView).navigateToRecordCreation()
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun test_invalid_account_id_kicks_user_back_to_account_list() {
        `when`(mockView.getAccountId()).thenReturn(null)
        presenter.loadAccount()

        verify(mockView).getAccountId()
        verify(mockView).handleInvalidAccount()
        verifyZeroInteractions(mockUsecase)
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun test_account_retrieval_error_kicks_user_back_to_account_list() {
        `when`(mockView.getAccountId()).thenReturn(0)
        presenter.loadAccount()

        verify(mockView).getAccountId()
        verify(mockUsecase).execute(captor.capture(), eq(0))
        captor.firstValue.onError(Throwable())

        verify(mockView).handleInvalidAccount()
        verifyNoMoreInteractions(mockView)
        verifyNoMoreInteractions(mockUsecase)

    }

    @Test
    fun test_overview_collapse_rendered() {
        `when`(mockView.getAccountId()).thenReturn(0)
        presenter.loadAccount()

        verify(mockView).getAccountId()
        verify(mockUsecase).execute(captor.capture(), eq(0))
        captor.firstValue.onSuccess(overviewModel)

        verify(mockView).renderOverviewCollapse(overviewModel.account)
    }

    @Test
    fun test_recent_records_widget_rendered() {
        `when`(mockView.getAccountId()).thenReturn(0)
        presenter.loadAccount()

        verify(mockView).getAccountId()
        verify(mockUsecase).execute(captor.capture(), eq(0))
        captor.firstValue.onSuccess(overviewModel)

        verify(mockView).renderRecentRecords(overviewModel.recentRecords)
    }

    @Test
    fun test_nav_drawer_profile_updated() {
        `when`(mockView.getAccountId()).thenReturn(0)
        presenter.loadAccount()

        verify(mockView).getAccountId()
        verify(mockUsecase).execute(captor.capture(), eq(0))
        captor.firstValue.onSuccess(overviewModel)

        verify(mockView).renderNavHeader(overviewModel.account)
    }

    @After
    fun tearDown() {
        presenter.detach()
    }
}