package com.veriff.badooribs.ribs.root.intro.consent

import com.badoo.ribs.core.Rib
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.veriff.badooribs.ribs.root.intro.consent.ConsentRib.Input
import com.veriff.badooribs.ribs.root.intro.consent.ConsentRib.Output

interface ConsentRib : Rib, Connectable<Input, Output> {
    interface Dependency

    sealed class Input

    sealed class Output {
        object Accepted : Output()
        object Declined : Output()
    }
}