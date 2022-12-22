package com.badoo.ribs.core.customisation

import com.badoo.ribs.core.Rib
import kotlin.reflect.KClass

interface MutableRibCustomisationDirectory : RibCustomisationDirectory {

    fun <T : Rib> putSubDirectory(key: KClass<T>, value: RibCustomisationDirectory)

    fun <T : RibRoutingConfig> put(key: KClass<T>, value: T)
}
