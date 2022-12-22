package com.veriff.badooribsflow.ribs.root.verification

import com.badoo.ribs.core.Rib
import com.badoo.ribs.flow.connector.Connectable
import com.veriff.badooribsflow.ribs.RibDependency
import com.veriff.badooribsflow.ribs.root.intro.IntroRib

interface VerificationRib : Rib, Connectable<VerificationRib.Input, VerificationRib.Output> {
    interface Dependency : RibDependency

    sealed class Input

    sealed class Output {
        object Finished: Output()
    }
}
