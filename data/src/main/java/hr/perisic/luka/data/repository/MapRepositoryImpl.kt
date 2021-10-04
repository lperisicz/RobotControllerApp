package hr.perisic.luka.data.repository

import hr.perisic.luka.data.api.source.MapApiSource
import hr.perisic.luka.domain.model.MapModel
import hr.perisic.luka.domain.repository.MapRepository
import kotlinx.coroutines.flow.Flow

internal class MapRepositoryImpl(
    private val mapApiSource: MapApiSource
): MapRepository {

    override fun getMap(): Flow<MapModel> = mapApiSource.getMap()
}