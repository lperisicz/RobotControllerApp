package hr.perisic.luka.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth")
internal data class AuthModel(
    @PrimaryKey @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "token") val token: String
)