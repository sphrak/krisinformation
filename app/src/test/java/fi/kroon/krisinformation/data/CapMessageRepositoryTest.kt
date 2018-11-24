package fi.kroon.krisinformation.data

import fi.kroon.krisinformation.BaseUnitTest
import fi.kroon.krisinformation.common.NetworkHandler
import fi.kroon.krisinformation.data.capmessage.CapMessageRepository
import fi.kroon.krisinformation.data.capmessage.model.CapMessage
import fi.kroon.krisinformation.data.capmessage.net.CapMessageApi
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.functional.Either
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import retrofit2.Response

class CapMessageRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var mockCapMessageApi: CapMessageApi

    @Mock
    private lateinit var mockNetworkHandler: NetworkHandler

    @Mock
    private lateinit var mockResponse: Response<List<CapMessage>>

    @Mock
    private lateinit var mockListCapMessage: List<CapMessage>

    private lateinit var mockCapMessageRepository: CapMessageRepository

    private val testThrowable = Throwable()

    @Before
    fun setup() {
        mockCapMessageRepository = CapMessageRepository(mockCapMessageApi, mockNetworkHandler)
    }

    @Test
    fun `network handler not connected should return network failure`() {
        doReturn(false).`when`(mockNetworkHandler).isConnected

        mockCapMessageRepository
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
        doReturn(mockListCapMessage).`when`(mockResponse).body()
        doReturn(Single.just(mockResponse)).`when`(mockCapMessageApi).get()

        mockCapMessageRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Right<List<CapMessage>> && it.b == mockListCapMessage }
    }

    @Test
    fun `network connected but response code is 403`() {
        doReturn(403).`when`(mockResponse).code()
        doReturn(true).`when`(mockNetworkHandler).isConnected
        doReturn(Single.just(mockResponse)).`when`(mockCapMessageApi).get()

        mockCapMessageRepository
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
        doReturn(Single.just(mockResponse)).`when`(mockCapMessageApi).get()

        mockCapMessageRepository
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
        doReturn(Single.just(mockResponse)).`when`(mockCapMessageApi).get()

        mockCapMessageRepository
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
        doReturn(Single.just(mockResponse)).`when`(mockCapMessageApi).get()

        mockCapMessageRepository
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
        doReturn(Single.just(mockResponse)).`when`(mockCapMessageApi).get()

        mockCapMessageRepository
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
        doReturn(Single.just(mockResponse)).`when`(mockCapMessageApi).get()

        mockCapMessageRepository
            .get()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueAt(0) { it is Either.Left<Failure> && it.a is Failure.NetworkException }
    }

    /*@Test
    fun `repository fails and returns throwable`() {
        val testThrowableSingle = Single.error<Either<Failure, List<CapMessage>>>(testThrowable)
        doReturn(testThrowableSingle)
            .`when`(mockCapMessageApi)
            .get()

        mockCapMessageRepository
            .get()
            .test()
            .assertError(Throwable::class.java)
    }*/
}