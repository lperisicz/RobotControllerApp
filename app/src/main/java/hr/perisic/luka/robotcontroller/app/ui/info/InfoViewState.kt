package hr.perisic.luka.robotcontroller.app.ui.info

import hr.perisic.luka.domain.model.InfoModel

internal sealed class InfoViewState {

    data class Info(
        val infoModel: InfoModel
    ) : InfoViewState()
}