package hr.perisic.luka.data.api.source

import hr.perisic.luka.data.api.socket.InfoSocket
import hr.perisic.luka.domain.model.InfoModel
import kotlinx.coroutines.flow.Flow

internal class InfoApiSourceImpl(
    private val infoSocket: InfoSocket
) : InfoApiSource {

    override fun getInfo(): Flow<InfoModel> = infoSocket.getInfo()
}