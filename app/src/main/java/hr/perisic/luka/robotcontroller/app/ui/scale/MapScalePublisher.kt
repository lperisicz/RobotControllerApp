package hr.perisic.luka.robotcontroller.app.ui.scale

import android.graphics.PointF
import hr.perisic.luka.domain.base.extensions.mutableSharedFlowWithLatest
import hr.perisic.luka.domain.model.MapModel
import hr.perisic.luka.domain.usecase.GetMapUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlin.math.pow

class MapScalePublisher(
    private val getMapUseCase: GetMapUseCase
) {

    private val scale = mutableSharedFlowWithLatest<MapScale>()

    fun setScale(scale: MapScale) {
        this.scale.tryEmit(scale)
    }

    fun getScale(): Flow<MapScale> = scale.combine(getMapUseCase()) { scale, mapModel ->
        mapModel?.let { image ->
            MapScale(
                scale = scale.scale,
                scrollPosition = scale.scrollPosition,
                operator = createOperator(scale, image)
            )
        } ?: scale
    }

    private fun createOperator(scale: MapScale, image: MapModel): (PointF) -> PointF {
        return { pointF ->

            fun translateValue(value: Float, k: Float, c: Float, d: Float): Float {
                return if(k > 1f)
                    k.times(value.plus(d.times(1f.div(k.pow(2)).minus(c))))
                else
                    value
            }

            val deviceWidth = 1080f
            val deviceHeight = 2280f
            val coefficient = deviceWidth.div(image.size.width.toFloat())
            val imageDisplayedHeight = image.size.height.toFloat().times(coefficient)
            val imageDisplayedWidth = image.size.width.times(coefficient)
            val imageVerticalOffset = 300//deviceHeight.minus(imageDisplayedHeight).div(2f)
            PointF(
                translateValue(
                    pointF.x.times(coefficient), scale.scale, scale.scrollPosition.x, deviceWidth
                ).also {
                       println("TRANSLATE TO: $it, FROM: ${pointF.x.times(coefficient)}")
                    if (scale.scale.times(pointF.x.times(coefficient)) < it) {
                        println("ERROR AT: k ${scale.scale}")
                    }
                },
                translateValue(
                    pointF.y.times(coefficient), scale.scale, scale.scrollPosition.y, imageDisplayedHeight
                ).plus(imageVerticalOffset).also {
                    println("TRANSLATE Y TO: $it, FROM: ${pointF.y.times(coefficient)}")
                }
            )
        }
    }
}

data class MapScale(
    val scale: Float,
    val scrollPosition: PointF,
    val operator: (PointF) -> PointF = { point -> point }
)