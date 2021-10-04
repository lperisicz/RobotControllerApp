package hr.perisic.luka.data.repository

import hr.perisic.luka.data.api.source.MapBarriersApiSource
import hr.perisic.luka.domain.model.MapBarrierModel
import hr.perisic.luka.domain.repository.MapBarriersRepository
import kotlinx.coroutines.flow.Flow

internal class MapBarriersRepositoryImpl(
    private val mapBarriersApiSource: MapBarriersApiSource
): MapBarriersRepository {

    override fun getMapBarriers(): Flow<List<MapBarrierModel>> = mapBarriersApiSource.getMapBarriers()
}