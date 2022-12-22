package com.veriff.badooribs.ribs.error

import android.view.ViewGroup
import com.badoo.ribs.core.view.AndroidRibView
import com.veriff.badooribs.databinding.RibErrorBinding

internal class ErrorViewImpl(private val binding: RibErrorBinding) :
    AndroidRibView(),
    ErrorView {

    override val androidView: ViewGroup
        get() = binding.root

    override fun setError(title: String, text: String) {
        binding.errorTitle.text = title
        binding.errorText.text = text
    }
}