package hr.perisic.luka.robotcontroller.app.ui.position

import hr.perisic.luka.robotcontroller.app.ui.scale.MapScale

internal sealed class RobotPositionViewState {

    data class Position(
        val x: Int,
        val y: Int
    ): RobotPositionViewState()

    data class Scale(
        val mapScale: MapScale
    ): RobotPositionViewState()

    data class Info(
        val visible: Boolean
    ): RobotPositionViewState()
}