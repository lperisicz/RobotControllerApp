package hr.perisic.luka.data.api.service

import hr.perisic.luka.data.api.model.LoginResponseApiModel
import hr.perisic.luka.domain.model.LoginRequestModel
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthApi {

    @POST("client/")
    suspend fun loginUser(@Body loginRequest: LoginRequestModel): LoginResponseApiModel
}