package hr.perisic.luka.data.api.source

import hr.perisic.luka.data.api.model.LoginResponseApiModel
import hr.perisic.luka.data.api.service.AuthApi
import hr.perisic.luka.domain.model.LoginRequestModel

internal class AuthApiSourceImpl(
    private val authApi: AuthApi
): AuthApiSource {

    override suspend fun loginUser(loginRequestModel: LoginRequestModel): LoginResponseApiModel = authApi.loginUser(loginRequestModel)
}