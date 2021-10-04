package hr.perisic.luka.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import hr.perisic.luka.data.db.dao.AuthModelDao
import hr.perisic.luka.data.db.model.AuthModel

private const val DB_VERSION = 1
internal const val DB_NAME = "auth_database"

@Database(entities = [AuthModel::class], version = DB_VERSION)
internal abstract class AuthDatabase: RoomDatabase() {

    abstract fun authModelDao(): AuthModelDao
}