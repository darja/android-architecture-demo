package com.badoo.ribs.core.customisation

import com.badoo.ribs.core.Rib
import kotlin.reflect.KClass

interface RibCustomisationDirectory {

    val parent: RibCustomisationDirectory?

    fun <T : Rib> getSubDirectory(key: KClass<T>) : RibCustomisationDirectory?

    fun <T : Rib> getSubDirectoryOrSelf(key: KClass<T>) : RibCustomisationDirectory

    fun <T : RibRoutingConfig> get(key: KClass<T>) : T?

    fun <T : RibRoutingConfig> getRecursively(key: KClass<T>) : T?

    fun <T : RibRoutingConfig> getRecursivelyOrDefault(default: T) : T =
        get(default::class) ?: parent?.get(default::class) ?: default
}
