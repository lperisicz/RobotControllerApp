package hr.perisic.luka.robotcontroller.app.ui.barrier

import hr.perisic.luka.robotcontroller.app.base.BaseViewModel
import hr.perisic.luka.domain.usecase.GetMapBarriersUseCase
import kotlinx.coroutines.flow.map

internal class MapBarriersViewModel(
    getMapBarriersUseCase: GetMapBarriersUseCase
) : BaseViewModel<MapBarriersViewState>() {

    init {
        query(getMapBarriersUseCase.invoke().map { MapBarriersViewState(it) })
    }
}