package hr.perisic.luka.robotcontroller.app.ui.widget.destination

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.absoluteValue

private const val LONG_PRESS_TIMEOUT = 500L
private const val MOVE_ACTION_MAX_DISTANCE = 20

internal class DestinationRequestView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var lastActionType: ActionType = ActionType.OTHER
    var listener: DestinationRequestListener? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false
        return false
        /*return when (event.action) {
            MotionEvent.ACTION_DOWN -> onActionDown()
            MotionEvent.ACTION_UP -> onActionUp(event)
            MotionEvent.ACTION_MOVE -> onActionMove(event)
            else -> onActionOther()
        }*/
    }

    private fun onActionDown(): Boolean {
        lastActionType = ActionType.DOWN
        return false
    }

    private fun onActionUp(event: MotionEvent): Boolean =
        if (listener != null && lastActionType == ActionType.DOWN && event.downTime >= LONG_PRESS_TIMEOUT) {
            lastActionType = ActionType.OTHER
            listener?.onRequestDestination(event.x, event.y)
            false
        } else {
            lastActionType = ActionType.OTHER
            false
        }

    private fun onActionMove(event: MotionEvent): Boolean =
        when {
            event.historySize == 0 -> false
            event.getHistoricalX(0).minus(event.x).absoluteValue <= MOVE_ACTION_MAX_DISTANCE -> false
            else -> {
                lastActionType = ActionType.OTHER
                false
            }
        }

    private fun onActionOther(): Boolean {
        lastActionType = ActionType.OTHER
        return false
    }

    private enum class ActionType {
        DOWN, OTHER;
    }

    interface DestinationRequestListener {

        fun onRequestDestination(x: Float, y: Float)
    }
}