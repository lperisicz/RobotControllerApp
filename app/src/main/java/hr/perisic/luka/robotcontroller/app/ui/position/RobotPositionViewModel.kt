package hr.perisic.luka.robotcontroller.app.ui.position

import hr.perisic.luka.robotcontroller.app.base.BaseViewModel
import hr.perisic.luka.robotcontroller.app.ui.scale.MapScalePublisher
import hr.perisic.luka.domain.usecase.GetRobotPositionUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

internal class RobotPositionViewModel(
    getRobotPositionUseCase: GetRobotPositionUseCase,
    mapScalePublisher: MapScalePublisher
) : BaseViewModel<RobotPositionViewState>() {

    init {
        query(getRobotPositionUseCase().map { RobotPositionViewState.Position(it.x, it.y) })
        query(mapScalePublisher.getScale().map { RobotPositionViewState.Scale(it) })
    }

    fun onRobotClicked() {
        runCommand {
            viewStates.first().let {
                //TODO find last info state and decide what the next info state should be
                if(it is RobotPositionViewState.Info && it.visible) {
                    viewStates.tryEmit(RobotPositionViewState.Info(false))
                } else {
                    viewStates.tryEmit(RobotPositionViewState.Info(true))
                }
            }
        }
    }

    fun onOutsideClicked() {
        runCommand {
            viewStates.tryEmit(RobotPositionViewState.Info(false))
        }
    }
}