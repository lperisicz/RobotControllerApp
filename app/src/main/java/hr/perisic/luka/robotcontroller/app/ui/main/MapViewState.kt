package hr.perisic.luka.robotcontroller.app.ui.main

import hr.perisic.luka.domain.model.MapModel

internal sealed class MapViewState {

    data class Map(
        val mapModel: MapModel
    ) : MapViewState()

}