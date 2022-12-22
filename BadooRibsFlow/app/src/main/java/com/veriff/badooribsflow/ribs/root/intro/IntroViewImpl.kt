package com.veriff.badooribsflow.ribs.root.intro

import android.view.ViewGroup
import com.badoo.ribs.core.view.AndroidRibView
import com.veriff.badooribsflow.databinding.RibIntroBinding
import com.veriff.badooribsflow.ribs.root.intro.IntroView.Event
import com.veriff.badooribsflow.util.clicks
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.merge

class IntroViewImpl(private val binding: RibIntroBinding) : AndroidRibView(),
    IntroView {
    override val androidView: ViewGroup
        get() = binding.root

    override suspend fun collect(collector: FlowCollector<Event>) {
        merge(
            binding.continueButton.clicks { Event.ContinueClicked },
            binding.cancelButton.clicks { Event.CancelClicked }
        )
            .collect(collector)
    }
}
