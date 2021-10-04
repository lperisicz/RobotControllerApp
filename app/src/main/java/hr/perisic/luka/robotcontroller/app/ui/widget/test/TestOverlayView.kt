package hr.perisic.luka.robotcontroller.app.ui.widget.test

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TestOverlayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), TestOverlayContract {

    private var viewWidth: Float = 0f
    private var viewHeight: Float = 0f

    private var verticalOffset: Float = 0f
    private var horizontalOffset: Float = 0f

    private var bitmap: Bitmap? = null

    init {

    }

    override fun setBitmap(bitmap: Bitmap?) {
        this.bitmap = bitmap
        invalidate()
        calculateOffsets()
    }

    override fun calculateOffsets() {
        bitmap?.let {
            val bitmapHeight = it.height
            val bitmapWidth = it.width

            this.verticalOffset = (viewHeight - bitmapHeight).div(2f)
            this.horizontalOffset = (viewWidth - bitmapWidth).div(2f)

            println("VIEW calculateOffset(): $verticalOffset : $horizontalOffset")
        }
    }

    override fun onMotionEvent(event: MotionEvent): Boolean {
        val positionValid = isPositionValid(event.x, event.y)
        if (positionValid) {

        }
        return positionValid
    }

    override fun isPositionValid(xPosition: Float, yPosition: Float): Boolean {
        val isXPositionValid = xPosition in horizontalOffset..(viewWidth - horizontalOffset)
        val isYPositionValid = xPosition in horizontalOffset..(viewWidth - horizontalOffset)
        if (isXPositionValid) {
            println("VIEW VALID X")
        } else {
            println("VIEW INVALID X")
        }
        if (isYPositionValid) {
            println("VIEW VALID Y")
        } else {
            println("VIEW INVALID Y")
        }
        return isXPositionValid && isYPositionValid && bitmap?.let {
            false
        } ?: false
    }

    override fun isPixelValid(xPosition: Float, yPosition: Float): Boolean {
        return bitmap?.let {
            /*return if(it.getPixel(xPosition.toInt(), yPosition.toInt()) < ) {

                true
            } else*/ false
        } ?: false
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        viewWidth = MeasureSpec.getSize(widthMeasureSpec).toFloat()
        viewHeight = MeasureSpec.getSize(heightMeasureSpec).toFloat()
        calculateOffsets()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (event?.action == MotionEvent.ACTION_DOWN || event?.action == MotionEvent.ACTION_MOVE) {
            onMotionEvent(event)
        } else super.onTouchEvent(event)
    }
}

interface TestOverlayContract {

    fun setBitmap(bitmap: Bitmap?)

    fun calculateOffsets()

    fun onMotionEvent(event: MotionEvent): Boolean

    fun isPositionValid(xPosition: Float, yPosition: Float): Boolean

    fun isPixelValid(xPosition: Float, yPosition: Float): Boolean
}