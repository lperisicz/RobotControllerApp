package hr.perisic.luka.robotcontroller.app.ui.info

import hr.perisic.luka.robotcontroller.app.base.BaseViewModel
import hr.perisic.luka.domain.usecase.GetInfoUseCase
import kotlinx.coroutines.flow.map

internal class InfoViewModel(
    getInfoUseCase: GetInfoUseCase
) : BaseViewModel<InfoViewState>() {

    init {
        query(getInfoUseCase().map { InfoViewState.Info(it) })
    }

}