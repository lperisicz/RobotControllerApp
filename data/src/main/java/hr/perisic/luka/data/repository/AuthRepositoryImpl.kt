package hr.perisic.luka.data.repository

import hr.perisic.luka.data.api.source.AuthApiSource
import hr.perisic.luka.data.db.model.AuthModel
import hr.perisic.luka.data.db.source.AuthDbSource
import hr.perisic.luka.domain.model.LoginRequestModel
import hr.perisic.luka.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

internal class AuthRepositoryImpl(
    private val authApiSource: AuthApiSource,
    private val authDbSource: AuthDbSource
): AuthRepository {

    override suspend fun loginUser(loginRequestModel: LoginRequestModel) {
        val loginResponse = authApiSource.loginUser(loginRequestModel)
        authDbSource.saveAuth(
            AuthModel(
                email = loginRequestModel.email,
                token = loginResponse.token
            )
        )
    }

    override fun getIsLoggedIn(): Flow<Boolean> = authDbSource.getIsLoggedIn()
}