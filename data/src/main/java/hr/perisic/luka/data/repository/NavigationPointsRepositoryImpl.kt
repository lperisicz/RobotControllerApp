package hr.perisic.luka.data.repository

import hr.perisic.luka.data.api.source.NavigationPointsApiSource
import hr.perisic.luka.domain.model.NavigationPointModel
import hr.perisic.luka.domain.repository.NavigationPointsRepository
import kotlinx.coroutines.flow.Flow

internal class NavigationPointsRepositoryImpl(
    private val navigationPointsApiSource: NavigationPointsApiSource
) : NavigationPointsRepository {

    override fun getNavigationPoints(): Flow<List<NavigationPointModel>> = navigationPointsApiSource.getNavigationPoints()
}