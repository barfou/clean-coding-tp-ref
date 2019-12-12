package fr.appsolute.tp.data.database

import androidx.room.Room
import fr.appsolute.tp.RickAndMortyApplication


private class DatabaseManagerImpl(
    override val database: RickAndMortyDatabase
) : DatabaseManager

interface DatabaseManager {

    val database: RickAndMortyDatabase

    companion object {
        private const val DATABASE_NAME = "rick_and_morty.db"

        @Volatile
        private var databaseManager: DatabaseManager? = null

        var override: DatabaseManager? = null

        fun getInstance(app: RickAndMortyApplication? = null): DatabaseManager {
            return override ?: databaseManager ?: synchronized(this) {
                DatabaseManagerImpl(
                    Room.databaseBuilder(
                        app ?: throw IllegalStateException("No Application"),
                        RickAndMortyDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                ).also {
                    databaseManager = it
                }
            }
        }

    }
}