package fr.appsolute.tp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.appsolute.tp.data.database.dao.EpisodeDao
import fr.appsolute.tp.data.model.Episode

@Database(
    entities = [
        Episode::class
    ],
    version = 1,
    exportSchema = true
)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract val episodeDao: EpisodeDao
}