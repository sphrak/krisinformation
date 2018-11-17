package fi.kroon.krisinformation.data.theme

import fi.kroon.krisinformation.data.HEADER_NO_CACHE
import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.functional.Either
import fi.kroon.krisinformation.data.theme.model.ThemeResponse
import fi.kroon.krisinformation.data.theme.net.ThemeApi
import fi.kroon.krisinformation.di.component.scope.KrisAppScope
import fi.kroon.vadret.utils.NetworkHandler
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

@KrisAppScope
class ThemeRepository @Inject constructor(
    private val themeApi: ThemeApi,
    private val networkHandler: NetworkHandler
) {
    fun get(forceCacheInvalidation: Boolean = false): Single<Either<Failure, ThemeResponse>> =
        when (networkHandler.isConnected) {
            true -> themeApi.get(cacheControl = getCacheHeader(forceCacheInvalidation)).map {
                when (it.code()) {
                    200 -> Either.Right(it.body()!!)
                    403 -> Either.Left(Failure.HttpForbidden403())
                    400 -> Either.Left(Failure.HttpBadRequest400())
                    500 -> Either.Left(Failure.HttpInternalServerError500())
                    503 -> Either.Left(Failure.HttpServiceUnavailable503())
                    504 -> Either.Left(Failure.HttpGatewayTimeout504())
                    else -> Either.Left(Failure.NetworkException())
                }
            }.doOnError {
                Timber.d("Error occurred: $it")
            }.onErrorReturn {
                Either.Left(Failure.RuntimeException())
            }
            else -> Single.just(Either.Left(Failure.NetworkOfflineFailure()))
        }

    private fun getCacheHeader(forceCacheInvalidation: Boolean) = if (networkHandler.isConnected && forceCacheInvalidation) {
        HEADER_NO_CACHE
    } else {
        null
    }
}