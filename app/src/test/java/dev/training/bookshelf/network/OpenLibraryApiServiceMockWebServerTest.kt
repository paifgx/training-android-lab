package dev.training.bookshelf.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class OpenLibraryApiServiceMockWebServerTest {

    private lateinit var server: MockWebServer
    private lateinit var service: OpenLibraryApiService

    @Before
    fun setup() {
        server = MockWebServer()
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        service = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(OpenLibraryApiService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `searchBooks sends query and parses Open Library response`() = runTest {
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(
                    """
                    {
                      "docs": [
                        {
                          "key": "/works/OL1W",
                          "title": "Kotlin in Action",
                          "author_name": ["Dmitry Jemerov", "Svetlana Isakova"],
                          "first_publish_year": 2017,
                          "cover_i": 12345,
                          "edition_count": 3,
                          "first_sentence": ["Kotlin is a pragmatic programming language."]
                        }
                      ]
                    }
                    """.trimIndent()
                )
        )

        val response = service.searchBooks(
            query = "kotlin",
            limit = 1,
            fields = "key,title,author_name"
        )

        val request = server.takeRequest()
        assertEquals("/search.json?q=kotlin&limit=1&fields=key%2Ctitle%2Cauthor_name", request.path)
        val firstBook = response.docs?.firstOrNull()
        assertNotNull(firstBook)
        assertEquals("/works/OL1W", firstBook?.key)
        assertEquals("Kotlin in Action", firstBook?.title)
        assertEquals(listOf("Dmitry Jemerov", "Svetlana Isakova"), firstBook?.authors)
    }
}
