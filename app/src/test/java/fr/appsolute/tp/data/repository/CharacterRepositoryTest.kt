package fr.appsolute.tp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import fr.appsolute.tp.data.model.Character
import fr.appsolute.tp.test.getBlockingValue
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.assertTrue

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class CharacterRepositoryTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()
    private val testDispatcher = newSingleThreadContext("UI context")
    private lateinit var repository: CharacterRepository

    private val rick = Character(
        1,
        "Rick Sanchez",
        "Alive",
        "Human",
        "",
        "Male",
        Character.Place(
            "Earth (C-137)",
            "https://rickandmortyapi.com/api/location/1"
        ),
        Character.Place(
            "Earth (Replacement Dimension)",
            "https://rickandmortyapi.com/api/location/20"
        ),
        "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2",
            "https://rickandmortyapi.com/api/episode/3",
            "https://rickandmortyapi.com/api/episode/4",
            "https://rickandmortyapi.com/api/episode/5",
            "https://rickandmortyapi.com/api/episode/6",
            "https://rickandmortyapi.com/api/episode/7",
            "https://rickandmortyapi.com/api/episode/8",
            "https://rickandmortyapi.com/api/episode/9",
            "https://rickandmortyapi.com/api/episode/10",
            "https://rickandmortyapi.com/api/episode/11",
            "https://rickandmortyapi.com/api/episode/12",
            "https://rickandmortyapi.com/api/episode/13",
            "https://rickandmortyapi.com/api/episode/14",
            "https://rickandmortyapi.com/api/episode/15",
            "https://rickandmortyapi.com/api/episode/16",
            "https://rickandmortyapi.com/api/episode/17",
            "https://rickandmortyapi.com/api/episode/18",
            "https://rickandmortyapi.com/api/episode/19",
            "https://rickandmortyapi.com/api/episode/20",
            "https://rickandmortyapi.com/api/episode/21",
            "https://rickandmortyapi.com/api/episode/22",
            "https://rickandmortyapi.com/api/episode/23",
            "https://rickandmortyapi.com/api/episode/24",
            "https://rickandmortyapi.com/api/episode/25",
            "https://rickandmortyapi.com/api/episode/26",
            "https://rickandmortyapi.com/api/episode/27",
            "https://rickandmortyapi.com/api/episode/28",
            "https://rickandmortyapi.com/api/episode/29",
            "https://rickandmortyapi.com/api/episode/30",
            "https://rickandmortyapi.com/api/episode/31"
        ),
        "https://rickandmortyapi.com/api/character/1",
        "2017-11-04T18:48:46.250Z"
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = CharacterRepository.instance
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.close()
    }

    @Test
    fun getPaginatedListTest() = runBlocking {
        val value = repository.getPaginatedList(this).getBlockingValue(
            timeOut = 10
        )
        assertTrue(
            "Size should 0 or 20",
            value?.count()?.equals(0) ?: false || value?.count()?.equals(20) ?: false
        )
    }

    @Test
    fun getCharacterDetails() = runBlocking {
        val value = repository.getCharacterDetails(1)
        Assert.assertEquals("Should be Rick", rick, value)
    }
}