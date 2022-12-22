package com.veriff.uberribs.ribs.intro.consent

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ConsentRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: ConsentBuilder.Component
  @Mock internal lateinit var interactor: ConsentInteractor
  @Mock internal lateinit var view: ConsentView

  private var router: ConsentRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = ConsentRouter(view, interactor, component)
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

