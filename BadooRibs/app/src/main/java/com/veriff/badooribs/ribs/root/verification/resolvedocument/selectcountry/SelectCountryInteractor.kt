package com.veriff.badooribs.ribs.root.verification.resolvedocument.selectcountry

import androidx.lifecycle.Lifecycle
import com.badoo.binder.using
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams

internal class SelectCountryInteractor(
    private val buildParams: BuildParams<*>,
) : Interactor<SelectCountryRib, SelectCountryView>(buildParams) {
    override fun onViewCreated(view: SelectCountryView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            // brief but not very readable
            bind(view to rib.output using ViewEventToOutput)
        }
    }

    internal object ViewEventToOutput : (SelectCountryView.Event) -> SelectCountryRib.Output? {
        override fun invoke(event: SelectCountryView.Event): SelectCountryRib.Output? =
            when (event) {
                is SelectCountryView.Event.CountrySelected -> SelectCountryRib.Output.CountrySelected(event.country)
            }
    }
}