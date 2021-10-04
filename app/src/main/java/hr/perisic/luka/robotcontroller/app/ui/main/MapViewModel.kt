package hr.perisic.luka.robotcontroller.app.ui.main

import hr.perisic.luka.robotcontroller.app.base.BaseViewModel
import hr.perisic.luka.robotcontroller.app.ui.scale.MapScale
import hr.perisic.luka.robotcontroller.app.ui.scale.MapScalePublisher
import hr.perisic.luka.domain.usecase.GetMapUseCase
import kotlinx.coroutines.flow.map

internal class MapViewModel(
    getMapUseCase: GetMapUseCase,
    private val mapScalePublisher: MapScalePublisher
) : BaseViewModel<MapViewState>() {

    init {
        query(getMapUseCase().map { MapViewState.Map(it) })
    }

    fun setScale(scale: MapScale) = mapScalePublisher.setScale(scale)
}