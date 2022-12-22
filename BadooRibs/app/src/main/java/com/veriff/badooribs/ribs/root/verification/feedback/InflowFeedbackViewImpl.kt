package com.veriff.badooribs.ribs.root.verification.feedback

import android.view.ViewGroup
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.ViewFactory
import com.jakewharton.rxrelay2.PublishRelay
import com.veriff.badooribs.databinding.RibInflowFeedbackBinding
import com.veriff.badooribs.util.inflater
import io.reactivex.ObservableSource

internal class InflowFeedbackViewImpl(
    context: ViewFactory.Context,
    private val events: PublishRelay<InflowFeedbackView.Event> = PublishRelay.create()
) : AndroidRibView(),
    InflowFeedbackView,
    ObservableSource<InflowFeedbackView.Event> by events {

    private val binding = RibInflowFeedbackBinding.inflate(context.inflater())

    override val androidView: ViewGroup
        get() = binding.root

    init {
        binding.continueButton.setOnClickListener {
            events.accept(InflowFeedbackView.Event.ContinueClick)
        }
        binding.retryButton.setOnClickListener {
            events.accept(InflowFeedbackView.Event.RetryClick)
        }
    }
}