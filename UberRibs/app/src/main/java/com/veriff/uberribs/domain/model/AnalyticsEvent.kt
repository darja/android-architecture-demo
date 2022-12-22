package com.veriff.uberribs.domain.model

sealed class AnalyticsEvent(val name: String) {
    object VerificationStart: AnalyticsEvent("Verification start")
    object ConsentAgreed: AnalyticsEvent("Consent agreed")
    object ConsentDeclined: AnalyticsEvent("Consent declined")
}