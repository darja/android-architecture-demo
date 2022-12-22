package com.veriff.uberribs.ribs.intro

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class IntroRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: IntroBuilder.Component
  @Mock internal lateinit var interactor: IntroInteractor

  private var router: IntroRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = IntroRouter(interactor, component)
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
