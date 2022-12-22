package com.veriff.badooribs.ribs.root

import com.veriff.badooribs.ribs.error.ErrorBuilder
import com.veriff.badooribs.ribs.error.ErrorRib
import com.veriff.badooribs.ribs.root.intro.IntroBuilder
import com.veriff.badooribs.ribs.root.intro.IntroRib
import com.veriff.badooribs.ribs.root.verification.VerificationFlowBuilder
import com.veriff.badooribs.ribs.root.verification.VerificationFlowRib

internal class RootChildBuilders(
    dependency: RootRib.Dependency
) {
    private val subtreeDeps = SubtreeDependency(dependency)

    val errorBuilder: ErrorBuilder = ErrorBuilder(subtreeDeps)
    val introBuilder: IntroBuilder = IntroBuilder(subtreeDeps)
    val verificationFlowBuilder: VerificationFlowBuilder = VerificationFlowBuilder(subtreeDeps)

    class SubtreeDependency(
        dependency: RootRib.Dependency
    ) : RootRib.Dependency by dependency,
        ErrorRib.Dependency,
        IntroRib.Dependency,
        VerificationFlowRib.Dependency
}