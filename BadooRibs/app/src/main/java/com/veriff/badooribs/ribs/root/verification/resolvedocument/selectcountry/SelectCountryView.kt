package com.veriff.badooribs.ribs.root.verification.resolvedocument.selectcountry

import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import io.reactivex.ObservableSource

interface SelectCountryView : RibView,
    ObservableSource<SelectCountryView.Event> {

    interface Factory : ViewFactory<SelectCountryView>

    sealed class Event {
        class CountrySelected(val country: String) : Event()
    }
}