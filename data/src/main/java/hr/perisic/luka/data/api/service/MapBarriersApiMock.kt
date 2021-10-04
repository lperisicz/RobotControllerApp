package hr.perisic.luka.data.api.service

import hr.perisic.luka.domain.model.MapBarrierModel

internal class MapBarriersApiMock : MapBarriersApi {

    override suspend fun getMapBarriers(): List<MapBarrierModel> = listOf(
        MapBarrierModel(
            startX = 100,
            startY = 1050,
            endX = 800,
            endY = 1250
        ),
        MapBarrierModel(
            startX = 100,
            startY = 1350,
            endX = 500,
            endY = 1400
        )
    )
}