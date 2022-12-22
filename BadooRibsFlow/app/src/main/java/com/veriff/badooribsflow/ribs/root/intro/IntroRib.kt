package com.veriff.badooribsflow.ribs.root.intro

import com.badoo.ribs.core.Rib
import com.badoo.ribs.flow.connector.Connectable
import com.veriff.badooribsflow.ribs.RibDependency
import com.veriff.badooribsflow.ribs.root.intro.IntroRib.Input
import com.veriff.badooribsflow.ribs.root.intro.IntroRib.Output

interface IntroRib : Rib, Connectable<Input, Output> {
    interface Dependency : RibDependency

    sealed class Input

    sealed class Output {
        object Finished : Output()
        object Cancelled : Output()
    }
}
