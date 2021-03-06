package fi.kroon.krisinformation.data

import fi.kroon.krisinformation.BaseUnitTest
import fi.kroon.krisinformation.common.NetworkHandler
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.feed.FeedRepository
import fi.kroon.krisinformation.data.feed.model.FeedResponse
import fi.kroon.krisinformation.data.feed.net.FeedApi
import fi.kroon.krisinformation.data.functional.Either
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import retrofit2.Response

class FeedRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var mockFeedApi: FeedApi

    @Mock
    private lateinit var mockNetworkHandler: NetworkHandler

    @Mock
    private lateinit var mockResponse: Response<FeedResponse>

    @Mock
    private lateinit var mockFeedResponse: FeedResponse

    private lateinit var mockFeedRepository: FeedRepository

    @Before
    fun setup() {
        mockFeedRepository = FeedRepository(mockFeedApi, mockNetworkHandler)
    }

    @Test
    fun `network handler not connected should return network failure`() {
        doReturn(false).`when`(mockNetworkHandler).isConnected

        mockFeedRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.NetworkOfflineFailure }
    }

    @Test
    fun `network handler is connected and returns response`() {

        doReturn(true).`when`(mockNetworkHandler).isConnected
        doReturn(200).`when`(mockResponse).code()
        doReturn(mockFeedResponse).`when`(mockResponse).body()
        doReturn(Single.just(mockResponse)).`when`(mockFeedApi).get()

        mockFeedRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Right<FeedResponse> && it.b == mockFeedResponse }
    }

    @Test
    fun `network connected but response code is 403`() {
        doReturn(403).`when`(mockResponse).code()
        doReturn(true).`when`(mockNetworkHandler).isConnected
        doReturn(Single.just(mockResponse)).`when`(mockFeedApi).get()

        mockFeedRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.HttpForbidden403 }
    }

    @Test
    fun `network connected but response code is 400`() {
        doReturn(400).`when`(mockResponse).code()
        doReturn(true).`when`(mockNetworkHandler).isConnected
        doReturn(Single.just(mockResponse)).`when`(mockFeedApi).get()

        mockFeedRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.HttpBadRequest400 }
    }

    @Test
    fun `network connected but response code is 500`() {
        doReturn(500).`when`(mockResponse).code()
        doReturn(true).`when`(mockNetworkHandler).isConnected
        doReturn(Single.just(mockResponse)).`when`(mockFeedApi).get()

        mockFeedRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.HttpInternalServerError500 }
    }

    @Test
    fun `network connected but response code is 503`() {
        doReturn(503).`when`(mockResponse).code()
        doReturn(true).`when`(mockNetworkHandler).isConnected
        doReturn(Single.just(mockResponse)).`when`(mockFeedApi).get()

        mockFeedRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.HttpServiceUnavailable503 }
    }

    @Test
    fun `network connected but response code is 504`() {
        doReturn(504).`when`(mockResponse).code()
        doReturn(true).`when`(mockNetworkHandler).isConnected
        doReturn(Single.just(mockResponse)).`when`(mockFeedApi).get()

        mockFeedRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.HttpGatewayTimeout504 }
    }

    @Test
    fun `network connected but response code is unknown`() {
        doReturn(999).`when`(mockResponse).code()
        doReturn(true).`when`(mockNetworkHandler).isConnected
        doReturn(Single.just(mockResponse)).`when`(mockFeedApi).get()

        mockFeedRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.NetworkException }
    }
}