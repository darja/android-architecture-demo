package com.veriff.uberribs.ribs.intro.progress

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.veriff.uberribs.R
import com.veriff.uberribs.databinding.RibFullscreenProgressBinding

/**
 * Top level view for {@link FullscreenProgressBuilder.FullscreenProgressScope}.
 */
class FullscreenProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle),
    FullscreenProgressInteractor.FullscreenProgressPresenter {
    private val binding = RibFullscreenProgressBinding.inflate(LayoutInflater.from(context), this)

    init {
        setBackgroundColor(
            ResourcesCompat.getColor(
                context.resources,
                R.color.loading_overlay,
                context.theme
            )
        )
    }
}
