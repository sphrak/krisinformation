package fi.kroon.krisinformation.domain

import fi.kroon.krisinformation.data.exception.Failure
import fi.kroon.krisinformation.data.features.FeaturesRepository
import fi.kroon.krisinformation.data.features.model.Features
import fi.kroon.krisinformation.data.functional.Either
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class FeaturesUseCase @Inject constructor(
    private val featuresRepository: FeaturesRepository
) {
    fun get(forceCacheInvalidation: Boolean = false): Single<Either<Failure, Features>> = featuresRepository
        .get(forceCacheInvalidation)
        .doOnError {
            Timber.d("$it")
        }
}