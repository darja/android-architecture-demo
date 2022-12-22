package com.veriff.uberribs.ribs.finished

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class VerificationFinishedRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: VerificationFinishedBuilder.Component
  @Mock internal lateinit var interactor: VerificationFinishedInteractor
  @Mock internal lateinit var view: VerificationFinishedView

  private var router: VerificationFinishedRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = VerificationFinishedRouter(view, interactor, component)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router!!)
    RouterHelper.detach(router!!)

    throw RuntimeException("Remove this test and add real tests.")
  }

}

