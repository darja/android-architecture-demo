package com.veriff.uberribs.domain.usecase

import com.veriff.uberribs.domain.model.AnalyticsEvent
import com.veriff.uberribs.domain.repo.AnalyticsRepository
import javax.inject.Inject

class PostAnalyticsEventUseCase @Inject constructor(private val repository: AnalyticsRepository) {
    fun execute(event: AnalyticsEvent) {
        repository.postEvent(event)
    }
}