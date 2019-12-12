package fr.appsolute.tp.data.database.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import fr.appsolute.tp.data.database.RickAndMortyDatabase
import fr.appsolute.tp.data.model.Episode
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class EpisodeDaoTest {

    // region episodeReference
    private val episodeReference = Episode(
        1,
        "Pilot",
        "December 2, 2013",
        "S01E01",
        emptyList(),
        "https://rickandmortyapi.com/api/episode/1",
        "2017-11-10T12:56:33.798Z"
    )
    // endregion

    private lateinit var dao: EpisodeDao

    @Before
    fun setUp() {
        dao = database.episodeDao
    }

    @Test
    fun selectAll() = runBlocking {
        dao.insertAll(episodeReference)
        assertEquals("Should be same", episodeReference, dao.selectAll().first())
    }

    @Test
    fun getCount() = runBlocking {
        dao.insertAll(episodeReference)
        assertEquals("Should be same", 1, dao.getCount())
    }

    @Test
    fun insertAll() = runBlocking {
        dao.insertAll(episodeReference)
        assertEquals("Should be same", episodeReference, dao.selectAll().first())
    }

    companion object {
        private lateinit var database: RickAndMortyDatabase

        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            database = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().targetContext,
                RickAndMortyDatabase::class.java
            ).build()
        }
    }
}