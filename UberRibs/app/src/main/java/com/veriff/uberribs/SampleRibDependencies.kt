package com.veriff.uberribs

import com.veriff.uberribs.domain.model.Branding
import com.veriff.uberribs.domain.model.FeatureFlags
import com.veriff.uberribs.domain.repo.AnalyticsRepository
import com.veriff.uberribs.ribs.RootBuilder

object SampleRibDependencies: RootBuilder.ParentComponent {
    private val analytics = AnalyticsRepository()
    private val featureFlags = FeatureFlags(coinbase = false)
    private val branding = Branding(
        primaryColorRes = R.color.teal_700,
        secondaryColorRes = R.color.teal_200
    )

    override fun analytics(): AnalyticsRepository {
        return analytics
    }

    override fun featureFlags(): FeatureFlags {
        return featureFlags
    }

    override fun branding(): Branding {
        return branding
    }
}