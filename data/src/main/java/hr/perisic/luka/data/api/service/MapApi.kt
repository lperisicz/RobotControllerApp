package hr.perisic.luka.data.api.service

import hr.perisic.luka.domain.model.MapModel
import retrofit2.http.GET

internal interface MapApi {

    @GET("maps/1")
    suspend fun getMap(): MapModel
}