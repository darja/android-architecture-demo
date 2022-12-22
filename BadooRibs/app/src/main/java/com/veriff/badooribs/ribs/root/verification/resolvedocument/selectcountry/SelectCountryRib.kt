package com.veriff.badooribs.ribs.root.verification.resolvedocument.selectcountry

import com.badoo.ribs.core.Rib
import com.badoo.ribs.rx2.clienthelper.connector.Connectable

interface SelectCountryRib : Rib, Connectable<SelectCountryRib.Input, SelectCountryRib.Output> {
    interface Dependency

    sealed class Input

    sealed class Output {
        class CountrySelected(country: String) : Output()
    }
}