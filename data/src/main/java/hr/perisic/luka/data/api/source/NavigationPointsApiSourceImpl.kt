package hr.perisic.luka.data.api.source

import hr.perisic.luka.data.api.service.PatrolPointsApi
import hr.perisic.luka.domain.model.NavigationPointModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class NavigationPointsApiSourceImpl(
    private val navigationPointsApi: PatrolPointsApi
): NavigationPointsApiSource {

    override fun getNavigationPoints(): Flow<List<NavigationPointModel>> = flow {
        emit(navigationPointsApi.getPatrolPoints())
    }
}