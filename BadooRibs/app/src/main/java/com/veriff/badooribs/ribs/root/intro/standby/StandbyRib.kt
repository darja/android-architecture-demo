package com.veriff.badooribs.ribs.root.intro.standby

import com.badoo.ribs.core.Rib
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.veriff.badooribs.domain.model.VerificationScenario
import com.veriff.badooribs.ribs.RibDependency

interface StandbyRib : Rib, Connectable<StandbyRib.Input, StandbyRib.Output> {
    interface Dependency : RibDependency

    sealed class Input

    sealed class Output {
        class VerificationStart(val scenario: VerificationScenario) : Output()
    }
}