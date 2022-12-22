package com.veriff.badooribs.ribs.root.verification.resolvedocument.documenttype

import com.badoo.ribs.core.Rib
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.veriff.badooribs.domain.model.DocumentType

interface SelectDocumentTypeRib : Rib, Connectable<SelectDocumentTypeRib.Input, SelectDocumentTypeRib.Output> {
    interface Dependency

    sealed class Input

    sealed class Output {
        class DocumentTypeSelected(val documentType: DocumentType) : Output()
    }
}