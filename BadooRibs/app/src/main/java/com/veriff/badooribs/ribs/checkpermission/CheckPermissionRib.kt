package com.veriff.badooribs.ribs.checkpermission

import com.badoo.ribs.core.Rib
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.veriff.badooribs.ribs.RibDependency

interface CheckPermissionRib : Rib, Connectable<CheckPermissionRib.Input, CheckPermissionRib.Output> {
    interface Dependency : RibDependency

    sealed class Input

    sealed class Output {
        object Granted : Output()
        object Denied : Output()
    }
}