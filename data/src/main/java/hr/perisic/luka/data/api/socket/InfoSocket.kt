package hr.perisic.luka.data.api.socket

import hr.perisic.luka.domain.model.InfoModel
import kotlinx.coroutines.flow.Flow

internal interface InfoSocket {

    fun getInfo(): Flow<InfoModel>
}