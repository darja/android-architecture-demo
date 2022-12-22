package com.veriff.uberribs.domain.repo

import android.util.Log
import com.veriff.uberribs.domain.model.AnalyticsEvent

class AnalyticsRepository() {
    init {
        Log.i("Analytics", "Create repository")
    }
    fun postEvent(event: AnalyticsEvent) {
        Log.i("Analytics", "Post event: ${event.name} [$this]")
    }
}