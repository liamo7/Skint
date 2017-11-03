package com.loh.skint

import com.loh.skint.ui.base.MvpView
import com.loh.skint.ui.base.presenter.BasePresenter
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PresenterTest {

    @Mock
    private lateinit var mockView: MvpView

    private lateinit var presenter: TestPresenter
    @Before
    fun setUp() {
        presenter = TestPresenter()
    }

    @Test
    fun testAttach() {
        attachMockView()

        verifyNoMoreInteractions(mockView)
        assertEquals(mockView, presenter.getView())
    }

    @Test(expected = BasePresenter.ViewNotAttachedException::class)
    fun testDetach() {
        attachMockView()

        // detach the mock view
        detachMockView()

        // to test that the view is null, we try to get the view
        // a detached view should throw an exception
        presenter.getView()
    }

    private fun attachMockView() {
        presenter.attach(mockView)
    }

    private fun detachMockView() {
        presenter.detach()
    }

    class TestPresenter : BasePresenter<MvpView>() {
        override fun cleanUp() {}
    }
}
