package com.veriff.badooribs.ribs.root.verification.resolvedocument.selectcountry

import android.view.ViewGroup
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.ViewFactory
import com.jakewharton.rxrelay2.PublishRelay
import com.veriff.badooribs.databinding.RibSelectCountryBinding
import com.veriff.badooribs.util.inflater
import io.reactivex.ObservableSource

internal class SelectCountryViewImpl(
    context: ViewFactory.Context,
    private val events: PublishRelay<SelectCountryView.Event> = PublishRelay.create()
) : AndroidRibView(),
    SelectCountryView,
    ObservableSource<SelectCountryView.Event> by events {

    private val binding = RibSelectCountryBinding.inflate(context.inflater())

    override val androidView: ViewGroup
        get() = binding.root

    init {
        binding.estoniaButton.setOnClickListener {
            events.accept(SelectCountryView.Event.CountrySelected("Estonia"))
        }
        binding.ukButton.setOnClickListener {
            events.accept(SelectCountryView.Event.CountrySelected("UK"))
        }
        binding.nigeriaButton.setOnClickListener {
            events.accept(SelectCountryView.Event.CountrySelected("Nigeria"))
        }
    }
}