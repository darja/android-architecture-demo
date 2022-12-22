package com.veriff.badooribs.ribs.root.verification.resolvedocument

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.ribs.clienthelper.childaware.whenChildBuilt
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.routing.source.backstack.operation.replace
import com.veriff.badooribs.domain.logic.verification.GetTargetsForDocumentTypeUseCase
import com.veriff.badooribs.ribs.root.verification.resolvedocument.ResolveDocumentRib.Configuration
import com.veriff.badooribs.ribs.root.verification.resolvedocument.documenttype.SelectDocumentTypeRib
import com.veriff.badooribs.ribs.root.verification.resolvedocument.selectcountry.SelectCountryRib
import io.reactivex.functions.Consumer

internal class ResolveDocumentInteractor(
    private val deps: ResolveDocumentRib.Dependency,
    private val backStack: BackStack<Configuration>,
    buildParams: BuildParams<*>,
) : Interactor<ResolveDocumentRib, Nothing>(
    buildParams = buildParams
) {
    private val getTargetsForDocumentTypeUseCase = GetTargetsForDocumentTypeUseCase()

    override fun onCreate(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {}

        whenChildBuilt<SelectCountryRib>(nodeLifecycle) { commonLifecycle, child ->
            commonLifecycle.createDestroy {
                bind(child.output to selectCountryListener)
            }
        }

        whenChildBuilt<SelectDocumentTypeRib>(nodeLifecycle) { commonLifecycle, child ->
            commonLifecycle.createDestroy {
                bind(child.output to selectDocumentTypeListener)
            }
        }
    }

    private val selectCountryListener = Consumer<SelectCountryRib.Output> { output ->
        when (output) {
            is SelectCountryRib.Output.CountrySelected -> backStack.replace(Configuration.SelectDocumentType)
        }
    }

    private val selectDocumentTypeListener = Consumer<SelectDocumentTypeRib.Output> { output ->
        when (output) {
            is SelectDocumentTypeRib.Output.DocumentTypeSelected -> {
                deps.verificationRepository.setup(output.documentType) // to prevent unresolved document for the next photo
                val newTargets = getTargetsForDocumentTypeUseCase.execute(output.documentType)
                rib.output.accept(ResolveDocumentRib.Output.Finish(targetUpdate = newTargets))
            }
        }
    }
}