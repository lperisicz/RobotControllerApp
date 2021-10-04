package hr.perisic.luka.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.perisic.luka.data.db.model.AuthModel
import kotlinx.coroutines.flow.Flow

@Dao
internal interface AuthModelDao {

    @Query("SELECT COUNT(*) FROM auth")
    fun getCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(authModel: AuthModel)

    @Query("SELECT * FROM auth LIMIT 1")
    fun getSingle(): Flow<AuthModel>

    @Query("DELETE FROM auth")
    suspend fun delete()
}