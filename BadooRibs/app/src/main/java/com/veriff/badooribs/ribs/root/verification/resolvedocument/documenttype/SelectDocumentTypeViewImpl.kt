package com.veriff.badooribs.ribs.root.verification.resolvedocument.documenttype

import android.view.ViewGroup
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.ViewFactory
import com.jakewharton.rxrelay2.PublishRelay
import com.veriff.badooribs.databinding.RibDocumentTypeBinding
import com.veriff.badooribs.ribs.root.verification.resolvedocument.documenttype.SelectDocumentTypeView.Event
import com.veriff.badooribs.util.inflater
import io.reactivex.ObservableSource

internal class SelectDocumentTypeViewImpl(
    context: ViewFactory.Context,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : AndroidRibView(),
    SelectDocumentTypeView,
    ObservableSource<Event> by events {

    private val binding = RibDocumentTypeBinding.inflate(context.inflater())

    override val androidView: ViewGroup
        get() = binding.root

    init {
        binding.idCardButton.setOnClickListener {
            events.accept(Event.IdCardSelected)
        }
        binding.passportButton.setOnClickListener {
            events.accept(Event.PassportSelected)
        }
        binding.drivingLicenseButton.setOnClickListener {
            events.accept(Event.DrivingLicenseSelected)
        }
        binding.residencePermitButton.setOnClickListener {
            events.accept(Event.ResidencePermitSelected)
        }
    }
}