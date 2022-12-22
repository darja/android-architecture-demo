package com.badoo.ribs.routing

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class Routing<C : Parcelable>(
    val state: C,
    val identifier: Identifier = Identifier(),
    val meta: Parcelable = NoMeta
) : Parcelable {

    @Parcelize
    data class Identifier(
        val id: Serializable = 0
    ) : Parcelable
}

@Parcelize
object NoMeta : Parcelable
