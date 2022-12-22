package com.veriff.badooribsflow

import android.os.Bundle
import android.view.ViewGroup
import com.badoo.ribs.android.RibActivity
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.modality.BuildContext
import com.veriff.badooribsflow.ribs.root.RootBuilder
import com.veriff.badooribsflow.ribs.root.RootRib

class MainActivity : RibActivity() {

    override val rootViewGroup: ViewGroup
        get() = findViewById(R.id.rib_container)

    override fun createRib(savedInstanceState: Bundle?): Rib {
        val buildContext = BuildContext.root(
            savedInstanceState = savedInstanceState,
        )
        val rootDeps = object : RootRib.Dependency {}
        return RootBuilder(rootDeps).build(buildContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        System.setProperty("kotlinx.coroutines.debug", "on")
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
    }
}
