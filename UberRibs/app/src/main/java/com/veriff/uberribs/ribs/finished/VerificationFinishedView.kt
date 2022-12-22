package com.veriff.uberribs.ribs.finished

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.veriff.uberribs.R
import com.veriff.uberribs.databinding.RibVerificationFinishedBinding

/**
 * Top level view for {@link VerificationFinishedBuilder.VerificationFinishedScope}.
 */
class VerificationFinishedView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle),
    VerificationFinishedInteractor.VerificationFinishedPresenter {

    val binding = RibVerificationFinishedBinding.inflate(LayoutInflater.from(context), this)
}
