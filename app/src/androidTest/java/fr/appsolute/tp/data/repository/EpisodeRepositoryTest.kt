package fr.appsolute.tp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import fr.appsolute.tp.data.database.DatabaseManager
import fr.appsolute.tp.data.database.RickAndMortyDatabase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class EpisodeRepositoryTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()
    private val testDispatcher = newSingleThreadContext("UI context")
    private lateinit var repository: EpisodeRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = EpisodeRepository.instance
    }

    @After
    fun tearDown() {
        databaseManager.database.clearAllTables()
        Dispatchers.resetMain()
    }

    @Test
    fun getAllEpisode() = runBlockingTest {
        repository.getAllEpisode(List(31) { it + 1 }).run {
            assertEquals("Size of the list must be 31", 31, this.size)
        }
        val totalList = databaseManager.database.episodeDao.selectAll()
        assertEquals("Size of total list in database must be 31", 31, totalList.size)
    }

    @Test
    fun getAllEpisodeFor5Episodes() = runBlockingTest {
        repository.getAllEpisode(List(5) { it + 1 }).run {
            assertEquals("Size of the list must be 5", 5, this.size)
        }
        val totalList = databaseManager.database.episodeDao.selectAll()
        assertEquals("Size of total list in database must be 31", 31, totalList.size)
    }


    companion object {

        private lateinit var databaseManager: DatabaseManager

        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            DatabaseManager.override = object : DatabaseManager {
                override val database: RickAndMortyDatabase = Room.inMemoryDatabaseBuilder(
                    InstrumentationRegistry.getInstrumentation().targetContext,
                    RickAndMortyDatabase::class.java
                ).build()
            }.also {
                databaseManager = it
            }
        }

    }

}