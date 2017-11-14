package com.loh.skint.ui.account

import com.loh.skint.domain.model.AVAILABLE_CURRENCIES
import com.loh.skint.domain.usecase.account.GetAccounts
import com.loh.skint.ui.account.list.AccountListPresenter
import com.loh.skint.ui.account.list.Presenter
import com.loh.skint.ui.account.list.View
import com.nhaarman.mockito_kotlin.KArgumentCaptor
import com.nhaarman.mockito_kotlin.argumentCaptor
import io.reactivex.observers.DisposableSingleObserver
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.threeten.bp.LocalDate
import org.threeten.bp.Month
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class AccountListPresenterTest {

    @Mock private lateinit var mockView: View
    @Mock private lateinit var mockUsecase: GetAccounts

    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<List<Account>>>
    private lateinit var presenter: Presenter

    @Before
    fun setUp() {
        captor = argumentCaptor()
        presenter = AccountListPresenter(mockUsecase)
        presenter.attach(mockView)
    }

    @Test
    fun test_empty_state_shown_when_no_accounts_available() {
        presenter.retrieveAccounts()

        // return an empty list
        verify(mockUsecase).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(ArrayList())

        /*
         * What should happen:
         *
         * 1) Empty container is shown
         * 2) Account list is hidden
         */
        verify(mockView).showEmptyState()
        verify(mockView).hideAccountsList()
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun test_empty_state_shown_on_error() {
        presenter.retrieveAccounts()

        // return an empty list
        verify(mockUsecase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(Throwable())

        /*
         * What should happen:
         *
         * 1) Empty container is shown
         * 2) Account list is hidden
         */
        verify(mockView).showEmptyState()
        verify(mockView).hideAccountsList()
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun test_accounts_list_shown_when_available() {
        val accounts = listOf(
                Account(UUID.randomUUID(), 0, "Test 1", "30.00", AVAILABLE_CURRENCIES[0], LocalDate.of(2017, Month.JANUARY, 1), -1, null),
                Account(UUID.randomUUID(), 1, "Test 2", "50.00", AVAILABLE_CURRENCIES[1], LocalDate.of(2017, Month.JUNE, 1), -1, null)
        )

        presenter.retrieveAccounts()

        // return an empty list
        verify(mockUsecase).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(accounts)

        /*
         * What should happen:
         *
         * 1) Empty container is hidden
         * 2) Account list is shown
         */
        verify(mockView).hideEmptyState()
        verify(mockView).showAccounts(accounts)
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun test_fab_click_navigates_to_account_creation() {
        presenter.onFabClicked()

        verify(mockView).navigateToAccountCreation()
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun test_account_list_item_clicked_navigates_to_details() {
        presenter.onAccountItemClicked(Account(UUID.randomUUID(), 0, "", "", AVAILABLE_CURRENCIES[0], LocalDate.of(2017, Month.JANUARY, 1), -1, null))

        verify(mockView).navigateToAccount(ArgumentMatchers.anyInt())
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun test_usecase_is_disposed_on_detach() {
        presenter.detach()
        verify(mockUsecase).dispose()
        verifyNoMoreInteractions(mockUsecase)
    }

    @After
    fun tearDown() {
        presenter.detach()
    }
}