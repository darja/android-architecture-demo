package com.veriff.badooribsflow.ribs.root.verification

import android.view.ViewGroup
import com.badoo.ribs.core.view.AndroidRibView
import com.veriff.badooribsflow.databinding.RibVerificationBinding
import com.veriff.badooribsflow.util.clicks
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.merge

class VerificationViewImpl(private val binding: RibVerificationBinding) : AndroidRibView(),
    VerificationView {
    override val androidView: ViewGroup
        get() = binding.root

    override suspend fun collect(collector: FlowCollector<VerificationView.Event>) {
        merge(
            binding.verifyButton.clicks { VerificationView.Event.VerifyClicked },
        )
            .collect(collector)
    }
}
