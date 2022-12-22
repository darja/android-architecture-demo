package com.veriff.badooribs.ribs.root.intro.consent

import android.view.ViewGroup
import com.badoo.ribs.core.view.AndroidRibView
import com.jakewharton.rxrelay2.PublishRelay
import com.veriff.badooribs.databinding.RibConsentBinding
import com.veriff.badooribs.ribs.root.intro.consent.ConsentView.Event
import io.reactivex.ObservableSource

internal class ConsentViewImpl(
    private val binding: RibConsentBinding,
    private val events: PublishRelay<Event> = PublishRelay.create()
) :
    AndroidRibView(),
    ConsentView,
    ObservableSource<Event> by events {

    override val androidView: ViewGroup
        get() = binding.root

    init {
        binding.agreeButton.setOnClickListener {
            events.accept(Event.AcceptClicked)
        }
        binding.declineButton.setOnClickListener {
            events.accept(Event.DeclineClicked)
        }
    }
}