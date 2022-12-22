package com.veriff.uberribs.ribs.intro.standby

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class StandbyInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: StandbyInteractor.StandbyPresenter
  @Mock internal lateinit var router: StandbyRouter

  private var interactor: StandbyInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestStandbyInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<StandbyInteractor.StandbyPresenter, StandbyRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}