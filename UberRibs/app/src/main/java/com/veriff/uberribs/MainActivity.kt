package com.veriff.uberribs

import android.util.Log
import android.view.ViewGroup
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import com.veriff.uberribs.domain.model.Branding
import com.veriff.uberribs.domain.model.FeatureFlags
import com.veriff.uberribs.domain.repo.AnalyticsRepository

import com.veriff.uberribs.ribs.RootBuilder
import com.veriff.uberribs.ribs.RootBuilder.ParentComponent

class MainActivity : RibActivity() {
    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *> {
        val rootBuilder = RootBuilder(SampleRibDependencies)
        return rootBuilder.build(parentViewGroup)
    }
}