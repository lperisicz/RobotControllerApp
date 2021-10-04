package hr.perisic.luka.data.db.source

import hr.perisic.luka.data.db.model.AuthModel
import kotlinx.coroutines.flow.Flow

internal interface AuthDbSource {

    suspend fun saveAuth(authModel: AuthModel)

    fun getIsLoggedIn(): Flow<Boolean>

    fun getAuthModel(): Flow<AuthModel?>

    suspend fun deleteAuthModel()
}