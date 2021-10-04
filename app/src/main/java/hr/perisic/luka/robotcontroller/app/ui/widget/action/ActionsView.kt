package hr.perisic.luka.robotcontroller.app.ui.widget.action

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import hr.perisic.luka.robotcontroller.R
import hr.perisic.luka.robotcontroller.databinding.LayoutActionsBinding

internal class ActionsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: LayoutActionsBinding? = LayoutActionsBinding.inflate(LayoutInflater.from(context), this, true)
    private var positiveActionText: String?
    @DrawableRes
    private var positiveActionIcon: Int?

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ActionsView,
            0, 0).apply {

            try {
                positiveActionText = getString(R.styleable.ActionsView_positiveActionText)
                positiveActionIcon = getResourceId(R.styleable.ActionsView_positiveActionIcon, R.drawable.ic_navigate)
            } finally {
                recycle()
            }
        }
        binding!!.fabActionPositive.text = positiveActionText
        binding!!.fabActionPositive.icon = ContextCompat.getDrawable(context, positiveActionIcon!!)
    }

    override fun onDetachedFromWindow() {
        binding = null
        super.onDetachedFromWindow()
    }
}