package fi.kroon.krisinformation.domain

import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.functional.Either
import fi.kroon.krisinformation.data.theme.ThemeRepository
import fi.kroon.krisinformation.data.theme.model.ThemeResponse
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class ThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {
    fun get(forceCacheInvalidation: Boolean = false): Single<Either<Failure, ThemeResponse>> {
        return themeRepository
            .get(forceCacheInvalidation)
            .doOnError {
                Timber.d("$it")
            }
    }
}