package com.veriff.badooribs.ribs

import com.badoo.ribs.android.permissionrequester.CanProvidePermissionRequester
import com.veriff.badooribs.domain.repo.MockScenariosRepository
import com.veriff.badooribs.domain.repo.VerificationRepository
import io.reactivex.Scheduler

interface RibDependency: CanProvidePermissionRequester {
    val io: Scheduler
    val ui: Scheduler

    val verificationRepository: VerificationRepository
    val scenariosRepository: MockScenariosRepository
}