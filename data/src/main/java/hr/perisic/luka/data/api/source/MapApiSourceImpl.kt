package hr.perisic.luka.data.api.source

import hr.perisic.luka.data.api.service.MapApi
import hr.perisic.luka.domain.model.MapModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class MapApiSourceImpl(
    private val mapApi: MapApi
): MapApiSource {

    override fun getMap(): Flow<MapModel> = flow {
        emit(mapApi.getMap())
    }
}