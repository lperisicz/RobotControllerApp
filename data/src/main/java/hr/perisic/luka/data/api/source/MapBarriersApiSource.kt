package hr.perisic.luka.data.api.source

import hr.perisic.luka.domain.model.MapBarrierModel
import kotlinx.coroutines.flow.Flow

internal interface MapBarriersApiSource {

    fun getMapBarriers(): Flow<List<MapBarrierModel>>
}