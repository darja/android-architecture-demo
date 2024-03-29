package com.veriff.uberribs.ribs.intro.progress

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class FullscreenProgressInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: FullscreenProgressInteractor.FullscreenProgressPresenter
  @Mock internal lateinit var router: FullscreenProgressRouter

  private var interactor: FullscreenProgressInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestFullscreenProgressInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<FullscreenProgressInteractor.FullscreenProgressPresenter, FullscreenProgressRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}