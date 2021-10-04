package hr.perisic.luka.data.api.source

import hr.perisic.luka.domain.model.InfoModel
import kotlinx.coroutines.flow.Flow

//TODO change to internal
interface InfoApiSource {

    fun getInfo(): Flow<InfoModel>
}