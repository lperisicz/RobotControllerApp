package hr.perisic.luka.robotcontroller.app.ui.widget.points

import android.content.Context
import android.graphics.Canvas
import android.graphics.PointF
import android.graphics.drawable.RotateDrawable
import android.util.AttributeSet
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import hr.perisic.luka.domain.model.NavigationPointModel
import hr.perisic.luka.robotcontroller.R
import hr.perisic.luka.robotcontroller.app.ui.widget.base.BaseOverlayView
import kotlin.math.pow
import kotlin.math.sqrt

internal class NavigationPointsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var navigationPoints: List<NavigationPointModel> = emptyList()
    private var currentDraggedPoint: NavigationPointModel? = null

    private val navigationPointDrawable = RotateDrawable().also {
        it.drawable = ContextCompat.getDrawable(context, R.drawable.ic_navigation_point)!!
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        navigationPoints.forEach {
            canvas?.drawNavigationPoint(it)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event == null) return false
        return when(event.action) {
            MotionEvent.ACTION_DOWN -> PointF(event.x, event.y).atAnyNavigationPoint()
            MotionEvent.ACTION_MOVE -> onScroll(PointF(event.x, event.y))
            else -> false
        }
    }

    private fun onScroll(position: PointF): Boolean {
        return currentDraggedPoint?.let {
            it.moveTo(position)
            true
        } ?: false
    }

    private fun Canvas.drawNavigationPoint(navigationPoint: NavigationPointModel) {
        val pivot = navigationPointDrawable.intrinsicHeight.div(2).toFloat()
        navigationPointDrawable.pivotX = pivot
        navigationPointDrawable.pivotY = pivot
        navigationPointDrawable.fromDegrees = 0f
        navigationPointDrawable.toDegrees = navigationPoint.angle.toFloat() * 2
        navigationPointDrawable.isPivotXRelative = false
        navigationPointDrawable.isPivotYRelative = false
        navigationPointDrawable.setBounds(
            navigationPoint.x,
            navigationPoint.y,
            navigationPoint.x + navigationPointDrawable.intrinsicWidth,
            navigationPoint.y + navigationPointDrawable.intrinsicHeight
        )
        navigationPointDrawable.draw(this)
    }

    fun setNavigationPoints(points: List<NavigationPointModel>) {
        this.navigationPoints = points
        invalidate()
    }

    private fun NavigationPointModel.moveTo(position: PointF) {
        val newList = navigationPoints.filter { it != this }.plus(
            NavigationPointModel(
                position.x.toInt(),
                position.y.toInt(),
                this.angle
            )
        )
        setNavigationPoints(newList)
    }

    private fun PointF.atAnyNavigationPoint(): Boolean {
        return navigationPoints.find { this.atNavigationPoint(it) }?.let {
            currentDraggedPoint = it
            true
        } ?: false
    }

    private fun PointF.atNavigationPoint(navigationPointModel: NavigationPointModel): Boolean {
        val distanceTo = this.distanceTo(PointF(navigationPointModel.x.toFloat(), navigationPointModel.y.toFloat()))
        return distanceTo <= navigationPointDrawable.intrinsicHeight
    }

    private fun PointF.distanceTo(otherPoint: PointF): Float {
        return sqrt(
            (otherPoint.x - this.x).pow(2) + (otherPoint.y - this.y).pow(2)
        )
    }
}

/*{

    private var circleColor: Int = Color.GREEN
    private var circlePaint: Paint = Paint()

    private var imageBitmap: Bitmap? = null

    private var circleRadius = 50f
    private var centerX = 200f
    private var centerY = 200f

    val mMatrix = Matrix()

    init {
        circlePaint.isAntiAlias = true
        circlePaint.color = circleColor
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            canvas.withMatrix(mMatrix) {
                canvas.drawCircle(centerX, centerY, circleRadius, circlePaint)
                canvas.drawCircle(centerX - circleRadius - circleRadius, centerY, circleRadius, circlePaint)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (event?.action == MotionEvent.ACTION_DOWN || event?.action == MotionEvent.ACTION_MOVE) {
            try {
                val pixel = imageBitmap?.getPixel(event.x.toInt(), event.y.toInt())
                if (pixel != null && pixel < 126.toRGB()) {
                    circleColor = Color.RED
                    circlePaint.color = circleColor
                } else {
                    circleColor = Color.GREEN
                    circlePaint.color = circleColor
                }
            } catch (e: Exception) {
                circleColor = Color.GRAY
                circlePaint.color = circleColor
                return false
            }
            setCenter(event.x, event.y)
            true
        } else false
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val viewWidth = MeasureSpec.getSize(widthMeasureSpec)
        val viewHeight = MeasureSpec.getSize(heightMeasureSpec)
        setCenter(viewWidth.toFloat() / 2, viewHeight.toFloat() / 2)
    }

    fun setImageBitmap(imageBitmap: Bitmap?) {
        this.imageBitmap = imageBitmap
    }

    private fun setCenter(xPosition: Float, yPosition: Float) {
        val points = floatArrayOf(xPosition, yPosition)
        mMatrix.mapPoints(points)
        this.centerX = points[0]
        this.centerY = points[1]
        println("STATE NEW: CENTER: $centerX, $centerY")
        invalidate()
    }

    fun setZoomViewState(viewState: ZoomableImageView.ZoomedImageViewState) {

    }

}

private fun Int.toRGB(): Int {
    return this * 0x010101 +  0xFF000000.toInt()
}
*/