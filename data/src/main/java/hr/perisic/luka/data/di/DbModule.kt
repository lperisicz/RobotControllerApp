package hr.perisic.luka.data.di

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import hr.perisic.luka.data.db.database.AuthDatabase
import hr.perisic.luka.data.db.source.AuthDbSource
import hr.perisic.luka.data.db.source.AuthDbSourceImpl
import org.koin.dsl.module
import hr.perisic.luka.data.db.database.DB_NAME as AUTH_DB_NAME

internal val dbModule = module {

    single {
        Room.databaseBuilder(get(), AuthDatabase::class.java, AUTH_DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<AuthDatabase>().authModelDao()
    }

    single<AuthDbSource> {
        AuthDbSourceImpl(
            authModelDao = get()
        )
    }
}

//TODO delete
private fun test() {
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE `auth` ADD COLUMN refresh_toke TEXT")
    }
}

    MIGRATION_1_2
}