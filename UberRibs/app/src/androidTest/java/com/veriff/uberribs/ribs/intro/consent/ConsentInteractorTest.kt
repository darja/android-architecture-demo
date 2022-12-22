package com.veriff.uberribs.ribs.intro.consent

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ConsentInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: ConsentInteractor.ConsentPresenter
  @Mock internal lateinit var router: ConsentRouter

  private var interactor: ConsentInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestConsentInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<ConsentInteractor.ConsentPresenter, ConsentRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}