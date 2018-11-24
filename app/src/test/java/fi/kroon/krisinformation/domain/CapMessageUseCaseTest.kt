package fi.kroon.krisinformation.domain

import fi.kroon.krisinformation.BaseUnitTest
import fi.kroon.krisinformation.common.Schedulers
import fi.kroon.krisinformation.data.capmessage.CapMessageRepository
import fi.kroon.krisinformation.data.capmessage.model.CapMessage
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.filter.FilterRepository
import fi.kroon.krisinformation.data.filter.model.Filter
import fi.kroon.krisinformation.data.functional.Either
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn

class CapMessageUseCaseTest : BaseUnitTest() {

    @Mock
    private lateinit var mockCapMessageRepository: CapMessageRepository

    @Mock
    private lateinit var mockFilterRepository: FilterRepository

    /*@Mock
    private lateinit var filterLocalDataSource: FilterLocalDataSource*/

    @Mock
    private lateinit var mockCapMessageList: List<CapMessage>

    private lateinit var mockCapMessageUseCase: CapMessageUseCase

    private lateinit var mockSchedulers: Schedulers

    @Mock
    private lateinit var mockFilterUseCase: FilterUseCase

    @Mock
    private lateinit var mockFilterList: List<Filter>

    private val testThrowable = Throwable()

    @Before
    fun setup() {
        mockSchedulers = Schedulers()
        // mockFilterUseCase = FilterUseCase(mockFilterRepository)
        mockCapMessageUseCase = CapMessageUseCase(
            mockCapMessageRepository,
            mockFilterUseCase,
            mockSchedulers
        )
    }

    /*@Test
    fun `usecase returns data as single`() {

        val testCapMessage = Either.Right(mockCapMessageList) as Either<Failure, List<CapMessage>>
        val testSingle = Single.just(testCapMessage)
        val testFilterInsertSuccess = Single.just(listOf("1", "2"))

        doReturn(testFilterInsertSuccess)
            .`when`(mockFilterUseCase)
            .insert(mockFilterList)

        doReturn(testSingle)
            .`when`(mockCapMessageRepository)
            .get()

        mockCapMessageUseCase
            .get()
            .test()
            .assertResult(testCapMessage)
    }*/

    @Test
    fun `usecase fails and returns runtime exception`() {
        val testFailure = Either.Left(Failure.RuntimeException())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockCapMessageRepository)
            .get()

        mockCapMessageUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails and returns network offline exception`() {
        val testFailure = Either.Left(Failure.NetworkOfflineFailure())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockCapMessageRepository)
            .get()

        mockCapMessageUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails and returns http forbidden 403`() {
        val testFailure = Either.Left(Failure.HttpForbidden403())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockCapMessageRepository)
            .get()

        mockCapMessageUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails and returns http bad request 400`() {
        val testFailure = Either.Left(Failure.HttpBadRequest400())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockCapMessageRepository)
            .get()

        mockCapMessageUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails and returns http internal server error 500`() {
        val testFailure = Either.Left(Failure.HttpInternalServerError500())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockCapMessageRepository)
            .get()

        mockCapMessageUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails and returns http service unavailable 503`() {
        val testFailure = Either.Left(Failure.HttpServiceUnavailable503())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockCapMessageRepository)
            .get()

        mockCapMessageUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails and returns http gateway timeout 504`() {
        val testFailure = Either.Left(Failure.HttpGatewayTimeout504())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockCapMessageRepository)
            .get()

        mockCapMessageUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails and returns network exception`() {
        val testFailure = Either.Left(Failure.NetworkException())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockCapMessageRepository)
            .get()

        mockCapMessageUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails with throwable`() {
        val testThrowableSingle = Single.error<Either<Failure, List<CapMessage>>>(testThrowable)
        doReturn(testThrowableSingle)
            .`when`(mockCapMessageRepository)
            .get()

        mockCapMessageUseCase
            .get()
            .test()
            .assertError(Throwable::class.java)
    }
}