package hr.perisic.luka.data.api.source

import hr.perisic.luka.domain.model.MapModel
import kotlinx.coroutines.flow.Flow

internal interface MapApiSource {

    fun getMap(): Flow<MapModel>
}