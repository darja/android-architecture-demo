package com.veriff.badooribs.ribs.root.verification.resolvedocument

import com.veriff.badooribs.ribs.root.verification.resolvedocument.SelectDocumentType.SelectDocumentTypeBuilder
import com.veriff.badooribs.ribs.root.verification.resolvedocument.documenttype.SelectDocumentTypeRib
import com.veriff.badooribs.ribs.root.verification.resolvedocument.selectcountry.SelectCountryBuilder
import com.veriff.badooribs.ribs.root.verification.resolvedocument.selectcountry.SelectCountryRib

internal class ResolveDocumentChildBuilders(
    dependency: ResolveDocumentRib.Dependency
) {
    private val subtreeDeps = SubtreeDependency(dependency)

    val selectCountryBuilder = SelectCountryBuilder(subtreeDeps)
    val selectDocumentTypeBuilder = SelectDocumentTypeBuilder(subtreeDeps)

    class SubtreeDependency(
        dependency: ResolveDocumentRib.Dependency
    ) : ResolveDocumentRib.Dependency by dependency,
        SelectCountryRib.Dependency,
        SelectDocumentTypeRib.Dependency
}