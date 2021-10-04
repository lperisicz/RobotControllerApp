package hr.perisic.luka.robotcontroller.app.ui.widget.position

import android.content.Context
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.PointF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import hr.perisic.luka.robotcontroller.R
import hr.perisic.luka.robotcontroller.app.ui.scale.MapScale
import hr.perisic.luka.robotcontroller.app.ui.widget.base.BaseOverlayView

internal class RobotPositionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseOverlayView(context, attrs, defStyleAttr) {

    var listener: Listener? = null
    private val robotDrawable: Drawable = ContextCompat.getDrawable(context, R.drawable.ic_robot_on_map)!!
    private var robotPosition: Point? = null
    private val drawableSize = resources.getDimensionPixelSize(R.dimen.robot_point_size)

    private var mapScale: MapScale = MapScale(
        scale = 1f,
        scrollPosition = PointF(0f, 0f)
    )

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRobot()
    }

    override fun onClick(position: PointF): Boolean {
        return position.atRobot().also { robotClicked ->
            if (robotClicked) {
                listener?.onRobotClicked()
            }
        }
    }

    override fun consumeMotionEvent(position: PointF): Boolean = position.atRobot()

    fun setPosition(x: Int, y: Int) {
        this.robotPosition = Point(x, y)
        invalidate()
    }

    fun setScale(scale: MapScale) {
        this.mapScale = scale
        invalidate()
    }

    private fun Canvas.drawRobot() = robotPosition?.let {
        val projectedPosition = PointF(it.x.toFloat(), it.y.toFloat()) //mapScale.operator(PointF(it.x.toFloat(), it.y.toFloat()))
        robotDrawable.setBounds(
            projectedPosition.x.toInt() - drawableSize.scale().div(2),
            projectedPosition.y.toInt() - drawableSize.scale().div(2),
            projectedPosition.x.toInt() + drawableSize.scale().div(2),
            projectedPosition.y.toInt() + drawableSize.scale().div(2)
        )
        robotDrawable.draw(this)
    }

    private fun Int.scale(): Int = this.times(this@RobotPositionView.mapScale.scale).toInt()

    private fun PointF.atRobot(): Boolean {
        val let = robotPosition?.let {
            x.toInt() in it.x - drawableSize.scale().div(2)..it.x + drawableSize.scale().div(2) &&
                    y.toInt() in it.y - drawableSize.scale().div(2)..it.y + drawableSize.scale().div(2)
        }
        if (let?.not() == true) {
            listener?.onOutsideClicked()
        }
        return let ?: false
    }

    internal interface Listener {

        fun onRobotClicked()

        fun onOutsideClicked()
    }

}