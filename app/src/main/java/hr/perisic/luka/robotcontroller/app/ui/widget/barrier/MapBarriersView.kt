package hr.perisic.luka.robotcontroller.app.ui.widget.barrier

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import hr.perisic.luka.robotcontroller.R
import hr.perisic.luka.domain.model.MapBarrierModel

class MapBarriersView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val circleRadius = resources.getDimension(R.dimen.barrier_circle_radius)
    private val circleColor = ContextCompat.getColor(context, R.color.color_primary)
    private val circlePaint = Paint()

    private val lineStrokeWidth = resources.getDimension(R.dimen.barrier_line_stroke_width)
    private val lineColor = ContextCompat.getColor(context, R.color.color_on_surface)
    private val linePaint = Paint()

    private var barriers: List<MapBarrierModel> = emptyList()

    init {
        circlePaint.isAntiAlias = true
        circlePaint.color = circleColor
        circlePaint.style = Paint.Style.FILL

        linePaint.isAntiAlias = true
        linePaint.color = lineColor
        linePaint.strokeWidth = lineStrokeWidth
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean = false

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        barriers.forEach {
            canvas?.drawBarrier(it)
        }
    }

    private fun Canvas.drawBarrier(mapBarrier: MapBarrierModel) {
        mapBarrier.apply {
            drawLine(startX, startY, endX, endY)
            drawCircle(startX, startY)
            drawCircle(endX, endY)
        }
    }

    private fun Canvas.drawLine(startX: Int, startY: Int, endX: Int, endY: Int) {
        drawLine(startX.toFloat(), startY.toFloat(), endX.toFloat(), endY.toFloat(), linePaint)
    }

    private fun Canvas.drawCircle(x: Int, y: Int) {
        drawCircle(x.toFloat(), y.toFloat(), circleRadius, circlePaint)
    }

    fun setMapBarriers(barriers: List<MapBarrierModel>) {
        this.barriers = barriers
        invalidate()
    }
}
