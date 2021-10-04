package hr.perisic.luka.data.db.source

import hr.perisic.luka.data.db.dao.AuthModelDao
import hr.perisic.luka.data.db.model.AuthModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class AuthDbSourceImpl(
    private val authModelDao: AuthModelDao
) : AuthDbSource {

    override suspend fun saveAuth(authModel: AuthModel) = authModelDao.insert(authModel)

    override fun getIsLoggedIn(): Flow<Boolean> = authModelDao.getCount().map { it > 0 }

    override fun getAuthModel(): Flow<AuthModel> = authModelDao.getSingle()

    override suspend fun deleteAuthModel() = authModelDao.delete()
}