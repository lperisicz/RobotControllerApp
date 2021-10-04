package hr.perisic.luka.data.api.service

import hr.perisic.luka.domain.model.NavigationPointModel
import retrofit2.http.GET

internal interface PatrolPointsApi {

    @GET("points/details/1")
    suspend fun getPatrolPoints(): List<NavigationPointModel>
}