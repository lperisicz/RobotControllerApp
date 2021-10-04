package hr.perisic.luka.data.api.source

import hr.perisic.luka.data.api.service.MapBarriersApi
import hr.perisic.luka.domain.model.MapBarrierModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class MapBarriersApiSourceImpl(
    private val mapBarriersApi: MapBarriersApi
): MapBarriersApiSource {

    override fun getMapBarriers(): Flow<List<MapBarrierModel>> = flow {
        emit(mapBarriersApi.getMapBarriers())
    }
}