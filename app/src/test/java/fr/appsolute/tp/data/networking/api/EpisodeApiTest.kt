package fr.appsolute.tp.data.networking.api

import fr.appsolute.tp.data.model.Episode
import fr.appsolute.tp.data.networking.HttpClientManager
import fr.appsolute.tp.data.networking.createApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class EpisodeApiTest {

    private lateinit var api: EpisodeApi
    private val episodeReference = Episode(
        1,
        "Pilot",
        "December 2, 2013",
        "S01E01",
        listOf(
            "https://rickandmortyapi.com/api/character/1",
            "https://rickandmortyapi.com/api/character/2",
            "https://rickandmortyapi.com/api/character/35",
            "https://rickandmortyapi.com/api/character/38",
            "https://rickandmortyapi.com/api/character/62",
            "https://rickandmortyapi.com/api/character/92",
            "https://rickandmortyapi.com/api/character/127",
            "https://rickandmortyapi.com/api/character/144",
            "https://rickandmortyapi.com/api/character/158",
            "https://rickandmortyapi.com/api/character/175",
            "https://rickandmortyapi.com/api/character/179",
            "https://rickandmortyapi.com/api/character/181",
            "https://rickandmortyapi.com/api/character/239",
            "https://rickandmortyapi.com/api/character/249",
            "https://rickandmortyapi.com/api/character/271",
            "https://rickandmortyapi.com/api/character/338",
            "https://rickandmortyapi.com/api/character/394",
            "https://rickandmortyapi.com/api/character/395",
            "https://rickandmortyapi.com/api/character/435"
        ),
        "https://rickandmortyapi.com/api/episode/1",
        "2017-11-10T12:56:33.798Z"
    )

    @Before
    fun setUp() {
        api = HttpClientManager.instance.createApi()
    }

    @Test
    fun getAllEpisode() = runBlocking {
        val response = api.getAllEpisode(1)
        assertTrue("Response must be a success", response.isSuccessful)
        assertEquals(
            "First episode must be the reference",
            episodeReference,
            response.body()?.results?.first()
        )
    }
}