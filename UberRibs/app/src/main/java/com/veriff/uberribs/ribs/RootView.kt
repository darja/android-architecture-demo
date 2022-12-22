package com.veriff.uberribs.ribs

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast

/**
 * Top level view for {@link RootBuilder.RootScope}.
 */
class RootView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), RootInteractor.RootPresenter {
    init {
//        setBackgroundColor(Color.CYAN)
    }

    override fun showVerificationCancelled() {
        Toast.makeText(context, "Verification cancelled", Toast.LENGTH_SHORT).show()
    }
}
