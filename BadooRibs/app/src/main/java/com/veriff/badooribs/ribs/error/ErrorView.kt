package com.veriff.badooribs.ribs.error

import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory

interface ErrorView : RibView {
    interface Factory : ViewFactory<ErrorView>

    fun setError(title: String, text: String)
}