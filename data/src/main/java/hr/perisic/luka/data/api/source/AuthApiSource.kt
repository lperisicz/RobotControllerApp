package hr.perisic.luka.data.api.source

import hr.perisic.luka.data.api.model.LoginResponseApiModel
import hr.perisic.luka.domain.model.LoginRequestModel

internal interface AuthApiSource {

    suspend fun loginUser(loginRequestModel: LoginRequestModel): LoginResponseApiModel
}