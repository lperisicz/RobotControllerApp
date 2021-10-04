package hr.perisic.luka.domain.repository

import hr.perisic.luka.domain.model.MapModel
import kotlinx.coroutines.flow.Flow

interface MapRepository {

    fun getMap(): Flow<MapModel>
}