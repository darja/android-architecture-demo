package com.veriff.badooribs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.badoo.ribs.android.RibActivity
import com.badoo.ribs.android.permissionrequester.PermissionRequester
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.modality.BuildContext
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.plugin.utils.debug.DebugControlsHost
import com.badoo.ribs.core.plugin.utils.debug.GrowthDirection
import com.badoo.ribs.debug.TreePrinter
import com.veriff.badooribs.databinding.ActivityMainBinding
import com.veriff.badooribs.domain.repo.MockScenariosRepository
import com.veriff.badooribs.domain.repo.VerificationRepository
import com.veriff.badooribs.ribs.root.RootBuilder
import com.veriff.badooribs.ribs.root.RootRib
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : RibActivity() {
    private lateinit var binding: ActivityMainBinding

    override val rootViewGroup: ViewGroup
        get() = binding.ribContainer

    override fun createRib(savedInstanceState: Bundle?): Rib {
        val buildContext = BuildContext.root(
            savedInstanceState = savedInstanceState,
            defaultPlugins = { node ->
                if (BuildConfig.DEBUG) {
                    listOfNotNull(
                        if (node.isRoot) createDebugControlHost() else null
                    )
                } else emptyList()
            }
        )

        val rootDeps = object : RootRib.Dependency {
            override val permissionRequester: PermissionRequester =
                integrationPoint.permissionRequester

            override val verificationRepository = VerificationRepository()
            override val scenariosRepository = MockScenariosRepository()

            override val io: Scheduler = Schedulers.io()
            override val ui: Scheduler = AndroidSchedulers.mainThread()
        }

        return RootBuilder(rootDeps).build(buildContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
    }

    private fun createDebugControlHost(): Plugin =
        DebugControlsHost(
            viewGroupForChildren = { findViewById(R.id.debug_controls_host) },
            growthDirection = GrowthDirection.BOTTOM,
            defaultTreePrinterFormat = TreePrinter.FORMAT_SIMPLE
        )
}