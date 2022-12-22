package com.veriff.uberribs.ribs.intro.consent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.jakewharton.rxrelay2.PublishRelay
import com.veriff.uberribs.databinding.RibConsentBinding
import com.veriff.uberribs.domain.model.Branding
import com.veriff.uberribs.ribs.intro.consent.ConsentInteractor.ConsentPresenter.UiEvent
import io.reactivex.Observable

/**
 * Top level view for {@link ConsentBuilder.ConsentScope}.
 */
class ConsentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle), ConsentInteractor.ConsentPresenter {
    private val binding = RibConsentBinding.inflate(LayoutInflater.from(context), this)

    private val uiEvents = PublishRelay.create<UiEvent>()

    init {
        binding.agreeButton.setOnClickListener { uiEvents.accept(UiEvent.Agree) }
        binding.declineButton.setOnClickListener { uiEvents.accept(UiEvent.Decline) }
    }

    fun applyBranding(branding: Branding) {
        binding.agreeButton.setBackgroundColor(context.getColor(branding.primaryColorRes))
        binding.declineButton.setBackgroundColor(context.getColor(branding.secondaryColorRes))
    }

    override fun observeUiEvents(): Observable<UiEvent> = uiEvents
}
