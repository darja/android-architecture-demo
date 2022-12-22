package com.veriff.badooribsflow.ribs.root

import com.veriff.badooribsflow.ribs.root.intro.IntroBuilder
import com.veriff.badooribsflow.ribs.root.intro.IntroRib
import com.veriff.badooribsflow.ribs.root.verification.VerificationBuilder
import com.veriff.badooribsflow.ribs.root.verification.VerificationRib

internal class RootChildBuilders(
    dependency: RootRib.Dependency
) {
    private val subtreeDeps = SubtreeDependency(dependency)

    val introBuilder: IntroBuilder = IntroBuilder(subtreeDeps)

    val verificationBuilder: VerificationBuilder = VerificationBuilder(subtreeDeps)

    class SubtreeDependency(
        dependency: RootRib.Dependency
    ) : RootRib.Dependency by dependency,
        IntroRib.Dependency,
        VerificationRib.Dependency
}
