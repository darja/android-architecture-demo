package com.veriff.badooribs.ribs.root.intro

import com.veriff.badooribs.ribs.root.intro.consent.ConsentBuilder
import com.veriff.badooribs.ribs.root.intro.consent.ConsentRib
import com.veriff.badooribs.ribs.root.intro.standby.StandbyBuilder
import com.veriff.badooribs.ribs.root.intro.standby.StandbyRib

internal class IntroChildBuilders(
    dependency: IntroRib.Dependency
) {
    private val subtreeDeps = SubtreeDependency(dependency)

    val consentBuilder: ConsentBuilder = ConsentBuilder(subtreeDeps)
    val standbyBuilder: StandbyBuilder = StandbyBuilder(subtreeDeps)

    class SubtreeDependency(
        dependency: IntroRib.Dependency
    ) : IntroRib.Dependency by dependency,
        ConsentRib.Dependency,
        StandbyRib.Dependency
}