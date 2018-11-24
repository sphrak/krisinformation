package fi.kroon.krisinformation.data

import fi.kroon.krisinformation.BaseUnitTest
import fi.kroon.krisinformation.common.NetworkHandler
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.features.FeaturesRepository
import fi.kroon.krisinformation.data.features.model.Features
import fi.kroon.krisinformation.data.features.net.FeaturesApi
import fi.kroon.krisinformation.data.functional.Either
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Response

class FeaturesRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var mockFeaturesApi: FeaturesApi

    @Mock
    private lateinit var mockNetworkHandler: NetworkHandler

    @Mock
    private lateinit var mockResponse: Response<Features>

    @Mock
    private lateinit var mockFeatures: Features

    private lateinit var mockFeaturesRepository: FeaturesRepository

    @Before
    fun setup() {
        mockFeaturesRepository = FeaturesRepository(mockFeaturesApi, mockNetworkHandler)
    }

    @Test
    fun `network handler not connected should return network failure`() {
        Mockito.doReturn(false).`when`(mockNetworkHandler).isConnected

        mockFeaturesRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.NetworkOfflineFailure }
    }

    @Test
    fun `network handler is connected and returns response`() {
        Mockito.doReturn(200).`when`(mockResponse).code()
        Mockito.doReturn(true).`when`(mockNetworkHandler).isConnected
        Mockito.doReturn(mockFeatures).`when`(mockResponse).body()
        Mockito.doReturn(Single.just(mockResponse)).`when`(mockFeaturesApi).get()

        mockFeaturesRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Right<Features> && it.b == mockFeatures }
    }

    @Test
    fun `network connected but response code is 403`() {
        Mockito.doReturn(403).`when`(mockResponse).code()
        Mockito.doReturn(true).`when`(mockNetworkHandler).isConnected
        Mockito.doReturn(Single.just(mockResponse)).`when`(mockFeaturesApi).get()

        mockFeaturesRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.HttpForbidden403 }
    }

    @Test
    fun `network connected but response code is 400`() {
        Mockito.doReturn(400).`when`(mockResponse).code()
        Mockito.doReturn(true).`when`(mockNetworkHandler).isConnected
        Mockito.doReturn(Single.just(mockResponse)).`when`(mockFeaturesApi).get()

        mockFeaturesRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.HttpBadRequest400 }
    }

    @Test
    fun `network connected but response code is 500`() {
        Mockito.doReturn(500).`when`(mockResponse).code()
        Mockito.doReturn(true).`when`(mockNetworkHandler).isConnected
        Mockito.doReturn(Single.just(mockResponse)).`when`(mockFeaturesApi).get()

        mockFeaturesRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.HttpInternalServerError500 }
    }

    @Test
    fun `network connected but response code is 503`() {
        Mockito.doReturn(503).`when`(mockResponse).code()
        Mockito.doReturn(true).`when`(mockNetworkHandler).isConnected
        Mockito.doReturn(Single.just(mockResponse)).`when`(mockFeaturesApi).get()

        mockFeaturesRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.HttpServiceUnavailable503 }
    }

    @Test
    fun `network connected but response code is 504`() {
        Mockito.doReturn(504).`when`(mockResponse).code()
        Mockito.doReturn(true).`when`(mockNetworkHandler).isConnected
        Mockito.doReturn(Single.just(mockResponse)).`when`(mockFeaturesApi).get()

        mockFeaturesRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.HttpGatewayTimeout504 }
    }

    @Test
    fun `network connected but response code is unknown`() {
        Mockito.doReturn(999).`when`(mockResponse).code()
        Mockito.doReturn(true).`when`(mockNetworkHandler).isConnected
        Mockito.doReturn(Single.just(mockResponse)).`when`(mockFeaturesApi).get()

        mockFeaturesRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.NetworkException }
    }
}