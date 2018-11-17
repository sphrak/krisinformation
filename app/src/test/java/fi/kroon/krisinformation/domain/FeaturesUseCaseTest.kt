package fi.kroon.krisinformation.domain

import fi.kroon.krisinformation.BaseUnitTest
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.features.FeaturesRepository
import fi.kroon.krisinformation.data.features.model.Features
import fi.kroon.krisinformation.data.functional.Either
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn

class FeaturesUseCaseTest : BaseUnitTest() {

    @Mock
    private lateinit var mockFeaturesRepository: FeaturesRepository

    @Mock
    private lateinit var mockFeatures: Features

    private lateinit var mockFeaturesUseCase: FeaturesUseCase

    private val testThrowable = Throwable()

    @Before
    fun setup() {
        mockFeaturesUseCase = FeaturesUseCase(mockFeaturesRepository)
    }

    @Test
    fun `usecase returns data as single`() {
        val testCapMessage = Either.Right(mockFeatures) as Either<Failure, Features>
        val testSingle = Single.just(testCapMessage)

        doReturn(testSingle)
            .`when`(mockFeaturesRepository)
            .get()

        mockFeaturesUseCase
            .get()
            .test()
            .assertResult(testCapMessage)
    }

    @Test
    fun `usecase fails and returns runtime exception`() {
        val testFailure = Either.Left(Failure.RuntimeException())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockFeaturesRepository)
            .get()

        mockFeaturesUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails and returns network offline exception`() {
        val testFailure = Either.Left(Failure.NetworkOfflineFailure())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockFeaturesRepository)
            .get()

        mockFeaturesUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails and returns http forbidden 403`() {
        val testFailure = Either.Left(Failure.HttpForbidden403())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockFeaturesRepository)
            .get()

        mockFeaturesUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails and returns http bad request 400`() {
        val testFailure = Either.Left(Failure.HttpBadRequest400())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockFeaturesRepository)
            .get()

        mockFeaturesUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails and returns http internal server error 500`() {
        val testFailure = Either.Left(Failure.HttpInternalServerError500())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockFeaturesRepository)
            .get()

        mockFeaturesUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails and returns http service unavailable 503`() {
        val testFailure = Either.Left(Failure.HttpServiceUnavailable503())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockFeaturesRepository)
            .get()

        mockFeaturesUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails and returns http gateway timeout 504`() {
        val testFailure = Either.Left(Failure.HttpGatewayTimeout504())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockFeaturesRepository)
            .get()

        mockFeaturesUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails and returns network exception`() {
        val testFailure = Either.Left(Failure.NetworkException())
        val testFailureSingle = Single.just(testFailure)
        doReturn(testFailureSingle)
            .`when`(mockFeaturesRepository)
            .get()

        mockFeaturesUseCase
            .get()
            .test()
            .assertResult(testFailure)
    }

    @Test
    fun `usecase fails with throwable`() {
        val testThrowableSingle = Single.error<Either<Failure, Features>>(testThrowable)
        doReturn(testThrowableSingle)
            .`when`(mockFeaturesRepository)
            .get()

        mockFeaturesUseCase
            .get()
            .test()
            .assertError(Throwable::class.java)
    }
}