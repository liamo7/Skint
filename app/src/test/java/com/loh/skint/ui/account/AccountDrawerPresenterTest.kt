package com.loh.skint.ui.account

import com.loh.skint.domain.model.AVAILABLE_CURRENCIES
import com.loh.skint.domain.usecase.account.GetAccount
import com.loh.skint.ui.model.Account
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableSingleObserver
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.threeten.bp.LocalDate
import org.threeten.bp.Month
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class AccountDrawerPresenterTest {

    @Mock private lateinit var mockView: View
    @Mock private lateinit var mockUsecase: GetAccount

    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<Account>>
    private lateinit var presenter: Presenter

    private var account = Account(UUID.randomUUID(), 0, "Test 1", "30.00", AVAILABLE_CURRENCIES[0], LocalDate.of(2017, Month.JANUARY, 1), -1, null)

    @Before
    fun setUp() {
        captor = argumentCaptor()
        presenter = AccountDrawerPresenter(mockUsecase)
        presenter.attach(mockView)
    }


    @Test
    fun test_nav_drawer_profile_updated() {
        Mockito.`when`(mockView.getAccountId()).thenReturn(0)
        presenter.loadAccount()

        verify(mockView).getAccountId()
        verify(mockUsecase).execute(captor.capture(), eq(0))
        captor.firstValue.onSuccess(account)

        verify(mockView).renderNavHeader(account)
        verifyNoMoreInteractions(mockView)
    }

    @After
    fun tearDown() {
        presenter.cleanUp()
    }
}