package hr.perisic.luka.data.api.service

import hr.perisic.luka.domain.model.NavigationPointModel

internal class PatrolPointsApiMock : PatrolPointsApi {

    override suspend fun getPatrolPoints(): List<NavigationPointModel> = listOf(
        NavigationPointModel(
            150,
            200,
            130
        )
    )
}