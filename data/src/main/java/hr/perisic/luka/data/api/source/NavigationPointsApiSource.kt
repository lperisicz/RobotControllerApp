package hr.perisic.luka.data.api.source

import hr.perisic.luka.domain.model.NavigationPointModel
import kotlinx.coroutines.flow.Flow

internal interface NavigationPointsApiSource {

    fun getNavigationPoints(): Flow<List<NavigationPointModel>>
}