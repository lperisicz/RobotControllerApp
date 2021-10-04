package hr.perisic.luka.domain.repository

import hr.perisic.luka.domain.model.MapBarrierModel
import kotlinx.coroutines.flow.Flow

interface MapBarriersRepository {

    fun getMapBarriers(): Flow<List<MapBarrierModel>>
}