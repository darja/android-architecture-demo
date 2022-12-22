package com.veriff.uberribs.ribs.finished

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class VerificationFinishedInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: VerificationFinishedInteractor.VerificationFinishedPresenter
  @Mock internal lateinit var router: VerificationFinishedRouter

  private var interactor: VerificationFinishedInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestVerificationFinishedInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<VerificationFinishedInteractor.VerificationFinishedPresenter, VerificationFinishedRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}