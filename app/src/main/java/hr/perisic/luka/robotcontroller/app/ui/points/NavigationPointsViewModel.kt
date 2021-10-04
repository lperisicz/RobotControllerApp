package hr.perisic.luka.robotcontroller.app.ui.points

import hr.perisic.luka.robotcontroller.app.base.BaseViewModel
import hr.perisic.luka.domain.usecase.GetNavigationPointsUseCase
import kotlinx.coroutines.flow.map

internal class NavigationPointsViewModel(
    getNavigationPointsUseCase: GetNavigationPointsUseCase
) : BaseViewModel<NavigationPointsViewState>() {

    init {
        query(getNavigationPointsUseCase().map { NavigationPointsViewState(it) })
    }

}