package com.loh.skint.ui.record

import com.loh.skint.domain.usecase.record.GetRecords
import com.loh.skint.testUtil.ModelProvider.Companion.randomRecord
import com.loh.skint.ui.record.list.Presenter
import com.loh.skint.ui.record.list.RecordListPresenter
import com.loh.skint.ui.record.list.View
import com.nhaarman.mockito_kotlin.KArgumentCaptor
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.reactivex.observers.DisposableSingleObserver
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class RecordListPresenterTest {

    @Mock private lateinit var mockView: View
    @Mock private lateinit var mockUsecase: GetRecords

    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<List<Record>>>
    private lateinit var presenter: Presenter

    @Before
    fun setUp() {
        captor = argumentCaptor()
        presenter = RecordListPresenter(mockUsecase)
        presenter.attach(mockView)
    }

    @Test
    fun test_empty_state_shown_when_no_records_available() {
        presenter.retrieveRecords()

        verify(mockView).getAccountUUID()

        // return an empty list
        Mockito.verify(mockUsecase).execute(captor.capture(), Mockito.eq(0))
        captor.firstValue.onSuccess(ArrayList())

        Mockito.verify(mockView).displayEmptyState()
        Mockito.verify(mockView).hideRecords()
        Mockito.verifyNoMoreInteractions(mockView)
    }

    @Test
    fun test_empty_state_shown_on_account_id_error() {
        Mockito.`when`(mockView.getAccountUUID()).thenReturn(null)
        presenter.retrieveRecords()

        verify(mockView).getAccountUUID()

        verify(mockView).displayEmptyState()
        verify(mockView).hideRecords()
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun test_empty_state_shown_on_error() {
        presenter.retrieveRecords()

        verify(mockView).getAccountUUID()

        // return an empty list
        Mockito.verify(mockUsecase).execute(captor.capture(), Mockito.eq(0))
        captor.firstValue.onError(Throwable())


        Mockito.verify(mockView).displayEmptyState()
        Mockito.verify(mockView).hideRecords()
        Mockito.verifyNoMoreInteractions(mockView)
    }

    @Test
    fun test_records_shown_when_available() {
        val records = listOf(randomRecord(), randomRecord(), randomRecord())

        presenter.retrieveRecords()

        verify(mockView).getAccountUUID()

        // return an empty list
        Mockito.verify(mockUsecase).execute(captor.capture(), Mockito.eq(0))
        captor.firstValue.onSuccess(records)

        verify(mockView).hideEmptyState()
        verify(mockView).displayRecords(records)
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun test_fab_click_navigates_to_record_creation() {
    }

    @Test
    fun test_record_clicked_navigates_to_details() {
    }

    @Test
    fun test_usecase_is_disposed_on_detach() {
        presenter.detach()
        Mockito.verify(mockUsecase).dispose()
        Mockito.verifyNoMoreInteractions(mockUsecase)
    }

    @After
    fun tearDown() {
        presenter.detach()
    }
}