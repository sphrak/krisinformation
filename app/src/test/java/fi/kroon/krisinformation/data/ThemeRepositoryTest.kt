package fi.kroon.krisinformation.data

import fi.kroon.krisinformation.BaseUnitTest
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.functional.Either
import fi.kroon.krisinformation.data.theme.ThemeRepository
import fi.kroon.krisinformation.data.theme.model.ThemeResponse
import fi.kroon.krisinformation.data.theme.net.ThemeApi
import fi.kroon.vadret.utils.NetworkHandler
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import retrofit2.Response

class ThemeRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var mockThemeApi: ThemeApi

    @Mock
    private lateinit var mockNetworkHandler: NetworkHandler

    @Mock
    private lateinit var mockResponse: Response<ThemeResponse>

    @Mock
    private lateinit var mockThemeResponse: ThemeResponse

    private lateinit var mockThemeRepository: ThemeRepository

    @Before
    fun setup() {
        mockThemeRepository = ThemeRepository(mockThemeApi, mockNetworkHandler)
    }

    @Test
    fun `network handler not connected should return network failure`() {
        doReturn(false).`when`(mockNetworkHandler).isConnected

        mockThemeRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.NetworkOfflineFailure }
    }

    @Test
    fun `network handler is connected and returns response`() {
        doReturn(200).`when`(mockResponse).code()
        doReturn(true).`when`(mockNetworkHandler).isConnected
        doReturn(mockThemeResponse).`when`(mockResponse).body()
        doReturn(Single.just(mockResponse)).`when`(mockThemeApi).get()

        mockThemeRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Right<ThemeResponse> && it.b == mockThemeResponse }
    }

    @Test
    fun `network connected but response code is 403`() {
        doReturn(403).`when`(mockResponse).code()
        doReturn(true).`when`(mockNetworkHandler).isConnected
        doReturn(Single.just(mockResponse)).`when`(mockThemeApi).get()

        mockThemeRepository
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
        doReturn(Single.just(mockResponse)).`when`(mockThemeApi).get()

        mockThemeRepository
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
        doReturn(Single.just(mockResponse)).`when`(mockThemeApi).get()

        mockThemeRepository
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
        doReturn(Single.just(mockResponse)).`when`(mockThemeApi).get()

        mockThemeRepository
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
        doReturn(Single.just(mockResponse)).`when`(mockThemeApi).get()

        mockThemeRepository
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
        doReturn(Single.just(mockResponse)).`when`(mockThemeApi).get()

        mockThemeRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.NetworkException }
    }
}