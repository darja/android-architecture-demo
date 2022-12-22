package com.veriff.uberribs.ribs.intro.standby

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.jakewharton.rxrelay2.PublishRelay
import com.veriff.uberribs.databinding.RibStandbyBinding
import com.veriff.uberribs.ribs.intro.consent.ConsentInteractor
import com.veriff.uberribs.ribs.intro.standby.StandbyInteractor.StandbyPresenter.UiEvent
import io.reactivex.Observable

/**
 * Top level view for {@link StandbyBuilder.StandbyScope}.
 */
class StandbyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle), StandbyInteractor.StandbyPresenter {
    private val binding = RibStandbyBinding.inflate(LayoutInflater.from(context), this)

    private val uiEvents = PublishRelay.create<UiEvent>()

    init {
        binding.readyButton.setOnClickListener { uiEvents.accept(UiEvent.ReadyForVerification) }
    }

    override fun observeUiEvents(): Observable<UiEvent> = uiEvents

}
