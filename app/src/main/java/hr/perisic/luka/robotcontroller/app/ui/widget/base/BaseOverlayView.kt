package hr.perisic.luka.robotcontroller.app.ui.widget.base

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

open class BaseOverlayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean = e?.let {
            this@BaseOverlayView.onClick(PointF(it.x, it.y))
        } ?: false

        override fun onLongPress(e: MotionEvent?) = e?.let {
            this@BaseOverlayView.onLongClick(e.x, e.y)
        } ?: Unit

        override fun onDown(e: MotionEvent?): Boolean = e?.let {
            this@BaseOverlayView.consumeMotionEvent(PointF(it.x, it.y))
        } ?: false

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean
        = e1?.let {
            this@BaseOverlayView.onScroll(PointF(it.x, it.y))
        } ?: false
    }

    private var gestureDetector: GestureDetector = GestureDetector(context, gestureListener)

    init {
        isClickable = true
    }

    @SuppressLint("ClickableViewAccessibility")
    final override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    protected open fun consumeMotionEvent(position: PointF): Boolean = false

    protected open fun onClick(position: PointF): Boolean = false

    protected open fun onLongClick(x: Float, y: Float) {}

    protected open fun onScroll(position: PointF): Boolean = false


}