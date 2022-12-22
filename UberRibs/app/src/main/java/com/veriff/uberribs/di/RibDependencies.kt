package com.veriff.uberribs.di

import com.veriff.uberribs.domain.model.Branding
import com.veriff.uberribs.domain.model.FeatureFlags
import com.veriff.uberribs.domain.repo.AnalyticsRepository

interface RibDependencies {
    fun analytics(): AnalyticsRepository
    fun featureFlags(): FeatureFlags
    fun branding(): Branding
}