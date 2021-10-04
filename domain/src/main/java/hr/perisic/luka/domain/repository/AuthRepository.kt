package hr.perisic.luka.domain.repository

import hr.perisic.luka.domain.model.LoginRequestModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun loginUser(loginRequestModel: LoginRequestModel)

    fun getIsLoggedIn(): Flow<Boolean>
}