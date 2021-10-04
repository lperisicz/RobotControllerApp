package hr.perisic.luka.data.api.service

import hr.perisic.luka.domain.model.MapBarrierModel

internal interface MapBarriersApi {

    suspend fun getMapBarriers(): List<MapBarrierModel>
}