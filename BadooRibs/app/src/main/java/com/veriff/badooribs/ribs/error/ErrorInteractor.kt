package com.veriff.badooribs.ribs.error

import androidx.lifecycle.Lifecycle
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams

internal class ErrorInteractor(
    private val buildParams: BuildParams<ErrorPayload>
): Interactor<ErrorRib, ErrorView>(buildParams) {

    override fun onViewCreated(view: ErrorView, viewLifecycle: Lifecycle) {
        val error = buildParams.payload
        view.setError(error.title, error.text)
    }
}